package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.animal.model.comment;

public class CommentDao02 extends SuperDao{

	public List<comment> GetDataByPk(int no) throws Exception{
		//해당 게시뭄ㄹ 번호에 달려 있는 댓글 목록을 가장 오래된 글부터 정렬하여 반환해줍니다
		String sql = " select * from comments";
		      sql += " where no = ? order by cnum asc " ;
		
		
		List<comment> lists = new ArrayList<comment>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, no);
	
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			
			lists.add(this.makebean(rs));
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}

		return lists;
	}

	private comment makebean(ResultSet rs) throws Exception{

        comment bean = new comment();
        
        bean.setCnum(rs.getInt("cnum"));
        bean.setContent(rs.getString("content"));
        bean.setNo(rs.getInt("no"));
        bean.setRegdate(rs.getString("regdate"));
        bean.setWriter(rs.getString("writer"));
        
		return bean;
	}

	    public int DeleteData(int cnum) throws Exception {
	    	System.out.println("삭제될 코멘트 번호 : " + cnum );
	    	
	    	int cnt = -1 ;
			
			String sql = " delete from comments where cnum = ? ";
			
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			//ResultSet 필요 없음
			
			if(conn==null) {conn=super.getConnection();}
			
			conn.setAutoCommit(false);
			// 자동커밋 잘 안되기도 하므로 아예 꺼놓자
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, cnum);
			
			cnt = pstmt.executeUpdate();
			
			conn.commit();

			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
			return cnt;
        
	    }
	    
		public int InsertData(comment bean) throws Exception {
		System.out.println(bean);
		
		// 회원에 대한 1건(bean 객체) 데이터를 데이터 베이스에 추가합니다. 
		int cnt = -1 ;
		
		String sql = " insert into comments(cnum, no, writer, content, regdate)";
		sql += " values(mycomment.nextval, ?, ?, ?, sysdate)";
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet 필요 없음
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		// 자동커밋 잘 안되기도 하므로 아예 꺼놓자
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, bean.getNo());
		pstmt.setString(2, bean.getWriter());
		pstmt.setString(3, bean.getContent());
		
		
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();

		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		return cnt;
	}


		public comment getDataByPk(int cnum) throws Exception {
			System.out.println("찾고자 하는 코멘트 번호: " + cnum);
			
			String sql = " select * from comments" ;
			sql += " where cnum = ? " ;
			
			PreparedStatement pstmt = null ;
			ResultSet rs= null ;
			Connection conn = null ;
			
			if(conn==null) {conn = super.getConnection() ;}		
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, cnum);		
			rs = pstmt.executeQuery();
			
			comment bean = null ;		
			if(rs.next()) {
				bean = this.makebean(rs) ;
			}
			
			if(rs!=null){rs.close();}
			if(pstmt!=null){pstmt.close();}
			if(conn!=null){conn.close();}
			
			return bean;
		}

		public int UpdateData(comment bean) throws Exception {

			int cnt = -1;
			
			String sql = " update comments set ";
			      sql += " no = ?, writer = ?, content = ?, regdate = ?";
			      sql += " where cnum = ?";
			
			Connection conn = null ;
			PreparedStatement pstmt = null ;
					
			if(conn==null) {conn=super.getConnection();}
					
			conn.setAutoCommit(false); 
					
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, bean.getNo());
			pstmt.setString(2, bean.getWriter()) ;
			pstmt.setString(3, bean.getContent()) ;
			pstmt.setString(4, bean.getRegdate()) ;
			
			pstmt.setInt(5, bean.getCnum());
			cnt = pstmt.executeUpdate() ;
			
			conn.commit();
			
			if(pstmt!=null) {pstmt.close();}
			if(conn!=null) {conn.close();}
			
			return cnt ;		
		}
			
			
		}
		