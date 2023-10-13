package arka.listeners;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import arka.helper.Utility;

public class ExtentManager {
	
	
	public static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		
		if(extent==null)
		{
			extent=createInstance();
			return extent;
		}
		else
		{
			return extent;
		}
		
	}

	public static ExtentReports createInstance()
	{
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/AutomationReport_"+Utility.getCurrentDate()+".html");
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		
		sparkReporter.config().setReportName("Sprint 1 Report");
		LocalDateTime now = LocalDateTime.now(); 
		ZonedDateTime zonedUTC = now.atZone(ZoneId.of("UTC"));
		// converting to IST
		ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
	        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");  
	        String newFormat = zonedIST.format(format); 
		sparkReporter.config().setTimeStampFormat(newFormat);
		
		sparkReporter.config().setTheme(Theme.DARK);
		
		ExtentReports report=new ExtentReports();
		
		report.attachReporter(sparkReporter);
		
		return report;
		
	}
	

}
