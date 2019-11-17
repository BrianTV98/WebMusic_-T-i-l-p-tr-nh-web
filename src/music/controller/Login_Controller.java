package music.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import music.entity.User;
import music.model.UserRegister;

@Transactional
@Controller
@RequestMapping("/login/")
public class Login_Controller {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "index", method = RequestMethod.GET )
	public String index(ModelMap model) {
		model.addAttribute("user", new User());
		return "login/login";
	}
	@RequestMapping(value = "index", method = RequestMethod.POST)
	public String index(ModelMap model, @ModelAttribute("user") User user) {
		Boolean check=login_authenthication(user);
		if(check==false) {
			model.addAttribute("message", "*Sai tên đăng nhập hoặc mật khẩu");
			return "login/login";
		}
		else {
			HomeController.username=user.getUserName();
			return "redirect:/main/home.htm";
		}

	}
	
	private Boolean login_authenthication(User user) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User u  WHERE u.userName = :a AND u.passWord = :b";
		Query query = session.createQuery(hql);
		query.setParameter("a", user.getUserName());
		query.setParameter("b", user.getPassWord());
		List<User> results =query.list();
		if(results.size()==0) {
			return false;
		}
		else {	
			return true;
		}
		
	}
	//Register
	
	
}
