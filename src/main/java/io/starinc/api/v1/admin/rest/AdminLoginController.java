package io.starinc.api.v1.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.admin.vo.AdminLoginVo;
import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.common.rest.LoginLogController;
import io.starinc.api.v1.common.util.SystemUtil;
import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.user.mapper.UserMapper;
import io.starinc.api.v1.user.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class AdminLoginController {
	
	// private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private LoginLogController loginLogController;
	
	/**
	 * 관리자 로그인
	 * 
	 * @param uid
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/doLoginAdmin" , method = {RequestMethod.POST, RequestMethod.GET})
	public CommonResultVo doLoginAdmin(@RequestBody AdminLoginVo adminLoginVo, HttpServletRequest request, HttpSession session) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		String uid = adminLoginVo.getUid();
		String pwd = adminLoginVo.getPwd();
		
		try {
			// 사용자 조회
			UserVo userVo = this.userMapper.selectUserByUid(uid);
			
			// 1. 아이디 없음
			if (userVo == null) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("selectUserByUid is null.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
			
			// 2. 패스워드 비교
	//		String sha512Password = userVo.getPwd2();
			if (!pwd.equals(userVo.getPwd())) {
				// 로그인 로그 등록 - 패스워드 오류
				this.loginLogController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_WRONG_PASSWORD, SystemUtil.getRealIp(request));
				
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("wrong password.");
				commonResultVo.setReturnCd("2");
				return commonResultVo;
			}
			
			// 3. 관리자 권한 체크
			if (!"1234".equals(userVo.getAdcd())) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("not admin.");
				commonResultVo.setReturnCd("3");
				return commonResultVo;
			}
			
			userVo.setPwd(null); // 패스워드 제거
			commonResultVo.setReturnValue(userVo); // 사용자 정보 설정
			
//			// 세션에 사용자 정보 저장
//			session.setAttribute(Constant.SESSION_USER_INFO, userVo);
			
			// 로그인 로그 등록
			this.loginLogController.insertLoginLog(userVo.getUid(), Constant.LOGIN_LOG_CD_LOGIN, SystemUtil.getRealIp(request));
			
		} catch (Exception e) {
			// 결과코드 : 실패
			commonResultVo.setResultCd("FAIL"); // 비정상처리
			commonResultVo.setResultMsg(e.toString());
			commonResultVo.setReturnCd("3");
			return commonResultVo;
		}
		
		return commonResultVo;
	}
}
