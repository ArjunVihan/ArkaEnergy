package arka.helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import arka.dataProvider.ConfigReader;

public class Utility 
{

	public static WebElement waitForWebElement(WebDriver driver,By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
		
		if(ConfigReader.getProperty("highlightElement").contains("true"))
		{
			return highlightWebElement(driver, element);
		}
		else
		{
			return element;
		}
	
	}
	
	public static WebElement waitForElement(WebDriver driver,By locator)
	{
		WebElement element=null;
		
		for(int i=0;i<30;i++)
		{
			
			try 
			{
				element=driver.findElement(locator);
				
				if(element.isDisplayed() && element.isEnabled())
				{
					if(ConfigReader.getProperty("highlightElement").contains("true"))
					{
						return highlightWebElement(driver, element);
					}
					else
					{
						return element;
					}
					
				}
			} catch (Exception e) 
			{	
				System.out.println("Waiting for element conditions to be checked");
				wait(1);
			}
			
		}
			
		return element;
			
	}
	public static WebElement waitForElement(WebDriver driver,By locator,int time)
	{
		WebElement element=null;
		
		for(int i=0;i<time;i++)
		{
			
			try 
			{
				element=driver.findElement(locator);
				
				if(element.isDisplayed() && element.isEnabled())
				{
					if(ConfigReader.getProperty("highlightElement").contains("true"))
					{
						return highlightWebElement(driver, element);			
					}
					else
					{
						return element;
					}
					
				}
			} catch (Exception e) 
			{	
				System.out.println("Waiting for element conditions to be checked");
				wait(1);
			}
			
		}
			
		return element;
			
	}
	
	public static void wait(int second)
	{
		
		try 
		{
		Thread.sleep(second*1000);
		
		} catch (InterruptedException e) {
			
			System.out.println("Something Went Wrong");
		}
	
	}
	
	public static WebElement highlightWebElement(WebDriver driver,WebElement ele)
	{	
		JavascriptExecutor js=(JavascriptExecutor)driver;

		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px red')",ele);

		Utility.wait(1);
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",ele);

		return ele;
	}

	
	public static WebElement highlightWebElement(WebDriver driver,By locator)
	{
		
		WebElement ele=driver.findElement(locator);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;

		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px red')",ele);

		Utility.wait(1);
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",ele);

		
		return ele;
	}
	
	public static String getCurrentDate()
	{
		 LocalDateTime now = LocalDateTime.now();   
		 ZonedDateTime zonedUTC = now.atZone(ZoneId.of("UTC"));
			ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
	        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");  
	        String newFormat = zonedIST.format(format);  
return newFormat;
	}
	
	public static void captureScreenshot(WebDriver driver)
	{
		
		try 
		{
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File("./Screenshots/screenshot"+getCurrentDate()+".png"));
			
		} catch (IOException e) {
			
			System.out.println("Exception "+e.getMessage());
		}
		
		
	}
	
	public static String captureScreenshotAsBase64(WebDriver driver)
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		String screenshot=ts.getScreenshotAs(OutputType.BASE64);
		
		return screenshot; 
	}
	

	public static Alert waitForAlert(WebDriver driver)
	{
			
		Alert alt=null;
		
		for(int i=0;i<15;i++)
		{
			
			
			try 
			{
				alt=driver.switchTo().alert();

				break;
			}
			catch(NoAlertPresentException e)
			{
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					
				}
				
				System.out.println("Alert Not Found - Retrying");
			}
			
			
		}
		
		return alt;
		
	}
	
	public static Alert waitForAlert(WebDriver driver,int seconds)
	{
		Alert alt=null;
		
		for(int i=0;i<seconds;i++)
		{
			
			
			try 
			{
				alt=driver.switchTo().alert();

				break;
			}
			catch(NoAlertPresentException e)
			{
				try 
				{
					Thread.sleep(1000);
					
				} catch (InterruptedException e1) {
					
				}
				
				System.out.println("Alert Not Found - Retrying");
			}
			
		}
		
		return alt;
	}
	

	public static void selectValueFromList(WebDriver driver,By locator,String elementToSearch) 
	{
		List<WebElement> allValues=driver.findElements(locator);
		
		for(WebElement ele:allValues)
		{
			wait(2);		
		if(ele.getText().contains(elementToSearch)) {
				
				ele.click();
				break;
			}
		}
	}
	
	public static void waitForLoaderDisappear() {
		
		
		WebDriverWait wait=new WebDriverWait(null, null);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("")));
	}
	
	
	
	
	public static void selectValueFromList(WebDriver driver,String xpathValue,String elementToSearch) 
	{
		
		List<WebElement> allValues=driver.findElements(By.xpath(xpathValue));
		
		for(WebElement ele:allValues)
		{
			if(ele.getText().contains(elementToSearch)) {
				ele.click();
				break;
			}
		}
	
	}
	
	
}
