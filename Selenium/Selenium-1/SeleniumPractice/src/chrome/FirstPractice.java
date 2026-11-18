package chrome;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstPractice {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		
		//Logging into my college survey page
		driver.get("https://tinyurl.com/494x6rh6");
		driver.findElement(By.xpath("//input[1]")).sendKeys("23A81A05E9");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("23A81A05E9");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Locate the <h2> element
		WebElement genai = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='AGENTIC AI']"))
		);

		//System.out.println(genai.isDisplayed());
		String name=genai.getText();
		//System.out.println(name);
		//retriving all courses text names
		List<WebElement> courses=driver.findElements(By.xpath("//h2"));
		System.out.println("No. of courses are:"+courses.size());
		System.out.println("The courses retrived are:");
		courses.forEach(
				course->{
					System.out.println(course.getText());
				}
				);		
		List<WebElement> goodButtons=driver.findElements(By.xpath("//button[text()='Good']"));
		System.out.println("No. of good buttons:"+goodButtons.size());
		
		goodButtons.forEach(WebElement::click);
		
		WebElement submitButton=driver.findElement(By.xpath("//button[text()='Submit All Answers']"));
		System.out.println("Is submit button available??"+submitButton.isDisplayed());
		submitButton.click();
		
		//Checking if I have completed the survey ?
		//Thread.sleep(1500);
		//driver.close();
	}
	
	
	
	static void css() {
//		driver.get("https://testautomationpractice.blogspot.com/");
//		
//		//use name and class also
//		
//				//using cssselectors 
//		//using id
//		driver.findElement(By.id("name")).sendKeys("username1");
//		driver.findElement(By.id("email")).sendKeys("mymail@gmail.com");
//		driver.findElement(By.id("phone")).sendKeys("9210100012");
		//driver.findElement(By.cssSelector("textarea#textarea")).sendKeys("home,my street,my town,my State");
//		driver.findElement(By.cssSelector("textarea[id='textarea']")).sendKeys(",My country");
//		
//		String title=driver.getTitle();
//		System.out.println(title);
//		
//		boolean logoDisplayed1=driver.findElement(By.id("name")).isDisplayed();
//		boolean logoDisplayed2=driver.findElement(By.cssSelector("textarea[id='textarea']")).isDisplayed();
//		System.out.println(logoDisplayed1);
//		System.out.println(logoDisplayed2);
////		boolean logoDisplayed3=driver.findElement(By.cssSelector("textarea[id='textarearandom']")).isDisplayed();		
////		System.out.println(logoDisplayed3);
//		Thread.sleep(1100);
//		driver.findElement(By.linkText("Ude)my Courses")).click();
//		System.out.println("Navigating back to home after opening udemy courses");
//		driver.findElement(By.linkText("Courses")).click();
//		System.out.println("Back to home");
//		System.out.println("End");
	}

}
