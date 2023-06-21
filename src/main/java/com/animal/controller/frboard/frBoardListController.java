package com.animal.controller.frboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.frBoardDao;
import com.animal.model.frboard;
import com.animal.utility.Paging;

public class frBoardListController extends SuperClass {
  
	@Override//게시물 목록
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		if(mode==null) {mode="all";}
		if(keyword==null) {keyword="";}
		
		frBoardDao dao = new frBoardDao();
		List<frboard> lists = null;
		boolean isGrid = false;
		
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword); // 이건 차후 수정 가능
		    String url = super.getUrlInfo("frboList");
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			System.out.print("필드 검색 파라미터 확인 : ");
			System.out.println(pageInfo.getFlowParameter()); 
			
			lists = dao.SelectAll(pageInfo);
			
			request.setAttribute("datalist", lists);
			
			request.setAttribute("pageInfo", pageInfo); //페이징 정보를 바인딩
			
			String gotopage = "frboard/frboList.jsp";
			super.Gotopage(gotopage);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
