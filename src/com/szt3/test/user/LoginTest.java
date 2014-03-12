package com.szt3.test.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.szt3.pages.LoginPage;
import com.szt3.pages.UserPage;

public class LoginTest {
	private WebDriver driver;
	@DataProvider
	public Object[][] testDate(){
		return new Object[][]{
				new Object[]{"","111111","用户名不能为空"},
				new Object[]{"sa","","密码不能为空"},
				new Object[]{"sd","111111","用户名不存在"},
				new Object[]{"sa","aaaaaa","密码错误"}
		};
		
	}
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.get("http://10.0.10.41:9999/");
	}
	@Test(dataProvider="testDate")
	public void testCreateUser(String name,String pwd,String errmsg){
		LoginPage login=new LoginPage(driver);
		login.UserLoginName.clear();
		login.UserLoginName.sendKeys(name);
		login.password.clear();
		login.password.sendKeys(pwd);
		login.submit.click();
		String msg=login.errormsg.getText();
		System.out.println(msg);
		Assert.assertEquals(msg, errmsg);
	}
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
