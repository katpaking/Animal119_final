package com.animal.model;

public class Animal {
	private int num ;
	private String name ;
	private String type ;
	private String kind ;
	private String anigender ;

	private String image01 ;
	private String image02 ;
	private String image03 ;
	
	private String remark;
	private String category;
	private String inputdate;
	
	public Animal() {
		// TODO Auto-generated constructor stub
	}

	public Animal(int num, String name, String type, String kind, String anigender, String image01, String image02,
			String image03, String remark, String category, String inputdate) {
		super();
		this.num = num;
		this.name = name;
		this.type = type;
		this.kind = kind;
		this.anigender = anigender;
		this.image01 = image01;
		this.image02 = image02;
		this.image03 = image03;
		this.remark = remark;
		this.category = category;
		this.inputdate = inputdate;
	}

	@Override
	public String toString() {
		return "Animal [num=" + num + ", name=" + name + ", type=" + type + ", kind=" + kind + ", anigender="
				+ anigender + ", image01=" + image01 + ", image02=" + image02 + ", image03=" + image03 + ", remark="
				+ remark + ", category=" + category + ", inputdate=" + inputdate + "]";
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getAnigender() {
		return anigender;
	}

	public void setAnigender(String anigender) {
		this.anigender = anigender;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	
	

}
