package sele2;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Session11DropDowns{

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
        	driver.get("https://www.saucedemo.com/");
        	driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        	driver.findElement(By.xpath("//input[@id='login-button']")).click();
        	
        	WebElement filter=driver.findElement(By.className("product_sort_container"));
        	Select filterdd=new Select(filter);
        	filterdd.selectByIndex(3);
            //PART 1: NORMAL SELECT DROPDOWN (ASSIGNMENT 1) & MULTI-SELECT (ASSIGNMENT 3)
//            driver.get("https://testautomationpractice.blogspot.com/");
//
//            //1. Locate Country <select> element and create Select object: By.id("country")
//            WebElement countryElement=driver.findElement(By.id("country"));
//            //Select countryDropdown=new Select(countryElement);
//            Select countryDropdown=new Select(countryElement);
//
//            //2. Test all 3 selection methods: selectByVisibleText("India"), selectByValue("usa"), selectByIndex(2)
//            countryDropdown.selectByVisibleText("India");
//            Thread.sleep(2000);
//            countryDropdown.selectByValue("usa");
//            Thread.sleep(2000);
//            countryDropdown.selectByIndex(2);
//            Thread.sleep(2000);
//
//            
//            //3. Capture all options, print total count, and print every option text:
//            //List<WebElement> countryOptions=countryDropdown.getOptions();
//            List<WebElement> countryOptions=countryDropdown.getOptions();
//            
//            System.out.println("[SELECT] Total Countries: "+countryOptions.size());
//            //for(WebElement opt:countryOptions) { System.out.println(" - "+opt.getText()); }
//            for(WebElement opt:countryOptions) {
//            	System.out.println("-"+opt.getText());
//            }
//            System.out.println("Success until here!");
//            Thread.sleep(6000);
//            //4. Multi-Select (Assignment 3): Locate Colors box and select Red, Blue, and Green: By.id("colors")
//            WebElement colorsElement=driver.findElement(By.id("colors"));
//            Select colorsDropdown=new Select(colorsElement);
//            
//            colorsDropdown.selectByVisibleText("Red");
//            colorsDropdown.selectByValue("blue");
//            colorsDropdown.selectByVisibleText("Green");
//            
//            System.out.println("[MULTI-SELECT] Selected Colors Count: "+colorsDropdown.getAllSelectedOptions().size());
//            
//            System.out.println("Success until here!");
//            Thread.sleep(6000);
        	
            //PART 2: BOOTSTRAP DROPDOWN
//            driver.get("https://demoqa.com/select-menu");//https://www.jquery-az.com/boots/demo.php?ex=63.0_2
//            //https://demoqa.com/select-menu
//            WebElement dropDown1=driver.findElement(By.xpath("//div[@class='css-hlgwow']//div[@id='react-select-2-placeholder']"));
//            System.out.println("IS present="+dropDown1.isDisplayed());
//            Thread.sleep(3000);
//            
            //5. Open Bootstrap dropdown using XPath contains(): //button[contains(@class,'multiselect')]
           // driver.findElement(By.xpath("//button[contains(@class,'multiselect')]")).click();

//            //6. Capture all Bootstrap dropdown options: //ul[contains(@class,'multiselect-container')]//label
//            List<WebElement> bootOptions=driver.findElements(By.xpath(""));
//            System.out.println("[BOOTSTRAP] Total Options: "+bootOptions.size());
//
//            //7. Loop through options and click "Java", "Python", and "MySQL":
//            for(WebElement opt:bootOptions) {
//                String text=opt.getText();
//                //if(text.equals("Java")||text.equals("Python")||text.equals("MySQL")) { opt.click(); }
//            }
//            System.out.println("[BOOTSTRAP] Multiple options selected via loop.");
//
//            //PART 3: HIDDEN DROPDOWN (ASSIGNMENT 2 - ORANGEHRM EMPLOYMENT STATUS)
//            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//
//            //8. Login to OrangeHRM (Admin / admin123) and click PIM:
//            driver.findElement(By.name("username")).sendKeys("Admin");
//            driver.findElement(By.name("password")).sendKeys("admin123");
//            driver.findElement(By.xpath("//button[@type='submit']")).click();
//            //Click PIM menu: //span[text()='PIM']
//            driver.findElement(By.xpath("")).click();
//
//            //9. Click the 'Employment Status' dropdown trigger: //label[text()='Employment Status']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]
//            driver.findElement(By.xpath("")).click();
//
//            //10. Capture all hidden listbox options (verify first using SelectorsHub Debugger): //div[@role='listbox']//span
//            List<WebElement> hiddenOptions=driver.findElements(By.xpath(""));
//            System.out.println("[HIDDEN] Total Employment Status Options: "+hiddenOptions.size());
//            //for(WebElement opt:hiddenOptions) { System.out.println(" - "+opt.getText()); }
//            for(WebElement opt:hiddenOptions) {
//            }
//
//            //11. Select 'Full-Time Permanent' from the hidden listbox: //div[@role='listbox']//span[text()='Full-Time Permanent']
//            driver.findElement(By.xpath("")).click();
//            System.out.println("[HIDDEN] Selected 'Full-Time Permanent' successfully.");

        } 
        finally {
           // driver.quit();
        }
    }
}