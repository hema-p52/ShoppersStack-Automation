package GenericUtilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import GenericUtilities.ExcelUtility;

@Listeners(GenericUtilities.listener_Implementation.class)
public class shoppersStackTest extends ExcelUtility {

    public static WebDriver driver;
    public static ExtentTest test;
    public static ExtentReports reports;

    @BeforeSuite(alwaysRun = true)
    public void reportGen() {

        ExtentSparkReporter spark =
                new ExtentSparkReporter(
                        "./src/test/resources/reports/Log.html");

        reports = new ExtentReports();

        reports.attachReporter(spark);

        System.out.println("Report initialized");
    }

    @BeforeClass(alwaysRun = true)
    public void openBrowser() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.shoppersstack.com/");

        System.out.println("Browser opened");
        System.out.println("Home page opened");
        System.out.println("Title: " + driver.getTitle());

        // Create ExtentTest here
        test = reports.createTest("Signup Test");

        System.out.println("ExtentTest created");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {

        if (driver != null) {

            driver.quit();
            driver = null;

            System.out.println("Browser closed");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void flushReport() {

        if (reports != null) {

            reports.flush();

            System.out.println("Report flushed");
        }
    }
}