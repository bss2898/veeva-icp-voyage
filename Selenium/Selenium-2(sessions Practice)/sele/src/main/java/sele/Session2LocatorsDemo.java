package sele;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Session2LocatorsDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.reliancedigital.in/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().window().maximize();
		try {
			WebElement searchbar=driver.findElement(By.cssSelector("input.search-input"));
			searchbar.click();
			searchbar.sendKeys("Samsung");
			//By tag and class
			//driver.findElement(By.cssSelector("div.product-details")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-details")));
			driver.findElement(By.cssSelector("div.product-details")).click();
			
			//After searching for samsun we are now searching a product again
			Thread.sleep(4000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='text']")));
			searchbar=driver.findElement(By.cssSelector("input[type='text']"));
			searchbar.sendKeys("Iphone");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-details")));
			driver.findElement(By.cssSelector("div.product-details")).click();
		}
		finally {
			
		}
	}

}
