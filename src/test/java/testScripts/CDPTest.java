package testScripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v113.emulation.Emulation;
import org.openqa.selenium.devtools.v113.network.Network;
import org.openqa.selenium.devtools.v113.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class CDPTest {
ChromeDriver driver;
DevTools devTools;
	@BeforeTest
	public void setup() {
		driver=new ChromeDriver();
		devTools=driver.getDevTools();
		devTools.createSession();
		
	}
	//devtools running in specific pixels, like mobile or any device resolution
	public void deviceModeTest() {
		Map deviceMetrics=new HashMap() {{
			put("width",600);
			put("height", 1000);
			put("deviceScaleFactor", 50);
			put("mobile", true);
		}};
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",deviceMetrics);
		driver.get("https://www.selenium.dev/");
		}
	/*
	 * //using devtools to select specific location based on latitude and langitude
	//https://chromedevtools.github.io/devtools-protocol/
	//https://oldnavy.gap.com/stores/
	//https://www.latlong.net/
	 **/
	//@Test
	public void geoLocTest() {
		devTools.send(Emulation.setGeolocationOverride(
				Optional.of(36.778259), 
				Optional.of(-119.417931),
				Optional.of(100))
				);
		driver.get("https://oldnavy.gap.com/stores");
	}
	
	//114 version
	@Test
	public void basicAuthTest() {
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		Map<String, Object> headers = new HashMap<>();
		
	String strUser="admin";
	String strPwd="admin";
	
	String basicAuth ="Basic "+new String(new Base64().encode(
			String.format("%s:%s", strUser,strPwd).getBytes()));
	System.out.println("Auth......."+basicAuth);
	//set Header-Auth Token
	headers.put("Authorization",  basicAuth);
	devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
	driver.get("https://the-internet.herokuapp.com/basic_auth");
	String strMsg=driver.findElement(By.cssSelector("div.example p")).getText();
	Assert.assertEquals(strMsg, "Congratulations! You must have the proper credentials.");
		
	}
	
	
}
