package registerJDBC;

import java.util.List;

public class StudentController {

	private StudentService stdService;

	public StudentController() {
		stdService = new StudentService();
	}

	/**
	 * 학생 추가
	 *
	 * @param student
	 * @return int
	 */
	public int addStudent(Student student) {
		int result = stdService.insertStudent(student);
		return result;
	}

	/**
	 * 모든 학생 조회
	 *
	 * @return List<Student>
	 */
	public List<Register> findAll() {
		List<Register> list = stdService.selectAll();
		return list;
	}

	/**
	 * 조회하는 학생의 정보가 존재하는지 확인
	 *
	 * @param id
	 * @return boolean
	 */
	public Student notEmptyStudent(String id, String pw) {
		Student student = stdService.selectByInfo(id, pw);
		return student;
	}

	/**
	 * 과목 코드 변경
	 *
	 * @param codeNum
	 * @param std
	 * @return int
	 */
	public int changeCodeNum(int codeNum, Student std) {
		int result = stdService.updateCodeNum(codeNum, std);
		return result;
	}

	/**
	 * 중복되는 ID인지 확인
	 * 
	 * @param id
	 * @return
	 */
	public int checkDoubleId(String id) {
		int result = stdService.checkDupId(id);
		return result;
	}

}
