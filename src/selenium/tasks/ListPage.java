package selenium.tasks;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ListPage {
    @FindBy(how = How.XPATH, using = "//div[2]//button[1]")
    private WebElement addPersonButton1;
    @FindBy(how = How.XPATH, using = "//div[2]//button[2]")
    private WebElement ResetListButton1;
    @FindBy(how = How.XPATH, using = "//div[4]//button[1]")
    private WebElement addPersonButton2;
    @FindBy(how = How.XPATH, using = "//div[4]//button[2]")
    private WebElement ResetListButton2;
    @FindBy(how = How.CLASS_NAME, using = "w3-padding-16")
    private List PersonList;


    public void clickAddPerson1() {
        addPersonButton1.click();
    }

    public void clickAddPerson2() {
        addPersonButton2.click();
    }

    public void clickResetList1() {
        ResetListButton1.click();
    }

    public void clickResetList2() {
        ResetListButton2.click();
    }


}
