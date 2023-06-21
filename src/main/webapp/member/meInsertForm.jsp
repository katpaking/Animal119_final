<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 화면</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style type="text/css">
body {
	min-height: 100vh;
	background: -webkit-gradient(linear, left bottom, right top, from(#92b5db),
		to(#1d466c));
	background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
	background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
}

.input-form {
	max-width: 680px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}
input[type="button"] {
	font-size: 0.55rem;
	padding: 5px 5px;
}
</style>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<link rel="stylesheet" href="/resources/demos/style.css">

<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#hiredate').datepicker(); */
  	  		$('#hiredate').datepicker({dateFormat: "yy/mm/dd"}); 
  	  	 	
  		});
  		
  		function validCheck(){
  			/* alert('반환 값이 false이면 이벤트 전파가 되지 않습니다.'); */
  			var id = $('#id').val();  			
  			if(id.length < 4 || id.length > 10){
  				swal('아이디는 4자리 이상 10자리 이하로 입력해 주세요.');
  				$('#id').focus();
  				return false ;
  			}
  			
  			var name = $('#name').val();  			
  			if(name.length < 2 || name.length > 10){  				
  				$('#name').focus();
  				swal('이름은 2자리 이상 10자리 이하로 입력해 주세요.');
  				return false ;
  			}  			
		
  			var password = $('#password').val();  			
  			if(password.length < 3 || password.length > 12){
  				swal('비밀 번호는 3자리 이상 12자리 이하로 입력해 주세요.');
  				$('#password').focus();
  				return false ;
  			}    			
 			
  			var regex = /^[a-z]\S{4,11}$/ ; /* 정규 표현식 */
  			
  			var result = regex.test(password) ;
  			
			if(result == false){
				swal('첫 글자는 반드시 소문자로 작성되어야 합니다.');
				return false ; 
			}
			
			if(password.indexOf("!") <= 0 && password.indexOf("@") <= 0 && password.indexOf("#") <= 0 && password.indexOf("$") <= 0){
				swal('특수 문자 !@#% 중에서 최소 1개 이상이 필요합니다.');
				return false ; 
			}
  			
  			var radioList = $("input[type='radio']:checked");
  			if(radioList.length == 0){
  				swal('성별을 선택해 주세요.'); 
  				return false ;
  			}
  			
  			var checkList = $("input[type='checkbox']:checked");
  			if(checkList.length < 1){
  				swal('반려동물은 최소 1개 이상 반드시 선택해야 합니다.'); 
  				return false ;
  			}
  			
  			var hiredate = $('#hiredate').val();
  			
  			var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/ ;
  			var result = regex.test(regdate) ;
  			
  			if(result == false){
  				alert('날짜 형식은 반드시 yyyy/mm/dd 형식으로 작성해 주세요.');
  				$('#hiredate').focus() ;
  				return false ;
  			} 	
  			
  			/* alert(id + '/' ) ; */
  			
  			return true ;
  		}
  	</script>
</head>


<body>
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3">회원가입</h4>

				<form action="<%=withFormTag%>" method="post"
					class="validation-form" novalidate>
					<input type="hidden" name="command" value="meInsert">


					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="id">아이디</label> <input type="text"
								class="form-control" id="id" name="id" placeholder="" value="" required>
							<div class="invalid-feedback">아이디를 입력해주세요.</div>
						</div>
						
						<div class="col-md-6 mb-3">
							<label for="name">이름</label> <input type="text"
								class="form-control" id="name" name="name" placeholder="" value="" required>
							<div class="invalid-feedback">이름을 입력해주세요.</div>
						</div>
						
						<div class="col-md-6 mb-3">
							<label for="password">비밀번호</label> <input type="password"
								class="form-control" id="password" name="password"  placeholder="" value="" required>
							<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
						</div>
					</div>



					<div class="input-group">
						<span class="input-group-text col-md-2">성별</span>
						<div class="form-control">
							<label class="radio-inline radio_gender"> &nbsp;<input
								type="radio" name="gender" value="male">남자
							</label> &nbsp; <label class="radio-inline radio_gender"> &nbsp;<input
								type="radio" name="gender" value="female">여자
							</label>
						</div>
					</div>

					<div class="input-group">
						<span class="input-group-text col-md-2">반려 동물</span>
						<div class="form-control">
							<label class="checkbox-inline chk_hobby"> &nbsp;<input
								type="checkbox" name="hobby" value="강아지">강아지
							</label> &nbsp; <label class="checkbox-inline chk_hobby"> &nbsp;<input
								type="checkbox" name="hobby" value="고양이">고양이
							</label> &nbsp; <label class="checkbox-inline chk_hobby"> &nbsp;<input
								type="checkbox" name="hobby" value="없음">없음
							</label> &nbsp; <label class="checkbox-inline chk_hobby"> &nbsp;<input
								type="checkbox" name="hobby" value="기타">기타
							</label>
						</div>
					</div>

					<div class="input-group">
						<span class="input-group-text col-md-2">가입 일자</span> <input
							id="hiredate" name="hiredate" type="datetime"
							class="form-control" placeholder="">
					</div>
                                      
					<div style="">
						<br/>
						<button type="submit" class="btn btn-info"
							onclick="return validCheck();">등록</button>

						&nbsp;&nbsp;&nbsp;

						<button type="reset" class="btn btn-danger">초기화</button>
					</div>
				</form>
			</div>
		</div>
</body>
</html>