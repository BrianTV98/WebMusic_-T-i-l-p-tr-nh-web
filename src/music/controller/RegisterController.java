package music.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import music.entity.User;
import music.model.UserRegister;

@Transactional
@Controller
@RequestMapping("/login/")
public class RegisterController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "register", method = RequestMethod.GET )
	public String register(ModelMap model) {
		model.addAttribute("user", new UserRegister());
		return "register/register";
	}
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(ModelMap model, @ModelAttribute("user") UserRegister user, BindingResult errs) {
		
		boolean checkValid=valid_Register(model,user,errs);
		if(checkValid==false) {
			return "register/register";
		}
		else {
			User userEntity= userRegister_to_UserEntiy(user);
			Boolean checkInsert=InsertUser(userEntity);
			if(checkInsert==false) {
				errs.rejectValue("userName", "user", "*Tài khoản này đã tồn tại!");
				return "register/register";
			}
			return "login/login";
		}
	}
	
	private Boolean InsertUser(User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean check=false;
		try {
			
			session.save(user);
			t.commit();
			check=true;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		return check;
		
	}
	private User userRegister_to_UserEntiy(UserRegister user) {
		User userEntity= new User(user.getUserName(), user.getPassWord(), user.getFullName(), user.getSdt());
		return userEntity;
	}
	public boolean valid_Register(ModelMap model, UserRegister user, BindingResult errs) {
		boolean c=true;
		if(user.getFullName().isEmpty()==true) {
			errs.rejectValue("fullName", "user","*Họ và tên không được trống");
			c=false;
		}
		// sdt
		if(user.getSdt().isEmpty()==true) {// so dien thoai rong
			errs.rejectValue("sdt", "user","*Số điện thoại không được trống");
			c=false;
		}
		else {
			// chieu dai ko hop le
			if(user.getSdt().length()>=9 && user.getSdt().length()<20) {
				try {
					int sdt= Integer.parseInt(user.getSdt());
					if(!user.getSdt().substring(0, 1).equals("0")) {
						errs.rejectValue("sdt", "user","*Số điện thoại không hợp lệ");
						c=false;
					}
				}
				catch (Exception e) {
					// TODO: handle exception
					errs.rejectValue("sdt", "user","*Số điện thoại không hợp lệ");
					c=false;
				}
			}
			else {
				errs.rejectValue("sdt", "user","*Số điện thoại không hợp lệ");
				c=false;
			}
		}
		
		if(user.getUserName().isEmpty()==true) {
			errs.rejectValue("userName", "user","*Tên tài khoản không được trống");
			c=false;
		}
		// pass
		if(user.getPassWord().isEmpty()==true) {
			
		}
		else {
			if(user.getPassWord().length()<=6) {
				errs.rejectValue("passWord","user" ,"*Mật khẩu qúa ngắn");
				c=false;
			}
			else {
				if(!user.getPassWord().equals(user.getRetrypass())) {
					errs.rejectValue("retrypass","user" ,"*Vui lòng xác nhận lại mật khẩu");
					c=false;
				}
			}
		}
		return c;
	}
}
