package com.sinha.testCases;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sinha.base.TestBase;
import com.sinha.utilities.DataSuplier;

public class AddCustomerTest extends TestBase {
	
	@Test(dataProvider = "getCustomerDetails", dataProviderClass = DataSuplier.class)
	public void addCustomer(String firstName, String lastName, String pCode) throws Exception
	{
		List<WebElement> buttons = driver.findElements(By.cssSelector(OR.getProperty("managerButtons")));
		WebElement addCustomerButton = buttons.get(0);
		addCustomerButton.click();
		extentTest.info("click on add customer button");
		
		List<WebElement> inputs = driver.findElements(By.cssSelector(OR.getProperty("addCustomerInputs")));
		inputs.get(0).sendKeys(firstName);
		inputs.get(1).sendKeys(lastName);
		inputs.get(2).sendKeys(pCode);
		extentTest.info("details added on every field");

		driver.findElement(By.cssSelector(OR.getProperty("addCustomerSubmitButton"))).click();
		
		extentTest.info("submitted the details");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		Thread.sleep(3000);
	}
	
	@Test(dataProvider = "getCustomerDetails", dataProviderClass = DataSuplier.class)
	public void verifyAddedCustomer(String firstName, String lastName, String pCode) throws Exception
	{
		List<WebElement> buttons = driver.findElements(By.cssSelector(OR.getProperty("managerButtons")));
		WebElement addCustomerButton = buttons.get(2);
		addCustomerButton.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tbody")));
		
		boolean flag = false;
		List<WebElement> customerNames =  driver.findElements(By.cssSelector("tbody tr td:nth-child(1)"));
		for(WebElement ele : customerNames)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
			String text = ele.getText();
			if(text.equals(firstName))
			{
				flag = true;
			}
		}
		if(!flag)
		{
			Assert.fail("%s - %s - %s : Customer has not added".formatted(firstName, lastName, pCode));
		}
		Thread.sleep(3000);

	}
	

	

}
