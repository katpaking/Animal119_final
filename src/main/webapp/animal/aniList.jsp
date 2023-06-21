<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<style type="text/css">
		/* table 셀의 수평 가운데 정렬 */
		.card{margin-left:auto;margin-right:auto;}
		.card-img-top{width:300px;height:300px;}		
		
		#buttonList{margin-top:10px;}
		
		#updateAnchor, #deleteAnchor{opacity:0.8;}
		
		.removeUnderLine{text-decoration-line: none;}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			// Initialize popoveer
			var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
			var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
  				return new bootstrap.Popover(popoverTriggerEl);
			});

		});
	</script>
</head>
<body>
	<div class="container mt-3">
		<div class="row">
			<div class="col-2">
				<img src="<%=contextPath%>/image/protect.png" alt="img" class="d-block" height="60">
			</div>			
		</div>

	<table class="table table-borderess">
		<thead>
		</thead>
		<tbody>		
			<c:set var="colsu" value="${applicationScope.map['product_column_size']}"/>
			<c:forEach var="bean" items="${requestScope.datalist}" varStatus="status">
			<c:if test="${status.index mod colsu == 0}">
			<tr>
			</c:if>  
				<td>
				<div class="card" style="width: 19rem;">
					<a class="removeUnderLine" href="<%=notWithFormTag%>aniDetail&num=${bean.num}">
						<img src="<%=contextPath%>/image/${bean.image01}" class="card-img-top" alt="${bean.name}">
						<div class="card-body">	  								
							<h5 class="card-title" style="font-weight:bold;color:black;">이름 : ${bean.name}</h5>
							<p class="card-text">
								<span data-bs-toggle="popover" title="${bean.name}" data-bs-trigger="hover" data-bs-content="${bean.kind}">
									<font color="black" size="3">
										<b>등록번호 : </b>${bean.num}<br/>
										<b>종류 : </b>${bean.type}<br/>
										<b>품종 : </b>${bean.kind}<br/>
										<b>성별 : </b>${bean.anigender}<br/>
										<b>발견일자 : </b>${bean.inputdate}<br/>	
									</font>
								</span>
							</p>
						<c:if test="${whologin == 2}">
							<div id="buttonList">
								<a id="updateAnchor" class="btn btn-primary" href="<%=notWithFormTag%>aniUpdate&num=${bean.num}${requestScope.pageInfo.flowParameter}">수정</a>
								<a id="deleteAnchor" class="btn btn-danger" href="<%=notWithFormTag%>aniDelete&num=${bean.num}${requestScope.pageInfo.flowParameter}">삭제</a>		    								
							</div>		    							
						</c:if>
						</div>
					</a>
				</div>
				</td>
			<c:if test="${status.index mod colsu == (colsu-1)}">
			</tr>
			</c:if>
			</c:forEach>
		</tbody>
	</table>
	
	${requestScope.pageInfo.pagingHtml}
	</div>
</body>
</html>
