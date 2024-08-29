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

public class BbsSearch_ok_Action extends Action {

	private BbsDAO bbsdao = new BbsDAO(); // DAO 객체를 필드로 선언하여 여러 메서드에서 재사용

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<BbsForm> list = new ArrayList<BbsForm>();
		System.out.println("서블릿 통해서 넘어온 검색 기능 작동 페이지");
		String searchKeyword = request.getParameter("searchKeyword");
		String bbsCategory = request.getParameter("category");
		System.out.println(searchKeyword);
		System.out.println(bbsCategory);
		list = null;
		list = bbsdao.search(bbsCategory, searchKeyword);

		request.setAttribute("lists", list);
		System.out.println(list);

		System.out.println("게시글 검색 페이지로 이동");
		return mapping.findForward("search_ok");

	}

}
