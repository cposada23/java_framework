package controllers;

import dto.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pageobjects.GoogleHomePage;
import pageobjects.GoogleSearchResultsPage;
import utils.CustomActions;
import utils.exceptions.ActionsException;

public class GoogleController {
    private static final Logger LOGGER = LogManager.getLogger(GoogleController.class);
    private GoogleHomePage googleHomePage;
    private GoogleSearchResultsPage googleSearchResultsPage;
    CustomActions actions;

    public GoogleController(CustomActions actions) {
        this.googleHomePage = new GoogleHomePage(actions);
        this.googleSearchResultsPage = new GoogleSearchResultsPage(actions);
        this.actions = actions;
    }

    public void navigateToHomePage(String url) {
        actions.getDriver().navigate().to(url);
    }


    public void searchFor(String searchString) {
        try {
            googleHomePage.isInputVisible();
            googleHomePage.searchFor(searchString);
        } catch (ActionsException e) {
            String msg = "Error while trying to search for " + searchString + " Exception raised: " + e.getMessage();
            LOGGER.error(msg);
            Assertions.fail(msg);
        }
    }

    public void waitForSearchResults() {
        Assertions.assertTrue(
            googleSearchResultsPage.getNumberOfResults() > 0,
            "the search didn't produce a search result"
        );
    }

    public void selectFirstResult() {
        try {
            googleSearchResultsPage.clickOnResult(1);
        } catch (Exception e ) {
            String msg = "Error selecting the first result " + e;
            LOGGER.info(msg);
            Assertions.fail(msg);
        }
    }

}
