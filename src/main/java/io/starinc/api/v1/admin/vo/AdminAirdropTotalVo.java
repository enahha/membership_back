package io.starinc.api.v1.admin.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AdminAirdropTotalVo {
	private String reg_date;
	private int count;
}
