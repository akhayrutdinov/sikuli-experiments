package training.sikuli;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.Screen;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static training.sikuli.PresentationAssister.addReportField;
import static training.sikuli.PresentationAssister.getReportFilterElementId;
import static training.sikuli.PresentationAssister.toggleReportFiltersBar;

public class PentahoAnalyticsSikukiTest {

    private static final String PENTAHO_URL = "http://localhost:8080/pentaho/";

    private WebDriver driver;

    @Before
    public void before() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void after() throws Exception {
        PresentationAssister.keepScreen();
        driver.quit();
    }


    private void login() throws Exception {
        driver.get(PENTAHO_URL + "Login");
        driver.findElement(By.id("j_username")).sendKeys("Admin");
        driver.findElement(By.id("j_password")).sendKeys("password");
        driver.findElement(By.className("btn")).click();
    }

    private void switchToAnalyzer() throws Exception {
        driver.get(PENTAHO_URL + "api/repos/xanalyzer/editor?showFieldList=true&showFieldLayout=true&catalog=SteelWheels&cube=SteelWheelsSales&autoRefresh=true&showRepositoryButtons=true");
    }

    private void setUpReport() throws Exception {
        addReportField(driver, ReportField.TERRITORY);
        addReportField(driver, ReportField.COUNTRY);
        addReportField(driver, ReportField.SALES);
        // display charts
        driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ALT, "c"));
    }

    private void findBar() throws Exception {
        Screen s = new Screen();
        s.doubleClick("src/main/resources/bar.png");
    }

    private void assertFilterExists(String fieldName) {
        String domId = getReportFilterElementId(fieldName);
        assertNotNull(driver.findElement(By.id(domId)));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(String.format("document.getElementById(\"%s\").style.backgroundColor = \"green\";", domId));
    }

    private void assertFilterIsAbsent(String fieldName) {
        String domId = getReportFilterElementId(fieldName);
        try {
            driver.findElement(By.id(domId));
            fail("Expected not to reach this line");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // expected
        }
    }

    @Test
    public void testCharts() throws Exception {
        login();
        PresentationAssister.keepScreen();

        switchToAnalyzer();
        PresentationAssister.keepScreen();

        setUpReport();

        findBar();
        PresentationAssister.keepScreen();

        toggleReportFiltersBar(driver);

        assertFilterExists("Territory");
        assertFilterExists("Country");
        assertFilterIsAbsent("State");
    }
}
