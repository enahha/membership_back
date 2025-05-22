package io.starinc.api.v1.nfttype.mapper;

import java.util.List;

import io.starinc.api.v1.nfttype.vo.NftTypeVo;

public interface NftTypeMapper {

	// NFT 타입 리스트 조회
	public List<NftTypeVo> selectNftTypeList(NftTypeVo nftTypeVo) throws Exception;
	
	// NFT 타입 및 가격 리스트 조회
	public List<NftTypeVo> selectNftTypeAndPriceList(int projectSeq) throws Exception;
	
	// NFT 타입 조회
	public NftTypeVo selectNftType(NftTypeVo nftTypeVo) throws Exception;
	
	// NFT 타입의 amount_remain -1
	public int updateNftTypeRemainCount(NftTypeVo nftTypeVo) throws Exception;
	
	// NFT 타입별 NFT 가격 조회
	public List<NftTypeVo> selectNftTypeAndPriceByProjectSeq(NftTypeVo nftTypeVo) throws Exception;
	 
//	// NFT 타입 등록
//	public int insertNftType(NftTypeVo nftTypeVo) throws Exception;
//	
//	// NFT 타입 수정
//	public int updateNftType(NftTypeVo nftTypeVo) throws Exception;
	
//	// NFT 타입 삭제
//	public int deleteNftType(NftTypeVo nftTypeVo) throws Exception;
	
}
