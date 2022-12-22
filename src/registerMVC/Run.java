package registerMVC;

import java.util.List;

public class Run {

	public static void main(String[] args) {
		SubjectController subCon = new SubjectController();
		StudentController stdCon = new StudentController();
		RegisterView view = new RegisterView();

		OUTER: while (true) {
			int choice = view.printMenu();

			switch (choice) {
			case 1:
				List<SubjectModel> list = subCon.getList();
				if (list.size() == 0) {
					view.displayError("개설과목이 없습니다. 관리자에게 문의하세요!");
				} else {
					view.showSubject(list);
				}
				break;
			case 2:
				// 개설과목을 보여줘야 고를 수 있음
				view.showSubject(subCon.getList());
				String[] strArr = view.registerSubject();
				SubjectModel m = subCon.selcetSubject(view.registerSubCode());
				stdCon.addStudent(strArr, m);
				view.displaySuccess("수강신청을 성공했습니다");
				break;
			case 3:
				// 수강 내역 조회
				String[] info = view.registerSubject(); // 등록할때 작성한 아이디, 비밀번호가 담김
				StudentModel model = stdCon.getStudent(info[0], info[1]); // 해당 정보와 일치하는 모델 가져옴
				// 변경 or 유지
				EXIT: while (true) {
					String msg = view.showAddSubject(model); // 해당 정보와 일치하는 모델 출력
					switch (msg) {
					case "yes":
					case "YES":
						// 새로운 과목을 입력받기전 이전 과목에 대한 정보를 가져와서
						// 수강 신청 인원을 줄여준다
						stdCon.updateSubject(model);
						// 개설 과목을 보여준다
						view.showSubject(subCon.getList());
						// 변경
						int num = view.modifyNum();
						SubjectModel subjectModel = subCon.selcetSubject(num); // 입력 받은 넘버코드와 일치하는 과목이 담김
						stdCon.updateSubject(model, subjectModel);
						view.displaySuccess("수강신청 내역을 변경했습니다");
						break EXIT;
					case "no":
					case "NO":
						view.displaySuccess("수강신청 내역을 유지했습니다");
						break EXIT;
					default:
						view.displayError("YES 또는 NO를 선택해주세요");
						break;
					}
				}
				break; // 안쓰면 프로그램 종료됨!!
			case 4:
				view.exit("===== 프로그램이 종료되었습니다!! =====");
				break OUTER;
			case 0: // 관리자의 정보와 일치해야 볼 수 있도록
				String[] admin = view.loginView();
				boolean bool = stdCon.adminCheck(admin);
				if (bool) {
					view.displaySuccess("관리자 정보와 일치합니다");
					INNER: while (true) {
						int num = view.adminMenu();
						switch (num) {
						case 0:
							subCon.tmpMethod();
							view.displaySuccess("자동 과목 생성 완료");
							break;
						case 1:
							subCon.addSubject(view.inputSubject());
							view.displaySuccess("과목 생성 완료");
							break;
						case 2:
							subCon.removeSubject(view.deleteSubject());
							view.displaySuccess("과목 삭제 완료");
							break;
						case 3:
							if (stdCon.getSize() == 0) {
								view.displayError("수강 신청한 학생이 없습니다");
							} else {
								view.checkStudent(stdCon.getList());
							}

							if (subCon.getSize() == 0) {
								view.displayError("개설한 과목이 없습니다");
							} else {
								view.showSubject(subCon.getList());
							}
							break;
						case 4:
							view.exit("===== 관리자모드가 종료되었습니다!! =====");
							break INNER;
						default:
							view.displayError("0 ~ 4 사이의 번호를 입력해주세요!");
							break;
						}
					}
				} else {
					view.displayError("관리자 정보와 일치하지 않습니다");
				}
				break; // 안쓰면 관리자 정보가 일치하지 않는 경우 default문장까지 실행됨!!
			default:
				view.displayError("1 ~ 4 사이의 번호를 입력해주세요!");
				break;
			}

		}

	}

}