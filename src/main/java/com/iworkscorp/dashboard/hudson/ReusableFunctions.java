package com.iworkscorp.dashboard.hudson;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

//import org.openqa.selenium.html5.ResultSet;

/**
 * @author vshivaraman
 * @author vpandey
 */
public class ReusableFunctions extends TestBase {

//	private static Logger LOGGER = LoggerFactory.getLogger(ReusableFunctions.class);


    public static int waitInBetween = Integer.parseInt(OR.getProperty("waitInBetween"));


    /**
     * WebDriver will find the element on the browser based on the Name
     *
     * @param ObjName - name of the particular element
     * @return
     */
    public static WebElement getObjectByName(String ObjName) {
        try {
            return driver.findElement(By.name(ObjName));
        } catch (Throwable t) {
            //report error
            Assert.assertTrue(t.getMessage(), false);
            return null;

        }
    }

    /**
     * WebDriver will find the element on the browser based on the XPath
     *
     * @param xpathKey - XPATH of the particular element
     * @return
     */
    public static WebElement getObjectByXpath(String xpathKey) {
        try {
            return driver.findElement(By.xpath(xpathKey));
        } catch (Throwable t) {
            //report error
            //TestUtil.takeScreenShot(xpathKey);
            Assert.assertTrue(t.getMessage(), false);
            return null;
        }
    }

    /**
     * WebDriver will find the element on the browser based on the LinkText
     *
     * @param linkText - LinkText of the particular Link
     * @return
     */
    public static WebElement getObjectByLinktext(String linkText) {
        try {
            return driver.findElement(By.linkText(linkText));
        } catch (Throwable t) {
            //TestUtil.takeScreenShot(linkText);
            Assert.assertTrue(t.getMessage(), false);
            return null;
        }
    }

    /**
     * WebDriver will find the element on the browser based on the class name
     *
     * @param className - classname of the particular element
     * @return
     */
    public static WebElement getObjectByClassName(String className) {
        try {
            return driver.findElement(By.className(className));
        } catch (Throwable t) {
            //TestUtil.takeScreenShot(className);
            //Assert.assertTrue(t.getMessage(), false);
            return null;
        }
    }

    /**
     * WebDriver will find the elements on the browser based on the class name
     *
     * @param className - classname of the particular element
     * @return
     */
    public static List<WebElement> getObjectsByClassName(String className) {
        try {
            return driver.findElements(By.className(className));
        } catch (Throwable t) {
            Assert.assertTrue(t.getMessage(), false);
            return null;
        }
    }

    /**
     * WebDriver will find the elements on the browser based on the class name
     *
     * @param tagName - tagName of the particular element
     * @return
     */
    public static List<WebElement> getObjectsBytagName(String tagName) {
        try {
            return driver.findElements(By.tagName(tagName));
        } catch (Throwable t) {
            Assert.assertTrue(t.getMessage(), false);
            return null;
        }
    }

    /**
     * WebDriver will find the elements on the browser based on the xpath and return the number of nodes with xpath specified
     *
     * @param by
     * @return
     */
    public static int getXpathCount(By by) {
        try {
            List<WebElement> locatedElements = driver.findElements(by);
            int count = 0;
            for (WebElement webElement : locatedElements) {
                count++;
            }
            return count;
        } catch (Throwable t) {
            Assert.assertTrue(t.getMessage(), false);
            return (Integer) null;
        }
    }

    /**
     * WebDriver will find the elements on the browser based on the linkText name
     *
     * @param linkText - linkText of the particular element
     * @return
     */
    public static List<WebElement> getObjectsByLinkText(String linkText) {
        try {
            return driver.findElements(By.linkText(linkText));
        } catch (Throwable t) {
            Assert.assertTrue(t.getMessage(), false);
            return null;
        }
    }

    /**
     * WebDriver will find the elements on the browser based on the ID
     *
     * @param id
     * @return
     */
    public static WebElement getObjectByID(String id) {
        try {
            return driver.findElement(By.id(id));
        } catch (Throwable t) {
            Assert.assertTrue(t.getMessage(), false);
            return null;
        }
    }

    /**
     * @param elements
     * @param attributename
     * @param value
     * @return
     */
    public static WebElement selectElementStrictEquals(List<WebElement> elements, String attributename, String value) {
        for (WebElement element : elements) {
            if (element.getAttribute(attributename).equals(value)) {
                return element;
            }
        }
        Assert.fail("Element not found! " + elements.toString());
        return null;
    }

    /**
     * @param elements
     * @param attributename
     * @param value
     * @return
     */
    public static WebElement selectElementIfEquals(List<WebElement> elements, String attributename, String value) {
        for (WebElement element : elements) {
            if (value.equals(element.getAttribute(attributename))) {
                return element;
            }
        }
        return null;
    }

    /**
     * @param elements
     * @param attributename
     * @param value
     * @return
     */
    public static WebElement selectElementContains(List<WebElement> elements, String attributename, String value) {
        for (WebElement element : elements) {
            if (element.getAttribute(attributename).toString().contains(value)) {
                return element;
            }
        }
        return null;
    }

    /**
     * @param elements
     * @param value
     * @return
     */
    public static WebElement selectElementContains(List<WebElement> elements, String value) {
        for (WebElement element : elements) {
            // System.out.println(element.getText());
            if (element.getText().contains(value)) {
                return element;
            }
        }
        // Assert.fail("Element not found! " + elements.toString());
        return null;
    }

    public static WebElement selectElementIfDisplayed(List<WebElement> elements, By by) {

        for (WebElement element1 : elements) {

            element1 = driver.findElement(by);

            if (element1.isDisplayed()) {
                return element1;
            }
        }
        // Assert.fail("Element not found! " + elements.toString());
        return null;
    }

    public static WebElement selectElementIfNotDisplayed(List<WebElement> elements, By by) {
        for (WebElement element1 : elements) {

            WebElement element2 = element1.findElement(by);

            if (element2 == null || !element2.isDisplayed()) {
                return element2;
            }
        }
        return driver.findElement(by);
    }


