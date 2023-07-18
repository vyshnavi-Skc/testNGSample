package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTwoTest {
  @Test
  public void cypressSearchTest() {
	WebDriver  driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://www.google.com/");
		WebElement schBox=driver.findElement(By.name("q"));
		schBox.sendKeys("Testng Tutorial");
		schBox.submit();
		Assert.assertEquals(driver.getTitle(), "Testng Tutorial - Google Search");
	driver.close();
  }
}
