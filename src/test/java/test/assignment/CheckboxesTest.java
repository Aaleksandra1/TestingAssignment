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

public class CheckboxesTest {
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
	public void checkboxesPageShouldOpen() {
		//given
		String url="https://the-internet.herokuapp.com/checkboxes";
		driver.get("https://the-internet.herokuapp.com");
		
		//when
		driver.findElement(By.linkText("Checkboxes")).click();
						
		//then
		Assert.assertEquals(driver.getCurrentUrl(),url);
	}
	
	@Test
	public void shouldBeTwoCheckboxes() {
		//given
		int expected=2;
		driver.get("https://the-internet.herokuapp.com");
				
		//when
		driver.findElement(By.linkText("Checkboxes")).click();
		int actual=driver.findElements(By.cssSelector("input[type='checkbox']")).size();
		
		//then
		Assert.assertEquals(actual,expected);
	}
	
	@Test
	public void shouldBeAbleToCheckCheckbox() {
		//given
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Checkboxes")).click();
		
		//when
		driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).click();
		
		//then
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='checkboxes']/input[1]")).isSelected());
	}
	
	@Test
	public void shouldBeAbleToUncheckCheckbox() {
		//given
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Checkboxes")).click();
		
		//when
		driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).click();

		//then
		Assert.assertFalse(driver.findElement(By.xpath("//*[@id='checkboxes']/input[2]")).isSelected());
	}
	
}
