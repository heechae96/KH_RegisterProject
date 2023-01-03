package registerJDBC;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

	private static Properties prop;

	// JDBCTemplate을 싱글톤 패턴 적용
	private static JDBCTemplate instance;
	private static Connection connection;

	private JDBCTemplate() {
	}

	public static JDBCTemplate getDriverLoad() {
		if (instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}

	// 연결을 만들어주는 메소드
	public static Connection getConnection() {
		try {
			prop = new Properties();
			// 스트림에서 배웠음
			FileReader reader = new FileReader("resources/dev.properties");
			prop.load(reader);
			
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			
			// connection.isClosed()을 안쓰면 중간에 연결이 종료되는 상황 발생
			if (connection == null || connection.isClosed()) {
				Class.forName(prop.getProperty("driver"));
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); // 오토커밋 해제
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// 커밋
	// 위에서 오토커밋을 해제해뒀기 때문에 커밋을 따로 해줘야 함
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 롤백
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 연결 해제
	// 위에서 연결이 끊어지면 다시 연결되도록 했기 때문에 프로그램 종료시에 close()를 해줄것
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}