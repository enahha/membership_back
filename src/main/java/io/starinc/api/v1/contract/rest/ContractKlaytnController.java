package io.starinc.api.v1.contract.rest;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klaytn.caver.contract.Contract;
import com.klaytn.caver.contract.ContractDeployParams;
import com.klaytn.caver.contract.SendOptions;

import io.starinc.api.v1.contract.ContractUtil;
import io.starinc.api.v1.contract.vo.ContractCreateAirdropVo;
import io.starinc.api.v1.contract.vo.ContractResultVo;

//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api/contract")
public class ContractKlaytnController {
	
	private static final Logger logger = LoggerFactory.getLogger(ContractKlaytnController.class);
	
	// @Autowired
	// private UserMapper userMapper;
	
	
	@Value("${klaytn.contract-abi}")
	private String klaytnContractAbi;
	
	@Value("${klaytn.contract-bin}")
	private String klaytnContractBin;
	
	@Value("${klaytn.contract-airdrop-abi}")
	private String klaytnContractAirdropAbi;
	
	@Value("${klaytn.contract-airdrop-bin}")
	private String klaytnContractAirdropBin;
	
	
//	// KSP-FACTORY
//	@Value("${klaytn.contract-ksp-factory-abi}")
//	private String klaytnContractKspFactoryAbi;
//	
//	@Value("${klaytn.contract-ksp-factory-bin}")
//	private String klaytnContractKspFactoryBin;
	
	
//	@Value("${klaytn.contract-ksp-factory-create-klay-pool-abi}")
//	private String klaytnContractKspFactoryCreateKlayPoolAbi;
	
//	@Value("${klaytn.contract-ksp-factory-abi}")
//	private String klaytnContractKspFactoryAbi;	
	
//	/**
//	 * 토큰 생성
//	 * 
//	 * @param contractName
//	 * @param contractCreateTokenVo
//	 * @return
//	 * @throws Exception
//	 */
//	public ContractResultVo createToken(String contractName, ContractCreateTokenVo contractCreateTokenVo) throws Exception {
//		System.out.println("createToken called");
//		System.out.println(contractCreateTokenVo);
//		
//		ContractResultVo contractResultVo = new ContractResultVo();
//		contractResultVo.setResultCd("SUCCESS");
//		contractResultVo.setResultMsg("Create token success.");
//		
//		String tokenName = contractCreateTokenVo.getToken_name();
//		String tokenSymbol = contractCreateTokenVo.getToken_symbol();
//		int tokenDecimals = contractCreateTokenVo.getToken_decimals();
//		String tokenInitalSupply = contractCreateTokenVo.getToken_initial_supply();
//		String tokenToAddress = contractCreateTokenVo.getToken_to_address();
//
//		try {
//			
////			/***************************************************************************
////			// BeastarapiApplication 에서 초기 실행시키면 운영에서 안돌아감.. 로컬은 됨.. 뭐가 문제일까ㅋ
////			***************************************************************************/
////			// ABI_JSON_MAP에 해당 값이 없으면 컨트랙트 세팅. 서버기동후 초기 1회 실행됨.
////			if (StringUtil.isEmpty(ContractUtil.ABI_JSON_MAP.get(contractName))) {
////				// 컨트랙트 .sol 파일 컴파일 해서 abi, bin을 각각 ContractUtil.java의  ABI_JSON_MAP, BYTE_CODE_MAP 변수에 설정한다.
////				ContractUtil.setContract(ContractUtil.CONTRACT_NAME_CREATE_TOKEN_KLAYTN, this.klaytnContractAbi, this.klaytnContractBin);
////			}
////			////////////////////////////////////////////////////////////////////////////
////			
////			String abiJson = ContractUtil.ABI_JSON_MAP.get(contractName);
////			String byteCode = ContractUtil.BYTE_CODE_MAP.get(contractName);
//			
//			// 컨트랙트 GET
//			Contract contract = ContractUtil.getContractKlaytn(contractName, this.klaytnContractAbi, this.klaytnContractBin);
//			
//			// SendOption 설정
//			SendOptions sendOptions = new SendOptions();
//			sendOptions.setFrom(ContractUtil.KLAYSTAR_WALLET_ADDRESS_KLAYTN);
//			sendOptions.setGas(BigInteger.valueOf(ContractUtil.KLAYSTAR_SEND_OPTIONS_GAS));
//			
//			Object[] paramsDeploy = {
//					tokenName
//					, tokenSymbol
//					, tokenDecimals
//					, new BigInteger(tokenInitalSupply)
//					, tokenToAddress
//				};
//			ContractDeployParams deployParam = new ContractDeployParams(this.klaytnContractBin, paramsDeploy);
//			
//			// 토큰 생성 - constructor에서 생성
//			Contract newContract = contract.deploy(deployParam, sendOptions);
//			
//			// 실행 결과 컨트랙트 주소 설정
//			contractResultVo.setContractAddress(newContract.getContractAddress());
//			
////			// 토큰 생성
////			ContractMethod methodMint = newContract.getMethod("mint");
////			Object[] paramsMint = {
////					tokenToAddress // 수신자 지갑주소
////					,new BigInteger(tokenInitalSupply)
////				};
////			TransactionReceiptData receiptMint = methodMint.send(Arrays.asList(paramsMint), sendOptions);
////			logger.info("contractAddress: " + receiptMint.getContractAddress());
////			logger.info("transactionHash: " + receiptMint.getTransactionHash());
////			contractResultVo.setTxHash(receiptMint.getTransactionHash());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			contractResultVo.setResultCd("FAIL");
//			contractResultVo.setResultMsg("Create and Send token failed.");
//		}
//		return contractResultVo;
//	}
	
	
	
	
	/**
	 * 에어드랍 생성
	 * 
	 * @param contractName
	 * @param contractCreateAirdropVo
	 * @return
	 * @throws Exception
	 */
	public ContractResultVo createAirdrop(String contractName, ContractCreateAirdropVo contractCreateAirdropVo) throws Exception {
		System.out.println("createToken called");
		System.out.println(contractCreateAirdropVo);
		
		ContractResultVo contractResultVo = new ContractResultVo();
		contractResultVo.setResultCd("SUCCESS");
		contractResultVo.setResultMsg("Create token success.");
		
		try {
			// 컨트랙트 GET
			Contract contract = ContractUtil.getContractKlaytn(contractName, this.klaytnContractAirdropAbi, this.klaytnContractAirdropBin);
			
			// SendOption 설정
			SendOptions sendOptions = new SendOptions();
			sendOptions.setFrom(ContractUtil.KLAYSTAR_WALLET_ADDRESS_KLAYTN);
			sendOptions.setGas(BigInteger.valueOf(ContractUtil.KLAYSTAR_SEND_OPTIONS_GAS));
			
			Object[] paramsDeploy = {
					contractCreateAirdropVo.getToken_contract_address()
					, contractCreateAirdropVo.getPool_contract_address()
				};
			ContractDeployParams deployParam = new ContractDeployParams(this.klaytnContractAirdropBin, paramsDeploy);
			
			// 토큰 생성 - constructor에서 생성
			Contract newContract = contract.deploy(deployParam, sendOptions);
			
			// 실행 결과 컨트랙트 주소 설정
			contractResultVo.setContractAddress(newContract.getContractAddress());
			
		} catch (Exception e) {
			e.printStackTrace();
			contractResultVo.setResultCd("FAIL");
			contractResultVo.setResultMsg("Create airdrop failed.");
		}
		return contractResultVo;
	}
	
	
	
	
//	
//	
//	
//	public ContractResultVo addTotalSupplyToken(String contractName, TokenVo tokenVo) throws Exception {
//		System.out.println("addTotalSupplyToken called");
//		System.out.println(tokenVo);
//		
//		ContractResultVo contractResultVo = new ContractResultVo();
//		contractResultVo.setResultCd("SUCCESS");
//		contractResultVo.setResultMsg("addTotalSupplyToken success.");
//		
//		String tokenName = tokenVo.getName();
//		String tokenSymbol = tokenVo.getSymbol();
//		int tokenDecimals = tokenVo.getDecimals();
//		String tokenInitalSupply = tokenVo.getInitial_supply();
//		String addTotalSupplyAmount = tokenVo.getAdd_total_supply_amount();
//		String tokenToAddress = tokenVo.getWallet_address();
//
//		try {
//			
////			/***************************************************************************
////			// BeastarapiApplication 에서 초기 실행시키면 운영에서 안돌아감.. 로컬은 됨.. 뭐가 문제일까ㅋ
////			***************************************************************************/
////			// ABI_JSON_MAP에 해당 값이 없으면 컨트랙트 세팅. 서버기동후 초기 1회 실행됨.
////			if (StringUtil.isEmpty(ContractUtil.ABI_JSON_MAP.get(contractName))) {
////				// 컨트랙트 .sol 파일 컴파일 해서 abi, bin을 각각 ContractUtil.java의  ABI_JSON_MAP, BYTE_CODE_MAP 변수에 설정한다.
////				ContractUtil.setContract(ContractUtil.CONTRACT_NAME_SET_TOKEN_KLAYTN, this.klaytnContractAbi, this.klaytnContractBin);
////			}
////			////////////////////////////////////////////////////////////////////////////
////			
////			String abiJson = ContractUtil.ABI_JSON_MAP.get(contractName);
////			String byteCode = ContractUtil.BYTE_CODE_MAP.get(contractName);
//			
//			// 컨트랙트 GET
//			Contract contract = ContractUtil.getContractKlaytn(contractName, this.klaytnContractAbi, this.klaytnContractBin);
//			
//			// SendOption 설정
//			SendOptions sendOptions = new SendOptions();
//			sendOptions.setFrom(ContractUtil.KLAYSTAR_WALLET_ADDRESS_KLAYTN);
//			sendOptions.setGas(BigInteger.valueOf(ContractUtil.KLAYSTAR_SEND_OPTIONS_GAS));
//			
//			Object[] paramsDeploy = {
//					tokenName
//					, tokenSymbol
//					, tokenDecimals
//					, new BigInteger(tokenInitalSupply)
//					, tokenToAddress
//			};
//			ContractDeployParams deployParam = new ContractDeployParams(this.klaytnContractBin, paramsDeploy);
//			
//			// 토큰 생성 - constructor에서 생성
//			Contract newContract = contract.deploy(deployParam, sendOptions);
//			
//			// 실행 결과 컨트랙트 주소 설정
//			contractResultVo.setContractAddress(newContract.getContractAddress());
//			
//			// 토큰 발행량 추가
//			ContractMethod methodMint = newContract.getMethod("mint");
//			Object[] paramsMint = {
//					tokenToAddress // 수신자 지갑주소
//					,new BigInteger(addTotalSupplyAmount)
//			};
//			TransactionReceiptData receiptMint = methodMint.send(Arrays.asList(paramsMint), sendOptions);
//			logger.info("contractAddress: " + receiptMint.getContractAddress());
//			logger.info("transactionHash: " + receiptMint.getTransactionHash());
//			contractResultVo.setTxHash(receiptMint.getTransactionHash());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			contractResultVo.setResultCd("FAIL");
//			contractResultVo.setResultMsg("addTotalSupplyToken failed.");
//		}
//		return contractResultVo;
//	}
	
	
}
