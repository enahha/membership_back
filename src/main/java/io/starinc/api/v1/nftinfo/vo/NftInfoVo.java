package io.starinc.api.v1.nftinfo.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class NftInfoVo extends CommonVo {
public class NftInfoVo {
	private int seq;
	private int project_seq;
	private String nft_id;
	private String nft_name;
	private String nft_type;
	private String status;
	private String image_file_name;
	private String image_file_extension;
	private String image_file_url;
	private String image_file_name_original;
	private String json_file_name;
	private String json_file_extension;
	private String json_file_url;
	private String json_file_name_original;
	private String reveal_json_file_name;
	private String reveal_json_file_extension;
	private String reveal_json_file_url;
	private String reveal_json_file_name_original;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
	
	// 민팅 대상 NFT를 조회하기 위한 nft_type에서 필터링 조건 - 조회 쿼리의 where절에 설정할 조건
	private String nft_id_start;
	private String nft_id_end;
	
}
