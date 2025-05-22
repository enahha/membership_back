package io.starinc.api.v1.payment.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MigrationLogVo extends CommonVo {
public class PaymentVo {
	private int seq;
	private int project_seq;
	private String status;
	private String wallet_address_from;
	private String wallet_type_from;
	private String wallet_address_to;
	private String wallet_type_to;
	private String currency_symbol;
	private String currency_amount_total;
	private int quantity_a;
	private int quantity_s;
	private int quantity_r;
	private int quantity_v;
	private int quantity_me100;
	private int quantity_me1500;
	private int quantity_me10000;
	private String price_a;
	private String price_s;
	private String price_r;
	private String price_v;
	private String price_me100;
	private String price_me1500;
	private String price_me10000;
	private String subtotal_a;
	private String subtotal_s;
	private String subtotal_r;
	private String subtotal_v;
	private String subtotal_me100;
	private String subtotal_me1500;
	private String subtotal_me10000;
	private String tx_id;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
}
