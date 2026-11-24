package sele;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session10FramesAndIframesPractice {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
        	driver.get("https://ui.vision/demo/webtest/frames/");
        	//PART 1: FRAME 1 (SWITCH BY WEBELEMENT & RETURN TO MAIN PAGE)
            //1. Locate Frame 1 as a WebElement: //frame[@src='frame_1.html']
        	 System.out.println(driver.getWindowHandle());
            WebElement frame1=driver.findElement(By.xpath("//frameset/child::frame[@src='frame_1.html']"));
            //2. Switch driver focus into Frame 1: driver.switchTo().frame(frame1);
            driver.switchTo().frame(frame1);
            System.out.println(driver.getWindowHandle());
            
            //3. Enter text in Frame 1 input box: //input[@name='mytext1']
            driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Frame 1 texttt");
            System.out.println("[FRAME 1] Text entered successfully.");

            //4. Switch back to main page before accessing another frame: driver.switchTo().defaultContent();
            
            driver.switchTo().defaultContent();
            
            //PART 2: FRAME 3 (NESTED IFRAME BY INDEX & JAVASCRIPT EXECUTOR CLICK)
            
            //5. Locate Frame 3 and switch into it: //frame[@src='frame_3.html']
            WebElement frame3=driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
            //driver.switchTo().frame(frame3);
            driver.switchTo().frame(frame3);
            //6. Enter text in Frame 3 input box: //input[@name='mytext3']
            driver.findElement(By.xpath("//input[@name='mytext3']")).sendKeys("Inside Outer Frame 3");

            //7. Switch into the single inner Google Form iFrame using index 0: driver.switchTo().frame(0);
            driver.switchTo().frame(0);
            //8. Locate the 'I am a human' radio button inside the inner iFrame: //div[@aria-label='I am a human']
            WebElement radioBtn=driver.findElement(By.xpath("//div[@aria-label='I am a human']"));

            //9. Click the radio button using JavascriptExecutor (avoids ElementClickInterceptedException):
            JavascriptExecutor js=(JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();",radioBtn);
            System.out.println("[FRAME 3 - INNER IFRAME] Radio button clicked via JavascriptExecutor.");
            Thread.sleep(3000);
            //10. Exit nested iFrame completely back to the main page: driver.switchTo().defaultContent();
            
            driver.switchTo().defaultContent();
            //PART 3: ASSIGNMENT - FRAME 5 INTERACTION & LOGO VERIFICATION
            //11. Locate Frame 5 and switch into it: //frame[@src='frame_5.html']
            WebElement frame5=driver.findElement(By.xpath("//frame[@src='frame_5.html']"));
            driver.switchTo().frame(frame5);
            Thread.sleep(3000);
            System.out.println("Breakpoint-analyze");
            //12. Enter text in Frame 5 input box: //input[@name='mytext5']
            driver.findElement(By.xpath("//input[@name='mytext5']")).sendKeys("Session 10 Assignment");

            //13. Click the link inside Frame 5: By.linkText("https://a9t9.com")
            driver.findElement(By.linkText("https://a9t9.com")).click();

            //14. Verify the UI.Vision logo is displayed inside the newly loaded Frame 5 content: //a[@id='logo']/img
            WebElement logo=driver.findElement(By.xpath("//a[@id='logo']/img"));
            //boolean logoVisible=logo.isDisplayed();
            boolean logoVisible=logo.isDisplayed();
            System.out.println("[ASSIGNMENT - FRAME 5] UI.Vision Logo Displayed: "+logoVisible);
            WebElement UIVision=driver.findElement(By.xpath("//span[text()='1. Get Ui.Vision']"));
            UIVision.click();
        }
        finally {
        	Thread.sleep(3000);
        	driver.close();
        }
	}

}
