package pagar.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends AbstractComponents{
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	
	@FindBy (css="tr td:nth-child(3)") 
	private List<WebElement> orderedItems;
	
	public Boolean verifyIfOrderIsDisplayed(String productName) {
		Boolean match = orderedItems.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

}
