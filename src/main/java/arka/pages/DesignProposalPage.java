package arka.pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Reporter;

import arka.helper.Utility;

public class DesignProposalPage {

	WebDriver driver;
	
	public  DesignProposalPage(WebDriver driver) 
	 {
	
		this.driver=driver;
	}
	
	private By designTab=By.xpath("//div[@id='tab-Design']");
	private By designOrderNowBtn=By.xpath("(//span[contains(text(),'Order Now')])[2]");
	private By agreeTc=By.xpath("//div[@class='footer']//span[@class='el-checkbox__inner']");
	private By dispayNowBtn=By.xpath("//button[@class='el-button footerBtn el-button--primary is-disabled']");
	private By payNowBtn=By.xpath("//button[@class='el-button footerBtn el-button--primary']");
	private By orderReceiveText=By.xpath("//h3[normalize-space()='Order Received Successfully.']");
	private By continueBtn=By.xpath("//span[normalize-space()='Continue']");
	private By netMetering=By.xpath("//span[@class='el-radio__input is-checked']//span[@class='el-radio__inner']");
	private By price=By.xpath("(//input[@type='number'])[2]");
	private By tariff=By.xpath("(//input[@type='number'])[4]");
	private By load=By.xpath("//input[@type='Number']");
	private By consumption=By.xpath("//input[@placeholder='Select Consumption Profile']");
	private By consumptionValuesList=By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']//li[contains(@class,'el-select-dropdown__item')]//span");
	private By energyConsumption=By.xpath("//div[@class='el-form-item']//input[@type='number']");
	private By updateConsumption=By.xpath("//span[normalize-space()='Update']");
	private By successUpdatemsg=By .xpath("//p[@class='el-message__content']");
	private By nextBtnStep2=By.xpath("//span[normalize-space()='Save & Next']");
	private By priceStep2 = By.xpath("//div[@class='gridView']//div[1]//div[1]//input[1]");
	private By nextBtnStep3 = By.xpath("//button[@class='el-button submitBtn el-button--primary']");
	private By powerGazebo = By.xpath("//label[normalize-space()='Power Gazebo']");
	private By moduleSelect = By.xpath("(//input[@placeholder='Select Module'])[2]");
	private By moduleList = By.xpath("//div[@x-placement='bottom-start']//ul[@class='el-scrollbar__view el-select-dropdown__list']//li//span");
	private By nextBtnStep4=By.xpath("//span[normalize-space()='Save & Next']");
	private By selectFile=By.xpath("//input[@type='file']");
	private By uploadFilename =By.xpath("//p[@class='contentSD']//abbr");
	private By nextBtnStep5 =By.xpath("//button[@class='el-button submitBtn el-button--primary']");
	private By reqProject =By.xpath("//textarea[@class='el-textarea__inner']");
	private By phn = By.xpath("//input[@type='number']");
	private By email = By.xpath("//input[@name='email']");
	private By saveLastBtn = By.xpath("//button[@class='el-button submitBtn el-button--primary']");
	private By createdProposalList = By.xpath("//div[@class=\"designCardsContainer\"]//div[@class='cards']");
	List<WebElement>proposalCount;
	int proposalCountSize=0;
	
