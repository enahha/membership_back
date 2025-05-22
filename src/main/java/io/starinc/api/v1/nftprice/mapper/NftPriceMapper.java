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
package io.starinc.api.v1.nftprice.mapper;

import java.util.List;

import io.starinc.api.v1.nftprice.vo.NftPriceVo;
 
public interface NftPriceMapper {
	
	// NFT 가격 리스트 조회
	public List<NftPriceVo> selectNftPriceList(NftPriceVo nftPriceVo) throws Exception;
	
	// NFT 가격 조회
	public NftPriceVo selectNftPrice(NftPriceVo nftPriceVo) throws Exception;
	
//	// NFT 가격 조회 by SEQ
//	public NftPriceVo selectNftPriceBySeq(NftPriceVo nftPriceVo) throws Exception;
	
//	// NFT 가격 등록
//	public int insertNftPrice(NftPriceVo nftPriceVo) throws Exception;
//	
//	// NFT 가격 수정
//	public int updateNftPrice(NftPriceVo nftPriceVo) throws Exception;
	
//	// NFT 가격 삭제
//	public int deleteNftPrice(NftPriceVo nftPriceVo) throws Exception;
}
