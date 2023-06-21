package com.animal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.animal.model.CartItem;
/*import com.animal.model.CartItem;*/
import com.animal.model.Product;
import com.animal.utility.MyUtility;
import com.animal.utility.Paging; 

public class ProductDao extends SuperDao{
   public int DeleteData(int num) throws Exception{
      // 관리자가 해당 상품 번호 num을 삭제하려고 시도합니다.
      // 참조 무결성 제약 조건을 확인하고, 주문 상세 테이블의 비고(remark) 컬럼에 변경 사항을 기록합니다.
      System.out.println("삭제할 상품 번호 : " + num);
      int cnt = -1 ;
      
      String sql = "" ;
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;      
      if(conn==null) {conn=super.getConnection();}      
      conn.setAutoCommit(false);
      
      // step01 : 주문 상세 테이블의 비고 컬럼에 수정하기
            Product bean = this.GetDataByPk(num) ;      
            
            String remark = MyUtility.getCurrentTime() + bean.getName() + "(상품 번호 : " + num + "번) 상품이 삭제 되었습니다." ;
            
            sql = " update orderdetails set remark = ? where pnum = ? " ;      
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, remark);
            pstmt.setInt(2, num);
            cnt = pstmt.executeUpdate() ;      
            if(pstmt != null) {pstmt.close();}
      
      // step02 : 상품 테이블에서 해당 상품 번호 삭제하기      
      sql = " delete from products where num = ? " ;
      pstmt=conn.prepareStatement(sql);
      pstmt.setInt(1, num);      
      cnt = pstmt.executeUpdate() ;   
      
