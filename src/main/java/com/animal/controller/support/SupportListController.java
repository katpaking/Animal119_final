package com.animal.controller.support;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.SupportDao;
import com.animal.model.support;
import com.animal.utility.Paging;

public class SupportListController extends SuperClass{
	@Override // 게시물 목록을 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response); 
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		
		if(mode==null) {mode="all";}
		if(keyword==null) {keyword="";}
		
		SupportDao dao = new SupportDao() ;
		List<support> lists = null ;
		boolean isGrid = false ;
		
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword) ;
			String url = request.getContextPath() + "/Animal?command=suList";
					
				//	super.getUrlInfo("suList");
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);			
			
			System.out.print("필드 검색 파라미터 확인 : ");
			System.out.println(pageInfo.getFlowParameter()); 
			
			// lists = dao.SelectAll(); // 이전 버전			
			lists = dao.SelectAll(pageInfo);
			
			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo); // 페이징 정보를 바인딩
			
			String gotopage = "support/suList.jsp" ;			
			super.Gotopage(gotopage);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
