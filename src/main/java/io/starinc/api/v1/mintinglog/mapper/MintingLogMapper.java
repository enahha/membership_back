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
package io.starinc.api.v1.mintinglog.mapper;

import java.util.List;

import io.starinc.api.v1.mintinglog.vo.MintingLogVo;
 
public interface MintingLogMapper {
	
//	// 민팅로그 리스트 조회
//	public int selectMintingLogListLastPageNum(CommonVo commonVo) throws Exception;
//	public List<MintingLogVo> selectMintingLogList(CommonVo commonVo) throws Exception;
	
//	// 민팅로그 조회
//	public MintingLogVo selectMintingLog(int seq) throws Exception;
	
	//민팅 완료 리스트 조회
	public List<MintingLogVo> selectMintingLog(MintingLogVo mintingLogVo) throws Exception;
	
	// 민팅로그 등록 (민팅 전)
	public int insertMintingLog(MintingLogVo mintingLogVo) throws Exception;
	
	// 민팅로그 수정 (민팅 후)
	public int updateMintingLog(MintingLogVo mintingLogVo) throws Exception;
	
//	// 민팅로그 삭제
//	public int deleteMintingLog(MintingLogVo mintingLogVo) throws Exception;
}