      conn.commit();      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}      
      return cnt ;
   }
   
   
     public CartItem getCartItem(Integer pnum, Integer qty) throws Exception { 
         // 해당 상품 번호에 대한 Product 정보를 CartItem 객체에 대입하고 반환해 줍니다. 
         Product bean = this.GetDataByPk(pnum) ;
         
         CartItem item = new CartItem() ;
         
         item.setImage01(bean.getImage01()) ;
         item.setMid(null) ; // 임시 저장용 테이블과 관련 있음.
         item.setPname(bean.getName()) ;
         item.setPnum(pnum);
         item.setPoint(bean.getPoint()) ;
         item.setPrice(bean.getPrice()) ;
         item.setQty(qty) ;
         
         return item;
      }
    
   
   public Product GetDataByPk(int num) throws Exception{
      // 해당 상품 번호를 이용하여 상품 bean 객체를 반환해 줍니다.
      System.out.println("찾고자 하는 상품 번호: " + num);
      
      String sql = " select * from products" ;
      sql += " where num = ? " ;
      
      PreparedStatement pstmt = null ;
      ResultSet rs= null ;
      Connection conn = null ;
      
      if(conn==null) {conn = super.getConnection() ;}      
      pstmt = conn.prepareStatement(sql);      
      pstmt.setInt(1, num);      
      rs = pstmt.executeQuery();
      
      Product bean = null ;      
      if(rs.next()) {
         bean = this.makeBean(rs) ;
      }
      
      if(rs!=null){rs.close();}
      if(pstmt!=null){pstmt.close();}
      if(conn!=null){conn.close();}
      
      return bean;
   }   
   
   public int InsertData(Product bean) throws Exception{
      // bean 객체 1개를 등록합니다.
      System.out.println(bean);
      int cnt = -1 ;
      
      String sql = " insert into products(num, name, company, comments, image01, image02, image03, stock, price, point, remark, category, inputdate)" ;       
      sql += " values(myproduct.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, '', ?, ?)" ; 
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;      
      if(conn==null) {conn=super.getConnection();}      
      conn.setAutoCommit(false);       
      pstmt=conn.prepareStatement(sql);
      
      pstmt.setString(1, bean.getName()) ;      
      pstmt.setString(2, bean.getCompany()) ;      
      pstmt.setString(3, bean.getComments()) ;
      pstmt.setString(4, bean.getImage01()) ;
      pstmt.setString(5, bean.getImage02()) ;
      pstmt.setString(6, bean.getImage03()) ;
      pstmt.setInt(7, bean.getStock()) ;
      pstmt.setInt(8, bean.getPrice()) ;
      pstmt.setInt(9, bean.getPoint()) ;
      pstmt.setString(10, bean.getCategory()) ;
      pstmt.setString(11, bean.getInputdate()) ;
      
      cnt = pstmt.executeUpdate() ;      
      conn.commit();      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}      
      return cnt ;
   }
   
   
   public List<Product> SelectAll() throws Exception{
      // 상품 목록을 조회하여 반환해 줍니다.
      String sql = " select * from products order by num desc " ;
      
      List<Product> lists = new ArrayList<Product>() ;
      
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
   
   public List<Product> SelectAll(Paging pageInfo) throws Exception{
      // TopN 구문을 이용하여 페이징 처리된 목록을 반환해 줍니다.
      String sql = " select num, name, company, comments, image01, image02, image03, stock, price, point, remark, category, inputdate ";
      sql += " from (select num, name, company, comments, image01, image02, image03, stock, price, point, remark, category, inputdate, ";
      sql += " rank() over(order by num desc) as ranking ";
      sql += " from products)";
      sql += " where ranking between ? and ? ";
      
      List<Product> lists = new ArrayList<Product>() ;
      
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
   
   
   private Product makeBean(ResultSet rs) throws Exception{
      Product bean = new Product();
      
      bean.setCategory(rs.getString("category"));
      bean.setComments(rs.getString("comments"));
      bean.setCompany(rs.getString("company"));
      bean.setImage01(rs.getString("image01"));
      bean.setImage02(rs.getString("image02"));
      bean.setImage03(rs.getString("image03"));
      
      //bean.setInputdate(rs.getString("inputdate"));
      
      bean.setInputdate(String.valueOf(rs.getDate("inputdate")));
      
      bean.setName(rs.getString("name"));
      bean.setNum(rs.getInt("num"));
      bean.setPoint(rs.getInt("point"));
      bean.setPrice(rs.getInt("price"));
      bean.setRemark(rs.getString("remark"));
      bean.setStock(rs.getInt("stock"));
      
      return bean;
   }


   // 상품 1개
   public Product getDataByPk01(int num) {
      return new Product(1, "강아지용품", "멍멍이월드", "강아지가 좋아하는 개껌", "child2.jpg", "child3.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25");      
   } 
   
   /*
    * public Product getDataByPk02(int num) { if(num == 10) { return new
    * Product(10, "아동복01", "키즈패션", "신발이 넘 이쁘요.", "child2.jpg", "child3.jpg", null,
    * 10, 10000, 4, "비고란", "child", "2000/12/25");
    * 
    * }else { return new Product(20, "아동복02", "키즈패션", "조아요.", "child4.jpg",
    * "child5.jpg", null, 10, 20000, 4, "비고란", "child", "2000/12/25"); } }
    */
   
   // 회원 목록 보기 기능) 회원 전체 목록을 반환해 줍니다.
   public List<Product> getDataList(){
      List<Product> datalist = new ArrayList<Product>() ;
      
      datalist.add(new Product(10, "강아지용", "멍멍이월드", "좋아요", "child2.jpg", "child3.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25"));
      
      datalist.add(new Product(20, "강아지용", "멍멍이월드", "조아요.", "child4.jpg", "child5.jpg", null, 10, 20000, 4, "비고란", "child", "2000/12/25"));
      
      datalist.add(new Product(30, "고양이용", "캐츠용품", "너무 잘 이용하고 있어요", "child5.jpg", "child6.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25"));
      
      datalist.add(new Product(40, "고양이용", "캐츠용품", "고양이가 참 좋아하는 상품", "child7.jpg", "child8.jpg", null, 10, 10000, 4, "비고란", "child", "2000/12/25"));
      
      datalist.add(new Product(50, "강아지용", "도그월드", "강아지가 정말 좋아해요", "woman_coat2.gif", "onepiece1.gif", null, 10, 10000, 4, "비고란", "child", "2000/12/25"));
      
      datalist.add(new Product(60, "고양이용", "고양이집사", "강아지가 진짜 좋아하는 옷이에요", "onepiece2.gif", "onepiece3.gif", null, 10, 10000, 4, "비고란", "child", "2000/12/25"));
      
      return datalist ;
   }


   public int GetTotalRecordCount(String mode, String keyword) throws Exception{
      System.out.print("검색할 필드명 : " + mode);
      System.out.print(", 검색할 키워드 : " + keyword + "\n");
      
      String sql = " select count(*) as cnt from products " ;
      
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

   public int UpdateData(Product bean) throws Exception{
      // 관리자가 상품 정보를 수정합니다.
      System.out.println(bean);
      int cnt = -1 ;
      
      // remark 컬럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
      String sql = " update products set ";
      sql += " name = ?, company = ?, comments = ?, image01 = ?, image02 = ?, ";
      sql += " image03 = ?, stock = ?, price = ?, point = ?, ";
      sql += " category = ?, inputdate = ? " ;       
      sql += " where num = ?" ; 
      
      Connection conn = null ;
      PreparedStatement pstmt = null ;      
      if(conn==null) {conn=super.getConnection();}      
      conn.setAutoCommit(false);       
      pstmt=conn.prepareStatement(sql);
      
      pstmt.setString(1, bean.getName()) ;      
      pstmt.setString(2, bean.getCompany()) ;      
      pstmt.setString(3, bean.getComments()) ;
      pstmt.setString(4, bean.getImage01()) ;
      pstmt.setString(5, bean.getImage02()) ;
      pstmt.setString(6, bean.getImage03()) ;
      pstmt.setInt(7, bean.getStock()) ;
      pstmt.setInt(8, bean.getPrice()) ;
      pstmt.setInt(9, bean.getPoint()) ;
      pstmt.setString(10, bean.getCategory()) ;
      pstmt.setString(11, bean.getInputdate()) ;
      pstmt.setInt(12, bean.getNum()) ;
      
      cnt = pstmt.executeUpdate() ;      
      conn.commit();      
      if(pstmt!=null) {pstmt.close();}
      if(conn!=null) {conn.close();}      
      return cnt ;
   }


}