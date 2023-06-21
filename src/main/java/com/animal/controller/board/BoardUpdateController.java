package com.animal.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.BoardDao;
import com.animal.model.board;
import com.oreilly.servlet.MultipartRequest;

public class BoardUpdateController extends SuperClass  {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		
		int no = 0 ;
		BoardDao dao = null;
		board bean = null;
		
		try {
			dao = new BoardDao();
			no = Integer.parseInt(request.getParameter("no"));
			
		  bean = dao.getDataByPk(no);
		  if(bean!=null) {
			request.setAttribute("bean", bean);
		}
		  super.Gotopage("board/boUpdateForm.jsp");
		  
	} catch (Exception e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
		super.Gotopage("board/boList.jsp");
	   }
	
     }
	
		
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
		
		
		board bean = new board();
		
		bean.setNo(Integer.parseInt(request.getParameter("no")));
		
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setContent(request.getParameter("content"));
		bean.setRegdate(request.getParameter("regdate"));
		bean.setImage01(request.getParameter("image01"));
		bean.setImage02(request.getParameter("image02"));
		bean.setImage03(request.getParameter("image03"));
		
		bean.setReadhit(Integer.parseInt(request.getParameter("readhit")));
		
		bean.setCategory(request.getParameter("category"));
		
		bean.setGroupno(Integer.parseInt(request.getParameter("groupno")));
		bean.setOrderno(Integer.parseInt(request.getParameter("orderno")));
		bean.setDepth(Integer.parseInt(request.getParameter("depth")));

		BoardDao dao = new BoardDao();
		
		int cnt = -1 ; 
		
		try {
		cnt = dao.UpdateData(bean);
		if(cnt == -1) {
			super.Gotopage("board/boUpdateForm.jsp");
			
		}else {
			String gotopage = super.getUrlInfo("boList");
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
