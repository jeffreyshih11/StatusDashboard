package com.iworks.DISS.test.jvsSmokeTests;

import com.iworks.DISS.test.common.functions.CATSReusuableFunctions;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.functions.JVSReusableFunctions;
import com.iworks.DISS.test.common.functions.JvsCreateSubject;
import com.iworks.DISS.test.common.functions.ReusableFunctions;
import com.iworks.DISS.test.common.properties.CATSProperties;
import com.iworks.DISS.test.common.properties.JVSProperties;
import com.iworks.DISS.test.common.utils.TestBase;
import com.iworks.DISS.test.common.utils.Build;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;



public class PortalSmokeTest extends TestBase {
    private static final String CATEGORY_TYPE = JVSProperties.CivilianRetiree.getProperty();
    private static final String HUMAN_RESOURCES_ROLE = "Human Resources";
    private static final String COMPONENT_ADJUDICATOR_ROLE = "Component Adjudicator";
    private static final String SECURITY_OFFICER_ADMIN_ROLE = "Security Officer Admin";
    private static final String SECURITY_OFFICER_STANDARD_ROLE = "Security Officer Standard";
    private static final String HELP_DESK_ROLE = "Help Desk";//Help Desk
    private static final String HIERARCHY_MANAGER_ROLE = "Hierarchy Manager";
    private static final String ACCOUNT_MANAGER_ROLE = "Account Manager";
    private static final String SECURITY_OFFICER_VISIT_ADMIN_ROLE = "Security Officer Visit Admin";
    private static final String PHYSICAL_ACCESS_CONTROL_PERSONNEL_ROLE = "Physical Access Control Personnel";
    private static final String SECURITY_MANAGER_ROLE = "Security Manager";
    private String smoName = "--AutoSMO-1";

    // Map User name to User Info
    Map<String, UserInfo> userNameToInfoMap = new HashMap<>();
    //Map user to tab info
    Map<String, TabInfo> userToTabInfoMap = new HashMap<>();
    JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
    CATSReusuableFunctions reusable = new CATSReusuableFunctions();
    String firstName = null;
    String lastName = null;
    long startTime;
    long endTime;
    private Random random;


    public static class UserInfo {
        public String fName;
        public String lName;
        public String uName;
        public String ssn;
        public String role;
        public String leftNavLinks;
        public String mainPageTabs;
        public String smoTreeView;
        public String smoTreeActions;
    }
    public static class TabInfo{
        public String role;
        public String subjectTabDetails;
        public String smoDetailsTabDetails;
        public String unreadNotifications;
        public String taskInbox;
        public String searchOrganizations;
    }


    public Class<? extends PortalSmokeTest> clazz = this.getClass();
    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() throws Exception {
        startTime = System.currentTimeMillis();
        String className = clazz.getCanonicalName();
        reusable.createLogFile(className, name);
//		DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
//		Calendar cal = Calendar.getInstance();
//		String date = dateFormat.format(cal.getTime());
//		DateFormat dateFormat1 = new SimpleDateFormat("yyMMdd");
//		String date1 = dateFormat1.format(cal.getTime());
//		String environment = CONFIG.getProperty("Environment");
//		File targetFile = new File("S://AutomationOutput//" + environment+"//"+ className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc");
//		reusable.createLogFile(className, name,targetFile );

        random = new Random();
    }

    @After
    public void tearDown() throws Exception {
        endTime = System.currentTimeMillis();
        double executionTime = (endTime - startTime) / 1000.0;
        System.out.println("This test took " + executionTime + "Seconds \n");
    }

    /**
     * This method will initialize the browser and maximize it before the class
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setupClass() throws Exception {
        initialize();

    }

    /**
     * This method will close the browser and driver after the class
     *
     * @throws Exception
     */
    @AfterClass
    public static void teardownClass() throws Exception {

        quitBrowser();

    }

