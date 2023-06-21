package com.animal.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.NoticeDao;
import com.animal.model.notice;

public class NoticeReplyController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 답글의 최대 깊이 제한하기
		int depth = Integer.parseInt(request.getParameter("depth"));
		if(depth==3) {
			String message = "답글의 깊이는 최대 3까지입니다";
			super.setAlertMessage(message);
			new NoticeListController().doGet(request, response);
			return; // 문제가 더 생길 시 여기서 끝내고 다음 구문을 실행하지 않도록
		}
		
		int replyCount = 0 ; // 총 답글의 갯수
		int groupno = Integer.parseInt(request.getParameter("groupno"));
		
		NoticeDao dao = new NoticeDao();
		
		try {
		replyCount = dao.GetReplyCount(groupno);
		
		if(replyCount >= 5) {
			String message = "답글 최대수를 초과하여 답글 생성이 불가능합니다.";
			super.setAlertMessage(message);
			new NoticeListController().doGet(request, response);
			
		}else {
			super.Gotopage("notice/noReplyForm.jsp");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}	
}
				
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	
	   
	   // 답글도 신규 작성 개념이므로, no 컬럼은 쓰지 않아도 됩니다.
	   // 기타 조회 수(readhit)와 비고(remark) 컬럼도 명시하지 않습니다
	   String writer = request.getParameter("writer");
	   String subject = request.getParameter("subject");
	   String content = request.getParameter("content");
	   String regdate = request.getParameter("regdate");
   //  String remark = request.getParameter("");
	 
   //  int readhit = Integer.parseInt(request.getParameter(""));	   
	   int groupno = Integer.parseInt(request.getParameter("groupno"));
	   int orderno = Integer.parseInt(request.getParameter("orderno"));
	   int depth = Integer.parseInt(request.getParameter("depth"));
	   
	   notice bean = new notice();
	   
//	   bean.setNo(no);	   
	   bean.setGroupno(groupno); // 넘어온 그룹 번호는 동일하게
	   bean.setOrderno(orderno + 1); // 정렬순서는 +1
	   bean.setDepth(depth + 1); // 글의 깊이도 +1
//	   bean.setReadhit(readhit);
	   
	   bean.setContent(content);
	   bean.setRegdate(regdate);
//	   bean.setRemark(remark);
	   bean.setSubject(subject);
	   bean.setWriter(writer);
	   
	   int cnt = -1 ;
	   NoticeDao dao = new NoticeDao();
	   
	   try {
	   cnt = dao.ReplyData(bean, orderno);
	   new NoticeListController().doGet(request, response);
	   
	   }catch (Exception e) {
		   e.printStackTrace();
	   }
	   
	}

}
