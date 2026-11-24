package sele2;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Session12AutoSuggestAndTablesPractice {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));

        try {
            //PART 1: AUTO-SUGGEST DROPDOWN
            /*driver.get("https://www.google.com/");

            //1. Type "selenium" into the search box: By.name("q")
            driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("selenium");
            Thread.sleep(2000);

            //2. Capture all dynamic auto-suggest options: //ul[@role='listbox']//li//div[@role='option']
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='OBMEnb']//ul[@jsname='bw4e9b']//li[@data-view-type='1']")));
            List<WebElement> suggestions=driver.findElements(By.xpath("//div[@class='OBMEnb']//ul[@jsname='bw4e9b']//li[@data-view-type='1']"));
            System.out.println("[AUTO-SUGGEST] Total Suggestions: "+suggestions.size());

            //3. Loop through suggestions, click "selenium webdriver", and break:
            for(WebElement item:suggestions) {
                String optiontext=item.getText();
                System.out.println(optiontext);
                if(optiontext.equals("selenium testing")) {
                	
                	item.click();
                	Thread.sleep(40000);//for captcha
                	Thread.sleep(4000);
                }
            }
            System.out.println("[AUTO-SUGGEST] Selected suggestion successfully.");
			*/
        	/*
            //PART 2: STATIC WEB TABLE OPERATIONS (BOOKTABLE)
            driver.get("https://testautomationpractice.blogspot.com/");

            //4. Count total rows (including header) and total columns in BookTable:
            //int rows=driver.findElements(By.xpath("//table[@name='BookTable']//tr")).size();
            int rows=driver.findElements(By.xpath("//table[@name='BookTable']//tr")).size();
            
            //int cols=driver.findElements(By.xpath("//table[@name='BookTable']//th")).size();
            
            int cols=driver.findElements(By.xpath("//table[@name='BookTable']//tr/th")).size();
            System.out.println("[STATIC TABLE] Rows: "+rows+" | Columns: "+cols);

            //5. Read a specific cell (Row 5, Column 1): //table[@name='BookTable']//tr[5]/td[1]
            String specificBook=driver.findElement(By.xpath("//table[@name='BookTable']//tr[4]/td[1]")).getText();
            System.out.println("[STATIC TABLE] Row 5 Col 1 Value: "+specificBook);

            //6. Nested for loops with XPath parameterization to print all data rows (start at r=2 to skip header):
            System.out.println("[STATIC TABLE] Full Table Data:");
            for(int r=2;r<=rows;r++) {
                for(int c=1;c<=cols;c++) {
                    //String cellValue=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td["+c+"]")).getText();
                    String cellValue=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]//td["+c+"]")).getText();
                    System.out.print(cellValue+"\t");
                }
                System.out.println();
            }

            //7. Conditional Data Retrieval: Print Book Names (col 1) where Author (col 2) is "Mukesh":
            for(int r=2;r<=rows;r++) {
                //String author=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[2]")).getText();
                String author=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[2]")).getText();
                if(author.equals("Mukesh")) {
                    //String bookName=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[1]")).getText();
                    String bookName=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[1]")).getText();;
                    System.out.println("[CONDITIONAL] Book by Mukesh: "+bookName);
                }
            }

            //8. Calculate Total Price of all books (col 4):
            int totalPrice=0;
            for(int r=2;r<=rows;r++) {
                //String priceStr=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[4]")).getText();
                String priceStr=driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[4]")).getText();
                totalPrice+=Integer.parseInt(priceStr);
            }
            System.out.println("[AGGREGATION] Total Price of All Books: "+totalPrice);
*/
        	
        	
            //PART 3: ASSIGNMENT - BLAZEDEMO LOWEST PRICE TICKET BOOKING
            driver.get("https://blazedemo.com/");

            //9. Select Departure City ("Boston") and Destination City ("New York") using Select class:
            new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText("Boston");
            new Select(driver.findElement(By.name("toPort"))).selectByVisibleText("New York");

            
            //10. Click 'Find Flights' button: //input[@value='Find Flights']
            driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
            

            //11. Find the row index of the lowest price in column 6 (strip '$' and parse Double):
            //int flightRows=driver.findElements(By.xpath("//table[@class='table']//tbody/tr")).size();
            int flightRows=driver.findElements(By.xpath("//table[@class='table']//tbody/tr")).size();
            double lowestPrice=Double.MAX_VALUE;
            int lowestPriceRow=1;

            for(int r=1;r<=flightRows;r++) {
                //String rawPrice=driver.findElement(By.xpath("//table[@class='table']//tbody/tr["+r+"]/td[6]")).getText();
                String rawPrice=driver.findElement(By.xpath("//table[@class='table']//tbody//tr["+r+"]//td[6]")).getText();
                double price=Double.parseDouble(rawPrice.replace("$",""));
                //double price=9999;
                if(price<lowestPrice) {
                    lowestPrice=price;
                    lowestPriceRow=r;
                }
            }
            System.out.println("[ASSIGNMENT] Lowest Price: $"+lowestPrice+" found at Row: "+lowestPriceRow);

            //12. Click 'Choose This Flight' button in the lowestPriceRow using parameterized XPath:
            //driver.findElement(By.xpath("//table[@class='table']//tbody/tr["+lowestPriceRow+"]/td[1]/input")).click();
            driver.findElement(By.xpath("//table[@class='table']//tbody/tr["+lowestPriceRow+"]/td[1]/input")).click();
            //13. Fill passenger name and click 'Purchase Flight': By.id("inputName") and //input[@value='Purchase Flight']
            driver.findElement(By.id("inputName")).sendKeys("SDET Candidate");
            driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();

            //14. Validate final confirmation heading ("Thank you for your purchase today!"): //h1
            String confirmHeading=driver.findElement(By.xpath("//h1")).getText();
            if(confirmHeading.equals("Thank you for your purchase today!")) {
                System.out.println("[ASSIGNMENT - PASS] Booking Confirmed: "+confirmHeading);
            } else {
                System.out.println("[ASSIGNMENT - FAIL] Unexpected heading: "+confirmHeading);
            }

        } finally {
            driver.quit();
        }
    }
}