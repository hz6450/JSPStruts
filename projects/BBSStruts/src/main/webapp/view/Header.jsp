<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<title>JSP 게시판 웹 사이트</title>
<style type="text/css">
.btn-arrow-left {
	margin-right: 10px;
}

</style>
</head>
<body>

	<div class="mdl-layout mdl-js-layout">
		<header class="mdl-layout__header mdl-layout__header--scroll">
			<div class="mdl-layout__header-row">
				<!-- Title -->
				<a class="mdl-navigation__link" href="main.do"><span class="mdl-layout-title" >JSP 게시판 웹 사이트</span></a>
				<!-- Add spacer, to align navigation to the right -->
				<div class="mdl-layout-spacer"></div>
				<!-- Navigation -->
				<nav class="mdl-navigation">
					<a class="mdl-navigation__link" href="main.do">메인</a> <a
						class="mdl-navigation__link" href="BBS.do">게시판</a>
				</nav>
			</div>
		</header>
		<div class="mdl-layout__drawer">
			<span class="mdl-layout-title">Title</span>
			<nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="main.do">메인</a> <a
					class="mdl-navigation__link" href="BBS.do">게시판</a>
			</nav>
		</div>
