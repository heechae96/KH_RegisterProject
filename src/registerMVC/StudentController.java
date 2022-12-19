package registerMVC;

import java.util.ArrayList;
import java.util.List;

public class StudentController {

	private List<StudentModel> studentList;

	public StudentController() {
		studentList = new ArrayList<StudentModel>();
	}

	public List<StudentModel> getList(){
		return studentList;
	}

	public boolean addStudent(String[] info, SubjectModel model) {
		String name = info[0];
		String phone = info[1];
		StudentModel m = new StudentModel(name, phone, model);
		return studentList.add(m);
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

	public StudentModel updateSubject(String name, String phone, SubjectModel newSub) {
		for (int i = 0; i < studentList.size(); i++) {
			StudentModel checkModel = studentList.get(i);
			if ((name.equals(studentList.get(i).getName())) && phone.equals(studentList.get(i).getPhone())) {
				checkModel.setSubject(newSub);
				return checkModel;
			}
		}
		return null;
	}
	
	public boolean adminCheck(String[] admin) {
		if("admin".equals(admin[0]) && "1234".equals(admin[1])) {
			return true;
		}else {
			return false;
		}
	}

}
