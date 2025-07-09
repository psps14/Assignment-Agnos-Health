package testcase;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import config.DevConfig;
import testdata.TestData;
import page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TC1_LogInTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get(DevConfig.BASE_URL);
    }

    @Test
    public void TC02_01_VerifyLoginWithValidCredentials() throws InterruptedException, IOException {
        // get Page Object
        LoginPage loginPage = new LoginPage(driver);

        // Iinput data from 'TestData'
        loginPage.enterUsername(TestData.VALID_USERNAME);
        loginPage.enterPassword(TestData.VALID_PASSWORD);
        loginPage.clickLogin();

        // Check able to login and go to dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("css-tpc67v"))
        );
        assertTrue(dashboardText.isDisplayed());

        //TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./SeleniumScreenshots/Screen.png"));



    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
