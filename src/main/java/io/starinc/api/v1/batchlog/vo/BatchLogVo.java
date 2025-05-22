package io.starinc.api.v1.batchlog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MineLogVo extends CommonVo {
public class BatchLogVo {
	private int seq;
	private String status_cd;
	private String message;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
