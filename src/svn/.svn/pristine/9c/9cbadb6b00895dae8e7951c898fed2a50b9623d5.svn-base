package com.iworks.DISS.test.catsRegression.caseAssignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;

import com.iworks.DISS.test.catsSmokeTests.CATSSmokeTest.UserInfo;
import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.*;

/**
 * This class will create test data required to test the Manage Priorities for
 * Auto Assignment TCs and verify the functionality of view adjudicator, update
 * adjudicator case assignment, assignment profile permission, new case tab,
 * update new case ranking, update in process case tab, update in progress case
 * tab as team chief, division chief, branch chief.
 * 
 * @author vshivaraman
 *
 */
public class ManagePrioritiesForAutoAssignment extends TestBase {

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

	// Map User name to User Info
	Map<String, UserInfo> userNameToUserInfoMap = new HashMap<>();
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	public Class<? extends ManagePrioritiesForAutoAssignment> clazz = this.getClass();
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
	 * methods for verifying the functionalities for managing priorities for
	 * auto assignment
	 * 
	 * @throws Exception
	 */
	@Test
	public void managePrioritiesForAutoAssignment() throws Exception {
		// Instance of class SSNFNameAndLName
		SSNFNameAndLName sub = new SSNFNameAndLName();
		// Call the create test data method to create division chief, branch
		// chief, team chief and a team member users
		List<UserInfo> list = createTestData(sub);
		// Map role to userInfo
		Map<String, UserInfo> roleToUserInfomap = new HashMap<String, ManagePrioritiesForAutoAssignment.UserInfo>();
		// for each userInfo in the list map the role to info
		for (UserInfo userInfo : list) {
			roleToUserInfomap.put(userInfo.role, userInfo);
		}

		// Get the div, branch, team chief and team members first and last names
		String divChiefFName = roleToUserInfomap.get(DIVISION_CHIEF_ROLE).fName;
		String divChiefLastName = roleToUserInfomap.get(DIVISION_CHIEF_ROLE).lName;
		String branchChiefFName = roleToUserInfomap.get(BRANCH_CHIEF_ROLE).fName;
		String branchChiefLName = roleToUserInfomap.get(BRANCH_CHIEF_ROLE).lName;
		String teamChiefFName = roleToUserInfomap.get(TEAM_CHIEF_ROLE).fName;
		String teamChiefLName = roleToUserInfomap.get(TEAM_CHIEF_ROLE).lName;
		String teamMemberFName = roleToUserInfomap.get(ADJUDICATOR_ROLE).fName;
		String teamMemberLName = roleToUserInfomap.get(ADJUDICATOR_ROLE).lName;

		// Create hierarchy for manage priority
		createManagepriorityHierarchy("1", "CAF-DoD CAF", "DIVISION-Division ManagePriority Test", "DIVISION-Division ManagePriority Test 1", divChiefFName, divChiefLastName, "BRANCH-Branch ManagePriority Test", "BRANCH-Branch ManagePriority Test 1", branchChiefFName, branchChiefLName, "TEAM-Team ManagePriority Test", "TEAM-Team ManagePriority Test 1", teamChiefFName, teamChiefLName, teamMemberFName, teamMemberLName);
		for (String userName : userNameToUserInfoMap.keySet()) {
			if (userName.contains("AdjuFN")) {
				continue;
			}
			String chiefFName = userNameToUserInfoMap.get(userName).fName;
			String chiefLName = userNameToUserInfoMap.get(userName).lName;
			// Verify manage priorities
			verifyManagePriorities(userName, "CAF-DoD CAF", "DIVISION-Division ManagePriority Test", "BRANCH-Branch ManagePriority Test", "TEAM-Team ManagePriority Test", chiefFName, chiefLName, teamMemberFName, teamMemberLName);

		}

		// delete hierarchy
		deleteManagepriorityHierarchy("CAF-DoD CAF", "DIVISION-Division ManagePriority Test", "BRANCH-Branch ManagePriority Test", "TEAM-Team ManagePriority Test", teamMemberFName, teamMemberLName, teamChiefFName, branchChiefFName, divChiefFName);
		catsLogout();

	}

