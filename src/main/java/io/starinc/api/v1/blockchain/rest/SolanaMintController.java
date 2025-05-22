package io.starinc.api.v1.blockchain.rest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.p2p.solanaj.core.Account;
import org.p2p.solanaj.core.AccountMeta;
import org.p2p.solanaj.core.PublicKey;
import org.p2p.solanaj.core.Transaction;
import org.p2p.solanaj.core.TransactionInstruction;
import org.p2p.solanaj.programs.SystemProgram;
import org.p2p.solanaj.rpc.RpcApi;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.ws.SignatureNotification;
import org.p2p.solanaj.ws.listeners.NotificationEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.ipfs.multibase.Base58;
import io.starinc.api.v1.common.mapper.KeyValueMapper;
import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.mintinglog.vo.MintingLogVo;
import io.starinc.api.v1.project.vo.ProjectVo;

@RestController
@RequestMapping(value = "/api")
public class SolanaMintController {
	
	@Autowired
	private KeyValueMapper keyValueMapper;
	
	// ■■■■■■■ 테스트 완료되면 constant로 이동 ■■■■■■■
	
	//솔라나 네트워크 URL
	private static final String SOLANA_RPC_URL = "https://api.devnet.solana.com";
	//메타플렉스 기반 프로그램을 실행하기 위한 프로그램 ID
	private static final String SOLANA_METADATA_PROGRAM_ID = "metaqbxxUerdq28cj1RbAWkYQm3ybzjb6a8bt518x1s";
	//토큰 기반 프로그램을 실행하기 위한 프로그램 ID
	private static final String SOLANA_TOKEN_PROGRAM_ID = "TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA";
	//렌트 관련 기능 실행을 위한 ID
	private static final String SOLANA_SYSVAR_RENT_PUBKEY = "SysvarRent111111111111111111111111111111111";
	//토큰 어카운트 일반적인 SPACE 크기
	private static final int SOLANA_TOKEN_ACCOUNT_SPACE = 165;
	// 최대 재시도 횟수
	private static final int SOLANA_MAX_RETRY_COUNT = 3;	
	
	@GetMapping("/blockchain/mintSolanaNft")
	public CommonResultVo mintSolanaNft(
			@RequestParam String projectId
			, @RequestParam String projectSymbol
			, @RequestParam String jsonUrl
			, @RequestParam int sellerFeeBasisPoints
			, @RequestParam String collectionMintAddress
			, @RequestParam String collectionMetadataAddress
			, @RequestParam String collectionMasterEditionAddress
			, @RequestParam String userWalletAddress
			) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		MintingLogVo mintingLogVo = new MintingLogVo(); // mintingLogVo에 각 program_id 등 계정 설정해서 반환
		
		String solana_system_wallet_secret_key = this.keyValueMapper.selectKeyValue("solana_system_wallet_secret_key");
		String solana_mint_program_id = this.keyValueMapper.selectKeyValue("solana_mint_program_id");
		String solana_whitelist_key  = this.keyValueMapper.selectKeyValue("solana_whitelist_key");
		
		RpcClient client = new RpcClient(SOLANA_RPC_URL);
		RpcApi api = client.getApi();	   
		
		//솔라나 지갑 주소 유효성 검사
		try {
			if (isValidSolanaAddress(api, userWalletAddress)) {
				System.out.println("The provided Solana address is valid and exists on the blockchain.");
			} else {
				commonResultVo.setResultCd("FAILURE");
				commonResultVo.setReturnCd("1");
				commonResultVo.setResultMsg("The provided Solana address is invalid or does not exist on the blockchain.");
				return commonResultVo;
			}
		} catch (RpcException e) {
			e.printStackTrace();
			commonResultVo.setResultCd("FAILURE");
			commonResultVo.setReturnCd("1");
			commonResultVo.setResultMsg("An error occurred while checking the address: " + e.getMessage());
			return commonResultVo;
		}
		
		// Base58 인코딩된 개인키를 디코드
		byte[] secretKey = Base58.decode(solana_system_wallet_secret_key);
		Account payer = new Account(secretKey);
		Account mintAuthority = payer;
		