    /**
     * @param elements
     * @param value
     * @return
     */
    public static WebElement selectElementEquals(List<WebElement> elements, String value) {
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(value)) {
                return element;
            }
        }
        // Assert.fail("Element not found! " + elements.toString());
        return null;
    }

    /**
     * @param value
     */
    public static void clickCountryDropdown(String value) {
        List<WebElement> dropDown = driver.findElements(By.tagName("li"));

        ReusableFunctions.selectElementIfEquals(dropDown, "data-label", value).click();
        WaitingToLoad();
    }

    /**
     * @param type
     */
    public static void clickpoolRecModalDropdowns(String type) {
        List<WebElement> dropdowns = driver.findElements(By.className("selectItemText"));
        for (WebElement dropdown : dropdowns) {
            // System.out.println(dropdown.getAttribute("$89"));
            if (dropdown.getAttribute("$89").contains(type)) {
                dropdown.click();
                break;
            }
        }
    }

    /**
     * @param recType
     */
    public static void selectFromDropdown(String recType) {
        List<WebElement> rectypes = driver.findElements(By.id("majorTabPanel:createsub:j_id_eg_label"));
        for (WebElement element : rectypes) {
            if (recType.equals(element.getText())) {
                element.click();
                break;
            }
        }
    }

    /**
     * @param recType
     */
    public static void selectSelectedFromDropdown(String recType) {
        List<WebElement> rectypes = driver.findElements(By.className("pickListCellSelected"));
        for (WebElement element : rectypes) {
            if (recType.equals(element.getText())) {
                element.click();
                break;
            }
        }
    }

    /**
     * @param recType
     * @return
     */
    public static boolean selectFromDropdown1(String recType) {
        boolean flag = false;
        List<WebElement> rectypes = driver.findElements(By.className("pickListCell"));
        for (WebElement element : rectypes) {
            if (recType.equals(element.getText())) {
                element.click();
                flag = true;
                break;
            }
        }
        return flag;
    }


    /**
     * @param value
     * @param elementId
     */
    public static void selectWebListByVisibleText(String value, String elementId) {
        Select selectBox = new Select(driver.findElement(By.xpath(elementId)));
        selectBox.selectByVisibleText(value);
    }

    /**
     * @param value
     * @param elementId
     */
    public static void selectWebListByValue(String value, String elementId) {
        Select selectBox = new Select(driver.findElement(By.xpath(elementId)));
        selectBox.selectByValue(value);
    }

    /**
     * @param xpath
     * @param text
     */
    public static void VerifyText(String xpath, String text) {
        String Verify = driver.findElement(By.xpath(xpath)).getText();
//		System.out.println(Verify);
        Assert.assertTrue(Verify.contains(text));
        System.out.println(text + " is seen.");
    }

    /**
     * @param Attr
     * @param fieldAttr
     * @param fieldVal
     */
    public static void enterDetails(String Attr, String fieldAttr, String fieldVal) {
        List<WebElement> textboxes = driver.findElements(By.id("logonForm:myLogon"));
        for (WebElement element : textboxes) {
            if (fieldAttr.equalsIgnoreCase(element.getAttribute(Attr))) {
                element.clear();
                element.sendKeys(fieldVal);
                break;
            }
        }
    }

    /**
     * @param classname
     * @param text
     */

    //Asha method for wait and verify text
    public static void waitAndVerifyTextByClassname(String classname, String text) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        String Verify = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                Verify = ReusableFunctions.waitAndGetText(By.className(classname));

                if (Verify.contains(text)) {
                    System.out.println(Verify + " -is seen \n");
                } else {
                    System.out.println(Verify + " -is  not seen \n");
                }

                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element by " + classname + " with specified text :" + text + "\n");

    }

    public static void waitAndVerifyEnabled(By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement element = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {
                element = driver.findElement(by);

                if (element.isEnabled()) {
                    System.out.println(element + " is enabled \n");
                }
                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 10 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + element + "or is not enabled");

    }

    public static void waitAndVerifyNotEnabled(By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement element = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {
                element = driver.findElement(by);

                if (!element.isEnabled()) {
                    System.out.println(element + " is  not enabled \n");
                }
                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 10 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + element);

    }

    public static void waitAndVerifyErrMsg(By by, String msg) {

        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement expValue = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {
                expValue = driver.findElement(by);

                if (expValue.getText().contains(msg)) {
                    System.out.println("error message " + msg + "is seen \n");
                }
                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 10 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find the message" + msg);

    }


    public static void waitAndVerifyText(String xpath, String text) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        String Verify = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                Verify = driver.findElement(By.xpath(xpath)).getText();

                if (Verify.contains(text)) {
                    System.out.println(Verify + " -is seen \n");
                }

                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element " + Verify);


    }

    public static void waitAndVerifyIfElementExists(By xpath, By elementPath) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement element = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {


            try {

                List<WebElement> list = driver.findElements(xpath);
                element = ReusableFunctions.selectElementIfDisplayed(list, elementPath);

                if (element.isDisplayed()) {
                    System.out.println(element.toString() + " is seen,test pass \n");

                }

                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element " + element);


    }

    public static void waitAndVerifyIfElementDoesNotExists(By xpath, By elementPath) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement element = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {


            try {

                List<WebElement> list = driver.findElements(xpath);
                element = ReusableFunctions.selectElementIfNotDisplayed(list, elementPath);

                if (element.equals(null)) {
                    System.out.println(element.toString() + " is not seen,test pass \n");

                }

                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element " + element);


    }

    public static void waitAndVerifyIfElementDoesNotExists(By elementPath) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();


        while ((currentTime - startTime) / 1000 < waitSeconds) {


            try {

                List<WebElement> element = driver.findElements(elementPath);
                System.out.println(element.size());
                if (element.size() == 0) {

                    System.out.println(elementPath.toString() + " is  not seen,test pass \n");

                }

                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element  is seen");


    }


    public static String waitAndGetText(By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        String text = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                text = driver.findElement(by).getText();

                Thread.sleep(waitInBetween);
                System.out.println("The text seen is " + text + "\n");
                return text;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element " + by);


    }

    public static String waitAndGetValue(By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        String text = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                text = driver.findElement(by).getAttribute("value");

                Thread.sleep(waitInBetween);

                return text;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element " + by);


    }

    public static void waitUntilElementExistsAnsSendkeys(By by, String text) {
        waitUntilElementExistsAnsSendkeys(by, text, waitInBetween);
    }

    public static void waitUntilElementExistsAnsSendkeys(By by, String text, int waitInbetween) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement x = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                x = driver.findElement(by);

                x.isEnabled();
                x.sendKeys(text);
                Thread.sleep(waitInbetween);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(waitInbetween);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + x.getText() + "using" + by);
    }

    /**
     * @param by
     */
    public static void waitUntilElementExistsAndClick(By by) {
        waitUntilElementExistsAndClick(by, waitInBetween);
    }

    public static void waitUntilElementExistsAndClick(By by, int waitInBetweenSteps) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement x = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                x = driver.findElement(by);
                //System.out.println(x+"is the element"+by+" is the by used");
                x.isEnabled();
                x.click();
                Thread.sleep(waitInBetweenSteps);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(waitInBetweenSteps);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element: " + by);

    }

    public static void waitUntilElementExistsAndRightClick(By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement x = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {
                x = driver.findElement(by);

                Actions performClick = new Actions(driver);
                performClick.moveToElement(x);
                performClick.contextClick(x).perform();

                Thread.sleep(waitInBetween);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(waitInBetween);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + x.getText() + "using" + by);

    }

    public static void waitUntilElementExistsAndDoubleClick(By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement x = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {
                x = ReusableFunctions.waitUntilElementExistsAndFindBy(by);

                Actions performClick = new Actions(driver);
                performClick.moveToElement(x);
                performClick.doubleClick(x).perform();
                WaitingToLoad();
                Thread.sleep(waitInBetween);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(waitInBetween);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + x.getText() + "using" + by);

    }


    public static WebElement waitUntilElementExistsAndFindBy(By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement x = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                x = driver.findElement(by);

                Thread.sleep(waitInBetween);
                return x;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(waitInBetween);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + x.getText() + "using" + by);


    }

    public static List<WebElement> waitUntilElementsExistsAndFindBy(By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        List<WebElement> x = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                x = driver.findElements(by);

                Thread.sleep(waitInBetween);
                return x;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(waitInBetween);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + x.toString() + "using" + by);


    }

    public static void waitUntilElementExistsAndClear(By by) {
        waitUntilElementExistsAndClear(by, waitInBetween);
    }

    public static void waitUntilElementExistsAndClear(By by, int waitInBetweenSteps) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement x = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                x = driver.findElement(by);

                x.clear();

                Thread.sleep(waitInBetweenSteps);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(waitInBetweenSteps);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + x.getText() + "using" + by);


    }

    //Asha Method
    public static void waitAndSelectbyClassName(String className, String value) {
        waitAndSelectbyClassName(className, value, waitInBetween);
    }

    public static void waitAndSelectbyClassName(String className, String value, int waitInBetween) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        List<WebElement> list = null;
        WebElement lst = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                list = driver.findElements(By.className(className));
                lst = ReusableFunctions.selectElementContains(list, value);
                lst.click();
                Thread.sleep(waitInBetween);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(waitInBetween);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + lst.toString() + "in 60 sec");


    }

    // Asha Method
    public static void waitAndClick_Link(String linktext) {
        waitAndClick_Link(linktext, waitInBetween);
    }

    public static void waitAndClick_Link(String linktext, int waitInBetween) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        WebElement form = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                form = driver.findElement(By.xpath(JVSProperties.Container1.getProperty()));

                form.findElement(By.linkText(linktext)).click();
                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element" + linktext + "was not found within 60s");
    }


    /**
     * @param xpathKey
     * @param partlinktext
     */
    public static void click_partialLink(String xpathKey, String partlinktext) {
        pause(1);
        WebElement form = driver.findElement(By.xpath(OR.getProperty(xpathKey)));
//			System.out.println(form.getText());
        form.findElement(By.partialLinkText(partlinktext)).click();
        WaitingToLoad();
    }

    //Asha Method
    public static void assertFalseOnDropMenus(By by, String drplstName) {
        WebElement modal_window = driver.findElement(by);
        List<WebElement> box = modal_window.findElements(By.tagName("li"));
        WebElement x = ReusableFunctions.selectElementContains(box, drplstName);
        if (x != null) {
            System.out.println((drplstName + "is seen in the drop down. Hence the test fails \n"));

        } else {
            System.out.println(drplstName + " is not seen in the drop down list. \n");
        }

    }

    //Asha Method
    public static void waitAndClickOnDropMenus(By by, String drplstName) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        WebElement x = null;

        WebElement modal_window = null;
        List<WebElement> box = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                modal_window = driver.findElement(by);

                box = modal_window.findElements(By.tagName("li"));

                x = ReusableFunctions.selectElementContains(box, drplstName);

                x.click();
                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("Cannot find this element" + x.getText());

    }
