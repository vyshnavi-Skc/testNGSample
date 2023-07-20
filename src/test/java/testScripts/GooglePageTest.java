package testScripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class GooglePageTest {

	
	WebDriver driver;
	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	
	@BeforeTest
	public void setupExtent() {
		extentReports=new ExtentReports();
		spark=new ExtentSparkReporter("test-output/SparkReport.html");
		extentReports.attachReporter(spark);
	}
@BeforeMethod
	//@BeforeTest
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	//@Test(alwaysRun=true, dependsOnMethods="seleniumSearchTest")
	public void javasearchTest() {
		extentTest = extentReports.createTest("Java Search Test");
		driver.get("https://www.google.com/");
		WebElement schBox=driver.findElement(By.name("q"));
		schBox.sendKeys("Java Tutorial");
		schBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	
	}
	@Test(retryAnalyzer=RetryTest.class)
	public void seleniumSearchTest() {
		extentTest = extentReports.createTest("Selenium Search Test");
		driver.get("https://www.google.com/");
		WebElement schBox=driver.findElement(By.name("q"));
		schBox.sendKeys("Selenium Tutorial");
		schBox.submit();
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
	
	}
	//@Test
	public void appiumSearchTest() {
		extentTest = extentReports.createTest("Appium Search Test");
		driver.get("https://www.google.com/");
		WebElement schBox=driver.findElement(By.name("q"));
		schBox.sendKeys("Appium Tutorial");
		schBox.submit();
		Assert.assertEquals(driver.getTitle(), "Appium Tutorial - Google Search");
	
	}
	
	@AfterTest
	public void finishExtent() {
		extentReports.flush();
	}
	@AfterMethod
	//@AfterTest
	public void teardown() {
		driver.close();
	}
}
