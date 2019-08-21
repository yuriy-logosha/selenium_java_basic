package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FeedbackSubmittedPage {

    @FindBy(how = How.TAG_NAME, using = "span")
    private List<WebElement> dataFields;

    @FindBy(how = How.CSS, using = "#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-green.w3-xlarge")
    private WebElement buttonYes;

    @FindBy(how = How.CSS, using = "#fb_thx > div > div.w3-btn-group > button.w3-btn.w3-red.w3-xlarge")
    private WebElement buttonNo;

    @FindBy(how = How.CSS, using = "#message")
    private WebElement thanksMessage;

    @FindBy(how = How.CSS, using = ".w3-green")
    private WebElement messagePanel;

    public FeedbackSubmittedPage() {
    }



    public void checkFieldsAreEmpty() {
        for (WebElement dataField : dataFields) {
            assertTrue(dataField.getText().equals("") || dataField.getText().equals("null"));
        }
    }

    public void checkButtonColors() {
        assertEquals("rgba(76, 175, 80, 1)", buttonYes.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonYes.getCssValue("color"));
        assertEquals("rgba(244, 67, 54, 1)", buttonNo.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonNo.getCssValue("color"));
    }

    public void clickYes() {
        buttonYes.click();
    }

    public void clickNo() {
        buttonNo.click();
    }

    public void checkFinalMessageWithoutName() {
        assertEquals("Thank you for your feedback!", thanksMessage.getText());
    }

    public void checkFinalMessageColors() {
        assertEquals("rgba(76, 175, 80, 1)", messagePanel.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", messagePanel.getCssValue("color"));
    }

    public void checkFeedbackFieldsFilledCorrectly(String name, String age, String comment) {
        assertEquals(name, dataFields.get(0).getText());
        assertEquals(age, dataFields.get(1).getText());
        assertEquals("English", dataFields.get(2).getText());
        assertEquals("male", dataFields.get(3).getText());
        assertEquals("Good", dataFields.get(4).getText());
        assertEquals(comment, dataFields.get(5).getText());
    }

    public void checkFinalMessageWithName(String name) {
        assertEquals("Thank you, " + name + ", for your feedback!", thanksMessage.getText());
    }
}
