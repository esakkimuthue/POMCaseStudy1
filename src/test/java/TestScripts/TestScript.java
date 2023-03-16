package TestScripts;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import base.TestBase;
import pages.cartPage;
import pages.homePage;
import pages.loginPage;

public class TestScript extends TestBase {
	
	ExtentReports reports;
	ExtentSparkReporter spark;
    ExtentTest extentTest;
    
    @BeforeMethod
    public void setupExtent() { 
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target\\SparkReport.html");
		reports.attachReporter(spark);
	}
	
	@BeforeTest
	public void setup() throws IOException {
		chrome();
	}
	
	@Test(priority=1)
	public void loginPagesTest() throws InterruptedException, IOException {
		
		extentTest = reports.createTest("login Test");
		loginPage log = new loginPage();
		log.login();
	 
	}
	
	@Test(priority=2, dataProvider="Catalogue")
	public void addingItems(String category, String itemName) throws InterruptedException {
		extentTest = reports.createTest("AddItems Test");
		homePage items = new homePage();
		items.SelectItems(category, itemName);
	}
	
	@Test(priority=3)
	public void delItem() throws InterruptedException {
		extentTest = reports.createTest("DeleteItems Test");
		cartPage product =new cartPage();
		product.DeleteItem();
			
		}
	
	@Test(priority=4)
	public void PlaceOrder() throws InterruptedException {
		extentTest = reports.createTest("PlaceOrder Test");
		cartPage purchase = new cartPage();
		purchase.PlaceOrder();
	}
	
	@DataProvider(name="Catalogue")
	public Object[][] getData() throws CsvValidationException,IOException
	{
		  String path=System.getProperty("user.dir")+"//src//test//resources//TestData//SelectItems.csv";
		  String[] cols;
		  CSVReader reader = new CSVReader(new FileReader(path));
		  ArrayList<Object> dataList=new ArrayList<Object>();
		  while((cols=reader.readNext())!=null)
		  {
			  Object[] record={cols[0], cols[1]};
			  dataList.add(record);
		  }
		  return dataList.toArray(new Object[dataList.size()][]);
		  
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		if(ITestResult.FAILURE == result.getStatus()) {
			extentTest.log(Status.FAIL, result.getThrowable().getMessage());
		}
	}
	@AfterTest
	public void Extent() {
		driver.close();
		reports.flush();
		
	}

}
