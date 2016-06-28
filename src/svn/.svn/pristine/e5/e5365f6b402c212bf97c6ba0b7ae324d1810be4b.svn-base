package com.iworks.DISS.test.cats.testDataGeneration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.Sheet;
import jxl.read.biff.BiffException;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
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

public class CATSTestDataGenClosedCases extends TestBase {
	private static final String CATEGORY_TYPE = JVSProperties.CivilianRetiree.getProperty();
	private static final String SECURITY_OFFICER_STANDARD_ROLE = "Security Officer Standard";
	private static final String PROCESS_TEAM_ROLE = "Process Team";
	private static final String ADJUDICATOR_ROLE = "Adjudicator";
	static jxl.Sheet Sheet;

	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
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
		public String leftNavLinks;
	}

	public Class<? extends CATSTestDataGenClosedCases> clazz = this.getClass();
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
		File targetFile = new File("S://AutomationOutput//" + environment+"//"+"TestedByBuild"+"//"+ className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc");
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
	public void catsDataGenTest() throws Exception {
//		TestgenCreateUserAndHierarchy test = new TestgenCreateUserAndHierarchy();
//		test.testgencreateUserAndHierarchy();
		 readExcel();
		 int totalRows = Sheet.getRows();
		 String adjuUserName = null;
		 String soUserName= null;
		 String proteamuserName= null;
		 for(int j=1;j<totalRows;j++) {
			 String role =Sheet.getCell(0, j).getContents().toString();
			 if(role.equalsIgnoreCase(ADJUDICATOR_ROLE)) {
				 adjuUserName=Sheet.getCell(4,j).getContents().toString();
			 }else if (role.equalsIgnoreCase(SECURITY_OFFICER_STANDARD_ROLE)) {
				 soUserName=Sheet.getCell(4,j).getContents().toString();
			}else if (role.equalsIgnoreCase(PROCESS_TEAM_ROLE)) {
				proteamuserName=Sheet.getCell(4,j).getContents().toString();
			}
			
		 }
			
		for (int i = 0; i < 51; i++) {
			
		
			
		//Generate a subject and case and process it as normal flow
		createTestGenData(adjuUserName);
		
		//Generate a subject and case and process it through due process and adjudicate as revoked
		createTestGenData(adjuUserName, soUserName);
		
		// Generates a subject and case and processes it through appeals process
		createTestGenData(adjuUserName, soUserName,proteamuserName );

		}

	}

	public static void readExcel() throws IOException, BiffException {
		FileInputStream file = new FileInputStream("C:\\Users\\vshivaraman\\Documents\\TestGenUsers1.xls");
		Workbook wBook = Workbook.getWorkbook(file);
		// get sheet
		Sheet = wBook.getSheet(0);

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
 * @param subssn
 * @param subfirstName
 * @param subjectLName
 * @param subjectBirthCountry
 * @param adjudicatorUserName
 * @param procTeamUserName
 * @param standardSOUserName
 * @throws Exception
 */
	public void createTestGenData(String adjudicatorUserName) throws Exception {

		// Verify Cats log in function
		System.out.println("Test: Verify CATs log in function");
		reusable.loginToCATS("1");
		System.out.println("Result:Logged in to CATS as user 1 correctly");
		//DataGen Test Subject 
		// Call ssn generator to create snn, first and last names for DataGen test
		// subject
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("DataGenFName", "DataGenLName");
		 String subssn = list.get(0).ssn;
		String subfirstName = list.get(0).firstName;
		String sublastName = list.get(0).lastName;
		//Create the subject
		// Verify that create subject function is working successfully
		System.out.println("Test:Verify that create subject function is working correctly \n");
		reusable.createSubject(subssn, subfirstName, sublastName,"1983","Oct","20","United States");
		System.out.println("Result:Create subject function is working successfully \n");
	
		//Verify Add citizenship function
		System.out.println("test: Verify add citizenship funnctionality");
		reusable.addCitizenshipForSubject(subssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		System.out.println("Result: Subjects citizenship was added successfully as Natural born US Citizen");
	
		
		// Verify CATs log out function
		System.out.println("Test: Verify CATs log out function");
		catsLogout();
		System.out.println("Result:Logged out of CATS as user 1 correctly");

		// Verify Log in to JVS as 1
		System.out.println("Test: Verify Log in to JVS Portal application as user 1");
		jvsreusable.loginToJVS("1");
		System.out.println("Result:Log in to JVS Portal application as user 1 was successful");

		// Verify Create SMO relationship function between the case and the SO
		System.out.println("Test: Verify Create SMO relationship function between the case and the SO");
		jvsreusable.createSMORelationship(CATEGORY_TYPE, subssn);
		System.out.println("Result: Create SMO relationship function between the case and the SO was successful");

		// Verify Log out of JVS function
		System.out.println("Test: Verify Log out of JVS function");
		jvsreusable.jvsLogout();
		System.out.println("Result: Log out of JVS function was successful");

		// Log in to CATS UAT as user 1
		reusable.loginToCATS("1");

		// Verify create case function for the subject is working
		System.out.println("Test: Verify create case function for the subject is working");
		reusable.createCaseForSubject(subssn, CATSProperties.DivisionTestGen1.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());
		System.out.println("Result: Create case function for the subject is working");

		// Get the case ID of the cases created
		String caseID = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("First case ID is : " + caseID);

		// Log out of CATs as user 1
		catsLogout();

		// log in to CATs as adjudicator
		reusable.loginToCATS(adjudicatorUserName);

	reusable.searchCase(caseID);

		// Verify that the case can be successfully passed through normal
		// adjudication flow and closed with determination = favorable
		System.out.println("Test:Verify that the case can be successfully passed through normal adjudication flow and closed with determination = favorable");
		reusable.determinationFavorable(caseID, CATSProperties.LevelCodeConfidential.getProperty());
		System.out.println("Result: Case: " + caseID + "has been closed successfully with determination = Favorable in normal flow");

		// Log out adjudicator
		catsLogout();
	}
	
	public void createTestGenData(String adjudicatorUserName,String standardSOUserName)throws Exception{
		// Verify Cats log in function
		System.out.println("Test: Verify CATs log in function");
		reusable.loginToCATS("1");
		//DataGen Test Subject 
		// Call ssn generator to create snn, first and last names for DataGen test
		// subject
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("DataGenFName", "DataGenLName");
		 String subssn = list.get(0).ssn;
		String subfirstName = list.get(0).firstName;
		String sublastName = list.get(0).lastName;
		//Create the subject
		// Verify that create subject function is working successfully
		System.out.println("Test:Verify that create subject function is working correctly \n");
		reusable.createSubject(subssn, subfirstName, sublastName,"1983","Oct","20","United States");
		System.out.println("Result:Create subject function is working successfully \n");
	
		//Verify Add citizenship function
		System.out.println("test: Verify add citizenship funnctionality");
		reusable.addCitizenshipForSubject(subssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		System.out.println("Result: Subjects citizenship was added successfully as Natural born US Citizen");
	
		
		// Verify CATs log out function
		System.out.println("Test: Verify CATs log out function");
		catsLogout();
		System.out.println("Result:Logged out of CATS as user 1 correctly");

		// Verify Log in to JVS as 1
		System.out.println("Test: Verify Log in to JVS Portal application as user 1");
		jvsreusable.loginToJVS("1");
		System.out.println("Result:Log in to JVS Portal application as user 1 was successful");

		// Verify Create SMO relationship function between the case and the SO
		System.out.println("Test: Verify Create SMO relationship function between the case and the SO");
		jvsreusable.createSMORelationship(CATEGORY_TYPE, subssn);
		System.out.println("Result: Create SMO relationship function between the case and the SO was successful");

		// Verify Log out of JVS function
		System.out.println("Test: Verify Log out of JVS function");
		jvsreusable.jvsLogout();
		System.out.println("Result: Log out of JVS function was successful");
		// Log in to CATS UAT as user 1
		reusable.loginToCATS("1");

		// Verify create case function for the subject is working
		System.out.println("Test: Verify create case function for the subject is working");
		reusable.createCaseForSubject(subssn, CATSProperties.DivisionTestGen1.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());
		System.out.println("Result: Create case function for the subject is working");

		// Get the case ID of the cases created
		String caseID = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("First case ID is : " + caseID);

		// Log out of CATs as user 1
		catsLogout();

		// log in to CATs as adjudicator
		reusable.loginToCATS(adjudicatorUserName);
		reusable.searchCase(caseID);
	

		// Verify that the second case goes through Due process and adjudicated
		// in normal flow and closed with determination = Revoked
		System.out.println("Test:Verify that the second case goes through Due process and adjudicated in normal flow and closed with determination = Revoked");
		System.out.println("Sub test:Verify that case phase is Due process");
		reusable.determinationsNotFavorable(caseID, CATSProperties.DeterminationNone.getProperty(), CATSProperties.AllegiancePanel.getProperty(), CATSProperties.DisqualifyingCondition.getProperty(), CATSProperties.CivilianMPCCode.getProperty());
		System.out.println("Result:Case is in Due process phase");

		// Initiate CATS SOR Flow
		System.out.println("Sub test: Verify CATS SOR flow is initiated by adjudicator and SOR document is uploaded");
		reusable.sorFlow(subssn, CATSProperties.SOROption.getProperty());
		System.out.println("Result: CATS SOR flow has been initiated by adjudicator and SOR document is uploaded");

		// Logout as adjudicator
		catsLogout();

		// Log in to Portal as SO
		jvsreusable.loginToJVS(standardSOUserName);

		// Call JVSSORFlow method
		System.out.println("Sub Test:Verify JVS SOR flow has been claimed, acknowledgement receipt and subject response documents were uploaded and task closed by security officer in Portal application");
		jvsreusable.jvsSORflow(subssn, subfirstName, sublastName);
		System.out.println("Result:JVS SOR flow has been claimed, acknowledgement receipt and subject response documents were uploaded and task closed by security officer in Portal application");

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS
		reusable.loginToCATS(adjudicatorUserName);

		// initiate LOD/LOR flow
		System.out.println("Sub Test:Verify LOD/LOR flow has been initiated as adjudicator, LOR document is uploaded and sent to SO");
		reusable.lodLORFlow(subssn, CATSProperties.LODLOROption.getProperty());
		System.out.println("Result:Verify LOD/LOR flow has been initiated as adjudicator, LOR document is uploaded and sent to SO");

		// Log out Adjudicator from CATS
		catsLogout();

		// Log in to JVS
		jvsreusable.loginToJVS(standardSOUserName);

		// Call the JVSLOD LOR method to complete the LOD/LOR flow
		System.out.println("Sub Test:Verify JVS LOD/LOR flow has been claimed, acknowledgement receipt and subject response documents were uploaded and task closed by security officer in Portal application");
		jvsreusable.jvsLODLORflow(subssn, subfirstName, sublastName, "No");
		System.out.println("Result:JVS LOD/LOR flow has been claimed, acknowledgement receipt and subject response documents were uploaded and task closed by security officer in Portal application");

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS
		reusable.loginToCATS(adjudicatorUserName);

		// close the case with determination = revoked
		caseDenied(caseID);
		System.out.println("case :" + caseID + " is closed successfuly processed through due process and closed with determiation = Revoked");
		System.out.println("Result:The second case goes through Due process and adjudicated in normal flow and closed with determination = Revoked");

		// log out adjudicator
		catsLogout();
	}
	
	
	public void createTestGenData(String adjudicatorUserName,String standardSOUserName, String procTeamUserName)throws Exception{
		// Verify Cats log in function
		System.out.println("Test: Verify CATs log in function");
		reusable.loginToCATS("1");
		//DataGen Test Subject 
		// Call ssn generator to create snn, first and last names for DataGen test
		// subject
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("DataGenFName", "DataGenLName");
		 String subssn = list.get(0).ssn;
		String subfirstName = list.get(0).firstName;
		String sublastName = list.get(0).lastName;
		//Create the subject
		// Verify that create subject function is working successfully
		System.out.println("Test:Verify that create subject function is working correctly \n");
		reusable.createSubject(subssn, subfirstName, sublastName,"1983","Oct","20","United States");
		System.out.println("Result:Create subject function is working successfully \n");
	
		//Verify Add citizenship function
		System.out.println("test: Verify add citizenship funnctionality");
		reusable.addCitizenshipForSubject(subssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		System.out.println("Result: Subjects citizenship was added successfully as Natural born US Citizen");
	
		
		// Verify CATs log out function
		System.out.println("Test: Verify CATs log out function");
		catsLogout();
		System.out.println("Result:Logged out of CATS as user 1 correctly");

		// Verify Log in to JVS as 1
		System.out.println("Test: Verify Log in to JVS Portal application as user 1");
		jvsreusable.loginToJVS("1");
		System.out.println("Result:Log in to JVS Portal application as user 1 was successful");

		// Verify Create SMO relationship function between the case and the SO
		System.out.println("Test: Verify Create SMO relationship function between the case and the SO");
		jvsreusable.createSMORelationship(CATEGORY_TYPE, subssn);
		System.out.println("Result: Create SMO relationship function between the case and the SO was successful");

		// Verify Log out of JVS function
		System.out.println("Test: Verify Log out of JVS function");
		jvsreusable.jvsLogout();
		System.out.println("Result: Log out of JVS function was successful");
		// Log in to CATS UAT as user 1
		reusable.loginToCATS("1");

		// Verify create case function for the subject is working
		System.out.println("Test: Verify create case function for the subject is working");
		reusable.createCaseForSubject(subssn, CATSProperties.DivisionTestGen1.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.SelectSMO.getProperty(), CATSProperties.InvestigationSSBI.getProperty());
		System.out.println("Result: Create case function for the subject is working");

		// Get the case ID of the cases created
		String caseID = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("First case ID is : " + caseID);

		// Log out of CATs as user 1
		catsLogout();



		// Log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorUserName);

		reusable.searchCase(caseID);

		// Verify that the Third case goes through Due process and adjudicated
		// in Appeals flow and closed with determination = favorable
		System.out.println("Test:Verify that the case goes through Due process and adjudicated in Appeals flow and closed with determination = favorable");

		System.out.println("Sub test:Verify that case phase is Due process");
		reusable.determinationsNotFavorable(caseID, CATSProperties.DeterminationNone.getProperty(), CATSProperties.AllegiancePanel.getProperty(), CATSProperties.DisqualifyingCondition.getProperty(), CATSProperties.CivilianMPCCode.getProperty());
		System.out.println("Result:Case is in Due process phase");

		// Initiate CATS SOR Flow
		reusable.sorFlow(subssn, CATSProperties.SOROption.getProperty());

		// Logout as adjudicator
		catsLogout();

		// Log in to Portal as SO
		jvsreusable.loginToJVS(standardSOUserName);

		// Call JVSSORFlow method
		jvsreusable.jvsSORflow(subssn, subfirstName, sublastName);

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS
		reusable.loginToCATS(adjudicatorUserName);

		// initiate LOD/LOR flow
		reusable.lodLORFlow(subssn, CATSProperties.LODLOROption.getProperty());

		// Log out Adjudicator from CATS
		catsLogout();

		// Log in to JVS
		jvsreusable.loginToJVS(standardSOUserName);

		// Call the JVSLOD LOR method to complete the LOD/LOR flow
		System.out.println("Test: Verify that the case is sent to appeals process by SO form portal");
		jvsreusable.jvsLODLORflow(subssn, subfirstName, sublastName, "Yes");
		System.out.println("Result: The case is sent to appeals process by SO form portal");

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log into CATS as Process team member
		reusable.loginToCATS(procTeamUserName);

		// Call the lodLORappeals process as process team member
		System.out.println("Test: Verify that the case is claimed,NOIA document is uploaded and case sent to Adjudicaotr by process tema member");
		reusable.lodLORProcessTeamAppealsFlow(subfirstName, sublastName);
		System.out.println("Result:The case is claimed,NOIA document is uploaded and case sent to Adjudicaotr by process tema member");

		// Log out process team useriga
		catsLogout();

		// Log into CATS as Adjudicator
		reusable.loginToCATS(adjudicatorUserName);

		// Call the appealsadjudication method to grant favorable determination
		// and update the eligibility after adjudication

		reusable.appealAdjudicationAsAdjudicator(caseID, "Favorable", CATSProperties.LevelConfidential.getProperty(), CATSProperties.AppealDetermiantionFavorable.getProperty(), CATSProperties.ExceptionNone.getProperty());
		pause(5);
		System.out.println("Result:The Third case went through Due process and adjudicated in Appeals flow and closed with determination = favorable");

		// Log out user adjudicator from CATS
		catsLogout();

	}

/**
 * This function will revoke a case based on ssn passed to it
 * @param searchableSsn1
 */
	public void caseDenied(String caseID) {
reusable.searchCase(caseID);

		// Click on the determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

		// Click on revoked option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDenied.getProperty()));

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



}
