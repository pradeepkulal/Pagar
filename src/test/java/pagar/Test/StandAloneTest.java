package pagar.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String product1="zara coat 3";
		String country1=" India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("pradeep.k@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pradeep@9900");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(product1)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		WebElement webElement=	driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]"));
		JavascriptExecutor js = (JavascriptExecutor)driver; 
	        js.executeScript("arguments[0].click();", webElement);
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(product1));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a =new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//div[@class='form-group']/input")),"ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action_submit")).click();
		String actualMsg= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(actualMsg," Thankyou for the order.  ");
	}

}
