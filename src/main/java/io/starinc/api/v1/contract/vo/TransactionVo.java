package io.starinc.api.v1.contract.vo;

import io.starinc.api.v1.common.vo.CommonVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TransactionVo extends CommonVo {
	
	private int seq;
	private String tx_id;
	private String tx_type_cd;
	private String contract_address;
	private int token_seq;
//	private String token_name;
//	private String token_symbol;
//	private int token_decimals;
//	private String token_total_supply;
//	private String token_logo_image;
	private String token_contract_address;
}
