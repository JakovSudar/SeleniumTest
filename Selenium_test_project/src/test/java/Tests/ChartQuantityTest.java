package Tests;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

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
        homePage = new HomePage(driver);
        searchResult = new SearchResult(driver);
        chartPage = new ChartPage(driver);
        loginPage = new LoginPage(driver);

       //navigate to web page
        driver.navigate().to(testURL);
        driver.manage().window().maximize();
    }
    //This test checks if chart is showing correct quantity number of products when we continue shopping after adding one product to chart
    @Test
    public void ChartQuantityTest_ContinueShopping() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(700, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        searchResult.ContinueShopping();
        //Test verify, we should have 1 product in chart.
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        Assert.assertEquals(chartPage.GetProductQuantity(),"1");

    }
    //This test checks if chart is showing correct quantity number of products when we continue shopping after adding two product to chart
    @Test
    public void ChartQuantityTest_ContinueShopping2() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(700, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        //Add 2 "Playstation" to chart
        searchResult.IncreaseAmountBy1();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        searchResult.ContinueShopping();
        //Test verify, we should have 2 products in chart.
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        Assert.assertEquals(chartPage.GetProductQuantity(),"2");

    }
    //This test checks if chart is showing correct quantity number of products when we end shopping after adding one product to chart
    @Test
    public void ChartQuantityTest_EndShopping() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(900, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        searchResult.EndShopping();
        //Test verify, we should have 1 products in chart.
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        Assert.assertEquals(chartPage.GetProductQuantity(),"1");
    }
    //This test checks if chart is showing correct quantity number of products when we end shopping after adding two product to chart
    @Test
    public void ChartQuantityTest_EndShopping2() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        //Add 2 "Playstation" to chart
        searchResult.IncreaseAmountBy1();
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        searchResult.EndShopping();
        //Test verify, we should have 1 products in chart.
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        Assert.assertEquals(chartPage.GetProductQuantity(),"2");

    }
    //This two tests checks if chart is showing correct quantity number of products when user instead of clicking + to increase
    //number of products, write correct value with keyboard.
    @Test
    public void ChartQuantityTest_EndShopping_InsertedNumberOfProducts() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(700, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        //Add 2 "Playstation" to chart
        searchResult.SetAmount("10");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        searchResult.EndShopping();
        //Test verify, we should have 1 products in chart.
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        Assert.assertEquals(chartPage.GetProductQuantity(),"10");

    }
    @Test
    public void ChartQuantityTest_ContinueShopping_InsertedNumberOfProducts() throws InterruptedException {
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(700, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        //Add 2 "Playstation" to chart
        searchResult.SetAmount("10");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        searchResult.ContinueShopping();
        //Test verify, we should have 1 products in chart.
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        Assert.assertEquals(chartPage.GetProductQuantity(),"10");

    }

    //We shouldn't be able to order 50001 Playstations
    @Test
    public void Add500001ProductQuantity()throws InterruptedException{
        homePage.CloseNewsletterPopup();
        homePage.CloseCookiePopup();
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(700, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        //Add 50001 "Playstation" to chart
        searchResult.SetAmount("50000");
        searchResult.IncreaseAmountBy1();
        searchResult.EndShopping();
        chartPage.NextStep();
        Thread.sleep(200);
        loginPage.SetUsername("jakovusdar@gmail.com");
        loginPage.SetPassword("testiranje123");
        loginPage.SubmitLogin();
        chartPage.NextStep();
        chartPage.SetPickupInStore();
        chartPage.ChoosePaymentOption_Cards();
        chartPage.NextStep();
        Assert.assertEquals(true,chartPage.IsOrderReady());
    }
    //If add product to chart after that same product was already added, quantity in chart should increase
    @Test
    public void AddSameProductTwoTimes()throws InterruptedException{
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(700, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        searchResult.ContinueShopping();
        //Repeat process
        homePage.SetSearchingProduct("Playstation");
        homePage.SubmitSearch();
        driver.manage().timeouts().implicitlyWait(700, TimeUnit.MILLISECONDS);
        searchResult.BuyProduct(1);
        searchResult.ContinueShopping();
        chartPage.EnterChart();
        //Quantity should be 2
        Assert.assertEquals(chartPage.GetQuantityOfFirstProductInChart(),"2");

    }
    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        driver.quit();
    }
}