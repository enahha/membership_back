package io.starinc.api.v1.airdroplog.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.airdroplog.mapper.AirdropLogMapper;
import io.starinc.api.v1.airdroplog.vo.AirdropLogRequestVo;
import io.starinc.api.v1.airdroplog.vo.AirdropLogVo;
import io.starinc.api.v1.common.util.CommonUtil;
import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.verifyedaddress.mapper.VerifyedAddressMapper;
import io.starinc.api.v1.verifyedaddress.vo.VerifyedAddressVo;

//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class AirdropLogController {
	
	// private static final Logger logger = LoggerFactory.getLogger(AirdropLogController.class);
	
	@Autowired
	private AirdropLogMapper airdropLogMapper;
	
	@Autowired
	private VerifyedAddressMapper verifyedAddressMapper;
	
	/**
	 * 에어드랍 로그 전체 리스트 마지막 페이지 번호 조회
	 * 
	 * @param uid
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/airdropLog/selectAirdropLogListLastPageNum")
	public int selectAirdropLogListLastPageNum(
			@RequestParam int pageSize
			, @RequestParam(required = false) String keyword
			, @RequestParam(required = false) String status
			, @RequestParam(required = false) String oldWalletAddress) throws Exception {
		AirdropLogVo airdropLogVo = new AirdropLogVo();
		airdropLogVo.setKeyword(keyword);
		airdropLogVo.setPageSize(pageSize);
		airdropLogVo.setOld_wallet_address(oldWalletAddress);
//		airdropLogVo.setStatus(status);
		return this.airdropLogMapper.selectAirdropLogListLastPageNum(airdropLogVo);
	}
	
	/**
	 * 에어드랍 로그 요청 전체 리스트 조회
	 * 
	 * @param uid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/airdropLog/selectAirdropLogList")
	public List<AirdropLogVo> selectAirdropLogList(
			@RequestParam int pageNum
			, @RequestParam int pageSize
			, @RequestParam(required = false) String keyword
			, @RequestParam(required = false) String status
			, @RequestParam(required = false) String oldWalletAddress
			, @RequestParam(required = false) String newWalletAddress) throws Exception {
		// 페이징 처리
		AirdropLogVo airdropLogVo = new AirdropLogVo();
		airdropLogVo.setKeyword(keyword);
		airdropLogVo.setStartRow(CommonUtil.pagingStartRow(pageNum, pageSize));
		airdropLogVo.setEndRow(CommonUtil.pagingEndRow(pageNum, pageSize));
		airdropLogVo.setOld_wallet_address(oldWalletAddress);
		airdropLogVo.setNew_wallet_address(newWalletAddress);
//		airdropLogVo.setStatus(status);
		return this.airdropLogMapper.selectAirdropLogList(airdropLogVo);
	}
	
	/**
	 * 에어드랍 로그 중복 token id 확인
	 * 
	 * @param uid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/airdropLog/checkDuplicate")
    public ResponseEntity<Map<String, Object>> checkDuplicate(@RequestBody AirdropLogVo airdropLogVo) {
        List<String> tokenIds = airdropLogVo.getTokenIdList();

        // 중복된 nft_id 조회
        List<String> duplicateIds = this.airdropLogMapper.findDuplicateNftIdList(tokenIds);

        // 결과 반환
        Map<String, Object> response = new HashMap<>();
        response.put("duplicateIds", duplicateIds);
        return ResponseEntity.ok(response);
    }
	
	/**
	 * 에어드랍 로그 조회
	 * 
	 * @param uid
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/airdropLog/selectAirdropLog")
	public AirdropLogVo selectAirdropLog(@RequestParam(required = false) String newWalletAddress, @RequestParam String ethNftId) throws Exception {
		AirdropLogVo airdropLogVo = new AirdropLogVo();
		airdropLogVo.setNew_wallet_address(newWalletAddress);
		airdropLogVo.setOld_nft_id(ethNftId);
		return this.airdropLogMapper.selectAirdropLog(airdropLogVo);
	}
	
//	/**
//	 * 에어드랍 로그 등록
//	 * 
//	 * @param airdropLogVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/airdropLog/insertAirdropLog")
//	public CommonVo insertAirdropLog(@RequestBody AirdropLogVo airdropLogVo) throws Exception {
//		CommonVo commonVo = new CommonVo();
//		commonVo.setResultCd("SUCCESS");
//		try {
//			airdropLogVo.setOld_project_id("1");
//			airdropLogVo.setReg_id(airdropLogVo.getOld_wallet_address());
//			int resultCount = this.airdropLogMapper.insertAirdropLog(airdropLogVo);
//			if (resultCount == 0) {
//				commonVo.setResultCd("FAIL");
//				commonVo.setResultMsg("insertAirdropLog failed.");
//				return commonVo;
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonVo.setResultCd("FAIL");
//			commonVo.setResultMsg(e.toString());
//		}
//		return commonVo;
//	}
	
	/**
	 * 에어드랍 로그 등록(리스트)
	 * 
	 * @param mediaVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/airdropLog/insertAirdropLogList")
	public CommonVo insertAirdropLogList(@RequestBody AirdropLogRequestVo airdropLogRequestVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		
		try {
//			String message = airdropLogRequestVo.getMessage();
//			String signature = airdropLogRequestVo.getSignature();
//			String userAddress = airdropLogRequestVo.getUserAddress();
//			
//			SignatureController signatureController = new SignatureController();
//			boolean isSignatureValid = signatureController.verifySignature(message, signature, userAddress);
//			if (!isSignatureValid) {
//				commonVo.setResultCd("FAIL");
//				commonVo.setResultMsg("Signature verification failed.");
//				return commonVo;
//			}
			
			String userAddress = airdropLogRequestVo.getUserAddress();
			String uuid = airdropLogRequestVo.getUuid();
			VerifyedAddressVo verifyedAddressVo = new VerifyedAddressVo();
			verifyedAddressVo.setUuid(uuid);
			verifyedAddressVo.setWallet_address(userAddress);
			int verifyedResultCount = this.verifyedAddressMapper.countVerifyedAddress(verifyedAddressVo);
			if (verifyedResultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("countVerifyedAddress failed.");
				return commonVo;
			}
			
			
			// 리스트 한 번에 insert
			int resultCount = this.airdropLogMapper.insertAirdropLogList(airdropLogRequestVo.getAirdropLogList());
			
			// 결과
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("insertAirdropLogList failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	
	/**
	 * 에어드랍 로그 수정
	 * 
	 * @param airdropLogVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/airdropLog/updateAirdropLog")
	public CommonVo updateAirdropLog(@RequestBody AirdropLogVo airdropLogVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
			airdropLogVo.setMod_id(airdropLogVo.getUid());
			int resultCount = this.airdropLogMapper.updateAirdropLog(airdropLogVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("updateAirdropLog failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	/**
	 * 에어드랍 로그 상태 수정
	 * 
	 * @param nftInfoVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/airdropLog/updateAirdropLogStatus")
	public CommonResultVo updateAirdropLogStatus(@RequestBody AirdropLogVo airdropLogVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.airdropLogMapper.updateAirdropLogStatus(airdropLogVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updateNftInfoStatus failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			e.printStackTrace();
			commonResultVo.setResultCd("FAIL");
			commonResultVo.setReturnCd("-1");
			commonResultVo.setResultMsg(e.toString());
		}
		return commonResultVo;
	}
	
	/**
	 * 에어드랍 로그 상태 수정
	 * 
	 * @param nftInfoVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/airdropLog/updateAirdropLogStatusAndNewNftId")
	public CommonResultVo updateAirdropLogStatusAndNewNftId(@RequestBody AirdropLogVo airdropLogVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.airdropLogMapper.updateAirdropLogStatusAndNewNftId(airdropLogVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updateNftInfoStatus failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			e.printStackTrace();
			commonResultVo.setResultCd("FAIL");
			commonResultVo.setReturnCd("-1");
			commonResultVo.setResultMsg(e.toString());
		}
		return commonResultVo;
	}
	
	/**
	 * 에어드랍 로그 상태 수정
	 * 
	 * @param nftInfoVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/airdropLog/updateAirdropLogSignature")
	public CommonResultVo updateAirdropLogSignature(@RequestBody AirdropLogVo airdropLogVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.airdropLogMapper.updateAirdropLogSignature(airdropLogVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updateNftInfoStatus failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			e.printStackTrace();
			commonResultVo.setResultCd("FAIL");
			commonResultVo.setReturnCd("-1");
			commonResultVo.setResultMsg(e.toString());
		}
		return commonResultVo;
	}
	
	/**
	 * 에어드랍 로그 삭제
	 * 
	 * @param airdropLogVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/airdropLog/deleteAirdropLog")
	public CommonVo deleteAirdropLog(@RequestBody AirdropLogVo airdropLogVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
			airdropLogVo.setDel_id(airdropLogVo.getUid());
			int resultCount = this.airdropLogMapper.deleteAirdropLog(airdropLogVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("deleteAirdropLog failed.");
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
