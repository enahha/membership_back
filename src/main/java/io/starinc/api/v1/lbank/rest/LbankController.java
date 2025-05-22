package io.starinc.api.v1.lbank.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.starinc.api.v1.lbank.vo.LbankVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class LbankController {
	
	// private static final Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);
	
//	@Autowired
//	private FileMstMapper fileMstMapper;

//	@Value("${koreaexim.authkey}")
//	private String koreaeximAuthkey;
	
	// 요청 URL
	private static final String LBANK_API_URI_TOKEN_PRICE_LIST = "https://api.lbank.info/v2/supplement/ticker/price.do";
	
	/**
	 * 토큰 가격을 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/lbank/getLbankTokenPrice")
	public LbankVo getLbankTokenPrice(@RequestParam String tokenSymbolPair) throws Exception {
		LbankVo lbankVo = new LbankVo();
		
		try {
			// 반환용 넘버 포멧
//			NumberFormat formatAmount = NumberFormat.getInstance();
//			formatAmount.setGroupingUsed(false); // 지수표시 안함
//			formatAmount.setMinimumFractionDigits(6); // 소수점 6자리까지 표시
//			
			// String tokenSymbolPair = "wtec_usdt";
			
			// 1. 토큰 시세정보 조회
			// HttpResponseVo httpResponseVo = this.sendGet(this.klayswapTokenInfoUrl);
			final HttpClient client = HttpClientBuilder.create().build();
			final HttpGet httpGet = new HttpGet(LBANK_API_URI_TOKEN_PRICE_LIST);
			final HttpResponse response = client.execute(httpGet);
			final int responseCode = response.getStatusLine().getStatusCode();
			
			if (responseCode != 200) {
				lbankVo.setResultCd("FAIL");
				lbankVo.setResultMsg("ResponseCode is not 200.");
			}
			
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			
			// {"result":true,"data":[{"symbol":"ladyf_usdt","price":"0.00001829"},{"symbol":"ccx_usdt","price":"0.004648"},{"symbol":"paragon_usdt","price":"0.8974""}, ... ],"error_code":0,"ts":1718366594632}
			JsonNode returnNode = mapper.readTree(response.getEntity().getContent());
			// [{"symbol":"ladyf_usdt","price":"0.00001829"},{"symbol":"ccx_usdt","price":"0.004648"},{"symbol":"paragon_usdt","price":"0.8974""}, ... ]
			JsonNode dataNode = returnNode.get("data");
			
			// 2. 토큰 시세정보 검색 및 가격 설정
			for (int i = 0; i < dataNode.size(); i++) {
				JsonNode rowObj = dataNode.get(i);
				// ex) {"symbol":"wtec_usdt","price":"0.4878"}
				String symbol = rowObj.get("symbol").asText().replaceAll("\"", "");;
				if (tokenSymbolPair.equals(symbol)) {
					String price = rowObj.get("price").asText().replaceAll("\"", "");
					lbankVo.setPrice(price);
					// System.out.println(price);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			lbankVo.setResultCd("FAIL");
			lbankVo.setResultMsg("catched.");
			lbankVo.setPrice("-1");
		}
		return lbankVo;
	}
	
}
