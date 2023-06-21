package com.animal.controller.nocomment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.notice.NoticeDetailController;
import com.animal.dao.noCommentDao;

public class noCommentDeleteController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		noCommentDao dao = new noCommentDao();
		
	    int cnt = -1 ;
	    
	    try {
			cnt = dao.DeleteData(cnum);
			
			new NoticeDetailController().doGet(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

}
