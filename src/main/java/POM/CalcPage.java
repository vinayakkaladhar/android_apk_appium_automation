package POM;

import Utilities.DriverUtilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class CalcPage extends DriverUtilities {

    AppiumDriver driver;

    public CalcPage() throws MalformedURLException {
        driver = getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), CalcPage.class);
    }

    @AndroidFindBy(id = "com.android2.calculator3:id/op_add")
    private AndroidElement addButton;

    @AndroidFindBy(id = "com.android2.calculator3:id/result")
    private AndroidElement result;

    public void enterTwoNumbersToAdd(String a, String b) throws InterruptedException {
        driver.findElement(By.id(String.format("com.android2.calculator3:id/digit_%s", a))).click();
        driver.findElement(By.id("com.android2.calculator3:id/op_add")).click();
        driver.findElement(By.id(String.format("com.android2.calculator3:id/digit_%s", b))).click();
    }

    public String result() throws InterruptedException {
        return driver.findElement(By.id("com.android2.calculator3:id/result")).getText().toString();
    }
}
