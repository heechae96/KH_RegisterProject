package registerJDBC;

import java.sql.Connection;
import java.util.List;

public class StudentService {

	private StudentDAO stdDao;

	public StudentService() {
		stdDao = new StudentDAO();
	}

	public int insertStudent(Student student) {
		Connection conn = JDBCTemplate.getConnection();
		int result = stdDao.insertStudent(conn, student);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public Student selectByInfo(String id, String pw) {
		Connection conn = JDBCTemplate.getConnection();
		Student student = stdDao.selectByInfo(conn, id, pw);
		return student;
	}

	public List<Register> selectAll() {
		Connection conn = JDBCTemplate.getConnection();
		List<Register> sList = stdDao.selectAll(conn);
		return sList;
	}

	public int updateCodeNum(int codeNum, Student std) {
		Connection conn = JDBCTemplate.getConnection();
		int result = stdDao.updateCodeNum(conn, codeNum, std);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public int checkDupId(String id) {
		Connection conn = JDBCTemplate.getConnection();
		int result = stdDao.checkDupId(conn, id);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
