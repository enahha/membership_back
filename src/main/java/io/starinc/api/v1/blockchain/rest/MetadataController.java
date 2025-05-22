package io.starinc.api.v1.blockchain.rest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.mapper.KeyValueMapper;
import io.starinc.api.v1.common.rest.CommonController;
import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.project.mapper.ProjectMapper;
import io.starinc.api.v1.project.vo.ProjectVo;

@RestController
@RequestMapping(value = "/api")
public class MetadataController {
	
	// private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
//	@Autowired
//	private CommonController commonController;
//	
//	@Autowired
//	private ProjectMapper projectMapper;
//	
//	@Autowired
//	private KeyValueMapper keyValueMapper;
//	
//	/**
//	 * 메타데이터 _collection.json 파일 생성할 JSONObject 내용 작성
//	 * 
//	 * @param projectSeq
//	 * @return CommonResultVo
//	 * @throws Exception
//	 */
//	@PostMapping("/blockchain/createSolanaCollectionJsonFile")
//	public CommonResultVo createSolanaCollectionJsonFile(@RequestParam int projectSeq) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		// project 정보 조회
//		ProjectVo projectVo = new ProjectVo();
//		projectVo.setSeq(projectSeq);
//		projectVo = this.projectMapper.selectProject(projectVo);
//		
//		if (projectVo == null) {
//			commonResultVo.setResultCd("FAIL"); // 비정상처리
//			commonResultVo.setResultMsg("selectProject failed.");
//			commonResultVo.setReturnCd("1");
//			return commonResultVo;
//		}
//		
//		// int sellerFeeBasisPoints = Integer.parseInt(this.keyValueMapper.selectKeyValue("seller_fee_basis_points"));
//		
//		// Create the root JSON object
//		JSONObject root = new JSONObject();
//		root.put("name", projectVo.getProject_name());
//		root.put("symbol", projectVo.getProject_symbol());
//		root.put("description", projectVo.getDescription());
//		root.put("seller_fee_basis_points", this.keyValueMapper.selectKeyValue("seller_fee_basis_points"));
//		root.put("image", projectVo.getImage_url());
//		
//		// Create and add the collection object
//		JSONObject collection = new JSONObject();
//		// collection.put("name", "Test Kongz Collection");
//		// collection.put("family", "Brave Kongz Family");
//		collection.put("name", projectVo.getProject_name());
//		collection.put("family", "");
//		root.put("collection", collection);
//		
////		root.put("animation_url", null);
////		
////		// Create and add the attributes array
////		JSONArray attributes = new JSONArray();
////		JSONObject attribute1 = new JSONObject();
////		attribute1.put("trait_type", "Test");
////		attribute1.put("value", "100%");
////		attributes.add(attribute1);
////		
////		JSONObject attribute2 = new JSONObject();
////		attribute2.put("trait_type", "Kongz");
////		attribute2.put("value", "Kongz!");
////		attributes.add(attribute2);
////		
////		root.put("attributes", attributes);
////		
////		root.put("external_url", "https://opensea.io/collection/brave-kongz");
//		
//		// Create and add the properties object
//		JSONObject properties = new JSONObject();
//		properties.put("category", "image");
//		
//		// Create and add the creators array
//		JSONArray creators = new JSONArray();
//		JSONObject creator = new JSONObject();
//		// creator.put("address", "4gbdX7szo3RN4nNmc8BFM6buAkfu9sKhHSbp1uo4CLUF");
//		// creator.put("address", "5Du5PuShAibKF4CtKGovjzEaxYfbDNNSpP8oKUQyjzQA");
//		creator.put("address", this.keyValueMapper.selectKeyValue("solana_creater_address"));
//		creator.put("verified", true);
//		creator.put("share", 100);
//		creators.add(creator);
//		properties.put("creators", creators);
//		
//		// Create and add the files array
//		JSONArray files = new JSONArray();
//		JSONObject file = new JSONObject();
//		file.put("type", "image/png");
//		file.put("uri", projectVo.getImage_url());
//		files.add(file);
//		properties.put("files", files);
//		
//		root.put("properties", properties);
//		
//		// JSON 파일 생성 후 URL 설정
//		String fileUrl = this.commonController.saveSolanaCollectionJsonFile(projectVo.getProject_id(), root);
//		commonResultVo.setReturnValue(fileUrl);
//		
//		return commonResultVo;
//	}
}