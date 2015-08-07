package training.sikuli;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


class PresentationAssister {

    static void keepScreen() throws Exception {
        Thread.sleep(5000);
    }

    static void addReportField(WebDriver driver, ReportField field) {
        WebElement element = driver.findElement(By.id(field.getDomId()));
        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }

    static void toggleReportFiltersBar(WebDriver driver) {
        driver.findElement(By.id("RPT001FilterPaneToggle")).click();
    }

    static String getReportFilterElementId(String fieldName) {
        return String.format("filter_[Markets].[%s]", fieldName);
    }
}
