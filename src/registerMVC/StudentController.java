package registerMVC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentController {

	SubjectController subjectCon;
	List<StudentModel> studenetList;
	RegisterView view;

	public StudentController() {
		studenetList = new ArrayList<StudentModel>();
	}

	public void addSubject(StudentModel m) {
		studenetList.add(m);
	}

	public SubjectModel getSubject(int code) {
		for (int i = 0; i < studenetList.size(); i++) {
			SubjectModel checkModel = subjectCon.subjectList.get(i);
			if (code == studenetList.get(i).getCode()) {
				return checkModel;
			}
		}
		return null;
	}
	
	public StudentModel getSubject(String name, String phone) {
		for (int i = 0; i < studenetList.size(); i++) {
			StudentModel checkModel = studenetList.get(i);
			if ((name == studenetList.get(i).getName()) && (phone == studenetList.get(i).getPhone())) {
				return checkModel;
			}
		}
		return null;
	}
	
	public StudentModel updateSubject(String name, String phone, int code, SubjectModel newSub) {
		for (int i = 0; i < studenetList.size(); i++) {
			StudentModel checkModel = studenetList.get(i);
			if ((name == studenetList.get(i).getName()) && (phone == studenetList.get(i).getPhone())) {
				checkModel.setCode(code);
				checkModel.setSubject(newSub);
				return checkModel;
			}
		}
		return null;
	}
	
	public void keepOrUpdate(String msg) {
		if("yes".equalsIgnoreCase(msg)) {
			view.modifySubject();
		} else if("no".equalsIgnoreCase(msg)) {
			return;
		}
	}

}
