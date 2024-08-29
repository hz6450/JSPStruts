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

public class BbsUpdate_ok_Action extends Action {

	private BbsDAO bbsdao = new BbsDAO(); // DAO 객체를 필드로 선언하여 여러 메서드에서 재사용

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BbsForm dto = new BbsForm();
		System.out.println("여기로 오기는 함");
		System.out.println(request.getParameter("bbsTitle"));
		System.out.println(request.getParameter("bbsContent"));
		System.out.println(request.getParameter("bbsID"));
		dto.setBbsTitle(request.getParameter("bbsTitle"));
		dto.setBbsContent(request.getParameter("bbsContent"));
		dto.setBbsId(Integer.parseInt(request.getParameter("bbsID")));
		
		System.out.println(dto);

		bbsdao.update(dto);
		System.out.println("수정 완료");

		System.out.println("게시글 검색 페이지로 이동");
		// 게시글 작성 후, 게시판 목록 페이지로 이동
		String cp = request.getContextPath();
		String articleUrl = cp + "/BBS.do";

		// 리다이렉션을 통해 게시판 페이지로 이동
		response.sendRedirect(articleUrl);

		return null; // 리다이렉션 후에는 null을 반환합니다.

	}

}
