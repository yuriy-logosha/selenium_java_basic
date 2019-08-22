package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.PageFactory;
import selenium.pages.FormPage;
import selenium.pages.ListPage;

import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class Task3Bonus {
    WebDriver driver;
    static FormPage formPage;
    static ListPage listPage;

//ListPage listPage = PageFactory.initElements(driver, ListPage.class);
//     should contain what you see when you just open the page (the table with names/jobs)


//     should be what you see if you click "Add" or "Edit" (2 input field and a button (Add/Edit) and (Cancel)

//    Bonus:
//    try storing people via an Object/separate class

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/list_of_people");
        formPage = PageFactory.initElements(driver, FormPage.class);
        listPage = PageFactory.initElements(driver, ListPage.class);


    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void addPerson() throws InterruptedException {
        /* TODO:
         * implement adding new person using page object
         *
         * in order: store the list of people and jobs currently on page
         * add a person via "Add person button"
         * check the list again, that non of the people where changes, but an additional one with correct name/job was added
         */
        String name = "Mihails";
        String surname = "Frolenkovs";
        String job = "QA";
        List<Person> currentPersonsInitial = listPage.getPersons();
        listPage.clickAddPerson();

        formPage.enterName("Mihails");
        formPage.enterSurname("Frolenkovs");
        formPage.enterJob("QA");
        formPage.enterDob("08/20/2019");
        formPage.checkEnglish();
        formPage.selectMale();
        formPage.selectStatusIntern();
        formPage.clickAdd();
        List<Person> currentPersonsActual = listPage.getPersons();
        assertEquals(currentPersonsInitial.size() + 1, currentPersonsActual.size());

        int samePersons = 0;
        int notSamePersons = 0;

        for (Person p : currentPersonsActual) {
            boolean matchFound = false;
            for (Person c : currentPersonsInitial) {
                if (c.equals(p)) {
                    matchFound = true;
                    break;
                }
            }
            if (matchFound) {
                samePersons++;
            } else {
                notSamePersons++;
                if (notSamePersons > 1) {
                    fail("Only 1 persons information expected to be new");
                }
                assertEquals(name, p.getName());
                assertEquals(surname, p.getSurname());
                assertEquals(job, p.getJob());
          //      Thread.sleep(10000);
            }
        }

        assertEquals(currentPersonsInitial.size(), samePersons);
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
