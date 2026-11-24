package sele;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session8NavigationAndWindowsPractice {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            //PART 1: NAVIGATIONAL COMMANDS
            //1. Launch first URL using String: driver.navigate().to("https://the-internet.herokuapp.com/");
//            driver.navigate().to("https://the-internet.herokuapp.com/");
//
//            //2. Launch second URL using java.net.URL object: URL practiceUrl=URI.create("https://testautomationpractice.blogspot.com/").toURL();
//            URL practiceUrl=null;
//            //driver.navigate().to(practiceUrl);
//            Thread.sleep(2000);
//            driver.navigate().to("https://www.youtube.com/");
//            Thread.sleep(1500);
//            //3. Navigate back and print URL: driver.navigate().back();
//            driver.navigate().back();
//            System.out.println("[NAVIGATE] After back(): "+driver.getCurrentUrl());
//            
//            //4. Navigate forward and print URL: driver.navigate().forward();
//            driver.navigate().forward();
//            System.out.println("[NAVIGATE] After forward(): "+driver.getCurrentUrl());
//
//            //5. Refresh the current page: driver.navigate().refresh();
//            driver.navigate().refresh();
//            System.out.println("[NAVIGATE] Refreshed Page Title: "+driver.getTitle());
            
            //PART 2: ASSIGNMENT (SEARCH -> COUNT -> LOOP CLICK -> HANDLE & SELECTIVELY CLOSE WINDOWS)
            //6. Locate Wikipedia search box and type "Selenium": By.id("Wikipedia1_wikipedia-search-input")
            driver.navigate().to("https://testautomationpractice.blogspot.com/");
            WebElement searchBox=driver.findElement(By.id("Wikipedia1_wikipedia-search-input"));
            searchBox.sendKeys("Selenium");
            
            //7. Click the Wikipedia search button: By.cssSelector("input.wikipedia-search-button")
            driver.findElement(By.cssSelector("input.wikipedia-search-button")).click();

            //8. Capture and count all search result links: By.xpath("//div[@id='wikipedia-search-result-link']/a")
            List<WebElement> searchLinks=driver.findElements(By.xpath("//div[@id='wikipedia-search-result-link']/a"));
            System.out.println("[ASSIGNMENT] Total Search Result Links: "+searchLinks.size());

            //9. Loop through each link and click it to open multiple tabs:
            //for(WebElement link:searchLinks) { link.click(); }
            for(WebElement link:searchLinks) {
            	link.click();            	
            }

            //10. Capture all open window IDs: Set<String> windowIds=driver.getWindowHandles();
            Set<String> windowIds=driver.getWindowHandles();
            System.out.println("[WINDOWS] Total Open Windows: "+windowIds.size());

            //11. Convert Set to List to inspect parent window ID by index 0: List<String> windowList=new ArrayList<>(windowIds);
            List<String> windowList=new ArrayList<>(windowIds);
            String parentWindowId=windowList.get(0);
            System.out.println("[WINDOWS] Parent Window ID (Index 0): "+parentWindowId);

            //12. Loop through all window IDs, switch to each, print title, and selectively close two specific Wikipedia tabs:
            for(String winId:windowIds) {
                driver.switchTo().window(winId);
                String currentTitle=driver.getTitle();
                System.out.println("[TAB] Title: "+currentTitle);
                
                //Close selectively if title matches "Selenium - Wikipedia" or "Selenium in biology - Wikipedia":
                if(!currentTitle.equals("Selenium - Wikipedia")) { driver.close(); System.out.println("[CLOSED] "+currentTitle); }
            }

            //13. Switch focus back to parent window and print remaining open tab count:
            driver.switchTo().window(parentWindowId);
            System.out.println("Current windowww!!"+driver.getTitle());
            System.out.println("[WINDOWS] Remaining Open Windows After Selective Close: "+driver.getWindowHandles().size());

        } finally {
            driver.quit();
        }
    }
}