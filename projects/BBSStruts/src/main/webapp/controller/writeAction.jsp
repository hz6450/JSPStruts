<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bbs.BbsDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.Random" %>

<%@ page import="javax.servlet.ServletException"%>
<%@ page import="javax.servlet.annotation.WebServlet"%>

<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>


<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="bbs" class="bbs.BbsForm" scope="page" />
<jsp:setProperty name="bbs" property="bbsTitle" />
<jsp:setProperty name="bbs" property="bbsContent" />
<jsp:setProperty name="bbs" property="bbsCategory" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>jsp 게시판 웹 사이트</title>
</head>

<body>
	
	<%
	Random ran = new Random();
	Random ran2 = new Random();
	
	String tempID = ran.nextInt(100) + "." + ran2.nextInt(100);
	
	String userID = null;
	
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}


		if (bbs.getBbsTitle() == null || bbs.getBbsContent() == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>"); // script태그 실행문구
			script.println("alert('입력안된 사항이 있습니다')");
			script.println("history.back()");
			script.println("</script>");
		} else {
			BbsDAO bbsDAO = new BbsDAO();
			int result = bbsDAO.write(bbs.getBbsTitle(), tempID, bbs.getBbsContent(), bbs.getBbsCategory());
			if (result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>"); // script태그 실행문구
		script.println("alert('글쓰기에 실패했습니다.')");
		script.println("history.back()");
		script.println("</script>");
			} else {
		PrintWriter script = response.getWriter();
		script.println("<script>"); // script태그 실행문구
		script.println("location.href='../view/BBS.jsp'");
		script.println("</script>");
			}
		}


	%>
</body>

</html>