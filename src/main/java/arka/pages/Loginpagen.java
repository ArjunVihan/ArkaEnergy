package arka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import arka.helper.Utility;

public class Loginpagen {

	WebDriver driver;
	
	 public Loginpagen(WebDriver driver) {
	
		this.driver=driver;
	}
	
	private By username=By.xpath("//input[@placeholder='Email Id']");
	
	private By password=By.xpath("//input[@id='password']");
	
	private By loginButton=By.xpath("//div[@class='button'][normalize-space()='Login']");
	
	private By defaultDashboard=By.xpath("//div[@class='selection-container el-popover__reference']//p[contains(text(),'Default Dashboard')]");
	
	public void loginToApplication(String uname,String pass)
	{
		
		//driver.findElement(username).sendKeys(uname);
		Utility.waitForWebElement(driver, username).sendKeys(uname);
		Reporter.log("LOG:INFO - Username Entered ");
		Utility.waitForWebElement(driver, password).sendKeys(pass);
		Reporter.log("LOG:INFO - Password Entered ");
		//driver.findElement(password).sendKeys(pass);
		
		//driver.findElement(loginButton).click();
		
		Utility.waitForWebElement(driver, loginButton).click();
		Reporter.log("LOG:INFO - Login Button Clicked ");
		
	}
	
	public boolean isUserLoggedIn()
	{
		boolean status=false;
		
		try
		{
			//driver.findElement(manageOption).isDisplayed();
			Utility.waitForWebElement(driver, defaultDashboard).isDisplayed();
			status=true;
			Reporter.log("LOG:INFO - Login Successful ");
		}
		catch(NoSuchElementException e)
		{
			
		}
		return status;
		
	}
	
}


