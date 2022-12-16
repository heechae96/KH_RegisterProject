package register;

import java.util.Scanner;

public class RegisterFunction {

	Student[] students;
//	static Student[] students;

	public RegisterFunction() {
		students = new Student[21];
	}

	int PERSON = 0; // 0~20
//	static int PERSON = 0; // 0~20

	Subject[] subjects = { new Subject("Java", 1, "김이준", 3, 0), new Subject("Python", 2, "이서준", 3, 0),
			new Subject("C언어", 3, "홍하준", 3, 0), new Subject("프론트 개발자 양성과정", 4, "박도윤", 3, 0),
			new Subject("Springframework & 클라우드 융합 웹 개발자 양성과정", 5, "민봉식", 3, 0),
			new Subject("클라우드 컴퓨팅 엔지니어 양성과정", 6, "김시우", 3, 0),
			new Subject("빅데이터 기반 금융 솔루션 UI개발자 양성과정", 7, "서은우", 3, 0) };

	public void checkPeople() {
		for (int i = 0; i < PERSON; i++) {
			System.out.println((i + 1) + "번: " + students[i]);
		}

		System.out.println("-------------------------------------------------------------------");

		for (int i = 0; i < subjects.length; i++) {
			System.out.println(subjects[i]);
		}

	}

	public int printMenu() {
		System.out.println("====== 간단한 수강 신청 프로그램 ======");
		System.out.println("1. 개설 과목 조회");
		System.out.println("2. 수강 신청");
		System.out.println("3. 수강 내역 조회");
		System.out.println("4. 프로그램 종료");
		System.out.print("번호 입력: ");

		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();

		return input;
	}

	public void showSubject() {
		System.out.println("==================================== 개설 과목 ====================================");

		for (int i = 0; i < subjects.length; i++) {
			System.out.println("과목명: " + subjects[i].getSubjectName());
			System.out.println("과목코드: " + subjects[i].getSubjectCode());
			System.out.println("교수명: " + subjects[i].getName());
			System.out.println("제한: " + subjects[i].getCapacity() + ", 신청인원: " + subjects[i].getRegisterNum());
			System.out.println("===================================================================================");
		}
	}

	public void findUpdate(int i, int code) {
		// 새로 추가한 과목은 신청인원 +1
		students[i].setSubject(subjects[code - 1]);
		int check = subjects[code - 1].getRegisterNum();
		subjects[code - 1].setRegisterNum(check + 1);
		// 이전에 추가했던 과목은 최소하므로 신청인원 -1
		int prev = students[i].getCode();
		int prevNum = subjects[code - 1].getRegisterNum();
		subjects[prev - 1].setRegisterNum(prevNum - 1);
		// 변경했으면 신청한 코드로 변경
		students[i].setCode(code);
		// findUpdate를 빠져나감
		return;
	}

	public void notFindUpdate(String name, String phone, int code) {
		students[PERSON] = new Student(name, phone, subjects[code - 1], code);
		int check = subjects[code - 1].getRegisterNum();
		subjects[code - 1].setRegisterNum(check + 1);
		PERSON++;
		return; // notFindUpdate를 빠져나감
	}

	public void addSubject() {
		System.out.println("==================================== 수강 신청 ====================================");

		for (int i = 0; i < subjects.length; i++) {
			System.out.println("과목코드: " + subjects[i].getSubjectCode() + ", 과목명: " + subjects[i].getSubjectName()
					+ ", 교수명: " + subjects[i].getName() + ", 제한: " + subjects[i].getCapacity() + ", 신청인원: "
					+ subjects[i].getRegisterNum());
		}

		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요: ");
		String name = sc.next();
		System.out.print("휴대폰 번호를 입력하세요: ");
		String phone = sc.next();
		System.out.print("선택할 강좌의 과목코드를 입력하세요: ");
		int code = sc.nextInt();

		// 일치하는 정보가 존재하는 경우
		// 새로운 객체를 만들지 않고, 기본 객체를 변경
		for (int i = 0; i < PERSON; i++) {
			String checkName = students[i].getName();
			String checkPhone = students[i].getPhone();

			if (checkName.equals(name) && checkPhone.equals(phone)) {
				findUpdate(i, code);
				return; // addSubject()를 빠져나감
			}
		}

		// 여기까지 도달하면 일치하는 정보가 없는것
		// 새로운 객체를 만들어준다
		notFindUpdate(name, phone, code);

	}

	public void showAddSubject() {
		System.out.println("==================================== 수강 신청 내역 ====================================");

		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요: ");
		String name = sc.next();
		System.out.print("휴대폰 번호를 입력하세요: ");
		String phone = sc.next();

		// NullPointException 발생이유
		// students가 크기 21인 배열로 선언 및 할당만 되어있다.
		// 초기화 해서 채워줘야 하는데 채워주지 않은 상태로 조회하려고 해서 예외!

		for (int i = 0; i < students.length; i++) {
			try {
				String name2 = students[i].getName();
				String phone2 = students[i].getPhone();

				if (name.equals(name2) && phone.equals(phone2)) {
					System.out.println(students[i]);
					System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
					System.out.println("수강 내역을 변경하시고 싶으시면 새롭게 수강 신청 해주세요");
					System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
					break; // 안빠져나가면 NullPointException 발생!
							// 앞에서 먼저 생성된것만 출력됨
				}
			} catch (NullPointerException e) { // 여기까지 도달하면 일치하는 정보가 없는것
				System.out.println("일치하는 정보가 없습니다!!!");
				System.out.println("다시 확인하고 조회하세요!!!");
				break; // 다시 확인하라는 메시지가 계속 출력되는걸 막음
			}

		}
	}

	public void exit() {
		System.out.println("====== 프로그램이 종료되었습니다!!! ======");
	}

	public void exceptionMsg() {
		System.out.println("====== 1~4 사이의 숫자를 입력해주세요!!! ======\n");
	}

}
