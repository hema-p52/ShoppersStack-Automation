package GenericUtilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class listener_Implementation implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println(
                "========== TEST START =========="
        );

        if (ssTest.reports != null) {

            ssTest.test = ssTest.reports.createTest(
                    result.getMethod().getMethodName()
            );

            System.out.println("ExtentTest created");
        }
    }


    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println(
                "========== TEST SUCCESS =========="
        );

        if (ssTest.test != null) {

            ssTest.test.log(
                    Status.PASS,
                    result.getMethod().getMethodName() + " PASS"
            );

            if (ssTest.driver != null) {

                TakesScreenshot ts =
                        (TakesScreenshot) ssTest.driver;

                String screenshot =
                        ts.getScreenshotAs(OutputType.BASE64);

                ssTest.test.addScreenCaptureFromBase64String(
                        screenshot
                );
            }
        }
    }


    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println(
                "========== TEST FAILURE =========="
        );

        System.out.println(
                "Test name: "
                + result.getMethod().getMethodName()
        );

        System.out.println(
                "Exception: "
                + result.getThrowable()
        );

        if (ssTest.test != null) {

            ssTest.test.log(
                    Status.FAIL,
                    result.getMethod().getMethodName() + " FAIL"
            );

            if (ssTest.driver != null) {

                TakesScreenshot ts =
                        (TakesScreenshot) ssTest.driver;

                String screenshot =
                        ts.getScreenshotAs(OutputType.BASE64);

                ssTest.test.addScreenCaptureFromBase64String(
                        screenshot
                );
            }
        }
    }
}