	public void designOrderProposal( String priceRate,String tariffRate,String criticalLoad,String consumptionValue,String energyValue,String priceProposal,String gazeboModule,String fileName,String projectReq,String phnNo,String emailAdd)
	{
	
	System.out.println("Net metering is selected: "+driver.findElement(netMetering).isSelected());
	Utility.waitForWebElement(driver, price).clear();
	Utility.waitForWebElement(driver, price).sendKeys(priceRate);
	System.out.println("Price Rate Entered");
	Utility.waitForWebElement(driver, tariff).clear();
	Utility.waitForWebElement(driver, tariff).sendKeys(tariffRate);	
	System.out.println("Tariff Rate Entered");
	Utility.waitForWebElement(driver, load).clear();
	Utility.waitForWebElement(driver, load).sendKeys(criticalLoad);	
	System.out.println("Critical Load Entered");
	Utility.waitForWebElement(driver, consumption).click();
	Utility.selectValueFromList(driver, consumptionValuesList, consumptionValue);
	System.out.println("Consumption Value Entered");
	Utility.waitForWebElement(driver,energyConsumption).clear();
	Utility.waitForWebElement(driver,energyConsumption).sendKeys(energyValue);	
	System.out.println("Energy Consumption Entered");
	Utility.waitForWebElement(driver,updateConsumption).click();
	System.out.println("Energy Consumption Updated :"+driver.findElement(successUpdatemsg).isDisplayed());
	Utility.waitForWebElement(driver,nextBtnStep2).click();
	Utility.waitForWebElement(driver,priceStep2).clear();
	Utility.waitForWebElement(driver,priceStep2).sendKeys(priceProposal);
	System.out.println("Rate proposed entered");
	Utility.waitForWebElement(driver,nextBtnStep3).click();
	Utility.waitForWebElement(driver, powerGazebo).click();
	Utility.wait(2);
	Utility.waitForWebElement(driver, moduleSelect).click();
	Utility.selectValueFromList(driver, moduleList, gazeboModule);
	System.out.println("Power Gazebo Module Selected");
	Utility.waitForWebElement(driver,nextBtnStep4).click();
	String paths=System.getProperty("user.dir");
	String filePath = paths +"/TestData/demo.png";
		
	WebElement ele= driver.findElement(selectFile);
	LocalFileDetector detector = new LocalFileDetector();
	String path =new File(filePath).getAbsolutePath();
	File file = detector.getLocalFile(path);
	((RemoteWebElement) ele).setFileDetector(detector);
	ele.sendKeys(file.getAbsolutePath());
	Reporter.log("LOG:INFO - File Uploaded ");
	if((driver.findElement(uploadFilename).getText().equalsIgnoreCase(fileName)))
	Reporter.log("LOG:INFO - File Uploaded ");
	Utility.waitForWebElement(driver, nextBtnStep5).click();
	Utility.waitForWebElement(driver, reqProject).sendKeys(projectReq);
	Utility.waitForWebElement(driver, phn).sendKeys(phnNo);
	Utility.waitForWebElement(driver, email).sendKeys(emailAdd);
	System.out.println("Project requirement description , number and email entered");
	Utility.waitForWebElement(driver, saveLastBtn).click();

	}
	
	public void designOrderCreated()
	{
		Utility.waitForWebElement(driver, designTab).click();
		proposalCount=driver.findElements(createdProposalList);
		proposalCountSize=proposalCount.size();
		System.out.println(proposalCount.size());
		Utility.waitForWebElement(driver, designOrderNowBtn).click();
		System.out.println("Pay Now Button Enabled : "+driver.findElement(dispayNowBtn).isEnabled());
		Utility.waitForWebElement(driver, agreeTc).click();
		System.out.println("Agree T&C checked");
		System.out.println("Pay Now Button Enabled : "+driver.findElement(payNowBtn).isEnabled());
		Utility.waitForWebElement(driver, payNowBtn).click();
		System.out.println("Pay Now Button Clicked");
	}
	
	public boolean isdesignOrderCreated()
	{
		boolean status=false;
		
		try
		{
			
			driver.findElement(orderReceiveText).getText().contains("Successfully");
			status=true;
			Reporter.log("LOG:INFO - Design Order Proposal successfully created");
		}
		catch(NoSuchElementException e)
		{
			
		}
		return status;
		
	}
	
	public void preliminiaryOrderProposal()
	{
		Utility.waitForWebElement(driver, continueBtn).click();
	}

	public boolean isPreProposalCreated()
	{
		boolean status=false;
		try
		{		Utility.wait(2);
			List<WebElement>totalCount=driver.findElements(createdProposalList);
	
			System.out.println("New total Proposals created : "+totalCount.size());			
			
			if(totalCount.size()>proposalCount.size())
			{
			status=true;
			Reporter.log("LOG:INFO - Preliminary Proposal successfully created");
		}
			}
		catch(NoSuchElementException e)
		{
			
		}
		return status;
		
			

	}
}
