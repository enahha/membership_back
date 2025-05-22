package io.starinc.api.v1.nftprice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.nftprice.mapper.NftPriceMapper;
import io.starinc.api.v1.nftprice.vo.NftPriceVo;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class NftPriceController {
	
	@Autowired
	private NftPriceMapper nftPriceMapper;
	
//	/**
//	 * NFT 가격 리스트 마지막 페이지 번호 조회
//	 * 
//	 * @param uid
//	 * @param pageSize
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/nftprice/selectNftPriceListLastPageNum" , method = {RequestMethod.GET})
//	public int selectNftPriceListLastPageNum(@RequestParam String uid, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
//		NftPriceVo nftPriceVo = new NftPriceVo();
//		nftPriceVo.setKeyword(keyword);
//		nftPriceVo.setPageSize(pageSize);
//		return this.nftPriceMapper.selectNftPriceListLastPageNum(nftPriceVo);
//	}
	
	/**
	 * NFT 가격 리스트 조회
	 * 
	 * @param uid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nftprice/selectNftPriceList")
	public List<NftPriceVo> selectNftPriceList(@RequestParam int projectSeq, @RequestParam int nftTypeSeq) throws Exception {
		
		NftPriceVo nftPriceVo = new NftPriceVo();
		nftPriceVo.setProject_seq(projectSeq);
		nftPriceVo.setNft_type_seq(nftTypeSeq);
		
		return this.nftPriceMapper.selectNftPriceList(nftPriceVo);
	}
	
	/**
	 * NFT 가격 조회
	 * 
	 * @param uid
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/nftprice/selectNftPrice")
	public NftPriceVo selectNftPrice(@RequestParam int projectSeq, @RequestParam int nftTypeSeq, @RequestParam String currencySymbol) throws Exception {
		NftPriceVo nftPriceVo = new NftPriceVo();
		nftPriceVo.setProject_seq(projectSeq);
		nftPriceVo.setNft_type_seq(nftTypeSeq);
		nftPriceVo.setCurrency_symbol(currencySymbol);
		return this.nftPriceMapper.selectNftPrice(nftPriceVo);
	}
	
//	/**
//	 * NFT 가격 등록
//	 * 
//	 * @param nftPriceVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/nftprice/insertNftPrice")
//	public CommonResultVo insertNftPrice(@RequestBody NftPriceVo nftPriceVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			int resultCount = this.nftPriceMapper.insertNftPrice(nftPriceVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("insertNftPrice failed.");
//				commonResultVo.setReturnCd("1");
//				return commonResultVo;
//			}
//			// insert 후 자동생성된 seq 값 반환 (update시 필요하기 때문에 front에 저장)
//			commonResultVo.setReturnValue(nftPriceVo);
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
//	 * NFT 가격 수정
//	 * 
//	 * @param nftPriceVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@PostMapping("/nftprice/updateNftPrice")
//	public CommonResultVo updateNftPrice(@RequestBody NftPriceVo nftPriceVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			int resultCount = this.nftPriceMapper.updateNftPrice(nftPriceVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("updateNftPrice failed.");
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
//	 * NFT 가격 삭제
//	 * 
//	 * @param nftPriceVo
//	 * @return commonVo
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/nftprice/deleteNftPrice" , method = {RequestMethod.POST})
//	public CommonResultVo deleteNftPrice(@RequestBody NftPriceVo nftPriceVo) throws Exception {
//		CommonResultVo commonResultVo = new CommonResultVo();
//		commonResultVo.setResultCd("SUCCESS");
//		commonResultVo.setReturnCd("0");
//		
//		try {
//			nftPriceVo.setDel_id(nftPriceVo.getUid());
//			int resultCount = this.nftPriceMapper.deleteNftPrice(nftPriceVo);
//			if (resultCount == 0) {
//				commonResultVo.setResultCd("FAIL"); // 비정상처리
//				commonResultVo.setResultMsg("deleteNftPrice failed.");
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
