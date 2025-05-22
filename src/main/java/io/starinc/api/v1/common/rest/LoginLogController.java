package io.starinc.api.v1.common.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.login.mapper.LoginMapper;
import io.starinc.api.v1.login.vo.LoginLogVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class LoginLogController {
	
//	private static final Logger logger = LoggerFactory.getLogger(LoginLogController.class);
	
	@Autowired
	private LoginMapper loginMapper;
	
	/**
	 * 로그인 관련 로그 처리
	 * 
	 * @param uid
	 * @param logCd
	 * @param clientIp
	 * 
	 * @return resultCount
	 */
	public int insertLoginLog(String uid, String logCd, String clientIp) {
		int resultCount = 0;
		try {
			LoginLogVo loginLogVo = new LoginLogVo();
			loginLogVo.setUid(uid);
			loginLogVo.setLog_cd(logCd); // 로그인 성공
			loginLogVo.setClient_ip(clientIp);
			resultCount = this.loginMapper.insertLoginLog(loginLogVo);
			loginLogVo = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultCount;
	}
}
