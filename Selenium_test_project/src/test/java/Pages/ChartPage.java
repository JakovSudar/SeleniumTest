package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ChartPage {
    WebDriver driver = null;

    //injecting driver trough constructor
    public ChartPage(WebDriver driver){
        this.driver=driver;
        this.actions = new Actions(driver);
    }

    Actions actions;

    By product_quantity = By.id("basket-number-mobile");
    By btn_chart = By.id("desktop-cart-button");
    By btn_next = By.className("kosarica-next");
    By radio_cards = By.id("kartice");
    By radio_pickupStore = By.id("preuzimanje");
    By order_rewiev = By.xpath("/html/body/div[6]/div[1]/div[2]/div/div/h2");
    By firstProduct_quantity = By.xpath("/html/body/div[6]/div[1]/div[2]/div/div/ul/li/div[5]/input");

    //methods
    //Returns number that is shown next to the "KOŠARICA" button
    public  String  GetProductQuantity (){

        return driver.findElement(product_quantity).getText();
    }
    public void EnterChart(){
        driver.findElement(btn_chart).click();
    }
    public void NextStep(){
       WebElement btn =  driver.findElement(btn_next);
        actions.moveToElement(btn).click().perform();
    }
    public void ChoosePaymentOption_Cards(){
        driver.findElement(radio_cards).click();
    }
    public boolean IsOrderReady(){
        return driver.findElement(order_rewiev).getText().equals("Pregled narudžbe");
    }
    public void SetPickupInStore(){
        driver.findElement(radio_pickupStore).click();
    }
    //Returns quantity number of first item in chart
    public String GetQuantityOfFirstProductInChart(){
        return driver.findElement(firstProduct_quantity).getAttribute("value");
    }
}
