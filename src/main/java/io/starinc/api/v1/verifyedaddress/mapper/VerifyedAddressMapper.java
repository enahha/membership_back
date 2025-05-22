package io.starinc.api.v1.verifyedaddress.mapper;

import io.starinc.api.v1.verifyedaddress.vo.VerifyedAddressVo;

public interface VerifyedAddressMapper {
	// address와 uuid로 사용자 확인 
	public int countVerifyedAddress(VerifyedAddressVo verifyedAddressVo) throws Exception;
	
	// address로 마지막 uuid조회
	public VerifyedAddressVo selectLastSeqAndUuidByAddress(VerifyedAddressVo verifyedAddressVo) throws Exception;
	
	// 등록
	public int insertVerifyedAddress(VerifyedAddressVo verifyedAddressVo) throws Exception;
	
}
