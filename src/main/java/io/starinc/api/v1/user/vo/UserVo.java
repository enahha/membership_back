package io.starinc.api.v1.user.vo;

import io.starinc.api.v1.common.vo.CommonVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserVo extends CommonVo {
	
	private String col1;
	private String col2;
	private String col3;
	
//	private String ranking;
	
//	private String seq;
	private String uid;
	private String wallet_address;
	private String wallet_type;
//	private String sid;
//	private String api_sid;
//	private String store;
	private String pwd;
	private String pwd2; // 암호화 패스와드
	private String auth_key;
	private String solana_wallet_address;
	private String memetoon_id;
	private String memetoon_pwd;
	private String name;
	private String nickname;
	private String ucode;
	private String birth;
	private String gender;
//	private String email;
	private String nation;
	private String mobile_no;
	private String tel_no;
	private String profile_image;
	private String thumbnail_image;
	private String introduce;
	
	private String id_card_image;
	private String home_address;
	private String home_address_image;
	private String business_registration_no;
	private String business_license_image;
	private String company_address;
	private String company_address_image;
	private String company_tel_no;
	
//	private String homepage;
//	private String facebook;
//	private String instagram;
//	private String twitter;
	private String push_agree_yn;
	private String adcd;
	
//	private String age;
//	private String address1;
//	private String address2;
//	private String address3;
//	
//	private String admin_yn;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
	
//	private String count_contents; // 게시물수
//	private String count_product; // 상품수
//	private String count_circle; // 모임수
//	private String count_follow_from; // 팔로워수
//	private String count_follow_to; // 팔로잉수
//	
//	private String follow_yn; // 팔로우 여부
}
