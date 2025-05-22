package io.starinc.api.v1.minting.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.blockchain.rest.SolanaController;
import io.starinc.api.v1.blockchain.rest.SolanaMintController;
import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.common.mapper.KeyValueMapper;
import io.starinc.api.v1.common.util.StringUtil;
import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.mintinglog.mapper.MintingLogMapper;
import io.starinc.api.v1.mintinglog.vo.MintingLogVo;
import io.starinc.api.v1.nftinfo.mapper.NftInfoMapper;
import io.starinc.api.v1.nftinfo.vo.NftInfoVo;
import io.starinc.api.v1.nftprice.mapper.NftPriceMapper;
import io.starinc.api.v1.nftprice.vo.NftPriceVo;
import io.starinc.api.v1.nfttype.mapper.NftTypeMapper;
import io.starinc.api.v1.nfttype.vo.NftTypeVo;
import io.starinc.api.v1.project.mapper.ProjectMapper;
import io.starinc.api.v1.project.vo.ProjectVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class MintingController {
	
	private static final Logger logger = LoggerFactory.getLogger(MintingController.class);
	
	@Autowired
	private SolanaMintController solanaMintController;
	
	@Autowired
	private SolanaController solanaController;
	
	@Autowired
	private NftTypeMapper nftTypeMapper;
	
	@Autowired
	private NftPriceMapper nftPriceMapper;
	
	@Autowired
	private NftInfoMapper nftInfoMapper;
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private MintingLogMapper mintingLogMapper;
	
	@Autowired
	private KeyValueMapper keyValueMapper;
	
	/**
	 * 민팅 실행
	 * 
	 * @param projectSeq
	 * @param walletAddress
	 * @param walletType
	 * @param nftType
	 * @param mintAmount : 1회 요청에 대한 민팅 갯수(추후 사용, 지금은 1로 고정)
	 * @param currencySymbol : 결제 통화
	 * @param currencyAmount : 결제 통화 총 금액
	 * @return CommonResultVo
	 * @throws Exception
	 */
	@PostMapping("/minting/mintSolana")
	public CommonResultVo mintSolana(
			@RequestParam int projectSeq
			, @RequestParam String walletAddress
			, @RequestParam String walletType
			, @RequestParam String nftType
			, @RequestParam int mintAmount
			, @RequestParam String currencySymbol
			, @RequestParam String currencyAmount
			) throws Exception {
		
		logger.debug("■■■■■■■ /blockchain/mint ■■■■■■■ START");
		logger.debug("■■■■■■■ param projectSeq: " + projectSeq);
		logger.debug("■■■■■■■ param walletAddress: " + walletAddress);
		logger.debug("■■■■■■■ param walletType: " + walletType);
		logger.debug("■■■■■■■ param nftType: " + nftType);
		logger.debug("■■■■■■■ param nftType: " + mintAmount);
		logger.debug("■■■■■■■ param currencySymbol: " + currencySymbol);
		logger.debug("■■■■■■■ param currencyAmount: " + currencyAmount);
		
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0"); // 정상완료코드
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		// 1. minting_log 테이블에 insert
		/////////////////////////////////////////////////////////////////////////////////////////////
		MintingLogVo mintingLogVo = new MintingLogVo();
		mintingLogVo.setProject_seq(projectSeq);
		mintingLogVo.setWallet_address(walletAddress);
		mintingLogVo.setWallet_type(walletType);
		mintingLogVo.setNft_type(nftType);
		mintingLogVo.setCurrency_symbol(currencySymbol);
		mintingLogVo.setReg_id(walletAddress); // 등록자 id는 사용자 지갑주소
		
		int resultLogCount = this.mintingLogMapper.insertMintingLog(mintingLogVo);
		if (resultLogCount == 0) {
			commonResultVo.setResultCd("FAIL"); // 비정상처리
			commonResultVo.setResultMsg("insertMintingLog failed.");
			commonResultVo.setReturnCd("8");
			// return commonResultVo; // 로그 관련 처리에 에러가 발생해도 전체 처리를 중지시키지 않음
		}
		// insert 후 자동생성된 seq 값 반환 (update시 필요하기 때문에 front에 저장)
		// int seq = mintingLogVo.getSeq(); // mintingLogVo 내에 seq 설정되어 있음(쿼리에서 selectKey로 세팅)
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		
		// BigInteger refundCurrencyAmount = new BigInteger(currencyAmount); // 환불 비용
//		int refundCurrencyAmount = Integer.parseInt(currencyAmount); // 환불 비용
		int refundCurrencyAmount = 0;
		if (StringUtil.isNotEmpty(currencyAmount)) {
			refundCurrencyAmount = Integer.parseInt(currencyAmount); // 환불 비용
		}
		
		// 파라미터 체크
		if (projectSeq < 1
				|| StringUtil.isEmpty(walletAddress)
				|| StringUtil.isEmpty(walletType)
				|| StringUtil.isEmpty(nftType)
				|| mintAmount < 1
				|| StringUtil.isEmpty(currencySymbol)
				|| StringUtil.isEmpty(currencyAmount)
		) {
			// 파라미터가 없을 경우 환불처리 후 리턴
			// 환불 처리
			if (StringUtil.isNotEmpty(currencyAmount)) { // 금액 정보가 있는 경우
				this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
			}
			
			commonResultVo.setResultCd("FAIL"); // 비정상처리
			commonResultVo.setResultMsg("parameter is empty.");
			commonResultVo.setReturnCd("99");
			return commonResultVo;
		}
		
		// 전체 처리 실패시에도 nft_info 처리중(10)이던 상태를 실패(5)로 돌려놓기 위해서 상단에 선언.
		NftInfoVo nftInfoVo = null;
		
		try {
			/////////////////////////////////////////////////////////////////////////////////////////////
			// 2. project 테이블 조회
			/////////////////////////////////////////////////////////////////////////////////////////////
			ProjectVo projectVo = new ProjectVo();
			projectVo.setSeq(projectSeq);
			projectVo = this.projectMapper.selectProject(projectVo);
			
			// 조회 결과 없을 경우 환불처리 후 리턴
			if (projectVo == null) {
				// 환불 처리
				this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
				
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("selectProject is null.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			// 3. nft_type 테이블 조회
			/////////////////////////////////////////////////////////////////////////////////////////////
			NftTypeVo nftTypeVo = new NftTypeVo();
			nftTypeVo.setProject_seq(projectSeq); // 프로젝트 seq
			nftTypeVo.setNft_type(nftType); // 조회조건으로 화면에서 선택한 NFT Type 설정
			nftTypeVo = this.nftTypeMapper.selectNftType(nftTypeVo);
			
			// 조회 결과 없을 경우 환불처리 후 리턴
			if (nftTypeVo == null) {
				// 환불 처리
				this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
				
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("selectNftType is null.");
				commonResultVo.setReturnCd("2");
				return commonResultVo;
			}
			
			// 남아있는 (처리중이지 않은: status < 10) NFT 갯수 확인
			if (nftTypeVo.getAmount_remain() < mintAmount) {
				// 환불 처리
				this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
				
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("nftTypeVo.getAmount_remain(): " + nftTypeVo.getAmount_remain() + ", mintAmount: " + mintAmount);
				commonResultVo.setReturnCd("3");
				return commonResultVo;
			}
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			// 4. nft_price 테이블 조회
			/////////////////////////////////////////////////////////////////////////////////////////////
			NftPriceVo nftPriceVo = new NftPriceVo();
			nftPriceVo.setProject_seq(projectSeq); // 프로젝트 seq
			nftPriceVo.setNft_type_seq(nftTypeVo.getSeq()); // 2에서 검색한 nft_type 테이블의 seq
			nftPriceVo.setCurrency_symbol(currencySymbol); // 조회조건으로 화면에서 선택한 currencySymbol 설정
			nftPriceVo = this.nftPriceMapper.selectNftPrice(nftPriceVo);
			
			// 조회 결과 없을 경우 환불처리 후 리턴
			if (nftPriceVo == null) {
				// 환불 처리
				this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
				
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("selectNftPrice is null.");
				commonResultVo.setReturnCd("4");
				return commonResultVo;
			}
			
			// NFT 가격 설정 - 환불 처리를 위해 필요(민팅처리에는 필요없음)
			int nftPrice = 0;
			// USD 관련 통화일 경우에는 price_usd를 설정, 그 이외에는 price_currency를 설정
			if (StringUtil.isNotEmpty(currencyAmount)
					&& StringUtil.isNotEmpty(currencySymbol)
					&& "USD".equals(currencySymbol)
					&& nftPriceVo.getIs_usd_value() == 1
			) {
				nftPrice = nftPriceVo.getPrice_usd();
			} else {
				nftPrice = nftPriceVo.getPrice_currency();
			}
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////
			// ◆◆◆◆◆◆◆ 여기부터 mintAmount 만큼 반복 처리 ◆◆◆◆◆◆◆ START
			// 각각의 처리 완료시마다 currencyAmount를 차감하여 이후 실패시 환불할 금액 수정
			/////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////
			for (int i = 0; i < mintAmount; i++) {
				
				/////////////////////////////////////////////////////////////////////////////////////////////
				// 5. nft_info 테이블 조회
				/////////////////////////////////////////////////////////////////////////////////////////////
				nftInfoVo = new NftInfoVo();
				nftInfoVo.setProject_seq(projectSeq); // 프로젝트 seq
				nftInfoVo.setNft_type(nftType); // 조회조건으로 화면에서 선택한 NFT Type 설정
				nftInfoVo.setNft_id_start(nftTypeVo.getNft_id_start());
				nftInfoVo.setNft_id_end(nftTypeVo.getNft_id_end());
				nftInfoVo = this.nftInfoMapper.selectNftInfo(nftInfoVo);
				
				// 조회 결과 없을 경우 환불처리 후 리턴
				if (nftInfoVo == null) {
					// 환불 처리
					this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
					
					commonResultVo.setResultCd("FAIL"); // 비정상처리
					commonResultVo.setResultMsg("selectNftInfo is null.");
					commonResultVo.setReturnCd("5");
					return commonResultVo;
				}
				
				// 민팅 대상 NFT ID 설정
				String nftId = nftInfoVo.getNft_id();
				
				// nft_info 테이블에 해당 data의 status를 처리중(10)으로 수정
				nftInfoVo.setStatus(Constant.NFT_STATUS_MINT_PROCESSING); // "10"
				nftInfoVo.setMod_id(walletAddress); // 수정자 id는 사용자 지갑주소
				int resultCount = this.nftInfoMapper.updateNftInfoStatusProcessing(nftInfoVo);
				
				/////////////////////////////////////////////////////////////////////////////////////////////
				// nft_info 데이터 lock에 실패한 경우 - 성공할때까지 300번(key_value에 설정) 재시도 - START
				/////////////////////////////////////////////////////////////////////////////////////////////
				if (resultCount == 0) {
					// 타 사용자로 인해 STATUS가 10으로 변경되어 STATUS < 10 조건이 안맞아서 update에 실패한 경우 resultCount가 0임.
					// 이 경우에는 update가 성공할 때까지 다시 nft_info 테이블을 조회하고 다시 update 함.
					int processCount = 1; // retry 횟수
					boolean processFailed = false; // 프로세스 실패 여부
					
					int retryCountLimit = 300;
					String getNftIdRetryCount = this.keyValueMapper.selectKeyValue(Constant.KEY_VALUE_GET_NFT_ID_RETRY_COUNT_LIMIT); // get_nft_id_retry_count_limit 
					if (StringUtil.isNotEmpty(getNftIdRetryCount)) {
						retryCountLimit = Integer.parseInt(getNftIdRetryCount);
					}
					// NFT ID 조회 프로세스 반복
					while (resultCount > 0) {
						
						// 0.1초 대기
						Thread.sleep(100);
						
						/////////////////////////////////////////////////////////////////////////////////////////////
						// 다시 nft_info 테이블 조회
						/////////////////////////////////////////////////////////////////////////////////////////////
						nftInfoVo = this.nftInfoMapper.selectNftInfo(nftInfoVo);
						
						// 조회 결과 없을 경우 환불처리 후 리턴
						if (nftInfoVo == null) {
							// 환불 처리
							this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
							
							commonResultVo.setResultCd("FAIL"); // 비정상처리
							commonResultVo.setResultMsg("selectNftInfo is null.");
							commonResultVo.setReturnCd("6");
							return commonResultVo;
						}
						
						// 민팅 대상 NFT ID 설정
						nftId = nftInfoVo.getNft_id();
						
						// nft_info 테이블에 해당 data의 status를 처리중(10)으로 수정
						nftInfoVo.setStatus(Constant.NFT_STATUS_MINT_PROCESSING); // "10"
						nftInfoVo.setMod_id(walletAddress); // 수정자 id는 사용자 지갑주소
						resultCount = this.nftInfoMapper.updateNftInfoStatusProcessing(nftInfoVo);
						
						processCount++; // 처리 카운트 증가
						
						// 300회 retry 후 실패 판정
						if (resultCount == 0 && processCount > retryCountLimit) {
							processFailed = true;
							break;
						}
					}
					
					// 300회 retry 후 프로세스 실패 판정시 - 환불 후 처리 종료
					if (processFailed) {
						// 환불 처리
						this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
						
						commonResultVo.setResultCd("FAIL"); // 비정상처리
						commonResultVo.setResultMsg("Get NFT ID process failed. processCount: " + processCount);
						commonResultVo.setReturnCd("7");
						return commonResultVo;
					}
					
					/////////////////////////////////////////////////////////////////////////////////////////////
					// nft_info 데이터 lock에 실패한 경우 - 성공할때까지 300번(key_value에 설정) 재시도 - END
					/////////////////////////////////////////////////////////////////////////////////////////////
				}
				
				

				/////////////////////////////////////////////////////////////////////////////////////////////
				// 6. New NFT 발행 처리
				/////////////////////////////////////////////////////////////////////////////////////////////
				
				// TODO: try~catch로 묶고 solanaNewController.mintSolanaNft 처리 내에서 exception은 모두 throw로 처리해야함. 그러면 아래 [처리 결과 없을 경우 환불처리 후 리턴] 이부분 없애도 됨
				// 지금은 그냥 놔둬도 될 듯. 나중에 시간 나면 처리.
				MintingLogVo newMintingLogVo = null;
				try {
					newMintingLogVo = (MintingLogVo) this.solanaMintController.mintSolanaNft(projectVo.getProject_id()
																							, projectVo.getProject_symbol()
																							, nftInfoVo.getJson_file_url() // 리빌전 사용할 JSON 파일 URL
																							, projectVo.getCreator_fee() // sellerFeeBasisPoints
																							, projectVo.getCollection_mint_address()
																							, projectVo.getCollection_metadata_address()
																							, projectVo.getCollection_master_edition_address()
																							, walletAddress).getReturnValue();
				} catch (Exception e) {
					// 예외 발생시 환불처리 후 리턴
					
					// 환불 처리
					this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
					
					// nft_info 테이블에 해당 data의 status를 실패(5)로 수정
					if (nftInfoVo != null) {
						nftInfoVo.setStatus(Constant.NFT_STATUS_MINT_FAILED); // "5"
						resultCount = this.nftInfoMapper.updateNftInfoStatus(nftInfoVo);
						logger.debug("nftInfoMapper.updateNftInfoStatus status: " + Constant.NFT_STATUS_MINT_FAILED + ", resultCount: " + resultCount);
					}
					
					e.printStackTrace(); // TODO: 배포시 주석 처리
					
					commonResultVo.setResultCd("FAIL"); // 비정상처리
					commonResultVo.setResultMsg("solanaNewController.mintSolanaNft catch.");
					commonResultVo.setReturnCd("9");
					return commonResultVo;
				}
				
				// 처리 결과 없을 경우 환불처리 후 리턴
				if (newMintingLogVo == null) {
					// 환불 처리
					this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
					
					// nft_info 테이블에 해당 data의 status를 실패(5)로 수정
					if (nftInfoVo != null) {
						nftInfoVo.setStatus(Constant.NFT_STATUS_MINT_FAILED); // "5"
						resultCount = this.nftInfoMapper.updateNftInfoStatus(nftInfoVo);
						logger.debug("nftInfoMapper.updateNftInfoStatus status: " + Constant.NFT_STATUS_MINT_FAILED + ", resultCount: " + resultCount);
					}
					
					commonResultVo.setResultCd("FAIL"); // 비정상처리
					commonResultVo.setResultMsg("solanaNewController.mintSolanaNft is null.");
					commonResultVo.setReturnCd("10");
					return commonResultVo;
				}
				
				// New NFT mint 처리 결과 설정
				mintingLogVo.setMint_account_key(newMintingLogVo.getMint_account_key());
				mintingLogVo.setToken_account_key(newMintingLogVo.getToken_account_key());
				mintingLogVo.setMetadata_account_key(newMintingLogVo.getMetadata_account_key());
				mintingLogVo.setMaster_edition_account_key(newMintingLogVo.getMaster_edition_account_key());
				
				
				/////////////////////////////////////////////////////////////////////////////////////////////
				// 7. NFT 정보 minting_log 테이블에 update
				/////////////////////////////////////////////////////////////////////////////////////////////
				// 5의 처리 후 mintingLogVo 에는 seq 값만 남아있었으나 5의 처리에서 각각의 account_key 설정되었음
				mintingLogVo.setNft_id(nftId); // 3에서 조회한 nft_info 테이블의 id
				mintingLogVo.setMod_id(walletAddress); // 수정자 id는 사용자 지갑주소
				
				resultLogCount = this.mintingLogMapper.updateMintingLog(mintingLogVo);
				if (resultLogCount == 0) {
					commonResultVo.setResultCd("FAIL"); // 비정상처리
					commonResultVo.setResultMsg("updateMintingLog failed.");
					commonResultVo.setReturnCd("11");
					// return commonResultVo; // 로그 관련 처리에 에러가 발생해도 전체 처리를 중지시키지 않음
				}
				
				/////////////////////////////////////////////////////////////////////////////////////////////
				// 8. NFT 타입 nft_type 테이블에 amount_remain - 1 update
				/////////////////////////////////////////////////////////////////////////////////////////////
				int resultTypeCount = this.nftTypeMapper.updateNftTypeRemainCount(nftTypeVo);
				if (resultTypeCount == 0) {
					commonResultVo.setResultCd("FAIL"); // 비정상처리
					commonResultVo.setResultMsg("updateNftTypeRemainCount failed.");
					commonResultVo.setReturnCd("12");
					// return commonResultVo; // 처리에 에러가 발생해도 전체 처리를 중지시키지 않음
					// 관련 처리가 실패하면 한번 더 시도해야 할까요..?
				}

				// 민팅 성공시 환불 예정금액 차감
				// refundCurrencyAmount = refundCurrencyAmount.subtract(new BigInteger(String.valueOf(nftPrice)));
				refundCurrencyAmount = refundCurrencyAmount - nftPrice;
			}
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////
			// ◆◆◆◆◆◆◆ 여기부터 mintAmount 만큼 반복 처리 ◆◆◆◆◆◆◆ END
			// 각각의 처리 완료시마다 currencyAmount를 차감하여 이후 실패시 환불할 금액 수정
			/////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////
			
		} catch (Exception e) {
			// 환불 처리
			this.refund(projectSeq, walletAddress, walletType, nftType, currencySymbol, refundCurrencyAmount);
			
			// nft_info 테이블에 해당 data의 status를 실패(5)로 수정
			if (nftInfoVo != null) {
				nftInfoVo.setStatus(Constant.NFT_STATUS_MINT_FAILED); // "5"
				int resultCount = this.nftInfoMapper.updateNftInfoStatus(nftInfoVo);
				logger.debug("nftInfoMapper.updateNftInfoStatus status: " + Constant.NFT_STATUS_MINT_FAILED + ", resultCount: " + resultCount);
			}
			
			// 결과코드 : 실패
			commonResultVo.setResultCd("FAIL");
			commonResultVo.setReturnCd("-1");
			commonResultVo.setResultMsg(e.toString());
			return commonResultVo;
		}
		
		logger.debug("■■■■■■■ /blockchain/mint ■■■■■■■ END");
		
		return commonResultVo;
	}
	
	
	/**
	 * 환불 처리 - 사용자 지갑주소로 민팅비용 전송
	 * 
	 * @param projectSeq
	 * @param walletAddress
	 * @param walletType
	 * @param nftType
	 * @param currencySymbol
	 * @param refundCurrencyAmount
	 * @return boolean
	 * @throws Exception
	 */
	// @PostMapping("/blockchain/refund")
	public boolean refund(
			int projectSeq
			, String walletAddress
			, String walletType
			, String nftType
			, String currencySymbol
			, int refundCurrencyAmount
			) throws Exception {
		
		logger.debug("□■□■□■□ /blockchain/refund □■□■□■□ START");
		logger.debug("□■□■□■□ param projectSeq: " + projectSeq);
		logger.debug("□■□■□■□ param walletAddress: " + walletAddress);
		logger.debug("□■□■□■□ param walletType: " + walletType);
		logger.debug("□■□■□■□ param nftType: " + nftType);
		logger.debug("□■□■□■□ param currencySymbol: " + currencySymbol);
		logger.debug("□■□■□■□ param refundCurrencyAmount: " + refundCurrencyAmount);
		
		boolean result = true;
		
		try {
			// TODO: 1. 환불처리 로직 구현 + 2. 환불처리 로그 등록
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			// 1. 환불 정보 준비
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			// nft_type 테이블 정보 조회 - nft_type 테이블의 seq 정보가 필요함
			NftTypeVo nftTypeVo = new NftTypeVo();
			nftTypeVo.setProject_seq(projectSeq); // 프로젝트 seq
			nftTypeVo.setNft_type(nftType); // 조회조건으로 화면에서 선택한 NFT Type 설정
			nftTypeVo = this.nftTypeMapper.selectNftType(nftTypeVo);
			
			// nft_price 테이블 정보 조회 - currency_contract 정보가 필요함
			NftPriceVo nftPriceVo = new NftPriceVo();
			nftPriceVo.setProject_seq(projectSeq); // 프로젝트 seq
			nftPriceVo.setNft_type_seq(nftTypeVo.getSeq()); // 2에서 검색한 nft_type 테이블의 seq
			nftPriceVo.setCurrency_symbol(currencySymbol); // 조회조건으로 화면에서 선택한 currencySymbol 설정
			nftPriceVo = this.nftPriceMapper.selectNftPrice(nftPriceVo);
			
			// 결제 통화 contract address
			String refundCurrencyContract = nftPriceVo.getCurrency_contract();
			int refundCurrencyDecimal = nftPriceVo.getCurrency_decimal();
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			// 2. 환불 처리 - 사용자 지갑주소로 민팅비용 전송
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			// refundCurrencyContract, refundCurrencyAmount, refundCurrencyDecimal 정보로 환불 처리 실행
			// admin_wallet_address -> (사용자의) walletAddress 로 전송
			
			try {
				// 통화 전송
				this.solanaController.solanaTransfer(
						Constant.SYSTEM_WALLET_ADDRESS_SOLANA // from
						, walletAddress // to
						, refundCurrencyContract
						, refundCurrencyDecimal
						, refundCurrencyAmount);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			// 3. 환불 처리 로그 등록 - refund_log 테이블
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			// 관련 정보 로그 등록
			
			
			
			
		} catch (Exception e) {
			result = false;
			
			e.printStackTrace(); // TODO: 배포시 주석 처리
			logger.debug("□■□■□■□ /blockchain/refund □■□■□■□ catch - e.getMessage(): " + e.getMessage());
			logger.debug("□■□■□■□ /blockchain/refund □■□■□■□ catch - e.toString(): " + e.toString());
		}
		
		logger.debug("□■□■□■□ /blockchain/refund □■□■□■□ END");
		
		return result;
	}
}