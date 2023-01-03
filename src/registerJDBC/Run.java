package registerJDBC;

import java.util.HashMap;
import java.util.List;

public class Run {

	public static void main(String[] args) {

		RegisterView view = new RegisterView();
		SubjectController subCon = new SubjectController();
		StudentController stdCon = new StudentController();
		Subject subject = null;
		Student student = null;

		List<Subject> list = null;
		List<Student> list2 = null;
		HashMap<String, String> map = null;

		int result = -1;
		int codeNum = -1;
		int maxNum = -1;
		int currNum = -1;

		OUTER: while (true) {
			int mainNum = view.mainMenu();
			switch (mainNum) {
			case 0:
				map = view.checkUser();
				String adminId = map.get("id");
				String adminPw = map.get("pw");
				// 관리자 로그인 성공
				if (adminId.equals("admin") && adminPw.equals("admin")) {
					INNER: while (true) {
						int subNum = view.subMenu();
						switch (subNum) {
						case 0:
							// 자동 과목 생성
							result = subCon.addAutoSubject();
							if (result > 0) {
								view.displaySuccess("자동 과목 추가 성공!!");
							} else {
								view.displayFail("자동 과목 추가 실패..");
							}
							break;
						case 1:
							// 과목 생성
							subject = view.inputSubject();
							result = subCon.addSubject(subject);
							if (result > 0) {
								view.displaySuccess("과목 추가 성공!!");
							} else {
								view.displayFail("과목 추가 실패..");
							}
							break;
						case 2:
							// 개설 과목 노출 필요
							list = subCon.findAll();
							view.printAllSubject(list);
							// 과목 삭제
							codeNum = view.inputCodeNum();
							result = subCon.removeSubject(codeNum);
							if (result > 0) {
								view.displaySuccess("과목 삭제 성공!!");
							} else {
								view.displayFail("과목 삭제 실패..");
							}
							break;
						case 3:
							// 과목 조회
							list = subCon.findAll();
							if (list.isEmpty()) {
								view.displayFail("개설된 과목이 없습니다..");
							} else {
								view.printAllSubject(list);
								view.displaySuccess("개설 과목 조회 완료!!");
							}
							break;
						case 4:
							// 학생 조회
							// 조인을 해서 가져와
							list2 = stdCon.findAll();
							if (list2.isEmpty()) {
								view.displayFail("수강 신청한 학생이 없습니다..");
							} else {
								view.printAllStudent(list2);
								view.displaySuccess("모든 학생 조회 완료!!");
							}
							break;
						case 5:
							// 관리자 모드 나가기
							view.displaySuccess("관리자 모드 종료");
							break INNER;
						default:
							view.displayFail("0 ~ 5 사이의 수를 입력하세요");
							break;
						}
					}
					break; // 주의!! 이거 안쓰면 case 1로 넘어가버린다!!
				} else {
					// 관리자 로그인 실패
					view.displayFail("관리자 계정이 아닙니다");
					break;
				}
			case 1:
				// 개설 과목 조회
				list = subCon.findAll();
				if (list.isEmpty()) {
					view.displayFail("개설된 과목이 없습니다..");
				} else {
					view.printAllSubject(list);
					view.displaySuccess("개설 과목 조회 완료!!");
				}
				break;
			case 2:
				OUT: while (true) {
					// 개설 과목 노출 필요
					list = subCon.findAll();
					if (list.isEmpty()) {
						view.displayFail("개설된 과목이 없습니다. 관리자에게 문의하세요!");
						break OUT;
					} else { // 수강 신청
						view.printAllSubject(list);
						view.printMsg("=============== 위의 과목 코드를 확인해주세요 ===============");
						student = view.inputStudent();
						// 중복되는 아이디인지 확인 작업
						result = stdCon.checkDoubleId(student.getStudentId());
						if (result <= 0) {
							int checkNum = student.getSubjectCode();
							codeNum = student.getSubjectCode();
							maxNum = subCon.checkRegisterMaxNum(codeNum);
							currNum = subCon.checkRegisterNum(codeNum);
							
							// 없는 과목코드가 들어가지 못하도록 확인 작업
							if (subCon.checkCodeNum(checkNum) > 0) {
								subject = subCon.findByCodeNum(checkNum);

								// 수강 가능한 인원을 초과했는지 확인 작업
								if (maxNum > currNum) {
									// 수강 신청 인원 + 1
									subCon.plusSubject(subject);
									result = stdCon.addStudent(student);
									if (result > 0) {
										view.displaySuccess("수강 신청 완료!!");
										break OUT;
									}
								} else {
									view.displayFail("수강 인원을 초과했습니다. 다른 과목을 신청해주세요..");
								}
							} else {
								view.displayFail("수강신청을 실패하였습니다. 과목코드를 다시 확인하세요..");
							}
						} else {
							view.displayFail("중복되는 ID입니다. 새로운 ID를 입력해주세요..");
						}
					}
				}
				break;
			case 3:
				map = view.checkUser();
				String userId = map.get("id");
				String userPw = map.get("pw");
				student = stdCon.notEmptyStudent(userId, userPw);
				if (student != null) {
					view.displaySuccess("해당 회원정보가 일치합니다!!");
					subject = subCon.findByCodeNum(student.getSubjectCode());
					view.printSubject(subject);
					// 내역을 변경할지 안할지 정해야함
					EXIT: while (true) {
						String choice = view.inputChoice();
						if (choice.equalsIgnoreCase("yes")) {
							// 변경하는 코드!
							list = subCon.findAll();
							view.printAllSubject(list);
							view.printMsg("=============== 위의 과목 코드를 확인해주세요 ===============");
							codeNum = view.changeSubject();

							// 없는 과목코드가 들어가지 못하도록 확인 작업
							if (subCon.checkCodeNum(codeNum) > 0) {
								maxNum = subCon.checkRegisterMaxNum(codeNum);
								currNum = subCon.checkRegisterNum(codeNum);

								// 수강 가능한 인원을 초과했는지 확인 작업
								if (subCon.checkCodeNum(codeNum) > 0) {
									if (maxNum > currNum) {
										result = stdCon.changeCodeNum(codeNum, student);
										if (result > 0) {
											// 기존과목 수강 신청 인원 - 1
											subCon.minusSubject(subject);
											// 새로운 과목 수강 신청 인원 + 1
											subject = subCon.findByCodeNum(codeNum);
											subCon.plusSubject(subject);
											view.displaySuccess("수강신청 내역을 변경하였습니다!!");
											break EXIT;
										}
									} else {
										view.displayFail("수강 인원을 초과했습니다. 다른 과목을 신청해주세요..");
									}
								}
							} else {
								view.displayFail("수강신청 내역을 변경하지 못했습니다. 과목코드를 다시 확인하세요..");
								break;
							}
						} else if (choice.equalsIgnoreCase("no")) {
							view.displaySuccess("그대로 수강신청 내역을 유지합니다");
							break EXIT;
						} else {
							view.displayFail("YES 또는 NO를 입력하세요..");
							break;
						}
					} // end - if
				} else {
					view.displayFail("해당 회원정보가 존재하지 않습니다..");
				}
				break;
			case 4:
				// 프로그램 종료
				view.displaySuccess("프로그램 종료");
				break OUTER;
			default:
				view.displayFail("0 ~ 4 사이의 수를 입력하세요");
				break;
			}

		}

	}

}
