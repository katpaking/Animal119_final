package com.animal.controller.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;


import com.animal.dao.SupportDao;
import com.animal.model.support;

public class SupportUpdateController extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);

		int no = 0;
		SupportDao dao = null;
		support bean = null;

		try {
			dao = new SupportDao();
			no = Integer.parseInt(request.getParameter("no"));

			bean = dao.getDataByPk(no);
			if (bean != null) {
				request.setAttribute("bean", bean);
			}
			super.Gotopage("support/suUpdateForm.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			super.Gotopage("support/suList.jsp");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);

		
		 int no = Integer.parseInt(request.getParameter("no"));
		 
		   String writer = request.getParameter("writer") ; 
		   String subject = request.getParameter("subject") ; 
		   String phone = request.getParameter("phone"); 
		   String content = request.getParameter("content") ; 
		   String hopeday = request.getParameter("hopeday"); 
		   String regdate = request.getParameter("regdate") ;
			
			 int groupno = Integer.parseInt(request.getParameter("groupno")); 
			 int orderno = Integer.parseInt(request.getParameter("orderno"));
			 
		
		  support bean = new support();

		/*
		 * bean.setNo(Integer.parseInt(request.getParameter("no")));
		 * 
		 * bean.setWriter(request.getParameter("writer"));
		 * bean.setSubject(request.getParameter("subject"));
		 * bean.setPhone(request.getParameter("phone"));
		 * bean.setContent(request.getParameter("content"));
		 * bean.setHopeday(request.getParameter("hopeday"));
		 * bean.setRegdate(request.getParameter("regdate"));
		 */
		/*
		 * bean.setGroupno(Integer.parseInt(request.getParameter("groupno")));
		 * bean.setOrderno(Integer.parseInt(request.getParameter("orderno")));
		 * 
		 */
		bean.setNo(no); 
	     bean.setWriter(writer); 
	     bean.setSubject(subject);
	     bean.setPhone(phone); 
	     bean.setContent(content); 
	     bean.setHopeday(hopeday);
		 bean.setRegdate(regdate);
		
			
			  bean.setGroupno(groupno); 
			  bean.setOrderno(orderno);
			 

		SupportDao dao = new SupportDao();

		int cnt = -1;
		try {
			cnt = dao.UpdateData(bean);

			if (cnt == -1) {
				new SupportUpdateController().doGet(request, response);

			} else {
				String gotopage = super.getUrlInfo("suList");
				gotopage += "&pageNumber=" + request.getParameter("pageNumber");
				gotopage += "&pageSize=" + request.getParameter("pageSize");
				gotopage += "&mode=" + request.getParameter("mode");
				gotopage += "&keyword=" + request.getParameter("keyword");

				new SupportListController().doGet(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private int getNumberData(String parameter) {
		boolean flag = false;

		flag = parameter == null || parameter.equals("") || parameter.equals("null");

		System.out.println(this.getClass() + "getNumberData method called");
		return !flag ? Integer.parseInt(parameter) : 0;

	}

}
