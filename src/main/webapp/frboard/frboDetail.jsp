<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   
<%@ page import="com.animal.model.frboard"%>
<%@ page import="com.animal.dao.frBoardDao"%>
   
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>


<!DOCTYPE html>
<html>
<head>
   <title>${bean.subject}</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <style type="text/css">
      /* 댓글들을 위한 스타일 지정 */
      * {
         padding: 0;
         margin: 0;
         color: #333;
      }
      ul { list-style: none; }
      #container { padding: 30px 20px; }
      #insertComment {
         padding: 20px 15px;
         border-bottom: 1px solid #7BAEB5;
      }

      #insertComment label {
         display: inline-block;
         width: 80px;
         font-size: 14px;
         font-weight: bold;
         margin-bottom: 10px;
      }

      #insertComment input[type='text'], #insertComment textarea {
         border: 1px solid #ccc;
         vertical-align: middle;
         padding: 3px 10px;
         font-size: 12px;
         line-height: 150%;
      }

      #insertComment textarea {
         width: 450px;
         height: 120px ;
      }

      .commentItem {
         font-size: 13px;
         color: #333;
         padding: 15px;
         border-bottom: 1px dotted #ccc;
         line-height: 150%;
      }

      .commentItem .writer {
         color: #555;
         line-height: 200%;
      }

      .commentItem .writer input {
         vertical-align: middle;
      }

      .commentItem .writer .name {
         color: #222;
         font-weight: bold;
         font-size: 14px;
      }
      
      .form-group {
         margin-bottom: 3px;
      }
      
      .form-control {
         height: 25px;
      }
      .btn-primary{opacity: 0.8;}
      #backButton{margin:auto;color:white;}
      a {text-decoration: none;color: white;}
      table
      {          
          border: 1px solid #444444;
          border-collapse: collapse;
        }
        #subjectArea{min-height:400px;}      
        
         .find-btn{
         text-align: center;}

        
   </style>

 <script type="text/javascript">
      function addNewItem(cnum, writer, content, regdate) {
         /* 댓글 1개 추가 */
         var litag = $('<li>'); /* 댓글 li */
         litag.addClass('commentItem');
         
         var ptag = $('<p>'); /* 작성자 정보 */
         ptag.addClass('writer');
         
         var spantag = $('<span>'); /* 작성자 이름 */
         spantag.addClass('name');
         spantag.html(writer + '님');
         
         var spandate = $('<span>'); /* 작성 일자 */
         spandate.html('&nbsp;&nbsp;/&nbsp;&nbsp;' + regdate + ' ');
         
         if(writer == '${sessionScope.loginfo.id}'){
            var inputtag = $('<input/>'); /* 삭제하기 */
            var attrlist = {'id':writer, 'class':'btn btn-outline-primary', 'type':'button', 'value':'삭제', 'pmkey' : cnum};
            inputtag.attr(attrlist);
            inputtag.addClass('delete_btn');
            
            var spanTag = $('<span>');
            spanTag.html('&nbsp;&nbsp;');
            
            var inputtag2 = $('<input/>'); 
            var attrlist2 = {'id':writer, 'class':'btn btn-outline-primary', 'type':'button', 'value':'수정', 'pmkey' : cnum};
            inputtag2.attr(attrlist2);
            inputtag2.addClass('update_btn');
         }else{
            var inputtag = '';
            var inputtag2 = '';
         }
         
         var content_p = $('<p>');
         content_p.html(content);
         
         ptag.append(spantag).append(spandate).append(inputtag).append(spanTag).append(inputtag2);
         litag.append(ptag).append(content_p);
         
         $('#comment_list').append(litag);
      }
   
      function getListComment() {
         $('#comment_list').empty();
         $.ajax({
            url : '<%=notWithFormTag%>cmList',
            data : 'no=' + '${bean.no}',
            type : 'get',
            dataType : 'json',
            success : function(result, status) {
               /* result : 넘어온 결과 값 */
               $.each(result, function(idx) {
                  var cnum = result[idx].cnum;
                  var writer = result[idx].writer;
                  var content = result[idx].content;
                  var regdate = result[idx].regdate;
                  addNewItem(cnum, writer, content, regdate);
               })
            }
         });
      }
      
      /* 삭제 버튼 */
      /* on() : 선택된 요소에 이벤트 핸들러 함수 연결 */
      /* data 영역의 pmkey : 코멘트의 기본 키 */
      $(document).on('click', '.delete_btn', function() {
         if(confirm('선택하신 댓글을 삭제하시겠습니까?')){
            $.ajax({
               url : '<%=notWithFormTag%>cmDelete',
               data : 'cnum=' + $(this).attr('pmkey') + "&no=" + '${bean.no}',
               type : 'get',
               dataType : 'text',
               success : function(result, status) {
                  getListComment();
               }
            });
         }
      });
      
      $(document).on('click', '.update_btn', function() {
         var btn_idx = $(this).parent().parent().index();
         
         $('#comment_list li').each(function(index, item) {
            var li_idx = $(item).index();
            if(li_idx == btn_idx){
               var chdivtag = $(this).find('div').length;
               if(chdivtag == 0){
                  var length = $(this).children().length;
                  var chinputtag = $(this).find('input');
                  var pmkey = chinputtag.attr('pmkey');
                                                
                  if(length < 3){
                     var attrlist = "";
                     
                     var divTag = $('<div>');
                     
                     var formTag = $('<form>');
                     attrlist = {'class':'form-horizontal', 'role':'form'};
                     formTag.attr(attrlist);
                     
                     var tableTag = $('<table>');
                     attrlist = {'class':'table table-borderless'};
                     tableTag.attr(attrlist);
                     
                     var theadTag = $('<thead>');
                     var tbodyTag = $('<tbody>');
                     
                     var trTag1 = $('<tr>');
                     var tdTag1 = $('<td>');
                     attrlist = {'width':'40%'};
                     tdTag1.attr(attrlist);
                     var textTag = $('<textarea>');
                     attrlist = {'id':'updateArea', 'rows':3, 'cols':50};
                     textTag.attr(attrlist);
                     
                     tdTag1.append(textTag);
                     trTag1.append(tdTag1);
                     
                     var trTag2 = $('<tr>');
                     var tdTag2 = $('<td>');
                     attrlist = {'width':'20%'};
                     tdTag1.attr(attrlist);
                     var inputtag = $('<input/>');
                     attrlist = {'id':'btnUpdate', 'class':'btn btn-outline-primary', 'type':'button', 'value':'등록', 'pmkey': pmkey};
                     inputtag.attr(attrlist);
                     inputtag.addClass('insert_btn');
                     
                     tdTag2.append(inputtag);
                     trTag2.append(tdTag2);
                     
                     tbodyTag.append(trTag1).append(trTag2);
                     tableTag.append(theadTag).append(tbodyTag);
                     formTag.append(tableTag);
                     divTag.append(formTag);
                     $(this).append(divTag);
                  }
               }else{
                  $(this).find('div').remove();
               }
            }
         });
      });
      
      $(document).on('click', '.insert_btn', function() {
         if(confirm('댓글을 수정하시겠습니까?')){
            if($('#updateArea').val() == ""){
               alert('수정할 내용을 입력해 주세요.');
               $('#updateArea').focus();
            }else{
               $.ajax({
                  url : '<%=notWithFormTag%>cmUpdate',
                  data : 'cnum=' + $(this).attr('pmkey') + "&no=" + '${bean.no}' + "&content=" + $('#updateArea').val(),
                  type : 'post',
                  dataType : 'text',
                  success : function(result, status) {
                     getListComment();
                  }
               });
            }
         }
      });
      
      $(document).ready(function() {
         getListComment(); /* 댓글 리스트 목록 반환 */
         
           /** 댓글 내용 등록 이벤트 */
           $("#comment_form").submit(function(){
              // 작성자 이름에 대한 입력 여부 검사
              if (!$("#writer").val()) {
                 alert("이름을 입력하세요.");
                 $("#writer").focus();
                 return false;
              }
      
              // 내용에 대한 입력 여부 검사
              if (!$("#content").val()) {
                 alert("내용을 입력하세요.");
                 $("#content").focus();
                 return false;
              }      
               
              var url = '<%=notWithFormTag%>cmInsert' ;
              var parameter = $('#comment_form').serialize() ;
              $.post(url, parameter, function( data ) {
                 /* alert( '댓글이 작성되었읍니다.' ) ; */
                 getListComment() ; /* 목록 갱신 */
                 $("#writer").val('') ;
                 $("#content").val('') ;
              }).fail(function() {
                 alert("댓글 작성에 실패했습니다. 잠시 후에 다시 시도해 주세요.");
                 return false;
              });
              return true;
           });         
      });
   </script>
