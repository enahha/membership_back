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
package io.starinc.api.v1.mintplan.mapper;

import java.util.List;

import io.starinc.api.v1.mintplan.vo.MintPlanVo;
 
public interface MintPlanMapper {
	
//	// 민트플랜 리스트 조회
//	public int selectMintPlanListLastPageNum(CommonVo commonVo) throws Exception;
//	public List<MintPlanVo> selectMintPlanList(CommonVo commonVo) throws Exception;
	public List<MintPlanVo> selectMintPlanList(MintPlanVo mintPlanVo) throws Exception;
	
	// 민트플랜 필드 리스트 for CSV 조회
	public List<MintPlanVo> selectMintPlanFieldList(MintPlanVo mintPlanVo) throws Exception;
	
//	// 민트플랜 조회
//	public MintPlanVo selectMintPlan(int seq) throws Exception;
	
	// 민트플랜 리스트 조회
	public List<MintPlanVo> selectMintPlan(MintPlanVo mintPlanVo) throws Exception;
	
	// 민트플랜 등록
	public int insertMintPlan(MintPlanVo mintPlanVo) throws Exception;
	
//	// 민트플랜 수정
//	public int updateMintPlan(MintPlanVo mintPlanVo) throws Exception;
	
	// 민트플랜 상태 수정
	public int updateMintPlanStatus(MintPlanVo mintPlanVo) throws Exception;
	
	
//	// 민트플랜 삭제
//	public int deleteMintPlan(MintPlanVo mintPlanVo) throws Exception;
}
