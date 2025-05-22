package io.starinc.api.v1.common;

public class Constant {
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	// ■ 배포시 반드시 수정해야 되는 값
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	// 운영용
//	// address
//	// 0xb4285d543F192859cdB9f825686F3a2A8f8AA8BC
	// public static final String KLAYTN_MAINNET_URL = "https://public-node-api.klaytnapi.com/v1/cypress";
	public static final String KLAYTN_MAINNET_URL = "https://public-en-cypress.klaytn.net";
	public static final String SERVER_DOMAIN = "http://43.203.212.152:8080";
	public static final String PAYMENT_FREE = "N"; // Y:무료, N:유료
	public static final String KLAYTN_CHAIN_ID = "1001"; // TESTNET
	// public static final String KLAYTN_CHAIN_ID = "8217"; // MAINNET
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
//	// 로컬용
//	// TODO: 개발완료시 주석 삭제 필요!
//	// address
//	// 0xb4285d543f192859cdb9f825686f3a2a8f8aa8bc
//	// Private Key
	public static final String SYSTEM_WALLET_PRIVATE_KEY_SOLANA = "4NeUceuuvaJtZaAp3m8uZo95CiADNbXrLp89e739U35WfrqjLCbp3jhggDk4EQhzdq2tAEopBnVy3XxbAw34SgCa";
//	// 0xa5defed86796a0185a2a97c157c54dad8e2e80152c40d304b547cc0a776faad4
//	public static final String KLAYTN_MAINNET_URL = "https://api.baobab.klaytn.net:8651"; // TODO: 배포시 실제 메인넷 주소로 변경 필요
//	public static final String SERVER_DOMAIN = "http://172.30.1.52:8888"; // TODO: 결제 로컬테스트시 사용
//	public static final String SERVER_DOMAIN = "http://121.162.58.101:8081";
	// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	// NFT Migration용 지갑 주소 : 0x74116f9786E11D02BDd08b852C2A68f4990EF359
	public static final String KLAYTN_WALLET_KEY_CREATE_TOKEN = "0xbf792d906c4b9f220c1e81b6c2a5b69aac1dbd307722bb7373bbfc06d5186da70x000x74116f9786e11d02bdd08b852c2a68f4990ef359";
	// public static final String KLAYTN_WALLET_KEY_CREATE_POOL = "aaaaaa";
	
	// public static final String NFT_ADMIN_ADDRESS = "0xb4285d543F192859cdB9f825686F3a2A8f8AA8BC";
	
//	public static String KLAYTN_WALLET_KEY_CREATE_TOKEN = "aaaaaa";
//	public static String KLAYTN_WALLET_KEY_CREATE_POOL = "aaaaaa";
	
	// 테스트 만수르 계정
	// public static String KLAYTN_WALLET_KEY = "aaaaaa";
	
	
	
