package com.animal.controller.mall;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.product.productListController;
import com.animal.dao.MallDao;

public class MallCalculateController extends SuperClass{
	@Override // 카트 목록 내용을 이용하여 결제를 진행합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		MallDao madao = new MallDao() ;
		
		try {			
			int totalPoint = (Integer)super.session.getAttribute("totalPoint") ;
			
			// Calculate(로그인한사람, 결제정보, 총적립포인트)
			
			madao.Calculate(super.loginfo, super.mycart.GetAllCartList(), totalPoint) ; 
			
			// 결제가 마무리되면 세션 영역의 모든 항목을 삭제합니다.
			super.session.removeAttribute("mycart");
			super.session.removeAttribute("totalAmount");
			super.session.removeAttribute("totalPoint");
			super.session.removeAttribute("orderList");
			
			new productListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
