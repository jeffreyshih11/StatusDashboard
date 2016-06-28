package com.iworks.DISS.test.catsRegression.manageHierarchy;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;

import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.*;

/**
 * This class will create test data required to test the manual TCs as each user
 * with related permissions for the following: Case Visibility is restricted to
 * User's CAF as each user with view case permission • Verify in Subject details
 * page- Case history has a case with ‘HA’ status • Verify in case
 * details page case is seen. Searching for Case outside of CAF returns error
 * message for each user with search permission • Verify that error message is
 * seen when searched for Case ID in CAF2 for Subject1 in CAF1. One open Case
 * per Subject per CAF as each user with create case permission • Verify that
 * Create case option is enabled for subject2 in CAF1 and CAF2 Case outside of
 * current CAF shown on Subject Details page as each user with view case info
 * permission • Verify that in subject details page of subject1- Subject case
 * history Case ID sub1case2 is seen but cannot click on that row to open case
 * details. Verify Data Elements and Fields for each user • Verify LHS, Subject
 * details page , case details page
 * 
 * @author vshivaraman
 *
 */
public class CAFBasedFilteringOfSubjectAndCase extends TestBase {
	
	private static final String CASE_TYPE = CATSProperties.CaseTypeSCI.getProperty();
	private String investigationType = null;
	private String smoName = null;
	private String divisionName = null;
	private String categoryType = null;
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
	private static final String INDUSTRY_PROCESS_TEAM_ROLE = "Industry Process Team";

