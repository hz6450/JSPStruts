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

public class BbsAction extends Action {

	private BbsDAO bbsdao = new BbsDAO(); // DAO 객체를 필드로 선언하여 여러 메서드에서 재사용
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String cp = request.getContextPath();
		System.out.println("게시판 페이지로 이동");
		String pageNumber = request.getParameter("pageNumber");
		int currentPage = parsePageNumber(pageNumber);

		String category = request.getParameter("category");
		System.out.println(currentPage);
		System.out.println(category);

		int dataCount = bbsdao.getNextPage(category);
		int numPerPage = 5;
		int totalPage = getTotalPage(numPerPage, dataCount);

		System.out.println("총페이지" + totalPage);

		if (currentPage > totalPage)
			currentPage = totalPage;
		
		
		
		ArrayList<BbsForm> lists = bbsdao.getList(currentPage, category);
		//System.out.println(lists.bbsTitle);

		System.out.println(lists);
		String articleUrl = cp + "/BBS/view.do?pageNum=" + currentPage;
		request.setAttribute("lists", lists);
		request.setAttribute("articleUrl", articleUrl);
		request.setAttribute("dataCount", dataCount);
		request.setAttribute("totalPage", totalPage);
		return mapping.findForward("BBS");
	}

	

	private int parsePageNumber(String pageNumber) {
		try {
			return (pageNumber != null) ? Integer.parseInt(pageNumber) : 1;
		} catch (NumberFormatException e) {
			return 1; // 기본 페이지 번호 설정
		}
	}

	private int getTotalPage(int numPerPage, int dataCount) {
		return (dataCount % numPerPage == 0) ? (dataCount / numPerPage) : (dataCount / numPerPage + 1);
	}
}
