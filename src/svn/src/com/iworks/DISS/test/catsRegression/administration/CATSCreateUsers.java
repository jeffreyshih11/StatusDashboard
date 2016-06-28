package com.iworks.DISS.test.catsRegression.administration;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.TestBase;

/**
 * This class will perform the following actions to test create User functionalities
 * in CATS application 
 *  Create test data required programmatically so the test can be run without any human intervention and multiple times- different user types in CATS
 *  Verify Log in as each user role and verify the LHS links[ which changes based on role]
 *   a. Create a subject for the user
 *   b. Create user for the subject (10 different roles)
 *   c.Verify CATS User status is Active
 *   d.Verify JPAS Id is required
 *   e. Verify organization is required and can be added
 *   f.Verify subject details link
 *   g.Verify Roles is required and can be added
 *   h.Verify optional permissions for the role selected is populated in the Available section under Permission section
 *   i.Verify that user is saved and can log in
 * 
 * @author vshivaraman
 */

public class CATSCreateUsers extends TestBase {


	private static final String EXECUTIVE_CHIEF_ROLE = "Executive Chief";
	private static final String PSYCH_EVALUATION_TEAM_ROLE = "Psych Evaluation Team";
	private static final String OPM_LIAISON_ROLE = "OPM Liaison";
	private static final String TECT_ROLE = "TECT";
	private static final String TRAINEE_ROLE = "Trainee";
	private static final String CATS_APPLICATION_ADMINISTRATOR_ROLE = "CATS Application Administrator";
	private static final String QUALITY_CONTROL_ROLE = "Quality Control Reviewer";
	private static final String ITTFSCREENER_ROLE = "ITTF Screener";
	private static final String GENERAL_COUNSEL_ROLE = "General Counsel";
	private static final String PROCESS_TEAM_ROLE = "Process Team";
	private static final String ADJUDICATOR_ROLE = "Adjudicator";
	private static final String TEAM_CHIEF_ROLE = "Team Chief";
	private static final String BRANCH_CHIEF_ROLE = "Branch Chief";
	private static final String DIVISION_CHIEF_ROLE = "Division Chief";
	
	//Map User name to User Info
	Map<String, UserInfo> userNameToInfoMap = new HashMap<>();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	
		
	public static class UserInfo {
		public String fName;
		public String lName;
		public String uName;
		public String ssn;
		public String role;
		public String optionalPermissions;
		public String leftNavLinks;
	}