	public static class UserInfo {
		public String uName;
		public String fName;
		public String lName;
		public String role;
		public String myCAFName;
		public String myCAFCaseID;
		public String otherCAFCaseID;
		public String subWithOpenCaseSSN;
		public String subWithNoOpenCaseSSN;

	}

	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	long startTime;
	long endTime;
	public Class<? extends CAFBasedFilteringOfSubjectAndCase> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	/**
	 * This method will create a log file in the path specified to log all the
	 * console messages to a word doc
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className, name);
		investigationType = "//*[@id='createCaseForm:createCaseInvType_panel']//li[@data-label='"+ PreconditionVariables.getProperty("investigationType") +"']";
		System.out.println("Investigation Type : " + PreconditionVariables.getProperty("investigationType"));
		smoName = "//li[@data-label='"+PreconditionVariables.getProperty("smoName")+"']";
		System.out.println("SMO Name :"+PreconditionVariables.getProperty("smoName")+"\n");
		categoryType = "//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel']//li[text()='"+ PreconditionVariables.getProperty("categoryType") +"']";
		System.out.println("Category Type is  :"+PreconditionVariables.getProperty("categoryType")+"\n");
	}

	/**
	 * This method will print the time it took for the test to run in the log
	 * file
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		endTime = System.currentTimeMillis();
		double executionTime = (endTime - startTime) / 1000.0;
		System.out.println("This test took " + executionTime + "Seconds");
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
	 * This method will initiate the test data creation and the calls to the
	 * methods for verifying the functionalities for managing hierarchy based on
	 * the CAF, division,branch,team,team member names and the chief names of
	 * division,branch and team passed to the methods
	 * 
	 * @throws Exception
	 */
	@Test
	public void cafBasedFilteringOFCaseAndSubject() throws Exception {
		
		// Create Subject with open case
		String subSSN = createTestSubjects();
		System.out.println("SSN of Subject with an open case is : " + subSSN + "\n");
		
		//Create Subject without open case
		String subSSNwithoutOpenCase = createTestSubjects();
		System.out.println("SSN of Subject with no open case is : " + subSSNwithoutOpenCase + "\n");

		// Create test data for first CAF
		List<UserInfo> list = createTestData("1", "DoD CAF", subSSN);
		
		// Create test data for second CAF
		List<UserInfo> list1 = createTestData("10001", "DOE CAF", subSSN);
		list.addAll(list1);
		
		// For each user in the list based on their role and permission verify the following:Case visibility ,
		// Searching for Case outside of CAF,Case outside of current CAF shown on Subject Details page and One open Case per Subject per CAF
		for (UserInfo userInfo : list) {
			userInfo.subWithNoOpenCaseSSN = subSSNwithoutOpenCase;
			reusable.loginToCATS(userInfo.uName);
			System.out.println("Test User role is : " + userInfo.role + "\n");
			System.out.println("Test user in CAF : " + userInfo.myCAFName + "\n");
			if (userInfo.myCAFName.equalsIgnoreCase("DoD CAF")) {
				userInfo.otherCAFCaseID = list1.get(0).myCAFCaseID;
			} else if (userInfo.myCAFName.equalsIgnoreCase("DOE CAF")) {
				userInfo.otherCAFCaseID = list.get(0).myCAFCaseID;
			}
			System.out.println("Case ID of the case not in my CAF is : " + userInfo.otherCAFCaseID + "\n");
			System.out.println("Case ID of the case in my CAF is : " + userInfo.myCAFCaseID + "\n");
			
			// Case visibility: can only open cases within the same CAF
			System.out.println("Test:Case visibility: Verify that user can only open cases within the same CAF \n");
			caseVisibility(userInfo.subWithOpenCaseSSN, userInfo.myCAFCaseID);
			System.out.println("Result:Case visibility:verified that user can only open cases within the same CAF- TEST PASS \n");
			
			// Searching for Case outside of CAF returns error message
			System.out.println("Test:Searching for Case outside of CAF: Verify that error message is seen when user searches for a case outside their CAF \n");
			searchCaseOutsideCAF(userInfo.subWithOpenCaseSSN, userInfo.otherCAFCaseID);
			System.out.println("Result:Searching for Case outside of CAF: Verified that error message is seen when user searches for a case outside their CAF \n");

			// Case outside of current CAF shown on Subject Details page
			System.out.println("Test:Case outside of CAF: Verify that Case outside of user's CAF is seen in Subject Details page \n");
			caseOutsideCAFSeenInSubjectDetailsPage(userInfo.subWithOpenCaseSSN, userInfo.otherCAFCaseID);
			System.out.println("Result:Case outside of CAF: Verified that Case outside of user's CAF is seen in Subject Details page \n");
			
			// One open Case per Subject per CAF
			if (userInfo.role.equalsIgnoreCase(ADJUDICATOR_ROLE) || userInfo.role.equalsIgnoreCase(BRANCH_CHIEF_ROLE) || userInfo.role.equalsIgnoreCase(DIVISION_CHIEF_ROLE) || userInfo.role.equalsIgnoreCase(TEAM_CHIEF_ROLE) || userInfo.role.equalsIgnoreCase(EXECUTIVE_CHIEF_ROLE) || userInfo.role.equalsIgnoreCase(TECT_ROLE) || userInfo.role.equalsIgnoreCase(TRAINEE_ROLE) || userInfo.role.equalsIgnoreCase(PROCESS_TEAM_ROLE) || userInfo.role.equalsIgnoreCase(CATS_APPLICATION_ADMINISTRATOR_ROLE) || userInfo.role.equalsIgnoreCase(QUALITY_CONTROL_ROLE)) {
				System.out.println("Test:Verify that only One open Case per Subject per CAF is available \n");
				oneOpenCasePerCAF(userInfo.subWithNoOpenCaseSSN);
				System.out.println("Result:Verified that only One open Case per Subject per CAF is available \n");
			}
			catsLogout();
		}
		
		//Delete the hierarchy for DoD CAF
		deleteHierarchyBasedOnCAF(list, "1", "DoD CAF", "DivDoD CAF", "BranchDoD CAF", "TeamDoD CAF");
		
		//Delete hierarchy for DOE CAF
		deleteHierarchyBasedOnCAF(list, "10001", "DOE CAF", "DivDOE CAF", "BranchDOE CAF", "TeamDOE CAF");

	}
	
