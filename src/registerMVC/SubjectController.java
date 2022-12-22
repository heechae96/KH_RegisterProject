package registerMVC;

import java.util.ArrayList;
import java.util.List;

public class SubjectController {

	private List<SubjectModel> subjectList;

	public SubjectController() {
		subjectList = new ArrayList<SubjectModel>();
	}

	public List<SubjectModel> getList() {
		return subjectList;
	}

	public int getSize() {
		return subjectList.size();
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

	// 편의를 위해 잠시 생성해둔것
	public void tmpMethod() {
		subjectList.add(new SubjectModel("Java", 1, "김이준", 3, 0));
		subjectList.add(new SubjectModel("Python", 2, "이서준", 3, 0));
		subjectList.add(new SubjectModel("C언어", 3, "홍하준", 3, 0));
		subjectList.add(new SubjectModel("프론트 개발자 양성과정", 4, "박도윤", 3, 0));
		subjectList.add(new SubjectModel("Springframework & 클라우드 융합 웹 개발자 양성과정", 5, "민봉식", 3, 0));
		subjectList.add(new SubjectModel("클라우드 컴퓨팅 엔지니어 양성과정", 6, "김시우", 3, 0));
		subjectList.add(new SubjectModel("빅데이터 기반 금융 솔루션 UI개발자 양성과정", 7, "서은우", 3, 0));
	}
}