	// 시스템 지갑 주소
	public static final String SYSTEM_WALLET_ADDRESS_SOLANA = "5Du5PuShAibKF4CtKGovjzEaxYfbDNNSpP8oKUQyjzQA";
	public static final String SYSTEM_WALLET_ADDRESS_KLAYTN = "0xb4285d543F192859cdB9f825686F3a2A8f8AA8BC";
	
	
	// NFT 타입
	public static final String NFT_TYPE_A = "a";
	public static final String NFT_TYPE_S = "s";
	public static final String NFT_TYPE_R = "r";
	public static final String NFT_TYPE_V = "v";
	public static final String NFT_TYPE_ME100 = "me100";
	public static final String NFT_TYPE_ME1500 = "me1500";
	public static final String NFT_TYPE_ME10000 = "me10000";
	
	
	// NFT 결제 상태 코드
	public static final String PAYMENT_STATUS_CANCEL = "0";
	public static final String PAYMENT_STATUS_PAYMENT_COMPLETED = "10";
	// public static final String PAYMENT_STATUS_MINT_PROCESSING = "30";
	public static final String PAYMENT_STATUS_MINT_FAILED = "40";
	public static final String PAYMENT_STATUS_MINT_COMPLETED = "50";
	
	
	// NFT 처리 상태 코드
	public static final String NFT_STATUS_MINT_WAITING = "0";
	public static final String NFT_STATUS_MINT_FAILED = "5";
	public static final String NFT_STATUS_MINT_PROCESSING = "10";
	public static final String NFT_STATUS_MINT_COMPLETED = "50";
	
	
	// key_value 테이블 key 목록
	public static final String KEY_VALUE_GET_NFT_ID_RETRY_COUNT_LIMIT = "get_nft_id_retry_count_limit";
	public static final String KEY_VALUE_LAST_PRICE_WTEC = "last_price_wtec";
	public static final String KEY_VALUE_LAST_PRICE_WTEC_INTERVAL_SECOND = "last_price_wtec_interval_second";
	public static final String KEY_VALUE_BATCH_TIME_HOUR_MINT = "batch_time_hour_mine";
	
	
	// 타입별 NFT ID 범위
	public static final String NFT_ID_START_TYPE_A = "3001";
	public static final String NFT_ID_END_TYPE_A = "4000";
	public static final String NFT_ID_START_TYPE_S = "2001";
	public static final String NFT_ID_END_TYPE_S = "3000";
	public static final String NFT_ID_START_TYPE_R = "1001";
	public static final String NFT_ID_END_TYPE_R = "2000";
	public static final String NFT_ID_START_TYPE_V = "1";
	public static final String NFT_ID_END_TYPE_V = "1000";
	public static final String NFT_ID_START_TYPE_ME100 = "1";
	public static final String NFT_ID_END_TYPE_ME100 = "1000000";
	public static final String NFT_ID_START_TYPE_ME1500 = "1";
	public static final String NFT_ID_END_TYPE_ME1500 = "1000000";
	public static final String NFT_ID_START_TYPE_ME10000 = "1";
	public static final String NFT_ID_END_TYPE_ME10000 = "1000000";
	
	// Token Symbol
	public static final String TOKEN_SYMBOL_BTC = "BTC";
	public static final String TOKEN_SYMBOL_WTEC = "WTEC";
	public static final String LBANK_TOKEN_SYMBOL_PAIR_WTEC_USDT = "wtec_usdt";
	
	
	// 시스템 상수
	public static final String SYSTEM_REG_ID = "SYSTEM";
	public static final String SYSTEM_MOD_ID = "SYSTEM";
	public static final String SYSTEM_REG_ID_BATCH = "BATCH";
	
	
	public static final String BATCH_STATUS_CD_SUCCESS = "SUCCESS";
	public static final String BATCH_STATUS_CD_ERROR_MARKET_PRICE = "ERROR_MARKET_PRICE";
	public static final String BATCH_STATUS_CD_ERROR_MINE_LOG = "ERROR_MINE_LOG";
	
	
	public static final String KLAYTN_KAS_DOMAIN = "https://th-api.klaytnapi.com";
	public static final String KLAYTN_KAS_AUTHORIZATION_BASIC = "Basic S0FTS0RZNkxaVEM4RDI0WTFCUk1KTE0zOk1QYU56eDNvak1zRUpUX2lXRHdxZ0RCMWxaekJoT3I1YnhLbnYwREo=";
	
	
	
	
	// 카카오 redirect 로컬 테스트용
	public static final String SERVER_DOMAIN_TEST_FRONT = "http://121.162.58.101:8081";
	public static final String SERVER_DOMAIN_TEST_BACK = "http://121.162.58.101:8888";
	
	// public static final String SERVER_DOMAIN = "http://1.232.77.251"; // ================ TEST
	// public static final String FILE_SERVER_IP = "beastar.io";
	// public static final String FILE_SERVER_IP = "1.224.108.250";
	// public static final String SERVER_APP_NAME = "beastar";
	
