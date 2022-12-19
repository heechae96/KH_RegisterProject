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
				break;
//			case 3:
//				view.keepOrUpdate(view.showAddSubject());
//				break;
			case 4:
				view.exit("===== 프로그램이 종료되었습니다!! =====");
				break OUTER;
			case 0: // 관리자의 정보와 일치해야 볼 수 있도록
				String[] admin = view.loginView();
				boolean bool = stdCon.adminCheck(admin);
				if (bool == true) {
					view.displaySuccess("관리자 정보와 일치합니다");
					INNER:
					while(true) {
						int num = view.adminMenu();
						switch(num) {
						case 1:
							subCon.addSubject(view.inputSubject());
							break;
						case 2:
							subCon.removeSubject(view.deleteSubject());
							break;
						case 3:
							view.checkAll(stdCon.getList(), subCon.getList());
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
				break;
			default:
				view.exceptionMsg("1 ~ 4 사이의 번호를 입력해주세요!");
				break;
			}

		}

	}

}
