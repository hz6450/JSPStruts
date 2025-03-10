<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%	
request.setCharacterEncoding("UTF-8");	
String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/custom-font.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<title>JSP 게시판 웹 사이트</title>
<style type="text/css">
    .btn-arrow-left {
        margin-right: 10px;
    }
    
    .mdl-layout__content{
	margin: 40px;
}

.demo-card-wide.mdl-card {
  width: 100%;
}
.demo-card-wide > .mdl-card__title {
  color: #fff;
  height: 176px;
  background: url('images/1.jpg') center / cover;
}
.demo-card-wide > .mdl-card__menu {
  color: #fff;
}
</style>
</head>

<body>
	<%
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	%>

<%@ include file="./view/Header.jsp" %>
  <main class="mdl-layout__content">
<div class="demo-card-wide mdl-card mdl-shadow--2dp">
  <div class="mdl-card__title">
    <h2 class="mdl-card__title-text">JSP 게시판 웹 사이트</h2>
  </div>
  <div class="mdl-card__supporting-text">
 	Struts, MDL, JSP 사용
  </div>
  <div class="mdl-card__actions mdl-card--border">
    <button  onclick="location.href='BBS.do'" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
      바로 글쓰러 가기
    </button>
  </div>
  <div class="mdl-card__menu">
    <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
      <i class="material-icons">share</i>
    </button>
  </div>
</div>
  </main>
  <%@ include file="./view/Footer.jsp" %>
</div>


</body>



</html>
