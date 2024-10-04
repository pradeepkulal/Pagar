package pagar.TestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import pagar.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent= ExtentReporterNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS,result.getMethod().getMethodName()+" Test is Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
	extentTest.get().fail(result.getThrowable());
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String dest = null;
		try {
			dest = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(dest,result.getMethod().getMethodName());
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	@Override
	public void onFinish(ITestContext result) {
		extent.flush();
	}
	
}
