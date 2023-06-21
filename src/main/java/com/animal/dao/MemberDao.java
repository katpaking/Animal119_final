package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.animal.model.Member;
import com.animal.model.Product;
import com.animal.utility.MyUtility;
import com.animal.utility.Paging;

public class MemberDao extends SuperDao{
	public int DeleteData(String id) throws Exception{
		// id에 해당하는 회원이 탈퇴를 합니다.
		System.out.println("탈퇴할 아이디 : " + id);
		
		// 회원에 대한 1건(bean 객체) 데이터를 데이터 베이스에 추가합니다.
		int cnt = -1 ;

		Connection conn = null ;
		PreparedStatement pstmt = null ;
		
		if(conn==null) {conn=super.getConnection();}		
		conn.setAutoCommit(false); 
		
		String sql = "";
		
		Member bean = this.getDataByPk(id) ;
		String content = MyUtility.getCurrentTime() + bean.getName() + "(아이디 : " + id + ")님이 탈퇴를 하였습니다." ;
		
		// step01 : 게시물 테이블 remark 컬럼 업데이트
		sql = " update boards set content = ? where writer = ? " ;
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, content) ;
		pstmt.setString(2, id) ;		
		cnt = pstmt.executeUpdate() ;
		 
		// step02 : 회원 테이블 id 행 삭제
		sql = " delete from members where id = ? " ;
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, id) ;
		cnt = pstmt.executeUpdate() ;
		
		conn.commit();
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		return cnt ;
	}	
	
	// 회원 1사람의 정보를 반환해 줍니다.
	// 현재 일시적인 데이터이고, 차후 데이터 베이스에서 직접 읽어 오도록 하겠습니다. 
	public Member getDataByPk01(String id) {
		return new Member("hong", "홍길동", "1234", "male", "농구,배구", "2002/06/24", 100, "비고란입니다.");
	}
	
	// 회원 목록 보기 기능) 회원 전체 목록을 반환해 줍니다.
	public List<Member> getDataList(){
		List<Member> datalist = new ArrayList<Member>() ;
		
		datalist.add(new Member("hong", "홍길동", "1234", "male", "농구,배구", "2002/06/24", 100, "비고란입니다."));
		datalist.add(new Member("park", "박혁신", "1234", "male", "농구,배구", "2002/06/24", 100, "비고란입니다."));
		datalist.add(new Member("choi", "최만위", "1234", "female", "농구,배구", "2002/06/24", 100, "비고란입니다."));
		datalist.add(new Member("kim", "김동섭", "1234", "male", "농구,배구", "2002/06/24", 100, "비고란입니다."));
		datalist.add(new Member("lee", "이수돌", "1234", "male", "농구,배구", "2002/06/24", 100, "비고란입니다."));
		
		return datalist ;
	}
	

	public Member SelectData(String id, String password) throws Exception{
		System.out.println(id + "/" + password);
		
		// 아이디와 비번을 이용하여 로그인 가능한지 판단합니다.
		String sql = " select * from members" ;
		sql += " where id = ? and password = ?" ;
		
		// ?는 placeholder이라고 하며, 치환될 대상입니다.
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		
		if(conn==null) {conn = super.getConnection() ;}
		
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환은 반드시 실행 전에 해야 합니다.
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		
		rs = pstmt.executeQuery();
		
		Member bean = null ;
		
		if(rs.next()) {
			bean = this.makeBean(rs) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return bean;
	}

	public int InsertData(Member bean) throws Exception{
		System.out.println(bean);
		
		// 회원에 대한 1건(bean 객체) 데이터를 데이터 베이스에 추가합니다.
		int cnt = -1 ;
		
		String sql = " insert into members(id, name, password, gender, hobby, hiredate, point, remark)" ; 
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?)" ; 
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false); 
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getId()) ;
		pstmt.setString(2, bean.getName()) ;
		pstmt.setString(3, bean.getPassword()) ;
		pstmt.setString(4, bean.getGender()) ;
		pstmt.setString(5, bean.getHobby()) ;
		pstmt.setString(6, bean.getHiredate()) ;
		pstmt.setInt(7, bean.getPoint()) ;
		pstmt.setString(8, bean.getRemark()) ;
		
		cnt = pstmt.executeUpdate() ;
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt ;
	}

	public List<Member> SelectAll() throws Exception{
		// 전체 회원 목록을 리스트 컬렉션에 저장시키고 반환해 줍니다.
		String sql = " select * from members" ; 
		
		List<Member> lists = new ArrayList<Member>() ;
		
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
	
	public List<Member> SelectAll(Paging pageInfo) throws Exception{
	      // TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
	      String sql = " select id, name, password, gender, hobby, hiredate, point, remark ";
	      sql += " from (select id, name, password, gender, hobby, hiredate, point, remark, ";
	      sql += " rank() over(order by id desc) as ranking ";
	      sql += " from members)";
	      sql += " where ranking between ? and ? ";
	      
	      List<Member> lists = new ArrayList<Member>() ;
	      
	      Connection conn = null; 
	      PreparedStatement pstmt = null ;
	      ResultSet rs = null ; 
	      
	      if(conn==null){conn=super.getConnection();}
	      pstmt = conn.prepareStatement(sql) ;
	      
	      pstmt.setInt(1, pageInfo.getBeginRow());
	      pstmt.setInt(2, pageInfo.getEndRow());      
	      rs=pstmt.executeQuery();
	      
	      while (rs.next()){
	         lists.add(this.makeBean(rs)) ;         
	      }
	      
	      if(rs!=null) {rs.close();}
	      if(pstmt!=null) {pstmt.close();}
	      if(conn!=null) {conn.close();}      
	      
	      return lists;
	   }      
	   

	private Member makeBean(ResultSet rs) throws Exception {
		Member bean = new Member() ;
		bean.setGender(rs.getString("gender"));
		bean.setHiredate(String.valueOf(rs.getDate("hiredate")));
		bean.setHobby(rs.getString("hobby"));
		bean.setId(rs.getString("id"));
		bean.setPoint(rs.getInt("point"));
		bean.setName(rs.getString("name"));
		bean.setPassword(rs.getString("password"));
		bean.setRemark(rs.getString("remark"));
		return bean;
	}

	public Member getDataByPk(String id) throws Exception {
		// id를 이용하여 회원을 조회합니다.
		System.out.println("찾고자 하는 아이디 : " + id);
		
		// 아이디와 비번을 이용하여 로그인 가능한지 판단합니다.
		String sql = " select * from members" ;
		sql += " where id = ? " ;
		
		// ?는 placeholder이라고 하며, 치환될 대상입니다.
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		
		if(conn==null) {conn = super.getConnection() ;}
		
		pstmt = conn.prepareStatement(sql);
		
		// ? 치환은 반드시 실행 전에 해야 합니다.
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery();
		
		Member bean = null ;
		
		if(rs.next()) {
			bean = this.makeBean(rs) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return bean;
	}

	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		  System.out.println("검색할 필드명 : " + mode);
	      System.out.println("검색할 키워드 : " + keyword + "\n");
	     
	      String sql = " select count(*) as cnt from members " ;
	      
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
}

