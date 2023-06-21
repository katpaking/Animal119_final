package com.animal.controller.mall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.product.productListController;

public class MallDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);		
		
		if(super.mycart.GetCartSize()==0) {
			super.setAlertMessage("카트 품목이 존재하지 않아서 상품 목록 페이지로 이동합니다."); 
			new productListController().doGet(request, response);
			
		}else {
			// 카트 목록(wishList)에서 삭제하고자 하는 상품 번호
			int pnum = Integer.parseInt(request.getParameter("pnum")) ;
			super.mycart.DeleteCart(pnum);
			 
			super.session.setAttribute("mycart", super.mycart);
			new MallListController().doGet(request, response); 
		}		
	}
}