import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JdbcUtil {
	private static Properties p=new Properties();
	private static String	url=null;
	private static String	user=null;
	private static String	driver=null;
	private static String	password=null;
	static {
		try{
			p.load(ClassLoader.getSystemResourceAsStream("jdbcConfig.properties"));
			url=p.getProperty("url");
			user=p.getProperty("user");
			driver=p.getProperty("driver");
			password=p.getProperty("password");
			Class.forName(driver);
		}catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}

	//ÊÍ·Å×ÊÔ´
	public static void release(Object o){
		if (o == null){
			return;
		}
		if (o instanceof ResultSet){
			try {
				((ResultSet)o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(o instanceof Statement){
			try {
				((Statement)o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Connection){
			Connection c = (Connection)o;
			try {
				if (!c.isClosed()){
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
         public static void release(ResultSet rs, Statement stmt,Connection conn){
			release(rs);
			release(stmt);
			release(conn);
	}
}

