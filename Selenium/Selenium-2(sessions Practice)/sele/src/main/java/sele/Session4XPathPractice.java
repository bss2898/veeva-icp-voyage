package sele;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session4XPathPractice {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get("https://www.saucedemo.com/");

            //Relative XPath with single attribute: //input[@id='user-name']
            WebElement usernameInput=driver.findElement(By.xpath("//input[@placeholder='Username']"));
            usernameInput.sendKeys("standard_user");

            //Multiple attributes with 'and' operator: //input[@type='password' and @name='password']
            WebElement passwordInput=driver.findElement(By.xpath("//input[@data-test='password' and @id='password']"));
            passwordInput.sendKeys("secret_sauce");

            //starts-with() function on id attribute: //input[starts-with(@id,'login-')]
            WebElement loginButton=driver.findElement(By.xpath("//input[starts-with(@name,'login-')]"));
            loginButton.click();
            
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            
            WebElement isLogged=driver.findElement(By.xpath("//div[@class=\"app_logo\"]"));
            if(isLogged.getText().equals("Swag Labs")) {
            	System.out.println("[PASS]:Login successful");
            }
            else {
            	System.out.println("[FAIL]:Login successful");
            }
            //text() function exact match on page title: //span[text()='Products']
            
            WebElement productsHeader=driver.findElement(By.xpath("//span[text()='Products']"));
            System.out.println("[PASS] text() Header: "+productsHeader.getText());

            //contains() function on visible text: //div[contains(text(),'Sauce Labs Backpack')]
            WebElement backpackTitle=driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Backpack')]"));
            System.out.println("[PASS] contains(text()) Item: "+backpackTitle.getText());

            //contains() function on dynamic/long attribute: //button[contains(@id,'sauce-labs-backpack')]
            WebElement addBackpackBtn=driver.findElement(By.xpath("//button[contains(@id,'sauce-labs-backpack')]"));
            addBackpackBtn.click();

            //Chained XPath (Parent container to child badge): //div[@id='shopping_cart_container']//span[@class='shopping_cart_badge']
            WebElement cartBadge=driver.findElement(By.xpath("//div[@id='shopping_cart_container']//span[@class='shopping_cart_badge']"));
            System.out.println("[PASS] Chained XPath Cart Count: "+cartBadge.getText());

            //XPath Axis preview (Traverse from product text up to ancestor card, then down to price): //div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']
            WebElement backpackPrice=driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']"));
            System.out.println("[PASS] Axis Traversal Price: "+backpackPrice.getText());

        } finally {
        	Thread.sleep(4000);
        	driver.quit();
        }
    }
}