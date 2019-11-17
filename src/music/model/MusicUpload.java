package music.model;

import org.springframework.web.multipart.MultipartFile;

public class MusicUpload {
	private String tenBaiHat;
	private String caSi;
	private String tacGia;
	private String idAlbum;
	private String idTheLoai;
	private MultipartFile filePhoto;
	private MultipartFile fileAudio;
	
	
	
	public MusicUpload() {
		super();
		this.filePhoto=null;
		this.fileAudio=null;
	}
	public MusicUpload(String tenBaiHat, String caSi, String tacGia, String idAlbum,
			String idTheLoai, MultipartFile filePhoto, MultipartFile fileAudio) {
		super();
		this.tenBaiHat = tenBaiHat;
		this.caSi = caSi;
		this.tacGia = tacGia;
		this.idAlbum = idAlbum;
		this.idTheLoai = idTheLoai;
		this.filePhoto = filePhoto;
		this.fileAudio = fileAudio;
	}
	
	public String getTenBaiHat() {
		return tenBaiHat;
	}
	public void setTenBaiHat(String tenBaiHat) {
		this.tenBaiHat = tenBaiHat;
	}
	public String getCaSi() {
		return caSi;
	}
	public void setCaSi(String caSi) {
		this.caSi = caSi;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public String getIdAlbum() {
		return idAlbum;
	}
	public void setIdAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}
	public String getIdTheLoai() {
		return idTheLoai;
	}
	public void setIdTheLoai(String idTheLoai) {
		this.idTheLoai = idTheLoai;
	}
	public MultipartFile getFilePhoto() {
		return filePhoto;
	}
	public void setFilePhoto(MultipartFile filePhoto) {
		this.filePhoto = filePhoto;
	}
	public MultipartFile getFileAudio() {
		return fileAudio;
	}
	public void setFileAudio(MultipartFile fileAudio) {
		this.fileAudio = fileAudio;
	}
	
}