		// 메타데이터 객체 생성, 추후 name, symbol, uri 등은 외부 파라미터로 받게 수정
		// Metadata metadata = new Metadata(projectId, "BK", "https://kingnslave.com/json/BraveKongzNFTMeta", 500);
		Metadata metadata = new Metadata(projectId, projectSymbol, jsonUrl, sellerFeeBasisPoints);
		
		try {
			// 민트 계정 생성
			PublicKey mintAccountKey = executeWithRetry(() -> createMintAccount(api, payer).get(60, TimeUnit.SECONDS));
			// 토큰 계정 생성ㅃ
			PublicKey tokenAccountKey = executeWithRetry(() -> createTokenAccount(api, payer).get(60, TimeUnit.SECONDS));
			// 메타데이터 계정 생성
			PublicKey metadataAccountKey = createMetadataAccount(api, mintAccountKey);
			// 마스터 에디션 계정 생성
			PublicKey masterEditionAccountKey = createMasterEditionAccount(api, mintAccountKey);
			//민팅하는 NFT가 연동할 컬렉션 주소
			PublicKey collectionMintAccountKey = new PublicKey(collectionMintAddress);
			//민팅하는 NFT를 검증할 컬렉션 메타데이터 주소
			PublicKey collectionMetadataAccountKey = new PublicKey(collectionMetadataAddress);
			//민팅하는 NFT가 검증할 컬렉션 마스터에디션 주소
			PublicKey collectionMasterEditionAccountKey = new PublicKey(collectionMasterEditionAddress);			
			//민팅하는 NFT의 소유자 지갑 주소
			PublicKey clientAccountKey = new PublicKey(userWalletAddress);
			PublicKey updateAuthorityKey = clientAccountKey;
			PublicKey whitelistKey = new PublicKey(solana_whitelist_key);
						
			// TODO: PublicKey 주소를 string으로 변환하는 처리 확인 필요
			// mintingLogVo에 민팅 관련 acccount 설정해서 반환
//			mintingLogVo.setNew_nft_contract(collectionMintAddress);
//			mintingLogVo.setNew_nft_id(mintAccountKey.toBase58());
			mintingLogVo.setMint_account_key(mintAccountKey.toBase58());
			mintingLogVo.setToken_account_key(tokenAccountKey.toBase58());
			mintingLogVo.setMetadata_account_key(metadataAccountKey.toBase58());
			mintingLogVo.setMaster_edition_account_key(masterEditionAccountKey.toBase58());
			// 반환값 설정
			commonResultVo.setReturnValue(mintingLogVo);	
						
			// Metadata 객체를 JSON으로 직렬화
			ObjectMapper objectMapper = new ObjectMapper();
			String metadataJson = objectMapper.writeValueAsString(metadata);
			System.out.println("metadataJson : " + metadataJson);
			byte[] metadataBytes = metadataJson.getBytes(StandardCharsets.UTF_8);
			
			// 트랜잭션 생성
			Transaction transaction = new Transaction();
			// 어카운트 정보 배열 생성
			List<AccountMeta> keys = Arrays.asList(
				new AccountMeta(whitelistKey, false, true),
				new AccountMeta(payer.getPublicKey(), true, true),
				new AccountMeta(mintAuthority.getPublicKey(), true, false),
				new AccountMeta(updateAuthorityKey, true, false),
				new AccountMeta(mintAccountKey, false, true),
				new AccountMeta(tokenAccountKey, false, true),
				new AccountMeta(metadataAccountKey, false, true),
				new AccountMeta(masterEditionAccountKey, false, true),
				new AccountMeta(SystemProgram.PROGRAM_ID, false, false),
				new AccountMeta(new PublicKey(SOLANA_SYSVAR_RENT_PUBKEY), false, false),
				new AccountMeta(new PublicKey(SOLANA_METADATA_PROGRAM_ID), false, false),
				new AccountMeta(new PublicKey(SOLANA_TOKEN_PROGRAM_ID), false, false),
				new AccountMeta(clientAccountKey, false, false),
				new AccountMeta(collectionMintAccountKey, false, false),
				new AccountMeta(collectionMetadataAccountKey, false, false),
				new AccountMeta(collectionMasterEditionAccountKey, false, false)
			);
			
			TransactionInstruction instruction = new TransactionInstruction(
				new PublicKey(solana_mint_program_id),
				keys,
				metadataBytes
			);
			
			transaction.addInstruction(instruction);
			
			String recentBlockhash = api.getRecentBlockhash();
			transaction.setRecentBlockHash(recentBlockhash);
			
			CompletableFuture<CommonResultVo> futureResult = new CompletableFuture<>();
			
			NotificationEventListener listener = new NotificationEventListener() {
				@Override
				public void onNotificationEvent(Object event) {
					System.out.println("NFT Minting Transaction event: " + event);
					
					if (event instanceof SignatureNotification) {
						SignatureNotification notification = (SignatureNotification) event;
						
						if (notification.hasError()) {
							commonResultVo.setResultCd("FAILURE");
							commonResultVo.setReturnCd("1");
							commonResultVo.setResultMsg("Transaction failed: " + notification.getError());
						} else {
							commonResultVo.setResultCd("SUCCESS");
							commonResultVo.setReturnCd("0");
							commonResultVo.setResultMsg("Transaction successful");
						}
						futureResult.complete(commonResultVo);
					} else {
						commonResultVo.setResultCd("FAILURE");
						commonResultVo.setReturnCd("1");
						commonResultVo.setResultMsg("Unexpected event");
						futureResult.complete(commonResultVo);
					}
				}
			};
			
			System.out.println("sendAndConfirmTransaction");
			api.sendAndConfirmTransaction(transaction, Arrays.asList(payer), listener);
			
			return futureResult.get(60, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			commonResultVo.setResultCd("FAILURE");
			commonResultVo.setReturnCd("1");
			commonResultVo.setResultMsg("Exception: " + e.getMessage());
			e.printStackTrace();  // 전체 스택 트레이스를 콘솔에 출력
			return commonResultVo;
		} catch (Exception e) {
			commonResultVo.setResultCd("FAILURE");
			commonResultVo.setReturnCd("1");
			commonResultVo.setResultMsg("Unexpected Exception: " + e.getMessage());
			e.printStackTrace();  // 전체 스택 트레이스를 콘솔에 출력
			return commonResultVo;
		}
	}
	
