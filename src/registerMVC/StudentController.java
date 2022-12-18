package registerMVC;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

	SubjectController subjectCon = SubjectController.getInstance();
	RegisterView view;

	private static StudentController INSTANCE;

	private StudentController() {
	}

	public static StudentController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new StudentController();
		}
		return INSTANCE;
	}

	private static List<StudentModel> studentList = new ArrayList<StudentModel>();

	public List<StudentModel> list() {
		return studentList;
	}

	public void addSubject(StudentModel m) {
		studentList.add(m);
	}

	public StudentModel getSubject(String name, String phone) {
		for (int i = 0; i < studentList.size(); i++) {
			StudentModel checkModel = studentList.get(i);
			if ((name.equals(studentList.get(i).getName())) && (phone.equals(studentList.get(i).getPhone()))) {
				return checkModel;
			}
		}
		return null;
	}

	public StudentModel updateSubject(String name, String phone, int code, SubjectModel newSub) {
		for (int i = 0; i < studentList.size(); i++) {
			StudentModel checkModel = studentList.get(i);
			if ((name.equals(studentList.get(i).getName())) && phone.equals(studentList.get(i).getPhone())) {
				checkModel.setCode(code);
				checkModel.setSubject(newSub);
				return checkModel;
			}
		}
		return null;
	}

	public void keepOrUpdate(String msg) {
		if ("yes".equalsIgnoreCase(msg)) {
			view = new RegisterView();
			view.modifySubject();
		} else if ("no".equalsIgnoreCase(msg)) {
			return;
		}
	}

}
