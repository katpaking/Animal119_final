<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		/* 총금액과 적립 포인트를 위한 스타일 지정 */
		.largeSpan{color:red;font-size:1.2em;font-weight:bolder;}

		/* 수정을 위한 스타일 지정 */
		.row{margin:auto;vertical-align: middle;}		
		.col{margin: -30px;vertical-align: middle;}
		.form-control{width:40px;height:40px; }		
	</style>
	<script type="text/javascript">
		function editQty(pnum){
			/* 수정할 개수로써 id 속성이 'edit_상품번호'로 unique해야 합니다.  */
			
			var qty = $('#edit_' + pnum).val();
			if(!qty){
				alert('수정할 개수를 입력해 주세요.') ;
				$('#edit_' + pnum).focus();
				return false ;
			}
			
			/* alert(qty + '/' + pnum) ; */
			
			/* 수정할 상품 번호와 수정할 개수를 이용하여 컨트롤러로 넘김 */
			location.href = '<%=notWithFormTag%>maUpdate&pnum=' + pnum + '&qty=' + qty ;
		}
	
		$(document).ready(function(){
			
		});		
	</script>
</head>
<body>
	<div class="container mt-3">
		<h2>카트 내역 보기</h2>
		<p>내가 구매하고자 하는 상품 목록에 대한 정보를 보여 주는 페이지입니다.</p>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>상품 이름</th>
					<th>수량</th>
					<th>단가</th>
					<th>포인트</th>
					<th>금액</th>
					<th>누적 포인트</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${sessionScope.orderList}">     
				<tr>					
					<td align="center" valign="middle">
						<img alt="${bean.pname}" width="45" height="45" class="rounded" 
							src="<%=contextPath%>/image/${bean.image01}">
						<br/>					
						${bean.pname}
					</td>
					<td align="center" valign="middle">${bean.qty} 개</td>
					
					<td align="center" valign="middle">
						<fmt:formatNumber value="${bean.price}" pattern="###,###"/> 원
					</td>					
					
					<td align="center" valign="middle">
						<fmt:formatNumber value="${bean.point}" pattern="###,###"/> 원
					</td>					
					
					<td align="center" valign="middle">
						<fmt:formatNumber value="${bean.price*bean.qty}" pattern="###,###"/> 원
					</td>
										
					<td align="center" valign="middle">
						<fmt:formatNumber value="${bean.point*bean.qty}" pattern="###,###"/> 원
					</td> 
					
					<td align="center" valign="middle">
						<div class="row">
							<div class="col">
								<input class="form-control form-control-sm" type="text" id="edit_${bean.pnum}" name="edit_${bean.pnum}">
							</div>
							<div class="col">						
								<button class="btn btn-outline-secondary btn-sm" onclick="editQty('${bean.pnum}');">							 
								수정
							</button>
							</div>
						</div>
						<div class="input-group input-group-sm">
   							<button class="input-group-text" onclick="editQty('${bean.pnum}');">수정</button>
  							<input class="form-control" type="text" id="edit_${bean.pnum}" name="edit_${bean.pnum}"
  							 style="width:10px;font-size:30px;">>
						</div>
					</td>
					<td align="center" valign="middle">
						<a href="<%=notWithFormTag%>maDelete&pnum=${bean.pnum}">
							삭제
						</a>						
					</td>
				</tr>		
				</c:forEach>
				
				<%-- 결제 하기, 추가 주문 버튼과 총 금액과 적립 포인트 보여 주기 --%>
				<tr>
					<td align="center" colspan="4">
						<a href="<%=notWithFormTag%>maCalculate">
							결제 하기
						</a>
						&nbsp;&nbsp;&nbsp;
						<a href="<%=notWithFormTag%>prList">
							추가 주문
						</a>						
					</td>
					
					<td align="center" colspan="4">
						<span class="largeSpan">
							총 금액 : 
							<fmt:formatNumber value="${sessionScope.totalAmount}" pattern="###,###"/> 원
						</span>
						&nbsp;&nbsp;&nbsp;
						<span class="largeSpan">
							총 적립 포인트 : 
							<fmt:formatNumber value="${sessionScope.totalPoint}" pattern="###,###"/> 원
						</span>						
					</td>					
				</tr>			 
			</tbody>
		</table>
	</div>	
</body>
</html>






