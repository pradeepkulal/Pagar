package pagar.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;
import pagar.PageObjects.CartPage;
import pagar.PageObjects.CheckOutPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	@FindBy(css="[routerlink*='cart']")
	WebElement cartPageBtn;
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement ordersPageBtn;

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCartPage() {
		JavascriptExecutor js = (JavascriptExecutor)driver; 
        js.executeScript("arguments[0].click();", cartPageBtn);
		CartPage cartPage= new CartPage(driver);
		return new CartPage(driver);
	}
	
	public OrdersPage gotOrdersPage() {
		ordersPageBtn.click();
		OrdersPage ordersPage=new OrdersPage(driver);
		return new OrdersPage(driver);
	}
	
	
	



}
