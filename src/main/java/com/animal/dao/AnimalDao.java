package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.animal.model.Animal;
import com.animal.model.CartItem;
import com.animal.utility.MyUtility;
import com.animal.utility.Paging; 

public class AnimalDao extends SuperDao{
   public int DeleteData(int num) throws Exception{
      // 관리자가 해당 상품 번호 num을 삭제하려고 시도합니다.
      // 참조 무결성 제약 조건을 확인하고, 주문 상세 테이블의 비고(remark) 컬럼에 변경 사항을 기록합니다.
      System.out.println("삭제할 동물 번호 : " + num);
      int cnt = -1 ;
      
      String sql = "" ;
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;      
      if(conn==null) {conn=super.getConnection();}      
      conn.setAutoCommit(false);
      
      
      
      // step02 : 상품 테이블에서 해당 상품 번호 삭제하기      
      sql = " delete from animals where num = ? " ;
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, num);      
      cnt = pstmt.executeUpdate() ;   
      
      conn.commit();      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}      
      return cnt ;
   }
   
   
   public CartItem getCartItem(Integer pnum, Integer qty) throws Exception{
      // 해당 상품 번호에 대한 Product 정보를 CartItem 객체에 대입하고 반환해 줍니다. 
      Animal bean = this.GetDataByPk(pnum) ;
      
      CartItem item = new CartItem() ;
      
      item.setImage01(bean.getImage01()) ;
      item.setMid(null) ; // 임시 저장용 테이블과 관련 있음.
      item.setPname(bean.getName()) ;
      item.setPnum(pnum);
      item.setQty(qty) ;
      
      return item;
   }   
   
   public Animal GetDataByPk(int num) throws Exception{
      // 해당 상품 번호를 이용하여 상품 bean 객체를 반환해 줍니다.
      System.out.println("찾고자 하는 등록 번호: " + num);
      
      String sql = " select * from animals" ;
      sql += " where num = ? " ;
      
      PreparedStatement pstmt = null ;
      ResultSet rs= null ;
      Connection conn = null ;
      
      if(conn==null) {conn = super.getConnection() ;}      
      pstmt = conn.prepareStatement(sql);      
      pstmt.setInt(1, num);      
      rs = pstmt.executeQuery();
      
      Animal bean = null ;      
      if(rs.next()) {
         bean = this.makeBean(rs) ;
      }
      
      if(rs!=null){rs.close();}
      if(pstmt!=null){pstmt.close();}
      if(conn!=null){conn.close();}
      
      return bean;
   }   
   
   public int InsertData(Animal bean) throws Exception{
      // bean 객체 1개를 등록합니다.
      System.out.println(bean);
      int cnt = -1 ;
      
      String sql = " insert into animals(num, name, type, kind, anigender, image01, image02, image03, remark, category, inputdate)" ;       
      sql += " values(myanimal.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ; 
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;      
      if(conn==null) {conn=super.getConnection();}      
      conn.setAutoCommit(false);       
      pstmt=conn.prepareStatement(sql);
      
      pstmt.setString(1, bean.getName()) ;      
      pstmt.setString(2, bean.getType()) ;      
      pstmt.setString(3, bean.getKind()) ;
      pstmt.setString(4, bean.getAnigender()) ;
      pstmt.setString(5, bean.getImage01()) ;
      pstmt.setString(6, bean.getImage02()) ;
      pstmt.setString(7, bean.getImage03()) ;
      pstmt.setString(8, bean.getRemark()) ;
      pstmt.setString(9, bean.getCategory()) ;
      pstmt.setString(10, bean.getInputdate()) ;
      
      cnt = pstmt.executeUpdate() ;      
      conn.commit();      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}      
      return cnt ;
   }
   
   
   public List<Animal> SelectAll() throws Exception{
      // 상품 목록을 조회하여 반환해 줍니다.
      String sql = " select * from animals order by num desc " ;
      
      List<Animal> lists = new ArrayList<Animal>() ;
      
      Connection conn = null; 
      PreparedStatement pstmt = null ;
      ResultSet rs = null ; 
      
      if(conn==null){conn=super.getConnection();}
      pstmt = conn.prepareStatement(sql) ;
      rs=pstmt.executeQuery();
      
      while (rs.next()){
         lists.add(this.makeBean(rs)) ;         
      }
      
      if(rs!=null) {rs.close();}
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}      
      
      return lists;
   }   
   
   public List<Animal> SelectAll(Paging pageInfo) throws Exception{
      // TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
      String sql = " select num, name, type, kind, anigender, image01, image02, image03, remark, category, inputdate ";
      sql += " from (select num, name, type, kind, anigender, image01, image02, image03, remark, category, inputdate, ";
      sql += " rank() over(order by num desc) as ranking ";
      sql += " from animals)";
      sql += " where ranking between ? and ? ";
      
      List<Animal> lists = new ArrayList<Animal>() ;
      
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
   
   
   private Animal makeBean(ResultSet rs) throws Exception{
      Animal bean = new Animal();
      
      bean.setCategory(rs.getString("category"));
      bean.setType(rs.getString("type"));
      bean.setKind(rs.getString("kind"));
      bean.setAnigender(rs.getString("anigender"));
      bean.setImage01(rs.getString("image01"));
      bean.setImage02(rs.getString("image02"));
      bean.setImage03(rs.getString("image03"));
      
      bean.setInputdate(String.valueOf(rs.getDate("inputdate")));
      
      bean.setName(rs.getString("name"));
      bean.setNum(rs.getInt("num"));
      bean.setRemark(rs.getString("remark"));
      
      return bean;
   }


   // 상품 1개
   public Animal getDataByPk01(int num) {
      return new Animal(20, "똘이", "강아지", "말티즈", "수컷",  "child2.jpg", null , null , "비고란", "양호", "2000/12/25");      
   } 
   
   
   // 회원 목록 보기 기능) 회원 전체 목록을 반환해 줍니다.
   public List<Animal> getDataList(){
      List<Animal> datalist = new ArrayList<Animal>() ;
      
      datalist.add(new Animal(10, "똘이", "강아지", "말티즈", "수컷",  "child2.jpg", null , null , "비고란", "양호", "2000/12/25"));
      
      datalist.add(new Animal(20, "초코", "강아지", "말티즈", "암컷",  "child2.jpg", null , null , "비고란", "양호", "2001/12/25"));
      
      datalist.add(new Animal(30, "딱지", "강아지", "말티즈", "수컷",  "child2.jpg", null , null , "비고란", "양호", "2002/12/25"));
      
      datalist.add(new Animal(40, "나비", "고양이", "랙돌", "수컷",  "child2.jpg", null , null , "비고란", "양호", "2003/12/25"));
      
      datalist.add(new Animal(50, "초롱", "고양이", "먼치킨", "수컷",  "child2.jpg", null , null , "비고란", "양호", "2004/12/25"));
      
      datalist.add(new Animal(60, "호야", "고앵이", "스핑크스", "암컷",  "child2.jpg", null , null , "비고란", "양호", "2005/12/25"));
      
      return datalist ;
   }


   public int GetTotalRecordCount(String mode, String keyword) throws Exception{
      System.out.print("검색할 필드명 : " + mode);
      System.out.print(", 검색할 키워드 : " + keyword + "\n");
      
      String sql = " select count(*) as cnt from animals " ;
      
      PreparedStatement pstmt = null ;
      ResultSet rs= null ;
      Connection conn = null ;
      int cnt = -1 ;
      
      if(conn==null) {conn = super.getConnection() ;}      
      pstmt = conn.prepareStatement(sql);               
      rs = pstmt.executeQuery();
      
      if(rs.next()) {
         cnt = rs.getInt("cnt") ;
      }
      
      if(rs!=null){rs.close();}
      if(pstmt!=null){pstmt.close();}
      if(conn!=null){conn.close();}
      
      return cnt ;
   }

   public int UpdateData(Animal bean) throws Exception{
      // 관리자가 상품 정보를 수정합니다.
      System.out.println(bean);
      int cnt = -1 ;
      
      // remark 컬럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
      String sql = " update animals set ";
      sql += " name = ?, type = ?, kind = ?, anigender = ?, image01 = ?, image02 = ?, ";
      sql += " image03 = ?, " ;
      sql += " category = ?, inputdate = ? " ;       
      sql += " where num = ?" ; 
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;      
      if(conn==null) {conn=super.getConnection();}      
      conn.setAutoCommit(false);       
      pstmt=conn.prepareStatement(sql);
      
      pstmt.setString(1, bean.getName()) ;      
      pstmt.setString(2, bean.getType()) ;      
      pstmt.setString(3, bean.getKind()) ;
      pstmt.setString(4, bean.getAnigender()) ;
      pstmt.setString(5, bean.getImage01()) ;
      pstmt.setString(6, bean.getImage02()) ;
      pstmt.setString(7, bean.getImage03()) ;
      pstmt.setString(8, bean.getCategory()) ;
      pstmt.setString(9, bean.getInputdate()) ;
      pstmt.setInt(10, bean.getNum()) ;
      
      cnt = pstmt.executeUpdate() ;      
      conn.commit();      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}      
      return cnt ;
   }
}