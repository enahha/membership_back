package io.starinc.api.v1.reservation.mapper;

import java.util.List;

import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.reservation.vo.ReservationVo;

public interface ReservationMapper {
	// 예약 리스트 조회
	public int selectReservationListLastPageNum(CommonVo commonVo) throws Exception;
	public List<ReservationVo> selectReservationList(CommonVo commonVo) throws Exception;
	
	// 예약 등록
	public int insertReservation(ReservationVo reservationVo) throws Exception;
	
	// 예약 전체 조회(지갑 주소 별)
	public List<ReservationVo> selectReservationByWalletAddress(ReservationVo reservationVo) throws Exception;
	
	// 예약 단일 조회(seq) 
	public ReservationVo selectReservationBySeq(ReservationVo reservationVo) throws Exception;
	
	// 예약 수정
	public int updateReservation(ReservationVo reservationVo) throws Exception;
	
	// 예약 status 수정
	public int updateReservationStatus(ReservationVo reservationVo) throws Exception;
	
	// 예약 삭제
	public int deleteReservation(ReservationVo reservationVo) throws Exception;

}
