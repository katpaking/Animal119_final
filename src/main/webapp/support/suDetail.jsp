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
		

		<table class="table table-striped">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td align="center">작성자</td>
					<td>${bean.writer}</td>
				</tr>
				<tr>
					<td align="center">글제목</td>
					<td>${bean.subject}</td>
				</tr>
				<tr>
					<td align="center">신청자 전화번호</td>
					<td>${bean.phone}</td>
				</tr>
				
				<tr>
					<td align="center">희망 날짜</td>
					<td>${bean.hopeday}</td>
					
				</tr> 
				
              	<tr>
					<td align="center">글내용</td>
										<td>
					<textarea id="content" name="content" rows="3" cols="50" class="form-control" readonly style="height:200px;">${bean.content}</textarea>
					</td>
					
				</tr> 
					
				<tr>
					<td align="center">작성 일자</td>
					<td>${bean.regdate}</td>
							
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