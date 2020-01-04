package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver = null;

    //constructor driver injection
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Declaring elements
    By textBox_search = By.name("q");
    By btn_submit_search = By.className("submit");


    //methods
    public void SetSearchingProduct(String product){
        driver.findElement(textBox_search).sendKeys(product);
    }
    public void SubmitSearch(){
        driver.findElement(btn_submit_search).click();
    }



}
