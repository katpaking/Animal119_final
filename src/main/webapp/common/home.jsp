<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript">
		$(document).ready(function(){
			// Initialize popoveer
			var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
			var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
					return new bootstrap.Popover(popoverTriggerEl);
			});
	
		});
	</script>
	<style type="text/css">
		.rounded-pill{opacity:0.8;}
		#noUnderLine{text-decoration-line:none;}	
		.popover{max-width:600px;}
		.card{margin-left:auto;margin-right:auto;}		
		.card-img-top{width:100px;height:100px;}
		
		
	</style>
</head>
<body>
	<div style="height:80px"></div>

	
	<div style="float:left;padding:0px 0px 0px 180px;margin:0;">
		<a style="color:black;" id="noUnderLine" href="<%=notWithFormTag%>noList"><img src="<%=contextPath%>/image/공지사항.png" alt="img" class="d-block" height="40"></a>		
		<table class="table table-striped">
			<thead>
				<tr align="center">					
					<th width="140">분류</th>
					<th width="400">제목</th>					
					<th width="140">작성 일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${ndatalist}" begin="1" end="4"> 
				<tr>					
					<td align="center">${bean.category}</td>		
					<td>
						<a id="noUnderLine" href="<%=notWithFormTag%>noDetail&no=${bean.no}">						
							<span data-bs-toggle="popover" title="${bean.subject}" data-bs-trigger="hover" data-bs-content="${bean.content}">
								${bean.subject}
	    					</span>				
						</a>
					</td>					
					<td>${bean.regdate}</td>
				</tr>
				</c:forEach>	 
			</tbody>
		</table>
	</div>
	
	<div style="float:right;padding:0px 200px 0px 0px;margin:0;">
		<a style="color:black;" id="noUnderLine" href="<%=notWithFormTag%>noList"><img src="<%=contextPath%>/image/자유게시판.png" alt="img" class="d-block" height="40"></a>
		

		<table class="table table-striped">
			<thead>
				<tr align="center">					
					<th width="140">분류</th>					
					<th width="400">글제목</th>
					<th width="140">작성자</th>	
					<th width="140">작성 일자</th>
				
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${bdatalist}" begin="1" end="4"> 
				<tr>					
					<td align="center">${bean.category}</td>					
					<td>
						<a id="noUnderLine" href="<%=notWithFormTag%>frboDetail&no=${bean.no}">						
							<c:if test="${fn:length(bean.subject) >= 20}">
								<c:forEach begin="1" end="${bean.depth}">
									<span class="badge rounded-pill bg-secondary">re</span>							
								</c:forEach>						
								<span data-bs-toggle="popover" title="${bean.subject}" data-bs-trigger="hover" data-bs-content="${bean.content}">
									${fn:substring(bean.subject, 0, 20)}...
								</span>
							</c:if>
							<c:if test="${fn:length(bean.subject) < 20}">
								<c:forEach begin="1" end="${bean.depth}">
									<span class="badge rounded-pill bg-secondary">re</span>							
								</c:forEach>							
								<span data-bs-toggle="popover" title="${bean.subject}" data-bs-trigger="hover" data-bs-content="${bean.content}">
									${bean.subject}
								</span>
							</c:if> 			
	    					
						</a>
					</td>
					<td align="center">${bean.writer}</td>
					<td>${bean.regdate}</td>					
				</tr>
				</c:forEach>		
			</tbody>
		</table>
	</div>
	

	<div style="height:20px"></div>
	

	<div style="float:left;padding:0px 0px 0px 180px;margin:0;">
		<table>
			<thead></thead>
			<tbody>
				<tr align="center">
					<td>
							<a href="<%=notWithFormTag%>aniList"><img src="<%=contextPath%>/image/유기동물_입양.jpg" alt="img" class="d-block" style="width:400px"></a>
					</td>
					<td>
						<c:if test="${whologin == 2}">
							<a href="<%=notWithFormTag%>suList"><img src="<%=contextPath%>/image/volunteer.png" alt="img" class="d-block" style="height:390px"></a>
						</c:if>
						<c:if test="${whologin != 2}">
							<a href="<%=notWithFormTag%>noList"><img src="<%=contextPath%>/image/volunteer.png" alt="img" class="d-block" style="height:390px"></a>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
					
<div style="float:right;padding:0px 180px 0px 0px;" class="display:flex">
	<div class="flex-item">		
		<a id="noUnderLine" href="<%=notWithFormTag%>aniList"><img src="<%=contextPath%>/image/waiting.png" alt="img" class="d-block" height="40"></a>
		<table class="table table-borderess">
			<thead>
			</thead>
			<tbody>
				<tr>					
					<c:forEach var="bean" items="${requestScope.adatalist}" varStatus="status" begin="0" end="6">
					<td align="center">
						<div class="card" style="width:100px;">
							<a class="removeUnderLine" href="<%=notWithFormTag%>aniDetail&num=${bean.num}">
								<img src="<%=contextPath%>/image/${bean.image01}" class="card-img-top" alt="${bean.name}">
								<c:if test="${fn:length(bean.name) >= 7}">								
									<br>${fn:substring(bean.name, 0, 5)}...								
								</c:if>
								<c:if test="${fn:length(bean.name) < 7}">
									<br>${bean.name}								
								</c:if> 									
							</a>
						</div>
					</td>
					</c:forEach>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="flex-item">
		<a id="noUnderLine" href="<%=notWithFormTag%>prList"><img src="<%=contextPath%>/image/shop.png" alt="img" class="d-block" height="40"></a>
		<table class="table table-borderess">
			<thead>
			</thead>
			<tbody>
				<tr>
				<c:forEach var="bean" items="${requestScope.sdatalist}" varStatus="status" begin="0" end="6">
					<td align="center">
						<div class="card" style="width: 100px;">
							<a class="removeUnderLine" href="<%=notWithFormTag%>prDetail&num=${bean.num}">
	  							<img src="<%=contextPath%>/image/${bean.image01}" class="card-img-top" alt="${bean.name}">
	    						<c:if test="${fn:length(bean.name) >= 7}">								
									<br>${fn:substring(bean.name, 0, 5)}...								
								</c:if>
								<c:if test="${fn:length(bean.name) < 7}">
									<br>${bean.name}								
								</c:if> 	
	  						</a>
						</div>
					</td>
				</c:forEach>
				</tr>
		</table>	
	</div>
</div>
	
	
	
	<div style="height:20px"></div>


	<script type="text/javascript">
		/* 필드 검색시 입력 했던 콤보 박스(mode)의 내용과 키워드(keyword) 입력 상자에 있던 내용은 보존되어야 합니다. */
		$(document).ready(function(){ 
			var myoptions = $('#mode option');
			
			for(var i=0 ; i<myoptions.length ; i++){
				if(myoptions[i].value == '${requestScope.pageInfo.mode}'){
					myoptions[i].selected = true ; 
				}
			}
			
			$('#keyword').val('${requestScope.pageInfo.keyword}') ;
		});
	</script>	
</body>
</html>