	public Class<? extends CATSCreateUsers> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	
	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
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
	 * Test to verify Create user functionalities in CATS application: 
	 * 	 a. Create a subject for the user
	 *   b. Create user for the subject (10 different roles)
	 *   c.Verify CATS User status is Active
	 *   d.Verify JPAS Id is required
	 *   e. Verify organization is required and can be added
	 *   f.Verify subject details link
	 *   g.Verify Roles is required and can be added
	 *   h.Verify optional permissions for the role selected is populated in the Available section under Permission section
	 *   i.Verify that user is saved and can log in
	 * 
	 * @throws Exception
	 */
	@Test
	public void catsCreateUsersTest() throws Exception {
		
		//Create test data
		System.out.println("Create Test Data\n");
		//Instantiate class SSNFNameAndLName
		SSNFNameAndLName sub = new SSNFNameAndLName();
		// Call the createtestData function to create test data
		List<UserInfo> list = createTestData(sub);
		//Map role to userInfo
		Map<String, UserInfo> roleToUserInfomap = new HashMap<String, CATSCreateUsers.UserInfo>();
		//for eachuserInfo in the list map the role to info
		for (UserInfo userInfo : list) {
			roleToUserInfomap.put(userInfo.role, userInfo);
		}
		System.out.println("Test Data creation complete.\n");
		
		//create different users
		for (UserInfo userInfo : list) {
			System.out.println("Test: Create user with role "+userInfo.role+"\n");
			createUser(userInfo.ssn, 22222, "DoD CAF", userInfo.uName, userInfo.role);
			System.out.println("Result: user with the role "+userInfo.role+" has been successfully created.\n");
		}
		
		catsLogout();
		// Verify Log in function as different user roles 
		// Call the verify log in function to verify log in as different users
		verifyLogin(list,sub.ssn);
		
		//Verify password validations
		System.out.println("TEST:Verify Password Validations \n");
		//Log in to CATS as CATS Application admin
		reusable.loginToCATS(roleToUserInfomap.get(CATS_APPLICATION_ADMINISTRATOR_ROLE).uName);
		//Search for the subject's snn
		reusable.searchUserCATS(sub.ssn);
		//Call the create user method
		createUser("22222","DoD CAF",ADJUDICATOR_ROLE);
		//Gett the user id value
		String subjectUserID = ReusableFunctions.waitAndGetValue(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']"));
		
		//Verify that the password length >15 char if not error message should be seen
		System.out.println("TEST:Verify that the password length >15 char if not error message should be seen. \n");
		verifyPassword("1", "***Registration Password must be at least 15 characters long***", "invalid");
		System.out.println("Result:Correct error message is seen when password length<15 chars. \n");
		
		//Verify password contains at least 1 capital alpha char if not error message should be seen
		System.out.println("TEST:Verify password contains at least 1 capital alpha char if not error message should be seen.\n");
		verifyPassword("1@aaaaaaaaaaaaa", "***Registration Password does not meet character requirements***", "invalid");
		System.out.println("Result:Correct error message is seen when no capital alpha char is seen in the password. \n");
		
		//Verify password contains at least one number
		System.out.println("Test:Verify password contains at least one number.\n");
		verifyPassword("A@aaaaaaaaaaaaa", "***Registration Password does not meet character requirements***", "invalid");
		System.out.println("Result: Correct error message is seen when no number is seen in the password.\n");
		
		//Verify password contains at least one special char
		System.out.println("Test:Verify password contains at least one special char.\n");
		verifyPassword("A1aaaaaaaaaaaaa", "***Registration Password does not meet character requirements***", "invalid");
		System.out.println("Result:Correct error message is seen when password contains no special char. \n");
		
		//Verify that password DOES NOT contain the user id
		System.out.println("Test:Verify that password DOES NOT contain the user id.\n");
		verifyPassword(subjectUserID+"A@aaa", "***Registration Password cannot be the same or contain the User ID***", "invalid");
		System.out.println("Result:Correct error message is seen when password CONTAINS USER ID.\n");
		
		System.out.println("RESULT:Password Validations verified- TEST PASS. \n");
		catsLogout();
		


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
		reusable.loginToCATS("1");
		List<UserInfo> retList = new ArrayList<>();
		
		//Division Chief User
		UserInfo uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Manage Templates,Search SMOs";
		uInfo.optionalPermissions="";
		createSubjectsForCATSCreateUserTest("DivChfFName", "DivChfLName",  DIVISION_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
		
		//Executive Chief
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Assignment,Manage Hierarchy,Manual Assignment,Subject Management,Manage Templates,Search SMOs";
		uInfo.optionalPermissions="";
		createSubjectsForCATSCreateUserTest("ExeChfFName", "ExeChfLName",  EXECUTIVE_CHIEF_ROLE, uInfo);
		retList.add(uInfo);

			
		//Branch Chief User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Search SMOs";
		uInfo.optionalPermissions="View Person of Interest,Adjudicate and View CATS USER,Adjudicate Due Process,Adjudicate and View Person of Interest,View ITTF Results";
		createSubjectsForCATSCreateUserTest("BrcChfFName", "BrcChfLName",  BRANCH_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
		
		//Team Chief User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Inbox,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Search SMOs";
		uInfo.optionalPermissions="View Person of Interest,Adjudicate and View CATS USER,Adjudicate Due Process,Adjudicate and View Person of Interest,View ITTF Results";
		createSubjectsForCATSCreateUserTest("TeamChFName", "TeamChLName", TEAM_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
	
		//Adjudicator User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Task Inbox,Unread Notifications:,Productivity,Case Inbox,Subject Management,SMO";
		uInfo.optionalPermissions="Update Subject PII,View CATS USER,View Person of Interest,Adjudicate and View CATS USER,Adjudicate Due Process,Adjudicate HSPD12/Suitability,Adjudicate and View Person of Interest,Review,View ITTF Results,View Reports and Metrics";
		createSubjectsForCATSCreateUserTest("AdjuFName", "AdjuLName",  ADJUDICATOR_ROLE, uInfo);
		retList.add(uInfo);
		


		// Process team user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Inbox,Manual Assignment,Case Inbox,Create Subject,Search SMOs";
		uInfo.optionalPermissions="Update Subject PII,View CATS USER,View Person of Interest";
		createSubjectsForCATSCreateUserTest("ProTeaFName",	"ProTeaLName",  PROCESS_TEAM_ROLE, uInfo);
		retList.add(uInfo);


		// General counsel user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Inbox,Case Inbox,Search SMOs";
		uInfo.optionalPermissions="View CATS USER,View Person of Interest,View ITTF Results";
		createSubjectsForCATSCreateUserTest("GenCouFName",	"GenCouLName",  GENERAL_COUNSEL_ROLE, uInfo);
		retList.add(uInfo);

		
		// ITTFScreener
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications,ITTF Inbox,Case Inbox,Search SMOs";
		uInfo.optionalPermissions="View CATS USER,View Person of Interest";
		createSubjectsForCATSCreateUserTest("ITTFScFName",	"ITTFScLName",  ITTFSCREENER_ROLE, uInfo);
		retList.add(uInfo);

		
		// QualityControl
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications,QC Inbox,Case Inbox,Search SMOs";
		uInfo.optionalPermissions="View CATS USER,View Person of Interest,View ITTF Results";
		createSubjectsForCATSCreateUserTest("QuaConFName",	"QuaConLName", QUALITY_CONTROL_ROLE, uInfo);
		retList.add(uInfo);

		// Trainee
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications,Case Inbox,Create Subject,Search SMOs";
		uInfo.optionalPermissions="View CATS USER,View Person of Interest";
		createSubjectsForCATSCreateUserTest("TraFName",	"TraLName",  TRAINEE_ROLE, uInfo);
		retList.add(uInfo);

		// CATS Application Administrator user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications,Manage Hierarchy,Manual Assignment,Case Inbox,Create Subject,Manage Templates,Search SMOs";
		uInfo.optionalPermissions="";
		createSubjectsForCATSCreateUserTest("CatsAAFName","CatsAALName", CATS_APPLICATION_ADMINISTRATOR_ROLE, uInfo);
		retList.add(uInfo);

		// TECT user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,ITTF Inbox,Case Inbox,Search SMOs";
		uInfo.optionalPermissions="View CATS USER,View Person of Interest";
		retList.add(uInfo);
		createSubjectsForCATSCreateUserTest("TECTUrFName",	"TECTUrLName",  TECT_ROLE, uInfo);
		
		//OPM Liason
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Case Inbox,Create Subject,Search SMOs";
		uInfo.optionalPermissions="";
		retList.add(uInfo);
		createSubjectsForCATSCreateUserTest("OPMLiaFName",	"OPMLiaLName", OPM_LIAISON_ROLE, uInfo);

		//Pscyh Evaluation Team
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Case Inbox,Subject Management,Search SMOs";
		uInfo.optionalPermissions="View CATS USER,View Person of Interest";
		retList.add(uInfo);
		createSubjectsForCATSCreateUserTest("PscEvaFName",	"PscEvaLName", PSYCH_EVALUATION_TEAM_ROLE, uInfo);
		

		//Create user test Subject 
		// Call ssn generator to create snn, first and last names for smoke test
		// subject
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("cUserFName", "cUserLName");
		sub.ssn = list.get(0).ssn;
		sub.firstName = list.get(0).firstName;
		sub.lastName = list.get(0).lastName;
		System.out.println("Test:Verify that create subject function is working correctly \n");
		reusable.createSubject(sub.ssn, sub.firstName, sub.lastName,"1983","Oct","20","United States");
		System.out.println("SSN is: "+sub.ssn+"\n");
		System.out.println("First name is: "+sub.firstName+"\n");
		System.out.println("Last name is: "+sub.lastName+"\n");
		System.out.println("Result:Create subject function is working successfully \n");



		// return list of user information
		return retList;

	}

	/**
	 * This function will create the subject based on first name,last name,role passed
	 * @param firstnamepatern
	 * @param lastNamepatern
	 * @param userNamePattern
	 * @param role
	 * @param uInfo
	 * @throws Exception
	 */
	public void createSubjectsForCATSCreateUserTest(String firstnamepatern,String lastNamepatern,  String role,
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
			
			//create username
			uInfo.uName = uInfo.fName.substring(0, 6)+(lastFour).substring(lastFour.length() - 4);
			System.out.println("Username is "+uInfo.uName+"\n");
			userNameToInfoMap.put(uInfo.uName, uInfo);
			
			// create subject for user
			reusable.createSubject(uInfo.ssn, uInfo.fName, uInfo.lName, "1983", "20");
							
		}

	/**
	 * This function will verify the log in function and LHS panel for each user in the list passed to it
	 * @param list
	 * @throws Exception
	 */
	public void verifyLogin(List<UserInfo> list, String subjectSNN) throws Exception {
		
		for (String userName : userNameToInfoMap.keySet() ) {
			
			System.out.println("Test: Verify Log in to CATS as " + userName+"\n");
			reusable.loginToCATS(userName);
			System.out.println("Result: Log in to CATs as " + userName + " was successful \n");
			
			//Verify that for users with the roles  division chief, branch chief, team chief or executive chief when new subject is serached error message is seen

			if(userNameToInfoMap.get(userName).role.equalsIgnoreCase(EXECUTIVE_CHIEF_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(DIVISION_CHIEF_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(TEAM_CHIEF_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(BRANCH_CHIEF_ROLE)) {
				System.out.println("Test:Verify that for the role "+userNameToInfoMap.get(userName).role+"create user is not availbale when subject is new"+"\n");
				verifyCreateUserNotavailable(subjectSNN);
				System.out.println("RESULT:create user is not available when subject is new for user with the role "+userNameToInfoMap.get(userName).role+"\n");
			}
			
			//Verify that for users with the following roles adjudicator,Trainee,OPM,process team,general counsel,Psych eval team,tect,ITTF screener,QA reviewer user management is not available
			if(userNameToInfoMap.get(userName).role.equalsIgnoreCase(ADJUDICATOR_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(TRAINEE_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(TECT_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(OPM_LIAISON_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(PROCESS_TEAM_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(GENERAL_COUNSEL_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(ITTFSCREENER_ROLE)||userNameToInfoMap.get(userName).role.equalsIgnoreCase(QUALITY_CONTROL_ROLE)) {
				System.out.println("Test:Verify that for the role "+userNameToInfoMap.get(userName).role+"create user is not availbale when subject is new"+"\n");
				ReusableFunctions.waitAndVerifyIfElementDoesNotExists(By.xpath("//button[@id='cpanelForm:searchUserManagement']"));
				System.out.println("RESULT:create user is not availbale when subject is new for user with the role "+userNameToInfoMap.get(userName).role+"\n");
			}
			
			//Verify that CATS Application admin has user management section
			if(userNameToInfoMap.get(userName).role.equalsIgnoreCase(CATS_APPLICATION_ADMINISTRATOR_ROLE)) {
				System.out.println("Test:Verify that user management IS available for user with the role "+userNameToInfoMap.get(userName).role+"\n");
				ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container1.getProperty(), "User Management");
				System.out.println("RESULT:User management IS available for user with the role "+userNameToInfoMap.get(userName).role+"\n");

	
			}
			
			System.out.println("Test: Verify role on the top navigation \n");
			ReusableFunctions.waitAndVerify_IfContains("//span[parent::p[contains(text(),'Your role is') or contains(text(),'Your roles are')]]",userNameToInfoMap.get(userName).role); 
			System.out.println("Result:Correct role is seen in the top of the page as: "+userNameToInfoMap.get(userName).role+"\n");
			
			System.out.println("Test: Verify the links in the LHS panel \n");
			verifyLeftHandPanel(userNameToInfoMap.get(userName).leftNavLinks);
			System.out.println("result: Links in the LHS panel is verified \n");
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
	 * This function will verify that all the required fields trigger the error message if not filled
	 * @param ssn
	 */
	public void verifyRequiredFields(String ssn) {
		
		//Click on save
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");
		pause(2);
		//verify that the error message indicating all the  required fields is seen
		ReusableFunctions.waitAndVerify_IfContains("//div[contains(@class,'ui-messages-error')]//span", "Enter JPAS User ID.");
		ReusableFunctions.waitAndVerify_IfContains("//div[contains(@class,'ui-messages-error')]//span", "Assign at least one CAF.");
		ReusableFunctions.waitAndVerify_IfContains("//div[contains(@class,'ui-messages-error')]//span", "Assign at least one Role.");
		pause(2);
	}
	
	/**
	 * This function will verify that JPAS ID is a required field and will trigger error message when not filled
	 * @param ssn
	 * @param organization
	 * @param role
	 */
	public void verifyJPASRequired(String ssn,String organization,String role) {
		
		//select the Organization
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td/ul/li[@data-item-label='"+organization+"']")); 
		com.iworks.DISS.test.common.functions.ReusableFunctions.doubleClick("//td/ul/li[@data-item-label='"+organization+"']");
		pause(5);
		
		//verify that all the roles are available for the organization selected
		System.out.println("TEST:Verify that all the roles are available for the organization selected.\n");
		verifyRolesForOrganization();
		System.out.println("RESULT: Verified that all the roles for the org selected is seen in the list.\n");
		
		//Select the role
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']//li[@data-item-label='"+role+"']")); 
		com.iworks.DISS.test.common.functions.ReusableFunctions.doubleClick("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='"+role+"']");
		pause(5);
		//Click on save
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");
		pause(2);
		//Verify error message is seen
		ReusableFunctions.waitAndVerify_IfContains("//div[contains(@class,'ui-messages-error')]//span", "Enter JPAS User ID.");
		pause(2);
	}
	
	public void verifyRolesForOrganization() {
		String[] roles = {"Adjudicator","Process Team",EXECUTIVE_CHIEF_ROLE,"Division Chief","Branch Chief","Team Chief","General Counsel","TECT","Quality Control Reviewer","CATS Application Administrator","Trainee","ITTF Screener",PSYCH_EVALUATION_TEAM_ROLE,OPM_LIAISON_ROLE,"Industry Process Team"};
		for (String role : roles) {
			ReusableFunctions.waitAndVerify_IfContains("//table[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']", role);
		}
	}
	
	/**
	 * This function will verify that all the fields as per requirement is seen in the User details page
	 * @param ssn
	 */
	public void verifyFieldsOnUserDetailsPage(String ssn) {
		reusable.searchUserCATS(ssn);
		String[] userDetailsFields = {"CATS User ID","Name","*JPAS User ID","Status","Subject Details","Registration User ID:","Registration Password:","Password Confirmation:","Generate User ID","Generate Password","*Organizations","*Roles","Permissions","Save","Cancel"};
		for (String fieldName : userDetailsFields) {
			ReusableFunctions.waitAndVerify_IfContains("//form[@id='majorTabPanel:catsUserDetailForm']", fieldName);
		}
		
	}
	
	/**
	 * 
	 * @param role
	 * @param optionalPermissions
	 */
	public void verifyOptionalPermissionForRole(String role,String optionalPermissions) {
		String[] optionalPermissionsArr =optionalPermissions.split(",");
		for (String optionalPermission : optionalPermissionsArr) {
			ReusableFunctions.waitAndVerify_IfContains("//table[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']", optionalPermission);
		}
	}
	
	public void verifyCreateUserNotavailable(String ssn) {
		reusable.searchUserCATS(ssn);
		pause(2);
		ReusableFunctions.waitAndVerify_IfContains("//span[@id='cpanelForm:validateMessage1']", "You do not have role to search other users");
		System.out.println("Error message is seen correctly- Test PASS");
	}
	public void createUser(String jpasID,String organization, String role) {
		//enter the JPASS User id
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:jpasUserId']"), jpasID);
		pause(2);
			
		//select the Organization
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td/ul/li[@data-item-label='"+organization+"']")); 
		com.iworks.DISS.test.common.functions.ReusableFunctions.doubleClick("//td/ul/li[@data-item-label='"+organization+"']");
		WaitingToLoad();
		
		//Select the role
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='"+role+"']")); 
		com.iworks.DISS.test.common.functions.ReusableFunctions.doubleClick("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']/tbody/tr/td[1]/ul/li[@data-item-label='"+role+"']");
		pause(5);
				
		//select all permissions
		com.iworks.DISS.test.common.functions.ReusableFunctions.doubleClick("//table[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[@title='Add All']");
		pause(5);

	}
	public void verifyPassword(String password, String message, String passValidity) {
		//click on the registration password text box
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[preceding-sibling::td/label[contains(text(),'Registration Password')]]/input"));
		//clear the content of registration password text box
		ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//td[preceding-sibling::td/label[contains(text(),'Registration Password')]]/input"));
		//enter the pasword
		ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td[preceding-sibling::td/label[contains(text(),'Registration Password')]]/input"), password);
		//click on the password confirmation text box
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[preceding-sibling::td/label[contains(text(),'Password Confirmation')]]/input"));
		//clear the password confirmation text box
		ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//td[preceding-sibling::td/label[contains(text(),'Password Confirmation')]]/input"));
		//enter the password again
		ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td[preceding-sibling::td/label[contains(text(),'Password Confirmation')]]/input"), password);
		//click on save button
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:catsUserDetailForm:userDetailsSave']"));

		if(passValidity.equalsIgnoreCase("invalid")) {
			ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:catsUserDetailForm:registrationmsgs']//span", message);
		}
		if(passValidity.equalsIgnoreCase("valid")) {
			List<WebElement> list= ReusableFunctions.waitUntilElementsExistsAndFindBy(By.xpath("//div[@id='majorTabPanel:catsUserDetailForm:registrationmsgs' and not(*)]"));
			System.out.println("Element with the path "+list.get(0).toString()+" is seen correctly- Test PASS \n");
		}
	}
	/**
	 * This method will create a user with all permissions based on the SSN and role within the organization specified with the given user name.
	 * @author vshivaraman
	 * @param ssn
	 * @param userIDJPAS
	 * @param organization
	 * @param userName
	 * @param role
	 */
	public void createUser(String ssn, int userIDJPAS,String organization, String userName, String role) {
		
		System.out.println("Test: Verify all the fields on the User details Page. \n");
		verifyFieldsOnUserDetailsPage(ssn);
		System.out.println("Result: All the fields on the user details page has been verified. \n");
		
		//Verify that user status is "active"
		System.out.println("TEST:Verify that the user status is 'ACTIVE' \n");
		ReusableFunctions.waitAndVerify_IfContains("//label[@id='majorTabPanel:catsUserDetailForm:catsUserStatus_label']", "Active");
		System.out.println("RESULT:The user status is verified as 'ACTIVE' \n");
		
		//Verify that error message is seen if required fields are not filled
		System.out.println("Test:Verify that error message is seen if required fields are not filled.\n");
		verifyRequiredFields(ssn);
		System.out.println("Result: Error message is seen correctly if required fields are not filled.\n");
		
		
		
		//Verify that JPAS ID is required after selecting organization and role
		System.out.println("Test:Verify that JPAS ID is required after selecting organization and role \n");
		verifyJPASRequired(ssn, organization, role);
		System.out.println("Result:Error message is seen if JPAS ID is not entered after selecting organization and role \n");
		
		System.out.println("Creating and saving the user with the role "+role+"\n");
		
		//enter the JPASS User id
		String userIDJPAS1 =String.valueOf(userIDJPAS);
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:jpasUserId']"), userIDJPAS1);
		
		//verify error message when password does not meet req
		//Verify password meets req
		
		//Click on the auto password button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Generate Password");
		pause(2);
		
		driver.findElement(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']")).click();
		pause(2);
		
		//enter the user name should be 8 to 10 charcs only
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClear(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']"));
		pause(2);
		
		driver.findElement(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']")).click();
		pause(2);
		
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:catsUserDetailForm:regid']"), userName);
		//pause(5);
		
//		//select the Organization
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:catsUserDetailForm:catsUserDetailOrganizationPickList']//li[@data-item-label='"+organization+"']")); 
//		com.iworks.DISS.test.common.functions.ReusableFunctions.doubleClick("//table[@id='majorTabPanel:catsUserDetailForm:catsUserDetailOrganizationPickList']//li[@data-item-label='"+organization+"']");
//		WaitingToLoad();
//		
//		//Select the role
//		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']//li[@data-item-value='"+role+"']")); 
//		com.iworks.DISS.test.common.functions.ReusableFunctions.doubleClick("//*[@id='majorTabPanel:catsUserDetailForm:catsUserDetailRolePickList']//li[@data-item-value='"+role+"']");
//		pause(5);
		
		//Verify that all optional permission for the role is seen 
		System.out.println("Test:Verify that all optional permission for the role: "+role+" is seen.\n ");
		verifyOptionalPermissionForRole(role,userNameToInfoMap.get(userName).optionalPermissions);
		System.out.println("Result: Optional permissions for the role: "+role+" is seen correctly.\n");
		
		//select all permissions
		com.iworks.DISS.test.common.functions.ReusableFunctions.doubleClick("//table[@id='majorTabPanel:catsUserDetailForm:catsUserDetailPermPickList']/tbody/tr/td[2]/button[@title='Add All']");
		WaitingToLoad();
		
		//Click on save
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");
		
		//WaitingToLoad();
		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully.");
		pause(5);
		
		
	}








}
