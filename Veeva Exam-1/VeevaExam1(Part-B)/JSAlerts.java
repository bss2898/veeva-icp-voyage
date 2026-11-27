package VeevaExam1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSAlerts {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		 2.	Handling JavaScript Alerts & Pop-ups
			Problem Statement: Automate a scenario where clicking a button triggers a JavaScript Confirmation Alert. Print the alert text to the console, 
			accept the alert,
			 and then trigger a Prompt Alert, enter "Automation Test" into it, and dismiss it.
			Evaluation Criteria:
			Switching context using driver.switchTo().alert().
			Correct usage of getText(), accept(), sendKeys(), and dismiss().

		 * */
		WebDriver driver=new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//using webdriverwait
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		try {
			driver.get("https://the-internet.herokuapp.com/javascript_alerts");
			WebElement alertButton=wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Click for JS Alert')]"))
					);
			
			alertButton.click();
			Thread.sleep(3000);
			//switching to the alert
			Alert firstAlert=driver.switchTo().alert();
			System.out.println("Text read:"+firstAlert.getText());
			firstAlert.accept();
			System.out.println("Handled alert reading and accepting!");
			//Prompting and dismissing
			WebElement alertButton2=wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Click for JS Confirm')]"))
					);
			System.out.println("Dismissing alert!");
			alertButton2.click();
			Thread.sleep(3000);
			System.out.println(alertButton2.getText());
			Alert alert2=driver.switchTo().alert();
			alert2.dismiss();
			
			//prompting using sendkeys
			WebElement alertButton3=wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Click for JS Prompt')]"))
					);
			alertButton3.click();
			Thread.sleep(3000);
			Alert alert3=driver.switchTo().alert();
			alert3.sendKeys("Automation Test");
			alert3.accept();
			Thread.sleep(3000);
			//alert3.dismiss();
			
		}
		finally {
			//to check the results
			Thread.sleep(4000);
			driver.quit();
		}
		
	}	

}
