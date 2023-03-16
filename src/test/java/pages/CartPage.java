package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.TestBase;

public class CartPage extends TestBase {
	
	@FindBy(xpath = "//li/a[@id='cartur']")
	WebElement Cart;
	
	@FindBy(xpath = "//div/h3")
	WebElement BefDel;
	
	@FindBy(xpath = "//div/h3")
	WebElement AftDel;
	
	@FindBy(xpath = "//td/a")
	WebElement Delbtn;
	
	@FindBy(xpath="//td/a")
	WebElement DelClk;
	
	@FindBy(xpath = "//button[@class='btn btn-success']")
	WebElement Orderbtn;
	
	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void DeleteItem() throws InterruptedException {
		
		Cart.click();
		Thread.sleep(3000);
		String then = BefDel.getText();
		DelClk.click();
		String now = AftDel.getText();
		if(then.equals(now)) {
			System.out.println("Item Deleted");
		}
		else {
			System.out.println("Item not Deleted");
		}
		
		
	}
	
	public void PlaceOrder() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("button[data-toggle='modal']")).click(); 
	    Thread.sleep (3000);
	    driver.findElement(By.cssSelector("#name")).sendKeys("Esakki");
		driver.findElement(By.cssSelector("#country")).sendKeys("India"); 
		driver.findElement(By.cssSelector("#city")).sendKeys("TVL");
	    driver.findElement(By.cssSelector("#card")).sendKeys("no card");
		driver.findElement(By.cssSelector("#month")).sendKeys(" month"); 
		driver.findElement(By.cssSelector("#year")).sendKeys(" year"); 
		Thread.sleep (3000);
		driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click(); 
		Thread.sleep (1000);
		WebElement postPurchase = driver.findElement(By.xpath("//h2[contains(text(),'Thank you')]"));
	    Assert.assertEquals(postPurchase.getText(), "Thank you for your purchase!");
	    driver.findElement(By.cssSelector("button[tabindex='1']")).click();
	}

}
