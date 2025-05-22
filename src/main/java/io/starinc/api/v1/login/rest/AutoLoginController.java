package io.starinc.api.v1.login.rest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class AutoLoginController {
	
//	private static final Logger logger = LoggerFactory.getLogger(AutoLoginController.class);
//	
//	@Autowired
//	private UserMapper userMapper;
//	
//	@Autowired
//	private LoginMapper loginMapper;
//	
//	@Autowired
//	private LogController logController;
//	
//	/**
//	 * 자동로그인
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/login/autoLogin" , method = {RequestMethod.POST})
//	public UserVo autoLogin(@RequestBody UserVo userVo, HttpServletRequest request) throws Exception {
//		try {
//			// 사용자 정보 조회
//			UserVo dbUserVo = this.userMapper.selectUserAutoLogin(userVo);
//			
//			if (dbUserVo == null) { // 사용자가 없는 경우, 자동로그인 실패
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 사용자가 없는 경우 dbUserVo == null");
//				userVo.setResultCd("FAIL");
//				userVo.setResultMsg("User doesn't exist.");
//			} else {
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 사용자가 있는 경우");
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 로그인 로그 등록 시작");
//				// 로그인 로그 등록
//				int count = this.logController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_AUTO_LOGIN, SystemUtil.getRealIp(request));
//				if (count < 1) {
//					userVo.setResultMsg("Login log insert failed.");
//				}
//				
//				logger.debug("■■■■■■■■■■■■■■■■■■■■■ 로그인 로그 등록 완료");
//				
//				// auth_key 갱신 필요
//				userVo = dbUserVo;
//				userVo.setPwd("");
//				userVo.setResultCd("SUCCESS"); // 결과코드 : 성공
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			userVo.setResultCd("FAIL");
//			userVo.setResultMsg(e.toString());
//			logger.debug("■■■■■■■■■■■■■■■■■■■■■ e.toString(): " + e.toString());
//		}
//		return userVo;
//	}
}
