package com.animal.mall;

import java.util.Map;
import java.util.Scanner;

import com.animal.model.CartItem;

public class CartTest {
	public static void main(String[] args) {
		CartManager cartMgr = new CartManager() ;
		CartMap cartMap = new CartMap() ;
		
		Scanner scan = new Scanner(System.in) ;
		int pnum =0, qty = 0 ;
		
		while(true) {
			System.out.println("\n1.카트 목록, 2.추가, 3.삭제, 4.수정, 5.품목 개수 확인, 0.종료");
			int menu = scan.nextInt() ;
			switch (menu) {
			case 1: 
				System.out.println("구매 수량 정보");
				Map<Integer, Integer> cartList = cartMgr.GetAllCartList() ;
				System.out.println(cartList); 
				
				System.out.println("\n어떤 걸 구매하나?");
				
				// itemList : 이 매장에서 판매하는 품목 리스트
				Map<Integer, CartItem> itemList = cartMap.getItemList();
				for(Integer key : cartList.keySet()) {
					CartItem item = itemList.get(key);
					System.out.println(item);
				}
				break ; 
				
			case 2: 
				System.out.print("추가할 상품 번호 입력 : ");
				pnum = scan.nextInt() ;
				
				System.out.print("구매 수량 입력 : ");
				qty = scan.nextInt() ;
				
				cartMgr.AddCart(pnum, qty);
				break ;	
				
			case 3: 
				System.out.print("삭제할 상품 번호 입력 : ");
				pnum = scan.nextInt() ;
				
				cartMgr.DeleteCart(pnum);
				break ;	
				
			case 4: 
				System.out.println("이전 개수에 누적됩니다.");
				System.out.print("수정할 상품 번호 입력 : ");
				pnum = scan.nextInt() ;
				
				System.out.print("구매 수량 입력 : ");
				qty = scan.nextInt() ;
				
				cartMgr.EditCart(pnum, qty);
				
				break ;	
				
			case 5: 
				int size = cartMgr.GetCartSize();
				System.out.println("구매할 품목 개수 : " + size);
				break ;
				
			case 0: 
				System.out.println("프로그램 종료");
				System.exit(0); // 정상적인 종료임을 운영체제에게 알려 주는 역할
			}			
		}
	}
}
