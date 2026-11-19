package sele;
 

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Session5XPathAxesPractice {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            driver.get("https://the-internet.herokuapp.com/tables");

            //following-sibling: Anchor on 'jsmith@gmail.com' in table1 and get his Due amount ($50.00): //table[@id='table1']//td[text()='jsmith@gmail.com']/following-sibling::td[1]
            WebElement jsmithDue=driver.findElement(By.xpath("//table[@id='table1']//td[text()='jsmith@gmail.com']/following-sibling::td[1]"));
            System.out.println("[PASS] following-sibling Due: "+jsmithDue.getText());

            //preceding-sibling: Anchor on 'fbach@yahoo.com' in table1 and get his Last Name ('Bach'): //table[@id='table1']//td[text()='fbach@yahoo.com']/preceding-sibling::td[2]
            WebElement fbachLastName=driver.findElement(By.xpath("//table[@id='table1']//td[text()='fbach@yahoo.com']/preceding-sibling::td[2]"));
            System.out.println("[PASS] preceding-sibling Last Name: "+fbachLastName.getText());

            //parent with wildcard (*): Anchor on 'Tim' in table1 and verify parent tag name ('tr'): //table[@id='table1']//td[text()='Tim']/parent::* or tr can be used
            WebElement timRow=driver.findElement(By.xpath("//table[@id='table1']//td[text()='Tim']/parent::*"));
            System.out.println("[PASS] parent::* Tag Name: "+timRow.getTagName());

            //ancestor + descendant: Anchor on 'jdoe@hotmail.com' in table1, move up to ancestor tr, then down to descendant 'delete' link: //table[@id='table1']//td[text()='jdoe@hotmail.com']/ancestor::tr/descendant::a[text()='delete']
            WebElement jdoeDeleteLink=driver.findElement(By.xpath("//table[@id='table1']//td[text()='jdoe@hotmail.com']/ancestor::tr//a[text()='delete']"));
            System.out.println("[PASS] ancestor + descendant Link Href: "+jdoeDeleteLink.getDomAttribute("href"));

            //child + last(): Navigate from table1 tbody to child tr[last()], then child td[3] (Email of last row): //table[@id='table1']/tbody/child::tr[last()]/child::td[3]
            //email of the last 
            WebElement lastRowEmail=driver.findElement(By.xpath("//table[@id='table1']/tbody/child::tr[last()]/child::td[3]"));
            System.out.println("[PASS] child + last() Email: "+lastRowEmail.getText());

            //following: Start at table1 header 'Last Name' and grab the very first following td cell ('Smith'): //table[@id='table1']//span[text()='Last Name']/following::td[1]
            WebElement firstFollowingCell=driver.findElement(By.xpath("//table[@id='table1']//span[text()='Last Name']/following::td[1]"));
            System.out.println("[PASS] following::td[1] Text: "+firstFollowingCell.getText());

            //preceding: Start at table2 header and locate the preceding table1's h4 heading ('Example 1'): //table[@id='table2']/preceding::h4[1]
            WebElement precedingHeading=driver.findElement(By.xpath("//table[@id='table2']/preceding::h4[2]"));
            System.out.println("[PASS] preceding::h4[1] Text: "+precedingHeading.getText());

        } finally {
        	Thread.sleep(2500);
            driver.quit();
        }
    }
}