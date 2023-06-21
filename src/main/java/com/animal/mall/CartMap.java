package com.animal.mall;

import java.util.HashMap;

import java.util.Map;

import com.animal.model.CartItem;

// 이 클래스는 테스트용으로 사용되는 한시적 클래스입니다.
// 차후 데이터 베이스를 이용하여 처리됩니다.
public class CartMap {
	// itemList : 우리 매장에서 판매 가능한 상품 목록들 
	private Map<Integer, CartItem> itemList = null ;
	
	public CartMap() {
		this.itemList = new HashMap<Integer, CartItem>();
		
		// String mid, int pnum, String pname, int qty, int price, String image01, int point) 
		itemList.put(1, new CartItem("admin", 1, "강아지 용품", 100, 1000, "image01.jpg", 10));
		itemList.put(2, new CartItem("admin", 1, "강아지 용품", 200, 2000, "image02.jpg", 20));
		itemList.put(3, new CartItem("admin", 1, "강아지 용품", 300, 3000, "image03.jpg", 30));
		itemList.put(4, new CartItem("admin", 1, "고양이 용품", 400, 4000, "image04.jpg", 40));
		itemList.put(5, new CartItem("admin", 1, "고양이 용품", 500, 5000, "image05.jpg", 50));
		itemList.put(6, new CartItem("admin", 1, "고양이 용품", 600, 6000, "image06.jpg", 60));
	}
	public Map<Integer, CartItem> getItemList() {
		return itemList;
	}	
}