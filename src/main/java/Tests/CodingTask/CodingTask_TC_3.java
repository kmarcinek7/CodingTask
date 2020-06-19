package Tests.CodingTask;

import Pages.CodingTask.CustomerPage;
import Tests.FunctionalTest;
import org.junit.Test;

public class CodingTask_TC_3 extends FunctionalTest {

    /**
     * checkClearButtonDisplay test case checking of displaying clear button
     * check if using button clears filter results
     */

    @Test
    public void checkClearButtonDisplay() {
        FunctionalTest.driver.get(baseURL);
        CustomerPage cusPage = new CustomerPage(driver);
        /* Precondition - make sure "Match case is set to true */
        cusPage.matchCase(true);

        /* Try to find customer by partial text and City */
        cusPage.inputKeys("FAST");
        cusPage.selectFromDropDown("City");
        /* Check if button is enable */
        cusPage.isClearbuttonDisplayed(true);
        cusPage.clearResults();
        /* Check if button is enable */
        cusPage.isClearbuttonDisplayed(false);

        /* Try to find customer by Email */
        cusPage.inputKeys("info@bond.ir");
        cusPage.selectFromDropDown("Email");
        /* Check if button is enable */
        cusPage.isClearbuttonDisplayed(true);
        /* Clear input */
        cusPage.clearInput();
        /* Check if button is enable */
        cusPage.isClearbuttonDisplayed(false);

        /* Try to find customer by Id */
        cusPage.inputKeys("3");
        cusPage.selectFromDropDown("Id");
        /* Check if button is enable */
        cusPage.isClearbuttonDisplayed(true);
        /* Clear input */
        cusPage.clearInput();
        cusPage.inputKeys("1");
        /* Check if button is enable */
        cusPage.isClearbuttonDisplayed(true);
        /* Clear input */
        cusPage.clearInput();
        /* Check if button is enable */
        cusPage.isClearbuttonDisplayed(false);
    }

}
