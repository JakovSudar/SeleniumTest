package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver = null;

    //injecting driver trough constructor
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //declaring elements
    By textBox_username = By.id("username");
    By textBox_password = By.name("password");
    By btn_login = By.name("submit");
    By btn_logoff = By.xpath("/html/body/div[2]/div/nav[2]/a[6]/span");
    By message = By.id("server_message");

    //methods
    public void SetUsername(String username){
        driver.findElement(textBox_username).sendKeys(username);
    }
    public void SetPassword(String password){
        driver.findElement(textBox_password).sendKeys(password);
    }
    public void SubmitLogin(){
        driver.findElement(btn_login).click();
    }
    public void LogOff(){
        driver.findElement(btn_logoff).click();
    }
    public String GetErrorMessage(){
        return driver.findElement(message).getText();
    }

}
