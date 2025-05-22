package io.starinc.api.v1.minelog.vo;

import java.util.List;

import io.starinc.api.v1.nftinfo.vo.NftInfoVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
// public class MineLogVo extends CommonVo {
public class MineLogParamVo {
	private List<NftInfoVo> nftInfoList;
}
