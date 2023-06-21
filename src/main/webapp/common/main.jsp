<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="./../common/bootstrap5.jsp" %>
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
	
<!DOCTYPE html>
<html>
<head>
	<title>Bootstrap Example</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<!-- Carousel -->
<div id="demo" class="carousel slide" data-bs-ride="carousel">

  <!-- Indicators/dots -->
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
  </div>
  
  <!-- The slideshow/carousel -->
  <div class="carousel-inner">
    <div class="carousel-item active">
    <a href="<%=notWithFormTag%>home">
      <img src="<%=contextPath%>/image/MainCampaign1.jpg" alt="img" class="d-block" style="width:100%">
      </a>
    </div>
    <div class="carousel-item">
    <a href="<%=notWithFormTag%>home">
      <img src="<%=contextPath%>/image/MainCampaign2.jpg" alt="img" class="d-block" style="width:100%">
      </a>
    </div>
    <div class="carousel-item">
    <a href="<%=notWithFormTag%>home">
      <img src="<%=contextPath%>/image/MainCampaign3.jpg" alt="img" class="d-block" style="width:100%">
      </a>
    </div>
  </div>
  
  <!-- Left and right controls/icons -->
  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
    <span class="carousel-control-next-icon"></span>
  </button>
</div>	

</body>
</html>