	// 카카오톡 연동
	public static final String KAKAO_REST_API_KEY = "8204f132470b51916767cceb57475198";
	public static final String KAKAO_TOKEN_REQUEST_URI = "https://kauth.kakao.com/oauth/token";
	public static final String KAKAO_INFO_URI = "https://kapi.kakao.com/v2/user/me";
	public static final String KAKAO_REDIRECT_URI = Constant.SERVER_DOMAIN + "/api/login/kakaoLoginCallbackApp";
	
	// 로그인 로그
	public static final String LOGIN_LOG_CD_JOIN = "JOIN";
	public static final String LOGIN_LOG_CD_JOIN_KAKAO = "JOIN_KAKAO";
//	public static final String LOGIN_LOG_CD_AUTO_LOGIN = "LOGIN_AUTO";
	public static final String LOGIN_LOG_CD_LOGIN = "LOGIN";
	public static final String LOGIN_LOG_CD_LOGIN_KAKAO = "LOGIN_KAKAO";
	public static final String LOGIN_LOG_CD_LOGOUT = "LOGOUT";
	public static final String LOGIN_LOG_CD_WRONG_PASSWORD = "WRONG_PWD";
	
	// 트랜잭션 타입 코드
	public static final String TRANSACTION_TYPE_CD_TOKEN_CREATE = "TOKEN_CREATE";
	public static final String TRANSACTION_TYPE_CD_TOKEN_ADD_SUPPLY = "TOKEN_ADD_SUPPLY";
	public static final String TRANSACTION_TYPE_CD_TOKEN_MINT = "TOKEN_MINT";
	public static final String TRANSACTION_TYPE_CD_TOKEN_BURN = "TOKEN_BURN";
	public static final String TRANSACTION_TYPE_CD_POOL_CREATE = "POOL_CREATE";
	public static final String TRANSACTION_TYPE_CD_AIRDROP_CREATE = "AIRDROP_CREATE";
	public static final String TRANSACTION_TYPE_CD_AIRDROP_CREATE_DISTRIBUTION = "AIRDROP_CREATE_DISTRIBUTION";
	public static final String TRANSACTION_TYPE_CD_AIRDROP_DEPOSIT = "AIRDROP_DEPOSIT";
	public static final String TRANSACTION_TYPE_CD_AIRDROP_REFIX_BLOCK_AMOUNT = "AIRDROP_REFIX_BLOCK_AMOUNT";
	
	// 토큰 생성 상태 코드
	public static final String TOKEN_CREATE_STATUS_CD_REGISTERED = "10"; // 신청 등록 - 결제 대기
	public static final String TOKEN_CREATE_STATUS_CD_PAID = "11"; // 생성 대기 - 결제 완료
	public static final String TOKEN_CREATE_STATUS_CD_CREATED = "20"; // 생성 완료
	// 토큰 계약 상태 코드 - 계약검증
	public static final String TOKEN_CONTRACT_STATUS_CD_PAID = "30"; // 결제완료
	public static final String TOKEN_CONTRACT_STATUS_CD_USER_REQUESTED = "32"; // 사용자 요청완료
	public static final String TOKEN_CONTRACT_STATUS_CD_ACCEPTED = "34"; // 접수완료
	public static final String TOKEN_CONTRACT_STATUS_CD_SUBMITTED = "36"; // 제출완료
	public static final String TOKEN_CONTRACT_STATUS_CD_SUCCESS = "40"; // 계약 검증 성공
	public static final String TOKEN_CONTRACT_STATUS_CD_REJECTED = "90"; // 사용자 요청 반려
	public static final String TOKEN_CONTRACT_STATUS_CD_FAILED = "99"; // 계약 검증 실패
	
