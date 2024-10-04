package pagar.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pagar.PageObjects.CartPage;
import pagar.PageObjects.CheckOutPage;
import pagar.PageObjects.ConfirmOrderPage;
import pagar.PageObjects.ProductCataloguePage;
import pagar.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidation()throws InterruptedException, IOException {
		ProductCataloguePage pcPage=lp.loginApplication("pradeepkX.k@gmail.com","Pradeep@9900");
		String actualErrorMsg = lp.getToastMsg();
		Assert.assertEquals(actualErrorMsg, "Incorrect email or password.");
}
	@Test
	public void submitOrder()throws InterruptedException, IOException {
		String product1="ZARA COAT 3";
		ProductCataloguePage pcPage=lp.loginApplication("pradeep.k@gmail.com","Pradeep@9900");
		List<WebElement> products= pcPage.getProductList();
		pcPage.addProductToCart(product1);
		CartPage cartPage=pcPage.goToCartPage();
		Boolean result= cartPage.checkIfItemListedOrNot(product1);
		Assert.assertTrue(result);
		CheckOutPage checkoutP= cartPage.goToCheckOutPage();
		checkoutP.selectCountry("india");
		ConfirmOrderPage confirmOrderPage= checkoutP.submitOrder();
		String actualMsg=confirmOrderPage.getConfirmationMessage();
		Assert.assertEquals(actualMsg,"THANKYOU FOR THE ORDER.");
	}
}