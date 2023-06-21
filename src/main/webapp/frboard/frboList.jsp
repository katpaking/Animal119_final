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
	<script type="text/javascript">
		$(document).ready(function(){ 
			
		});
		function searchAll(){ /* 전체 검색 */
			location.href = '<%=notWithFormTag%>frboList' ;
		}
		
		function writeForm(){ /* 글쓰기 */
			location.href = '<%=notWithFormTag%>frboInsert' ;
		}
	</script>
	<style type="text/css">
		.rounded-pill{opacity:0.8;}
		#noUnderLine{text-decoration-line:none;}
	</style>
</head>
<body>
	<div class="container mt-3">
		<img src="<%=contextPath%>/image/자유게시판.png" alt="img" class="d-block" height="60">
		<br/>	
	
		<table class="table table-striped">
			<!-- <thead> -->
			<tr>
				<td colspan="9" align="center">
				<form name="myform" action="<%=withFormTag%>" method="get">
					<div class="row">
						<input type="hidden" name="command" value="frboList">										
						<div class="col-sm-2">							
							<select class="form-control" id="mode" name="mode">
								<option value="all" selected="selected">--- 선택해 주세요 ---
								<option value="writer">작성자
								<option value="subject">글제목
								<option value="content">글내용
							</select>
						</div>
						<div class="col-sm-5">
							<input class="form-control" type="text" name="keyword" id="keyword" placeholder="키워드를 입력해 주세요.">
						</div>							
						<div class="col">
							<button type="submit" class="btn btn-warning" onclick="">검색</button>
						</div>
						<div class="col">				
							<button type="button" class="btn btn-warning" onclick="searchAll();">전체 검색</button>
						</div>
						<div class="col">
							<button type="button" class="btn btn-info" onclick="writeForm();">글 쓰기</button>
						</div>
						<div class="col">
							${requestScope.pageInfo.pagingStatus}
						</div>
					</div>
				</form>
				</td>
			</tr>
			<tr align="center">
				<th>글번호</th>
				<th>분류</th>
				<th>작성자</th>
				<th>글제목</th>
				<th>작성 일자</th>
				<th>조회수</th>				
			</tr>
			<!-- 		</thead> -->
			<tbody>			   
				<c:forEach var="bean" items="${datalist}"> 
				<tr align="center">
					<td align="center">${bean.no}</td>
					<td align="center">${bean.category}</td>
					<td>${bean.writer}</td>
					<td>
						<a id="noUnderLine" href="<%=notWithFormTag%>frboDetail&no=${bean.no}">						
						<c:forEach begin="1" end="${bean.depth}">
							<span class="badge rounded-pill bg-secondary">re</span>							
						</c:forEach>
							${bean.subject}							
						</a>
					</td>					
					<td>${bean.regdate}</td>
					<td>
						<c:if test="${bean.readhit >= 50}">
							<span class="badge rounded-pill bg-danger">${bean.readhit}</span>
						</c:if>												
						<c:if test="${bean.readhit < 50}">
							<span class="badge rounded-pill bg-primary">${bean.readhit}</span>
						</c:if>
					</td>			
				</tr>
				</c:forEach>				 
			</tbody>
		</table>
	
		${requestScope.pageInfo.pagingHtml}
	</div>

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
