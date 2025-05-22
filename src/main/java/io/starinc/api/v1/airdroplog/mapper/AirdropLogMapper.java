package io.starinc.api.v1.airdroplog.mapper;

import java.util.List;

import io.starinc.api.v1.airdroplog.vo.AirdropLogVo;
import io.starinc.api.v1.common.vo.CommonVo;

public interface AirdropLogMapper {
	
	// 에어드랍 로그 갯수 조회
	public int selectAirdropLogCount() throws Exception;
	
	// 에어드랍 로그 리스트 조회
	public int selectAirdropLogListLastPageNum(CommonVo commonVo) throws Exception;
	public List<AirdropLogVo> selectAirdropLogList(CommonVo commonVo) throws Exception;
	
//	// 에어드랍 로그 조회
//	public AirdropLogVo selectAirdropLog(AirdropLogVo noticeVo) throws Exception;
	
	// 에어드랍 로그 조회
	public AirdropLogVo selectAirdropLog(AirdropLogVo noticeVo) throws Exception;
	
	// 중복된 nft_id 리스트 조회
	List<String> findDuplicateNftIdList(List<String> tokenIdList);
	
	// 에어드랍 로그 등록
	public int insertAirdropLog(AirdropLogVo noticeVo) throws Exception;
	
	// 에어드랍 로그 등록(리스트)
	public int insertAirdropLogList(List<AirdropLogVo> airdropLogList) throws Exception;
	
	// 에어드랍 로그 등록(준비)
	public int insertAirdropLogListForPrepare(List<AirdropLogVo> airdropLogList) throws Exception;
	
	// 에어드랍 로그 수정
	public int updateAirdropLog(AirdropLogVo noticeVo) throws Exception;
	
	// NFT 정보 상태 수정
	public int updateAirdropLogStatus(AirdropLogVo noticeVo) throws Exception;
	
	// NFT 정보 상태, new nft id 수정
	public int updateAirdropLogStatusAndNewNftId(AirdropLogVo noticeVo) throws Exception;
	
	// NFT 시그니처 수정
	public int updateAirdropLogSignature(AirdropLogVo noticeVo) throws Exception;
	
	// NFT 시그니처 수정
	public int updateAirdropLogNftIdRandom() throws Exception;
	
	// 에어드랍 로그 삭제
	public int deleteAirdropLog(AirdropLogVo noticeVo) throws Exception;
}
