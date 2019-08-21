package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.FeedbackSamplePage;
import selenium.pages.FeedbackSubmittedPage;

import java.util.concurrent.TimeUnit;

public class Task2 {
    private WebDriver driver;
    private static FeedbackSamplePage feedbackSample;
    private static FeedbackSubmittedPage FeedbackSubmitted;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        feedbackSample = PageFactory.initElements(driver, FeedbackSamplePage.class);
        FeedbackSubmitted = PageFactory.initElements(driver, FeedbackSubmittedPage.class);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void initialFeedbackPage() {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
        feedbackSample.noDataEntered();
//         check that the button send is blue with white letters
        feedbackSample.checkButtonSendColors();
    }

    @Test
    public void emptyFeedbackPage() {
//         TODO:
//         click "Send" without entering any data
        feedbackSample.clickSendButton();
//         check fields are empty or null
        FeedbackSubmitted.checkFieldsAreEmpty();
//         check button colors
//         (green with white letter and red with white letters)
        FeedbackSubmitted.checkButtonColors();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
        String name = "Fidan";
        String age = "22";
        String comment = "This page is useless";

//         TODO:
//      fill the whole form, click "Send"
        feedbackSample.fillAllFields(name, age, comment);
        feedbackSample.clickSendButton();
//         check fields are filled correctly
        FeedbackSubmitted.checkFeedbackFieldsFilledCorrectly(name, age, comment);
//         check button colors
//         (green with white letter and red with white letters)
        FeedbackSubmitted.checkButtonColors();
    }

    @Test
    public void yesOnFeedbackPageWithName() {
        String name = "Anar";
//         TODO:
//         enter only name
        feedbackSample.fillNameField(name);
//         click "Send"
        feedbackSample.clickSendButton();
//         click "Yes"
        FeedbackSubmitted.clickYes();
//         check message text: "Thank you, NAME, for your feedback!"
        FeedbackSubmitted.checkFinalMessageWithName(name);
//         color of text is white with green on the background
        FeedbackSubmitted.checkFinalMessageColors();
    }

    @Test
    public void yesOnFeedbackPageWithoutName() {
//         TODO:
//         click "Send" (without entering anything
        feedbackSample.clickSendButton();
//         click "Yes"
        FeedbackSubmitted.clickYes();
//         check message text: "Thank you for your feedback!"
        FeedbackSubmitted.checkFinalMessageWithoutName();
//         color of text is white with green on the background
        FeedbackSubmitted.checkFinalMessageColors();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        String name = "Anar";
        String age = "29";
        String comment = "Feedback";

        feedbackSample.fillAllFields(name, age, comment);
//         click "Send"
        feedbackSample.clickSendButton();
//         click "No"
        FeedbackSubmitted.clickNo();
//         check fields are filled correctly
        feedbackSample.checkFieldsFilledCorrectly(name, age, comment);
    }
}