package com.animal.controller.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.frboard.frBoardDetailController;
import com.animal.dao.CommentDao;
import com.animal.model.comment;

public class CommentInsertController extends SuperClass{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	
	
	comment bean = new comment();
	
	bean.setNo(Integer.parseInt(request.getParameter("no")));
	bean.setContent(request.getParameter("content"));
	bean.setWriter(request.getParameter("writer"));
	
	CommentDao dao = new CommentDao();
	
	int cnt = -1;
	
	try {
		cnt = dao.InsertData(bean);
		
		new frBoardDetailController().doPost(request, response);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

}
