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

public class StudentDAO {
	
	private Properties prop;
	
	public StudentDAO() {
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
	
	// 추가
	public int insertStudent(Connection conn, Student student) {
		String sql = prop.getProperty("insertStudent");
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 순서필요x, 해당 인덱스 번호랑 값만 맞춰주면 된다!
			pstmt.setInt(4, student.getSubjectCode());
			pstmt.setString(3, student.getStudentPw());
			pstmt.setString(2, student.getName());
			pstmt.setString(1, student.getStudentId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 특정 회원만 조회
	public Student selectByInfo(Connection conn, String id, String pw) {
		String sql = prop.getProperty("selectByInfo");
		Student student = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				student = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return student;
	}

	// 전체 조회 + 과목명
	public List<Register> selectAll(Connection conn) {
		// 관리자를 학생테이블에 같이 포함시켰기 때문에 제외 시켜야 한다
		String sql = prop.getProperty("selectAll");
		Register register = null;
		List<Register> sList = null;

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			sList = new ArrayList<>();
			while (rs.next()) {
				register = new Register(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
				sList.add(register);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sList;
	}
	
	// 과목 코드 변경
	public int updateCodeNum(Connection conn, int codeNum, Student std) {
		String sql = prop.getProperty("updateCodeNum");
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, codeNum);
			pstmt.setString(2, std.getStudentId());
			pstmt.setString(3, std.getStudentPw());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 중복 아이디 처리
	public int checkDupId(Connection conn, String id) {
		String sql = prop.getProperty("checkDupId");
		int result = -1;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

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
