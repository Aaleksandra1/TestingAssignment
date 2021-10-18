package test.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicAuthenticationTest {
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
	
	@Test
	public void basicAuthenticationShouldLogIn() {
		//given
	    String login="admin";
	    String password="admin";
	    String http="https://";
	    String result="Congratulations! You must have the proper credentials.";
	    driver.get("https://the-internet.herokuapp.com");
			    
	    //when
	   	driver.findElement(By.linkText("Basic Auth")).click();
		String url=driver.getCurrentUrl();
		driver.get(http+login+":"+password+"@"+url.substring(http.length()));
	    String message=driver.findElement(By.xpath("//*[@id='content']/div/p")).getText();
	    
	    //then
	    Assert.assertEquals(message, result);
	 }
}
