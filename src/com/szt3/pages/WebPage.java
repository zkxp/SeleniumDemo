package com.szt3.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebPage {
	public WebDriver driver;
	private static Logger logger=LogManager.getLogger();
	public void selectDownlist(String selection,String option){
		WebElement selected=driver.findElement(By.xpath("//div[contains(@class,'eye-window')]/descendant::span[text()='"+selection+"']/following::input"));
		logger.info(selection+"下拉框默认值为："+selected.getAttribute("value"));
		WebElement dropdownBtn=driver.findElement(By.xpath("//div[contains(@class,'eye-window')]/descendant::span[text()='"+selection+"']/following::span"));
		dropdownBtn.click();
		WebElement UL=driver.findElement(By.xpath("//div[@class='eye-scroll eye-list eye-list-dropdown']/ul"));
		List<WebElement> lis=UL.findElements(By.className("eye-list-item-text"));
		for (WebElement li : lis) {
			if(li.getText().equals(option)){
				li.click();
			}
		}
		logger.info(selection+"下拉框选中的值为："+selected.getAttribute("value"));
		
	}
}
