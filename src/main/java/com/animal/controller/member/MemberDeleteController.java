package com.animal.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.MemberDao;

public class MemberDeleteController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		MemberDao dao = new MemberDao() ;
		int cnt = -1; 
				
		try {
			String id = request.getParameter("id") ;
			cnt = dao.DeleteData(id);
			super.session.invalidate(); 
			new MemberInsertController().doGet(request, response); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
