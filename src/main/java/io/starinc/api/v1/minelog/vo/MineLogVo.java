package io.starinc.api.v1.minelog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MineLogVo extends CommonVo {
public class MineLogVo {
	private int seq;
	private int project_seq;
	private String nft_id;
	private String mine_type;
	private String daily_price;
	private String amount;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
