package io.starinc.api.v1.mintplan.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MigrationLogVo extends CommonVo {
public class MintPlanVo {
	private int seq;
	private int project_seq;
	private int payment_seq;
	private String status;
	private String wallet_address_to;
	private String wallet_type_to;
	private String currency_symbol;
	private String nft_type;
	private String nft_id;
	private String json_file_url;
	private String tx_id;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
