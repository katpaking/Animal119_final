<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp"%>
<%@ page import="java.util.*"%>

<%
// 모든 문서에서 사용될 application 이름(Context)
String contextPath = request.getContextPath();

// in frontController.java file
String urlPatterns = "/Animal";

// form 태그를 사용할 때 필요한 변수
String withFormTag = contextPath + urlPatterns;

// form 태그가 아닌 경우에 사용할 변수
String notWithFormTag = contextPath + urlPatterns + "?command=";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 세션 영역에 loginfo라는이름으로 로그인 정보가 바인되어 있습니다. --%>
<%-- 변수 whologin : 0(미로그인) 1(일반 사용자) 2 (관리자) --%>

<c:set var="whologin" value="0" />
<c:if test="${not empty sessionScope.loginfo}">
	<c:if test="${sessionScope.loginfo.id == 'admin'}">
		<c:set var="whologin" value="2" />
	</c:if>
	<c:if test="${sessionScope.loginfo.id != 'admin'}">
		<c:set var="whologin" value="1" />
	</c:if>
</c:if>

<c:if test="${empty sessionScope.loginfo}">
	<c:set var="whologin" value="0" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- for sweet alert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<style type="text/css">
		.dropdown:hover .dropdown-menu {
			background-color:HoneyDew;
   			display: block;
   		 	margin-top: 0;
		}
	
		th { /* 테이블의 헤더 가운데 정렬 */
			text-align:center;
			font-size: 1.2rem;
		} 
		
		.mainTitle {
			background-color:HoneyDew;
			border: 1px solid LightBlue;
			padding: 10px;
			border-radius: 15px;
		}
	
		@import url(https://fonts.googleapis.com/css?family=Lato:400,300,700,900);
		
		*, *:after, *:before {
			-webkit-box-sizing: border-box;
			-moz-box-sizing: border-box;
			box-sizing: border-box;
		}

		a:hover, a:focus {
			outline: none;
		}

		/*----*/

		h3 {
			color: #fff;
			font-weight: bold;
			margin: 10px 0 0 0;
			position: relative;
			padding: 0 0 5px 0;
		}
		
		h3:before {
			content: "";
			background: rgba(255, 255, 255, 0.1);
			bottom: -4px;
			width: 25%;
			height: 1px;
			left: 0;
			position: absolute;
		}
		
		.back {
			padding: 0 0 0 0;
		}
		
		
		.color-4 {
			background: dark;
		}
		
		body * {
		  font-family: Lato !important;
		}

		/*
		-----------------
		common style
		-----------------
		*/

		.menu a {
			color:  dark;
			font-family: Lato;
			font-size: 17pt;
			font-weight: 400;  
			padding: 15px 25px;
			/**/
			position: relative;
			display: block;
			text-decoration: none;
			text-transform: uppercase;
		}


		/*
		---------------------------------------
		hover effect 24
		---------------------------------------
		*/
		
		.SMN_effect-24 a {
			padding: 10px 20px;
			position: relative;
			overflow: hidden;
			display: inline-block;
			line-height: 35px;
			-webkit-transition: 0.3s ease-out;
			-moz-transition: 0.3s ease-out;
			transition: 0.3s ease-out;
		}
		
		.SMN_effect-24 a span:before {
			width: 5px;
			height: 5px;
			background: transparent;
			content: "";
			position: absolute;
			left: 0;
			top: 0;
			border-top: 2px solid #218c74;
			border-left: 2px solid #218c74;
			-webkit-transition: 0.3s;
			-moz-transition: 0.3s;
			transition: 0.3s;
			opacity: 0;
		}
		
		.SMN_effect-24 a span:after {
			width: 6px;
			height: 6px;
			background: transparent;
			content: "";
			position: absolute;
			right: 0;
			bottom: 0;
			border-right: 2px solid #218c74;
			border-bottom: 2px solid #218c74;
			-webkit-transition: 0.3s;
			-moz-transition: 0.3s;
			transition: 0.3s;
			opacity: 0;
		}
		
		.SMN_effect-24 a:before {
			width: 6px;
			height: 6px;
			background: transparent;
			content: "";
			position: absolute;
			right: 0;
			top: 0;
			border-right: 2px solid #218c74;
			border-top: 2px solid #218c74;
			-webkit-transition: 0.3s;
			-moz-transition: 0.3s;
			transition: 0.3s;
			opacity: 0;
		}
		
		.SMN_effect-24 a:after {
			width: 6px;
			height: 6px;
			background: transparent;
			content: "";
			position: absolute;
			left: 0;
			bottom: 0;
			border-left: 2px solid #218c74;
			border-bottom: 2px solid #218c74;
			-webkit-transition: 0.3s;
			-moz-transition: 0.3s;
			transition: 0.3s;
			opacity: 0;
		}
		
		.SMN_effect-24 a:hover {
			color: #218c74;
		}
		
		.SMN_effect-24 a:hover:before {
			opacity: 1;
			right: 5px;
			top: 5px;
		}
		
		.SMN_effect-24 a:hover:after {
			opacity: 1;
			left: 5px;
			bottom: 5px;
		}
		
		.SMN_effect-24 a:hover span:before {
			opacity: 1;
			left: 5px;
			top: 5px;
		}
		
		.SMN_effect-24 a:hover span:after {
			opacity: 1;
			right: 5px;
			bottom: 5px;
		}
	</style>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" style="height:70px;">
	<h3>
		<div class="container-fluid back color-4">
			<div class="row columns justify-content-center">      
			<ul class="navbar-nav align-center expanded text-center SMN_effect-24">				
				<li class="nav-item dropdown">
					<ul>
						<a class="navbar-brand" href="<%=notWithFormTag%>home"><img src="<%=contextPath%>/image/119animal.png" alt="img" class="d-block" width="50"></a>
						<c:if test="${whologin == 0}">
							<a class="nav-link"><p style="line-height:0.2"><font size="1.5">환영합니다.<br>손님</font></p></a>
						</c:if>
						<c:if test="${whologin != 0}">
							<a class="nav-link"><p style="line-height:0.2"><font size="1.5">환영합니다.<br>${sessionScope.loginfo.name}님 </font></p></a>
						</c:if>
					</ul>
				</li>

			<!-- 회원 section -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">회원메뉴</a>
					<ul class="dropdown-menu">
						<c:if test="${whologin == 0}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>meInsert">회원가입</a></li>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogin">로그인</a></li>
						</c:if>
						<c:if test="${whologin != 0}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">상세보기</a></li>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>meDelete&id=${sessionScope.loginfo.id}">탈퇴하기</a></li>
						</c:if>
						<c:if test="${whologin == 2}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>meList">회원목록</a></li>
						</c:if>
					</ul>
				</li>

			<!-- 보호동물 안내 section -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">보호동물</a>
					<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>aniInsert">보호동물 등록</a></li>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>aniList">보호동물 안내</a></li>
						<c:if test="${whologin == 2}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>AdoptList">입양신청 목록</a></li>
						</c:if>
					</ul>
				</li>


			<!-- 커뮤니티 section -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">커뮤니티</a>
					<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>noList">공지사항</a></li>
						<c:if test="${whologin == 2}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>noInsert">공지사항 등록</a></li>
						</c:if>
						<c:if test="${whologin == 2}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>suList">봉사신청 목록</a></li>
						</c:if>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>frboList">자유게시판</a></li>
						<c:if test="${whologin != 0}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>frboInsert">게시글 등록</a></li>
						</c:if>
					</ul>
				</li>
				 
			<!-- product section -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">쇼핑몰</a>
					<ul class="dropdown-menu"> 
						<c:if test="${whologin == 2}">
							<li><a class="dropdown-item" href="<%=notWithFormTag%>prInsert">상품 등록</a></li>
						</c:if>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>prList">목록 보기</a></li>    
					</ul>
				</li>
			
			
			<!-- mall section -->
				<li class="nav-item dropdown mallimg">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><img src="<%=contextPath%>/image/cart.png" alt="img" class="d-block" width="50"></a>
					<ul class="dropdown-menu">          
						<li><a class="dropdown-item" href="<%=notWithFormTag%>maList">카트 내역 보기</a></li>
						<li><a class="dropdown-item" href="<%=notWithFormTag%>maHistory">나의 쇼핑 내역</a></li>
					</ul>
				</li>       
			</ul>
			</div>
		</div>
		</b>
	</h3>
</nav>
	<%-- 사용자에게 주의/경고/오류를 알려주기 위한 Alert Box --%>
	<c:if test="${not empty sessionScope.alertMSG}">
		<div class="alert alert-danger alert-dismissible">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			${sessionScope.alertMSG}
		</div>
	</c:if>

	<%-- session 영역에 alertMSG를 빈 문자열로 셋팅합니다. --%>
	<%-- <c:set var="alertMSG" value="" scope="session"/> --%>

	<%-- session 영역의 변수 alertMSG를 제거합니다. --%>
	<c:remove var="alertMSG" scope="session" />
</body>
</html>