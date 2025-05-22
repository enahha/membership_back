/**
 * 
 */
package io.starinc.api.v1.contract;

import java.io.IOException;

import io.starinc.api.v1.common.Constant;
import com.klaytn.caver.Caver;
import com.klaytn.caver.contract.Contract;
import com.klaytn.caver.wallet.keyring.KeyringFactory;
import com.klaytn.caver.wallet.keyring.SingleKeyring;

/**
 * @author ahn
 *
 */
public class ContractUtil {
	
	// private static final Logger logger = LoggerFactory.getLogger(ContractUtil.class);
	
//	public static final String CONTRACT_NAME_KLAYSTAR_KLAYTN = "KIP7Token";
	public static final String CONTRACT_NAME_CREATE_TOKEN_KLAYTN = "CREATE_TOKEN_KLAYTN";
	public static final String CONTRACT_NAME_CREATE_AIRDROP_KLAYTN = "CREATE_AIRDROP_KLAYTN";
	public static final String CONTRACT_NAME_SET_TOKEN_KLAYTN = "SET_TOKEN_KLAYTN";
//	public static final String CONTRACT_NAME_KSP_FACTORY_KLAYTN = "KSP_FACTORY_KLAYTN";
	public static final String CONTRACT_ADDRESS_KSP_FACTORY_KLAYTN = "0xc6a2ad8cc6e4a7e08fc37cc5954be07d499e7654"; // KSP 공식
	public static final String CONTRACT_ADDRESS_BRAVEKONGZ_KLAYTN = "0x291736769c10c6bbc4d96584285247220d1389b1";
	
	
	
	public static final String FUNCTION_NAME_APPROVE_KLAYTN = "approve";
	public static final String FUNCTION_NAME_TOKEN_MINT = "mint"; // 토큰발행
	public static final String FUNCTION_NAME_TOKEN_BURN = "burn"; // 토큰소각
	public static final String FUNCTION_NAME_TOKEN_BURN_FROM = "burnFrom"; // 토큰소각 다른지갑
	
	public static final String FUNCTION_NAME_CREATE_KLAY_POOL_KLAYTN = "createKlayPool"; // klay + x 쌍
	public static final String FUNCTION_NAME_CREATE_KCT_POOL_KLAYTN = "createKctPool"; // kct + kct 쌍
	public static final String FUNCTION_NAME_AIRDROP_CREATE_DISTRIBUTION_KLAYTN = "createDistribution"; // 에어드랍 시작
	public static final String FUNCTION_NAME_AIRDROP_DEPOSIT_KLAYTN = "deposit"; // 에어드랍 토큰 충전
	public static final String FUNCTION_NAME_AIRDROP_REFIX_BLOCK_AMOUNT_KLAYTN = "refixBlockAmount"; // 에어드랍 블록당 드랍 갯수 수정
	public static final String CONTRACT_ADDRESS_KLAY = "0x0000000000000000000000000000000000000000";
	
	// contract submit 관련 정보
	public static final String COMPILER_VERSION_KLAYTN = "Solidity v0.5.6+commit.b259423e";
	public static final String OPEN_SOURCE_LICENSE_TYPE_KLAYTN = "None";
	public static final String OPTIMIZATION_KLAYTN = "false";
	public static final String OPTIMIZATION_RUNS_KLAYTN = "200";
	// public static final String EVM_VERSION_KLAYTN = "byzantium";
	public static final String EVM_VERSION_KLAYTN = "petersburg";
	
	
	// 소스 코드
//	public static HashMap<String, String> ABI_JSON_MAP = new HashMap<String, String>();
//	public static HashMap<String, String> BYTE_CODE_MAP = new HashMap<String, String>();
	
//	public static String ABI_JSON_CREATE_TOKEN = "";
//	public static String BYTE_CODE_CREATE_TOKEN = "";
	
