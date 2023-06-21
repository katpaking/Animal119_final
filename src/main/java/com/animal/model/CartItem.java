package com.animal.model;

// 카트에 담은 상품 1개에 대한 정보를 저장하고 있는 클래스
public class CartItem {
	private String mid ; // 고객 아이디
	private int pnum ; // 상품 번호
	private String pname ; // 상품명 
	private int qty ; // 구매 수량
	private int price ; // 단가
	private String image01 ; // 이미지 이름
	private int point ; // 적립 포인트
	
	public CartItem() {}

	public CartItem(String mid, int pnum, String pname, int qty, int price, String image01, int point) {
		super();
		this.mid = mid;
		this.pnum = pnum;
		this.pname = pname;
		this.qty = qty;
		this.price = price;
		this.image01 = image01;
		this.point = point;
	}

	@Override
	public String toString() {
		return "CartItem [mid=" + mid + ", pnum=" + pnum + ", pname=" + pname + ", qty=" + qty + ", price=" + price
				+ ", image01=" + image01 + ", point=" + point + "]";
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage01() {
		return image01;
	}

	public void setImage01(String image01) {
		this.image01 = image01;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	

}