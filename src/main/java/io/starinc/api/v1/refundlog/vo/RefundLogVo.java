package io.starinc.api.v1.refundlog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MigrationLogVo extends CommonVo {
public class RefundLogVo {
	private int seq;
	private int project_seq;
	private String wallet_address;
	private String wallet_type;
	private String nft_type;
	private String mint_amount;
	private String currency_symbol;
	private String currency_contract;
	private String currency_amount;
	private String tx_id;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
