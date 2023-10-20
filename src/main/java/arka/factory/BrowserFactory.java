package arka.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import arka.dataProvider.ConfigReader;

public class BrowserFactory 
{
	public static WebDriver driver;
	
	public static WebDriver getDriver()
	{
		return driver;
	}
	
	public static WebDriver startBrowser(String browserName,String appURL)
	{
		if(ConfigReader.getProperty("local").equalsIgnoreCase("true"))
		{
			if(browserName.equalsIgnoreCase("Edge") || browserName.equalsIgnoreCase("Microsoft Edge") || browserName.equalsIgnoreCase("MSEdge"))
			{
				
				driver=new EdgeDriver();
	     	}
			else if(browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("GC") || browserName.equalsIgnoreCase("Google Chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("Firefox") || browserName.equalsIgnoreCase("FF") || browserName.equalsIgnoreCase("Mozilla"))
			{
				driver=new FirefoxDriver();
			}
		}		
		else {
			
			if(browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("GC") || browserName.equalsIgnoreCase("Google Chrome"))
		   {
			
			ChromeOptions opt=new ChromeOptions();
			
			opt.addArguments("--no-sandbox");
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			capabilities.setCapability("browserName", "chrome");
			
			capabilities.setCapability("browserVersion", "117.0");
			
			capabilities.setCapability("platformName", "linux");
			
			opt.merge(capabilities);
			
			try {
				System.out.print(ConfigReader.getProperty("gridURL")+":"+ConfigReader.getProperty("gridPort")+"/wd/hub");
									
				driver=new RemoteWebDriver(new URL(ConfigReader.getProperty("gridURL")+":"+ConfigReader.getProperty("gridPort")+"/wd/hub"), opt);
			} catch (MalformedURLException e) {
			System.out.println("Could not connect to grid "+e.getMessage());
			}
			} 
			else if(browserName.equalsIgnoreCase("Firefox") || browserName.equalsIgnoreCase("FF") || browserName.equalsIgnoreCase("Mozila"))
		{
				FirefoxOptions opt=new FirefoxOptions();
				
				opt.addArguments("--no-sandbox");
				
				DesiredCapabilities capabilities=new DesiredCapabilities();
				
				capabilities.setCapability("browserName", "firefox");
				
				capabilities.setCapability("browserVersion", "117.0");
				
				capabilities.setCapability("platformName", "linux");
				
				opt.merge(capabilities);
				
				try {
					System.out.print(ConfigReader.getProperty("gridURL")+":"+ConfigReader.getProperty("gridPort")+"/wd/hub");
										
					driver=new RemoteWebDriver(new URL(ConfigReader.getProperty("gridURL")+":"+ConfigReader.getProperty("gridPort")+"/wd/hub"), opt);
				} catch (MalformedURLException e) {
				System.out.println("Could not connect to grid "+e.getMessage());
				}
		}
		else if(browserName.equalsIgnoreCase("Edge") || browserName.equalsIgnoreCase("Microsoft Edge") || browserName.equalsIgnoreCase("MSEdge"))
		{
			EdgeOptions opt=new EdgeOptions();
			
			opt.addArguments("--no-sandbox");
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			capabilities.setCapability("browserName", "Edge");
			
			capabilities.setCapability("browserVersion", "117.0");
			
			capabilities.setCapability("platformName", "linux");
			
			opt.merge(capabilities);
			
			try {
				System.out.print(ConfigReader.getProperty("gridURL")+":"+ConfigReader.getProperty("gridPort")+"/wd/hub");
									
				driver=new RemoteWebDriver(new URL(ConfigReader.getProperty("gridURL")+":"+ConfigReader.getProperty("gridPort")+"/wd/hub"), opt);
			} catch (MalformedURLException e) {
			System.out.println("Could not connect to grid "+e.getMessage());
			}
		}
		}
		driver.manage().window().setSize(new Dimension(1360,998));
			
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("pageLoadTimeOut"))));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
		
		driver.get(appURL);
		
		return driver;
	}
	
	}