	/**
	 * This method will create the test data for the CAF passed to it and return a list of user information
	 * @param user
	 * @param caf
	 * @param subSSN
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> createTestData(String user, String caf, String subSSN) throws Exception {
		String divName = "Div" + caf;
		String divChiefFname = null;
		String divChiefLName = null;
		String branchName = "Branch" + caf;
		String branchChiefFName = null;
		String branchChiefLName = null;
		String teamName = "Team" + caf;
		String teamChiefFName = null;
		String teamChiefLName = null;
		String teamMemberFName = null;
		String teamMemberLName = null;
		String processTeamMemberFName = null;
		String processTeamMemberLName = null;
		String indProcessTeamMemberFName = null;
		String indProcessTeamMemberLName = null;
	
		System.out.println("Division name: " + divName + "\n");
		System.out.println("Branch name: " + branchName + "\n");
		System.out.println("Team name: " + teamName + "\n");
		
		//Log in to CATS as user
		reusable.loginToCATS(user);

		List<UserInfo> uList = new ArrayList<CAFBasedFilteringOfSubjectAndCase.UserInfo>();

		// create users in the CAF passed
		uList = createUsersBasedOnCAF(caf);
		for (UserInfo info : uList) {

			if (info.role.equalsIgnoreCase(DIVISION_CHIEF_ROLE)) {
				divChiefFname = info.fName;
				divChiefLName = info.lName;

			} else if (info.role.equalsIgnoreCase(BRANCH_CHIEF_ROLE)) {
				branchChiefFName = info.fName;
				branchChiefLName = info.lName;
			} else if (info.role.equalsIgnoreCase(TEAM_CHIEF_ROLE)) {
				teamChiefFName = info.fName;
				teamChiefLName = info.lName;
			} else if (info.role.equalsIgnoreCase(ADJUDICATOR_ROLE)) {
				teamMemberFName = info.fName;
				teamMemberLName = info.lName;
			}else if (info.role.equalsIgnoreCase(PROCESS_TEAM_ROLE)) {
				processTeamMemberFName = info.fName;
				processTeamMemberLName = info.lName;
			}else if (info.role.equalsIgnoreCase(INDUSTRY_PROCESS_TEAM_ROLE)) {
				indProcessTeamMemberFName = info.fName;
				indProcessTeamMemberLName = info.lName;
			}
		}
		//Call the method to create hierarchy in the CAF 
		createHierarchyBasedonCAF(subSSN, caf, divName, divChiefFname, divChiefLName, branchName, branchChiefFName, branchChiefLName, teamName, teamChiefFName, teamChiefLName, teamMemberFName, teamMemberLName,processTeamMemberFName,processTeamMemberLName,indProcessTeamMemberFName,indProcessTeamMemberLName);
		
		// create a open case for the subject in CAF
		divisionName = "//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='"+ divName +"']";
		reusable.createCaseForSubject(subSSN, divisionName, CASE_TYPE, smoName, investigationType);
		// Get the case ID of the cases created
		String caseID = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("case ID in CAF : "+caf+"is :" + caseID+"\n");

		for (UserInfo userInfo : uList) {
			userInfo.subWithOpenCaseSSN = subSSN;
			userInfo.myCAFCaseID = caseID;

		}

		catsLogout();
		return uList;

	}
	/**
	 * This method will create the subjects, add citizenship and establish SMO relationship for testing CAF based filtering
	 * @return
	 * @throws Exception
	 */
	public String createTestSubjects() throws Exception {
		// Create Subject and get the subject SSN
		// call ssn gen to create ssn, first name and last name for user
		List<SSNFNameAndLName> ssnlist = reusable.ssnNameGenerator("subFName", "suLName");
		// get first name and last name and SSN
		String subSSN = ssnlist.get(0).ssn;
		String fName = ssnlist.get(0).firstName;
		String lName = ssnlist.get(0).lastName;

		// Log in to CATS
		reusable.loginToCATS("1");
		// create subject
		reusable.createSubject(subSSN, fName, lName, "1983", "Oct", "20", "United States");
		// Add citizenship for the subject
		reusable.addCitizenshipForSubject(subSSN, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		// Log out of CATS
		catsLogout();
		jvsreusable.loginToJVS("1");
		jvsreusable.createSMORelationship(categoryType, subSSN);
		jvsreusable.logout();
		return subSSN;

	}

	/**
	 * This method will create hierarchy in the CAF specified and will edit the team member profile to be able to receive auto assigned cases
	 * @param subSSN
	 * @param cafName
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

	public void createHierarchyBasedonCAF(String subSSN, String cafName, String divName, String divChiefFname, String divChiefLName, String branchName, String branchChiefFName, String branchChiefLName, String teamName, String teamChiefFName, String teamChiefLName, String teamMemberFName, String teamMemberLName, String processTeamMemberFName, String processTeamMemberLName,String indProcessTeamMemberFName,String indProcessTeamMemberLName) throws Exception {

		// Add division
		System.out.println("Add Division by the name of Division Hierarchy Test");
		System.out.println("Result:");
		reusable.addDivision("CAF-" + cafName, divName);

		// Add division chief
		System.out.println("Add Division chief ");
		System.out.println("Result:");
		reusable.addDivisionChief("CAF-" + cafName, "DIVISION-" + divName, divChiefFname, divChiefLName);

		// Add branch
		System.out.println("Add branch");
		System.out.println("Result:");
		reusable.addBranch("CAF-" + cafName, "DIVISION-" + divName, branchName);

		// Add Branch chief
		System.out.println("Add branch chief");
		System.out.println("Result:");
		reusable.addBranchChief("CAF-" + cafName, "DIVISION-" + divName, "BRANCH-" + branchName, branchChiefFName, branchChiefLName);

		// Add team
		System.out.println("Add team");
		System.out.println("Result:");
		reusable.addTeam("CAF-" + cafName, "DIVISION-" + divName, "BRANCH-" + branchName, teamName);

		// Add Team chief
		System.out.println("Add Team chief");
		System.out.println("Result:");
		reusable.addTeamChief("CAF-" + cafName, "DIVISION-" + divName, "BRANCH-" + branchName, "TEAM-" + teamName, teamChiefFName, teamChiefLName);

		// Add team member
		System.out.println("Add team member");
		System.out.println("Result:");
		reusable.addTeamMember("CAF-" + cafName, "DIVISION-" + divName, "BRANCH-" + branchName, "TEAM-" + teamName, teamMemberFName, teamMemberLName);

		// edit team member info
		System.out.println("Edit team member info");
		System.out.println("Result:");
		reusable.editTeamMemberDetails("CAF-" + cafName, "DIVISION-" + divName, "BRANCH-" + branchName, "TEAM-" + teamName, teamMemberFName, teamMemberLName, "Yes", "Yes");
		
		//add process team member to process team hierarchy
		System.out.println("Add process tema member to process team hierarchy");
		reusable.addProcessTeamMember(cafName,PROCESS_TEAM_ROLE,processTeamMemberFName,processTeamMemberLName);
		System.out.println("Process Team member added to Process team");

		//add Industry process team member to PSMO-i hierarchy
		System.out.println("Add process tema member to process team hierarchy");
		reusable.addProcessTeamMember(cafName,"PSMO-I Team",indProcessTeamMemberFName,indProcessTeamMemberLName);
		System.out.println("Process Team member added to Process team");

	}

/**
 * This method will create the users for CAF based filtering test
 * cases. It will create users with appropriate roles and permissions and return the list of the same
 * @param caf
 * @return
 * @throws Exception
 */
	public List<UserInfo> createUsersBasedOnCAF(String caf) throws Exception {

		List<UserInfo> retList = new ArrayList<UserInfo>();

		// Division Chief User
		UserInfo uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "DivChfFName", "DivChfLName", DIVISION_CHIEF_ROLE, uInfo);
		retList.add(uInfo);

		// Executive Chief
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "ExeChfFName", "ExeChfLName", EXECUTIVE_CHIEF_ROLE, uInfo);
		retList.add(uInfo);

