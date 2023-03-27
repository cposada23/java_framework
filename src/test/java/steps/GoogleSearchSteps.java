package steps;

import controllers.GoogleController;
import dto.Product;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import utils.CustomActions;
import utils.WebDriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GoogleSearchSteps {

    private static final Logger LOGGER = LogManager.getLogger(GoogleSearchSteps.class);
    private CustomActions actions;
    private GoogleController googleController;
    private Product selectedProduct;
//
//    @Before()
//    public void setUp() {
//        // Browser setup, property read from the test.properties file
//
//        try (
//                InputStream input = new FileInputStream("src/test/resources/config/test.properties")
//        ) {
//            Properties properties = new Properties();
//            properties.load(input);
//
//            String browser = properties.getProperty("browser");
//            boolean headless = "true".equals(properties.getProperty("headless"));
//
//            LOGGER.info("Executing test in Browser: " + browser);
//            LOGGER.info("Headless Mode: " + headless);
//
//            WebDriver driver = WebDriverFactory.getWebDriver(browser, headless);
//            actions = new CustomActions(driver);
//        } catch (IOException ex) {
//            Assertions.fail("could not load the configuration file"+ ex.getMessage());
//        }
//
//        googleController = new GoogleController(actions);
//    }
//
//    @After()
//    public void tearDown() {
//        if(actions.getDriver() != null) {
//            actions.getDriver().close();
//            actions.getDriver().quit();
//        }
//    }
//
//
//    @Given("the user navigates to {string}")
//    public void theUserNavigatesTo(String url){
//        googleController.navigateToHomePage(url);
//    }
//
//    @When("the user searches for {string}")
//    public void theUserSearchesFor(String searchString) {
//        googleController.searchFor(searchString);
//        googleController.waitForSearchResults();
//    }
//
//    @Then("the user navigates to the firs result")
//    public void theUserNavigatesToTheFirsResult() throws InterruptedException {
//        googleController.selectFirstResult();
//        Thread.sleep(10000);
//    }
}
