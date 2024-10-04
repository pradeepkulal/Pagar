package pagar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pagar.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".form-group input") 
	private WebElement dropDownTextField;
	@FindBy(css=".totalRow button") WebElement checkOutBtn;
	@FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]") WebElement selectCountry;
	@FindBy(css=".actions a") 
	private WebElement placeOrderBtn;
	By countrySearchResults=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a =new Actions(driver);
		a.sendKeys(dropDownTextField,countryName).build().perform();;
		waitForElementToAppear(countrySearchResults);
		selectCountry.click();
		
	}
	
	
	public ConfirmOrderPage submitOrder() {	
	placeOrderBtn.click();
	return new ConfirmOrderPage(driver);
	}

}
