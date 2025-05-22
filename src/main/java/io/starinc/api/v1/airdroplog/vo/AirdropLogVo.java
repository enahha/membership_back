package io.starinc.api.v1.airdroplog.vo;

import java.util.List;

import io.starinc.api.v1.common.vo.CommonVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AirdropLogVo extends CommonVo {
	private int seq;
	private String status;
	private String old_project_id;
	private String old_wallet_address;
	private String old_wallet_type;
	private String old_nft_contract;
	private String old_nft_id;
	private String old_nft_name;
	private String old_nft_image;
	private String new_wallet_address;
	private String new_nft_contract;
	private String new_nft_id;
	private String new_mint_account_key;
	private String new_mint_signature;
	private String new_token_account_key;
	private String new_metadata_account_key;
	private String new_master_edition_account_key;
	
    private List<String> tokenIdList;

}
