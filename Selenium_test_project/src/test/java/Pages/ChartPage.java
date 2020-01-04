package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChartPage {
    WebDriver driver = null;

    //injecting driver trough constructor
    public ChartPage(WebDriver driver){
        this.driver=driver;
    }

    By product_quantity = By.id("basket-number-mobile");
    By btn_chart = By.id("desktop-cart-button");

    //methods
    public  String  GetProductQuantity (){

        return driver.findElement(product_quantity).getText();
    }
    public void EnterChart(){
        driver.findElement(btn_chart).click();
    }
}
