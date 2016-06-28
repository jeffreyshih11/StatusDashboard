package com.iworks.DISS.test.catsSmokeTests;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.TestBase;

/**
 * This class will perform the following actions to test major functionalities
 * in CATS application Functionalities included in smoke test:
 *  Create test data required programmatically so the test can be run without any human intervention and multiple times- different user types in CATS
 *  Verify Log in as each user role and verify the LHS links[ which changes based on role]
 *  Verify Hierarchy-
 *   a. Create a division, branch, team 
 *   b. add div chief, branch chief, team chief and team member 
 *   c. Verify division details, branch details, team details, team member details and edit team member details
 *  Verify basic functions in CATS 
 *   a. Create a subject 
 *   b. Verify subject details 
 *   c. Create SMO relationship for the subject 
 *   d. Create a case for the subject 
 *   e. Verify case details 
 *   f. Verify that the manual case has been auto assigned to the eligible adjudicator 
 *   g. Process the case through normal flow and adjudicate and close case with determination = favorable 
 *   h. Create second case for same subject 
 *   i. Process the case such that it is referred to Due process 
 *   j. Initiate SOR flow
 *   k. Upload required documents 
 *   l. Complete SOR flow in JVS 
 *   m. Initiate LOR flow
 *   n. Upload required docs 
 *   o. Complete LOR flow 
 *   p. Close the case with determination = Revoked 
 *   q. Create third case 
 *   r. Process case through Due process ,initiate and complete SOR flow, Initiate and complete LOR flow such that the case will go to Appeals process 
 *   s. Upload required documents as process team member 
 *   t. Close case in appeals process with determination = Favorable
 * 
 * @author vshivaraman
 */

public class CATSSmokeTestWithReport extends TestBase {
	private static final String EXECUTIVE_CHIEF = "1";
	private static final String EXECUTIVE_CHIEF_ROLE="Executive Chief";
	private static final String CATEGORY_TYPE = JVSProperties.CivilianRetiree.getProperty();
	private static final String SECURITY_OFFICER_STANDARD_ROLE = "Security Officer Standard";
	private static final String TECT_ROLE = "TECT";
	private static final String TRAINEE_ROLE = "Trainee";
	private static final String CATS_APPLICATION_ADMINISTRATOR_ROLE = "CATS Application Administrator";
	private static final String QUALITY_CONTROL_ROLE = "Quality Control Reviewer";
	private static final String ITTFSCREENER_ROLE = "ITTF Screener";
	private static final String GENERAL_COUNSEL_ROLE = "General Counsel";
	private static final String SUB_BIRTH_COUNTRY_NAME = "United States";
	private static final String TEAM_NAME = "TEAM-Team Smoke test";
	private static final String PROCESS_TEAM_NAME="TEAM-Process Team";
	private static final String BRANCH_NAME = "BRANCH-Branch Smoke test";
	private static final String DIVISION_NAME = "DIVISION-Division Smoke Test";
	private static final String CAF_NAME = "CAF-DoD CAF";
	private static final String PROCESS_TEAM_ROLE = "Process Team";
	private static final String ADJUDICATOR_ROLE = "Adjudicator";
	private static final String TEAM_CHIEF_ROLE = "Team Chief";
	private static final String BRANCH_CHIEF_ROLE = "Branch Chief";
	private static final String DIVISION_CHIEF_ROLE = "Division Chief";
	
	//Map User name to User Info
	Map<String, UserInfo> userNameToInfoMap = new HashMap<>();
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	String className ;
	long startTime;
	long endTime;
	
		
	public static class UserInfo {
		public String fName;
		public String lName;
		public String uName;
		public String ssn;
		public String role;
		public String leftNavLinks;
		public String reportType;
		public String catsStandardReportName;
		public String catsManagementReportName;
		public String catsUserAuditReportName;
	}

