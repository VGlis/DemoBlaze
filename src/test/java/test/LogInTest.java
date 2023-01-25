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
        loginpage.inputCartLink().click();
        loginpage.sleep();


        String SamsungG7 = "Samsung galaxy s7";
        int numberSamsungG7Before = 0;
        TablePage tablePage = new TablePage(driver);
        List<List<String>> tableDataBeforeAddProduct = tablePage.getTableData();
        for(int n = 0; n < tableDataBeforeAddProduct.size(); n++) {
           if(tableDataBeforeAddProduct.get(n).get(1).equals(SamsungG7)){
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
            if(tableDataAfterAddProduct.get(z).get(1).equals(SamsungG7)){
                numberSamsungG7After ++;
            }
        }
        System.out.println("║ Number of Samsung G7 is: " + numberSamsungG7After);

        Assert.assertEquals("Test is Failed", numberSamsungG7Before + 1, numberSamsungG7After);

        tablePage.close();

    }
}


