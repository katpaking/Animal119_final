package com.animal.controller.mall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.product.productListController;

// 상품 상세 페이지에서 장바구니 버튼을 클릭하였습니다.
public class MallInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String message = null  ;
		
		if(super.loginfo==null) {
			super.youNeededLogin();
			return ;
		}
			
		// 누군가 로그인 되어 있음.
		int stock = Integer.parseInt(request.getParameter("stock")) ; // 재고 수량
		int qty = Integer.parseInt(request.getParameter("qty")) ; // 구매할 수량
		
		if(stock < qty) { // 재고 수량 초과
			message = "재고 수량이 부족합니다.";
			super.setAlertMessage(message);
			new productListController().doGet(request, response) ;
			
		}else {
			int num = Integer.parseInt(request.getParameter("num")) ; // 상품 번호
			super.mycart.AddCart(num, qty) ;
			super.session.setAttribute("mycart", mycart) ;
			new MallListController().doGet(request, response) ;
		}
	}
}