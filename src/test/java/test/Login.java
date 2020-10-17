package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	String browser = "chrome";
	@Test(enabled=false, priority = 2)
	public void negativeLogin() throws InterruptedException {
		WebDriver driver = null;
		if (browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browser.equals("ff"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://magento.com");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"block-header\"]/ul/li[9]/a/span[1]/div")).click();

		driver.findElement(By.id("email")).sendKeys("ptrusha135@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("hello");
		driver.findElement(By.id("send2")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div")));
		String error =driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div/div")).getText();
		System.out.println("error: "+error);
		if (error.equals("Invalid login or password."))
		{
			System.out.println("Test case pass");
		}
	driver.quit();
	}
	@Test(priority = 1)
	public void register()
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://magento.com");
		
		driver.findElement(By.xpath("//*[@id=\"block-header\"]/ul/li[9]/a/span[1]/div")).click();
		driver.findElement(By.xpath("//*[@id=\"register\"]/span")).click();
		driver.findElement(By.id("firstname")).sendKeys("Trusha");
		driver.findElement(By.id("lastname")).sendKeys("Patel");
		driver.findElement(By.id("email_address")).sendKeys("ptrusha135@gmail.com");
		driver.findElement(By.id("password")).sendKeys("welcome11");
		driver.findElement(By.id("password-confirmation")).sendKeys("welcome11");
		Select primary = new Select(driver.findElement(By.id("company_type")));
		primary.selectByVisibleText("Develops Magento extensions");
		Select role = new Select(driver.findElement(By.id("individual_role")));
		role.selectByValue("technical/developer");
		Select country = new Select(driver.findElement(By.id("country")));
		country.selectByIndex(10);
		//driver.findElement(By.id("agree_terms")).click();
		if (!driver.findElement(By.id("agree_terms")).isSelected())
		  {
		 driver.findElement(By.id("agree_terms")).click();
		  }
		driver.quit();
	}

}
