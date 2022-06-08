package org.serratec.config;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Configuration
public class MailConfig {
	
	@Autowired
	private JavaMailSender javaMailSender;
		
	public void enviarEmailHTML(String para, String assunto,String texto) throws MessagingException{
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		
		mimeMessageHelper.setTo(para);
		mimeMessageHelper.setSubject(assunto);
		mimeMessageHelper.setText(texto, true);
		
		mimeMessageHelper.setFrom("grupo2.serratec.api@gmail.com");
		
		javaMailSender.send(mimeMessage);
	}
}
