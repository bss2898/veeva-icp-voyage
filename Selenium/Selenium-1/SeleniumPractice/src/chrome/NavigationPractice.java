package chrome;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationPractice {

	public static void main(String[] args)throws InterruptedException {
		// TODO Auto-generated method stub
//		EdgeDriver driver=new EdgeDriver();
//		driver.navigate().to("https://www.edge.com");
//		driver.findElement(By.className("gLFyf")).sendKeys("Hello google"+Keys.ENTER);
//		WebDriver driver = new ChromeDriver();
		//WebDriver driver=new ChromeDriver();
		
		listOfWebElements();
		
        //driver.quit();

		//youtubeVideo(driver);
	}
	static void listOfWebElements() throws InterruptedException {
		WebDriver driver1=new ChromeDriver();
		driver1.get("https://www.selenium.dev/selenium/web/web-form.html");

		Thread.sleep(3800);
		
		List<WebElement> list=driver1.findElements(By.xpath("class='form-label w-100'"));
//	   	List<WebElement> list=driver1.findElements(By.tagName("img"));
		
		System.out.println(list.size());
		
		driver1.quit();
		
		//System.out.println(list.size());
	}
	static void youtubeVideo(WebDriver driver) throws InterruptedException{
		Thread.sleep(1500);
		driver.manage().window().maximize();
        driver.navigate().to("https://www.google.com");
        Thread.sleep(000);

        driver.navigate().to("https://www.selenium.dev");
        Thread.sleep(2000);

        driver.navigate().back();
        Thread.sleep(2000);

        driver.navigate().to("https://www.youtube.com");
        driver.findElement(By.name("search_query")).sendKeys("Dream or Die Part 1 trailer",Keys.ENTER);
        Thread.sleep(2000);

        driver.navigate().to("https://youtu.be/D6mjGY1FFsA?si=obOzNFMaSNaKls7Y");

        driver.findElement(By.className("ytp-large-play-button ytp-button")).click();
        driver.findElement(By.className("html5-video-container"));

        driver.navigate().refresh();

	}

}