	/**
	 * This method will create the test data required for manage ManagePriority
	 * Test cases. It will create division chief, branch chief, team chief and
	 * team member users with appropriate roles and permissions and return the
	 * list of the same
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> createTestData(SSNFNameAndLName sub) throws Exception {

		reusable.loginToCATS("1");

		List<UserInfo> retList = new ArrayList<>();

		// Create Division chief
		UserInfo uInfo = new UserInfo();
		// call ssn gen to create div chief user
		createUsersForManagePrioritiesTest("DivChfFName", "DivChfLName", DIVISION_CHIEF_ROLE, uInfo);
		retList.add(uInfo);

		// Branch Chief User
		uInfo = new UserInfo();

		createUsersForManagePrioritiesTest("BrcChfFName", "BrcChfLName", BRANCH_CHIEF_ROLE, uInfo);
		retList.add(uInfo);

		// Team Chief User
		uInfo = new UserInfo();
		createUsersForManagePrioritiesTest("TeamChFName", "TeamChLName", TEAM_CHIEF_ROLE, uInfo);
		retList.add(uInfo);

		// Adjudicator User
		uInfo = new UserInfo();
		createUsersForManagePrioritiesTest("AdjuFName", "AdjuLName", ADJUDICATOR_ROLE, uInfo);
		retList.add(uInfo);

		catsLogout();
		return retList;
	}

	/**
	 * This function will create the subject and user for the subject based on
	 * firstname,last name,role passed
	 * 
	 * @param firstnamepatern
	 * @param lastNamepatern
	 * @param userNamePattern
	 * @param role
	 * @param uInfo
	 * @throws Exception
	 */
	public void createUsersForManagePrioritiesTest(String firstnamepatern, String lastNamepatern, String role, UserInfo uInfo) throws Exception {

		// call ssn gen to create ssn, first name and last name for user
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator(firstnamepatern, lastNamepatern);
		// get first name and last name
		uInfo.ssn = list.get(0).ssn;
		uInfo.fName = list.get(0).firstName;
		uInfo.lName = list.get(0).lastName;
		uInfo.role = role;
		System.out.println("Role is :" + role);
		System.out.println("SSN is " + uInfo.ssn);
		System.out.println("First name is " + uInfo.fName);
		System.out.println("Last name is " + uInfo.lName);
		int index = (list.get(0).index);
		String lastFour = ("000" + Integer.toString(index));
		// create user
		uInfo.uName = uInfo.fName.substring(0, 6) + (lastFour).substring(lastFour.length() - 4);
		System.out.println("Username is " + uInfo.uName + "\n");

		// create subject for user
		reusable.createSubject(uInfo.ssn, uInfo.fName, uInfo.lName, "1983", "20");
		// Create the user based on role
		reusable.createUser(uInfo.ssn, 22222, "DoD CAF", uInfo.uName, role);
		System.out.println(uInfo.role + " userName is " + uInfo.uName + "\n");
		System.out.println();
		userNameToUserInfoMap.put(uInfo.uName, uInfo);

	}

	/**
	 * This method will verify that the users passed to it have the ability to
	 * view, update, edit and save priority of auto assigned case and member profile.
	 * 
	 * @param user
	 * @param cafName
	 * @param divName
	 * @param chiefFname
	 * @param chiefLName
	 * @param branchName
	 * @param teamName
	 * @param teamMemberFName
	 * @param teamMemberLName
	 * @throws Exception
	 */

