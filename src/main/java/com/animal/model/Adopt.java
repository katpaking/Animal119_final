package com.animal.model;

public class Adopt {
	
	private int num; //게시글 번호
	private String writer; // 작성자
	private String subject; // 제목
	private String phone; //신청자 전화번호
	private String regdate; // 신청서 등록일
	private int groupno;
	private int orderno;
	
	
	public Adopt() {
		
	}
	

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
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
		return "Adopt [num=" + num + ", writer=" + writer + ", subject=" + subject + ", phone=" + phone + ", regdate="
				+ regdate + ", groupno=" + groupno + ", orderno=" + orderno + "]";
	}

	

}
