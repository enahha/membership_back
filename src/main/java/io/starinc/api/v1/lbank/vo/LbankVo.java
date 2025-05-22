package io.starinc.api.v1.lbank.vo;

import io.starinc.api.v1.common.vo.CommonVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LbankVo extends CommonVo {
	
	// private String seq;
	private String symbol;
	private String price;
	
}