	public Class<? extends CATSSmokeTestWithReport> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	
	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		className = clazz.getCanonicalName();
		DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		DateFormat dateFormat1 = new SimpleDateFormat("yyMMdd");
		String date1 = dateFormat1.format(cal.getTime());
		String environment = CONFIG.getProperty("Environment");
		File targetFile = new File("C://AutomationOutput//" + environment+"//"+"TestedByBuild"+"//"+ className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc");
		reusable.createLogFile(className, name,targetFile );
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

	/**
	 * Test to verify major functionalities in CATS application: Verify manage
	 * hierarchy, Verify log ins as different CATs roles ,Verify CATS basic
	 * functions ,Verify delete Actions of hierarchy added
	 * 
	 * @throws Exception
	 */
	@Test
	public void catsSmokeTest() throws Exception {
		//Instance of class SSNFNameAndLName
		SSNFNameAndLName sub = new SSNFNameAndLName();
		// Call the createtestData function to create users with different roles
		List<UserInfo> list = createTestData(sub);
		//Map role to userInfo
		Map<String, UserInfo> roleToUserInfomap = new HashMap<String, CATSSmokeTestWithReport.UserInfo>();
		//for eachuserInfo in the list map the role to info
		for (UserInfo userInfo : list) {
			roleToUserInfomap.put(userInfo.role, userInfo);
		}

		// Verify manageHierarchy function
		// call the verifyManageHierarchy to verify the hierarchy functions
		verifyManageHierarchy(CAF_NAME, "Kelly Brown", DIVISION_NAME, roleToUserInfomap.get(DIVISION_CHIEF_ROLE).fName, roleToUserInfomap.get(DIVISION_CHIEF_ROLE).lName, BRANCH_NAME, roleToUserInfomap.get(BRANCH_CHIEF_ROLE).fName, roleToUserInfomap.get(BRANCH_CHIEF_ROLE).lName, TEAM_NAME, roleToUserInfomap.get(TEAM_CHIEF_ROLE).fName, roleToUserInfomap.get(TEAM_CHIEF_ROLE).lName, roleToUserInfomap.get(ADJUDICATOR_ROLE).fName, roleToUserInfomap.get(ADJUDICATOR_ROLE).lName,roleToUserInfomap.get(PROCESS_TEAM_ROLE).fName,roleToUserInfomap.get(PROCESS_TEAM_ROLE).lName);
		
		// Verify Log in function as different user roles 
		// Call the verify log in function to verify log in as different users
		verifyLogin(list);

		// Verify basic functions in CATS application
		// Call the verifyCATSBasicFunctions
		verifyCATSBasicFunctions(sub.ssn, sub.firstName, sub.lastName, SUB_BIRTH_COUNTRY_NAME, roleToUserInfomap.get(ADJUDICATOR_ROLE).uName, roleToUserInfomap.get(PROCESS_TEAM_ROLE).uName, roleToUserInfomap.get(SECURITY_OFFICER_STANDARD_ROLE).uName);

		// Verify delete function for hierarchy added
		// Call the deleteAction function to delete the hierarchy created
		verifyDeleteActions(CAF_NAME, DIVISION_NAME, BRANCH_NAME, TEAM_NAME, roleToUserInfomap.get(ADJUDICATOR_ROLE).fName, roleToUserInfomap.get(ADJUDICATOR_ROLE).lName);

	}


	/**
	 * This function will return a list of user information
	 * of the users created within the function
	 * 
	 * @return List<FNameAndLName>
	 * @throws Exception
	 */
	public List<UserInfo> createTestData(SSNFNameAndLName sub) throws Exception {
		// Log in to CATS as user 1
		reusable.loginToCATS(EXECUTIVE_CHIEF);
		List<UserInfo> retList = new ArrayList<>();
		//Executive chief
		UserInfo uInfo = new UserInfo();
		uInfo.leftNavLinks="";
		uInfo.reportType="CATS Standard  Reports,CATS Management Reports,CATS User Audit Reports";
		uInfo.catsStandardReportName="Guidelines,Active Case,Case Action Detail,Case Action Summary,Cases In Appeals,Case Status,Consolidated Ingest,ITTF,OPM Delivery Projection,RAISE,Workforce Performance";
		uInfo.catsManagementReportName="Case Aging,Final Actions,QC Summary,Workflow Detail";
		uInfo.catsUserAuditReportName="Active User Roles And Optional Permissions,User Account Audit";
		uInfo.uName = EXECUTIVE_CHIEF;
		uInfo.role = EXECUTIVE_CHIEF_ROLE;
		userNameToInfoMap.put(uInfo.uName, uInfo);
		
		//Division Chief User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Manage Templates,Search SMOs";
		uInfo.reportType="CATS Standard  Reports,CATS Management Reports,CATS User Audit Reports";
		uInfo.catsStandardReportName="Guidelines,Active Case,Case Action Detail,Case Action Summary,Cases In Appeals,Case Status,Consolidated Ingest,ITTF,OPM Delivery Projection,RAISE,Workforce Performance";
		uInfo.catsManagementReportName="Case Aging,Final Actions,QC Summary,Workflow Detail";
		uInfo.catsUserAuditReportName="Active User Roles And Optional Permissions,User Account Audit";
		createUsersForCATSSmokeTest("DivChfFName", "DivChfLName",  DIVISION_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
			
		//Branch Chief User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Search SMOs";
		uInfo.reportType="CATS Standard  Reports,CATS Management Reports,CATS User Audit Reports";
		uInfo.catsStandardReportName="Guidelines,Active Case,Case Action Detail,Case Action Summary,Cases In Appeals,Case Status,Consolidated Ingest,ITTF,OPM Delivery Projection,RAISE,Workforce Performance";
		uInfo.catsManagementReportName="Case Aging,Final Actions,QC Summary,Workflow Detail";
		uInfo.catsUserAuditReportName="Active User Roles And Optional Permissions,User Account Audit";
		createUsersForCATSSmokeTest("BrcChfFName", "BrcChfLName",  BRANCH_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
		
		//Team Chief User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Inbox,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Search SMOs";
		uInfo.reportType="CATS Standard  Reports,CATS Management Reports,CATS User Audit Reports";
		uInfo.catsStandardReportName="Guidelines,Active Case,Case Action Detail,Case Action Summary,Cases In Appeals,Case Status,Consolidated Ingest,ITTF,OPM Delivery Projection,RAISE,Workforce Performance";
		uInfo.catsManagementReportName="Case Aging,Final Actions,QC Summary,Workflow Detail";
		uInfo.catsUserAuditReportName="Active User Roles And Optional Permissions,User Account Audit";
		createUsersForCATSSmokeTest("TeamChFName", "TeamChLName", TEAM_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
	
		//Adjudicator User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Task Inbox,Unread Notifications:,Productivity,Case Inbox,Subject Management,SMO";
		uInfo.reportType="CATS Standard  Reports";
		uInfo.catsStandardReportName="Active Case,Case Action Detail,Case Action Summary,Case Status,Workforce Performance";
		uInfo.catsManagementReportName="";
		uInfo.catsUserAuditReportName="";
		createUsersForCATSSmokeTest("AdjuFName", "AdjuLName",  ADJUDICATOR_ROLE, uInfo);
		retList.add(uInfo);
		
		//Smoke Test Subject 
		// Call ssn generator to create snn, first and last names for smoke test
		// subject
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("smokeFName", "smokeLName");
		sub.ssn = list.get(0).ssn;
		sub.firstName = list.get(0).firstName;
		sub.lastName = list.get(0).lastName;
		//Create the subject
		// Verify that create subject function is working successfully
		System.out.println("Test:Verify that create subject function is working correctly \n");
		reusable.createSubject(sub.ssn, sub.firstName, sub.lastName,"1983","Oct","20","United States");
		System.out.println("Result:Create subject function is working successfully \n");

		

		// Process team user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Inbox,Manual Assignment,Case Inbox,Create Subject,Search SMOs";
		createUsersForCATSSmokeTest("ProTeaFName",	"ProTeaLName",  PROCESS_TEAM_ROLE, uInfo);
		retList.add(uInfo);


		// Create general counsel user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Inbox,Case Inbox,Search SMOs";
		createUsersForCATSSmokeTest("GenCouFName",	"GenCouLName",  GENERAL_COUNSEL_ROLE, uInfo);
		retList.add(uInfo);

		
		// Create ITTFScreener
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications,Case Inbox,Search SMOs";//ITTF Inbox
		createUsersForCATSSmokeTest("ITTFScFName",	"ITTFScLName",  ITTFSCREENER_ROLE, uInfo);
		retList.add(uInfo);

		
		// Create QualityControl
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications,Case Inbox,Search SMOs";
		createUsersForCATSSmokeTest("QuaConFName",	"QuaConLName", QUALITY_CONTROL_ROLE, uInfo);
		retList.add(uInfo);

		// Trainee
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications,Case Inbox,Create Subject,Search SMOs";
		createUsersForCATSSmokeTest("TraFName",	"TraLName",  TRAINEE_ROLE, uInfo);
		retList.add(uInfo);

		// CATS Application Administrator user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications,Manage Hierarchy,Manual Assignment,Case Inbox,Create Subject,Manage Templates,Search SMOs";
		createUsersForCATSSmokeTest("CatsAAFName","CatsAALName", CATS_APPLICATION_ADMINISTRATOR_ROLE, uInfo);
		retList.add(uInfo);

		// TECT user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Case Inbox,Search SMOs";
		createUsersForCATSSmokeTest("TECTUrFName",	"TECTUrLName",  TECT_ROLE, uInfo);
		retList.add(uInfo);
		
		// Create SO
		uInfo = new UserInfo();
		createUsersForCATSSmokeTest("SOStanfFName", "SOStanLName",  SECURITY_OFFICER_STANDARD_ROLE, uInfo);
		retList.add(uInfo);
		catsLogout();
		jvsreusable.loginToJVS(EXECUTIVE_CHIEF);
		// Create user with standard SO member role
		jvsreusable.createUser(uInfo.ssn, uInfo.uName, "DISS", SECURITY_OFFICER_STANDARD_ROLE);
		System.out.println("Security Officer Standard user name is "+ uInfo.uName+"\n");
		jvsreusable.jvsLogout();

		// return list of user information
		return retList;

	}

	/**
	 * This function will create the subject and user for the subject based on firstname,last name,role passed
	 * @param firstnamepatern
	 * @param lastNamepatern
	 * @param userNamePattern
	 * @param role
	 * @param uInfo
	 * @throws Exception
	 */
	public void createUsersForCATSSmokeTest(String firstnamepatern,String lastNamepatern,  String role,
			 UserInfo uInfo  )throws Exception{
			
					
			// call ssn gen to create ssn, first name and last name for user
			List<SSNFNameAndLName> list = reusable.ssnNameGenerator(firstnamepatern, lastNamepatern);
			// get first name and last name
			uInfo.ssn = list.get(0).ssn;
			uInfo.fName = list.get(0).firstName;
			uInfo.lName = list.get(0).lastName;
			uInfo.role= role;
			System.out.println("Role is :"+role);
			System.out.println("SSN is " + uInfo.ssn);
			System.out.println("First name is " + uInfo.fName);
			System.out.println("Last name is " + uInfo.lName);
			int index = (list.get(0).index);
			String lastFour = ("000" + Integer.toString(index));
			
			//create user
			uInfo.uName = uInfo.fName.substring(0, 6)+(lastFour).substring(lastFour.length() - 4);
			System.out.println("Username is "+uInfo.uName+"\n");
			userNameToInfoMap.put(uInfo.uName, uInfo);
			
			// create subject for user
			reusable.createSubject(uInfo.ssn, uInfo.fName, uInfo.lName, "1983", "20");
			if(role.equals(SECURITY_OFFICER_STANDARD_ROLE)) {
				return ;
			}
			
			
			reusable.createUser(uInfo.ssn, 22222, "DoD CAF", uInfo.uName,role);
			System.out.println(uInfo.role+" userName is " + uInfo.uName+"\n");
			System.out.println();
			
			
		}

	/**
	 * This function will verify the log in function for each user in the list passed to it
	 * @param list
	 * @throws Exception
	 */
	public void verifyLogin(List<UserInfo> list) throws Exception {
		
		for (String userName : userNameToInfoMap.keySet() ) {
			if(userName.contains("SOStan")) {
				continue;
			}
			System.out.println("Test: Verify Log in to CATS as " + userName+"\n");
			reusable.loginToCATS(userName);
			System.out.println("Result: Log in to CATs as " + userName + " was successful \n");
			
			
			System.out.println("Test: Verify the links in the LHS panel \n");
			verifyLeftHandPanel(userNameToInfoMap.get(userName).leftNavLinks);
			System.out.println("result: Links in the LHS panel is verified \n");
			if(userNameToInfoMap.get(userName).role.contains(EXECUTIVE_CHIEF_ROLE)||userNameToInfoMap.get(userName).role.contains(DIVISION_CHIEF_ROLE)||userNameToInfoMap.get(userName).role.contains(BRANCH_CHIEF_ROLE)||userNameToInfoMap.get(userName).role.contains(ADJUDICATOR_ROLE)||userNameToInfoMap.get(userName).role.contains(TEAM_CHIEF_ROLE)) {
			System.out.println("Test: Verfy CATS Reports for the role "+userNameToInfoMap.get(userName).role);
			verifyCATSReports(userName, userNameToInfoMap.get(userName).reportType,userNameToInfoMap.get(userName).catsStandardReportName,userNameToInfoMap.get(userName).catsManagementReportName,userNameToInfoMap.get(userName).catsUserAuditReportName,userNameToInfoMap.get(userName).role);
			System.out.println("Result:CATS Reports for the role "+userNameToInfoMap.get(userName).role+" was successful-TEST PASS \n");

			}
			catsLogout();

		}

	}

	/**
	 * This function will verify each left hand side panel links 
	 * @param leftNavLinks
	 * @throws Exception
	 */
	public void verifyLeftHandPanel(String leftNavLinks) throws Exception {
		String [] leftNavValArray = leftNavLinks.split(",");
		for (String leftNavStr : leftNavValArray) {
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container1.getProperty(), leftNavStr);
		}

	}

	/**
	 * This function will verify the hierarchy functions like add division,branch,team,team member,edit team member
	 * add division chief,team chief,branch chief,verify CAFdetails,division details,branch details,team details to the hierarchy created
	 * @param cafName
	 * @param cafChiefName
	 * @param divName
	 * @param divChiefFname
	 * @param divChiefLName
	 * @param branchName
	 * @param branchChiefFName
	 * @param branchChiefLName
	 * @param teamName
	 * @param teamChiefFName
	 * @param teamChiefLName
	 * @param teamMemberFName
	 * @param teamMemberLName
	 * @throws Exception
	 */
	public void verifyManageHierarchy(String cafName, String cafChiefName, String divName, String divChiefFname, String divChiefLName, String branchName, String branchChiefFName, String branchChiefLName, String teamName, String teamChiefFName, String teamChiefLName, String teamMemberFName, String teamMemberLName,String processTeamMemberFName,String porcessTeamMemberLName) throws Exception {

		// Log into CATS as 1
		reusable.loginToCATS(EXECUTIVE_CHIEF);
		// Verify that Manage hierarchy is seen in the LHS panel
		System.out.println("Verify if manage hierarchy is seen \n");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container1.getProperty(), "Manage Hierarchy");
		System.out.println("Result: Manage hierarchy is seen \n");
		
		// Verify that Manual Assignment is seen in the LHS panel
		System.out.println("Verify if manual assignment is seen \n");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container1.getProperty(), "Manual Assignment");
		System.out.println("Result:Manual assignment is seen \n");
		
		// Click on the manage hierarchy link
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));

