package ShopperStackScipts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import GenericUtilities.ExcelUtility;
import ObjectRepository.ssLoginPage;
import GenericUtilities.ssTest;

public class LogIn extends GenericUtilities.ssTest {

    @Test(
        dataProvider = "getMultipleData",
        dataProviderClass = GenericUtilities.ExcelUtility.class
    )
    public void login(String email, String password) {

        System.out.println("Testing email: " + email);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement loginLink = null;

        // Login button retry
        for (int i = 0; i < 3; i++) {

            try {

                loginLink = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.id("loginBtn")
                        )
                );

                // Wait for popup/backdrop to disappear
                wait.until(
                        ExpectedConditions.invisibilityOfElementLocated(
                                By.cssSelector("div.MuiBackdrop-root")
                        )
                );

                wait.until(
                        ExpectedConditions.elementToBeClickable(loginLink)
                );

                loginLink.click();

                System.out.println("Login link clicked");
                break;

            } catch (Exception e) {

                System.out.println(
                        "Login click attempt "
                        + (i + 1)
                        + " failed"
                );

                if (i == 2) {
                    throw e;
                }

                driver.navigate().refresh();

                wait.until(d ->
                        ((JavascriptExecutor) d)
                                .executeScript(
                                        "return document.readyState"
                                )
                                .equals("complete")
                );
            }
        }

        // Verify login page
        wait.until(
                ExpectedConditions.urlContains("user-signin")
        );

        System.out.println(
                "URL: " + driver.getCurrentUrl()
        );

        // Login
        ssLoginPage lp = new ssLoginPage(driver);

        lp.getEmailtf().sendKeys(email);
        lp.getPasswordtf().sendKeys(password);
        lp.getLoginButton().click();

        // Verify successful login
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//button[@aria-label='Account settings']"
                        )
                )
        );

        System.out.println("LOGIN SUCCESS");
    }
}