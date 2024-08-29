package bbs;

import org.apache.struts.action.ActionForm;

public class BbsForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bbsId;
	private String bbsTitle;
	private String userID;
	private String bbsDate;
	private String bbsContent;
	private String bbsCategory;
	private int bbsView;

	public String getBbsCategory() {
		return bbsCategory;
	}

	public void setBbsCategory(String bbsCategory) {
		this.bbsCategory = bbsCategory;
	}

	public int getBbsView() {
		return bbsView;
	}

	public void setBbsView(int bbsView) {
		this.bbsView = bbsView;
	}

	public int getBbsId() {
		return bbsId;
	}

	public void setBbsId(int bbsId) {
		this.bbsId = bbsId;
	}

	public String getBbsTitle() {
		return bbsTitle;
	}

	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getBbsDate() {
		return bbsDate;
	}

	public void setBbsDate(String bbsDate) {
		this.bbsDate = bbsDate;
	}

	public String getBbsContent() {
		return bbsContent;
	}

	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}

	public int getBbsAvailable() {
		return bbsAvailable;
	}

	public void setBbsAvailable(int bbsAvailable) {
		this.bbsAvailable = bbsAvailable;
	}

	private int bbsAvailable;

}
