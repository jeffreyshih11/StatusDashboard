package com.iworkscorp.dashboard.hudson;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;


import  com.iworkscorp.dashboard.hudson.CATSProperties;
import  com.iworkscorp.dashboard.hudson.JVSProperties;


/**
 * This class contains reusable methods, page functions and nested class of CATS
 * application
 * 10/30/2015-a All growl message verifications have been replaced by hard pause since growl messages display time is not standard and not reliable. Verification of growl messages have to be done manually at this time.
 *
 * @author vshivaraman
 */

public class CATSReusableFunctions extends  com.iworkscorp.dashboard.hudson.TestBase {
    ReusableFunctions reusable = new ReusableFunctions();

    public static class SSNFNameAndLName {
        public String firstName;
        public String lastName;
        public String ssn;
        public int index;


    }

    /**
     * This method will create a log file in the path provided
     *
     * @param className
     * @param name
     * @throws Exception
     * @author vshivaraman
     */
    public void createLogFile(String className, TestName name) throws Exception {
        // date picker
        DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        DateFormat dateFormat1 = new SimpleDateFormat("yyMMdd");
        String date1 = dateFormat1.format(cal.getTime());
        String environment = CONFIG.getProperty("Environment");
        File targetFile = new File("C://Users//Public//Documents//AutomationOutput//" + environment + "//" + className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc");
        targetFile.getParentFile().mkdirs();
        FileOutputStream file = new FileOutputStream(targetFile);
        PrintStream print = new PrintStream(file);
        System.setOut(print);
        System.out.println("Current test case is : " + name.getMethodName() + "\n");

    }

    public File createFile(String className, TestName name) throws Exception {
        // date picker
        DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        DateFormat dateFormat1 = new SimpleDateFormat("yyMMdd");
        String date1 = dateFormat1.format(cal.getTime());
        String environment = CONFIG.getProperty("Environment");
        File targetFile = new File("C://Users//Public//Documents//AutomationOutput//" + environment + "//" + className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc");
        targetFile.getParentFile().mkdirs();
        return targetFile;

    }

    public File createPictutreFile(String className, TestName name) throws Exception {
        // date picker
        DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        DateFormat dateFormat1 = new SimpleDateFormat("yyMMdd");
        String date1 = dateFormat1.format(cal.getTime());
        String environment = CONFIG.getProperty("Environment");
        File targetFile = new File("C://Users//Public//Documents//AutomationOutput//" + environment + "//" + className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".jpg");
        targetFile.getParentFile().mkdirs();
        return targetFile;

    }

    public String captureScreen(String className, TestName name) {
        File source = null;
        File targetFile;
        String filePath;

        try {
            targetFile = createPictutreFile(className, name);

            source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(source, targetFile);
            filePath = targetFile.getAbsolutePath();

        } catch (Exception e) {
            filePath = "Failed to capture screenshot: " + e.getMessage();
        }
        return filePath;
    }

    /**
     * This method will create a log file in the path provided
     *
     * @param className
     * @param name
     * @throws Exception
     * @author vshivaraman
     */
    public void createLogFile(String className, TestName name, File targetFile) throws Exception {

        //File targetFile = new File("C://Users//Public//Documents//AutomationOutput//" + className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc");
        targetFile.getParentFile().mkdirs();
        FileOutputStream file = new FileOutputStream(targetFile);
        PrintStream print = new PrintStream(file);
        System.setOut(print);
        System.out.println("Current test case is" + name.getMethodName() + "\n");

    }


    /**
     * This method will log in to CATS application using the user name passed
     *
     * @param user
     * @author vshivaraman
     */
    public void loginToCATS(String user) throws Exception {
        // Gets CATS URL
        driver.navigate().to(CONFIG.getProperty("CATS_Url"));
        // Calls the LoginWithUser function and passes the user id value to it
        ReusableFunctions.waitAndLoginWithCatsUser(user);

    }

    /**
     * log into CATS with user specified user and environment
     *
     * @param user
     * @param env
     * @throws Exception
     */
    public void loginToCATSEnv(String user, String env) throws Exception {
        String login_url = null;
        if (env.equals("DEV")) {
            login_url = "DEV_CATS_Url";

        } else if (env.equals("QA")) {
            login_url = "QA_CATS_Url";

        } else if (env.equals("GAT")) {
            login_url = "GAT_CATS_Url";

        } else if (env.equals("LOCAL")) {
            login_url = "LOCAL_CATS_Url";

        } else if (env.equals("BASELINE")) {
            login_url = "BASELINE_CATS_Url";

        } else if (env.equals("DEMO")) {
            login_url = "DEMO_CATS_Url";

        } else if (env.equals("UAT")) {
            login_url = "UAT_CATS_Url";

        }
        driver.navigate().to(CONFIG.getProperty(login_url));
        ReusableFunctions.waitAndLoginWithUser(user);
    }

    /**
     * log into CATS as user 1 into a user specified environment
     *
     * @param env
     * @throws Exception
     */
    public void loginToCATSasOneEnv(String env) throws Exception {
        loginToCATSEnv("1", env);
    }

    /**
     * This method will return a list of SSN,first name and last name
     * generated based on the baseSSN pattern and index set in AutoOR properties file
     * and then increment the index and update the AutoOR properties file for the next generation.
     * The baseSSN pattern and index to use are based on the enumeration values of IndexSSNTOUse and BaseSSNToUse in CATS_Propeties file.
     *
     * @param fName
     * @param lName
     * @throws Exception
     * @author vshivaraman
     */
    public List<SSNFNameAndLName> ssnNameGenerator(String fName, String lName) throws Exception {

        // Generate SSN, First Name and last name based on the baseSSN pattern and index set in AutoOR properties file.
        SSNFNameAndLName item = new SSNFNameAndLName();
        String index1 = AutoOR.getProperty(CATSProperties.IndexSSNToUse.getProperty());

        if (index1 != null) {
            index1 = index1.trim();
            //System.out.println(index1);

        }
        int index = Integer.parseInt(index1);
        item.firstName = fName + (index);
        if (item.firstName.length() > 20) {
            item.firstName = item.firstName.substring(0, 20);
        }
        item.lastName = lName + (index);
        String lastFour = ("000" + (index));
        item.ssn = AutoOR.getProperty(CATSProperties.BaseSSNToUse.getProperty()) + (lastFour).substring(lastFour.length() - 4);
        item.index = Integer.parseInt(lastFour);
        // Increment SSN Index
        index++;
        //System.out.println(index);

        // update the index with the incremented value in AutoOR properties file
        AutoOR.setProperty(CATSProperties.IndexSSNToUse.getProperty(), String.valueOf(index));
        OutputStream OrOut = new FileOutputStream(System.getProperty("user.dir") + "//src//config//AutoOR.properties");
        AutoOR.store(OrOut, null);
        OrOut.close();

        // Create a list of SSN, First name last name to be returned.
        List<SSNFNameAndLName> retList = new ArrayList<CATSReusableFunctions.SSNFNameAndLName>();

        retList.add(item);
        return retList;

    }

    /**
     * This method will create a subject based on the SSN,first name and last
     * name four digit value of the year and date values.
     *
     * @param ssn            Social Security Number of Subject.
     * @param fName          First name of Subject.
     * @param lName          last name of Subject.
     * @param yearFourDigits
     * @param date
     * @throws Exception
     * @author vshivaraman
     */
    public void createSubject(String ssn, String fName, String lName, String yearFourDigits, String date) throws Exception {

        // Click on the link create subject
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectLink.getProperty()));

        // cleans the subject ssn field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath(CATSProperties.CreateSubjectSSN.getProperty()));

        // enter the SSN value
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectSSN.getProperty()), ssn, 100);

        // Enter last name parameter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectLname.getProperty()), lName);


        // Enter First name parameter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectFname.getProperty()), fName);

        // click on date picker icon
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectCalender.getProperty()));

        // Select year
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[@value='" + yearFourDigits + "']"));

        //Select date
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + date + "']"));
        pause(2);

        // Click on the create subject button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectBtn.getProperty()));

        // Click on Yes
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectYesBtn.getProperty()));
        WaitingToLoad();
        pause(5);
        //Verify the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "A new subject has been created for "+fName+" "+lName);

    }

    /**
     * This method will create a subject based on the SSN,first name and last
     * name four digit value of the year, first 3 chars of the month and date values.
     *
     * @param ssn            Social Security Number of Subject.
     * @param fName          First name of Subject.
     * @param lName          last name of Subject.
     * @param yearFourDigits
     * @param date
     * @throws Exception
     * @author vshivaraman
     */
    public void createSubject(String ssn, String fName, String lName, String yearFourDigits, String month3Chars, String date, String birthCountry) throws Exception {

        // Click on the link create subject
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectLink.getProperty()));

        // cleans the subject ssn field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath(CATSProperties.CreateSubjectSSN.getProperty()));

        // enter the SSN value
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectSSN.getProperty()), ssn, 100);

        // Enter last name parameter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectLname.getProperty()), lName);


        // Enter First name parameter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectFname.getProperty()), fName);

        // click on date picker icon
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:cDOB']/button"));

        // Select year
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[@value='" + yearFourDigits + "']"));

        //Select the month
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]/option[text()='" + month3Chars + "']"));

        //Select date
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + date + "']"));
        pause(2);

        //Select birth country
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:birthCountrySelectMenu_label']"));

        //click on the country filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:birthCountrySelectMenu_filter']"));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:birthCountrySelectMenu_panel']//li[text()='" + birthCountry + "']"));

        // Click on the create subject button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectBtn.getProperty()));

        // Click on Yes
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:yesGrantForm']"));//majorTabPanel:yesGrantForm
        WaitingToLoad();
        pause(5);
        //Verify the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "A new subject has been created for "+fName+" "+lName);

    }

    /**
     * This method will create a subject based on the SSN,first name and last
     * name four digit value of the year and date values.
     *
     * @param ssn            Social Security Number of Subject.
     * @param fName          First name of Subject.
     * @param lName          last name of Subject.
     * @param yearFourDigits
     * @param date
     * @throws Exception
     * @author vshivaraman
     */
    public void createSubject(String ssn, String fName, String lName, String yearFourDigits, String date, String birthCountry) throws Exception {

        // Click on the link create subject
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectLink.getProperty()));

        // cleans the subject ssn field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath(CATSProperties.CreateSubjectSSN.getProperty()));

        // enter the SSN value
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectSSN.getProperty()), ssn, 100);

        // Enter last name parameter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectLname.getProperty()), lName);


        // Enter First name parameter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectFname.getProperty()), fName);

        // click on date picker icon
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:cDOB']/button"));

        // Select year
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/select[2]/option[@value='" + yearFourDigits + "']"));

        //Select date
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + date + "']"));
        pause(2);

        //Select birth country
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:birthCountrySelectMenu_label']"));

        //click on the country filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:birthCountrySelectMenu_filter']"));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:birthCountrySelectMenu_panel']//li[text()='" + birthCountry + "']"));
