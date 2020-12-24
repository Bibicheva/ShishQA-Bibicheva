import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class First {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void openPage() {
        String titleActual = driver.getTitle();
        String titleExpected = "Unlimint Payment Page";
        Assert.assertEquals("Title not matching", titleExpected, titleActual);
    }

    @Test
    public void firstPayment() throws IOException, InterruptedException {
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.sendKeys("4000000000000002");
        WebElement cardHolder = driver.findElement(By.id("input-card-holder"));
        cardHolder.sendKeys("Ci Von");
        WebElement cardExpriesMonth = driver.findElement(By.id("card-expires-month"));
        cardExpriesMonth.sendKeys("10");
        WebElement cardExpriesYear = driver.findElement(By.id("card-expires-year"));
        cardExpriesYear.sendKeys("2023");
        WebElement CVV = driver.findElement(By.id("input-card-cvc"));
        CVV.sendKeys("123");
        WebElement Question = driver.findElement(By.id("cvc-hint-toggle"));
        Question.click();

        WebElement Page = driver.findElement(By.id("main-container"));
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(3000))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver, Page);
        Thread.sleep(3000);
        ImageIO.write(screenshot.getImage(), "jpg", new File("C:\\Users\\bmvrz\\Desktop\\google-logo.png"));
        assertTrue(new File("C:\\Users\\bmvrz\\Desktop\\google-logo.png").exists());

        WebElement paymentdataActionsSubmit = driver.findElement(By.id("action-submit"));
        paymentdataActionsSubmit.click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement Success = driver.findElement(By.id("success"));
        Success.submit();
    }

}