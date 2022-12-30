package registerJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String USER = "PROJECT";
    private final String PASSWORD = "PROJECT";
    private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

    // 관리자 부분
    // 0. 자동 과목 생성
    // sql문에 값을 입력받는것이 없으면
    // sqlInjection에 방어하기 위해 Statement를 쓰자!
    public int autoInsertSubject() {
        String sql1 = "INSERT INTO SUBJECT_TBL VALUES('자바', SUB_SEQUENCE.NEXTVAL, '김이준', DEFAULT, DEFAULT)";
        String sql2 = "INSERT INTO SUBJECT_TBL VALUES('파이썬', SUB_SEQUENCE.NEXTVAL, '이서준', DEFAULT, DEFAULT)";
        String sql3 = "INSERT INTO SUBJECT_TBL VALUES('C언어', SUB_SEQUENCE.NEXTVAL, '홍하준', DEFAULT, DEFAULT)";
        String sql4 = "INSERT INTO SUBJECT_TBL VALUES('프론트 개발자 양성과정', SUB_SEQUENCE.NEXTVAL, '박도윤', DEFAULT, DEFAULT)";
        String sql5 = "INSERT INTO SUBJECT_TBL VALUES('Springframework & 클라우드 융합 웹 개발자 양성과정', SUB_SEQUENCE.NEXTVAL, '민봉식', DEFAULT, DEFAULT)";
        String sql6 = "INSERT INTO SUBJECT_TBL VALUES('클라우드 컴퓨팅 엔지니어 양성과정', SUB_SEQUENCE.NEXTVAL, '김시우', DEFAULT, DEFAULT)";
        String sql7 = "INSERT INTO SUBJECT_TBL VALUES('빅데이터 기반 금융 솔루션 UI개발자 양성과정', SUB_SEQUENCE.NEXTVAL, '서은우', DEFAULT, DEFAULT)";

        List<String> list = new ArrayList<>();
        list.add(sql1);
        list.add(sql2);
        list.add(sql3);
        list.add(sql4);
        list.add(sql5);
        list.add(sql6);
        list.add(sql7);

        int result = -1;
        try {
            Class.forName(DRIVER_NAME);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            for (int i = 0; i < 7; i++) {
                result = stmt.executeUpdate(list.get(i));
            }
            return result;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 1. 과목 생성
    public int insertSubject(Subject subject) {
        String sql = "INSERT INTO SUBJECT_TBL(?,SUB_SEQUENCE.NEXTVAL,?,DEFAULT,DEFAULT)";
        int result = -1;
        try {
            Class.forName(DRIVER_NAME);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,subject.getSubjectName());
            pstmt.setString(2,subject.getName());
            result = pstmt.executeUpdate();

            return result;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 2. 과목 삭제
    public int deleteSubject(int codeNum) {
        String sql = "DELETE FROM SUBJECT_TBL WHERE SUBJECT_CODE = ?";
        int result = -1;
        try {
            Class.forName(DRIVER_NAME);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,codeNum);
            result = pstmt.executeUpdate();

            return result;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 3. 전체 과목 조회
    public List<Subject> selectAll() {
        String sql = "SELECT * FROM SUBJECT_TBL";
        Subject subject = null;
        List<Subject> sList = null;

        try {
            Class.forName(DRIVER_NAME);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            sList = new ArrayList<>();
            while (rs.next()) {
                subject = new Subject(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                sList.add(subject);
            }

            return sList;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Subject selectByCodeNum(int codeNum){
        String sql = "SELECT * FROM SUBJECT_TBL WHERE SUBJECT_CODE = ?";
        Subject subject = null;
        try {
            Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,codeNum);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                subject = new Subject(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }

            return subject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
