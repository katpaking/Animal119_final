package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.animal.model.Adopt;
import com.animal.utility.Paging;


public class AdoptDao extends SuperDao{

	
	private Adopt makeBean(ResultSet rs) throws Exception{
		Adopt bean = new Adopt() ;
	      bean.setNum(rs.getInt("num"));
	      bean.setWriter(rs.getString("writer"));
	      bean.setSubject(rs.getString("subject"));
	      bean.setPhone(String.valueOf(rs.getString("phone")));
	      bean.setRegdate(String.valueOf(rs.getDate("regdate")));
	      
	      bean.setGroupno(rs.getInt("groupno"));
	      bean.setOrderno(rs.getInt("orderno"));
	      
	      return bean;
	}

	public Adopt getDataByPk(int num) throws Exception {
		System.out.println("찾고자 하는 글번호: " + num);
	      
	      String sql = " select * from adopts" ;
	      sql += " where num = ?" ;
	      
	      PreparedStatement pstmt = null ;
	      ResultSet rs= null ;
	      Connection conn = null ;
	      
	      if(conn==null) {conn = super.getConnection() ;}      
	      pstmt = conn.prepareStatement(sql);      
	      pstmt.setInt(1, num);      
	      rs = pstmt.executeQuery();
	      
	      Adopt bean = null ;      
	      if(rs.next()) {
	         bean = this.makeBean(rs) ;
	      }
	      
	      if(rs!=null){rs.close();}
	      if(pstmt!=null){pstmt.close();}
	      if(conn!=null){conn.close();}
	      
	      return bean;
	}

	public int InsertData(Adopt bean) throws Exception{
		System.out.println(bean);
		      
		      // bean 객체 1건의 데이터를 데이터 베이스에 추가합니다.
	    int cnt = -1 ;
		      
		String sql = " insert into adopts(num, writer, subject, phone, regdate, groupno, orderno )" ;
		sql += " values(myadopt.nextval, ?, ?, ?, sysdate, myadopt.currval, default)" ;
		      
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		      
		if(conn==null) {conn=super.getConnection();}
		      
		conn.setAutoCommit(false); 
		      
		pstmt=conn.prepareStatement(sql);
		      
		pstmt.setString(1, bean.getWriter()) ;
		pstmt.setString(2, bean.getSubject()) ;
		pstmt.setString(3, bean.getPhone()) ;
		      
		cnt = pstmt.executeUpdate() ;
		      
		conn.commit();
		      
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		      
		return cnt ;      
		   }

	public int GetTotalRecordCount(String mode, String keyword) throws Exception{
		  System.out.println("검색할 필드명 : " + mode);
	      System.out.println("검색할 키워드 : " + keyword + "\n");
	     
	      String sql = " select count(*) as cnt from adopts " ;
	      
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
	public List<Adopt> SelectAll() throws Exception{
		// 전체 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.
		String sql = " select * from adopts order by num desc" ;
		
		List<Adopt> lists = new ArrayList<Adopt>() ;
		
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

	public List<Adopt> SelectAll(Paging pageInfo) throws Exception{
		String sql = " select num, writer, subject, phone, regdate, groupno, orderno "; 
	      sql += " from (select num, writer, subject, phone, regdate, groupno, orderno,"; 

	      sql += " rank() over(order by groupno desc, orderno asc) as ranking"; //for 답글 기능
	      sql += " from adopts";
	      
	        String mode = pageInfo.getMode() ;
			String keyword = pageInfo.getKeyword() ;
			
			if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
				sql += " where " + mode + " like '%" + keyword + "%'" ;
			}
			
			sql += " )";
			sql += " where ranking between ? and ? ";
			
	      List<Adopt> lists = new ArrayList<Adopt>() ;
	      
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

	
	public int UpdateData(Adopt bean) throws Exception{
		  int cnt = -1;
	      
	      String sql = " update adopts set ";
	            sql += " writer = ?, subject = ?, phone = ?,";
	            sql += " regdate = sysdate, groupno = ?, orderno = ?";
	           
	            sql += " where num = ?";
	      
	      Connection conn = null ;
	      PreparedStatement pstmt = null ;
	      if(conn==null) {conn=super.getConnection();}
	      conn.setAutoCommit(false); 
	      pstmt=conn.prepareStatement(sql);
	      
	      pstmt.setString(1, bean.getWriter()) ;
	      pstmt.setString(2, bean.getSubject()) ;
	      pstmt.setString(3,  bean.getPhone());	 
	     
	      pstmt.setInt(4, bean.getGroupno());
	      pstmt.setInt(5, bean.getOrderno());
	      
	      pstmt.setInt(6, bean.getNum());
	            
	        cnt = pstmt.executeUpdate() ;
	      
	      conn.commit();
	      
	      if(pstmt!=null) {pstmt.close();}
	      if(conn!=null) {conn.close();}
	      
	      return cnt ;      
	   }

	public int DeleteData(int num) throws Exception {
		System.out.println("삭제될 글번호 : " + num);
		int cnt = -1 ;
		
		String sql = " delete from adopts where num = ?" ; 
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;		
		if(conn==null) {conn=super.getConnection();}		
		conn.setAutoCommit(false); 		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, num) ;
		
		cnt = pstmt.executeUpdate() ;		
		conn.commit();		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}	
		
		return cnt ;
	}
	}

