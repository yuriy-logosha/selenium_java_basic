package selenium.sample;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";

    @Test
    public void goToHomepage() throws Exception {
///         define driver
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        WebDriver browser = new ChromeDriver();

//         go to https://kristinek.github.io/site/index2.html
        browser.get("https://kristinek.github.io/site/index2.html");

//         get title of page
        System.out.println(browser.getTitle());

//         get URL of current page
        System.out.println(browser.getCurrentUrl());

//         close browser
        browser.quit();
    }
}
