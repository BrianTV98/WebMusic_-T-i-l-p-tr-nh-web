package music.controller;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
	SessionFactory factory;
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test( ModelMap model) {
		Session session= factory.openSession();
		Query query= session.createQuery("From Music");
		List<Music> list=  query.list();
		model.addAttribute("list", list);
		return "test";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.POST)
	
	public String test(ModelMap model, @RequestParam("file") MultipartFile file) {
		if(file.isEmpty()) {
			model.addAttribute("message", "Vui lòng chọn file!");
		}	
		else {
			try {
				String path="E:\\Lap_trinh_web\\WebNgheNhac\\WebContent\\resources\\image\\"+ file.getOriginalFilename();
				file.transferTo( new File(path));
				model.addAttribute("message", path);
			}
			catch (Exception e) {
				model.addAttribute("message", "Lỗi Lưu file");
			}
		}
	
		return "test";
	}

}
