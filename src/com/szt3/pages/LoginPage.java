package com.szt3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebElement UserLoginName;
	public WebElement password;
	public WebElement submit;
	@FindBy(id="errormsg")
	public WebElement errormsg;
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		System.out.println("初始化成功");
	}
	public void login(String name,String pwd){
		UserLoginName.clear();
		
		UserLoginName.sendKeys(name);
		password.clear();
		password.sendKeys(pwd);
		submit.click();
	}
	public void login() {
		// TODO Auto-generated method stub
		UserLoginName.clear();
		UserLoginName.sendKeys("sa");
		password.clear();
		password.sendKeys("111111");
		submit.click();
	}
	
}
