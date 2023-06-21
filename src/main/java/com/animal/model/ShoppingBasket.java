package com.animal.model;

// 나의 wishList 품목 1개를 의미하는 bean 클래스
public class ShoppingBasket {	
	private String mid ;
	private int pnum ;
	private int qty ;
	
	public ShoppingBasket() {}
	
	@Override
	public String toString() {
		return "ShoppingBasket [mid=" + mid + ", pnum=" + pnum + ", qty=" + qty + "]";
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}