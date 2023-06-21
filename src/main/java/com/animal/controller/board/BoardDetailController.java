package com.animal.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.BoardDao;
import com.animal.model.board;

public class BoardDetailController extends SuperClass{
	@Override // 회원의 아이디를 이용하여 회원 상세 정보를 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter("no")) ;
		
		BoardDao dao = new BoardDao() ;
		
		board bean = null ;
		try {
			bean = dao.getDataByPk(no);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean==null){
			super.setAlertMessage("잘못된 게시물 정보입니다.");
			super.Gotopage("common/home.jsp");
		}else {
			request.setAttribute("bean", bean) ;
			super.Gotopage("board/boDetail.jsp"); 
		}
	}
}

