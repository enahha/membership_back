package io.starinc.api.v1.common.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.starinc.api.v1.common.mapper.DateMapper;


//@ApiIgnore
@RestController
@Transactional
@RequestMapping(value = "/api")
public class DateController {
	
//	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private DateMapper dateMapper;
	
	/**
	 * DB 서버 시간 조회
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/common/getNow")
	public String sendMail(@RequestParam String uid) throws Exception {
		String now = "";
		try {
			// now = DateUtil.getYYYYMMDDHHMM();
			now = dateMapper.selectNow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}
}