//		//
//		driver.findElement(By.xpath("//*[@id='majorTabPanel:createsub:birthCountrySelectMenu_filter']")).clear();
//
//		//
//		com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:createsub:birthCountrySelectMenu_filter']"), birthCountry);

        // Click on the create subject button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectBtn.getProperty()));

        // Click on Yes
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:yesGrantForm']"));
        WaitingToLoad();
        //Verify the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "A new subject has been created for "+fName+" "+lName);
        pause(5);
    }

    /**
     * This method will create the subject based on the ssn, first name and last name values. The DOB is always 1983-07-20
     *
     * @param ssn
     * @param fName
     * @param lName
     * @throws Exception
     * @author vshivaraman
     */
    public void createSubject(String ssn, String fName, String lName) throws Exception {

        String dob = "1983-07-20";
        // Click on the link create subject
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectLink.getProperty()));

        // cleans the subject ssn field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath(CATSProperties.CreateSubjectSSN.getProperty()));

        // enter the SSN value
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectSSN.getProperty()), ssn, 100);

        // Enter last name parameter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectLname.getProperty()), lName);

        // Enter First name parameter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectFname.getProperty()), fName);

        //Enter the DOB
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.CreateSubjectdob.getProperty()), dob);

        // Click on the create subject button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateSubjectBtn.getProperty()));

        // Click on Yes
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "A new subject has been created for " + fName + " " + lName);
        pause(5);

    }

    /**
     * This method will search for a subject based on SSN parameter passed
     *
     * @param ssn Social security number of the subject to search.
     * @author vshivaraman
     */
    public void searchSubjectCATS(String ssn) {
        // Click on the SubjectSSN search text field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.SsnSearch.getProperty()));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath(CATSProperties.SsnSearch.getProperty()));
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.SsnSearch.getProperty()));

        // Enter the SSN value
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.SsnSearch.getProperty()), ssn);

        // Click on search button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.SsnSearchBtn.getProperty()));
        pause(2);
        WaitingToLoad();

    }


    /**
     * This method will search for a user based on SSN parameter passed
     *
     * @param ssn Social security number of the user to search.
     * @author ssolorzano
     */
    public void searchUserCATS(String ssn) {
        // Click on the SubjectSSN search text field

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath(CATSProperties.userSSN.getProperty()));
        pause(2);
        // Enter the SSN value
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.userSSN.getProperty()), ssn, 500);

        // Click on search button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.userSSNSearch.getProperty()));
        WaitingToLoad();

    }

    /**
     * This method will create a case for the subject based on SSN, division
     * type, investigation type, case type and requesting SMO type passed.
     *
     * @param ssn                   Social Security number of subject for which case is to be
     *                              created.
     * @param divXpathProperty      Xpath for the division
     * @param caseTypeProperty      xpath for the case type
     * @param smoProperty           xpath for the requesting SMO
     * @param investigationProperty xpath for the Investigation type
     * @author vshivaraman
     */
    public void createCaseForSubject(String ssn, String divXpathProperty, String caseTypeProperty, String smoProperty, String investigationProperty) {

        // Call the searchSubject method to search for subject
        searchSubjectCATS(ssn);
        pause(4);

        // Click on the Subject Action drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.SubjectActionBtn.getProperty()));
        // Click on the create case option in the drop down list
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateCaseDropdownOption.getProperty()), 500);
        pause(2);

        // Click on the Investigation Type drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.InvestigationType.getProperty()), 500);
        pause(1);

        // click on value passed in drop down list
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(investigationProperty));
        pause(4);

        // Click on Division drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.SelectDivision.getProperty()));
        pause(1);


        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='createCaseForm:selectDivisionMenu_focus']"), "Division Auto");
        // Click on the division option based on the divXpathproperty passed
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(divXpathProperty));
        pause(2);

        // Click on case type drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CaseType.getProperty()));
        pause(2);

        // Click on the option based on value of caseTypeProperty passed
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(caseTypeProperty));
        pause(1);

        // Click on Requesting SMO drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.RequestingSMO.getProperty()));
        pause(1);

        // Click on option based on value for smoProperty passed
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(smoProperty));
        pause(1);

        // Click Create case button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateCaseButton.getProperty()));
        pause(1);

        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "A case ");
        pause(2);

    }

    /**
     * This method will create a user with all permissions based on the SSN and role within the organization specified with the given user name.
     *
     * @param ssn
     * @param userIDJPAS
     * @param organization
     * @param userName
     * @param role
     * @author vshivaraman
     */
    public void createUser(String ssn, int userIDJPAS, String organization, String userName, String role) {
        //search for the user management section
        searchUserCATS(ssn);

        //enter the JPASS User id
        String userIDJPAS1 = String.valueOf(userIDJPAS);
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:jpasUserId']"), userIDJPAS1);

        //Click on the auto password button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Generate Password");
        pause(2);

        driver.findElement(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']")).click();
        pause(2);

        //enter the user name should be 8 to 10 charcs only
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']"));
        pause(2);

        driver.findElement(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']")).click();
        pause(2);

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']"), userName);

        //select the Organization
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td/ul/li[@data-item-label='" + organization + "']"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//td/ul/li[@data-item-label='" + organization + "']");
        WaitingToLoad();

        //Select the role
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='" + role + "']"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='" + role + "']");
        pause(5);


        //select all permissions
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//table[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[@title='Add All']");
        WaitingToLoad();

        //Click on save
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        WaitingToLoad();
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully.");
        pause(5);

    }

    /**
     * This method will create a user with all permissions based on the SSN and role within the organization specified and return the user name.
     *
     * @param ssn
     * @param userIDJPAS
     * @param organization

     * @param role
     * @author vshivaraman
     */
    public String createUser(String ssn, int userIDJPAS, String organization, String role) {
        //search for the user management section
        searchUserCATS(ssn);

        //enter the JPASS User id
        String userIDJPAS1 = String.valueOf(userIDJPAS);
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:jpasUserId']"), userIDJPAS1);

        //Click on the auto password button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Generate Password");
        pause(2);

        //String userName = com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndGetText(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']"));
        pause(2);

        String userName = driver.findElement(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']")).getAttribute("value");

        //select the Organization
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td/ul/li[@data-item-label='" + organization + "']"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//td/ul/li[@data-item-label='" + organization + "']");
        WaitingToLoad();

        //Select the role
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='" + role + "']"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='" + role + "']");
        pause(5);


        //select all permissions
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//table[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[@title='Add All']");
        WaitingToLoad();

        //Click on save
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        WaitingToLoad();
        String growltext = com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndGetText(By.className("ui-growl-title"));
        if (growltext.equalsIgnoreCase("Please choose another registration user ID, as this one is already in use.")) {
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Generate User ID");
            pause(4);
            userName = driver.findElement(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']")).getAttribute("value");
            //Click on save
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        }
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully.");
        return userName;

    }

    /**
     * This method will return the Case ID based on the SSN search
     *
     * @param ssn
     * @author vshivaraman
     */
    public String getCaseID(String ssn) {
        // Click on SSN search text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.SsnSearch.getProperty()));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath(CATSProperties.SsnSearch.getProperty()));
        pause(2);
        // enter the value based on ssn passed
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(CATSProperties.SsnSearch.getProperty()), ssn);
        // Click on SSN search button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.SsnSearchBtn.getProperty()));

        // Get the case ID value from the subject case history data table
        String caseID = com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:subjtabview:subCaseDat_data']/tr[1]/td[1]"));
        System.out.println("SSN: " + ssn + "Case ID: " + caseID + "\n");
        // returns the case ID value to the calling function
        return caseID;
    }

    /**
     * This method will search for a case based on CaseID
     *
     * @param caseID
     * @author vshivaraman
     */
    public void searchCase(String caseID) {
        // Click on the Case search textr box on LHS panel under Case management
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='cpanelForm:mainSearchCase']"));
        pause(2);
        // Enter the Case ID
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='cpanelForm:mainSearchCase']"), caseID);

        // Search for the case
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Search Case");
        pause(10);
    }

    /**
     * This method will set the determination to favorable for your case based
     * on case ID using no disqualifying condition radio button
     *
     * @param caseID
     * @author vshivaraman
     */
    public void determinationFavorable(String caseID, String levelCodeOption) {

        String determination = CATSProperties.DeterminationFavorable.getProperty();
        //System.out.println("Determination to be set is " + determination+"\n");

        // Click on Files Review tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewTAb.getProperty()));

        // Click on save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewSave.getProperty()), 500);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Files Reviewed saved");
        pause(5);

        // Click on Guidelines Tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Guidelinestab.getProperty()));
        pause(5);

        // click on No disqualyfying radio button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(text(),'No Disqualifying Conditions')]"));

        // Click on save button
        // com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id(CATSProperties.GuidelinesSave.getProperty()));

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Guidelines Saved.");
        pause(30);

        // Click on Determination tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));
        pause(2);
        // Click on the Determination drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

        pause(5);

        // Click on the option based on value of determination variable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(determination));

        // Click on the Level code drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_label']"));

        pause(3);
        // Select the level code option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(levelCodeOption));

        // Click save Button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Determination saved.");
        pause(5);

        // Click on Summary tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

        // Click on Case closed button
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Close Case");
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CaseClosed.getProperty()));


        // Click on confirmation yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");
        WaitingToLoad();
        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Case Closed");
        pause(5);
        System.out.println("case is closed \n");
    }

    /**
     * This method will set the determination to favorable for your case based
     * on case ID using disqualifying condition and mitigation conditions
     *
     * @param caseID
     * @author vshivaraman
     */
    public void determinationFavorable(String caseID, String levelCodeOption, String conditionPanel, String disqualifyingCondition, String mitigationCondition) {

        String determination = CATSProperties.DeterminationFavorable.getProperty();
        //String determinationText = driver.findElement(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationFormId:adjudicationDeterminationCodeId_panel']/div/ul/li[text()='Favorable']/@text")).toString();
        //System.out.println("Determination to be set is " + determination);

        // Click on Files Review tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewTAb.getProperty()));

        // Click on save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewSave.getProperty()), 500);

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Files Reviewed saved");
        pause(5);

        // Click on Guidelines Tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Guidelinestab.getProperty()));

        // Expand on a condition panel
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(conditionPanel));
        pause(5);

        // Select a disqualifying reason
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(disqualifyingCondition));
        pause(5);

        // Select a mitigating condition
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(mitigationCondition));
        pause(3);

        // Click on save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelineOutputPanelId']//button[contains(.,'Save')]"));

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Guidelines Saved.");
        pause(15);

        // Click on Determination tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

        // Click on the Determination drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

        // Click on the option based on value of determination variable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(determination));

        // Click on the Level code drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_label']"));

        // Select the level code option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(levelCodeOption));

        // Click save Button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Determination saved.");
        pause(5);

        // Click on Summary tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

        // Click on Case closed button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Close Case");
        // com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CaseClosed.getProperty()));


