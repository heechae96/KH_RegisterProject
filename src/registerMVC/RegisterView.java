package registerMVC;

import java.util.List;
import java.util.Scanner;

public class RegisterView {

	// 관리자만 접근
	public String[] loginView() {
		Scanner sc = new Scanner(System.in);
		System.out.print("관리자 아이디 입력 >> ");
		String id = sc.next();
		System.out.print("관리자 비밀번호 입력 >> ");
		String pw = sc.next();

		String[] admin = { id, pw };
		return admin;
	}

	public int adminMenu() {
		// 관리자만 알 수 있도록 화면에는 노출x
		System.out.println("0. 자동 과목 생성");
		System.out.println("1. 과목 생성");
		System.out.println("2. 과목 삭제");
		System.out.println("3. 모든 정보 조회(과목, 학생)");
		System.out.println("4. 나가기");
		System.out.print("번호 입력: ");

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		return num;
	}

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

	public void checkAll(List<StudentModel> stdM, List<SubjectModel> subM) {
		for (int i = 0; i < stdM.size(); i++) {
			System.out.println((i + 1) + "번: " + stdM.get(i));
		}

		System.out.println("========================================================================");

		for (int i = 0; i < subM.size(); i++) {
			System.out.println(subM.get(i));
		}
	}

	// 이용자가 접근
	public int printMenu() {
		System.out.println("====== 간단한 수강 신청 프로그램 ======");
		System.out.println("1. 개설 과목 조회");
		System.out.println("2. 수강 신청");
		System.out.println("3. 수강 내역 조회");
		System.out.println("4. 프로그램 종료");
		System.out.println("0. 관리자 권한");
		System.out.print("번호 입력: ");

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		return input;
	}

	// 개설 과목 조회
	public void showSubject(List<SubjectModel> subM) {
		System.out.println("==================================== 개설 과목 ====================================");
		for (int i = 0; i < subM.size(); i++) {
			System.out.println(subM.get(i));
		}
	}

	// 수강신청
	public String[] registerSubject() {
		System.out.println("==================================== 수강 신청 ====================================");

		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요: ");
		String name = sc.next();
		System.out.print("휴대폰 번호를 입력하세요: ");
		String phone = sc.next();

		String[] info = { name, phone };
		return info;
	}

	public int registerSubCode() {
		Scanner sc = new Scanner(System.in);
		System.out.print("선택할 강좌의 과목코드를 입력하세요: ");
		int code = sc.nextInt();

		return code;
	}

	public int modifyNum() {
		System.out.print("변경할 강좌의 과목코드를 입력하세요: ");
		Scanner sc = new Scanner(System.in);
		int code = sc.nextInt();

		return code;
	}

	public String showAddSubject(StudentModel model) {
		System.out.println("==================================== 수강 신청 내역 ====================================");
		System.out.println(model);
		System.out.println("========================================================================");
		System.out.println("수강 신청 내역을 변경 하고 싶으시면 YES");
		System.out.println("수강 신청 내역을 유지 하고 싶으시면 NO");
		System.out.print("YES OR NO : ");

		Scanner sc = new Scanner(System.in);
		String msg = sc.next();

		return msg;
	}

	public void exit(String msg) {
		System.out.println(msg);
	}

	public void exceptionMsg(String msg) {
		System.out.println("[예외 발생] " + msg);
	}

	public void displaySuccess(String message) {
		System.out.println("[처리 결과] : " + message);
	}

	public void displayError(String message) {
		System.out.println("[오류 발생] : " + message);
	}
}