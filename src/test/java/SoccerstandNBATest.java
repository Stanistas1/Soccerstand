import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SoccerstandNBATest {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.soccerstand.com/pl/koszykowka/usa/nba/tabela/#/CpvDJmdj/table/overall");
        WebElement cookiePanel = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookiePanel.click();
    }

    @AfterClass
    public static void close() {
        driver.close();
    }


    @Test
    public void overallPointsTest() {
        WebElement tableBody = driver.findElement(By.id("tournament-table-tabs-and-content"));
        List<WebElement> tablesBody = tableBody.findElements(By.className("ui-table__body"));
        WebElement westTable = tablesBody.get(0);
        WebElement eastTable = tablesBody.get(1);
        List<WebElement> westRows = westTable.findElements(By.className("table__cell--totalPoints"));
        long pointsWestScored = 0;
        long pointsWestConceded = 0;
        for (WebElement row : westRows) {
            String[] points = row.getText().split(":");
            pointsWestScored = pointsWestScored + Integer.parseInt(points[0]);
            pointsWestConceded = pointsWestConceded + Integer.parseInt(points[1]);
        }
        List<WebElement> eastRows = eastTable.findElements(By.className("table__cell--totalPoints"));
        long pointsEastScored = 0;
        long pointsEastConceded = 0;
        for (WebElement row : eastRows) {
            String[] points = row.getText().split(":");
            pointsEastScored = pointsEastScored + Integer.parseInt(points[0]);
            pointsEastConceded = pointsEastConceded + Integer.parseInt(points[1]);
        }
        assertEquals(pointsEastScored + pointsWestScored, pointsEastConceded + pointsWestConceded);
    }

    @Test
    public void overallGamesTest() {
        List<WebElement> tablesBody = driver.findElements(By.className("ui-table__body"));
        WebElement westTable = tablesBody.get(0);
        WebElement eastTable = tablesBody.get(1);
        List<WebElement> rows = westTable.findElements(By.className("ui-table__row"));
        rows.addAll(eastTable.findElements(By.className("ui-table__row")));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("span"));
            assertEquals(5, columns.size());
            assertEquals(Integer.parseInt(columns.get(0).getText()), Integer.parseInt(columns.get(1).getText()) + Integer.parseInt(columns.get(2).getText()));
            assertEquals(30, rows.size());
        }
    }
}