		// Verify that Hierarchy Layout is seen
		System.out.println("Verify if hierarchy layout is seen. \n");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Hierarchy Layout");
		System.out.println("Result: User is able to view CAF Hiearachy layout \n");
		

		// Verify CAF tree tab is seen
		System.out.println("Verify if CAF Tree is seen \n");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "CAF Tree");
		System.out.println("Result: CAF Tree is seen \n");
		System.out.println();

		// Verify Name is seen
		System.out.println("Verify if Name is seen");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Name");
		System.out.println("Result: Name is seen");
		System.out.println();

		// Verify Chief is seen
		System.out.println("Verify if Chief column is seen");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Chief");
		System.out.println("Result:Chief column is seen");
		System.out.println();
		
		// Verify Deputy is seen
		System.out.println("Verify if Deputy column is seen");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Deputy");
		System.out.println("Result:Deputy column is seen");
		System.out.println();

		// Verify CAF-DoD CAF is seen
		System.out.println("Verify if 'CAF-DoD CAF' is seen");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td", CAF_NAME);

		// Verify if Chief search icon exists in CAF Hierarchy
		System.out.println("Verify if Chief search icon exists in CAF Hierarchy");
		System.out.println("Result:");
		WebElement chiefSearchElem = driver.findElement(By.xpath("//*[@id='majorTabPanel:cafHierarchyTreeTableId:0:buttonLookupChiefUser']"));
		Assert.assertNotNull(chiefSearchElem);
		System.out.println("Search Icon is there in Hierarchy Layout in Chief column");
		System.out.println();

		// Verify if Deputy search icon exists in CAF Hierarchy
		System.out.println("Verify if Deputy search icon exists in CAF Hierarchy");
		System.out.println("Result:");
		WebElement deputySearchElem = driver.findElement(By.xpath("//*[@id='majorTabPanel:cafHierarchyTreeTableId:0:buttonLookupDeputyChiefUser']"));
		Assert.assertNotNull(deputySearchElem);
		System.out.println("Search Icon is there in Hierarchy Layout in Deputy column");
		System.out.println();

		// Verify Save button is seen in Layout
		System.out.println("Verify if Save button exists in layout");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Save");

		// Verify cancel button is seen in Layout
		System.out.println("Verify if cancel button exists in layout");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Cancel");

		// Add division
		System.out.println("Add Division by the name of Division Smoke Test");
		System.out.println("Result:");
		reusable.addDivision(cafName, "Division Smoke Test");

		// Add division chief
		System.out.println("Add Division chief ");
		System.out.println("Result:");
		reusable.addDivisionChief(cafName, divName, divChiefFname, divChiefLName);

		// Add branch
		System.out.println("Add branch");
		System.out.println("Result:");
		reusable.addBranch(cafName, divName, "Branch Smoke test");

		// Add Branch chief
		System.out.println("Add branch chief");
		System.out.println("Result:");
		reusable.addBranchChief(cafName, divName, branchName, branchChiefFName, branchChiefLName);

		// Add team
		System.out.println("Add team");
		System.out.println("Result:");
		reusable.addTeam(cafName, divName, branchName, "Team Smoke test");

		// Add Team chief
		System.out.println("Add Team chief");
		System.out.println("Result:");
		reusable.addTeamChief(cafName, divName, branchName, teamName, teamChiefFName, teamChiefLName);

		// Add team member
		System.out.println("Add team member");
		System.out.println("Result:");
		reusable.addTeamMember(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);

		// Verify CAF Details
		System.out.println("Verify CAF details");
		System.out.println("Result:");
		String cafChiefFName = cafChiefName.substring(0, 5);
		String cafChiefLName = cafChiefName.substring(6);
		reusable.verifyCAFDetails(cafName, cafChiefFName, cafChiefLName, teamMemberFName, teamMemberLName);
		//

		// Verify division details
		System.out.println("Verify division details");
		System.out.println("Result:");
		reusable.verifyDivisionDetails(cafName, divName, divChiefFname, divChiefLName, teamMemberFName, teamMemberLName);

		// Verify branch details
		System.out.println("Verify branch details");
		System.out.println("Result:");
		reusable.verifyBranchDetails(cafName, divName, branchName, branchChiefFName, branchChiefLName, teamMemberFName, teamMemberLName);

		// Verify team details
		System.out.println("Verify Team Details page");
		System.out.println();
		reusable.verifyTeamDetails(cafName, divName, branchName, teamName, teamChiefFName, teamChiefLName, teamMemberFName, teamMemberLName);

		// verify team member details
		System.out.println("Verify team member details");
		System.out.println("Result:");
		reusable.verifyTeamMemberDetails(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);

		// edit team member info
		System.out.println("Edit team member info");
		System.out.println("Result:");
		reusable.editTeamMemberDetails(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName, "Yes", "Yes");
		
		//add process team member to process team hierarchy
		System.out.println("Add process tema member to process team hierarchy");
		reusable.addProcessTeamMember(cafName,PROCESS_TEAM_NAME,processTeamMemberFName,porcessTeamMemberLName);
		System.out.println("Process Team member added to Process team");

		catsLogout();

	}

