package arka.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import arka.base.BaseClass;
import arka.dataProvider.CustomDataProvider;
import arka.pages.AddTaskpage;
import arka.pages.Leadspage;
import arka.pages.Loginpage;

public class Login extends BaseClass 
{
	@Test(dataProvider = "userData",dataProviderClass = CustomDataProvider.class)
	public void loginWithValidCredentials(String uname,String pass)
	{
		Loginpage login=new Loginpage(driver);
		
		login.loginToApplication(uname,pass);
		
		Assert.assertTrue(login.isUserLoggedIn(),"Login failed");

}
	@Test(dataProvider = "leadData",dataProviderClass = CustomDataProvider.class)
	public void addNewLead(String uname,String pass,String name,String owner,String email,String phnNo,String property,String leadSource,String pipeline,String stage,String address,String cost)
	{

		Loginpage login=new Loginpage(driver);
		login.loginToApplication(uname,pass);
		Leadspage leadspage = new Leadspage(driver);
		leadspage.addLead(name,owner, email,phnNo, property, leadSource, pipeline, stage,address,cost);
		Assert.assertTrue(leadspage.leadGenerated(),"New lead generated");
	}
	
	@Test(dataProvider = "taskData",dataProviderClass = CustomDataProvider.class)
	public void addTask(String uname,String pass,String lName,String startDate,String dueDate,String taskDescription)
	{

		Loginpage login=new Loginpage(driver);
		login.loginToApplication(uname,pass);
		Faker faker=new Faker();
		String taskname=faker.name().firstName();
		AddTaskpage addTask=new AddTaskpage(driver);
		addTask.addTask(lName, startDate, dueDate, taskDescription,taskname);
		Assert.assertTrue(addTask.istaskCreated(taskname),"Task Created");
	}
}
