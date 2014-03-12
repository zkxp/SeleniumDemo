package com.szt3.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateUserWindow {
	public WebDriver driver;
	public WebElement UserLoginName;
	public WebElement UserName;
	public WebElement UserPwd;
	public WebElement UserPhone;
	public WebElement Email;
	public WebElement Memo;
	@FindBy(xpath="/html/body/div[12]/div[3]/div[2]/div/span")
	public WebElement submit;
	@FindBy(xpath="/html/body/div[12]/div[3]/div[2]/div[2]/span")
	public WebElement cancel;


//	类别
	@FindBy(xpath="/html/body/div[24]/div[2]/div/div[6]/div[2]/div/div/span")
	public WebElement categoryManage;
	@FindBy(xpath="/html/body/div[24]/div[2]/div/div[6]/div[2]/div/div[2]/span")
	public WebElement categoryAD;
	
//	状态下拉菜单
	@FindBy(xpath="/html/body/div[24]/div[2]/div/div[5]/div[2]/div/span")
	public WebElement statutsDL;
	public final String statusUL="/html/body/div[8]/ul";

	
//	公司
	@FindBy(xpath="/html/body/div[16]/div[2]/div/div[7]/div[2]/div/span")
	public WebElement companyDL;
	public final String  companyUL="/html/body/div[9]/ul";
 
//	部门
	@FindBy(xpath="/html/body/div[16]/div[2]/div/div[8]/div[2]/div/span")
	public WebElement departmentDL;
	public final String departmentUL="/html/body/div[10]/ul";

// 角色
	public final String roleUL="/html/body/div[12]/div[2]/div/div[11]/div[2]/div/ul";
	public final String roleSetUL="/html/body/div[13]/div[2]/div/ul";
	
	
	
	public CreateUserWindow(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void selectDownlist(String selection,String option){
		WebElement dropdownBtn=driver.findElement(By.xpath("//div[contains(@class,'eye-window')]/descendant::span[text()='"+selection+"']/following::span"));
		dropdownBtn.click();
		WebElement UL=driver.findElement(By.xpath("//div[@class='eye-scroll eye-list eye-list-dropdown']/ul"));
		List<WebElement> lis=UL.findElements(By.className("eye-list-item-text"));
		for (WebElement li : lis) {
			if(li.getText().equals(option)){
				li.click();
			}
		}
	}
	public void selectCheckBox(String mode,String[] roles){
		WebElement UL=driver.findElement(By.xpath(mode));
		List<WebElement> list=UL.findElements(By.className("eye-chk-text"));
		for(WebElement li:list){
			for(String role:roles){
				if(li.getText().equals(role)){
					li.click();
				}
			}
		}
	}
}
