package registerJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTemplate {

	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final String USER = "PROJECT";
	private final String PASSWORD = "PROJECT";
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

	// JDBCTemplate을 싱글톤 패턴 적용
	private static JDBCTemplate INSTANCE;

	private JDBCTemplate() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static JDBCTemplate getDriverLoad() {
		if (INSTANCE == null) {
			INSTANCE = new JDBCTemplate();
		}
		return INSTANCE;
	}

	// 연결을 만들어주는 메소드
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false); // 오토커밋 해제
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 커밋
	// 위에서 오토커밋을 해제해뒀기 때문에 커밋을 따로 해줘야 함
	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 롤백
	public void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 연결 해제
	public void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
