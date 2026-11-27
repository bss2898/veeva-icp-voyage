package VeevaExam1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FormValidation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		 1.	Dynamic Form Submission & Validation
			Problem Statement: Write a Selenium script to navigate to a registration page, fill in text fields (Name, Email, Password), 
			select a country from a standard <select> dropdown by visible text, check a "Terms & Conditions" checkbox
			only if it is not already selected, and click Submit.
			Evaluation Criteria:
			Proper use of Select class (selectByVisibleText()).
			Conditional check using isSelected() before clicking the checkbox.
			Verification of a success message after submission.
		 * */
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try {
			//getting website
			driver.get("https://testautomationpractice.blogspot.com/");
			driver.manage().window().fullscreen();
			WebElement nameEnter=driver.findElement(By.xpath("//input[@class='form-control' and @id='name']"));
			nameEnter.clear();
			nameEnter.sendKeys("Bala Sai Surya");
			//entering email
			driver.findElement(By.id("email")).sendKeys("bss@gmail.com");
			driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("9120921109");
			WebElement countryDD = driver.findElement(By.id("country"));
			
			Select select=new Select(countryDD);
			if(select.getFirstSelectedOption().isSelected()){
				System.out.println("The country is chosen in defualtly which is:"+select.getFirstSelectedOption().getText());
			}
			System.out.println("Choosing another country by visible text:");
			select.selectByVisibleText("India");
			WebElement selected=select.getFirstSelectedOption();
			System.out.println("IS selected:"+selected.isSelected());
			System.out.println(" Selected country:"+selected.getText());
			
			
		}
		finally {
			//to check the results
			Thread.sleep(4000);
			driver.quit();
		}
	}

}
