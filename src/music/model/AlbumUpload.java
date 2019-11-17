package music.model;

import org.springframework.web.multipart.MultipartFile;

public class AlbumUpload {
	private String tenAlbum;
	private String tenCasiAlbum;
	private MultipartFile filephoto;

	public String getTenAlbum() {
		return tenAlbum;
	}

	public void setTenAlbum(String tenAlbum) {
		this.tenAlbum = tenAlbum;
	}

	public String getTenCasiAlbum() {
		return tenCasiAlbum;
	}

	public void setTenCasiAlbum(String tenCasiAlbum) {
		this.tenCasiAlbum = tenCasiAlbum;
	}

	public MultipartFile getFilephoto() {
		return filephoto;
	}

	public void setFilephoto(MultipartFile filephoto) {
		this.filephoto = filephoto;
	}

	public AlbumUpload(String tenAlbum, String tenCasiAlbum, MultipartFile filephoto) {
		super();
		this.tenAlbum = tenAlbum;
		this.tenCasiAlbum = tenCasiAlbum;
		this.filephoto = filephoto;
	}

	public AlbumUpload() {
		super();
	}
}
