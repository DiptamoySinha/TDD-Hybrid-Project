package com.sinha.testCases;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.sinha.base.TestBase;
import com.sinha.utilities.CaptureScreenShots;

public class BankManagerLoginTest extends TestBase {
	
	
	@Test
	public void bankManagerLogin() throws Exception
	{
		logger.info("Hello from login test");
		
		String title = driver.getTitle();
		softAssert.assertEquals(title, "manager", "The title is : %s".formatted(title));

//		try
//		{
//			softAssert.assertEquals(title, "manager", "The title is : %s".formatted(title));
//
//		}
//		catch(Throwable t)
//		{
//			String base64String = CaptureScreenShots.getScreenShot();
//			extentTest.fail(t, MediaEntityBuilder.createScreenCaptureFromBase64String(base64String).build());
//		}
	
		driver.findElement(By.cssSelector(OR.getProperty("bankmanagerLogin"))).click();
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.urlContains("manager"));
		List<WebElement> buttons = driver.findElements(By.cssSelector(OR.getProperty("managerButtons")));
		for(WebElement ele : buttons)
		{
			String text = ele.getText();
			if(!Arrays.asList(new String[] {"Add Customer","Open Account","Customers"}).contains(text))
			{
				softAssert.fail("%s Button is not present".formatted(text));
			}
		}
		
		softAssert.assertAll();
		
	}

}
