package registerJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String USER = "PROJECT";
	private final String PASSWORD = "PROJECT";
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

	// 추가
	public int insertStudent(Student student) {
		String sql = "INSERT INTO STUDENT_TBL VALUES(?,?,?,?)";
		int result = -1;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 순서필요x, 해당 인덱스 번호랑 값만 맞춰주면 된다!
			pstmt.setInt(4, student.getSubjectCode());
			pstmt.setString(3, student.getStudentPw());
			pstmt.setString(2, student.getName());
			pstmt.setString(1, student.getStudentId());
			result = pstmt.executeUpdate();

			return result;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 특정 회원만 조회
	public Student selectByInfo(String id, String pw) {
		String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = ? AND STUDENT_PW = ?";
		Student student = null;

		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				student = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}

			return student;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 전체 조회
	public List<Student> selectAll() {
		String sql = "SELECT * FROM STUDENT_TBL ORDER BY STUDENT_ID ASC";
		Student student = null;
		List<Student> sList = null;

		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			sList = new ArrayList<>();
			while (rs.next()) {
				student = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				sList.add(student);
			}

			return sList;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 과목 코드 변경
	public int updateCodeNum(int codeNum, Student std) {
		String sql = "UPDATE STUDENT_TBL SET SUBJECT_CODE = ? WHERE STUDENT_ID = ? AND STUDENT_PW = ?";
		int result = -1;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, codeNum);
			pstmt.setString(2, std.getStudentId());
			pstmt.setString(3, std.getStudentPw());
			result = pstmt.executeUpdate();

			return result;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 중복 아이디 처리
	public int checkDupId(String id) {
		String sql = "SELECT COUNT(*) FROM STUDENT_TBL WHERE STUDENT_ID = ?";
		int result = -1;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}

			return result;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
