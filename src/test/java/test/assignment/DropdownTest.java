package test.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownTest {
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
	public void dropdownPageShouldOpen() {
		//given
		String url="https://the-internet.herokuapp.com/dropdown";
		driver.get("https://the-internet.herokuapp.com");
		
		//when
		driver.findElement(By.linkText("Dropdown")).click();
						
		//then
		Assert.assertEquals(driver.getCurrentUrl(),url);
	}
	
	@Test
	public void defaultOptionFromDroplistIsSelectedAfterEnteringDroplistPage() {
		//given
		String expected="Please select an option";
		driver.get("https://the-internet.herokuapp.com");

		//when
		driver.findElement(By.linkText("Dropdown")).click();
		WebElement staticList=driver.findElement(By.cssSelector("#dropdown"));
		Select dropdown=new Select(staticList);
		String option=dropdown.getFirstSelectedOption().getText();
		
		//then
		Assert.assertEquals(option,expected);
	}

	@Test
	public void shouldBeAbleToChooseFromDropdown() {
		//given
		String expectedOption="Option 1";
		driver.get("https://the-internet.herokuapp.com");
		driver.findElement(By.linkText("Dropdown")).click();
		
		//when
		WebElement staticList=driver.findElement(By.cssSelector("#dropdown"));
		Select dropdown=new Select(staticList);
		dropdown.selectByValue("1");
		String option=dropdown.getFirstSelectedOption().getText();
		
		//then
		Assert.assertEquals(option,expectedOption);
	}

}
