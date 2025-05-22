package io.starinc.api.v1.exchangerate.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.starinc.api.v1.common.util.StringUtil;
import io.starinc.api.v1.exchangerate.mapper.ExchangeRateMapper;
import io.starinc.api.v1.exchangerate.vo.ExchangeRateVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class ExchangeRateController {
	
	// private static final Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);
	
	@Autowired
	private ExchangeRateMapper exchangeRateMapper;
	
//	@Autowired
//	private FileMstMapper fileMstMapper;

	@Value("${koreaexim.authkey}")
	private String koreaeximAuthkey;
	
	// 요청 URL
	private static final String requestBaseUrl = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=";
	
	/**
	 * 환율을 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/exchangerate/selectExchangeRate")
	public ExchangeRateVo selectExchangeRate(@RequestParam String uid) throws Exception {
		ExchangeRateVo exchangeRateVo = new ExchangeRateVo();
		
		// 환율
		String exchangeRate = "";
		
		// 어제 날짜 계산
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
		calendar.add(Calendar.DATE, -1);
		String searchDate = SDF.format(calendar.getTime());
		exchangeRateVo.setSearch_date(searchDate);
		
		// DB 조회
		// 1. 데이터가 있으면 반환
		// 2. 데이터가 없으면 한국수출입은행 API로 조회 후 DB에 저장
		ExchangeRateVo exchangeRateVoDb = this.exchangeRateMapper.selectExchangeRate(exchangeRateVo);
		
		try {
			if (exchangeRateVoDb == null) {
				// 2. 데이터가 없으면 한국수출입은행 API로 조회 후 DB에 저장
				// 요청 URL
				String requestUrl = requestBaseUrl + this.koreaeximAuthkey + "&searchdate=" + searchDate + "&data=AP01";
				
				
				// 반환용 넘버 포멧
//				NumberFormat formatPrice = NumberFormat.getInstance();
//				formatPrice.setGroupingUsed(false); // 지수표시 안함
//				formatPrice.setMinimumFractionDigits(2); // 소수점 10자리까지 표시
				
				final HttpClient client = HttpClientBuilder.create().build();
				final HttpGet httpGet = new HttpGet(requestUrl);
				final HttpResponse response = client.execute(httpGet);
				final int responseCode = response.getStatusLine().getStatusCode();
				
				if (responseCode == 200) {
					// 정상인 경우에만 DB에 환율 등록
					// JSON 형태 반환값 처리
					ObjectMapper mapper = new ObjectMapper();
					JsonNode returnNode = mapper.readTree(response.getEntity().getContent());
					
					// 2. 토큰정보 검색 및 가격 설정
					for (int i = 0; i < returnNode.size(); i++) {
						JsonNode rowObj = returnNode.get(i);
						// ex) [{"result":1,"cur_unit":"USD","ttb":"1,274.62","tts":"1,300.37","deal_bas_r":"1,287.5","bkpr":"1,287","yy_efee_r":"0","ten_dd_efee_r":"0","kftc_bkpr":"1,287","kftc_deal_bas_r":"1,287.5","cur_nm":"미국 달러"}]
						String rowResult = String.valueOf(rowObj.get("result"));
						String rowCurUnit = String.valueOf(rowObj.get("cur_unit"));
						if (!StringUtil.isEmpty(rowCurUnit)) {
							rowCurUnit = rowCurUnit.replaceAll("\"", "");
						}
						if ("1".equals(rowResult) && "USD".equals(rowCurUnit)) {
							exchangeRate = String.valueOf(rowObj.get("deal_bas_r"));
							if (!StringUtil.isEmpty(exchangeRate)) {
								exchangeRate = exchangeRate.replaceAll("\"", "");
								exchangeRate = exchangeRate.replaceAll(",", "");
							}
							break;
						}
					}
					
					if (!StringUtil.isEmpty(exchangeRate)) {
						// DB에 등록
						// exchangeRateVo.setDeal_bas_r(formatPrice.format(new Double(exchangeRate)));
						exchangeRateVo.setDeal_bas_r(exchangeRate);
						exchangeRateVo.setReg_id(uid);
						this.exchangeRateMapper.insertExchangeRate(exchangeRateVo);
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			// 결과코드 : 실패
			exchangeRateVo.setResultCd("FAIL");
			exchangeRateVo.setResultMsg(e.toString());
		}
		
		if (exchangeRateVoDb == null) {
			// 데이터 조회
			exchangeRateVoDb = this.exchangeRateMapper.selectLastExchangeRate(exchangeRateVo);
			if (exchangeRateVoDb == null) {
				exchangeRateVo.setResultCd("FAIL");
				return exchangeRateVo;
			} else {
				exchangeRateVoDb.setResultCd("SUCCESS");
			}
		}
		return exchangeRateVoDb;
	}
	
}
