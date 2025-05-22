package io.starinc.api.v1.verifyedaddress.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.verifyedaddress.mapper.VerifyedAddressMapper;
import io.starinc.api.v1.verifyedaddress.vo.VerifyedAddressVo;

//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class VerifyedAddressController {

	@Autowired
	VerifyedAddressMapper verifyedAddressMapper;
	
	public String insertVerifyedAddress(String walletAddress) throws Exception {
		VerifyedAddressVo verifyedAddressVo = new VerifyedAddressVo();
		String authKey = UUID.randomUUID().toString();
		verifyedAddressVo.setWallet_address(walletAddress);
		verifyedAddressVo.setUuid(authKey);
		verifyedAddressVo.setReg_id(walletAddress);
		
		int insertresultCount = this.verifyedAddressMapper.insertVerifyedAddress(verifyedAddressVo);
		if (insertresultCount  == 1) {
			verifyedAddressVo = this.verifyedAddressMapper.selectLastSeqAndUuidByAddress(verifyedAddressVo);
		}
		
		String uuid = verifyedAddressVo.getUuid();

		return uuid;
	}
}