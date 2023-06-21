package com.animal.controller.mall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.MallDao;
import com.animal.model.CartItem;
import com.animal.model.Order;

public class MallDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		if(super.loginfo==null) {
			super.youNeededLogin();
			return ;
		}
		
		MallDao madao = new MallDao()  ;
		
		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			
			Order order = madao.getDetailHistory(oid) ;
			
			List<CartItem> lists = madao.ShowDetail(oid) ;
			
			request.setAttribute("order", order); // 주문 정보
			request.setAttribute("lists", lists); // 쇼핑 정보
			
			super.GotoPage("mall/maDetailHistory.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




