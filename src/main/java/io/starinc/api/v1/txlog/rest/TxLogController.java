package io.starinc.api.v1.txlog.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.txlog.mapper.TxLogMapper;
import io.starinc.api.v1.txlog.vo.TxLogVo;

@RestController
@RequestMapping(value = "/api")
public class TxLogController {
	
	@Autowired
	private TxLogMapper txLogMapper;
	
	/**
	 * txLog 등록
	 * 
	 * @param txLogVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/txlog/insertTxLog")
	public CommonResultVo insertTxLog(@RequestBody TxLogVo txLogVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		try {
			txLogVo.setReg_id(txLogVo.getReg_id());
			int resultCount = this.txLogMapper.insertTxLog(txLogVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("insertMintingLog failed.");
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
}
