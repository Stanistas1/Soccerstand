import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testAddToCartStandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCart.click();

        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_badge"));
        assertEquals("1", shoppingCartBadge.getText());
    }

    @Test
    public void testRemoveProductStandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCart.click();
        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_link"));
        shoppingCartBadge.click();
        WebElement remove = driver.findElement(By.id("remove-sauce-labs-backpack"));
        remove.click();

        WebElement removedCartItem = driver.findElement(By.className("removed_cart_item"));
        assertEquals("", removedCartItem.getText());
    }

    @Test
    public void testAddItem_FromItemDetailsStandardUSer() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement itemFive = driver.findElement(By.id("item_5_title_link"));
        WebElement itemFiveAdd = itemFive.findElement(By.className("inventory_item_name"));
        itemFiveAdd.click();
        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        addToCart.click();

        WebElement remove = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
        assertEquals("Remove", remove.getText());
    }

    @Test
    public void testBackToProductsStandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement itemFive = driver.findElement(By.id("item_5_title_link"));
        WebElement itemFiveAdd = itemFive.findElement(By.className("inventory_item_name"));
        itemFiveAdd.click();
        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        addToCart.click();
        WebElement backToProduct = driver.findElement(By.id("back-to-products"));
        backToProduct.click();

        WebElement result = driver.findElement(By.className("title"));
        assertEquals("Products", result.getText());
    }

    @Test
    public void testPriceStandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCart.click();
        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_link"));
        shoppingCartBadge.click();
        WebElement checkout = driver.findElement(By.id("checkout"));
        checkout.click();
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Cfels");
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("London");
        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("0-700");
        WebElement continueOrder = driver.findElement(By.id("continue"));
        continueOrder.click();

        WebElement totalPrice = driver.findElement(By.className("summary_total_label"));
        assertEquals("Total: $32.39", totalPrice.getText());
    }

    @Test
    public void testFinishOrderStandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement addToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCart.click();
        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_link"));
        shoppingCartBadge.click();
        WebElement checkout = driver.findElement(By.id("checkout"));
        checkout.click();
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Cfels");
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("London");
        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("0-700");
        WebElement continueOrder = driver.findElement(By.id("continue"));
        continueOrder.click();
        WebElement finish = driver.findElement(By.id("finish"));
        finish.click();

        WebElement completeHeader = driver.findElement(By.className("complete-header"));
        assertEquals("Thank you for your order!", completeHeader.getText());
    }

    @Test
    public void testProductSortContainerAtoZStandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement selectContainer = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(selectContainer);
        select.selectByValue("az");
        WebElement inventoryList = driver.findElement(By.className("inventory_list"));
        List<WebElement> items = inventoryList.findElements(By.className("inventory_item"));
        for (int i = 0; i < 5; i++) {
            WebElement inventoryItemName = items.get(i).findElement(By.className("inventory_item_name"));
            WebElement nextInventoryItemName = items.get(i + 1).findElement(By.className("inventory_item_name"));
            assertTrue(inventoryItemName.getText().compareToIgnoreCase(nextInventoryItemName.getText()) < 0);
        }
    }

    @Test
    public void testProductSortContainerZToAStandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement selectContainer = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(selectContainer);
        select.selectByValue("za");
        WebElement inventoryList = driver.findElement(By.className("inventory_list"));
        List<WebElement> items = inventoryList.findElements(By.className("inventory_item"));
        for (int i = 0; i < 5; i++) {
            WebElement inventoryItemName = items.get(i).findElement(By.className("inventory_item_name"));
            WebElement nextInventoryItemName = items.get(i + 1).findElement(By.className("inventory_item_name"));
            assertTrue(inventoryItemName.getText().compareToIgnoreCase(nextInventoryItemName.getText()) > 0);
        }
    }

    @Test
    public void testAllProductStandardUser() {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement addToCartSauceLabsBackpack = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartSauceLabsBackpack.click();
        WebElement addToCartSauceLabsBikeLight = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        addToCartSauceLabsBikeLight.click();
        WebElement addToCartSauceLabsBotlTShirt = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
        addToCartSauceLabsBotlTShirt.click();
        WebElement addToCartSauceLabsFleeceJacket = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
        addToCartSauceLabsFleeceJacket.click();
        WebElement addToCartSauceLabsOnesie = driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
        addToCartSauceLabsOnesie.click();
        WebElement addToCartTestAllTheThingsTshirt = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
        addToCartTestAllTheThingsTshirt.click();
        WebElement shoppingCartBadge = driver.findElement(By.className("shopping_cart_link"));
        shoppingCartBadge.click();

        WebElement cartList = driver.findElement(By.className("cart_list"));
        List<WebElement> cartItems = cartList.findElements(By.className("cart_item"));
        assertEquals(6, cartItems.size());
    }
}