/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.starinc.api.v1.user.mapper;

import io.starinc.api.v1.user.vo.UserVo;
 
public interface UserMapper {
//	// 이메일로 유저 조회
//	public UserVo selectUidByEmail(String email) throws Exception;
//	// 새 비밀번호로 수정 - 비밀번호 찾기
//	public int updateUserNewPwd(UserVo userVo) throws Exception;

//	// wallet_address로 사용자 조회
	public UserVo selectUserByWalletAddress(String wallet_address) throws Exception;
//	
//	// 사용자 조회
//	public UserVo selectUser(String uid) throws Exception;
	
	// uid로 사용자 조회
	public UserVo selectUserByUid(String uid) throws Exception;
	
//	// 자동로그인 사용자 조회
//	public UserVo selectUserAutoLogin(UserVo userVo) throws Exception;
//	
	// 사용자 등록
	public int insertUser(UserVo userVo) throws Exception;
//	
//	// 새 비밀번호로 수정 - 비밀번호 찾기
//	public int updateUserPwd(UserVo userVo) throws Exception;
//	
//	// 카카오 회원으로 수정
//	public int updateUserToKakaoUser(UserVo userVo) throws Exception;
	
	// solana_wallet_address 수정
	public int updateUserSolanaWalletAddress(UserVo userVo) throws Exception;
	
	// Memetoon 계정정보 수정
	public int updateUserMemetoonAccount(UserVo userVo) throws Exception;
//	
////	// 사용자 아이디 리스트 조회 - 사이트맵 작성에서 사용
////	public List<UserVo> selectUidList() throws Exception;
////	
////	// 상품수, 게시물수, 모임수, 팔로워수, 팔로잉수, 오늘방문자수, 전체방문자수 조회
////	public UserVo selectUserCounts(String uid) throws Exception;
////	
////	
////	// 프로필 이미지 수정
////	public int updateUserProfileImage(UserVo userVo) throws Exception;
////	
//	// 회원정보 수정
//	public int updateUser(UserVo userVo) throws Exception;
////	
////	public int deleteUser(UserVo userVo) throws Exception;
}
