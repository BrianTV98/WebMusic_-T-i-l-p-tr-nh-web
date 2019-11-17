package music.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import music.entity.Music;
import music.entity.TheLoai;

@Transactional
@Controller
@RequestMapping("/main/")
public class TheLoaiController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("theloai")
	public String theloai(ModelMap model) {
		Session session= factory.openSession();
		Query query= session.createQuery("From TheLoai");
		List<TheLoai> list_TheLoai= query.list();
		model.addAttribute("list", list_TheLoai);
		if(HomeController.username.isEmpty()==true) {
			model.addAttribute("login", "Đăng Nhập");
		}
		else {
			model.addAttribute("login", "Đăng xuất");
		}
		return "main/theloai";
	}
	@RequestMapping(value = "theloai/{idtheloai}", method = RequestMethod.GET)
	public String Music_of_Album(ModelMap model, @PathVariable("idtheloai") String idtheloai) {
		Session session= factory.openSession();
		Query query= session.createQuery("From TheLoai m where m.idTheLoai = :id");
		query.setParameter("id", idtheloai);
		TheLoai theloai= (TheLoai) query.uniqueResult();
		List<Music> list= (List<Music>) theloai.getMusics();
		model.addAttribute("list", list);
		model.addAttribute("message", "THỂ LOẠI: "+theloai.getTenTheLoai());
		session.close();
		return "main/home";
	}
	
}
