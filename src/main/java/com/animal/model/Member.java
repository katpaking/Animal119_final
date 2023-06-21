package com.animal.model;

public class Member {
	private String id ;
	private String name ;
	private String password ;
	private String gender ;
	private String hobby ;
	private String hiredate ; // 날짜이지만 문자열로 처리하면 코딩이 좀 간결해짐 
	private int point ;
	private String remark;
	

	public Member() {
	
	}

	public Member(String id, String name, String password, String gender, String hobby, String hiredate, int point,
			String remark) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.hobby = hobby;
		this.hiredate = hiredate;
		this.point = point;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", gender=" + gender + ", hobby="
				+ hobby + ", hiredate=" + hiredate + ", point=" + point + ", remark=" + remark + "]";
	}
}
