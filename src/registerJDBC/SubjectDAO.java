package registerJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {

	// 관리자 부분
	// 0. 자동 과목 생성
	// sql문에 값을 입력받는것이 없으면
	// sqlInjection에 방어하기 위해 Statement를 쓰자!
	public int autoInsertSubject(Connection conn) {
		String sql1 = "INSERT INTO SUBJECT_TBL VALUES('자바', '김이준', SUB_SEQUENCE.NEXTVAL, DEFAULT, DEFAULT)";
		String sql2 = "INSERT INTO SUBJECT_TBL VALUES('파이썬', '이서준', SUB_SEQUENCE.NEXTVAL, DEFAULT, DEFAULT)";
		String sql3 = "INSERT INTO SUBJECT_TBL VALUES('C언어', '홍하준', SUB_SEQUENCE.NEXTVAL, DEFAULT, DEFAULT)";
		String sql4 = "INSERT INTO SUBJECT_TBL VALUES('프론트 개발자 양성과정', '박도윤', SUB_SEQUENCE.NEXTVAL, DEFAULT, DEFAULT)";
		String sql5 = "INSERT INTO SUBJECT_TBL VALUES('Springframework & 클라우드 융합 웹 개발자 양성과정', '민봉식', SUB_SEQUENCE.NEXTVAL, DEFAULT, DEFAULT)";
		String sql6 = "INSERT INTO SUBJECT_TBL VALUES('클라우드 컴퓨팅 엔지니어 양성과정', '김시우', SUB_SEQUENCE.NEXTVAL, DEFAULT, DEFAULT)";
		String sql7 = "INSERT INTO SUBJECT_TBL VALUES('빅데이터 기반 금융 솔루션 UI개발자 양성과정', '서은우', SUB_SEQUENCE.NEXTVAL, DEFAULT, DEFAULT)";

		List<String> list = new ArrayList<>();
		list.add(sql1);
		list.add(sql2);
		list.add(sql3);
		list.add(sql4);
		list.add(sql5);
		list.add(sql6);
		list.add(sql7);

		int result = 0;
		try {
//			Class.forName(DRIVER_NAME);
//			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			for (int i = 0; i < 7; i++) {
				result += stmt.executeUpdate(list.get(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 1. 과목 생성
	// 완성
	public int insertSubject(Connection conn, Subject subject) {
		String sql = "INSERT INTO SUBJECT_TBL VALUES(?, ?, SUB_SEQUENCE.NEXTVAL, ?, DEFAULT)";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject.getSubjectName());
			pstmt.setString(2, subject.getName());
			pstmt.setInt(3, subject.getCapacity());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 2. 과목 삭제
	public int deleteSubject(Connection conn, int codeNum) {
		String sql = "DELETE FROM SUBJECT_TBL WHERE SUBJECT_CODE = ?";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, codeNum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 3. 전체 과목 조회
	// 완성
	public List<Subject> selectAll(Connection conn) {
		String sql = "SELECT * FROM SUBJECT_TBL ORDER BY SUBJECT_CODE";
		Subject subject = null;
		 List<Subject> sList = null;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			sList = new ArrayList<>();
			while (rs.next()) {
				subject = new Subject(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
				sList.add(subject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
	}

	// 받아온 과목코드와 일치하는 과목을 조회
	public Subject selectByCodeNum(Connection conn, int codeNum) {
		String sql = "SELECT * FROM SUBJECT_TBL WHERE SUBJECT_CODE = ?";
		Subject subject = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, codeNum);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				subject = new Subject(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subject;
	}

	// 해당 과목코드가 존재하는지 확인
	public int checkCodeNum(Connection conn, int codeNum) {
//		String sql = "SELECT COUNT(*) AS COUNT FROM SUBJECT_TBL WHERE SUBJECT_CODE = ?";
		String sql = "SELECT COUNT(*) FROM SUBJECT_TBL WHERE SUBJECT_CODE = ?";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, codeNum);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 받아온 과목코드와 일치하는 과목의 신청수강인원을 +1
	public int plusRegisterNum(Connection conn, Subject subject) {
		String sql = "UPDATE SUBJECT_TBL SET REGISTER_NUMBER = ? WHERE SUBJECT_CODE = ?";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subject.getRegisterNumber() + 1);
			pstmt.setInt(2, subject.getSubjectCode());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 받아온 과목코드와 일치하는 과목의 신청수강인원을 -1
	public int minusRegisterNum(Connection conn, Subject subject) {
		String sql = "UPDATE SUBJECT_TBL SET REGISTER_NUMBER = ? WHERE SUBJECT_CODE = ?";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, subject.getRegisterNumber() - 1);
			pstmt.setInt(2, subject.getSubjectCode());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 신청한 과목의 최대수강신청 인원을 가져옴
	public int getMaxNum(Connection conn, int codeNum) {
		String sql = "SELECT CAPACITY FROM SUBJECT_TBL WHERE SUBJECT_CODE = ?";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, codeNum);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 신청한 과목의 현재수강인원을 가져옴
	public int getCurrNum(Connection conn, int codeNum) {
		String sql = "SELECT REGISTER_NUMBER FROM SUBJECT_TBL WHERE SUBJECT_CODE = ?";
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, codeNum);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
