package com.szt3.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bsh.StringUtil;


public class UserPage{
	@FindBy(xpath="/html/body/div[2]/div/div/div/div[6]/div/span")
	public WebElement createBtn;
	@FindBy(xpath="/html/body/div[13]/div[3]/div[2]/div/span")
	public WebElement alertSubmit;
	@FindBy(xpath="/html/body/div[13]/div[2]/div/div/span")
	public WebElement alertContent;
	@FindBy(xpath="/html/body/div[13]/div[2]/div/div/span")
	public WebElement repeatContent;
	@FindBy(xpath="/html/body/div[2]/div/div/div/div[2]/div/span")	
	public WebElement statusDL;
	public final String statusUL="/html/body/div[5]/ul";
	@FindBy(xpath="/html/body/div[2]/div/div/div/div[4]/div/span")	
	public WebElement roleDL;
//	public final String roleUL="/html/body/div[6]/ul";
	@FindBy(xpath="/html/body/div[2]/div/div/div/div[5]/div")
	public WebElement queryBtn;
	@FindBy(xpath="/html/body/div[2]/div/div[2]/div/div[2]")
	public WebElement userGrid;
//	submit确定
	@FindBy(xpath="//div[contains(@class,'eye-window')]/descendant::div[@class='eye-window-ft-btns']/div/span")
	public WebElement submitBtn;
//	confirm确定
	@FindBy(xpath="//div[contains(@class,'eye-window eye-window-confirm')]/div[3]/div[2]/div/span")
	public WebElement confirmBtn;
//	confirm取消
	@FindBy(xpath="//div[contains(@class,'eye-window eye-window-confirm')]/div[3]/div[2]/div[2]/span")
	public WebElement cancelBtn;
//	confirm提示信息 
	@FindBy(xpath="//div[contains(@class,'eye-window eye-window-confirm')]/div[2]/div/div/span")
	public WebElement confirmInfo;
//	@FindBy(xpath="/html/body/div[2]/div/div/div/div[7]/div/div[5]/a")
//	public WebElement nextBtn;
//	@FindBy(xpath="/html/body/div[2]/div/div/div/div[7]/div/div/a")
//	public WebElement firstBtn;
// 角色
	public final String roleUL="/html/body/div[12]/div[2]/div/div[11]/div[2]/div/ul";
	public final String roleSetUL="//div[@class='eye-window']/div[2]/div/ul";	
//	角色设置确定
	@FindBy(xpath="//div[contains(@class,'eye-window')]/div[3]/div/div/span")
//	public WebElement roleSubmitBtn;
	public WebDriver driver;
	public UserPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	public void selectDownlist(String dl,String option){
//		WebElement UL = null;
//		String xpath=null;
//		switch (dl) {
//		case "status":
//			statusDL.click();
//			xpath=statusUL;
//			break;
//		case "role":
//			roleDL.click();
//			xpath=roleUL;
//			break;
//		default:
//			break;
//		}
//		UL=driver.findElement(By.xpath(xpath));
//		List<WebElement> lis=UL.findElements(By.className("eye-list-item-text"));
//		for (WebElement li : lis) {
//			if(li.getText().equals(option)){
//				li.click();
//			}
//		}
//	}
	public  List queryUser(String status,String role){
		int statusId = 0;
		switch(status){
		case "启用":
			statusId=1;
			break;
		case "禁用":
			statusId=0;
			break;
		default:
			break;
		}
		List<List> userList=new ArrayList<List>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://10.0.10.41:3306/db_szt3?user=root&password=mission";
			Connection con=DriverManager.getConnection(url);
			Statement stmt=con.createStatement();
			String sql= "select u.userLoginName,u.userName,u.userPhone,c.companyname, "+
						"(case u.status when 1 then '启用' when 0 then '禁用' else 'err' end )status ,group_concat(role.rolename order by role.fk_roleid asc) "+
						"from tuser u left join  "+
					    "(select rl.* ,r.status staturs, r.rolename  "+
					    "from trolerelation rl  "+
					    "left join Trole r on rl.fk_roleid=r.pk_roleid "+
					    "where status in (-1,1))  "+
					    "role on u.pk_usersid=role.fk_userid "+
					    "left join tcompany c on u.fk_companyid=c.pk_companyid "+
					    "WHERE u.status <> -1 and u.pk_usersid <>66 "+
					    "group by u.userLoginName "+
					    "order by createtime desc ";
			String queryStatusSql= "select u.userLoginName,u.userName,u.userPhone,c.companyname, "+
					"(case u.status when 1 then '启用' when 0 then '禁用' else 'err' end )status ,group_concat(role.rolename order by role.fk_roleid asc) "+
					"from tuser u left join  "+
				    "(select rl.* ,r.status staturs, r.rolename  "+
				    "from trolerelation rl  "+
				    "left join Trole r on rl.fk_roleid=r.pk_roleid "+
				    "where status in (-1,1))  "+
				    "role on u.pk_usersid=role.fk_userid "+
				    "left join tcompany c on u.fk_companyid=c.pk_companyid "+
				    "WHERE  u.pk_usersid <>66 and u.status ="+statusId+
				    " group by u.userLoginName "+
				    "order by createtime desc ";
			String queryRoleSql="select u.userLoginName,u.userName,u.userPhone,c.companyname, "+
					"(case u.status when 1 then '启用' when 0 then '禁用' else 'err' end )status ,group_concat(role.rolename order by role.fk_roleid asc) "+
					"from tuser u left join  "+
				    "(select rl.* ,r.status staturs, r.rolename  "+
				    "from trolerelation rl  "+
				    "left join Trole r on rl.fk_roleid=r.pk_roleid "+
				    "where status in (-1,1))  "+
				    "role on u.pk_usersid=role.fk_userid "+
				    "left join tcompany c on u.fk_companyid=c.pk_companyid "+
				    "WHERE u.status <> -1 and  u.pk_usersid <>66 "+
				    " and u.pk_usersid in (select rl.fk_userid from trolerelation rl left join trole r on rl.fk_roleid=r.pk_roleid where r.rolename='"+role+"') "+
				    " group by u.userLoginName "+
				    "order by createtime desc ";
			String querySql="select u.userLoginName,u.userName,u.userPhone,c.companyname, "+
					"(case u.status when 1 then '启用' when 0 then '禁用' else 'err' end )status ,group_concat(role.rolename order by role.fk_roleid asc) "+
					"from tuser u left join  "+
				    "(select rl.* ,r.status staturs, r.rolename  "+
				    "from trolerelation rl  "+
				    "left join Trole r on rl.fk_roleid=r.pk_roleid "+
				    "where status in (-1,1))  "+
				    "role on u.pk_usersid=role.fk_userid "+
				    "left join tcompany c on u.fk_companyid=c.pk_companyid "+
				    "WHERE u.status <> -1 and  u.pk_usersid <>66  and u.status ="+statusId+
				    " and u.pk_usersid in (select rl.fk_userid from trolerelation rl left join trole r on rl.fk_roleid=r.pk_roleid where r.rolename='"+role+"') "+
				    " group by u.userLoginName "+
				    "order by createtime desc ";
			ResultSet rs=null;
			if(status=="" && role==""){
				 rs=stmt.executeQuery(sql);
			}
			if(status!=""&&role==""){
				rs=stmt.executeQuery(queryStatusSql);
			}
			if(status==""&&role!=""){
				rs=stmt.executeQuery(queryRoleSql);
			}
			if(status!=""&&role!=""){
				rs=stmt.executeQuery(querySql);
			}
			List<String> param=null;
			while(rs.next()){
				param=new ArrayList<String>();
				for(int i=1;i<7;i++){
					param.add(rs.getString(i));
				}
				userList.add(param);
			}
			return userList;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
		
		
		
	}
	public List getUserList(List<List> uList){
		List<WebElement> uDivs = userGrid.findElements(By.className("eye-dataGrid-row"));
		List<String> uParam=null;
		for(WebElement div:uDivs){
				List<WebElement> params=div.findElements(By.className("eye-dataGrid-row-text"));
				uParam= new ArrayList<String>();
				for(WebElement param:params){
					if(params.indexOf(param)!=6) {
						String text="";
						text=param.getText();
						if(params.indexOf(param)==5){
							text=text.replace("】【", ",");
							text=text.replace("【", "");
							text=text.replace("】", "");
						}
						 uParam.add(text);
					}
				}
				uList.add(uParam);
				System.out.println(uList.size());
		}
		return uList;
	}
	
	public boolean compareUsers(String status,String role){
		WebElement nextBtn=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[7]/div/div[5]/a"));;
		List<List> pageList=new ArrayList<>();
//		System.out.println(nextBtn.getCssValue("cursor"));
		boolean isPointer=true;
		boolean isEqual=true;
		do{
			if(nextBtn.getCssValue("cursor").equals("default")){
				pageList=getUserList(pageList);
				isPointer=false;
			}else{
				System.out.println(nextBtn.getCssValue("cursor"));
				nextBtn=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[7]/div/div[5]/a"));;
				pageList=getUserList(pageList);
				nextBtn.click();
			}
		}while(isPointer);
		List<List> dbList=queryUser(status,role);
		for(int i=0;i<dbList.size();i++){
			
			for (int j=0;j<dbList.get(i).size();j++){
				System.out.print("dbus:"+dbList.get(i).get(j)+" ");
//				System.out.print("page:"+pageList.get(i).get(j)+" ");
			}
			System.out.println();
			for (int j=0;j<dbList.get(i).size();j++){
//				System.out.print("db:"+dbList.get(i).get(j)+" ");
				System.out.print("page:"+pageList.get(i).get(j)+" ");
			}
			System.out.println();
			System.out.println();
		}
		for(int i=0;i<dbList.size();i++){
			for (int j=0;j<dbList.get(i).size();j++){
				String dbParam="";
				String pageParam="";
				if(dbList.get(i).get(j)==null){
					dbParam="";
				}
				else{
					dbParam=dbList.get(i).get(j).toString();
					pageParam=pageList.get(i).get(j).toString();
				}
				
				if(!dbParam.equals(pageParam)){
					isEqual=false;
					break;
				}
			}
		}
		return isEqual;
	}
	public void queryPage(String status, String role) {
		// TODO Auto-generated method stub
		if(status!=null||status!=""){
			selectToolBarDownlist("用户状态", status);
		}
		if(role!=null||role!=""){
			selectToolBarDownlist("用户角色", role);
		}
		queryBtn.click();
	}
	public void operateClick(String loginName, String operate) {
		// TODO Auto-generated method stub
		WebElement btn=driver.findElement(By.xpath("//span[text()='"+loginName+"']/following::span/div/a[text()='"+operate+"']"));
		btn.click();
	}
	public void selectDownlist(String selection,String option){
		WebElement selected=driver.findElement(By.xpath("//div[contains(@class,'eye-window')]/descendant::span[text()='"+selection+"']/following::input"));
		System.out.println(selection+"下拉框默认值为："+selected.getAttribute("value"));
		WebElement dropdownBtn=driver.findElement(By.xpath("//div[contains(@class,'eye-window')]/descendant::span[text()='"+selection+"']/following::span"));
		dropdownBtn.click();
		WebElement UL=driver.findElement(By.xpath("//div[@class='eye-scroll eye-list eye-list-dropdown']/ul"));
		List<WebElement> lis=UL.findElements(By.className("eye-list-item-text"));
		for (WebElement li : lis) {
			if(li.getText().equals(option)){
				li.click();
			}
		}
		System.out.println(selection+"下拉框选中的值为："+selected.getAttribute("value"));

		
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
	public List queryUserByLoginName(String name){
		List<String> userList=new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://10.0.10.41:3306/db_szt3?user=root&password=mission";
			Connection con=DriverManager.getConnection(url);
			Statement stmt=con.createStatement();
			String sql=" select  u.userLoginName,u.userName,group_concat(role.rolename order by role.fk_roleid asc) role,c.companyname,d.departmentname, "+
					    "(case u.usertype when 1 then '管理部门' when 2 then '广告商' else 'err' end) type,u.userPhone, u.email, "+
					    "u.createtime,u.updatetime "+
					    "from tuser u left join  ( "+
					    "select rl.* ,r.status status, r.rolename  "+
					    "from trolerelation rl   "+
					    "left join Trole r on rl.fk_roleid=r.pk_roleid  "+
					    "where status in (-1,1)  "+
					    "		)   "+
						"role on u.pk_usersid=role.fk_userid  "+
						"left join tcompany c on u.fk_companyid=c.pk_companyid  "+
						"left join tdepartment d on d.pk_departmentid=u.fk_departmentid  "+
						"WHERE u.userLoginname='"+name+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				for(int i=1;i<11;i++){
					if(i==10||i==9){
						
						String text=rs.getString(i);
						userList.add(text.substring(0, 19));
					}else{
						String text=rs.getString(i);
						if(text==null)text=" ";
						userList.add(text);
					}
				}
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	public List getUserInfo(){
		
		WebElement div=driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div"));
		List<WebElement> uDivs = div.findElements(By.className("eye-form-cont"));
		List<String> pList=new ArrayList<String>();
		for(WebElement param:uDivs){
			String text=param.getText();
			if(uDivs.indexOf(param)==2){
				text=param.getText();
				text=text.replace("】【", ",");
				text=text.replace("【", "");
				text=text.replace("】", "");
			}
				 pList.add(text);
			
		}
		
		return pList;
	}
	public Boolean compareUser(String name) {
		// TODO Auto-generated method stub
		WebElement div=driver.findElement(By.xpath("//span[text()='"+name+"']"));
		div.click();
		List<String> dbList=queryUserByLoginName(name);
		List<String> pageList=getUserInfo();
		Boolean isEqual=true;
		for(int i=0;i<dbList.size();i++){
			if(!dbList.get(i).equals(pageList.get(i))){
				isEqual=false;
				break;
			}
		}
		return isEqual;
		
	}
	public void selectToolBarDownlist(String selection,String option){
		
		WebElement dropdownBtn=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/span[text()='"+selection+"']/following::span"));
		dropdownBtn.click();
		WebElement UL=driver.findElement(By.xpath("//div[@class='eye-scroll eye-list eye-list-dropdown']/ul"));
		List<WebElement> lis=UL.findElements(By.className("eye-list-item-text"));
		for (WebElement li : lis) {
			if(li.getText().equals(option)){
				li.click();
			}
		}
		WebElement selected=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/span[text()='"+selection+"']/following::input"));
		System.out.println(selection+"下拉框选中的值为："+selected.getAttribute("value"));
	}
}
