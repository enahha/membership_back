package io.starinc.api.v1.admin.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AdminLoginVo {
	private String uid;
	private String pwd;
}
