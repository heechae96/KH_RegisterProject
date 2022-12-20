package registerMVC;

public class Run {

	public static void main(String[] args) {
		SubjectController subCon = new SubjectController();
		StudentController stdCon = new StudentController();
		RegisterView view = new RegisterView();

		OUTER: while (true) {
			int choice = view.printMenu();

			switch (choice) {
			case 1:
				view.showSubject(subCon.getList());
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
				StudentModel model = stdCon.getSubject(info[0], info[1]); // 해당 정보와 일치하는 모델 가져옴
				// 변경 or 유지
				EXIT: while (true) {
					String msg = view.showAddSubject(model); // 해당 정보와 일치하는 모델 출력
					switch (msg) {
					case "yes":
						int num = view.modifyNum();
						SubjectModel subjectModel = subCon.selcetSubject(num); // 입력 받은 넘버코드와 일치하는 과목이 담김
						stdCon.updateSubject(model, subjectModel);
						view.displaySuccess("수강신청 내역을 변경했습니다");
						break EXIT;
					case "no":
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
							view.checkAll(stdCon.getList(), subCon.getList());
							view.displaySuccess("모든 정보 조회 완료");
							break;
						case 4:
							view.exit("===== 관리자모드가 종료되었습니다!! =====");
							break INNER;
						default:
							view.exceptionMsg("1 ~ 4 사이의 번호를 입력해주세요!");
							break;
						}
					}
				} else {
					view.displayError("관리자 정보와 일치하지 않습니다");
				}
				break; // 안쓰면 관리자 정보가 일치하지 않는 경우 default문장까지 실행됨!!
			default:
				view.exceptionMsg("1 ~ 4 사이의 번호를 입력해주세요!");
				break;
			}

		}

	}

}