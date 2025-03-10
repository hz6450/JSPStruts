<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.BbsForm"%>
<%@ page import="bbs.BbsDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/custom-font.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<title>jsp 게시판 웹 사이트</title>
</head>

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

input.form-control, textarea.form-control {
	width: 100%;
	box-sizing: border-box; /* padding 및 border를 포함하여 너비를 계산 */
}

textarea.form-control {
	resize: vertical; /* 세로로만 크기 조절 가능하도록 설정 */
}
</style>

<body>
	<c:set var="bbsID" value="0" />
	<c:if test="${not empty param.bbsID}">
		<c:set var="bbsID" value="${param.bbsID}" />
	</c:if>
	<c:if test="${bbsID == 0} ">
		<script>
			alert("유효하지 않은 값입니다.");
			location.href = "bbs.jsp";
		</script>
	</c:if>
	<%
	int bbsID = Integer.parseInt(request.getParameter("bbsID"));
	BbsForm bbs = new BbsDAO().getBbs(bbsID);

	pageContext.setAttribute("title", bbs.getBbsTitle());
	pageContext.setAttribute("content", bbs.getBbsContent());
	%>

	<script>
		function updateIt(event) {
			console.log("쓰기 호출");
			event.preventDefault(); // 기본 폼 제출 동작 방지

			var f = document.myForm;

			var str = f.bbsTitle.value.trim();
			if (!str) {
				alert("\n제목을 입력하세요.");
				f.bbsTitle.focus();
				return;
			}
			f.bbsTitle.value = str;

			str = f.bbsContent.value.trim();
			if (!str) {
				alert("\n내용을 입력하세요.");
				f.bbsContent.focus();
				return;
			}
			f.bbsContent.value = str;

			// URL 확인
			console.log("Action URL: " + f.action);

			f.action = "./update_ok.do";
			f.submit();
			console.log("성공적으로 전송");
		}
	</script>

	 <%@ include file="Header.jsp" %>
		<main class="mdl-layout__content">
			<div class="page-content">
				<div class="container">
					<form method="post" name="myForm">
						<div class="row">

							<table class="mdl-data-table mdl-js-data-table"
								style="text-align: center; border: 1px solid #dddddd;" id="bbs-table">
								<thead>
									<tr>
										<th colspan="2"
											style="background-color: #eeeeee; text-align: center;">게시판
											글 수정</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="text" class="form-control"
											placeholder="글 제목" name="bbsTitle" maxlength="50"
											value="${title}"></td>
									</tr>

									<tr>
										<td><textarea class="form-control" placeholder="글 내용"
												name="bbsContent" maxlength="2048" style="height: 350px;"><c:out
													value="${content}" /></textarea></td>
									</tr>
									<tr>
										<td>
											<!-- 숨겨진 필드에 bbsID 추가 --> <input type="hidden" name="bbsID"
											value="${bbsID}">
										</td>
									</tr>
								</tbody>

							</table>
							<button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"
								onclick="updateIt(event)">글 수정</button>

						</div>
					</form>
				</div>

			</div>
		</main>
		 <%@ include file="Footer.jsp" %>
	</div>
</body>

</html>
