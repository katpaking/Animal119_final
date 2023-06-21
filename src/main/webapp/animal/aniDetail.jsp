<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.card{margin-left:auto;margin-right:auto;}
		.leftside{margin-left:0px;}
		.card_borderless{border:0px;}
		
		.small_image{width:100px;height:100px;margin:2px;border-radius:5px;}
		#totalprice{color:red;font-size: 20px;font-weight:bolder;}
		.cart{background-color:black;border:1px solid black;} /* 속성 표기법 */
		.rightnow{background-color:red;border:1px solid red;}
		#qty{margin:-10px;border:0px;font-size:0.7rem;}	
		.plus, .minus{font-size:1.1rem;}
	</style>
	
	
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
	<div class="container mt-3">
		<img src="<%=contextPath%>/image/소개.png" alt="img" class="d-block" height="40">
		
		<table class="table table-borderless ">
			<thead>
			</thead>
			<tbody>
				<tr>
					<td class="col-lg-5">
						<div class="card" style="width: 30rem;">
  							<img src="<%=contextPath%>/image/${bean.image01}" class="card-img-top active_image zoom" alt="${bean.name}">
						</div>
					</td>
					<td class="col-lg-1">
						<img src="<%=contextPath%>/image/${bean.image01}" class="card-img-top small_image" alt="${bean.name}">
						
						<c:if test="${not empty bean.image02}">
							<img src="<%=contextPath%>/image/${bean.image02}" class="card-img-top small_image" alt="${bean.name}">
						</c:if>
						
						<c:if test="${not empty bean.image03}">
							<img src="<%=contextPath%>/image/${bean.image03}" class="card-img-top small_image" alt="${bean.name}">
						</c:if>						
					</td>
					<td class="col-lg-6">
						<div class="card leftside card_borderless" style="width: 18rem;">
							<font color="black" size="3">
							<h5 class="card-title">${bean.name}</h5>							
								<b>등록번호 : </b>${bean.num}<br/>
								<b>종류 : </b>${bean.type}<br/>
								<b>품종 : </b>${bean.kind}<br/>
								<b>성별 : </b>${bean.anigender}<br/>
								<b>발견일자 : </b>${bean.inputdate}<br/>
							</font>    
							<form>	
								<br/>					
								<c:if test="${whologin != 0}">
									<a href="<%=notWithFormTag%>AdoptInsert&num=${bean.num}">
										<button type="button" class="btn btn-primary">입양하기</button>
									</a>
								</c:if>
							</form>
						</div>						
					</td>					
				</tr>
			</tbody>
		</table>		
	</div>
</body>
</html>