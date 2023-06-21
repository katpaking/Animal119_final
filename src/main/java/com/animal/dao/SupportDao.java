package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.animal.model.support;
import com.animal.utility.Paging;


public class SupportDao extends SuperDao{

	
	private support makeBean(ResultSet rs) throws Exception{
		  support bean = new support() ;
	      bean.setNo(rs.getInt("no"));
	      bean.setWriter(rs.getString("writer"));
	      bean.setSubject(rs.getString("subject"));
	      bean.setPhone(String.valueOf(rs.getString("phone")));
	      bean.setContent(rs.getString("content"));
	      bean.setHopeday(String.valueOf(rs.getDate("hopeday")));
	      bean.setRegdate(String.valueOf(rs.getDate("regdate")));
	      
	      bean.setGroupno(rs.getInt("groupno"));
	      bean.setOrderno(rs.getInt("orderno"));
	      
	      return bean;
	}

	public support getDataByPk(int no) throws Exception {
		System.out.println("찾고자 하는 글번호: " + no);
	      
	      String sql = " select * from supports" ;
	      sql += " where no = ?" ;
	      
	      PreparedStatement pstmt = null ;
	      ResultSet rs= null ;
	      Connection conn = null ;
	      
	      if(conn==null) {conn = super.getConnection() ;}      
	      pstmt = conn.prepareStatement(sql);      
	      pstmt.setInt(1, no);      
	      rs = pstmt.executeQuery();
	      
	      support bean = null ;      
	      if(rs.next()) {
	         bean = this.makeBean(rs) ;
	      }
	      
	      if(rs!=null){rs.close();}
	      if(pstmt!=null){pstmt.close();}
	      if(conn!=null){conn.close();}
	      
	      return bean;
	}

	public int InsertData(support bean) throws Exception{
		System.out.println(bean);
		      
		      // bean 객체 1건의 데이터를 데이터 베이스에 추가합니다.
	    int cnt = -1 ;
		      
		String sql = " insert into supports(no, writer, subject, phone, content, hopeday, regdate, groupno, orderno )" ;
		sql += " values(mysupport.nextval, ?, ?, ?, ?, ?, sysdate, mysupport.currval, default)" ;
		      
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		      
		if(conn==null) {conn=super.getConnection();}
		      
		conn.setAutoCommit(false); 
		      
		pstmt=conn.prepareStatement(sql);
		      
		pstmt.setString(1, bean.getWriter()) ;
		pstmt.setString(2, bean.getSubject()) ;
		pstmt.setString(3, bean.getPhone()) ;
		pstmt.setString(4, bean.getContent()) ;
		pstmt.setString(5, bean.getHopeday());
/*		pstmt.setString(6, bean.getRegdate()) ; */
		      
		cnt = pstmt.executeUpdate() ;
		      
		conn.commit();
		      
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		      
		return cnt ;      
		   }

	public int GetTotalRecordCount(String mode, String keyword) throws Exception{
		System.out.println("검색할 필드명 : " + mode);
	     System.out.println("검색할 키워드 : " + keyword + "\n");
	     
	      String sql = " select count(*) as cnt from supports " ;
	      
	      if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
				sql += " where " + mode + " like '%" + keyword + "%'" ;
			}
	      
	      PreparedStatement pstmt = null ;
	      ResultSet rs= null ;
	      Connection conn = null ;
	      int cnt = -1 ;
	      
	      if(conn==null) {
	      conn = super.getConnection() ;}      
	      pstmt = conn.prepareStatement(sql);         
	      
	      rs = pstmt.executeQuery();
	         
	      if(rs.next()) {
	         cnt = rs.getInt("cnt") ;
	      }
	      
	      if(rs!=null){rs.close();}
	      if(pstmt!=null){pstmt.close();}
	      if(conn!=null){conn.close();}
	      
	      return cnt;
	}
	public List<support> SelectAll() throws Exception{
		// 전체 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.
		String sql = " select * from supports order by no desc" ;
		
		List<support> lists = new ArrayList<support>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery() ;
		
		while(rs.next()) {
			lists.add(this.makeBean(rs)) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return lists;
	}

	public List<support> SelectAll(Paging pageInfo) throws Exception{
		String sql = " select no, writer, subject, phone, content, hopeday, regdate, groupno, orderno "; 
	      sql += " from (select no, writer, subject, phone, content, hopeday, regdate, groupno, orderno,"; 
	      
	 //     sql += " rank() over(order by no desc) as ranking"; // 답글 구현 이전 코딩      
	      sql += " rank() over(order by groupno desc, orderno asc) as ranking"; //for 답글 기능
	      sql += " from supports";
	      
	        String mode = pageInfo.getMode() ;
			String keyword = pageInfo.getKeyword() ;
			
			if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
				sql += " where " + mode + " like '%" + keyword + "%'" ;
			}
			
			sql += " )";
			sql += " where ranking between ? and ? ";
			
	      List<support> lists = new ArrayList<support>() ;
	      
	      Connection conn = null ;
	      PreparedStatement pstmt = null ;
	      ResultSet rs = null ;
	      
	      if(conn==null){conn=super.getConnection();}
	      pstmt = conn.prepareStatement(sql);
	      
	      pstmt.setInt(1, pageInfo.getBeginRow());
	      pstmt.setInt(2, pageInfo.getEndRow());
	      
	      rs = pstmt.executeQuery() ;
	      
	      while(rs.next()) {
	         lists.add(this.makeBean(rs)) ;
	      }
	      
	      if(rs!=null){rs.close();}
	      if(pstmt!=null){pstmt.close();}
	      if(conn!=null){conn.close();}
	      
	      return lists;
	   }

	
	public int UpdateData(support bean) throws Exception{
		  int cnt = -1;
	      
	      String sql = " update supports set ";
	            sql += " writer = ?, subject = ?, phone = ?, content = ?,";
	            sql += " hopeday = ?, regdate = sysdate, groupno = ?, orderno = ?";
	           
	            sql += " where no = ?";
	      
	      Connection conn = null ;
	      PreparedStatement pstmt = null ;
	      if(conn==null) {conn=super.getConnection();}
	      conn.setAutoCommit(false); 
	      pstmt=conn.prepareStatement(sql);
	      
	      pstmt.setString(1, bean.getWriter()) ;
	      pstmt.setString(2, bean.getSubject()) ;
	      pstmt.setString(3,  bean.getPhone());
	      pstmt.setString(4, bean.getContent()) ;
	      pstmt.setString(5, bean.getHopeday());
	 /*   pstmt.setString(6, bean.getRegdate()) ; */
	     
	      pstmt.setInt(6, bean.getGroupno());
	      pstmt.setInt(7, bean.getOrderno());
	      
	      pstmt.setInt(8, bean.getNo());
	            
	        cnt = pstmt.executeUpdate() ;
	      
	      conn.commit();
	      
	      if(pstmt!=null) {pstmt.close();}
	      if(conn!=null) {conn.close();}
	      
	      return cnt ;      
	   }

	public int DeleteData(int no) throws Exception {
		System.out.println("삭제될 글번호 : " + no);
		int cnt = -1 ;
		
		String sql = " delete from supports where no = ?" ; 
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;		
		if(conn==null) {conn=super.getConnection();}		
		conn.setAutoCommit(false); 		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, no) ;
		
		cnt = pstmt.executeUpdate() ;		
		conn.commit();		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}	
		
		return cnt ;
	}
	}

