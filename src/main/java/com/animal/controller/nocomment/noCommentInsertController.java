package com.animal.controller.nocomment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.notice.NoticeDetailController;
import com.animal.dao.noCommentDao;
import com.animal.model.nocomment;

public class noCommentInsertController extends SuperClass{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	
	
	  nocomment bean = new nocomment();
	
	bean.setNo(Integer.parseInt(request.getParameter("no")));
	bean.setContent(request.getParameter("content"));
	bean.setWriter(request.getParameter("writer"));
	
	noCommentDao dao = new noCommentDao();
	
	int cnt = -1;
	
	try {
		cnt = dao.InsertData(bean);
		
		new NoticeDetailController().doPost(request, response);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

}
