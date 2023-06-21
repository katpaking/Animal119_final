package com.animal.controller.animal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.AnimalDao;
import com.animal.dao.CategoryDao;
import com.animal.model.Animal;
import com.animal.model.Category;
import com.oreilly.servlet.MultipartRequest;

public class AnimalInsertController extends SuperClass{
	
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
		
		String gotopage = "animal/aniInsertForm.jsp";
		super.Gotopage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Animal bean = new Animal(); 
		
		// 상품 등록시 상품 번호는 시퀀스가 알아서 처리해주므로 신경 쓸 필요가 없습니다.
		//bean.setNum(getNumberData(mr.getParameter("num")));		
		
		bean.setName(mr.getParameter("name"));
		bean.setType(mr.getParameter("type"));
		bean.setAnigender(mr.getParameter("anigender"));
		bean.setKind(mr.getParameter("kind"));
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		bean.setImage03(mr.getFilesystemName("image03"));
		
		bean.setRemark(mr.getParameter("remark"));
		bean.setCategory(mr.getParameter("category"));
		bean.setInputdate(mr.getParameter("inputdate"));
		
		AnimalDao dao = new AnimalDao() ;
		int cnt = -1 ; 
		try {
			cnt = dao.InsertData(bean);
			
			if(cnt==-1) {
				super.Gotopage("animal/aniInsertForm.jsp");
				
			}else {
				new AnimalListController().doGet(request, response);
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
