            <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>
<style>
body {
	background-image: ./woman-g75bf541ca_1280.jpg';
	background-size: 100%;
	font-size: 0.75rem;
}

#loginBoxTitle {
	color: #000000;
	font-weight: bold;
	font-size: 1.9rem;
	text-transform: uppercase;
	padding: 5px;
	margin-bottom: 20px;
	background: linear-gradient(to right, #270a09, #8ca6ce);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
}

input[type="button"] {
	font-size: 0.75rem;
	padding: 5px 10px;
}

.login-box {
	margin: 150px auto;
	background-color: rgba(255, 255, 255, 1);
	border-radius: 10px;
	padding: 40px 30px;
	border: 5px solid #0e0e0e;
	width: 350px;
	filter: drop-shadow(0px 0px 10px rgba(0, 0, 0, .5));
}

.form-group label {
	font-size: 0.75rem;
	margin: 5px 0;
}

#login-btn-box {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-top: 10px;
}
</style>

<!DOCTYPE html>

<html lang="">
<head>


</head>
<body class="">
	<form action="<%=withFormTag%>" method="post">
		<input type="hidden" name="command" value="meLogin">
		<div id="container">
			<!-- login Box -->
			<div class="login-box">
				<div id="loginBoxTitle">119Animal Login</div>

				<div class="form-group">
					<label>고객 아이디</label> <input type="text" name="id" id="id"
						class="form-control" value=""
						style="text-transform: lowercase; ime-mode: disabled">
				</div>
				<div class="form-group">
					<label>비밀번호</label> <input type="password" name="password"
						id="password" class="form-control" value="" autocomplete="off">
				</div>
				<div id="login-btn-box">
					<div style="">
						<span> 아이디 저장</span> <input type="checkbox" id="workSite"
							name="worksite" style="margin-bottom: 5px">
					</div>
					<div style="">
						<button type="submit" class="btn btn-danger">로그인</button>
						<a type="button" href="<%=notWithFormTag%>meInsert"
							class="btn btn-info">회원 가입</a>
					</div>
				</div>
			</div>
			<!-- login Box //-->
		</div>

		<!-- Bootstrap Bundle with Popper -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
			crossorigin="anonymous"></script>
	</form>

</body>
</html>