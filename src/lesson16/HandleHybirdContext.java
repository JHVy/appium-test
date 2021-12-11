package lesson16;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HandleHybirdContext {
    public static void main(String[] args) {

        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver();
            MobileElement webviewLabelElem = appiumDriver.findElementByAccessibilityId("Webview");
            webviewLabelElem.click();

            appiumDriver.getContextHandles().forEach(context -> {
                System.out.println(context);
            });

            appiumDriver.context("WEBVIEW_com.wdiodemoapp");
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle ");
            navToggleBtnElem.click();

            List<MobileElement> menuItems = appiumDriver.findElementsByCssSelector(".menu__list-item a");
            List<MenuItem> menuItemList = new ArrayList<>();
            for (MobileElement menuItem : menuItems) {
                String menuText = menuItem.getText();
                String menuHyberlink = menuItem.getAttribute("href");
                if(StringUtils.isEmpty(menuText)) {
                    menuItemList.add(new MenuItem("GitHub " +" "+ menuHyberlink));
                }else {
                    menuItemList.add(new MenuItem(menuText +" "+ menuHyberlink));
                }

            }



            Thread.sleep(3000);

            menuItemList.forEach(System.out::println);

            //switch to native context
            appiumDriver.context("NATIVE_APP");


            // interact with native context element
            MobileElement loginLabelElem = appiumDriver.findElementByAccessibilityId("Login");
            loginLabelElem.click();

            appiumDriver.runAppInBackground(Duration.ofSeconds(3));

            Thread.sleep(5000);

        } catch (Exception ignored) {

        } finally {
            DriverFactory.stopAppiumServer();
        }
    }

    public static class menuItem {
        private String text;
        private String hyperLink;

        public menuItem(String text, String hyperLink) {
            this.text = text;
            this.hyperLink = hyperLink;
        }

        public String text() {
            return text;
        }

        public String hyperLink() {
            return hyperLink;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "Text='" + text + '\'' +
                    ", HyperLink='" + hyperLink + '\'' +
                    '}';
        }
    }
}
