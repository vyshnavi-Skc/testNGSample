package testScripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class LoginTestDataProvider {

	
	WebDriver driver;
	Properties prop;
	
//	@BeforeMethod
	@BeforeTest
	public void setup() throws IOException {
		prop=new Properties();
		String path =System.getProperty("user.dir")+"//src//test//resources//configFiles//config.properties";
		FileInputStream fin = new FileInputStream(path);
		prop.load(fin);
		String strBrowser = prop.getProperty("browser");
		if(strBrowser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if(strBrowser.equalsIgnoreCase("chrome")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test(dataProvider="loginData")
	public void validLogin(String strName,String strPass) {
		driver.get(prop.getProperty("url"));
		driver.findElement(By.cssSelector("#username")).sendKeys(strName);
		driver.findElement(By.id("password")).sendKeys(strPass);
		driver.findElement(By.className("radius")).click();
		boolean isValidUser=driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
		Assert.assertTrue(isValidUser);
			
	}
	@DataProvider(name="loginData")
	public Object[][] getData() throws CsvValidationException, IOException{
		String path =System.getProperty("user.dir")+"//src//test//resources//dataFiles//userData.csv";
		CSVReader reader=new CSVReader(new FileReader(path));
		String cols[];
		ArrayList<Object> dataList=new ArrayList<Object>();
		while((cols=reader.readNext())!=null) {
			Object record[] = {cols[0],cols[1]};
			dataList.add(record);
			
		}
		return dataList.toArray(new Object[dataList.size()][]);
		
	}
	//@AfterMethod
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