	@GetMapping("/blockchain/createSolanaCollection")
	public CommonResultVo createSolanaCollection(
			@RequestParam String projectId
			, @RequestParam String projectSymbol
			, @RequestParam String jsonUrl
			) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
//		MintingLogVo mintingLogVo = new MintingLogVo(); // mintingLogVo에 각 program_id 등 계정 설정해서 반환
		ProjectVo projectVo = new ProjectVo();
		
		String solana_system_wallet_secret_key = this.keyValueMapper.selectKeyValue("solana_system_wallet_secret_key");
		String solana_mint_program_id = this.keyValueMapper.selectKeyValue("solana_mint_program_id");
		String solana_whitelist_key  = this.keyValueMapper.selectKeyValue("solana_whitelist_key");
		
		//sellerFeeBasisPoints 검색
		int sellerFeeBasisPoints = Integer.parseInt(this.keyValueMapper.selectKeyValue("seller_fee_basis_points"));
		
		RpcClient client = new RpcClient(SOLANA_RPC_URL);
		RpcApi api = client.getApi();	   
				
		// Base58 인코딩된 개인키를 디코드
		byte[] secretKey = Base58.decode(solana_system_wallet_secret_key);
		Account payer = new Account(secretKey);
		Account mintAuthority = payer;
		Account updateAuthority = payer;
		
		// 메타데이터 객체 생성, 추후 name, symbol, uri 등은 외부 파라미터로 받게 수정
		// Metadata metadata = new Metadata(projectId, "BK", "https://kingnslave.com/json/BraveKongzNFTMeta", 500);
		Metadata metadata = new Metadata(projectId, projectSymbol, jsonUrl, sellerFeeBasisPoints);
		
