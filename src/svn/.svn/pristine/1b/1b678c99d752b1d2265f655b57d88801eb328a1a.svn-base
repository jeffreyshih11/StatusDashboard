package com.iworks.DISS.test.catsRegression.manageHierarchy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;

import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.*;

/**
 * This class will create test data required to test the following manual TCs:
 * 5799,5801,5802,5804,5805,5807,5808,
 * 5809,5810,5811,5812,5813,5816,5817,5818,5819,5820,5821,16049
 * and verify the functionality of adding, editing, deleting a division, branch, team, division chief,branch chief
 * team chief, team member. Verify the details of CAF,Division,branch,team and team member.
 * Adding team member to same team again,deleting division or branch with child nodes.
 * 
 * @author vshivaraman
 *
 */
public class CAFHierarchy extends TestBase {

	private static final String ADJUDICATOR_ROLE = "Adjudicator";
	private static final String TEAM_CHIEF_ROLE = "Team Chief";
	private static final String BRANCH_CHIEF_ROLE = "Branch Chief";
	private static final String DIVISION_CHIEF_ROLE = "Division Chief";

	public static class UserInfo {
		public String fName;
		public String lName;
		public String uName;
		public String ssn;
		public String role;

	}
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	public Class<? extends CAFHierarchy> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	/**
	 * This method will create a log file in th epath specified to log all the
	 * console messages to a word doc
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className, name);

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
	 * methods for verifying the functionalities for managing hierarchy based 
	 * on the CAF, division,branch,team,team member names and the chief names of 
	 * division,branch and team passed to the methods
	 * 
	 * @throws Exception
	 */
	@Test
	public void manageHierarchy() throws Exception {
		//Instance of class SSNFNameAndLName
		SSNFNameAndLName sub = new SSNFNameAndLName();
		// Call the createtestData function to create users with different roles
		List<UserInfo> list = createTestData(sub);
		//Map role to userInfo
		Map<String, UserInfo> roleToUserInfomap = new HashMap<String, CAFHierarchy.UserInfo>();
		//for eachuserInfo in the list map the role to info
		for (UserInfo userInfo : list) {
			roleToUserInfomap.put(userInfo.role, userInfo);
		}
		// Call the create test data method to create division chief, branch
		// chief, team chief and a team member users
		

		// Get the div, branch, team chief and team members first and last names
		String divChiefFName = roleToUserInfomap.get(DIVISION_CHIEF_ROLE).fName;
		String divChiefLastName = roleToUserInfomap.get(DIVISION_CHIEF_ROLE).lName;
		String branchChiefFName = roleToUserInfomap.get(BRANCH_CHIEF_ROLE).fName;
		String branchChiefLName = roleToUserInfomap.get(BRANCH_CHIEF_ROLE).lName;
		String teamChiefFName = roleToUserInfomap.get(TEAM_CHIEF_ROLE).fName;
		String teamChiefLName = roleToUserInfomap.get(TEAM_CHIEF_ROLE).lName;
		String teamMemberFName = roleToUserInfomap.get(ADJUDICATOR_ROLE).fName;
		String teamMemberLName = roleToUserInfomap.get(ADJUDICATOR_ROLE).lName;
		String teamMemeberUserName = roleToUserInfomap.get(ADJUDICATOR_ROLE).uName;
		verifyManageHierarchyNotSeen(teamMemeberUserName);
		catsLogout();
		// Call the verify manage hierarchy method to run the tests for manage
		// hierarchy
		verifyManageHierarchy("1", "CAF-DoD CAF", "Kelly Brown", "DIVISION-Division Hierarchy Test", "DIVISION-Division Hierarchy Test 1", divChiefFName, divChiefLastName, "BRANCH-Branch Hierarchy test", "BRANCH-Branch Hierarchy test 1", branchChiefFName, branchChiefLName, "TEAM-Team Hierarchy test", "TEAM-Team Hierarchy test 1", teamChiefFName, teamChiefLName, teamMemberFName, teamMemberLName);
		catsLogout();

		verifyManageHierarchyNotSeen(teamMemeberUserName);
		catsLogout();

	}

