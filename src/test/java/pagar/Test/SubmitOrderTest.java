package pagar.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pagar.AbstractComponents.OrdersPage;
import pagar.PageObjects.CartPage;
import pagar.PageObjects.CheckOutPage;
import pagar.PageObjects.ConfirmOrderPage;
import pagar.PageObjects.LoginPage;
import pagar.PageObjects.ProductCataloguePage;
import pagar.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData",groups={"purchasetest"})
	public void submitOrder(HashMap<String, String> input)throws InterruptedException, IOException {
		ProductCataloguePage pcPage=lp.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products= pcPage.getProductList();
		pcPage.addProductToCart("prduct");
		CartPage cartPage=pcPage.goToCartPage();
		Boolean result= cartPage.checkIfItemListedOrNot("prduct");
		Assert.assertTrue(result);
		CheckOutPage checkoutP= cartPage.goToCheckOutPage();
		checkoutP.selectCountry("india");
		ConfirmOrderPage confirmOrderPage= checkoutP.submitOrder();
		String actualMsg=confirmOrderPage.getConfirmationMessage();
		Assert.assertEquals(actualMsg,"THANKYOU FOR THE ORDER.");
	}
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest()throws InterruptedException, IOException {
		String product1="ZARA COAT 3";
		ProductCataloguePage pcPage=lp.loginApplication("pradeep.k@gmail.com","Pradeep@9900");
		OrdersPage orderPage=pcPage.gotOrdersPage();
		Assert.assertTrue(orderPage.verifyIfOrderIsDisplayed(product1));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map=new HashMap<String,String>();
//		map.put("email", "pradeep.k@gmail.com");
//		map.put("password", "Pradeep@9900");
//		map.put("product", "ZARA COAT 3");
//		HashMap<String, String> map1=new HashMap<String,String>();
//		map1.put("email", "Manjunath@gmail.com");
//		map1.put("password", "Manju@1234");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String,String>> data=	getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/org/pk/Ecommerce/data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
