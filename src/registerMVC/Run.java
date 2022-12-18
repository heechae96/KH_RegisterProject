package registerMVC;

public class Run {

	public static void main(String[] args) {
		SubjectController subCon = SubjectController.getInstance();
		StudentController stdCon = StudentController.getInstance();
		RegisterView view = new RegisterView();

		EXIT: while (true) {
			int choice = view.printMenu();

			switch (choice) {
			case 1:
				view.showSubject();
				break;
			case 2:
				stdCon.addSubject(view.registerSubject());
				break;
			case 3:
				stdCon.keepOrUpdate(view.showAddSubject());
				break;
			case 4:
				view.exit();
				break EXIT;
			case 1111: // 관리자만 사용하도록!
				subCon.addSubject(view.inputSubject());
				break;
			case 2222: // 관리자만 사용하도록!
				subCon.removeSubject(view.deleteSubject());
				break;
			case 3333: // 관리자만 사용하도록!
				view.checkAll();
				break;
			default:
				view.exceptionMsg("1 ~ 4 사이의 번호를 입력해주세요!");
				break;
			}

		}

	}

}
