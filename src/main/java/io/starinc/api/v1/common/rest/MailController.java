package io.starinc.api.v1.common.rest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.util.MailUtil;
import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.common.vo.MailVo;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class MailController {
	
//	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	/**
	 * 메일보내기
	 * 
	 * @param findVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/common/sendMail")
	public CommonVo sendMail(@RequestBody MailVo mailVo) throws Exception {
		CommonVo commonVo = new CommonVo();
		commonVo.setResultCd("SUCCESS");
//		commonVo.setResultMsg("sendMail called.");
		try {
			MailUtil mailUtil = new MailUtil();
			mailUtil.send(mailVo);
		} catch (Exception e) {
			commonVo.setResultCd("FAIL");
			commonVo.setResultMsg(e.toString());
		}
		return commonVo;
	}
}
