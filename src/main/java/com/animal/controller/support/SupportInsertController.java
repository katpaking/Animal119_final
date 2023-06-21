package com.animal.controller.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.controller.notice.NoticeListController;
import com.animal.dao.SupportDao;
import com.animal.model.support;

public class SupportInsertController extends SuperClass{
	private final String PREFIX = "support/" ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response); 
		String gotopage = PREFIX + "suInsertForm.jsp" ; 
		
		super.Gotopage(gotopage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String writer = request.getParameter("writer") ;
		String subject = request.getParameter("subject") ;
		String phone = request.getParameter("phone");
		String content = request.getParameter("content") ;
		String hopeday = request.getParameter("hopeday") ;
		String regdate = request.getParameter("regdate") ;		
		
		support bean = new support() ;
		
		
		bean.setWriter(writer);
		bean.setSubject(subject);
		bean.setPhone(phone);
		bean.setContent(content);
		bean.setHopeday(hopeday);
		bean.setRegdate(regdate);
		
		SupportDao dao = new SupportDao();
		
		
		int cnt = -1 ;// 가정) -1은 실패 
		try {
			cnt = dao.InsertData(bean);
			
			if(cnt == -1) { // 실패
				new SupportInsertController().doGet(request, response);
				
			}else { // 성공
				new NoticeListController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
	
	}
	}
}
/*
 * private int getNumberData(String parameter) { boolean flag = false;
 * 
 * flag = parameter==null || parameter.equals("")|| parameter.equals("null");
 * 
 * System.out.println(this.getClass() + "getNumberData method called"); return
 * !flag ? Integer.parseInt(parameter) : 0 ;
 * 
 * 
 * // if(!flag) { // bean.setStock(Integer.parseInt(mystock)); // }else { //
 * bean.setStock(0);
 * 
 * }
 * 
 * }
 */
