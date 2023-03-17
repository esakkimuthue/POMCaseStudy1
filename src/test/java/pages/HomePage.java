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
	
	public void selectItems(String category, String item) throws InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='Home ']")).click();
		Thread.sleep(2000);
		String openedCategory = "//a[text()='"+category+"']";
		driver.findElement(By.xpath(openedCategory)).click();
		Thread.sleep(2000);
		String openedItem = "//a[text()='"+item+"']";
		driver.findElement(By.xpath(openedItem)).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement button = driver.findElement(By.xpath("//a[text()='Add to cart']"));
		wait.until(ExpectedConditions.elementToBeClickable(button));
		button.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		System.out.println(item+","+alert.getText());
		alert.accept();
}}
