package music.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.websocket.server.PathParam;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.sun.xml.internal.ws.api.ha.HaInfo;

import music.entity.Album;
import music.entity.Music;
import music.entity.TheLoai;
import music.entity.User;
import music.model.MusicUpload;

@Transactional
@Controller
@RequestMapping("/main/")
public class HomeController {
	public static String username = "";
	public Music music2; //fix_upload_ko nhan dc du lieu sau cap nhap
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(ModelMap model) {
		Session session =factory.openSession();
		Query query = session.createQuery("From Music");
		List<Music> list = query.list();
		model.addAttribute("search", new String());
		if(HomeController.username.isEmpty()==true) {
			model.addAttribute("login", "Đăng Nhập");
		}
		else {
			model.addAttribute("login", "Đăng xuất");
		}
		model.addAttribute("list", radom_30_music(list));
		return "main/home";
	}
	
	
	@RequestMapping(value = "login")
	public String lgoin(ModelMap model) {
		if(HomeController.username.isEmpty()==true) {
			return "redirect:/login/index.htm";
		}
		HomeController.username="";
		return "redirect:/main/home.htm";
	}
	
	
	
	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String home(ModelMap model, @RequestParam("search") String search) {
		model.addAttribute("message", ""); // test
		if(search.isEmpty()) {
			Session session =factory.openSession();
			Query query = session.createQuery("From Music");
			List<Music> list = query.list();
			model.addAttribute("search", new String());
			model.addAttribute("list", radom_30_music(list));
			if(HomeController.username.isEmpty()==true) {
				model.addAttribute("login", "Đăng Nhập");
			}
			else {
				model.addAttribute("login", "Đăng xuất");
			}
			return "main/home";
		}
		else {
			Session session =factory.openSession();
			Query query = session.createQuery("From Music m where m.tenBaiHat like :s");
			query.setParameter("s", "%"+search+"%");
			List<Music> list = query.list(); //test
			model.addAttribute("message", search);
			model.addAttribute("search", new String());
			model.addAttribute("list", radom_30_music(list));
			if(HomeController.username.isEmpty()==true) {
				model.addAttribute("login", "Đăng Nhập");
			}
			else {
				model.addAttribute("login", "Đăng xuất");
			}
			session.close();
			return "main/home";
		}
	}
	private List<Music> radom_30_music(List<Music> list) {
		//ramdom
		Collections.shuffle(list);
		//get 30 phan tu
		List<Music> result= new ArrayList<Music>();
		try {
			for(int i=0;i <30;i++) {
				result.add(list.get(i));
			}
		}
		catch (Exception e) {
			
		}
		return result;
	}




	@RequestMapping( value = "QuanLyNhac", method = RequestMethod.GET)
	public String Personal(ModelMap model) {
		// test du lieu
		Session session = factory.openSession();
		Query query = session.createQuery("From User u Where u.userName= :name");
		query.setParameter("name", HomeController.username);
		User user = (User) query.uniqueResult();
		List<Music> list = (List<Music>) user.getMusics();
		model.addAttribute("list", toMusicDisplay(list));
		model.addAttribute("search", new String());
		session.close();
		return "main/QuanLyNhac";
	}
	
	@RequestMapping(value = "QuanLyNhac", method = RequestMethod.POST)
	public String Personal(ModelMap model, @RequestParam("search") String search) {
		if(search.isEmpty()==true) {
			Session session = factory.openSession();
			Query query = session.createQuery("From User u Where u.userName= :name");
			query.setParameter("name", HomeController.username);
			User user = (User) query.uniqueResult();
			List<Music> list = (List<Music>) user.getMusics();
			model.addAttribute("list", toMusicDisplay(list));
			model.addAttribute("search", new String());
			session.close();
			return "main/QuanLyNhac";
		}
		else {
			Session session= factory.openSession();
			Query query= session.createQuery("From Music m where m.user= :user and m.tenBaiHat like :str");
			User user= (User) session.get(User.class, HomeController.username);
			query.setParameter("user", user);
			query.setParameter("str", "%"+search+"%");
			List<Music> list = query.list();
			model.addAttribute("list", toMusicDisplay(list));
			session.close();
			return "main/QuanLyNhac";
		}
	}

