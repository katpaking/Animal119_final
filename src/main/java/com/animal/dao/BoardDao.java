package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.animal.model.board;
import com.animal.utility.Paging;


public class BoardDao extends SuperDao{
   

   public int ReplyData(board bean, int orderno) throws Exception {
      //답글 기능에는 구문 두 개가 쓰인다
      
      System.out.println(bean);
      
      int cnt = -1 ;
      Connection conn = null ;
      PreparedStatement pstmt = null ;
      if(conn==null) {conn=super.getConnection();}
      conn.setAutoCommit(false); 
      
      
      // update 구문) 동일한 그룹 번호에 대하여 orderno 컬럼의 숫자를 1씩 증가시켜야 합니다
      String sql1 = " update boards set orderno = orderno + 1" ;
      //      ㄴsql과 구분을 위해
      sql1 += " where groupno = ? and orderno > ? ";
      
      pstmt=conn.prepareStatement(sql1);
      
      pstmt.setInt(1, bean.getGroupno()) ;
      pstmt.setInt(2, orderno) ;
      
      cnt = pstmt.executeUpdate() ;
      
      
      // insert 구문) 해당 bean 객체를 이용하여 답글을 작성합니다
      
      pstmt = null ;
      String sql2 = " insert into boards(no, category, writer, subject, image01, image02, image03, content, regdate, groupno, orderno, depth)" ;   
      
      sql2 += " values(myboard.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
      
      pstmt=conn.prepareStatement(sql2);
      
//      writer, subject, content, regdate, groupno, orderno, depth)"      
//        1        2       3         4        5        6       7      
      pstmt.setString(1, bean.getWriter()) ;
      pstmt.setString(2, bean.getCategory()) ;
      pstmt.setString(3, bean.getSubject()) ;
      pstmt.setString(4, bean.getImage01()) ;
      pstmt.setString(5, bean.getImage02()) ;
      pstmt.setString(6, bean.getImage03()) ;
      pstmt.setString(7, bean.getContent()) ;
      pstmt.setString(8, bean.getRegdate()) ;
      pstmt.setInt(9, bean.getGroupno()) ;
      pstmt.setInt(10, bean.getOrderno()) ;
      pstmt.setInt(11, bean.getDepth()) ;
      
      cnt = pstmt.executeUpdate() ;
      
      conn.commit();
      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}
      
