package io.starinc.api.v1.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class KeyValueVo {
	private String cd_key;
	private String cd_value;
	private String del_yn;
	private String reg_id;
	private String mod_id;
}
