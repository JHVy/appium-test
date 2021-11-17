package lesson14;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DriverFactoryTest {
    //private final static By creadsFormSel = By.xpath("//android.widget.EditText");
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLabel = androidDriver.findElementByAccessibilityId("Login");
            loginLabel.click();

            //Taking about relative XPath
            List<MobileElement> credsFormElems = androidDriver.findElementsByXPath("//android.widget.EditText");
            final int EMAIL_INPUT_INDEX = 0;
            final int PASSWORD_INPUT_INDEX = 1;
            credsFormElems.get(EMAIL_INPUT_INDEX).sendKeys("Teo@sth.com");
            credsFormElems.get(PASSWORD_INPUT_INDEX).sendKeys("87654321");

            //MobileElement emailInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-email']");
            //MobileElement passwordInputElem = androidDriver.findElementByXPath("//android.widget.EditText[@content-desc='input-password']");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");

            //Input email & password
            //emailInputElem.sendKeys("Teo@sth.com");
            //passwordInputElem.sendKeys("87654321");
            loginBtnElem.click();

            //Taking about handing Multiple matched elements and best practice

            //Some Xpath
            //*[contains(@test, When the device')]
            ////*[contains(@content-desc, 'Message read')]
            //new UiSelector().textContains("When the device").className("android.widget.TextView")

            MobileElement loginFeatureDescElem = androidDriver.findElementByXPath("//*[contains(@text,'When the device')]");
            MobileElement loginFeatureDescElemUiSel =
                    androidDriver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"When the device\").className(\"android.widget.TextView\")");
            System.out.println(loginFeatureDescElem.getText());
            System.out.println(loginFeatureDescElemUiSel.getText());

            WebDriverWait wait = new WebDriverWait(androidDriver, 45);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle")));
            MobileElement loginResultDialogElem = androidDriver.findElementById("android:id/alertTitle");
            System.out.println(loginResultDialogElem.getText());

        } catch (Exception ignored){

        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
