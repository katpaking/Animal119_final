package com.animal.controller.nocomment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.animal.controller.SuperClass;
import com.animal.dao.noCommentDao;
import com.animal.model.nocomment;

public class noCommentListController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter("no"));
		noCommentDao dao = new noCommentDao();
		List<nocomment> comments = null;
	
	try {
		comments = dao.GetDataByPk(no);
		System.out.println("댓글 개수 : " + comments.size());
		
		JSONArray jsArr = new JSONArray();
		for(nocomment comm : comments) {
			JSONObject jsobj = new JSONObject();
			jsobj.put("cnum", comm.getCnum());
			jsobj.put("writer", comm.getWriter());
			jsobj.put("content", comm.getContent());
			jsobj.put("regdate", comm.getRegdate());
			jsArr.add(jsobj);	
		}
	
		request.setAttribute("jsArr", jsArr);
		super.Gotopage("nocomment/nocmList.jsp");
		
	} catch (Exception e) {
		
	}
	
	
	}
}


