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

public class BrokenImagesTest {
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
	public void brokenImagesPageShouldOpen() {
		//given
		String url="https://the-internet.herokuapp.com/broken_images";
		driver.get("https://the-internet.herokuapp.com");
		
		//when
		driver.findElement(By.linkText("Broken Images")).click();
						
		//then
		Assert.assertEquals(driver.getCurrentUrl(),url);
	}
	@Test
	public void image1ShouldBeDisplayedCorrectly () {
		//given
		String height="0";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Broken Images")).click();
		
		//when
		driver.findElement(By.xpath("//*[@id='content']/div/img[1]")).getAttribute("naturalHeight");
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/img[1]")).getAttribute("naturalHeight"), height);
	}
	
	@Test
	public void image2ShouldBeDisplayedCorrectly () {
		//given
		String height="0";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Broken Images")).click();
		
		//when
		driver.findElement(By.xpath("//*[@id='content']/div/img[2]")).getAttribute("naturalHeight");
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/img[2]")).getAttribute("naturalHeight"), height);
	}
	
	@Test
	public void image3ShouldBeDisplayedCorrectly () {
		//given
		String height="0";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Broken Images")).click();
		
		//when
		driver.findElement(By.xpath("//*[@id='content']/div/img[3]")).getAttribute("naturalHeight");
		
		//then
		Assert.assertNotEquals(driver.findElement(By.xpath("//*[@id='content']/div/img[3]")).getAttribute("naturalHeight"), height);
	}
}
