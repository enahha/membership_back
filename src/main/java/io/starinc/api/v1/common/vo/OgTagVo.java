package io.starinc.api.v1.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OgTagVo {
	private String ogTitle;
	private String ogDescription;
	private String ogImage;
}