    @Test
    public void portalSmokeTest() throws Exception {
        SSNFNameAndLName sub = new SSNFNameAndLName();
        // Call the createtestData function to create users with different roles
        System.out.println("Test:Call the createtestData function to create users with different roles \n");
        List<UserInfo> list = createTestData(sub);
        System.out.println("Result: Test data for different roles have been created \n");

        // Call the verify log in function to verify log in as different users and verify their LHS panel links ,Main page tabs and each tab detail
        verifyLogin(list);

        // Call the verifyCATSBasicFunctions
        verifyJVSBasicFunctions(sub.ssn);//sub.ssn
        // "India", adjudicatorUserName, procTeamUsername, standardSOUserName);
        jvsreusable.jvsLogout();

    }


    //ALL THE DIFFERENT ENVIORNMENT METHODS
    @Test
    //DEV
    public void environmentValidationDEV() throws Exception{
        environmentValidation("DEV");
    }

    @Test
    //QA
    public void environmentValidationQA() throws Exception{
        environmentValidation("QA");
    }

    @Test
    //GAT
    public void environmentValidationGAT() throws Exception{
        environmentValidation("GAT");
    }

    @Test
    //LOCAL
    public void environmentValidationLOCAL() throws Exception{
        environmentValidation("LOCAL");
    }

    @Test
    //BASELINE
    public void environmentValidationBASELINE() throws Exception{
        environmentValidation("BASELINE");
    }

    @Test
    //DEMO
    public void environmentValidationDEMO() throws Exception{
        environmentValidation("DEMO");
    }

    @Test
    //UAT
    public void environmentValidationUAT() throws Exception{
        environmentValidation("UAT");
    }

    //HELPER method to run each application
    public void environmentValidation(String env) throws Exception{
        //JVSsmoke(env);
        CATSsmoke(env, JVSsmoke(env));
        serviceDeskSmoke(env);
    }


    /**
     * Creates a subject in JVS and searches for the subject
     * @param env
     * @return
     * @throws Exception
     */
    public UserInfo JVSsmoke(String env) throws Exception{
        final UserInfo userInfo = new UserInfo();
        userInfo.fName = "Environment";
        userInfo.lName = "Confirmation";
        userInfo.ssn = getSsn();
        jvsreusable.loginToJvsAsOneEnv(env);
        new JvsCreateSubject().createSubject(userInfo);
        jvsreusable.subjectSearch(userInfo.ssn);
        jvsreusable.jvsLogout(env);

        return userInfo;
    }

