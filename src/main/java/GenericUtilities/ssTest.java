package GenericUtilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

@Listeners(listener_Implementation.class)
public class ssTest {

    public static WebDriver driver;

    public static ExtentReports reports;

    public static ExtentTest test;


    // ================= BEFORE SUITE =================

    @BeforeSuite
    public void beforeSuite() {

        System.out.println("===== BEFORE SUITE =====");

        ExtentSparkReporter spark =
                new ExtentSparkReporter(
                        "./src/test/resources/reports/ExtentReport.html"
                );

        reports = new ExtentReports();

        reports.attachReporter(spark);

        System.out.println("Extent Report initialized");
    }


    // ================= BEFORE METHOD =================

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {

        System.out.println("===== BEFORE METHOD START =====");

        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else {

            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }

        driver.manage().window().maximize();

        driver.get("https://www.shoppersstack.com/");

        System.out.println("Home page opened");
        System.out.println("Title: " + driver.getTitle());
    }


    // ================= AFTER METHOD =================

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        System.out.println("===== AFTER METHOD START =====");

        if (driver != null) {

            try {

                WebDriverWait wait =
                        new WebDriverWait(
                                driver,
                                Duration.ofSeconds(10)
                        );

                // Account Settings

                WebElement settings = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//button[@aria-label='Account settings']"
                                )
                        )
                );

                settings.click();

                System.out.println("Account Settings clicked");


                // Logout

                WebElement logout = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//li[@role='menuitem' and normalize-space(.)='Logout']"
                                )
                        )
                );

                logout.click();

                System.out.println("Logout successful");

            } catch (Exception e) {

                System.out.println(
                        "Logout failed: "
                        + e.getClass().getSimpleName()
                );

            } finally {

                driver.quit();
                driver = null;

                System.out.println("Browser closed");
            }
        }
    }


    // ================= AFTER SUITE =================

    @AfterSuite
    public void afterSuite() {

        System.out.println("===== AFTER SUITE =====");

        if (reports != null) {

            reports.flush();

            System.out.println("Extent Report flushed");
        }
    }
}