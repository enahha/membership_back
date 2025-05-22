package io.starinc.api.v1.snapshot.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.snapshot.mapper.SnapshotMapper;
import io.starinc.api.v1.snapshot.vo.SnapshotVo;

//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class SnapshotController {
	@Autowired
	private SnapshotMapper snapshotMapper;
	
	private String heliusGetAssetsByGroupUrl = "https://mainnet.helius-rpc.com/";
	private String heliusApiKey = "55181376-951d-4dca-ade1-79f9f82060a4";
	
	/**
	 * 스냅샷 검색 후 테이블 등록
	 * 
	 * @param contractAddress
	 * @return commonVo
	 * @throws Exception
	 */
	@GetMapping("/snapshot")
	public CommonVo insertAirdropLogList(@RequestParam String collectionAddress) throws Exception {
	CommonVo commonVo = new CommonVo();
	commonVo.setResultCd("SUCCESS");
	
	try {
		// 1. snapshot_mst 등록
		SnapshotVo snapshotVo = new SnapshotVo();
		snapshotVo.setMainnet("solana");
		snapshotVo.setCollection_address(collectionAddress);
		snapshotVo.setReg_id("SYSTEM");
		
		// `snapshot_mst` 테이블에 insert 쿼리 실행
		int mstResult = this.snapshotMapper.insertSnapshotMst(snapshotVo); // insert된 seq 값을 받음
		if (mstResult <= 0) {
		    commonVo.setResultCd("FAIL");
		    commonVo.setResultMsg("Failed to insert snapshot_mst or received invalid mstSeq.");
		    return commonVo;
		}
		
		// 2. getAssetsByGroup 조회
		String requestUrl = this.heliusGetAssetsByGroupUrl + "?api-key=" + this.heliusApiKey;
		
		// HttpClient 생성
		try (CloseableHttpClient client = HttpClients.createDefault()) { 
			ObjectMapper mapper = new ObjectMapper();
			int page = 1;
			int resultNumber = 1;
			boolean hasMoreData = true;
			
			while (hasMoreData) {
				HttpPost httpPost = new HttpPost(requestUrl);
				
				    // 요청 본문 설정
				ObjectNode paramsNode = mapper.createObjectNode();
				paramsNode.put("groupKey", "collection");
				paramsNode.put("groupValue", collectionAddress);
				paramsNode.put("page", page);
				
				ObjectNode requestNode = mapper.createObjectNode();
				requestNode.put("jsonrpc", "2.0");
				requestNode.put("id", 1);
				requestNode.put("method", "getAssetsByGroup");
				requestNode.set("params", paramsNode);
				
				StringEntity entity = new StringEntity(requestNode.toString(), "UTF-8");
				httpPost.setEntity(entity);
				
				// 헤더 설정
				httpPost.addHeader("Content-Type", "application/json");
				
				// 요청 실행
				HttpResponse response = client.execute(httpPost);
				int responseCode = response.getStatusLine().getStatusCode();
				
				if (responseCode != 200) {
					commonVo.setResultCd("FAIL");
					commonVo.setResultMsg("ResponseCode is not 200.");
					return commonVo;
				}
				
				// JSON 응답 처리
				JsonNode returnNode = mapper.readTree(response.getEntity().getContent());
				JsonNode resultArray = returnNode.path("result").path("items");
				
				// snapshotList를 한 번에 쌓아서 DB에 삽입
				List<SnapshotVo> snapshotList = new ArrayList<>();
				
				for (JsonNode nftInfoObj : resultArray) {
					SnapshotVo snapshot = new SnapshotVo();
				
					// 필요한 필드 추출
					snapshot.setOwner_address(nftInfoObj.path("ownership").path("owner").asText());
					snapshot.setJson_uri(nftInfoObj.path("content").path("json_uri").asText());
					snapshot.setNft_id(nftInfoObj.path("id").asText());
					snapshot.setNft_name(nftInfoObj.path("content").path("metadata").path("name").asText());
					
					// Rarity 추출
					JsonNode attributes = nftInfoObj.path("content").path("metadata").path("attributes");
					for (JsonNode attribute : attributes) {
						if ("Rarity".equals(attribute.path("trait_type").asText())) {
							snapshot.setRarity(attribute.path("value").asText());
							break;
						}
					}
					
					snapshot.setMst_seq(snapshotVo.getMst_seq());
					snapshot.setResult_number(resultNumber);
					snapshotList.add(snapshot);
					
					resultNumber++;
				}
				
				// DB에 insert (각 페이지마다 한 번만 호출)
				if (!snapshotList.isEmpty()) {
					int resultCount = this.snapshotMapper.insertSnapshotList(snapshotList);
					if (resultCount == 0) {
						commonVo.setResultCd("FAIL");
						commonVo.setResultMsg("insertSnapshotList failed.");
						return commonVo;
					}
				}
				
				// 결과가 pageSize보다 적다면 더 이상 데이터가 없다는 의미
				hasMoreData = resultArray.size() == 1000;
				page++;
			}
		}
	} catch (Exception e) {
		 e.printStackTrace();
		commonVo.setResultCd("FAIL");
		commonVo.setResultMsg(e.toString());
	}
		return commonVo;
	}
}
