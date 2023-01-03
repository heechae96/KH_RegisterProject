package registerJDBC;

import java.util.List;

public class SubjectController {

	private SubjectService subService;

	public SubjectController() {
		subService = new SubjectService();
	}

	/**
	 * 과목 추가
	 * 
	 * @param subject
	 * @return int
	 */
	public int addSubject(Subject subject) {
		int result = subService.insertSubject(subject);
		return result;
	}

	/**
	 * 과목 삭제
	 * 
	 * @param codeNum
	 * @return int
	 */
	public int removeSubject(int codeNum) {
		int result = subService.deleteSubject(codeNum);
		return result;
	}

	/**
	 * 모든 과목 조회
	 * 
	 * @return List<Subject>
	 */
	public List<Subject> findAll() {
		List<Subject> list = subService.selectAll();
		return list;
	}

	/**
	 * 받아온 과목코드와 일치하는 과목을 조회
	 * 
	 * @param codeNum
	 * @return Subject
	 */
	public Subject findByCodeNum(int codeNum) {
		Subject subject = subService.selectByCodeNum(codeNum);
		return subject;
	}

	/**
	 * 해당 과목코드가 존재하는지 확인
	 * 
	 * @param codeNum
	 * @return int
	 */
	public int checkCodeNum(int codeNum) {
		int result = subService.checkCodeNum(codeNum);
		return result;
	}

	/**
	 * 해당 과목의 수강신청 인원 + 1
	 * 
	 * @param sub
	 * @return int
	 */
	public int plusSubject(Subject sub) {
		int result = subService.plusRegisterNum(sub);
		return result;
	}

	/**
	 * 해당 과목의 수강신청 인원 - 1
	 * 
	 * @param sub
	 * @return int
	 */
	public int minusSubject(Subject sub) {
		int result = subService.minusRegisterNum(sub);
		return result;
	}

	/**
	 * 해당 과목의 신청수강인원을 조회
	 * 
	 * @return int
	 */
	public int checkRegisterNum(int codeNum) {
		int num = subService.getCurrNum(codeNum);
		return num;
	}

	/**
	 * 해당 과목의 최대수강인원을 조회
	 * 
	 * @return int
	 */
	public int checkRegisterMaxNum(int codeNum) {
		int num = subService.getMaxNum(codeNum);
		return num;
	}

}