	public static final String TOKEN_CONTRACT_STATUS_NAME_PAID = "PAID : 결제완료"; // 30
	public static final String TOKEN_CONTRACT_STATUS_NAME_USER_REQUESTED = "USER_REQUESTED : 사용자 요청완료"; // 32
	public static final String TOKEN_CONTRACT_STATUS_NAME_ACCEPTED = "ACCEPTED : 접수완료"; // 34
	public static final String TOKEN_CONTRACT_STATUS_NAME_SUBMITTED = "SUBMITTED : 제출완료"; // 36
	public static final String TOKEN_CONTRACT_STATUS_NAME_SUCCESS = "SUCCESS : 계약검증 성공"; // 40
	public static final String TOKEN_CONTRACT_STATUS_NAME_REJECTED = "REJECTED : 사용자 요청 반려"; // 90
	public static final String TOKEN_CONTRACT_STATUS_NAME_FAILED = "FAILED : 계약검증 실패"; // 99
	
	
	public static final String RECRUIT_STATUS_CD_REGISTERED = "10"; // 채용 등록 완료
	public static final String RECRUIT_STATUS_CD_PAID = "30"; // 채용 결제 완료
	public static final String RECRUIT_STATUS_CD_CLOSED = "40"; // 채용 마감 완료
	
	public static final String RECRUIT_APPLY_STATUS_CD_APPLIED = "10"; // 화이트리스트 신청 등록 완료
	public static final String RECRUIT_APPLY_STATUS_CD_PAID = "30"; // 화이트리스트 신청 결제 완료
	public static final String RECRUIT_APPLY_STATUS_CD_REJECTED = "99"; // 화이트리스트 신청 탈락
	
	
	public static final String WHITELIST_STATUS_CD_REGISTERED = "10"; // 화이트리스트 등록 완료
	public static final String WHITELIST_STATUS_CD_PAID = "30"; // 화이트리스트 결제 완료
	public static final String WHITELIST_STATUS_CD_CLOSED = "40"; // 화이트리스트 마감 완료
	
	public static final String WHITELIST_APPLY_STATUS_CD_APPLIED = "10"; // 화이트리스트 신청 등록 완료
	public static final String WHITELIST_APPLY_STATUS_CD_PAID = "30"; // 화이트리스트 신청 결제 완료
	public static final String WHITELIST_APPLY_STATUS_CD_REJECTED = "99"; // 화이트리스트 신청 탈락
	
	
	public static final String MINTING_DEFAULT_BASE_URI = "https://beastar.io/nft/"; // NFT 민팅 디폴트 기본 URL
	
