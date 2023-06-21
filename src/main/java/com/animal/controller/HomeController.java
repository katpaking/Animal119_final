package com.animal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.animal.controller.SuperClass;
import com.animal.dao.frBoardDao;
import com.animal.dao.AnimalDao;
import com.animal.dao.NoticeDao;
import com.animal.dao.ProductDao;
import com.animal.model.Animal;
import com.animal.model.Product;
import com.animal.model.frboard;
import com.animal.model.notice;
import com.animal.utility.Paging;

public class HomeController extends SuperClass {
  
	@Override//게시물 목록
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		super.doGet(request, response);
		boolean isGrid = false;
		
		// 공지사항 부분		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		NoticeDao ndao = new NoticeDao();
		List<notice> nlists = null;
		
		if(mode==null) {mode="all";}
		if(keyword==null) {keyword="";}
				
		// 자유게시판 부분		
		
		frBoardDao bdao = new frBoardDao();
		List<frboard> blists = null;		

		
		// 보호동물 부분		
		AnimalDao adao = new AnimalDao();
		List<Animal> alists = null ;
		
		// 쇼핑몰 부분(product)	
		ProductDao sdao = new ProductDao();
		List<Product> slists = null ;
		
		try {
			// 공지사항 부분
			int ntotalCount = ndao.GetTotalRecordCount(mode, keyword); 
		    String nurl = super.getUrlInfo("home");
			
			Paging npageInfo = new Paging(pageNumber, pageSize, 
					ntotalCount, nurl, mode, keyword, isGrid);
			System.out.println(npageInfo);
			
			nlists = ndao.SelectAll(npageInfo);
			
			request.setAttribute("ndatalist", nlists);			
			request.setAttribute("npageInfo", npageInfo); //페이징 정보를 바인딩
			
			// 자유게시판 부분			
			int btotalCount = bdao.GetTotalRecordCount(mode, keyword); 
		    String burl = request.getContextPath() + "/Animal?command=boList";
			
			Paging bpageInfo = new Paging(pageNumber, pageSize, btotalCount, burl, mode, keyword, isGrid);
			
			System.out.print("필드 검색 파라미터 확인 : ");
			System.out.println(bpageInfo.getFlowParameter()); 
			
			blists = bdao.SelectAll(bpageInfo);
			
			request.setAttribute("bdatalist", blists);			
			request.setAttribute("bpageInfo", bpageInfo); //페이징 정보를 바인딩
			
			// 보호동물 부분
			int atotalCount = adao.GetTotalRecordCount(mode, keyword) ;
			String aurl = super.getUrlInfo("aniList");
			
			Paging apageInfo = new Paging(pageNumber, pageSize, atotalCount, aurl, mode, keyword, isGrid);
			System.out.println(this.getClass());
			System.out.println(apageInfo); 

			alists = adao.SelectAll(apageInfo);	
			
			request.setAttribute("adatalist", alists);
			request.setAttribute("apageInfo", apageInfo); 
			
			// 쇼핑몰 부분(product)
			int stotalCount = sdao.GetTotalRecordCount(mode,keyword) ;
			String surl = super.getUrlInfo("prList");
			
			Paging spageInfo = new Paging(pageNumber, pageSize, stotalCount, surl, mode, keyword, isGrid);
			System.out.println(this.getClass());
			System.out.println(spageInfo); 

			slists = sdao.SelectAll(spageInfo);	
			
			request.setAttribute("sdatalist", slists);
			request.setAttribute("spageInfo", spageInfo); 
			
			// 공통 부분						
			String gotopage = "common/home.jsp";
			super.Gotopage(gotopage);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override//게시물 목록
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		super.doPost(request, response);
		boolean isGrid = false;
		
		// 공지사항 부분		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		NoticeDao ndao = new NoticeDao();
		List<notice> nlists = null;
		
		if(mode==null) {mode="all";}
		if(keyword==null) {keyword="";}
				
		// 자유게시판 부분		
		
		frBoardDao bdao = new frBoardDao();
		List<frboard> blists = null;		

		
		// 보호동물 부분		
		AnimalDao adao = new AnimalDao();
		List<Animal> alists = null ;
		
		// 쇼핑몰 부분(product)	
		ProductDao sdao = new ProductDao();
		List<Product> slists = null ;
		
		try {
			// 공지사항 부분
			int ntotalCount = ndao.GetTotalRecordCount(mode, keyword); 
		    String nurl = super.getUrlInfo("home");
			
			Paging npageInfo = new Paging(pageNumber, pageSize, 
					ntotalCount, nurl, mode, keyword, isGrid);
			System.out.println(npageInfo);
			
			nlists = ndao.SelectAll(npageInfo);
			
			request.setAttribute("ndatalist", nlists);			
			request.setAttribute("npageInfo", npageInfo); //페이징 정보를 바인딩
			
			// 자유게시판 부분			
			int btotalCount = bdao.GetTotalRecordCount(mode, keyword); 
		    String burl = request.getContextPath() + "/Animal?command=boList";
			
			Paging bpageInfo = new Paging(pageNumber, pageSize, btotalCount, burl, mode, keyword, isGrid);
			
			System.out.print("필드 검색 파라미터 확인 : ");
			System.out.println(bpageInfo.getFlowParameter()); 
			
			blists = bdao.SelectAll(bpageInfo);
			
			request.setAttribute("bdatalist", blists);			
			request.setAttribute("bpageInfo", bpageInfo); //페이징 정보를 바인딩
			
			// 보호동물 부분
			int atotalCount = adao.GetTotalRecordCount(mode, keyword) ;
			String aurl = super.getUrlInfo("aniList");
			
			Paging apageInfo = new Paging(pageNumber, pageSize, atotalCount, aurl, mode, keyword, isGrid);
			System.out.println(this.getClass());
			System.out.println(apageInfo); 

			alists = adao.SelectAll(apageInfo);	
			
			request.setAttribute("adatalist", alists);
			request.setAttribute("apageInfo", apageInfo); 
			
			// 쇼핑몰 부분(product)
			int stotalCount = sdao.GetTotalRecordCount(mode,keyword) ;
			String surl = super.getUrlInfo("prList");
			
			Paging spageInfo = new Paging(pageNumber, pageSize, stotalCount, surl, mode, keyword, isGrid);
			System.out.println(this.getClass());
			System.out.println(spageInfo); 

			slists = sdao.SelectAll(spageInfo);	
			
			request.setAttribute("sdatalist", slists);
			request.setAttribute("spageInfo", spageInfo); 
			
			// 공통 부분						
			String gotopage = "common/home.jsp";
			super.Gotopage(gotopage);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
