package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InputDAO {
	static Connection conn = null;

	public static ObservableList<BoardVO> boardList() {
		Statement stmt = null;
		ResultSet rs = null;
		ObservableList<BoardVO> boardList = FXCollections.observableArrayList();
		String sql = "select * from input_board";
		conn = DBcon.getConnection();// connection객체 생성

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setContents(rs.getString("contents"));
				vo.setExitDate(rs.getString("exit_Date"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setTitle(rs.getString("title"));
				vo.setPublicity(rs.getString("publicity"));
				boardList.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return boardList;
	}

	public static void updateBoard(BoardVO vo) {
		PreparedStatement stmt = null;
		String sql = "update input_board "//
				+ "    set publicity = ?, " //
				+ "        exit_date = ?, "//
				+ "       contents = ? "//
				+ "   where board_no = ? ";
		conn = DBcon.getConnection();

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getPublicity());
			stmt.setString(2, vo.getExitDate());
			stmt.setString(3, vo.getContents());
			stmt.setInt(4, vo.getBoardNo());
			int r = stmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// update

	public static void insertBoard(InputBoardVO bo) {
		PreparedStatement pstmt = null;
		String sql = "insert into input_board values" //
				+ "((select nvl(max(board_no),0)+1 from input_board),?,?,?,?,?)";
		conn = DBcon.getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bo.getTitle());
			pstmt.setString(2, bo.getPasswd());
			pstmt.setString(3, bo.getPublicity());
			pstmt.setString(4, bo.getExitDate());
			pstmt.setString(5, bo.getContents());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건 입력됨.");

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void deleteBoard(BoardVO vo) {
		PreparedStatement stmt = null;
		String sql = "delete from input_board where board_no= ?";
		conn = DBcon.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, vo.getBoardNo());
			int r = stmt.executeUpdate();
			System.out.println(r +"건 입력됨");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
