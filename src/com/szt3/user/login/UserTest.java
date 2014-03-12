package com.szt3.user.login;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.server.handler.interactions.MouseDown;
import org.openqa.selenium.server.SeleniumServer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opera.core.systems.scope.protos.ExecProtos.MouseAction;
import com.opera.core.systems.scope.protos.SystemInputProtos.MouseInfo.MouseButton;
import com.thoughtworks.selenium.Selenium;

public class UserTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	 baseUrl="http://10.0.10.31:9999/";
    System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\IEDriverServer.exe");
    driver=new InternetExplorerDriver();
//	System.setProperty("webdriver.firefox.bin","D:\\Program Files\\Mozilla Firefox\\firefox.exe");
//    driver=new FirefoxDriver();
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    WebElement userName=driver.findElement(By.name("UserLoginName"));
	WebElement password=driver.findElement(By.name("UserPwd"));
	WebElement sumbit=driver.findElement(By.id("submit"));
	password.clear();
	userName.sendKeys("sa");
	password.sendKeys("111111");
	sumbit.click();
  }

  @Test
  public void testUser() throws Exception {
//	WebDriverWait wait=new WebDriverWait(driver, 10);
	
    driver.get(baseUrl + "/User/usermanagement");
    driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[6]/div/span")).click();
//    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[16]/div[2]/div/div[2]/div[2]/div/input")));
    driver.findElement(By.name("UserLoginName")).clear();
    driver.findElement(By.name("UserLoginName")).sendKeys("usertesta1bc");
    driver.findElement(By.name("UserName")).clear();
    driver.findElement(By.name("UserName")).sendKeys("用户名");
    driver.findElement(By.cssSelector("html body div.eye-window div.eye-scroll div.eye-settingForm div.eye-form-item div.eye-form-cont div.eye-ipt span.eye-list-dropdownBtn")).click();
//    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("html body div.eye-scroll ul li.eye-list-item span.eye-list-item-text")));
//    WebElement ele=driver.findElement(By.cssSelector("html body div.eye-list-dropdown ul li.eye-list-item "));
   
    driver.findElement(By.xpath("/html/body/div[12]/div[2]/div/div[6]/div[2]/div/div[2]")).click();
//  广告商 
//    driver.findElement(By.xpath("/html/body/div[12]/div[2]/div/div[6]/div[2]/div/div")).click();
//   公司
    driver.findElement(By.xpath("/html/body/div[12]/div[2]/div/div[7]/div[2]/div/span")).click();
    driver.findElement(By.xpath("/html/body/div[9]/ul/li")).click();
//  部门  
//    driver.findElement(By.xpath("/html/body/div[12]/div[2]/div/div[8]/div[2]/div/span")).click();
  
//    driver.findElement(By.xpath("//div[16]/div[2]/div/div[7]/div[2]/div/span")).click();
//    driver.findElement(By.xpath("//div[16]/div[2]/div/div[8]/div[2]/div/span")).click();
    WebElement phone=driver.findElement(By.xpath("/html/body/div[12]/div[2]/div/div[9]/div[2]/div/input"));
    phone.sendKeys("11111111111");
    WebElement email=driver.findElement(By.xpath("/html/body/div[12]/div[2]/div/div[10]/div[2]/div/input"));
    email.sendKeys("1@1.com");
    driver.findElement(By.xpath("/html/body/div[12]/div[3]/div[2]/div")).click();
    
    WebElement info=driver.findElement(By.xpath("/html/body/div[13]/div[2]/div/div/span"));
    WebElement submit2=driver.findElement(By.xpath("/html/body/div[13]/div[3]/div[2]/div/span"));
    submit2.click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
