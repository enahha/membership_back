/**
 * 
 */
package io.starinc.api.v1.common.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.common.vo.MailVo;

/**
 * @author ahn
 *
 */
public class MailUtil {
	
	
	/**
	 * 이메일 전송
	 * 
	 * @param mailVo
	 * @throws MessagingException 
	 */
	public void send(MailVo mailVo) throws MessagingException {
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
		p.put("mail.smtp.auth","true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587"); // gmail 포트
//		p.put("mail.smtp.starttls.enable", "true"); // use TLS

		Authenticator auth = new MyAuthentication();
		//session 생성 및  MimeMessage생성
		Session session = Session.getInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);
		//편지보낸시간
		msg.setSentDate(new Date());
		InternetAddress from = new InternetAddress() ;
		from = new InternetAddress(Constant.ADMIN_EMAIL);
		// 이메일 발신자
		msg.setFrom(from);
		// 이메일 수신자
		InternetAddress to = new InternetAddress(mailVo.getEmail_to());
		msg.setRecipient(Message.RecipientType.TO, to);
		// 이메일 제목
		msg.setSubject(mailVo.getSubject(), "UTF-8");
		// 이메일 내용 
		msg.setText(mailVo.getText(), "UTF-8");
		// 이메일 헤더 
		msg.setHeader("content-Type", "text/html");
		//메일보내기
		System.out.println("===== 메일 보냄 =====");
		javax.mail.Transport.send(msg);
	}
	
	class MyAuthentication extends Authenticator {
		PasswordAuthentication pa;
		public MyAuthentication() {
			String id = "question_pkz@gaudilabs.io"; // 구글 ID
			String pw = "tabi xpfz ueti czgu"; // 구글 비밀번호
			// ID와 비밀번호를 입력한다.
			pa = new PasswordAuthentication(id, pw);
		}
		// 시스템에서 사용하는 인증정보
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}
}
