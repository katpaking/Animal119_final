package com.animal.controller.adopt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.AdoptDao;
import com.animal.model.Adopt;

public class AdoptInsertController extends SuperClass{
	private final String PREFIX = "adopt/" ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response); 
		String gotopage = PREFIX + "adoptInsertForm.jsp" ; 
		
		super.Gotopage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String writer = request.getParameter("writer") ;
		String subject = request.getParameter("subject") ;
		String phone = request.getParameter("phone");
		String regdate = request.getParameter("regdate") ;		
		
		
		Adopt bean = new Adopt() ;
		
		
		bean.setWriter(writer);
		bean.setSubject(subject);
		bean.setPhone(phone);
		bean.setRegdate(regdate);
		
		
		AdoptDao dao = new AdoptDao();
		
		
		int cnt = -1 ;// 가정) -1은 실패 
		try {
			cnt = dao.InsertData(bean);
			
			if(cnt == -1) { // 실패
				new AdoptInsertController().doGet(request, response);
				
			}else { // 성공
				new AdoptListController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
}