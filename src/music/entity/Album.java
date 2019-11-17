package music.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {
	
	@Id
	@GeneratedValue
	private String	idAlbum;
	private String	tenAlbum;
	private String	tenCaSiALbum;
	private String	linkHinhAlbum;
	
	@OneToMany(mappedBy = "Album")
	private Collection<Music> musics;
	
	
	public Album() {
		super();
	}
	
	
	public Album(String idAlbum, String tenAlbum, String tenCaSiALbum, String linkHinhAlbum, Collection<Music> musics) {
		super();
		this.idAlbum = idAlbum;
		this.tenAlbum = tenAlbum;
		this.tenCaSiALbum = tenCaSiALbum;
		this.linkHinhAlbum = linkHinhAlbum;
		this.musics = musics;
	}


	public Collection<Music> getMusics() {
		return musics;
	}
	public void setMusics(Collection<Music> musics) {
		this.musics = musics;
	}
	public String getIdAlbum() {
		return idAlbum;
	}
	public void setIdAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}
	public String getTenAlbum() {
		return tenAlbum;
	}
	public void setTenAlbum(String tenAlbum) {
		this.tenAlbum = tenAlbum;
	}
	public String getTenCaSiALbum() {
		return tenCaSiALbum;
	}
	public void setTenCaSiALbum(String tenCaSiALbum) {
		this.tenCaSiALbum = tenCaSiALbum;
	}
	public String getLinkHinhAlbum() {
		return linkHinhAlbum;
	}
	public void setLinkHinhAlbum(String linkHinhAlbum) {
		this.linkHinhAlbum = linkHinhAlbum;
	}
}
