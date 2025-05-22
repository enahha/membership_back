package io.starinc.api.v1.exchangerate.vo;

import io.starinc.api.v1.common.vo.CommonVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ExchangeRateVo extends CommonVo {
	
	private String seq;
	private String search_date;
	private String deal_bas_r;
	
}
