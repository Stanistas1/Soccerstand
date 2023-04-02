import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class EbayTests {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.ebay.pl/");
        TimeUnit.SECONDS.sleep(5);
        WebElement cookiePanel = driver.findElement(By.id("gdpr-banner-accept"));
        cookiePanel.click();
    }

    @Test
    public void test() {
        assertTrue(true);
    }

    @Test
    public void addItemToCart() {
        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        searchBox.sendKeys("lego star wars millenium falcon");
        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        searchButton.click();
        WebElement filtersRow = driver.findElement(By.className("fake-tabs"));
        filtersRow.findElement(By.xpath("//*[text()='Kup teraz']")).click();
        List<WebElement> itemList = driver.findElements(By.className("s-item"));
        WebElement productImage = itemList.get(1).findElement(By.className("s-item__image"));
        productImage.click();
        WebElement mainContent = driver.findElement(By.id("mainContent"));
        WebElement form = mainContent.findElement(By.tagName("form"));
        System.out.println(form.getText());
        //  WebElement ovimBuyBoxWrapper = form.findElement(By.className("x-buybox__section"));

    }
}
