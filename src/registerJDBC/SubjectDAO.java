package registerJDBC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SubjectDAO {

	private Properties prop;

	public SubjectDAO() {
		prop = new Properties();
		try {
			FileReader reader = new FileReader("resources/query.properties");
			prop.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 1. 과목 생성
	public int insertSubject(Connection conn, Subject subject) {
		String sql = prop.getProperty("insertSubject");
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
		String sql = prop.getProperty("deleteSubject");
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
	public List<Subject> selectAll(Connection conn) {
		String sql = prop.getProperty("selectAll2");
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
		String sql = prop.getProperty("selectByCodeNum");
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
		String sql = prop.getProperty("checkCodeNum");
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
		String sql = prop.getProperty("plusRegisterNum");
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
		String sql = prop.getProperty("minusRegisterNum");
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
		String sql = prop.getProperty("getMaxNum");
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
		String sql = prop.getProperty("getCurrNum");
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