		// Branch Chief User
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "BrcChfFName", "BrcChfLName", BRANCH_CHIEF_ROLE, uInfo);
		retList.add(uInfo);

		// Team Chief User
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "TeamChFName", "TeamChLName", TEAM_CHIEF_ROLE, uInfo);
		retList.add(uInfo);

		// Adjudicator User
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "AdjuFName", "AdjuLName", ADJUDICATOR_ROLE, uInfo);
		retList.add(uInfo);

		// Process team user
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "ProTeaFName", "ProTeaLName", PROCESS_TEAM_ROLE, uInfo);
		retList.add(uInfo);

		// General counsel user
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "GenCouFName", "GenCouLName", GENERAL_COUNSEL_ROLE, uInfo);
		retList.add(uInfo);

		// ITTFScreener
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "ITTFScFName", "ITTFScLName", ITTFSCREENER_ROLE, uInfo);
		retList.add(uInfo);

		// QualityControl
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "QuaConFName", "QuaConLName", QUALITY_CONTROL_ROLE, uInfo);
		retList.add(uInfo);

		// Trainee
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "TraFName", "TraLName", TRAINEE_ROLE, uInfo);
		retList.add(uInfo);

		// CATS Application Administrator user
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		createUsersForCATSHierarchyTest(caf, "CatsAAFName", "CatsAALName", CATS_APPLICATION_ADMINISTRATOR_ROLE, uInfo);
		retList.add(uInfo);

		// TECT user
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		retList.add(uInfo);
		createUsersForCATSHierarchyTest(caf, "TECTUrFName", "TECTUrLName", TECT_ROLE, uInfo);

		// OPM Liason
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		retList.add(uInfo);
		createUsersForCATSHierarchyTest(caf, "OPMLiaFName", "OPMLiaLName", OPM_LIAISON_ROLE, uInfo);

		// Pscyh Evaluation Team
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		retList.add(uInfo);
		createUsersForCATSHierarchyTest(caf, "PscEvaFName", "PscEvaLName", PSYCH_EVALUATION_TEAM_ROLE, uInfo);
		
		// Industry process team member
		uInfo = new UserInfo();
		uInfo.myCAFName = caf;
		retList.add(uInfo);
		createUsersForCATSHierarchyTest(caf, "IProTeFName", "IProTeLName", INDUSTRY_PROCESS_TEAM_ROLE, uInfo);


		return retList;
	}

	/**
	 * This function will create the subject and user for the subject based on
	 * first name,last name,role passed
	 * @param caf
	 * @param firstnamepatern
	 * @param lastNamepatern
	 * @param role
	 * @param uInfo
	 * @throws Exception
	 */
	public void createUsersForCATSHierarchyTest(String caf, String firstnamepatern, String lastNamepatern, String role, UserInfo uInfo) throws Exception {

		// call ssn gen to create ssn, first name and last name for user
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator(firstnamepatern, lastNamepatern);
		// get first name and last name

		uInfo.role = role;
		uInfo.fName = list.get(0).firstName;
		uInfo.lName = list.get(0).lastName;
		System.out.println("Role is :" + role);
		System.out.println("SSN is " + list.get(0).ssn);
		System.out.println("First name is " + list.get(0).firstName);
		System.out.println("Last name is " + list.get(0).lastName);
		int index = (list.get(0).index);
		String lastFour = ("000" + Integer.toString(index));
		// create user
		uInfo.uName = list.get(0).firstName.substring(0, 6) + (lastFour).substring(lastFour.length() - 4);
		System.out.println("Username is " + uInfo.uName + "\n");

		// create subject for user
		reusable.createSubject(list.get(0).ssn, list.get(0).firstName, list.get(0).lastName, "1983", "20");
		// Create the user based on role
		reusable.createUser(list.get(0).ssn, 22222, caf, uInfo.uName, role);
		System.out.println();

	}

	/**
	 * This method will verify that the case is seen in the subject details page and status is HA and only cases within the same CAF is viewable
	 * @param subSSN
	 * @param subCaseID
	 * @throws Exception
	 */
	public void caseVisibility(String subSSN, String subCaseID) throws Exception {
		//Search for hte subject
		reusable.searchSubjectCATS(subSSN);
		// verify that the case is seen in the subject details page and status
		// is HA
		ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:subjtabview:subCaseDat_data']//td", subCaseID);
		ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:subjtabview:subCaseDat_data']//td[preceding-sibling::td[contains(text(),'"+ subCaseID +"')]]", "Human Adjudication");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:subjtabview:subCaseDat_data']//td[contains(text(),'"+ subCaseID +"')]"));
		pause(5);
		//search for the case with in the same CAF and verify that it's Details is viewable
		reusable.searchCase(subCaseID);
		ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel']", "Case Details");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//span[preceding-sibling::a[contains(text(),'Case Details')]]"));
		pause(5);

	}
	/**
	 * This method will verify that the error message is seen when user searches for a case outside of the CAF they are provisioned in
	 * @param subSSN
	 * @param otherCAFCaseID
	 * @throws Exception
	 */
	public void searchCaseOutsideCAF(String subSSN, String otherCAFCaseID) throws Exception {
		//Search for the case in another CAF
		reusable.searchCase(otherCAFCaseID);
		// verify error message is seen
		ReusableFunctions.waitAndVerify_IfContains("//span[@id='cpanelForm:findCaseMsg']", "You cannot view a case belonging to another CAF");
		pause(5);
	}
	/**
	 * This method will verify that the cases outside of the same CAF is listed in Subject details page but the details are not viewable when outside the CAF
	 * @param subSSN
	 * @param otherCAFCaseID
	 * @throws Exception
	 */
	public void caseOutsideCAFSeenInSubjectDetailsPage(String subSSN, String otherCAFCaseID) throws Exception {
		reusable.searchSubjectCATS(subSSN);
		ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:subjtabview:subCaseDat_data']//td", otherCAFCaseID);
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:subjtabview:subCaseDat_data']//td[contains(text(),'"+ otherCAFCaseID +"')]"));
		// pause(2);
		// ReusableFunctions.waitAndVerify_IfContains("//div[@id='ui-growl-message']",
		// "You cannot view a case belonging to another CAF");
		pause(2);
		ReusableFunctions.waitAndVerifyIfNotContains("//div[@id='majorTabPanel']", "Case Details");
		pause(5);
	}
	/**
	 * This method will verify that only one open case per subject per CAF is available and user in the same CAF can create case for that subject
	 * @param subWithoutOpenCaseInCurrentCAFSSN
	 * @throws Exception
	 */
	public void oneOpenCasePerCAF(String subWithoutOpenCaseInCurrentCAFSSN) throws Exception {
		reusable.searchSubjectCATS(subWithoutOpenCaseInCurrentCAFSSN);
		pause(2);
		// Click on the Subject Action drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.SubjectActionBtn.getProperty()));
		// Verify Create case option is enabled
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyEnabled(By.xpath(CATSProperties.CreateCaseDropdownOption.getProperty()));

		// Click on the create case option in the drop down list
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CreateCaseDropdownOption.getProperty()));
		pause(2);
		// click cancel in the create case pop up
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='createCaseForm:createCaseClearButton']"));
		pause(2);

	}

