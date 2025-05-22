package io.starinc.api.v1.nftinfo.mapper;

import java.util.List;

import io.starinc.api.v1.nftinfo.vo.NftInfoVo;

public interface NftInfoMapper {
	
	// NFT 정보 민팅 완료 리스트 조회(민팅 후 1일 경과)
	public List<NftInfoVo> selectNftInfoMintCompletedList(NftInfoVo nftInfoVo) throws Exception;
	
	// NFT 정보 조회
	public NftInfoVo selectNftInfo(NftInfoVo nftInfoVo) throws Exception;
	
	// NFT 정보 조회 랜덤
	public NftInfoVo selectNftInfoRamdonSeq(NftInfoVo nftInfoVo) throws Exception;
	
	// NFT 정보 조회 (에어드랍용)
	public NftInfoVo selectNftInfoById(NftInfoVo nftInfoVo) throws Exception;
	
	// NFT 남은 수량
	public int selectNftInfoRemainCount(NftInfoVo nftInfoVo) throws Exception;
	
//	// NFT 정보 등록
//	public int insertNftInfo(NftInfoVo nftInfoVo) throws Exception;
//	
//	// NFT 정보 수정
//	public int updateNftInfo(NftInfoVo nftInfoVo) throws Exception;
	
	// NFT 정보 상태 처리중으로 수정
	public int updateNftInfoStatusProcessing(NftInfoVo nftInfoVo) throws Exception;
	
	// NFT 정보 상태 수정
	public int updateNftInfoStatus(NftInfoVo nftInfoVo) throws Exception;
	
//	// NFT 정보 삭제
//	public int deleteNftInfo(NftInfoVo nftInfoVo) throws Exception;
	
	// ME NFT 팔린 개수 확인
//	public int countMeNftType(NftInfoVo nftInfoVo) throws Exception;

}
