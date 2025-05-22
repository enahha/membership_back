package io.starinc.api.v1.admin.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class AdminTestController {
	
	// private static final Logger logger = LoggerFactory.getLogger(AdminTestController.class);
	
	@Value("${klaytn.admin-wallet-address}")
	private String klaytnAdminWalletAddress;
	
//	@Autowired
//	private ContractKlaytnController contractKlaytnController;
	
//	@Autowired
//	private TokenMapper tokenMapper;
	
//	/**
//	 * Excute IDO
//	 * 
//	 * @param contractAddress
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/admin/excuteIdoContractTest" , method = {RequestMethod.POST})
//	public CommonVo excuteIdoContractTest(@RequestBody TokenVo tokenVo) throws Exception {
//		CommonVo commonVo = new CommonVo();
//		commonVo.setResultCd("SUCCESS");
//		try {
//			Caver caver = new Caver(Constant.KLAYTN_MAINNET_URL);
//			SingleKeyring deployer = KeyringFactory.createFromKlaytnWalletKey(Constant.KLAYTN_WALLET_KEY_CREATE_POOL);
//			caver.wallet.add(deployer);
//			
//			// Request request = caver.rpc.klay.getCode(contractAddress);
//			
//			
//			// 1. 토큰 등록 정보 조회
//			TokenVo dbTokenVo = tokenMapper.selectTokenPaid(tokenVo);
//			if (dbTokenVo == null) {
//				commonVo.setResultCd("FAIL");
//				commonVo.setResultMsg("No token in DB.");
//				return commonVo;
//			}
//			
//			// ContractResultVo contractResultVo = contractKlaytnController.excuteIdo(ContractUtil.CONTRACT_NAME_KSP_FACTORY_KLAYTN, dbTokenVo);
//			
//			
//			ContractResultVo contractResultVo = contractKlaytnController.createPool(dbTokenVo);
//			
//			
//			
////			logger.debug("■■■■■ result.getId(): " + request.getId());
//			
//		} catch (Exception e) {
//			// returnVo에 해당 코드를 설정해도 front에서 받을 수 없음
//			// @Transactional 에서 404로 반환해버림...
//			e.printStackTrace();
//			commonVo.setResultCd("FAIL");
//			commonVo.setResultMsg("Create New Token Failed..");
//		}
//		return commonVo;
//	}
	
	
//	/**
//	 * 배포 코드 조회
//	 * 
//	 * @param contractAddress
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/admin/getContractCode" , method = {RequestMethod.GET})
//	public CommonVo getContractCode(@RequestParam String contractAddress) throws Exception {
//		CommonVo commonVo = new CommonVo();
//		commonVo.setResultCd("SUCCESS");
//		try {
//			Caver caver = new Caver(Constant.KLAYTN_MAINNET_URL);
//			SingleKeyring deployer = KeyringFactory.createFromKlaytnWalletKey(Constant.KLAYTN_WALLET_KEY_CREATE_TOKEN);
//			caver.wallet.add(deployer);
//			
//			Request request = caver.rpc.klay.getCode(contractAddress);
//			logger.debug("■■■■■ result.getId(): " + request.getId());
//			logger.debug("■■■■■ result.getJsonrpc(): " + request.getJsonrpc());
//			logger.debug("■■■■■ result.getMethod(): " + request.getMethod());
//			logger.debug("■■■■■ result.getParams(): " + request.getParams());
//			logger.debug("■■■■■ result.getResponseType(): " + request.getResponseType());
//			
//			Response res = request.send();
//			Object obj = res.getResult();
//			
//			logger.debug("■■■■■ obj.toString(): " + obj.toString());
//			
////			Contract contract = caver.contract.create(abiJson);
//			
////			SendOptions sendOptions = new SendOptions();
////			sendOptions.setFrom(deployer.getAddress());
////			sendOptions.setGas(BigInteger.valueOf(4000000));
////			
////			ContractDeployParams deployParam = new ContractDeployParams(byteCode, deployParams);
////
////			Contract newContract = contract.deploy(deployParam, sendOptions);
////			// TransactionReceipt.TransactionReceiptData receipt = contract.send(sendOptions, "set", "test", "testValue");
////			
////			System.out.println("Contract address : " + newContract.getContractAddress());
//			
//		} catch (Exception e) {
//			// returnVo에 해당 코드를 설정해도 front에서 받을 수 없음
//			// @Transactional 에서 404로 반환해버림...
//			e.printStackTrace();
//			commonVo.setResultCd("FAIL");
//			commonVo.setResultMsg("Create New Token Failed..");
//		}
//		return commonVo;
//	}
}
