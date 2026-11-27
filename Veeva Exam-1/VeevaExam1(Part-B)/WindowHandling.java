package VeevaExam1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WindowHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/*
		 3.	Multi-Window / Tab Handling
		Problem Statement: Open a webpage containing a link that opens in a new browser tab. 
		Click the link, switch to the newly opened tab, extract and print its page title, 
		close the new tab, and switch control back to the parent window.
		Evaluation Criteria:
		Capturing parent window ID via getWindowHandle().
		Iterating through getWindowHandles() to identify and switch to the child window.
		Closing only the active tab (driver.close()) rather than the entire session (driver.quit()), followed by switching 
		back to the parent handle.
		 * */
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
		try {
			driver.get("https://the-internet.herokuapp.com/windows");
			System.out.println("Original window page title:"+driver.getTitle());
			driver.findElement(By.xpath("//a[@href='/windows/new']")).click();
			Thread.sleep(3000);
			Set<String> set=driver.getWindowHandles();
			List<String> list=new ArrayList<>(set);
			driver.switchTo().window(list.get(1));
			System.out.println("Switched window page title:"+driver.getTitle());
			driver.close();
			driver.switchTo().window(list.get(0));
			System.out.println("After closing the child tab");
			System.out.println("Title:"+driver.getTitle());
			//System.out.println(list);
		}
		finally {
			//to check the results
			Thread.sleep(4000);
			driver.quit();
		}
	}	

}
