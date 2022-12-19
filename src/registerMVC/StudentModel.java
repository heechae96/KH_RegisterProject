package registerMVC;

public class StudentModel {

	private String name; 				// 이름
	private String phone; 				// 휴대폰 번호
	private SubjectModel subject; 		// 수강 신청 과목

	// 생성자
	public StudentModel() {

	}

	public StudentModel(String name, String phone, SubjectModel subject) {
		this.name = name;
		this.phone = phone;
		this.subject = subject;
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

	public SubjectModel getSubject() {
		return subject;
	}

	public void setSubject(SubjectModel subject) {
		this.subject = subject;
	}

	// toString()
	public String toString() {
		return "이름=" + name + ", 휴대폰번호=" + phone + "\n" + subject;
	}

}