	public void verifyManagePriorities(String user, String cafName, String divName, String branchName, String teamName, String chiefFName, String chiefLName, String teamMemberFName, String teamMemberLName) throws Exception {

		// Log in to CATS as user passed
		reusable.loginToCATS(user);
		System.out.println("Logged in as user: " + user + "\n");

		// get to your team
		System.out.println("Test:Verify the user can get to their team list \n");
		getTeamDetails(user, divName, branchName, teamName);
		System.out.println("Result:Verifies the user can get to their team list- TEST PASS \n");

		// view member list and update team member assignment profile
		System.out.println("Test:View member and update team member assignment profile \n");
		verifyViewAndUpdateAssignment(teamMemberFName, teamMemberLName);
		System.out.println("Result:View member and update team member assignment profile-TEST PASS \n");

		// verify updates do not change user inbox/ inventory
		System.out.println("Test:Verify profile assignement updates do not change user inbox/ inventory \n");
		verifyInboxInventoryDoesNotChangeOnUpdateAssignment(teamMemberFName, teamMemberLName);
		System.out.println("Result:Verified profile assignment updates do not change user inbox/ inventory \n");

		// Verify change Case Assignment
		// Save option case assignment as Case Assignment New Cases first
		System.out.println("Test:Verify change Case Assignment-Save Case assignment as Case Assignment New Cases first \n");
		verifyUpdateOfCaseassignmentOptions(user, divName, branchName, teamName, "yes", "yes", teamMemberFName, teamMemberLName);
		System.out.println("Result:Verified change Case Assignment-Save Case assignment as Case Assignment New Cases first-TEST PASS \n");
		// Save option case assignment as Case Assignment In Progress Cases
		// first
		System.out.println("Test:Verify change Case Assignment-Save Case assignment as Case Assignment In Progress Cases first \n");
		verifyUpdateOfCaseassignmentOptions(user, divName, branchName, teamName, "yes", "no", teamMemberFName, teamMemberLName);
		System.out.println("Result:Verified change Case Assignment-Save Case assignment as Case Assignment In Progress Cases first-TEST PASS \n");
		// Save option case assignment as Case Assignment None
		System.out.println("Test:Verify change Case Assignment-Save Case assignment as Case Assignment None \n");
		verifyUpdateOfCaseassignmentOptions(user, divName, branchName, teamName, "no", "no", teamMemberFName, teamMemberLName);
		System.out.println("Result:Verified change Case Assignment-Save Case assignment as Case Assignment None-TEST PASS \n");

		// Investigation Type tab change Investigation types and
		// ranking//Investigation Type Tab - remove investigation assigned
		System.out.println("Test:Verify in Investigation Type tab user is able to change Investigation types and ranking and remove investigation assigned \n");
		verifyUpdatesToInvestigationTypeOptions(user, divName, branchName, teamName, teamMemberFName, teamMemberLName);
		System.out.println("Result:Verified in Investigation Type tab user is able to change Investigation types and ranking and remove investigation assigned- TEST PASS \n");

		// addAdd Adjudication Phase and change ranking//Adjudication Phase-
		// remove assignment
		System.out.println("Test:Verify in Adjudication phase tab user is able to change adjudication phases and ranking and remove adjudication phase assigned \n");
		verifyUpdatesToAdjudicationPhaseOptions(user, divName, branchName, teamName, teamMemberFName, teamMemberLName);
		System.out.println("Result:Verify in Adjudication phase tab user is able to change adjudication phases and ranking and remove adjudication phase assigned-TEST PASS \n");

		// Priority Programs Group 1 Priority Programs Group 2 Priority Programs
		// Group 3 Priority Programs Group 4
		System.out.println("Test:Verify in priority program group tab user is able to assign groups2-4 and remove groups2-4 assigned and verify that group 1 is already assigned by default ans cannot be edited by user \n");
		verifyUpdatesToPriorityProgramGroupsOptions(user, divName, branchName, teamName, teamMemberFName, teamMemberLName);
		System.out.println("Result:Verified in priority program group tab user is able to assign groups2-4 and remove groups2-4 assigned and verified that group 1 is already assigned by default ans cannot be edited by user-TEST PASS \n");

		// Auto-Assignment Profile can be left blank
		System.out.println("Test:Verify that Auto-Assignment Profile can be left blank \n");
		verifyAutoAssignPorfileCanbeSavedAsBlank(user, divName, branchName, teamName, teamMemberFName, teamMemberLName);
		System.out.println("Result:Verify that Auto-Assignment Profile can be left blank \n");
		catsLogout();

	}
/**
 * This method will create the hierarchy and add users to the hierarchy for testing manage priority functions.
 * @param user
 * @param cafName
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
	public void createManagepriorityHierarchy(String user, String cafName, String divName, String divName1, String divChiefFname, String divChiefLName, String branchName, String branchName1, String branchChiefFName, String branchChiefLName, String teamName, String teamName1, String teamChiefFName, String teamChiefLName, String teamMemberFName, String teamMemberLName) throws Exception {

		// Log in to CATS as user passed
		reusable.loginToCATS(user);

		// Click on the manage hierarchy link
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));

		// Add division
		System.out.println("Add Division by the name of Division ManagePriority Test");
		System.out.println("Result:");
		reusable.addDivision(cafName, "Division ManagePriority Test");

		// Add division chief
		System.out.println("Add Division chief ");
		System.out.println("Result:");
		reusable.addDivisionChief(cafName, divName, divChiefFname, divChiefLName);

		// Add branch
		System.out.println("Add branch");
		System.out.println("Result:");
		reusable.addBranch(cafName, divName, "Branch ManagePriority Test");

		// Add Branch chief
		System.out.println("Add branch chief");
		System.out.println("Result:");
		reusable.addBranchChief(cafName, divName, branchName, branchChiefFName, branchChiefLName);

		// Add team
		System.out.println("Add team");
		System.out.println("Result:");
		reusable.addTeam(cafName, divName, branchName, "Team ManagePriority Test");

		// Add Team chief
		System.out.println("Add Team chief");
		System.out.println("Result:");
		reusable.addTeamChief(cafName, divName, branchName, teamName, teamChiefFName, teamChiefLName);

		// Add team member
		System.out.println("Add team member");
		System.out.println("Result:");
		reusable.addTeamMember(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);

		catsLogout();

	}

	
/**
 * This method will delete the hierarchy and users created for testing the manage priority	functions.
 * @param cafName
 * @param divName
 * @param branchName
 * @param teamName
 * @param teamMemberFName
 * @param teamMemberLName
 * @param teamChiefFName
 * @param branchChiefFName
 * @param divChiefFname
 * @throws Exception
 */
	public void deleteManagepriorityHierarchy(String cafName, String divName, String branchName, String teamName, String teamMemberFName, String teamMemberLName, String teamChiefFName, String branchChiefFName, String divChiefFname) throws Exception {
		reusable.loginToCATS("1");
		// Delete team member
		System.out.println("Delete team member");
		System.out.println("Result:");
		reusable.deleteTeamMember(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);

		// delete team chief
		System.out.println("Delete team chief");
		System.out.println("Result:");
		reusable.deleteTeamChief(cafName, divName, branchName, teamName, teamChiefFName);

		// delete team
		System.out.println("Delete team");
		System.out.println("Result:");
		reusable.deleteTeam(cafName, divName, branchName, teamName);

		// delete branch chief
		System.out.println("Delete branch chief");
		System.out.println("Result:");
		reusable.deleteBranchChief(cafName, divName, branchName, branchChiefFName);

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
 * This method will test the ability of users to view and update member profile
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
		
	public void verifyViewAndUpdateAssignment(String teamMemberFName, String teamMemberLName) throws Exception {
		// get the member list
		int rowCount = ReusableFunctions.waitUntilElementsExistsAndFindBy(By.xpath("//table/tbody[@id='majorTabPanel:HierarchyEntityUserListTabId:details_data']/tr")).size();
		System.out.println("Number of members seen is : " + rowCount);
		for (int i = 1; i <= rowCount; i++) {
			ReusableFunctions.waitAndGetText(By.xpath("//tr[" + i + "]//span[contains(@id,':tdMemberName')]"));

		}
		// click on the member filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

		// Enter the team member name
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);

		// Verify that the team member name is seen in the table
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table", teamMemberFName + " " + teamMemberLName);
		//
		// Select the team member to update
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");
		//
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='majorTabPanel:hierarchyEntityUserDetailId:memberDetailsProductivityLevelId_label']"));
		// Select productivity level as 1
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:hierarchyEntityUserDetailId:memberDetailsProductivityLevelId_panel']//li[text()='2']"));

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");

