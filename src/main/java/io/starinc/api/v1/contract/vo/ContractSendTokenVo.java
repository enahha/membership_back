package io.starinc.api.v1.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ContractSendTokenVo {
	
	private String address_from;
	private String address_to;
	private String amount;
	
	private String resultCd;
	private String resultMsg;
}
