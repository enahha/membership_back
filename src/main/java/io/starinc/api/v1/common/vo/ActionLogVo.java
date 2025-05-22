package io.starinc.api.v1.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ActionLogVo {
	
	private String seq;
	private String req_url;
	private String query_string;
	private String params;
	private String client_ip;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
	
}
