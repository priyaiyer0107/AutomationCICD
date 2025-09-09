package SKAutomation.Resources;


import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;

public class ExtentReportsDemo {

	

	public static ExtentReports config()
	{
		String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Test");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("SKAutomation", "Tester");
		return extent;
		
		
	}
}
