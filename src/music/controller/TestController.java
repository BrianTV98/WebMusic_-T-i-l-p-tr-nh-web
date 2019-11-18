package music.controller;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.internal.logging.Logger;

import music.entity.Music;

@Transactional
@Controller
public class TestController {
	@Autowired
	ServletContext context;
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String test(ModelMap model) {
		model.addAttribute("from", new String());
		model.addAttribute("to", new String());
		model.addAttribute("subject", new String());
		model.addAttribute("body", new String());
		return "test";
	}
	
	
	@RequestMapping(value = "/test",method = RequestMethod.POST)
	public String test(ModelMap model, @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("subject") String subject,@RequestParam("body") String body ) {
		try {
			//tao mail
			MimeMessage mail= mailer.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body,true);
			
			//gui mail
			mailer.send(mail);
			model.addAttribute("message", "Gửi mail thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "Gửi email thất bại! ");
		}
		return "test" ;
	}

}
