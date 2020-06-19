package Pages.CodingTask;

import Pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class CustomerPage extends PageObject {


    @FindBy(id = "search-input")
    private WebElement searchInput;

    @FindBy(id = "search-column")
    private WebElement searchColumn;

    @FindBy(id = "match-case")
    private WebElement matchCase;

    @FindBy(xpath = "//table[@class='table-hover']")
    private WebElement tableHover;

    @FindBy(id = "clear-button")
    private WebElement clearButton;

    @FindBy(id = "table-resume")
    private WebElement tableResume;

    @FindBy(id = "search-slogan")
    private WebElement searchSlogan;

    @FindBy(xpath = "//table[@class='table table-hover']")
    private WebElement table;

    /*
    * @param driver - init Webdriver
    */
    public CustomerPage(WebDriver driver){
        super(driver);
        assertTrue(searchInput.isDisplayed());
    }

    /*
     * @param word - any text you can put into edit field
     */
    public void inputKeys(String word){
        try {
            searchInput.sendKeys(word);
        }
        catch(NoSuchElementException e){
            throw new Error("Element with id='search-input' is not visible");
        }
    }

    /*
     * Function clear edit field but not by click 'Clear' button
     */
    public void clearInput(){
        searchInput.clear();
    }

    /*
     * @param column - provide column
     */
    public void selectFromDropDown(String column){
        try {
            Select drpColumn = new Select(searchColumn);
            drpColumn.selectByVisibleText(column);
        }
        catch(NoSuchElementException e)
        {
            throw new Error("There is no such column element: "+column);
        }
    }

    /*
     * @return - Return cow count
     *           (result - 1 because header of table is also count)
     */
    public int checkTableSize(){
        List<WebElement> row = table.findElements(By.tagName("tr"));
        return row.size()-1;
    }

    /*
     * @param col - provide column name like "Name", "City", etc.
     * @param row - provide row which want to check
     * @return - based on col and row params return related value from table cell
     */
    public String getTableValue(String col, int row){
        List<WebElement> rowList = table.findElements(By.tagName("th"));
        String colName;

        // Loop for check column name index - column index = i;
        int i=1;
        for(WebElement rCell:rowList) {
            colName = rCell.getText();
            if(colName.equals(col)) {
                break;}
            i++;
        }
        try {
            WebElement value = driver.findElement(By.xpath("//table[@class='table table-hover']/tbody/tr[" + row + "]/td[" + i + "]"));
            return value.getText();
        }
        catch (NoSuchElementException e){
            throw new Error("Provided arguments: "+col+" and "+row+" are not matching to any value in table: row: "+row+" col "+i);
        }
    }

    /*
     * @param mCase contain only 'true' or 'false' value;
     */
    public void matchCase(boolean mCase){
        try{
            if(mCase!=matchCase.isSelected())
            {
                matchCase.click();
            }
        }
        catch(ElementNotVisibleException e){
            throw new Error("Checkbox with id = 'match-case' not visible");
        }

    }

    /*
     * Function for hit clear result button
     */
    public void clearResults(){
        try {
            clearButton.click();
        }
        catch (ElementNotVisibleException e) {
            throw new Error("Clear button is not visible");
        }
    }

    /*
     * @param value - contain true or false to check if clear button is displayed
     */
    public void isClearbuttonDisplayed(boolean value){
        Assert.assertEquals(value, clearButton.isDisplayed());
    }

    /*
     * @param text contain any text to check
     * if displayed resume info match with current results;
     */
    public void containTableResume(String text){
        Assert.assertTrue(tableResume.getText().contains(text));
    }

    /*
     * @param text contain any text to check
     * if displayed slogan match with current results;
     */
    public void containSearchSlogan(String text){
        Assert.assertTrue(searchSlogan.getText().contains(text));
    }

}