//Asha method

    public static void selectCellFromTable(String containsKey, String tableXPath) {
        List<WebElement> subjects = driver.findElements(By.xpath(tableXPath));
        WebElement sub = ReusableFunctions.selectElementContains(subjects, containsKey);
        List<WebElement> cells = sub.findElements(By.tagName("td"));
        ReusableFunctions.selectElementContains(cells, containsKey).click();
        WaitingToLoad();
    }

    //Asha method
    public static void waitAndSelectCellFromTableAndClickFirstLink(String containsKey, String tableXPath) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                //Get the list of web element in the table
                webelementLst = driver.findElements(By.xpath(tableXPath));
                //System.out.println(webelementLst.get(0).getText());

                //Get the web element within the list which contains the value passed
                sub = ReusableFunctions.selectElementContains(webelementLst, containsKey);


                //Get the List of web elements that contains the tag tr in the table
                cells = sub.findElements(By.tagName("tr"));
                //WebElement cell = ReusableFunctions.selectElementContains(webelementLst, containsKey).findElement(By.tagName("tr")).findElement(By.tagName("a"));
                //cell.click();

                //click on the anchor contained in the cell within the row that contains the value sent
                ReusableFunctions.selectElementContains(cells, containsKey).findElements(By.tagName("a")).get(0).click();
                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element" + containsKey + "was not found within " + waitSeconds);
    }

    public static void waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(String valueOfCell1, String tableXPath, By byOfCellToClick) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {
                pause(2);
                //Get the list of web element in the table
                webelementLst = driver.findElements(By.xpath(tableXPath));


                //Get the web element within the list which contains the value passed
                sub = ReusableFunctions.selectElementContains(webelementLst, valueOfCell1);


                //Get the List of web elements that contains the tag tr in the table
                cells = sub.findElements(By.tagName("tr"));

                ReusableFunctions.selectElementContains(cells, valueOfCell1).findElement(byOfCellToClick).click();

                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element " + valueOfCell1 + " was not found within " + waitSeconds);
    }

    public static String waitAndGetNumberOfRowsInATable(String tableXPath) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        List<WebElement> webelementLst = null;
        WebElement sub = null;

        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {
                pause(2);
                //Get the list of web element in the table

                int rows = driver.findElements(By.xpath(tableXPath)).size();
                System.out.println("Number of rows in teh table is " + rows + "\n");
                //sub = ReusableFunctions.selectElementContains(webelementLst, value);


                Thread.sleep(waitInBetween);
                return Integer.toString(rows);
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element " + tableXPath + " was not found within " + waitSeconds);
    }

    public static WebElement waitAndfindCellFromTableBasedOnvalueOfAnotherCell(String valueOfCell1, String tableXPath, By byOfCellToClick) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                //Get the list of web element in the table
                webelementLst = ReusableFunctions.waitUntilElementsExistsAndFindBy(By.xpath(tableXPath));

                //Get the web element within the list which contains the value passed
                sub = ReusableFunctions.selectElementContains(webelementLst, valueOfCell1);


                //Get the List of web elements that contains the tag tr in the table
                cells = sub.findElements(By.tagName("tr"));

                //click on the anchor contained in the cell within the row that contains the value sent
                WebElement element = ReusableFunctions.selectElementContains(cells, valueOfCell1).findElement(byOfCellToClick);
                Thread.sleep(waitInBetween);
                return element;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element" + valueOfCell1 + "was not found within " + waitSeconds);
    }

    public static WebElement waitAndfindelementFromlistBasedOnvalueOfAnotherelement(String valueOfCell1, String xPath, By byOfCellToFind, String tag) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                //Get the list of web element in the table
                webelementLst = ReusableFunctions.waitUntilElementsExistsAndFindBy(By.xpath(xPath));
                int numberOFWebelements = webelementLst.size();
                //System.out.println("numberOFWebelements "+numberOFWebelements);
                //System.out.println(webelementLst.get(0).getText());

                //Get the web element within the list which contains the value passed
                sub = ReusableFunctions.selectElementContains(webelementLst, valueOfCell1);


                //Get the List of web elements that contains the tag in the parent xpath
                cells = sub.findElements(By.tagName(tag));
                //WebElement cell = ReusableFunctions.selectElementContains(webelementLst, containsKey).findElement(By.tagName("tr")).findElement(By.tagName("a"));
                //cell.click();

                //click on the anchor contained in the cell within the row that contains the value sent
                WebElement element = ReusableFunctions.selectElementContains(cells, valueOfCell1).findElement(byOfCellToFind);
                Thread.sleep(waitInBetween);
                return element;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element" + valueOfCell1 + "was not found within " + waitSeconds);
    }

    //Asha method
    public static void waitAndselectCellFromTable(String containsKey, By by) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                webelementLst = driver.findElements(by);

                sub = ReusableFunctions.selectElementContains(webelementLst, containsKey);

                cells = sub.findElements(By.tagName("td"));

                ReusableFunctions.selectElementContains(cells, containsKey).click();
                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element" + containsKey + "was not found within " + waitSeconds + "Seconds");
    }


    public static void waitAndselectCellFromTable(String containsKey, String tablePath) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                webelementLst = driver.findElements(By.xpath(tablePath));

                sub = ReusableFunctions.selectElementContains(webelementLst, containsKey);

                cells = sub.findElements(By.tagName("td"));

                ReusableFunctions.selectElementContains(cells, containsKey).click();
                Thread.sleep(waitInBetween);
                return;
            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element" + containsKey + "was not found within " + waitSeconds + "Seconds");
    }


    public static void click_partialLink2(String partlinktext) {
        WaitingToLoad();
        WebElement form = driver.findElement(By.id("container_2"));
        //System.out.println(form.getText());
        form.findElement(By.partialLinkText(partlinktext)).click();
        WaitingToLoad();
    }

    public static void click_SelectSMO_Dropdown() {
        WaitingToLoad();
        ReusableFunctions.VerifyText("//*[@id='page-container']", "SMO");
        WebElement form = driver.findElement(By.id("cpanelForm:smoPanel"));

        //Select the drop down
        List<WebElement> dropdowns = driver.findElements(By.id("cpanelForm:sitesel"));
        dropdowns.get(0).click();
        form.findElement(By.xpath("//*[@id='cpanelForm:j_id_2e']")).click();
        ReusableFunctions.WaitingToLoad();
    }

    public static void clickXButtonOnTab(String xpath) {
        WaitingToLoad();
        driver.findElement(By.xpath(xpath)).click();

//			Assert.assertFalse(driver.findElement(By.xpath(xpath)).isDisplayed());
    }

    public static void clickClose() {
        WaitingToLoad();
        List<WebElement> buttons = driver.findElements(By.className("ui-button-text"));
        WebElement button = ReusableFunctions.selectElementContains(buttons, "Close");
        WaitingToLoad();
        button.click();
    }

    public static void VerifyTabs(String text) {
        WaitingToLoad();
        WebElement container = driver.findElement(By.xpath(OR.getProperty("container2")));
        WebElement grid = container.findElement(By.tagName("ul"));
//			System.out.println(grid.getText());
        Assert.assertTrue(grid.getText().contains(text));
    }

    public static void Verify_By_Xpath_IfContains(String xpathKey, String value) {
        WaitingToLoad();
        List<WebElement> list = driver.findElements(By.xpath(OR.getProperty(xpathKey)));
        WebElement lst = ReusableFunctions.selectElementContains(list, value);
        Assert.assertTrue(lst.getText().contains(value));
//			System.out.println("===============================================================");
//			System.out.println(lst.getText());
//			System.out.println("===============================================================");
    }

    public static void Verify_By_Xpath_IfNotContains(String xpathKey, String value) {
        WaitingToLoad();
        List<WebElement> list = driver.findElements(By.xpath(OR.getProperty(xpathKey)));
        WebElement lst = ReusableFunctions.selectElementContains(list, value);
        Assert.assertFalse(lst.getText().contains(value));
//			System.out.println(lst.getText());
    }

    public static void Verify_By_Classname_IfContains(String ClassNameKey, String value) {
        WaitingToLoad();
        List<WebElement> list = driver.findElements(By.className(OR.getProperty(ClassNameKey)));
        WebElement lst = ReusableFunctions.selectElementContains(list, value);
//			System.out.println(lst.getText());
        Assert.assertTrue(lst.getText().contains(value));
    }

    public static void SearchButtonClick() {
        WaitingToLoad();
        WebElement button = driver.findElement(By.className("ui-button-text"));
        System.out.println(button.getText());
        button.findElement(By.xpath("//*[@id='cpanelForm:j_id_2r']")).click();
        WaitingToLoad();
    }

    public static void SelectCellFromGrid(String ssn, String subject) {
        WaitingToLoad();
        driver.findElement(By.xpath("//*[@id='majorTabPanel:SubTable:ssn:filter']")).sendKeys(ssn);
        WaitingToLoad();
        List<WebElement> subjects = driver.findElements(By.xpath("//*[@id='majorTabPanel:SubTable']"));
        WebElement sub = ReusableFunctions.selectElementContains(subjects, subject);
        List<WebElement> cells = sub.findElements(By.className("ui-dt-c"));
        ReusableFunctions.selectElementContains(cells, subject).click();
        WaitingToLoad();

//			WebElement container = driver.findElement(By.xpath(OR.getProperty("container2")));
//			List<WebElement> subjects = container.findElements(By.xpath("//*[@id='majorTabPanel:ViewSubjects']"));
//			WebElement table = ReusableFunctions.selectElementContains(subjects, "role", "tabpanel");
//			List<WebElement> cell = table.findElements(By.tagName("tr"));
//			System.out.println("====================================");
//			System.out.println(table.getSize());
//			cell.get(2).click();
//			WaitingToLoad();


    }

    public static void SelectFromGrid(String value) {
        WaitingToLoad();
        List<WebElement> cont2 = driver.findElements(By.xpath("//*[@id='container_2']"));
        WebElement grid = ReusableFunctions.selectElementContains(cont2, value);
        List<WebElement> tab = grid.findElements(By.xpath("//*[@id='majorTabPanel:ViewSubjects']"));
        WebElement tab1 = ReusableFunctions.selectElementContains(tab, value);
        List<WebElement> tab2 = tab1.findElements(By.xpath("//*[@id='majorTabPanel:SubTable']"));
        WebElement tab3 = ReusableFunctions.selectElementContains(tab2, value);
        List<WebElement> cells = tab3.findElements(By.className("ui-dt-c"));
        ReusableFunctions.selectElementContains(cells, value).click();
        WaitingToLoad();
    }

    public static void ButtonClick(String BtnText) {

        WaitingToLoad();
        List<WebElement> btn = driver.findElements(By.tagName("button"));
        WebElement btn1 = ReusableFunctions.selectElementContains(btn, BtnText);
        //System.out.println(btn1.getText());
        btn1.click();
        WaitingToLoad();
    }

    //Asha Mehtod
    public static void waitAndButtonClick(String BtnText) {
        waitAndButtonClick(BtnText, waitInBetween);
    }

    public static void waitAndButtonClick(String BtnText, int waitInbetween) {

        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        List<WebElement> btn = null;
        WebElement btn1 = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {
                btn = driver.findElements(By.tagName("button"));
                btn1 = ReusableFunctions.selectElementContains(btn, BtnText);
                btn1.click();
                Thread.sleep(waitInbetween);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element" + btn1 + "was not found within 100 ms");
    }


    public static void subjectPanel_searchBtn(String BtnText) {
        List<WebElement> buttons = driver.findElements(By.id("cpanelForm:subjetPanel"));
        WebElement btn = ReusableFunctions.selectElementContains(buttons, BtnText);
        WebElement btn1 = btn.findElement(By.tagName("button"));
        System.out.println(btn1.getText());
        btn1.click();

    }

    public static WebElement button(String BtnText) {
        WaitingToLoad();
        List<WebElement> btn = driver.findElements(By.tagName("button"));
        System.out.println(btn.size());
        WebElement btn1 = ReusableFunctions.selectElementContains(btn, BtnText);
        List<WebElement> btn2 = btn1.findElements(By.className("ui-button-text"));
        WebElement btn3 = ReusableFunctions.selectElementContains(btn2, BtnText);
        System.out.println(btn3.getText());
//			btn3.click();
        pause(2);
        if (btn3.getText().equalsIgnoreCase(BtnText)) {
            return btn3;
        }
        return null;
    }

    public static void LinkClick(String linkText) {
        WaitingToLoad();
        WebElement container = driver.findElement(By.id("container_2"));
        WebElement panel = container.findElement(By.id("majorTabPanel"));
        List<WebElement> cont2 = panel.findElements(By.tagName("li"));
        WebElement btn1 = ReusableFunctions.selectElementContains(cont2, linkText);
        System.out.println(btn1.getText());
        btn1.click();
        pause(1);
    }

    public static void subjectDetails_SubLinkClick(String linkText) {
        WaitingToLoad();
        WebElement container = driver.findElement(By.id("container_2"));
        WebElement panel = container.findElement(By.id("majorTabPanel:CaseDetail"));
        List<WebElement> cont2 = panel.findElements(By.tagName("li"));
        WebElement btn1 = ReusableFunctions.selectElementContains(cont2, linkText);
        System.out.println(btn1.getText());
        btn1.click();
        pause(1);
    }

    public static void verifyTrue(String xpathKey, String value) {
        String panel1 = getObject(xpathKey).getText();
        Assert.assertTrue(panel1.contains(value));
    }

    //Asha's method
    public static void verifyFalse(By by, String value) throws Exception {
        List<WebElement> list = driver.findElements(by);
        try {
            WebElement element = ReusableFunctions.selectElementContains(list, value);
            if (element.isDisplayed()) {
                System.out.println(value + " is seen test fail");
            }
        } catch (Exception e) {
            System.out.println(value + " is not seen hence test Pass");
        }

    }

    public static void verifyIfExistsAndTextPresent(By by, String value) throws Exception {
        List<WebElement> list = driver.findElements(by);
        try {
            WebElement element = ReusableFunctions.selectElementContains(list, value);
            String text = element.getText();
            if (element.isDisplayed() && (text.contains(value))) {
                System.out.println(value + " is seen hence test Pass \n");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(value + " is not seen hence test Fail \n");
            System.out.println();
        }

    }

    public static void verifyIfElementExists(By listPath, By elementPath) throws Exception {
        List<WebElement> list = driver.findElements(listPath);
        try {

            WebElement element = ReusableFunctions.selectElementIfDisplayed(list, elementPath);

            if (element.isDisplayed()) {
                System.out.println(elementPath.toString() + " is seen hence test Pass");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(elementPath.toString() + " is not seen hence test Fail");
            System.out.println();
        }

    }

    public static void LoginWithUser(String user) throws Exception {
        String usr = IdentifyUser(user);
//			SeleniumUtil.loginToPortal(usr);
        loginToPortal(usr);
    }

    //Asha method
    public static void waitAndLoginWithUser(String user) throws Exception {
        String usr = IdentifyUser(user);
//			SeleniumUtil.loginToPortal(usr);
        waitAndloginToPortal(usr);
    }

    public static void LoginWithCatsUser(String user) throws Exception {
        String usr = IdentifyUser(user);
//			SeleniumUtil.loginToCatsPortal(usr);
        loginToCats(usr);
    }

    //Asha Method
    public static void waitAndLoginWithCatsUser(String user) throws Exception {
        String usr = IdentifyUser(user);
//			SeleniumUtil.loginToCatsPortal(usr);
        waitAndloginToCats(usr);
    }

    public static void LoginToReports(String user) throws Exception {
        String usr = IdentifyUser(user);
//			SeleniumUtil.loginToPortal(usr);
        loginToReports(usr);
    }

    public static String IdentifyUser(String user) {
        String id = null;
        if (user.equalsIgnoreCase("Security_Officer_Standard")
                || user.equalsIgnoreCase("SOS")
                || user.equalsIgnoreCase("Standard")
                || user.equalsIgnoreCase("SecurityOfficerStandard")) {
//           id = "2001";
            id = "mikewasa84";
        } else if (user.equalsIgnoreCase("Security_Officer_Admin")
                || user.equalsIgnoreCase("SOA")
                || user.equalsIgnoreCase("Admin")
                || user.equalsIgnoreCase("SecurityOfficerAdmin")) {
            id = "macmind25";
//           id = "2002";
        } else if (user.equalsIgnoreCase("Physical_Access_Control_Personnel")
                || user.equalsIgnoreCase("PACP")
                || user.equalsIgnoreCase("Physical")
                || user.equalsIgnoreCase("PhysicalAccessControlPersonnel")) {
            id = "2903";
//           id = "1103";
        } else if (user.equalsIgnoreCase("Security_Officer_Visit_Admin")
                || user.equalsIgnoreCase("SOVA")
                || user.equalsIgnoreCase("Visit")
                || user.equalsIgnoreCase("SecurityOfficerVisitAdmin")) {
//           id = "2904";
            id = "1104";
        } else if (user.equalsIgnoreCase("Account_Manager")
                || user.equalsIgnoreCase("AM")
                || user.equalsIgnoreCase("Account")
                || user.equalsIgnoreCase("AccountManager")) {
//           id = "19";
            id = "troytrum67";
//           id = "9";
        } else if (user.equalsIgnoreCase("Hierarchy_Manager")
//                    || user.equalsIgnoreCase("HM")
                || user.equalsIgnoreCase("Hierarchy")
//                    || user.equalsIgnoreCase("H")
                || user.equalsIgnoreCase("HierarchyManager")) {
            id = " spacspen54";
//           id = "2808";
        } else if (user.equalsIgnoreCase("HR_Officer")
                || user.equalsIgnoreCase("HR")
                || user.equalsIgnoreCase("HROfficer")
                || user.equalsIgnoreCase("HROfficer")
                || user.equalsIgnoreCase("HumanResource")
                || user.equalsIgnoreCase("Human_Resources_Officer")) {
            id = "porthuma18";
//           id="1011";

        } else if (user.equalsIgnoreCase("Field_Adjudicator")
                || user.equalsIgnoreCase("FA")
                || user.equalsIgnoreCase("field adjudicator")
                || user.equalsIgnoreCase("FieldAdjudicator")) {
//       	id = "17";
//       	id = "11052";
            id = "100993";
//=========================================================================================================

            // CATS ROLES
        } else if (user.equalsIgnoreCase("Adjudicator")
                || user.equalsIgnoreCase("adjtor")
                || user.equalsIgnoreCase("ADJ")) {
            id = "kencats42";

        } else if (user.equalsIgnoreCase("Process_Team")
                || user.equalsIgnoreCase("PT")
                || user.equalsIgnoreCase("process team")
                || user.equalsIgnoreCase("ProcessTeam")) {
            id = "zoycats60";

        } else if (user.equalsIgnoreCase("Executive_Chief")
                || user.equalsIgnoreCase("Executive")
                || user.equalsIgnoreCase("executive chief")
                || user.equalsIgnoreCase("ExecutiveChief")) {
            id = "JaneLawr14";

        } else if (user.equalsIgnoreCase("Division_Chief")
                || user.equalsIgnoreCase("division chief")
                || user.equalsIgnoreCase("Division")
                || user.equalsIgnoreCase("DivisionChief")) {
            id = "MaynPres14";

        } else if (user.equalsIgnoreCase("Branch_Chief")
                || user.equalsIgnoreCase("Branch")
                || user.equalsIgnoreCase("branch chief")
                || user.equalsIgnoreCase("BranchChief")) {
            id = "HarrAlva14";

        } else if (user.equalsIgnoreCase("Team_Chief")
                || user.equalsIgnoreCase("TM")
                || user.equalsIgnoreCase("Team")
                || user.equalsIgnoreCase("team chief")
                || user.equalsIgnoreCase("TeamChief")) {
            id = "GailBuck14";

        } else if (user.equalsIgnoreCase("General_Counsel")
                || user.equalsIgnoreCase("GC")
                || user.equalsIgnoreCase("General")
                || user.equalsIgnoreCase("general counsel")
                || user.equalsIgnoreCase("GeneralCounsel")) {
            id = "RodePett14";

        } else if (user.equalsIgnoreCase("Trainee")
                || user.equalsIgnoreCase("Train")
                || user.equalsIgnoreCase("Student")
                || user.equalsIgnoreCase("Part Time")
                || user.equalsIgnoreCase("parttime")) {
            id = "PollMend14 ";

        } else if (user.equalsIgnoreCase("ITTF_Screener")
                || user.equalsIgnoreCase("ITTF")
                || user.equalsIgnoreCase("ittf screener")
                || user.equalsIgnoreCase("ITTFScreener")) {
            id = "LakaReyn14";

        } else if (user.equalsIgnoreCase("Reviewer")
                || user.equalsIgnoreCase("Rev")
                || user.equalsIgnoreCase("R")) {
            id = "kencats42";

        } else if (user.equalsIgnoreCase("Quality_Control")
                || user.equalsIgnoreCase("Quality_Control_Reviewer")
                || user.equalsIgnoreCase("QC")
                || user.equalsIgnoreCase("QCR")
                || user.equalsIgnoreCase("Quality")
                || user.equalsIgnoreCase("Reviewer")
                || user.equalsIgnoreCase("control")
                || user.equalsIgnoreCase("quality control reviewer")
                || user.equalsIgnoreCase("QualityControlReviewer")
                || user.equalsIgnoreCase("quality control")
                || user.equalsIgnoreCase("QualityControl")) {
            id = "LannWood14";

        } else if (user.equalsIgnoreCase("Help_Desk")
                || user.equalsIgnoreCase("HD")
                || user.equalsIgnoreCase("help desk")
                || user.equalsIgnoreCase("Help")
                || user.equalsIgnoreCase("Application Administrator")
                || user.equalsIgnoreCase("Application")
                || user.equalsIgnoreCase("Administrator")
                || user.equalsIgnoreCase("Admin")
                || user.equalsIgnoreCase("desk")
                || user.equalsIgnoreCase("HelpDesk")) {
            id = "1";

        } else if (user.equalsIgnoreCase("TECT")
                || user.equalsIgnoreCase("tec")
                || user.equalsIgnoreCase("teq")
                || user.equalsIgnoreCase("t")
                || user.equalsIgnoreCase("ITTF")
                || user.equalsIgnoreCase("te")) {
            id = "AlexButl14";

        } else if (user.equalsIgnoreCase("OPMLiaison")
                || user.equalsIgnoreCase("OPML")
                || user.equalsIgnoreCase("Liaison")) {
            id = "NollComb14";

        } else if (user.equalsIgnoreCase("PsychEvaluation")
                || user.equalsIgnoreCase("PsychE")
                || user.equalsIgnoreCase("PsycheEval")) {
            id = "ViraMead14";


        } else {
            id = user;
        }

        return id;
    }

    public static void loginToPortal(String username) throws InterruptedException {
        getObjectByXpath("//*[@id='hidden:continueToUrl']").click();            //Click the OK button on the Consent Page
        getObjectByID("myCheckbox").click();                                    //click checkbox to log in
        getObjectByID("logonForm:Username").sendKeys(username);                //enter username
        getObjectByID("logonForm:Password").sendKeys(CATSProperties.defaultPassword.getProperty());                //enter password
        getObjectByID("logonForm:l").click();
        WaitingToLoad();
        //click the PII consent "Continue" button
        getObjectByXpath("//*[@id='disclaimForm:discCont']").click();

    }

    //Asha wait to log in method
    public static void waitAndloginToPortal(String username) throws InterruptedException {
        //Click the OK button on the Consent Page
        waitUntilElementExistsAndClick(By.xpath("//*[@id='hidden:continueToUrl']"));

        //click checkbox to log in
        waitUntilElementExistsAndClick(By.id("myCheckbox"));

        // Enter Username
        waitUntilElementExistsAnsSendkeys(By.id("logonForm:Username"), username);
        //Enter password
        waitUntilElementExistsAnsSendkeys(By.id("logonForm:Password"), CATSProperties.defaultPassword.getProperty());                //enter password
        waitUntilElementExistsAndClick(By.id("logonForm:l"));
        //This was added due to issue seen in GATs in Jan 2016
        //driver.navigate().to("http://192.168.55.177:7003/diss-jvs-ui/faces/default.xhtml");
        //Click on  PII consent "Continue" button
        waitUntilElementExistsAndClick(By.xpath("//*[@id='disclaimForm:discCont']"));
        pause(5);//click the PII consent "Continue" button

    }

    public static void loginToCats(String username) throws InterruptedException {

        getObjectByXpath("//*[@id='hidden:continueToUrl']").click();            //Click the OK button on the Consent Page
        getObjectByID("myCheckbox").click();                                    //click checkbox to log in
        getObjectByID("logonForm:Username").sendKeys(username);                //enter username
        getObjectByID("logonForm:Password").sendKeys(CATSProperties.defaultPassword.getProperty());                //enter password
        getObjectByID("logonForm:l").click();
        WaitingToLoad();
        getObjectByID("disclaimForm:discCont").click();                            //click the PII consent "Continue" button
        getObjectByXpath("//*[@id='disclaimForm:discCont']").click();                            //click the PII consent "Continue" button
    }

    //Asha method
    public static void waitAndloginToCats(String username) throws InterruptedException {

        String currentURL = driver.getCurrentUrl();
        //		System.out.println(currentURL);
        if (currentURL.contains("catsMain")) {
            driver.navigate().to(CONFIG.getProperty("CATS_Url1"));
            pause(5);
            driver.navigate().to(CONFIG.getProperty("CATS_Url1"));
            //Click the OK button on the Consent Page
            waitUntilElementExistsAndClick(By.xpath("//*[@id='hidden:continueToUrl']"));

            //click checkbox to log in
            waitUntilElementExistsAndClick(By.id("myCheckbox"));

            // Enter Username
            waitUntilElementExistsAnsSendkeys(By.id("logonForm:Username"), username);
            //Enter password
            waitUntilElementExistsAnsSendkeys(By.id("logonForm:Password"), CATSProperties.defaultPassword.getProperty());                //enter password
            waitUntilElementExistsAndClick(By.id("logonForm:l"));
            // Click on "Continue" button
            waitUntilElementExistsAndClick(By.xpath("//*[@id='disclaimForm:discCont']"));
        }
        //Click the OK button on the Consent Page
        waitUntilElementExistsAndClick(By.xpath("//*[@id='hidden:continueToUrl']"));

        //click checkbox to log in
        waitUntilElementExistsAndClick(By.id("myCheckbox"));

        // Enter Username
        waitUntilElementExistsAnsSendkeys(By.id("logonForm:Username"), username);
        //Enter password
        waitUntilElementExistsAnsSendkeys(By.id("logonForm:Password"), CATSProperties.defaultPassword.getProperty());                //enter password
        waitUntilElementExistsAndClick(By.id("logonForm:l"));

        // Click on "Continue" button
        waitUntilElementExistsAndClick(By.xpath("//*[@id='disclaimForm:discCont']"));                            //click the PII consent "Continue" button
    }

    public static void loginToReports(String username) throws InterruptedException {

        getObjectByXpath("//*[@id='hidden:continueToUrl']").click();            //Click the OK button on the Consent Page
        getObjectByID("myCheckbox").click();                                    //click checkbox to log in
        getObjectByID("logonForm:Username").sendKeys(username);                //enter username
        getObjectByID("logonForm:Password").sendKeys(CATSProperties.defaultPassword.getProperty());                //enter password
        getObjectByID("logonForm:l").click();
        WaitingToLoad();
    }

    // Minesh Functions
    public static void verifyTextFromDropdownCATS(String drpMenuID, String drplstName) {
        WebElement modal_window = driver.findElement(By.id(drpMenuID));
        List<WebElement> box = modal_window.findElements(By.tagName("li"));
        WebElement x = ReusableFunctions.selectElementContains(box, drplstName);
        Assert.assertTrue(x.getText().contains(drplstName));

        // x.click();
    }

    public static void verifyFalseOnDropMenu(String drpMenuID, String drplstName) {
        WebElement modal_window = driver.findElement(By.id(drpMenuID));
        List<WebElement> box = modal_window.findElements(By.tagName("li"));
        WebElement x = ReusableFunctions.selectElementContains(box, drplstName);
        if (x != null) {
            System.out.println("System still allowed to create case with existing open case");
        } else {
            System.out.println("System not allowed to create case with existing open case");
        }
        // x.click();
    }

    public static void SelectFromDropdownCATS(String drpMenuID, String drplstName) {
        WebElement modal_window = driver.findElement(By.id(drpMenuID));
        List<WebElement> box = modal_window.findElements(By.tagName("li"));
        WebElement x = ReusableFunctions.selectElementContains(box, drplstName);
        Assert.assertTrue(x.getText().contains(drplstName));
        x.click();
    }

    public static void verifyTextFromDropdown(String drpMenuID, String drplstName) {
        WebElement modal_window = driver.findElement(By.id(drpMenuID));
        List<WebElement> box = modal_window.findElements(By.tagName("div"));
        WebElement x = ReusableFunctions.selectElementContains(box, drplstName);
        Assert.assertTrue(x.getText().contains(drplstName));
    }

    //Asha method
    public static void verifyTextFromDropdown1(By by, String drplstName) {
        WebElement modal_window = driver.findElement(by);
        List<WebElement> box = modal_window.findElements(By.tagName("li"));
        WebElement x = ReusableFunctions.selectElementContains(box, drplstName);
        Assert.assertTrue(x.getText().contains(drplstName));
    }

    public static void verifyEnabled(String txtBoxPath) {
        WebElement txtBox = driver.findElement(By.xpath(txtBoxPath));
        Assert.assertTrue(txtBox.isEnabled());

    }

    public static void verifyEnabledById(String id) {
        WebElement txtBox = driver.findElement(By.id(id));
        //WebElement txtBox = driver.findElement(By.cssSelector("div[id^='majorTabPanel:j_id_g']"));
        Assert.assertTrue(txtBox.isEnabled());

    }

    public static void click_MainPageLink(String linktext) {
        WebElement form = driver.findElement(By.id("container_2"));
        form.findElement(By.linkText(linktext)).click();
        WaitingToLoad();
    }

    //CATS Functions
    public static void clickBtnOnPopup(String clsName, String BtnName) {
        //WebElement popWindow = driver.findElement(By.id("majorTabPanel:createsub:j_id_ep"));
        List<WebElement> buttons = driver.findElements(By.id(clsName));
        WebElement button = ReusableFunctions.selectElementContains(buttons, BtnName);
        button.click();
    }

    public static void verifyErrMsg(String errXpath, String msg) {

        //ExpValue = driver.findElement(By.className(OR.getProperty("createSubjectErrMsg"))).getText();
//			String ExpValue = driver.findElement(By.xpath("//*[@id='majorTabPanel:missinginfomessages']/div/ul/li/span[2]")).getText();
        WebElement ExpValue = driver.findElement(By.xpath(errXpath));

        Assert.assertTrue(ExpValue.getText().contains(msg));

    }

    public static void verifyFromGrid(String xpathKey, String textToVerify) {
        WaitingToLoad();
        List<WebElement> list = driver.findElements(By.xpath(xpathKey));
        WebElement lst = ReusableFunctions.selectElementContains(list, textToVerify);
        Assert.assertTrue(lst.getText().contains(textToVerify));
    }

    public static void Verify_IfContains(String xpathKey, String value) {
        WaitingToLoad();
        List<WebElement> list = driver.findElements(By.xpath(xpathKey));
        WebElement lst = ReusableFunctions.selectElementContains(list, value);
        Assert.assertTrue(lst.getText().contains(value));
        System.out.println(lst.getText());

    }

    //Asha Method
    public static void waitAndVerify_IfContains(String xpathKey, String value) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        List<WebElement> list = null;
        WebElement lst = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                list = ReusableFunctions.waitUntilElementsExistsAndFindBy(By.xpath(xpathKey));

                lst = ReusableFunctions.selectElementContains(list, value);

                Assert.assertTrue(lst.getText().contains(value));
                System.out.println(value + "is seen \n");

                Thread.sleep(waitInBetween);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element" + value + "within 100 ms");
    }

    public static void waitAndVerify_IfTableContainsElement(String xpathKey, String elementXpath, String attribute, String attributeValue) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        List<WebElement> list = null;
        WebElement lst = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                list = ReusableFunctions.waitUntilElementsExistsAndFindBy(By.xpath(xpathKey));

                lst = ReusableFunctions.selectElementContains(list, elementXpath);
                String classAttribute = lst.getAttribute(attribute).toString();
                System.out.println(classAttribute + "\n");
                Assert.assertTrue(classAttribute.contains(attributeValue));
                System.out.println(attributeValue + "is seen \n");

                Thread.sleep(waitInBetween);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element with " + elementXpath + " was not found within 100 ms");
    }

    public static void waitAndVerifyIfNotContains(String xpathKey, String value) {
        int waitSeconds = 60;
        long startTime = Calendar.getInstance().getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        List<WebElement> list = null;
        WebElement lst = null;
        while ((currentTime - startTime) / 1000 < waitSeconds) {
            try {

                list = driver.findElements(By.xpath(xpathKey));

                lst = ReusableFunctions.selectElementContains(list, value);
                Assert.assertNull(lst);

                System.out.println(value + " is not seen.\n");

                Thread.sleep(waitInBetween);
                return;

            } catch (Exception e) {

                // just wait for 100 millis
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    // do nothing
                }
            }
            currentTime = Calendar.getInstance().getTimeInMillis();
        }
        throw new RuntimeException("This element " + xpathKey + " was not found within 60s");
    }

    /**
     * This function will return a string value of todays date
     *
     * @return
     * @author vshivaraman
     */
    public String selectTodaysDate() {
        // date picker
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        if (date.startsWith("0")) {
            date = date.substring(1);
        }
        return date;
    }

    /**
     * This function will return a string value of todays date
     *
     * @return
     * @author vshivaraman
     */
    public void selectDate(String month, String yearFourDigit, String date) {
        //select the month
        waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/select[1]/option[text()='" + month + "']"));

        // Select year
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[@value='" + yearFourDigit + "']"));

        if (date.startsWith("0")) {
            date = date.substring(1);
        }
        //Select date
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + date + "']"));

    }

    public static void selectFromDropMenu(String xPath, String tgName, Integer indexId) {
        List<WebElement> table = driver.findElements(By.xpath(xPath));
        WebElement grid = ReusableFunctions.selectElementContains(table, tgName);

        System.out.println(grid.getSize());
        List<WebElement> span = grid.findElements(By.tagName("span"));
        span.get(indexId).click();
    }

    public static void rightClick(String xPaths) {
        WebElement rightBtnClicks = driver.findElement(By.xpath(xPaths));
        Actions performClick = new Actions(driver);
        performClick.moveToElement(rightBtnClicks);
        performClick.contextClick(rightBtnClicks).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        WaitingToLoad();
    }
