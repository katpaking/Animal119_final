<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* box model 공부할 것 */
	.input-group{margin: 7px;}
	
	.input-group-text{
		display:block;
		margin-left:auto;
		margin-right:auto;
	}
	#buttonset{margin-top:15px;}
	.radio_gender, .chk_hobby{font-size:1.1rem;} /* 주위 글꼴의 1.1배 */
	
</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	
  	<link rel="stylesheet" href="/resources/demos/style.css">
  	  	
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#hiredate').datepicker(); */
  	  		$('#hopeday').datepicker({dateFormat: "yy/mm/dd"}); 
  	  	  /*  $('#regdate').datepicker({dateFormat: "yy/mm/dd"});*/
  	  	 	
  		});
  		
  		function validCheck(){
  			/* alert('반환 값이 false이면 이벤트 전파가 되지 않습니다.'); */
  			var subject = $('#subject').val();
  			if(subject.length < 3 || subject.length > 30){
  				alert('글 제목은 3글자 이상 30글자 이하여야 합니다');
  				$('#subject').focus();
  				return false;
  			}
  			
  			var content = $('#content').val();
  			if(content.length < 5 || content.length > 1000){
  				alert('글 내용은 5글자 이상 1000글자 이하여야 합니다');
  				$('#content').focus();
  				return false;
  			}
  			var hopeday = $('#hopeday').val();
  			
  			var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/ ;
  			var result = regex.test(hopeday) ;
  			
  			if(result == false){
  				alert('신청 날짜 형식은 반드시 yyyy/mm/dd 형식으로 작성해 주세요.');
  				$('#hopeday').focus() ;
  				return false ;
  			} 	

  			
  			return true ;
  		}
  	</script>
</head>
<body>
	<div class="container mt-3 col-md-5">
		<img src="<%=contextPath%>/image/입양해주세요.png" alt="img" class="d-block" height="40">		
		
		<form action="<%=withFormTag%>" method="post">
		<input type="hidden" name="command" value="AdoptInsert">		
		
		<div id="adoptNum" class="input-group">
				<span class="input-group-text col-md-2" hidden>게시글 번호</span> 
				<input id="fakenum" name="fakenum" disabled="disabled" type="number" class="form-control" placeholder="" hidden>
				<input id="num" type="hidden" name="num">
				<!--  숫자인 걸 강조하고 싶을 때 number로 해준다-->
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">작성자</span> 
				<c:set var="userInfo" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})"/> 
				<input id="fakewriter" name="fakewriter" disabled="disabled" type="text" class="form-control" placeholder="" value="${userInfo}">
				<input id="writer" type="hidden" name="writer" value="${sessionScope.loginfo.id}">
				</div>
				
			<div class="input-group">
				<span class="input-group-text col-md-2">글제목</span> 
				<input id="fakesubject" name="fakesubject" type="text" class="form-control" placeholder="" value="<%=request.getParameter("num")%>번 친구 입양을 신청합니다." disabled>
				<input id="subject" name="subject" type="text" class="form-control" placeholder="" value="<%=request.getParameter("num")%>번 친구 입양을 신청합니다." hidden>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">전화번호</span> 
				<input id="phone" name="phone" type="text" class="form-control"
					placeholder="">
			</div>

			<div class="input-group">
				<span class="input-group-text col-md-2" hidden>등록 일자</span> 
				<input id="fakeregdate" name="fakeregdate" disabled="disabled" type="datetime" class="form-control" placeholder="" hidden>
				<input id="regdate" name="regdate" type="hidden" class="form-control" placeholder="" hidden>
			</div>		
			

				
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg" onclick="return validCheck();"> 
					등록
				</button>
				
				&nbsp;&nbsp;&nbsp;
				
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>