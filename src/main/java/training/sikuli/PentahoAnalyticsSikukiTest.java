package training.sikuli;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.Screen;

public class PentahoAnalyticsSikukiTest {

  private static final String PENTAHO_URL = "http://localhost:8080/pentaho/";

  private WebDriver driver;

  @Before
  public void before() {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait( 15, TimeUnit.SECONDS );
    driver.manage().window().maximize();
  }

  @After
  public void after() throws InterruptedException {
    Thread.sleep( 5000 );
    driver.quit();
  }

  @Test
  public void testCharts() throws Exception {
    driver.get( PENTAHO_URL + "Login" );
    WebElement loginInput = driver.findElement( By.id( "j_username" ) );
    loginInput.sendKeys( "Admin" );

    WebElement inputPass = driver.findElement( By.id( "j_password" ) );
    inputPass.sendKeys( "password" );

    WebElement loginButton = driver.findElement( By.className( "btn" ) );
    loginButton.click();

    Thread.sleep( 5000 );

    driver
        .get( PENTAHO_URL
            + "api/repos/xanalyzer/editor?showFieldList=true&showFieldLayout=true&catalog=SteelWheels&cube=SteelWheelsSales&autoRefresh=true&showRepositoryButtons=true" );

    Thread.sleep( 5000 );
    
    WebElement territory = driver.findElement( By.id("dojoUnique2") );
    Actions action = new Actions(driver);
    action.doubleClick( territory ).perform();
    
    WebElement county = driver.findElement( By.id("dojoUnique3") );
    action = new Actions(driver);
    action.doubleClick( county ).perform();
    
    WebElement sales = driver.findElement( By.id("dojoUnique7") );
    action = new Actions(driver);
    action.doubleClick( sales ).perform();
    
    driver.findElement(By.xpath("//body")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ALT, "c"));
    
    Screen s = new Screen();

    s.doubleClick( "src/main/resources/bar.png" );
    
    Thread.sleep( 5000 );
    
    WebElement filter = driver.findElement( By.id("RPT001FilterPaneToggle") );
    filter.click();
    
    JavascriptExecutor js = (JavascriptExecutor) driver;
    
    
    Assert.assertNotNull(driver.findElement(By.id( "filter_[Markets].[Territory]" )));
    js.executeScript("document.getElementById(\"filter_[Markets].[Territory]\").style.backgroundColor = \"green\"; ");
    
    Assert.assertNotNull(driver.findElement(By.id( "filter_[Markets].[Country]" )));
    js.executeScript("document.getElementById(\"filter_[Markets].[Country]\").style.backgroundColor = \"green\"; ");
    
    try {
      driver.findElement(By.id( "filter_[Markets].[State]" ));
      Assert.fail("Should be failed here");
    } catch (org.openqa.selenium.NoSuchElementException e) {
      // expected
    }
  }

}
