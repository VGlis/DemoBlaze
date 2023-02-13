package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

public class LoginPage {

    public WebDriver driver;

    public LoginPage (ChromeDriver driver) {
        this.driver = driver;
    }
    public WebElement inputUserName () {
        return driver.findElement(By.xpath("//input[@id='loginusername']"));

    }

    public WebElement inputPassword () {
        return driver.findElement(By.xpath("//input[@id='loginpassword']"));

    }

    public WebElement buttonLogin () {
        return driver.findElement(By.id("login2"));
    }

    public WebElement buttonLogin2 () {
        return driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]"));
    }

    public void openPage () {
        driver.get("https://demoblaze.com/index.html");
    }
    public void setUserName (String userName) {
        inputUserName().sendKeys(userName);
    }

    public void setPassword (String Passsword) {
        inputPassword().sendKeys(Passsword);
    }

    public void clickLogin () {
        buttonLogin().click();
    }
    public void clickLogin2 () {
        buttonLogin2().click();
    }



   public String welcomeText(){
        return driver.findElement(By.id("nameofuser")).getText();

    }

    public WebElement inputCartLink() {
        return driver.findElement(By.linkText("Cart"));
    }

    public WebElement inputHomeLink() {
        return driver.findElement(By.xpath("//*[@id='navbarExample']/ul/li[1]/a"));
    }

    public WebElement inputAddToCartLink() {
        return driver.findElement(By.linkText("Add to cart"));
    }

    public WebElement inputSGS7Link() {
        return driver.findElement(By.linkText("Samsung galaxy s7"));
    }

    public String SamsungG7Text() {
        WebElement SamsungG7onHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/div[4]/div/div/h4/a"));
        String SamsungG7Text = SamsungG7onHomePage.getText();
        return SamsungG7Text;
    }

    public String SG7priceOnHomePageText(){
        WebElement SG7priceOnHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/div[4]/div/div/h5"));
        String SG7priceOnHomePageText = SG7priceOnHomePage.getText().substring(1);
        return SG7priceOnHomePageText;
    }

    public void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void close () {
        driver.close();
        driver.quit();
    }


}
