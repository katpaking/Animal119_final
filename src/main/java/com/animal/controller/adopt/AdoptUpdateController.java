package com.animal.controller.adopt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.AdoptDao;
import com.animal.model.Adopt;


public class AdoptUpdateController extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);

		int no = 0;
		AdoptDao dao = null;
		Adopt bean = null;

		try {
			dao = new AdoptDao();
			no = Integer.parseInt(request.getParameter("no"));

			bean = dao.getDataByPk(no);
			if (bean != null) {
				request.setAttribute("bean", bean);
			}
			super.Gotopage("adopt/AdoptUpdateForm.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			super.Gotopage("adopt/AdoptList.jsp");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);

		
		 int num = Integer.parseInt(request.getParameter("num"));
		 
		   String writer = request.getParameter("writer") ; 
		   String subject = request.getParameter("subject") ; 
		   String phone = request.getParameter("phone"); 
		   String regdate = request.getParameter("regdate") ;
			
			 int groupno = Integer.parseInt(request.getParameter("groupno")); 
			 int orderno = Integer.parseInt(request.getParameter("orderno"));
			 
		
			 Adopt bean = new Adopt();

		bean.setNum(num); 
	     bean.setWriter(writer); 
	     bean.setSubject(subject);
	     bean.setPhone(phone); 
		 bean.setRegdate(regdate);
		
			
			  bean.setGroupno(groupno); 
			  bean.setOrderno(orderno);
			 

			  AdoptDao dao = new AdoptDao();

		int cnt = -1;
		try {
			cnt = dao.UpdateData(bean);

			if (cnt == -1) {
				new AdoptUpdateController().doGet(request, response);

			} else {
				String gotopage = super.getUrlInfo("AdoptList");
				gotopage += "&pageNumber=" + request.getParameter("pageNumber");
				gotopage += "&pageSize=" + request.getParameter("pageSize");
				gotopage += "&mode=" + request.getParameter("mode");
				gotopage += "&keyword=" + request.getParameter("keyword");

				new AdoptListController().doGet(request, response);

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
