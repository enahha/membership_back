package io.starinc.api.v1.mintinglog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MigrationLogVo extends CommonVo {
public class MintingLogVo {
	private int seq;
	private int project_seq;
	private String wallet_address;
	private String wallet_type;
	private String nft_type;
	private String currency_symbol;
	private String nft_id;
	private String mint_account_key;
	private String verify_collection_signature;
	private String create_nft_signature;
	private String token_account_key;
	private String metadata_account_key;
	private String master_edition_account_key;
	private String tx_id;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
