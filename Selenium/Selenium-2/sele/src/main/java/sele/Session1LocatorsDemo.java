package sele;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session1LocatorsDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		try {
			driver.get("https://the-internet.herokuapp.com/login");
			String expTitle=driver.getTitle();
			String actTitle=driver.getTitle();
			
			if(expTitle.equals(actTitle)) {
				System.out.println("[PASS] Title verified:"+actTitle);
			}
			else {
				System.out.println("[FAIL] Expected: " + expTitle + " | Found: " + actTitle);
			}
			
			driver.findElement(By.id("username")).sendKeys("tomsmith");
			WebElement passwordIP=driver.findElement(By.name("password"));
			//passwordIP.sendKeys("SupeSecretPassword!!! wrong");
			//Thread.sleep(2000);
			passwordIP.clear();
			passwordIP.sendKeys("SuperSecretPassword!");
			//driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
			
			driver.findElement(By.cssSelector("button.radius[type='submit']")).click();
			
			//AFter logging in
			//finding banner
			
			WebElement flashBanner = driver.findElement(By.xpath("//div[@id='flash']"));
			String bannerText = flashBanner.getText();
			
            if (bannerText.contains("You logged into a secure area!")) {
                System.out.println("[PASS] Login Successful via ID, Name, CSS, and XPath.");
            }
            
            List<WebElement> allLinks = driver.findElements(By.tagName("a"));
            System.out.println("[INFO] Total anchor tags on page: " + allLinks.size());
            
            WebElement logoutbutton=driver.findElement(By.className("button"));
            System.out.println("[INFO] Logout button located via className: " + logoutbutton.isDisplayed());
            
            WebElement elementalLink = driver.findElement(By.partialLinkText("Elemental"));
            System.out.println("[INFO] Footer link located via partialLinkText: " + elementalLink.getText());   
            WebElement exactFooterLink=driver.findElement(By.linkText("Elemental Selenium"));
            System.out.println("[INFO] Exact link located via linkText: " + exactFooterLink.getAttribute("href")); 
            
            System.out.println("[INFO] Link target URL (getDomAttribute): " + exactFooterLink.getDomAttribute("href"));
            System.out.println("[INFO] Link CSS color (getCssValue): " + exactFooterLink.getCssValue("color"));
            //logoutbutton.click();
            
            
            
            
		}
		finally  {
			Thread.sleep(3000);
            driver.close();
        }
	}

}
