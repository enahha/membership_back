package io.starinc.api.v1.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SelectVo {
	private String label;
	private String value;
}
