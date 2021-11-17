package lesson13;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class LaunchApp {
    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver= null;

        try {//Setup DesiredCapabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "R58M61NHNJP");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);


            // {capabilities} -----> Appium Server: 4723
            URL appiumServer = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AndroidDriver<>(appiumServer, desiredCapabilities);
        }catch (Exception ignored){
        }

        //Interact with Mobile Element
        if (appiumDriver!=null){
            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
            System.out.println("Connected appium server and launch target app!");
            MobileElement loginLable =appiumDriver.findElementByAccessibilityId("Login");
            loginLable.click();;
        } else {
            System.out.println("Error when connecting with Appium server!");
        }

    }
}
