package registerJDBC;

import java.util.List;

public class StudentController {

	/**
	 * 학생 추가
	 *
	 * @param student
	 * @return int
	 */
	public int addStudent(Student student) {
		StudentDAO stdDAO = new StudentDAO();
		int result = stdDAO.insertStudent(student);
		return result;
	}

	/**
	 * 모든 학생 조회
	 *
	 * @return List<Student>
	 */
	public List<Student> findAll() {
		StudentDAO stdDao = new StudentDAO();
		List<Student> list = stdDao.selectAll();
		return list;
	}

	/**
	 * 조회하는 학생의 아이디가 존재하는지 확인
	 *
	 * @param id
	 * @return boolean
	 */
	public Student notEmptyStudent(String id) {
		StudentDAO stdDao = new StudentDAO();
		Student student = stdDao.selectById(id);
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
		StudentDAO stdDao = new StudentDAO();
		int result = stdDao.updateCodeNum(codeNum, std);
		return result;
	}

}
