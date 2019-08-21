package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;



import java.awt.*;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GenericSamplePage {
    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBys({@FindBy(className = "w3-input")})
    private List<WebElement> textFields;

    @FindBys({@FindBy(name = "gender")})
    private List<WebElement> genderOption;

    @FindBy(id="like_us")
    private WebElement likeSelect;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public String getPageHeader() {
        return header.getText();
    }

    public void checkPageHeaderText(String aHeaderText) {
        assertEquals(getPageHeader(), aHeaderText);
    }

    public void isTextInputEmpty(){
        for (WebElement txtFld: textFields) {
            assertEquals("", txtFld.getText());
        }
    }
    public void isDunnoChecked(){
        assertTrue(genderOption.get(2).isSelected());
       }

    public void checkLikeOption(){
        Select select = new Select(likeSelect);
        assertEquals("Choose your option", select.getFirstSelectedOption().getText());
     }

     public void checkSendColor(){
         assertEquals("rgba(33, 150, 243, 1)", submitButton.getCssValue("background-color") );
         assertEquals("rgba(255, 255, 255, 1)", submitButton.getCssValue("color") );
     }

    }