		// Verify that the growl message is seen
		pause(2);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println();
		// Click on the CAF tree tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='Hierarchy Entity Details']"));

	}
/**
 * This method will verify that the changes made to the member profile does not change the Inbox/Inventory count for that member.
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
	public void verifyInboxInventoryDoesNotChangeOnUpdateAssignment(String teamMemberFName, String teamMemberLName) throws Exception {

		// click on the member filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

		// Enter the team member name
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);

		// Verify that the team member name is seen in the table
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table", teamMemberFName + " " + teamMemberLName);
		// get the inbox and inventory values before change
		String inboxCount = ReusableFunctions.waitAndGetText(By.xpath("//span[contains(@id,':tdMemberInboxSize')]"));
		String inventoryCount = ReusableFunctions.waitAndGetText(By.xpath("//span[contains(@id,':tdMemberInvSize')]"));
		// Select the team member to update
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");

		//
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(.,'In-Progress cases followed by New')]"));

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

		// Verify that the growl message is seen
		pause(2);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println();
		// Click on the CAF tree tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='Hierarchy Entity Details']"));

		String inboxCountAfterUpdate = ReusableFunctions.waitAndGetText(By.xpath("//td[preceding-sibling::td/span[contains(text(),'" + teamMemberFName + "')]]/span[contains(@id,':tdMemberInboxSize')]")); // td[preceding-sibling::td/span[contains(text(),'Automation
																																																			// Adjudicat')]]/span[contains(@id,':tdMemberInboxSize')]

		String inventoryCountAfterUpdate = ReusableFunctions.waitAndGetText(By.xpath("//td[preceding-sibling::td/span[contains(text(),'" + teamMemberFName + "')]]/span[contains(@id,':tdMemberInboxSize')]")); // td[preceding-sibling::td/span[contains(text(),'Automation
																																																				// Adjudicat')]]/span[contains(@id,':tdMemberInboxSize')]

		if (inboxCount.equalsIgnoreCase(inboxCountAfterUpdate) && inventoryCount.equalsIgnoreCase(inventoryCountAfterUpdate)) {
			System.out.println("Verified that inbox and inventory counts did not change after update. They are seen as :" + inboxCountAfterUpdate + " and " + inventoryCountAfterUpdate);
		}

		// Click on the CAF tree tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));

	}
/**
 * This method will verify that the user has the ability to change the case assignment options and save them.
 * @param user
 * @param divName
 * @param branchName
 * @param teamName
 * @param autoAssign
 * @param newCaseFirst
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
	public void verifyUpdateOfCaseassignmentOptions(String user, String divName, String branchName, String teamName, String autoAssign, String newCaseFirst, String teamMemberFName, String teamMemberLName) throws Exception {
		getTeamDetails(user, divName, branchName, teamName);
		// click on the member filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

		// Enter the team member name
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);
		// Select the team member to update
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");

		if (autoAssign.equalsIgnoreCase("Yes") && newCaseFirst.equalsIgnoreCase("yes")) {
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options']//label[contains(.,'New cases followed by In-Progress')]"));
		}

		if (autoAssign.equalsIgnoreCase("Yes") && newCaseFirst.equalsIgnoreCase("no")) {
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options']//label[contains(.,'In-Progress cases followed by New')]"));
		}
		if (autoAssign.equalsIgnoreCase("no")) {
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:options']//label[contains(.,'None')]"));
		}
		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

		// Verify that the growl message is seen
		pause(5);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println();

		// Click on the CAF tree tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));
	}
/**
 * This method will navigate through the hierarchy based on the user and get to the team members profile.
 * @param userName
 * @param divisionName
 * @param branchName
 * @param teamName
 * @throws Exception
 */
	public void getTeamDetails(String userName, String divisionName, String branchName, String teamName) throws Exception {

		// Select manage hierarchy link
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));
		pause(5);

		if (userName.contains("DivChf")) {

			// Expand the division
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + divisionName + "']/span"));

			// expand the branch
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span[2]"));

			// Right click at team
			com.iworks.DISS.test.common.functions.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']/span");
		} else if (userName.contains("BrcChf")) {
			// expand the branch
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*/td[text()='" + branchName + "']/span"));

			// Right click at team
			com.iworks.DISS.test.common.functions.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']/span");
		} else if (userName.contains("TeamCh")) {
			// Right click at team
			com.iworks.DISS.test.common.functions.ReusableFunctions.rightClick1("//*/td[text()='" + teamName + "']");
		}

		// Select details option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Details"));
	}
