package com.sinha.testCases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sinha.base.TestBase;
import com.sinha.utilities.DataSuplier;

public class OpenAccountTest extends TestBase {
	
	public Map<String, String> map = new HashMap();
	
	@Test(priority=1,dataProvider = "getCustomerDetails", dataProviderClass = DataSuplier.class)
	public void openAccountTest(String firstName, String lastName, String pCode)
	{
		List<WebElement> buttons = driver.findElements(By.cssSelector(OR.getProperty("managerButtons")));
		WebElement addCustomerButton = buttons.get(1);
		addCustomerButton.click();
		
		WebElement customerDropdown = driver.findElement(By.cssSelector(OR.getProperty("openAccountCustomerSelect")));
		WebElement currencyDropdown = driver.findElement(By.cssSelector(OR.getProperty("openAccountCurrency")));
		
		Select select1 = new Select(customerDropdown);
		select1.selectByVisibleText(firstName + " " + lastName);
		
		Select select2 = new Select(currencyDropdown);
		select2.selectByVisibleText("Dollar");
		
		driver.findElement(By.cssSelector(OR.getProperty("openAccountProcessButton"))).click();
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		map.put(pCode, alert.getText().split(":")[1]);
//		accountId = alert.getText().split(":")[1];
		alert.accept();
		
//		if(alert.getText().split(":")[1] == null)
//		{
//			Assert.fail("account is not created for %s".formatted(firstName + " " + lastName));
//		}
	}
	
	@Test(priority=2,dataProvider = "getCustomerDetails", dataProviderClass = DataSuplier.class)
	public void verifyAccountId(String firstName, String lastName, String pCode) throws Exception
	{
		List<WebElement> buttons = driver.findElements(By.cssSelector(OR.getProperty("managerButtons")));
		WebElement addCustomerButton = buttons.get(2);
		addCustomerButton.click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("tbody")));
		
		String accountLoc = "tbody tr:nth-child(%d) td:nth-child(4) span";

		List<WebElement> customerNames =  driver.findElements(By.cssSelector("tbody tr td:nth-child(3)"));
		int count = 0;
		for(WebElement ele : customerNames)
		{
			count++;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", ele);
			String text = ele.getText();
			
			
			System.out.println(text + " : " + pCode);
			if(text.equals(pCode))
			{
				accountLoc = accountLoc.formatted(count);
				System.out.println("Inside IF");
			}		

		}
		List<WebElement> accounts =  driver.findElements(By.cssSelector(accountLoc));
		for(WebElement acnts : accounts)
		{
			String number = acnts.getText();
			if(!number.trim().equals(map.get(pCode).trim()))
			{
				Assert.fail("Account number is not matched, actual : %s, expected: %s".formatted(number, map.get(pCode)));
			}
		}
	}

}
