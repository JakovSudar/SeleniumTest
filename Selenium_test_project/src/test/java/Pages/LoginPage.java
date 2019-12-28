package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver = null;

    //injecting driver trought constructor
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //declaring elements
    By textBox_username = By.id("username");
    By textBox_password = By.name("password");
    By btn_login = By.name("submit");

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

}
