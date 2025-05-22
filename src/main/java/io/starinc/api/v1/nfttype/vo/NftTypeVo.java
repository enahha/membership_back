package io.starinc.api.v1.nfttype.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class NftTypeVo extends CommonVo {
public class NftTypeVo {
	private int seq;
	private int project_seq;
	private int order_no;
	private String nft_type;
	private String nft_type_name;
	private String nft_type_name_ko;
	private String nft_id_start;
	private String nft_id_end;
	private int amount_supply;
	private int amount_remain;
	private String image_url;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
	
	// nft_price 컬럼
	private int nft_price_seq;
	private int nft_type_seq;
	private int nft_price_order_no;
	private String currency_symbol;
	private String currency_name;
	private String currency_contract;
	private int currency_decimal;
	private String currency_logo_image;
	private int is_usd_value;
	private int price_usd;
	private int price_currency;
}
