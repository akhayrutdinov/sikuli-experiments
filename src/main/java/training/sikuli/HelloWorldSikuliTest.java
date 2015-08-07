package training.sikuli;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.Screen;

public class HelloWorldSikuliTest {

    @Test
    public void demonstrate() throws Exception {
        WebDriver driver = new FirefoxDriver();
        try {
            driver.manage().window().maximize();
            driver.get("http://property.rzd.ru/");

            Screen s = new Screen();
            s.doubleClick("src/main/resources/SaintPetersburg.png");

            PresentationAssister.keepScreen();
        } finally {
            driver.quit();
        }
    }
}
