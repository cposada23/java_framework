package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CustomActions;
import utils.CustomWaits;
import utils.exceptions.ActionsException;

import java.util.List;

public class GoogleSearchResultsPage {

    private CustomActions actions;

    @FindAll(
            @FindBy(xpath = "//*[contains(@class, 'yuRUbf')]")
    )
    private List<WebElement> searchResults;


    public GoogleSearchResultsPage(CustomActions actions) {
        this.actions = actions;
        PageFactory.initElements(actions.getDriver(), this);
    }

    public int getNumberOfResults() {
        CustomWaits.waitForNumberOfElementsToBeGreaterThanOne(searchResults, 10);
        return searchResults.size();
    }


    public void clickOnResult(int resultNumber) throws ActionsException {
        try {
            WebElement result = searchResults.get(resultNumber - 1).findElement(By.xpath("./a"));
            actions.click(result, 10);
        } catch (Exception e) {
            throw new ActionsException("Error trying to click on result # " + (resultNumber - 1), e);
        }

    }

}
