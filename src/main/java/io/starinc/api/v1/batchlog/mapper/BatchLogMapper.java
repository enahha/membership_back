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
package io.starinc.api.v1.batchlog.mapper;

import io.starinc.api.v1.batchlog.vo.BatchLogVo;
 
public interface BatchLogMapper {
	
//	// 배치로그 리스트 조회
//	public int selectBatchLogListLastPageNum(CommonVo commonVo) throws Exception;
//	public List<BatchLogVo> selectBatchLogList(CommonVo commonVo) throws Exception;
	
//	// 배치로그 조회
//	public BatchLogVo selectBatchLog(int seq) throws Exception;
	
//	// 배치로그 리스트 조회
//	public List<BatchLogVo> selectBatchLog(BatchLogVo batchLogVo) throws Exception;
	
	// 배치로그 등록
	public int insertBatchLog(BatchLogVo batchLogVo) throws Exception;
	
//	// 배치로그 수정
//	public int updateBatchLog(BatchLogVo batchLogVo) throws Exception;
	
//	// 배치로그 삭제
//	public int deleteBatchLog(BatchLogVo batchLogVo) throws Exception;
}
