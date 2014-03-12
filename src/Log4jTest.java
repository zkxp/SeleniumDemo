import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4jTest {
	private static Logger logger=LogManager.getLogger();
	public static void main(String[] args) {
		logger.entry();
		logger.info("hello world");
		logger.error("Hello Error");
		logger.exit();
	}
}
