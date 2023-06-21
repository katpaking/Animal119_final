package com.animal.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.NoticeDao;
import com.animal.model.notice;
import com.oreilly.servlet.MultipartRequest;

public class NoticeUpdateController2 extends SuperClass  {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		
		int no = 0 ;
		NoticeDao dao = null;
		notice bean = null;
		
		try {
			dao = new NoticeDao();
			no = Integer.parseInt(request.getParameter("no"));
			
		  bean = dao.getDataByPk(no);
		  if(bean!=null) {
			request.setAttribute("bean", bean);
		}
		  super.Gotopage("notice/noUpdateForm.jsp");
		  
	} catch (Exception e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
		super.Gotopage("notice/noList.jsp");
	   }
	
     }
	
		
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
		
		int no = 0;
		
		notice bean = new notice();
		
		bean.setNo(Integer.parseInt(request.getParameter("no")));
		
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setContent(request.getParameter("content"));
		bean.setRegdate(request.getParameter("regdate"));
		bean.setImage01(request.getParameter("image01"));
		
		bean.setReadhit(Integer.parseInt(request.getParameter("readhit")));
		
		bean.setCategory(request.getParameter("category"));
		
		bean.setGroupno(Integer.parseInt(request.getParameter("groupno")));
		bean.setOrderno(Integer.parseInt(request.getParameter("orderno")));
		bean.setDepth(Integer.parseInt(request.getParameter("depth")));

		NoticeDao dao = new NoticeDao();
		
		int cnt = -1 ; 
		
		try {
		cnt = dao.UpdateData(bean);
		if(cnt == -1) {
			super.Gotopage("notice/noUpdateForm.jsp");
			
		}else {
			String gotopage = super.getUrlInfo("noList");
			gotopage += "&pageNumber=" + request.getParameter("pageNumber");
			gotopage += "&pageSize=" + request.getParameter("pageSize");
			gotopage += "&mode=" + request.getParameter("mode");
			gotopage += "&keyword=" + request.getParameter("keyword");
			
			response.sendRedirect(gotopage);
		}
		
		}catch (Exception e) {
	        e.printStackTrace();	
	}
	
	}

	private int getNumberData(String parameter) {
		 boolean flag = false;
		    
		    flag = parameter==null || parameter.equals("")|| parameter.equals("null");
			
		    System.out.println(this.getClass() + "getNumberData method called");
		    return !flag ? Integer.parseInt(parameter) : 0 ;
	}

}
