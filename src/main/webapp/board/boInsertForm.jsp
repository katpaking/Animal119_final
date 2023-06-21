<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp" %>


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

#buttonset {margin-top: 15px;}
#boardNum {display:none;visibility:hidden;}
	
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<link rel="stylesheet" href="/resources/demos/style.css">

<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  /*$('#inputdate').datepicker();*/
	  $('#inputdate').datepicker({dateFormat: "yy/mm/dd"});
	  
//  alert('${requestScope.categories}');
	 /* $('#category')*/
}); 
function validCheck(){  /*폼 유효성 검사*/
	var subject = $('#subject').val();
	if(subject.length < 3 || subject.length > 20){
		alert('글 제목은 3글자 이상 20글자 이하여야 합니다');
		$('#subject').focus();
		return false;
	}
	var content = $('#content').val();
	if(content.length < 5 || content.length > 30){
		alert('글 내용은 5글자 이상 30글자 이하여야 합니다');
		$('#content').focus();
		return false;
	}
	var regdate = $('#regdate').val();
	
	var regex = /^\d{4}[\/-][01]\d{1}[\/-][0123]\d{1}$/ ;
	
	// [\/-] : 안쪽에 열거된 것 중 하나
	var result = regex.test(regdate);
	
	if(result == false){
		alert('날짜 형식은 반드시 yyyy/mm/dd 형식으로 작성해주세요.');
		$('#regdate').focus();
		return false;
	}
	return true;
}

</script>


</head>
<body>
	<div class="container mt-3 col-md-6">
	<h2>게시글 등록</h2>
		<!-- div=division(영역, 구역) 컨테이너 박스 만들어주는 구문-->
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="boInsert">
		<div id="boardNo" class="input-group">
				<span class="input-group-text col-md-2">게시글번호</span> 
				<input id="no"	name="no" type="number" class="form-control" placeholder="">
				<!--  숫자인 걸 강조하고 싶을 때 number로 해준다-->
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-3">카테고리</span> 
				<select id="category" name="category" class="form-select form-select-lg">
					<option value="-">--항목을 선택해주세요</option>
					<option value="자유게시판">자유게시판</option>
					<option value="근황/후기">근황/후기</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글쓴이</span> 
				<c:set var="userInfo" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})"/> 
				<input id="fakewriter" name="fakewriter" disabled="disabled" type="text" class="form-control" placeholder="" value="${userInfo}">
				<input id="writer" type="hidden" name="writer" value="${sessionScope.loginfo.id}">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글제목</span> 
				<input id="subject" name="subject" type="text" class="form-control"
					placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-3">이미지01</span> 
				<input id="image01"
					name="image01" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-3">이미지02</span> 
				<input id="image02"
					name="image02" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-3">이미지03</span> 
				<input id="image03"
					name="image03" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글내용</span> 
				<input id="content" name="content" type="text" class="form-control"
					placeholder="">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">등록일자</span> 
				<input id="regdate" name="regdate" type="datetime" class="form-control" placeholder="">
				<!--                                             ㄴ문자열이되(모든 파라미터는 문자열로 간주) 의미를 부여해준 것-->
			</div>
			
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg">등록</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>


		</form>
</body>
</html>