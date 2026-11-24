package sele;


import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Session9CheckboxesAndAlertsPractice {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            //PART 1: CHECKBOX STRATEGIES
//            driver.get("https://testautomationpractice.blogspot.com/");
//
//            //1. Capture all 7 day checkboxes: //input[@class='form-check-input' and @type='checkbox']
//            List<WebElement> checkboxes=driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
//            System.out.println("[CHECKBOX] Total Checkboxes: "+checkboxes.size());
//
//            //2. Select ALL checkboxes using an enhanced for loop:
//            //for(WebElement cb:checkboxes) { cb.click(); }
//            for(WebElement cb:checkboxes) {
//            	cb.click();
//            }
//            System.out.println("[CHECKBOX] All Selected - First Checked: "+checkboxes.get(0).isSelected());
//
//            //3. Unselect ONLY currently selected checkboxes using isSelected():
//            //for(WebElement cb:checkboxes) { if(cb.isSelected()) { cb.click(); } }
//            for(WebElement cb:checkboxes) {
//            	if(cb.isSelected()) {
//            		cb.click();
//            	}
//            }
//            System.out.println("[CHECKBOX] All Unselected - First Checked: "+checkboxes.get(0).isSelected());
//
//            //4. Dynamic Selection - Select ONLY the LAST 3 checkboxes using size() calculation:
//           for(int i=checkboxes.size()-3;i<checkboxes.size();i++) { 
//        	   checkboxes.get(i).click();
//        	   }
////            for(int i=0;i<0;i++) {
////            }
          //  System.out.println("[CHECKBOX] Last 3 Selected - Index 3: "+checkboxes.get(3).isSelected()+" | Index 4: "+checkboxes.get(4).isSelected());

            //PART 2: JAVASCRIPT ALERTS (NORMAL, CONFIRMATION, PROMPT)
//            driver.get("https://the-internet.herokuapp.com/javascript_alerts");
//
//            //5. Normal Alert - Click button, switch, read text, accept: //button[text()='Click for JS Alert']
//            driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
//            //Alert normalAlert=driver.switchTo().alert();
//            Alert normalAlert=driver.switchTo().alert();
//            System.out.println("[ALERT - NORMAL] Text: "+normalAlert.getText());
//            //normalAlert.accept();
//            normalAlert.accept();
//
//            //6. Confirmation Alert - Click button, switch, and dismiss (Cancel): //button[text()='Click for JS Confirm']
//            driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
//            //Alert confirmAlert=driver.switchTo().alert();
//            Alert confirmAlert=driver.switchTo().alert();
//            Thread.sleep(2000);
//            //confirmAlert.dismiss();
//            confirmAlert.dismiss();
//            System.out.println("[ALERT - CONFIRM] Result: "+driver.findElement(By.id("result")).getText());
//            Thread.sleep(3000);
//            //7. Prompt Alert - Click button, switch, sendKeys("Selenium SDET"), and accept: //button[text()='Click for JS Prompt']
//            driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
//            Thread.sleep(2000);
//            //Alert promptAlert=driver.switchTo().alert();
//            Alert promptAlert=driver.switchTo().alert();
//            //promptAlert.sendKeys("Selenium SDET");
//            promptAlert.sendKeys("Welcome BSS");
//            Thread.sleep(2000);
//            //promptAlert.accept();
//            promptAlert.accept();
//            Thread.sleep(2000);
//            System.out.println("[ALERT - PROMPT] Result: "+driver.findElement(By.id("result")).getText());

            //PART 3: ADVANCED ALERT HANDLING (EXPLICIT WAIT & AUTHENTICATED POPUP)
            //8. Handle Alert using Explicit Wait (alertIsPresent) WITHOUT driver.switchTo():
//        	driver.get("https://the-internet.herokuapp.com/javascript_alerts");
//            WebDriverWait myWait=new WebDriverWait(driver,Duration.ofSeconds(10));
//            driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
//            //Alert waitAlert=myWait.until(ExpectedConditions.alertIsPresent());
//            Alert waitAlert=myWait.until(ExpectedConditions.alertIsPresent());
//            Thread.sleep(3000);
//            //waitAlert.accept();
//            waitAlert.accept();
//            System.out.println("[ALERT - EXPLICIT WAIT] Handled: "+driver.findElement(By.id("result")).getText());

            //9. Authenticated Popup - Inject username (admin) and password (admin) into URL:
            driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
            //driver.get("");
            WebElement authMessage=driver.findElement(By.xpath("//div[@class='example']/p"));
            System.out.println("[AUTH POPUP] Success Message: "+authMessage.getText());

        } finally {
            driver.quit();
        }
    }
}