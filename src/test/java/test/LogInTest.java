package test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.TablePage;
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

        WebElement SamsungG7onHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/div[4]/div/div/h4/a"));
        String SamsungG7Text = SamsungG7onHomePage.getText();
        WebElement SG7priceOnHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/div[4]/div/div/h5"));
        String SG7priceOnHomePageText = SG7priceOnHomePage.getText().substring(1);
        System.out.println("║ Home page - Selected   product  is: " + SamsungG7Text);
        System.out.println("║ Home page - Price of " + SamsungG7Text + " is: $" + SG7priceOnHomePageText);
        loginpage.inputCartLink().click();

        loginpage.sleep();

        int numberSamsungG7Before = 0;
        TablePage tablePage = new TablePage(driver);
        List<List<String>> tableDataBeforeAddProduct = tablePage.getTableData();
        for(int n = 0; n < tableDataBeforeAddProduct.size(); n++) {
           if(tableDataBeforeAddProduct.get(n).get(1).equals(SamsungG7Text)){
                numberSamsungG7Before ++;
           }

        }

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
        List<List<String>> tableData = tablePage.getTableData();
        for(int i = 0; i < tableData.size(); i++) {
            List<String> tableRow = tableData.get(i);
            int row = i + 1;
            for(int j = 0; j < tableRow.size(); j++) {
                int column = j + 1;
                System.out.println("║ Row: "+ row + " ║ Column: " + column + " ║ = ║ " + tableRow.get(j));

            }

        }



        int numberSamsungG7After = 0;
        List<List<String>> tableDataAfterAddProduct = tablePage.getTableData();
        for(int z = 0; z < tableDataAfterAddProduct.size(); z++) {
            if(tableDataAfterAddProduct.get(z).get(1).equals(SamsungG7Text)){
               numberSamsungG7After ++;
            }

        }

        WebElement SG7priceInCartPage = driver.findElement(By.xpath("//*[@id='tbodyid']/tr[1]/td[3]"));
        String SG7priceInCartPageText = SG7priceInCartPage.getText();

        WebElement TotalPrice = driver.findElement(By.id("totalp"));
        String TotalPriceText = TotalPrice.getText();

        int SG7priceInCartPageInt = Integer.parseInt(SG7priceInCartPageText);
        int TotalPriceInt = Integer.parseInt(TotalPriceText);
        int TotalPriceIntSum = SG7priceInCartPageInt * numberSamsungG7After;

        System.out.println("║ -------------------------------------------------------------║");
        System.out.println("║ Cart page - Price of " + SamsungG7Text + "    is: $" + SG7priceInCartPageText);
        System.out.println("║ Cart page - Number of " + SamsungG7Text + "   is: " + numberSamsungG7After);
        System.out.println("║ Cart page - Total Price " + SamsungG7Text + " is: $" + TotalPriceIntSum);

        Assert.assertEquals("Test is Failed", TotalPriceIntSum, TotalPriceInt);
        Assert.assertEquals("Test is Failed", SG7priceInCartPageText, SG7priceOnHomePageText);
        Assert.assertEquals("Test is Failed", numberSamsungG7Before + 1, numberSamsungG7After);
        System.out.println("║ -------------------------------------------------------------║");



        tablePage.close();

    }
}


