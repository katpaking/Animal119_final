package com.animal.controller.mall;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.product.productListController;

public class MallUpdateController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		if(super.mycart.GetCartSize()==0) {
			super.setAlertMessage("카트 품목이 존재하지 않아서 상품 목록 페이지로 이동합니다."); 
			new productListController().doGet(request, response);
			
		}else {
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			
			super.mycart.EditCart(pnum, qty);
			new MallListController().doGet(request, response);			
		}
	}
}