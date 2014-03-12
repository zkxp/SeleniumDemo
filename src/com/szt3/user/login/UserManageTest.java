package com.szt3.user.login;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.szt3.pages.UserPage;

public class UserManageTest {
	private WebDriver driver;

	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.get("http://10.0.10.41:9999/");
	}
	@Test
	public void testCreateUser(){
		UserPage userPage=new UserPage(driver);
		System.out.println(userPage.createBtn.getTagName());
	}
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}
