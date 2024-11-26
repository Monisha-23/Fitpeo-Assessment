package org.example.stepDefinitions;


import io.cucumber.java.en.*;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Main {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        System.out.println("Browser opened and ready for tests.");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        }
    }

    @Given("User clicks on the revenue calculator tab")
    public void clickOnTab(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Revenue Calculator')]")));
        element.click();
    }

    @When("User navigates to {string}")
    public void navigateTo(String url) throws InterruptedException {
        driver.get(url);
    }

    @Then("User verifies the page title as {string}")
    public void verifyTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals("Page title does not match", expectedTitle, actualTitle);
    }

    @Then("User verifies the url of the page as {string}")
    public void verifyURL(String expectedURL) {
        String actualURL = driver.getCurrentUrl();
        assertEquals("Page URL does not match", expectedURL, actualURL);
    }

    @Given("User scrolls down to the {string}")
    public void scrollDown(String section){
        WebElement element;
        switch (section.toLowerCase()) {
            case "slider":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Medicare Eligible Patients')]")));
                break;
            case "ctp":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'MuiBox-root css-79elbk')]")));
                break;
            default:
                throw new IllegalArgumentException("Invalid section: " + section);
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @And("User sets the slider value to {int}")
    public void setSliderValue(int targetValue) throws InterruptedException {
        WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".MuiSlider-root")));
        WebElement sliderThumb = driver.findElement(By.xpath("//span[contains(@class,\"MuiSlider-thumb\")]//input"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        int sliderMin = Integer.parseInt(js.executeScript("return arguments[0].getAttribute('min') || 0;", slider).toString());
        int sliderMax = Integer.parseInt(js.executeScript("return arguments[0].getAttribute('max') || 2000;", slider).toString());
        int currentValue = Integer.parseInt(js.executeScript("return arguments[0].getAttribute('value') || 200;", sliderThumb).toString());

        if (targetValue < sliderMin || targetValue > sliderMax) {
            throw new IllegalArgumentException("Target value is out of range.");
        }

        int sliderWidth = Integer.parseInt(js.executeScript("return arguments[0].getBoundingClientRect().width;", slider).toString());// Get the total width of the slider
        int zeroOffset = (int) ((sliderMin - currentValue) * (sliderWidth / (double) (sliderMax - sliderMin))); // Offset to reach 0
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(sliderThumb, zeroOffset, 0).perform();


        int targetOffset = (int) ((targetValue - sliderMin) * (sliderWidth / (double) (sliderMax - sliderMin)));
        actions.dragAndDropBy(sliderThumb, targetOffset, 0).perform();
        int presentValue = Integer.parseInt(sliderThumb.getAttribute("aria-valuenow"));
        System.out.println(presentValue);

        if (presentValue != targetValue) {
            Keys direction = (presentValue < targetValue) ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;
            while (presentValue != targetValue) {
                sliderThumb.sendKeys(direction);
                Thread.sleep(100);
                presentValue = Integer.parseInt(sliderThumb.getAttribute("aria-valuenow")); // Fetch the updated value
                System.out.println("Current slider value: " + presentValue);
            }
        }

        assertEquals("Slider value mismatch", targetValue, presentValue);

    }

    @Then("User validates the text field with value as {int}")
    public void validTextField(int value){
        WebElement textField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class,'MuiInputBase-input')]")));
        assertEquals("Text field value mismatch", String.valueOf(value), textField.getAttribute("value"));
    }



    @When("User enters {int} in the text field")
    public void updateTextfield(int value) throws InterruptedException {
        WebElement textField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class,'MuiInputBase-input')]")));
        textField.clear();
        textField.sendKeys(Keys.BACK_SPACE);
        textField.sendKeys(Keys.BACK_SPACE);
        textField.sendKeys(Keys.BACK_SPACE);
        textField.sendKeys(String.valueOf(value));

    }

    @Then("User validates the slider value as {int}")
    public void validateSliderValue(int value){
        WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'MuiSlider-thumb')]//input")));
        int sliderValue = Integer.parseInt(slider.getAttribute("aria-valuenow"));
        assertEquals("Slider value mismatch", value, sliderValue);
    }

    @And("User checks the checkbox for {string}")
    public void selectCheckBox(String ctpCode) throws InterruptedException {
        WebElement checkbox = driver.findElement(By.xpath("//p[text()='" + ctpCode + "']/following-sibling::label//input[@type='checkbox']"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @Then("User validates the reimbursement as {string}")
    public void validateRecurring(String price){
        WebElement reimbursement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Recurring Reimbursement')]/following-sibling::p")));
        assertEquals("Reimbursement mismatch", price, reimbursement.getText());

    }








}
