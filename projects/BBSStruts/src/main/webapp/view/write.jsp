<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<title>jsp 게시판 웹 사이트</title>
</head>

<style>
#bbs-table {
    box-shadow: 4px 4px 10px rgba(0, 0, 0, 0.1);
    width: 100%;
}

#bbs-table td {
    vertical-align: middle;
    text-align: left;
    
}

#bbs-table td input {
    vertical-align: middle;
    text-align: left;
    width:100%;
}

#bbs-table td option {
    vertical-align: middle;
    text-align: left;
    width:100%;
}

#bbs-table th {
    vertical-align: middle;
    background-color: #eeeeee; 
    text-align: center;
}

.mdl-layout__content{
	margin: 40px;
}
.mdl-grid {
    justify-content: center;
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
	<c:set var="userID" value="null"></c:set>
	<c:if test="${not empty sessionScope.userID}">
		<c:set var="userID" value="${sessionScope.userID}" />
	</c:if>

	<script>
		function sendIt(event) {
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

			str = f.bbsCategory.value.trim();
			if (!str) {
				alert("\n카테고리를 선택하세요.");
				f.bbsCategory.focus();
				return;
			}
			f.bbsCategory.value = str;

			// URL 확인
			console.log("Action URL: " + f.action);

			f.action = "./write_ok.do";
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

							<table class="mdl-data-table mdl-js-data-table" id="bbs-table" style="border: 1px solid #dddddd;">
								<thead>
									<tr>
										<th class="mdl-data-table__cell--non-numeric" colspan="2">게시판 글쓰기 양식</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><select name="bbsCategory" id="bbsCategory"
											class="form-control">
												<option value="notice">공지</option>
												<option value="free">자유</option>
												<option value="question">질문</option>
										</select></td>
										<td><input type="text" class="form-control"
											placeholder="글 제목" name="bbsTitle" maxlength="50"></td>
									</tr>
									<tr>
										<td colspan="2"><textarea 
												class="form-control" placeholder="글 내용" name="bbsContent"
												maxlength="2048" style="height: 350px;"></textarea></td>
									</tr>
								</tbody>


							</table>
							<button
								class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored"
								onclick="sendIt(event)">글쓰기</button>

						</div>
					</form>
				</div>

			</div>
		</main>
		
		 <%@ include file="Footer.jsp" %>


</body>
</html>