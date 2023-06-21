package com.animal.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.NoticeDao;
import com.animal.model.notice;

public class NoticeDetailController extends SuperClass{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
	@Override // 회원의 아이디를 이용하여 회원 상세 정보를 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter("no")) ;
		
		NoticeDao dao = new NoticeDao() ;
		
		notice bean = null ;
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
			super.Gotopage("notice/noDetail.jsp"); 
		}
	}
}

