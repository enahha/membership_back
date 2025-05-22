package io.starinc.api.v1.minelog.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.minelog.mapper.MineLogMapper;
import io.starinc.api.v1.minelog.vo.MineLogParamVo;
import io.starinc.api.v1.minelog.vo.PendingRewardsVo;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class MineLogController {
	
	@Autowired
	private MineLogMapper mineLogMapper;
	
	/**
	 * 마이페이지 채굴량 조회
	 * 
	 * @param List<NftInfoVo> nftList
	 * @return List<PendingRewardsVo>
	 * @throws Exception
	 */
	@PostMapping("/minelog/selectMineLogPendingRewardsList")
	public List<PendingRewardsVo> selectMineLogpendingRewardsList(@RequestBody MineLogParamVo mineLogParamVo) throws Exception {
		// System.out.println("            @#@#@#@# selectMineLogpendingRewardsList start @#@#@#@##");
//		for (int i = 0; i < nftList.(); i++) {
//			NftInfoVo nftInfoVo = nftList.get(i);
//			System.out.println(nftInfoVo + "            @#@#@#@# nftInfoVo");
//		}
		
		// 파라미터가 없을 경우 리턴
		if (mineLogParamVo == null || mineLogParamVo.getNftInfoList() == null || mineLogParamVo.getNftInfoList().size() == 0) {
			return null;
		}
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("nftInfoList", mineLogParamVo.getNftInfoList());
		
		////////////////////////////////////////// 테스트 코드 START
//		ArrayList<PendingRewardsVo> pendingRewardsList = new ArrayList<PendingRewardsVo>();
//		PendingRewardsVo pendingRewardsVo = new PendingRewardsVo();
//		pendingRewardsVo.setMine_type("WTEC");
//		pendingRewardsVo.setAmount("123.456789");
//		
//		pendingRewardsList.add(pendingRewardsVo);
//		
//		
//		pendingRewardsVo = new PendingRewardsVo();
//		pendingRewardsVo.setMine_type("BTC");
//		pendingRewardsVo.setAmount("789");
//		
//		pendingRewardsList.add(pendingRewardsVo);
		//////////////////////////////////////////테스트 코드 END
				
		return this.mineLogMapper.selectMineLogPendingRewardsList(hashMap);
		// return pendingRewardsList;
	}
	
	/**
	 * 마이페이지 NFT 타입별 채굴량 조회
	 * 
	 * @param List<NftInfoVo> nftList
	 * @return List<PendingRewardsVo>
	 * @throws Exception
	 */
	@PostMapping("/minelog/selectMineLogNftTypePendingRewardsList")
	public List<PendingRewardsVo> selectMineLogNftTypePendingRewardsList(@RequestBody MineLogParamVo mineLogParamVo) throws Exception {
		// 파라미터가 없을 경우 리턴
		if (mineLogParamVo == null || mineLogParamVo.getNftInfoList() == null || mineLogParamVo.getNftInfoList().size() == 0) {
			return null;
		}
		
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("nftInfoList", mineLogParamVo.getNftInfoList());
		
		return this.mineLogMapper.selectMineLogNftTypePendingRewardsList(hashMap);
		// return pendingRewardsList;
	}
}
