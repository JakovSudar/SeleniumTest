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
    By btn_cookieClose = By.xpath("/html/body/div[14]/p/img");
    By btn_newsletterClose = By.xpath("/html/body/div[13]/div[1]/p");


    //methods
    public void SetSearchingProduct(String product){
        driver.findElement(textBox_search).sendKeys(product);
    }
    public void SubmitSearch(){
        driver.findElement(btn_submit_search).click();
    }
    public void CloseCookiePopup(){
        driver.findElement(btn_cookieClose).click();
    }
    public void CloseNewsletterPopup(){
        driver.findElement(btn_newsletterClose).click();
    }


}
