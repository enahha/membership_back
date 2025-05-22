package io.starinc.api.v1.login.rest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class LoginController {
	
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//	
//	@Autowired
//	private UserMapper userMapper;
//	
//	@Autowired
//	private LoginMapper loginMapper;
//	
//	@Autowired
//	private LoginLogController logController;
//
//	/**
//	 * 아이디 중복 체크
//	 * 
//	 * @param  UserVo
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/login/checkIdDuplicate")
//	public CommonVo checkIdDuplicate(@RequestBody UserTempVo userTempVo) throws Exception {
//		CommonVo commonVo = new CommonVo();
//		commonVo.setResultCd("SUCCESS");
//		commonVo.setResultMsg("It's a available ID");
//		try {
//			int count = this.loginMapper.countUserId(userTempVo);
//			if (count > 0) {
//				commonVo.setResultCd("FAIL");
//				commonVo.setResultMsg("ID is already in use.");
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonVo.setResultCd("FAIL");
//			commonVo.setResultMsg(e.toString());
//		}
//		return commonVo;
//	}
//
//	/**
//	 * 아이디 존재 체크
//	 * 
//	 * @param  UserVo
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/login/checkIdExist")
//	public CommonVo checkIdExist(@RequestBody UserTempVo userTempVo) throws Exception {
//		CommonVo commonVo = new CommonVo();
//		commonVo.setResultCd("SUCCESS");
//		commonVo.setResultMsg("ID exists.");
//		try {
//			int count = this.loginMapper.countUserId(userTempVo);
//			if (count == 0) {
//				commonVo.setResultCd("FAIL");
//				commonVo.setResultMsg("ID doesn't exist");
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonVo.setResultCd("FAIL");
//			commonVo.setResultMsg(e.toString());
//		}
//		return commonVo;
//	}
//	
//	/**
//	 * 이메일 확인 코드 전송
//	 * 
//	 * @param  UserVo
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/login/sendMailCode")
//	public CommonVo sendMailCode(@RequestBody UserTempVo userTempVo) throws Exception {
//		CommonVo commonVo = new CommonVo();
//		commonVo.setResultCd("SUCCESS");
//		commonVo.setResultMsg("Email Code has been sent.");
//		
//		// 메일코드 생성
//		int newCode = NumberUtil.randomRange(100000, 999999);
//		
//		// 암호화
////		String encNewCode = SecurityUtil.getSHA512(String.valueOf(newCode));
//		String text = "Verification code : <b>" + newCode + "</b><br>";
//		
//		// 2. 메일 내용 작성
//		String subject = "[" + Constant.SERVER_DOMAIN + "] The email verification code information";
//		
//		// 3. 메일 전송
//		MailVo mailVo = new MailVo();
//		mailVo.setSubject(subject);
//		mailVo.setText(text);
//		mailVo.setEmail_to(userTempVo.getUid());
//		MailUtil mailUtil = new MailUtil();
//		try {
//			mailUtil.send(mailVo);
//		} catch (Exception e) {
//			logger.debug(e.toString());
//			commonVo.setResultCd("MAIL_ERROR");
//			commonVo.setResultMsg(e.toString());
//		}
//		
//		// 가입중인 유저 정보 등록
//		try {
//			userTempVo.setCode(String.valueOf(newCode));
//			int count = this.loginMapper.mergeUserTemp(userTempVo);
//			if (count == 0) {
//				commonVo.setResultCd("FAIL");
//				commonVo.setResultMsg("mergeUserTemp failed.");
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonVo.setResultCd("FAIL");
//			commonVo.setResultMsg(e.toString());
//		}
//		
//		return commonVo;
//	}
//	
//	/**
//	 * 이메일 확인 코드 체크
//	 * 
//	 * @param  UserVo
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/login/checkEmailCode")
//	public CommonVo checkEmailCode(@RequestBody UserTempVo userTempVo) throws Exception {
//		CommonVo commonVo = new CommonVo();
//		commonVo.setResultCd("SUCCESS");
//		commonVo.setResultMsg("Email code verification is completed.");
//		try {
//			int count = this.loginMapper.checkEmailCode(userTempVo);
//			if (count == 0) {
//				commonVo.setResultCd("FAIL");
//				commonVo.setResultMsg("Email code verification is failed.");
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonVo.setResultCd("FAIL");
//			commonVo.setResultMsg(e.toString());
//		}
//		return commonVo;
//	}
//	
//	
//	/**
//	 * 로그인 - 클레이스타 계정으로 로그인
//	 * 
//	 * @param userVo
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/login/doLogin")
//	public UserVo doLogin(@RequestBody UserVo userVo, HttpServletRequest request, HttpSession session) throws Exception {
//		try {
//			String sha512Password = userVo.getPwd2();
//			
//			// CORDOVA APP에서 WEB으로 이동하는 경우, auth_key로 비교
//			if (StringUtil.isEmpty(sha512Password)) {
//				sha512Password = userVo.getAuth_key();
//			}
//			
//			// 사용자 조회
//			userVo = this.userMapper.selectUser(userVo.getUid());
//			
//			// 1. 아이디 없음
//			if (userVo == null) {
//				UserVo returnVo = new UserVo();
//				returnVo.setResultCd("NO_ID"); // 입력 아이디의 사용자 없음
//				return returnVo;
//			}
//			
//			// 2. 패스워드 비교
//			String dbPwd = userVo.getPwd();
//	//		String sha512Password = userVo.getPwd2();
//			if (!sha512Password.equals(dbPwd)) {
//				// 인증키와 비교
//				if (!sha512Password.equals(userVo.getAuth_key())) {
//					UserVo returnVo = new UserVo();
//					returnVo.setResultCd("WRONG_PWD"); // 패스워드 오류
//					
//					// 로그인 로그 등록 - 패스워드 오류
//					int count = this.logController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_WRONG_PASSWORD, SystemUtil.getRealIp(request));
//					if (count < 1) {
//						userVo.setResultMsg("Login log insert failed.");
//					}
//					
//					return returnVo;
//				}
//			}
//			userVo.setPwd(""); // 패스워드 제거
//			
//			// 결과코드 : 성공
//			userVo.setResultCd("SUCCESS");
//			// 세션에 사용자 정보 저장
//			session.setAttribute(Constant.SESSION_USER_INFO, userVo);
//			
//			// 로그인 로그 등록
//			int count = this.logController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_LOGIN, SystemUtil.getRealIp(request));
//			if (count < 1) {
//				userVo.setResultMsg("Login log insert failed.");
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			userVo.setResultCd("FAIL");
//			userVo.setResultMsg(e.toString());
//		}
//		
//		return userVo;
//	}
//	
//	/**
//	 * 로그아웃
//	 * 
//	 * @param session
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/login/doLogout")
//	public UserVo doLogout(@RequestBody UserVo userVo, HttpSession session, HttpServletRequest request) throws Exception {
//		userVo.setResultCd("SUCCESS");
//		try {
//			// 로그아웃 로그 등록
//			int count = this.logController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_LOGOUT, SystemUtil.getRealIp(request));
//			if (count < 1) {
//				userVo.setResultMsg("Logout log insert failed.");
//			}
//			if (session != null) {
//				// 세션의 사용자 정보 초기화
//				session.invalidate();
//			}
//		} catch (Exception e) {
//			logger.debug("■■■■■■■■■■■■■■■■■■■■■ e.toString(): " + e.toString());
//			userVo.setResultCd("FAIL");
//			userVo.setResultMsg("Logout failed.");
//		}
//		return userVo;
//	}
	
}
