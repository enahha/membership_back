package io.starinc.api.v1.nftprice.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class NftPriceVo extends CommonVo {
public class NftPriceVo {
	private int seq;
	private int project_seq;
	private int nft_type_seq;
	private int order_no;
	private String currency_symbol;
	private String currency_name;
	private String currency_contract;
	private int currency_decimal; // 통화 소수점 자릿수 - solana는 소숫점 자릿수가 9 이므로 1 solana는 1.000000000 이므로 1000000000 로 계산되어야 함. 0.000000001 solana는 1로 계산됨.
	private String currency_logo_image;
	private int is_usd_value;
	private int price_usd;
	private int price_currency;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
