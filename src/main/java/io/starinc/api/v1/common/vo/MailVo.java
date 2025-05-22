package io.starinc.api.v1.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MailVo {
	private String subject;
	private String text;
	private String email_from;
	private String email_to;
}
