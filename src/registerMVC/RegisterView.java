package registerMVC;

import java.util.Scanner;

public class RegisterView {

	SubjectController subController = new SubjectController();
	StudentController stdController = new StudentController();

	// 관리자만 접근
	public SubjectModel inputSubject() {
		Scanner sc = new Scanner(System.in);

		System.out.print("과목이름: ");
		String subjectName = sc.nextLine();
		System.out.print("과목코드: ");
		int subjectCode = sc.nextInt();
		System.out.print("교수명: ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("최대 수강 인원: ");
		int capacity = sc.nextInt();
		System.out.print("신청 수강 인원: ");
		int registerNum = sc.nextInt();

		return new SubjectModel(subjectName, subjectCode, name, capacity, registerNum);
	}

	public int deleteSubject() {
		Scanner sc = new Scanner(System.in);

		System.out.print("삭제할 과목의 과목코드를 입력하세요 : ");
		int num = sc.nextInt();

		return num;
	}
	
	public void checkAll() {
		for (int i = 0; i < stdController.studenetList.size(); i++) {
			System.out.println((i + 1) + "번: " + stdController.studenetList.get(i));
		}

		System.out.println("-------------------------------------------------------------------");

		for (int i = 0; i < subController.subjectList.size(); i++) {
			System.out.println(subController.subjectList.get(i));
		}

	}

	// 이용자가 접근
	public int printMenu() {
		System.out.println("====== 간단한 수강 신청 프로그램 ======");
		System.out.println("1. 개설 과목 조회");
		System.out.println("2. 수강 신청");
		System.out.println("3. 수강 내역 조회");
		System.out.println("4. 프로그램 종료");
		// 관리자만 알 수 있도록 화면에는 노출x
		// System.out.println("1111. 과목 생성");
		// System.out.println("2222. 과목 삭제");
		// System.out.println("3333. 모든 정보 조회(과목, 학생)");
		System.out.print("번호 입력: ");

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		return input;
	}

	public void showSubject() {
		System.out.println("==================================== 개설 과목 ====================================");
		for (int i = 0; i < subController.subjectList.size(); i++) {
			System.out.println("과목명: " + subController.subjectList.get(i).getSubjectName());
			System.out.println("과목코드: " + subController.subjectList.get(i).getSubjectCode());
			System.out.println("교수명: " + subController.subjectList.get(i).getName());
			System.out.println("제한: " + subController.subjectList.get(i).getCapacity() + ", 신청인원: "
					+ subController.subjectList.get(i).getRegisterNum());
			System.out.println("===================================================================================");
		}
	}

	public StudentModel registerSubject() {
		System.out.println("==================================== 수강 신청 ====================================");

		for (int i = 0; i < subController.subjectList.size(); i++) {
			System.out.println("과목코드: " + subController.subjectList.get(i).getSubjectCode() + ", 과목명: "
					+ subController.subjectList.get(i).getSubjectName() + ", 교수명: "
					+ subController.subjectList.get(i).getName() + ", 제한: "
					+ subController.subjectList.get(i).getCapacity() + ", 신청인원: "
					+ subController.subjectList.get(i).getRegisterNum());
		}

		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요: ");
		String name = sc.next();
		System.out.print("휴대폰 번호를 입력하세요: ");
		String phone = sc.next();
		System.out.print("선택할 강좌의 과목코드를 입력하세요: ");
		int code = sc.nextInt();

		SubjectModel subject = stdController.getSubject(code);

		return new StudentModel(name, phone, subject, code);

	}

	public void modifySubject() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요: ");
		String name = sc.next();
		System.out.print("휴대폰 번호를 입력하세요: ");
		String phone = sc.next();

		StudentModel checkStuedent = stdController.getSubject(name, phone);
		System.out.println("================================== 수강 신청 내역 ==================================");
		System.out.println(checkStuedent.getSubject());

		System.out.println("================================== 개설 과목 ==================================");
		for (int i = 0; i < subController.subjectList.size(); i++) {
			System.out.println("과목코드: " + subController.subjectList.get(i).getSubjectCode() + ", 과목명: "
					+ subController.subjectList.get(i).getSubjectName() + ", 교수명: "
					+ subController.subjectList.get(i).getName() + ", 제한: "
					+ subController.subjectList.get(i).getCapacity() + ", 신청인원: "
					+ subController.subjectList.get(i).getRegisterNum());
		}

		System.out.print("변경할 강좌의 과목코드를 입력하세요: ");
		int code = sc.nextInt();
		SubjectModel subject = stdController.getSubject(code);
		stdController.updateSubject(name, phone, code, subject);
	}

	public String showAddSubject() {
		System.out.println("==================================== 수강 신청 내역 ====================================");

		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요: ");
		String name = sc.next();
		System.out.print("휴대폰 번호를 입력하세요: ");
		String phone = sc.next();
		
		StudentModel checkStuedent = stdController.getSubject(name, phone);
		System.out.println(checkStuedent.getSubject());
		
		System.out.println("========================================================================");
		System.out.println("수강 신청 내역을 변경 하고 싶으시면 YES");
		System.out.println("수강 신청 내역을 유지 하고 싶으시면 NO");
		String msg = sc.next();
		
		return msg;
	}
	
	public void exit() {
		System.out.println("====== 프로그램이 종료되었습니다!!! ======");
	}

	public void exceptionMsg(String msg) {
		System.out.println("");
	}

}
