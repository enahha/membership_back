package io.starinc.api.v1.mintplan.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import io.starinc.api.v1.mintplan.mapper.MintPlanMapper;
import io.starinc.api.v1.mintplan.vo.MintPlanVo;
import io.starinc.api.v1.nftinfo.mapper.NftInfoMapper;
import io.starinc.api.v1.nftinfo.vo.NftInfoVo;
import io.starinc.api.v1.payment.mapper.PaymentMapper;
import io.starinc.api.v1.payment.vo.PaymentVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class MintPlanController {
	
	private static final Logger logger = LoggerFactory.getLogger(MintPlanController.class);
	
	@Autowired
	private MintPlanMapper mintPlanMapper;
	
	@Autowired
	private PaymentMapper paymentMapper;
	
//	@Autowired
//	private NftTypeMapper nftTypeMapper;
	
	@Autowired
	private NftInfoMapper nftInfoMapper;
	
//	/**
//	 * 민트플랜 리스트 마지막 페이지 번호 조회
//	 * 
//	 * @param uid
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mintplan/selectMintPlanListLastPageNum" , method = {RequestMethod.GET})
//	public int selectMintPlanListLastPageNum(@RequestParam String uid, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		MintPlanVo mintPlanVo = new MintPlanVo();
//		mintPlanVo.setKeyword(keyword);
//		mintPlanVo.setPageSize(pageSize);
//		return this.mintPlanMapper.selectMintPlanListLastPageNum(mintPlanVo);
//	}
	
//	/**
//	 * 민트플랜 리스트 조회
//	 * 
//	 * @param uid
//	 * @param pageNum
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mintplan/selectMintPlanList" , method = {RequestMethod.GET})
//	public List<MintPlanVo> selectMintPlanList(@RequestParam String uid, @RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		// 페이징 처리
//		MintPlanVo mintPlanVo = new MintPlanVo();
//		mintPlanVo.setKeyword(keyword);
//		mintPlanVo.setStartRow(CommonUtil.pagingStartRow(pageNum, pageSize));
//		mintPlanVo.setEndRow(CommonUtil.pagingEndRow(pageNum, pageSize));
//		return this.mintPlanMapper.selectMintPlanList(mintPlanVo);
//	}
	
	/**
	 * 민트플랜 리스트 조회
	 * 
	 * @param projectSeq
	 * @return List<MintPlanVo>
	 * @throws Exception
	 */
	@GetMapping("/mintplan/selectMintPlanList")
	public List<MintPlanVo> selectMintPlanList(@RequestParam int projectSeq) throws Exception {
		MintPlanVo mintPlanVo = new MintPlanVo();
		mintPlanVo.setProject_seq(projectSeq);
		return this.mintPlanMapper.selectMintPlanList(mintPlanVo);
	}
	
	/**
	 * 민트플랜 필드 리스트 for CSV 조회
	 * 
	 * @param projectSeq
	 * @return List<MintPlanVo>
	 * @throws Exception
	 */
	@GetMapping("/mintplan/selectMintPlanFieldList")
	public List<MintPlanVo> selectMintPlanFieldList(@RequestParam int projectSeq) throws Exception {
		MintPlanVo mintPlanVo = new MintPlanVo();
		mintPlanVo.setProject_seq(projectSeq);
		return this.mintPlanMapper.selectMintPlanFieldList(mintPlanVo);
	}
	
//	/**
//	 * 민트플랜 조회
//	 * 
//	 * @param uid
//	 * @param seq
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mintplan/selectMintPlan" , method = {RequestMethod.GET})
//	public CommonResultVo selectMintPlan(@RequestParam String uid, @RequestParam int seq) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		MintPlanVo mintPlanVo = this.mintPlanMapper.selectMintPlan(seq);
//		if (mintPlanVo == null) {
//			commonResultVo.setResultCd("FAIL"); // 비정상처리
//			commonResultVo.setResultMsg("selectMintPlan is null.");
//			commonResultVo.setReturnCd("1");
//		} else {
//			commonResultVo.setReturnValue(mintPlanVo);
//		}
//		return commonResultVo;
//	}
	
	/**
	 * 민트플랜 리스트 등록
	 * 
	 * @param mintPlanVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/mintplan/insertMintPlanList")
	public CommonResultVo insertMintPlanList(@RequestBody MintPlanVo mintPlanVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int projectSeq = mintPlanVo.getProject_seq();
			
			// 결제정보 리스트 조회
			PaymentVo paymentVo = new PaymentVo();
			paymentVo.setProject_seq(projectSeq); // 쿼리에 project_seq in (1, 4, 5, 6) 으로 해놔서 이 조건 의미 없음.
			List<PaymentVo> paymentList = this.paymentMapper.selectPaymentListNotInMintPlan(paymentVo);
			
			// 결제정보 리스트가 없는 경우 리턴
			if (paymentList == null || paymentList.size() < 1) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("selectPaymentList is null.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			// nft_type 테이블 조회
			/////////////////////////////////////////////////////////////////////////////////////////////
			// 결제정보 리스트 루프 - NFT ID 채번하여 mint_plan 테이블에 insert
			for (int i = 0; i < paymentList.size(); i++) {
				paymentVo = paymentList.get(i);
				
				logger.debug(i + ": " + paymentVo.toString());
				
				// 여기서 paymentVo 내용을 토대로 mint_plan 테이블에 데이터 insert
				// nft_info 테이블에서 NFT ID를 타입에 맞춰서 순서대로 채번해야 함
				
				// NFT ID 채번 - 지금은 무조건 1개만 구매하기 때문에 루프처리 안함. 추후 for문으로 갯수만큼 채번해야 함.
				String nftType = "";
				String nftIdStart = "0";
				String nftIdEnd = "0";
				if (paymentVo.getQuantity_a() > 0) {
					nftType = "a";
					nftIdStart = Constant.NFT_ID_START_TYPE_A;
					nftIdEnd = Constant.NFT_ID_END_TYPE_A;
				} else if (paymentVo.getQuantity_s() > 0) {
					nftType = "s";
					nftIdStart = Constant.NFT_ID_START_TYPE_S;
					nftIdEnd = Constant.NFT_ID_END_TYPE_S;
				} else if (paymentVo.getQuantity_r() > 0) {
					nftType = "r";
					nftIdStart = Constant.NFT_ID_START_TYPE_R;
					nftIdEnd = Constant.NFT_ID_END_TYPE_R;
				} else if (paymentVo.getQuantity_v() > 0) {
					nftType = "v";
					nftIdStart = Constant.NFT_ID_START_TYPE_V;
					nftIdEnd = Constant.NFT_ID_END_TYPE_V;
				} else if (paymentVo.getQuantity_me100() > 0) {
					nftType = "me100";
					nftIdStart = Constant.NFT_ID_START_TYPE_ME100;
					nftIdEnd = Constant.NFT_ID_END_TYPE_ME100;
				} else if (paymentVo.getQuantity_me1500() > 0) {
					nftType = "me1500";
					nftIdStart = Constant.NFT_ID_START_TYPE_ME1500;
					nftIdEnd = Constant.NFT_ID_END_TYPE_ME1500;
				} else if (paymentVo.getQuantity_me10000() > 0) {
					nftType = "me10000";
					nftIdStart = Constant.NFT_ID_START_TYPE_ME10000;
					nftIdEnd = Constant.NFT_ID_END_TYPE_ME10000;
				} else {
					logger.debug(i + ": " + "ERROR, nftType: " + nftType);
					// 처리 중단하지 않고 계속 진행
				}
				
				/////////////////////////////////////////////////////////////////////////////////////////////
				// nft_info 테이블 조회
				/////////////////////////////////////////////////////////////////////////////////////////////
				NftInfoVo nftInfoVo = new NftInfoVo();
				nftInfoVo.setProject_seq(paymentVo.getProject_seq()); // 프로젝트 seq
				nftInfoVo.setNft_type(nftType); // 조회조건으로 화면에서 선택한 NFT Type 설정
				nftInfoVo.setNft_id_start(nftIdStart);
				nftInfoVo.setNft_id_end(nftIdEnd);
				nftInfoVo = this.nftInfoMapper.selectNftInfo(nftInfoVo);
				
				// nft_info 테이블에 해당 data의 status를 처리중(10)으로 수정
				nftInfoVo.setStatus(Constant.NFT_STATUS_MINT_PROCESSING); // "10"
				nftInfoVo.setMod_id(paymentVo.getWallet_address_from()); // 수정자 id는 사용자 지갑주소
				int resultCount = this.nftInfoMapper.updateNftInfoStatusProcessing(nftInfoVo);
				if (resultCount == 0) {
					logger.debug(i + ": " + "ERROR, updateNftInfoStatusProcessing resultCount is 0.");
				}
				
				// 민팅 플랜 data 설정
				mintPlanVo.setProject_seq(paymentVo.getProject_seq()); // 프로젝트 seq
				mintPlanVo.setPayment_seq(paymentVo.getSeq());
				mintPlanVo.setStatus(Constant.NFT_STATUS_MINT_WAITING); // "0"
				mintPlanVo.setWallet_address_to(paymentVo.getWallet_address_from());
				mintPlanVo.setWallet_type_to(paymentVo.getWallet_type_from());
				mintPlanVo.setCurrency_symbol(paymentVo.getCurrency_symbol());
				mintPlanVo.setNft_type(nftType);
				mintPlanVo.setNft_id(nftInfoVo.getNft_id());
				mintPlanVo.setJson_file_url(nftInfoVo.getJson_file_url());
				mintPlanVo.setReg_id(Constant.SYSTEM_REG_ID); // SYSTEM
				
				resultCount = this.mintPlanMapper.insertMintPlan(mintPlanVo);
				if (resultCount == 0) {
					commonResultVo.setResultCd("FAIL"); // 비정상처리
					commonResultVo.setResultMsg("insertMintPlan failed.");
					commonResultVo.setReturnCd("2");
					return commonResultVo;
				}
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
	 * 민트플랜 등록
	 * 
	 * @param mintPlanVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/mintplan/insertMintPlan")
	public CommonResultVo insertMintPlan(@RequestBody MintPlanVo mintPlanVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.mintPlanMapper.insertMintPlan(mintPlanVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("insertMintPlan failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
			// insert 후 자동생성된 seq 값 반환 (update시 필요하기 때문에 front에 저장)
			commonResultVo.setReturnValue(mintPlanVo);
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
	 * 민트플랜 수정
	 * 
	 * @param mintPlanVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/mintplan/updateMintPlanStatus")
	public CommonResultVo updateMintPlanStatus(@RequestBody MintPlanVo mintPlanVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.mintPlanMapper.updateMintPlanStatus(mintPlanVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updateMintPlanStatus failed.");
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
//	 * 민트플랜 삭제
//	 * 
//	 * @param mintPlanVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/mintplan/deleteMintPlan" , method = {RequestMethod.POST})
//	public CommonResultVo deleteMintPlan(@RequestBody MintPlanVo mintPlanVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			mintPlanVo.setDel_id(mintPlanVo.getUid());
//			int resultCount = this.mintPlanMapper.deleteMintPlan(mintPlanVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("deleteMintPlan failed.");
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
}
