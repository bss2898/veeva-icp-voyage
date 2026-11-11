package chrome;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstPractice {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		
		//Logging into my college survey page
		/*driver.get("https://tinyurl.com/494x6rh6");
		driver.findElement(By.xpath("//input[1]")).sendKeys("23A81A05J5");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("23A81A05J5");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("button[type='submit']")).click();*/
		
		driver.get("https://testautomationpractice.blogspot.com/");
		
		
		//using id
		driver.findElement(By.id("name")).sendKeys("username1");
		driver.findElement(By.id("email")).sendKeys("mymail@gmail.com");
		driver.findElement(By.id("phone")).sendKeys("9210100012");
		//use name and class also
		
		//using cssselectors 
		driver.findElement(By.cssSelector("textarea#textarea")).sendKeys("home,my street,my town,my State");
		driver.findElement(By.cssSelector("textarea[id='textarea']")).sendKeys(",My country");
		
		String title=driver.getTitle();
		System.out.println(title);
		
		
		Thread.sleep(1500);
		driver.close();
	}

}