</head>
<body>

   <div class="container mt-3">
      <table class="table table-striped">
         <thead>
         </thead>
         <tbody>
            <tr>
               <td align="center">분류</td>
               <td>${bean.category}</td>
            </tr>
            <tr>
               <td align="center">작성자</td>
               <td>${bean.writer}</td>
            </tr>
            <tr>
               <td align="center">글제목</td>
               <td>${bean.subject}</td>
            </tr>
            
            <%-- <tr>
                     <td align="center">이미지</td>
                     <td>
                        <div class="card" style="width: 30rem; float:left;">
                           <img src="<%=contextPath%>/image/${bean.image01}">
                       
                     </div> 
                  
                    </td>
               </tr> --%>
               
               
               <c:if test="${not empty bean.image01}">
                   <tr>
                   <td align="center">이미지</td>
                      <td>
                    <div class="card" style="width: 30rem; float:left;">
                       <img src="<%=contextPath%>/image/${bean.image01}">
                           </div> 
                           </td>
                       </tr>
                 </c:if>
              
                 
            
                 
               <td align="center">글내용</td>
               <td>
               <textarea id="content" name="content" rows="3" cols="50" class="form-control" readonly style="height:200px;">${bean.content}</textarea>
               </td>
            </tr>    
            <tr>
               <td align="center">작성 일자</td>
               <td>${bean.regdate}</td>
            </tr>
            <tr>
               <td align="center">조회수</td>
               <td>${bean.readhit}</td>
            </tr>         
         </tbody>
      </table>
       <div class="find-btn inline-flex">
         
	         <button type="button" class="btn btn-primary"> 
	            <c:set var="replyInfo" value="&groupno=${bean.groupno}&orderno=${bean.orderno}&depth=${bean.depth}" />
	               <a href="<%=notWithFormTag%>frboReply&no=${bean.no}&category=${bean.category}${requestScope.pageInfo.flowParameter}${replyInfo}">답글</a>
	         </button>  
	         
	         <c:if test="${sessionScope.loginfo.id==bean.writer or whologin == 2}">
	         <button type="button" class="btn btn-primary">
	            <a href="<%=notWithFormTag%>frboUpdate&no=${bean.no}${requestScope.pageInfo.flowParameter}">수정</a>
	         </button>
	         <button type="button" class="btn btn-danger">
	            <a href="<%=notWithFormTag%>frboDelete&no=${bean.no}${requestScope.pageInfo.flowParameter}">삭제</a>
	         </button>	      
	         </c:if>  
	                 <button id="backButton" type="button" class="btn btn-primary" onclick="history.back();">
            돌아 가기
         </button>
           
      </div>
      
        <br/>



      <div>
         <!-- 댓글 영역 -->
        <div class="col-sm-12">
            <ul id="comment_list">
               <!-- 여기에 동적 생성 요소가 들어가게 됩니다. -->
            </ul>
            <div id="insertComment">
               <form id="comment_form" method="post" role="form"
                  class="form-horizontal">
                  <table class="table">
                     <thead>
                     </thead>
                     <tbody>
                        <tr>
                           <td>
                              <label for="content" class="col-xs-3 col-lg-3 control-label">작성자</label>
                           </td>
                           <td>
                              <input type="hidden" name="no" value="${bean.no}" />
                              <input type="text" name="fakewriter" id="fakewriter"
                              class="form-control" size="10" disabled="disabled"
                              value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})님">
                              <input type="hidden" name="writer" id="writer"
                              value="${sessionScope.loginfo.id}">
                           </td>
                        </tr>
                        <tr>
                           <td>
                              <label for="content"
                              class="col-xs-3 col-lg-3 control-label">댓글 내용</label>
                           </td>
                           <td>
                              <textarea name="content" rows="3" cols="50"
                                 id="content"></textarea>
                           </td>
                        </tr>
                        <tr>
                           <td colspan="2">
                              <button type="submit" class="btn btn-info">등록하기</button>
                           </td>
                        </tr>
                     </tbody>
                  </table>
               </form>
            </div>
         </div>
      </div>
   </div>
</body>
</html>