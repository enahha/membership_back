package io.starinc.api.v1.core.jsp;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@RequestMapping(value = "/api")
@Controller
public class JspController implements ErrorController {
	
//	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//	
//	@Autowired
//	private UserMapper userMapper;
//	
//	@Autowired
//	private LoginLogController logController;
//	
//	
//	@RequestMapping(value = "/" , method = {RequestMethod.POST, RequestMethod.GET})
//	public String main(HttpServletRequest request, HttpSession session, Model model) throws Exception {
//		return "index.html";
//	}
//	/**
//	 * 카카오 로그인 redirectUri
//	 * 
//	 * @param  UserVo
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "payment/test" , method = {RequestMethod.POST, RequestMethod.GET})
//	public String test(HttpServletRequest request, HttpSession session, Model model) throws Exception {
//		return "index";
//	}
//	@RequestMapping(value = "payment/payReturn" , method = {RequestMethod.POST, RequestMethod.GET})
//	public String payReturn(HttpServletRequest request, HttpSession session, Model model) throws Exception {
//		return "payment/INIStdPayReturn";
//	}
////	@RequestMapping(value = "test" , method = {RequestMethod.POST, RequestMethod.GET})
////	public String test(HttpServletRequest request, HttpSession session, Model model) throws Exception {
////		return "INIStdPayRequest";
////	}
////	@RequestMapping(value = "test" , method = {RequestMethod.POST, RequestMethod.GET})
////	public String test(HttpServletRequest request, HttpSession session, Model model) throws Exception {
////		return "INIStdPayRequest";
////	}
//	
//	
//	
//	
//	/**
//	 * 카카오 로그인 redirectUri
//	 * 
//	 * @param  UserVo
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "login/kakaoLoginCallbackApp" , method = {RequestMethod.POST, RequestMethod.GET})
//	public String kakaoLoginCallbackApp(HttpServletRequest request, HttpSession session, Model model) throws Exception {
//		UserVo userVo = new UserVo(); // 반환값
//		userVo.setResultCd("SUCCESS");
//		userVo.setResultMsg("Kakao login success.");
//		
//		String state = request.getParameter("state");
//		String frontPath = "";
//		String frontLocale = "";
//		String adcd = "";
//		if (state != null) {
//			String[] stateArray = state.split("\\|");
//			if (stateArray.length > 0) {
//				frontPath = stateArray[0];
//			}
//			if (stateArray.length > 1) {
//				frontLocale = stateArray[1];
//			}
//			if (stateArray.length > 2) {
//				adcd = stateArray[2];
//			}
//		}
//		
//		try {
//			logger.debug(request.getParameter("code"));
//			logger.debug(request.getParameter("state"));
//			logger.debug(request.getParameter("error"));
//			logger.debug(request.getParameter("error_description"));
//			
//			String autorizeCode = request.getParameter("code");
//			logger.debug("autorizeCode: " + autorizeCode);
//			JsonNode tokenInfoNode = KakaoLoginUtil.getAccessToken(autorizeCode);
//			
//			if (tokenInfoNode.get("error") != null) {
//				logger.debug("ERROR: " + tokenInfoNode.get("error").asText());
//				logger.debug("ERROR_DESC: " + tokenInfoNode.get("error_description").asText());
//			}
//			
//			String accessToken = String.valueOf(tokenInfoNode.get("access_token").asText());
//			logger.debug("accessToken: " + accessToken);
//			
//			
//			logger.debug("■■■■■■■■■■■■■■■■■■■■■ 카카오 로그인 사용자 정보 요청 전");
//			JsonNode kakaoInfoNode = KakaoLoginUtil.getKakaoUserInfo(accessToken);
//			logger.debug("■■■■■■■■■■■■■■■■■■■■■ 카카오 로그인 사용자 정보 요청 후");
//			if (kakaoInfoNode == null) {
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 카카오 로그인 사용자 정보 요청 kakaoInfoNode == null");
//				userVo.setResultCd("E01");
//				userVo.setResultMsg("카카오 사용자 정보 요청 실패");
////				return userVo;
//				return "kakaoCallbackApp";
//			}
//			
//			JsonNode kakaoAccountNode = kakaoInfoNode.get("kakao_account");
//			String kakaoEmail = kakaoAccountNode.get("email").asText(); // 이메일
//			if (StringUtil.isEmpty(kakaoEmail)) {
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 카카오 로그인 사용자 정보 요청 kakaoEmail is Empty");
//				userVo.setResultCd("E02");
//				userVo.setResultMsg("카카오 사용자 정보 email이 없습니다.");
////				return userVo;
//				return "kakaoCallbackApp";
//			}
//			
//			// 사용자 조회 - 비회원인 경우에는 회원가입 처리
//			userVo.setUid(kakaoEmail);
//			UserVo dbUserVo = this.userMapper.selectUser(kakaoEmail);
//			
//			if (dbUserVo == null) { // 사용자가 없는 경우
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 카카오 로그인 사용자가 없는 경우 dbUserVo == null");
//				
//				
//				// 자동로그인용 32자리 인증키 생성
//				String authKey = UUID.randomUUID().toString().replace("-", "");
//				userVo.setAuth_key(authKey);
//				
//				userVo.setPwd(accessToken); // 패스워드는 accessToken
//				
//				// 사용자 정보 UserVo에 설정
////				logger.debug("■■■■■■■■■■■■■■■■■■■■■ setUserVoFromKakaoInfo 전");
////				this.setUserVoFromKakaoInfo(kakaoInfoNode, userVo);
////				logger.debug("■■■■■■■■■■■■■■■■■■■■■ setUserVoFromKakaoInfo 후");
//				
//				int result = this.userMapper.insertUser(userVo);
//				if (result < 1) {
//					userVo.setResultCd("E03");
//					userVo.setResultMsg("카카오 사용자 정보 등록 실패");
//				}
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ insertUser(userVo) 후");
//				
//				// 로그인 로그 등록
//				int count = this.logController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_JOIN_KAKAO, SystemUtil.getRealIp(request));
//				if (count < 1) {
//					userVo.setResultMsg("Login log insert failed.");
//				}
//				
//				
//				
//			} else {
//				// 기회원인 경우 - DB유저 정보로 설정
//				userVo = dbUserVo;
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 기회원인 경우 - DB유저 정보로 설정");
//				
//				userVo.setPwd(""); // 패스워드 제거
//				
//				// 로그인 로그 등록
//				int count = this.logController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_LOGIN, SystemUtil.getRealIp(request));
//				if (count < 1) {
//					userVo.setResultMsg("Login log insert failed.");
//				}
//				
//				// 결과코드 : 성공
//				userVo.setResultCd("SUCCESS");
//			}
//			
//			// 세션에 사용자 정보 저장
//			// session.setAttribute(Constant.SESSION_USER_INFO, userVo);
//		} catch (Exception e) {
//			logger.debug("■■■■■■■■■■■■■■■■■■■■■ e.toString(): " + e.toString());
//			userVo.setResultCd("FAIL");
//			userVo.setResultMsg("Kakao login failed.");
//		}
//		
//		
//		model.addAttribute("resultCd", userVo.getResultCd());
//		model.addAttribute("setResultMsg", userVo.getResultMsg());
//		
//		model.addAttribute("email", userVo.getUid());
//		model.addAttribute("authKey", userVo.getAuth_key());
//		model.addAttribute("frontPath", frontPath);
//		model.addAttribute("frontLocale", frontLocale);
//		model.addAttribute("adcd", adcd);
//		
//		// 도메인이 운영(https://beastar.io)이면 운영 도메인으로,
//		model.addAttribute("domain", Constant.SERVER_DOMAIN);
//		// 도메인이 테스트(http://121.162.58.101:8888) 이면 http://121.162.58.101:8081 로 설정
//		if (!Constant.SERVER_DOMAIN.contains("beastar.io")) {
//			model.addAttribute("domain", Constant.SERVER_DOMAIN_TEST_FRONT);
//		}
//		
//		return "kakaoCallbackApp";
//	}
 
}
