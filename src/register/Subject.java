package register;

public class Subject {
	
	private String subjectName;		// 과목이름
	private int subjectCode; 		// 과목코드
	private String name;			// 교수명
	private int capacity;			// 최대 수강 인원
	private int registerNum;		// 신청 수강 인원
	
	// 생성자
	public Subject(String subjectName, int subjectCode, String name, int capacity, int registerNum) {
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
		this.name = name;
		this.capacity = capacity;
		this.registerNum = registerNum;
	}
	
	// Getters and Setters
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getRegisterNum() {
		return registerNum;
	}

	public void setRegisterNum(int registerNum) {
		this.registerNum = registerNum;
	}

	// toString
	public String toString() {
		return "과목코드: " + subjectCode + ", 과목명: " + subjectName
				+ ", 교수명: " + name + ", 제한: " + capacity + ", 신청인원: "
				+ registerNum;
	}
	
}
