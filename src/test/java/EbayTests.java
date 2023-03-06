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
        WebElement searchItem = driver.findElement(By.id("gh-ac"));
        searchItem.sendKeys("Kask Motocyklowy");
        WebElement wyszukaj = driver.findElement(By.id("gh-btn"));
        wyszukaj.click();
        WebElement filtresRow = driver.findElement(By.className("fake-tabs"));
        filtresRow.findElement(By.xpath("//*[text()='Kup teraz']")).click();
        List<WebElement> imageContainers = driver.findElements(By.className("s-item"));
        WebElement firstItem = imageContainers.get(3);
        firstItem.click();
    }

     /*   WebElement filtresTable = driver.findElement(By.id("s0-51-12-0-1-2-6"));
        WebElement kupTeraz = filtresTable.findElement(By.className("radio__unchecked"));
        kupTeraz.click(); */


}
