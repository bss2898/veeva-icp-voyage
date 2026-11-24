package sele2;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session13DynamicPaginationPractice {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            //PART 1: DYNAMIC PAGINATION TABLE WITH CHECKBOXES (DATATABLES)
/*            driver.get("https://datatables.net/extensions/select/examples/checkbox/checkbox.html");

            //1. Capture pagination info text (e.g., "Showing 1 to 10 of 57 entries"): //div[@id='example_info']
            String infoText=driver.findElement(By.xpath("//div[@class='dt-info']")).getText();
            System.out.println("[PAGINATION] Status Text: "+infoText);

            //2. Extract total entries using substring() and indexOf(), then convert via Integer.parseInt():
            //String totalEntriesStr=infoText.substring(infoText.indexOf("of ")+3,infoText.indexOf(" entries"));
            String totalEntriesStr=infoText.substring(infoText.indexOf("of ")+3,infoText.indexOf("entries")-1);
            int totalEntries=Integer.parseInt(totalEntriesStr);
            //int totalEntries=0;
            int totalPages=(int)Math.ceil(totalEntries/10.0);
            System.out.println("[PAGINATION] Total Entries: "+totalEntries+" | Calculated Pages: "+totalPages);

            //3. Loop through pages 1 to 3 (or totalPages), read Name, Position, Salary, and select row checkboxes:
            for(int p=1;p<=3;p++) {
                //Conditional logic: Only click the page button when moving past Page 1
                if(p>1) {
                    //Dynamic XPath for page number button: //button[contains(@class,'dt-paging-button') and text()='"+p+"']
                    WebElement pageBtn=driver.findElement(By.xpath("//button[contains(@class,'dt-paging-button') and text()='"+p+"']"));
                    pageBtn.click();
                    Thread.sleep(1500);
                }

                //4. Count dynamic rows on the current page: //table[@id='example']/tbody/tr
                //int currentRows=driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();
                int currentRows=driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();
                System.out.println("--- Page "+p+" (Rows: "+currentRows+") ---");

                //5. Nested loop to read columns (Name=col 2, Position=col 3, Salary=col 6) and click checkbox (col 1):
                for(int r=1;r<=currentRows;r++) {
                    //String name=driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+r+"]/td[2]")).getText();
                    String name=driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+r+"]/td[2]")).getText();
                    String position=driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+r+"]/td[3]")).getText();
                    //String position="";
                    String salary=driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+r+"]/td[6]")).getText();
                    //String salary="";

                    //Click checkbox in column 1 of row r: //table[@id='example']/tbody/tr["+r+"]/td[1]/input[@type='checkbox']
                    driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+r+"]/td[1]/input[@type='checkbox']")).click();

                    System.out.println(name+" | "+position+" | "+salary);
                }
            }
       */

            //PART 2: BOOTSTRAP / DIV-BASED TABLE (NO <table>, <tr>, OR <td> TAGS - DEMOQA)
            driver.get("https://demoqa.com/webtables");

            //6. Count total div rows inside the table body: //div[@class='rt-tbody']//div[@class='rt-tr-group']
            int divRows=driver.findElements(By.xpath("//table[contains(@class,'-striped')]//tbody//tr")).size();
            //int divRows=0;
            System.out.println("\n[DIV TABLE] Total Div Row Containers: "+divRows);

            //7. Loop through div rows, skip empty placeholder rows with an if condition, and extract First Name (col 1) & Department (col 6):
            for(int r=1;r<=divRows;r++) {
                String firstName=driver.findElement(By.xpath("//table[contains(@class,'-striped')]//tbody//tr["+r+"]//td[1]")).getText();
                System.out.println(firstName);
                //String firstName="";
                //Conditional check to ignore blank template rows: if(!firstName.isBlank())
                if(!firstName.isBlank()) {
                    String email=driver.findElement(By.xpath("//table[contains(@class,'-striped')]//tbody//tr["+r+"]//td[4]")).getText();
                    //String email="";
                    String department=driver.findElement(By.xpath("//table[contains(@class,'-striped')]//tbody//tr["+r+"]//td[6]")).getText();
                    //String department="";
                    System.out.println("[DIV TABLE] User: "+firstName+" | Email: "+email+" | Dept: "+department);
                }
            }

        } finally {
        	Thread.sleep(6000);
            driver.quit();
        }
    }
}