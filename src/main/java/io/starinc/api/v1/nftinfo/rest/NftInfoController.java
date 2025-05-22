package io.starinc.api.v1.nftinfo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.nftinfo.mapper.NftInfoMapper;
import io.starinc.api.v1.nftinfo.vo.NftInfoVo;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class NftInfoController {
	
	@Autowired
	private NftInfoMapper nftInfoMapper;
	
//	/**
//	 * NFT 정보 리스트 마지막 페이지 번호 조회
//	 * 
//	 * @param uid
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/nftinfo/selectNftInfoListLastPageNum" , method = {RequestMethod.GET})
//	public int selectNftInfoListLastPageNum(@RequestParam String uid, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		NftInfoVo nftInfoVo = new NftInfoVo();
//		nftInfoVo.setKeyword(keyword);
//		nftInfoVo.setPageSize(pageSize);
//		return this.nftInfoMapper.selectNftInfoListLastPageNum(nftInfoVo);
//	}
	
//	/**
//	 * NFT 정보 리스트 조회
//	 * 
//	 * @param uid
//	 * @param pageNum
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@GetMapping("/nftinfo/selectNftInfoList")
//	public List<NftInfoVo> selectNftInfoList(@RequestBody NftInfoVo nftInfoVo) throws Exception {
//		return this.nftInfoMapper.selectNftInfoList(nftInfoVo);
//	}
	
	/**
	 * NFT 정보 조회
	 * 
	 * @param uid
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nftinfo/selectNftInfo")
	public NftInfoVo selectNftInfo(@RequestParam int projectSeq, @RequestParam String nftType, @RequestParam String nftIdStart, @RequestParam String nftIdEnd) throws Exception {
		NftInfoVo nftInfoVo = new NftInfoVo();
		nftInfoVo.setProject_seq(projectSeq);
		nftInfoVo.setNft_type(nftType);
		nftInfoVo.setNft_id_start(nftIdStart);
		nftInfoVo.setNft_id_end(nftIdEnd);
		return this.nftInfoMapper.selectNftInfo(nftInfoVo);
	}
	
	
	/**
	 * NFT 정보 조회 랜덤
	 * 
	 * @param uid
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nftinfo/selectNftInfoRamdonSeq")
	public NftInfoVo selectNftInfoRamdonSeq(@RequestParam int projectSeq, @RequestParam String nftType, @RequestParam String nftIdStart, @RequestParam String nftIdEnd) throws Exception {
		NftInfoVo nftInfoVo = new NftInfoVo();
		nftInfoVo.setProject_seq(projectSeq);
		nftInfoVo.setNft_type(nftType);
		nftInfoVo.setNft_id_start(nftIdStart);
		nftInfoVo.setNft_id_end(nftIdEnd);
		return this.nftInfoMapper.selectNftInfoRamdonSeq(nftInfoVo);
	}
	
	/**
	 * 에어드랍용 NFT 정보 조회
	 * 
	 * @param projectSeq
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nftinfo/selectNftInfoById")
	public NftInfoVo selectNftInfoById(@RequestParam int projectSeq, @RequestParam String nftId) throws Exception {
		NftInfoVo nftInfoVo = new NftInfoVo();
		nftInfoVo.setProject_seq(projectSeq);
		nftInfoVo.setNft_id(nftId);
		System.out.println("Debug: ProjectSeq = " + projectSeq + ", NftId = " + nftId); // 디버깅 로그 추가
		return this.nftInfoMapper.selectNftInfoById(nftInfoVo);
	}
	
	/**
	 * NFT 남은 수량
	 * 
	 * @param projectSeq
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nftinfo/selectNftInfoRemainCount")
	public int selectNftInfoRemainCount(@RequestParam int projectSeq) throws Exception {
		NftInfoVo nftInfoVo = new NftInfoVo();
		nftInfoVo.setProject_seq(projectSeq);
		return this.nftInfoMapper.selectNftInfoRemainCount(nftInfoVo);
	}
	
//	/**
//	 * NFT 정보 등록
//	 * 
//	 * @param nftInfoVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/nftinfo/insertNftInfo")
//	public CommonResultVo insertNftInfo(@RequestBody NftInfoVo nftInfoVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			int resultCount = this.nftInfoMapper.insertNftInfo(nftInfoVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("insertNftInfo failed.");
//				commonResultVo.setReturnCd("1");
//				return commonResultVo;
//			}
//			// insert 후 자동생성된 seq 값 반환 (update시 필요하기 때문에 front에 저장)
//			commonResultVo.setReturnValue(nftInfoVo);
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonResultVo.setResultCd("FAIL");
//			commonResultVo.setReturnCd("-1");
//			commonResultVo.setResultMsg(e.toString());
//		}
//		return commonResultVo;
//	}
//	
//	/**
//	 * NFT 정보 수정
//	 * 
//	 * @param nftInfoVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/nftinfo/updateNftInfo")
//	public CommonResultVo updateNftInfo(@RequestBody NftInfoVo nftInfoVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			int resultCount = this.nftInfoMapper.updateNftInfo(nftInfoVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("updateNftInfo failed.");
//				commonResultVo.setReturnCd("1");
//				return commonResultVo;
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonResultVo.setResultCd("FAIL");
//			commonResultVo.setReturnCd("-1");
//			commonResultVo.setResultMsg(e.toString());
//		}
//		return commonResultVo;
//	}
	
	/**
	 * NFT 정보 상태 처리중으로 수정
	 * 
	 * @param nftInfoVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/nftinfo/updateNftInfoStatusProcessing")
	public CommonResultVo updateNftInfoStatusProcessing(@RequestBody NftInfoVo nftInfoVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.nftInfoMapper.updateNftInfoStatusProcessing(nftInfoVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updateNftInfoStatusProcessing failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			e.printStackTrace();
			commonResultVo.setResultCd("FAIL");
			commonResultVo.setReturnCd("-1");
			commonResultVo.setResultMsg(e.toString());
		}
		return commonResultVo;
	}
	
	/**
	 * NFT 정보 상태 수정
	 * 
	 * @param nftInfoVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/nftinfo/updateNftInfoStatus")
	public CommonResultVo updateNftInfoStatus(@RequestBody NftInfoVo nftInfoVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.nftInfoMapper.updateNftInfoStatus(nftInfoVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updateNftInfoStatus failed.");
				commonResultVo.setReturnCd("1");
				return commonResultVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			e.printStackTrace();
			commonResultVo.setResultCd("FAIL");
			commonResultVo.setReturnCd("-1");
			commonResultVo.setResultMsg(e.toString());
		}
		return commonResultVo;
	}
	
//	/**
//	 * NFT 정보 삭제
//	 * 
//	 * @param nftInfoVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/nftinfo/deleteNftInfo" , method = {RequestMethod.POST})
//	public CommonResultVo deleteNftInfo(@RequestBody NftInfoVo nftInfoVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			nftInfoVo.setDel_id(nftInfoVo.getUid());
//			int resultCount = this.nftInfoMapper.deleteNftInfo(nftInfoVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("deleteNftInfo failed.");
//				commonResultVo.setReturnCd("1");
//				return commonResultVo;
//			}
//		} catch (Exception e) {
//			// 결과코드 : 실패
//			commonResultVo.setResultCd("FAIL");
//			commonResultVo.setReturnCd("-1");
//			commonResultVo.setResultMsg(e.toString());
//		}
//		return commonResultVo;
//	}
	
	
//	/**
//	 * NFT 민팅 수량 확인
//	 * 
//	 * @param nftInfoVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@GetMapping("/nftinfo/countMeNftType")
//	public int countMeNftType(@RequestParam String nftType) throws Exception {
//		NftInfoVo nftInfoVo = new NftInfoVo();
//		nftInfoVo.setNft_type(nftType);
//		return this.nftInfoMapper.countMeNftType(nftInfoVo);
//	}

}
