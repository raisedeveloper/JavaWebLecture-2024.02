package ch99_PracticeProject;

import java.time.LocalDate;

public class MessageProject {
	private int mid;
	private String content;
	private String writer;
	private LocalDate modTime;
	private int isDelete;
	
	public MessageProject() { }
	
	public MessageProject(String content, String writer, LocalDate modTime, int isDelete) {
		super();
		this.content = content;
		this.writer = writer;
		this.modTime = modTime;
		this.isDelete = isDelete;
	}

	public MessageProject(int mid, String content, String writer, LocalDate modTime, int isDelete) {
		super();
		this.mid = mid;
		this.content = content;
		this.writer = writer;
		this.modTime = modTime;
		this.isDelete = isDelete;
	
	}
	@Override
	public String toString() {
		return "MessageProject [mid=" + mid + ", content=" + content + ", writer=" + writer + ", modTime=" + modTime
				+ ", isDelete=" + isDelete + "]";
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public LocalDate getModTime() {
		return modTime;
	}
	public void setModTime(LocalDate modTime) {
		this.modTime = modTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}