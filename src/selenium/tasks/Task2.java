package selenium.tasks;

import javafx.scene.control.RadioButton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import selenium.pages.AgeSamplePage;
import selenium.pages.AgeSubmittedSamplePage;

import javax.swing.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Task2<checkBoxes> {

    static WebDriver driver;
    static AgeSamplePage agePage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:

        assertEquals("", driver.findElement(By.id("fb_name")).getText());

        assertEquals("", driver.findElement(By.id("fb_age")).getText());

        //check that all field are empty


        List<WebElement> checkBoxes = driver.findElements(By.name("language")); //FIND CHECKBOXES

        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }


        List<WebElement> radioButtons = driver.findElements(By.className("w3-radio")); //FIND radiobuttons

        assertTrue(radioButtons.get(2).isSelected());

//         "Choose your option" in "How do you like us?"


        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());


//         check that the button send is blue with white letters

        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));

        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));

        // assertEquals("white", sendButton.getCssValue("text-color"));

        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));




    }


    @Test
    public void emptyFeedbackPage
            () throws Exception {
//         TODO:
//         click "Send" without entering any data

        WebElement sendButton = driver.findElement(By.className("w3-btn-block"));
        sendButton.click();


//         check fields are empty or null


        assertEquals("", driver.findElement(By.id("name")).getText());

        assertEquals("", driver.findElement(By.id("age")).getText());

        assertEquals("", driver.findElement(By.id("language")).getText());

        assertEquals("null", driver.findElement(By.id("gender")).getText());

        assertEquals("null", driver.findElement(By.id("option")).getText());

        assertEquals("", driver.findElement(By.id("comment")).getText());


//         check button colors

        WebElement yesButton = driver.findElement(By.className("w3-green"));

        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));


        // assertEquals("white", sendButton.getCssValue("text-color"));

        WebElement noButton = driver.findElement(By.className("w3-red"));

        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));

        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));

       //(green with white letter and red with white letters)



        @Test
        public void notEmptyFeedbackPage() throws Exception {

//         TODO:
//         fill the whole form, click "Send"
//
      WebElement textArea = driver.findElement(By.id("numb"));  ///find text area 1


            //
            //        String newTextOne = "text"; //enter text "text" in area 1
            //
            //        textArea.sendKeys(newTextOne); // sending keys "text"
            //
            //        driver.findElement(By.className("w3-btn")).click(); //click on submit button
            //
            //        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
            //
            //        //assert actual end expected error messages are same

//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        }




    }}