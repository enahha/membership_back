package io.starinc.api.v1.mintinglog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.mintinglog.mapper.MintingLogMapper;
import io.starinc.api.v1.mintinglog.vo.MintingLogVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class MintingLogController {
	
	// private static final Logger logger = LoggerFactory.getLogger(MintingLogController.class);
	
	@Autowired
	private MintingLogMapper mintingLogMapper;
	
//	/**
//	 * 민팅로그 리스트 마지막 페이지 번호 조회
//	 * 
//	 * @param uid
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mintinglog/selectMintingLogListLastPageNum" , method = {RequestMethod.GET})
//	public int selectMintingLogListLastPageNum(@RequestParam String uid, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		MintingLogVo mintingLogVo = new MintingLogVo();
//		mintingLogVo.setKeyword(keyword);
//		mintingLogVo.setPageSize(pageSize);
//		return this.mintingLogMapper.selectMintingLogListLastPageNum(mintingLogVo);
//	}
	
//	/**
//	 * 민팅로그 리스트 조회
//	 * 
//	 * @param uid
//	 * @param pageNum
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mintinglog/selectMintingLogList" , method = {RequestMethod.GET})
//	public List<MintingLogVo> selectMintingLogList(@RequestParam String uid, @RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		// 페이징 처리
//		MintingLogVo mintingLogVo = new MintingLogVo();
//		mintingLogVo.setKeyword(keyword);
//		mintingLogVo.setStartRow(CommonUtil.pagingStartRow(pageNum, pageSize));
//		mintingLogVo.setEndRow(CommonUtil.pagingEndRow(pageNum, pageSize));
//		return this.mintingLogMapper.selectMintingLogList(mintingLogVo);
//	}
	
//	/**
//	 * 민팅로그 조회
//	 * 
//	 * @param uid
//	 * @param seq
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mintinglog/selectMintingLog" , method = {RequestMethod.GET})
//	public CommonResultVo selectMintingLog(@RequestParam String uid, @RequestParam int seq) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		MintingLogVo mintingLogVo = this.mintingLogMapper.selectMintingLog(seq);
//		if (mintingLogVo == null) {
//			commonResultVo.setResultCd("FAIL"); // 비정상처리
//			commonResultVo.setResultMsg("selectMintingLog is null.");
//			commonResultVo.setReturnCd("1");
//		} else {
//			commonResultVo.setReturnValue(mintingLogVo);
//		}
//		return commonResultVo;
//	}
	
	/**
	 * 민팅로그 등록 (민팅 전)
	 * 
	 * @param mintingLogVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/mintinglog/insertMintingLog")
	public CommonResultVo insertMintingLog(@RequestBody MintingLogVo mintingLogVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.mintingLogMapper.insertMintingLog(mintingLogVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("insertMintingLog failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
			// insert 후 자동생성된 seq 값 반환 (update시 필요하기 때문에 front에 저장)
			commonResultVo.setReturnValue(mintingLogVo);
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
	 * 민팅로그 수정 (민팅 후)
	 * 
	 * @param mintingLogVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/mintinglog/updateMintingLog")
	public CommonResultVo updateMintingLog(@RequestBody MintingLogVo mintingLogVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.mintingLogMapper.updateMintingLog(mintingLogVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updateMintingLog failed.");
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
	
//	/**
//	 * 민팅로그 삭제
//	 * 
//	 * @param mintingLogVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mintinglog/deleteMintingLog" , method = {RequestMethod.POST})
//	public CommonResultVo deleteMintingLog(@RequestBody MintingLogVo mintingLogVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			mintingLogVo.setDel_id(mintingLogVo.getUid());
//			int resultCount = this.mintingLogMapper.deleteMintingLog(mintingLogVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("deleteMintingLog failed.");
//				commonResultVo.setReturnCd("1");
//				return commonResultVo;
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonResultVo.setResultCd("FAIL");
//			commonResultVo.setReturnCd("-1");
//			commonResultVo.setResultMsg(e.toString());
//		}
//		return commonResultVo;
//	}
	
//	/**
//	 * 마이그레이션완료된 ntf정보 리스트 조회
//	 * 
//	 * @param oldWalletAddress
//	 * @return NftVo
//	 * @throws Exception
//	 */
//	@GetMapping("/mintinglog/getCompletedNftList")
//	public List<MintingLogVo> selectMintingLogList(@RequestParam String oldWalletAddress) throws Exception {
//		MintingLogVo mintingLogVo = new MintingLogVo();
//		mintingLogVo.setOld_wallet_address(oldWalletAddress);
//		return this.mintingLogMapper.selectMintingLog(mintingLogVo);
//	}
}