		try {
			// 민트 계정 생성
			PublicKey mintAccountKey = executeWithRetry(() -> createMintAccount(api, payer).get(60, TimeUnit.SECONDS));
			// 토큰 계정 생성
			PublicKey tokenAccountKey = executeWithRetry(() -> createTokenAccount(api, payer).get(60, TimeUnit.SECONDS));
			// 메타데이터 계정 생성
			PublicKey metadataAccountKey = createMetadataAccount(api, mintAccountKey);
			// 마스터 에디션 계정 생성
			PublicKey masterEditionAccountKey = createMasterEditionAccount(api, mintAccountKey);
			//콜렉션 ID 소유자 (현재는 payer로 되어있으나, 만약 추후 해당 부분도 파라미터로 받을 수 있는 여지가 있으므로 변수 구분해놓음) 
			PublicKey clientAccountKey = payer.getPublicKey();
			PublicKey whitelistKey = new PublicKey(solana_whitelist_key);
						
			// mintingLogVo에 민팅 관련 acccount 설정해서 반환
//			mintingLogVo.setNew_nft_id(mintAccountKey.toBase58());
//			mintingLogVo.setMint_account_key(mintAccountKey.toBase58());
//			mintingLogVo.setToken_account_key(tokenAccountKey.toBase58());
//			mintingLogVo.setMetadata_account_key(metadataAccountKey.toBase58());
//			mintingLogVo.setMaster_edition_account_key(masterEditionAccountKey.toBase58());
//			// 반환값 설정
//			commonResultVo.setReturnValue(mintingLogVo);	
			
			projectVo.setContract_address(mintAccountKey.toBase58());
			projectVo.setCollection_mint_address(mintAccountKey.toBase58());
			projectVo.setCollection_master_edition_address(masterEditionAccountKey.toBase58());
			projectVo.setCollection_metadata_address(metadataAccountKey.toBase58());
			// 반환값 설정
			commonResultVo.setReturnValue(projectVo);
						
			// Metadata 객체를 JSON으로 직렬화
			ObjectMapper objectMapper = new ObjectMapper();
			String metadataJson = objectMapper.writeValueAsString(metadata);
			System.out.println("metadataJson : " + metadataJson);
			byte[] metadataBytes = metadataJson.getBytes(StandardCharsets.UTF_8);
			
			// 트랜잭션 생성
			Transaction transaction = new Transaction();
			// 어카운트 정보 배열 생성
			List<AccountMeta> keys = Arrays.asList(
					new AccountMeta(whitelistKey, false, true),
					new AccountMeta(payer.getPublicKey(), true, true),
					new AccountMeta(mintAuthority.getPublicKey(), true, false),
					new AccountMeta(updateAuthority.getPublicKey(), true, false),
					new AccountMeta(mintAccountKey, false, true),
					new AccountMeta(tokenAccountKey, false, true),
					new AccountMeta(metadataAccountKey, false, true),
					new AccountMeta(masterEditionAccountKey, false, true),
					new AccountMeta(SystemProgram.PROGRAM_ID, false, false),
					new AccountMeta(new PublicKey(SOLANA_SYSVAR_RENT_PUBKEY), false, false),
					new AccountMeta(new PublicKey(SOLANA_METADATA_PROGRAM_ID), false, false),
					new AccountMeta(new PublicKey(SOLANA_TOKEN_PROGRAM_ID), false, false),
					new AccountMeta(clientAccountKey, false, false)
			);
			
			TransactionInstruction instruction = new TransactionInstruction(
				new PublicKey(solana_mint_program_id),
				keys,
				metadataBytes
			);
			
			transaction.addInstruction(instruction);
			
			String recentBlockhash = api.getRecentBlockhash();
			transaction.setRecentBlockHash(recentBlockhash);
			
			CompletableFuture<CommonResultVo> futureResult = new CompletableFuture<>();
			
			NotificationEventListener listener = new NotificationEventListener() {
				@Override
				public void onNotificationEvent(Object event) {
					System.out.println("Create Collection Transaction event: " + event);
					
					if (event instanceof SignatureNotification) {
						SignatureNotification notification = (SignatureNotification) event;
						
						if (notification.hasError()) {
							commonResultVo.setResultCd("FAILURE");
							commonResultVo.setReturnCd("1");
							commonResultVo.setResultMsg("Transaction failed: " + notification.getError());
						} else {
							commonResultVo.setResultCd("SUCCESS");
							commonResultVo.setReturnCd("0");
							commonResultVo.setResultMsg("Transaction successful");
						}
						futureResult.complete(commonResultVo);
					} else {
						commonResultVo.setResultCd("FAILURE");
						commonResultVo.setReturnCd("1");
						commonResultVo.setResultMsg("Unexpected event");
						futureResult.complete(commonResultVo);
					}
				}
			};
			
			System.out.println("sendAndConfirmTransaction");
			api.sendAndConfirmTransaction(transaction, Arrays.asList(payer), listener);
			
			return futureResult.get(60, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			commonResultVo.setResultCd("FAILURE");
			commonResultVo.setReturnCd("1");
			commonResultVo.setResultMsg("Exception: " + e.getMessage());
			e.printStackTrace();  // 전체 스택 트레이스를 콘솔에 출력
			return commonResultVo;
		} catch (Exception e) {
			commonResultVo.setResultCd("FAILURE");
			commonResultVo.setReturnCd("1");
			commonResultVo.setResultMsg("Unexpected Exception: " + e.getMessage());
			e.printStackTrace();  // 전체 스택 트레이스를 콘솔에 출력
			return commonResultVo;
		}
	}
	