	public static final String MINTING_STATUS_CD_REGISTERED = "10"; // NFT 민팅 등록 완료
	public static final String MINTING_STATUS_CD_PAID = "30"; // NFT 민팅 결제 완료
	public static final String MINTING_STATUS_CD_CLOSED = "40"; // NFT 민팅 마감 완료
	
	
	public static final String PRESALE_STATUS_CD_REGISTERED = "10"; // 사전판매 등록 완료
	public static final String PRESALE_STATUS_CD_PAID = "30"; // 사전판매 결제 완료
	public static final String PRESALE_STATUS_CD_CLOSED = "40"; // 사전판매 마감 완료
	
	
	public static final String POOL_STATUS_CD_REGISTERED = "10"; // 유동성 풀 등록
	public static final String POOL_STATUS_CD_PAID = "30"; // 유동성 풀 결제 완료
	public static final String POOL_STATUS_CD_USER_REQUESTED = "32"; // 사용자 요청완료
	public static final String POOL_STATUS_CD_ACCEPTED = "34"; // 접수완료
	// public static final String POOL_STATUS_CD_SUBMITTED = "36"; // 제출완료
	public static final String POOL_STATUS_CD_SUCCESS = "40"; // 유동성 풀 생성 완료
	public static final String POOL_STATUS_CD_REJECTED = "90"; // 사용자 요청 반려
	public static final String POOL_STATUS_CD_FAILED = "99"; // 유동성 풀 생성 실패
	
	
	public static final String POOL_STATUS_NAME_PAID = "PAID : 결제완료"; // 30
	public static final String POOL_STATUS_NAME_USER_REQUESTED = "USER_REQUESTED : 사용자 요청완료"; // 32
	public static final String POOL_STATUS_NAME_ACCEPTED = "ACCEPTED : 접수완료"; // 34
//	public static final String POOL_STATUS_NAME_SUBMITTED = "SUBMITTED : 제출완료"; // 36
	public static final String POOL_STATUS_NAME_SUCCESS = "SUCCESS : 풀 생성 완료"; // 40
	public static final String POOL_STATUS_NAME_REJECTED = "REJECTED : 사용자 요청 반려"; // 90
	public static final String POOL_STATUS_NAME_FAILED = "FAILED : 풀 생성 실패"; // 99
	
	
	public static final String AIRDROP_STATUS_CD_REGISTERED = "10"; // 에어드랍 등록 완료
	public static final String AIRDROP_STATUS_CD_PAID = "30"; // 에어드랍 결제 완료
	public static final String AIRDROP_STATUS_CD_USER_REQUESTED = "32"; // 사용자 요청완료
	public static final String AIRDROP_STATUS_CD_ACCEPTED = "34"; // 접수완료
	public static final String AIRDROP_STATUS_CD_CONTRACT_CREATED = "35"; // 계약 생성 완료
	public static final String AIRDROP_STATUS_CD_SUBMITTED = "36"; // 제출완료
	public static final String AIRDROP_STATUS_CD_SUCCESS = "40"; // 에어드랍 생성 완료
	public static final String AIRDROP_STATUS_CD_REJECTED = "90"; // 사용자 요청 반려
	public static final String AIRDROP_STATUS_CD_FAILED = "99"; // 에어드랍 생성 실패
	
	public static final String AIRDROP_STATUS_NAME_PAID = "PAID : 결제완료"; // 30
	public static final String AIRDROP_STATUS_NAME_USER_REQUESTED = "USER_REQUESTED : 사용자 요청완료"; // 32
	public static final String AIRDROP_STATUS_NAME_ACCEPTED = "ACCEPTED : 접수완료"; // 34
	public static final String AIRDROP_STATUS_NAME_CONTRACT_CREATED = "CREATED : 계약 생성완료"; // 35
	public static final String AIRDROP_STATUS_NAME_SUBMITTED = "SUBMITTED : 제출완료"; // 36
	public static final String AIRDROP_STATUS_NAME_SUCCESS = "SUCCESS : 에어드랍 생성 완료"; // 40
	public static final String AIRDROP_STATUS_NAME_REJECTED = "REJECTED : 사용자 요청 반려"; // 90
	public static final String AIRDROP_STATUS_NAME_FAILED = "FAILED : 에어드랍 생성 실패"; // 99
	
	
	public static final String TOKEN_MINT_STATUS_CD_REGISTERED = "10"; // 토큰발행 등록 완료
	public static final String TOKEN_MINT_STATUS_CD_PAID = "30"; // 토큰발행 결제 완료
	public static final String TOKEN_MINT_STATUS_CD_SUCCESS = "40"; // 토큰발행 성공
	public static final String TOKEN_MINT_STATUS_CD_FAILED = "99"; // 토큰발행 실패
	
	public static final String TOKEN_BURN_STATUS_CD_REGISTERED = "10"; // 토큰소각 등록 완료
	public static final String TOKEN_BURN_STATUS_CD_PAID = "30"; // 토큰소각 결제 완료
	public static final String TOKEN_BURN_STATUS_CD_SUCCESS = "40"; // 토큰소각 성공
	public static final String TOKEN_BURN_STATUS_CD_FAILED = "99"; // 토큰소각 실패
	
