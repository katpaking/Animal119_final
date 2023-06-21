package com.animal.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.ProductDao;
import com.animal.model.Product;

public class productDetailController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		int num = Integer.parseInt(request.getParameter("num")) ;
		ProductDao dao = new ProductDao();
		
		try {
			Product bean = dao.GetDataByPk(num);
			request.setAttribute("bean", bean); 
			super.Gotopage("product/prDetail.jsp"); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
