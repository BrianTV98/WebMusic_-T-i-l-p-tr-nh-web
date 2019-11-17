package music.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
	@Id
	private String userName;
	private String passWord;
	private String fullName;
	private String sdt;
	
	@OneToMany(mappedBy = "user")
	private Collection<Music> musics;
	
	public User(String userName, String passWord, String fullName, String sdt) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.sdt = sdt;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public User() {
		super();
	}
	
	public String getUserName() {
		return userName;
	}
	public String getPassWord() {
		return passWord;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Collection<Music> getMusics() {
		return musics;
	}

	public void setMusics(Collection<Music> musics) {
		this.musics = musics;
	}
}
