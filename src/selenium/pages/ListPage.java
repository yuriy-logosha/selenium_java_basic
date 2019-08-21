package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import selenium.tasks.Person;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ListPage {
    @FindBy(how = How.CLASS_NAME, using = "w3-padding-16") //
            List<WebElement> persons; //
    @FindBy(how = How.XPATH, using = "//div[4]//button[1]")
    WebElement addBtn;
    @FindBy(how = How.XPATH, using = "//li[@id='person2']//span[@class='w3-closebtn editbtn w3-padding w3-margin-right w3-medium']")
    WebElement editBtn;


    public List<Person> getPersons() {

        ArrayList<Person> personList = new ArrayList<>();
        for(WebElement personElement : persons) {
            Person person = new Person();
            person.setName(personElement.findElement(By.className("name")).getText());
            person.setSurname(personElement.findElement(By.className("surname")).getText());
            person.setJob(personElement.findElement(By.className("job")).getText());
            person.setDob(personElement.findElement(By.className("dob")).getText());
            person.setLanguages(personElement.findElement(By.className("language")).getText());
            person.setGender(personElement.findElement(By.className("gender")).getText());
            person.setStatus(personElement.findElement(By.className("status")).getText());
            personList.add(person);




        }

        return personList;


    }
    public void clickAddPerson() {
        addBtn.click();


    }
    public void clickEdit() {
        editBtn.click();

    }
}



