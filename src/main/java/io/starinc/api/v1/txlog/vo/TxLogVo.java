package io.starinc.api.v1.txlog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TxLogVo {
	private int seq;
	private String tx_id;
	private String tx_type;
	private String sender;
	private String receiver;
	private String amount;
	private String tx_status;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
