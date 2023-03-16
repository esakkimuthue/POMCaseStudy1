package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class HomePage extends TestBase {
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void SelectItems(String category, String itemName) throws InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='Home ']")).click();
		Thread.sleep(2000);
		String currentCategory = "//a[text()='"+category+"']";
		driver.findElement(By.xpath(currentCategory)).click();
		Thread.sleep(2000);
		String currentItem = "//a[text()='"+itemName+"']";
		driver.findElement(By.xpath(currentItem)).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement btn = driver.findElement(By.xpath("//a[text()='Add to cart']"));
		wait.until(ExpectedConditions.elementToBeClickable(btn));
		btn.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(itemName+","+alert.getText());
		alert.accept();
}}
