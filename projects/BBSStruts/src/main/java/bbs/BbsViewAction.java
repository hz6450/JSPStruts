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

public class BbsViewAction extends Action {

	private BbsDAO bbsdao = new BbsDAO(); // DAO 객체를 필드로 선언하여 여러 메서드에서 재사용

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bbsId = request.getParameter("bbsID");
		int bbsID = Integer.parseInt(bbsId);
		BbsForm bbs = new BbsDAO().getBbs(bbsID);

		String title = bbs.getBbsTitle();
		String category = bbs.getBbsCategory();
		String content = bbs.getBbsContent();
		String time = bbs.getBbsDate().substring(0, 11) + bbs.getBbsDate().substring(11, 13) + "시"
				+ bbs.getBbsDate().substring(14, 16) + "분";
		int view = bbs.getBbsView() + 1;

		request.setAttribute("title", title);
		request.setAttribute("category", category);
		request.setAttribute("content", content);
		request.setAttribute("time", time);
		request.setAttribute("view", view);

		System.out.println("상세 페이지로 이동");
		return mapping.findForward("view");
	}

}


