package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.TablePage;

import java.util.List;

public class CartPage {

    public WebDriver driver;

    public CartPage(ChromeDriver driver) {
        this.driver = driver;
    }

    public WebElement inputCartLink() {
        return driver.findElement(By.linkText("Cart"));
    }

    public String SamsungG7Text() {
        WebElement SamsungG7onHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]"));
        String SamsungG7Text = SamsungG7onHomePage.getText();
        return SamsungG7Text;
    }

    public String SG7priceOnHomePageText(){
        WebElement SG7priceOnHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/div[4]/div/div/h5"));
        String SG7priceOnHomePageText = SG7priceOnHomePage.getText().substring(1);
        return SG7priceOnHomePageText;
    }

    public String SG7priceInCartPage(){
        WebElement SG7priceInCartPage = driver.findElement(By.xpath("//*[@id='tbodyid']/tr[1]/td[3]"));
        String SG7priceInCartPageText = SG7priceInCartPage.getText();
        return SG7priceInCartPageText;
    }

    public String TotalPriceText(){
        WebElement TotalPrice = driver.findElement(By.id("totalp"));
        String TotalPriceText = TotalPrice.getText();
        return TotalPriceText;
    }

    public void checkCartIsEmptySamsungG7() {
        WebElement SamsungG7onHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/div[4]/div/div/h4/a"));
        String SamsungG7Text = SamsungG7onHomePage.getText();
        WebElement SG7priceOnHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/div[4]/div/div/h5"));
        String SG7priceOnHomePageText = SG7priceOnHomePage.getText().substring(1);
        System.out.println("║ Home page - Selected   product  is: " + SamsungG7Text);
        System.out.println("║ Home page - Price of " + SamsungG7Text + " is: $" + SG7priceOnHomePageText);
        inputCartLink().click();
        int numberSamsungG7Before = 0;
        TablePage tablePage = new TablePage(driver);
        List<List<String>> tableDataBeforeAddProduct = tablePage.getTableData();
        for(int n = 0; n < tableDataBeforeAddProduct.size(); n++) {
            if(tableDataBeforeAddProduct.get(n).get(1).equals(SamsungG7Text)){
                numberSamsungG7Before ++;
            }

        }
    }

    public void viewTableinCartPage(){
        TablePage tablePage = new TablePage(driver);
        List<List<String>> tableData = tablePage.getTableData();
        for(int i = 0; i < tableData.size(); i++) {
            List<String> tableRow = tableData.get(i);
            int row = i + 1;
            for(int j = 0; j < tableRow.size(); j++) {
                int column = j + 1;
                System.out.println("║ Row: "+ row + " ║ Column: " + column + " ║ = ║ " + tableRow.get(j));

            }

        }
    }

    public Integer numberSamsungG7Before(){
        WebElement SamsungG7onHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]"));
        String SamsungG7Text = SamsungG7onHomePage.getText();
        int numberSamsungG7Before = 0;
        TablePage tablePage = new TablePage(driver);
        List<List<String>> tableDataBeforeAddProduct = tablePage.getTableData();
        for(int n = 0; n < tableDataBeforeAddProduct.size(); n++) {
            if(tableDataBeforeAddProduct.get(n).get(1).equals(SamsungG7Text)){
                numberSamsungG7Before ++;
            }

        }
        return numberSamsungG7Before;
    }

    public Integer numberSamsungG7After(){
        WebElement SamsungG7onHomePage = driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]"));
        String SamsungG7Text = SamsungG7onHomePage.getText();
        TablePage tablePage = new TablePage(driver);
        int numberSamsungG7After = 0;
        List<List<String>> tableDataAfterAddProduct = tablePage.getTableData();
        for(int z = 0; z < tableDataAfterAddProduct.size(); z++) {
            if(tableDataAfterAddProduct.get(z).get(1).equals(SamsungG7Text)){
                numberSamsungG7After ++;
            }
        }
        return numberSamsungG7After;
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
