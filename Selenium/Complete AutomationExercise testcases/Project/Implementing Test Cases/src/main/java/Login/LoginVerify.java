package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginVerify {
    public static void main(String[] args) {
        /*
        1. Launch browser
        2. Navigate to url 'http://automationexercise.com'
        3. Verify that home page is visible successfully
        4. Click on 'Signup / Login' button
        5. Verify 'Login to your account' is visible
        6. Enter correct email address and password
        7. Click 'login' button
        8. Verify that 'Logged in as username' is visible
        9. Click 'Delete Account' button
        10. Verify that 'ACCOUNT DELETED!' is visible
        * */

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(9)
        );
        /*1. Launch browser
        2. Navigate to url 'http://automationexercise.com'*/
        driver.get("http://automationexercise.com");
        System.out.println(
                "[PASS]:Home page verified:[TITLE]:"+driver.getTitle()
        );
        /*
         3. Verify that home page is visible successfully
        4. Click on 'Signup / Login' button
        */
        driver.findElement(By.partialLinkText("Signup")).click();
        WebElement loginVis= driver.findElement(
                By.xpath(
                        "//h2[1]"
                )
        );
        //5. Verify 'Login to your account' is visible
        System.out.println("[PASS]Login account text visible: "+loginVis.isDisplayed());

//        6. Enter correct email address and password
//        7. Click 'login' button
        WebElement loginemail=driver.findElement(
                By.xpath("//input[@type='email'][@data-qa='login-email']")
        );
        loginemail.sendKeys("demo156@gmail.com");
        driver.findElement(
                By.xpath(
                        "//input[@data-qa='login-password']"
                )
        ).sendKeys("password");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
        System.out.println("[PASS] Login successful");

        //8. Verify that 'Logged in as username' is visible
        System.out.println(driver.findElement(By.xpath("//li/a[contains(text(),'Logged in')]")).getText());
        //9. Click 'Delete Account' button
        driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
        //10. Verify that 'ACCOUNT DELETED!' is visible
        System.out.println("[PASS] Account deleted successfully:"+driver.findElement(By.xpath("//b")).getText());

        driver.close();
    }
}
