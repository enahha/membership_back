package io.starinc.api.v1.admin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.admin.mapper.AdminUserMapper;
import io.starinc.api.v1.common.util.CommonUtil;
import io.starinc.api.v1.user.vo.UserVo;


//@ApiIgnore
@RestController
//@Transactional
@RequestMapping(value = "/api")
public class AdminUserController {
	
	// private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	
//	/**
//	 * 사용자 조회
//	 * 
//	 * @param userVo
//	 * @return userVo
//	 * @throws Exception
//	 */
//	@PostMapping("/admin/selectAdminUser")
//	public UserVo selectAdminUser(@RequestBody UserVo userVo) throws Exception {
//		// System.out.println("selectUser called");
//		userVo = this.adminUserMapper.selectAdminUser(userVo);
//		if (userVo != null) {
//			userVo.setPwd(null);
//		}
//		return userVo;
//	}
	
	/**
	 * 사용자 리스트 마지막 페이지 번호 조회
	 * 
	 * @param uid
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/selectAdminUserListLastPageNum")
	public int selectAdminUserListLastPageNum(@RequestParam String uid, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
		UserVo userVo = new UserVo();
		userVo.setKeyword(keyword);
		userVo.setPageSize(pageSize);
		// userVo.setUid(uid);
		// userVo.setCreate_status_cd(Constant.TOKEN_CREATE_STATUS_CD_CREATED);
		return this.adminUserMapper.selectAdminUserListLastPageNum(userVo);
	}
	
	/**
	 * 사용자 리스트 조회
	 * 
	 * @param uid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/selectAdminUserList")
	public List<UserVo> selectAdminUserList(@RequestParam String uid, @RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
		// 페이징 처리
		UserVo userVo = new UserVo();
		userVo.setKeyword(keyword);
		userVo.setStartRow(CommonUtil.pagingStartRow(pageNum, pageSize));
		userVo.setEndRow(CommonUtil.pagingEndRow(pageNum, pageSize));
		// userVo.setUid(uid);
		// userVo.setCreate_status_cd(Constant.TOKEN_CREATE_STATUS_CD_CREATED);
		
		// List<MintingVo> resultList = this.adminMintingMapper.selectAdminMintingWithdrawList(mintingVo);
		return this.adminUserMapper.selectAdminUserList(userVo);
	}
}
