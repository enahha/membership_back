package io.starinc.api.v1.login.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FindVo {

	// private String findTarget;
	// private String name;
	private String email;

	// private String uid;
}
