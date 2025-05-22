package io.starinc.api.v1.project.mapper;

import java.util.List;

import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.project.vo.ProjectVo;

public interface ProjectMapper {

	// 프로젝트 리스트 조회
	public int selectProjectListLastPageNum(CommonVo commonVo) throws Exception;
	public List<ProjectVo> selectProjectList(CommonVo commonVo) throws Exception;
	
	// 프로젝트 등록
	public int insertProject(ProjectVo projectVo) throws Exception;
	
	// 프로젝트 조회
	public ProjectVo selectProject(ProjectVo projectVo) throws Exception;
	
	// 프로젝트 수정
	public int updateProject(ProjectVo projectVo) throws Exception;
	
	// 프로젝트 삭제
	public int deleteProject(ProjectVo projectVo) throws Exception;
	
	// 솔라나 collection key 업데이트
	public int updateSolCollectionKey(ProjectVo projectVo) throws Exception;
}
