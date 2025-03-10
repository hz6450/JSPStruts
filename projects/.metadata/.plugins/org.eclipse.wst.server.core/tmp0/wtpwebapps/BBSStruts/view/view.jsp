<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.BbsForm"%>
<%@ page import="bbs.BbsDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="../css/custom-font.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<title>jsp 게시판 웹 사이트</title>
<style type="text/css">
.inputTodoBox {
	display: flex;
	justify-content: flex-end; /* 버튼을 오른쪽으로 정렬 */
	gap : 10px;
	margin-top: 20px;
}

.inputTodoBox button {
	border: 1px solid #dddddd;
}
.mdl-layout__content{
	margin: 40px;
}

#bbs-table {
	box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.1);
	width: 100%;
	border: 1px solid #dddddd;
	text-align: center;
}

#bbs-table td {
	vertical-align: middle;
	text-align: left;
}

#bbs-table th {
	vertical-align: middle;
	background-color: #eeeeee;
	text-align: center;
}
</style>
</head>

<body>

	<c:set var="bbsID" value="0" />
	<c:if test="${not empty param.bbsID}">
		<c:set var="bbsID" value="${param.bbsID}" />
	</c:if>
	<c:if test="${bbsID == 0} ">
		<script>
			alert("유효하지 않은 값입니다.");
			location.href = "bbs.jsp"; /* 수정된 부분: location.href() -> location.href */
		</script>
	</c:if>

	<%@ include file="Header.jsp"%>
	<main class="mdl-layout__content">
		<div class="page-content">
			<div class="container">
				<div class="row">
					<table class="mdl-data-table mdl-js-data-table" id="bbs-table">
						<thead>
							<tr>
								<th colspan="3" class="mdl-data-table__cell--non-numeric">게시판
									글보기</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width: 20%;">글 제목</td>
								<td colspan="2"><c:out value="${title}" /></td>
							</tr>

							<tr>
								<td style="width: 20%;">글 종류</td>
								<td colspan="2"><c:out value="${category}" /></td>
							</tr>
							<tr>
								<td>작성 일자</td>
								<td colspan="2"><c:out value="${time}" /></td>
							</tr>
							<tr>
								<td style="vertical-align: middle;">내용</td>
								<td colspan="2" style="height: 200px; text-align: left;"><c:out
										value="${content}" /></td>
							</tr>
							<tr>
								<td>조회수</td>
								<td colspan="2"><c:out value="${view}" /></td>
							</tr>
						</tbody>
					</table>

				</div>
				<div class="inputTodoBox">
					<button onclick="location.href='javascript:history.back();'" class="mdl-button mdl-js-button mdl-js-ripple-effect">목록</button>
					<button style="background-color:DodgerBlue; color:white;" onclick="location.href='./update.do?bbsID=${bbsID}'" class="mdl-button mdl-js-button mdl-js-ripple-effect">수정</button>
					<button style="background-color:Red; color:white;"onclick="location.href='./delete.do?bbsID=${bbsID}'" class="mdl-button mdl-js-button mdl-js-ripple-effect">삭제</button>
				</div>
			</div>
			
		</div>
	</main>
	<%@ include file="Footer.jsp"%>
</body>
</html>