    /**
     * searches for the subject created in JVS in CATS
     * and creates a case and uploads a document on that subject
     * @param env
     * @param userInfo
     * @throws Exception
     */
    public void CATSsmoke(String env, UserInfo userInfo) throws Exception{
        reusable.loginToCATSasOneEnv(env);
        reusable.createCaseForSubject(userInfo.ssn,"//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[text()='Division D (Air Force)']" /*"/[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division B (Army)']"*/, CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Doc_Tab.getProperty()));
        reusable.docUpload("//*/input[@type='file']", "C:\\Users\\jshih\\Documents\\pii_certificate.pdf", "COI", "pii_certificate", "description");
    }

    /**
     * checks if EAI properties are there in ServiceDesk
     * @param env
     * @throws Exception
     */
    public void serviceDeskSmoke(String env) throws Exception{
        //driver.navigate().to(CONFIG.getProperty("ServiceDesk_Url"));
        //ReusableFunctions.waitAndLoginWithUser("1");
        loginToSDasOneEnv(env);
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.id("statusPanelForm:eaiConfigLink"));
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.id("majorTabPanel:eaiPropertyDataGridId:0:j_id_dc_toggler"));

        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.id("majorTabPanel:eaiPropertyDataGridId:3:j_id_dc_toggler"));
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.id("majorTabPanel:eaiPropertyDataGridId:2:j_id_dc_toggler"));
        WebElement empty = com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndFindBy(By.id("majorTabPanel:eaiPropertyDataGridId:3:propertiesIdTable:0:propertiesIdeaipropKey1"));
        System.out.print("SDf");
    }


    /**
     * log into ServiceDesk as user '1'
     * @param env
     * @throws Exception
     */
    public void loginToSDasOneEnv(String env) throws Exception{
        loginToSDEnv("1", env);
    }


    /**
     * logs into ServiceDesk on the appropriate environment
     * @param user
     * @param env
     * @throws Exception
     */
    public void loginToSDEnv(String user, String env) throws Exception{
        String login_url = null;
        switch(env){
            case "DEV":
                login_url = "DEV_ServiceDesk_Url";
                break;
            case "QA":
                login_url = "QA_ServiceDesk_Url";
                break;
            case "GAT":
                login_url = "GAT_ServiceDesk_Url";
                break;
            case "LOCAL":
                login_url = "LOCAL_ServiceDesk_Url";
                break;
            case "BASELINE":
                login_url = "BASELINE_ServiceDesk_Url";
                break;
            case "DEMO":
                login_url = "DEMO_ServiceDesk_Url";
                break;
            case "UAT":
                login_url = "UAT_ServiceDesk_Url";
                break;
        }
        driver.navigate().to(CONFIG.getProperty(login_url));
        ReusableFunctions.waitAndLoginWithUser(user);
    }


    @Test
    public void connectToHudson() throws Exception {
        driver.navigate().to("https://office.iworkscorp.com/hudson/login?from=%2Fhudson");
        ReusableFunctions.waitUntilElementExistsAndClick(By.id("top-nav"));
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[text()='log in']"), 1500);
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_username']"), "avillaflor");
        ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='loginForm']//input[@name='j_password']"), "avillaflor");
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='loginForm']//input[@id='loginButton']"), 2000);


       /* driver.navigate().to("https://office.iworkscorp.com/hudson/view/Baseline/");
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("/*//*[@id= 'projectstatus']//[@class='sortheader']"));
        URL url = new URL("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_1/lastBuild/consoleFull");
        driver.navigate().to("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_1/lastSuccessfulBuild/api/json");
        String pageSource = driver.getPageSource();
        int revIndex = pageSource.indexOf("\"revision\":");
        String revisionNum = pageSource.substring(revIndex + 11, revIndex + 16);
        int dateIndex = pageSource.indexOf("id");
        String date = pageSource.substring(dateIndex + 3, pageSource.indexOf("keepLog") - 3);
        int userIndex = pageSource.indexOf("Started by user");
        String user = pageSource.substring(userIndex+ 16, pageSource.indexOf("userName") -3);*/


        ArrayList<Build> baselineList = new ArrayList<Build>();
        Build baseline_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_1/lastSuccessfulBuild/api/json");
        baselineList.add(baseline_tag_1);
        Build baseline_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_2/lastSuccessfulBuild/api/json");
        baselineList.add(baseline_tag_2);
        Build baseline_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/Baseline/job/App_build_and_deploy_baseline_build_tag_3/lastSuccessfulBuild/api/json");
        baselineList.add(baseline_tag_3);

        Build mostRecentBaseLine = baselineList.get(0);
        for(int baselineListIdx = 0; baselineListIdx < baselineList.size(); baselineListIdx++){
            if(!mostRecentBaseLine.isMostRecent(baselineList.get(baselineListIdx))){
                mostRecentBaseLine = baselineList.get(baselineListIdx);
            }
        }

        ArrayList<Build> demoList = new ArrayList<Build>();
        Build DEMO_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_1/lastSuccessfulBuild/api/json");
        demoList.add(DEMO_tag_1);
        Build DEMO_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_2/lastSuccessfulBuild/api/json");
        demoList.add(DEMO_tag_2);
        Build DEMO_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/DEMO/job/App_build_and_deploy_demo_build_tag_3/lastSuccessfulBuild/api/json");
        demoList.add(DEMO_tag_3);

        Build mostRecentDEMO = demoList.get(0);
        for(int demoListIdx = 0; demoListIdx < demoList.size(); demoListIdx++){
            if(!mostRecentDEMO.isMostRecent(demoList.get(demoListIdx))){
                mostRecentDEMO = demoList.get(demoListIdx);
            }
        }


        ArrayList<Build> devList = new ArrayList<Build>();
        Build DEV_automation = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_automation/lastSuccessfulBuild/api/json");
        devList.add(DEV_automation);
        Build DEV_JUnit = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_run_JUnit/lastSuccessfulBuild/api/json");
        devList.add(DEV_JUnit);
        Build DEV_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_build_tag_1/lastSuccessfulBuild/api/json");
        devList.add(DEV_tag_1);
        Build DEV_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_build_tag_2/lastSuccessfulBuild/api/json");
        devList.add(DEV_tag_2);
        Build DEV_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/DEV/job/App_build_and_deploy_dev_build_tag_3/lastSuccessfulBuild/api/json");
        devList.add(DEV_tag_3);


        Build mostRecentDEV = devList.get(0);
        for(int devListIdx = 0; devListIdx < devList.size(); devListIdx++){
            if(!mostRecentDEV.isMostRecent(devList.get(devListIdx))){
                mostRecentDEV = devList.get(devListIdx);
            }
        }


        ArrayList<Build> gatList = new ArrayList<Build>();
        Build Gat_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_1/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_1);
        Build Gat_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_2/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_2);
        Build Gat_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/GAT/job/App_build_and_deploy_gat_build_tag_3/lastSuccessfulBuild/api/json");
        gatList.add(Gat_tag_3);

        Build mostRecentGAT = gatList.get(0);
        for(int gatListIdx = 0; gatListIdx < gatList.size(); gatListIdx++){
            if(!mostRecentGAT.isMostRecent(gatList.get(gatListIdx))){
                mostRecentGAT = gatList.get(gatListIdx);
            }
        }

        ArrayList<Build> qaList = new ArrayList<Build>();
        Build QA_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_1/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_1);
        Build QA_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_2/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_2);
        Build QA_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/QA/job/App_build_and_deploy_qa_build_tag_3/lastSuccessfulBuild/api/json");
        qaList.add(QA_tag_3);

        Build mostRecentQA = qaList.get(0);
        for(int qaListIdx = 0; qaListIdx < qaList.size(); qaListIdx++){
            if(!mostRecentQA.isMostRecent(qaList.get(qaListIdx))){
                mostRecentQA = qaList.get(qaListIdx);
            }
        }

        ArrayList<Build> uatList = new ArrayList<Build>();
        Build UAT_tag_1 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_1/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_1));
        Build UAT_tag_2 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_2/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_2));
        Build UAT_tag_3 = new Build("https://office.iworkscorp.com/hudson/view/UAT/job/App_build_and_deploy_uat_build_tag_3/lastSuccessfulBuild/api/json");
        uatList.add((UAT_tag_3));

        Build mostRecentUAT = uatList.get(0);
        for(int uatListIdx = 0; uatListIdx < uatList.size(); uatListIdx++){
            if(!mostRecentUAT.isMostRecent(uatList.get(uatListIdx))){
                mostRecentUAT = uatList.get(uatListIdx);
            }
        }



        System.out.println("dfa");




    }







    /*@Test
    public void environmentValidation() throws Exception {

        final UserInfo userInfo = new UserInfo();
        userInfo.fName = "Environment";
        userInfo.lName = "Confirmation";
        userInfo.ssn = getSsn();
        jvsreusable.loginToJvsAsOne();
        new JvsCreateSubject().createSubject(userInfo);
        jvsreusable.subjectSearch(userInfo.ssn);
        jvsreusable.jvsLogout();

        reusable.loginToCATS("1");
        reusable.createCaseForSubject(userInfo.ssn, "/[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division D (Air Force)']", CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Doc_Tab.getProperty()));
        reusable.docUpload("/*//*//**//*input[@type='file']", "C:\\Users\\jshih\\Documents\\pii_certificate.pdf", "COI", "pii_certificate", "description");


        //log into service desk?
        driver.navigate().to(CONFIG.getProperty("QA_ServiceDesk_Url"));
        ReusableFunctions.waitAndLoginWithUser("1");
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.id("statusPanelForm:eaiConfigLink"));
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.id("majorTabPanel:eaiPropertyDataGridId:0:j_id_dc_toggler"));

        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.id("majorTabPanel:eaiPropertyDataGridId:3:j_id_dc_toggler"));
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.id("majorTabPanel:eaiPropertyDataGridId:2:j_id_dc_toggler"));
        WebElement empty = com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndFindBy(By.id("majorTabPanel:eaiPropertyDataGridId:3:propertiesIdTable:0:propertiesIdeaipropKey1"));

    }*/

    @Test
    public void uploadDoc() throws Exception{
        reusable.loginToCATS("1");
        reusable.searchSubjectCATS("106-99-2640");
        //reusable.createCaseForSubject("106-99-2640", "//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='Division D (Air Force)']", CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Doc_Tab.getProperty()));
        reusable.docUpload("//*/input[@type='file']", "C:\\Users\\jshih\\Documents\\pii_certificate.pdf", "COI", "pii_certificate", "description");
        ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='documentUploaderForm:documentUploadAddButtonId']"));
        pause(10);
    }


    private String getSsn() {
        return String.valueOf(random.nextInt() * 1000000000);
    }

    /**
     * This function will return a list of first names, last names and usernames
     * of the users created
     *
     * @return List<FNameAndLName>
     * @throws Exception
     */
    public List<UserInfo> createTestData(SSNFNameAndLName sub) throws Exception {
        List<UserInfo> retList = new ArrayList<UserInfo>();

        UserInfo uInfo = new UserInfo();
        TabInfo tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,User,View Users,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "Subjects,SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Make Current SMO,Show SMO Detail,Create SMO Here,Deactivate";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="Army,Navy,AF,DoD Civilian,Other Federal Agencies,PSMO,Other SMO,--AutoSMO-1";
        uInfo.uName= "1";
        userNameToInfoMap.put(uInfo.uName, uInfo);
        userToTabInfoMap.put(uInfo.uName, tInfo);


        //Security Officer Standard
        // call ssn gen to create Security Officer standard user
        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "Subjects,SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "SecOffStnFName", "SecOffStnLName",  SECURITY_OFFICER_STANDARD_ROLE, tInfo, uInfo);
        retList.add(uInfo);

        //Security Officer Admin

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "Subjects,SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "SecOffAdmFName", "SecOffadmLName", SECURITY_OFFICER_ADMIN_ROLE, tInfo, uInfo);
        retList.add(uInfo);

        //Component Adjudicator

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "CompAdjFName", "CompAdjLName",  COMPONENT_ADJUDICATOR_ROLE, tInfo, uInfo);
        retList.add(uInfo);



        //Human Resource

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "HumResFName", "HumResLName", HUMAN_RESOURCES_ROLE, tInfo, uInfo);
        retList.add(uInfo);

        //Security manager

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "SecManFName", "SecManLName",  SECURITY_MANAGER_ROLE, tInfo, uInfo);
        retList.add(uInfo);


        //Physical access control personnel

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "Subjects,SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "PhyACPFName", "PhyACPLName", PHYSICAL_ACCESS_CONTROL_PERSONNEL_ROLE, tInfo, uInfo);
        retList.add(uInfo);


        //Security officer visit admin

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "Subjects,SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "SOVAdmFName", "SOVAdmLName",  SECURITY_OFFICER_VISIT_ADMIN_ROLE, tInfo, uInfo);
        retList.add(uInfo);


        //Account manager

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "Subjects,SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "ActManFName", "ActManLName",  ACCOUNT_MANAGER_ROLE, tInfo, uInfo);
        retList.add(uInfo);

        //Hierarchy manager

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Users,View Current SMO,View SMO Tree,Search SMOs,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "Subjects,SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail,Create SMO Here,Deactivate SMO";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "HryManFName", "HryManLName",  HIERARCHY_MANAGER_ROLE, tInfo, uInfo);
        retList.add(uInfo);


        //help Desk

        uInfo = new UserInfo();
        tInfo = new TabInfo();
        uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
        uInfo.mainPageTabs = "Subjects,SMO Details";
        uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
        tInfo.subjectTabDetails="Last Name,First Name,SSN";
        tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
        tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
        tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
        tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
        uInfo.smoTreeView ="--AutoSMO-1";
        createUsersForSmokeTest( "HepDskFName", "HepDskLName",  HELP_DESK_ROLE, tInfo, uInfo);
        retList.add(uInfo);

        // create a subject for JVs testing

        List<SSNFNameAndLName> list = reusable.ssnNameGenerator("smokeJVSFName", "smokeJVSLName");
        sub.ssn = list.get(0).ssn;
        sub.firstName = list.get(0).firstName;
        sub.lastName = list.get(0).lastName;
        reusable.loginToCATS("1");
        System.out.println("SSN is " + sub.ssn);
        System.out.println("First name is " + sub.firstName);
        System.out.println("Last name is " + sub.lastName);
        // create subject for Portal testing
        reusable.createSubject(sub.ssn, sub.firstName, sub.lastName, "1983", "20");
        //log out user 1 from CATS
        catsLogout();

        return retList;

    }

    public void createUsersForSmokeTest(String firstnamepatern,String lastNamepatern,  String role,
                                        TabInfo tInfo, UserInfo uInfo  )throws Exception{

        //log in to CATS
        reusable.loginToCATS("1");

        // call ssn gen to create ssn, first name and last name for user
        List<SSNFNameAndLName> list = reusable.ssnNameGenerator(firstnamepatern, lastNamepatern);
        // get first name and last name
        uInfo.ssn = list.get(0).ssn;
        uInfo.fName = list.get(0).firstName;
        uInfo.lName = list.get(0).lastName;
        uInfo.role= role;
        tInfo.role= role;
        System.out.println("SSN is " + uInfo.ssn);
        System.out.println("First name is " + uInfo.fName);
        System.out.println("Last name is " + uInfo.lName);

        // create subject for Security officer standard member
        reusable.createSubject(uInfo.ssn, uInfo.fName, uInfo.lName, "1983", "20");
        //log out user 1 from CATS
        catsLogout();
        //Log in to Portal
        jvsreusable.loginToJVS("1");
        // Create user with  role

        uInfo.uName = jvsreusable.createUser(uInfo.ssn, smoName, role);
        System.out.println("User Name name is " + uInfo.uName +"\n");
        userNameToInfoMap.put(uInfo.uName, uInfo);
        userToTabInfoMap.put(uInfo.uName, tInfo);
        //log out of portal
        jvsreusable.jvsLogout();

    }

    public void verifyLogin(List<UserInfo> list) throws Exception {

        for (String userName : userNameToInfoMap.keySet()) {
            System.out.println("Test: Verify Log in to CATS as " + userName+"\n");
            jvsreusable.loginToJVS(userName);
            System.out.println("Result: Log in to CATs as " + userName + " was successful \n");

            System.out.println("Test: Verify the links in the LHS panel \n");
            verifyLeftHandPanel(userName,userNameToInfoMap.get(userName).leftNavLinks);
            System.out.println("result: Links in the LHS panel is verified \n");

            System.out.println("Test: Verify the links in the Main page and its tabs \n");
            verifyMainPage(userNameToInfoMap.get(userName).mainPageTabs,userToTabInfoMap.get(userName).subjectTabDetails,userToTabInfoMap.get(userName).smoDetailsTabDetails);
            System.out.println("Result: Links in the Main page and its tabs are verified \n");

            System.out.println("Test:Verify SMO tree View");
            verifySMOTreeViewAndActions(userNameToInfoMap.get(userName).uName,userNameToInfoMap.get(userName).smoTreeView,userNameToInfoMap.get(userName).smoTreeActions);
            System.out.println("Result: SMO tree view has been successfully verified \n");
            jvsreusable.jvsLogout();

        }

    }

    public void verifyLeftHandPanel(String userName,String leftNavLinks) throws Exception {
        String[] leftNavValArray = leftNavLinks.split(",");
        for (String leftNavStr : leftNavValArray) {
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(JVSProperties.Container1.getProperty(), leftNavStr);
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText(leftNavStr));

            if(leftNavStr.contains("Task Inbox")) {
                verifyTaskInbox(userToTabInfoMap.get(userName).taskInbox);
            }
            if(leftNavStr.contains("Unread Notifications")) {
                verifyUnreadNotification(userToTabInfoMap.get(userName).unreadNotifications);
            }
            if(leftNavStr.contains("Search Organizations")) {
                verifyOrganizationSearch(userToTabInfoMap.get(userName).searchOrganizations);
            }
        }

    }



    public void verifyMainPage(String mainPage, String tabInfo, String smoTab) throws Exception {
        String[] mainPageValArray = mainPage.split(",");
        String[] subjectTabValArray = tabInfo.split(",");
        String[] smoTabValArray = smoTab.split(",");
        for (String mainPageStr : mainPageValArray) {
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(JVSProperties.Container2.getProperty(), mainPageStr);
            if (mainPageStr.equals("Subjects")) {
                com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText(mainPageStr));
                for (String subjectTabstr : subjectTabValArray) {
                    com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(JVSProperties.Container2.getProperty(), subjectTabstr);
                }
            }
            if (mainPageStr.equals("SMO Details")) {
                com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText(mainPageStr));
                for (String smoTabstr : smoTabValArray) {
                    com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(JVSProperties.Container2.getProperty(), smoTabstr);
                }
            }
        }
    }


    public void verifyUnreadNotification(String unreadNotifications) throws Exception {
        String[] unreadNotificationsValArray = unreadNotifications.split(",");
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Unread Notifications"));
        for (String unreadNotificationStr : unreadNotificationsValArray) {
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(JVSProperties.Container2.getProperty(), unreadNotificationStr);
        }
    }
    public void verifyTaskInbox(String taskInbox) throws Exception {
        String[] taskInboxValArray = taskInbox.split(",");
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));
        for (String taskInboxStr : taskInboxValArray) {
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(JVSProperties.Container2.getProperty(), taskInboxStr);
        }
    }
    public void verifyOrganizationSearch(String searchOrganization) throws Exception {
        String[] searchOrganizationValArray = searchOrganization.split(",");
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Search Organization"));
        for (String searchOrganizationStr : searchOrganizationValArray) {
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(JVSProperties.Container2.getProperty(), searchOrganizationStr);
        }
    }

    public void verifySMOTreeViewAndActions(String userName, String smoTreeView, String smoTreeAction) throws Exception {
        String[] smoTreeValArray = smoTreeView.split(",");
        String[] smoTreeActionValArray = smoTreeAction.split(",");

        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("View SMO Tree"));
        // Expand Tree for 1
        if(userName.equalsIgnoreCase("1")) {
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//li[contains(@id,'majorTabPanel:smoTree')]//span[contains(@class,'ui-tree-toggler ui-icon ui-icon-triangle-1-e')]"));
        }
        for (String smoTreeStr : smoTreeValArray) {
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//ul[@class='ui-tree-container']", smoTreeStr);
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:smoTree']/ul//li//span[text()='"+ smoTreeStr +"']"));
            pause(2);
            com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndRightClick(By.xpath("//*[@id='majorTabPanel:smoTree']/ul//li//span[text()='"+ smoTreeStr +"']"));
            System.out.println("SMO selected is " + smoTreeStr+"\n");
            pause(2);

            for (String smoTreeActionStr : smoTreeActionValArray) {

                com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:treeMenu']", smoTreeActionStr);
            }
        }
    }



    public void verifyJVSBasicFunctions(String ssn)throws Exception{

        //Verify Log in function
        System.out.println("Test Verify Log in function \n");
        jvsreusable.loginToJVS("1");
        System.out.println("Result: Log in- TEST PASS \n");

        //Verify create SMO function
        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        String smoName= "smokeTest"+date;
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Create SMO"));

        System.out.println("Test:Verify create SMO function \n");
        jvsreusable.createSMO("DoD Civilian Agency", "Defense Manpower Data Center", "Jan", "20", "2015", "-", smoName, "1", "United States", "SmokeTest3", "SmokeTest3", "California","22222");
//		ReusableFunctions.waitAndfindelementFromlistBasedOnvalueOfAnotherelement("SMO Tree", "//div[@id='majorTabPanel']", By.xpath("//span[contains(@class,'ui-icon-close')]"),"li");
        pause(10);
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("View SMO Tree"));
        pause(5);
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//li[contains(@id,'majorTabPanel:smoTree')]//span[contains(@class,'ui-tree-toggler ui-icon ui-icon-triangle-1-e')]"));
        ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:smoTree']", "--"+smoName+"-1");
        System.out.println("Result:create SMO function-TEST PASS \n");

        //Verify create organization function
        System.out.println("Test:Verify create organization functionality");
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Create Organization"));
        jvsreusable.createOrganization("SmokeOrg"+date, "DoD Civilian Agency", "test","1234", "test", "California", "United States");
        System.out.println("Result:Create organization functionality -TEST PASS");

        //Verify Search organization by name functionality
        System.out.println("Test: Verify Search organization by Name functionality \n");
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Search Organizations"));
        jvsreusable.searchOrganization("SmokeOrg"+date);
        System.out.println("Result:Search organization by Name functionality- TEST PASS \n");


        //Verify edit subject info functionality
        System.out.println("Test:Verify edit subject info functionality \n");
        jvsreusable.editSubjectInfo(ssn);
        System.out.println("Result:Edit subject info functionality- TEST PASS \n");

        //Create SMO relationship
        System.out.println("Test:Verify Create SMO relationship functionality \n");
        jvsreusable.createSMORelationship(CATEGORY_TYPE, ssn);
        System.out.println("Result:Create SMO relationship functionality- TEST PASS \n");

        //Add Foreign Relationship and verify

        //Add visits and verify

        //Verify Deactivate SMO function
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("View SMO Tree"));
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//li[contains(@id,'majorTabPanel:smoTree')]//span[contains(@class,'ui-tree-toggler ui-icon ui-icon-triangle-1-e')]"));
        System.out.println("Test:Verify Deactivate SMO functionality \n");
        jvsreusable.deactivateSMO("--"+smoName+"-1", "Invalid Entry", "test", "May", "1999", "20");
        //	verify that the node has been deactivated
        ReusableFunctions.waitAndfindelementFromlistBasedOnvalueOfAnotherelement("SMO Tree", "//div[@id='majorTabPanel']", By.xpath("//span[contains(@class,'ui-icon-close')]"),"li");
        pause(10);
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("View SMO Tree"));
        pause(5);
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//li[contains(@id,'majorTabPanel:smoTree')]//span[contains(@class,'ui-tree-toggler ui-icon ui-icon-triangle-1-e')]"));
        ReusableFunctions.waitAndVerify_IfContains("//span[contains(@id,':deactchildnode')]", "--"+smoName+"-1");
        System.out.println("Result:The SMO has been deactivated and is seen in the tree with the deactivate icon: Deactivate SMO functionality- TEST PASS \n");





        //Remove deactivated SMO and verify that the SMO is no longer seen in the View SMO tree
