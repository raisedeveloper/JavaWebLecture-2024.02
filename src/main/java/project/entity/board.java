package project.entity;

import java.time.LocalDateTime;

public class board {
	private int bid;
	private String title;
	private String content;
	private String uid;
	private LocalDateTime motTime;
	private int isDeleted;
	private int viewCount;
	private int replyCount;
	private String uname;	// user stable 과 join 해서 얻게 될 내용
	
	
	public board() { }
	
	// 게시글 생성 할 때 필요한 생성자만 나열(미리 세팅)
	public board(String title, String content, String uid) {
		this.title = title;
		this.content = content;
		this.uid = uid;
	}

	// 게시글 수정할 때 필요한 생성자만 나열(update 상황 가정 - 미리 세팅)
	public board(int bid, String title, String content, String uid) {
		this.bid = bid;
		this.title = title;
		this.content = content;
		this.uid = uid;
		
		
	}

	// 코드를 쉽게 쓰기 위해 게시글 목록 생성자만 나열
	public board(int bid, String title, LocalDateTime motTime, int viewCount, int replyCount, String uname) {
		super();
		this.bid = bid;
		this.title = title;
		this.motTime = motTime;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
		this.uname = uname;
	}

	public board(int bid, String title, String content, String uid, LocalDateTime motTime, int isDeleted, int viewCount,
			int replyCount, String uname) {
		this.bid = bid;
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.motTime = motTime;
		this.isDeleted = isDeleted;
		this.viewCount = viewCount;
		this.replyCount = replyCount;
		this.uname = uname;
	}
	
	@Override
	public String toString() {
		return "board [bid=" + bid + ", title=" + title + ", content=" + content + ", uid=" + uid + ", motTime="
				+ motTime + ", isDeleted=" + isDeleted + ", viewCount=" + viewCount + ", replyCount=" + replyCount
				+ ", uname=" + uname + "]";
	}
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public LocalDateTime getMotTime() {
		return motTime;
	}
	public void setMotTime(LocalDateTime motTime) {
		this.motTime = motTime;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
}