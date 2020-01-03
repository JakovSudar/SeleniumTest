package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResult {

    WebDriver driver = null;

    //injecting driver trough constructor
    public SearchResult(WebDriver driver){
        this.driver=driver;
    }
    By searched_product = By.xpath("/html/body/div[6]/div/section/div[3]/section[1]");
    By basket_amount = By.id("basket-amount");
    By continue_shopping = By.className("kupovina");
    By end_shopping = By.className("blagajna");

    //productOrder is ordinal number of product in search result
    public void BuyProduct(String productOrder){
        driver.findElement(By.xpath("/html/body/div[6]/div/section/div[3]/section["+productOrder+"]/div[3]/div[2]")).click();
    }
    public void SetAmount(String amount){
        //clear amount
        driver.findElement(basket_amount).clear();
        //set new amount
        driver.findElement(basket_amount).sendKeys(amount);
    }

    //next 2 methods needs to be called right after method BuyProduct
    public void ContinueShopping(){
        driver.findElement(continue_shopping).click();
    }
    public void EndShopping(){
        driver.findElement(end_shopping).click();
    }
}
