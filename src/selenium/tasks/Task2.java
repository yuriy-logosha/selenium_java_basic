package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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

   // @After
  //  public void closeBrowser() {
  //      driver.quit();
  //  }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//        + check that all field are empty and no tick are clicked
//         +"Don't know" is selected in "Genre"
//         +"Choose your option" in "How do you like us?"
//        + check that the button send is blue with white letters

        List<WebElement> checkBoxes = driver.findElements(By.name("language"));
        WebElement NameField = driver.findElement(By.id("fb_name"));
        WebElement AgeField = driver.findElement(By.id("fb_age"));
        WebElement CommentField = driver.findElement(By.name("comment"));
        WebElement MaleRadio = driver.findElement(By.xpath("//div[4]//input[1]"));
        WebElement FemaleRadio = driver.findElement(By.xpath("//div[4]//input[2]"));
        WebElement UnknownRadio = driver.findElement(By.xpath("//div[4]//input[3]"));

        //Checking Login, Password and Comment fields are empty
        assertEquals(NameField.getText(), "");
        assertEquals(AgeField.getText(), "");
        assertEquals(CommentField.getText(), "");

        //Checking CheckBoxes aren't checked
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
            checkBox.click();
            assertTrue(checkBox.isSelected()); // checkboxes are selected
            checkBox.click();
            assertFalse(checkBox.isSelected()); // checkboxes are NOT selected
        }
        // Don't Know Radio button is checked
        assertTrue(UnknownRadio.isSelected()); // radio are selected

        // Male Radio button check
        assertFalse(MaleRadio.isSelected()); // radio are NOT selected
        MaleRadio.click();
        assertTrue(MaleRadio.isSelected());

        // Female Radio button check
        assertFalse(FemaleRadio.isSelected()); // radio are NOT selected
        FemaleRadio.click();
        assertTrue(FemaleRadio.isSelected());

        //Check dropdown
        //Select dropdown = new Select(driver.findElement(By.id("like_us")));
        //assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());

        // Check color of button and button letter
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("background-color"),"rgba(33, 150, 243, 1)");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).getCssValue("text-decoration-color"),"rgb(255, 255, 255)");

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//        + click "Send" without entering any data
//        +check fields are empty or null
//        +check button colors
//        + (green with white letter and red with white letters)

        //Click on button submit
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

        //Check all fields is empty
        WebElement NameField = driver.findElement(By.id("name"));
        assertEquals(NameField.getText(),"");

        WebElement AgeField = driver.findElement(By.id("age"));
        assertEquals(AgeField.getText(),"");

        WebElement LanguageField = driver.findElement(By.id("language"));
        assertEquals(LanguageField.getText(),"");

        WebElement GenderField = driver.findElement(By.id("gender"));
        assertEquals(GenderField.getText(),"null");

        WebElement OptionField = driver.findElement(By.id("option"));
        assertEquals(OptionField.getText(),"null");

        WebElement CommentField = driver.findElement(By.id("comment"));
        assertEquals(CommentField.getText(),"");

        //Check button letters and background color
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color"),"rgba(76, 175, 80, 1)");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("text-decoration-color"),"rgb(255, 255, 255)");

        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("background-color"),"rgba(244, 67, 54, 1)");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("text-decoration-color"),"rgb(255, 255, 255)");
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//        + fill the whole form, click "Send"
//        + check fields are filled correctly
//        + check button colors
//         +(green with white letter and red with white letters)
        WebElement NameField = driver.findElement(By.id("fb_name"));
        WebElement AgeField = driver.findElement(By.id("fb_age"));
        WebElement CheckBox = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]"));
        WebElement MaleRadio = driver.findElement(By.xpath("//div[4]//input[1]"));
        WebElement CommentField = driver.findElement(By.name("comment"));
        String name  = "name";
        String age  = "22";
        String comment = "comments";

        // Complete form
        NameField.sendKeys(name);
        AgeField.sendKeys(age);
        CheckBox.click();
        MaleRadio.click();
        CommentField.sendKeys(comment);
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByValue("Why me?");

        //Click on button submit
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

        //Check all fields is correctly completed
        WebElement NameField2 = driver.findElement(By.id("name"));
        assertEquals(NameField2.getText(),"name");

        WebElement AgeField2 = driver.findElement(By.id("age"));
        assertEquals(AgeField2.getText(),"22");

        WebElement LanguageField2 = driver.findElement(By.id("language"));
        assertEquals(LanguageField2.getText(),"English");
        System.out.println(LanguageField2.getText());

        WebElement GenderField2 = driver.findElement(By.id("gender"));
        assertEquals(GenderField2.getText(),"male");
        System.out.println(GenderField2.getText());

        WebElement OptionField2 = driver.findElement(By.id("option"));
        assertEquals(OptionField2.getText(),"Why me?");
        System.out.println(OptionField2.getText());

        WebElement CommentField2 = driver.findElement(By.id("comment"));
        assertEquals(CommentField2.getText(),"comments");


        //Check button letters and background color
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("background-color"),"rgba(76, 175, 80, 1)");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).getCssValue("text-decoration-color"),"rgb(255, 255, 255)");

        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("background-color"),"rgba(244, 67, 54, 1)");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).getCssValue("text-decoration-color"),"rgb(255, 255, 255)");
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background

        //Enter name
        WebElement NameField = driver.findElement(By.id("fb_name"));
        String name  = "name";
        NameField.sendKeys(name);

        //Click on button send
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

        //Click on button Yes
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();

        WebElement Message = driver.findElement(By.id("message"));
        assertEquals("Thank you, name, for your feedback!",Message.getText());
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         +click "Send" (without entering anything)
//         +click "Yes"
//         +check message text: "Thank you for your feedback!"
//        + color of text is white with green on the background

        //Click on button send
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

        //Click on button Yes
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[1]")).click();

        WebElement Message = driver.findElement(By.id("message"));
        assertEquals("Thank you for your feedback!",Message.getText());
        assertEquals(Message.getCssValue("background-color"),"rgba(0, 0, 0, 0)");
        assertEquals(Message.getCssValue("color"),"rgba(255, 255, 255, 1)");

    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly

        WebElement NameField = driver.findElement(By.id("fb_name"));
        WebElement AgeField = driver.findElement(By.id("fb_age"));
        WebElement CheckBox = driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]"));
        WebElement MaleRadio = driver.findElement(By.xpath("//div[4]//input[1]"));
        WebElement CommentField = driver.findElement(By.name("comment"));
        String name  = "name";
        String age  = "22";
        String comment = "comments";

        // Complete form
        NameField.sendKeys(name);
        AgeField.sendKeys(age);
        CheckBox.click();
        MaleRadio.click();
        CommentField.sendKeys(comment);
        Select dropdown = new Select(driver.findElement(By.id("like_us")));
        dropdown.selectByValue("Why me?");

        //Click on button submit
        driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/button")).click();

        //Click on button No
        driver.findElement(By.xpath("//*[@id=\"fb_thx\"]/div/div[2]/button[2]")).click();


        System.out.println(driver.findElement(By.id("fb_name")).getText());
       // System.out.println(AgeField.getText());
      //  System.out.println(CommentField.getText());

        /* assertEquals(NameField.getText(),"name");
        assertEquals(AgeField.getText(),"22");

        assertTrue(driver.findElement(By.xpath("//*[@id=\"lang_check\"]/input[1]")).isSelected());

        assertTrue(driver.findElement(By.xpath("//*[@id=\"fb_form\"]/form/div[4]/input[1]")).isSelected());


        WebElement CommentField2 = driver.findElement(By.id("comment"));
        assertEquals(CommentField2.getText(),"comments"); */

    }
}