	/**
	 * This method will create the test data required for manage hierarchy test
	 * cases. It will create division chief, branch chief, team chief and team
	 * member users with appropriate roles and permissions and return the list
	 * of the same
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> createTestData(SSNFNameAndLName sub) throws Exception {

		reusable.loginToCATS("1");

		List<UserInfo> retList = new ArrayList<>();
		
		//Create Division chief
		UserInfo uInfo = new UserInfo();
		// call ssn gen to create div chief user
		createUsersForCATSHierarchyTest("DivChfFName", "DivChfLName",  DIVISION_CHIEF_ROLE, uInfo);
		retList.add(uInfo);


		//Branch Chief User
		uInfo = new UserInfo();

		createUsersForCATSHierarchyTest("BrcChfFName", "BrcChfLName",  BRANCH_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
		
		//Team Chief User
		uInfo = new UserInfo();
		createUsersForCATSHierarchyTest("TeamChFName", "TeamChLName", TEAM_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
	
		//Adjudicator User
		uInfo = new UserInfo();
		createUsersForCATSHierarchyTest("AdjuFName", "AdjuLName",  ADJUDICATOR_ROLE, uInfo);
		retList.add(uInfo);

		catsLogout();
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
	public void createUsersForCATSHierarchyTest(String firstnamepatern,String lastNamepatern,  String role,
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
				
			// create subject for user
			reusable.createSubject(uInfo.ssn, uInfo.fName, uInfo.lName, "1983", "20");
			//Create the user based on role
			reusable.createUser(uInfo.ssn, 22222, "DoD CAF", uInfo.uName,role);
			System.out.println(uInfo.role+" userName is " + uInfo.uName+"\n");
			System.out.println();
			
			
		}

	/**
	 * This method will verify that users passed do not have the ability to view
	 * manage hierarchy and CAF tree tab
	 * 
	 * @param userName
	 * @throws Exception
	 */
	public void verifyManageHierarchyNotSeen(String userName) throws Exception {

		// Log in to CATS as user passed
		reusable.loginToCATS(userName);

		WaitingToLoad();

		// Verify that Manage hierarchy is NOT seen in the LHS panel
		com.iworks.DISS.test.common.functions.ReusableFunctions.verifyFalse(By.xpath(CATSProperties.Container1.getProperty()), "Manage Hierarchy");

		// Verify CAF tree tab is Not seen
		com.iworks.DISS.test.common.functions.ReusableFunctions.verifyFalse(By.xpath(CATSProperties.Container2.getProperty()), "CAF Tree");

	}

	/**
	 * This method will verify that the users passed to it have the ability to
	 * view manage hierarchy and CAF tree and can add division,branch,team,team
	 * members. Edit division,branch,team.Delete Division,branch,team and team
	 * members and can view the details of division,branch,team and team members
	 * based on the parameters passed to the method.
	 * 
	 * @param user
	 * @param cafName
	 * @param cafChiefName
	 * @param divName
	 * @param divName1
	 * @param divChiefFname
	 * @param divChiefLName
	 * @param branchName
	 * @param branchName1
	 * @param branchChiefFName
	 * @param branchChiefLName
	 * @param teamName
	 * @param teamName1
	 * @param teamChiefFName
	 * @param teamChiefLName
	 * @param teamMemberFName
	 * @param teamMemberLName
	 * @throws Exception
	 */
	
	public void verifyManageHierarchy(String user, String cafName, String cafChiefName, String divName, String divName1, String divChiefFname, String divChiefLName, String branchName, String branchName1, String branchChiefFName, String branchChiefLName, String teamName, String teamName1, String teamChiefFName, String teamChiefLName, String teamMemberFName, String teamMemberLName) throws Exception {

		// Log in to CATS as user passed
		reusable.loginToCATS(user);

		// Verify that Manage hierarchy is seen in the LHS panel
		System.out.println("Verify if manage hierarchy is seen");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container1.getProperty(), "Manage Hierarchy");

