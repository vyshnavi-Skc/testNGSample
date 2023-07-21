package testScripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v113.emulation.Emulation;
import org.openqa.selenium.devtools.v113.network.Network;
import org.openqa.selenium.devtools.v113.network.model.Headers;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


public class RemoteDriverTest {
WebDriver driver;
@Test
public void test() throws MalformedURLException {
	ChromeOptions options=new ChromeOptions();
	
	options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
	String strHub="http://172.31.31.19:4444";
	driver = new RemoteWebDriver(new URL(strHub), options);
	
	//java -jar selenium-server4.10.0.jar hub
	//java -jar selenium-server4.10.0.jar node --hub http://172.31.31.19:4444
	//java -jar selenium-server4.10.0.jar node
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	 driver.manage().window().maximize();
	 
	 driver.get("https://www.google.com/");
	 WebElement schBox=driver.findElement(By.name("q"));
		schBox.sendKeys("Java Tutorial");
		schBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	
}
@AfterTest
public void closeDriver() {
	driver.close();
}
	
}
