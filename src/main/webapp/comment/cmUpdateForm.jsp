
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

</script>


</head>
<body>
	<div class="container mt-3 col-md-6">
	     <h2>댓글 수정</h2>
	     
	     <table class="table table-striped">
		<!-- div=division(영역, 구역) 컨테이너 박스 만들어주는 구문-->
		
		<form action="<%=withFormTag%>" method="post">
		<input type="text" name="command" value="cmUpdate">
		
<!-- 	<input type="number" name="no" value="${bean.cnum}">
		<input type="number" name="no" value="${bean.no}">
		<input type="text" name="writer" value="${bean.writer}">
		<input type="text" name="content" value="${bean.content}">
		<input type="text" name="regdate" value="${bean.regdate}">  -->
		
			<div class="input-group">
				<span class="input-group-text col-md-2">댓글번호</span> 
				<input id="fakecnum" name="fakecnum" disabled="disabled" type="number" class="form-control" placeholder="" value="${requestScope.bean.cnum}">
				<input type="hidden" id="cnum" name="cnum" value="${requestScope.bean.cnum}">
				<!--  숫자인 걸 강조하고 싶을 때 number로 해준다-->
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글번호</span> 
				<input id="fakeno"	name="fakeno" disabled="disabled" type="number" class="form-control" placeholder="" value="${requestScope.bean.no}">
				<input type="hidden" id="no" name="no" value="${requestScope.bean.no}">
				<!--  숫자인 걸 강조하고 싶을 때 number로 해준다-->
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글쓴이</span> 
				<input id="fakewriter" name="fakewriter" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.bean.writer}">
			    <input type="hidden" id="writer" name="writer"  value="${requestScope.bean.writer}">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">글내용</span> 
				<input id="content" name="content" type="text" class="form-control"
					placeholder=""  value="${requestScope.bean.content}>">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-2">등록일자</span> 
				<input id="regdate" name="regdate" type="datetime" class="form-control" placeholder=""  value="${requestScope.bean.regdate}">
				<!--                                             ㄴ문자열이되(모든 파라미터는 문자열로 간주) 의미를 부여해준 것-->
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg">수정</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		</form>
</body>
</html>