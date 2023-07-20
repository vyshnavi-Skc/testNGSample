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

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class LoginTestDataExcel {

	
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
	
	public String readFromExcel(String objName) {
		String objPath="";
		String path = System.getProperty("user.dir")+"//src//test//resources//dataFiles//loginData.xlsx";
		FileInputStream fin;
		//XSSF ->.xlsx
		XSSFWorkbook workbook = null;
		//HSSF ->.xls
		try {
			fin=new FileInputStream(path);
			workbook=new XSSFWorkbook(fin);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet loginSheet= workbook.getSheet("loginData");
		int rowNum=loginSheet.getLastRowNum();
		System.out.println("Number of rows....."+rowNum);
		for(int i=1;i<=rowNum;i++) {
			XSSFRow row=loginSheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName));
			objPath=row.getCell(1).getStringCellValue();
		}
		return objPath;
	}
	
	//@AfterMethod
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
