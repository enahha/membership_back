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
package io.starinc.api.v1.marketprice.mapper;

import io.starinc.api.v1.marketprice.vo.MarketPriceVo;
 
public interface MarketPriceMapper {
	
//	// 시장가격 리스트 조회
//	public int selectMarketPriceListLastPageNum(CommonVo commonVo) throws Exception;
//	public List<MarketPriceVo> selectMarketPriceList(CommonVo commonVo) throws Exception;
	
	// 시장가격 조회
	public MarketPriceVo selectMarketPriceByDate(MarketPriceVo marketPriceVo) throws Exception;
	
	// 시장가격 등록
	public int insertMarketPrice(MarketPriceVo marketPriceVo) throws Exception;
	
//	// 시장가격 수정
//	public int updateMarketPrice(MarketPriceVo marketPriceVo) throws Exception;
	
//	// 시장가격 삭제
//	public int deleteMarketPrice(MarketPriceVo marketPriceVo) throws Exception;
}
