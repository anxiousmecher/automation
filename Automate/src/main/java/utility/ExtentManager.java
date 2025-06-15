package utility;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentManager{
	private static ExtentReports extent;
	public static ExtentReports getInstance()
	{
		if(extent==null) {
			String reportpath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";
		ExtentSparkReporter spark=new ExtentSparkReporter(reportpath);
		extent=new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Environment", "Validation");
		extent.setSystemInfo("Tester", "Aman Garg");
		
	}return extent;}
		
}