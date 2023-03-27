package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CustomActions;
import utils.CustomWaits;
import utils.exceptions.ActionsException;

import java.util.List;

public class HexactaPage {

    CustomActions actions;

    @FindBy(xpath = "//a[@class='searchbox']//span[contains(text(), 'Search')]")
    private WebElement searchButton;

    @FindBy(id = "search-field")
    private WebElement searchInput;

    @FindAll(
            @FindBy(xpath = "//article[@class='display-flex']//h3//a")
    )
    List<WebElement> resultsTitles;

    public HexactaPage(CustomActions actions) {
        PageFactory.initElements(actions.getDriver(), this);
        this.actions = actions;
    }

    public void clickOnSearchButton() throws ActionsException {
        actions.click(searchButton);
    }

    public void searchFor(String searchString) throws ActionsException {
        actions.sendText(searchInput, searchString);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void waitForSearchResults()  {
        CustomWaits.waitForNumberOfElementsToBeGreaterThanOne(resultsTitles, 10);
    }

    public List<WebElement> getResults() {
        return resultsTitles;
    }

}
