package io.starinc.api.v1.login.vo;

import io.starinc.api.v1.common.vo.CommonVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserTempVo extends CommonVo {
	
	private String uid;
	private String pwd;
	private String code;
	
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
