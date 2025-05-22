package io.starinc.api.v1.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ContractCreateAirdropVo {
	
	private String token_contract_address;
	private String pool_contract_address;
}
