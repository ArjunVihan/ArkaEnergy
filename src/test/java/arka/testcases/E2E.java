package arka.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import arka.base.BaseClass;
import arka.dataProvider.CustomDataProvider;
import arka.pages.AddTaskpage;
import arka.pages.Leadspage;
import arka.pages.Loginpage;

public class E2E extends BaseClass {
	
	// This test will cover Login , lead generation , task generation and logout
	
	@Test(dataProvider="leadData",dataProviderClass =CustomDataProvider.class)
	public void loginLeadTaskCreation(String uname,String pass,String name,String owner,String email,String phnNo,String property,String leadSource,String pipeline,String stage,String address,String cost,String lName,String startDate,String dueDate,String taskDescription)
	
{	
Loginpage login = new Loginpage(driver);
login.loginToApplication(uname,pass);
Assert.assertTrue(login.isUserLoggedIn(),"Login failed");
Leadspage leadspage = new Leadspage(driver);
leadspage.addLead(name,owner, email,phnNo, property, leadSource, pipeline, stage,address,cost);
Assert.assertTrue(leadspage.leadGenerated(),"Lead Generation Failed");
Faker faker=new Faker();
String taskname=faker.name().firstName();
AddTaskpage addTask=new AddTaskpage(driver);
addTask.addTask(lName, startDate, dueDate, taskDescription,taskname);
Assert.assertTrue(addTask.istaskCreated(taskname),"Task Creation Failed");
}

}
