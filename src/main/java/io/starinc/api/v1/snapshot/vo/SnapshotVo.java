package io.starinc.api.v1.snapshot.vo;

import io.starinc.api.v1.common.vo.CommonVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SnapshotVo extends CommonVo {
	public int seq;
	public int mst_seq;
	public int result_number;
	private String owner_address;
	private String nft_id;
	private String nft_name;
	private String rarity;
	private String amount;
	private String json_uri;
	
	private String mainnet;
	private String collection_name;
	private String collection_address;
}
