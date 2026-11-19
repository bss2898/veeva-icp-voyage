package sele;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session6WebDriverMethodsPractice {

    public static void main(String[] args) {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //1. Launch URL: driver.get("https://testautomationpractice.blogspot.com/");
        driver.get("https://testautomationpractice.blogspot.com/");

        //2. Get Title: String title=driver.getTitle();
        String title=driver.getTitle();
        System.out.println("[GET] Page Title: "+title);

        //3. Get Current URL: String currentUrl=driver.getCurrentUrl();
        String currentUrl=driver.getCurrentUrl();
        System.out.println("[GET] Current URL: "+currentUrl);

        //4. Get Page Source (check length or contains instead of printing full HTML): String pageSource=driver.getPageSource();
        String pageSource=driver.getPageSource();
        System.out.println("[GET] Page Source Contains Title: "+pageSource.contains("Automation Testing Practice"));

        //5. Get Single Window Handle: String mainWindowId=driver.getWindowHandle();
        String mainWindowId=driver.getWindowHandle();
        System.out.println("[GET] Single Window ID: "+mainWindowId);

        //6. Check isDisplayed() on Page Heading: //h1[@class='title']
        WebElement pageHeading=driver.findElement(By.xpath("//h1[@class='title']"));
        //boolean headingVisible=pageHeading.isDisplayed();
        boolean headingVisible=pageHeading.isDisplayed();
        System.out.println("[CONDITIONAL] Heading Displayed: "+headingVisible);

        //7. Check isEnabled() on Name & Email Input Boxes: By.id("name") and By.id("email")
        WebElement nameInput=driver.findElement(By.id("name"));
        WebElement emailInput=driver.findElement(By.id("email"));
        //System.out.println("[CONDITIONAL] Name Enabled: "+nameInput.isEnabled()+" | Email Enabled: "+emailInput.isEnabled());
        System.out.println("[CONDITIONAL] Name Enabled: "+nameInput.isEnabled()+" | Email Enabled: "+emailInput.isEnabled());
        

        //8. Radio Buttons - isEnabled() & isSelected() before and after click: By.id("male") and By.id("female")
        WebElement maleRadio=driver.findElement(By.id("male"));
        WebElement femaleRadio=driver.findElement(By.id("female"));
        //System.out.println("[RADIO] Male Enabled: "+maleRadio.isEnabled()+" | Selected Before Click: "+maleRadio.isSelected());
        System.out.println("[RADIO] Male Enabled: "+maleRadio.isEnabled()+" | Selected Before Click: "+maleRadio.isSelected());
        maleRadio.click();
        //System.out.println("[RADIO] Male Selected After Click: "+maleRadio.isSelected()+" | Female Selected: "+femaleRadio.isSelected());
        System.out.println("[RADIO] Male Selected After Click: "+maleRadio.isSelected()+" | Female Selected: "+femaleRadio.isSelected());

        //9. Checkboxes - isSelected() before and after click: By.id("sunday") and By.id("monday")
        WebElement sundayCheckbox=driver.findElement(By.id("sunday"));
        WebElement mondayCheckbox=driver.findElement(By.id("monday"));
        //System.out.println("[CHECKBOX] Before Click - Sunday: "+sundayCheckbox.isSelected()+" | Monday: "+mondayCheckbox.isSelected());
        System.out.println("[CHECKBOX] Before Click - Sunday: "+sundayCheckbox.isSelected()+" | Monday: "+mondayCheckbox.isSelected());
        sundayCheckbox.click();
        mondayCheckbox.click();
        //System.out.println("[CHECKBOX] After Click - Sunday: "+sundayCheckbox.isSelected()+" | Monday: "+mondayCheckbox.isSelected());
        System.out.println("[CHECKBOX] After Click - Sunday: "+sundayCheckbox.isSelected()+" | Monday: "+mondayCheckbox.isSelected());

        //10. Open New Tab & capture all window IDs: //button[text()='New Tab']
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();
        //Set<String> allWindowIds=driver.getWindowHandles();
        Set<String> allWindowIds=driver.getWindowHandles();
        System.out.println("[GET] All Window IDs ("+allWindowIds.size()+"): "+allWindowIds);

        //11. Browser Methods: First run with driver.close() to observe the 2nd tab staying open, then switch to driver.quit()
        driver.quit();
       // driver.close();
    }
}