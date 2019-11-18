package music.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import music.entity.User;

@Transactional
@Controller
@RequestMapping(value ="/login/")
public class ForgotPasswordController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping(value = "forgotpassword",method = RequestMethod.GET)
	public String test(ModelMap model) {
		model.addAttribute("username", new String());
		return "forgot_password/forgot_password";
	}
	
	
	@RequestMapping(value = "forgotpassword",method = RequestMethod.POST)
	public String test(ModelMap model, @RequestParam("username") String username ) {
		if(username.isEmpty()==true) {
			model.addAttribute("message", "Vui lòng điền usernmae");
			return "forgot_password/forgot_password";
		}
		String subject="Bảo mật tài khoản nghe nhạc";
		User user = getUser(username);
		String body ="Tài khoản: "+ user.getUserName()+"\n"
				+"Password: " +user.getPassWord();
		try {
			//tao mail
			MimeMessage mail= mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setTo(user.getEmail());
			helper.setSubject(subject);
			helper.setText(body,true);
			
			//gui mail
			mailer.send(mail);
			model.addAttribute("message", "Mật khẩu của bạn đã được gửi về địa chỉ email: "+user.getEmail());
			model.addAttribute("navigation", "Quay về trang đăng nhập");
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message", "Gửi email thất bại! ");
		}
		return "forgot_password/forgot_password" ;
	}


	private User getUser(String username) {
		Session session= factory.openSession();
		Query query= session.createQuery("From User u where u.userName= :username");
		query.setParameter("username", username);
		User user= (User) query.uniqueResult();
		session.close();
		return user;
	}
}