/**
 * This function will delete the team member,team,branch and division from the hierarchy
 * @param cafName
 * @param divName
 * @param branchName
 * @param teamName
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
	public void verifyDeleteActions(String cafName, String divName, String branchName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {

		// Log into CATS as 1
		reusable.loginToCATS(EXECUTIVE_CHIEF);
		// Delete team member
		System.out.println("Delete team member");
		System.out.println("Result:");
		reusable.deleteTeamMember(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);

		// delete team
		System.out.println("Delete team");
		System.out.println("Result:");
		reusable.deleteTeam(cafName, divName, branchName, teamName);

		// delete branch
		System.out.println("Delete branch");
		System.out.println("Result:");
		reusable.deleteBranch(cafName, divName, branchName);

		// delete division
		System.out.println("Delete division");
		System.out.println("Result:");
		reusable.deleteDivision(cafName, divName);

		catsLogout();
	}

/**
 * This function will verify the basic functions in CATs like
 *   a. Create a subject 
 *   b. Verify subject details 
 *   c. Create SMO relationship for the subject 
 *   d. Create first case for the subject 
 *   e. Verify case details 
 *   f. Verify that the manual case has been auto assigned to the eligible adjudicator 
 *   g. Process the case through normal flow and adjudicate and close case with determination = favorable 
 *   h. Create second case for same subject 
 *   i. Process the case such that it is referred to Due process 
 *   j. Initiate SOR flow
 *   k. Upload required documents 
 *   l. Complete SOR flow in JVS 
 *   m. Initiate LOR flow
 *   n. Upload required docs 
 *   o. Complete LOR flow 
 *   p. Close the case with determination = Revoked 
 *   q. Create third case 
 *   r. Process case through Due process ,initiate and complete SOR flow, Initiate and complete LOR flow such that the case will go to Appeals process 
 *   s. Upload required documents as process team member 
 *   t. Close case in appeals process with determination = Favorable
 * 
 * @param subjectSSN
 * @param subjectFName
 * @param subjectLName
 * @param subjectBirthCountry
 * @param adjudicatorUserName
 * @param procTeamUserName
 * @param standardSOUserName
 * @throws Exception
 */
	public void verifyCATSBasicFunctions(String subjectSSN, String subjectFName, String subjectLName, String subjectBirthCountry, String adjudicatorUserName, String procTeamUserName, String standardSOUserName) throws Exception {

		// Verify Cats log in function
		System.out.println("Test: Verify CATs log in function");
		reusable.loginToCATS(EXECUTIVE_CHIEF);
		System.out.println("Result:Logged in to CATS as user 1 correctly");

		// Verify that create subject function is working successfully
//		System.out.println("Test:Verify that create subject function is working correctly");
//		reusable.createSubject(subjectSSN, subjectFName, subjectLName, "1983", "20", subjectBirthCountry);
//		System.out.println("Result:Create subject function is working successfully");

		// Verify search subject function is successful
		System.out.println("Test:Verify search subject function is successful");
		reusable.searchSubjectCATS(subjectSSN);
		System.out.println("Result:Search subject based on SSN function is working correctly");
	
		//Verify Add citizenship function
		System.out.println("test: Verify add citizenship funnctionality");
		reusable.addCitizenshipForSubject(subjectSSN, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		System.out.println("Result: Subjects citizenship was added successfully as Natural born US Citizen");
	
		// Verify Subject details
		System.out.println("Test: Verify Subject details");
		verifySubjectdetails(subjectSSN);
		System.out.println("Result:Subject details have been verified");
		

		
		// Verify CATs log out function
		System.out.println("Test: Verify CATs log out function");
		catsLogout();
		System.out.println("Result:Logged out of CATS as user 1 correctly");

		// Verify Log in to JVS as 1
		System.out.println("Test: Verify Log in to JVS Portal application as user 1");
		jvsreusable.loginToJVS(EXECUTIVE_CHIEF);
		System.out.println("Result:Log in to JVS Portal application as user 1 was successful");

		// Verify Create SMO relationship function between the case and the SO
		System.out.println("Test: Verify Create SMO relationship function between the case and the SO");
		jvsreusable.createSMORelationship(CATEGORY_TYPE, subjectSSN);
		System.out.println("Result: Create SMO relationship function between the case and the SO was successful");

		// Verify Log out of JVS function
		System.out.println("Test: Verify Log out of JVS function");
		jvsreusable.jvsLogout();
		System.out.println("Result: Log out of JVS function was successful");

		// Log in to CATS UAT as user 1
		reusable.loginToCATS(EXECUTIVE_CHIEF);

		// Verify create case function for the subject is working
		System.out.println("Test: Verify create case function for the subject is working");
		reusable.createCaseForSubject(subjectSSN, CATSProperties.DivisionSmoke.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());
		System.out.println("Result: Create case function for the subject is working");

		// Get the case ID of the cases created
		String caseID1 = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("First case ID is : " + caseID1);

		// Log out of CATs as user 1
		catsLogout();

		// log in to CATs as adjudicator
		reusable.loginToCATS(adjudicatorUserName);

		// Verify case details
		System.out.println("Test: Verify case details");
		verifyCaseDetails(caseID1);
		System.out.println("Result:Case details have been verified");

		// Click on case inbox tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='container_2']//a[text()='Case Inbox']"));

		// Verify case is auto assigned and seen in Open case Datatable based on
		// SSN.
		System.out.println("Test:Verify case is auto assigned and seen in Open case Datatable based on subject SSN");
		String searchableSsn = subjectSSN.substring(0, 3) + "-" + subjectSSN.substring(3, 5) + "-" + subjectSSN.substring(5);
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:SubTable_data']", searchableSsn);
		System.out.println("Resultest:Case is auto assigned and seen in Open case Datatable based on subject SSN");

		// Verify that the case can be successfully passed through normal
		// adjudication flow and closed with determination = favorable
		// Select the case from the open case data table based on ssn
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable_data']");
		System.out.println("Test:Verify that the case can be successfully passed through normal adjudication flow and closed with determination = favorable");
		reusable.determinationFavorable(caseID1, CATSProperties.LevelCodeConfidential.getProperty());
		System.out.println("Result: Case: " + caseID1 + "has been closed successfully with determination = Favorable in normal flow");

		// Log out adjudicator
		catsLogout();

		// Log in to CATs as 1
		reusable.loginToCATS(EXECUTIVE_CHIEF);

		// Create second case for same subject
		reusable.createCaseForSubject(subjectSSN, CATSProperties.DivisionSmoke.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());

		// Get the case ID of the cases created
		String caseID2 = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("Second case ID is : " + caseID2);
		// Log user 1 out of CATs
		catsLogout();

		// Log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorUserName);

		// click on case inbox tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='container_2']//a[text()='Case Inbox']"));

		// Verify second case is auto assigned and seen in Open case Datatable
		// based on SSN.
		String searchableSsn1 = subjectSSN.substring(0, 3) + "-" + subjectSSN.substring(3, 5) + "-" + subjectSSN.substring(5);
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:SubTable_data']", searchableSsn1);

		// Select the case from the open case data table based on ssn
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(searchableSsn1, "//*[@id='majorTabPanel:SubTable_data']");

		// Verify that the second case goes through Due process and adjudicated
		// in normal flow and closed with determination = Revoked
		System.out.println("Test:Verify that the second case goes through Due process and adjudicated in normal flow and closed with determination = Revoked");
		System.out.println("Sub test:Verify that case phase is Due process");
		reusable.determinationsNotFavorable(caseID2, CATSProperties.DeterminationNone.getProperty(), CATSProperties.AllegiancePanel.getProperty(), CATSProperties.DisqualifyingCondition.getProperty(), CATSProperties.CivilianMPCCode.getProperty());
		System.out.println("Result:Case is in Due process phase");

		// Initiate CATS SOR Flow
		System.out.println("Sub test: Verify CATS SOR flow is initiated by adjudicator and SOR document is uploaded");
		reusable.sorFlow(subjectSSN, CATSProperties.SOROption.getProperty());
		System.out.println("Result: CATS SOR flow has been initiated by adjudicator and SOR document is uploaded");

		// Logout as adjudicator
		catsLogout();

		// Log in to Portal as SO
		jvsreusable.loginToJVS(standardSOUserName);

		// Call JVSSORFlow method
		System.out.println("Sub Test:Verify JVS SOR flow has been claimed, acknowledgement receipt and subject response documents were uploaded and task closed by security officer in Portal application");
		jvsreusable.jvsSORflow(subjectSSN, subjectFName, subjectLName);
		System.out.println("Result:JVS SOR flow has been claimed, acknowledgement receipt and subject response documents were uploaded and task closed by security officer in Portal application");

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS
		reusable.loginToCATS(adjudicatorUserName);

		// initiate LOD/LOR flow
		System.out.println("Sub Test:Verify LOD/LOR flow has been initiated as adjudicator, LOR document is uploaded and sent to SO");
		reusable.lodLORFlow(subjectSSN, CATSProperties.LODLOROption.getProperty());
		System.out.println("Result:Verify LOD/LOR flow has been initiated as adjudicator, LOR document is uploaded and sent to SO");

		// Log out Adjudicator from CATS
		catsLogout();

		// Log in to JVS
		jvsreusable.loginToJVS(standardSOUserName);

		// Call the JVSLOD LOR method to complete the LOD/LOR flow
		System.out.println("Sub Test:Verify JVS LOD/LOR flow has been claimed, acknowledgement receipt and subject response documents were uploaded and task closed by security officer in Portal application");
		jvsreusable.jvsLODLORflow(subjectSSN, subjectFName, subjectLName, "No");
		System.out.println("Result:JVS LOD/LOR flow has been claimed, acknowledgement receipt and subject response documents were uploaded and task closed by security officer in Portal application");

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS
		reusable.loginToCATS(adjudicatorUserName);

		// close the case with determination = revoked
		caseRevoked(searchableSsn1);
		System.out.println("case :" + caseID2 + " is closed successfuly processed through due process and closed with determiation = Revoked");
		System.out.println("Result:The second case goes through Due process and adjudicated in normal flow and closed with determination = Revoked");

		// log out adjudicator
		catsLogout();

		// log in as user 1
		reusable.loginToCATS(EXECUTIVE_CHIEF);

		// create 3rd case for same subject
		// Create Third case for same subject
		reusable.createCaseForSubject(subjectSSN, CATSProperties.DivisionSmoke.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());

		// Get the case ID of the cases created
		String caseID3 = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("Third case ID is : " + caseID3);
		// Log user 1 out of CATs
		catsLogout();

		// Log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorUserName);

		// click on case inbox tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='container_2']//a[text()='Case Inbox']"));

		// Verify third case is auto assigned and seen in Open case Datatable
		// based on SSN.
		String searchableSsn3 = subjectSSN.substring(0, 3) + "-" + subjectSSN.substring(3, 5) + "-" + subjectSSN.substring(5);
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:SubTable_data']", searchableSsn3);

		// Select the case from the open case data table based on ssn
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(searchableSsn3, "//*[@id='majorTabPanel:SubTable_data']");

		// Verify that the Third case goes through Due process and adjudicated
		// in Appeals flow and closed with determination = favorable
		System.out.println("Test:Verify that the Third case goes through Due process and adjudicated in Appeals flow and closed with determination = favorable");

		System.out.println("Sub test:Verify that case phase is Due process");
		reusable.determinationsNotFavorable(caseID3, CATSProperties.DeterminationNone.getProperty(), CATSProperties.AllegiancePanel.getProperty(), CATSProperties.DisqualifyingCondition.getProperty(), CATSProperties.CivilianMPCCode.getProperty());
		System.out.println("Result:Case is in Due process phase");

		// Initiate CATS SOR Flow
		reusable.sorFlow(subjectSSN, CATSProperties.SOROption.getProperty());

		// Logout as adjudicator
		catsLogout();

		// Log in to Portal as SO
		jvsreusable.loginToJVS(standardSOUserName);

		// Call JVSSORFlow method
		jvsreusable.jvsSORflow(subjectSSN, subjectFName, subjectLName);

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS
		reusable.loginToCATS(adjudicatorUserName);

		// initiate LOD/LOR flow
		reusable.lodLORFlow(subjectSSN, CATSProperties.LODLOROption.getProperty());

		// Log out Adjudicator from CATS
		catsLogout();

		// Log in to JVS
		jvsreusable.loginToJVS(standardSOUserName);

		// Call the JVSLOD LOR method to complete the LOD/LOR flow
		System.out.println("Test: Verify that the case is sent to appeals process by SO form portal");
		jvsreusable.jvsLODLORflow(subjectSSN, subjectFName, subjectLName, "Yes");
		System.out.println("Result: The case is sent to appeals process by SO form portal");

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log into CATS as Process team member
		reusable.loginToCATS(procTeamUserName);

		// Call the lodLORappeals process as process team member
		System.out.println("Test: Verify that the case is claimed,NOIA document is uploaded and case sent to Adjudicaotr by process tema member");
		reusable.lodLORProcessTeamAppealsFlow(subjectFName, subjectLName);
		System.out.println("Result:The case is claimed,NOIA document is uploaded and case sent to Adjudicaotr by process tema member");

		// Log out process team useriga
		catsLogout();

		// Log into CATS as Adjudicator
		reusable.loginToCATS(adjudicatorUserName);

		// Call the appealsadjudication method to grant favorable determination
		// and update the eligibility after adjudication

		reusable.appealAdjudicationAsAdjudicator(caseID3, "Favorable", CATSProperties.LevelConfidential.getProperty(), CATSProperties.AppealDetermiantionFavorable.getProperty(), CATSProperties.ExceptionNone.getProperty());
		pause(5);
		System.out.println("Result:The Third case went through Due process and adjudicated in Appeals flow and closed with determination = favorable");

		// Log out user adjudicator from CATS
		catsLogout();

	}

/**
 * This function will revoke a case based on ssn passed to it
 * @param searchableSsn1
 */
	public void caseRevoked(String searchableSsn1) {
		// Select the new case from the open case datatable.
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn1, "//*[@id='majorTabPanel:SubTable']");

		// Click on the determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

		// Click on revoked option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationRevoked.getProperty()));

		// Click on save button to save determination
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));

		// Verify growl message is seen
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Determination saved.");

		// Click on Summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

		// Click on Case closed button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Close Case");
		WaitingToLoad();

		// Click on confirmation yes button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");
		WaitingToLoad();
	}

