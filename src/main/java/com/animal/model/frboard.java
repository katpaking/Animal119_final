package com.animal.model;

public class frboard {

	private int no;
	private String category;
	private String writer;
	private String subject;
	private String image01;

	private String content;
	private String regdate;
	private int readhit;
	private int groupno; // 게시글 그룹 번호
	private int orderno; // 게시할 순번
	private int depth; // 글의 깊이

	public frboard() {
		// TODO Auto-generated constructor stub
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getImage01() {
		return image01;
	}

	public void setImage01(String image01) {
		this.image01 = image01;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getReadhit() {
		return readhit;
	}

	public void setReadhit(int readhit) {
		this.readhit = readhit;
	}

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public frboard(int no, String category, String writer, String subject, String image01, String content,
			String regdate, int readhit, int groupno, int orderno, int depth) {
		super();
		this.no = no;
		this.category = category;
		this.writer = writer;
		this.subject = subject;
		this.image01 = image01;
		this.content = content;
		this.regdate = regdate;
		this.readhit = readhit;
		this.groupno = groupno;
		this.orderno = orderno;
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "frboard [no=" + no + ", category=" + category + ", writer=" + writer + ", subject=" + subject
				+ ", image01=" + image01 + ", content=" + content + ", regdate=" + regdate + ", readhit=" + readhit
				+ ", groupno=" + groupno + ", orderno=" + orderno + ", depth=" + depth + "]";
	}
	
	
}
