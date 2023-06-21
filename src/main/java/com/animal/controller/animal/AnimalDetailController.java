package com.animal.controller.animal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.AnimalDao;
import com.animal.model.Animal;

public class AnimalDetailController extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		super.doGet(request, response);
		
		int num = Integer.parseInt(request.getParameter("num")) ;
		AnimalDao dao = new AnimalDao();
		
		try {
			Animal bean = dao.GetDataByPk(num);
			request.setAttribute("bean", bean); 
			super.Gotopage("animal/aniDetail.jsp"); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
