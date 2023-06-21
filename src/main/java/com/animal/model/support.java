package com.animal.model;

public class support {
	
	private int no; //게시글 번호
	private String writer; // 작성자
	private String subject; // 제목
	private String phone; //신청자 전화번호
	private String content; //글내용
	private String hopeday; // 희망 신청일
	private String regdate; // 신청서 등록일
	private int groupno;
	private int orderno;
	
	
	public support() {
		
	}


	public support(int no, String writer, String subject, String phone, String content, String hopeday, String regdate,
			int groupno, int orderno) {
		super();
		this.no = no;
		this.writer = writer;
		this.subject = subject;
		this.phone = phone;
		this.content = content;
		this.hopeday = hopeday;
		this.regdate = regdate;
		this.groupno = groupno;
		this.orderno = orderno;
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getHopeday() {
		return hopeday;
	}


	public void setHopeday(String hopeday) {
		this.hopeday = hopeday;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
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


	@Override
	public String toString() {
		return "support [no=" + no + ", writer=" + writer + ", subject=" + subject + ", phone=" + phone + ", content="
				+ content + ", hopeday=" + hopeday + ", regdate=" + regdate + ", groupno=" + groupno + ", orderno="
				+ orderno + "]";
	}
	
	
	

}
