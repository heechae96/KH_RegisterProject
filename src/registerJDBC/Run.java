package registerJDBC;

import java.util.HashMap;
import java.util.List;

public class Run {

    public static void main(String[] args) {

        RegisterView view = new RegisterView();
        SubjectController subCon = new SubjectController();
        StudentController stdCon = new StudentController();
        Subject subject = null;
        Student student = null;
        List<Subject> list = null;
        List<Student> list2 = null;

        int result = -1;
        int codeNum = -1;

        OUTER:
        while (true) {
            int mainNum = view.mainMenu();
            switch (mainNum) {
                case 0:
                    HashMap<String, String> map = view.checkAdmin();
                    String id = map.get("id");
                    String pw = map.get("pw");
                    // 관리자 로그인 성공
                    if (id.equals("admin") && pw.equals("1234")) {
                        INNER:
                        while (true) {
                            int subNum = view.subMenu();
                            switch (subNum) {
                                case 0:
                                    // 자동 과목 생성
                                    result = subCon.addAutoSubject();
                                    if (result > 0) {
                                        view.displaySuccess("자동 과목 추가 성공!!");
                                    } else {
                                        view.displayFail("자동 과목 추가 실패..");
                                    }
                                    break;
                                case 1:
                                    // 과목 생성
                                    subject = view.inputSubject();
                                    result = subCon.addSubject(subject);
                                    if (result > 0) {
                                        view.displaySuccess("과목 추가 성공!!");
                                    } else {
                                        view.displayFail("과목 추가 실패..");
                                    }
                                    break;
                                case 2:
                                    // 개설 과목 노출 필요
                                    list = subCon.findAll();
                                    view.printAllSubject(list);
                                    // 과목 삭제
                                    codeNum = view.inputCodeNum();
                                    result = subCon.removeSubject(codeNum);
                                    if (result > 0) {
                                        view.displaySuccess("과목 삭제 성공!!");
                                    } else {
                                        view.displayFail("과목 삭제 실패..");
                                    }
                                    break;
                                case 3:
                                    // 모든 정보 조회(과목, 학생)
                                    // 과목
                                    list = subCon.findAll();
                                    if (list.isEmpty()) {
                                        view.displayFail("개설된 과목이 없습니다..");
                                    } else {
                                        view.printAllSubject(list);
                                        view.displaySuccess("개설 과목 조회 완료!!");
                                    }
                                    view.printMsg("=============================================");
                                    // 학생
                                    list2 = stdCon.findAll();
                                    if (list2.isEmpty()) {
                                        view.displayFail("수강 신청한 학생이 없습니다..");
                                    } else {
                                        view.printAllStudent(list2);
                                        view.displaySuccess("모든 학생 조회 완료!!");
                                    }
                                    break;
                                case 4:
                                    // 관리자 모드 나가기
                                    view.displaySuccess("관리자 모드 종료");
                                    break INNER;
                                default:
                                    view.displayFail("0 ~ 4 사이의 수를 입력하세요");
                                    break;
                            }
                        }
                        break;  // 주의!! 이거 안쓰면 case 1로 넘어가버린다!!
                    } else {
                        // 관리자 로그인 실패
                        view.displayFail("관리자 계정이 아닙니다");
                        break;
                    }
                case 1:
                    // 개설 과목 조회
                    list = subCon.findAll();
                    if (list.isEmpty()) {
                        view.displayFail("개설된 과목이 없습니다..");
                    } else {
                        view.printAllSubject(list);
                        view.displaySuccess("개설 과목 조회 완료!!");
                    }
                    break;
                case 2:
                    // 개설 과목 노출 필요
                    list = subCon.findAll();
                    if (list.isEmpty()) {
                        view.displayFail("개설된 과목이 없습니다. 관리자에게 문의하세요!");
                        break;
                    } else { // 수강 신청
                        view.printAllSubject(list);
                        view.printMsg("=============== 위의 과목 코드를 확인해주세요 ===============");
                        student = view.inputStudent();
                        // null
                        result = stdCon.addStudent(student);
                        if (result > 0) {
                            view.displaySuccess("수강 신청 완료!!");
                        } else {
                            view.displayFail("수강 신청 실패..");
                        }
                        break;
                    }
                case 3:
                    String StudentId = view.inputStudentId();
                    student = stdCon.notEmptyStudent(StudentId);
                    if (student != null) {
                        view.displaySuccess("해당 id가 존재합니다!!");
                        subject = subCon.findByCodeNum(student.getSubjectCode());
                        view.printSubject(subject);
                        // 내역을 변경할지 안할지 정해야함
                        EXIT:
                        while (true) {
                            String choice = view.inputChoice();
                            if (choice.equalsIgnoreCase("yes")) {
                                // 변경하는 코드!
                                list = subCon.findAll();
                                view.printAllSubject(list);
                                view.printMsg("=============== 위의 과목 코드를 확인해주세요 ===============");
                                codeNum = view.changeSubject();
                                result = stdCon.changeCodeNum(codeNum, student);
                                if(result > 0){
                                    view.displaySuccess("수강신청 내역을 변경하였습니다!!");
                                }else{
                                    view.displayFail("수강신청 내역을 변경하지 못했습니다. 과목코드를 다시 확인하세요!!ㄴ");
                                }
                                break EXIT;
                            } else if (choice.equalsIgnoreCase("no")) {
                                view.displaySuccess("그대로 수강신청 내역을 유지합니다");
                                break EXIT;
                            } else {
                                view.displayFail("YES 또는 NO를 입력하세요..");
                            }
                        }
                    } else {
                        view.displayFail("해당 id가 존재하지 않습니다..");
                    }
                    break;
                case 4:
                    // 프로그램 종료
                    view.displaySuccess("프로그램 종료");
                    break OUTER;
                default:
                    view.displayFail("0 ~ 4 사이의 수를 입력하세요");
                    break;
            }

        }

    }

}
