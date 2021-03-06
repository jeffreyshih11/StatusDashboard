package com.iworkscorp.dashboard.hudson;


import org.openqa.selenium.By;

/**
 * This class provides all reusable functions in JVS application
 *
 * @author vshivaraman
 */
public class JVSReusableFunctions extends TestBase {

    private ReusableFunctions reusable = new ReusableFunctions();
    public static final String MAJOR_TAB_PANEL = "majorTabPanel:";

    /**
     * This method will open the JVS URL, log in to JVS using the user id passed
     * to it.
     *
     * @param user
     * @author vshivaraman
     */
    public void loginToJVS(String user) throws Exception {
        driver.navigate().to(CONFIG.getProperty("QA_CP_Url"));
        ReusableFunctions.waitAndLoginWithUser(user);
    }

    public void loginToJvsAsOne() throws Exception {
        loginToJVS("1");
    }

    /**
     * log into JVS as '1' on the specified environment
     *
     * @param env
     * @throws Exception
     */
    public void loginToJvsAsOneEnv(String env) throws Exception {
        loginToJVS("1", env);
    }

    /**
     * log into JVS as the specified user on the specified environment
     *
     * @param user
     * @param env
     * @throws Exception
     */
    public void loginToJVS(String user, String env) throws Exception {
        String login_url = null;
        if (env.equals("DEV")) {
            login_url = "DEV_CP_Url";

        } else if (env.equals("QA")) {
            login_url = "QA_CP_Url";

        } else if (env.equals("GAT")) {
            login_url = "GAT_CP_Url";

        } else if (env.equals("LOCAL")) {
            login_url = "LOCAL_CP_Url";

        } else if (env.equals("Baseline")) {
            login_url = "BASELINE_CP_Url";

        } else if (env.equals("DEMO")) {
            login_url = "DEMO_CP_Url";

        } else if (env.equals("UAT")) {
            login_url = "UAT_CP_Url";

        }
        driver.navigate().to(CONFIG.getProperty(login_url));
        ReusableFunctions.waitAndLoginWithUser(user);
    }

    /**
     * This method will log out the user passed from JVS
     *
     * @author vshivaraman
     */

    public void jvsLogout() {

        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Logoff"));
        // System.out.println("Logged out of JVS");
       com.iworkscorp.dashboard.hudson.ReusableFunctions.getObjectByLinktext("Logoff").isEnabled();
        driver.navigate().to(CONFIG.getProperty("QA_CP_Url1"));
        WaitingToLoad();
        driver.navigate().to(CONFIG.getProperty("QA_CP_Url1"));
        pause(3);
    }


    public void jvsLogout(String env) {
        String logout_url = null;
        if (env.equals("DEV")) {
            logout_url = "DEV_CP_Url1";

        } else if (env.equals("QA")) {
            logout_url = "QA_CP_Url1";

        } else if (env.equals("GAT")) {
            logout_url = "GAT_CP_Url1";

        } else if (env.equals("LOCAL")) {
            logout_url = "LOCAL_CP_Url1";

        } else if (env.equals("Baseline")) {
            logout_url = "BASELINE_CP_Url1";

        } else if (env.equals("DEMO")) {
            logout_url = "DEMO_CP_Url1";

        } else if (env.equals("UAT")) {
            logout_url = "UAT_CP_Url1";

        }

       com.iworkscorp.dashboard.hudson.ReusableFunctions.getObjectByLinktext("Logoff").isEnabled();
        driver.navigate().to(CONFIG.getProperty(logout_url));
        WaitingToLoad();
        driver.navigate().to(CONFIG.getProperty(logout_url));
        pause(3);
    }

