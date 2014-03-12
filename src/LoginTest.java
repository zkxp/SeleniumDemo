

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
@RunWith( value= Parameterized.class)
public class LoginTest {
	private String paramName;
	private String paramPwd;
	private String errMsg;
	private WebDriver driver;
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.get("http://10.0.10.31:9999/");
	}
	@Parameters
	public static Collection testDate() throws IOException{
		return Arrays.asList(new Object[][]{
				{"sa","111111"},{"zgl","111111"}
		});
//		return getTestDate("d:\\test.csv");
//		InputStream sheet=new FileInputStream("d:\\java\\temp\\test.xlsx");
//		return new SpreadsheetData(sheet).getData();
		
	}
	private static Collection<String[]> getTestDate(String fileName) throws IOException {
		// TODO Auto-generated method stub
		List<String[]> records=new ArrayList<String[]>();
		String record;
		
		BufferedReader file=new BufferedReader(new FileReader(fileName));
		while((record=file.readLine())!=null){
			String fields[]=record.split(",");
			records.add(fields);
			return records;
		}
		return null;
	}
	public LoginTest(String name,String pwd,String errMsg){
		this.paramName=name;
		this.paramPwd=pwd;
		this.errMsg=errMsg;
	}

	@Test
	public void login(){
		WebElement userName=driver.findElement(By.name("UserLoginName"));
		WebElement password=driver.findElement(By.name("UserPwd"));
		WebElement sumbit=driver.findElement(By.id("submit"));
		userName.clear();
		password.clear();
		userName.sendKeys(paramName);
		password.sendKeys(paramPwd);
		sumbit.click();
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errormsg")));
		assertEquals(errMsg,driver.findElement(By.id("errormsg")).getText());
	
	}
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
