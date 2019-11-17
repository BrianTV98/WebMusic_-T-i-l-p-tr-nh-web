package music.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;



import music.entity.Album;
import music.entity.Music;
import music.entity.TheLoai;
import music.entity.User;
import music.model.MusicUpload;

@Transactional
@Controller
@RequestMapping("/main/")
public class UpLoadMusicController {
	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext context;

	@RequestMapping(value = "uploadMusic", method = RequestMethod.GET)
	public String upload(ModelMap model) {
		model.addAttribute("music", new MusicUpload());
		sendaddAtribute(model);	
		return "main/upload";
	}

	private void sendaddAtribute(ModelMap model) {
		model.addAttribute("tenBaiHat", new String());
		model.addAttribute("tacGia", new String());
		model.addAttribute("caSi", new String());
		List<Album> listAlbum = getDSAlbum();
		List<TheLoai> listTheLoai = getDsTheLoai();
		
		model.addAttribute("albums", listAlbum);
		model.addAttribute("theloai", listTheLoai);

	}

	private List<Album> getDSAlbum() {
		Session session = factory.openSession();
		Query query = session.createQuery("From Album");
		List<Album> listTheLoais = query.list();
		session.close();
		return listTheLoais;
	}

	@RequestMapping(value = "uploadMusic", method = RequestMethod.POST)
	public String upload1(ModelMap model, @ModelAttribute("music") MusicUpload music,
			BindingResult errs) {
		if (valid(music, errs) == false) {
			model.addAttribute("music", music);
			sendaddAtribute(model);
			return "main/upload";
		} else {
			String uriImage= uploadImage(model, music.getFilePhoto());
			String uriAudio= uploadAudio(model, music.getFileAudio());
			if(uriImage.isEmpty()==false&&uriAudio.isEmpty()==false) {
				Music m=new Music("", music.getTenBaiHat(), music.getCaSi(), music.getTacGia(), uriAudio, uriImage, 0,
						getAlbum(music.getIdAlbum()), getTheLoai(music.getIdTheLoai()), getUser(HomeController.username));
				if(SaveDB( model,m)==true) { //fix_upload_complie_not_recive_album+theloai
					model.addAttribute("music", new MusicUpload());
					sendaddAtribute(model);	
					 return "main/upload";
				}		
			}
		}
		return  "mai/upload";
		
	}

	private boolean valid(MusicUpload music, BindingResult errs) {
		boolean c = true;
		if (music.getTenBaiHat().isEmpty() == true) {
			errs.rejectValue("tenBaiHat", "music", "Tên bài Hát không được để trống");
			c = false;
		}
		if (music.getFilePhoto().isEmpty() == true) {
			errs.rejectValue("filePhoto","music", "Vui lòng chọn ảnh");
			c = false;
		} else {
			if (music.getFilePhoto().getContentType().equals("image/jpeg") || music.getFilePhoto().getContentType().equals("image/png")) {
			} else {
				errs.rejectValue("filePhoto","music", music.getFilePhoto().getContentType());
				c = false;
			}
		}
		if (music.getFileAudio().isEmpty() == true) {
			errs.rejectValue("fileAudio","music", "Vui lòng chọn audio");
			c = false;
		} else {
			if (!music.getFileAudio().getContentType().equals("audio/mp3")) {
				errs.rejectValue("fileAudio","music", "Audio không hợp lệ");
			}
		}
		return c;
	}

	private boolean SaveDB(ModelMap model,Music music) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean check = false;
		try {
			session.save(music);
			t.commit();
			check = true;
			model.addAttribute("message", "Upload Thành Công!");
		} catch (Exception e) {
			model.addAttribute("message2", "Upload Thất bại!" +e );
			t.rollback();
		} finally {
			session.close();
		}
		return check;

	}

	private String uploadImage(ModelMap model, MultipartFile image) { // neu up thanh cong thi tra ve link, neu that bai
																		// tra ve ""
		if (image.isEmpty()) {
			model.addAttribute("message", "Vui lòng chọn file!");
		} else {
			try {
				String root="E:Lap_trinh_web\\WebNgheNhac\\WebContent\\";
				String path = "resources\\image\\";
	
				image.transferTo(new File(root+path+image.getOriginalFilename()));
				return path+image.getOriginalFilename();
			} catch (Exception e) {
				model.addAttribute("message", "Lỗi Lưu file");
			}
		}
		return "";
	}

	private String uploadAudio(ModelMap model, MultipartFile audio) { // neu up thanh cong thi tra ve link, neu that bai
		// tra ve ""
		try {
			String root="E:\\Lap_trinh_web\\WebNgheNhac\\WebContent\\";
			String path = "resources\\audio\\";
					
			audio.transferTo(new File(root+path+audio.getOriginalFilename()));
			return path+ audio.getOriginalFilename();
		} catch (Exception e) {
			
		}

		return "";
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
		Session session= factory.openSession();
		Query query= session.createQuery("From TheLoai t where t.idTheLoai= :id");
		query.setParameter("id", idTheLoai);
		TheLoai theLoai= (TheLoai) query.uniqueResult();
		session.close();
		return theLoai;
	}
	
	private User getUser(String username) {
		Session session = factory.openSession();
		Query query= session.createQuery("From User u where u.userName = :username");
		query.setParameter("username", username);
		User user= (User) query.uniqueResult();
		session.close();
		return user;	
	}
}