	// 시스템 변수로 사용!!!
	public static String KLAYSTAR_WALLET_ADDRESS_KLAYTN = "";
	public static int KLAYSTAR_SEND_OPTIONS_GAS = 15000000;
	
//	/**
//	 * Set Complied Contract. (abi, bin)
//	 * @param String contractName
//	 * 
//	 * @return Contract
//	 */
//	public static void setContract(String contractName, String klaytnContractAbi, String klaytnContractBin) throws Exception {
////		File solFile = new File(CONTRACT_SOL_FILE_PATH_KLAYTN);
////		File solFile = new File(contractSolFilePath);
//		// System.out.println(solFile.getName());
//		// System.out.println(solFile.getAbsolutePath());
//		// System.out.println(solFile.canRead());
//		
//		ABI_JSON_MAP.put(contractName, klaytnContractAbi);
//		BYTE_CODE_MAP.put(contractName, klaytnContractBin);
//		
//		////////////////////////////////////
//		// 컨트랙트 확인용
//		////////////////////////////////////
//		checkContractInstance(klaytnContractAbi);
//		////////////////////////////////////
//	}
	
//	/**
//	 * Set Complied Contract. (abi, bin)
//	 * @param String contractName
//	 * 
//	 * @return Contract
//	 */
//	public static void setContract(String contractName, String contractSolFilePath) throws Exception {
////		File solFile = new File(CONTRACT_SOL_FILE_PATH_KLAYTN);
//		File solFile = new File(contractSolFilePath);
//		// System.out.println(solFile.getName());
//		// System.out.println(solFile.getAbsolutePath());
//		// System.out.println(solFile.canRead());
//		
//		SolidityCompiler.Result res = SolidityCompiler.compile(solFile, false, true, Options.ABI, Options.BIN, Options.INTERFACE, Options.METADATA);
//		
//		logger.debug(res.getOutput());
//		
//		if (!res.isFailed()) {
//			CompilationResult result = CompilationResult.parse(res.getOutput());
//			CompilationResult.ContractMetadata meta = result.getContract(contractName);
//			// abi
//			String abi = meta.abi;
//			System.out.println("abi: " + abi);
//			// bin
//			String bin = meta.bin;
//			System.out.println("bin: " + bin);
//			
//			ABI_JSON_MAP.put(contractName, abi);
//			BYTE_CODE_MAP.put(contractName, bin);
//			
//			////////////////////////////////////
//			// 컨트랙트 확인용
//			////////////////////////////////////
//			checkContractInstance(abi);
//			////////////////////////////////////
//		} else {
//			System.out.println(res.getErrors());
//		}
//	}
	
	/**
	 * Check Contract Info.
	 * @param String abiJson
	 */
	public static void checkContractInstance(String abiJson) {
		Caver caver = new Caver(Constant.KLAYTN_MAINNET_URL);
		try {
			Contract contract = caver.contract.create(abiJson);
			contract.getMethods().forEach((name, method) ->{
				System.out.println(method.getType() + " " +  caver.abi.buildFunctionString(method));
			});
			System.out.println("ContractAddress : " + contract.getContractAddress());
		} catch (IOException e) {
			// 예외 처리
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * Get Contract.
	 * @param String abiJson
	 * @param String byteCode
	 * 
	 * @return Contract
	 */
	public static Contract getContractKlaytn(String contractName, String abiJson, String byteCode) {
		Caver caver = new Caver(Constant.KLAYTN_MAINNET_URL);
		try {
			SingleKeyring deployer = KeyringFactory.createFromKlaytnWalletKey(Constant.KLAYTN_WALLET_KEY_CREATE_TOKEN);
			// 시스템 지갑주소 설정
			KLAYSTAR_WALLET_ADDRESS_KLAYTN = deployer.getAddress();
			caver.wallet.add(deployer);
			
//			Contract contract = caver.contract.create(abiJson);
			
//			SendOptions sendOptions = new SendOptions();
//			sendOptions.setFrom(deployer.getAddress());
//			sendOptions.setGas(BigInteger.valueOf(4000000));
//			
//			ContractDeployParams deployParam = new ContractDeployParams(byteCode, deployParams);
//
//			Contract newContract = contract.deploy(deployParam, sendOptions);
//			// TransactionReceipt.TransactionReceiptData receipt = contract.send(sendOptions, "set", "test", "testValue");
//			
//			System.out.println("Contract address : " + newContract.getContractAddress());
			return caver.contract.create(abiJson);
		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	/**
//	 * Deploy Contract.
//	 * @param Object deployParams
//	 * @param String abiJson
//	 * @param String byteCode
//	 * 
//	 * @return Contract
//	 */
//	public static Contract deployContractCreateToken(Object[] deployParams, String abiJson, String byteCode) {
//		Caver caver = new Caver(KLAYTN_MAINNET_URL);
//		// SingleKeyring deployer = caver.wallet.keyring.createFromPrivateKey("0x{private key}");
//		// caver.wallet.add(deployer);
//		
//		SingleKeyring deployer = KeyringFactory.createFromKlaytnWalletKey(KLAYTN_WALLET_KEY);
//		caver.wallet.add(deployer);
//		
//		try {
//			Contract contract = caver.contract.create(abiJson);
//			
//			SendOptions sendOptions = new SendOptions();
//			sendOptions.setFrom(deployer.getAddress());
//			sendOptions.setGas(BigInteger.valueOf(4000000));
//			
//			ContractDeployParams deployParam = new ContractDeployParams(byteCode, deployParams);
//
//			Contract newContract = contract.deploy(deployParam, sendOptions);
//			// TransactionReceipt.TransactionReceiptData receipt = contract.send(sendOptions, "set", "test", "testValue");
//			
//			System.out.println("Contract address : " + newContract.getContractAddress());
//			return newContract;
//		} catch (IOException | TransactionException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
//			// 예외 처리
//			e.printStackTrace();
//			return null;
//		}
//	}
}
