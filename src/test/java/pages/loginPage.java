package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;
import org.testng.Assert;

public class loginPage extends TestBase {
	
	@FindBy(xpath = "//li/a[@id='login2']")
	WebElement loginButton;
	
	@FindBy(xpath = "//button[@onclick='logIn()']")
	WebElement Submit;
	
	public loginPage() {
		PageFactory.initElements(driver, this);
	}
	
	 public void login() throws InterruptedException {
		 System.out.println("driver="+driver);
		 loginButton.click();
		 driver.findElement(By.xpath("//input[@id='loginusername']")).sendKeys("Esaki1");
		 driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("Muthu");
		 Submit.click();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//li/a[contains(text(),'Home')]")).click();
		 WebElement who = driver.findElement(By.xpath("//li/a[@id='nameofuser']"));
		 Thread.sleep(3000);
		 Assert.assertEquals(who.getText(), "Welcome Esaki1");
		 
	 }

}
