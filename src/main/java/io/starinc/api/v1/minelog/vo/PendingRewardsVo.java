package io.starinc.api.v1.minelog.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MineLogVo extends CommonVo {
public class PendingRewardsVo {
	private String mine_type;
	private String nft_type;
	private String amount;
}
