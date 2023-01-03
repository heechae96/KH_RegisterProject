package registerJDBC;

public class Register {
	// 이후에 JPA를 배우면 이런식으로 추가적인 VO를 만들필요가 없다
	private String studentId; 		// 아이디
	private String studentPw; 		// 비밀번호
	private String name; 			// 이름
	private int subjectCode; 		// 과목코드
	private String subjectName; 	// 과목 이름
	
	public Register() {
		super();
	}

	public Register(String studentId, String studentPw, String name, int subjectCode, String subjectName) {
		super();
		this.studentId = studentId;
		this.studentPw = studentPw;
		this.name = name;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentPw() {
		return studentPw;
	}

	public void setStudentPw(String studentPw) {
		this.studentPw = studentPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
    public String toString() {
    	return "아이디: " + studentId + "\n" +
    			"비밀번호: " + studentPw + "\n" +
    			"이름: " + name + "\n" +
    			"과목명: " + subjectName + "\n" +
    			"과목 코드: " + subjectCode;
    }
}
