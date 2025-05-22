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
package io.starinc.api.v1.refundlog.mapper;

import io.starinc.api.v1.refundlog.vo.RefundLogVo;
 
public interface RefundLogMapper {
	
//	// 환불로그 리스트 조회
//	public int selectRefundLogListLastPageNum(CommonVo commonVo) throws Exception;
//	public List<RefundLogVo> selectRefundLogList(CommonVo commonVo) throws Exception;
	
//	// 환불로그 조회
//	public RefundLogVo selectRefundLog(int seq) throws Exception;
	
//	// 환불로그 리스트 조회
//	public List<RefundLogVo> selectRefundLog(RefundLogVo refundLogVo) throws Exception;
	
	// 환불로그 등록
	public int insertRefundLog(RefundLogVo refundLogVo) throws Exception;
	
//	// 환불로그 수정
//	public int updateRefundLog(RefundLogVo refundLogVo) throws Exception;
	
//	// 환불로그 삭제
//	public int deleteRefundLog(RefundLogVo refundLogVo) throws Exception;
}
