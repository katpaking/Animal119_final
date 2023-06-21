
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* box model 공부할 것 */
.input-group {
	margin: 7px;
}

.input-group-text {
	display: block;
	margin-left: auto;
	margin-right: auto;
}

#buttonset {
	margin-top: 15px;
}
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<link rel="stylesheet" href="/resources/demos/style.css">

<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  /* $('#regdate').datepicker();  
	  $('#regdate').datepicker({dateFormat: "yy/mm/dd"});*/
	});
	
function validCheck(){  /*폼 유효성 검사*/
	var subject = $('#subject').val();
	if(subject.length < 3 || subject.length > 50){
		alert('글 제목은 3글자 이상 50글자 이하여야 합니다');
		$('#subject').focus();
		return false;
	}
	var content = $('#content').val();
	if(content.length < 5 || content.length > 1500){
		alert('글 내용은 5글자 이상 1500글자 이하여야 합니다');
		$('#content').focus();
		return false;
	}
/*	var regdate = $('#regdate').val();
	
	var regex = /^\d{4}[\/-][01]\d{1}[\/-][0123]\d{1}$/ ;
	
	// [\/-] : 안쪽에 열거된 것 중 하나
	var result = regex.test(regdate);
	
	if(result == false){
		alert('날짜 형식은 반드시 yyyy/mm/dd 형식으로 작성해주세요.');
		$('#regdate').focus();
		return false;
	}*/
	return true;
}
</script>


</head>
<body>
	<div class="container mt-3 col-md-6">
	     
	     <table class="table table-striped">
		<!-- div=division(영역, 구역) 컨테이너 박스 만들어주는 구문-->
		
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="frboUpdate">
		<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">	
		<input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">	
		<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>"> 	
		<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>"> 	
		
		<input type="hidden" name="readhit" value="${bean.readhit}"> 	
		<input type="hidden" name="groupno" value="${bean.groupno}">	
		<input type="hidden" name="orderno" value="${bean.orderno}"> 
		<input type="hidden" name="depth" value="${bean.depth}">  
		
			<div class="input-group">
				<span class="input-group-text col-md-2">게시글번호</span> 
				<input id="fakeno"	name="fakeno" disabled="disabled" type="number" class="form-control" placeholder="" value="${requestScope.bean.no}">
				<input type="hidden" id="no" name="no" value="${requestScope.bean.no}">
				<!--  숫자인 걸 강조하고 싶을 때 number로 해준다-->
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">카테고리</span> 
				<%-- <input id="category" name="category" type="text" class="form-control"
					placeholder=""  value="${requestScope.bean.category}"> --%>
				<select id="category" name="category" class="form-select">
					<option value="${requestScope.bean.category}">${requestScope.bean.category}</option>
					<option value="자유게시판">-자유게시판</option>
					<option value="근황/후기">-근황/후기</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글쓴이</span> 
				<input id="fakewriter" name="fakewriter" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.bean.writer}">
			    <input type="hidden" id="writer" name="writer"  value="${requestScope.bean.writer}">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글제목</span> 
				<input id="subject" name="subject" type="text" class="form-control"
					placeholder=""  value="${requestScope.bean.subject}">
			</div>
			<div class="input-group">
			<span class="input-group-text col-md-2">현재 이미지</span>
			<input id="deleteImage01" name="deleteImage01" type="text" class="form-control"
					placeholder=""  value="${requestScope.bean.image01}">			
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">이미지 첨부</span> 
				<input id="image01" name="image01" type="file" class="form-control" placeholder=""></div>
			
			
			<div class="input-group">
				<span class="input-group-text col-md-2">글내용</span> 
				<textarea id="content" name="content" rows="10" cols="100" class="form-control" placeholder="">${requestScope.bean.content}</textarea>
			</div>  
			
			<div class="input-group">
				<span class="input-group-text col-md-2">등록일자</span> 
				<input id="regdate" name="regdate" type="datetime" class="form-control" placeholder=""  value="${requestScope.bean.regdate}">
				<!--                                             ㄴ문자열이되(모든 파라미터는 문자열로 간주) 의미를 부여해준 것-->
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg"
				onclick="return validCheck();">수정</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		


		</form>
</body>
</html>