//Asha

    public static void rightClick1(String xPaths) {
        WebElement rightBtnClicks = driver.findElement(By.xpath(xPaths));
        Actions performClick = new Actions(driver);
        performClick.moveToElement(rightBtnClicks);
        performClick.contextClick(rightBtnClicks).perform();
        WaitingToLoad();
    }

    public static void doubleClick(String xPath) {
        WebElement doubleClicks = driver.findElement(By.xpath(xPath));
        Actions performClick = new Actions(driver);
        performClick.moveToElement(doubleClicks);
        performClick.doubleClick(doubleClicks).perform();
        WaitingToLoad();
    }

    public static void doubleClick(By by) {
        WebElement doubleClicks = driver.findElement(by);
        Actions performClick = new Actions(driver);
        performClick.moveToElement(doubleClicks);
        performClick.doubleClick(doubleClicks).perform();
        WaitingToLoad();
    }

    public static WebElement doubleClickClearAndReturnelement(By by, String sendkey) {
        WebElement doubleClicks = driver.findElement(by);
        Actions performClick = new Actions(driver);
        performClick.moveToElement(doubleClicks);
        performClick.doubleClick(doubleClicks).perform();
        performClick.sendKeys(doubleClicks, sendkey).perform();
        WaitingToLoad();

        return doubleClicks;
    }

    public static void passwordValidation(String username, String password) throws InterruptedException {


        getObjectByID("myCheckbox").click();                                    //click checkbox to log in
        getObjectByID("logonForm:Username").clear();
        getObjectByID("logonForm:Username").sendKeys(username);                //enter username
        getObjectByID("logonForm:Password").sendKeys(password);                //enter password
        getObjectByID("logonForm:l").click();
        //getObjectByXpath("//*[@id='disclaimForm:discCont']").click();							//click the PII consent "Continue" button
        WaitingToLoad();
    }

    public static void popUpMessage(String popupText) {
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel displayText = new JLabel(popupText);
        panel.add(displayText);
        JOptionPane.showOptionDialog(null, panel, "Attach Document", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    public static void mouseScroll(String xpathkey) {
        Actions action = new Actions(dr);
        WebElement elementToClick = driver.findElement(By.xpath(xpathkey));
        action.moveToElement(elementToClick).build().perform();
    }

    public static void hoverOver(By by) {
        Actions action = new Actions(driver);
        WebElement hoverOverElement = ReusableFunctions.waitUntilElementExistsAndFindBy(by);
        System.out.println("web element is " + hoverOverElement);
        action.moveToElement(hoverOverElement);

        action.perform();
        WaitingToLoad();
    }


    //Method to generate a random SSN. Use this for creating for new subjects.
    public static String randomssngenerator() throws Exception {

        String SSN = null;
        //String ssnfirstfive = "423-93-";
        String ssnfirstfive = "111-22-";

        int lastfour = (int) (Math.random() * 8999 + 1000);
        SSN = ssnfirstfive + lastfour;

        //System.out.println("SSN is: " +SSN);

        return SSN;
    }

    public static void VerifyTextByXPath(String xpathKey, String text) {
        String Verify = driver.findElement(By.xpath(OR.getProperty(xpathKey))).getText();
        Assert.assertTrue(Verify.contains(text));
    }

    public static void VerifyTextByClassname(String classname, String text) {
        String Verify = driver.findElement(By.className(classname)).getText();
        System.out.println(Verify);
        Assert.assertTrue(Verify.contains(text));
    }

    public static void VerifyTextByPartialtext(String id, String text) {
        String Verify = driver.findElement(By.id(id)).getText();
        Assert.assertTrue(Verify.contains(text));
    }

    public static void VerifyTextByID(String id, String text) {
        String Verify = driver.findElement(By.id(id)).getText();
        Assert.assertTrue(Verify.contains(text));
    }

    public static void VerifyTextFalse(String xpathKey, String text) {
        String Verify = driver.findElement(By.xpath(OR.getProperty(xpathKey))).getText();
        System.out.println(Verify);
        Assert.assertFalse(Verify.contains(text));
    }


    // ********** SQl Connection *********************
    public static Connection getConnectionQAJVS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "jvsqa");           //maintenance
        connectionProps.put("password", "jvsqa#IWC");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.10.202:1521:CTSQA", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionQACATS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "catsqa");           //maintenance
        connectionProps.put("password", "catsqa#IWC");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.10.202:1521:CTSQA", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionDemoCATS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "cats");           //maintenance
        connectionProps.put("password", "Meow#123");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.10.203:1521:CTSDMO", connectionProps);
        //System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionDemoJVS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "jvs");           //maintenance
        connectionProps.put("password", "Jvy3s#123");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.10.203:1521:CTSDMO", connectionProps);
        //System.out.println("Connected to database");
        return conn;
    }

    public static Connection getConnectionUATJVS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "UATJVS");           //maintenance
        connectionProps.put("password", "uatjvs#IWC");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.206:1521:CTSUAT", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionUATCATS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "UATCATS");           //maintenance
        connectionProps.put("password", "uatcats#IWC");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.206:1521:CTSUAT", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionDevCATS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "catsdev");           //maintenance
        connectionProps.put("password", "catsdev#IWC");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.10.201:1521:CTSDEV", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionDevJVS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "jvsdev");           //maintenance
        connectionProps.put("password", "jvsdev#IWC");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.10.201:1521:CTSDEV", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionGatCATS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "cats");           //maintenance
        connectionProps.put("password", "cats");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.196:1521:CTSGAT1", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionGatJVS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "jvs");           //maintenance
        connectionProps.put("password", "jvs");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.196:1521:CTSGAT1", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static Connection getConnectionBaselineCATS() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "DISSTEST");           //maintenance
        connectionProps.put("password", "dTest#321");           //maintenance
        conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.55.172:1521/DISSDGR.HQ.IWORKSCORP.COM", connectionProps);
        System.out.println("Connected to database \n");
        return conn;
    }

    public static String sqlQueryToList(String queryType, String query, String environment, String application, String columnName) {
        System.out.println("SQL> " + query + "\n");
        Connection con = null;
        String envr = environment + application;

        String Divs = "";
        PreparedStatement prs = null;
        ResultSet resultSet = null;

        if (envr.equalsIgnoreCase("qacats")) {
            try {
                con = getConnectionQACATS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("qajvs")) {
            try {
                con = getConnectionQAJVS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("uatcats")) {
            try {
                con = getConnectionUATCATS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("uatjvs")) {
            try {
                con = getConnectionUATJVS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("demojvs")) {
            try {
                con = getConnectionDemoJVS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("democats")) {
            try {
                con = getConnectionDemoCATS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("devjvs")) {
            try {
                con = getConnectionDevJVS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("devcats")) {
            try {
                con = getConnectionDevCATS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("gatcats")) {
            try {
                con = getConnectionGatCATS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("gatjvs")) {
            try {
                con = getConnectionGatJVS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } else if (envr.equalsIgnoreCase("baselinecats")) {
            try {
                con = getConnectionBaselineCATS();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }


        Statement stmt = null;
        try {
            prs = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            prs.execute();
            con.commit();
            if (queryType.equalsIgnoreCase("Select")) {
                resultSet = prs.getResultSet();

                while (resultSet.next()) {
                    Divs = resultSet.getString(columnName);
                    System.out.println("Result set is " + Divs + "\n");
                }
            }
            if (queryType.equalsIgnoreCase("update")) {
                System.out.println("No result set to process as it is an update statement \n");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return Divs;
    }


}



		
