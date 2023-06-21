package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.animal.model.frboard;
import com.animal.model.notice;
import com.animal.utility.Paging;

public class NoticeDao extends SuperDao{
   

   public int ReplyData(notice bean, int orderno) throws Exception {
      //답글 기능에는 구문 두 개가 쓰인다
      
      System.out.println(bean);
      
      int cnt = -1 ;
      Connection conn = null ;
      PreparedStatement pstmt = null ;
      if(conn==null) {conn=super.getConnection();}
      conn.setAutoCommit(false); 
      
      
      // update 구문) 동일한 그룹 번호에 대하여 orderno 컬럼의 숫자를 1씩 증가시켜야 합니다
      String sql1 = " update notices set orderno = orderno + 1" ;
      //      ㄴsql과 구분을 위해
      sql1 += " where groupno = ? and orderno > ? ";
      
      pstmt=conn.prepareStatement(sql1);
      
      pstmt.setInt(1, bean.getGroupno()) ;
      pstmt.setInt(2, orderno) ;
      
      cnt = pstmt.executeUpdate() ;
      
      
      // insert 구문) 해당 bean 객체를 이용하여 답글을 작성합니다
      
      pstmt = null ;
      String sql2 = " insert into notices(no, category, writer, subject, image01, content, regdate, groupno, orderno, depth)" ;   
      
      sql2 += " values(mynotice.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)" ;
      
      pstmt=conn.prepareStatement(sql2);
      
//      writer, subject, content, regdate, groupno, orderno, depth)"      
//        1        2       3         4        5        6       7      
      pstmt.setString(1, bean.getWriter()) ;
      pstmt.setString(2, bean.getCategory()) ;
      pstmt.setString(3, bean.getSubject()) ;
      pstmt.setString(4, bean.getImage01()) ;
      
      pstmt.setString(5, bean.getContent()) ;
   /*   pstmt.setString(6, bean.getRegdate()) ; */
      pstmt.setInt(6, bean.getGroupno()) ;
      pstmt.setInt(7, bean.getOrderno()) ;
      pstmt.setInt(8, bean.getDepth()) ;
      
      cnt = pstmt.executeUpdate() ;
      
      conn.commit();
      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}
      
      return cnt ;      
   }

   
   public int GetReplyCount(int groupno) throws Exception {
      
      System.out.println("검색할 groupno : " + groupno);
      
      String sql = " select count(*) as cnt from notices where groupno = ?" ;
      
      
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
     System.out.println("검색할 키워드 : " + keyword);
     
      String sql = " select count(*) as cnt from notices" ;
      
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
   
   public int InsertData(notice bean) throws Exception{
      System.out.println(bean);
      
      // bean 객체 1건의 데이터를 데이터 베이스에 추가합니다.
      int cnt = -1 ;
      
      String sql = " insert into notices(no, category, writer, subject, image01, content, regdate, readhit, groupno, orderno, depth)" ;
      sql += " values(mynotice.nextval, ?, ?, ?, ?, ?, sysdate, default, mynotice.currval, default, default)" ;
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;
      
      if(conn==null) {conn=super.getConnection();}
      
      conn.setAutoCommit(false); 
      
      pstmt=conn.prepareStatement(sql);
      
      pstmt.setString(1, bean.getCategory()) ;
      pstmt.setString(2, bean.getWriter()) ;
      pstmt.setString(3, bean.getSubject()) ;
      pstmt.setString(4, bean.getImage01()) ;
      
      pstmt.setString(5, bean.getContent()) ;
   /*   pstmt.setString(6, bean.getRegdate()) ; */
      
      cnt = pstmt.executeUpdate() ;
      
      conn.commit();
      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}
      
      return cnt ;      
   }
   

   public List<notice> SelectAll(Paging pageInfo) throws Exception{
      // TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
      String sql = " select no, category, writer, subject, image01, content, regdate, readhit, groupno, orderno, depth"; 
      sql += " from (select no, category, writer, subject, image01, content, regdate, readhit, groupno, orderno, depth,"; 
      
 //     sql += " rank() over(order by no desc) as ranking"; // 답글 구현 이전 코딩      
      sql += " rank() over(order by groupno desc, orderno desc) as ranking"; //for 답글 기능
      sql += " from notices";
      
      String mode = pageInfo.getMode() ;
		String keyword = pageInfo.getKeyword() ;
		
		if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
			sql += " where " + mode + " like '%" + keyword + "%'" ;
		}
		
		sql += " )";
		sql += " where ranking between ? and ? ";
      
      List<notice> lists = new ArrayList<notice>() ;
      
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


   public List<notice> SelectAll() throws Exception{
      // 전체 회원 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.
      String sql = " select * from notices order by no desc" ; 
      
      List<notice> lists = new ArrayList<notice>() ;
      
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

   private notice makeBean(ResultSet rs) throws Exception {
      notice bean = new notice() ;
      bean.setNo(rs.getInt("no"));
      bean.setCategory(rs.getString("category"));
      bean.setWriter(rs.getString("writer"));
      bean.setSubject(rs.getString("subject"));
      bean.setImage01(rs.getString("image01"));
      
      bean.setContent(rs.getString("content"));
      bean.setRegdate(String.valueOf(rs.getDate("regdate")));
      bean.setReadhit(rs.getInt("readhit"));
      bean.setGroupno(rs.getInt("groupno"));
      bean.setOrderno(rs.getInt("orderno"));
      bean.setDepth(rs.getInt("depth"));
      
      return bean;
   }



   public notice getDataByPk(int no) throws Exception {
	   System.out.println("찾고자 하는 글번호: " + no);
	      
	      String sql = " select * from notices" ;
	      sql += " where no = ?" ;
	      
	      PreparedStatement pstmt = null ;
	      ResultSet rs= null ;
	      Connection conn = null ;
	      
	      notice bean = null;

	      try {
	          conn = super.getConnection();
	          pstmt = conn.prepareStatement(sql);
	          pstmt.setInt(1, no);
	          rs = pstmt.executeQuery();

	          if (rs.next()) {
	              bean = this.makeBean(rs);

	              // 조회수 증가
	              String updateSql = "update notices set readhit = readhit + 1 where no = ?";
	              PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
	              updatePstmt.setInt(1, no);
	              updatePstmt.executeUpdate();
	              updatePstmt.close();
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	      } finally {
	          if (rs != null) { rs.close(); }
	          if (pstmt != null) { pstmt.close(); }
	          if (conn != null) { conn.close(); }
	      }

	      return bean;

   }

   public int UpdateData(notice bean) throws Exception {
      
      int cnt = -1;
      
      String sql = " update notices set ";
            sql += " category = ?, writer = ?, subject = ?, content = ?, regdate = sysdate,";
            sql += " readhit = ?, groupno = ?, orderno = ?, depth = ?, image01 = ? ";
           
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
		/* pstmt.setString(5, bean.getRegdate()) ; */
      pstmt.setInt(5, bean.getReadhit());
      pstmt.setInt(6, bean.getGroupno());
      pstmt.setInt(7, bean.getOrderno());
      pstmt.setInt(8, bean.getDepth());
      pstmt.setString(9, bean.getImage01()) ;
      
      pstmt.setInt(10, bean.getNo());
            
        cnt = pstmt.executeUpdate() ;
      
      conn.commit();
      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}
      
      return cnt ;      
   }


public int DeleteData(int no) throws Exception {
	// 게시물에서 해당 글번호를 삭제합니다. 
			System.out.println("삭제될 글번호 : " + no);
			int cnt = -1 ;
			
			String sql = " delete from notices where no = ?" ; 
			
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