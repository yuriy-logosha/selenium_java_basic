package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

public class FeedbackSamplePage {

    @FindBy(how = How.CSS, using = ".w3-check[type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(how = How.XPATH, using = "//input")
    private List<WebElement> inputFields;

    @FindBy(how = How.CSS, using = ".w3-radio[type='radio']")
    private List<WebElement> radiobuttons;

    @FindBy(how = How.ID, using = "like_us")
    private WebElement HowlikeUsSelect;

    @FindBy(how = How.TAG_NAME, using = "button")
    private WebElement buttonSend;

    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameField;

    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageField;

    @FindBy(how = How.TAG_NAME, using = "textarea")
    private WebElement textField;

    public FeedbackSamplePage() {
    }


    public void noDataEntered() {
        for (WebElement checkBox : checkboxes) {
            assertFalse(checkBox.isSelected());
        }

        assertFalse(radiobuttons.get(0).isSelected());
        assertFalse(radiobuttons.get(1).isSelected());
        assertTrue(radiobuttons.get(2).isSelected());

        for (WebElement inputField : inputFields) {
            assertEquals("", inputField.getText());
        }

        Select dropdown = new Select(HowlikeUsSelect);
        assertEquals("Choose your option", dropdown.getFirstSelectedOption().getText());
    }

    public void clickSendButton() {
        buttonSend.click();
    }

    public void checkButtonSendColors() {
        assertEquals("rgba(33, 150, 243, 1)", buttonSend.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", buttonSend.getCssValue("color"));
    }

    public void fillNameField(String name) {
        nameField.sendKeys(name);
    }

    public void fillAllFields(String name, String age, String comment) throws InterruptedException {
        nameField.sendKeys(name);
        ageField.sendKeys(age);
        textField.sendKeys(comment);
        checkboxes.get(0).click();
        radiobuttons.get(0).click();

        Select dropdown = new Select(HowlikeUsSelect);
        dropdown.selectByIndex(1);
    }

    public void checkFieldsFilledCorrectly(String name, String age, String comment) {

        assertEquals(name, nameField.getAttribute("value"));
        assertEquals(age, ageField.getAttribute("value"));
        assertTrue(checkboxes.get(0).isSelected());
        assertTrue(radiobuttons.get(0).isSelected());

        Select dropdown = new Select(HowlikeUsSelect);
        assertEquals("Good", dropdown.getFirstSelectedOption().getText());

        assertEquals(comment, textField.getAttribute("value"));
    }
}
