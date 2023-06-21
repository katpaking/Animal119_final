package com.animal.controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.MemberDao;
import com.animal.model.Member;
import com.animal.utility.Paging;

public class MemberListController extends SuperClass {
	@Override // 회원 목록을 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);		
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		
		if(mode==null) {mode="all";}
		if(keyword==null) {keyword="";}
		
		boolean isGrid = false ;

		MemberDao dao = new MemberDao();
		List<Member> lists = null;
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword) ;
			String url = request.getContextPath() + "/Animal?command=meList";
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);			
			
			System.out.print("필드 검색 파라미터 확인 : ");
			System.out.println(pageInfo.getFlowParameter()); 
			
			lists = dao.SelectAll(pageInfo);

			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo); // 페이징 정보를 바인딩

			String gotopage = "member/meList.jsp";
			super.Gotopage(gotopage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

