package com.iworks.DISS.test.catsRegression.favorableAfterDueProcess;

import java.util.ArrayList;
import java.util.List;

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
import com.iworks.DISS.test.common.functions.ReusableFunctions;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.TestBase;
/**
 * This Test script will select Favorable determination for the case after Due process and verify that the history of the case has the appropriate details.
 * This TC assumes the following preconditions for the case: User has Adjudicate Due Process permission, Determination Type: Security,Adjudication Phase: Due Process or Due Process Review,
 * the Subject has responded to the SOR and all RFAs are in closed status and the disqualifying guidelines are mitigated.
 * @author vshivaraman 
 */

public class FavorableAfterDueProcess extends TestBase {
	private  String adjudicatorID = null;
	private  String securityOfficerID = null;
	private  String investigationType =null;
	private  String smoName = null;
	private  String divisionName = null;
	private  String executiveChiefID = null;
	private  String categoryType = null;// JVSProperties.CivilianRetiree.getProperty();


	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	public static class SubjectInfo{
		public String fName;
		public String lName;
		public String caseID;
		public String ssn;
		
	}
	public  Class<? extends FavorableAfterDueProcess> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
	
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className,name);
		adjudicatorID = PreconditionVariables.getProperty("adjudicatorID");
		System.out.println("Adjudicator ID : "+adjudicatorID);
		securityOfficerID=PreconditionVariables.getProperty("securityOfficerID");
		System.out.println("Security officer ID : "+securityOfficerID);
		executiveChiefID="1";
		investigationType="//*[@id='createCaseForm:createCaseInvType_panel']//li[@data-label='"+PreconditionVariables.getProperty("investigationType")+"']";
		System.out.println("Investigation Type : "+PreconditionVariables.getProperty("investigationType"));
		smoName="//li[@data-label='"+PreconditionVariables.getProperty("smoName")+"']";
		System.out.println("SMO Name : "+PreconditionVariables.getProperty("smoName"));
		divisionName="//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='"+PreconditionVariables.getProperty("divisionName")+"']";
		System.out.println("Division Name : "+PreconditionVariables.getProperty("divisionName"));
		categoryType="//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel']//li[text()='"+PreconditionVariables.getProperty("categoryType")+"']";
		System.out.println("Category Type : "+PreconditionVariables.getProperty("categoryType")+"\n");

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

		
		quitBrowser();
	}

	



	/**
	 * This method will create test data for case with Current eligibility = S, Requested eligibility = C
	 * and determination = Favorable after due process and verify that the current eligibility =C (5.4 req change) after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	@Test
	public void testCaseWithEligibility() throws Exception {
	
	System.out.println("Test: Create test data with the following preconditions set :1. User has Adjudicate Due Process permission, "
				+ "2. Determination Type: Security,3.Adjudication Phase: Due Process or Due Process Review ,"
				+ "4.The Subject has responded to the SOR and all RFAs are in closed status,5. The disqualifying guidelines are mitigated.\n");

	List<SubjectInfo> list = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.AllegiancePanel.getProperty(), CATSProperties.MitigationCondition.getProperty());
	String ssn = list.get(0).ssn;
	String caseID = list.get(0).caseID;
	String firstName = list.get(0).fName;
	String lastName = list.get(0).lName;
	reusable.loginToCATS(adjudicatorID);

	
	
	System.out.println("Test:Initiating letter: Verify that the list of letters to be generated are present.\n");
	initiateLetter(caseID);
	System.out.println("Result:TEST PASS: Initiating letter: Verified that the list of letters to be generated are present.\n");
	
	System.out.println("Test:Letter not attached: Verify that case cannot be closed favorably when no Favorable Determination after Due Process Memo letter is attached.\n");
	doNotAddLetter(caseID,"Due Process Memo not attached to case documents");
	System.out.println("Result:TEST PASS: Letter not attached: Verified that case cannot be closed favorably when no Favorable Determination after Due Process Memo letter is attached.\n");
	
	System.out.println("Test:Add letter to Documents: Upload 'Favorable Determination after Due Process Memo letter' in Documents Tab.\n");
	addLetter(caseID);
	System.out.println("Result:TEST PASS: Add letter to Documents: Uploaded 'Favorable Determination after Due Process Memo letter' in Documents Tab.\n");
	
	System.out.println("Test:Complete Due Process as Favorable: Close case as favorable after due process.\n");
	completeDueprocess(caseID);
	System.out.println("Result:TEST PASS :Complete Due Process as Favorable: Closed case as favorable after due process.\n");
//	
//	jvsreusable.loginToJVS(securityOfficerID);
//	System.out.println("Test:Notification check as SO: Verify entry for 'Favourable After Due Process Memo' in the Notification tab.\n");
//	verifyNotificationAsSO(ssn, firstName,lastName, caseID);
//	System.out.println("Result:TEST PASS :Notification check as SO: Verified entry for 'Favourable After Due Process Memo' in the Notification tab.\n");
//	jvsreusable.logout();
	
	reusable.loginToCATS(adjudicatorID);
	reusable.searchCase(caseID);
	
	System.out.println("Test:History Table: Verify entry for 'Final Determination Made', 'Due process Ended' and 'Case Closed'.\n");
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("History"));
	verifyHistory("Final Determination Made");
	verifyHistory("Due Process Ended");
	verifyHistory("Case Closed");
	System.out.println("Result:TEST PASS: History Table: Verified entry for 'Final Determination Made', 'Due process Ended' and 'Case Closed'.\n");
	
	System.out.println("Test:Points: Verify entry for 'Productivity points' in the LHS panel.\n");
	verifyPoints();
	System.out.println("Result:TEST PASS:Points: Verified entry for 'Productivity points' in the LHS panel.\n");
	
	catsLogout();
	// Verify in cats
	verifyCatsEligibility(ssn,"Confidential");
	// Now verify in JVS
	portal_verification(ssn,"Confidential");
	}

	/**
	 * This method will create test data for case with Current eligibility = None, Requested eligibility = SCI
	 * and determination = Favorable after due process and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	
	@Test
	public void testCaseWithNoEligibility() throws Exception {
		System.out.println("Test: Create test data with the following preconditions set :1. User has Adjudicate Due Process permission, "
				+ "2. Determination Type: Security,3.Adjudication Phase: Due Process or Due Process Review ,"
				+ "4.The Subject has responded to the SOR and all RFAs are in closed status,5. The disqualifying guidelines are mitigated.\n");
		List<SubjectInfo> list = createTestData1(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.AllegiancePanel.getProperty(), CATSProperties.MitigationCondition.getProperty());
		String ssn = list.get(0).ssn;
		String caseID = list.get(0).caseID;
		String firstName = list.get(0).fName;
		String lastName = list.get(0).lName;
		reusable.loginToCATS(adjudicatorID);
		
		System.out.println("Test:Initiating letter: Verify that the list of letters to be generated are present.\n");
		initiateLetter(caseID);
		System.out.println("Result:TEST PASS: Initiating letter: Verified that the list of letters to be generated are present.\n");
		
		System.out.println("Test:Letter not attached: Verify that case cannot be closed favorably when no Favorable Determination after Due Process Memo letter is attached.\n");
		doNotAddLetter(caseID,"Due Process Memo not attached to case documents");
		System.out.println("Result:TEST PASS: Letter not attached: Verified that case cannot be closed favorably when no Favorable Determination after Due Process Memo letter is attached.\n");
		
		System.out.println("Test:Add letter to Documents: Upload 'Favorable Determination after Due Process Memo letter' in Documents Tab.\n");
		addLetter(caseID);
		System.out.println("Result:TEST PASS: Add letter to Documents: Uploaded 'Favorable Determination after Due Process Memo letter' in Documents Tab.\n");
		
		System.out.println("Test:Complete Due Process as Favorable: Close case as favorable after due process.\n");
		completeDueprocess(caseID);
		System.out.println("Result:TEST PASS :Complete Due Process as Favorable: Closed case as favorable after due process.\n");
//		
//		jvsreusable.loginToJVS(securityOfficerID);
//		System.out.println("Test:Notification check as SO: Verify entry for 'Favourable After Due Process Memo' in the Notification tab.\n");
//		verifyNotificationAsSO(ssn, firstName,lastName, caseID);
//		System.out.println("Result:TEST PASS :Notification check as SO: Verified entry for 'Favourable After Due Process Memo' in the Notification tab.\n");
//		jvsreusable.logout();
		
		reusable.loginToCATS(adjudicatorID);
		reusable.searchCase(caseID);
		
		System.out.println("Test:History Table: Verify entry for 'Final Determination Made', 'Due process Ended' and 'Case Closed'.\n");
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("History"));
		verifyHistory("Final Determination Made");
		verifyHistory("Due Process Ended");
		verifyHistory("Case Closed");
		System.out.println("Result:TEST PASS: History Table: Verified entry for 'Final Determination Made', 'Due process Ended' and 'Case Closed'.\n");
		
		System.out.println("Test:Points: Verify entry for 'Productivity points' in the LHS panel.\n");
		verifyPoints();
		System.out.println("Result:TEST PASS:Points: Verified entry for 'Productivity points' in the LHS panel.\n");
		
		catsLogout();
//		// Verify in cats
		verifyCatsEligibility(ssn, "SCI - ICD704");
//		// Now verify in JVS
		portal_verification(ssn, "SCI - ICD704");
	}


	
	/**
	 * This method will create the test data 
	 * for Favorable after due process scenarios 
	 * and set the Current eligibility such that the subject has an eligibility and process the case through due process.
	 * @param caseType
	 * @param caseType1
	 * @param levelCodeOption
	 * @return
	 * @throws Exception
	 */
	public List<SubjectInfo> createTestData(String caseType , String caseType1,String levelCodeOption,String levelCodeOption1,String conditionPanel,String mitigationCondition) throws Exception {

		List<SubjectInfo> reList = new ArrayList<FavorableAfterDueProcess.SubjectInfo>();
		
		SubjectInfo subInfo = new SubjectInfo();
		// Log in to CATS UAT as user 1 
		
		reusable.loginToCATS(executiveChiefID);
		
		// Call the ssnNameGenerator method to set First name,last name and SSN
		// based on baseSSN and index
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("autoFName", "autoLName");
		subInfo.ssn = list.get(0).ssn;
		subInfo.fName = list.get(0).firstName;
		subInfo.lName = list.get(0).lastName;
		System.out.println("SSN is " + subInfo.ssn);
		System.out.println("First name is " + subInfo.fName);
		System.out.println("Last name is " + subInfo.lName);
		
	
	
		//Create Subject based on the SSN, First name and last name passed
		reusable.createSubject(subInfo.ssn, subInfo.fName, subInfo.lName,"1983","Oct","20","United States");
		reusable.addCitizenshipForSubject(subInfo.ssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		
		// Log out of CATS user "1"
		catsLogout();
		
		// Log in to JVS as 1
		jvsreusable.loginToJVS(securityOfficerID);

		// Create SMO relationship between the case and the SO
		jvsreusable.createSMORelationship(categoryType, subInfo.ssn);

		// Log out of JVS
		jvsreusable.jvsLogout();
		
		// Log in to CATS UAT as user 1 
		reusable.loginToCATS(executiveChiefID);

		// Create case for the subject
		reusable.createCaseForSubject(subInfo.ssn, divisionName, caseType, smoName, investigationType);
		WaitingToLoad();

		// Get the case ID of the cases created
		String caseID = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("First case ID is : "+caseID);

		// Log out of CATS user 1
		catsLogout();

		// Login to CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);
		
		// Select case from Open case Datatable based on SSN.
		String searchableSsn = subInfo.ssn.substring(0, 3)+ "-" + subInfo.ssn.substring(3, 5) + "-" + subInfo.ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable_data']");

		// Close the case created with Favorable determination and current eligibility is set to caseType
		reusable.determinationFavorable(caseID,levelCodeOption);
		
		// Logout as adjauto21
		catsLogout();
		
		// Login as 1
		reusable.loginToCATS(executiveChiefID);
		
		// This method will create a new case for the same SSN above
		reusable.createCaseForSubject(subInfo.ssn, divisionName, caseType1, smoName, investigationType);
		WaitingToLoad();
		
		// Get the new case ID for same subject
		String caseID1 = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("Second Case ID is: " +caseID1);

		//Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']"));
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

		// logout as 1
		catsLogout();
		
		//log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

		// Set the case to none and refer to Due process
		reusable.determinationsNotFavorable(caseID1, CATSProperties.DeterminationNone.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(),CATSProperties.CivilianMPCCode.getProperty());

		// Initiate CATS SOR Flow
		reusable.sorFlow(subInfo.ssn, CATSProperties.SOROption.getProperty());

		// Logout as adjauto21
		catsLogout();

		// Log in to Portal as SO
		jvsreusable.loginToJVS(securityOfficerID);

		// Call JVSSORFlow method
		jvsreusable.jvsSORflow(subInfo.ssn, subInfo.fName, subInfo.lName);

		// Log out of JVS
		jvsreusable.jvsLogout();
		subInfo.caseID = caseID1;
		reList.add(subInfo);
		reusable.loginToCATS(adjudicatorID);
		reusable.searchCase(caseID);
		
		// Click on Guidelines Tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Guidelinestab.getProperty()));
		pause(30);

		// Expand on a condition panel
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(conditionPanel));
		pause(5);


		// Select a mitigating condition
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(mitigationCondition));
		pause(3);

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelineOutputPanelId']//button[contains(.,'Save')]"));
		pause(30);
		WaitingToLoad();
			
		return reList;

	}
	
	/**
	 * This method will create the test data for Favorable after due process scenarios 
	 * and set the Current eligibility such that the subject has no eligibility and process the case through due process.
	 * @param caseType
	 * @return
	 * @throws Exception
	 */
	public List<SubjectInfo> createTestData1(String caseType, String levelCodeOption,String conditionPanel,String mitigationCondition ) throws Exception {

		List<SubjectInfo> reList = new ArrayList<FavorableAfterDueProcess.SubjectInfo>();
		
		SubjectInfo subInfo = new SubjectInfo();
		// Log in to CATS UAT as user 1 
		
		reusable.loginToCATS(executiveChiefID);
		
		// Call the ssnNameGenerator method to set First name,last name and SSN
		// based on baseSSN and index
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("autoFName", "autoLName");
		subInfo.ssn = list.get(0).ssn;
		subInfo.fName = list.get(0).firstName;
		subInfo.lName = list.get(0).lastName;
		System.out.println("SSN is " + subInfo.ssn);
		System.out.println("First name is " + subInfo.fName);
		System.out.println("Last name is " + subInfo.lName);
		
	
	
		//Create Subject based on the SSN, First name and last name passed
		reusable.createSubject(subInfo.ssn, subInfo.fName, subInfo.lName,"1983","Oct","20","United States");
		reusable.addCitizenshipForSubject(subInfo.ssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		
		// Log out of CATS user "1"
		catsLogout();
		
		// Log in to JVS as 1
		jvsreusable.loginToJVS(securityOfficerID);

		// Create SMO relationship between the case and the SO
		jvsreusable.createSMORelationship(categoryType, subInfo.ssn);

		// Log out of JVS
		jvsreusable.jvsLogout();
		
		// Log in to CATS UAT as user 1 
		reusable.loginToCATS(executiveChiefID);

		// Create case for the subject
		reusable.createCaseForSubject(subInfo.ssn, divisionName, caseType, smoName, investigationType);
		WaitingToLoad();

		// Get the case ID of the cases created
		String caseID = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("First case ID is : "+caseID);


		//Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']"));
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

		// logout as 1
		catsLogout();
		
		//log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorID);
		// Select case from Open case Datatable based on SSN.
		String searchableSsn = subInfo.ssn.substring(0, 3)+ "-" + subInfo.ssn.substring(3, 5) + "-" + subInfo.ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable_data']");


		// Set the case to none and refer to Due process
		reusable.determinationsNotFavorable(caseID, CATSProperties.DeterminationNone.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(),CATSProperties.CivilianMPCCode.getProperty());

		// Initiate CATS SOR Flow
		reusable.sorFlow(subInfo.ssn, CATSProperties.SOROption.getProperty());

		// Logout as adjauto21
		catsLogout();

		// Log in to Portal as SO
		jvsreusable.loginToJVS(securityOfficerID);

		// Call JVSSORFlow method
		jvsreusable.jvsSORflow(subInfo.ssn, subInfo.fName, subInfo.lName);

		// Log out of JVS
		jvsreusable.jvsLogout();
		subInfo.caseID = caseID;
		reList.add(subInfo);
		reusable.loginToCATS(adjudicatorID);
		reusable.searchCase(caseID);
		
		// Click on Guidelines Tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Guidelinestab.getProperty()));
		pause(30);

		// Expand on a condition panel
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(conditionPanel));
		pause(5);


		// Select a mitigating condition
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(mitigationCondition));
		pause(3);

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelineOutputPanelId']//button[contains(.,'Save')]"));
		pause(30);
		WaitingToLoad();

		//
			catsLogout();	
		return reList;

	}
	
	public void initiateLetter(String caseID) throws Exception{
		
		//Search for the case
		reusable.searchCase(caseID);
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Documents"));
		ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:templetterForm:lettertable_data']", "Favorable Determination after Due Process Memo Template");
		
		
	}
	public void doNotAddLetter(String caseID,String errormessage)throws Exception{
		String determination = CATSProperties.DeterminationFavorable.getProperty();
		reusable.searchCase(caseID);
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		pause(5);

		// Click on the Determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));
		
		pause(5);
		// Click on the option based on value of determination variable
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(determination));

		// Click save Button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));
		pause(5);

		// Summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));
		
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Close Case");
		//WaitingToLoad();
		//verify error message
		ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSummaryId']//span[contains(@class,'ui-messages-error-summary')]", errormessage);
		
		
	}
	
	public void addLetter(String caseID) throws Exception{
		//Search for the case
		reusable.searchCase(caseID);
		//Click on Documents tab
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Documents"));
		//Verify Favorable Determination after Due process template is available
		ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:templetterForm:lettertable_data']", "Favorable Determination after Due Process Memo Template");
		//Click on Add Document button
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseDocumentsTableId:addDocumentButton']"));
		//Upload the document
		reusable.docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), "Favorable Determination after Due Process Memo", "Favorable Determination after Due Process","Favarable after Due process");
		
	}
	
	public void completeDueprocess(String caseID )throws Exception{
		String determination = CATSProperties.DeterminationFavorable.getProperty();
		reusable.searchCase(caseID);

		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));
		pause(5);
		// Click on the Determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));
		
		pause(5);
		// Click on the option based on value of determination variable
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(determination));

		// Click save Button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));
		pause(5);

		// Summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));
		
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Close Case");
		WaitingToLoad();

		// Click on confirmation yes button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");
		
		
		WaitingToLoad();
		// verify that growl message is seen
		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Case Closed");
		pause(5);
		System.out.println("case is closed \n");
		catsLogout();


	}
	
	public void verifyNotificationAsSO(String ssn,String firstName,String lastName,String caseID)throws Exception{
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Unread Notifications"));
		
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[preceding-sibling::span[contains(text(),'Details')] and contains(@id,'filter')]"));
		String text = "Favourable After Due Process Memo";
		ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[preceding-sibling::span[contains(text(),'Details')] and contains(@id,'filter')]"),text);
		pause(5);
		//		ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//div[@id='majorTabPanel:Notifications']//td[preceding-sibling::td[contains(text(),'SMO')] and contains(text(),'"+text+"')]"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:Notifications']//td[preceding-sibling::td[contains(text(),'SMO')] and contains(text(),'"+text+"')]"));
		pause(5);
		ReusableFunctions.waitAndVerify_IfContains("//table/tbody//td[preceding-sibling::td[contains(text(),'Related Subject:')]]/span[@id='majorTabPanel:notificationTemplateRelatedSubjectLink']", firstName+" "+lastName);//autoFName691 autoLName691 majorTabPanel:notificationTemplateRelatedSubjectLink
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:closeNotificationId']"));
		System.out.println("Result:Favorable After Due Process Memo notification is seen.- TEST PASS \n");

	}
	public void verifyHistory(String action)throws Exception{
				
		//Verify entry for action
		ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']", action);
		
		String firstName =ReusableFunctions.waitAndGetText(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']//td[preceding-sibling::td/span[contains(text(),'"+action+"')]]/span[contains(@id,':chPersonFirstName')]"));
		String lastName= ReusableFunctions.waitAndGetText(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']//td[preceding-sibling::td/span[contains(text(),'"+action+"')]]/span[contains(@id,':chPersonLastName')]"));
		String date = ReusableFunctions.waitAndGetText(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId_data']//td[preceding-sibling::td/span[contains(text(),'"+action+"')]]/span[contains(@id,':chActionDate')]"));
		System.out.println("The Case History subtab will display the Action as :"+action+" Action Taken By as :"+firstName+" "+lastName+" and Date as : "+date);
	}
	
	public void verifyPoints()throws Exception{
		ReusableFunctions.waitAndVerify_IfContains("//table[@id='prodPanel:prodDashboard2']", "Earned");
		String productivityPoints = ReusableFunctions.waitAndGetText(By.xpath("//table[@id='prodPanel:prodDashboard2']//td[preceding-sibling::td/label[contains(text(),'Earned')]]/label"));
		System.out.println("Productivity points earned is : "+productivityPoints);
	}

	/**
	 * This method will verify that the current eligibility 
	 * has been updated correctly based on the case and determination in CATS
	 * @param ssn
	 * @return
	 * @throws Exception
	 */
	public void verifyCatsEligibility(String ssn,String eligibility) throws Exception {

		
		String current_eligibility;
		
		//Log in to CATS 
		reusable.loginToCATS(adjudicatorID);
		
		//Search for the SSN
		reusable.searchSubjectCATS(ssn);
		
		// Gets the current eligibility of the case after the adjudication
		current_eligibility = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//td[preceding-sibling::td/div[contains(text(),'Eligibility Level')]]"));
		System.out.println("Eligibility after adjudication is " + current_eligibility);

		//Check if the current eligibility contains text specified in eligibility
		if (current_eligibility.contains(eligibility)) {
			System.out.println("Correct Eligibility after adjudication in CATS is verified as " + current_eligibility);
		} else {
			System.out.println("Wrong Eligibility after adjudication in CATS is seen as "+ current_eligibility);
		}
		
		// Log out Adjudicator from CATs
		catsLogout();
		
	}

	/**
	 * This method will verify that the current eligibility 
	 * updated based on case and determination is reflected in JVS and is the same as in CATS
	 * @param ssn
	 * @param eligibilityCATS
	 * @throws Exception
	 */
	public void portal_verification(String ssn, String eligibility) throws Exception {
		//Log in to JVS as SO
		jvsreusable.loginToJVS(securityOfficerID);
		
		//Search for the subject based on SSN
		jvsreusable.subjectSearch(ssn);
		
		// Click on Subject Details link in Subject summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SubjectDetailsLink1.getProperty()));
		
		//Get eligibility value in JVS after adjudication
		String eligibility1 = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//td[preceding-sibling::td/div[contains(text(),'Eligibility Level')]]"));
		
		//Check if the eligibility1 contains text specified in eligibility
		if (eligibility1.contains(eligibility)) {
			System.out.println("Correct Eligibility after adjudication in PORTAL is verified as " + eligibility1 );
		} else {
			System.out.println("Wrong eligibility after adjudication in PORTAL is seen as "+ eligibility1 );
		}
		
		//Log out SO from JVS
		jvsreusable.jvsLogout();
	}

}
