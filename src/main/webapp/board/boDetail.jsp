<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style type="text/css">
		#backButton{margin:auto;}
	</style>
</head>
<body>

	<div class="container mt-3">
		<h2>글번호 ${requestScope.bean.no}의 게시물 정보</h2>

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
				
				<tr>
               		<td align="center">이미지</td>
	               	<td>
	               		<div class="card" style="width: 20rem; float:left;">
	                     	<img src="<%=contextPath%>/image/${bean.image01}">
	                     </div>
	                     <div class="card" style="width: 20rem; float:left;">
	                     	<img src="<%=contextPath%>/image/${bean.image02}">
	                     </div>
	                     <div class="card" style="width: 20rem; both:clear;">
							<c:if test="${not empty bean.image02}">
	               				<img src="<%=contextPath%>/image/${bean.image03}">
	               			</c:if>
	            		</div>
	           		</td>
            	</tr>
				
              	<tr>
					<td align="center">글내용</td>
					<td>${bean.content}</td>
					
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
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back();">
				돌아 가기
			</button>
		</div>
	</div>
</body>
</html>