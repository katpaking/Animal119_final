package com.animal.controller.animal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.AnimalDao;
import com.animal.model.Animal;
import com.oreilly.servlet.MultipartRequest;

public class AnimalUpdateController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = 0;
		AnimalDao dao = null ;
		Animal bean = null ;
		
		try {
			dao = new AnimalDao() ;			
			num = Integer.parseInt(request.getParameter("num")) ; // 수정할 상품 번호
			
			bean = dao.GetDataByPk(num) ;
			if(bean!=null) {
				request.setAttribute("bean", bean);
			}
			super.Gotopage("animal/aniUpdateForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			super.Gotopage("animal/aniList.jsp");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Animal bean = new Animal(); 
				
		// 상품 등록과는 다르게 수정은 상품 번호를 챙겨야 합니다.
		bean.setNum(getNumberData(mr.getParameter("num")));
		
		bean.setCategory(mr.getParameter("category"));
		bean.setName(mr.getParameter("name"));
		bean.setType(mr.getParameter("type"));
		bean.setKind(mr.getParameter("kind"));
		bean.setAnigender(mr.getParameter("anigender"));
		bean.setRemark(mr.getParameter("remark"));
		bean.setInputdate(mr.getParameter("inputdate"));
		
		bean.setImage01(mr.getFilesystemName("image01"));
		bean.setImage02(mr.getFilesystemName("image02"));
		bean.setImage03(mr.getFilesystemName("image03"));
		
		AnimalDao dao = new AnimalDao() ;
		int cnt = -1 ; 
		try {
			cnt = dao.UpdateData(bean);
			
			if(cnt==-1) {
				super.Gotopage("animal/aniUpdateForm.jsp");
				
			}else {
				String gotopage = super.getUrlInfo("aniList");
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
