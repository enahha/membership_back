package io.starinc.api.v1.indodax.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.common.mapper.KeyValueMapper;
import io.starinc.api.v1.common.util.StringUtil;
import io.starinc.api.v1.common.vo.KeyValueVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class IndodaxController {
	
	// private static final Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);
	
	// 요청 URL
	private static final String INDODAX_API_URI_TOKEN_PRICE_BASE = "https://indodax.com/api/pairs_v2?pair=";
	
	@Autowired
	private KeyValueMapper keyValueMapper;
	
	/**
	 * 토큰 가격을 조회한다.
	 * 
	 * @param tokenSymbol
	 * @return price
	 * @throws Exception
	 */
	@GetMapping("/indodax/getIndodaxTokenPrice")
	public String getIndodaxTokenPrice(@RequestParam String tokenSymbol) throws Exception {
		try {
			// key_value 테이블에서 수정시간이 1분이 지나지 않은 'last_wtec_price' 데이터를 조회
			// ① 조회 결과가 있는 경우 : 결과값 반환
			// ② 조회 결과가 없는 경우 : indodax.com 시세 데이터 조회 후 key_value 테이블 업데이트 및 결과값 반환
			String intervalSecond = this.keyValueMapper.selectKeyValue(Constant.KEY_VALUE_LAST_PRICE_WTEC_INTERVAL_SECOND);
			String lastPriceWtec = this.keyValueMapper.selectKeyValueLastPriceWtec(intervalSecond);
			if (StringUtil.isNotEmpty(lastPriceWtec)) {
				return lastPriceWtec;
			}
			
			///////////////////////////////////////////////////////////////////////////////////
			// 1. USDT-IDR 페어 조회
			///////////////////////////////////////////////////////////////////////////////////
			final HttpClient client = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(INDODAX_API_URI_TOKEN_PRICE_BASE + "USDTIDR");
			HttpResponse response = client.execute(httpGet);
			int responseCode = response.getStatusLine().getStatusCode();
			
			if (responseCode != 200) {
				System.out.println("responseCode is not 200");
				return this.keyValueMapper.selectKeyValue(Constant.KEY_VALUE_LAST_PRICE_WTEC);
			}
			
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			
			JsonNode returnNode = mapper.readTree(response.getEntity().getContent());
			//{
			//	"id": "usdtidr",
			//	"symbol": "USDTIDR",
			//	"base_currency": "idr",
			//	"traded_currency": "usdt",
			//	"traded_currency_unit": "USDT",
			//	"description": "USDT\/IDR",
			//	"ticker_id": "usdt_idr",
			//	"volume_precision": 0,
			//	"price_precision": 1,
			//	"minmov": 1,
			//	"price_round": 8,
			//	"pricescale": 1,
			//	"trade_min_base_currency": 10000,
			//	"trade_min_traded_currency": 0.60790273,
			//	"has_memo": false,
			//	"memo_name": false,
			//	"trade_fee_percent": 0.2,
			//	"trade_fee_percent_taker": 0.2,
			//	"trade_fee_percent_maker": 0.1,
			//	"url_logo": "https:\/\/indodax.com\/v2\/logo\/svg\/color\/usdt.svg",
			//	"url_logo_png": "https:\/\/indodax.com\/v2\/logo\/png\/color\/usdt.png",
			//	"is_maintenance": 0
			//}
			
			// 2. USDT-IDR 토큰 가격 비율 설정
			String trade_min_traded_currency_usdtidr = returnNode.get("trade_min_traded_currency").asText().replaceAll("\"", "");
			
			// System.out.println("trade_min_traded_currency_usdtidr: " + trade_min_traded_currency_usdtidr);
			
			///////////////////////////////////////////////////////////////////////////////////
			// 2. WTEC-IDR 페어 조회
			///////////////////////////////////////////////////////////////////////////////////
			httpGet = new HttpGet(INDODAX_API_URI_TOKEN_PRICE_BASE + tokenSymbol + "IDR");
			response = client.execute(httpGet);
			responseCode = response.getStatusLine().getStatusCode();
			
			if (responseCode != 200) {
				System.out.println("responseCode is not 200");
				return this.keyValueMapper.selectKeyValue(Constant.KEY_VALUE_LAST_PRICE_WTEC);
			}
			
			// JSON 형태 반환값 처리
			mapper = new ObjectMapper();
			
			returnNode = mapper.readTree(response.getEntity().getContent());
			//{
			//	"id": "wtecidr",
			//	"symbol": "WTECIDR",
			//	"base_currency": "idr",
			//	"traded_currency": "wtec",
			//	"traded_currency_unit": "WTEC",
			//	"description": "WTEC\/IDR",
			//	"ticker_id": "wtec_idr",
			//	"volume_precision": 0,
			//	"price_precision": 1,
			//	"minmov": 1,
			//	"price_round": 8,
			//	"pricescale": 1,
			//	"trade_min_base_currency": 10000,
			//	"trade_min_traded_currency": 1.53893505,
			//	"has_memo": false,
			//	"memo_name": false,
			//	"trade_fee_percent": 0.2,
			//	"trade_fee_percent_taker": 0.2,
			//	"trade_fee_percent_maker": 0.1,
			//	"url_logo": "https:\/\/indodax.com\/v2\/logo\/svg\/color\/wtec.svg",
			//	"url_logo_png": "https:\/\/indodax.com\/v2\/logo\/png\/color\/wtec.png",
			//	"is_maintenance": 0
			//}
			
			// 2. WTEC-IDR 토큰 가격 비율 설정
			String trade_min_traded_currency_wtecidr = returnNode.get("trade_min_traded_currency").asText().replaceAll("\"", "");
			
			// System.out.println("trade_min_traded_currency_wtecidr: " + trade_min_traded_currency_wtecidr);
			
			BigDecimal trade_min_traded_currency_usdtidr_big_decimal = new BigDecimal(trade_min_traded_currency_usdtidr);
			BigDecimal trade_min_traded_currency_wtecidr_big_decimal = new BigDecimal(trade_min_traded_currency_wtecidr);
			
			// 소수점 4자리까지 올림 계산해서 반환
			String price = String.valueOf(trade_min_traded_currency_usdtidr_big_decimal.divide(trade_min_traded_currency_wtecidr_big_decimal, 4, RoundingMode.CEILING));
			
			KeyValueVo keyValueVo = new KeyValueVo();
			keyValueVo.setCd_key(Constant.KEY_VALUE_LAST_PRICE_WTEC);
			keyValueVo.setCd_value(price);
			keyValueVo.setDel_yn("N");
			keyValueVo.setMod_id(Constant.SYSTEM_MOD_ID);
			
			int resultCount = this.keyValueMapper.mergeKeyValue(keyValueVo);
			if (resultCount == 0) {
				return this.keyValueMapper.selectKeyValue(Constant.KEY_VALUE_LAST_PRICE_WTEC);
			} else {
				return price; // 계산 결과 반환
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.keyValueMapper.selectKeyValue(Constant.KEY_VALUE_LAST_PRICE_WTEC);
		}
	}
}
