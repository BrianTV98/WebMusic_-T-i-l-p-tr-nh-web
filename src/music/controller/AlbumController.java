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

import music.entity.Album;
import music.entity.Music;

@Transactional
@Controller
@RequestMapping("/main/")
public class AlbumController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/album")
	public String album(ModelMap model) {
		Session session= factory.openSession();
		Query query= session.createQuery("From Album");
		List<Album> list= query.list();
		list.remove(0);
		model.addAttribute("message", "");
		model.addAttribute("list", list);
		if(HomeController.username.isEmpty()==true) {
			model.addAttribute("login", "Đăng Nhập");
		}
		else {
			model.addAttribute("login", "Đăng xuất");
		}
		return "main/album";
	}
	
	@RequestMapping("/album/{idAlbum}")
	public String Music_of_Album(ModelMap model, @PathVariable("idAlbum") String idAlbum) {
		Session session= factory.openSession();
		Query query= session.createQuery("From Album a where a.idAlbum = :id");
		query.setParameter("id", idAlbum);
		Album album= (Album) query.uniqueResult();
		List<Music> list = (List<Music>) album.getMusics();
		model.addAttribute("message","ALBUM: " + album.getTenAlbum().toUpperCase());
		model.addAttribute("list", list);
		return "main/home";
	}
		
}
