package hu.unideb.inf;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class StepDefinitions {
    static WebDriver driver;

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Given("The Home page is open")
    public void theHomePageIsOpen() { driver.get("http://www.way2automation.com/demo.html");}

    @Given("The Registration Form page is open")
    public void theRegistrationFormPageIsOpen() {
        driver.get("http://way2automation.com/way2auto_jquery/index.php");
    }

    @Given("The Registration button is pressed")
    public void theRegistrationButtonIsPressed() {
        driver.findElement(By.xpath("//*[contains(text(), 'Registration')]")).click();
    }

    @Given("The Name is filled with {string}")
    public void theNameIsFilledWithName(String name) {
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(name);
    }

    @And("The Phone is filled with {string}")
    public void thePhoneIsFilledWithPhone(String phone) { driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone); }

    @And("The Email is filled with {string}")
    public void theEmailIsFilledWithEmail(String email) { driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email); }

    @And("The selected Country is {string}")
    public void theSelectedCountryIsCountry(String country) {
        if (country.length()>0) {
            Select select = new Select(driver.findElement(By.xpath("//select[@name='country']")));
            select.selectByVisibleText(country);
        }
    }

    @And("The City is filled with {string}")
    public void theCityIsFilledWithCity(String city) { driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city); }

    @And("The Username is filled with {string}")
    public void theUsernameIsFilledWithUsername(String username) { driver.findElement(By.xpath("//div[@id='load_box']/form[@id='load_form']/fieldset/input[@name='username']")).sendKeys(username); }

    @And("The Password is filled with {string}")
    public void thePasswordIsFilledWithPassword(String password) {driver.findElement(By.xpath("//div[@id='load_box']/form[@id='load_form']/fieldset/input[@name='password']")).sendKeys(password); }

    @And("The Submit button is clicked")
    public void theSubmitButtonIsClicked() {
        driver.findElement(By.xpath("//div[@class='fancybox-inner']/div/form[@id='load_form']/div/div[2]")).click();
    }

    @Then("The {string} shows the {string}")
    public void theFieldShowsTheMsg(String field, String msg) {
        if(field.equals("username") || field.equals("password")){
            Assert.assertEquals(msg, driver.findElement(By.xpath("//div[@id='load_box']/form[@id='load_form']/fieldset/input[@name='"+field+"']")).getAttribute("validationMessage"));
        }else {
            Assert.assertEquals(msg, driver.findElement(By.name(field)).getAttribute("validationMessage"));
        }
    }

    @And("Sign in link is pressed")
    public void signInLinkIsPressed() {
        //driver.findElement(By.xpath("//*[contains(text(), 'Signin')]")).click();
        driver.findElement(By.linkText("Signin")).click();
    }

    @Given("Valid username is written into username field")
    public void validUsernameIsWrittenIntoUsernameField() {
        driver.findElement(By.xpath("//div[@id='login']/form[@id='load_form']/fieldset/input[@name='username']")).sendKeys("Jennasmoth987");
    }

    @And("Invalid password is written into password field")
    public void invalidPasswordIsWrittenIntoPasswordField() {
        driver.findElement(By.xpath("//div[@id='login']/form[@id='load_form']/fieldset/input[@name='password']")).sendKeys("xxxxxxx");
    }

    @And("Valid password is written into password field")
    public void validPasswordIsWrittenIntoPasswordField() {
        driver.findElement(By.xpath("//div[@id='login']/form[@id='load_form']/fieldset/input[@name='password']")).sendKeys("password1");
    }

    @Then("Incorrect Username or Password message appears")
    public void incorrectUsernameOrPasswordMessageAppears() {
        List<WebElement> elements =  driver.findElements(By.xpath("//*[contains(text(), 'Invalid username password.')]"));
        Assert.assertNotEquals(0, elements.size());
    }

    @Then("The Login window dissapears")
    public void theLoginWindowDissapears() {

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='fancybox-inner']/div[@id='login']")));
        List<WebElement> elements =  driver.findElements(By.xpath("//div[@class='fancybox-inner']/div[@id='login']"));
        Assert.assertEquals(0, elements.size());
    }

    @Given("The alert page is selected")
    public void theAlertPageIsSelected() {
        driver.findElement(By.linkText("Alert")).click();
    }

    @And("The button for showing the alert is pressed")
    public void theButtonForShowingTheAlertIsPressed() {
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//*[contains(text(), 'Click the button to display an alert box:')]")).click();
    }

    @Then("The alert appears")
    public void theAlertAppears() {
        Assert.assertEquals("I am an alert box!",driver.switchTo().alert().getText());
    }
}
