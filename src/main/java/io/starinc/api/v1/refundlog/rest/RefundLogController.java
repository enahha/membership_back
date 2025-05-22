package io.starinc.api.v1.refundlog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.refundlog.mapper.RefundLogMapper;
import io.starinc.api.v1.refundlog.vo.RefundLogVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class RefundLogController {
	
	// private static final Logger logger = LoggerFactory.getLogger(RefundLogController.class);
	
	@Autowired
	private RefundLogMapper refundLogMapper;
	
//	/**
//	 * 환불로그 리스트 마지막 페이지 번호 조회
//	 * 
//	 * @param uid
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/refundlog/selectRefundLogListLastPageNum" , method = {RequestMethod.GET})
//	public int selectRefundLogListLastPageNum(@RequestParam String uid, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		RefundLogVo refundLogVo = new RefundLogVo();
//		refundLogVo.setKeyword(keyword);
//		refundLogVo.setPageSize(pageSize);
//		return this.refundLogMapper.selectRefundLogListLastPageNum(refundLogVo);
//	}
	
//	/**
//	 * 환불로그 리스트 조회
//	 * 
//	 * @param uid
//	 * @param pageNum
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/refundlog/selectRefundLogList" , method = {RequestMethod.GET})
//	public List<RefundLogVo> selectRefundLogList(@RequestParam String uid, @RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		// 페이징 처리
//		RefundLogVo refundLogVo = new RefundLogVo();
//		refundLogVo.setKeyword(keyword);
//		refundLogVo.setStartRow(CommonUtil.pagingStartRow(pageNum, pageSize));
//		refundLogVo.setEndRow(CommonUtil.pagingEndRow(pageNum, pageSize));
//		return this.refundLogMapper.selectRefundLogList(refundLogVo);
//	}
	
//	/**
//	 * 환불로그 조회
//	 * 
//	 * @param uid
//	 * @param seq
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/refundlog/selectRefundLog" , method = {RequestMethod.GET})
//	public CommonResultVo selectRefundLog(@RequestParam String uid, @RequestParam int seq) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		RefundLogVo refundLogVo = this.refundLogMapper.selectRefundLog(seq);
//		if (refundLogVo == null) {
//			commonResultVo.setResultCd("FAIL"); // 비정상처리
//			commonResultVo.setResultMsg("selectRefundLog is null.");
//			commonResultVo.setReturnCd("1");
//		} else {
//			commonResultVo.setReturnValue(refundLogVo);
//		}
//		return commonResultVo;
//	}
	
	/**
	 * 환불로그 등록
	 * 
	 * @param refundLogVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/refundlog/insertRefundLog")
	public CommonResultVo insertRefundLog(@RequestBody RefundLogVo refundLogVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.refundLogMapper.insertRefundLog(refundLogVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("insertRefundLog failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
			// insert 후 자동생성된 seq 값 반환 (update시 필요하기 때문에 front에 저장)
			commonResultVo.setReturnValue(refundLogVo);
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
//	 * 환불로그 수정
//	 * 
//	 * @param refundLogVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/refundlog/updateRefundLog")
//	public CommonResultVo updateRefundLog(@RequestBody RefundLogVo refundLogVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			int resultCount = this.refundLogMapper.updateRefundLog(refundLogVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("updateRefundLog failed.");
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
//	 * 환불로그 삭제
//	 * 
//	 * @param refundLogVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/refundlog/deleteRefundLog" , method = {RequestMethod.POST})
//	public CommonResultVo deleteRefundLog(@RequestBody RefundLogVo refundLogVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			refundLogVo.setDel_id(refundLogVo.getUid());
//			int resultCount = this.refundLogMapper.deleteRefundLog(refundLogVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("deleteRefundLog failed.");
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
//	@GetMapping("/refundlog/getCompletedNftList")
//	public List<RefundLogVo> selectRefundLogList(@RequestParam String oldWalletAddress) throws Exception {
//		RefundLogVo refundLogVo = new RefundLogVo();
//		refundLogVo.setOld_wallet_address(oldWalletAddress);
//		return this.refundLogMapper.selectRefundLog(refundLogVo);
//	}
}