		// Verify that Manual Assignment is seen in the LHS panel
		System.out.println("Verify if manual assignment is seen");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container1.getProperty(), "Manual Assignment");

		// Click on the manage hierarchy link
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));

		// Verify that Hierarchy Layout is seen
		System.out.println("Verify if hierarchy layout is seen.");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Hierarchy Layout");
		System.out.println(user + " is able to view CAF Hiearachy layout");
		System.out.println();

		// Verify CAF tree tab is seen
		System.out.println("Verify if CAF Tree is seen");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "CAF Tree");

		// Verify Name is seen
		System.out.println("Verify if Name is seen");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Name");

		// Verify Chief is seen
		System.out.println("Verify if Chief column is seen");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Chief");

		// Verify Deputy is seen
		System.out.println("Verify if Deputy column is seen");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains(CATSProperties.Container2.getProperty(), "Deputy");

		// Verify CAF-DoD CAF is seen
		System.out.println("Verify if 'CAF-DoD CAF' is seen");
		System.out.println("Result:");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td", "CAF-DoD CAF");

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

		// Verify CAF chief name contains the same text as parameter
		// cafChiefName
		System.out.println("Verify CAF chief name contains the same text as parameter cafChiefName");
		System.out.println("Result:");
		verifyCAFChiefname(cafName, cafChiefName);

		// Add division
		System.out.println("Add Division by the name of Division Hierarchy Test");
		System.out.println("Result:");
		reusable.addDivision(cafName, "Division Hierarchy Test");

		// Add division chief
		System.out.println("Add Division chief ");
		System.out.println("Result:");
		reusable.addDivisionChief(cafName, divName, divChiefFname, divChiefLName);

		// verify division chief name
		System.out.println("Verify division chief");
		System.out.println("Result:");
		verifyDivisionChiefname(cafName, divName, divChiefFname + " " + divChiefLName);

		// Edit and verify division edit
		System.out.println("Edit Division from divName to divName1");
		System.out.println("Result:");
		reusable.editDivision(cafName, divName, divName1);

		// edit and put the division name back to first division name
		System.out.println("Edit Division from divName1 to divName");
		System.out.println("Result:");
		reusable.editDivision(cafName, divName1, divName);

		// Add branch
		System.out.println("Add branch");
		System.out.println("Result:");
		reusable.addBranch(cafName, divName, "Branch Hierarchy test");

		// edit branch name to baranchname1 and verify the edit
		System.out.println("Edit branch from name to name1");
		System.out.println("Result:");
		reusable.editBranch(cafName, divName, branchName, branchName1);

		// edit and put back to branchname from branchname1
		System.out.println("Edit branch from name1 to name");
		System.out.println("Result:");
		reusable.editBranch(cafName, divName, branchName1, branchName);

		// Add Branch chief
		System.out.println("Add branch chief");
		System.out.println("Result:");
		reusable.addBranchChief(cafName, divName, branchName, branchChiefFName, branchChiefLName);

		// verify branch chief name
		System.out.println("Verify branch chief name");
		System.out.println("Result:");
		verifyBranchChiefname(cafName, divName, branchName, branchChiefFName + " " + branchChiefLName);

		// Add team
		System.out.println("Add team");
		System.out.println("Result:");
		reusable.addTeam(cafName, divName, branchName, "Team Hierarchy test");

		// edit team name from teamName to teamName1 and verify the edit
		System.out.println("Edit team name from teamName to teamName1 and verify the edit");
		System.out.println("Result:");
		reusable.editTeam(cafName, divName, branchName, teamName, teamName1);

		// edit and put back team name from teamName1 to teamName
		System.out.println("Edit team name from teamName1 to teamName and verify the edit");
		System.out.println("Result:");
		reusable.editTeam(cafName, divName, branchName, teamName1, teamName);

		// Add Team chief
		System.out.println("Add Team chief");
		System.out.println("Result:");
		reusable.addTeamChief(cafName, divName, branchName, teamName, teamChiefFName, teamChiefLName);

		// verify team chief name
		System.out.println("Verify team chief name");
		System.out.println("Result:");
		verifyTeamChiefname(cafName, divName, branchName, teamName, teamChiefFName + " " + teamChiefLName);

		// Add team member
		System.out.println("Add team member");
		System.out.println("Result:");
		reusable.addTeamMember(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);
		
		//Verify CAF Details
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


		// Add team member again
		System.out.println("Add same team member again");
		System.out.println("Result:");
		addTeamMemberAgain(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);

		// edit team member info
		System.out.println("Edit team member info");
		System.out.println("Result:");
		reusable.editTeamMemberDetails(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName, "Yes", "Yes");

		catsLogout();
		reusable.loginToCATS("1");
		// Delete team member
		System.out.println("Delete team member");
		System.out.println("Result:");
		reusable.deleteTeamMember(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);

		// delete team chief
		System.out.println("Delete team chief");
		System.out.println("Result:");
		reusable.deleteTeamChief(cafName, divName, branchName, teamName, teamChiefFName);

		// delete branch with child
		System.out.println("Delete branch with child node");
		System.out.println("Result:");
		deleteBranchNodeWithChild(cafName, divName, branchName);

		// delete team
		System.out.println("Delete team");
		System.out.println("Result:");
		reusable.deleteTeam(cafName, divName, branchName, teamName);

		// delete branch chief
		System.out.println("Delete branch chief");
		System.out.println("Result:");
		reusable.deleteBranchChief(cafName, divName, branchName, branchChiefFName);

		// delete division with child node
		System.out.println("Delete division with child node");
		System.out.println("Result:");
		deleteDivisionNodeWithChild(cafName, divName);

		// delete branch
		System.out.println("Delete branch");
		System.out.println("Result:");
		reusable.deleteBranch(cafName, divName, branchName);

		// delete division chief
		System.out.println("Delete division chief");
		System.out.println("Result:");
		reusable.deleteDivisionChief(cafName, divName, divChiefFname);

		// delete division
		System.out.println("Delete division");
		System.out.println("Result:");
		reusable.deleteDivision(cafName, divName);

	}

	/**
	 * This method will try to delete a branch with child and verify that the
	 * warning message is see and user cannot delete the node
	 * 
	 * @param cafName
	 * @param divName
	 * @param branchName
	 */
	public void deleteBranchNodeWithChild(String cafName, String divName, String branchName) {
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ cafName +"']"));
		pause(2);

		// Select the CAF
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='"+ cafName +"']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

		// Select the Division
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ divName +"']/span[2]"));

		// Select the Branch
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ branchName +"']/span[3]"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.rightClick1("//*/td[text()='"+ branchName +"']/span");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Delete"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-messages-warn-detail", "Delete children before deleting this node");

		System.out.println("Branch has child node hence was not ableto delete. This test is a PASS");
		System.out.println();
	}

	/**
	 * This method will delete a division node with child and verify that the
	 * warning message is seen and the node is not deleted
	 * 
	 * @param cafName
	 * @param divName
	 */
	public void deleteDivisionNodeWithChild(String cafName, String divName) {
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ cafName +"']"));
		pause(2);

		// Select the CAF
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='"+ cafName +"']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

		// Select the Division
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ divName +"']/span[2]"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.rightClick1("//*/td[text()='"+ divName +"']/span");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Delete"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-messages-warn-detail", "Delete children before deleting this node");

		System.out.println("Branch has child node hence was not ableto delete. This test is a PASS");
		System.out.println();
	}

