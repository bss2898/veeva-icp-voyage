package chrome;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session3CssSelectorsExercise {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		try{
			driver.get("https://www.saucedemo.com/");
			
			WebElement usernameInput=driver.findElement(By.cssSelector("input#user-name"));
            usernameInput.sendKeys("standard_user");
            
            WebElement passwordInput=driver.findElement(By.cssSelector("input[name='password']"));
            passwordInput.sendKeys("secret_sauce");
            
            WebElement loginButton=driver.findElement(By.cssSelector("input.submit-button[value='Login']"));
            
            loginButton.click();
            
            WebElement pageHeader=driver.findElement(By.cssSelector("span.title[data-test='title']"));
            System.out.println("[PASS] Tag+Class Header+attribute added(by me): "+pageHeader.getText());

            List<WebElement> allProducts=driver.findElements(By.cssSelector("div.inventory_item"));
            System.out.println("[PASS] Total Products Listed: "+allProducts.size());

            WebElement addBackpackBtn=driver.findElement(By.cssSelector("button[name='add-to-cart-sauce-labs-backpack']"));
            addBackpackBtn.click();
            
           // a.shopping_cart_link > span.shopping_cart_badge
            WebElement cartBadge=driver.findElement(By.cssSelector("a.shopping_cart_link>span.shopping_cart_badge"));
            System.out.println("[PASS] Direct Child Cart Count: "+cartBadge.getText());
//            //footer.footer div.footer_copy
            WebElement footerCopy=driver.findElement(By.cssSelector("footer.footer div.footer_copy"));
            System.out.println("[PASS] Descendant Footer Text: "+footerCopy.getText());
		}
		finally {
			Thread.sleep(5000);
			driver.quit();
		}
	}

}
