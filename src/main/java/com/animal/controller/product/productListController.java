package com.animal.controller.product;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.ProductDao;
import com.animal.model.Product;
import com.animal.utility.Paging;

public class productListController extends SuperClass {
	
	@Override // 상품 목록을 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		boolean isGrid = true ;
		
		ProductDao dao = new ProductDao();
		List<Product> lists = null ;
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword) ;
			String url = super.getUrlInfo("prList");
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			System.out.println(this.getClass());
			System.out.println(pageInfo); 

			lists = dao.SelectAll(pageInfo);	
			
			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo); 
			
			super.Gotopage("product/prList.jsp"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
