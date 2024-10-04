package pagar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pagar.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3") 
	private List<WebElement> item;
	@FindBy(css=".totalRow button") WebElement checkOutBtn;
	@FindBy(id="login") WebElement loginBtn;
	
	public CheckOutPage goToCheckOutPage() {
		checkOutBtn.click();
		return new CheckOutPage(driver);
	}
	
	public Boolean checkIfItemListedOrNot(String productName) {
		Boolean match = item.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	
}
