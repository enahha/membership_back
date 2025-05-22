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
package io.starinc.api.v1.minelog.mapper;

import java.util.List;
import java.util.Map;

import io.starinc.api.v1.minelog.vo.MineLogVo;
import io.starinc.api.v1.minelog.vo.PendingRewardsVo;
 
public interface MineLogMapper {
	
//	// 채굴로그 리스트 조회
//	public int selectMineLogListLastPageNum(CommonVo commonVo) throws Exception;
//	public List<MineLogVo> selectMineLogList(CommonVo commonVo) throws Exception;
	
	// 마이페이지 채굴량 조회
	public List<PendingRewardsVo> selectMineLogPendingRewardsList(Map<String, Object> hashMap) throws Exception;
	
	// 마이페이지 NFT 타입별 채굴량 조회
	public List<PendingRewardsVo> selectMineLogNftTypePendingRewardsList(Map<String, Object> hashMap) throws Exception;
	
//	// 채굴로그 조회
//	public MineLogVo selectMineLog(int seq) throws Exception;
	
//	// 채굴로그 리스트 조회
//	public List<MineLogVo> selectMineLog(MineLogVo mineLogVo) throws Exception;
	
	// 채굴로그 등록
	public int insertMineLog(MineLogVo mineLogVo) throws Exception;
	
//	// 채굴로그 수정
//	public int updateMineLog(MineLogVo mineLogVo) throws Exception;
	
//	// 채굴로그 삭제
//	public int deleteMineLog(MineLogVo mineLogVo) throws Exception;
}
