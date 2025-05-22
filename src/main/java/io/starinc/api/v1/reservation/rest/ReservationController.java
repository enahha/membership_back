package io.starinc.api.v1.reservation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.util.CommonUtil;
import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.reservation.mapper.ReservationMapper;
import io.starinc.api.v1.reservation.vo.ReservationVo;


@RestController
@Transactional
@RequestMapping(value = "/api")
public class ReservationController {
	
	@Autowired
	private ReservationMapper reservationMapper;
	
	/**
	 * 예약 리스트 마지막 페이지 번호 조회
	 * 
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/reservation/selectReservationListLastPageNum")
	public int selectReservationListLastPageNum(@RequestParam int pageSize, @RequestParam String keyword) throws Exception {
		ReservationVo reservationVo = new ReservationVo();
		reservationVo.setKeyword(keyword);
		reservationVo.setPageSize(pageSize);
		return this.reservationMapper.selectReservationListLastPageNum(reservationVo);
	}
	
	/**
	 * 예약 리스트 조회
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/reservation/selectReservationList")
	public List<ReservationVo> selectReservationList(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String keyword) throws Exception {
		// 페이징 처리
		ReservationVo reservationVo = new ReservationVo();
		reservationVo.setKeyword(keyword);
		reservationVo.setStartRow(CommonUtil.pagingStartRow(pageNum, pageSize));
		reservationVo.setEndRow(CommonUtil.pagingEndRow(pageNum, pageSize));
		return this.reservationMapper.selectReservationList(reservationVo);
	}
	
	
	/**
	 * 예약 전체 조회(지갑 주소 별)
	 * 
	 * @param walletAddress
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/reservation/selectReservationByWalletAddress")
	public List<ReservationVo> selectReservationByWalletAddress(@RequestParam String walletAddress) throws Exception {
		// 페이징 처리
		ReservationVo reservationVo = new ReservationVo();
		reservationVo.setWallet_address(walletAddress);
		return this.reservationMapper.selectReservationByWalletAddress(reservationVo);
	}
	
	/**
	 * 예약 단일 조회(seq) 
	 * 
	 * @param seq
	 * @return ReservationVo
	 * @throws Exception
	 */
	@GetMapping("/reservation/selectReservation")
	public ReservationVo selectReservation(@RequestParam int seq) throws Exception {
		ReservationVo reservationVo = new ReservationVo();
		reservationVo.setSeq(seq);
		return this.reservationMapper.selectReservationBySeq(reservationVo);
	}

	/**
	 * 예약 등록
	 * 
	 * @param reservationVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/reservation/insertReservation")
	public CommonVo insertReservation(@RequestBody ReservationVo reservationVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
			int resultCount = this.reservationMapper.insertReservation(reservationVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("insertReservation failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	/**
	 * 예약 수정
	 * 
	 * @param reservationVo
	 * @return commonVo  
	 * @throws Exception
	 */
	@PostMapping("/reservation/updateReservation")
	public CommonVo updateReservation(@RequestBody ReservationVo reservationVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
//			reservationVo.setMod_id("admin");
			int resultCount = this.reservationMapper.updateReservation(reservationVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("updateReservation failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	/**
	 * 예약 삭제
	 * 
	 * @param reservationVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/reservation/deleteReservation")
	public CommonVo deleteReservation(@RequestBody ReservationVo reservationVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
				
		try {
			reservationVo.setDel_id("admin");
			int resultCount = this.reservationMapper.deleteReservation(reservationVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("deleteReservation failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
	
	/**
	 * 예약 status 수정
	 * 
	 * @param reservationVo
	 * @return commonVo  
	 * @throws Exception
	 */
	@PostMapping("/reservation/updateReservationStatus")
	public CommonVo updateSolCollectionKey(@RequestBody ReservationVo reservationVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
		try {
			reservationVo.setMod_id("admin");
			int resultCount = this.reservationMapper.updateReservationStatus(reservationVo);
			if (resultCount == 0) {
				commonVo.setResultCd("FAIL");
				commonVo.setResultMsg("updateReservationStatus failed.");
				return commonVo;
			}
		} catch (Exception e) {
			// 결과코드 : 실패
			e.printStackTrace();
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
}
