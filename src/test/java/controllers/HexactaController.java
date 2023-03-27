package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import pageobjects.HexactaPage;
import utils.CustomActions;

import java.util.List;
import java.util.Locale;

public class HexactaController {


    private static final Logger LOGGER = LogManager.getLogger(HexactaController.class);

    private HexactaPage hexactaPage;
    private CustomActions actions;

    public HexactaController(CustomActions actions) {
        this.actions = actions;
        hexactaPage = new HexactaPage(actions);
    }


    public void navigateToHomePage(String url) {
        actions.getDriver().navigate().to(url);
    }

    public void doSearch(String searchString) {

        try {
            hexactaPage.clickOnSearchButton();
            hexactaPage.searchFor(searchString);
            hexactaPage.waitForSearchResults();
            // Resultados sean mayores 1
        } catch (Exception e) {
            LOGGER.error("Error while doing the search", e);
            Assertions.fail();
        }

    }

    public void validateSearchResults(String searchString) {
        try {
            List<WebElement> searchResults = hexactaPage.getResults();
            boolean found = false;
            for (int i = 0; i < searchResults.size(); i++) {
                String title = searchResults.get(i).getText();
                if (title.contains(searchString.toUpperCase(Locale.ROOT))) {
                    found = true;
                    i = searchResults.size() + 2;
                }
            }

            Assertions.assertTrue(
                    found,
                    "No results found for the given search String"
            );

        } catch (Exception e) {
            LOGGER.error("Error while doing the search", e);
            Assertions.fail();
        }
    }

}
