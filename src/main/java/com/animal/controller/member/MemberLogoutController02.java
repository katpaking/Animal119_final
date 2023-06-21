package com.animal.controller.member;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;

public class MemberLogoutController02 extends SuperClass {
	@Override // 회원이 로그 아웃을 시도합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		if (super.loginfo != null) {
			super.Gotopage("member/meLoginForm.jsp");
		} 
	}
}