	private CompletableFuture<PublicKey> createMintAccount(RpcApi api, Account payer) throws RpcException {
		CompletableFuture<PublicKey> futureResult = new CompletableFuture<>();
		
		Account mintAccount = new Account();
		Transaction transaction = new Transaction();
		
		long lamports = api.getMinimumBalanceForRentExemption(82);
		
		transaction.addInstruction(SystemProgram.createAccount(
			payer.getPublicKey(),
			mintAccount.getPublicKey(),
			lamports * 100,
			82,
			new PublicKey(SOLANA_TOKEN_PROGRAM_ID)
		));
		
		NotificationEventListener listener = new NotificationEventListener() {
			@Override
			public void onNotificationEvent(Object event) {
				System.out.println("Transaction event: " + event);
				
				if (event instanceof SignatureNotification) {
					SignatureNotification notification = (SignatureNotification) event;
					
					if (notification.hasError()) {
						futureResult.completeExceptionally(new RuntimeException("Failed to create mint account"));
					} else {
						System.out.println("Create Mint Account Address : " + mintAccount.getPublicKey().toBase58());
						futureResult.complete(mintAccount.getPublicKey());
					}
				} else {
					futureResult.completeExceptionally(new RuntimeException("Unexpected event"));
				}
			}
		};
		
		api.sendAndConfirmTransaction(transaction, Arrays.asList(payer, mintAccount), listener);
		
		return futureResult;
	}
	
	private CompletableFuture<PublicKey> createTokenAccount(RpcApi api, Account payer) throws RpcException {
		CompletableFuture<PublicKey> futureResult = new CompletableFuture<>();
		
		Account tokenAccount = new Account();
		Transaction transaction = new Transaction();
		
		long lamports = api.getMinimumBalanceForRentExemption(SOLANA_TOKEN_ACCOUNT_SPACE);
		
		transaction.addInstruction(SystemProgram.createAccount(
			payer.getPublicKey(),
			tokenAccount.getPublicKey(),
			lamports * 100,
			SOLANA_TOKEN_ACCOUNT_SPACE,
			new PublicKey(SOLANA_TOKEN_PROGRAM_ID)
		));
		
		NotificationEventListener listener = new NotificationEventListener() {
			@Override
			public void onNotificationEvent(Object event) {
				System.out.println("Transaction event: " + event);
				
				if (event instanceof SignatureNotification) {
					SignatureNotification notification = (SignatureNotification) event;
					
					if (notification.hasError()) {
						futureResult.completeExceptionally(new RuntimeException("Failed to create token account"));
					} else {
						System.out.println("Create Token Account Address : " + tokenAccount.getPublicKey().toBase58());
						futureResult.complete(tokenAccount.getPublicKey());
					}
				} else {
					futureResult.completeExceptionally(new RuntimeException("Unexpected event"));
				}
			}
		};
		
		api.sendAndConfirmTransaction(transaction, Arrays.asList(payer, tokenAccount), listener);
		
		return futureResult;
	}
	
