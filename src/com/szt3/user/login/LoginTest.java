package com.szt3.user.login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.*;

public class LoginTest {
	private WebDriver driver;
	private StringBuffer verificationErrors=new StringBuffer();
	@DataProvider
	public Object[][] testDate(){
		return new Object[][]{
				new Object[]{"","111111","�û�������Ϊ��"},
				new Object[]{"sa","","���벻��Ϊ��"},
				new Object[]{"sd","111111","�û���������"},
				new Object[]{"sa","aaaaaa","�������"}
		};
		
	}
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.get("http://10.0.10.31:9999/");
	}
	@Test(dataProvider="testDate")
	public void testLogin(String paramName,String paramPwd,String errmsg){
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
		Assert.assertEquals(errmsg,driver.findElement(By.id("errormsg")).getText());

	}
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	public void login(){
		WebElement userName=driver.findElement(By.name("UserLoginName"));
		WebElement password=driver.findElement(By.name("UserPwd"));
		WebElement sumbit=driver.findElement(By.id("submit"));
		userName.clear();
		password.clear();
		userName.sendKeys("sa");
		password.sendKeys("111111");
		sumbit.click();
	}
	
}