	// 사이트맵
	public static final String SITEMAP_FILE_NAME = "sitemap";
	public static final String SITEMAP_FILE_FULL_NAME = SITEMAP_FILE_NAME + ".xml";
	
	
	
	// 로그인 사용자 정보
	public static final String SESSION_USER_INFO = "session_user_info";
	public static final String SESSION_PAY_INFO = "session_pay_info";
	
	// 결제 관련 정보
	// public static final String PAYMENT_CREATE_PRICE = "1001";
	// public static final String PAYMENT_MID = "oneoninc01"; // MID
	// public static final String PAYMENT_SIGN_KEY = "OUp6VkxCTnU5K2xHRkpCYmFZWE5LUT09"; // signKey - 이니시스 관리자 사이트에서 확인 가능 상점정보 > 부가정보 > 웹결제 signkey 생성 조회
	public static final String PAYMENT_MID = "metashop51"; // MID
	public static final String PAYMENT_SIGN_KEY = "aaaaaa"; // signKey - 이니시스 관리자 사이트에서 확인 가능 상점정보 > 부가정보 > 웹결제 signkey 생성 조회
	
	
	
	public static final String PAYMENT_VERSION = "1.0";
	public static final String PAYMENT_CURRENCY = "WON";
	public static final String ACCEPT_METHOD = "below1000";
	public static final String PAYMENT_RETURN_URL = SERVER_DOMAIN + "/api/payment/paymentReturnUrl"; // PC결제용
	// public static final String PAYMENT_CLOSE_URL = SERVER_DOMAIN + "/api/payment/paymentCloseUrl";
	
	public static final String PAYMENT_NEXT_URL = SERVER_DOMAIN + "/api/payment/paymentReturnUrlMobile"; // 
	
	
	// 결제수단구분
	public static final String PAYMENT_PAY_TYPE_CARD = "CARD"; // 결제수단:신용카드
//	public static final String PAYMENT_PAY_TYPE_BANK = "BANK"; // 결제수단:계좌이체
//	public static final String PAYMENT_PAY_TYPE_VBANK = "VBANK"; // 결제수단:가상계좌
//	public static final String PAYMENT_PAY_TYPE_VBANK_PC = "VBank"; // 결제수단:가상계좌 PC
	
	// 결제처리 코드
	public static final String PAYMENT_MOBILE_CALLBACK_CD_SUCCESS = "00"; // 결제인증 성공
	public static final String PAYMENT_MOBILE_RESULT_CD_SUCCESS = "00"; // 결제승인 성공
	public static final String PAYMENT_PC_RETURN_CD_SUCCESS = "0000"; // 결제인증 성공
	public static final String PAYMENT_PC_RESULT_CD_SUCCESS = "0000"; // 결제승인 성공
	public static final String PAYMENT_NPAY_CD_SUCCESS = "Success"; // 네이버페이 처리 성공
	
	
	
	
	
	public static final String[] CONTRACT_TOKEN_TYPE_KLAYTN = {"KIP-7"}; // 토큰 타입 : 클레이튼
	
	public static final String MAINNET_KLAYTN = "KLAYTN"; // 메인넷 : 클레이튼

	
	/**
	 * Name of property of error message in response.
	 */
	public static final String ERROR_MESSAGE = "error_message";
	
	public static final String ADMIN_EMAIL = "question_pkz@gaudilabs.io";
	
	
	/* 클라이언트 디바이스 */
	public static final String CLIENT_DEVICE_PC = "P";
	public static final String CLIENT_DEVICE_MOBILE = "M";
	public static final String CLIENT_DEVICE_APP_ANDROID = "android";
	public static final String CLIENT_DEVICE_APP_IOS = "ios";
	
	/* 시스템 정보 */
	public static final String SYSTEM_ID_FOR_DB = "SYSTEM"; // 사용자가 볼 수 없는 시스템 계정명
	public static final String ADMIN_ID_FOR_DB = "beastar"; // 사용자가 볼 수 있는 관리자 계정명(공지사항 등)
	public static final String ADMIN_DJ_ID_FOR_DB = "dcodj"; // DJ
	public static final String ADMIN_QNA = "dcoqna"; // QnA
	
