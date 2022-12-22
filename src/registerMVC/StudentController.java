package registerMVC;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

	private List<StudentModel> studentList;

	public StudentController() {
		studentList = new ArrayList<StudentModel>();
	}

	public List<StudentModel> getList() {
		return studentList;
	}

	public int getSize() {
		return studentList.size();
	}

	public boolean addStudent(String[] info, SubjectModel model) {
		String name = info[0];
		String phone = info[1];
		// 수강신청 인원 +1
		int num = model.getRegisterNum();
		model.setRegisterNum(num + 1);
		StudentModel m = new StudentModel(name, phone, model);
		return studentList.add(m);
	}

	public StudentModel getStudent(String name, String phone) {
		for (int i = 0; i < studentList.size(); i++) {
			StudentModel checkModel = studentList.get(i);
			if ((name.equals(studentList.get(i).getName())) && (phone.equals(studentList.get(i).getPhone()))) {
				return checkModel;
			}
		}
		return null;
	}

	public void updateSubject(StudentModel stdModel, SubjectModel NewSubjectModel) {
		stdModel.setSubject(NewSubjectModel);
		// 새로 신청한 과목의 수강신청 인원 + 1
		int num = NewSubjectModel.getRegisterNum();
		NewSubjectModel.setRegisterNum(num + 1);
	}

	// 과목 수정시 수강신청 인원 -1 필요
	public void updateSubject(StudentModel model) {
		SubjectModel subjectModel = model.getSubject();
		int num = subjectModel.getRegisterNum();
		subjectModel.setRegisterNum(num - 1);
	}

	public boolean adminCheck(String[] admin) {
		if ("admin".equals(admin[0]) && "1234".equals(admin[1])) {
			return true;
		} else {
			return false;
		}
	}

}