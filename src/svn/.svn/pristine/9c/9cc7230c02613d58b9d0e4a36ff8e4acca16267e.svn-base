package com.iworks.DISS.test.jvsSmokeTests;

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

import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.TestBase;

public class PortalSmokeTestWithReports extends TestBase {
	private static final String SMO = "--AutoSMO-1";
	private static final String CATEGORY_TYPE = JVSProperties.CivilianRetiree.getProperty();
	private static final String HUMAN_RESOURCES_ROLE = "Human Resources";
	private static final String COMPONENT_ADJUDICATOR_ROLE = "Component Adjudicator";
	private static final String SECURITY_OFFICER_ADMIN_ROLE = "Security Officer Admin";
	private static final String SECURITY_OFFICER_STANDARD_ROLE = "Security Officer Standard";
	private static final String HELP_DESK_ROLE = "Help Desk";
	private static final String HIERARCHY_MANAGER_ROLE = "Hierarchy Manager";
	private static final String ACCOUNT_MANAGER_ROLE = "Account Manager";
	private static final String SECURITY_OFFICER_VISIT_ADMIN_ROLE = "Security Officer Visit Admin";
	private static final String PHYSICAL_ACCESS_CONTROL_PERSONNEL_ROLE = "Physical Access Control Personnel";
	private static final String SECURITY_MANAGER_ROLE = "Security Manager";

