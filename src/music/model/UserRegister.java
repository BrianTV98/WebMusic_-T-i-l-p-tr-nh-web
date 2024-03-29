package music.model;

public class UserRegister {
	private String userName;
	private String passWord;
	private String fullName;
	private String sdt;
	private String retrypass;
	private String email;	

	public UserRegister() {
		super();
	}

	public UserRegister(String userName, String passWord, String fullName, String sdt, String retrypass, String email) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.sdt = sdt;
		this.retrypass = retrypass;
		this.email = email;
	}

	public String getRetrypass() {
		return retrypass;
	}

	public void setRetrypass(String retrypass) {
		this.retrypass = retrypass;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