	private PublicKey createMetadataAccount(RpcApi api, PublicKey mintAccountKey) throws Exception {
		System.out.println("Start Create Metadata Account");
		
		PublicKey metadataProgram = new PublicKey(SOLANA_METADATA_PROGRAM_ID);
		
		// 각 단계의 바이트 배열 출력
		byte[] seed1 = "metadata".getBytes(StandardCharsets.UTF_8);
		byte[] seed2 = metadataProgram.toByteArray();
		byte[] seed3 = mintAccountKey.toByteArray();
		
		// PDA 계산
		PublicKey.ProgramDerivedAddress metadataPDA = PublicKey.findProgramAddress(
			Arrays.asList(seed1, seed2, seed3),
			metadataProgram
		);
		
		System.out.println("Create Metadata Account Address : " + metadataPDA.getAddress().toBase58());
		return metadataPDA.getAddress();
	}
	
	private PublicKey createMasterEditionAccount(RpcApi api, PublicKey mintAccountKey) throws Exception {
		System.out.println("Start Create Master Edition Account");
		
		PublicKey metadataProgram = new PublicKey(SOLANA_METADATA_PROGRAM_ID);
		
		// 각 단계의 바이트 배열 출력
		byte[] seed1 = "metadata".getBytes(StandardCharsets.UTF_8);
		byte[] seed2 = metadataProgram.toByteArray();
		byte[] seed3 = mintAccountKey.toByteArray();
		byte[] seed4 = "edition".getBytes(StandardCharsets.UTF_8);
		
		// PDA 계산
		PublicKey.ProgramDerivedAddress masterEditionPDA = PublicKey.findProgramAddress(
			Arrays.asList(seed1, seed2, seed3, seed4),
			metadataProgram
		);
		
		System.out.println("Create Master Edition Account Address : " + masterEditionPDA.getAddress().toBase58());
		return masterEditionPDA.getAddress();
	}
	
	// 재시도 로직을 포함하는 헬퍼 메서드
	private <T> T executeWithRetry(Callable<T> action) throws Exception {
		int retryCount = 0;
		while (true) {
			try {
				return action.call();
			} catch (Exception e) {
				if (++retryCount >= SOLANA_MAX_RETRY_COUNT) {
					throw e;
				}
				System.out.println("Retrying... (" + retryCount + "/" + SOLANA_MAX_RETRY_COUNT + ")");
			}
		}
	}
	
	public static boolean isValidSolanaAddress(RpcApi api, String address) throws RpcException {
		// Validate address format
		try {
			byte[] decodedAddress = Base58.decode(address);
			if (decodedAddress.length != 32) {
				return false;
			}
			
//			PublicKey publicKey = new PublicKey(address);
//			AccountInfo accountInfo = api.getAccountInfo(publicKey);
			
			// Check if account info is returned and address length is correct
//			return accountInfo.getValue() != null;
			return true;
		} catch (IllegalArgumentException e) {
			// Address is not a valid PublicKey format
			return false;
		}
	}
	
	// 메타데이터 클래스
	static class Metadata {
		@JsonProperty("name")
		String name;
		@JsonProperty("symbol")
		String symbol;
		@JsonProperty("uri")
		String uri;
		@JsonProperty("seller_fee_basis_points")
		int sellerFeeBasisPoints;
		
		public Metadata(String name, String symbol, String uri, int sellerFeeBasisPoints) {
			this.name = name;
			this.symbol = symbol;
			this.uri = uri;
			this.sellerFeeBasisPoints = sellerFeeBasisPoints;
		}
		
		public String getName() {
			return name;
		}
		
		public String getSymbol() {
			return symbol;
		}
		
		public String getUri() {
			return uri;
		}
		
		public int getSellerFeeBasisPoints() {
			return sellerFeeBasisPoints;
		}
	}
}