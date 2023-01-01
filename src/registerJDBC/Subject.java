package registerJDBC;

public class Subject {
    // 카멜표기법
    private String subjectName;     // 과목 이름
    private String name;            // 교수명
    private int subjectCode;        // 과목 코드
    private int capacity;           // 최대 수강 인원
    private int registerNumber;     // 신청 수강 인원

    public Subject() {
		super();
	}

	public Subject(String subjectName, String name, int subjectCode, int capacity, int registerNumber) {
		super();
		this.subjectName = subjectName;
		this.name = name;
		this.subjectCode = subjectCode;
		this.capacity = capacity;
		this.registerNumber = registerNumber;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}

	@Override
    public String toString() {
        return "과목코드: " + subjectCode + "\n" +
        		"과목명: " + subjectName + "\n" +
        		"교수명: " + name + "\n" + 
        		"최대 수강 인원: " + capacity + "\n" +
        		"신청 수강 인원: " + registerNumber;
    }
}
