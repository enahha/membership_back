package io.starinc.api.v1.common.vo;

import lombok.Data;

@Data
public class CommonVo {
	private String keyword;
//	private int rownum;
//	private int coin;
//	
//	private String filterType; // 필터링 타입
//	
//	private String seq;
	
//	private String seq_circle;
//	private String seq_product;
	private String uid;
//	private String sid;
//	private String name;
//	private String nickname;
	private String reg_id;
	private String reg_time;
	private String mod_id;
	private String mod_time;
	private String del_id;
	private String del_time;
	
//	private List<?> resultList;
	private String resultCd;
	private String resultMsg;
	
//	private int count;
//	private String uid_reward_to; // 내게 보상을 줄 유저들 검색시 사용
//	
//	private String endDate; // 이벤트 종료일
//	
//	private String targetYear; // 원더배당 리스트 조회 대상 연도
//	
	/** 페이지 넘버 */
	private int pageNum;
	/** 페이지 사이즈 */
	private int pageSize;
	/** 시작행 */
	private int startRow;
	/** 종료행 */
	private int endRow;
}
