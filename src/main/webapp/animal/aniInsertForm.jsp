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
	#productNum{display:none;visibility:hidden;}
</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	
  	<link rel="stylesheet" href="/resources/demos/style.css">
  	  	
  	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#inputdate').datepicker(); */
  	  		$('#inputdate').datepicker({dateFormat: "yy/mm/dd"});   	  		
  	  		
  	  		/* alert('${requestScope.categories}'); */
  	  		/* var categories = '${requestScope.categories}' ; */
  	  		/* console.log(categories); */
  	    	  	
  	  		/* $('#cateogory') */
  	  	 
  		});
  		
  		function validCheck(){
  			var name = $('#name').val();
  			if(name.length < 0 ){
  				alert('이름은 한 글자 이상이어야 합니다.');
  				$('#name').focus() ;
  				return false ;
  			}
  			
  			var type = $('#type').val();
  			if(type == '-'){ 
  				alert('카테고리를 선택해 주세요.');
	  			$('#type').focus();
	  			return false ;
  			}
  			
  			var kind = $('#kind').val();
  			if(company.length < 0 ){
  				alert('품종을 입력해주세요.');
  				$('#kind').focus() ;
  				return false ;
  			}
  			
  			var anigender = $('#anigender').val();
  			if(anigender == '-'){ 
  				alert('카테고리를 선택해 주세요.');
	  			$('#anigender').focus();
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
		<img src="<%=contextPath%>/image/newAnimal.png" alt="img" class="d-block" height="40">
		
		
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="command" value="aniInsert">
			<div id="animalNum" class="input-group" hidden>
				<span class="input-group-text col-md-2">등록 번호</span> 
				<input id="num" name="num" type="number" class="form-control" placeholder="">
			</div> 
			<div class="input-group">
				<span class="input-group-text col-md-2">보호동물 이름</span> 
				<input id="name" name="name" type="text" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">동물 종류</span> 
				<select id="type" name="type" class="form-select">
					<option value="-">-- 항목을 선택해 주세요.</option>
						<option value="강아지">강아지</option>
				  		<option value="고양이">고양이</option>
				  		<option value="기타동물">기타</option>
			  		</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">품종</span> 
				<input id="kind" name="kind" type="text" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">성별</span> 
				<select id="anigender" name="anigender" class="form-select">
					<option value="-">-- 항목을 선택해 주세요.</option>
						<option value="수컷">수컷</option>
				  		<option value="암컷">암컷</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">이미지01</span> 
				<input id="image01" name="image01" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">이미지02</span> 
				<input id="image02" name="image02" type="file" class="form-control" placeholder="">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">이미지03</span> 
				<input id="image03" name="image03" type="file" class="form-control" placeholder="">
			</div>			
			<div class="input-group">
				<span class="input-group-text col-md-2">비고</span> 
				<input id="remark" name="remark" type="text" class="form-control" placeholder="">
			</div>
				
			<div class="input-group">
				<span class="input-group-text col-md-2">발견 당시 상태</span> 
				<select id="category" name="category" class="form-select">
					<option value="-">-- 항목을 선택해 주세요.</option>
					<option value="good">양호</option>
					<option value="normal">보통</option>
				  	<option value="bad">나쁨</option>
				</select>
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">발견 일자</span> 
				<input id="inputdate" name="inputdate" type="datetime" class="form-control" placeholder="">
			</div>
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-success btn-lg"
					onclick="return validCheck();"> 
					등록
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-secondary btn-lg">초기화</button>
			</div>
		</form>
	</div>
</body>
</html>