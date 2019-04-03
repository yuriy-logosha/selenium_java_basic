package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Sample9Task {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         * 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.cssSelector("#start_green"));
        startGreen.click();
        TimeUnit.MILLISECONDS.sleep(500);
//         * 2) check that button does not appear,
        assertFalse(startGreen.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loadingGreen.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
        TimeUnit.MILLISECONDS.sleep(7 * 1000);
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
//         * success is seen instead "Green Loaded"
        WebElement finishGreen = driver.findElement(By.cssSelector("#finish_green"));
        assertTrue(finishGreen.isDisplayed());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         * 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.cssSelector("#start_green"));
        startGreen.click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertFalse(startGreen.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement finishGreen = driver.findElement(By.cssSelector("#finish_green"));
        assertTrue(finishGreen.isDisplayed());
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10)
                .ignoring(StaleElementReferenceException.class);
//         * 1) click on start loading green button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#start_green")));
        WebElement startGreen = driver.findElement(By.cssSelector("#start_green"));
        startGreen.click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#loading_green")));
        WebElement loadingGreen = driver.findElement(By.cssSelector("#loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertFalse(startGreen.isDisplayed());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#finish_green")));
        WebElement finishGreen = driver.findElement(By.cssSelector("#finish_green"));
        assertTrue(finishGreen.isDisplayed());
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
    }

}