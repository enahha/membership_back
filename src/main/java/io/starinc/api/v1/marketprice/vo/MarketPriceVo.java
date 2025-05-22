package io.starinc.api.v1.marketprice.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MarketPriceVo extends CommonVo {
public class MarketPriceVo {
	private int seq;
	private String date;
	private String currency_symbol;
	private String daily_price;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
