package com.sinha.listener;

import java.lang.reflect.Method;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.sinha.base.TestBase;
import com.sinha.utilities.CaptureScreenShots;

public class CustomListener extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		
		System.out.println(result.getName() + " " + result.getStatus());
		extentTest.pass(MarkupHelper.createLabel(result.getName() + " is passed", ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		
    	extentTest.addScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64), "this is failed");
//		String filepath = CaptureScreenShots.getScreenShot(result.getName());
//    	extentTest.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(filepath).build());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String browserDetails = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion();
        String author = context.getCurrentXmlTest().getParameter("author");

        extentTest = extentReport.createTest(context.getName());
        extentTest.assignDevice(browserDetails);
        extentTest.assignAuthor(author);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
	
}