//		System.out.println("Test: Remove deactivated SMO and verify that the SMO is no longer seen in the View SMO tree \n");
//		String query = "update SMO set LGCL_DEL_IND='T'  where SMO_NAME ='"+"--"+smoName+"-1'";
//		com.iworks.DISS.test.common.functions.ReusableFunctions.sqlQueryToList("update",query, CONFIG.getProperty("Environment"),"jvs", "LGCL_DEL_IND");
//		pause(5);
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("View SMO Tree"));
//		pause(2);
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//li[contains(@id,'majorTabPanel:smoTree')]//span[contains(@class,'ui-tree-toggler ui-icon ui-icon-triangle-1-e')]"));
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//ul[@class='ui-tree-container']", "Army");
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyIfNotContains("//ul[@class='ui-tree-container']", "--"+smoName+"-1");
//		System.out.println("Result: Remove deactivated SMO and verify that the SMO is no longer seen in the View SMO tree -TEST PASS \n");

        //Deactivate Organization
        System.out.println("Test: Verify Deactivate Organization functionality \n");
        com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Search Organizations"));
        jvsreusable.deactivateOrganization("SmokeOrg"+date);
        System.out.println("Result:Deactivate Organization functionality- TEST PASS \n");

        //Verify that the deactivated organization is no longer searchable- Changed based on Defect 12757 should be searchable but not editable.
//		System.out.println("Test: Verify that the deactivated organization is no longer searchable \n");
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Search Organizations"));
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:organizationName']"));
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//*[@id='majorTabPanel:organizationName']"));
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:organizationName']"), "SmokeTest");
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:FindORG']//button[@title='Search']"));
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyText("//div[@id='majorTabPanel:findorgmessages']//span", "Search Result: No Results Found");
//		System.out.println("Result:The deactivated organization is no longer searchable -TEST PASS \n");
    }
}