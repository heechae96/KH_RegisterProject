package registerJDBC;

public class Student {
    // 카멜표기법
    private String studentId;   // 아이디
    private String name;        // 이름
    private String phone;       // 휴대폰 번호
    private int subjectCode;    // 과목코드

    public Student() {
    }

    public Student(String studentId, String name, String phone, int subjectCode) {
        this.studentId = studentId;
        this.name = name;
        this.phone = phone;
        this.subjectCode = subjectCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

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

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    @Override
    public String toString() {
    	return "아이디: " + studentId + "\n" +
    			"이름: " + name + "\n" +
    			"휴대폰번호: " + phone + "\n" +
    			"수강 과목 코드: " + subjectCode;
    }
}
