package pagar.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pagar.AbstractComponents.AbstractComponents;


public class ConfirmOrderPage extends AbstractComponents {
	WebDriver driver;
	public ConfirmOrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	
	public String getConfirmationMessage() {
		CheckOutPage cp=new CheckOutPage(driver);
		return confirmationMessage.getText();
	}
}
