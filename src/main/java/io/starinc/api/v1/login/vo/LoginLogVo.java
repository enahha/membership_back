package io.starinc.api.v1.login.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginLogVo {
	private String uid;
	private String log_cd;
	private String client_ip;
	private String reg_id;
}
