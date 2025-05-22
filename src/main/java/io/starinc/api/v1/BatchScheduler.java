package io.starinc.api.v1;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.starinc.api.v1.batchlog.mapper.BatchLogMapper;
import io.starinc.api.v1.batchlog.vo.BatchLogVo;
import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.common.mapper.KeyValueMapper;
import io.starinc.api.v1.common.util.DateUtil;
import io.starinc.api.v1.common.util.StringUtil;
import io.starinc.api.v1.indodax.rest.IndodaxController;
import io.starinc.api.v1.lbank.rest.LbankController;
import io.starinc.api.v1.lbank.vo.LbankVo;
import io.starinc.api.v1.marketprice.mapper.MarketPriceMapper;
import io.starinc.api.v1.marketprice.vo.MarketPriceVo;
import io.starinc.api.v1.minelog.mapper.MineLogMapper;
import io.starinc.api.v1.minelog.vo.MineLogVo;
import io.starinc.api.v1.nftinfo.mapper.NftInfoMapper;
import io.starinc.api.v1.nftinfo.vo.NftInfoVo;

@Configuration
@EnableScheduling
public class BatchScheduler {
	
//	private static final Logger logger = LoggerFactory.getLogger(BatchScheduler.class);
//	
//	@Autowired
//	private IndodaxController indodaxController;
//	
//	@Autowired
//	private LbankController lbankController;
//	
//	@Autowired
//	private KeyValueMapper keyValueMapper;
//	
//	@Autowired
//	private MarketPriceMapper marketPriceMapper;
//	
//	@Autowired
//	private BatchLogMapper batchLogMapper;
//	
//	@Autowired
//	private NftInfoMapper nftInfoMapper;
//	
//	@Autowired
//	private MineLogMapper mineLogMapper;
//	
//	
//	/**
//	 * 배치
//	 * ① WTEC 가격 market_price 테이블에 등록
//	 * ② WTEC 가격에 따라 Daily 채굴량 mine_log 테이블에 등록
//	 * ※매시 정각(1시간 간격) 호출되게 설정 -> key_value의 batch_time_hour_mine 값인 17(자카르타 시간이 GMT+7시 이므로 17+7=24시)과 같을 경우에만 실행
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	// 
//	@Scheduled(cron = "0 0 0/1 * * *")	// 1시간마다 실행
////	@Scheduled(cron = "0 0 2 * * ?") // 매일 02시 실행
////	 @Scheduled(cron = "0,5,10,15,16,17,18,20,25,30,35,40,45,50,55 * * * * *") // 테스트용
////	 @Scheduled(cron = "17 * * * * *") // 테스트용 - 매분 17초에 실행(currentHour 대신 currentSeconds 를 keyValueBatchTimeHourMine 와 비교)
//	public void runBatchMine() throws Exception {
//		System.out.println("Batch job running at ...............   " + new Date());
//		
//		// 날짜 설정
//		Date todayDate = new Date();
//		int currentHour = todayDate.getHours();
////		int currentSeconds = todayDate.getSeconds(); // 테스트용
//		// System.out.println("currentSeconds: " + currentSeconds); // 테스트용
//		
//		// key_value에서 batch_time_hour_mine 값 조회 = 17
//		String keyValueBatchTimeHourMine = this.keyValueMapper.selectKeyValue(Constant.KEY_VALUE_BATCH_TIME_HOUR_MINT);
//		
//		logger.debug("keyValueBatchTimeHourMine: " + keyValueBatchTimeHourMine + " , currentHour: " + currentHour);
////		logger.debug("keyValueBatchTimeHourMine: " + keyValueBatchTimeHourMine + " , currentSeconds: " + currentSeconds); // 테스트용
//		
//		// 설정값과 같은 경우에만 실행
////		if (keyValueBatchTimeHourMine.equals(String.valueOf(currentSeconds))) { // 테스트용
//		 if (keyValueBatchTimeHourMine.equals(String.valueOf(currentHour))) {
//			// 배치로그 등록용
//			BatchLogVo batchLogVo = new BatchLogVo();
//			batchLogVo.setReg_id(Constant.SYSTEM_REG_ID_BATCH); // "BATCH"
//			
//			try {
//				///////////////////////////////////////////////////
//				// 1. market_price 테이블에 daily_price 등록
//				///////////////////////////////////////////////////
//				String wtecMarketPrice = "0";
//				try {
//					wtecMarketPrice = this.insertDailyMarketPrice();
//				} catch (Exception e) {
//					e.printStackTrace();
//					logger.error(e.getMessage());
//					// batch_log 테이블에 등록
//					batchLogVo.setStatus_cd(Constant.BATCH_STATUS_CD_ERROR_MARKET_PRICE); // ERROR_MARKET_PRICE
//					batchLogVo.setMessage(e.getMessage());
//					this.batchLogMapper.insertBatchLog(batchLogVo);
//				}
//				
//				///////////////////////////////////////////////////
//				// 2. NFT별 Daily 채굴량 mine_log 테이블에 등록
//				///////////////////////////////////////////////////
//				try {
//					this.insertDailyMineLog(wtecMarketPrice);
//				} catch (Exception e) {
//					e.printStackTrace();
//					logger.error(e.getMessage());
//					// batch_log 테이블에 등록
//					batchLogVo.setStatus_cd(Constant.BATCH_STATUS_CD_ERROR_MINE_LOG); // ERROR_MINE_LOG
//					batchLogVo.setMessage(e.getMessage());
//					this.batchLogMapper.insertBatchLog(batchLogVo);
//				}
//				
//				///////////////////////////////////////////////////
//				// 3. 배치로그 등록
//				///////////////////////////////////////////////////
//				batchLogVo.setStatus_cd(Constant.BATCH_STATUS_CD_SUCCESS);
//				batchLogVo.setMessage(null);
//				this.batchLogMapper.insertBatchLog(batchLogVo);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error(e.getMessage());
//				// batch_log 테이블에 등록
//				batchLogVo.setStatus_cd("-1");
//				batchLogVo.setMessage(e.getMessage());
//				this.batchLogMapper.insertBatchLog(batchLogVo);
//			}
//		}
//	}
//	
//	/**
//	 * WTEC 가격 조회 후 market_price 테이블에 등록
//	 * Indodax 거래소에서 WTEC 토큰 가격 조회 실패시, market_price 테이블에서 전일 가격 조회, 그것도 실패시 LBANK 거래소에서 토큰 가격을 조회한다.
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public String insertDailyMarketPrice() throws Exception {
//		MarketPriceVo marketPriceVo = new MarketPriceVo();
//		
//		// 현재 일자 YYYYMMDD
//		String todayYYYYMMDD = DateUtil.getYYYYMMDD();
//		
//		// 1.1 Indodax 거래소에서 시세 조회
//		String wtecMarketPrice = this.indodaxController.getIndodaxTokenPrice(Constant.TOKEN_SYMBOL_WTEC); // "WTEC"
//		
//		// Indodax에서 시세 조회 실패인 경우, 하루전 시세로 처리
//		if (StringUtil.isEmpty(wtecMarketPrice)) {
//			
//			Date todayDate = new Date();
//			
//			// 하루 전일 계산
//			// Calendar 객체를 이용하여 날짜 조작
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(todayDate);
//			calendar.add(Calendar.DAY_OF_MONTH, -1); // 1일 전
//			
//			// 새로운 Date 객체 얻기
//			Date yesterdayDate = calendar.getTime();
//			
//			// SimpleDateFormat을 이용하여 원하는 포맷으로 변환
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//			String yesterdayYYYYMMDD = dateFormat.format(yesterdayDate);
//			
//			// 하루전 시장가격 조회
//			marketPriceVo = new MarketPriceVo();
//			marketPriceVo.setDate(yesterdayYYYYMMDD);
//			marketPriceVo.setCurrency_symbol(Constant.TOKEN_SYMBOL_WTEC); // "WTEC"
//			marketPriceVo = this.marketPriceMapper.selectMarketPriceByDate(marketPriceVo);
//			
//			if (marketPriceVo != null) {
//				wtecMarketPrice = marketPriceVo.getDaily_price();
//			} else {
//				// LBANK 시세 조회
//				LbankVo lbankVo = this.lbankController.getLbankTokenPrice(Constant.LBANK_TOKEN_SYMBOL_PAIR_WTEC_USDT); // "wtec_usdt"
//				wtecMarketPrice = lbankVo.getPrice();
//			}
//		}
//		System.out.println("wtecMarketPrice: " + wtecMarketPrice);
//		
//		// 1.2 시장가격(market_price) insert
//		marketPriceVo = new MarketPriceVo();
//		marketPriceVo.setDate(todayYYYYMMDD);
//		marketPriceVo.setCurrency_symbol(Constant.TOKEN_SYMBOL_WTEC);
//		marketPriceVo.setDaily_price(wtecMarketPrice);
//		marketPriceVo.setReg_id(Constant.SYSTEM_REG_ID_BATCH); // "BATCH"
//		this.marketPriceMapper.insertMarketPrice(marketPriceVo);
//		
//		return wtecMarketPrice;
//	}
//	
//	/**
//	 * NFT 채굴량 mine_log 테이블에 등록
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public void insertDailyMineLog(String wtecMarketPrice) throws Exception {
//		
//		// 1. nft_info 테이블에서 status = 20 (민팅 완료) && 민팅 후 1일 경과인 데이터 조회
//		List<NftInfoVo> nftInfoList = this.nftInfoMapper.selectNftInfoMintCompletedList(null);
//		
//		// 민팅 완료된 NFT가 있을 경우에만 실행
//		if (nftInfoList != null && nftInfoList.size() > 0) {
//			
//			for (int i = 0; i < nftInfoList.size(); i++) {
//				NftInfoVo nftInfoVo = nftInfoList.get(i);
//				
//				// 타입별 채굴량 계산
//				String rewardWtecDollorValue = "0";
//				String rewardBitcoinTh = null;
//				if (Constant.NFT_TYPE_A.equals(nftInfoVo.getNft_type())) {
//					// rewardWtecDollorValue = 1250 / 1000 = 1.25
//					rewardWtecDollorValue = "1.25";
//					rewardBitcoinTh = "35";
//				} else if (Constant.NFT_TYPE_S.equals(nftInfoVo.getNft_type())) {
//					// rewardWtecDollorValue = 2500 / 1000 = 2.5
//					rewardWtecDollorValue = "2.5";
//					rewardBitcoinTh = "70";
//				} else if (Constant.NFT_TYPE_R.equals(nftInfoVo.getNft_type())) {
//					// rewardWtecDollorValue = 5000 / 1000 = 5
//					rewardWtecDollorValue = "5";
//					rewardBitcoinTh = "150";
//				} else if (Constant.NFT_TYPE_V.equals(nftInfoVo.getNft_type())) {
//					// rewardWtecDollorValue = 12500 / 1000 = 12.5
//					rewardWtecDollorValue = "12.5";
//					rewardBitcoinTh = "350";
//				} else if (Constant.NFT_TYPE_ME100.equals(nftInfoVo.getNft_type())) {
//					// rewardWtecDollorValue = 100 / 1000 = 0.1
//					rewardWtecDollorValue = "0.1";
//				} else if (Constant.NFT_TYPE_ME1500.equals(nftInfoVo.getNft_type())) {
//					// rewardWtecDollorValue = 1500 / 1000 = 1.5
//					rewardWtecDollorValue = "1.5";
//				} else if (Constant.NFT_TYPE_ME10000.equals(nftInfoVo.getNft_type())) {
//					// rewardWtecDollorValue = 10000 / 1000 = 10
//					rewardWtecDollorValue = "10";
//				}
//				
//				// WTEC 갯수 계산
//				Double rewardWtecDollorValueDouble = Double.valueOf(rewardWtecDollorValue);
//				Double wtecAmount =  rewardWtecDollorValueDouble / Double.valueOf(wtecMarketPrice);
//				
//				// mint_log 테이블에 insert - WTEC
//				MineLogVo mineLogVo = new MineLogVo();
//				mineLogVo.setProject_seq(nftInfoVo.getProject_seq());
//				mineLogVo.setNft_id(nftInfoVo.getNft_id());
//				mineLogVo.setMine_type(Constant.TOKEN_SYMBOL_WTEC); // "WTEC"
//				mineLogVo.setDaily_price(wtecMarketPrice);
//				mineLogVo.setAmount(wtecAmount.toString());
//				mineLogVo.setReg_id(Constant.SYSTEM_REG_ID_BATCH); // "BATCH"
//				
//				this.mineLogMapper.insertMineLog(mineLogVo);
//				
//				
//				// mint_log 테이블에 insert - BTC
//				if (StringUtil.isNotEmpty(rewardBitcoinTh)) {
//					mineLogVo = new MineLogVo();
//					mineLogVo.setProject_seq(nftInfoVo.getProject_seq());
//					mineLogVo.setNft_id(nftInfoVo.getNft_id());
//					mineLogVo.setMine_type(Constant.TOKEN_SYMBOL_BTC); // "BTC"
//					mineLogVo.setAmount(rewardBitcoinTh);
//					mineLogVo.setReg_id(Constant.SYSTEM_REG_ID_BATCH); // "BATCH"
//					
//					this.mineLogMapper.insertMineLog(mineLogVo);
//				}
//				
//			} // for loop
//			
//		}
//		
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