	// Map User name to User Info
	Map<String, UserInfo> userNameToInfoMap = new HashMap<>();
	//Map user to tab info
	Map<String, TabInfo> userToTabInfoMap = new HashMap<>();
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	String className= null;
	long startTime;
	long endTime;
	

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
		public String reportTypes;
	}
	public static class TabInfo{
		public String role;
		public String subjectTabDetails;
		public String smoDetailsTabDetails;
		public String unreadNotifications;
		public String taskInbox;
		public String searchOrganizations;
	}


	public Class<? extends PortalSmokeTestWithReports> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		className = clazz.getCanonicalName();
		//reusable.createLogFile(className, name);
		DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		DateFormat dateFormat1 = new SimpleDateFormat("yyMMdd");
		String date1 = dateFormat1.format(cal.getTime());
		String environment = CONFIG.getProperty("Environment");
		File targetFile = new File("C://AutomationOutput//" + environment+"//"+ className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc");
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

	@Test
	public void portalSmokeTestWithReports() throws Exception {
//		SSNFNameAndLName sub = new SSNFNameAndLName();
//		// Call the createtestData function to create users with different roles
//		System.out.println("Test:Call the createtestData function to create users with different roles \n");
//		List<UserInfo> list = createTestData(sub);
//		System.out.println("Result: Test data for different roles have been created \n");
//
//		// Call the verify log in function to verify log in as different users and verify their LHS panel links ,Main page tabs and each tab detail
//		verifyLogin(list);
//		
		// Call the verifyCATSBasicFunctions
		 verifyJVSBasicFunctions("111770089");//sub.ssn
		// "India", adjudicatorUserName, procTeamUsername, standardSOUserName);
		 jvsreusable.jvsLogout();

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
		tInfo.smoDetailsTabDetails="General Information,SMO Actions,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView ="Army,Navy,AF,DoD Civilian,Other Federal Agencies,PSMO";
		uInfo.reportTypes="Access Suspension (Owning),Access Suspension (Servicing),Periodic Reinvestigation,SMOs with No Subject Relationships,SMOs with No Active Users,User Roles and Optional Permissions,Case Status,Hosting Visit Report,Outgoing Visit Report,Subject Report,Submitted Incident";//Case Status,Hosting Visit Report,Outgoing Visit Report,Subject Report,Submitted Incident,
		uInfo.uName= "1";
		uInfo.role="1";
		userNameToInfoMap.put(uInfo.uName, uInfo);
		userToTabInfoMap.put(uInfo.uName, tInfo);
		
		
		//Security Officer Standard
		// call ssn gen to create Security Officer standard user
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "Subjects,SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="Access Suspension (Owning),Access Suspension (Servicing),Periodic Reinvestigation,SMOs with No Subject Relationships,Case Status,Hosting Visit Report,Outgoing Visit Report,Subject Report,Submitted Incident";//Case Status,Hosting Visit Report,Outgoing Visit Report,Subject Report,Submitted Incident,
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "SecOffStnFName", "SecOffStnLName",  SECURITY_OFFICER_STANDARD_ROLE, tInfo, uInfo);
		retList.add(uInfo);
		
		//Security Officer Admin
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "Subjects,SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="Access Suspension (Owning),Access Suspension (Servicing),Periodic Reinvestigation,SMOs with No Subject Relationships,Case Status,Hosting Visit Report,Outgoing Visit Report,Subject Report,Submitted Incident";//Case Status,Hosting Visit Report,Outgoing Visit Report,Subject Report,Submitted Incident,
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "SecOffAdmFName", "SecOffadmLName", SECURITY_OFFICER_ADMIN_ROLE, tInfo, uInfo);
		retList.add(uInfo);
				
		//Component Adjudicator
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="";
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "CompAdjFName", "CompAdjLName",  COMPONENT_ADJUDICATOR_ROLE, tInfo, uInfo);
		retList.add(uInfo);


		
		//Human Resource
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="";
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "HumResFName", "HumResLName", HUMAN_RESOURCES_ROLE, tInfo, uInfo);
		retList.add(uInfo);

		//Security manager
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="";
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "SecManFName", "SecManLName",  SECURITY_MANAGER_ROLE, tInfo, uInfo);
		retList.add(uInfo);

		
		//Physical access control personnel
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "Subjects,SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="Hosting Visit Report";
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "PhyACPFName", "PhyACPLName", PHYSICAL_ACCESS_CONTROL_PERSONNEL_ROLE, tInfo, uInfo);
		retList.add(uInfo);


		//Security officer visit admin
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "Subjects,SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="Hosting Visit Report,Outgoing Visit Report";
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "SOVAdmFName", "SOVAdmLName",  SECURITY_OFFICER_VISIT_ADMIN_ROLE, tInfo, uInfo);
		retList.add(uInfo);


		//Account manager
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "Subjects,SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="SMOs with No Active Users,User Roles and Optional Permissions";
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "ActManFName", "ActManLName",  ACCOUNT_MANAGER_ROLE, tInfo, uInfo);
		retList.add(uInfo);

		//Hierarchy manager
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Users,View Current SMO,View SMO Tree,Search SMOs,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "Subjects,SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail,Create SMO Here,Deactivate SMO";
		uInfo.reportTypes="SMOs with No Active Users,User Roles and Optional Permissions";
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
		createUsersForSmokeTest( "HryManFName", "HryManLName",  HIERARCHY_MANAGER_ROLE, tInfo, uInfo);
		retList.add(uInfo);


		//help Desk
		
		uInfo = new UserInfo();
		tInfo = new TabInfo();
		uInfo.leftNavLinks = "Task Inbox,Unread Notifications,View Current SMO,View SMO Tree,Search SMOs,View SMO Visits,View Current Organization,Search Organizations,View Subjects";
		uInfo.mainPageTabs = "Subjects,SMO Details";
		uInfo.smoTreeActions ="SMO Actions,Show SMO Detail";
		uInfo.reportTypes="Access Suspension (Owning),Access Suspension (Servicing),Periodic Reinvestigation,SMOs with No Subject Relationships,SMOs with No Active Users,User Roles and Optional Permissions,Case Status,Hosting Visit Report,Outgoing Visit Report,Subject Report,Submitted Incident";
		tInfo.subjectTabDetails="Last Name,First Name,SSN";
		tInfo.smoDetailsTabDetails="General Information,SMO Location,SON Mapping,SMO Point of Contact";
		tInfo.unreadNotifications ="Notification Inbox,Period,From,To,Apply,Received Notifications,Recipient,Type,Details,Received,Options";
		tInfo.taskInbox= "Task Inbox,Task Name,Description,Task Owner,Task For";
		tInfo.searchOrganizations="Organization Search,Find Organization,Find Organization By Name,Organization Name,Find Organization By Attributes,CAGE Code,Find Organization By Location,State,Country,Search,Clear";
		uInfo.smoTreeView =SMO;
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
			uInfo.uName = jvsreusable.createUser(uInfo.ssn, SMO, role);
			System.out.println("User Name name is " + uInfo.uName +"\n");
			userNameToInfoMap.put(uInfo.uName, uInfo);
			userToTabInfoMap.put(uInfo.uName, tInfo);
			//log out of portal
			jvsreusable.jvsLogout();
			
		}

	public void verifyLogin(List<UserInfo> list) throws Exception {

		for (String userName : userNameToInfoMap.keySet()) {
			System.out.println("Test: Verify Log in to JVS as " + userName+"\n");
			jvsreusable.loginToJVS(userName);
			System.out.println("Result: Log in to JVS as " + userName + " was successful \n");
			
			System.out.println("Test: Verify JVS Reports for the user "+userName+"\n");
			verifyJVSReports(userName, userNameToInfoMap.get(userName).reportTypes,SMO,userNameToInfoMap.get(userName).role);
			System.out.println("Result: JVS reports for " + userName + " was successful \n");
			
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
			pause(5);
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndRightClick(By.xpath("//*[@id='majorTabPanel:smoTree']/ul//li//span[text()='"+ smoTreeStr +"']"));
			System.out.println("SMO selected is " + smoTreeStr+"\n");
			pause(5);

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
	public void verifyJVSReports(String user, String reportType,String smo, String role) throws Exception {
		//jvsreusable.loginToJVS(user);
		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("parent handel is : "+parentWindowHandler);
		System.out.println("parentTitle is " + driver.getTitle());
		//click on Reporting link
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Reporting"));
		//click yes to open in new tab
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='appMenu:transferDissPortal']"));
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
             
		if (role.equalsIgnoreCase(COMPONENT_ADJUDICATOR_ROLE) || role.equalsIgnoreCase(HUMAN_RESOURCES_ROLE) || role.equalsIgnoreCase(SECURITY_MANAGER_ROLE)) {
			System.out.println("Result:" + role + " is able to log int o reports site but no reports are seen.");
			// log out of reports
			reportsLogout();
			// close tab
			driver.close();
			// switch to parent
			driver.switchTo().window(parentWindowHandler);
		}else {
             String[] reportTypeArr = reportType.split(",");    
             for (String reporttype : reportTypeArr) {
            	 ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,'cpanelForm:reportPanel4_content')]//a[contains(text(),'"+reporttype+"')]"));
            	 pause(5);
            	 ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[contains(@id,'JvsControlPanelId')]//td[preceding-sibling::td/label[contains(text(),'SMO')]]//li[contains(text(),'"+smo+"')]"));
	             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='View Report']"));
	             pause(3);
	             ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//object[@type='application/pdf']"));
	             System.out.println("Pdf is rendered correctly for "+reporttype);
	             captureScreen(className, name,role,reporttype);
	             pause(5);
             }
         //log out of reports
             reportsLogout();
		//close tab
		driver.close();
		//switch to parent
		driver.switchTo().window(parentWindowHandler);
	}
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
