package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.AgeSamplePage;
import static org.junit.Assert.fail;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Task3Bonus {
    static WebDriver driver;
    ListPage listPage = PageFactory.initElements(driver, ListPage.class);
//     should contain what you see when you just open the page (the table with names/jobs)

    FormPage formPage = PageFactory.initElements(driver, FormPage.class);
//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)

//    Bonus:
//    try storing people via an Object/separate class


    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        formPage = PageFactory.initElements(driver, FormPage.class);
        listPage = PageFactory.initElements(driver, ListPage.class);
        driver.get("https://kristinek.github.io/site/tasks/list_of_people");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void addPerson() {
        /* TODO:
         * implement adding new person using page object +
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */

        //listPage.getPersonList();

        List<Person> currentPersons = listPage.getPersonList();

        listPage.printList();
        listPage.clickAddPerson1();
        formPage.enterName("Name");
        formPage.enterSurname("Surname");
        formPage.enterJob("JobTitle");
        formPage.enterDob("08/21/2019");
        formPage.selectFemaleRadioButton();
        formPage.clickEnglishCheckBox();
        formPage.clickFrenchCheckBox();
        formPage.clickSpanishCheckBox();
        formPage.selectStatusContractor();
        formPage.clickAddButton();

        List<Person> newPersons = listPage.getPersonList();
        assertEquals(currentPersons.size()+1, newPersons.size());

        int samePersons = 0;
        int notSamePersons = 0;

        for(Person p : newPersons) {
            boolean matchFound = false;
            for(Person c : currentPersons) {
                if(c.equals(p)) {
                    matchFound = true;
                    break;
                }
            }
            if(matchFound) {
                samePersons++;
            } else {
                notSamePersons++;
                if(notSamePersons > 1) {
                    fail("Only 1 persons information expected to be new");
                }
              //  assertEquals("Name", p.getName());
             //   assertEquals("Surname", p.getSurname());
            //    assertEquals("JobTitle", p.getJob());
            }
        }

        assertEquals(currentPersons.size(), samePersons);

    }

    @Test
    public void editPerson() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link
         * check the list again and that 2 people stayed the same and the one used was changed
         */
    }

    @Test
    public void editPersonCancel() {
        /* TODO:
         * implement editing a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * edit one of existing persons via the edit link then click "Cancel" on edit form instead of "Edit"
         * check the list again and that no changes where made
         */
    }


    @Test
    public void deletePerson() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete 1 person see that there are now 2 people in the table with correct data
         */
    }


    @Test
    public void deletePersonAll() {
        /* TODO:
         * implement deleting a person using page object
         *
         * in order: store the list of people and jobs currently on page
         * delete all people and check that there is no no table on page, but the button Add is still present and working
         */
    }


}
