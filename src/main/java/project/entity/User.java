package project.entity;

import java.time.LocalDate;

public class User {
	  private String uid;
	    private String pwd;
	    private String uname;
	    private String email;
	    private LocalDate regDate;
	    private int isDeleted;
	    public User() {}
	    public User(String uid, String pwd, String uname, String email, LocalDate regDate, int isDeleted) {
	        super();
	        this.uid = uid;
	        this.pwd = pwd;
	        this.uname = uname;
	        this.email = email;
	        this.regDate = regDate;
	        this.isDeleted = isDeleted;
	    }

	    public User(String uid, String pwd, String uname, String email) {
	        this.uid = uid;
	        this.pwd = pwd;
	        this.uname = uname;
	        this.email = email;
	    }

	    @Override
	    public String toString() {
	        return "User [uid=" + uid + ", uname=" + uname + ", email=" + email + ", regDate=" + regDate
	                + ", isDeleted=" + isDeleted + "]";//getPwd하면 원할 때 찍을 수 있으니 toString에서는 제거했음(나중에 필요하면 쓰기, pwd 쓰면 너무 길어져서)
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

	    public String getUname() {
	        return uname;
	    }



		public void setUname(String uname) {
	        this.uname = uname;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public LocalDate getRegDate() {
	        return regDate;
	    }

	    public void setRegDate(LocalDate regDate) {
	        this.regDate = regDate;
	    }

	    public int getIsDeleted() {
	        return isDeleted;
	    }

	    public void setIsDeleted(int isDeleted) {
	        this.isDeleted = isDeleted;
	    }
}
