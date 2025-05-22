package io.starinc.api.v1.snapshot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.snapshot.vo.SnapshotVo;

public interface SnapshotMapper {
	// 스냅샷 리스트 조회
	public int selectSnapshotListLastPageNum(CommonVo commonVo) throws Exception;
	public List<SnapshotVo> selectSnapshotList(CommonVo commonVo) throws Exception;
	
	// 스냅샷 mst 등록
	public int insertSnapshotMst(SnapshotVo snapshotVo) throws Exception;

	// 스냅샷 등록(리스트)
	public int insertSnapshotList(@Param("list") List<SnapshotVo> snapshotList) throws Exception;
	
	// 스냅샷 mst 리스트 조회
	public List<SnapshotVo> selectSnapshotMstList() throws Exception;
}
