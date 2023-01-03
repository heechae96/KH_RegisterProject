package registerJDBC;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RegisterView {

	public int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 간단한 수강 신청 프로그램 =====");
		System.out.println("0. 관리자 권한");
		System.out.println("1. 개설 과목 조회");
		System.out.println("2. 수강 신청");
		System.out.println("3. 수강 내역 조회");
		System.out.println("4. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int num = sc.nextInt();
		return num;
	}

	public int subMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 관리자 페이지[주의!!] =====");
		System.out.println("0. 자동 과목 생성");
		System.out.println("1. 과목 생성");
		System.out.println("2. 과목 삭제");
		System.out.println("3. 과목 조회");
		System.out.println("4. 학생 조회");
		System.out.println("5. 나가기");
		System.out.print("메뉴 선택 : ");
		int num = sc.nextInt();
		return num;
	}

	public HashMap<String, String> checkUser() {
		HashMap<String, String> map = new HashMap<>();

		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();

		map.put("id", id);
		map.put("pw", pw);

		return map;
	}

	public Subject inputSubject() {
		Scanner sc = new Scanner(System.in);
		System.out.print("과목 이름 : ");
		String SubName = sc.nextLine();
		System.out.print("교수명 : ");
		String name = sc.next();
		System.out.print("최대수강인원: ");
		int maxNum = sc.nextInt();
		
		Subject subject = new Subject();
		subject.setSubjectName(SubName);
		subject.setName(name);
		subject.setCapacity(maxNum);

		return subject;
	}

	public int inputCodeNum() {
		Scanner sc = new Scanner(System.in);
		System.out.print("과목 코드 : ");
		int codeNum = sc.nextInt();
		return codeNum;
	}

	public void printAllSubject(List<Subject> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ". " + list.get(i));
			System.out.println("====================");
		}
	}

	public void printSubject(Subject subject) {
		System.out.println("과목명 : " + subject.getSubjectName());
		System.out.println("과목코드 : " + subject.getSubjectCode());
		System.out.println("교수명 : " + subject.getName());
		System.out.println("최대수강인원 : " + subject.getCapacity());
		System.out.println("신청수강인원 : " + subject.getRegisterNumber());
	}

	public void printAllStudent(List<Register> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + ". " + list.get(i));
			System.out.println("====================");
		}
	}
	
	public Student inputStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("비밀번호 : ");
		String pw = sc.next();
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("과목 코드 : ");
		int codeNum = sc.nextInt();

		Student student = new Student(id, pw, name, codeNum);
		return student;
	}

	public String inputChoice() {
		Scanner sc = new Scanner(System.in);
		System.out.println("수강신청 내역을 변경하고 싶으면 YES");
		System.out.println("수강신청 내역을 유지하고 싶으면 NO");
		System.out.print("YES OR NO : ");
		String choice = sc.next();
		return choice;
	}

	public int changeSubject() {
		Scanner sc = new Scanner(System.in);
		System.out.print("변경하실 과목의 과목 코드 : ");
		int codeNum = sc.nextInt();
		return codeNum;
	}

	public void displaySuccess(String msg) {
		System.out.println("[성공 메시지]: " + msg);
	}

	public void displayFail(String msg) {
		System.out.println("[실패 메시지]: " + msg);
	}

	public void printMsg(String msg) {
		System.out.println(msg);
	}
}
