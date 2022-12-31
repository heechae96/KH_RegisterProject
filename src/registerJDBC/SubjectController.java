package registerJDBC;

import java.util.List;

public class SubjectController {

	/**
	 * 자동 과목 추가
	 * 
	 * @return int
	 */
	public int addAutoSubject() {
		SubjectDAO subDao = new SubjectDAO();
		int result = subDao.autoInsertSubject();
		return result;
	}

	/**
	 * 과목 추가
	 * 
	 * @param subject
	 * @return int
	 */
	public int addSubject(Subject subject) {
		SubjectDAO subDao = new SubjectDAO();
		int result = subDao.insertSubject(subject);
		return result;
	}

	/**
	 * 과목 삭제
	 * 
	 * @param codeNum
	 * @return int
	 */
	public int removeSubject(int codeNum) {
		SubjectDAO subDao = new SubjectDAO();
		int result = subDao.deleteSubject(codeNum);
		return result;
	}

	/**
	 * 모든 과목 조회
	 * 
	 * @return List<Subject>
	 */
	public List<Subject> findAll() {
		SubjectDAO subDao = new SubjectDAO();
		List<Subject> list = subDao.selectAll();
		return list;
	}

	/**
	 * 받아온 과목코드와 일치하는 과목을 조회
	 * 
	 * @param codeNum
	 * @return Subject
	 */
	public Subject findByCodeNum(int codeNum) {
		SubjectDAO subDao = new SubjectDAO();
		Subject subject = subDao.selectByCodeNum(codeNum);
		return subject;
	}

	/**
	 * 해당 과목코드가 존재하는지 확인
	 * @param codeNum
	 * @return int
	 */
	public int checkCodeNum(int codeNum) {
		SubjectDAO subDao = new SubjectDAO();
		int result = subDao.checkCodeNum(codeNum);
		return result;
	}

	/**
	 * 해당 과목의 수강신청 인원 + 1
	 * @param sub
	 * @return int
	 */
	public int plusSubject(Subject sub) {
		SubjectDAO subDao = new SubjectDAO();
		int result = subDao.plusRegisterNum(sub);
		return result;
	}

	/**
	 * 해당 과목의 수강신청 인원 - 1
	 * @param sub
	 * @return int
	 */
	public int minusSubject(Subject sub) {
		SubjectDAO subDao = new SubjectDAO();
		int result = subDao.minusRegisterNum(sub);
		return result;
	}
	
}
