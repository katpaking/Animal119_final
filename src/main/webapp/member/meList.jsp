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

</head>
<body>

	<div class="container mt-3">
		<img src="<%=contextPath%>/image/meberlist.png" alt="img" class="d-block" height="40">

		<table class="table table-striped">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>가입 일자</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${datalist}">
					<tr >
						<td align="center">${bean.id}</td>
						<td align="center">${bean.name}</td>
						<td align="center">${bean.hiredate}</td>
						<td align="center">${bean.remark}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	${requestScope.pageInfo.pagingHtml}
	</div>

</body>
</html>









