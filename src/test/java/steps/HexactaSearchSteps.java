package steps;

import com.google.gson.Gson;
import controllers.GoogleController;
import controllers.HexactaController;
import dto.Post;
import dto.Product;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import net.jodah.failsafe.internal.util.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import utils.CustomActions;
import utils.WebDriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HexactaSearchSteps {

    private static final Logger LOGGER = LogManager.getLogger(GoogleSearchSteps.class);
    private CustomActions actions;
    private HexactaController hexactaController;
    private Post post;

    @Before("@web")
    public void setUp(Scenario scenario) {
        // Browser setup, property read from the test.properties file

        try (
                InputStream input = new FileInputStream("src/test/resources/config/test.properties")
        ) {
            Properties properties = new Properties();
            properties.load(input);

            String browser = properties.getProperty("browser");
            boolean headless = "true".equals(properties.getProperty("headless"));

            LOGGER.info(scenario.getName());
            LOGGER.info("Executing test in Browser: " + browser);
            LOGGER.info("Headless Mode: " + headless);

            WebDriver driver = WebDriverFactory.getWebDriver(browser, headless);
            actions = new CustomActions(driver);
            hexactaController = new HexactaController(actions);
        } catch (IOException ex) {
            Assertions.fail("could not load the configuration file"+ ex.getMessage());
        }
    }

    @After("@web")
    public void tearDown() {
        if(actions.getDriver() != null) {
            actions.getDriver().close();
            actions.getDriver().quit();
        }
    }

    @Given("the user navigates to {string}")
    public void theUserNavigatesTo(String url) {
        hexactaController.navigateToHomePage(url);
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String searchString) {
        hexactaController.doSearch(searchString);
    }

    @Then("the user validates that at least one of the results contains the text {string}")
    public void theUserValidatesThatAtLeastOneOfTheResultsContainsTheText(String searchString) {
        hexactaController.validateSearchResults(searchString);
    }

    @Given("The user consumes the web services to get the post with id {string}")
    public void theUserConsumesTheWebServicesToGetThePostWithId(String id) {
        String url = "https://jsonplaceholder.typicode.com/posts/"+ id;
        //ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        String response = target.request()
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class);


        Gson gson = new Gson();
        post = gson.fromJson(response, Post.class);


    }

    @Then("the user validate that the response contains the correct title of the post")
    public void theUserValidateTheResponseContainsTheCorrectTitleOfThePost() {
        Assertions.assertEquals(post.getUserId(), 1);
    }
}
