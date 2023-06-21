package com.animal.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.CategoryDao;
import com.animal.dao.ProductDao;
import com.animal.model.Category;
import com.animal.model.Product;
import com.oreilly.servlet.MultipartRequest;

public class productInsertController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 상품 등록 화면이 보이기 전에 category 목록 테이블을 읽어서 콤보 박스에 채워 넣기
		CategoryDao dao = new CategoryDao();
		List<Category> categories = null ;
		try {
			categories = dao.GetCategoryList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("categories", categories); 
		
		
		String gotopage = "product/prInsertForm.jsp";
		super.Gotopage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Product bean = new Product(); 
		
		// 상품 등록시 상품 번호는 시퀀스가 알아서 처리해주므로 신경 쓸 필요가 없습니다.
		//bean.setNum(getNumberData(mr.getParameter("num")));		
		
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
			cnt = dao.InsertData(bean);
			
			if(cnt==-1) {
				super.Gotopage("product/prInsertForm.jsp");
				
			}else {
				new productListController().doGet(request, response);
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
