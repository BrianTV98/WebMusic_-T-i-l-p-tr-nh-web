package music.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import music.entity.Music;
import music.entity.User;

@Transactional
@Controller
@RequestMapping("/main/")
public class MyMusicController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "MyMusic", method = RequestMethod.GET)
	public String MyMusic(ModelMap model) {
		if(HomeController.username.isEmpty()==true) { // neu chua dang nhap thi phai dang nhap
			return "redirect:/login/index.htm";
		}
		else {
			List<Music> list= getMyMusic();
			model.addAttribute("list", list);
			model.addAttribute("search", new String());
			return "main/CaNhan/MyMusic";
		}
	}
	
	@RequestMapping(value = "MyMusic", method = RequestMethod.POST)
	public String MyMusic(ModelMap model, @RequestParam("search") String search) {
		if(search.isEmpty()==true) {  // tra ve du lieu chuan
			List<Music> list= getMyMusic();
			model.addAttribute("list", list);
			model.addAttribute("search", new String());
			return "main/CaNhan/MyMusic";
		}
		else {
			Session session= factory.openSession();
			Query query= session.createQuery("From Music m where m.user= :user and m.tenBaiHat like :str");
			User user= (User) session.get(User.class, HomeController.username);
			query.setParameter("user", user);
			query.setParameter("str", "%"+search+"%");
			List<Music> list = query.list();
			model.addAttribute("list", list);
			session.close();
			return "main/CaNhan/MyMusic";
		}
	}

	private List<Music> getMyMusic() {
		Session session= factory.openSession();
		Query query= session.createQuery("From User u where u.userName= :username");
		query.setParameter("username", HomeController.username);
		User user= (User) query.uniqueResult();
		List<Music> list= (List<Music>) user.getMusics();
		return list;
	}
}
