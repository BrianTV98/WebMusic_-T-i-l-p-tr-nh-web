package music.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TheLoai {

	@Id
	private String idTheLoai;
	private String tenTheLoai;
	private String hinhTheLoai;
	
	@OneToMany(mappedBy = "TheLoai", fetch = FetchType.EAGER)
	private Collection<Music> musics;
	
	
	public TheLoai() {
		super();
	}

	public TheLoai(String idTheLoai, String tenTheLoai, String hinhTheLoai) {
		super();
		this.idTheLoai = idTheLoai;
		this.tenTheLoai = tenTheLoai;
		this.hinhTheLoai = hinhTheLoai;
	}
	
	public Collection<Music> getMusics() {
		return musics;
	}

	public void setMusics(Collection<Music> musics) {
		this.musics = musics;
	}

	public String getIdTheLoai() {
		return idTheLoai;
	}
	public void setIdTheLoai(String idTheLoai) {
		this.idTheLoai = idTheLoai;
	}
	public String getTenTheLoai() {
		return tenTheLoai;
	}
	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}
	public String getHinhTheLoai() {
		return hinhTheLoai;
	}
	public void setHinhTheLoai(String hinhTheLoai) {
		this.hinhTheLoai = hinhTheLoai;
	}
	
	
}
