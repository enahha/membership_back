package io.starinc.api.v1.login.rest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class KakaoLoginController {
	
//	private static final Logger logger = LoggerFactory.getLogger(KakaoLoginController.class);
//	
//	@Autowired
//	private UserMapper userMapper;
//	
//	@Autowired
//	private LoginLogController logController;
//	
//	/**
//	 * 카카오 로그인 앱 (android, ios)
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	@PostMapping("/login/insertSelectUser")
//	public UserVo insertSelectUser(@RequestBody UserVo userVo, HttpServletRequest request, HttpSession session) throws Exception {
//		// 결과코드 : 성공
//		userVo.setResultCd("SUCCESS");
//		try {
//			// 사용자 정보 조회
//			UserVo dbUserVo = this.userMapper.selectUser(userVo.getUid());
//			
//			if (dbUserVo == null) { // 사용자가 없는 경우, 회원가입
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 카카오 사용자가 없는 경우 dbUserVo == null");
//				
//				// 사용자 정보 등록
//				String authKey = UUID.randomUUID().toString().replace("-", "");
//				userVo.setAuth_key(authKey);
//				this.userMapper.insertUser(userVo);
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 카카오 회원 등록 완료");
//				
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 로그인 로그 등록 시작");
//				// 로그인 로그 등록
//				int count = this.logController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_JOIN_KAKAO, SystemUtil.getRealIp(request));
//				if (count < 1) {
//					userVo.setResultMsg("Login log insert failed.");
//				}
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 로그인 로그 등록 완료");
//			} else {
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 카카오 사용자가 있는 경우 - 기존 회원 정보 수정");
//				// 기회원인 경우
//				// ucode = 2로 수정(카카오회원), pwd, pwd2는 카카오 토큰으로 수정
//				this.userMapper.updateUserToKakaoUser(userVo);
//				
//				// DB유저 정보로 설정
//				userVo.setAuth_key(dbUserVo.getAuth_key());
//				
//				// DB유저 어드민코드(adcd) 설정
//				userVo.setAdcd(dbUserVo.getAdcd());
//				
//				// 로그인 로그 등록
//				int count = this.logController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_LOGIN_KAKAO, SystemUtil.getRealIp(request));
//				if (count < 1) {
//					userVo.setResultMsg("Login log insert failed.");
//				}
//			}
//			// 세션에 사용자 정보 저장
//			session.setAttribute(Constant.SESSION_USER_INFO, userVo);
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			userVo.setResultCd("FAIL");
//			userVo.setResultMsg(e.toString());
//			logger.debug("■■■■■■■■■■■■■■■■■■■■■ e.toString(): " + e.toString());
//		}
//		return userVo;
//	}
}
