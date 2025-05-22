package io.starinc.api.v1.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ContractResultVo {
	
	private String resultCd;
	private String resultMsg;
	private String contractAddress;
	private String txHash;
	
}
