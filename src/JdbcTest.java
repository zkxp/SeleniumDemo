import java.sql.Connection;
import java.sql.SQLException;


public class JdbcTest {
	public static void main(String[] args) {
		JdbcUtil jdbcUtil=new JdbcUtil();
		try {
			Connection con=jdbcUtil.getConnection();
			jdbcUtil.release(con);
			System.out.println(con.isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
