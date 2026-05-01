import POM.CalcPage;
import Utilities.DriverUtilities;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.interactions.Actions;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;
import static org.testng.Assert.assertEquals;

@Listeners(Utilities.ListenerTest.class)
/**
 * The Main test file thats mapped in testng.xml
 */
public class CalculatorApp extends CalcPage {

    public AppiumDriver driver;
    public CalcPage calcPage;

    public CalculatorApp() throws MalformedURLException {
        driver = DriverUtilities.driver;
    }

    @BeforeClass
    public void setUp() throws MalformedURLException {
        calcPage = new CalcPage();
    }

    @BeforeMethod
    public void startApp(){
        Activity activity = new Activity("com.android2.calculator3","com.xlythe.calculator.material.Theme.Orange");
        ((AndroidDriver) driver).startActivity(activity);
}

    @Test(priority = 0, description = "Verify addition of two numbers")
    public void verifyAddFunctionality() throws InterruptedException {
        calcPage.enterTwoNumbersToAdd("2","4");
        Assert.assertEquals(calcPage.result(),"6");
        Reporter.log("Numbers have been added successfully");
    }

    @AfterMethod
    public void stopApp() {
        ((AndroidDriver) driver).closeApp();
    }
}
