package com.animal.controller.animal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.AnimalDao;

public class AnimalDeleteController extends SuperClass {
	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = Integer.parseInt(request.getParameter("num")) ;
		AnimalDao dao = new AnimalDao() ;
		int cnt = -1 ;
		
		try {
			cnt = dao.DeleteData(num) ;			
			
			new AnimalListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
