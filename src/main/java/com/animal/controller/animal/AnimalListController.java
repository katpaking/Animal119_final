package com.animal.controller.animal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.AnimalDao;
import com.animal.model.Animal;
import com.animal.utility.Paging;

public class AnimalListController extends SuperClass{
	
	@Override // 상품 목록을 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		boolean isGrid = true ;
		
		AnimalDao dao = new AnimalDao();
		List<Animal> lists = null ;
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword) ;
			String url = super.getUrlInfo("aniList");
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			System.out.println(this.getClass());
			System.out.println(pageInfo); 

			// lists = dao.SelectAll(); // 이전 버전
			lists = dao.SelectAll(pageInfo);	
			
			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo); 
			
			super.Gotopage("animal/aniList.jsp"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
