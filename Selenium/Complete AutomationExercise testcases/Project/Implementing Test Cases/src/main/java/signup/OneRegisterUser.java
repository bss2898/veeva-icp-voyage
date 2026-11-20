package signup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OneRegisterUser {
    public static void main(String[] args) throws InterruptedException {

//		1. Launch browser
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
//		2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
//		3. Verify that home page is visible successfully
        String title=driver.getTitle();
        System.out.println("Title="+title);
        System.out.println("[PASS]:Home page visible");
//		4. Click on 'Signup / Login' button//
        WebElement loginbutton=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Signup / Login']"))
        );
        System.out.println("[PASS]Signup/Login button visible:"+loginbutton.isDisplayed());
        loginbutton.click();
        System.out.println("Navigated to signup/login page");

        Thread.sleep(2000);
        //		5. Verify 'New User Signup!' is visible
        WebElement newUserSignup=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New User Signup!']"))
        );
        System.out.println("[PASS]New user signup available?"+newUserSignup.isDisplayed());

//		6. Enter name and email address
        WebElement name=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @data-qa='signup-name']"))
        );
        name.sendKeys("Monkey D Luffy");
        WebElement email=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email' and @data-qa='signup-email']") )
        );
        email.sendKeys("nikaa56@gmail.com");
//		7. Click 'Signup' button
//		8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        WebElement signupbutton=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit' and @data-qa='signup-button']") )
        );
        System.out.println("[PASS]Is signUp button enabled?:"+signupbutton.isEnabled());
        signupbutton.click();
        System.out.println("Navigated to "+driver.getTitle());

//		9. Fill details: Title, Name, Email, Password, Date of birth

        Thread.sleep(6000);
        WebElement maleButton=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/input[@type='radio' and @id='id_gender1']") )
        );
        maleButton.click();
        System.out.println("[PASS] Male radio button clicked!");

        //re-entering the name
        WebElement nameField=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text' and @value='Monkey D Luffy']") )
        );
        nameField.clear();
        nameField.sendKeys("Monkey D. Luffy");

        WebElement password=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password' and @id='password']") )
        );
        password.sendKeys("passWord1");

        //DOB
        WebElement dayElement=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@data-qa='days' and @id='days']") )
        );
        Select dayDropDown=new Select(dayElement);
        dayDropDown.selectByValue("30");
        WebElement monthElement=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@data-qa='months' and @id='months']") )
        );
        Select monthDropDown=new Select(monthElement);
        monthDropDown.selectByIndex(11);
        WebElement yearsElement=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@data-qa='years' and @id='years']") )
        );
        Select yearsDropDown=new Select(yearsElement);
        yearsDropDown.selectByContainsVisibleText("202");


//		10. Select checkbox 'Sign up for our newsletter!'
        WebElement newsLetter=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='checkbox' and @name='newsletter']") )
        );
        newsLetter.click();
//		11. Select checkbox 'Receive special offers from our partners!'
        WebElement receiveOffers=wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='checkbox' and @name='optin']") )
        );
        receiveOffers.click();

//		12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        driver.findElement(By.cssSelector("input.form-control[name='first_name']")).sendKeys("Odinson");
        driver.findElement(By.cssSelector("input#last_name")).sendKeys("Thor");
        driver.findElement(By.cssSelector("input.form-control#company")).sendKeys("Asgard");

        driver.findElement(By.cssSelector("input.form-control#address1")).sendKeys("Tree of ygaslard");
        driver.findElement(By.cssSelector("input[data-qa='state'][id='state']")).sendKeys("AP");
        driver.findElement(By.cssSelector("input.form-control#city[data-qa='city']")).sendKeys("Vizag");
        driver.findElement(By.cssSelector("input.form-control#zipcode[data-qa='zipcode']")).sendKeys("501101");
        driver.findElement(By.xpath("//input[@data-qa='mobile_number']")).sendKeys("9001909091");
//		13. Click 'Create Account button'
        WebElement createButton=driver.findElement(By.xpath("//button[@data-qa='create-account']"));
        System.out.println("[PASS] Is create account button enable:"+createButton.isEnabled());
        createButton.click();
//		14. Verify that 'ACCOUNT CREATED!' is visible
        System.out.println("[PASS]Is the account created?? "+driver.findElement(By.xpath("//b[contains(text(),'Account Created!')]")).getText());
//		15. Click 'Continue' button
        WebElement continueButton=wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.linkText("Continue"))
        );
        continueButton.click();
//		16. Verify that 'Logged in as username' is visible
        WebElement loggedUsername=wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),' Logged in as ')]"))
        );
        System.out.println("[PASS]The user logged in displayed as:"+loggedUsername.getText());
//		17. Click 'Delete Account' button
        WebElement deleteAccount=wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Delete"))
        );
        deleteAccount.click();

//		18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        System.out.println("[PASS]Is the account DELETED?? "+driver.findElement(By.xpath("//b[contains(text(),'Account Deleted!')]")).getText());

        System.out.println("ALL TEST CASES PASSED!!");
        driver.findElement(By.linkText("Continue")).click();
        Thread.sleep(3000);
        driver.close();

    }
}
