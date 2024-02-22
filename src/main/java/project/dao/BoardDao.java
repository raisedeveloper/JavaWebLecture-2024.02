package project.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.entity.board;

public class BoardDao {
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/" + "jdbc/bbs"); // context에 추가한 부분의 name
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public board getBoard(int bid) {
		Connection conn = getConnection();
		// 골라라 전부를 보드로부터 만약 bid가 같다면
		String sql = "SELECT b.*, u.uname FROM board b"
				+ "	JOIN users u ON b.uid=u.uid"
				+ "	WHERE b.bid=?;";	// ?는 HeidiSQL에서 1, Eclips에서 변형
			board board = null;			// 빈껍데기 왜? 나중에 여기에 담아야 하기 때문		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);	// conn에 sql을 실행하려고 준비하는 것
			pstmt.setInt(1, bid);
						
			ResultSet rs = pstmt.executeQuery();// 쿼리 실행 sql이 pstmt에 담겨있고 execute 실행
			while (rs.next()) {
			board = new board(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), LocalDateTime.parse(rs.getString(5).replace(" ", "T")),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	// field 값은 title, content, uid 등 attribute name
	// query 값은 검색어 : 어떤 단어(attribute name)를 어디서(title, content, uid) 찾을 것인가?
	public List<board> getBoardList(String field, String query, int num, int offset) {
		Connection conn = getConnection();
		String sql = "SELECT b.*,u.uname FROM board b"
				+ "	JOIN users u ON b.uid=u.uid"
				+ "	WHERE b.isDeleted=0 AND " + field + " LIKE ?"	// title이 field로 바뀜
				+ "	ORDER BY bid DESC"
				+ "	LIMIT ? OFFSET ?;"; // into 있는 것이 정석	
		List<board> list = new ArrayList<board>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query);
			pstmt.setInt(2, num);
			pstmt.setInt(3, offset);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
			board board = new board(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), LocalDateTime.parse(rs.getString(5).replace(" ", "T")),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)); 
			list.add(board);
		
			}	rs.close(); pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void insertBoard(board board) {
		Connection conn = getConnection();
		// 구상단계 - 해야할 일 떠올리기(구조도, Design)
		// 1. SQL문을 쓸 것
		// 2. 쓴 SQL을 어디다 담을 공간을 준비 
		// 3. 내용물 넣을 것 준비 
		// 4. SQL문 실행 
		// 5. 자원 새지 않게 닫기
	        String sql = "insert into board values(default, ?, ?, ?, default, default, default, default)";
	        try {
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, board.getTitle());
	            pstmt.setString(2, board.getContent());
	            pstmt.setString(3, board.getUid());

	            pstmt.executeUpdate();
	            pstmt.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	public void updateBoard(board board) {
		Connection conn = getConnection();
		String sql = "update board set title=?, content=?, bid=? where bid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteBoard(int bid) {
		Connection conn = getConnection();
		String sql = "update board set isDelete where bid=?";
	}
	// field 값은 view 또는 reply 가져감
	public void increaseCount(String field, int bid) {
		Connection conn = getConnection();
		String sql = "UPDATE board SET " + field + "Count=" + field + "Count+1 WHERE bid=?";	// if 절의 sum 같은 느낌 / 조회수가 올라갈 때마다 +1  / ? = viewCount
		// where bid=? 어떤 게시판에 james의 글을 누군가 본다면 조회수가 올라간다 == bid가 누구인지 찾는 것
		try {	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}