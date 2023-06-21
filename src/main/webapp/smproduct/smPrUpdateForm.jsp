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
	/* box model 공부할 것 */
	.input-group{margin: 7px;}
	
	.input-group-text{
		display:block;
		margin-left:auto;
		margin-right:auto;
	}
	#buttonset{margin-top:15px;}
</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	
  	<link rel="stylesheet" href="/resources/demos/style.css">
  	  	
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#inputdate').datepicker(); */
  	  		$('#inputdate').datepicker({dateFormat: "yy/mm/dd"});
  	  		
  	  		/* 이전에 사용자가 선택했던 카테고리가 자동 클릭되도록... */
  	  		var category = '${requestScope.bean.category}';
  	  		
  	  		var optionlist = $('.myoption') ;
  	  		optionlist.each(function(){
  	  			if($(this).val() == category){
  	  				$("option[value='" + category + "']").attr('selected', true);		
  	  			}
  	  		});
  		});
  		
  		function validCheck(){
  			var name = $('#name').val();
  			if(name.length < 4 || name.length > 10){
  				alert('상품명은 4글자 이상 10글자 이하이어야 합니다.');
  				$('#name').focus() ;
  				return false ;
  			}
  			
  			var company = $('#company').val();
  			if(company.length < 4 || company.length > 10){
  				alert('제조 회사는 4글자 이상 10글자 이하이어야 합니다.');
  				$('#company').focus() ;
  				return false ;
  			}  		
  			
  			var comments = $('#comments').val();
  			if(comments.length < 10 || comments.length > 30){
  				alert('상품에 대한 코멘트는 10글자 이상 30글자 이하이어야 합니다.');
  				$('#comments').focus() ;
  				return false ;
  			}  		
  			
  			var image01 = $('#image01').val();
  			if(image01 == ''){
  				alert('대표 이미지는 필수 사항입니다.');
  				return false ;
  			}
  			
  			var isCheck = false ; /* 확장자 체크에 충족하면 true가 됩니다. */
  			const imgCheck = ['.png', '.jpg'] ; /* 확장자 체크용 배열 */
  			for(var i=0 ; i < imgCheck.length ; i++){
  				if(image01.endsWith(imgCheck[i])){
  					isCheck = true ;
  					break ;
  				}	
  			}
  			
  			if(isCheck == false){
  				alert('이미지의 확장자는 png 또는 jpg 형식이어야 합니다.');
  				return false ;
  			}
  			
  			const NumberMsg = '는(은) 숫자 형식이어야 합니다.' ;
  			
  			var stock = $('#stock').val();  			
  			if(stock == '' || isNaN(stock) == true){
  				alert('재고' + NumberMsg);
  				$('#stock').focus();
  				return false ;
  			}else{
  				var numStock = Number(stock);
  				if(numStock < -1 || numStock > 5){
  					alert('재고는 최대 5개까지입니다.');
  	  				$('#stock').focus();
  	  				return false ;
  				}
  			}
  			
  			var price = $('#price').val();  			
  			if(price == '' || isNaN(price) == true){
  				alert('단가' + NumberMsg);
  				$('#price').focus();
  				return false ;
  			}else{
  				var numPrice = Number(price);
  				if(numPrice < 100 || numPrice > 10000){
  					alert('단가은 100이상 10000이하의 값이어야 합니다.');
  	  				$('#price').focus();
  	  				return false ;
  				}
  			}  			
  			
  			var point = $('#point').val();  			
  			if(point == '' || isNaN(point) == true){
  				alert('포인트' + NumberMsg);
  				$('#point').focus();
  				return false ;
  			}else{
  				var numPoint = Number(point);
  				if(numPoint < 3 || numPoint > 10){
  					alert('포인트는 3이상 10이하의 값이어야 합니다.');
  	  				$('#point').focus();
  	  				return false ;
  				}
  			}  		
  			
  			
  			var cateogory = $('#cateogory').val();
  			if(cateogory == '-'){ /* 코딩할 때 option의 value 속성을 하이폰으로 설정했습니다. */
  				alert('카테고리를 선택해 주세요.');
	  			$('#cateogory').focus();
	  			return false ;
  			}
  			
  			
  			var inputdate = $('#inputdate').val();
  			
  			var regex = /^\d{4}[\/-][01]\d{1}[\/-][0123]\d{1}$/ ;
  			var result = regex.test(inputdate) ;
  			
  			if(result == false){
  				alert('날짜 형식은 반드시 yyyy/mm/dd 형식 또는 yyyy-mm-dd으로 작성해 주세요.');
  				$('#inputdate').focus() ;
  				return false ;
  			}  			
  			
  		}
  	</script>
