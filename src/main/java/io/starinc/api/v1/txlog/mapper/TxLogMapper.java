package io.starinc.api.v1.txlog.mapper;

import io.starinc.api.v1.txlog.vo.TxLogVo;

public interface TxLogMapper {
	// 트랜잭션 로그 등록
	public int insertTxLog(TxLogVo txLogVo) throws Exception;
	
	// 트랜잭션 로그 조회
	public TxLogVo selectTxLog(TxLogVo txLogVo) throws Exception;
	
//	// 트랜잭션 로그 수정
//	public int updateTxLog(TxLogVo txLogVo) throws Exception;
}
