package com.animal.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.ProductDao;
import com.animal.model.Product;
import com.oreilly.servlet.MultipartRequest;

public class productUpdateController extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = 0;
		ProductDao dao = null ;
		Product bean = null ;
		
		try {
			dao = new ProductDao() ;			
			num = Integer.parseInt(request.getParameter("num")) ; // 수정할 상품 번호
			
			bean = dao.GetDataByPk(num) ;
			if(bean!=null) {
				request.setAttribute("bean", bean);
			}
			super.Gotopage("product/prUpdateForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			super.Gotopage("product/prList.jsp");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Product bean = new Product(); 
				
		// 상품 등록과는 다르게 수정은 상품 번호를 챙겨야 합니다.
		bean.setNum(getNumberData(mr.getParameter("num")));
		
		bean.setCategory(mr.getParameter("category"));
		bean.setName(mr.getParameter("name"));
		bean.setCompany(mr.getParameter("company"));
		bean.setComments(mr.getParameter("comments"));
		bean.setRemark(mr.getParameter("remark"));
		bean.setInputdate(mr.getParameter("inputdate"));
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		bean.setImage03(mr.getFilesystemName("image03"));
		
		bean.setStock(getNumberData(mr.getParameter("stock")));
		bean.setPrice(getNumberData(mr.getParameter("price")));
		bean.setPoint(getNumberData(mr.getParameter("point")));
		
		ProductDao dao = new ProductDao() ;
		int cnt = -1 ; 
		try {
			cnt = dao.UpdateData(bean);
			
			if(cnt==-1) {
				super.Gotopage("product/prUpdateForm.jsp");
				
			}else {
				String gotopage = super.getUrlInfo("prList");
				gotopage += "&pageNumber=" + mr.getParameter("pageNumber");
				gotopage += "&pageSize=" + mr.getParameter("pageSize");
				gotopage += "&mode=" + mr.getParameter("mode");
				gotopage += "&keyword=" + mr.getParameter("keyword");

				response.sendRedirect(gotopage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private int getNumberData(String parameter) {
		boolean flag = false ;				
		
		flag = parameter==null || parameter.equals("") || parameter.equals("null") ;
		
		System.out.println(this.getClass() + " getNumberData method called"); 
		
		return !flag ? Integer.parseInt(parameter) : 0; 
	}	
	
}