	// 에러 이미지
	public static final String ERROR_IMAGE_URL = SERVER_DOMAIN + "/statics/images/error/error.jpg";
	
	// 디폴트 회원 프로필 이미지
	public static final String DEFAULT_PROFILE_IMAGE_URL = SERVER_DOMAIN + "/statics/images/member/member.gif";
	
	// 사용자 로그
	
	// 유튜버스 검색어 캐릭터셋
	public static final String CHARSET_UTF8 = "UTF-8";
	
//	// QnA
//	public static final String QNA_CD_SELLER = "SELLER";
//	public static final String QNA_CD_PRODUCT = "PRODUCT";
//	public static final String QNA_CD_SITE = "SITE";
	
	// NOTI 응답코드
	public static final String PAYMENT_NOTI_RESPONSE_OK = "payment/paymentCallbackNotiOK"; // NOTI에 OK 응답 - 이후 NOTI 수신 불가
	public static final String PAYMENT_NOTI_RESPONSE_FAIL = "payment/paymentCallbackNotiFAIL"; // NOTI에 FAIL 응답 - 내부처리 에러로 인해 다시 NOTI 수신
	
	// PC 가상계좌결제 입금통보(NOTI) 정상처리코드
	public static final String PAYMENT_NOTI_PC_STATUS_DEPOSIT_COMPLETED = "02"; // 모바일은 입금완료 상태코드가 02라서 통일시킴
	
	// 주문 상태
	public static final String ORDER_STATUS_PAYMENT_WAIT = "00";               // 입금대기
	public static final String ORDER_STATUS_PAYMENT_COMPLETED = "01";          // 결제완료(주문확인대기)
	public static final String ORDER_STATUS_REQUEST_CANCEL = "03";             // 취소요청
	public static final String ORDER_STATUS_CANCEL = "04";                     // 구매취소
	public static final String ORDER_STATUS_CANCEL_SELLER = "05";              // 판매취소
	public static final String ORDER_STATUS_DELIVERY_WAIT = "11";              // 배송대기(주문확인완료)
	public static final String ORDER_STATUS_DELIVERY_STARTED = "12";           // 배송중
	public static final String ORDER_STATUS_DELIVERY_COMPLETED = "21";         // 배송완료
	public static final String ORDER_STATUS_REQUEST_EXCHANGE = "40";           // 교환신청
	public static final String ORDER_STATUS_REQUEST_EXCHANGE_COMPLETED = "45"; // 교환완료
	public static final String ORDER_STATUS_REQUEST_RETURN = "62";             // 반품신청
	public static final String ORDER_STATUS_REQUEST_RETURN_COMPLETED = "64";   // 반품완료
	public static final String ORDER_STATUS_REQUEST_RETURN_REFUNDED = "66";    // 환불완료
	public static final String ORDER_STATUS_PURCHASE_CONFIRMED = "90";         // 구매확정
	// public static final String ORDER_STATUS_CALCULATE_COMPLETED = "91";        // 정산완료
	// public static final String ORDER_STATUS_REVIEW_COMPLETED = "92";           // 리뷰작성완료
	
	// 원온 계좌정보
	public static final String ONEON_BANK_NAME = "KB국민은행";
	public static final String ONEON_BANK_ACCOUNT_NO = "055201-04-230453";
	public static final String ONEON_BANK_ACCOUNT_NAME = "원온 주식회사";
	
	/* 업로드 파일 패스 */
	// public static final String FILE_UPLOAD_PATH = "D:/apache-tomcat-9.0.14/webapps/uploaded";
	// public static final String FILE_UPLOAD_PATH = "../uploaded";
	// public static final String FILE_UPLOADED_PATH = "/uploaded";
}
