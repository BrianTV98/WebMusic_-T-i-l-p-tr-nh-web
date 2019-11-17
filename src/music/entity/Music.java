package music.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BaiHat")
public class Music {
	
	@Id
	@GeneratedValue
	private String idBaiHat;
	private String tenBaiHat;
	private String caSi;
	private String tacGia;
	private String uriAudio;
	private String uriPhoto;
	
	private int LuocThich;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idAlbum")
	private Album Album;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTheLoai")
	private TheLoai TheLoai;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "username")
	private User user;
	
	
	public Music() {
		super();
	}

	public Music(String idBaiHat, String tenBaiHat, String caSi, String tacGia, String uriAudio, String uriPhoto,
			int luocThich, Album album, TheLoai theLoai, User user) {
		super();
		this.idBaiHat = idBaiHat;
		this.tenBaiHat = tenBaiHat;
		this.caSi = caSi;
		this.tacGia = tacGia;
		this.uriAudio = uriAudio;
		this.uriPhoto = uriPhoto;
		this.LuocThich = luocThich;
		this.Album = album;
		this.TheLoai = theLoai;
		this.user = user;
	}



	public String getIdBaiHat() {
		return idBaiHat;
	}

	public void setIdBaiHat(String idBaiHat) {
		this.idBaiHat = idBaiHat;
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

	public Album getAlbum() {
		return Album;
	}

	public void setAlbum(Album album) {
		Album = album;
	}

	

	public TheLoai getTheLoai() {
		return TheLoai;
	}

	public void setTheLoai(TheLoai theLoai) {
		TheLoai = theLoai;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}


	public String getUriAudio() {
		return uriAudio;
	}

	public void setUriAudio(String uriAudio) {
		this.uriAudio = uriAudio;
	}

	public int getLuocThich() {
		return LuocThich;
	}

	public void setLuocThich(int luocThich) {
		LuocThich = luocThich;
	}
	
	public String getUriPhoto() {
		return uriPhoto;
	}

	public void setUriPhoto(String uriPhoto) {
		this.uriPhoto = uriPhoto;
	}
}