      return cnt ;      
   }

   
   public int GetReplyCount(int groupno) throws Exception {
      
      System.out.println("검색할 groupno : " + groupno);
      
      String sql = " select count(*) as cnt from boards where groupno = ?" ;
      
      
      PreparedStatement pstmt = null ;
      ResultSet rs= null ;
      Connection conn = null ;
      int cnt = -1 ;
      
      if(conn==null) {conn = super.getConnection() ;}      
      pstmt = conn.prepareStatement(sql);         
      pstmt.setInt(1, groupno) ;
      rs = pstmt.executeQuery();
         
      if(rs.next()) {
         cnt = rs.getInt("cnt") ;
      }
      
      if(rs!=null){rs.close();}
      if(pstmt!=null){pstmt.close();}
      if(conn!=null){conn.close();}
      
      return cnt;
   }
   

   public int GetTotalRecordCount(String mode, String keyword) throws Exception {
     System.out.println("검색할 필드명 : " + mode);
     System.out.println("검색할 키워드 : " + keyword + "\n");
     
      String sql = " select count(*) as cnt from boards" ;
      
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
   
   public int InsertData(board bean) throws Exception{
      System.out.println(bean);
      
      // bean 객체 1건의 데이터를 데이터 베이스에 추가합니다.
      int cnt = -1 ;
      
      String sql = " insert into boards(no, category, writer, subject, image01, image02, image03, content, regdate, readhit, groupno, orderno, depth)" ;
      sql += " values(myboard.nextval, ?, ?, ?, ?, ?, ?, ?, ?, default, myboard.currval, default, default)" ;
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;
      
      if(conn==null) {conn=super.getConnection();}
      
      conn.setAutoCommit(false); 
      
      pstmt=conn.prepareStatement(sql);
      
      pstmt.setString(1, bean.getCategory()) ;
      pstmt.setString(2, bean.getWriter()) ;
      pstmt.setString(3, bean.getSubject()) ;
      pstmt.setString(4, bean.getImage01()) ;
      pstmt.setString(5, bean.getImage02()) ;
      pstmt.setString(6, bean.getImage03()) ;
      pstmt.setString(7, bean.getContent()) ;
      pstmt.setString(8, bean.getRegdate()) ;
      
      cnt = pstmt.executeUpdate() ;
      
      conn.commit();
      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}
      
      return cnt ;      
   }
   

   public List<board> SelectAll(Paging pageInfo) throws Exception{
      // TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
      String sql = " select no, category, writer, subject, image01, image02, image03, content, regdate, readhit, groupno, orderno, depth"; 
      sql += " from (select no, category, writer, subject, image01, image02, image03, content, regdate, readhit, groupno, orderno, depth,"; 
      
 //     sql += " rank() over(order by no desc) as ranking"; // 답글 구현 이전 코딩      
      sql += " rank() over(order by groupno desc, orderno asc) as ranking"; //for 답글 기능
      sql += " from boards";
      
      String mode = pageInfo.getMode() ;
		String keyword = pageInfo.getKeyword() ;
		
		if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
			sql += " where " + mode + " like '%" + keyword + "%'" ;
		}
		
		sql += " )";
		sql += " where ranking between ? and ? ";
		
      List<board> lists = new ArrayList<board>() ;
      
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


   public List<board> SelectAll() throws Exception{
      // 전체 회원 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.
      String sql = " select * from boards order by no desc" ; 
      
      List<board> lists = new ArrayList<board>() ;
      
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

   private board makeBean(ResultSet rs) throws Exception {
      board bean = new board() ;
      bean.setNo(rs.getInt("no"));
      bean.setCategory(rs.getString("category"));
      bean.setWriter(rs.getString("writer"));
      bean.setSubject(rs.getString("subject"));
      bean.setImage01(rs.getString("image01"));
      bean.setImage02(rs.getString("image02"));
      bean.setImage03(rs.getString("image03"));
      bean.setContent(rs.getString("content"));
      bean.setRegdate(String.valueOf(rs.getDate("regdate")));
      bean.setReadhit(rs.getInt("readhit"));
      bean.setGroupno(rs.getInt("groupno"));
      bean.setOrderno(rs.getInt("orderno"));
      bean.setDepth(rs.getInt("depth"));
      
      return bean;
   }



   public board getDataByPk(int no) throws Exception {
      System.out.println("찾고자 하는 글번호: " + no);
      
      String sql = " select * from boards" ;
      sql += " where no = ?" ;
      
      PreparedStatement pstmt = null ;
      ResultSet rs= null ;
      Connection conn = null ;
      
      if(conn==null) {conn = super.getConnection() ;}      
      pstmt = conn.prepareStatement(sql);      
      pstmt.setInt(1, no);      
      rs = pstmt.executeQuery();
      
      board bean = null ;      
      if(rs.next()) {
         bean = this.makeBean(rs) ;
      }
      
      if(rs!=null){rs.close();}
      if(pstmt!=null){pstmt.close();}
      if(conn!=null){conn.close();}
      
      return bean;
   }

   public int UpdateData(board bean) throws Exception {
      
      int cnt = -1;
      
      String sql = " update boards set ";
            sql += " category = ?, writer = ?, subject = ?, content = ?, regdate = ?,";
            sql += " readhit = ?, groupno = ?, orderno = ?, depth = ?,";
            sql += " image01 = ?, image02 = ?, image03 = ?";
            sql += " where no = ?";
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;
            
      if(conn==null) {conn=super.getConnection();}
            
      conn.setAutoCommit(false); 
            
      pstmt=conn.prepareStatement(sql);
      
      pstmt.setString(1, bean.getCategory()) ;
      pstmt.setString(2, bean.getWriter()) ;
      pstmt.setString(3, bean.getSubject()) ;
      pstmt.setString(4, bean.getContent()) ;
      pstmt.setString(5, bean.getRegdate()) ;
      pstmt.setInt(6, bean.getReadhit());
      pstmt.setInt(7, bean.getGroupno());
      pstmt.setInt(8, bean.getOrderno());
      pstmt.setInt(9, bean.getDepth());
      pstmt.setString(10, bean.getImage01()) ;
      pstmt.setString(11, bean.getImage02()) ;
      pstmt.setString(12, bean.getImage03()) ;
      pstmt.setInt(13, bean.getNo());
            
        cnt = pstmt.executeUpdate() ;
      
      conn.commit();
      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}
      
      return cnt ;      
   }
   
   public board GetDataByPk(int no) throws Exception{
		// 해당 기본 키를 이용하여 bean 객체를 반환해 줍니다.
		System.out.println("게시물 번호: " + no);
		
		String sql = " select * from boards" ;
		sql += " where no = ?" ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		
		if(conn==null) {conn = super.getConnection() ;}		
		pstmt = conn.prepareStatement(sql);		
		pstmt.setInt(1, no);		
		rs = pstmt.executeQuery();
		
		board bean = null ;		
		if(rs.next()) {
			bean = this.makeBean(rs) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return bean;
	}



}