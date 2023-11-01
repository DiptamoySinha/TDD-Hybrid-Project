package com.sinha.utilities;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.sinha.base.TestBase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CaptureScreenShots extends TestBase {
	
	
	public static String getScreenShot()
	{
		String base64String = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return base64String;
	}
	
	public static String getScreenShot(String fileName)
	{
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
		String destFile = "F:\\LIVE-PROJECT-1\\src\\test\\resources\\screenshots\\" + fileName + "\\" + formattedDate;
		File dest = new File(destFile);
		
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest.getAbsolutePath();
	}

}
