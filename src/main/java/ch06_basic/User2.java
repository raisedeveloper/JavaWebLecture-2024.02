package ch06_basic;

public class User2 {
	private String uid;
	private String pwd;
	
	public User2() { }

	public User2(String uid, String pwd) {
		this.uid = uid;
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "User2 [uid=" + uid + ", pwd=" + pwd + "]";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}