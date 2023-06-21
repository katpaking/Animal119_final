package com.animal.controller.frboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.frBoardDao;

public class frBoardDeleteController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		frBoardDao dao = new frBoardDao();
		int cnt = -1 ;
		
		try {
			int no = Integer.parseInt(request.getParameter("no")) ;
			cnt = dao.DeleteData(no);
			
			new frBoardListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
