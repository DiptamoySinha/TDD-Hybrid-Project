package com.sinha.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestBase {
	
	public static WebDriver driver;
	public static Actions actions;
	public static Properties OR = new Properties();
	public static Properties CONFIG = new Properties();
	public static FileInputStream fis;
    public static Logger logger = null;
    public static WebDriverWait wait;
    
    public static ExtentReports extentReport;
    public static ExtentTest extentTest;
    public static TakesScreenshot ts = ((TakesScreenshot) driver);
    public static SoftAssert softAssert = new SoftAssert();
    public static String browser;



	
	@BeforeSuite
	public void setup() throws Exception
	{
		logger = LogManager.getLogger(TestBase.class.getName());
		
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		logger.info("OR properties loaded");
		
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		CONFIG.load(fis);
		
		if(System.getenv("browser") != null && !System.getenv("browser").isEmpty())
		{
			browser = System.getenv("browser");
		}
		else
		{
			browser = CONFIG.getProperty("browser");
		}
		
		CONFIG.setProperty("browser", browser);
		
		System.out.println(CONFIG.getProperty("browser"));
		
		if(CONFIG.getProperty("browser").equals("chrome"))
		{
			System.out.println("inside if ");
			driver = new ChromeDriver();
			
		}
		else if(CONFIG.getProperty("browser").equals("edge"))
		{
			driver = new EdgeDriver();
		}
		driver.get(CONFIG.getProperty("testUrl"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		actions = new Actions(driver);
		
		extentReport = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter("F:\\LIVE-PROJECT-1\\target\\surefire-reports\\extent.html");
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("OS", System.getProperty("java.version"));
		
	}
	
	@AfterSuite
	public void tearDown()
	{
		if(driver != null)
		{
			driver.quit();
		}
		extentReport.flush();
	}
	
//	@BeforeTest
//	public void setTestDetailsInReport(ITestContext context)
//	{
//        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
//        String browserDetails = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion();
//        String author = context.getCurrentXmlTest().getParameter("author");
//
//        extentTest = extentReport.createTest(context.getName());
//        extentTest.assignDevice(browserDetails);
//        extentTest.assignAuthor(author);
//	}
//	

}
