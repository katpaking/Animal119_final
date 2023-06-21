package com.animal.model;

public class board {

	private int no;
	private String category;
	private String writer;
	private String subject;
	private String image01;
	private String image02;
	private String image03;
	private String content;
	private String regdate;
	private int readhit;
	private int groupno; // 게시글 그룹 번호
	private int orderno; // 게시할 순번
	private int depth; // 글의 깊이
	
	public board() {
		// TODO Auto-generated constructor stub
	}
	
	public board(int no, String category, String writer, String subject, String content, String regdate, int readhit,
			int groupno, int orderno, int depth) {
		super();
		this.no = no;
		this.category = category;
		this.writer = writer;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
		this.readhit = readhit;
		this.groupno = groupno;
		this.orderno = orderno;
		this.depth = depth;
		
	}


	public board(String image01, String image02, String image03) {
		super();
		this.image01 = image01;
		this.image02 = image02;
		this.image03 = image03;
	}

	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
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


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
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
	
	


	public String getImage01() {
		return image01;
	}

	public void setImage01(String image01) {
		this.image01 = image01;
	}

	public String getImage02() {
		return image02;
	}

	public void setImage02(String image02) {
		this.image02 = image02;
	}

	public String getImage03() {
		return image03;
	}

	public void setImage03(String image03) {
		this.image03 = image03;
	}

	@Override
	public String toString() {
		return "board [no=" + no + ", category=" + category + ", writer=" + writer + ", subject=" + subject
				+ ", image01=" + image01 + ", image02=" + image02 + ", image03=" + image03 + ", content=" + content
				+ ", regdate=" + regdate + ", readhit=" + readhit + ", groupno=" + groupno + ", orderno=" + orderno
				+ ", depth=" + depth + "]";
	}


	
}
