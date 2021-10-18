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

public class AddRemoveTest {
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
	public void addRemoveElementPageShouldOpen() {
		//given
		String url="https://the-internet.herokuapp.com/add_remove_elements/";
		driver.get("https://the-internet.herokuapp.com");
		
		//when
		driver.findElement(By.linkText("Add/Remove Elements")).click();
						
		//then
		Assert.assertEquals(driver.getCurrentUrl(),url);
	}
	
	@Test
	public void shouldBeAbleToAddElement() {
		//given
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Add/Remove Elements")).click();
		
		//when
		driver.findElement(By.xpath("//*[@id='content']/div/button")).click();
		
		//then
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='elements']/button[1]")).isDisplayed());
	}
	
	@Test
	public void shouldBeAbleToRemoveElement() {
		//given
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Add/Remove Elements")).click();
		driver.findElement(By.xpath("//*[@id='content']/div/button")).click();
		
		//when
		driver.findElement(By.xpath("//*[@id='elements']/button[1]")).click();
		
		//then
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='elements']/button[1]")).isEmpty());
	}

	@Test
	public void deleteButtonShouldNotAppearIfNoAddedElementExists() {
		//given
		driver.get("https://the-internet.herokuapp.com");
				
		//when
		driver.findElement(By.linkText("Add/Remove Elements")).click();
		
		//then
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='elements']/button[1]")).isEmpty());
	}
	
	@Test
	public void everyClickOnAddElementAddNewDeleteElement() {
		//given
		int expected=3;
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Add/Remove Elements")).click();
		
		//when
		for (int i=0;i<3;i++) {
		driver.findElement(By.xpath("//*[@id='content']/div/button")).click();
		}
		int actual=driver.findElements(By.cssSelector("button[class='added-manually']")).size();
				
		//then
		Assert.assertEquals(actual,expected);
		
	}
	
	@Test
	public void ifTwoOrMoreElementsWereAddedSecondDeleteButtonShouldRemoveSecondElement() {
		//given
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Add/Remove Elements")).click();
		
		//when
		for (int i=0;i<2;i++) {
		driver.findElement(By.xpath("//*[@id='content']/div/button")).click();
		}
		driver.findElement(By.xpath("//*[@id='elements']/button[2]")).click();
		
		//then
		Assert.assertTrue(driver.findElements(By.xpath("//*[@id='elements']/button[2]")).isEmpty());
		
	}
	
	@Test
	public void shouldNotBeDeleteButtonifTwoElementsAreAddedAndDeleted() {
		//given
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Add/Remove Elements")).click();
		
		//when
		for (int i=0;i<3;i++) {
		driver.findElement(By.xpath("//*[@id='content']/div/button")).click();
		}
		driver.findElement(By.xpath("//*[@id='elements']/button[1]")).click();
		driver.findElement(By.xpath("//*[@id='elements']/button[2]")).click();
		
		//then
		Assert.assertTrue(driver.findElements(By.cssSelector("input[class='added-manually']")).isEmpty());
		
	}
}
