package com.animal.controller.frboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.frBoardDao;
import com.animal.model.frboard;

public class frBoardDetailController extends SuperClass{
	@Override // 회원의 아이디를 이용하여 회원 상세 정보를 조회합니다.
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter("no")) ;
		frBoardDao dao = new frBoardDao() ;
		frboard bean = null ;
		
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
			super.Gotopage("frboard/frboDetail.jsp"); 
		}
	}
	
	
}

