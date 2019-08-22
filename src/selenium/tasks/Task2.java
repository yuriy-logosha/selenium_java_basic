package selenium.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

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
//         check that all field are empty and no tick are clicked
        WebElement name = driver.findElement(By.id("fb_name"));
        assertEquals("", name.getText());

        WebElement age = driver.findElement(By.id("fb_age"));
        assertEquals("", age.getText());

        WebElement language = driver.findElement(By.className("w3-check"));
        assertEquals("", language.getText());

//         "Don't know" is selected in "Genre"
        WebElement dontKnow = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[3]"));
        assertTrue(dontKnow.isSelected());

//         "Choose your option" in "How do you like us?"
        WebElement yourOption = driver.findElement((By.xpath("//*[@id=\"like_us\"]/option[1]")));
        assertEquals("Choose your option", yourOption.getText());

        WebElement comment = driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea"));
        assertEquals("", comment.getText());

//         check that the button send is blue with white letters
        assertEquals("rgba(33, 150, 243, 1)", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("background-color") );
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("color") );
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         check fields are empty or null
        WebElement name = driver.findElement(By.id("name"));
        WebElement age = driver.findElement((By.id("age")));
        WebElement language = driver.findElement(By.id("language"));
        WebElement gender = driver.findElement(By.id("gender"));
        WebElement option = driver.findElement((By.id("option")));
        WebElement comment = driver.findElement(By.id("comment"));

        assertEquals("" , name.getText());
        assertEquals("", age.getText());
        assertEquals("", language.getText());
        assertEquals("null", gender.getText());
        assertEquals("null", option.getText());
        assertEquals("", comment.getText());

        //check button colours
        //(green with white letters and red with white letters
        //yes button
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color") );
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("color") );
        //no button
        assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("background-color") );
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("color") );

    }



            @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"

        driver.findElement(By.id("fb_name")).sendKeys("Diana");
        driver.findElement(By.id("fb_age")).sendKeys("24");
        driver.findElement((By.xpath("//*[@id=\"lang_check\"]/input[1]"))).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys("bla bla bla");

        // click button
                driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

//         check fields are filled correctly
                assertEquals("Diana", driver.findElement(By.id("name")).getText());
                assertEquals("24", driver.findElement(By.id("age")).getText());
                assertEquals("English", driver.findElement(By.id("language")).getText());
                assertEquals("female", driver.findElement(By.id("gender")).getText());
                assertEquals("Good", driver.findElement(By.id("option")).getText());
                assertEquals("bla bla bla", driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
                //yes button
                assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color") );
                assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("color") );
    //no button
                assertEquals("rgba(244, 67, 54, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("background-color") );
                assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("color") );
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        String myName = "Diana";
        WebElement name = driver.findElement(By.id("fb_name"));
        name.sendKeys(myName);
//         click "Send"
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();
//         click "Yes"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();
//         check message text: "Thank you, NAME, for your feedback!"
        assertEquals("Thank you, " + myName + ", for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
        assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("color") );
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("background-color") );
    }
    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click(); // click "Yes"
//         check message text: "Thank you for your feedback!"
        Assert.assertEquals("Thank you for your feedback!", driver.findElement(By.id("message")).getText());
//         color of text is white with green on the background
          assertEquals("rgba(255, 255, 255, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("color"));
        assertEquals("rgba(76, 175, 80, 1)", driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div")).getCssValue("background-color"));
    }
    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        driver.findElement(By.id("fb_name")).sendKeys("Diana");
        driver.findElement(By.id("fb_age")).sendKeys("24");
        driver.findElement((By.xpath("//*[@id=\"lang_check\"]/input[1]"))).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).sendKeys("bla bla bla");

        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click(); // click "Send"
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).click();  //  click "No"
//         check fields are filled correctly
        assertEquals("Diana", driver.findElement(By.xpath("//*[@id=\"fb_name\"]")).getAttribute("value"));
        assertEquals("24", driver.findElement(By.id("fb_age")).getAttribute("value"));
        assertEquals("English", driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).getAttribute("value"));
        assertEquals("female", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[2]")).getAttribute("value"));
        assertEquals("Good", driver.findElement(By.xpath("//*[@id=\"like_us\"]/option[2]")).getAttribute("value"));
        assertEquals("bla bla bla", driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[6]/textarea")).getAttribute("value"));
    }
}