//Case Closed
        // Click on confirmation yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");
        WaitingToLoad();

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Case Closed");
        pause(5);
        System.out.println("case is closed \n");

    }

    /**
     * This method will set the MPC code ,refer the case to due process or close the case based on the Non-favorable determination,
     * case ID, conditionPanel in which to select the disqualifying condition and disqualifying condition passed
     *
     * @param caseID
     * @param disqualifyingCondition
     * @param conditionPanel
     * @param determination
     * @author vshivaraman
     */
    public void determinationsNotFavorable(String caseID, String determination, String conditionPanel, String disqualifyingCondition, String codeMCP) {

        // Click on Files Review tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewTAb.getProperty()));

        // Click on save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewSave.getProperty()));

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Files Reviewed saved");
        pause(5);

        // Click on Guidelines Tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Guidelinestab.getProperty()));

        // Expand on a condition panel
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(conditionPanel));
        pause(2);

        // Select a disqualifying reason
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(disqualifyingCondition));

        // Click on save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Guidelines Saved.");
        pause(30);

        // Click on Determination tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

        pause(5);
        // Click on the Determination drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

        pause(5);
        // Click on the option based on value of determination variable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(determination));

        // Click save Button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Determination saved.");
        pause(5);

        // close the growl message
//		com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='maingrowl_container']/div/div/div[2]/span"));
//		com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='maingrowl_container']/div/div/div[1]"));

        // Select the MPC code drop down menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.MPCCodedropDown.getProperty()));

        // Select MPC code from list
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(codeMCP));

        // verify that growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "MPC code changed to ");
        pause(10);

        // Summary tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));
        // pause(4);

        if (CATSProperties.DeterminationNone.getProperty().equals(determination)) {
            // Click on the refer button
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Refer");
            pause(2);
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,'adjudicationHspdSuitReferConfirmCasePanelId')]//button[contains(@id,'ToDueProcessCaseYesButton')]/span[text()='Refer']"));//changed in 5.4

            // Click on Inbox button to send to case to Due process and to inbox
            // of the adjudicator
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Inbox");
            WaitingToLoad();

        } else {
            // Case Close Button
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Close Case");
            WaitingToLoad();

            // Click on confirmation yes button
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");


            WaitingToLoad();
            // verify that growl message is seen
            //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Case Closed");
            pause(5);
            System.out.println("case is closed \n");
        }

    }

    /**
     * This method will upload files from windows
     *
     * @param inputField
     * @param filePath
     * @param docType
     * @param docName
     * @author vshivaraman
     */
    public void docUpload(String inputField, String filePath, String docType, String docName) {
        // Type Document name
        // com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.id("documentUploaderForm:editAddDocumentMessagesDocName"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.id("documentUploaderForm:editAddDocumentMessagesDocName"), docName);

        // Click on the Select doc type drop down
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("documentUploaderForm:editAddDocumentMessagesDocType_label"));
        pause(2);

        // Select letter/document type
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(docType));


        // Pass the input field and file path for the document to be uploaded
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(inputField), filePath);
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//tr[6]//td[2]", "test.pdf");
        // Click add document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.AddDocBtn.getProperty()));
        pause(2);

    }

    /**
     * This method will upload files from windows
     *
     * @param inputField
     * @param filePath
     * @param docType
     * @param docName
     * @author vshivaraman
     */
    public void docUpload(String inputField, String filePath, String docType, String docName, String docDescription) {
        // Type Document name
        // com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.id("documentUploaderForm:editAddDocumentMessagesDocName"));
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("documentUploaderForm:editAddDocumentMessagesDocName"));

        //select 'add document'
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseDocumentsTableId:addDocumentButton"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.id("documentUploaderForm:editAddDocumentMessagesDocName"), docName);

        //enter the doc description
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//textarea[@id='documentUploaderForm:editAddDocumentMessagesDocDescription']"), docDescription);

        // Click on the Select doc type drop down
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("documentUploaderForm:editAddDocumentMessagesDocType_label"));
        pause(2);

        // Select letter/document type
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']/div/ul/li[text()='" + docType + "']"));


        //selecting choose
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.className("ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left ui-fileupload-choose"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("documentUploaderForm:fileUploadId_input"));


        // Pass the input field and file path for the document to be uploaded
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(inputField), filePath);
        pause(10);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//tr[6]//td[2]", "test.pdf");
        // Click add document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.AddDocBtn.getProperty()));
        pause(10);

    }

    /**
     * This method will upload the file from the file path in to the input field specified for manage templates
     *
     * @param inputField
     * @param filePath
     * @author vshivaraman
     */

    public void docUpload(String inputField, String filePath) {

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(inputField), filePath);
        pause(10);
        // Click add document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.AddDocBtn.getProperty()));

        pause(5);

    }

    /**
     * This method will initiate the SOR flow from CATS based on SSN, sorType
     * passed
     *
     * @param ssn

     */
    public void sorFlow(String ssn, String actionsType) {

        // Select and click on row in Open case data table based on SSN
        String searchableSsn = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
        ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

        // Click on the RFA tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFA"));

        // Click Actions button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button']"));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(actionsType));

        // Click Upload document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Upload Document");

        // Calls the method docUpload to upload the document from system file
        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), CATSProperties.SORLetterOption.getProperty(), "SOR");
        pause(2);

        // Click Send button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("RFARequestForm:sendRFA"));

        // Verify the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "sent");
        pause(30);

    }

    /**
     * This method will initiate the LOD/LOR flow from CATS based on SSN,
     * sorType passed
     *
     * @param ssn
     * @author vshivaraman
     */
    public void lodLORFlow(String ssn, String actionsType) {

        // Select and click on row in Open case data table based on SSN
        String searchableSsn = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
        ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

        // Click on the RFA tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFA"));

        // Click Actions button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button']"));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(actionsType));

        // Click Upload document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Upload Document");

        // Calls the method docUpload to upload the document from system file
        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), CATSProperties.LODLORDocTypeOption.getProperty(), "LOD/LOR");

        // Click Send button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("RFARequestForm:sendRFA"));

        WaitingToLoad();
        //pause(5);

        // Verify the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "sent");
        pause(30);

    }

    /**
     * This method will initiate the LOD/LOR flow as Process team member from
     * CATS based on firstname, lastname passed, calls the docUpload method to
     * upload the NOIA document
     *
     * @param firstName
     * @param lastName
     * @author vshivaraman
     */
    public void lodLORProcessTeamAppealsFlow(String firstName, String lastName) {

        // Click on the task inbox link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));

        //click on name filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='majorTabPanel:SubTabletasks:subjectLastName:filter']"));

        //enter name to filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:SubTabletasks:subjectLastName:filter']"), firstName + " " + lastName);

        // Select the task
        ReusableFunctions.waitAndSelectCellFromTableAndClickFirstLink(firstName + " " + lastName, "//*[@id='majorTabPanel:SubTabletasks']");

        // Click claim button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Claim");

        // Verify that the growl message seen contains the text specified
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "claimed");
        pause(5);

        // Click upload document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Upload Document");

        // Calls the method docUpload to upload the document from system file
        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), CATSProperties.DocTypeNOIA.getProperty(), "Notice of Intent to Appeal");

        // Click NOIAdate picker date button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.NOIAReceivedDatePicker.getProperty()));

        //call Select todays date function to pick todays date in the calendar
        String date = reusable.selectTodaysDate();

        String NOIAReceivedDate = "//*[@id='ui-datepicker-div']//a[text()='" + date + "']";

        // Select today's date in the calendar
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(NOIAReceivedDate));

        // Click AppealReceivedDate picker date button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.AppealDatePicker.getProperty()));

        //call Select todays date function to pick todays date in the calendar
        date = reusable.selectTodaysDate();
        String appealSentDate = "//*[@id='ui-datepicker-div']//a[text()='" + date + "']";

        // Select today's date in the calendar
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(appealSentDate));

        // Click Send button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Send");
        WaitingToLoad();

        // Verify that the growl message seen contains the text specified
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "closed");
        pause(30);

    }

    /**
     * This method will initiate the LOD/LOR flow as Process team member from
     * CATS based on firstname, lastname passed, calls the docUpload method to
     * upload the NOIA document
     *
     * @param firstName
     * @param lastName
     * @author vshivaraman
     */
    public void rfaProcessTeamFlow(String firstName, String lastName, String documentType, String docname) {

        // Click on the task inbox link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));

        //click on name filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='majorTabPanel:SubTabletasks:subjectLastName:filter']"));

        //enter name to filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:SubTabletasks:subjectLastName:filter']"), firstName + " " + lastName);

        // Select the task
        ReusableFunctions.waitAndSelectCellFromTableAndClickFirstLink(firstName + " " + lastName, "//*[@id='majorTabPanel:SubTabletasks']");

        // Click claim button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Claim");

        // Verify that the growl message seen contains the text specified
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "claimed");
        pause(5);
        //

        // Click upload document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Upload Document");

        // Calls the method docUpload to upload the document from system file
        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), "//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']//li[text()='" + documentType + "']", docname);


        // Click Send button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Send");
        WaitingToLoad();

        // Verify that the growl message seen contains the text specified
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "closed");
        pause(30);

    }

    /**
     * This method will adjudicate a case in appeals process as an adjudicator
     * based on the level, determination and exception values passed and verify
     * that the determination text is seen in the drop down list based on the
     * determinationText parameter value.
     *
     * @param caseID
     * @param level
     * @param determination
     * @param exception
     * @author vshivaraman
     */
    public void appealAdjudicationAsAdjudicator(String caseID1, String determinationText, String level, String determination, String exception) {

        // Search for the case
        searchCase(caseID1);

        // click on choose level drop down label
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.AppealsAdjudicationChooseLevel.getProperty()));

        // choose a level
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(level));

        // click on choose determination drop down label
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.AppealAdjudicationChooseDetermination.getProperty()));

        //Verify that No Action and NDM is not seen in the drop down
        if (determination.equalsIgnoreCase(CATSProperties.AppealDetermiantionNDM.getProperty()) || determination.equalsIgnoreCase(CATSProperties.AppealDetermiantionNOAction.getProperty())) {
            com.iworkscorp.dashboard.hudson.ReusableFunctions.assertFalseOnDropMenus(By.xpath("//div[contains(@id,'appealsDetermination_panel')]/div/ul"), determinationText);
            catsLogout();
        } else {

            //Verify that the determination text is seen in the drop down list
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//div[contains(@id,'appealsDetermination_panel')]/div/ul", determinationText);//55 for all except dev
            System.out.println(determinationText + " is seen \n");

            //Verify that Denied is not seen when eligibility is !=None
            if (determination.equalsIgnoreCase(CATSProperties.AppealDetermiantionRevoked.getProperty())) {
                //Verify that Denied is not seen
                com.iworkscorp.dashboard.hudson.ReusableFunctions.assertFalseOnDropMenus(By.xpath("//div[contains(@id,'appealsDetermination_panel')]/div/ul"), "Denied");
            }

            //Verify that Revoked is not seen
            if (determination.equalsIgnoreCase(CATSProperties.AppealDetermiantionDenied.getProperty())) {
                //Verify that Revoked is not seen
                com.iworkscorp.dashboard.hudson.ReusableFunctions.assertFalseOnDropMenus(By.xpath("//div[contains(@id,'appealsDetermination_panel')]/div/ul"), "Revoked");
            }

            // select a determination
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(determination));

            // click on choose exception drop down label
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.AppealsAdjudicationChooseException.getProperty()));

            // select an exception
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(exception));

            // click on complete button
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Complete");

            pause(5);
        }

    }

    /**
     * This method will add a Division to the CAF hierarchy tree
     * based on the CAF name and division name specified
     *
     * @param cafName
     * @param divisionName
     * @author vshivaraman
     */

    public void addDivision(String cafName, String divisionName) {

        //Click on the manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:cafHierarchyTreeTableId']//td[contains(text(),'" + cafName + "')]"));
        pause(2);

        //Right click on CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//div[@id='majorTabPanel:cafHierarchyTreeTableId']//td[contains(text(),'" + cafName + "')]");

        // Select add division link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Add Division"));

        // Select the empty text input field where the division is to be added
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td/input[@type='text']"));

        // enter the Division name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td/input[@type='text']"), divisionName);

        // Click on Save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // Click on Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");
        WaitingToLoad();
        // Verify that the growl message is seen
        pause(5);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");

        System.out.println(divisionName + "has been added to " + cafName + "\n");


    }

    /**
     * This method will add a branch to the CAF tree under a branch based
     * on the CAF name and division name specified
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @author vshivaraman
     */
    public void addBranch(String cafName, String divisionName, String branchName) {


        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//span[parent::td[contains(text(),'" + cafName + "')] and contains(@class,'ui-treetable-toggler')]"));////span[parent::td[contains(text(),'CAF')] and contains(@class,'ui-treetable-toggler')]
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='"+cafName+"']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // select the division and right click  where the branch is to be added
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + divisionName + "']");

        // Select Add branch link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Add Branch"));

        //Select the empty text input field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td/input[@type='text']"));

        //Enter the Branch name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/td/input[@type='text']"), branchName);

        //Select the save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select the Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");
        WaitingToLoad();
        //Verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(2);
        System.out.println(branchName + "has been added to " + divisionName + "under " + cafName + "\n");

    }

    /**
     * This method will edit the division name from divisionname to
     * divisionname1 specified under the CAF specified based on the parameter
     * passed
     *
     * @param cafName
     * @param divisionName
     * @param divisionName1
     * @author vshivaraman
     */
    public void editDivision(String cafName, String divisionName, String divisionName1) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAf
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Select Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']"));

        // Right click on the division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + divisionName + "']");

        // Select the edit division link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Edit"));

        //Select the text field to be edited
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td/input[@type='text']"));

        //Clear the text in the field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//*/td/input[@type='text']"));
        pause(1);

        //Enter the new name for the division
        String divName1 = divisionName1.substring(9);
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td/input[@type='text']"));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/td/input[@type='text']"), divName1);

        // Select the save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // Select the Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");
        WaitingToLoad();
        // Verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(5);

        // Verify the Division name edit has been saved
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));

        // expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']/span"));

        // get the current Division name name and compare it with the name that was passed for divisonName1 parameter
        String newTeamName = com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndGetText(By.xpath("//*/td[text()='" + divisionName1 + "']"));
        if (newTeamName.equals(divisionName1)) {
            System.out.println(divisionName + " has been edited to " + divisionName1 + "\n");
        } else {
            System.out.println(divisionName + " has not been edited to " + divisionName1 + "\n");
        }

    }

    /**
     * This method will edit the branchname to branchname1 specified under the
     * division and CAF specified based on the parameter passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param branchName1
     * @author vshivaraman
     */
    public void editBranch(String cafName, String divisionName, String branchName, String branchName1) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        //Select the branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']"));

        // right click on the Branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + branchName + "']");

        // Select the edit link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Edit"));

        //Select the text field for the branch to be edited
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td/input[@type='text']"));


        //clear the field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//*/td/input[@type='text']"));
        pause(1);

        //Enter the new branch name
        String bchName1 = branchName1.substring(7);
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td/input[@type='text']"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td/input[@type='text']"), bchName1);

        // Select the save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // Select the Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");
        WaitingToLoad();
        // Verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(5);

        // Verify the branch name edit has been saved
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']/span"));

        // Expand the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // get the current branch name and verify it is same as the value passed for the parameter branchName1
        String newBranchName = com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndGetText(By.xpath("//*/td[text()='" + branchName1 + "']"));
        if (newBranchName.equals(branchName1)) {
            System.out.println(branchName + "has been edited to " + branchName1 + "\n");
        } else {
            System.out.println(branchName + "has not been edited to " + branchName1 + "\n");
        }

    }

    /**
     * This method will edit the teamname to teamname1 specified under the
     * division,branch and CAF specified based on the parameter passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @param teamName1
     * @author vshivaraman
     */
    public void editTeam(String cafName, String divisionName, String branchName, String teamName, String teamName1) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // Expand the Branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        //Select the Team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + teamName + "']"));

        // Right click on the team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']");

        // Select the edit team link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Edit"));

        //Select the text field for the team to be edited
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td/input[@type='text']"));

        //Clear the text field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//*/td/input[@type='text']"));

        //Enter the new team name
        String tName1 = teamName1.substring(5);
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td/input[@type='text']"));
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td/input[@type='text']"), tName1);

        // Select the save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // Select the Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        // Verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(5);

        // Verify the branch name edit has been saved
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']/span"));

        // Expand the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // Expand the Branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        // get the current team name and verify that it is the same as the value passed for the parameter teamName1
        String newTeamName = com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndGetText(By.xpath("//*/td[text()='" + teamName1 + "']"));
        if (newTeamName.equals(teamName1)) {
            System.out.println(teamName + "has been edited to " + teamName1 + "\n");
        } else {
            System.out.println(teamName + "has not been edited to " + teamName1 + "\n");
        }
        System.out.println();
    }

    /**
     * This method will add a team under a branch based on the branch name,
     * division name and CAF name specified
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @author vshivaraman
     */
    public void addTeam(String cafName, String divisionName, String branchName, String teamName) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // right click on the Branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + branchName + "']");

        // Select the add team link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Add Team"));

        // Select the empty input text field
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td/input[@type='text']"));

        // enter the team name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td/input[@type='text']"), teamName);

        // Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // Select Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        // verify that the growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");

        System.out.println(teamName + " has been added to " + branchName + " under division " + divisionName + " under CAF called " + cafName);
        System.out.println();
    }

    /**
     * This method will delete the team specified under the branch,division and
     * CAF specified based on the parameters passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @author vshivaraman
     */
    public void deleteTeam(String cafName, String divisionName, String branchName, String teamName) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // Expand the Branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        //Right click on the team to be deleted
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']");

        //Select the delete link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Delete"));

        // Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // Select Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        // verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(2);
        System.out.println(teamName + " has been deleted from " + branchName + " under division " + divisionName + " under CAF called " + cafName);
        System.out.println();
    }

    /**
     * This method will delete the branch under the division and CAF specified
     * based on the parameter values passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @author vshivaraman
     */
    public void deleteBranch(String cafName, String divisionName, String branchName) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        //right click on the branch to be deleted
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + branchName + "']");

        //Select the delete link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Delete"));

        // Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // Select Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        // verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(2);
        System.out.println(branchName + " has been deleted from " + branchName + " under division " + divisionName + " under CAF called " + cafName);
        System.out.println();
    }

    /**
     * This method will delete the division specified under the CAF specified
     * based on the parameters passed
     *
     * @param cafName
     * @param divisionName
     * @author vshivaraman
     */
    public void deleteDivision(String cafName, String divisionName) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Right click on the division to be deleted
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + divisionName + "']");

        //Select the delete link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Delete"));

        // Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        // Select Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        // verify that the growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");

        System.out.println(divisionName + " has been deleted from " + " under CAF called " + cafName);
        System.out.println();
    }

    /**
     * This method will add a team member specified to the team specified under
     * the branch,division and CAF specified
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @param teamMemberFName
     * @author vshivaraman
     */
    public void addTeamMember(String cafName, String divisionName, String branchName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {

        // Click on Manage Hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        // Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // Expand the Branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        //Right click on the team where the member is to be added
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']/span");

        //Click on the details link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));

        //Click on the add member button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Add Member");

        //Click on the last name search filter text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"));

        // filter by last name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"), teamMemberLName);

        //Call the pagination method to make sure appropriate div is selected based on if pagination is seen or not
        userSelectPagination(teamMemberFName);

        //Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select yes in the confirmation pop up
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify that the growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");

        System.out.println(teamMemberFName + " has been added as team member to " + teamName + " has been added to " + branchName + " under division " + divisionName + " under CAF called " + cafName);
        System.out.println();

        //Click on the CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));

    }

    public void addProcessTeamMember(String cafName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {

        // Click on Manage Hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        // Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Right click on the team where the member is to be added
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']/span");

        //Click on the details link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));

        //Click on the add member button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Add Member");

        //Click on the last name search filter text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"));

        // filter by last name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"), teamMemberLName);

        //Call the pagination method to make sure appropriate div is selected based on if pagination is seen or not
        userSelectPagination(teamMemberFName);

        //Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select yes in the confirmation pop up
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify that the growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");

        System.out.println(teamMemberFName + " has been added as team member to " + teamName + "under CAF called " + cafName);
        System.out.println();

        //Click on the CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));

    }


    /**
     * This method will verify the details in the CAF details page based on the
     * cafname,chief name, team member name passed to the method
     *
     * @param cafName
     * @param chiefFName
     * @param chiefLName
     * @param teamMemberFName
     * @param teamMemberLName
     * @throws Exception
     * @author vshivaraman
     */

    public void verifyCAFDetails(String cafName, String chiefFName, String chiefLName, String teamMemberFName, String teamMemberLName) throws Exception {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[contains(text(),'" + cafName + "')]"));
        pause(2);

        //Right click on the CAF for which the details are to be verified
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + cafName + "']");

        // Select Details link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Details"));

        //Verify if the chief name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfExistsAndTextPresent(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:teamDetailChief']"), (chiefFName + " " + chiefLName));

        //Verify if the CAF name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfExistsAndTextPresent(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:teamDetailCafName']"), cafName.substring(4));

        //Verify if the text box for member name filter is clickable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter"));
        System.out.println("Member filter text box is clickable");
        System.out.println();

        //Verify that the inbox count filter is clickable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:inboxCount:filter"));
        System.out.println("Inbox filter text box is clickable");
        System.out.println();

        //Verify that the inventory count filter is clickable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:inventoryCount:filter"));
        System.out.println("Inventory filter text box is clickable");
        System.out.println();

        //Verify that the division filter is clickable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:memberDivision:filter"));
        System.out.println("Division filter text box is clickable");
        System.out.println();

        //Verify that the Branch filter is clickable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:memberBranch:filter"));
        System.out.println("Branch filter text box is clickable");
        System.out.println();

        //Verify that the team filter is clicable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:memberTeam:filter"));
        System.out.println("Team filter text box is clickable");
        System.out.println();

        //Verify that the role filter is clickable
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:memberRole:filter"));
        System.out.println("Role filter text box is clickable");
        System.out.println();

        //Select the text box for member name filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter"));

        //Enter the team member name to be filtered
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.name("majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter"), teamMemberFName + " " + teamMemberLName);

        //Verify that the result contains the team member based on the filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table", teamMemberFName + " " + teamMemberLName);
        System.out.println("Team member is seen in the table list");
        System.out.println();

//		//Select the team member form the result
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");

        //Verify that the User details button is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:HierarchyEntityUserDetailInfoHeader']/div", "User Detail");
        System.out.println("User Detail seen");
        System.out.println();

//		// click on the User details button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("User Detail");

//		//Verify that the team member name is seen correctly in the resulting user details page
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:catsUserDetailForm:catsUserName']", teamMemberFName + " " + teamMemberLName);
        System.out.println("Team member is seen in the user details page under user name");
        System.out.println();
        //Click on the Member details tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel']/ul//a[text()='Member Details']"));
        pause(2);

        //Click on the CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel']/ul//a[text()='CAF Tree']"));
        //Click on the save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Click on the Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(5);
        System.out.println();

        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/li[contains(.,'CAF Tree')]/span[@title='Close tab']"));


    }

    /**
     * This method will verify the division details based on the division name, CAF name and chief name passed
     *
     * @param cafName
     * @param divisionName
     * @param chiefFName
     * @param chiefLName
     * @author vshivaraman
     */
    public void verifyDivisionDetails(String cafName, String divisionName, String chiefFName, String chiefLName, String teamMemberFName, String teamMemberLName) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Select the division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']"));

        // right click on the division whose details are to be verified
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + divisionName + "']");

        // Select the details link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));

        // Verify if the member page has division chief name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:HierarchyEntityUserListTabInfoHeader']/div", chiefFName + " " + chiefLName);

        // verify CAF name in the division details page
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:HierarchyEntityUserListTabInfoHeader']/div", cafName.substring(4));

        //Verify division name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:HierarchyEntityUserListTabInfoHeader']/div", divisionName.substring(9));

        //Verify that members table is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']", "Members ");

        //click on the member filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

        //Enter the team member name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);

        //Verify that the team member name is seen in the table
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table", teamMemberFName + " " + teamMemberLName);


        //Click on the CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));


    }

    /**
     * This method will verify the branch details based on the branch name, division name, CAF name and chief name passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param chiefFName
     * @param chiefLName
     * @author vshivaraman
     */
    public void verifyBranchDetails(String cafName, String divisionName, String branchName, String chiefFName, String chiefLName, String teamMemberFName, String teamMemberLName) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Expand the division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        //Select the branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']"));

        //Right click on the branch whose details are to be verified
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + branchName + "']");

        //Select the details link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));

        // Verify if the member page has division chief name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:HierarchyEntityUserListTabInfoHeader']/div", chiefFName + " " + chiefLName);

        // verify caf name in the division details page
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:HierarchyEntityUserListTabInfoHeader']/div", cafName.substring(4));

        //Verify division name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:HierarchyEntityUserListTabInfoHeader']/div", divisionName.substring(9));

        //Verify that the branch name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:HierarchyEntityUserListTabInfoHeader']/div", branchName.substring(7));

        //Verify that members table is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']", "Members ");

        //click on the member filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

        //Enter the team member name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);

        //Verify that the team member name is seen in the table
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table", teamMemberFName + " " + teamMemberLName);


        //Click on the CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));


    }

    /**
     * This method will verify the team member details based on the team member name, team name, branch name, division name and CAF name passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @param teamMemberFName
     * @param teamMemberLName
     * @throws Exception
     * @author vshivaraman
     */
    public void verifyTeamMemberDetails(String cafName, String divisionName, String branchName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        // Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand the division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // expand the branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        // Right click at team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']/span");

        // Select details option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Details"));

        // Select the row with the team member name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");

        // Verify that case Assignment tab is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfExistsAndTextPresent(By.partialLinkText("Case Assignment"), "Case Assignment");

        // Verify Investigation type tab is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfExistsAndTextPresent(By.partialLinkText("Investigation Type"), "Investigation Type");

        // Verify Adjudication phase tab is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfExistsAndTextPresent(By.partialLinkText("Adjudication Phase"), "Adjudication Phase");

        // Verify Priority Program tab is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfExistsAndTextPresent(By.partialLinkText("Priority Program Group"), "Priority Program Group");

        // Verify that the check box for auto assign is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfElementExists(By.xpath(CATSProperties.Container2.getProperty()), By.xpath("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:memberAssignCasesCB']/div[contains(concat(' ',normalize-space(@class),' '),'ui-chkbox-box')]"));

        //Click on the drop down menu for productivity level
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:hierarchyEntityUserDetailId:memberDetailsProductivityLevelId']//span"));

        //Verify drop down has option "2"
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:memberDetailsProductivityLevelId_panel']/div/ul", "2");

        //Click on Case assignment tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Case Assignment"));

        // Verify that the radio button for New cases followed by In-Progress is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfElementExists(By.xpath("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options']"), By.xpath("//tr[1]//span[contains(concat(' ',normalize-space(@class),' '),'ui-radiobutton-icon')]"));

        // Verify that the text New cases followed by In-Progress is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//label[@for='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options:0']", "New cases followed by In-Progress");

        // Verify that the radio button for In-Progress cases followed by New is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfElementExists(By.xpath("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options']"), By.xpath("//tr[2]//span[contains(concat(' ',normalize-space(@class),' '),'ui-radiobutton-icon')]"));

        // Verify that the text In-Progress cases followed by New is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//label[@for='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options:1']", "In-Progress cases followed by New");

        // Verify that the radio button for None is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.verifyIfElementExists(By.xpath("//*[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options']"), By.xpath("//tr[3]//span[contains(concat(' ',normalize-space(@class),' '),'ui-radiobutton-icon')]"));

        // Verify that the text None is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//label[@for='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options:2']", "None");

        //Click on the CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));


    }

    /**
     * This method will verify the team details based on the team member name, team name, branch name, division name and CAF name passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @param teamMemberFName
     * @param teamMemberLName
     * @throws Exception
     * @author vshivaraman
     */
    public void verifyTeamDetails(String cafName, String divisionName, String branchName, String teamName, String chiefFName, String chiefLName, String teamMemberFName, String teamMemberLName) throws Exception {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        // Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand the division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // expand the branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        // Right click at team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']/span");

        // Select details option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));

        //Verify that the chief name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:teamDetailChief']", chiefFName + " " + chiefLName);

        //Verify if CAF name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:teamDetailCafName']", cafName.substring(4));

        //Verify if the division Name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:teamDetailDivision']", divisionName.substring(9));

        //Verify that the branch name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:teamDetailBranch']", branchName.substring(7));

        //Verify that the team name is seen correctly
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:teamDetailTeam']", teamName.substring(5));

        //Verify that members table is seen
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']", "Members ");

        //click on the memeber filter
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

        //Enter the team member name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);

        //Verify that the team member name is seen in the table
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table", teamMemberFName + " " + teamMemberLName);

        //Click on the CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));


    }

    /**
     * This method will update the team member's permissions based on the CAF
     * name, division name, branch name, team name, the members name and if
     * cases are to be auto assigned or not and if the new cases should be added
     * first or not.
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @param teamMemberFName
     * @param teamMemberLName
     * @param autoAssign
     * @param newCaseFirst
     * @author vshivaraman
     */
    public void editTeamMemberDetails(String cafName, String divisionName, String branchName, String teamName, String teamMemberFName, String teamMemberLName, String autoAssign, String newCaseFirst) {

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        // Expand Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // Expand branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        //right click on  the Team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']/span");

        // select the Team Details link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));

        // Select the team member to update
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");

        //click on productivity level drop downmenu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:hierarchyEntityUserDetailId:memberDetailsProductivityLevelId']//span"));

        //Select productivity level as 1
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:hierarchyEntityUserDetailId:memberDetailsProductivityLevelId_panel']//li[text()='1']"));

        if (autoAssign.equalsIgnoreCase("Yes") && newCaseFirst.equalsIgnoreCase("yes")) {
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(.,'New cases followed by In-Progress')]"));
        }

        if (autoAssign.equalsIgnoreCase("Yes") && newCaseFirst.equalsIgnoreCase("no")) {
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(.,'In-Progress cases followed by New')]"));
        }
        if (autoAssign.equalsIgnoreCase("no")) {
            com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(.,'None')]"));
        }

        //Select the Investigation type tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Investigation Type"));

        //Select all case types
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//button[@title='Add All']");
        pause(2);

        //Select the Adjudication phase tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Adjudication Phase"));

        //Select all process
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']/tbody/tr/td[2]/button[@title='Add All']");

        //Select the priority program tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Priority Program Group"));

        //Select all the priority programs
        com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserPriorityProgramPickList']/tbody/tr/td[2]/button[@title='Add All']");
        pause(2);

        //Click on save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Click on yes in the confirmation pop up
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify that the growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        System.out.println();

        //Click on the CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));
    }

    /**
     * This method will add a division chief specified to the division specified
     * under the CAF specified
     *
     * @param cafName
     * @param divisionName
     * @param chiefFName
     * @param chiefLName
     * @throws Exception
     * @author vshivaraman
     */
    public void addDivisionChief(String cafName, String divisionName, String chiefFName, String chiefLName) throws Exception {
        //

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:cafHierarchyTreeTableId']//td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));
        pause(2);
        System.out.println("CAF is expanded correctly.");

        // Select Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']"));

        //Get the table path
        String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId']/div[2]/table";
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(divisionName, tableXPath, By.tagName("button"));

        WaitingToLoad();

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"));
        // filter by last name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"), chiefLName);

        //Call the pagination method to select the correct div based on whether pagination is seen or not
        userSelectPagination(chiefFName);

        //Click on the save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Click on the Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify that the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(2);
        System.out.println();

    }

    /**
     * This function will add citizenship of a subject based on the social security number,citizenship type,country,date,month[first 3 letters] and year passed
     *
     * @param subjectSSN
     * @param citizenshipType
     * @param country
     * @param date
     * @param month
     * @param yearFourDigits
     * @throws Exception
     * @author vshivaraman
     */
    public void addCitizenshipForSubject(String subjectSSN, String citizenshipType, String country, String date, String month, String yearFourDigits) throws Exception {

        //Search for subject
        searchSubjectCATS(subjectSSN);
        pause(2);
        //click on Add citizenhip button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@title='Add Citizenship']"));
        pause(5);
        //click on country menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='newCitizenshipFrm:citizenshipOfBirtheditId_label']"));//newCitizenshipFrm:citizenshipOfBirtheditId_label
        pause(2);
        //Select the country
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='newCitizenshipFrm:citizenshipOfBirtheditId_panel']//li[@data-label='" + country + "']"));  //newCitizenshipFrm:citizenShipOfBirtheditId_panel
        pause(4);
        //Click on the citizenshipType dropdown menu
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='newCitizenshipFrm:citisenshipType_label']"));//newCitizenshipFrm:citisenshipType_label
        //select the citizentship type
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='newCitizenshipFrm:citisenshipType_panel']//li[@data-label='" + citizenshipType + "']"));//newCitizenshipFrm:citisenshipType_panel
        //click on the calendar icon
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='newCitizenshipFrm:citizenshipDate']/button"));//newCitizenshipFrm:citizenshipDate
        //Select the month
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//select[contains(@class,'ui-datepicker-month')]/option[text()='" + month + "']"));
        // Select year
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//select[contains(@class,'ui-datepicker-year')]/option[@value='" + yearFourDigits + "']"));

        //Select date
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td/a[text()='" + date + "']"));


        //click on add button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='Add']"));

        //
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Citizenship");
        pause(10);

    }

    /**
     * This method will upload document to the template based on the template name passed
     *
     * @param templateName
     * @throws Exception
     * @author vshivaraman
     */
    public void manageTemplates(String templateName) throws Exception {

        // Get the table path
        String tableXPath = "//*[@id='majorTabPanel:templatemanageform:templatetable']/div[2]/table";

        //Click on manage template link on the LHS panel
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Templates"));

        //Select the upload button based on teh template name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(templateName, tableXPath, By.tagName("button"));
        WaitingToLoad();
        //call the doc upload function to upload the template document
        docUpload("//*[@id='documentUploaderForm:fileUploadId_input']", OR.getProperty("filePath1"));
    }

    /**
     * This method will upload document to the template based on the template name passed
     *
     * @param templateName
     * @throws Exception
     * @author vshivaraman
     */
    public void manageTemplates(String templateName, String filePath) throws Exception {

        //Click on manage template link on the LHS panel
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Templates"));

        //Select the upload button based on teh template name
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:templatemanageform:templatetable']//td[preceding-sibling::td/span[contains(text(),'" + templateName + "')]]/button[contains(@id,'documentAddManageTemplates')]"));
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(templateName, tableXPath, By.tagName("button"));
        pause(3);
        //call the doc upload function to upload the template document
        docUpload("//input[@id='documentUploaderForm:fileUploadId_input']", filePath);
    }

    /**
     * This method will select the correct table and select the cell containing the name of the user[chiefFName] based on whether the pagination is seen in the page or not
     *
     * @param chiefFName
     * @throws Exception
     * @author vshivaraman
     */
    public void userSelectPagination(String chiefFName) throws Exception {
        try {
            if (driver.findElement(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId_paginator_top']")).isDisplayed()) {
                ReusableFunctions.waitAndselectCellFromTable(chiefFName, "//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId']/div[3]/table");
            }
        } catch (Exception e) {
            ReusableFunctions.waitAndselectCellFromTable(chiefFName, "//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId']/div[2]/table");
        }
    }

    /**
     * This method will add a branch chief to the branch specified under the
     * specified division and CAF
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param chiefFName
     * @param chiefLName
     * @throws Exception
     * @author vshivaraman
     */
    public void addBranchChief(String cafName, String divisionName, String branchName, String chiefFName, String chiefLName) throws Exception {
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;

        //Get the table path
        String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId']/div[2]/table";

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Expand Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // Select the Branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']"));

        // Get the list of web element in the table
        webelementLst = driver.findElements(By.xpath(tableXPath));

        // Get the web element within the list which contains the value passed
        sub = ReusableFunctions.selectElementContains(webelementLst, branchName);

        // Get the List of web elements that contains the tag tr in the table
        cells = sub.findElements(By.tagName("tr"));

        // click on the button contained in the cell within the row that
        // contains the value sent
        ReusableFunctions.selectElementContains(cells, branchName).findElements(By.tagName("button")).get(0).click();

        // filter by last name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"), chiefLName);

        //Call the pagination method to select the correct table based on whether pagination is seen in the page or not
        userSelectPagination(chiefFName);

        //Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        System.out.println(chiefFName + "" + chiefLName + " has been added to the branch " + branchName);
        System.out.println();

    }

    /**
     * This method will add a team chief specified to the team specified under
     * the branch, division and CAF specified
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @param chiefFName
     * @param chiefLName
     * @throws Exception
     * @author vshivaraman
     */
    public void addTeamChief(String cafName, String divisionName, String branchName, String teamName, String chiefFName, String chiefLName) throws Exception {
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;

        //Get the table path
        String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId']/div[2]/table";

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Expand Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // Expand branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        // Select the Team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + teamName + "']"));

        // Get the list of web element in the table
        webelementLst = driver.findElements(By.xpath(tableXPath));

        // Get the web element within the list which contains the value passed
        sub = ReusableFunctions.selectElementContains(webelementLst, teamName);

        // Get the List of web elements that contains the tag tr in the table
        cells = sub.findElements(By.tagName("tr"));

        // click on the button contained in the cell within the row that
        // contains the value sent
        ReusableFunctions.selectElementContains(cells, teamName).findElements(By.tagName("button")).get(0).click();

        //Filter by last name
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchLastName:filter']"), chiefLName);

        //Call the pagination method to select the correct table based on whether pagination is seen in the page or not
        userSelectPagination(chiefFName);

        //Select Save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        System.out.println();
    }

    /**
     * This method will delete the team member specified from the team specified
     * under branch,division and CAF specified based on the parameters passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @param teamMemberFName
     * @param teamMemberLName
     * @author vshivaraman
     */
    public void deleteTeamMember(String cafName, String divisionName, String branchName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;


        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Expand Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        //Expand branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        //Select the team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + teamName + "']"));

        //Right click on the team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']");

        //Select details link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));

        // Get the list of web element in the table
        webelementLst = driver.findElements(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table"));

        // Get the web element within the list which contains the value passed
        sub = ReusableFunctions.selectElementContains(webelementLst, teamMemberFName + " " + teamMemberLName);

        // Get the List of web elements that contains the tag tr in the table
        cells = sub.findElements(By.tagName("tr"));

        // click on the delete button contained in the cell within the row that
        // contains the value sent
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndselectCellFromTable(containsKey, tableXPath);
        WebElement rowdeleteUser = ReusableFunctions.selectElementContains(cells, teamMemberFName + " " + teamMemberLName);
        WebElement deleteUser = rowdeleteUser.findElement(By.xpath("//button[@title='Disassociate User']/span"));
        pause(2);
        deleteUser.click();
        pause(2);


        //Verify that the user is deleted
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:HierarchyEntityUserListTabId:details_data']", "No Users Found");


        //Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select Yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");
        WaitingToLoad();

        //Verify growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
        pause(2);
        System.out.println(teamMemberFName + " " + teamMemberLName + " has been disassociated from " + teamName);
        System.out.println();

        //Click on CAF tree tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));
    }

    /**
     * This method will delete the association between the team chief and the
     * team specified under the branch, division and CAF specified based on the
     * parameters passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param teamName
     * @param teamChiefFName
     * @author vshivaraman
     */
    public void deleteTeamChief(String cafName, String divisionName, String branchName, String teamName, String teamChiefFName) {
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;

        //Get the table path
        String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId_data']";

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Expand Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span[2]"));

        // Expand branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[3]"));

        // Select the Team
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + teamName + "']"));

        // Get the list of web element in the table
        webelementLst = driver.findElements(By.xpath(tableXPath));

        // Get the web element within the list which contains the value passed
        sub = ReusableFunctions.selectElementContains(webelementLst, teamName);

        // Get the List of web elements that contains the tag tr in the table
        cells = sub.findElements(By.tagName("tr"));

        // click on the user look up button contained in the cell within the row that
        // contains the value sent
        ReusableFunctions.selectElementContains(cells, teamName).findElements(By.tagName("button")).get(0).click();

        //Select clear selection button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Clear Selection");
        WaitingToLoad();

        //Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");

        System.out.println(teamChiefFName + " has been removed from " + teamName);
        System.out.println();

        WaitingToLoad();
        // com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));


    }

    /**
     * This method will remove the branch chief association from the branch
     * specified under the division and CAF specified based on the parameter
     * passed
     *
     * @param cafName
     * @param divisionName
     * @param branchName
     * @param branchChiefFName
     * @author vshivaraman
     */
    public void deleteBranchChief(String cafName, String divisionName, String branchName, String branchChiefFName) {
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;

        //Get table path
        String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId_data']";

        //Select manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Expand Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//span[parent::td[contains(text(),'" + divisionName + "')] and contains(@class,'ui-treetable-toggler ui-icon ui-icon-triangle-1-e ui-c')]"));//Changed in 5.4 to ui-treetable-toggler ui-icon ui-icon-triangle-1-e ui-c  span[parent::td[contains(text(),'DIVISION-Division Hierarchy Test')] and contains(@class,'ui-treetable-toggler ui-icon ui-c ui-icon-triangle-1-e')]

        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ divisionName +"']/span[2]"));

        // Select branch
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']"));

        // Get the list of web element in the table
        webelementLst = driver.findElements(By.xpath(tableXPath));

        // Get the web element within the list which contains the value passed
        sub = ReusableFunctions.selectElementContains(webelementLst, branchName);

        // Get the List of web elements that contains the tag tr in the table
        cells = sub.findElements(By.tagName("tr"));

        // click on the user look up button contained in the cell within the row that
        // contains the value sent
        ReusableFunctions.selectElementContains(cells, branchName).findElements(By.tagName("button")).get(0).click();

        //Click on clear selection button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='clearSelectionButton']"));
        WaitingToLoad();

        //Select save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");

        System.out.println(branchChiefFName + " has been removed from " + branchName);
        System.out.println();

        WaitingToLoad();
        // com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));

    }

    /**
     * This method will delete the division chief association between the chief
     * user and the division specified under the CAF specified based on the
     * parameters passed
     *
     * @param cafName
     * @param divisionName
     * @param divisionChiefFName
     * @author vshivaraman
     */
    public void deleteDivisionChief(String cafName, String divisionName, String divisionChiefFName) {
        List<WebElement> webelementLst = null;
        WebElement sub = null;
        List<WebElement> cells = null;

        //Get the table path
        String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId_data']";

        //Select the manage hierarchy link
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
        pause(5);
        //Select the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + cafName + "']"));
        pause(2);

        // Expand the CAF
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='" + cafName + "']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

        //Select the Division
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']"));

        // Get the list of web element in the table
        webelementLst = driver.findElements(By.xpath(tableXPath));

        // Get the web element within the list which contains the value passed
        sub = ReusableFunctions.selectElementContains(webelementLst, divisionName);

        // Get the List of web elements that contains the tag tr in the table
        cells = sub.findElements(By.tagName("tr"));

        // click on the user look up button contained in the cell within the row that
        // contains the value sent
        ReusableFunctions.selectElementContains(cells, divisionName).findElements(By.tagName("button")).get(0).click();

        //Click on clear selection button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='clearSelectionButton']"));
        WaitingToLoad();

        //Select Save button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");

        //Select yes button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");

        //Verify that the growl message is seen
        pause(2);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");

        System.out.println(divisionChiefFName + " has been removed from " + divisionName);
        System.out.println();

        WaitingToLoad();
        // com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));

    }

    public void addInvestigationSubjectAction(String invType, String yearFourdigit, String month3Char, String closeDate, String invAgency, String agencyCaseNumber, String docName, String docType) {
        //click on the subject action button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:subjtabview:subactions_button']"));//"//*[@id='majorTabPanel:subjtabview:subactions_button']"

        //Select Add investigation option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:subjtabview:subActionsMenu']//a/span[text()='Add New Investigation']"));

        //Click on the label for investigation type
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='addInvestigationForm:addInvestigationTypeId_label']"));

        //click at the investigation type option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='addInvestigationForm:addInvestigationTypeId_panel']//li[text()='" + invType + "']"));
        //click on the close date calendar
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//span[@id='addInvestigationForm:addInvestigationCloseDateId']/button"));
        // Select year
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//select[contains(@class,'ui-datepicker-year')]/option[@value='" + yearFourdigit + "']"));

        //Select the month
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//select[contains(@class,'ui-datepicker-month')]/option[text()='" + month3Char + "']"));

        //Select date
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + closeDate + "']"));

        //Click on the investigation agency drop down
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='addInvestigationForm:addInvestigationAgencyId_label']"));

        //select the investigation agency option in the list
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='addInvestigationForm:addInvestigationAgencyId_panel']//li[text()='" + invAgency + "']"));

        //click on the agency case number text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input"));

        //enter the agency case number
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input"), agencyCaseNumber);

        //click on Add document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Add Document");

        //call the doc upload function

        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), docType, "For Add Investigation", "test");

        //click on the add investigation button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Add New Investigation");

        //Verify growl message
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Investigation Created Successfully");
        pause(5);

    }

    public void addInvestigationDocumentToExistingInnvestigationSubjectDetailsPage(String ssn, String agencyCaseNumber, String docType, String docName, String docDescription) {
        //Search fo rhte subject
        searchSubjectCATS(ssn);

        //Expand the row of the specific agencycase
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']//td[preceding-sibling::td/span[contains(text(),'" + agencyCaseNumber + "')]]/div"));
        //ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//tbody[@id='majorTabPanel:subjtabview:subjectInvestigations_data']", By.tagName("div"));

        //Click on the Add investigation document button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='Add Investigation Document']"));//Add Investigation Document

        //call the document upload function
        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), docType, docName, docDescription);


    }

    public void addInvestigationDocumentToExistingInnvestigationCasetDetailsPage(String caseID, String agencyCaseNumber, String docType, String docName, String docDescription) {
        //Search fo rhte subject
        searchCase(caseID);
        pause(2);
        //Expand the row of the specific agencycase
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']//td[preceding-sibling::td[contains(text(),'" + agencyCaseNumber + "')]]/div"));
        //ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']/div/table", By.xpath("./td[9]/div"));

        //Click on the Add investigation document button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,':addInvButton')]"));

        //call the document upload function
        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), docType, docName, docDescription);
        pause(3);


    }

    public void addInvestigationCaseDetailsInvestigationAction(String invType, String yearFourdigit, String month3Char, String closeDate, String invAgency, String agencyCaseNumber, String docName, String docType) {
        //click on the investigation action button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:InvButton_button']"));//"//*[@id='majorTabPanel:subjtabview:subactions_button']"

        //Select Add investigation option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:invActionMenu1']/span[text()='Add New Investigation']"));

        //Click on the label for investigation type
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='addInvestigationForm:addInvestigationTypeId_label']"));

        //click at the investigation type option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='addInvestigationForm:addInvestigationTypeId_panel']//li[text()='" + invType + "']"));
        //click on the close date calendar
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//span[@id='addInvestigationForm:addInvestigationCloseDateId']/button"));
        // Select year
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]/option[@value='" + yearFourdigit + "']"));

        //Select the month
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]/option[text()='" + month3Char + "']"));

        //Select date
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + closeDate + "']"));

        //Click on the investigation agency drop down
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='addInvestigationForm:addInvestigationAgencyId_label']"));

        //select the investigation agency option in the list
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='addInvestigationForm:addInvestigationAgencyId_panel']//li[text()='" + invAgency + "']"));

        //click on the agency case number text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='addInvestigationForm:addInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input"));