	@RequestMapping(value = "deleteMusic/{idBaiHat}")
	public String deleteMusic(ModelMap model, @PathVariable("idBaiHat") String idBaiHat,
			@ModelAttribute("music") Music music) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			music.setIdBaiHat(idBaiHat);
			session.delete(music);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			// TODO: handle exception
		} finally {
			session.close();
		}
		return "redirect:/main/QuanLyNhac.htm";
	}

	@RequestMapping(value = "updateMusic/{idBaiHat}", method = RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("idBaiHat") String idBaiHat) {
		Session session = factory.getCurrentSession();
		 music2 = (Music) session.get(Music.class, idBaiHat);
		MusicUpload m = new MusicUpload(music2.getTenBaiHat(), music2.getCaSi(), music2.getTacGia(),
				music2.getAlbum().getIdAlbum(), music2.getTheLoai().getIdTheLoai(), null, null);
		model.addAttribute("music", m);
		model.addAttribute("idBaiHat", music2.getIdBaiHat());
		List<Album> listAlbum = getDSAlbum();
		List<TheLoai> listTheLoai = getDsTheLoai();		
		model.addAttribute("albums", listAlbum);
		model.addAttribute("theloai", listTheLoai);
		
		return "main/updateMusic";
	}

	@RequestMapping(value = "updateMusic/{idBaiHat}", method = RequestMethod.POST)
	public String update(ModelMap model, @PathVariable("idBaiHat") String idBaiHat,
			@ModelAttribute("music") MusicUpload music) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		music2.setCaSi(music.getCaSi());
		music2.setTenBaiHat(music.getTenBaiHat());
		music2.setTacGia(music.getTacGia());
		music2.setAlbum(getAlbum(music.getIdAlbum()));
		music2.setTheLoai(getTheLoai(music.getIdTheLoai()));
		if(!music.getFilePhoto().isEmpty()) {
			music2.setUriPhoto(uploadImage(model, music.getFilePhoto()));
		}
		
		try {
			session.update(music2);
			t.commit();
			session.close();
			return "redirect:/main/QuanLyNhac.htm"; // update thanh cong
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", music2.getUriAudio());
			model.addAttribute("music", music);
			List<Album> listAlbum = getDSAlbum();
			List<TheLoai> listTheLoai = getDsTheLoai();

			model.addAttribute("albums", listAlbum);
			model.addAttribute("theloai", listTheLoai);
			return "main/updateMusic";
			// TODO: handle exception
		}
	}

	public List<MusicDisphay> toMusicDisplay(List<Music> list) {
		List<MusicDisphay> result_list = new ArrayList<HomeController.MusicDisphay>();

		Music it;
		for (int i = 0; i < list.size(); i++) {
			it = list.get(i);
			result_list.add(new MusicDisphay(it.getIdBaiHat(), String.valueOf(i + 1), it.getTenBaiHat(), it.getCaSi(),
					it.getTacGia(), it.getUriAudio(), it.getUriPhoto(), 0, it.getAlbum(), it.getTheLoai(), it.getUser(),
					it.getAlbum().getTenAlbum(), it.getTheLoai().getTenTheLoai()));
		}

		return result_list;
	}

	public class MusicDisphay extends Music {
		private String stt;
		private String tenAlbum;
		private String tenTheLoai;

		public MusicDisphay(String idBaiHat, String stt, String tenBaiHat, String caSi, String tacGia, String uriAudio,
				String uriPhoto, int luocThich, music.entity.Album album, music.entity.TheLoai theLoai, User user,
				String tenAlbum, String tenTheLoai) {
			super(idBaiHat, tenBaiHat, caSi, tacGia, uriAudio, uriPhoto, luocThich, album, theLoai, user);
			this.stt = stt;
			this.tenAlbum = tenAlbum;
			this.tenTheLoai = tenTheLoai;
		}

		public String getStt() {
			return stt;
		}

		public void setStt(String stt) {
			this.stt = stt;
		}

		public String getTenAlbum() {
			return tenAlbum;
		}

		public void setTenAlbum(String tenAlbum) {
			this.tenAlbum = tenAlbum;
		}

		public String getTenTheLoai() {
			return tenTheLoai;
		}

		public void setTenTheLoai(String tenTheLoai) {
			this.tenTheLoai = tenTheLoai;
		}
	}

	public String getNameAlbum(String id) {
		Session session = factory.openSession();
		Query query = session.createQuery("From Album a Where a.idAlbum = :id");
		query.setParameter("id", id);
		Album album = (Album) query.uniqueResult();
		session.close();
		return album.getTenAlbum();
	}

	public String getNameTheLoai(String id) {
		Session session = factory.openSession();
		Query query = session.createQuery("From TheLoai a Where a.idTheLoai = :id");
		query.setParameter("id", id);
		TheLoai theLoai = (TheLoai) query.uniqueResult();
		session.close();
		return theLoai.getTenTheLoai();
	}

	private List<Album> getDSAlbum() {
		Session session = factory.openSession();
		Query query = session.createQuery("From Album");
		List<Album> listTheLoais = query.list();
		session.close();
		return listTheLoais;
	}

	private List<TheLoai> getDsTheLoai() {
		Session session = factory.openSession();
		Query query = session.createQuery("From TheLoai");
		List<TheLoai> listTheLoais = query.list();
		session.close();
		return listTheLoais;
	}

	private Album getAlbum(String idAlbum) {
		Session session = factory.openSession();
		Query query = session.createQuery("From Album t where t.idAlbum = :id");
		query.setParameter("id", idAlbum);
		Album album = (Album) query.uniqueResult();
		session.close();
		return album;
	}

	private TheLoai getTheLoai(String idTheLoai) {
		Session session = factory.openSession();
		Query query = session.createQuery("From TheLoai t where t.idTheLoai= :id");
		query.setParameter("id", idTheLoai);
		TheLoai theLoai = (TheLoai) query.uniqueResult();
		session.close();
		return theLoai;
	}

	private String uploadImage(ModelMap model, MultipartFile image) { // neu up thanh cong thi tra ve link, neu that bai
		// tra ve ""
		if (image.isEmpty()) {
			model.addAttribute("message", "Vui lòng chọn file!");
		} else {
			try {
				String root = "E:Lap_trinh_web\\WebNgheNhac\\WebContent\\";
				String path = "resources\\image\\";

				image.transferTo(new File(root + path + image.getOriginalFilename()));
				return path + image.getOriginalFilename();
			} catch (Exception e) {
				model.addAttribute("message", "Lỗi Lưu file");
			}
		}
		return "";
	}

}
