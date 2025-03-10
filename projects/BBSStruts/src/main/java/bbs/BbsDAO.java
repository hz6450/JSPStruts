package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.struts.action.Action;

public class BbsDAO extends Action {

	private Connection conn;
	private ResultSet rs;

	// 초기화
	public BbsDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbID = "root";
			String dbPassword = "6450";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 현재날짜
	public String getDate() {
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// 총 카테고리 페이지 갯수
	public int getNextPage(String category) throws SQLException {
		String SQL = "";
		if (category == null) {
			SQL = "SELECT COUNT(*) FROM BBS";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
		} else {
			SQL = "SELECT COUNT(*) FROM BBS WHERE bbsCategory=?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
		}

		rs.next();
		return rs.getInt(1);
	}

	// 존재하는 게시판 ID 나열
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	// 글 작성
	public int write(String bbsTitle, String userID, String bbsContent, String bbsCategory) {
		String SQL = "INSERT INTO BBS VALUE(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setString(5, bbsContent);
			pstmt.setInt(6, 1);
			pstmt.setInt(7, 0);
			pstmt.setString(8, bbsCategory);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 카테고리별 내림차순 글 목록
	public ArrayList<BbsForm> getList(int pageNumber, String category) throws SQLException {
		String SQL = "";
		ArrayList<BbsForm> list = new ArrayList<BbsForm>();
		int limit = (pageNumber - 1) * 5;
		if (category == null || category == "all") {
			SQL = "SELECT * From BBS ORDER BY bbsID DESC LIMIT 5 OFFSET ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, limit);
			rs = pstmt.executeQuery();
		} else {
			SQL = "SELECT * From BBS WHERE bbsCategory=? ORDER BY bbsID DESC LIMIT 5 OFFSET ?;";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, category);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
		}
		while (rs.next()) {
			BbsForm bbs = new BbsForm();
			bbs.setBbsId(rs.getInt("BbsId"));
			bbs.setBbsTitle(rs.getString("BbsTitle"));
			bbs.setUserID(rs.getString(3));
			bbs.setBbsDate(rs.getString(4));
			bbs.setBbsContent(rs.getString(5));
			bbs.setBbsAvailable(rs.getInt(6));
			bbs.setBbsView(rs.getInt(7));
			bbs.setBbsCategory(rs.getString(8));
			list.add(bbs);
		}
		// 예시
		System.out.println("Executing query: " + SQL);

		return list;
	}

	public ArrayList<BbsForm> search(String category, String searchContent) throws SQLException {
		ArrayList<BbsForm> list = new ArrayList<>();
		String SQL = "SELECT * FROM BBS WHERE ";

		if ("bbsId".equals(category)) {
			SQL += "bbsId = ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, searchContent);
			rs = pstmt.executeQuery();

		} else {
			SQL += category + " LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%" + searchContent + "%");
			rs = pstmt.executeQuery();
		}

		while (rs.next()) {
			BbsForm bbs = new BbsForm();
			bbs.setBbsId(rs.getInt("bbsId"));
			bbs.setBbsTitle(rs.getString("bbsTitle"));
			bbs.setUserID(rs.getString("userID"));
			bbs.setBbsDate(rs.getString("bbsDate"));
			bbs.setBbsContent(rs.getString("bbsContent"));
			bbs.setBbsAvailable(rs.getInt("bbsAvailable"));
			bbs.setBbsView(rs.getInt("bbsViews"));
			bbs.setBbsCategory(rs.getString("bbsCategory"));
			list.add(bbs);
		}
		return list;
	}

	// 특정 게시물 정보 얻기 + 조회수 카운트
	public BbsForm getBbs(int bbsID) {
		String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				BbsForm bbs = new BbsForm();
				bbs.setBbsId(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));
				int bbsView = rs.getInt(7);
				bbs.setBbsCategory(rs.getString(8));
				bbs.setBbsView(bbsView);
				bbsView = bbsView + 1;
				countUpdate(bbsView, bbsID);
				return bbs;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 카운트된 조회수 저장
	public int countUpdate(int bbsCount, int bbsID) {
		String SQL = "update bbs set bbsViews = ? where bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsCount);// 물음표의 순서
			pstmt.setInt(2, bbsID);
			return pstmt.executeUpdate();// insert,delete,update
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;// 데이터베이스 오류
	}

	// 업데이트 함수
	public int update(BbsForm dto) {
		String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent= ? WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, dto.getBbsTitle());
			pstmt.setString(2, dto.getBbsContent());
			pstmt.setInt(3, dto.getBbsId());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 삭제함수
	public int delete(int bbsID) {
		String SQL = "DELETE FROM BBS WHERE bbsID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int insertData(BbsForm dto) {
		int result = 0;
		String SQL = "INSERT INTO BBS VALUE(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);

			pstmt.setInt(1, getNext());
			pstmt.setString(2, dto.getBbsTitle());
			pstmt.setString(3, dto.getUserID());
			pstmt.setString(4, getDate());
			pstmt.setString(5, dto.getBbsContent());
			pstmt.setInt(6, 1);
			pstmt.setInt(7, 0);
			pstmt.setString(8, dto.getBbsCategory());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

}
