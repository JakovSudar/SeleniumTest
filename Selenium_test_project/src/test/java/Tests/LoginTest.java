package Tests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    private WebDriver driver;
    //Declare a test URL variable
    private String testURL = "https://www.hgshop.hr/login.aspx";
    LoginPage loginPage;
    String errorMessage = "Korisničko ime i lozinka ne odgovaraju";

    //-----------------------------------Test Setup-----------------------------------

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.operadriver().setup();
        WebDriverManager.edgedriver().setup();
        WebDriverManager.iedriver().setup();
    }
    @BeforeMethod
    public void setupTest() {
        //Uncomment driver you want
            //Internet explorer driver
                //driver = new InternetExplorerDriver();
            //Google Chrome driver
                //driver = new ChromeDriver();
            //Opera driver
                //driver = new OperaDriver();
            //Firefox driver
                //driver = new FirefoxDriver();
            //Edge driver
                //driver = new EdgeDriver();

        //set POM
        loginPage = new LoginPage(driver);
        //navigate to web page
        driver.navigate().to(testURL);
        driver.manage().window().maximize();
    }
    @Test
    public void LoginWithRegisteredUsername ()throws InterruptedException{
        loginPage.SetUsername("jakovusdar@gmail.com");
        loginPage.SetPassword("testiranje123");
        loginPage.SubmitLogin();
        //If user can logoff, then he is logged in correctly
        loginPage.LogOff();
    }
    @Test
    public void LoginWithRegisteredUsername_FalsePassword ()throws InterruptedException{
        loginPage.SetUsername("jakovusdar@gmail.com");
        loginPage.SetPassword("abc1234lsadr@@");
        loginPage.SubmitLogin();
        //Error message should appear
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        Assert.assertEquals(loginPage.GetErrorMessage().isEmpty(),false);
    }
    @Test
    public void LoginWithFalseUsername ()throws InterruptedException{
        loginPage.SetUsername("akasd@sadf.com");
        loginPage.SetPassword("abc1234lsadr@@");
        loginPage.SubmitLogin();
        //Error message should appear
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        Assert.assertEquals(loginPage.GetErrorMessage().isEmpty(),false);
    }
   @Test
   public void SqlInjectionTest()throws InterruptedException{
       loginPage.SetUsername("'or'1=1'or'1=j@gmail.com");
       loginPage.SetPassword("'or'1=1");
       loginPage.SubmitLogin();
       //Error message should appear
       driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
       Assert.assertEquals(loginPage.GetErrorMessage().isEmpty(),false);
   }
    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        driver.quit();
    }

}
