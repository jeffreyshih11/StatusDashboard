package com.iworkscorp.dashboard.hudson;

//Created by jshih on 7/12/2016.


import org.junit.Test;

public class SmokeTestTest {
    SmokeTest smokeTest = new SmokeTest();




    /*private static final String CATEGORY_TYPE = JVSProperties.CivilianRetiree.getProperty();
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
    Map<String, SmokeTest.UserInfo> userNameToInfoMap = new HashMap<>();
    //Map user to tab info
    Map<String, SmokeTest.TabInfo> userToTabInfoMap = new HashMap<>();
    JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
    CATSReusableFunctions reusable = new CATSReusableFunctions();
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


    public Class<? extends SmokeTestTest> clazz = this.getClass();
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


*
     * This method will initialize the browser and maximize it before the class
     *
     * @throws Exception

    @BeforeClass
    public static void setupClass() throws Exception {
        initialize();

    }


*
     * This method will close the browser and driver after the class
     *
     * @throws Exception

    @AfterClass
    public static void teardownClass() throws Exception {

        quitBrowser();

    }*/


    @Test
    public void runDEV() throws Exception {

        boolean out = smokeTest.environmentValidationDEV();
        if(out){
            System.out.print("we gucci");
        }
        else{
            System.out.print("nooooo");
        }
    }

    @Test
    public void runDEMO() throws Exception {

        boolean out = smokeTest.environmentValidationDEMO();
        if(out){
            System.out.print("we gucci");
        }
        else{
            System.out.print("nooooo");
        }
    }
    @Test
    public void runQA() throws Exception {

        boolean out = smokeTest.environmentValidationQA();
        if(out){
            System.out.print("we gucci");
        }
        else{
            System.out.print("nooooo");
        }
    }
    @Test
    public void runUAT() throws Exception {

        boolean out = smokeTest.environmentValidationUAT();
        if(out){
            System.out.print("we gucci");
        }
        else{
            System.out.print("nooooo");
        }
    }
    @Test
    public void runBASELINE() throws Exception {

        boolean out = smokeTest.environmentValidationBASELINE();
        if(out){
            System.out.print("we gucci");
        }
        else{
            System.out.print("nooooo");
        }
    }
    @Test
    public void runGAT() throws Exception {

        boolean out = smokeTest.environmentValidationGAT();
        if(out){
            System.out.print("we gucci");
        }
        else{
            System.out.print("nooooo");
        }
    }
    @Test
    public void runLOCAL() throws Exception {

        boolean out = smokeTest.environmentValidationLOCAL();
        if(out){
            System.out.print("we gucci");
        }
        else{
            System.out.print("nooooo");
        }
    }

}
