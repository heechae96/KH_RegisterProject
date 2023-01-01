package registerJDBC;

public class Student {
    // 카멜표기법
    private String studentId;   // 아이디
    private String studentPw;   // 비밀번호
    private String name;        // 이름
    private int subjectCode;    // 과목코드

    public Student() {
		super();
	}

	public Student(String studentId, String studentPw, String name, int subjectCode) {
		super();
		this.studentId = studentId;
		this.studentPw = studentPw;
		this.name = name;
		this.subjectCode = subjectCode;
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

	@Override
    public String toString() {
    	return "아이디: " + studentId + "\n" +
    			"비밀번호: " + studentPw + "\n" +
    			"이름: " + name + "\n" +
    			"수강 과목 코드: " + subjectCode;
    }
}
