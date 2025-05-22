package io.starinc.api.v1.common.vo;

import java.util.List;

import lombok.Data;

@Data
public class ResponseVo {
	private String result_cd;
	private String result_msg;
	private int result_cnt;
	private List<?> result_list;
	private Object result_obj;
	private String result_key;
}
