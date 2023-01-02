package registerJDBC;

import java.sql.Connection;
import java.util.List;

public class StudentService {

	private JDBCTemplate jdbcTemplate;
	private StudentDAO stdDao;

	public StudentService() {
		jdbcTemplate = JDBCTemplate.getDriverLoad();
		stdDao = new StudentDAO();
	}

	public int insertStudent(Student student) {
		Connection conn = jdbcTemplate.getConnection();
		int result = stdDao.insertStudent(conn, student);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public Student selectByInfo(String id, String pw) {
		Connection conn = jdbcTemplate.getConnection();
		Student student = stdDao.selectByInfo(conn, id, pw);
		jdbcTemplate.close(conn);
		return student;
	}

	public List<Student> selectAll() {
		Connection conn = jdbcTemplate.getConnection();
		List<Student> sList = stdDao.selectAll(conn);
		jdbcTemplate.close(conn);
		return sList;
	}

	public int updateCodeNum(int codeNum, Student std) {
		Connection conn = jdbcTemplate.getConnection();
		int result = stdDao.updateCodeNum(conn, codeNum, std);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int checkDupId(String id) {
		Connection conn = jdbcTemplate.getConnection();
		int result = stdDao.checkDupId(conn, id);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

}
