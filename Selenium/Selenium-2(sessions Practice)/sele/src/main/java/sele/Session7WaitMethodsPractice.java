package sele;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Session7WaitMethodsPractice {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        

        
        
        try {
            //1. IMPLICIT WAIT (10 seconds): driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
            //Locate Start button: //div[@id='start']/button
            driver.findElement(By.xpath("//div[@id='start']/button")).click();
            
             //Wait dynamically via Implicit Wait for 'Hello World!' to enter DOM: //div[@id='finish']/h4
            WebElement implicitResult=driver.findElement(By.xpath("//div[@id='finish']/h4"));
            System.out.println("[IMPLICIT WAIT] Loaded Text: "+implicitResult.getText());

            //Reset Implicit Wait to 0 before using Explicit & Fluent Waits
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

            //2. EXPLICIT WAIT (10 seconds): WebDriverWait explicitWait=new WebDriverWait(driver,Duration.ofSeconds(10));
            WebDriverWait explicitWait=new WebDriverWait(driver,Duration.ofSeconds(10));;

            driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

            //Wait until Start button is clickable, then click: explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button")));
            WebElement startBtn=explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button")));
            startBtn.click();

            //Wait until hidden 'Hello World!' becomes visible: explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
            WebElement explicitResult=explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
            System.out.println("[EXPLICIT WAIT] Visible Text: "+explicitResult.getText());

            //3. FLUENT WAIT (Timeout 15s, Polling 2s, Ignoring NoSuchElementException):
            //Wait<WebDriver> fluentWait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
            Wait<WebDriver> fluentWait=null;

            driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
            driver.findElement(By.xpath("//div[@id='start']/button")).click();

            //Poll until 'Hello World!' appears in DOM: fluentWait.until(d->d.findElement(By.xpath("//div[@id='finish']/h4")));
            WebElement fluentResult=null;
            System.out.println("[FLUENT WAIT] Polled Text: "+fluentResult.getText());

        } finally {
        	Thread.sleep(3000);
            driver.quit();
        }
    }
}