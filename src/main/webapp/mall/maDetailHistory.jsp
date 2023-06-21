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
		.dddmain_header{
		background-color:LightGray;
		  border-top-left-radius: 2em 0.5em;
  border-top-right-radius: 1em 3em;
  border-bottom-right-radius: 4em 0.5em;
  border-bottom-left-radius: 1em 3em;}
	</style>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
    		<div class="col-sm-2"></div>
    		<div class="col-sm-8">
				<h2>
					${sessionScope.loginfo.name}(${sessionScope.loginfo.id})
					님의 주문 상세 내역
				</h2>
				<p>
					${sessionScope.loginfo.name} 고객님이 ${order.orderdate}에 
					구매하신 상품에 대한 세부 결제 내역입니다.
				</p>
				
				<br/>
				<h3 class="main_header">주문 내역</h3>		
				
				<table class="table table-striped">
					<thead>
						<tr>
							<th>제품명</th>
							<th>이미지</th>
							<th>단가</th>
							<th>수량</th>
							<th>금액</th>
						</tr>
					</thead>
					<tbody>
						<%-- 변수 : totalAmount(총금액) --%>
						<c:set var="totalAmount" value="0"/>
						
						<c:forEach var="bean" items="${requestScope.lists}">
						<tr>
							<td valign="middle">${bean.pname}</td>
							<td valign="middle">
								<img alt="${bean.image01}" width="45" height="45" 
									src="<%=contextPath%>/image/${bean.image01}">
							</td>
							<td valign="middle">
								<fmt:formatNumber value="${bean.price}" pattern="###,###"/> 원						
							</td>
							<td valign="middle">
								<fmt:formatNumber value="${bean.qty}" pattern="###,###"/>				
							</td>
							<td valign="middle">
								<c:set var="amount" value="${bean.price*bean.qty}"/>
								<c:set var="totalAmount" value="${totalAmount + amount}"/>
								<fmt:formatNumber value="${amount}" pattern="###,###"/> 원						
							</td>					
						</tr>					
						</c:forEach>
						<tr>
							<td colspan="5" align="right">
								합계 : <fmt:formatNumber value="${totalAmount}" pattern="###,###"/> 원&nbsp;&nbsp;
							</td>
						</tr>
						
						<%-- 운송비(shipExpense) 구하기 --%>
						<c:set var="shipExpense" value="0"/>
						
						<c:choose>
							<c:when test="${totalAmount >= 100000}">
								<c:set var="shipExpense" value="0"/>
							</c:when>
							<c:when test="${totalAmount >= 50000}">
								<c:set var="shipExpense" value="2000"/>
							</c:when>
							<c:otherwise>
								<c:set var="shipExpense" value="4000"/>
							</c:otherwise>
						</c:choose>
						<tr>
							<td colspan="5" align="right">
								운송비 : <fmt:formatNumber value="${shipExpense}" pattern="###,###"/> 원&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="5" align="right">
								<c:set var="finalAmount" value="${totalAmount + shipExpense}"/>
								
								최종 금액 : <fmt:formatNumber value="${finalAmount}" pattern="###,###"/> 원&nbsp;&nbsp;
							</td>
						</tr>				
					</tbody>
				</table>
				
				<br/>
				<h3>결제 정보</h3>	
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center">주문 번호</td>					
							<td align="left">${requestScope.order.oid}</td>
							<td align="center">주문 일자</td>
							<td align="left">${requestScope.order.orderdate}</td>
						</tr>	
						<tr>
							<td align="center">주문 총액</td>					
							<td align="left">
								<fmt:formatNumber value="${finalAmount}" pattern="###,###"/> 원
							</td>
							<td align="center">할인 금액</td>
							<td align="left">0 원</td>
						</tr>
						<tr>
							<td align="center">결제 금액</td>					
							<td align="left">
								<fmt:formatNumber value="${finalAmount}" pattern="###,###"/> 원
							</td>
							<td align="center">결제 상태</td>
							<td align="left">결제 완료</td>
						</tr>
					</tbody>
				</table>
				
				<br/>
				<h3>배송 정보</h3>	
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center" width="20%">받으시는 분</td>					
							<td align="left" width="80%">
								${sessionScope.loginfo.name}(${sessionScope.loginfo.id}) 님
							</td>
						</tr>
						<tr>
							<td align="center" width="20%">현재 적립 포인트</td>					
							<td align="left" width="80%">
								<fmt:formatNumber value="${sessionScope.loginfo.point}" pattern="###,###"/> 원						
							</td>
						</tr>
						<tr>
							<td align="center" width="20%">배송 방법</td>					
							<td align="left" width="80%">택배</td>
						</tr>
					</tbody>
				</table>    		
    		</div>
    		<div class="col-sm-2"></div>
  		</div>		
	</div>
</body>
</html>