    /**
     * This method will search for subjects based on SSN in JVS
     *
     * @param ssn
     * @author vshivaraman
     */
    public void subjectSearch(String ssn) throws Exception {
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath(JVSProperties.SubjectSearch.getProperty()));
        pause(2);
        // waitUntilElementExistsAndClick(By.xpath(JVSProperties.subjectSearch.getProperty()));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath(JVSProperties.SubjectSearch.getProperty()), ssn);

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SubjectSearchbtn.getProperty()));
        pause(5);
    }

    /**
     * This method will search for SSN in User tab JVS
     *
     * @param ssn
     * @author igopinathan
     */
    public void userSearch(String ssn) throws Exception {

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.id(JVSProperties.SearchUser.getProperty()));
        pause(2);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.id(JVSProperties.SearchUser.getProperty()), ssn);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id(JVSProperties.SearchUserButton.getProperty()));

    }

    /**
     * This method will search for SSN in User tab and create User in the User
     * Detail tab in JVS
     *
     * @param smoType
     * @param roleType
     * @author igopinathan
     */
    public void createUser(String smoType, String roleType) throws Exception {

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id(JVSProperties.SystemGeneratePassword.getProperty()));

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(smoType));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.ArrowRight1.getProperty()));

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(roleType));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.ArrowRight2.getProperty()));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id(JVSProperties.SaveRole.getProperty()));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id(JVSProperties.SaveRole.getProperty()));

        // PortalLogout();

    }

    public void clearAndEnterText(String fieldId, String textToInput) {
        final By id = By.id(fieldId);
        ReusableFunctions.waitUntilElementExistsAndClear(id);
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(id, textToInput);
    }

    public void selectDate(final String dateId, final String day) {
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='" + dateId + "']/button"));
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + day + "']"));
    }

    public void selectFromDropdown(final String dropDownId, final String dropDownEntry) {
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='" + dropDownId + "_label']"));
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='" + dropDownId + "_panel']//li[@data-label='" + dropDownEntry + "']"));
    }

    /**
     * This method will create users based on the ssn,username,smo and role
     * passed to it.Note: username should be between 8-10 characters.
     *
     * @param ssn
     * @param userName
     * @param smo
     * @param role
     * @throws Exception
     * @author vshivaraman
     */
    public void createUser(String ssn, String userName, String smo, String role) throws Exception {
        // Search for the user by SSN in User management section
        userSearch(ssn);
        // Click on generate password button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Generate Password");
        pause(1);
        // Click on user ID field
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/input[@id='majorTabPanel:regid']"));
        // clear the automatically generated user name
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//input[@id='majorTabPanel:regid']"));
        pause(1);
        // enter the user name
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/input[@id='majorTabPanel:regid']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:regid']"), userName);
        // Select the SMO
       com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick(By.xpath("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']//li[@data-item-label='" + smo + "']"));
        pause(3);
        // Select the role
       com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick(By.xpath("//*[@id='majorTabPanel:jvsUserDetailRolePickList']//li[@data-item-label='" + role + "']"));
        pause(3);
        // Select all permissions allowed for the role
       com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick(By.xpath("//*[@id='majorTabPanel:jvsUserDetailPermPickList']//button[@title='Add All']"));
        pause(1);
        // click save
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");
        WaitingToLoad();
        // Verify that the growl message is seen
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully.");
        pause(10);

    }

    /**
     * This method will create user based on the ssn,smo and role
     * passed to it and return the user name.
     *
     * @param ssn
     * @param smo
     * @param role
     * @throws Exception
     * @author vshivaraman
     */
    public String createUser(String ssn, String smo, String role) throws Exception {
        // Search for the user by SSN in User management section
        userSearch(ssn);
        // Click on generate password button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Generate Password");
        pause(1);
        // get the auto generated username
        String userName =com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndGetValue(By.xpath("//*/input[@id='majorTabPanel:regid']"));

        // Select the SMO
       com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick(By.xpath("//*[@id='majorTabPanel:jvsUserDetailSmoPickList']//li[@data-item-label='" + smo + "']"));
        pause(3);
        // Select the role
       com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick(By.xpath("//*[@id='majorTabPanel:jvsUserDetailRolePickList']//li[@data-item-label='" + role + "']"));
        pause(3);
        // Select all permissions allowed for the role
       com.iworkscorp.dashboard.hudson.ReusableFunctions.doubleClick(By.xpath("//*[@id='majorTabPanel:jvsUserDetailPermPickList']//button[@title='Add All']"));
        pause(1);
        // click save
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save");
        WaitingToLoad();
        // Verify that the growl message is seen
        pause(10);
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully.");
        return userName;

    }

    /**
     * This function will create a SMO based on org type,org name,activation date,prefix,index and address of SMO location
     *
     * @param orgType
     * @param org
     * @param activationMonth
     * @param activationDate
     * @param activationYear
     * @param smoPrefix
     * @param smoText
     * @param smoIndex
     * @param country
     * @param Street
     * @param city
     * @param state
     * @param zip
     * @throws Exception
     * @author vshivaraman
     */
    public void createSMO(String orgType, String org, String activationMonth, String activationDate, String activationYear, String smoPrefix, String smoText, String smoIndex, String country, String Street, String city, String state, String zip) throws Exception {

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("Organization Type", "//table[@id='majorTabPanel:createsmopanel']", By.tagName("label"));
        pause(5);
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:createsmopanel']//td[preceding-sibling::td/span[contains(text(),'Organization Type')]]//label"));
//		pause(2);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[63]//li[@data-label='" + orgType + "']"));//58 in Demo,QA  GATS- 2/1/16
        pause(10);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("Organization:", "//table[@id='majorTabPanel:createsmopanel']", By.tagName("label"));

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[64]//input[contains(@id,'filter')]"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//div[64]//input[contains(@id,'filter')]"), "Defense Manpower Data Center");
        // 59 in Demo,QA,  GATS - 2/1/16
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[64]//li[text()='" + org + "']"));// 59 in Demo,QA,  GATS - 2/1/16
        pause(10);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("Activation Date", "//table[@id='majorTabPanel:createsmopanel']", By.tagName("button"));
        // Select the month
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]/option[text()='" + activationMonth + "']"));
        // Select year
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]/option[@value='" + activationYear + "']"));

        // Select date
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + activationDate + "']"));


       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//input[@id='majorTabPanel:prefixname']"));
        pause(10);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='majorTabPanel:prefixname']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:prefixname']"), smoPrefix);

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='majorTabPanel:nametext']"));
        pause(10);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//input[@id='majorTabPanel:nametext']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:nametext']"), smoText);

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='majorTabPanel:nameindex']"));
        pause(10);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//input[@id='majorTabPanel:nameindex']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:nameindex']"), smoIndex);

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:jvsCreateSMONextButton']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:CreateSmoLocTable:jvsCreateSMOLOCAddLocationButton']"));
        addSMOLocation(country, Street, city, state, zip);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:jvsCreateSMONextButton']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Save SMO");
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title","SMO saved");

    }

    public void searchSMO(String smoName) throws Exception {
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:securityManagementOfficeName']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:securityManagementOfficeName']"), smoName);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:FindSMO']//button[@title='Search']"));
    }

    /**
     * This function will deactivate a SMO based on the SMO name and deactivation date details passed to it.
     *
     * @param smoName
     * @param reason
     * @param notes
     * @param deactivationMonth
     * @param deactivationYear
     * @param deactivationDate
     * @throws Exception
     * @author vshivaraman
     */
    public void deactivateSMO(String smoName, String reason, String notes, String deactivationMonth, String deactivationYear, String deactivationDate) throws Exception {
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//li[@data-nodetype='child']/span/span[3]//span[text()='" + smoName + "']"));
        pause(5);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndRightClick(By.xpath("//li[@data-nodetype='child']/span/span[3]//span[text()='" + smoName + "']"));
        pause(5);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[@id='majorTabPanel:mitem4']/span[text()='Deactivate SMO']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='smoDeactivateForm:reason_label']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='smoDeactivateForm:reason_panel']/div/ul/li[text()='" + reason + "']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//*[@id='smoDeactivateForm:securityManagementOfficeTerminationNotesText']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='smoDeactivateForm:securityManagementOfficeTerminationNotesText']"), notes);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='smoDeactivateForm:deDate']/button"));
        // Select year
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//select[contains(@class,'ui-datepicker-year')]/option[@value='" + deactivationYear + "']"));

        // Select the month
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//select[contains(@class,'ui-datepicker-month')]/option[text()='" + deactivationMonth + "']"));

        // Select date
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='" + deactivationDate + "']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='smoDeactivateForm:jvsDeactSmoDeactivateButton']"));
        pause(10);
        //com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
        // "Deactivated");

    }

    /**
     * This function will create an organization based on the name,type and
     * address passed to it
     *
     * @param orgName
     * @param orgType
     * @param addressLine1
     * @param city
     * @param state
     * @param country
     * @throws Exception
     * @author vshivaraman
     */
    public void createOrganization(String orgName, String orgType, String addressLine1, String agencyCode, String city, String state, String country) throws Exception {
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='majorTabPanel:CONa']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:CONa']"), orgName);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='majorTabPanel:COTD_label']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:COTD_panel']//li[text()='" + orgType + "']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:cOrgGA']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:cOrgGA']"), agencyCode);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:crOrgStr1']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:crOrgStr1']"), addressLine1);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:crOrgCity']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:crOrgCity']"), city);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:crOrgStat_label']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:crOrgStat_panel']/div/ul/li[text()='" + state + "']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:cOCu0_label']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:cOCu0_panel']/div/ul/li[text()='" + country + "']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Create Organization");
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "An Organization");
        pause(10);

    }

    /**
     * This function will search for an organization based ont eh organization name passed to it
     *
     * @param orgName
     * @throws Exception
     * @author vshivaraman
     */
    public void searchOrganization(String orgName) throws Exception {
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:organizationName']"));

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//*[@id='majorTabPanel:organizationName']"));

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:organizationName']"), orgName);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:FindORG']//button[@title='Search']"));
        pause(10);
        ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:orgResults_data']", orgName);
    }

    /**
     * This function will deactivate an organization based on the organization name passed to it
     *
     * @param orgName
     * @throws Exception
     * @author vshivaraman
     */
    public void deactivateOrganization(String orgName) throws Exception {
        searchOrganization(orgName);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndselectCellFromTable(orgName, By.xpath("//*[@id='majorTabPanel:orgResults']//table"));

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Organization Actions");
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a/span[text()='Deactivate Organization']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Yes");
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully.");
        pause(10);
    }

    /**
     * This function will add SMO location based on the country,street,city,
     * state and zip value passed to it
     *
     * @param country
     * @param street
     * @param city
     * @param state
     * @param zip
     * @throws Exception
     * @author vshivaraman
     */
    public void addSMOLocation(String country, String street, String city, String state, String zip) throws Exception {
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*Country", "//table[@id='majorTabPanel:createfindsmolocattributes']", By.xpath("//*[@id='majorTabPanel:creason2_label']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:creason2_panel']/div/ul/li[text()='" + country + "']"));
        pause(1);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*Street:", "//table[@id='majorTabPanel:createfindsmolocattributes']", By.tagName("input"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:cstreet']"), street);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*City", "//table[@id='majorTabPanel:createfindsmolocattributes']", By.tagName("input"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:ccity']"), city);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("State", "//table[@id='majorTabPanel:createfindsmolocattributes']", By.xpath("//label[@id='majorTabPanel:cstate_label']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:cstate_panel']/div/ul/li[text()='" + state + "']"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*Zip Code", "//table[@id='majorTabPanel:createfindsmolocattributes']", By.tagName("input"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:czipcode']"), zip);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:jvsCreateSMOLOCSaveLocationButton']"));

    }

    public void editSubjectInfo(String ssn) throws Exception {
        // Call the method to search for subject based on SSN
        subjectSearch(ssn);
        // Click on Subject Details link in Subject summary tab
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Subject Details"));

        //click on edit subject info button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[contains(text(),'Edit Subject Information')]"));

        //click on the input field for First name
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*First Name", "//table[@id='subjectEditForm:SubjectChangeAttributes']", By.tagName("input"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//table[@id='subjectEditForm:SubjectChangeAttributes']//tr[1]//input"));
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//table[@id='subjectEditForm:SubjectChangeAttributes']//tr[1]//input"), "testSmokeFName");
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='subjectEditForm:jvsPopupSubjectEditUpdateSubjectButton']"));
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Subject information saved");
        pause(10);
    }

    /**
     * This method will create SMO relationship between the case and SO based on
     * the category type and SSN passed.
     *
     * @param categoryType
     * @param ssn
     * @param categoryType
     * @param ssn
     * @throws Exception
     * @author vshivaraman
     */
    public void createSMORelationship(String categoryType, String ssn) throws Exception {

        // Call the method to search for subject based on SSN
        subjectSearch(ssn);

        // Click on Subject Details link in Subject summary tab
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Subject Details"));

        // click on SMO relationship tab
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SMORelationshipTab.getProperty()));

        pause(5);
        // Click on Create category button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.NewcategoryButton.getProperty()));
        pause(3);
        // Click on the CatergoryType drop down menu
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.CategoryTypeDropDown1.getProperty()));

        pause(2);

        // Click on the category type option based on the value passed for
        // argument categoryType
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(categoryType));

        // Click the next button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.CreateCategoryNextBtn.getProperty()));
        // Click check box to skip workdetails
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SkipWorkInfocheckbox.getProperty()));

        // Click next button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.WorkOrgNextBtn.getProperty()));
        // Click create button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.CreateCategoryBtn.getProperty()));

        WaitingToLoad();

        // Click on the Establish new relationship button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.EstablishNewRelationBtn.getProperty()));
        pause(2);
        // click on the select category radio button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SelectSubjectCategoryRadioBtn.getProperty()));
        pause(2);
        // click on the owning button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.OwningBtn.getProperty()));
        pause(1);

        // click on the next button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='addRelationshipWizardForm:jvsAddRelationshipWizardNextButton']"));
        pause(3);
        //click on next button in Access tab
        //	com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Next");// Missing in QA
        // Click on save button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SummarySaveBt.getProperty()));


        pause(10);

        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "New Relationship ");

        WaitingToLoad();
    }

    /**
     * This method will upload files from file path based on the inputField,
     * document type and document name passed
     *
     * @param inputField
     * @param filePath
     * @param docType
     * @param docName
     * @author vshivaraman
     */

    public void docUpload(String inputField, String filePath, String docType, String docName) {

       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("documentUploaderForm:editAddDocumentMessagesDocName"));

        // Enter Document name
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.id("documentUploaderForm:editAddDocumentMessagesDocName"), docName);

        // Click on the Select doc type drop down
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.id("documentUploaderForm:editAddDocumentMessagesDocType_label"));
        pause(2);

        // Select letter/document type
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(docType));
        pause(2);

        // Pass the input field and file path for the document to be uploaded
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@type='file']"), filePath);
        pause(2);
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndVerify_IfContains("//tr[6]//td[2]", "test.pdf");
        // Click add document button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.AddDocBtn.getProperty()));

        pause(2);

    }

    /**
     * This Method will complete the SOR flow from Portal as Security officer
     * for the subject whose SSN, first name and last name are passed
     *
     * @param ssn
     * @param firstName
     * @param lastName
     * @author vshivaraman
     */

    public void jvsSORflow(String ssn, String firstName, String lastName) throws Exception {

        // click task inbox
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));

        // Select the task
        ReusableFunctions.waitAndselectCellFromTable(firstName + " " + lastName, By.xpath("//*[@id='majorTabPanel:SubTabletasks']"));

        // Click claim button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,'PortalClaim')]"));

        // Verify the growl message is seen
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "claimed");
        pause(10);

        // Click upload document button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Upload Document");

        // Call document upload method to upload file
        docUpload(JVSProperties.InputField.getProperty(), OR.getProperty("filePath"), JVSProperties.DocTypeAcknowledgementReceipt.getProperty(), "Acknowledgement");

        // Click Acknowledge date button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.DatePickerIcon.getProperty()));

        // call Select todays date function to pick todays date in the calendar
        String date = reusable.selectTodaysDate();

        // Select todays date in the calendar
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='ui-datepicker-div']//a[text()='" + date + "']"));
        // Click upload document button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Upload Document");

        // Call document upload method to upload subject response file
        docUpload(JVSProperties.InputField.getProperty(), OR.getProperty("filePath"), JVSProperties.DocTypeSubjectResponse.getProperty(), "Subject response");

        // Click Send button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Send");

        // Verify the growl message is seen
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "completed");
        pause(30);

    }

    /**
     * This Method will complete the LOD/LOR flow from Portal as Security
     * officer with or without subject appeal for the subject based on the SSN,
     * first name and last name passed
     *
     * @param willSubjectAppeal
     * @param ssn
     * @param firstName
     * @param lastName
     * @author vshivaraman
     */

    public void jvsLODLORflow(String ssn, String firstName, String lastName, String willSubjectAppeal) throws Exception {

        if (willSubjectAppeal.equalsIgnoreCase("No")) {
            // click task inbox
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));

            // Select the task
            ReusableFunctions.selectCellFromTable(firstName + " " + lastName, "//*[@id='majorTabPanel:SubTabletasks']");

            // Click claim button
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,'PortalClaim')]"));

            // Verify the growl message is seen
            //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "claimed");
            pause(10);
            // Click upload document button
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Upload Document");

            // Call document upload method to upload file
            docUpload(JVSProperties.InputField.getProperty(), OR.getProperty("filePath"), JVSProperties.DocTypeAcknowledgementReceipt.getProperty(), "Acknowledgement of receipt");

            // Click LOR Acknowledgement date button
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.DatePickerIcon.getProperty()));
            // Call date picker function to select todays date
            String date = reusable.selectTodaysDate();
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//a[text()='" + date + "']"));
            pause(1);

            // Click Send button
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Send");
            WaitingToLoad();

            // Verify the growl message is seen
            //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "completed");
            pause(30);

        }

        if (willSubjectAppeal.equalsIgnoreCase("Yes")) {
            // click task inbox
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));

            // Select the task
            ReusableFunctions.selectCellFromTable(firstName + " " + lastName, "//*[@id='majorTabPanel:SubTabletasks']");

            // Click claim button
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,'PortalClaim')]"));

            // Verify the growl message is seen
            //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "claimed");
            pause(10);

            // Click upload document button
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Upload Document");

            // Call document upload method to upload file
            docUpload(JVSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), JVSProperties.DocTypeAcknowledgementReceipt.getProperty(), "Acknowledgement of receipt");

            // Click LOR Acknowledgement date button
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.DatePickerIcon.getProperty()));

            // call Select todays date function to pick todays date in the
            // calendar
            String date = reusable.selectTodaysDate();
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']//a[text()='" + date + "']"));
            pause(5);

            // Check the check box for Subject will appeal
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SubjectAppealCheckBox.getProperty()));

            // Click Send button
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndButtonClick("Send");
            WaitingToLoad();

            // Verify the growl message is seen
            //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "completed");
            pause(10);
        }
    }

    /**
     * This function will create a CSR provide supplemental Info request for a
     * SSN and upload document specified with docname,type and comments
     *
     * @param ssn
     * @param docName
     * @param docType
     * @param filePath
     * @param comments
     * @throws Exception
     * @author vshivaraman
     */
    public void csrProvideSupplementalInfo(String ssn, String docName, String docType, String filePath, String comments) throws Exception {
        // search for the subject based on ssn
        subjectSearch(ssn);
        // click on the subject details link
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Subject Details"));
        // click on the subject actions button to open the drop down list of
        // actions
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:subjtabview:subactions_button']"));
        // select the CSR
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a/span[text()='CSR/Provide Supplemental Information']"));
        // click on the upload doc button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrDialog']//button/span[text()='Upload Document']"));
        // call the document upload function to upload the document
        docUpload(JVSProperties.ChooseBtn.getProperty(), filePath, docType, docName);
        // click on comments text area
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*Comments:", "//div[2]/table", By.tagName("textarea"));
        // send comments
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//div[2]/table/tbody/tr/td[2]/textarea[@role='textbox']"), comments);
        // click send button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='csrForm:sendCsr']"));

        // Verify the growl message is seen
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "CSR Provide Supplemental Information submitted to CAF");
        pause(10);
    }

    /**
     * This function will create a CSR Reciprocity request for a SSN and upload
     * document with specified name,type, comments,category, eleigibility level,
     * agency name, montheligibility was granted
     *
     * @param category
     * @param eligibilityLevel
     * @param agencyName
     * @param monthEligibilityGranted      first 3 chars of the month as seen in the calendar in the app
     * @param yearEligibilityGranted       year four digits when eligibility was granted
     * @param dateEligibilitygranted       date "dd" when eligibility was granted
     * @param monthInvestigationCompleted  first 3 chars of the month as seen in the calendar in the app
     * @param yearInvestigationCompleted   year four digits when investigation was completed
     * @param dateInvestigationCompleted   date "dd" when investigation was completed
     * @param investigationServicePorvider Name of the investigation provider
     * @param locInvestigationprovider     location of the investigation provider
     * @param docName
     * @param docType
     * @param filePath
     * @param comments
     * @throws Exception
     * @author vshivaraman@param ssn
     */
    public void csrRequestReciprocity(String ssn, String category, String eligibilityLevel, String agencyName, String monthEligibilityGranted, String yearEligibilityGranted, String dateEligibilitygranted, String monthInvestigationCompleted, String yearInvestigationCompleted, String dateInvestigationCompleted, String investigationServicePorvider, String locInvestigationprovider, String docName, String docType, String filePath, String comments) throws Exception {
        // search for the subject based on ssn
        subjectSearch(ssn);
        // click on the subject details link
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Subject Details"));
        // click on the subject actions button to open the drop down list of
        // actions
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:subjtabview:subactions_button']"));
        // select the CSR
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a/span[text()='CSR/Request Reciprocity']"));
        // select subject category drop down menu"
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='csrForm:subjectCategoriesReciprocity_label']"));
        // select the subject category
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrForm:subjectCategoriesReciprocity_panel']/div/ul/li[@data-label='" + category + "']"));
        // select eligibility level requested drop down menu
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='csrForm:eligibilityLevelRequestedReciprocity_label']"));
        // select the eligibility level
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrForm:eligibilityLevelRequestedReciprocity_panel']//li[@data-label='" + eligibilityLevel + "']"));

        // click at agency that granted eligibility
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='csrForm:agencyName']"));
        // enter the name of the agency that granted eligibility
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='csrForm:agencyName']"), agencyName);

        // click on the calendar icon for date eligibility granted
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='csrForm:eligibilityDate']/button"));
        // select the date eligibility granted
        reusable.selectDate(monthEligibilityGranted, yearEligibilityGranted, dateEligibilitygranted);
        // Click on the date investigation completed calendar icon
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='csrForm:investigationDate']/button"));
        reusable.selectDate(monthInvestigationCompleted, yearInvestigationCompleted, dateInvestigationCompleted);
        // click on the investigation service provider text box
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='csrForm:investigationServiceProvider']"));
        // enter the investigation service provider name
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='csrForm:investigationServiceProvider']"), investigationServicePorvider);
        // click on the location of investigation provider text box
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='csrForm:investigationLocation']"));
        // enter the location
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='csrForm:investigationLocation']"), locInvestigationprovider);
        // click on the upload doc button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrDialog']//button/span[text()='Upload Document']"));
        // call the document upload function to upload the document
        docUpload(JVSProperties.ChooseBtn.getProperty(), filePath, docType, docName);
        // click on comments text area
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*Comments:", "//div[2]/table", By.tagName("textarea"));
        // send comments
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//div[2]/table/tbody/tr/td[2]/textarea[@role='textbox']"), comments);
        // click send button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='csrForm:sendCsr']"));

        // Verify the growl message is seen
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "CSR Reciprocity submitted to CAF");
        pause(10);
    }

    /**
     * This function will create a CSR Recertify request for a ssn and
     * upload document with specified doc name, type, comments, eligibility
     * level,break in service,and based on the value for break in service will
     * enter the month,date and year of breakinservice start and end, recertify
     * reason
     *
     * @param ssn
     * @param eligibilityLevel
     * @param breakInService           Yes or No value
     * @param monthBreakInServiceStart when break in service is Yes then enter the first 3 char of
     *                                 month break in service started and when break in service is No
     *                                 enter "None"
     * @param yearBreakInServiceStart  when break in service is Yes then enter the 4 digit year break
     *                                 in service started and when break in service is No enter
     *                                 "None"
     * @param dateBreakInServiceStart  when break in service is Yes then enter date ,in "dd"format,
     *                                 break in service started and when break in service is No enter
     *                                 "None"
     * @param monthBreakInServiceEnd   when break in service is Yes then enter the first 3 char of
     *                                 month break in service ended and when break in service is No
     *                                 enter "None"
     * @param yearBreakInServiceEnd    when break in service is Yes then enter the 4 digit year break
     *                                 in service ended and when break in service is No enter "None"
     * @param dateBreakInServiceEnd    when break in service is Yes then enter enter date ,in
     *                                 "dd"format, break in service ended and when break in service
     *                                 is No enter "None"
     * @param recertifyReason
     * @param docName
     * @param docType
     * @param filePath
     * @param comments
     * @throws Exception
     * @author vshivaraman
     */
    public void csrRecertify(String ssn, String eligibilityLevel, String breakInService, String monthBreakInServiceStart, String yearBreakInServiceStart, String dateBreakInServiceStart, String monthBreakInServiceEnd, String yearBreakInServiceEnd, String dateBreakInServiceEnd, String recertifyReason, String docName, String docType, String filePath, String comments) throws Exception {
        // search for the subject based on ssn
        subjectSearch(ssn);
        // click on the subject details link
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Subject Details"));
        // click on the subject actions button to open the drop down list of
        // actions
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:subjtabview:subactions_button']"));
        // select the CSR
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a/span[text()='CSR/Recertify']"));
        // select eligibility level requested drop down menu
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='csrForm:eligibilityLevelRequestedSelect_label']"));
        // select the eligibility level
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrForm:eligibilityLevelRequestedSelect_panel']//li[@data-label='" + eligibilityLevel + "']"));
        // select break in service value drop down menu
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='csrForm:breakInService_label']"));
        // Select the option for break in service
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrForm:breakInService_panel']//li[@data-label='" + breakInService + "']"));
        if (breakInService.equalsIgnoreCase("yes")) {
            // click on the calendar icon for date break in service start
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='csrForm:bissDate']/button"));
            // select the date eligibility granted
            reusable.selectDate(monthBreakInServiceStart, yearBreakInServiceStart, dateBreakInServiceStart);
            // Click on the date break inservice end calendar icon
           com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='csrForm:biseDate']/button"));
            reusable.selectDate(monthBreakInServiceEnd, yearBreakInServiceEnd, dateBreakInServiceEnd);
        }
        // click on the rectify reason drop down menu
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='csrForm:recertifyReasonSelect_label']"));
        // Select the option in the list for recertify reason
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrForm:recertifyReasonSelect_panel']//li[@data-label='" + recertifyReason + "']"));
        // click on the upload doc button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrDialog']//button/span[text()='Upload Document']"));
        // call the document upload function to upload the document
        docUpload(JVSProperties.ChooseBtn.getProperty(), filePath, docType, docName);
        // click on comments text area
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*Comments:", "//div[2]/table", By.tagName("textarea"));
        // send comments
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//div[2]/table/tbody/tr/td[2]/textarea[@role='textbox']"), comments);
        // click send button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='csrForm:sendCsr']"));

        // Verify the growl message is seen
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "CSR Recertify submitted to CAF");
        pause(10);
    }

    /**
     * This function will create a request for reconsideration for an ssn with
     * specified eligibility level,document name,type and comments passed to it
     *
     * @param ssn
     * @param eligibilityLevel
     * @param docName
     * @param docType
     * @param filePath
     * @param comments
     * @throws Exception
     * @author vshivaraman
     */
    public void csrRequestReconsideration(String ssn, String eligibilityLevel, String docName, String docType, String filePath, String comments) throws Exception {
        // search for the subject based on ssn
        subjectSearch(ssn);
        // click on the subject details link
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Subject Details"));
        // click on the subject actions button to open the drop down list of
        // actions
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:subjtabview:subactions_button']"));
        // select the CSR
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a/span[text()='CSR/Request Adjudication Reconsideration']"));

        // select eligibility level requested drop down menu
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='csrForm:eligibilityLevelRequestedSelectRecon_label']"));
        // select the eligibility level
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrForm:eligibilityLevelRequestedSelectRecon_panel']//li[@data-label='" + eligibilityLevel + "']"));

        // click on the upload doc button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrDialog']//button/span[text()='Upload Document']"));
        // call the document upload function to upload the document
        docUpload(JVSProperties.ChooseBtn.getProperty(), filePath, docType, docName);
        // click on comments text area
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*Comments:", "//div[2]/table", By.tagName("textarea"));
        // send comments
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//div[2]/table/tbody/tr/td[2]/textarea[@role='textbox']"), comments);
        // click send button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='csrForm:sendCsr']"));

        // Verify the growl message is seen
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "CSR Reconsideration submitted to CAF");
        pause(10);
    }

    /**
     * This function will create a CSR expedite request for a ssn and upload
     * specified document with name,type, comments and priority program passed
     * to it
     *
     * @param ssn
     * @param docName
     * @param docType
     * @param filePath
     * @param comments
     * @param priorityProgram
     * @throws Exception
     * @author vshivaraman
     */
    public void csrExpediteRequest(String ssn, String docName, String docType, String filePath, String comments, String priorityProgram) throws Exception {
        //search for the ssn
        subjectSearch(ssn);
        //click on the subject details link
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Subject Details"));
        //click on the subject actions button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:subjtabview:subactions_button']"));
        //select the CSR expedite process request option fromt eh drop down list
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a/span[text()='CSR/Expedite Process Request']"));
        //Click on the upload document button in the pop up
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrDialog']//button/span[text()='Upload Document']"));
        //call the document upload function to upload the specified document
        docUpload(JVSProperties.ChooseBtn.getProperty(), filePath, docType, docName);
        // click on priority program drop down
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='csrForm:CSRReqExpediteTargetPrioritySelectOneMenu_label']"));
        // select a priority program
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='csrForm:CSRReqExpediteTargetPrioritySelectOneMenu_panel']//li[@data-label='" + priorityProgram + "']"));
        // click on comments text area
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell("*Comments:", "//div[2]/table", By.tagName("textarea"));
        // send comments
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//div[2]/table//td[2]/textarea[@role='textbox']"), comments);
        // click send button
       com.iworkscorp.dashboard.hudson.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='csrForm:sendCsr']"));

        // Verify the growl message is seen
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "CSR Expedite Request submitted to CAF");
        pause(10);
    }

}
