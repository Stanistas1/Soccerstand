import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SocccerstandTest {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.soccerstand.com/pl/pilka-nozna/polska/pko-bp-ekstraklasa/tabela/#/4fofM1vn/table/overall");
        WebElement cookiePanel = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookiePanel.click();
    }

    @AfterClass
    public static void close() {
        driver.close();
    }

    @Before
    public void restart() {
        driver.get("https://www.soccerstand.com/pl/pilka-nozna/polska/pko-bp-ekstraklasa/tabela/#/4fofM1vn/table/overall");
        //       WebElement overallButton = driver.findElement(By.xpath("//*[text()='Ogółem']"));
        //      overallButton.click();
    }

    @Test
    public void numberOfTeamsTest() {
        WebElement tableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> rows = tableBody.findElements(By.className("ui-table__row"));

        assertEquals(18, rows.size());
    }

    @Test
    public void numberOfOverallGamesTest() {
        numberOfGamesTest();
    }

    @Test
    public void numberOfHomeGamesTest() {
        WebElement homeButton = driver.findElement(By.xpath("//*[text()='U siebie']"));
        homeButton.click();
        numberOfGamesTest();
    }

    @Test
    public void numberOfAwayGamesTest() {
        WebElement awayButton = driver.findElement(By.xpath("//*[text()='Na wyjeździe']"));
        awayButton.click();
        numberOfGamesTest();
    }

    private static void numberOfGamesTest() {
        WebElement homeTableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> rows = homeTableBody.findElements(By.className("ui-table__row"));

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("span"));
            assertEquals(6, columns.size());
            assertEquals(Integer.parseInt(columns.get(0).getText()), Integer.parseInt(columns.get(1).getText()) + Integer.parseInt(columns.get(2).getText()) + Integer.parseInt(columns.get(3).getText()));
        }
    }

    @Test
    public void promotionTest() {
        WebElement tableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> rows = tableBody.findElements(By.className("ui-table__row"));
        WebElement firstRow = rows.get(0);
        WebElement firstRowRank = firstRow.findElement(By.className("table__cell--rank"));
        WebElement firstRowCellRank = firstRowRank.findElement(By.className("tableCellRank"));
        WebElement secondRow = rows.get(1);
        WebElement secondRowRank = secondRow.findElement(By.className("table__cell--rank"));
        WebElement secondRowCellRank = secondRowRank.findElement(By.className("tableCellRank"));
        WebElement thirdRow = rows.get(2);
        WebElement thirdRowRank = thirdRow.findElement(By.className("table__cell--rank"));
        WebElement thirdRowCellRank = thirdRowRank.findElement(By.className("tableCellRank"));


        assertEquals("Awans - Liga Mistrzów (Kwalifikacje)", firstRowCellRank.getAttribute("title"));
        assertEquals("Awans - Liga Konferencji Europy (Kwalifikacje)", secondRowCellRank.getAttribute("title"));
        assertEquals("Awans - Liga Konferencji Europy (Kwalifikacje)", thirdRowCellRank.getAttribute("title"));
    }

    @Test
    public void pointsTest() {
        WebElement tableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> rows = tableBody.findElements(By.className("ui-table__row"));
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("span"));
            assertEquals(Integer.parseInt(columns.get(5).getText()), 3 * Integer.parseInt(columns.get(1).getText()) + Integer.parseInt(columns.get(2).getText()));

        }
    }

    @Test
    public void goalsTest() {
        WebElement tableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> rows = tableBody.findElements(By.className("table__cell--score"));
        long goalsScored = 0;
        long goalsConceded = 0;
        for (WebElement row : rows) {
            String[] goals = row.getText().split(":");
            goalsScored = goalsScored + Integer.parseInt(goals[0]);
            goalsConceded = goalsConceded + Integer.parseInt(goals[1]);
        }
        assertEquals(goalsScored, goalsConceded);
    }

    @Test
    public void orderTest() {
        WebElement tableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> rows = tableBody.findElements(By.className("table__cell--points"));
        for (int i = 0; i < 17; i++) {
            assertTrue(Integer.parseInt(rows.get(i).getText()) >= Integer.parseInt(rows.get(i + 1).getText()));
        }
    }

    @Test
    public void homeAwayTotalPointsTest() {
        WebElement tableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> rows = tableBody.findElements(By.className("ui-table__row"));
        Map<String, Integer> totalPoints = new HashMap<>();
        for (WebElement row : rows) {
            totalPoints.put(row.findElement(By.className("tableCellParticipant__name")).getText(), Integer.parseInt(row.findElement(By.className("table__cell--points")).getText()));
        }

        WebElement homeButton = driver.findElement(By.xpath("//*[text()='U siebie']"));
        homeButton.click();
        WebElement homeTableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> homeRows = homeTableBody.findElements(By.className("ui-table__row"));
        Map<String, Integer> homePoints = new HashMap<>();
        for (WebElement row : homeRows) {
            homePoints.put(row.findElement(By.className("tableCellParticipant__name")).getText(), Integer.parseInt(row.findElement(By.className("table__cell--points")).getText()));
        }

        WebElement awayButton = driver.findElement(By.xpath("//*[text()='Na wyjeździe']"));
        awayButton.click();
        WebElement awayTableBody = driver.findElement(By.className("ui-table__body"));
        List<WebElement> awayRows = awayTableBody.findElements(By.className("ui-table__row"));
        Map<String, Integer> awayPoints = new HashMap<>();
        for (WebElement row : awayRows) {
            awayPoints.put(row.findElement(By.className("tableCellParticipant__name")).getText(), Integer.parseInt(row.findElement(By.className("table__cell--points")).getText()));
        }

        for (String team : totalPoints.keySet()) {
        /*   Integer teamTotalPoints = totalPoints.get(team);
           Integer teamHomePoints = homePoints.get(team);
           Integer teamAwayPoints = awayPoints.get(team);
         */
            assertEquals((long) totalPoints.get(team), homePoints.get(team) + awayPoints.get(team));
        }
    }
}