/**
 * 
 * @param cafName
 * @param divName
 * @param branchName
 * @param teamName
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
	public void addTeamMemberAgain(String cafName, String divName, String branchName, String teamName, String teamMemberFName, String teamMemberLName)throws Exception {

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ cafName +"']"));
		pause(2);

		// Select the CAF
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='"+ cafName +"']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

		// Select the Division
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ divName +"']/span[2]"));

		// Select the Branch
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ branchName +"']/span[3]"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.rightClick1("//*/td[text()='"+ teamName +"']/span");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Add Member");

		// filter by first name
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId:userSearchFirstName:filter']"), teamMemberFName);

		// verify that team member name s not available in the search result

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId_data']/tr/td", "No Users Found");
		reusable.userSelectPagination("No Users Found");
		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable("No Users Found", "//*[@id='formWrapperToMakeDataTableFiltersWork:cafSearchUserTableId']/div[2]/table");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");

		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
		pause(5);

		System.out.println(teamMemberFName + " cannot be added to  " + teamName + "as it is already added as a team member");

		System.out.println();

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));

	}

	public void verifyCAFChiefname(String cafName, String cafChiefName) {
		List<WebElement> webelementLst = null;
		WebElement sub = null;
		List<WebElement> cells = null;

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ cafName +"']"));
		pause(2);
		
		// Select the CAF
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='"+ cafName +"']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

		// Verify CAF chief name
		String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId_data']";
		webelementLst = driver.findElements(By.xpath(tableXPath));
		sub = ReusableFunctions.selectElementContains(webelementLst, cafName);
		cells = sub.findElements(By.tagName("tr"));
		String cafChiefName1 = ReusableFunctions.selectElementContains(cells, cafName).findElements(By.tagName("td")).get(1).getText();

		if (cafChiefName1.contains(cafChiefName)) {
			System.out.println("Verified division chief name as " + cafChiefName);
			System.out.println();
		} else {
			System.out.println("Division chief name is not verified as  " + cafChiefName + " but is seen as " + cafChiefName1);
			System.out.println();
		}
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");

		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
		pause(5);


	}

	public void verifyDivisionChiefname(String cafName, String divisionName, String divisionChiefName) {
		List<WebElement> webelementLst = null;
		WebElement sub = null;
		List<WebElement> cells = null;
		String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId_data']";

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ cafName +"']"));
		pause(2);

		// Select the CAF
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='"+ cafName +"']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ divisionName +"']"));

		// Verify division chief name
		webelementLst = driver.findElements(By.xpath(tableXPath));
		sub = ReusableFunctions.selectElementContains(webelementLst, divisionName);
		cells = sub.findElements(By.tagName("tr"));
		String divChiefName = ReusableFunctions.selectElementContains(cells, divisionName).findElements(By.tagName("td")).get(1).getText();

		if (divChiefName.contains(divisionChiefName)) {
			System.out.println("Verified division chief name as " + divisionChiefName);
			System.out.println();
		} else {
			System.out.println("Division chief name is not verified as  " + divisionChiefName + " but is seen as " + divChiefName);
			System.out.println();
		}
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");

		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
		pause(5);


	}

	public void verifyBranchChiefname(String cafName, String divisionName, String branchName, String branchChiefName) {
		List<WebElement> webelementLst = null;
		WebElement sub = null;
		List<WebElement> cells = null;
		String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId_data']";
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ cafName +"']"));
		pause(2);
		// Select the CAF
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='"+ cafName +"']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ divisionName +"']/span[2]"));
		// Select the Branch
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ branchName +"']"));

		// Verify CAF chief name
		webelementLst = driver.findElements(By.xpath(tableXPath));
		sub = ReusableFunctions.selectElementContains(webelementLst, branchName);
		cells = sub.findElements(By.tagName("tr"));
		String chiefName = ReusableFunctions.selectElementContains(cells, branchName).findElements(By.tagName("td")).get(1).getText();

		if (chiefName.contains(branchChiefName)) {
			System.out.println("Verified branch chief name as " + branchChiefName);
			System.out.println();
		} else {
			System.out.println("Division chief name is not verified as  " + branchChiefName + " but is seen as " + chiefName);
			System.out.println();
		}
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");

		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
		pause(5);


	}

	public void verifyTeamChiefname(String cafName, String divisionName, String branchName, String teamName, String teamChiefName) {
		List<WebElement> webelementLst = null;
		WebElement sub = null;
		List<WebElement> cells = null;
		String tableXPath = "//*[@id='majorTabPanel:cafHierarchyTreeTableId_data']";
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ cafName +"']"));
		pause(2);
		// Select the CAF
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:cafHierarchyTreeTableId_data']/tr/td[text()='"+ cafName +"']/span[contains(concat(' ',normalize-space(@class),' '),'ui-treetable-toggler')]"));

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ divisionName +"']/span[2]"));
		// Select branch
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ branchName +"']/span[3]"));

		// Select the Team
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='"+ teamName +"']"));

		// Verify chief name
		webelementLst = driver.findElements(By.xpath(tableXPath));
		sub = ReusableFunctions.selectElementContains(webelementLst, teamName);
		cells = sub.findElements(By.tagName("tr"));
		String chiefName = ReusableFunctions.selectElementContains(cells, teamName).findElements(By.tagName("td")).get(1).getText();

		if (chiefName.contains(teamChiefName)) {
			System.out.println("Verified division chief name as " + teamChiefName);
			System.out.println();
		} else {
			System.out.println("Division chief name is not verified as  " + teamChiefName + " but is seen as " + chiefName);
			System.out.println();
		}
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");

		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");

		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Changes saved successfully");
		pause(5);


	}

}
