package testScripts;

import org.testng.annotations.Test;
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
	
//	@BeforeMethod
	@BeforeTest
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test(alwaysRun=true, dependsOnMethods="seleniumSearchTest")
	public void javasearchTest() {
		driver.get("https://www.google.com/");
		WebElement schBox=driver.findElement(By.name("q"));
		schBox.sendKeys("Java Tutorial");
		schBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	
	}
	@Test
	public void seleniumSearchTest() {
		
		driver.get("https://www.google.com/");
		WebElement schBox=driver.findElement(By.name("q"));
		schBox.sendKeys("Selenium Tutorial");
		schBox.submit();
		Assert.assertEquals(driver.getTitle(), "Senium Tutorial - Google Search");
	
	}
	@Test
	public void appiumSearchTest() {
		
		driver.get("https://www.google.com/");
		WebElement schBox=driver.findElement(By.name("q"));
		schBox.sendKeys("Appium Tutorial");
		schBox.submit();
		Assert.assertEquals(driver.getTitle(), "Appium Tutorial - Google Search");
	
	}
	//@AfterMethod
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
