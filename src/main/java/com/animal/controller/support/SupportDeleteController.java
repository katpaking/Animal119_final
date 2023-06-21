package com.animal.controller.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.SupportDao;



public class SupportDeleteController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	   super.doGet(request, response);
	
		SupportDao dao = new SupportDao();
		int cnt = -1 ;
		
		try {
			int no = Integer.parseInt(request.getParameter("no")) ;
			cnt = dao.DeleteData(no);
			
			new SupportListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

