package step_definitions;

import command_providers.ActOn;
import command_providers.AssertThat;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.ReadConfigFiles;

public class LoginSteps {

    private static Logger LOGGER = LogManager.getLogger(LoginSteps.class);

    WebDriver driver;

    private static final By Username = By.id("username");
    private static final By Password = By.id("password");
    private static final By Submit   = By.id("submit");
    private static final By Logout   = By.xpath("//a[contains(text(),'Log out')]");
    private static final By InvalidUsernameError = By.xpath("//div[@id='error' and contains(text(),'Your username is invalid!')]");
    private static final By InvalidPasswordError = By.xpath("//div[@id='error' and contains(text(),'Your password is invalid!')]");



    @Given("User is on the login page")
    public void navigateToLoginPage(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("TestAppUrl"));
        LOGGER.info("User is on the login page");
    }

    @When("^User enters username \"(.+?)\" and password \"(.+?)\"$")
    public void enterUserCredentials(String username, String password){
        ActOn.element(driver, Username).setValue(username);
        ActOn.element(driver, Password).setValue(password);
        LOGGER.info("User enters username and password");
    }

    @And("User clicks the login button")
    public void clickOnLogin(){
        ActOn.element(driver, Submit).click();
        LOGGER.info("User clicks the login button");
    }

    @Then("User is navigated to home page")
    public void validateSuccess() throws InterruptedException {
        Thread.sleep(3000);
        AssertThat.elementAssertions(driver, Logout).elementIsDisplayed();
        LOGGER.info("User is navigated to home page");
        ActOn.browser(driver).closeBrowser();
    }

    @Then("User is failed to login for invalid username")
    public void validateLoginFailureForInvalidUsername() throws InterruptedException {
        Thread.sleep(3000);
        AssertThat.elementAssertions(driver, InvalidUsernameError).elementIsDisplayed();
        LOGGER.info("Login failed for invalid username");
        ActOn.browser(driver).closeBrowser();
    }

    @Then("User is failed to login for invalid password")
    public void validateLoginFailureForInvalidPassword() throws InterruptedException {
        Thread.sleep(3000);
        AssertThat.elementAssertions(driver, InvalidPasswordError).elementIsDisplayed();
        LOGGER.info("Login failed for invalid password");
        ActOn.browser(driver).closeBrowser();
    }







}
