package register;

public class Student {

	private String name; 		// 이름
	private String phone; 		// 휴대폰 번호
	private Subject subject; 	// 수강 신청 과목
	private int code; 			// 수강신청한 과목 코드(인원 제한에 사용)

	// 생성자
	public Student() {

	}

	public Student(String name, String phone, Subject subject, int code) {
		this.name = name;
		this.phone = phone;
		this.subject = subject;
		this.code = code;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	// toString()
	public String toString() {
		return "이름=" + name + ", 휴대폰번호=" + phone + ", 확인코드=" + code + "\n" + subject;
	}

}
