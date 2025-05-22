package io.starinc.api.v1.airdrop.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.airdroplog.mapper.AirdropLogMapper;
import io.starinc.api.v1.airdroplog.vo.AirdropLogVo;
import io.starinc.api.v1.common.mapper.KeyValueMapper;
import io.starinc.api.v1.common.vo.CommonVo;

//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class AirdropController {
	
	// private static final Logger logger = LoggerFactory.getLogger(AirdropLogController.class);
	
	@Autowired
	private AirdropLogMapper airdropLogMapper;
	
	@Autowired
	private KeyValueMapper keyValueMapper;
	
	/**
	 * 에어드랍 로그 테이블 채우기
	 * airdrop_log 테이블에(5000 - airdrop_log 테이블에 행 갯수 = ?)개의 새 데이터를 물량지갑 주소로 5000개 채움
	 * 
	 * @param airdropAmount 에어드랍 총 갯수
	 * @return commonVo
	 * @throws Exception
	 */
	@GetMapping("/airdrop/fillAirdropLog")
	public CommonVo fillAirdropLog(int numberOfAirdrop) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		
		try {
			// 1. airdrop_log 테이블에(5000 - airdrop_log 테이블에 행 갯수 = ?)개의 새 데이터를 물량지갑 주소로 5000개 채움
			int airdropLogCount = this.airdropLogMapper.selectAirdropLogCount();
			
			// 추가되어야 할 갯수 = 5000 - 현재 신청 수
			int numberToBeAdded = numberOfAirdrop - airdropLogCount;
			
			// airdrop_log 테이블에 numberToBeAdded 수만큼 물량지갑주소로 insert
			
			// 물량지갑주소
			String listingWalletAddress = keyValueMapper.selectKeyValue("listing_wallet_address");
			
			ArrayList<AirdropLogVo> newAirdropLogList = new ArrayList<>();
			
			// 어차피 전부 동일하기 때문에 객체는 1개만 사용
			AirdropLogVo airdropLogVo = new AirdropLogVo();
			airdropLogVo.setNew_wallet_address(listingWalletAddress);
			
			// 추가되어야 할 갯수만큼 루프
			for (int i = 0; i < numberToBeAdded; i++) {
				newAirdropLogList.add(airdropLogVo);
			}
			
			if (numberToBeAdded > 0) { // 추가해야 하는 경우에만 실행
				// 리스트 한 번에 insert
				int resultCount = this.airdropLogMapper.insertAirdropLogListForPrepare(newAirdropLogList);
				if (resultCount == 0) {
					commonVo.setResultCd("FAIL");
					commonVo.setResultMsg("insertAirdropLogListForPrepare failed.");
					return commonVo;
				}
			}
			
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	/**
	 * 에어드랍 로그 nft id 랜덤 할당
	 * 
	 * @param airdropAmount 에어드랍 총 갯수
	 * @return commonVo
	 * @throws Exception
	 */
	@GetMapping("/airdrop/setNewNftId")
	public CommonVo setNewNftId(int numberOfAirdrop) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
			int resultCount = this.airdropLogMapper.updateAirdropLogNftIdRandom();
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("updateAirdropLogNftIdRandom failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
}
