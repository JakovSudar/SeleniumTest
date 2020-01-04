import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class LoginTest {
    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    private WebDriver driver;
    //Declare a test URL variable
    private String testURL = "https://www.hgshop.hr/login.aspx";
    LoginPage loginPage;

    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jakov\\Documents\\chromedriver.exe");
        //Create a new ChromeDriver
        driver = new ChromeDriver();
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
        Thread.sleep(300);
        Assert.assertEquals("Korisničko ime i lozinka ne odgovaraju.",loginPage.GetErrorMessage());
    }
    @Test
    public void LoginWithFalseUsername ()throws InterruptedException{
        loginPage.SetUsername("akasd@sadf.com");
        loginPage.SetPassword("abc1234lsadr@@");
        loginPage.SubmitLogin();
        //Error message should appear
        Thread.sleep(300);
        Assert.assertEquals("Korisničko ime i lozinka ne odgovaraju.",loginPage.GetErrorMessage());
    }

   @Test
   public void SqlInjectionTest()throws InterruptedException{
       loginPage.SetUsername("'or'1=1'or'1=j@gmail.com");
       loginPage.SetPassword("'or'1=1");
       loginPage.SubmitLogin();
       //Error message should appear
       Thread.sleep(300);
       Assert.assertEquals("Korisničko ime i lozinka ne odgovaraju.",loginPage.GetErrorMessage());
   }

    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        driver.quit();
    }

}
