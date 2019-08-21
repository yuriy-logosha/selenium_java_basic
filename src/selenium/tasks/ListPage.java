package selenium.tasks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;


public class ListPage {
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'w3-container')]//button[contains(text(), 'Add')]")
    private WebElement addButton;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'w3-container')]//button[contains(text(), 'Reset')]")
    private WebElement resetButton;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'w3-xlarge')]//span[contains(@class, 'name')]")
    private WebElement name;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'w3-xlarge')]//span[contains(@class, 'surname')]")
    private WebElement surname;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'job')]")
    private WebElement job;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'dob')]")
    private WebElement dob;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'language')]")
    private WebElement language;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'gender')]")
    private WebElement gender;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'status')]")
    private WebElement status;
    @FindBy(how = How.XPATH, using = "//li[starts-with(@id, 'person')]")
    private List<WebElement> person;

    public void storeTheList () {
        System.out.println(person.size());
    }
    public void addPerson () {
        addButton.click();
    }
}
