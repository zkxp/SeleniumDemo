package com.szt3.test.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import com.szt3.pages.LoginPage;

public class UserManageTestTemplate {
	private WebDriver driver;
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.get("http://10.0.10.41:9999/");
	}
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	@Test(dataProvider="testDate")
	public void testCreateUser(){
	
	}
}
