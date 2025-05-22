package io.starinc.api.v1.login.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class KakaoVo {
	
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	private String refresh_token_expires_in;
}
