package registerJDBC;

import java.sql.Connection;
import java.util.List;

public class SubjectService {

	private JDBCTemplate jdbcTemplate;
	private SubjectDAO subDao;

	public SubjectService() {
		jdbcTemplate = JDBCTemplate.getDriverLoad();
		subDao = new SubjectDAO();
	}

	public int autoInsertSubject() {
		Connection conn = jdbcTemplate.getConnection();
		int result = subDao.autoInsertSubject(conn);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int insertSubject(Subject subject) {
		Connection conn = jdbcTemplate.getConnection();
		int result = subDao.insertSubject(conn, subject);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int deleteSubject(int codeNum) {
		Connection conn = jdbcTemplate.getConnection();
		int result = subDao.deleteSubject(conn, codeNum);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public List<Subject> selectAll() {
		Connection conn = jdbcTemplate.getConnection();
		List<Subject> list = subDao.selectAll(conn);
		jdbcTemplate.close(conn);
		return list;
	}

	public Subject selectByCodeNum(int codeNum) {
		Connection conn = jdbcTemplate.getConnection();
		Subject subject = subDao.selectByCodeNum(conn, codeNum);
		jdbcTemplate.close(conn);
		return subject;
	}

	public int checkCodeNum(int codeNum) {
		Connection conn = jdbcTemplate.getConnection();
		int result = subDao.checkCodeNum(conn, codeNum);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

	public int plusRegisterNum(Subject subject) {
		Connection conn = jdbcTemplate.getConnection();
		int result = subDao.plusRegisterNum(conn, subject);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}
	
	public int minusRegisterNum(Subject subject) {
		Connection conn = jdbcTemplate.getConnection();
		int result = subDao.minusRegisterNum(conn, subject);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}
	
	public int getMaxNum(int codeNum) {
		Connection conn = jdbcTemplate.getConnection();
		int result = subDao.getMaxNum(conn, codeNum);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}
	
	public int getCurrNum(int codeNum) {
		Connection conn = jdbcTemplate.getConnection();
		int result = subDao.getCurrNum(conn, codeNum);
		if (result > 0) {
			jdbcTemplate.commit(conn);
		} else {
			jdbcTemplate.rollback(conn);
		}
		jdbcTemplate.close(conn);
		return result;
	}

}
