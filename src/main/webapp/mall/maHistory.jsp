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
		span {font-size:1.2rem;}
	</style>
</head>
<body>
	<div class="container mt-3">
		<h2>
			${sessionScope.loginfo.name}(${sessionScope.loginfo.id})
			님의 주문 내역
		</h2>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>주문 번호</th>
					<th>주문 일자</th>
					<th>상세 보기</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.lists}">
				<tr align="center">
					<td>${bean.oid}</td>
					<td>${bean.orderdate}</td>
					<td>
						<a href="<%=notWithFormTag%>maDetail&oid=${bean.oid}">
							상세 보기
						</a>						
					</td>
				</tr>					
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>