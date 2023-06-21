package com.animal.controller.frboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.animal.controller.SuperClass;
import com.animal.dao.frBoardDao;
import com.animal.model.frboard;
import com.oreilly.servlet.MultipartRequest;


public class frBoardUpdateController extends SuperClass  {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		
		int no = 0 ;
		frBoardDao dao = null;
		frboard bean = null;
		
		try {
			dao = new frBoardDao();
			no = Integer.parseInt(request.getParameter("no"));
			
		  bean = dao.getDataByPk(no);
		  if(bean!=null) {
			request.setAttribute("bean", bean);
		}
		  super.Gotopage("frboard/frboUpdateForm.jsp");
		  
	} catch (Exception e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
		super.Gotopage("frboard/frboList.jsp");
	   }
	
     }
	
		
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		frboard bean = new frboard();
		
		bean.setNo(getNumberData(mr.getParameter("no")));
	    bean.setCategory(mr.getParameter("category"));
	    bean.setWriter(mr.getParameter("writer"));
	    bean.setSubject(mr.getParameter("subject"));
	    
	    bean.setImage01(mr.getFilesystemName("image01"));
	    
	    bean.setContent(mr.getParameter("content"));
	    bean.setRegdate(mr.getParameter("regdate"));
	    
	    bean.setReadhit(getNumberData(mr.getParameter("readhit")));
	    bean.setGroupno(getNumberData(mr.getParameter("groupno")));
	    bean.setOrderno(getNumberData(mr.getParameter("orderno")));
	    bean.setDepth(getNumberData(mr.getParameter("depth")));

		frBoardDao dao = new frBoardDao();
		
		int cnt = -1 ; 
		
		try {
		cnt = dao.UpdateData(bean);
		if(cnt == -1) {
			super.Gotopage("frboard/frboUpdateForm.jsp");
			
		}else {
			String gotopage = super.getUrlInfo("frboList");
			gotopage += "&pageNumber=" + request.getParameter("pageNumber");
			gotopage += "&pageSize=" + request.getParameter("pageSize");
			gotopage += "&mode=" + request.getParameter("mode");
			gotopage += "&keyword=" + request.getParameter("keyword");
			
			new frBoardListController().doGet(request, response);
		}
		
		}catch (Exception e) {
	        e.printStackTrace();	
	        
	        new frBoardUpdateController().doGet(request, response);
	}
	
	}

	private int getNumberData(String parameter) {
		 boolean flag = false;
		    
		    flag = parameter==null || parameter.equals("")|| parameter.equals("null");
			
		    System.out.println(this.getClass() + "getNumberData method called");
		    return !flag ? Integer.parseInt(parameter) : 0 ;
	}

}
