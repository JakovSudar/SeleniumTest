
import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class ChartQuantityTest {
    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    private WebDriver driver;
    //Declare a test URL variable
    private String testURL = "https://www.hgshop.hr/";
    HomePage homePage;
    LoginPage loginPage;
    SearchResult searchResult;
    ChartPage chartPage;
    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Jakov\\Documents\\chromedriver.exe");
        //Create a new ChromeDriver
        driver = new ChromeDriver();
        //set POM
        homePage = new HomePage(driver);
        searchResult = new SearchResult(driver);
        chartPage = new ChartPage(driver);
       //navigate to web page
        driver.navigate().to(testURL);
        driver.manage().window().maximize();
    }
    //This test checks if chart is showing correct quantity number of products when we continue shopping after adding one product to chart
    @Test
    public void ChartQuantityTest_ContinueShopping() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        Thread.sleep(700);
        searchResult.BuyProduct(1);
        Thread.sleep(300);
        searchResult.ContinueShopping();
        //Test verify, we should have 1 product in chart.
        Thread.sleep(200);
        Assert.assertEquals(chartPage.GetProductQuantity(),"1");
        Thread.sleep(200);
    }
    //This test checks if chart is showing correct quantity number of products when we continue shopping after adding two product to chart
    @Test
    public void ChartQuantityTest_ContinueShopping2() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        Thread.sleep(700);
        searchResult.BuyProduct(1);
        //Add 2 "Playstation" to chart
        searchResult.IncreaseAmountBy1();
        Thread.sleep(300);
        searchResult.ContinueShopping();
        //Test verify, we should have 2 products in chart.
        Thread.sleep(200);
        Assert.assertEquals(chartPage.GetProductQuantity(),"2");
        Thread.sleep(200);
    }
    //This test checks if chart is showing correct quantity number of products when we end shopping after adding one product to chart
    @Test
    public void ChartQuantityTest_EndShopping() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        Thread.sleep(300);
        searchResult.BuyProduct(1);
        Thread.sleep(100);
        searchResult.EndShopping();
        //Test verify, we should have 1 products in chart.
        Thread.sleep(200);
        Assert.assertEquals(chartPage.GetProductQuantity(),"1");
        Thread.sleep(200);
    }
    //This test checks if chart is showing correct quantity number of products when we end shopping after adding two product to chart
    @Test
    public void ChartQuantityTest_EndShopping2() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        Thread.sleep(300);
        searchResult.BuyProduct(1);
        //Add 2 "Playstation" to chart
        searchResult.IncreaseAmountBy1();
        Thread.sleep(100);
        searchResult.EndShopping();
        //Test verify, we should have 1 products in chart.
        Thread.sleep(200);
        Assert.assertEquals(chartPage.GetProductQuantity(),"2");
        Thread.sleep(200);
    }
    //This test checks if chart is showing correct quantity number of products when user instead of clicking + to increase
    //number of products, write correct value with keyboard.
    @Test
    public void ChartQuantityTest_EndShopping_InsertedNumberOfProducts() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        Thread.sleep(300);
        searchResult.BuyProduct(1);
        //Add 2 "Playstation" to chart
        searchResult.SetAmount("10");
        Thread.sleep(100);
        searchResult.EndShopping();
        //Test verify, we should have 1 products in chart.
        Thread.sleep(200);
        Assert.assertEquals(chartPage.GetProductQuantity(),"10");
        Thread.sleep(200);
    }
    @Test
    public void ChartQuantityTest_ContinueShopping_InsertedNumberOfProducts() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        Thread.sleep(300);
        searchResult.BuyProduct(1);
        //Add 2 "Playstation" to chart
        searchResult.SetAmount("10");
        Thread.sleep(100);
        searchResult.ContinueShopping();
        //Test verify, we should have 1 products in chart.
        Thread.sleep(200);
        Assert.assertEquals(chartPage.GetProductQuantity(),"10");
        Thread.sleep(200);
    }
    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        driver.quit();
    }
}