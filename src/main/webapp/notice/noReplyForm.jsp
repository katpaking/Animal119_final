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
	.input-group{margin: 7px;}
	
	.input-group-text{
		display:block;
		margin-left:auto;
		margin-right:auto;
	}
	#buttonset{margin-top:15px;}
	#noticeNo{display:none;visibility:hidden;}
</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">		
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>  	
  	<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#regdate').datepicker(); */
  	  		$('#regdate').datepicker({dateFormat: "yy/mm/dd"});  	  	 
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
	<div class="container mt-3 col-md-5">
		
		
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="noReply">
			
			<input type="hidden" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="hidden" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="hidden" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<input type="hidden" name="groupno" value="<%=request.getParameter("groupno")%>">
			<input type="hidden" name="orderno" value="<%=request.getParameter("orderno")%>">
			<input type="hidden" name="depth" value="<%=request.getParameter("depth")%>">
			
			<div id="noticeNo" class="input-group">
				<span class="input-group-text col-md-2">게시물 번호</span> 
				<input id="no" name="no" type="number" class="form-control" placeholder="">
			</div> 
			<div class="input-group">
				<span class="input-group-text col-md-2">작성자</span>
				
				<c:set var="userInfo" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})"/> 
				<input id="fakewriter" name="fakewriter" disabled="disabled" type="text" class="form-control" 
					value="${userInfo}">
				<input id="writer" name="writer" type="hidden" value="${sessionScope.loginfo.id}">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글제목</span> 
				<input id="subject" name="subject" type="text" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">글내용</span> 
				<textarea id="content" name="content" rows="10" cols="100" class="form-control" placeholder=""></textarea>
			</div>  
			<div class="input-group">
				<span class="input-group-text col-md-2">등록 일자</span> 
				<input id="regdate" name="regdate" type="datetime" class="form-control" placeholder="">
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg"
					onclick="return validCheck();">
					답글 작성 
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>