//				WebElement element =com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndfindCellFromTableBasedOnvalueOfAnotherCell("Agency Case Number","//table[@id='majorTabPanel:caseDetailsPanel:newCaseInvestigationForm:newCaseInvestigatioGrid']",By.tagName("input"));
//				element.sendKeys(agencyCaseNumber);


        //enter the agency case number
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//table[@id='addInvestigationForm:addInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input"), agencyCaseNumber);

        //click on Add document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='addInvestigationForm:addInvestigationGridId']//button/span[contains(text(),'Add Document')]"));

        //call the doc upload function

        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), docType, "For Add Investigation", "test");

        //click on the add investigation button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//form[@id='addInvestigationForm']//button/span[contains(text(),'Add New Investigation')]"));

        //Verify growl message
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Investigation Created Successfully");
        pause(15);

    }


    public void addExistingInvestigation(String caseID, String existingInvestigation) {

        searchCase(caseID);
        pause(2);

        //click on the investigation action button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:InvButton_button']"));//"//*[@id='majorTabPanel:subjtabview:subactions_button']"

        //Select Add existing investigation option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:invActionMenu2']/span[text()='Add Existing Investigation']"));////a[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:invActionMenu2']/span


        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:newInvestigationType_label']"));


        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:newInvestigationType_panel']//li[@data-label='" + existingInvestigation + "']"));////div[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:newinvestigationType_panel']/div/ul/li[2

        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//form[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm']//button/span[text()='Save']"));
        pause(5);


    }

    public void updateInvestigationFromSubjectDetails(String ssn, String agencyCaseNumber, String invType, String yearFourdigit, String month3Char, String closeDate, String invAgency, String agencyCaseNumberNew) {
        searchSubjectCATS(ssn);

        ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//div[@id='majorTabPanel:subjtabview:subjectInvestigations']/div/table", By.tagName("button"));
        //Click on the label for investigation type
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='updateInvestigationFormId:updateInvestigationTypeId_label']"));

        //click at the investigation type option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='updateInvestigationFormId:updateInvestigationTypeId_panel']//li[text()='" + invType + "']"));
        //click on the close date calendar
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//span[@id='updateInvestigationFormId:updateInvestigationCloseDateId']/button"));
        // Select year
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//select[contains(@class,'ui-datepicker-year')]/option[@value='" + yearFourdigit + "']"));

        //Select the month
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//select[contains(@class,'ui-datepicker-month')]/option[text()='" + month3Char + "']"));

        //Select date
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + closeDate + "']"));

        //Click on the investigation agency drop down
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='updateInvestigationFormId:updateInvestigationAgencyId_label']"));

        //select the investigation agency option in the list
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='updateInvestigationFormId:updateInvestigationAgencyId_panel']//li[text()='" + invAgency + "']"));
        pause(2);
        //click on the agency case number text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='updateInvestigationFormId:updateInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input[@value='" + agencyCaseNumber + "']"));

        //clear the text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//table[@id='updateInvestigationFormId:updateInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input[@value='" + agencyCaseNumber + "']"));

        //enter the new agency case number
        WebElement element = com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndfindCellFromTableBasedOnvalueOfAnotherCell("Agency Case Number", "//table[@id='updateInvestigationFormId:updateInvestigationGridId']", By.tagName("input"));
        element.sendKeys(agencyCaseNumberNew);
        //Click the Update investigation button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='updateInvestigationFormId:updateInvestigationUpdateButtonId']"));

        //Investigation Updated Successfully
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Investigation Updated Successfully");
        pause(15);

    }


    public void updateInvestigationFromCaseDetails(String caseID, String agencyCaseNumber, String invType, String yearFourdigit, String month3Char, String closeDate, String invAgency, String agencyCaseNumberNew) {
        searchCase(caseID);
        pause(2);
        ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']/div/table", By.tagName("button"));
        //Click on the label for investigation type
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='updateInvestigationFormId:updateInvestigationTypeId_label']"));

        //click at the investigation type option
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='updateInvestigationFormId:updateInvestigationTypeId_panel']//li[text()='" + invType + "']"));
        //click on the close date calendar
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//span[@id='updateInvestigationFormId:updateInvestigationCloseDateId']/button"));
        // Select year
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]/option[@value='" + yearFourdigit + "']"));

        //Select the month
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]/option[text()='" + month3Char + "']"));

        //Select date
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + closeDate + "']"));

        //Click on the investigation agency drop down
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='updateInvestigationFormId:updateInvestigationAgencyId_label']"));

        //select the investigation agency option in the list
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='updateInvestigationFormId:updateInvestigationAgencyId_panel']//li[text()='" + invAgency + "']"));

        //click on the agency case number text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='updateInvestigationFormId:updateInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input[@value='" + agencyCaseNumber + "']"));

        //clear the text box
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//table[@id='updateInvestigationFormId:updateInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input[@value='" + agencyCaseNumber + "']"));

        //enter the agency case number
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//table[@id='updateInvestigationFormId:updateInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input"), agencyCaseNumberNew);
        //Click the Update investigation button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='updateInvestigationFormId:updateInvestigationUpdateButtonId']"));

        //Investigation Updated Successfully
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Investigation Updated Successfully");
        pause(10);
        //verify that the investigtion is updated
        //WebElement element1 =ReusableFunctions.waitAndfindCellFromTableBasedOnvalueOfAnotherCell("NACLC",  "//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']/div/table", By.xpath(("./td[6]")));
        WebElement element1 = ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//td[preceding-sibling::td[contains(text(),'" + invAgency + "')]]"));
        String newAgencyNumber = element1.getText();
        if (newAgencyNumber.equalsIgnoreCase(agencyCaseNumberNew)) {
            System.out.println("New updated agency number is seen correctly as " + agencyCaseNumberNew + "\n");
        } else {
            System.out.println("New updated agency number is NOT seen correctly as " + agencyCaseNumberNew + "\n");
        }

    }

    public void editInvestigationDoc(String ssn, String agencyCaseNumber, String docName, String newName, String docDescription, String newDescription, String docType, String newDocType) {
        //Search for hte subject
        searchSubjectCATS(ssn);

        //Expand the row of the specific agencycase
        ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//div[@id='majorTabPanel:subjtabview:subjectInvestigations']/div/table", By.tagName("div"));


        //Verify if the table has the doc
        ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:subjtabview:subjectInvestigations_data']", docName);

        //click on the edit for specific doc
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[preceding-sibling::td//a[contains(text(),'" + docName + "')]]/div/span"));
        //ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(docName, "//tr[@class='ui-expanded-row-content ui-widget-content']/td//table", By.xpath("./td[4]/div/span"));
        //ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(docName, "//*[@id='majorTabPanel:subjtabview:subjectInvestigations:0:investigationDocumentDetail_data']", By.xpath("//td[4]/div/span"));

        //clear the document name text
        ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//td[1]//div[@class='ui-cell-editor-input']//textarea[text()='" + docName + "']"));
        //pause(2);

        //Enter the new document name

        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//textarea[contains(concat(' ',normalize-space(@class),' '),'ui-state-focus')]"), newName);

        //clear the Document description text
        ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//td[2]//div[@class='ui-cell-editor-input']//textarea[text()='" + docDescription + "']"));

        //	Enter the new document description
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//textarea[contains(concat(' ',normalize-space(@class),' '),'ui-state-focus')]"), newDescription);

        //	Click on the Document type drop down
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[3]//div[@class='ui-cell-editor-input']//label[text()='" + docType + "']"));


        //	Select the new document type
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/div[contains(@id,'1:cdDocumentTypeMenu_panel')]//li[@data-label='" + newDocType + "']"));

        //Save the changes
        ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(newName, "//tr[@class='ui-expanded-row-content ui-widget-content']/td//table", By.xpath("./td[4]/div/span[contains(@class,'ui-icon ui-icon-check')]"));


        //Verify growl message
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Document successfully updated.");
        pause(5);
    }

    public void editInvestigationDocCaseDetailsPage(String caseID, String agencyCaseNumber, String docName, String newName, String docDescription, String newDescription, String docType, String newDocType) {
        //Search for hte subject
        searchCase(caseID);
        pause(2);
        // Expand the row of the specific agencycase
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']//td[preceding-sibling::td[contains(text(),'" + agencyCaseNumber + "')]]/div"));
        //	ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']/div/table", By.xpath("./td[9]/div"));

        pause(2);
        //Verify if the table has the doc
        ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId_data']", docName);

        //click on the edit for specific doc
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[contains(@id,':investigationCaseDocumentDetail_data')]//td[preceding-sibling::td//a[contains(text(),'" + docName + "')]]/button"));
        //ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(docName, "//tr[@class='ui-expanded-row-content ui-widget-content custom']/td//table", By.xpath("./td[4]/div/span"));
        //ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(docName, "//*[@id='majorTabPanel:subjtabview:subjectInvestigations:0:investigationDocumentDetail_data']", By.xpath("//td[4]/div/span"));

        //clear the document name text
        ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//td[preceding-sibling::td[contains(text(),'Document Name')]]/input[contains(@value,'" + docName + "')]"));
        //pause(2);

        //Enter the new document name

        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td[preceding-sibling::td[contains(text(),'Document Name')]]/input"), newName);

        //clear the Document description text
        ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//td[preceding-sibling::td[contains(text(),'Document Description')]]/textarea[contains(text(),'" + docDescription + "')]"));

        //	Enter the new document description
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td[preceding-sibling::td[contains(text(),'Document Description')]]/textarea"), newDescription);

        //	Click on the Document type drop down
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='documentUploaderForm:editAddDocumentMessagesDocType_label' and contains(text(),'" + docType + "')]"));//documentUploaderForm:editAddDocumentMessagesDocType_label


        //	Select the new document type
//		String rows =ReusableFunctions.waitAndGetNumberOfRowsInATable("//tbody[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId_data']//tr[@class='ui-expanded-row-content ui-widget-content custom']/td//table/tbody/tr[*]");
//		int rowid = Integer.parseInt(rows)-1;
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']//li[@data-label='" + newDocType + "']"));//maintenace changes based on the number of investigations

        //Save the changes
        //ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(newName, "//tr[@class='ui-expanded-row-content ui-widget-content custom']/td//table", By.xpath("./td[4]/div/span[contains(@class,'ui-icon ui-icon-check')]"));

        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='documentUploaderForm:documentUploadUpdateButtonId']"));
        //Verify growl message
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Document successfully updated.");

        pause(5);
    }

    public void sendForReview(String caseID, String reviewCategory, String firstName, String notes) throws Exception {
        //Search for he case based on case id
        searchCase(caseID);
        pause(2);
        //Click on review tab
        ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Review"));

        //click on the send for review button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseReviewTableId:SendForReviewButtonId']"));

        //click at the radio button for review category
        ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(reviewCategory, "//table[@id='sendForReviewPopupForm:selectReviewTypeRadio']", By.xpath("//label[text()='" + reviewCategory + "']"));

        if (reviewCategory.equalsIgnoreCase("Manually Assign")) {
            ReusableFunctions.waitUntilElementExistsAndClick(By.id("sendForReviewPopupForm:buttonLookupManualAssignedReviewId"));
            ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchFirstName:filter']"));
            ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchFirstName:filter']"), firstName);
            userSelectPagination(firstName);
            pause(2);
        }

        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//textarea[@id='sendForReviewPopupForm:sendForReviewNote']"), notes);
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='sendForReviewPopupForm:sendForCaseReviewButton']"));
        pause(2);
    }


    public void generateLetter(String caseID, String letterName) {
        //Search for the case
        searchCase(caseID);

        //click on the documetn tab
        ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Documents"));

        String parentWindowHandler = driver.getWindowHandle();
        System.out.println("parent handel is : " + parentWindowHandler);
        System.out.println("parentTitle is " + driver.getTitle());

        //click on the pencil icon for the letter
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:subjtabview1:templetterForm:lettertable']//td[preceding-sibling::td/span[contains(text(),'" + letterName + "')]]//span[@class='ui-button-icon-left ui-icon ui-c ui-icon-pencil']"));//Request for Medical Evaluation Letter
        pause(30);

        //switch to new tab
        Set<String> set = driver.getWindowHandles();
        int s = set.size();
        System.out.println("set size is" + s);
        Iterator<String> ite = set.iterator();


        String popupHandle = ite.next().toString();
        System.out.println("handle 1 is :" + popupHandle);
        if (popupHandle.equalsIgnoreCase(parentWindowHandler)) {
            popupHandle = ite.next().toString();
            System.out.println("handle 2 is :" + popupHandle);
            driver.switchTo().window(popupHandle);
            pause(5);
        } else {
            driver.switchTo().window(popupHandle);
            pause(5);
        }
        String letterWindow = driver.getTitle().toString();
        System.out.println("Letter window title is " + letterWindow);
        driver.manage().window().maximize();

        //Click on generate button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='RFALetterForm:rfaLettertabView:undoButtonForLetter']"));

        //click view pdf

        //verify pdf is generated

        driver.close();
        driver.switchTo().window(parentWindowHandler);


    }

    public void sendRFA(String caseID, String rfaName, String docType, String docName, String docDescription) throws Exception {
        searchCase(caseID);
        pause(5);

        // Click on the RFA tab
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFA"));

        // Click Actions button
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button']"));

        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[contains(@id,':rfaMenu')]/span[contains(text(),'" + rfaName + "')]"));

        // Click Upload document button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@class,'documentUploadButton')]"));

        // Calls the method docUpload to upload the document from system file
        docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), docType, docName, docDescription);
        pause(5);

        // Click Send button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("RFARequestForm:sendRFA"));

        // Verify the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "sent");
        pause(15);


    }

    public void generateRequestforLetter(String caseID, String letterName) throws Exception {

        generateLetter(caseID, letterName);

    }

    public void verifyRFAPopUpInfo(String[] rfaFields) throws Exception {


        for (String rfaField : rfaFields) {
            ReusableFunctions.waitAndVerify_IfContains("//form[@id='RFARequestForm']", rfaField);
        }


    }

    public void verifyRFAPopUpErrorMessages(String rfaErrormessage, String[] errormessages) throws Exception {

        //click on send
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='RFARequestForm:sendRFA']"));
        ReusableFunctions.waitAndVerify_IfContains("//div[@id='RFARequestForm:RFARequestFor']//div[contains(@class,'ui-messages-error')]", "Provide Request for Other Agency Files Letter document.");
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='Upload Document']"));
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='documentUploaderForm:documentUploadAddButtonId']"));

        for (String errorMessage : errormessages) {
            ReusableFunctions.waitAndVerify_IfContains("//div[@id='documentUploaderForm:documentUploaderFormMessagesId']//li", errorMessage);
        }


        //click cancel
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='documentUploaderForm:documentUploadCancelButtonId']"));
        //click on close window
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='RFARequestForm:closeWindowId']"));
        //click on the review tab
        ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Review"));

    }

    public void verifyRFACancel(String caseID, String documentType) throws Exception {

        searchCase(caseID);
        //click RFA tab
        ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFAs"));
        //Click on the row with the RFA
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']//td/span[contains(text(),'" + documentType + "')]"));

        //Click on Cancel req
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='RFARequestForm:cancelRFA']"));

        //
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='cancelRfaConfirmYes']"));

        pause(15);


        searchCase(caseID);

        //click RFA tab
        ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFAs"));
        String status = ReusableFunctions.waitAndGetText(By.xpath("//td[preceding-sibling::td/span[contains(text(),'" + documentType + "')]]/span"));

        if (status.equalsIgnoreCase("Canceled")) {
            System.out.println("Request for RFA has been Canceled \n");
        } else {
            System.out.println("Request for RFA was NOT canceled \n");
        }


    }

    public String getClaimDate(String caseID, String documentType) throws Exception {
        searchCase(caseID);
        ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFA"));
        String cliamDate = ReusableFunctions.waitAndGetText(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']//td[preceding-sibling::td/span[contains(text(),'" + documentType + "')] and preceding-sibling::td/span[contains(text(),'In Process')]]/span[contains(@id,':rfaRequestSmoClaimDate')]"));
        return cliamDate;

    }

    public void verifyHistory(String caseID, String action) throws Exception {
        //Verify entry for action
        ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']", action);

        String firstName = ReusableFunctions.waitAndGetText(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']//td[preceding-sibling::td/span[contains(text(),'" + action + "')]]/span[contains(@id,':chPersonFirstName')]"));
        String lastName = ReusableFunctions.waitAndGetText(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']//td[preceding-sibling::td/span[contains(text(),'" + action + "')]]/span[contains(@id,':chPersonLastName')]"));
        String date = ReusableFunctions.waitAndGetText(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']//td[preceding-sibling::td/span[contains(text(),'" + action + "')]]/span[contains(@id,':chActionDate')]"));
        System.out.println("The Case History subtab will display the Action as :" + action + " Action Taken By as :" + firstName + " " + lastName + " and Date as : " + date);

    }

    public void verifyEarnedPoints() throws Exception {
        ReusableFunctions.waitAndVerify_IfContains("//table[@id='prodPanel:prodDashboard2']", "Earned");
        String productivityPoints = ReusableFunctions.waitAndGetText(By.xpath("//table[@id='prodPanel:prodDashboard2']//td[preceding-sibling::td/label[contains(text(),'Earned')]]/label"));
        System.out.println("Productivity points earned is : " + productivityPoints);
    }

    public void initiateRequestforRFA(String rfaName) throws Exception {

        //click on the RFA tab
        ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFAs"));

        pause(5);

        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button']"));

        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[contains(@id,'rfaMenu')]/span[contains(text(),'" + rfaName + "')]"));

    }

    public void claimTask(String firstName, String lastName) throws Exception {
        //click on task inbox
        ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));
        // Select the task
        String name = firstName + " " + lastName;
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[following-sibling::td/span[contains(text(),'" + name + "')]]/span"));

        // Click claim button
        com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,'PortalClaim')]"));

        // Verify the growl message is seen
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "claimed");
        pause(5);

    }


    // **********************************************************************************


    // -----------------------------------------------------------------------------


    // ************WWINDOW HANDLES FOR NEW TABS****************************************


}
