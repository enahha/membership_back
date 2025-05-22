package io.starinc.api.v1.payment.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.payment.mapper.PaymentMapper;
import io.starinc.api.v1.payment.vo.PaymentVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class PaymentController {
	
	// private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentMapper paymentMapper;
	
//	/**
//	 * 결제정보 리스트 마지막 페이지 번호 조회
//	 * 
//	 * @param uid
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/payment/selectPaymentListLastPageNum" , method = {RequestMethod.GET})
//	public int selectPaymentListLastPageNum(@RequestParam String uid, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		PaymentVo paymentVo = new PaymentVo();
//		paymentVo.setKeyword(keyword);
//		paymentVo.setPageSize(pageSize);
//		return this.paymentMapper.selectPaymentListLastPageNum(paymentVo);
//	}
	
	/**
	 * 결제정보 리스트 조회 (결제자 리스트 일괄 민팅용)
	 * 
	 * @param uid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/payment/selectPaymentList")
	public List<PaymentVo> selectPaymentList(@RequestParam int projectSeq) throws Exception {
		PaymentVo paymentVo = new PaymentVo();
		paymentVo.setProject_seq(projectSeq);
		return this.paymentMapper.selectPaymentList(paymentVo);
	}
	
	/**
	 * 지갑주소별 결제정보 리스트 조회 (사용자 확인용)
	 * 
	 * @param projectSeq
	 * @param walletAddress
	 * @return List<PaymentVo>
	 * @throws Exception
	 */
	@GetMapping("/payment/selectPaymentListByWalletAddress")
	public List<PaymentVo> selectPaymentListByWalletAddress(@RequestParam int projectSeq, @RequestParam String walletAddress) throws Exception {
		PaymentVo paymentVo = new PaymentVo();
		paymentVo.setProject_seq(projectSeq);
		paymentVo.setWallet_address_from(walletAddress);
		
		return this.paymentMapper.selectPaymentListByWalletAddress(paymentVo);
	}
	
//	/**
//	 * 결제정보 조회
//	 * 
//	 * @param uid
//	 * @param seq
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/payment/selectPayment" , method = {RequestMethod.GET})
//	public CommonResultVo selectPayment(@RequestParam String uid, @RequestParam int seq) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		PaymentVo paymentVo = this.paymentMapper.selectPayment(seq);
//		if (paymentVo == null) {
//			commonResultVo.setResultCd("FAIL"); // 비정상처리
//			commonResultVo.setResultMsg("selectPayment is null.");
//			commonResultVo.setReturnCd("1");
//		} else {
//			commonResultVo.setReturnValue(paymentVo);
//		}
//		return commonResultVo;
//	}
	
	/**
	 * 결제정보 등록
	 * 
	 * @param paymentVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/payment/insertPayment")
	public CommonResultVo insertPayment(@RequestBody PaymentVo paymentVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		// 상태 코드 10(결제 완료)
		paymentVo.setStatus(Constant.PAYMENT_STATUS_PAYMENT_COMPLETED);
		
		try {
			int resultCount = this.paymentMapper.insertPayment(paymentVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("insertPayment failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
			// insert 후 자동생성된 seq 값 반환 (update시 필요하기 때문에 front에 저장)
			commonResultVo.setReturnValue(paymentVo);
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
//	 * 결제정보 수정
//	 * 
//	 * @param paymentVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/payment/updatePayment")
//	public CommonResultVo updatePayment(@RequestBody PaymentVo paymentVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			int resultCount = this.paymentMapper.updatePayment(paymentVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("updatePayment failed.");
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
	
	/**
	 * 결제정보 상태 민팅실패로 수정
	 * 
	 * @param paymentVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/payment/updatePaymentStatusMintFailed")
	public CommonResultVo updatePaymentStatusMintFailed(@RequestBody PaymentVo paymentVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		// 결제정보 상태 : 민팅 실패(40)
		paymentVo.setStatus(Constant.PAYMENT_STATUS_MINT_FAILED);
		paymentVo.setMod_id("SYSTEM");
		
		try {
			int resultCount = this.paymentMapper.updatePaymentStatus(paymentVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updatePaymentStatus failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonResultVo.setResultCd("FAIL");
			commonResultVo.setReturnCd("-1");
			commonResultVo.setResultMsg(e.toString());
		}
		return commonResultVo;
	}
	
	/**
	 * 결제정보 상태 민팅완료로 수정
	 * 
	 * @param paymentVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/payment/updatePaymentStatusMintCompleted")
	public CommonResultVo updatePaymentStatusMintCompleted(@RequestBody PaymentVo paymentVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		// 결제정보 상태 : 민팅 완료(50)
		paymentVo.setStatus(Constant.PAYMENT_STATUS_MINT_COMPLETED);
		paymentVo.setMod_id("SYSTEM");
		
		try {
			int resultCount = this.paymentMapper.updatePaymentStatus(paymentVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updatePaymentStatus failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonResultVo.setResultCd("FAIL");
			commonResultVo.setReturnCd("-1");
			commonResultVo.setResultMsg(e.toString());
		}
		return commonResultVo;
	}
	
//	/**
//	 * 결제정보 삭제
//	 * 
//	 * @param paymentVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/payment/deletePayment" , method = {RequestMethod.POST})
//	public CommonResultVo deletePayment(@RequestBody PaymentVo paymentVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			paymentVo.setDel_id(paymentVo.getUid());
//			int resultCount = this.paymentMapper.deletePayment(paymentVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("deletePayment failed.");
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
//	@GetMapping("/payment/getCompletedNftList")
//	public List<PaymentVo> selectPaymentList(@RequestParam String oldWalletAddress) throws Exception {
//		PaymentVo paymentVo = new PaymentVo();
//		paymentVo.setOld_wallet_address(oldWalletAddress);
//		return this.paymentMapper.selectPayment(paymentVo);
//	}
	
	/**
	 * ME NFT 민팅 수량 확인
	 * 
	 * @param PaymentVo
	 * @return commonVo
	 * @throws Exception
	 */
	@GetMapping("/payment/selectPaymentMeQuantitySold")
	public Map<String, Object> selectPaymentMeQuantitySold() throws Exception {
		return this.paymentMapper.selectPaymentMeQuantitySold();
	}
}