/**
 * This method will delete the hierarchies created in CAFs passed to it.
 * @param list
 * @param user
 * @param cafName
 * @param divName
 * @param branchName
 * @param teamName
 * @throws Exception
 */
	public void deleteHierarchyBasedOnCAF(List<UserInfo> list, String user, String cafName, String divName, String branchName, String teamName) throws Exception {
		reusable.loginToCATS(user);
		String divChiefFname = null;
		String branchChiefFName = null;
		String teamChiefFName = null;
		String teamMemberFName = null;
		String teamMemberLName = null;
		String divisionName = "DIVISION-" + divName;
		String branName = "BRANCH-" + branchName;
		String tName = "TEAM-" + teamName;
		String cName = "CAF-" + cafName;

		for (UserInfo userInfo : list) {
			if (userInfo.role.equalsIgnoreCase(DIVISION_CHIEF_ROLE) && userInfo.myCAFName.equalsIgnoreCase(cafName)) {
				divChiefFname = userInfo.fName;
				System.out.println("Div chief Fname :" + divChiefFname);

			} else if (userInfo.role.equalsIgnoreCase(BRANCH_CHIEF_ROLE) && userInfo.myCAFName.equalsIgnoreCase(cafName)) {
				branchChiefFName = userInfo.fName;
				System.out.println("bran chief Fname :" + branchChiefFName);

			} else if (userInfo.role.equalsIgnoreCase(TEAM_CHIEF_ROLE) && userInfo.myCAFName.equalsIgnoreCase(cafName)) {
				teamChiefFName = userInfo.fName;
				System.out.println("team chief Fname :" + teamChiefFName);

			} else if (userInfo.role.equalsIgnoreCase(ADJUDICATOR_ROLE) && userInfo.myCAFName.equalsIgnoreCase(cafName)) {
				teamMemberFName = userInfo.fName;
				System.out.println("Team mem Fname :" + teamMemberFName);
				teamMemberLName = userInfo.lName;
				System.out.println("lName" + teamMemberLName);
			}
		}

		// Delete team member
		System.out.println("Delete team member");
		System.out.println("Result:");
		reusable.deleteTeamMember(cName, divisionName, branName, tName, teamMemberFName, teamMemberLName);

		// delete team chief
		System.out.println("Delete team chief");
		System.out.println("Result:");
		reusable.deleteTeamChief(cName, divisionName, branName, tName, teamChiefFName);

		// delete team
		System.out.println("Delete team");
		System.out.println("Result:");
		reusable.deleteTeam(cName, divisionName, branName, tName);

		// delete branch chief
		System.out.println("Delete branch chief");
		System.out.println("Result:");
		reusable.deleteBranchChief(cName, divisionName, branName, branchChiefFName);

		// delete branch
		System.out.println("Delete branch");
		System.out.println("Result:");
		reusable.deleteBranch(cName, divisionName, branName);

		// delete division chief
		System.out.println("Delete division chief");
		System.out.println("Result:");
		reusable.deleteDivisionChief(cName, divisionName, divChiefFname);

		// delete division
		System.out.println("Delete division");
		System.out.println("Result:");
		reusable.deleteDivision(cName, divisionName);
		catsLogout();

	}

}
