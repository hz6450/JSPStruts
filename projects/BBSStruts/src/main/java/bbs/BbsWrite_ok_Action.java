package bbs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BbsWrite_ok_Action extends Action {

	private BbsDAO bbsdao = new BbsDAO(); // DAO 객체를 필드로 선언하여 여러 메서드에서 재사용

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BbsForm dto = new BbsForm();
		Random ran = new Random();
		String tempID = ran.nextInt(100) + "." + ran.nextInt(100);

		dto.setBbsTitle(request.getParameter("bbsTitle"));
		dto.setUserID(tempID);
		dto.setBbsContent(request.getParameter("bbsContent"));
		dto.setBbsCategory(request.getParameter("bbsCategory"));
		
		System.out.println("테스트제목" + dto.getBbsTitle());
		System.out.println("테스트내용" + dto.getBbsContent()); 

		bbsdao.insertData(dto);
		System.out.println("게시글 입력 성공");

		// 게시글 작성 후, 게시판 목록 페이지로 이동
		String cp = request.getContextPath();
		String articleUrl = cp + "/BBS.do";

		// 리다이렉션을 통해 게시판 페이지로 이동
		response.sendRedirect(articleUrl);

		return null; // 리다이렉션 후에는 null을 반환합니다.dleh
	}

}

