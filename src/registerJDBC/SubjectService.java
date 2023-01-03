package registerJDBC;

import java.sql.Connection;
import java.util.List;

public class SubjectService {

	private SubjectDAO subDao;

	public SubjectService() {
		subDao = new SubjectDAO();
	}

	public int insertSubject(Subject subject) {
		Connection conn = JDBCTemplate.getConnection();
		int result = subDao.insertSubject(conn, subject);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public int deleteSubject(int codeNum) {
		Connection conn = JDBCTemplate.getConnection();
		int result = subDao.deleteSubject(conn, codeNum);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public List<Subject> selectAll() {
		Connection conn = JDBCTemplate.getConnection();
		List<Subject> list = subDao.selectAll(conn);
		return list;
	}

	public Subject selectByCodeNum(int codeNum) {
		Connection conn = JDBCTemplate.getConnection();
		Subject subject = subDao.selectByCodeNum(conn, codeNum);
		return subject;
	}

	public int checkCodeNum(int codeNum) {
		Connection conn = JDBCTemplate.getConnection();
		int result = subDao.checkCodeNum(conn, codeNum);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public int plusRegisterNum(Subject subject) {
		Connection conn = JDBCTemplate.getConnection();
		int result = subDao.plusRegisterNum(conn, subject);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	public int minusRegisterNum(Subject subject) {
		Connection conn = JDBCTemplate.getConnection();
		int result = subDao.minusRegisterNum(conn, subject);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	public int getMaxNum(int codeNum) {
		Connection conn = JDBCTemplate.getConnection();
		int result = subDao.getMaxNum(conn, codeNum);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	public int getCurrNum(int codeNum) {
		Connection conn = JDBCTemplate.getConnection();
		int result = subDao.getCurrNum(conn, codeNum);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
