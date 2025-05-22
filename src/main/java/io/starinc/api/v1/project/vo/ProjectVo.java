package io.starinc.api.v1.project.vo;

import io.starinc.api.v1.common.vo.CommonVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProjectVo extends CommonVo {
	private int seq;
	private String project_id;
	private String project_symbol;
	private String project_name;
	private String project_name_ko;
	private String mainnet;
	private String contract_address;
	private String collection_mint_address;
	private String collection_master_edition_address;
	private String collection_metadata_address;
	private String json_path;
	private String admin_wallet_address;
	private String listing_wallet_address;
	private String fee_wallet_address;
	private String listing_wallet_secret_key;
	private String fee_wallet_secret_key;
	private String admin_wallet_secret_key;	
	private String image_url;
	private String description;
	private String description_ko;
	private String mint_start_time;
	private String mint_end_time;
	private String mint_start_block;
	private String mint_end_block;
	private int creator_fee;
}