/**
 * This function will verify the subject detail based on the SSN passed
 * @param ssn
 * @throws Exception
 */
	public void verifySubjectdetails(String ssn) throws Exception {
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Basic Info");
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(),
		// "Contact Info");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Other Subject Details");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "SMO Relationships");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Incidents");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "CSRs/RFAs");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Subject Notes/Documents");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyText(CATSProperties.verifySSNonSubjectDetail.getProperty(), ssn);

		// verify basic info tab detail
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Citizenship Information");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Subject's Case History");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Position of Trust Information");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Subject Personal Information");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Other Subject Details"));
		// Verify other subject details tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Aliases");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "SSN History Information");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Foreign Contact(s) and Relative(s) Information");
		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Add Foreign Contact or Relative");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Unknown Contact(s) and Relative(s) Information");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Domestic Contact(s) and Relative(s) Information");
		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "PolyGraph");
		// Verify SMO relationship tab details
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:subjtabview']//a[text()='SMO Relationships']")); 
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Categories");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Relationships");

		// Verify incidents tabl details
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Incidents")); // clicking
																																// incidents
																																// tab.
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:subjtabview:incidentsDataTable_head']", "Status");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:subjtabview:incidentsDataTable_head']", "Date");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:subjtabview:incidentsDataTable_head']", "Type");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Other Subject Details"));
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Basic Info"));
		pause(2);
	}

