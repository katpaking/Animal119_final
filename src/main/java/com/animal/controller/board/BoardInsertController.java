package com.animal.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.animal.controller.SuperClass;
import com.animal.dao.CategoryDao;
import com.animal.dao.BoardDao;
import com.animal.model.Category;
import com.animal.model.board;

public class BoardInsertController extends SuperClass{
	
@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 상품 등록 화면이 보이기 전에 category 목록 테이블을 읽어서 콤보 박스에 채워 넣기
		CategoryDao dao = new CategoryDao(); 
		List<Category> categories = null;
		
			try {
				categories = dao.GetCategoryList();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
		
		request.setAttribute("categories", categories);
		
		String gotopage = "board/boInsertForm.jsp";
		super.Gotopage(gotopage);
	}

@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	super.doPost(request, response);
	
	MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
	
    board bean = new board() ;
    
    // 상품 등록 시 상품 번호는 시퀸스가 알아서 처리해주므로 신경쓸 필요 없습니다 
    // bean.setNum(getNumberData(mr.getParameter("num")));
    
    bean.setNo(getNumberData(mr.getParameter("no")));
    bean.setCategory(mr.getParameter("category"));
    bean.setWriter(mr.getParameter("writer"));
    bean.setSubject(mr.getParameter("subject"));
    
    bean.setImage01(mr.getFilesystemName("image01"));
    bean.setImage02(mr.getFilesystemName("image02"));
    bean.setImage03(mr.getFilesystemName("image03"));
    
    bean.setContent(mr.getParameter("content"));
    bean.setRegdate(mr.getParameter("regdate"));
    
    bean.setReadhit(getNumberData(mr.getParameter("readhit")));
    bean.setGroupno(getNumberData(mr.getParameter("groupno")));
    bean.setOrderno(getNumberData(mr.getParameter("orderno")));
    bean.setDepth(getNumberData(mr.getParameter("depth")));
    
    BoardDao dao = new BoardDao();
    int cnt = -1 ;
    try {
    	cnt = dao.InsertData(bean);
    	if(cnt == -1) {
    		super.Gotopage("board/boInsertForm.jsp");
    	}else {
    		new BoardListController().doGet(request, response);
    	}
    	    	
    }catch(Exception e){
    	e.printStackTrace();
    }
}
    
  private int getNumberData(String parameter) {
    boolean flag = false;
    
    flag = parameter==null || parameter.equals("")|| parameter.equals("null");
	
    System.out.println(this.getClass() + "getNumberData method called");
    return !flag ? Integer.parseInt(parameter) : 0 ;
    
    
//    if(!flag) {
//		bean.setStock(Integer.parseInt(mystock));
//	}else {
//		bean.setStock(0);
	
   
  
    	
    }
}


