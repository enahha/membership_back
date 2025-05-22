package io.starinc.api.v1.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ContractCreateTokenVo {
	
	private String token_name;
	private String token_symbol;
	private int token_decimals;
	private String token_initial_supply;
	private String token_to_address;
}
