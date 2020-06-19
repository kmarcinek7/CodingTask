package Tests.CodingTask;

import Pages.CodingTask.CustomerPage;
import Tests.FunctionalTest;
import org.junit.Assert;
import org.junit.Test;

public class CodingTask_TC_2 extends FunctionalTest {

    /**
     * checkMatchCase checking filter behaviour when checkbox is ON and OFF
     * check if match case works for appropriate fields and results
     */

    @Test
    public void checkMatchCase() {
        FunctionalTest.driver.get(baseURL);
        CustomerPage cusPage = new CustomerPage(driver);
        /* Precondition - make sure "Match case is set to false */
        cusPage.matchCase(false);

        /* Try to find customer by partial text and City */
        cusPage.inputKeys("FAST");
        cusPage.selectFromDropDown("City");
        /* Check the results */
        Assert.assertEquals(cusPage.checkTableSize(),1);
        /* Match case ON */
        cusPage.matchCase(true);
        /* Check the results */
        cusPage.containTableResume("0 of 3");
        cusPage.containSearchSlogan("filtered by term \"FAST\" in City column with match case");
        Assert.assertEquals(cusPage.checkTableSize(),0);
        /* Clear results */
        cusPage.clearResults();

        /* Try to find customer by partial text Name */
        cusPage.inputKeys("B");
        cusPage.selectFromDropDown("Name");
        /* Check the results */
        Assert.assertEquals(cusPage.checkTableSize(),1);
        cusPage.containTableResume("1 of 3");
        cusPage.containSearchSlogan("by term \"B\" in Name");
        cusPage.containSearchSlogan("filtered by term \"B\" in Name column with match case");
        Assert.assertEquals(cusPage.getTableValue("Name",1),"Bondir");
        /* Match case ON */
        cusPage.matchCase(false);
        /* Check the results */
        Assert.assertEquals(cusPage.checkTableSize(),2);
        cusPage.containTableResume("2 of 3");
        cusPage.containSearchSlogan("by term \"B\" in Name");
        cusPage.containSearchSlogan("filtered by term \"B\" in Name column without match case");
        Assert.assertEquals(cusPage.getTableValue("Name",1),"Alabaster");
        /* Clear results */
        cusPage.clearResults();
    }

}
