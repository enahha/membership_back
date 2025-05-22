package io.starinc.api.v1.login.mapper;

import io.starinc.api.v1.login.vo.LoginLogVo;
import io.starinc.api.v1.login.vo.UserTempVo;
 
public interface LoginMapper {
	
	
	// 가입중인 유저 아이디 중복 체크
	public int countUserId(UserTempVo userTempVo) throws Exception;
	// 가입중인 유저 이메일 코드 체크
	public int checkEmailCode(UserTempVo userTempVo) throws Exception;
	// 가입중인 유저 정보 등록(아이디, 패스워드, 이메일코드)
	public int mergeUserTemp(UserTempVo userTempVo) throws Exception;
	// 로그인 로그 등록
	public int insertLoginLog(LoginLogVo loginLogVo) throws Exception;
	
//	public UserVo selectUserLogin(UserVo userVo) throws Exception;
	
	
//	public int insertLoginLog(LoginLogVo loginLogVo) throws Exception;
	
	
	
	// 회원여부(카운트) 조회
	// public int selectUserCount(String uid) throws Exception;
}
