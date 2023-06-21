package com.animal.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.MemberDao;
import com.animal.model.Member;

public class MemberDetailController extends SuperClass {
	@Override// 회원의 아이디를 이용하여 회원 상제 정보를 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		
		Member bean = null;
		try {
			bean = dao.getDataByPk(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean==null) {
			super.setAlertMessage("잘못된 회원 정보입니다.");
			super.Gotopage("common/home.jsp");
		}else {
			request.setAttribute("bean", bean);
			super.Gotopage("member/meDetail.jsp");
		}
	}
}
