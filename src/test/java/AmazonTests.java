import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class AmazonTests {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.pl/");
        WebElement cookiePanel = driver.findElement(By.id("sp-cc-accept"));
        cookiePanel.click();
    }

    private static int getNumberOfItemsInCart() {
        WebElement navCartTextContainer = driver.findElement(By.id("nav-cart-text-container"));
        navCartTextContainer.click();
        WebElement subTotal = driver.findElement(By.className("sc-number-of-items"));
        Matcher matcher = Pattern.compile("\\d+").matcher(subTotal.getText());
        return Integer.parseInt(matcher.group());
    }

    @Test
    public void test() {
        addItemToCart("Lego City", 7);
        addItemToCart("Lego Architecture", 23);

        assertEquals(2, getNumberOfItemsInCart());
    }

    private void addItemToCart(String productName, int index) {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(productName);
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();
        List<WebElement> imageContainers = driver.findElements(By.className("s-product-image-container"));
        imageContainers.get(index).click();
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();
    }

    @Test
    public void testChangeQuantityOfProduct() {
        addItemToCart("Lego City", 7);
        addItemToCart("Lego Architecture", 23);
        WebElement cart = driver.findElement(By.className("nav-cart-count"));
        cart.click();
        WebElement quantityFirstProduct = driver.findElement(By.id("a-autoid-0-announce"));
        quantityFirstProduct.click();
        WebElement quantity_4 = driver.findElement(By.id("quantity_4"));
        quantity_4.click();

        assertEquals(5, getNumberOfItemsInCart());
    }
}
