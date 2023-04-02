import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SauceTests {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterClass
    public static void close() {
        driver.close();
    }

    @Test
    public void testLogin_StandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement result = driver.findElement(By.className("title"));
        assertEquals("Products", result.getText());
    }

    @Test
    public void testLogin_LockedOutUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("locked_out_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement result = driver.findElement(By.xpath("//*[text()='Epic sadface: Sorry, this user has been locked out.']"));
        assertEquals("Epic sadface: Sorry, this user has been locked out.", result.getText());
    }

    @Test
    public void testLogin_ProblemUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("problem_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement result = driver.findElement(By.className("title"));
        assertEquals("Products", result.getText());
    }

    @Test
    public void testLogin_PerformanceGlitchUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("performance_glitch_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement result = driver.findElement(By.className("title"));
        assertEquals("Products", result.getText());
    }
}


// deklaracja a definicja zmiennej w javie