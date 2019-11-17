package music.controller;

import java.io.File;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.tracing.dtrace.ModuleAttributes;

import music.entity.Album;
import music.entity.User;
import music.model.AlbumUpload;

@Transactional
@Controller
@RequestMapping("/main/")
public class UploadAlbumController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "uploadAlbum", method = RequestMethod.GET)
	public String UploadAlbum(ModelMap model) {
		model.addAttribute("albums", new AlbumUpload());
		return "main/uploadAlbum";
	}

	@RequestMapping(value = "uploadAlbum", method = RequestMethod.POST)
	public String UploadAlbum(ModelMap model, @ModelAttribute("albums") AlbumUpload album, BindingResult errs) {
		if (valid(model, album, errs) == false)
			return "main/uploadAlbum";
		else {
			Album a= new Album("", album.getTenAlbum(), album.getTenCasiAlbum(), uploadImage(model, album.getFilephoto()),null);
			/*
			 * model.addAttribute("message", a); return "test";
			 */
			if(Save_Album(a, model)==true)	{
				model.addAttribute("message", "Upload Album Thành Công!");
				return "main/uploadAlbum";
			}
			else {
				return "main/uploadAlbum";
			}
		}
	}

	private boolean Save_Album(Album a, ModelMap model) {
		Session session= factory.openSession();
		Transaction t = session.beginTransaction();
		boolean check=true;
		try {
			session.save(a);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", e);
			check=false;
		}
		finally {
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
				String root="E:\\Lap_trinh_web\\WebNgheNhac\\WebContent\\";
				String path = "resources\\image\\albums\\";
				image.transferTo(new File(root+path+image.getOriginalFilename()));
				return path+image.getOriginalFilename();
			} catch (Exception e) {
				model.addAttribute("message", "Lỗi Lưu file");
			}
		}
		return "";
	}
	
	private User getUser(String username) {
		Session session = factory.openSession();
		Query query= session.createQuery("From User u where u.userName = :username");
		query.setParameter("username", username);
		User user= (User) query.uniqueResult();
		session.close();
		return user;	
	}
	private boolean valid(ModelMap model, AlbumUpload album, BindingResult errs) {
		boolean c = true;
		if (album.getTenAlbum().isEmpty() == true) {
			errs.rejectValue("tenAblum", "albums", "Tên album không được để trống");
			c = false;
		}
		if (album.getFilephoto().isEmpty() == true) {
			errs.rejectValue("filephoto", "albums", "Vui lòng chọn file ảnh");
			c = false;
		} else {
			if (album.getFilephoto().getContentType() == "image/jpeg"
					|| album.getFilephoto().getContentType() == "image/png") {
				errs.rejectValue("filephoto", "albums", "Tệp không hợp lệ. ");
				c = false;
			}
		}
		return c;
	}

}
