package test.assignment;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class HomePageTest {
	private WebDriver driver;
	
	@BeforeMethod
	public void beforeTest() {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
	}
	
	@AfterMethod
	public void afterTest() {
		driver.close();
	}
	@Test
	public void homepageShouldOpen() {
		//given
		String url="https://the-internet.herokuapp.com/";
		
		//when
		driver.get("https://the-internet.herokuapp.com");
		
		//then
		Assert.assertEquals(driver.getCurrentUrl(),url);
	}

}
