package com.animal.mall;

import java.util.HashMap;
import java.util.Map;

// 카트의 정보들을 관리해주는 카트 관리자 클래스 
public class CartManager {
	// carts : 카트에 담겨 있는 품목들을 저장하고 있는 맵
	// 카트 품목은 <상품번호, 구매수량>으로 구성되어 있습니다.
	private Map<Integer, Integer> carts = null ;
	
	public CartManager() {
		this.carts = new HashMap<Integer, Integer>();
	}
	
	// 카트에 상품을 추가합니다.
	public void AddCart(int pnum, int qty) {
		// pnum(상품 번호), qty(구매 수량)
		if(this.carts.containsKey(pnum)) { // 품목이 이미 들어 있는 경우 
			int newQty = this.carts.get(pnum) + qty ;
			this.carts.put(pnum, newQty);
		}else {
			this.carts.put(pnum, qty);
		}
	}
	
	// 카트의 상품 수량을 변경합니다.(이전 데이터 덮어 쓰기 : overwrite)
	public void EditCart(int pnum, int qty) {
		this.carts.put(pnum, qty);
	}	
	
	// 카트의 특정 상품을 삭제합니다.
	public void DeleteCart(int pnum) {
		this.carts.remove(pnum) ;
	}
	
	// 결제시 카트 내역을 모두 비웁니다.
	public void RemoveAllCart() {
		this.carts.clear();
	}
	
	// 카트 내역 정보를 반환해 줍니다.
	public Map<Integer, Integer> GetAllCartList(){
		return this.carts ;
	}
	
	// 카트 내의 품목 개수를 반환해 줍니다.
	public int GetCartSize() {
		return this.carts.size() ;
	}
}