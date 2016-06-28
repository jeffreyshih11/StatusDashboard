package com.iworks.DISS.test.cats.testDataGeneration;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;






import com.iworks.DISS.test.common.functions.CATSReusuableFunctions;
import com.iworks.DISS.test.common.functions.JVSReusableFunctions;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.utils.TestBase;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TestgenCreateUserAndHierarchy extends TestBase {
	private static final String SECURITY_OFFICER_STANDARD_ROLE = "Security Officer Standard";
	private static final String TEAM_NAME = "TEAM-Team DataGen test";
	private static final String PROCESS_TEAM_NAME="TEAM-Process Team";
	private static final String BRANCH_NAME = "BRANCH-Branch DataGen test";
	private static final String DIVISION_NAME = "DIVISION-Division DataGen Test";
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
	static jxl.Sheet Sheet;
	long startTime;
	long endTime;
	public static class UserInfo {
		public String fName;
		public String lName;
		public String uName;
		public String ssn;
		public String role;
		public String leftNavLinks;
	}
	public Class<? extends TestgenCreateUserAndHierarchy> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className, name);

	}

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

		driverClose();
	}

	@Test
	public void testgencreateUserAndHierarchy() throws Exception {
		writeExcel();
		// Call the createtestData function to create users with different roles
		List<UserInfo> list = createTestData();
		//Map role to userInfo
		Map<String, UserInfo> roleToUserInfomap = new HashMap<String, TestgenCreateUserAndHierarchy.UserInfo>();
		//for eachuserInfo in the list map the role to info
		for (UserInfo userInfo : list) {
			roleToUserInfomap.put(userInfo.role, userInfo);
		}
		Workbook existingWorkbook = Workbook.getWorkbook(new File("C:\\Users\\Public\\Documents\\TestGenUsers.xls"));
		WritableWorkbook workbookCopy = Workbook.createWorkbook(new File("C:\\Users\\Public\\Documents\\TestGenUsers.xls"), existingWorkbook);
		WritableSheet sheetToEdit = workbookCopy.getSheet(0);
		int i=sheetToEdit.getRows();
		System.out.println("Number of rows is : "+i);
				
		for (String role : roleToUserInfomap.keySet()) {
			
			
			
		
		    Label label1 = new Label(0, i, role);
		    Label label2 = new Label(1, i, roleToUserInfomap.get(role).fName);
		    Label label3 = new Label(2, i, roleToUserInfomap.get(role).lName);
		    Label label4 = new Label(3, i, roleToUserInfomap.get(role).ssn);
		    Label label5 = new Label(4, i, roleToUserInfomap.get(role).uName);
		    sheetToEdit.addCell(label1);
		    sheetToEdit.addCell(label2);
		    sheetToEdit.addCell(label3);
		    sheetToEdit.addCell(label4);
		    sheetToEdit.addCell(label5);
		    i++;
			
		}
		
		 workbookCopy.write();
		 workbookCopy.close();
		 existingWorkbook.close();
		 
	
		
		// call the verifyManageHierarchy to set the hierarchy for testgen
		setTestGenHierarchy(CAF_NAME, "Kelly Brown", DIVISION_NAME, roleToUserInfomap.get(DIVISION_CHIEF_ROLE).fName, roleToUserInfomap.get(DIVISION_CHIEF_ROLE).lName, BRANCH_NAME, roleToUserInfomap.get(BRANCH_CHIEF_ROLE).fName, roleToUserInfomap.get(BRANCH_CHIEF_ROLE).lName, TEAM_NAME, roleToUserInfomap.get(TEAM_CHIEF_ROLE).fName, roleToUserInfomap.get(TEAM_CHIEF_ROLE).lName, roleToUserInfomap.get(ADJUDICATOR_ROLE).fName, roleToUserInfomap.get(ADJUDICATOR_ROLE).lName,roleToUserInfomap.get(PROCESS_TEAM_ROLE).fName,roleToUserInfomap.get(PROCESS_TEAM_ROLE).lName);

	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws BiffException
	 */
	public static void writeExcel()throws IOException,BiffException{
		try {
		    String fileName = "C:\\Users\\vshivaraman\\Documents\\TestGenUsers.xls";
		    WritableWorkbook workbook = Workbook.createWorkbook(new File(fileName));
		    WritableSheet writablesheet1 = workbook.createSheet("Sheet1", 0);
		   
		    Label label1 = new Label(0, 0, "Role");
		    Label label2 = new Label(1, 0, "FirstName");
		    Label label3 = new Label(2, 0, "LastName");
		    Label label4 = new Label(3, 0, "SSN");
		    Label label5 = new Label(3, 0, "UserName");
		    writablesheet1.addCell(label1);
		    writablesheet1.addCell(label2);
		    writablesheet1.addCell(label3);
		    writablesheet1.addCell(label4);
		    writablesheet1.addCell(label5);
		    

		    workbook.write();
		    workbook.close();
		} catch (WriteException e) {

		}
	}


public static void modifyexcel(String role,String firstName,String lastName,String ssn,String userName)throws IOException,BiffException, RowsExceededException, WriteException{
	
	Workbook existingWorkbook = Workbook.getWorkbook(new File("C:\\Users\\vshivaraman\\Documents\\TestGenUsers.xls"));
	WritableWorkbook workbookCopy = Workbook.createWorkbook(new File("C:\\Users\\vshivaraman\\Documents\\TestGenUsers1.xls"), existingWorkbook);
	WritableSheet sheetToEdit = workbookCopy.getSheet(0);
	
	for(int row=1;row<7;row++) {
	    Label label1 = new Label(0, row, role);
	    Label label2 = new Label(1, row, firstName);
	    Label label3 = new Label(2, row, lastName);
	    Label label4 = new Label(3, row, ssn);
	    Label label5 = new Label(3, row, userName);
	    sheetToEdit.addCell(label1);
	    sheetToEdit.addCell(label2);
	    sheetToEdit.addCell(label3);
	    sheetToEdit.addCell(label4);
	    sheetToEdit.addCell(label5);

	}
	 workbookCopy.write();
	 workbookCopy.close();
	 existingWorkbook.close();
}
	public List<UserInfo> createTestData() throws Exception {
		// Log in to CATS as user 1
		reusable.loginToCATS("1");

		List<UserInfo> retList = new ArrayList<>();
		
		//Division Chief User
		UserInfo uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Manage Templates,Search SMOs";
		createUsersForCATSDataGenTest("DivChfFName", "DivChfLName",  DIVISION_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
			
		//Branch Chief User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Search SMOs";
		createUsersForCATSDataGenTest("BrcChfFName", "BrcChfLName",  BRANCH_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
		
		//Team Chief User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Inbox,Task Assignment,Manage Hierarchy,Manual Assignment,Case Inbox,Subject Management,Search SMOs";
		createUsersForCATSDataGenTest("TeamChFName", "TeamChLName", TEAM_CHIEF_ROLE, uInfo);
		retList.add(uInfo);
	
		//Adjudicator User
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Task Inbox,Unread Notifications:,Productivity,Case Inbox,Subject Management,SMO";
		createUsersForCATSDataGenTest("AdjuFName", "AdjuLName",  ADJUDICATOR_ROLE, uInfo);
		retList.add(uInfo);
		


		

		// Process team user
		uInfo = new UserInfo();
		uInfo.leftNavLinks="Communications,Unread Notifications:,Task Inbox,Manual Assignment,Case Inbox,Create Subject,Search SMOs";
		createUsersForCATSDataGenTest("ProTeaFName",	"ProTeaLName",  PROCESS_TEAM_ROLE, uInfo);
		retList.add(uInfo);


		
		// Create SO
		uInfo = new UserInfo();
		createUsersForCATSDataGenTest("SOStanfFName", "SOStanLName",  SECURITY_OFFICER_STANDARD_ROLE, uInfo);
		retList.add(uInfo);
		catsLogout();
		jvsreusable.loginToJVS("1");
		// Create user with standard SO member role
		jvsreusable.createUser(uInfo.ssn, uInfo.uName, "DISS", SECURITY_OFFICER_STANDARD_ROLE);
		System.out.println("Security Officer Standard user name is "+ uInfo.uName+"\n");
		jvsreusable.jvsLogout();

		// return list of user information
		return retList;

	}
	public void createUsersForCATSDataGenTest(String firstnamepatern,String lastNamepatern,  String role,
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
	public void setTestGenHierarchy(String cafName, String cafChiefName, String divName, String divChiefFname, String divChiefLName, String branchName, String branchChiefFName, String branchChiefLName, String teamName, String teamChiefFName, String teamChiefLName, String teamMemberFName, String teamMemberLName,String processTeamMemberFName,String porcessTeamMemberLName) throws Exception {

		// Log into CATS as 1
		reusable.loginToCATS("1");
		
		// Click on the manage hierarchy link
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Manage Hierarchy"));


		// Add division
		System.out.println("Add Division by the name of Division DataGen Test");
		System.out.println("Result:");
		reusable.addDivision(cafName, "Division DataGen Test");

		// Add division chief
		System.out.println("Add Division chief ");
		System.out.println("Result:");
		reusable.addDivisionChief(cafName, divName, divChiefFname, divChiefLName);

		// Add branch
		System.out.println("Add branch");
		System.out.println("Result:");
		reusable.addBranch(cafName, divName, "Branch DataGen test");

		// Add Branch chief
		System.out.println("Add branch chief");
		System.out.println("Result:");
		reusable.addBranchChief(cafName, divName, branchName, branchChiefFName, branchChiefLName);

		// Add team
		System.out.println("Add team");
		System.out.println("Result:");
		reusable.addTeam(cafName, divName, branchName, "Team DataGen test");

		// Add Team chief
		System.out.println("Add Team chief");
		System.out.println("Result:");
		reusable.addTeamChief(cafName, divName, branchName, teamName, teamChiefFName, teamChiefLName);

		// Add team member
		System.out.println("Add team member");
		System.out.println("Result:");
		reusable.addTeamMember(cafName, divName, branchName, teamName, teamMemberFName, teamMemberLName);



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
}
