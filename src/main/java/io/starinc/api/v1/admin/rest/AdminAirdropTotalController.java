package io.starinc.api.v1.admin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.admin.mapper.AdminAirdropTotalMapper;
import io.starinc.api.v1.admin.vo.AdminAirdropTotalVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class AdminAirdropTotalController {
	
	// private static final Logger logger = LoggerFactory.getLogger(AdminTotalController.class);
	
	@Autowired
	private AdminAirdropTotalMapper adminAirdropTotalMapper;
	
	/**
	 * 에어드랍 신청 총 건수 및 데일리 건수 조회
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/selectAdminAirdropTotalList")
	public List<AdminAirdropTotalVo> selectAdminAirdropTotalList(@RequestParam String uid) throws Exception {
		return this.adminAirdropTotalMapper.selectAdminAirdropTotalList();
	}
}
