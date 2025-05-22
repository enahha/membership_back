package io.starinc.api.v1.common.vo;

import lombok.Data;

@Data
public class CommonResultVo {
	private String resultCd;
	private String resultMsg;
	private String returnCd;
	private Object returnValue;
}