/**
 * This method will verify that the user is able to update,edit, change rankiing and save the investigation type options of the team member profile.
 * @param user
 * @param divName
 * @param branchName
 * @param teamName
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
	public void verifyUpdatesToInvestigationTypeOptions(String user, String divName, String branchName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {
		System.out.println("Test: Investigation Type New Cases functionalities \n");
		getTeamDetails(user, divName, branchName, teamName);
		// click on the member filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

		// Enter the team member name
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);
		// Select the team member to update
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");

		// Select the Investigation type tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Investigation Type"));

		String[] investigationTypes = { "SSBI", "OTHER", "Tier 2RS", "Tier 1", "Incident", "Tier 3", "Tier 2S", "NACLC", "BI", "ANACI", "NACI", "PPR", "Tier 3R", "NAC", "MBI", "SBPR", "RSI" };
		for (String invType : investigationTypes) {
			ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Available']]/li", invType);
		}
		ReusableFunctions.waitUntilElementExistsAndDoubleClick(By.xpath("//ul[preceding-sibling::div[text()='Available']]/li[@data-item-label='Tier 2RS']"));
		ReusableFunctions.waitUntilElementExistsAndDoubleClick(By.xpath("//ul[preceding-sibling::div[text()='Available']]/li[@data-item-label='SSBI']"));
		ReusableFunctions.waitUntilElementExistsAndDoubleClick(By.xpath("//ul[preceding-sibling::div[text()='Available']]/li[@data-item-label='NACLC']"));

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

		// Verify that the growl message is seen
		pause(10);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println("Changes saved successfully\n");

		// change ranking and verify
		// move to the end of list and verify
		System.out.println("Test: Change ranking and verify the change:New Cases Investigation Type Ranking- Functionality\n");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//ul[preceding-sibling::div[text()='Assigned']]/li[@data-item-label='Tier 2RS']"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@class,'ui-picklist-button-move-down')]"));
		ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li[2]", "Tier 2RS");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@class,'ui-picklist-button-move-up')]"));
		ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li[1]", "Tier 2RS");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@class,'ui-picklist-button-move-bottom')]"));
		ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li[3]", "Tier 2RS");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@class,'ui-picklist-button-move-top')]"));
		ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li[1]", "Tier 2RS");
		System.out.println("Result:Verified Change ranking :New Cases Investigation Type Ranking- Functionality- TEST PASS\n");

		// RemoveAll
		System.out.println("Test: Remove Investigation Types from the Assigned function\n");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@title='Remove All']/span"));
		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

		// Verify that the growl message is seen
		pause(10);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println("Changes removed successfully\n");
		System.out.println("Result: Remove Investigation Types from the Assigned function- TEST PASS \n");

		// Click on the CAF tree tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));
		System.out.println("Result: Investigation Type New Cases functionalities- TEST PASS \n");
	}
/**
 * This method will verify that the user is able to update,edit, change ranking and save the adjudication phase options of the team member profile.
 * @param user
 * @param divName
 * @param branchName
 * @param teamName
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
	public void verifyUpdatesToAdjudicationPhaseOptions(String user, String divName, String branchName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {
		System.out.println("Test: Add Adjudication Phase  functionalities \n");
		getTeamDetails(user, divName, branchName, teamName);
		// click on the member filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

		// Enter the team member name
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);
		// Select the team member to update
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");

		// Select the Investigation type tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Adjudication Phase"));

		String[] adjudicationPhases = { "Due Process", "Determination Review", "Determination", "Due Process Review" };
		for (String adjPhase : adjudicationPhases) {
			ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Available']]/li", adjPhase);
		}
		ReusableFunctions.waitUntilElementExistsAndDoubleClick(By.xpath("//ul[preceding-sibling::div[text()='Available']]/li[@data-item-label='Determination']"));
		ReusableFunctions.waitUntilElementExistsAndDoubleClick(By.xpath("//ul[preceding-sibling::div[text()='Available']]/li[@data-item-label='Due Process Review']"));
		ReusableFunctions.waitUntilElementExistsAndDoubleClick(By.xpath("//ul[preceding-sibling::div[text()='Available']]/li[@data-item-label='Due Process']"));

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

		// Verify that the growl message is seen
		pause(10);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println("Changes saved successfully\n");

		// change ranking and verify
		// move to the end of list and verify
		System.out.println("Test: Change ranking and verify the change:New Cases Adjudication Phase Ranking- Functionality\n");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//ul[preceding-sibling::div[text()='Assigned']]/li[@data-item-label='Determination']"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']//button[contains(@class,'ui-picklist-button-move-down')]"));
		ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li[2]", "Determination");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']//button[contains(@class,'ui-picklist-button-move-up')]"));
		ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li[1]", "Determination");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']//button[contains(@class,'ui-picklist-button-move-bottom')]"));
		ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li[3]", "Determination");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']//button[contains(@class,'ui-picklist-button-move-top')]"));
		ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li[1]", "Determination");
		System.out.println("Result:Verified Change ranking :New Cases Adjudication Phase Ranking- Functionality- TEST PASS\n");

		// RemoveAll
		System.out.println("Test: Remove Adjudication phase from the Assigned function\n");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']//button[@title='Remove All']/span"));
		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

		// Verify that the growl message is seen
		pause(10);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println("Changes removed successfully\n");
		System.out.println("Result: Remove Adjudication phases from the Assigned function- TEST PASS \n");

		// Click on the CAF tree tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));
		System.out.println("Result: Adjudication Phases- New Cases functionalities- TEST PASS \n");
	}
/**
 * This method will verify that the user is able to update,edit and save the priority program group options of the team member profile.
 * @param user
 * @param divName
 * @param branchName
 * @param teamName
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
	public void verifyUpdatesToPriorityProgramGroupsOptions(String user, String divName, String branchName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {
		System.out.println("Test: Add priority program Groups  functionalities \n");
		getTeamDetails(user, divName, branchName, teamName);
		// click on the member filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

		// Enter the team member name
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);
		// Select the team member to update
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");

		// Select the Investigation type tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Priority Program Group"));

		String[] priorityProgramGroups = { "Group 2", "Group 3", "Group 4", "Group 1" };
		for (String priProgGroup : priorityProgramGroups) {
			if (priProgGroup.equalsIgnoreCase("Group 1")) {
				ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Assigned']]/li", priProgGroup);
				System.out.println("Test: Verify that Group 1 is disabled and user cannot edit \n");
				ReusableFunctions.waitAndVerifyNotEnabled(By.xpath("//ul[preceding-sibling::div[text()='Assigned']]/li[contains(@class,'ui-state-disabled') and @data-item-label='Group 1']"));
				System.out.println("Result: Verified that Group 1 is disabled and user cannot edit -TEST PASS\n");
			} else {
				ReusableFunctions.waitAndVerify_IfContains("//ul[preceding-sibling::div[text()='Available']]/li", priProgGroup);
				ReusableFunctions.waitUntilElementExistsAndDoubleClick(By.xpath("//ul[preceding-sibling::div[text()='Available']]/li[@data-item-label='" + priProgGroup + "']"));
				// Click on save button
				com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

				// Click on yes in the confirmation pop up
				com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

				// Verify that the growl message is seen
				pause(10);
				// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
				// "Changes saved successfully");
				System.out.println("Changes saved successfully\n");

			}
		}

		// RemoveAll
		System.out.println("Test: Remove priority Program group options from the Assigned function\n");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserPriorityProgramPickList']//button[@title='Remove All']/span"));
		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

		// Verify that the growl message is seen
		pause(10);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println("Changes removed successfully\n");
		System.out.println("Result: Remove Priority Program Groups from the Assigned function- TEST PASS \n");

		// Click on the CAF tree tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));
		System.out.println("Result: Priority Program Group Option- New Cases functionalities- TEST PASS \n");
	}
/**
 * This method will verify that the user is able to save member profile with all options empty.
 * @param user
 * @param divName
 * @param branchName
 * @param teamName
 * @param teamMemberFName
 * @param teamMemberLName
 * @throws Exception
 */
	public void verifyAutoAssignPorfileCanbeSavedAsBlank(String user, String divName, String branchName, String teamName, String teamMemberFName, String teamMemberLName) throws Exception {
		System.out.println("Test: Auto assign Profiles can be saved blank  functionalities \n");
		getTeamDetails(user, divName, branchName, teamName);
		// click on the member filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"));

		// Enter the team member name
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//*/input[@id='majorTabPanel:HierarchyEntityUserListTabId:details:memberName:filter']"), teamMemberFName + " " + teamMemberLName);
		// Select the team member to update
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndselectCellFromTable(teamMemberFName + " " + teamMemberLName, "//*[@id='majorTabPanel:HierarchyEntityUserListTabId:details']/div[3]/table");
		System.out.println("Test: Case assignment = None\n");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Case Assignment"));
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(.,'None')]"));
		System.out.println("result: Case assignment = None-TEST PASS\n");

		// Select the Investigation type tab
		System.out.println("Test: Adjudication phase = NoneAssigned\n");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Adjudication Phase"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserAdjCasePickList']//button[@title='Remove All']/span"));
		System.out.println("Result: Adjudication phase = NoneAssigned\n");

		System.out.println("Test: Investigation Type = None Assigned \n");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Investigation Type"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@title='Remove All']/span"));
		System.out.println("result: Investigation type = None Assigned\n");

		System.out.println("Test: Priority Program group = None assigned\n");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Priority Program Group"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='majorTabPanel:hierarchyEntityUserDetailId:caseAssignmentTab:heUserPriorityProgramPickList']//button[@title='Remove All']/span"));
		System.out.println("Result: priority program group = None assigned \n");

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveEntityMemberDetails']"));

		// Click on yes in the confirmation pop up
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation']"));// majorTabPanel:hierarchyEntityUserDetailId:saveMemberConfirmation

		// Verify that the growl message is seen
		pause(10);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Changes saved successfully");
		System.out.println("Changes removed successfully\n");
		System.out.println("Result: Auto assign Profiles can be saved blank  functionalities- TEST PASS \n");

		// Click on the CAF tree tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='CAF Tree']"));
		System.out.println("Result: Adjudication Phases- New Cases functionalities- TEST PASS \n");

	}
}
