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
package io.starinc.api.v1.payment.mapper;

import java.util.List;
import java.util.Map;

import io.starinc.api.v1.payment.vo.PaymentVo;
 
public interface PaymentMapper {
	
//	// 결제정보 리스트 조회
//	public int selectPaymentListLastPageNum(CommonVo commonVo) throws Exception;
	
	// 결제정보 리스트 조회 (결제자 리스트 일괄 민팅용)
	public List<PaymentVo> selectPaymentList(PaymentVo paymentVo) throws Exception;
	
	// 결제정보 리스트 조회 (mint_plan insert용)
	public List<PaymentVo> selectPaymentListNotInMintPlan(PaymentVo paymentVo) throws Exception;
	
	
	
	// 지갑주소별 결제정보 리스트 조회 (사용자 확인용)
	public List<PaymentVo> selectPaymentListByWalletAddress(PaymentVo paymentVo) throws Exception;
	
//	// 결제정보 조회
//	public PaymentVo selectPayment(int seq) throws Exception;
	
//	// 결제정보 리스트 조회
//	public List<PaymentVo> selectPayment(PaymentVo paymentVo) throws Exception;
	
	// 결제정보 등록
	public int insertPayment(PaymentVo paymentVo) throws Exception;
	
//	// 결제정보 수정
//	public int updatePayment(PaymentVo paymentVo) throws Exception;
	
	// 결제정보 상태 수정
	public int updatePaymentStatus(PaymentVo paymentVo) throws Exception;
	
//	// 결제정보 삭제
//	public int deletePayment(PaymentVo paymentVo) throws Exception;
	
	public Map<String, Object> selectPaymentMeQuantitySold() throws Exception;
}
