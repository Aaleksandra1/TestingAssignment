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

public class FormAuthenticationTest {
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
	public void formAuthenticationPageShouldOpen() {
		//given
		String url="https://the-internet.herokuapp.com/login";
		driver.get("https://the-internet.herokuapp.com");
		
		//when
		driver.findElement(By.linkText("Form Authentication")).click();
						
		//then
		Assert.assertEquals(driver.getCurrentUrl(),url);
	}
	
	@Test
	public void shouldBeAbleToLogInWithCorrectCredentials() {
		//given
		String username="tomsmith";
		String password="SuperSecretPassword!";
		String message="Welcome to the Secure Area. When you are done click logout below.";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Form Authentication")).click();
		
		//when
		driver.findElement(By.cssSelector("#username")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
		
		//then
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='content']/div/h4")).getText(),message);
	}
	
	@Test
	public void shouldNotBeAbleToLogInWithIncorrectCredentials() {
		//given
		String username="demo";
		String password="test";
		String message="Welcome to the Secure Area. When you are done click logout below.";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Form Authentication")).click();//Go to page with Form Authentication list
		
		//when
		driver.findElement(By.cssSelector("#username")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/h4")).getText(),message);
	}
	@Test
	public void shouldNotBeAbleToLogInWithoutPassingCredentials() {
		//given
		String username="";
		String password="";
		String message="Welcome to the Secure Area. When you are done click logout below.";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Form Authentication")).click();
		
		//when
		driver.findElement(By.cssSelector("#username")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/h4")).getText(),message);
	}
	
	@Test
	public void shouldNotBeAbleToLogInWithoutPassingUsername() {
		//given
		String username="";
		String password="SuperSecretPassword!";
		String message="Welcome to the Secure Area. When you are done click logout below.";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Form Authentication")).click();
		
		//when
		driver.findElement(By.cssSelector("#username")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/h4")).getText(),message);
	}
	
	@Test
	public void shouldNotBeAbleToLogInWithoutPassingPassword() {
		//given
		String username="tomsmith";
		String password="";
		String message="Welcome to the Secure Area. When you are done click logout below.";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Form Authentication")).click();
		
		//when
		driver.findElement(By.cssSelector("#username")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/h4")).getText(),message);
	}
	
	@Test
	public void usernameShouldBeCaseSensetive() {
		//given
		String username="Tomsmith";
		String password="SuperSecretPassword!";
		String message="Welcome to the Secure Area. When you are done click logout below.";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Form Authentication")).click();
		
		//when
		driver.findElement(By.cssSelector("#username")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/h4")).getText(),message);
	}
	
	@Test
	public void passwordShouldBeCaseSensetive() {
		//given
		String username="tomsmith";
		String password="SUperSecretPassword!";
		String message="Welcome to the Secure Area. When you are done click logout below.";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Form Authentication")).click();
		
		//when
		driver.findElement(By.cssSelector("#username")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='login']/button/i")).click();
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/h4")).getText(),message);
	}
}
