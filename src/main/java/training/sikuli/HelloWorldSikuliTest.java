package training.sikuli;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class HelloWorldSikuliTest {

  public static void main( String[] args ) {
    
    WebDriver driver = new FirefoxDriver();
    
    driver.manage().window().maximize();
    
    // And now use this to visit RZD - Russian Railways
    driver.get( "http://property.rzd.ru/" );

    Screen s = new Screen();
    try {
      s.doubleClick( "src/main/resources/SaintPetersburg.png" );
      Thread.sleep( 5000 );
    } catch ( FindFailed | InterruptedException e ) {
      e.printStackTrace();
    }

    driver.quit();
  }

}
