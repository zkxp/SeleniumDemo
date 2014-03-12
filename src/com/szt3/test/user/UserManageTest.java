package com.szt3.test.user;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.szt3.pages.CreateUserWindow;
import com.szt3.pages.LoginPage;
import com.szt3.pages.UserPage;

public class UserManageTest {
	private WebDriver driver;
	@BeforeTest
	public void setUp(){
		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
		driver=new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.get("http://10.0.10.41:9999");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.login();
		driver.get("http://10.0.10.41:9999/User/usermanagement");
	}
	@AfterTest
	public void tearDown(){
//		driver.quit();
	}
// �����غ��ҳ��
	@Test
	public void testQueryUser(){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		UserPage userPage=new UserPage(driver);
		Assert.assertEquals(userPage.compareUsers("",""), true);
	}
//	�½��û�
	@Test
	public void testCreateUser(){
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[6]/div/span")));
		UserPage userPage=new UserPage(driver);
		userPage.createBtn.click();
		
		CreateUserWindow createUserWindow=new CreateUserWindow(driver);
		String userLoginName="test"+RandomStringUtils.randomAlphabetic(5);
		createUserWindow.UserLoginName.sendKeys(userLoginName);
		createUserWindow.UserName.sendKeys("test");
		
		createUserWindow.selectDownlist("status","����");
		createUserWindow.categoryAD.click();
		createUserWindow.selectDownlist("company","mission5");
		createUserWindow.selectDownlist("department","�з���");
		createUserWindow.UserPhone.sendKeys("13823211233");
		createUserWindow.Email.sendKeys("xx@xx.cn");
		createUserWindow.selectCheckBox(createUserWindow.roleUL,new String[]{"ϵͳ����Ա","�߼���ɫ"});
		createUserWindow.Memo.sendKeys("��ע");
		createUserWindow.submit.click();
		
		String text=userPage.alertContent.getText();
		userPage.alertSubmit.click();
		Assert.assertTrue(text.contains(userLoginName));
		
		userPage.compareUser("userLoginName");
	}
//	��ѯ�û�
	@Test
	public void queryUser() {
		// TODO Auto-generated method stub
		WebDriverWait wait=new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[6]/div/span")));
		UserPage userPage=new UserPage(driver);
		userPage.queryPage("����","ϵͳ����Ա");
		userPage.compareUsers("����","ϵͳ����Ա");
	}
	@Test
	public void updateUser(){
		WebDriverWait wait=new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div[6]/div/span")));
		UserPage userPage=new UserPage(driver);
		userPage.operateClick("user001", "�޸�");
		CreateUserWindow createUserWindow=new CreateUserWindow(driver);
		createUserWindow.selectDownlist("status","����");
		createUserWindow.categoryAD.click();
		createUserWindow.selectDownlist("company","mission5");
		createUserWindow.selectDownlist("department","�з���");
		createUserWindow.UserPhone.sendKeys("13823211233");
		createUserWindow.Email.sendKeys("xx@xx.cn");
		createUserWindow.selectCheckBox(createUserWindow.roleUL,new String[]{"ϵͳ����Ա","�߼���ɫ"});
		createUserWindow.Memo.sendKeys("��ע");
		createUserWindow.submit.click();
		userPage.compareUser("user001");
	
		
	}


}
