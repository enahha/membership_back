package io.starinc.api.v1.project.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.util.CommonUtil;
import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.project.mapper.ProjectMapper;
import io.starinc.api.v1.project.vo.ProjectVo;


@RestController
@Transactional
@RequestMapping(value = "/api")
public class ProjectController {
	
	@Autowired
	private ProjectMapper projectMapper;
	
	/**
	 * 프로젝트 리스트 마지막 페이지 번호 조회
	 * 
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/project/selectProjectListLastPageNum")
	public int selectProjectListLastPageNum(@RequestParam int pageSize, @RequestParam String keyword) throws Exception {
		ProjectVo projectVo = new ProjectVo();
		projectVo.setKeyword(keyword);
		projectVo.setPageSize(pageSize);
		return this.projectMapper.selectProjectListLastPageNum(projectVo);
	}
	
	/**
	 * 프로젝트 리스트 조회
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/project/selectProjectList")
	public List<ProjectVo> selectProjectList(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
		// 페이징 처리
		ProjectVo projectVo = new ProjectVo();
		projectVo.setKeyword(keyword);
		projectVo.setStartRow(CommonUtil.pagingStartRow(pageNum, pageSize));
		projectVo.setEndRow(CommonUtil.pagingEndRow(pageNum, pageSize));
		return this.projectMapper.selectProjectList(projectVo);
	}
	
	/**
	 * 프로젝트 조회
	 * 
	 * @param seq
	 * @return ProjectVo
	 * @throws Exception
	 */
	@GetMapping("/project/selectProject")
	public ProjectVo selectProject(@RequestParam int seq) throws Exception {
		ProjectVo projectVo = new ProjectVo();
		projectVo.setSeq(seq);
		return this.projectMapper.selectProject(projectVo);
	}
	
//	/**
//	 * old_project_id로 프로젝트 조회
//	 * 
//	 * @param seq
//	 * @return
//	 * @throws Exception
//	 */
//	@GetMapping("/project/selectProjectByOldProjectId")
//	public ProjectVo selectProjectByOldProjectId(@RequestParam String oldProjectId) throws Exception {
//		ProjectVo projectVo = new ProjectVo();
//		projectVo.setId(oldProjectId);
//		return this.projectMapper.selectProjectByOldProjectId(projectVo);
//	}
	
	/**
	 * 프로젝트 등록
	 * 
	 * @param projectVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/project/insertProject")
	public CommonVo insertProject(@RequestBody ProjectVo projectVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
			projectVo.setReg_id("No-User");
			int resultCount = this.projectMapper.insertProject(projectVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("insertProject failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	/**
	 * 프로젝트 수정
	 * 
	 * @param projectVo
	 * @return commonVo  
	 * @throws Exception
	 */
	@PostMapping("/project/updateProject")
	public CommonVo updateProject(@RequestBody ProjectVo projectVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
			projectVo.setMod_id("admin");
			int resultCount = this.projectMapper.updateProject(projectVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("updateProject failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	/**
	 * 프로젝트 삭제
	 * 
	 * @param projectVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/project/deleteProject")
	public CommonVo deleteProject(@RequestBody ProjectVo projectVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
				
		try {
			projectVo.setDel_id("admin");
			int resultCount = this.projectMapper.deleteProject(projectVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("deleteProject failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	/**
	 * 프로젝트 new Contract Address 수정
	 * 
	 * @param projectVo
	 * @return commonVo  
	 * @throws Exception
	 */
	@PostMapping("/project/updateSolCollectionKey")
	public CommonVo updateSolCollectionKey(@RequestBody ProjectVo projectVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
			projectVo.setMod_id("admin");
			int resultCount = this.projectMapper.updateSolCollectionKey(projectVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("updateProject failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			e.printStackTrace();
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
}