/**
 * This function will verify the case details based on the case ID passed to it
 * @param caseID
 */
	public void verifyCaseDetails(String caseID) {
		
		reusable.searchCase(caseID);
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Case Details");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Subject Details");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Case Info");
		com.iworks.DISS.test.common.functions.ReusableFunctions.verifyEnabled(CATSProperties.CaseDetailsCasetypeDropDown.getProperty());
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CaseDetailsCasetypeDropDown.getProperty()));
		System.out.println("case type drop down is clickable");
		// verify Priority Program editable attributes on case detail page.
		com.iworks.DISS.test.common.functions.ReusableFunctions.verifyEnabled(CATSProperties.CaseDetailsPriorityProgramDropDown.getProperty());
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CaseDetailsPriorityProgramDropDown.getProperty()));
		System.out.println("case priority program drop down is clickable");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Notes");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "History");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Review");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Incidents");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Documents");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "CSR");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "RFA");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "DCII");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Investigation Details");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Investigation Type");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Investigation Status");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Close Date");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Agency Case Number");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "RAISE");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Adjudication");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "RAISE");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Files Reviewed");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Guidelines");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Determination");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.container2.getProperty(), "Summary");
	}
	public void verifyCATSReports(String user, String reportType, String reportName1,String reportName2,String reportName3, String role) throws Exception {
		//reusable.loginToCATS(user);
		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("parent handel is : "+parentWindowHandler);
		System.out.println("parentTitle is " + driver.getTitle());
		//click on Reporting link
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Reporting"));
		//click yes to open in new tab
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='appMenu:transfer1DissPortalYes']"));
		pause(30);
		
		//switch to new tab
		Set<String> set = driver.getWindowHandles();
		int s =set.size();
		System.out.println("set size is" +s);
		Iterator<String> ite = set.iterator();

              
			 String popupHandle=ite.next().toString();
			 System.out.println("handle 1 is :" +popupHandle);
              if(popupHandle.equalsIgnoreCase(parentWindowHandler))
              {
                  popupHandle = ite.next().toString();  
                  System.out.println("handle 2 is :" +popupHandle);
             	 driver.switchTo().window(popupHandle);
             	pause(5);
              }else {
              driver.switchTo().window(popupHandle);
              pause(5);
              }
             String reportWindow= driver.getTitle().toString();
             System.out.println("Report window title is "+reportWindow);
             driver.manage().window().maximize();
             
             String[] reportTypeArr = reportType.split(",");
                         
             for (String reporttype : reportTypeArr) {
            	 ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,'_header')]/a[preceding-sibling::span[contains(text(),'"+reporttype+"')]]"));
            	 if(reporttype.equalsIgnoreCase("CATS Standard  Reports")) {
            		 String reportName = reportName1;
            		 String[] reportNameArr = reportName.split(",");
            		 for (String reportname : reportNameArr) {
     					if(reportname.equalsIgnoreCase("Guidelines")) {
     						System.out.println("Testing report: "+reportname);
     	            		 String[] subReportArr = {"HSPD-12","Security","Suitability"};
						for (String subreport : subReportArr) {
							System.out.println("Testing subreport: "+subreport+"\n");
							ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Guidelines"));
							pause(2);
							// click on the subreport
							ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText(subreport));
							pause(5);
							ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='View Report']"));
							pause(3);
							ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//object[@type='application/pdf']"));
							System.out.println("Pdf is rendered correctly for " + subreport);
							captureScreen(className, name, role, subreport);
							pause(5);
						}
     						
     	            	 }else {
     	            		System.out.println("Testing report: "+reportname);
     						ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText(reportname));
     						pause(5);
     			             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='View Report']"));
     			             pause(3);
     			             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//object[@type='application/pdf']"));
     			             System.out.println("Pdf is rendered correctly for "+reportname);
     			             
     			           captureScreen(className, name,role,reportname);
     			           pause(5);
     					}
     					 
     				}
            	 }
               	 if(reporttype.equalsIgnoreCase("CATS Management Reports")) {
            		 String reportName = reportName2;
            		 String[] reportNameArr = reportName.split(",");
            		 for (String reportname : reportNameArr) {
            			 if (reportname.equalsIgnoreCase("Case Aging")) {
            				 System.out.println("Testing report: "+reportname);
            				 
     						String[] subReportArr = {"Assigned","Inventory","Review","Suspended"};
     						for (String subreport : subReportArr) {
     							System.out.println("Testing sub report: "+subreport);
     							 ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Case Aging"));
     							 pause(2);
    							 //click on the subreport
      							ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText(subreport));
          							pause(10);
       	    		             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='View Report']"));
     	    		             pause(3);
     	    		             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//object[@type='application/pdf']"));
     	    		             System.out.println("Pdf is rendered correctly for "+subreport);
     	    		             
     	    		           captureScreen(className, name,role,subreport);
     	    		           pause(5);
     						}
            				// continue;
     						
     					}else {
     						System.out.println("Testing report: "+reportname);
     						ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText(reportname));
     						pause(5);
     			             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='View Report']"));
     			             pause(3);
     			             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//object[@type='application/pdf']"));
     			             System.out.println("Pdf is rendered correctly for "+reportname);
     			             
     			           captureScreen(className, name,role,reportname);
     			           pause(5);
     					}
     					 
     				}
     			}
              	 if(reporttype.equalsIgnoreCase("CATS User Audit Reports")) {
            		 String reportName = reportName3;
            		 String[] reportNameArr = reportName.split(",");
            		 for (String reportname : reportNameArr) {
            			 System.out.println("Testing report: "+reportname);
            				ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText(reportname));
            				pause(5);
     			             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='View Report']"));
     			             pause(3);
     			             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//object[@type='application/pdf']"));
     			             System.out.println("Pdf is rendered correctly for "+reportname);
     			             
     			           captureScreen(className, name,role,reportname);
     			           pause(5);
     					}
     					 
     				}
     			}
         //log out of reports
             reportsLogout();
		//close tab
		driver.close();
		//switch to parent
		driver.switchTo().window(parentWindowHandler);
	}
	   public String captureScreen(String className,TestName name, String role, String reportname) {
	        File source = null;
	        File targetFile;
	        String filePath;
			
	        try {
	        	// date picker
	    		
	    		Calendar cal = Calendar.getInstance();
	    		
	    		DateFormat dateFormat1 = new SimpleDateFormat("yyMMdd");
	    		String date1 = dateFormat1.format(cal.getTime());
	    		DateFormat dateFormat2 = new SimpleDateFormat("yyMMdd-HHmmss");
	    		String date2 = dateFormat2.format(cal.getTime());
	    		String environment = CONFIG.getProperty("Environment");
	    		 targetFile = new File("C://AutomationOutput//" +environment+"//"+"TestedByBuild"+"//"+ className + "//" + date1 + "//"+ role+ "//logoutput-" +reportname+ "-" + date2 + ".jpg");//C://AutomationOutput//" + environment+"//"+"TestedByBuild"+"//"+ className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc
	    		targetFile.getParentFile().mkdirs();
	        	source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            FileUtils.copyFile(source, targetFile); 
	             filePath = targetFile.getAbsolutePath();
	           
	        }
	        catch(Exception e) {
	        	filePath = "Failed to capture screenshot: " + e.getMessage();
	        }
	        return filePath;
	    }


}
