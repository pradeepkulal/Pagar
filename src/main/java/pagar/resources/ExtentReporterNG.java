package pagar.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReporterObject() {
		//ExtentReports, ExtentSparkReporter
				ExtentSparkReporter reporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\index.html");
				reporter.config().setReportName("Assesment Test");
				reporter.config().setDocumentTitle("Extent reports");
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester Name", "Pradeep");
				return extent;
	}
}
