package pagar.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pagar.PageObjects.LoginPage;

public class BaseTest {
	public WebDriver driver;
	public LoginPage lp;
	public WebDriver intializeBrowser() throws IOException {
		Properties prop =new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//pagar//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();	
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String,String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		//reading json to string 
		String jsonContent= FileUtils.readFileToString(new File(jsonFilePath),
				"UTF-8");
		// String to hashmap usind jackson databind
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver =intializeBrowser();
		lp=new LoginPage(driver);
		lp.goTo();
		return new LoginPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//screnshots//"+testCaseName+".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"//screnshots//"+testCaseName+".png";
	}
}