</head>
<body>
	<div class="container mt-3 col-md-5">
		<h2>상품 수정</h2>
		<p>관리자가 상품을 수정하는 페이지입니다.</p>
		
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">			
			<input type="text" name="command" value="prUpdate">
			<input type="text" name="pageNumber" value="<%=request.getParameter("pageNumber")%>">
			<input type="text" name="pageSize" value="<%=request.getParameter("pageSize")%>">
			<input type="text" name="mode" value="<%=request.getParameter("mode")%>">
			<input type="text" name="keyword" value="<%=request.getParameter("keyword")%>">
			
			<div class="input-group">
				<span class="input-group-text col-md-2">상품 번호</span> 
				<input id="fakenum" name="fakenum" type="number" disabled="disabled" class="form-control" placeholder="" value="${requestScope.bean.num}">
				<input id="num" name="num" type="hidden" value="${requestScope.bean.num}">
			</div> 
			<div class="input-group">
				<span class="input-group-text col-md-2">상품명</span> 
				<input id="name" name="name" type="text" class="form-control" placeholder="" value="${requestScope.bean.name}">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">제조 회사</span> 
				<input id="company" name="company" type="text" class="form-control" placeholder="" value="${requestScope.bean.company}"">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">코멘트</span> 
				<input id="comments" name="comments" type="text" class="form-control" placeholder="" value="${requestScope.bean.comments}"">
			</div>
			<div class="input-group">
				<input type="text" name="deleteImage01" value="${requestScope.bean.image01}">
				<span class="input-group-text col-md-2">이미지01</span> 
				<input id="image01" name="image01" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<input type="text" name="deleteImage02" value="${requestScope.bean.image02}">
				<span class="input-group-text col-md-2">이미지02</span> 
				<input id="image02" name="image02" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<input type="text" name="deleteImage03" value="${requestScope.bean.image03}">
				<span class="input-group-text col-md-2">이미지03</span> 
				<input id="image03" name="image03" type="file" class="form-control" placeholder="">
			</div>			
			<div class="input-group">
				<span class="input-group-text col-md-2">재고</span> 
				<input id="stock" name="stock" type="number" class="form-control" placeholder="" value="${requestScope.bean.stock}">
			</div>			
			<div class="input-group">
				<span class="input-group-text col-md-2">단가</span> 
				<input id="price" name="price" type="number" class="form-control" placeholder="" value="${requestScope.bean.price}">
			</div>			
			<div class="input-group">
				<span class="input-group-text col-md-2">포인트</span> 
				<input id="point" name="point" type="number" class="form-control" placeholder="" value="${requestScope.bean.point}">
			</div>			
			<div class="input-group">
				<span class="input-group-text col-md-2">비고</span> 
				<input id="remark" name="remark" disabled="disabled" type="text" class="form-control" placeholder="" value="${requestScope.bean.remark}">
			</div>
				
			<div class="input-group">
				<span class="input-group-text col-md-2">카테고리</span> 
				<select id="category" name="category" class="form-select form-select-lg">
					<option class="myoption" value="-">-- 항목을 선택해 주세요.</option>
				  	<option class="myoption" value="child">아동용품</option>
				  	<option class="myoption" value="man">남성용품</option>
				  	<option class="myoption" value="woman">여성용품</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">입고 일자</span>
				 
				 <input id="inputdate" name="inputdate" type="text" class="form-control" placeholder="" value="${requestScope.bean.inputdate}">
					
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary btn-lg"
					onclick="return validCheck();"> 
					수정
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>