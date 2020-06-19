package Tests.CodingTask;

import Pages.CodingTask.CustomerPage;
import Tests.FunctionalTest;
import org.junit.Assert;
import org.junit.Test;

public class CodingTask_TC_1 extends FunctionalTest {

    /**
     * findCustomerByAllFields checking filter by all available fields
     * check if filter works for appropriate fields and results
     */

    @Test
    public void findCustomerByAllFields() {
        FunctionalTest.driver.get(baseURL);
        CustomerPage cusPage = new CustomerPage(driver);
        /* Precondition - make sure "Match case is set to false */
        cusPage.matchCase(false);

        /* Try to find customer by partial text and City */
        cusPage.inputKeys("fast");
        cusPage.selectFromDropDown("City");
        /* Check the results */
        Assert.assertEquals(cusPage.checkTableSize(),1);
        cusPage.containTableResume("1 of 3");
        Assert.assertEquals(cusPage.getTableValue("City",1),"Belfast");
        /* Clear results */
        cusPage.clearResults();

        /* Try to find customer by Email */
        cusPage.inputKeys("conatact@postimex.pl");
        cusPage.selectFromDropDown("Email");
        /* Check the results */
        Assert.assertEquals(cusPage.checkTableSize(),1);
        cusPage.containTableResume("1 of 3");
        Assert.assertEquals(cusPage.getTableValue("Email",1),"conatact@postimex.pl");
        /* Clear results */
        cusPage.clearResults();

        /* Try to find customer by partial text Name */
        cusPage.inputKeys("o");
        cusPage.selectFromDropDown("Name");
        /* Check the results */
        Assert.assertEquals(cusPage.checkTableSize(),2);
        cusPage.containTableResume("2 of 3");
        cusPage.containSearchSlogan("by term \"o\" in Name");
        Assert.assertEquals(cusPage.getTableValue("Name",2),"Bondir");
        Assert.assertEquals(cusPage.getTableValue("Name",1),"Postimex");
        /* Clear results */
        cusPage.clearResults();

        /* Try to find customer by Id */
        cusPage.inputKeys("2");
        cusPage.selectFromDropDown("Id");
        /* Check the results */
        Assert.assertEquals(cusPage.checkTableSize(),1);
        cusPage.containTableResume("1 of 3");
        cusPage.containSearchSlogan("by term \"2\" in Id");
        Assert.assertEquals(cusPage.getTableValue("Id",1),"2");
        /* Clear results */
        cusPage.clearResults();

        /* Try to find customer by Id which not exist */
        cusPage.inputKeys("7");
        cusPage.selectFromDropDown("Id");
        /* Check the results */
        Assert.assertEquals(cusPage.checkTableSize(),0);
        cusPage.containTableResume("0 of 3");
        /* Clear results */
        cusPage.clearResults();
    }

}
