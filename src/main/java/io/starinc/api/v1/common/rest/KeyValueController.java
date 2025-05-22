package io.starinc.api.v1.common.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.common.mapper.KeyValueMapper;
import io.starinc.api.v1.common.vo.CommonResultVo;
import io.starinc.api.v1.common.vo.KeyValueVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class KeyValueController {
	
//	private static final Logger logger = LoggerFactory.getLogger(LoginLogController.class);
	
	@Autowired
	private KeyValueMapper keyValueMapper;
	
	/**
	 * 키밸류 코드 값 조회
	 * 
	 * @param sid
	 * @param cdKey
	 * 
	 * @return commonResultVo
	 * @throws Exception
	 */
	@GetMapping("/common/selectKeyValue")
	public CommonResultVo selectKeyValue(@RequestParam String cdKey) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		commonResultVo.setReturnValue(this.keyValueMapper.selectKeyValue(cdKey));
		
		return commonResultVo;
	}
	
	/**
	 * 키밸류 등록 또는 수정
	 * 
	 * @param paymentVo
	 * @return commonVo
	 * @throws Exception
	 */
	@PostMapping("/common/mergeKeyValue")
	public CommonResultVo mergeKeyValue(@RequestBody KeyValueVo keyValueVo) throws Exception {
		CommonResultVo commonResultVo = new CommonResultVo();
		commonResultVo.setResultCd("SUCCESS");
		commonResultVo.setReturnCd("0");
		
		keyValueVo.setDel_yn("N");
		
		try {
			int resultCount = this.keyValueMapper.mergeKeyValue(keyValueVo);
			if (resultCount == 0) {
				commonResultVo.setResultCd("FAIL"); // 비정상처리
				commonResultVo.setResultMsg("mergeKeyValue failed.");
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
