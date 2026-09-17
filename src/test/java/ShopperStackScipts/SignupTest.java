package ShopperStackScipts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ObjectRepository.SignupPage;
import GenericUtilities.shoppersStackTest;

public class SignupTest extends GenericUtilities.shoppersStackTest {

    @Test(dataProvider = "getMultipleData")
    public void signup(
            String firstName,
            String lastName,
            String gender,
            String phNum,
            String email,
            String password,
            String confPassword)
            throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        // Start every iteration from Home Page
        driver.get("https://www.shoppersstack.com/");

        System.out.println("--------------------------------");
        System.out.println("First Name : " + firstName);
        System.out.println("Last Name  : " + lastName);
        System.out.println("Gender     : " + gender);
        System.out.println("Phone      : " + phNum);
        System.out.println("Email      : " + email);
        System.out.println("Password   : " + password);
        System.out.println("Confirm    : " + confPassword);
        System.out.println("--------------------------------");

        // Login
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(normalize-space(),'Login')]")
                )
        ).click();

        System.out.println("Login clicked");

        // Wait for Sign-in page
        wait.until(
                ExpectedConditions.urlContains("user-signin")
        );

        System.out.println(
                "Sign-in page: " + driver.getCurrentUrl()
        );

        // Create Account
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[contains(normalize-space(),'Create Account')]")
                )
        ).click();

        System.out.println("Create Account clicked");

        SignupPage sp = new SignupPage(driver);

        // First Name
        sp.firstName().sendKeys(firstName);

        // Last Name
        sp.lastName().sendKeys(lastName);

        // Gender
        if ("Female".equalsIgnoreCase(gender)) {

            driver.findElement(By.id("Female")).click();

        } else if ("Male".equalsIgnoreCase(gender)) {

            driver.findElement(By.id("Male")).click();

        } else {

            driver.findElement(By.id("Other")).click();
        }

        // Phone
        sp.phoneNumber().sendKeys(phNum);

        // Email
        sp.emailAddress().sendKeys(email);

        // Password
        sp.passwordTF().sendKeys(password);

        // Confirm Password
        sp.confirmPasswordTf().sendKeys(confPassword);

        // Terms and Conditions
        WebElement terms = wait.until(
        	    ExpectedConditions.presenceOfElementLocated(
        	        By.id("Terms and Conditions")
        	    )
        	);

        	((JavascriptExecutor) driver)
        	    .executeScript(
        	        "arguments[0].scrollIntoView({block:'center'});",
        	        terms
        	    );

        	((JavascriptExecutor) driver)
        	    .executeScript(
        	        "arguments[0].click();",
        	        terms
        	    );

        // Register
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Register']")
                )
        ).click();

      //  test.log(Status.INFO, "Registration submitted");

        System.out.println("Registration submitted");

        Thread.sleep(5000);
    }
}
