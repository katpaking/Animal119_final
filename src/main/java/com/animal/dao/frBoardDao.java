package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.animal.model.frboard;
import com.animal.utility.Paging;


public class frBoardDao extends SuperDao{
   

   public int ReplyData(frboard bean, int orderno) throws Exception {
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
      String sql2 = " insert into boards(no, category, writer, subject, image01, content, regdate, groupno, orderno, depth)" ;   
      
      sql2 += " values(myboard.nextval, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)" ;
      
      pstmt=conn.prepareStatement(sql2);
   
      pstmt.setString(1, bean.getCategory()) ;
      pstmt.setString(2, bean.getWriter()) ;      
      pstmt.setString(3, bean.getSubject()) ;
      pstmt.setString(4, bean.getImage01()) ;      
      pstmt.setString(5, bean.getContent()) ;		
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
   
   public int InsertData(frboard bean) throws Exception{
      System.out.println(bean);
      
      // bean 객체 1건의 데이터를 데이터 베이스에 추가합니다.
      int cnt = -1 ;
      
      String sql = " insert into boards(no, category, writer, subject, image01, content, regdate, readhit, groupno, orderno, depth)" ;
      sql += " values(myboard.nextval, ?, ?, ?, ?, ?, sysdate, default, myboard.currval, default, default)" ;
      
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
/*    pstmt.setString(6, bean.getRegdate()) ;  */
      
      cnt = pstmt.executeUpdate() ;
      
      conn.commit();
      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}
      
      return cnt ;      
   }
   

   public List<frboard> SelectAll(Paging pageInfo) throws Exception{
      // TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
      String sql = " select no, category, writer, subject, image01, content, regdate, readhit, groupno, orderno, depth"; 
      sql += " from (select no, category, writer, subject, image01, content, regdate, readhit, groupno, orderno, depth,"; 
      
 //     sql += " rank() over(order by no desc) as ranking"; // 답글 구현 이전 코딩      
      sql += " rank() over(order by groupno desc, orderno desc) as ranking"; //for 답글 기능
      sql += " from boards";
      
        String mode = pageInfo.getMode() ;
		String keyword = pageInfo.getKeyword() ;
		
		if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
			sql += " where " + mode + " like '%" + keyword + "%'" ;
		}
		
		sql += " )";
		sql += " where ranking between ? and ? ";
		
      List<frboard> lists = new ArrayList<frboard>() ;
      
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


   public List<frboard> SelectAll() throws Exception{
      // 전체 회원 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.
      String sql = " select * from boards order by no desc" ; 
      
      List<frboard> lists = new ArrayList<frboard>() ;
      
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

   private frboard makeBean(ResultSet rs) throws Exception {
      frboard bean = new frboard() ;
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



   public frboard getDataByPk(int no) throws Exception {
      System.out.println("찾고자 하는 글번호: " + no);
      
      String sql = " select * from boards" ;
      sql += " where no = ?" ;
      
      PreparedStatement pstmt = null ;
      ResultSet rs= null ;
      Connection conn = null ;
      
      frboard bean = null;

      try {
          conn = super.getConnection();
          pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, no);
          rs = pstmt.executeQuery();

          if (rs.next()) {
              bean = this.makeBean(rs);

              // 조회수 증가
              String updateSql = "update boards set readhit = readhit + 1 where no = ?";
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

   public int UpdateData(frboard bean) throws Exception {
      
      int cnt = -1;
      
      String sql = " update boards set ";
            sql += " category = ?, writer = ?, subject = ?, content = ?, regdate = sysdate,";
            sql += " readhit = ?, groupno = ?, orderno = ?, depth = ?,";
            sql += " image01 = ?";
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
    /*  pstmt.setString(5, bean.getRegdate()) ;*/
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
   
   public frboard GetDataByPk(int no) throws Exception{
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
		
		frboard bean = null ;		
		if(rs.next()) {
			bean = this.makeBean(rs) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return bean;
	}


   public int DeleteData(int no) throws Exception {
	// 게시물에서 해당 글번호를 삭제합니다. 
			System.out.println("삭제될 글번호 : " + no);
			int cnt = -1 ;
			
			String sql = " delete from boards where no = ?" ; 
			
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


		/*
		 * public frboard getData(int no) throws Exception{ String sql2 =
		 * " select * from boards"; sql2 += " where no = ? ";
		 * 
		 * PreparedStatement pstmt = null; ResultSet rs = null; Connection conn = null;
		 * 
		 * if(conn==null) {conn = super.getConnection();}
		 * 
		 * pstmt = conn.prepareStatement(sql2); pstmt.setInt(1, no); rs =
		 * pstmt.executeQuery();
		 * 
		 * frboard bean = null;
		 * 
		 * if(rs.next()) { bean = this.makeBean(rs); } if(rs != null){rs.close();}
		 * if(pstmt != null){pstmt.close();} if(conn != null){conn.close();}
		 * 
		 * pstmt = null; rs = null; conn = null;
		 * 
		 * String sql3 =
		 * " update boards set writer = ?, subject = ?, content = ?, regdate = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), readhit = ?+1"
		 * ; sql3 += " where no = ?"; int cnt = -1;
		 * 
		 * if(conn == null) {conn = super.getConnection();} conn.setAutoCommit(false);
		 * 
		 * pstmt = conn.prepareStatement(sql3); pstmt.setString(1, bean.getWriter());
		 * pstmt.setString(2, bean.getSubject()); pstmt.setString(3, bean.getContent());
		 * pstmt.setString(4, bean.getRegdate()); pstmt.setInt(5, bean.getReadhit());
		 * pstmt.setInt(6, bean.getNo());
		 * 
		 * cnt = pstmt.executeUpdate(); conn.commit();
		 * 
		 * if(pstmt != null) {pstmt.close();} if(conn != null) {conn.close();}
		 * 
		 * pstmt = null; rs = null; conn = null;
		 * 
		 * String sql = " select * from boards"; sql += " where no = ? ";
		 * 
		 * if(conn==null) {conn = super.getConnection();} conn.setAutoCommit(false);
		 * 
		 * pstmt = conn.prepareStatement(sql); pstmt.setInt(1, no); rs =
		 * pstmt.executeQuery();
		 * 
		 * bean = null;
		 * 
		 * if(rs.next()) { bean = this.makeBean(rs); }
		 * 
		 * if(rs != null){rs.close();} if(pstmt != null){pstmt.close();} if(conn !=
		 * null){conn.close();}
		 * 
		 * return bean; }
		 */
   }