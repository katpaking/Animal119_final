package com.animal.controller.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.frboard.frBoardDetailController;
import com.animal.dao.CommentDao;

public class CommentDeleteController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int cnum = Integer.parseInt(request.getParameter("cnum"));
		CommentDao dao = new CommentDao();
		
	    int cnt = -1 ;
	    
	    try {
			cnt = dao.DeleteData(cnum);
			
			new frBoardDetailController().doGet(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

}
