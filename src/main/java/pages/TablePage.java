package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TablePage {

    public WebDriver driver;

    public TablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {

    }

    public List<List<String>> getTableData() {
        List<List<String>> tableData = new ArrayList<>();

        WebElement tableRoot = driver.findElement(By.className("table-responsive"));

        WebElement tBody = tableRoot.findElement(By.id("tbodyid"));

        List<WebElement> tRows = tBody.findElements(By.className("success"));

        for(int i = 0; i < tRows.size(); i++) {
            List<WebElement> tDataCells = tRows.get(i).findElements(By.xpath(".//td"));
            List<String> tRow = new ArrayList<>();
            for(int j = 0; j < tDataCells.size(); j++) {
                //System.out.println(tDataCells.get(j).getText());
                tRow.add(tDataCells.get(j).getText());
            }
            tableData.add(tRow);
        }

        return tableData;
    }


    public void close() {
        driver.close();
        driver.quit();
    }

}
