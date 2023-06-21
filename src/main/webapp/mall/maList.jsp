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
		
		/* 버튼 그룹을 위한 스타일 */
		.nostyle{color:white;text-decoration: none;}
		.btn-primary{opacity:0.8;}		
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
		function checkAll(){
			/* alert($("#checkall").prop("checked")); */
	        if($("#checkall").prop("checked")){
	            $("input[name=cartChkBox]").prop("checked",true);
	        }else{
	            $("input[name=cartChkBox]").prop("checked",false);
	        }
	        getOrderTotalPrice();
	    }	
		
        function AddComma(num){ /* 3자리마다 콤마 유형 넣기 */
            var regexp = /\B(?=(\d{3})+(?!\d))/g;
            return num.toString().replace(regexp, ',');
        }		
		
        function getOrderTotalPrice(){
            var orderTotalPrice = 0;
            var orderTotalPoint = 0;
            $("input[name=cartChkBox]:checked").each(function() {            	
                var pnum = $(this).val();
                var price = $(".price_" + pnum).attr("data");
                var point = $(".point_" + pnum).attr("data");
                var qty = $(".qty_" + pnum).attr("data");
                /* alert(pnum + '/' + price + '/' + qty); */
                orderTotalPrice += price*qty;
                orderTotalPoint += point*qty;
            });

            $(".orderTotalPrice").html(AddComma(orderTotalPrice) + '원');
            $(".orderTotalPoint").html(AddComma(orderTotalPoint) + '원');            
        }		

		$(document).ready(function(){
			$('#checkall').prop("checked", true);
			$("input[name=cartChkBox]").each(function() {            	
                $(this).prop("checked", true);                
            });
			
            $("input[name=cartChkBox]").change( function(){
                getOrderTotalPrice();
            });
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
					<th width="10%">
						<div class="form-check">
  							<input class="form-check-input" type="checkbox" id="checkall" name="option1" onclick="checkAll();">
  							<label class="form-check-label">전체 선택</label>
						</div>
					</th>
					<th>상품 이름</th>
					<th>수량</th>
					<th>단가</th>
					<th>포인트</th>
					<th>금액</th>
					<th>누적 포인트</th>
					<th width="10%">수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${sessionScope.orderList}">     
				<tr>
					<td align="center" valign="middle">
						<div class="form-check">
  							<input type="checkbox" name="cartChkBox" value="${bean.pnum}">
						</div>						
					</td>
					<td align="center" valign="middle">
						<img alt="${bean.pname}" width="45" height="45" class="rounded" 
							src="<%=contextPath%>/image/${bean.image01}">
						<br/>					
						${bean.pname}
					</td>
					<td align="center" class="qty_${bean.pnum}" data="${bean.qty}" valign="middle">${bean.qty} 개</td>
					
					<td align="center" class="price_${bean.pnum}" data="${bean.price}" valign="middle">
						<fmt:formatNumber value="${bean.price}" pattern="###,###"/> 원
					</td>					
					
					<td align="center" class="point_${bean.pnum}" data="${bean.point}" valign="middle">
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
					<td align="center" colspan="3">
						<a href="<%=notWithFormTag%>maCalculate">
							결제 하기
						</a>
						&nbsp;&nbsp;&nbsp;
						<a href="<%=notWithFormTag%>prList">
							추가 주문
						</a>						
					</td>
					
					<td align="left" colspan="6">
						<span class="largeSpan">
							총 금액 : 
							<span class="orderTotalPrice">
								<fmt:formatNumber value="${sessionScope.totalAmount}" pattern="###,###"/> 원
							</span>
							
						</span>
						&nbsp;&nbsp;&nbsp;
						<span class="largeSpan">
							총 적립 포인트 : 
							<span class="orderTotalPoint">
								<fmt:formatNumber value="${sessionScope.totalPoint}" pattern="###,###"/> 원
							</span>							
						</span>						
					</td>					
				</tr>			 
			</tbody>
		</table>
	</div>	
</body>
</html>






