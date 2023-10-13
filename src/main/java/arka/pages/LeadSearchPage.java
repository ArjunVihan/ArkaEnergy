package arka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import arka.helper.Utility;

public class LeadSearchPage {
WebDriver driver;
	
	public   LeadSearchPage(WebDriver driver) 
	 {
	
		this.driver=driver;
	}
	
	private By leadIcon= By.xpath("(//span[@class='icon'])[2]");
	private By listView=By.xpath("(//img[@class='typeIcon'])[2]");
	private By search=By.xpath("//input[@placeholder='Search']");
	private By leadDetailList=By.xpath("//div[@class='bodyContainer']//div//div//div//p//span//p");
	private By leadFullDetails=By.xpath("//p[@class='fullDet']");
	
	public void leadSearch(String lName)
	{
		Utility.waitForElement(driver, leadIcon).click();
			String colorListViewString=driver.findElement(listView).getCssValue("background-color");
		if(colorListViewString.equals("Blue")) {}
	else Utility.waitForWebElement(driver, listView).click();
		Utility.waitForWebElement(driver, search).sendKeys(lName);
	
		Utility.selectValueFromList(driver, leadDetailList, lName);
		Utility.waitForWebElement(driver, leadFullDetails).click();
		
		
			
	}
}
