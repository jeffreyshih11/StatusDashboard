package com.iworks.DISS.test.catsRegression.rfas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
 * This Test script will 
 * @author vshivaraman 
 */

public class OtherAgencyFilesRFA extends TestBase {
	
	private String executiveChiefID = null;
	private static final String TEMPLATE_TYPE= "Request for Other Agency Files Letter Template";
	private static final String DOCUMENT_TYPE = "Request for Other Agency Files Letter";
	private static final String RFA_NAME = "Other Agency Files";
	private String processTeamMemberID= null;
	private  String securityOfficerID = null;
	private  String adjudicatorID = null;
	private  String investigationType = null;
	private  String smoName = null;
	private  String divisionName = null;
	private  String categoryType = null;
	
	
	
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	ReusableFunctions reuse = new ReusableFunctions();

	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;

	public static class SubjectInfo {
		public String fName;
		public String lName;
		public String ssn;
		public String caseID;
	}
	public  Class<? extends OtherAgencyFilesRFA> clazz = this.getClass();

	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
		
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className,name);
		
		adjudicatorID = PreconditionVariables.getProperty("adjudicatorID");
		System.out.println("Adjudicator ID : "+adjudicatorID);
		processTeamMemberID=PreconditionVariables.getProperty("processTeamMemberID");
		System.out.println("Processteam Member ID : "+processTeamMemberID);
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
		System.out.println("Category Type : "+PreconditionVariables.getProperty("categoryType"));


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
	 * This method will create test data to test Medical Evaluation RFA functionalities for a case which has existing eligibility
	 * @throws Exception
	 */
	@Test
	public void verifyOtherAgencyFilesRFAForCaseWithEligibility() throws Exception {
	List<SubjectInfo> list  = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(),CATSProperties.CivilianMPCCode.getProperty());
	String firstName = list.get(0).fName;
	String lastName = list.get(0).lName;
	String ssn = list.get(0).ssn;
	String caseID = list.get(0).caseID;
	
	//log in to CATS as adjudicator
	reusable.loginToCATS(adjudicatorID);
	
	System.out.println("Test: Verify Generate Request for Medical Evaluation Letter functionality \n");
	reusable.generateRequestforLetter(caseID,TEMPLATE_TYPE);
	System.out.println("Result: Generate Request for Medical Evaluation Letter functionality was successful- TEST PASS \n");
	
	System.out.println("Test:Initiate Request for Other Agency Files Request Functionality \n");
	reusable.initiateRequestforRFA(RFA_NAME);
	System.out.println("Result:Initiate Request for Other Agency Files Request succesful- TEST PASS \n");
	
	System.out.println("Tets:Request for Other Agency Files RFA pop up- TC \n");
	String[] rfaFields = {"Request for Other Agency Files","Subject Details","First Name:","Middle Name:","Last Name:","SSN","Eligibility","SMO","Case Detail","Case ID","Requesting SMO", "Case Type","RFA Documents","Adjudicator"};
	reusable.verifyRFAPopUpInfo(rfaFields);
	System.out.println("Result:Request for Other Agency Files RFA pop up-TC- TEST PASS \n");
	
	System.out.println("Test:Request for Other Agency Files letter not attached \n");
	String rfaErrorMessage= "Provide Request for Other Agency Files Letter document.";
	String[] errormessages = {"Document required","Document name is required","Document type is required"};
	reusable.verifyRFAPopUpErrorMessages(rfaErrorMessage,errormessages);
	System.out.println("Result:Request for Other Agency Files letter not attached-Successful- error message is seen correctly-TEST PASS");
	
	System.out.println("Test:Verify Send Request for Other Agency Files RFA functionality \n");
	sendRFAforotherAgencyFile(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Other Agency File Letter", "Descripttion:Request for Other Agency File Letter");
	System.out.println("Result:Send Request for Other Agency Files RFA functionality- TEST PASS \n");
	
	System.out.println("Test:Verify cancel Request for Other Agency Files RFA functionality \n");
	reusable.verifyRFACancel(caseID,RFA_NAME);
	System.out.println("Result:Cancel Request for Other Agency Files RFA functionality- TEST PASS \n");
	
	sendRFAforotherAgencyFile(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Other Agency File Letter", "Descripttion:Request for Other Agency File Letter");
	
	catsLogout();
	
	//Log in to CATS as process team member
	reusable.loginToCATS(processTeamMemberID);
	
	//Claim RFA
	rfaProcessTeamClaim(firstName, lastName);
	catsLogout();
	
	//Log in as Adjudicator
	reusable.loginToCATS(adjudicatorID);
	
	//Verify claim date
	System.out.println("Test: Verify that correct claim date is seen.");
	String claimDate = reusable.getClaimDate(caseID, RFA_NAME);
	DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
	Calendar cal = Calendar.getInstance();
	String date = dateFormat.format(cal.getTime());
	if(claimDate.equalsIgnoreCase(date)) {
		System.out.println("Result: Claim date is seen correctly as : "+claimDate+"-TEST PASS \n");
	}else {
		System.out.println("Result: Claim date is not seen correclty : "+claimDate+"-TEST FAIL\n");
	}
	
	catsLogout();
	
	reusable.loginToCATS(processTeamMemberID);

	System.out.println("Test : Complete RFA process as Process team member \n");
	rfaProcessTeamFlow(firstName, lastName,"Other","RFA ProcessTeam");
	System.out.println("Result : Complete RFA process as Process team member- TEST PASS \n");
	catsLogout();

	reusable.loginToCATS(adjudicatorID);

	System.out.println("Test: Verify RFA Notification \n");
	verifyNotification(firstName,lastName,"Case available for assignment.",caseID);
	System.out.println("Result: Verify RFA Notification -TEST PASS\n");
	
	reusable.searchCase(caseID);
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("History"));

	System.out.println("Test: Verify history for RFA request sent \n");
	reusable.verifyHistory(caseID, "RFA Request for Other Agency Files Sent");
	System.out.println("Result: Verify history for RFA request sent- TEST PASS \n");
	
	System.out.println("Test: Verify history for RFA response received \n");
	reusable.verifyHistory(caseID, "RFA Request for Other Agency Files Response Received");
	System.out.println("Result: Verify history for RFA response received- TEST PASS \n");
	
	System.out.println("Test: Verify Points \n");
	reusable.verifyEarnedPoints();
	System.out.println("Result: Verify Points- TEST PASS \n");
	
	
	catsLogout();


	}

	/**
	 * This method will create test data to test Medical Evaluation RFA functionalities for a case which has  NO existing eligibility
	 * @throws Exception
	 */
	@Test
	public void verifyOtherAgencyFilesRFAForCaseWithoutEligibility() throws Exception {
	List<SubjectInfo> list = createTestData1(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(),CATSProperties.CivilianMPCCode.getProperty());
	String firstName = list.get(0).fName;
	String lastName = list.get(0).lName;
	String ssn = list.get(0).ssn;
	String caseID = list.get(0).caseID;
	
	//log in to CATS as adjudicator
	reusable.loginToCATS(adjudicatorID);
	
	System.out.println("Test: Verify Generate Request for Medical Evaluation Letter functionality \n");
	reusable.generateRequestforLetter(caseID,TEMPLATE_TYPE);
	System.out.println("Result: Generate Request for Medical Evaluation Letter functionality was successful- TEST PASS \n");
	
	System.out.println("Test:Initiate Request for Other Agency Files Request Functionality \n");
	reusable.initiateRequestforRFA(RFA_NAME);
	System.out.println("Result:Initiate Request for Other Agency Files Request succesful- TEST PASS \n");
	
	System.out.println("Tets:Request for Other Agency Files RFA pop up- TC \n");
	String[] rfaFields = {"Request for Other Agency Files","Subject Details","First Name:","Middle Name:","Last Name:","SSN","Eligibility","SMO","Case Detail","Case ID","Requesting SMO", "Case Type","RFA Documents","Adjudicator"};
	reusable.verifyRFAPopUpInfo(rfaFields);
	System.out.println("Result:Request for Other Agency Files RFA pop up-TC- TEST PASS \n");
	
	System.out.println("Test:Request for Other Agency Files letter not attached \n");
	String rfaErrorMessage= "Provide Request for Other Agency Files Letter document.";
	String[] errormessages = {"Document required","Document name is required","Document type is required"};
	reusable.verifyRFAPopUpErrorMessages(rfaErrorMessage,errormessages);
	System.out.println("Result:Request for Other Agency Files letter not attached-Successful- error message is seen correctly-TEST PASS");
	
	System.out.println("Test:Verify Send Request for Other Agency Files RFA functionality \n");
	sendRFAforotherAgencyFile(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Other Agency File Letter", "Descripttion:Request for Other Agency File Letter");
	System.out.println("Result:Send Request for Other Agency Files RFA functionality- TEST PASS \n");
	
	System.out.println("Test:Verify cancel Request for Other Agency Files RFA functionality \n");
	reusable.verifyRFACancel(caseID,RFA_NAME);
	System.out.println("Result:Cancel Request for Other Agency Files RFA functionality- TEST PASS \n");
	
	sendRFAforotherAgencyFile(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Other Agency File Letter", "Descripttion:Request for Other Agency File Letter");
	
	catsLogout();
	
	//Log in to CATS as process team member
	reusable.loginToCATS(processTeamMemberID);
	
	//Claim RFA
	rfaProcessTeamClaim(firstName, lastName);
	catsLogout();
	
	//Log in as Adjudicator
	reusable.loginToCATS(adjudicatorID);
	
	//Verify claim date
	System.out.println("Test: Verify that correct claim date is seen.");
	String claimDate = reusable.getClaimDate(caseID, RFA_NAME);
	DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
	Calendar cal = Calendar.getInstance();
	String date = dateFormat.format(cal.getTime());
	if(claimDate.equalsIgnoreCase(date)) {
		System.out.println("Result: Claim date is seen correctly as : "+claimDate+"-TEST PASS \n");
	}else {
		System.out.println("Result: Claim date is not seen correclty : "+claimDate+"-TEST FAIL\n");
	}
	
	catsLogout();
	
	reusable.loginToCATS(processTeamMemberID);

	System.out.println("Test : Complete RFA process as Process team member \n");
	rfaProcessTeamFlow(firstName, lastName,"Other","RFA ProcessTeam");
	System.out.println("Result : Complete RFA process as Process team member- TEST PASS \n");
	catsLogout();

	reusable.loginToCATS(adjudicatorID);

	System.out.println("Test: Verify RFA Notification \n");
	verifyNotification(firstName,lastName,"Case available for assignment.",caseID);
	System.out.println("Result: Verify RFA Notification -TEST PASS\n");
	
	reusable.searchCase(caseID);
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("History"));

	System.out.println("Test: Verify history for RFA request sent \n");
	reusable.verifyHistory(caseID, "RFA Request for Other Agency Files Sent");
	System.out.println("Result: Verify history for RFA request sent- TEST PASS \n");
	
	System.out.println("Test: Verify history for RFA response received \n");
	reusable.verifyHistory(caseID, "RFA Request for Other Agency Files Response Received");
	System.out.println("Result: Verify history for RFA response received- TEST PASS \n");
	
	System.out.println("Test: Verify Points \n");
	reusable.verifyEarnedPoints();
	System.out.println("Result: Verify Points- TEST PASS \n");
	
	
	catsLogout();
		}
	
	/**
	 * This method will create the test data 
	 * for Medical evaluation RFA test cases for subject with existing eligibility 
	 * and sets the Current eligibility for each case as specified in the pre-condition
	 * @param caseType
	 * @param caseType1
	 * @param levelCodeOption
	 * @return
	 * @throws Exception
	 */
	public List<SubjectInfo> createTestData(String caseType, String caseType1, String levelCodeOption, String levelCodeOption1, String conditionPanel, String disqualifyingCondition, String codeMCP) throws Exception {
		SubjectInfo uInfo = new SubjectInfo();
		// Log in to CATS UAT as user 1
		reusable.loginToCATS(executiveChiefID);

		// add the template for medical evaluation request
		reusable.manageTemplates(TEMPLATE_TYPE, OR.getProperty("filePathOtherAgencyFileTemp"));

		// Call the ssnNameGenerator method to set First name,last name and SSN
		// based on baseSSN and index
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("autoFName", "autoLName");
		String ssn = list.get(0).ssn;
		String firstName = list.get(0).firstName;
		String lastName = list.get(0).lastName;
		System.out.println("SSN is " + ssn);
		System.out.println("First name is " + firstName);
		System.out.println("Last name is " + lastName);
		uInfo.fName = firstName;
		uInfo.lName = lastName;
		uInfo.ssn = ssn;

		// Create Subject based on the SSN, First name and last name passed
		reusable.createSubject(ssn, firstName, lastName, "1983", "Oct", "20", "United States");
		reusable.addCitizenshipForSubject(ssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");

		// Log out of CATS user "1"
		catsLogout();

		// Log in to JVS as 1
		jvsreusable.loginToJVS(securityOfficerID);

		// Create SMO relationship between the case and the SO
		jvsreusable.createSMORelationship(categoryType, ssn);

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS UAT as user 1
		reusable.loginToCATS(executiveChiefID);

		// Create case for the subject
		reusable.createCaseForSubject(ssn, divisionName, caseType, smoName, investigationType);
		WaitingToLoad();

		// Get the case ID of the cases created
		String caseID = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("First case ID is : " + caseID);

		// Log out of CATS user 1
		catsLogout();

		// Login to CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select case from Open case Datatable based on SSN.
		String searchableSsn = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable_data']");

		// Close the case created with Favorable determination and current
		// eligibility is set to caseType
		reusable.determinationFavorable(caseID, levelCodeOption);

		// Logout as adjauto21
		catsLogout();

		// Login as 1
		reusable.loginToCATS(executiveChiefID);

		// This method will create a new case for the same SSN above
		reusable.createCaseForSubject(ssn, divisionName, caseType1, smoName, investigationType);
		WaitingToLoad();

		// Get the new case ID for same subject
		String caseID1 = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("Second Case ID is: " + caseID1);

		// Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']"));
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

	

	
		// verify that growl message is seen
		pause(5);
		uInfo.caseID = caseID1;
		List<SubjectInfo> returnList = new ArrayList<OtherAgencyFilesRFA.SubjectInfo>();
		returnList.add(uInfo);
		// log out of CATS
		catsLogout();
		return returnList;

	}
	
	/**
	 * This method will create the test data 
	 * for Medical evaluation RFA test cases for subject without existing eligibility 
	 * and sets the Current eligibility for each case as specified in the pre-condition
	 * @param caseType
	 * @return
	 * @throws Exception
	 */
	public List<SubjectInfo> createTestData1(String caseType, String levelCodeOption, String conditionPanel, String disqualifyingCondition, String codeMCP ) throws Exception {
		SubjectInfo uInfo = new SubjectInfo();
		// Log in to CATS UAT as user 1 
		reusable.loginToCATS(executiveChiefID);
		
		// add the template for medical evaluation request
		reusable.manageTemplates(TEMPLATE_TYPE, OR.getProperty("filePathOtherAgencyFileTemp"));

		// Call the ssnNameGenerator method to set First name,last name and SSN
		// based on baseSSN and index
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("autoFName", "autoLName");
		String ssn = list.get(0).ssn;
		String firstName = list.get(0).firstName;
		String lastName = list.get(0).lastName;
		System.out.println("SSN is " + ssn);
		System.out.println("First name is " + firstName);
		System.out.println("Last name is " + lastName);
		uInfo.fName= firstName;
		uInfo.lName = lastName;
		uInfo.ssn = ssn;

		//Create Subject based on the SSN, First name and last name passed
		reusable.createSubject(ssn, firstName, lastName,"1983","Oct","20","United States");
		reusable.addCitizenshipForSubject(ssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		
		// Log out of CATS user "1"
		catsLogout();
		
		// Log in to JVS as 1
		jvsreusable.loginToJVS(securityOfficerID);

		// Create SMO relationship between the case and the SO
		jvsreusable.createSMORelationship(categoryType, ssn);

		// Log out of JVS
		jvsreusable.jvsLogout();
		
		// Log in to CATS UAT as user 1 
		reusable.loginToCATS(executiveChiefID);

		// Create case for the subject
		reusable.createCaseForSubject(ssn, divisionName, caseType, smoName, investigationType);
		WaitingToLoad();

		// Get the case ID of the cases created
		String caseID = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println(caseID);
		
		//Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication =com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']"));
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

		// Log out of CATS user 1

		


		uInfo.caseID = caseID;
		List<SubjectInfo> returnList = new ArrayList<OtherAgencyFilesRFA.SubjectInfo>();
		returnList.add(uInfo);
		//log out of CATS
		catsLogout();
		return returnList;

	
	

	}
	public void rfaProcessTeamFlow(String firstName, String lastName,String documentType, String docname ) {

		// Click on the task inbox link
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));
		
		//click on name filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='majorTabPanel:SubTabletasks:subjectLastName:filter']"));
		
		//enter name to filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:SubTabletasks:subjectLastName:filter']"), firstName+" "+lastName);

		// Select the task
		ReusableFunctions.waitAndSelectCellFromTableAndClickFirstLink(firstName + " " + lastName, "//*[@id='majorTabPanel:SubTabletasks']");

	

		// Click upload document button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Upload Document");

		// Calls the method docUpload to upload the document from system file
		reusable.docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), "//div[@id='documentUploaderForm:editAddDocumentMessagesDocType_panel']//li[text()='"+documentType+"']", docname);



		// Click Send button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Send");
		WaitingToLoad();

		// Verify that the growl message seen contains the text specified
		//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "closed");
		pause(30);

	}

	

	public void rfaProcessTeamClaim(String firstName, String lastName) {

		// Click on the task inbox link
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));

		// click on name filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[@id='majorTabPanel:SubTabletasks:subjectLastName:filter']"));

		// enter name to filter
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[@id='majorTabPanel:SubTabletasks:subjectLastName:filter']"), firstName + " " + lastName);

		// Select the task
		ReusableFunctions.waitAndSelectCellFromTableAndClickFirstLink(firstName + " " + lastName, "//*[@id='majorTabPanel:SubTabletasks']");

		// Click claim button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Claim");

		// Verify that the growl message seen contains the text specified
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "claimed");
		pause(10);
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:taskDetailFormId:closeRFAOA']"));
	}

	public void sendRFAforotherAgencyFile(String caseID, String rfaName, String docType, String docName, String docDescription) throws Exception {
		reusable.searchCase(caseID);
		// Verify that the adjudication phase is Determination or Determination
		// Review
		ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//td[preceding-sibling::td/label[contains(text(),'Adjudication Phase')]]/span[text()='Determination' or text()='Determination Review']"));
		String adjPhase = ReusableFunctions.waitAndGetText(By.xpath("//span[@id='majorTabPanel:caseDetailsPanel:caseDetermination']"));
		System.out.println("Adjudication Phase is" + adjPhase);
		reusable.sendRFA(caseID, rfaName, docType, docName, docDescription);

	}

	public void verifyNotification(String firstName, String lastName, String notificationText, String caseID) throws Exception {
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Unread Notifications"));

		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[preceding-sibling::span[contains(text(),'Details')] and contains(@id,'filter')]"));

		String text = "Request for Other Agency Files: (CASE ID: " + caseID;
		ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[preceding-sibling::span[contains(text(),'Details')] and contains(@id,'filter')]"), text);
		ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//div[@id='majorTabPanel:Notifications']//td[preceding-sibling::td[contains(text(),'RFA Notification')] and contains(text(),'Subject: "+ firstName +" "+ lastName + "). "+ notificationText +"')]"));

	}

}
