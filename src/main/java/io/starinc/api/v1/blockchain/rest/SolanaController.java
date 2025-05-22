package io.starinc.api.v1.blockchain.rest;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.vo.CommonResultVo;

@RestController
@RequestMapping(value = "/api")
public class SolanaController {
	
	// private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
//	@Autowired
//	private CommonController commonController;
	
	
	/**
	 * Solana 체인에서 통화 전송
	 * 
	 * @param walletAddressFrom
	 * @param walletAddressTo
	 * @param currencyContractAddress
	 * @param currencyDecimal
	 * @param amount
	 * @return CommonResultVo
	 * @throws Exception
	 */
	//@PostMapping("/blockchain/solanaTransfer")
	public CommonResultVo solanaTransfer(
			@RequestParam String walletAddressFrom
			, @RequestParam String walletAddressTo
			, @RequestParam String currencyContractAddress
			, @RequestParam int currencyDecimal
			, @RequestParam int currencyAmount
			) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		// TODO: 솔라나 체인에서 통화 전송 처리
		// 시스템지갑(system_wallet_address_solana) 정보 연결해서 처리
		
		BigInteger currencyAmountBigInteger = new BigInteger(String.valueOf(currencyAmount)); // 환불 비용
		
		// ...
		
		
		
		return commonResultVo;
	}
}