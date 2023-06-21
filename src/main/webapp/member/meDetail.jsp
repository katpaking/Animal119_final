<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.animal.model.Member"%>
<%@page import="com.animal.dao.MemberDao"%>


<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
	#backButton{margin:auto;}
</style>

</head>
<body>

	<div class="container mt-3">
		<img src="<%=contextPath%>/image/memberInfo.png" alt="img" class="d-block" height="60">

		<table class="table table-striped">
			<thead>

			</thead>
			<tbody>
				<tr>
					<td align="center">아이디</td>
					<td>${bean.id}</td>
				</tr>
				<tr>
					<td align="center">성별</td>
					<td>${bean.gender}</td>
				</tr>
				<tr>
					<td align="center">반려동물</td>
					<td>${bean.hobby}</td>
				</tr>
				<tr>
					<td align="center">가입 일자</td>
					<td>${bean.hiredate}</td>
				</tr>
				<tr>
					<td align="center">적립 포인트</td>
					<td>${bean.point}</td>
				</tr>
			</tbody>
		</table>
		<div id="backButton">
			<button type="button" class="btn btn-warning"
				onclick="history.back();">돌아가기</button>
		</div>
	</div>
</body>
</html>









