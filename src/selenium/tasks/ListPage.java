package selenium.tasks;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
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
    List<WebElement> PersonList;


    public List<Person> getPersonList() {
        ArrayList<Person> Personlist = new ArrayList<>();
        for (WebElement PersonElement : PersonList) {
            Person person = new Person();
            person.setName(PersonElement.findElement(By.className("name")).getText());
            person.setSurname(PersonElement.findElement(By.className("surname")).getText());
            person.setJob(PersonElement.findElement(By.className("job")).getText());
            person.setDob(PersonElement.findElement(By.className("dob")).getText());
            person.setLanguage(PersonElement.findElement(By.className("language")).getText());
            person.setGender(PersonElement.findElement(By.className("gender")).getText());
            person.setGender(PersonElement.findElement(By.className("status")).getText());
            Personlist.add(person);
        }
        return Personlist;
    }

    public  void printList(){
        for(int i=0;i<PersonList.size();i++){
            System.out.println(PersonList.get(i).getText());
        }
    }

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
