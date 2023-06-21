package com.animal.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.MemberDao;
import com.animal.model.Member;

// 회원 가입
public class MemberInsertController extends SuperClass{
	private final String PREFIX = "member/";
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		// meLoginForm.jsp 파일로 이동하면 됩니다.
		String gotopage = PREFIX + "meInsertForm.jsp";

		super.Gotopage(gotopage);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		//
		String hobby = "";
		String[] hobbies = request.getParameterValues("hobby");
		if(hobbies==null) {
			hobby = "";
		}else {
			for(int i=0 ; i<hobbies.length; i++) {
				hobby += hobbies[i] + ",";
			}
			hobby=hobby.substring(0,hobby.length()-1);
		}
		
		String hiredate = request.getParameter("hiredate");
		
		String remark = request.getParameter("remark");
		

		Member bean = new Member();
		
		bean.setId(id);
		bean.setName(name);
		bean.setPassword(password);
		bean.setGender(gender);
		bean.setHobby(hobby);
		bean.setHiredate(hiredate);
		
		bean.setRemark(remark);
		
		MemberDao dao = new MemberDao();
		// 가정) -1은 가입 실패  cnt=(정수값)반환타입 : 1이 들어가면 성공 -1을 가정해서 실패 알아보기.
		int cnt = -1;
		try {
			cnt = dao.InsertData(bean);
			
			if(cnt == -1) {// 가입 실패
				new MemberInsertController().doGet(request, response);
			}else {//가입 성공
				new MemberLoginController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			new MemberInsertController().doGet(request, response);
		}
	}
}










