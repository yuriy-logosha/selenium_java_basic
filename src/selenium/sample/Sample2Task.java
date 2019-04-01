package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Sample2Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
//         get text "Heading 2 text" using id
        System.out.println(driver.findElement(By.id("heading_2")).getText());
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//         get attribute "id" and "value" of button "This is also a button" using name
        System.out.println(driver.findElement(By.name("randomButton2")).getAttribute("id"));
        System.out.println(driver.findElement(By.name("randomButton2")).getAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
        System.out.println(driver.findElement(By.className("test")).getText());
//         TODO:
//         get first text of class "test" (should be "Test Text 1")
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         get size text of class "test" (should be 5)
        System.out.println(driver.findElements(By.className("test")).size());

//         get text of class "test"
        List<WebElement> elements = driver.findElements(By.className("test"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
            System.out.println(element.getAttribute("class"));
        }

//         get third text of class "test" (should be "Test Text 4")
        System.out.println(driver.findElements(By.className("test")).get(2).getText());
        System.out.println(elements.get(2).getText());
    }
}
