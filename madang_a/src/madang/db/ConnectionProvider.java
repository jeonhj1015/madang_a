package madang.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 컴포넌트로 분리
// 디비 연결, 닫는 기능을 따로 컴포넌트 화 한다.
/*
 * 공통적인 부분은 맴버변수로 선언 
 * 객체와 무관하게 사용할 것이기 때문에 static 을 선언해 준다.
 * 
 * 
 */
public class ConnectionProvider {
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@localhost:1521:XE"; // localhost 나의 피시를 의미
	public static String id = "c##madang";
	public static String password = "madang";
	
	// 연결
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 1. jdbc 드라이버를 메모리로 로드한다.
			Class.forName(driver);
			
			// 2. DB서버에 연결한다.
			conn = DriverManager.getConnection(url,id,password);
		} catch (Exception e) {
			System.out.println("예외 : "+e.getMessage());
		}
		return conn; // return 을 해줘야함
	}
	
	
	// 닫기
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if( rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("예외 : "+e.getMessage());
		}
	}
	
	public static void close(Connection conn, PreparedStatement pstm) {
		try {
			if(pstm != null) {
				pstm.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println("예외 : "+e.getMessage());
		}
	}
}