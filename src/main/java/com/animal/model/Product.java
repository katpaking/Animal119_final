package com.animal.model;

public class Product {
	private int num ;
	private String name ;
	private String company ;
	private String comments ;
	private String image01 ;
	private String image02 ;
	private String image03 ;
	private int stock ;	
	private int price ;
	private int point ;
	private String remark;
	private String category;
	private String inputdate;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int num, String name, String company, String comments, String image01, String image02, String image03, int stock, int price, int point, String remark, String category, String inputdate) {
		super();
		this.num = num;
		this.name = name;
		this.company = company;
		this.comments = comments;
		this.image01 = image01;
		this.image02 = image02;
		this.image03 = image03;
		this.stock = stock;
		this.price = price;
		this.point = point;
		this.remark = remark;
		this.category = category;
		this.inputdate = inputdate;
	}

	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", company=" + company + ", comments=" + comments
				+ ", image01=" + image01 + ", image02=" + image02 + ", image03=" + image03 + ", stock=" + stock
				+ ", price=" + price + ", point=" + point + ", remark=" + remark + ", category=" + category
				+ ", inputdate=" + inputdate + "]";
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
