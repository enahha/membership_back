package io.starinc.api.v1.nfttype.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.nfttype.mapper.NftTypeMapper;
import io.starinc.api.v1.nfttype.vo.NftTypeVo;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class NftTypeController {
	
	@Autowired
	private NftTypeMapper nftTypeMapper;
	
//	/**
//	 * NFT 타입 리스트 마지막 페이지 번호 조회
//	 * 
//	 * @param uid
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/nfttype/selectNftTypeListLastPageNum" , method = {RequestMethod.GET})
//	public int selectNftTypeListLastPageNum(@RequestParam String uid, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		NftTypeVo nftTypeVo = new NftTypeVo();
//		nftTypeVo.setKeyword(keyword);
//		nftTypeVo.setPageSize(pageSize);
//		return this.nftTypeMapper.selectNftTypeListLastPageNum(nftTypeVo);
//	}
	
	/**
	 * NFT 타입 리스트 조회
	 * 
	 * @param nftTypeVo
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nfttype/selectNftTypeList")
	public List<NftTypeVo> selectNftTypeList(@RequestParam int projectSeq) throws Exception {
		NftTypeVo nftTypeVo = new NftTypeVo();
		nftTypeVo.setProject_seq(projectSeq);
		return this.nftTypeMapper.selectNftTypeList(nftTypeVo);
	}
	
	/**
	 * NFT 타입 및 가격 리스트 조회
	 * 
	 * @param nftTypeVo
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nfttype/selectNftTypeAndPriceList")
	public List<NftTypeVo> selectNftTypeAndPriceList(@RequestParam int projectSeq) throws Exception {
		return this.nftTypeMapper.selectNftTypeAndPriceList(projectSeq);
	}
	
	/**
	 * NFT 타입 조회
	 * 
	 * @param project_seq
	 * @param nft_type
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nfttype/selectNftType")
	public NftTypeVo selectNftType(@RequestParam int projectSeq, @RequestParam String nftType) throws Exception {
		NftTypeVo nftTypeVo = new NftTypeVo();
		nftTypeVo.setProject_seq(projectSeq);
		nftTypeVo.setNft_type(nftType);
		return this.nftTypeMapper.selectNftType(nftTypeVo);
	}
	
//	/**
//	 * NFT 타입과 타입별 가격 조회
//	 * 
//	 * @param project_seq
//	 * @return List<NftTypeVo>
//	 * @throws Exception
//	 */
//	@GetMapping("/nfttype/selectNftTypeAndPriceByProjectSeq")
//	public List<NftTypeVo> selectNftTypeAndPriceByProjectSeq(@RequestParam int projectSeq) throws Exception {
//		NftTypeVo nftTypeVo = new NftTypeVo();
//		nftTypeVo.setProject_seq(projectSeq);
//		return this.nftTypeMapper.selectNftTypeAndPriceByProjectSeq(nftTypeVo);
//	}
	
//	/**
//	 * NFT 타입 등록
//	 * 
//	 * @param nftTypeVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/nfttype/insertNftType")
//	public CommonResultVo insertNftType(@RequestBody NftTypeVo nftTypeVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			int resultCount = this.nftTypeMapper.insertNftType(nftTypeVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("insertNftType failed.");
//				commonResultVo.setReturnCd("1");
//				return commonResultVo;
//			}
//			// insert 후 자동생성된 seq 값 반환 (update시 필요하기 때문에 front에 저장)
//			commonResultVo.setReturnValue(nftTypeVo);
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
//	 * NFT 타입 수정
//	 * 
//	 * @param nftTypeVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/nfttype/updateNftType")
//	public CommonResultVo updateNftType(@RequestBody NftTypeVo nftTypeVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			int resultCount = this.nftTypeMapper.updateNftType(nftTypeVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("updateNftType failed.");
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
	 * NFT 타입 남은갯수 수정
	 * 
	 * @param nftTypeVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/nfttype/updateNftTypeRemainCount")
	public CommonResultVo updateNftTypeRemainCount(@RequestBody NftTypeVo nftTypeVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			int resultCount = this.nftTypeMapper.updateNftTypeRemainCount(nftTypeVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("updateNftTypeRemainCount failed.");
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
//	 * NFT 타입 삭제
//	 * 
//	 * @param nftTypeVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/nfttype/deleteNftType" , method = {RequestMethod.POST})
//	public CommonResultVo deleteNftType(@RequestBody NftTypeVo nftTypeVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			nftTypeVo.setDel_id(nftTypeVo.getUid());
//			int resultCount = this.nftTypeMapper.deleteNftType(nftTypeVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("deleteNftType failed.");
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
}
