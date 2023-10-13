package arka.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
			driver=new ChromeDriver();
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
			
		} else if(browserName.equalsIgnoreCase("Firefox") || browserName.equalsIgnoreCase("FF") || browserName.equalsIgnoreCase("Mozila"))
		{
			// read headless property from config file and if set to true then run the test in headless mode via --headless argument
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge") || browserName.equalsIgnoreCase("Microsoft Edge") || browserName.equalsIgnoreCase("MSEdge"))
		{
			// read headless property from config file and if set to true then run the test in headless mode via --headless argument
			driver=new EdgeDriver();
		}
		}
		driver.manage().window().setSize(new Dimension(1360,998));
			
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("pageLoadTimeOut"))));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
		
		driver.get(appURL);
		
		return driver;
	}
	
	}

