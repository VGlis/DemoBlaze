package test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.TablePage;
import pages.CartPage;
import java.util.List;



public class LogInTest {
    @Test
    public void verifyLoginWithSTestUserAndAddToCart() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vladan\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        LoginPage loginpage = new LoginPage(driver);
        loginpage.openPage();
        loginpage.clickLogin();
        loginpage.sleep();
        loginpage.setUserName("test");
        loginpage.setPassword("test");
        loginpage.clickLogin2();
        loginpage.sleep();
        Assert.assertEquals("Welcome test not found", "Welcome test", loginpage.welcomeText());
        loginpage.sleep();

        String SG7priceOnHomePageText = loginpage.SG7priceOnHomePageText();
        int SG7priceOnHomePage = Integer.parseInt(SG7priceOnHomePageText);

        CartPage cartpage = new CartPage(driver);

        cartpage.checkCartIsEmptySamsungG7();

        loginpage.sleep();
        loginpage.inputHomeLink().click();
        loginpage.sleep();
        loginpage.inputSGS7Link().click();
        loginpage.sleep();
        loginpage.inputAddToCartLink().click();
        loginpage.sleep();
        driver.switchTo().alert().accept();
        loginpage.sleep();
        loginpage.inputCartLink().click();
        loginpage.sleep();

        System.out.println("║ -------------------------------------------------------------║");
        System.out.println("║ Cart page - View Table in Cart Page: ");

        cartpage.viewTableinCartPage();

        int SG7priceInCartPageInt = Integer.parseInt(cartpage.SG7priceInCartPage());
        int TotalPriceInt = Integer.parseInt(cartpage.TotalPriceText());
        int numberSamsungG7After = cartpage.numberSamsungG7After();
        int numberSamsungG7Before = cartpage.numberSamsungG7Before();
        int TotalPriceIntSum = SG7priceInCartPageInt * numberSamsungG7After;

        System.out.println("║ -------------------------------------------------------------║");
        System.out.println("║ Cart page - Price of " + cartpage.SamsungG7Text() + "    is: $" + SG7priceInCartPageInt);
        System.out.println("║ Cart page - Number of " + cartpage.SamsungG7Text() + "   is: " + numberSamsungG7After);
        System.out.println("║ Cart page - Total Price " + cartpage.SamsungG7Text() + " is: $" + TotalPriceIntSum);

        Assert.assertEquals("Test is Failed", TotalPriceIntSum, TotalPriceInt);
        Assert.assertEquals("Test is Failed", SG7priceInCartPageInt, SG7priceOnHomePage);
        Assert.assertEquals("Test is Failed", numberSamsungG7Before, numberSamsungG7After);
        System.out.println("║ -------------------------------------------------------------║");

        cartpage.close();
    }
}


