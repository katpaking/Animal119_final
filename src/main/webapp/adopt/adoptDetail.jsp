<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

		<table class="table table-bordered">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td align="center" width="80" style="background-color:#A4A4A4;font-weight:bold;font-size:10;">작성자</td>
					<td align="center" width="200">${bean.writer}</td>
				</tr>
				<tr>
					<td align="center" width="140" style="background-color:#A4A4A4;font-weight:bold;font-size:10;">작성 일자</td>
					<td align="center" width="200">${bean.regdate}</td>
				</tr>
				<tr>
					<td align="center" width="140" style="background-color:#A4A4A4;font-weight:bold;font-size:10;">전화번호</td>
					<td align="center" width="200">${bean.phone}</td>
				</tr>
				<tr>
					<td align="center" width="140" style="background-color:#A4A4A4;font-weight:bold;font-size:10;">글제목</td>				
					<td width="200">${bean.subject}</td>				
				</tr>
			</tbody>
		</table>
		<div id="backButton" class="inline=flex-item">
			<button type="button" class="btn btn-primary" onclick="history.back();">
				돌아 가기
			</button>
		</div>
	</div>
</body>
</html>