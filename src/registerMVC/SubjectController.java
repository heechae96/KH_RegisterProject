package registerMVC;

import java.util.ArrayList;
import java.util.List;

public class SubjectController {

	private List<SubjectModel> subjectList;

	public SubjectController() {
		subjectList = new ArrayList<SubjectModel>();
	}
	
	public List<SubjectModel> getList(){
		return subjectList;
	}

	// 추가
	public void addSubject(SubjectModel sub) {
		subjectList.add(sub);
	}
	
	// 삭제
	public void removeSubject(int subjectCode) {
		for (int i = 0; i < subjectList.size(); i++) {
			int checkNum = subjectList.get(i).getSubjectCode();
			if (subjectCode == checkNum) {
				subjectList.remove(i);
				break;
			}
		}
	}

	// 조회
	public SubjectModel selcetSubject(int subjectCode) {
		for (int i = 0; i < subjectList.size(); i++) {
			int checkNum = subjectList.get(i).getSubjectCode();
			SubjectModel sm = subjectList.get(i);
			if (subjectCode == checkNum) {
				return sm;
			}
		}
		return null;
	}

}
