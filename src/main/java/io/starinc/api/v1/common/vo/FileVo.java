package io.starinc.api.v1.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FileVo extends CommonVo {
	private int seq;
	private int seq_file_mst;
	private String name;
	private String name_original;
	private String dir_path;
	private String url_path;
	private String size;
	private String extension;
	private String content_type;
	private String user_order;
}
