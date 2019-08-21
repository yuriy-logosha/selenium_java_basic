package selenium.tasks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FormPage {
    @FindBy(how = How.ID, using = "//div[2]//button[2]")
    private WebElement addPersonButton1;
    @FindBy(how = How.XPATH , using = "//div[2]//button[1]")
    private WebElement ResetListButton1;
    @FindBy(how = How.ID, using = "//div[4]//button[1]")
    private WebElement addPersonButton2;
    @FindBy(how = How.XPATH , using = "//div[4]//button[2]")
    private WebElement ResetListButton2;


}
