package com.iworks.DISS.test.catsRegression.rfas;

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
 * This Test script will 
 * @author vshivaraman 
 */

public class MedicalEvaluationRFA extends TestBase {
	
	private String executiveChiefID = null;
	private static final String DOCUMENT_TYPE = "Request for Medical Evaluation";
	private static final String RFA_NAME = "Medical Evaluation Request";
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
	public  Class<? extends MedicalEvaluationRFA> clazz = this.getClass();

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
	public void verifyMedicalEvaluationRFAForCaseWithEligibility() throws Exception {
	List<SubjectInfo> list = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(),CATSProperties.CivilianMPCCode.getProperty());
	String ssn = list.get(0).ssn;
	String caseID = list.get(0).caseID;
	String firstName= list.get(0).fName;
	String lastName = list.get(0).lName;
	//log in to CATS as adjudicator
	reusable.loginToCATS(adjudicatorID);
	reusable.generateRequestforLetter(caseID,DOCUMENT_TYPE);
	reusable.initiateRequestforRFA(RFA_NAME);
	String[] rfaFields = {"Request for Medical Evaluation","Subject Details","First Name:","Middle Name:","Last Name:","SSN","Eligibility","SMO","Case Detail","Case ID","Requesting SMO", "Case Type","RFA Documents","Adjudicator","Owning SMO"};
	
	reusable.verifyRFAPopUpInfo(rfaFields);
	
	String rfaErrorMessage="Provide Request for Medical Evaluation Letter document.";
	String[] errormessages = {"Document required","Document name is required","Document type is required"};
	reusable.verifyRFAPopUpErrorMessages(rfaErrorMessage,errormessages);
	sendRFAforMedicalEvaluation(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Medical Evaluation Letter", "Descripttion:Request for Medical Evaluation Letter");
	
	reusable.verifyRFACancel(caseID,DOCUMENT_TYPE);
	
	sendRFAforMedicalEvaluation(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Medical Evaluation Letter", "Descripttion:Request for Medical Evaluation Letter");

	
	catsLogout();
	
	jvsreusable.loginToJVS(securityOfficerID);
	
	reusable.claimTask(firstName,lastName);
	// Timer is set to 2 mins so wait for 2 min and 30 sec to verify timer expired notifications
	pause(150);
	verifyNotification(firstName,lastName,"Acknowledgement due date expired.",caseID);
	jvsreusable.logout();
	reusable.loginToCATS(adjudicatorID);
	verifyNotification(firstName,lastName,"Acknowledgement due date expired.",caseID);
	sendRFAforMedicalEvaluation(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Medical Evaluation Letter", "Descripttion:Request for Medical Evaluation Letter");
	catsLogout();
	jvsreusable.loginToJVS(securityOfficerID);
	reusable.claimTask(firstName,lastName);
	verifyrequestExtensionErrorMessage("Extension Justification is required for Request Extension");
	// acknowledment due date extention request- is set to 2 mins so with in 2 mins send acknowledment
	requestExtension();
	jvsreusable.logout();
	reusable.loginToCATS(adjudicatorID);
	verifyNotification(firstName,lastName,"Acknowledgement extension requested ",caseID);
	catsLogout();
	
	jvsreusable.loginToJVS(securityOfficerID);
	sendAcknowledgement(firstName,lastName);
	verifyrequestExtensionErrorMessage("Extension Justification is required for Request Extension");
	requestExtension();
	jvsreusable.logout();
	reusable.loginToCATS(adjudicatorID);
	verifyNotification(firstName,lastName,"Response extension requested ",caseID);
	catsLogout();
	jvsreusable.loginToJVS(securityOfficerID);
	sendResponse(firstName,lastName);
	jvsreusable.logout();

	}

	/**
	 * This method will create test data to test Medical Evaluation RFA functionalities for a case which has  NO existing eligibility
	 * @throws Exception
	 */
	@Test
	public void verifyMedicalEvaluationRFAForCaseWithoutEligibility() throws Exception {
	List<SubjectInfo> list = createTestData1(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(),CATSProperties.CivilianMPCCode.getProperty());
	String ssn = list.get(0).ssn;
	String caseID = list.get(0).caseID;
	String firstName= list.get(0).fName;
	String lastName = list.get(0).lName;
	//log in to CATS as adjudicator
	reusable.loginToCATS(adjudicatorID);
	reusable.generateRequestforLetter(caseID,DOCUMENT_TYPE);
	reusable.initiateRequestforRFA(RFA_NAME);
	String[] rfaFields = {"Request for Medical Evaluation","Subject Details","First Name:","Middle Name:","Last Name:","SSN","Eligibility","SMO","Case Detail","Case ID","Requesting SMO", "Case Type","RFA Documents","Adjudicator","Owning SMO"};
	
	reusable.verifyRFAPopUpInfo(rfaFields);
	
	String rfaErrorMessage="Provide Request for Medical Evaluation Letter document.";
	String[] errormessages = {"Document required","Document name is required","Document type is required"};
	reusable.verifyRFAPopUpErrorMessages(rfaErrorMessage,errormessages);
	sendRFAforMedicalEvaluation(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Medical Evaluation Letter", "Descripttion:Request for Medical Evaluation Letter");
	
	reusable.verifyRFACancel(caseID,DOCUMENT_TYPE);
	
	sendRFAforMedicalEvaluation(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Medical Evaluation Letter", "Descripttion:Request for Medical Evaluation Letter");

	
	catsLogout();
	
	jvsreusable.loginToJVS(securityOfficerID);
	
	reusable.claimTask(firstName,lastName);
	// Timer is set to 2 mins so wait for 2 min and 30 sec to verify timer expired notifications
	pause(150);
	verifyNotification(firstName,lastName,"Acknowledgement due date expired.",list.get(0).caseID);
	jvsreusable.logout();
	reusable.loginToCATS(adjudicatorID);
	verifyNotification(firstName,lastName,"Acknowledgement due date expired.",list.get(0).caseID);
	sendRFAforMedicalEvaluation(caseID, RFA_NAME, DOCUMENT_TYPE, "Test Request for Medical Evaluation Letter", "Descripttion:Request for Medical Evaluation Letter");
	catsLogout();
	jvsreusable.loginToJVS(securityOfficerID);
	reusable.claimTask(firstName,lastName);
	verifyrequestExtensionErrorMessage("Extension Justification is required for Request Extension");
	// acknowledment due date extention request- is set to 2 mins so with in 2 mins send acknowledment
	requestExtension();
	jvsreusable.logout();
	reusable.loginToCATS(adjudicatorID);
	verifyNotification(firstName,lastName,"Acknowledgement extension requested ",caseID);
	catsLogout();
	
	jvsreusable.loginToJVS(securityOfficerID);
	sendAcknowledgement(firstName,lastName);
	verifyrequestExtensionErrorMessage("Extension Justification is required for Request Extension");
	requestExtension();
	jvsreusable.logout();
	reusable.loginToCATS(adjudicatorID);
	verifyNotification(firstName,lastName,"Response extension requested ",caseID);
	catsLogout();
	jvsreusable.loginToJVS(securityOfficerID);
	sendResponse(firstName,lastName);
	jvsreusable.logout();
	}
	
	/**
	 * This method will create the test data 
	 * for Medical evaluation RFA test cases for subject with exisitng eligibility 
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
		reusable.manageTemplates("Request for Medical Evaluation Letter Template", OR.getProperty("filePathMedEvalReqTemp"));

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

		// logout as 1
		catsLogout();

		// log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn1 = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn1, "//*[@id='majorTabPanel:SubTable']");

		// Click on Files Review tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewTAb.getProperty()));

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewSave.getProperty()), 500);

		// verify that growl message is seen
		pause(5);

		// Click on Guidelines Tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Guidelinestab.getProperty()));

		// Expand on a condition panel
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(conditionPanel));
		pause(5);

		// Select a disqualifying reason
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(disqualifyingCondition));
		pause(5);

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelineOutputPanelId']//button[contains(.,'Save')]"));

		// verify that growl message is seen
		pause(15);

		// Select the MPC code drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.MPCCodedropDown.getProperty()));

		// Select MPC code from list
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(codeMCP));

		// verify that growl message is seen
		pause(5);
		uInfo.caseID = caseID1;
		List<SubjectInfo> returnList = new ArrayList<MedicalEvaluationRFA.SubjectInfo>();
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
		reusable.manageTemplates("Request for Medical Evaluation Letter Template", OR.getProperty("filePathMedEvalReqTemp"));

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
		catsLogout();
		
		//log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn1 = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn1, "//*[@id='majorTabPanel:SubTable']");
		
		// Click on Files Review tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewTAb.getProperty()));

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewSave.getProperty()), 500);

		// verify that growl message is seen
		pause(5);

		// Click on Guidelines Tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Guidelinestab.getProperty()));

		// Expand on a condition panel
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(conditionPanel));
		pause(5);

		// Select a disqualifying reason
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(disqualifyingCondition));
		pause(5);

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:guidelineOutputPanelId']//button[contains(.,'Save')]"));

		// verify that growl message is seen
		pause(15);

		// Select the MPC code drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.MPCCodedropDown.getProperty()));

		// Select MPC code from list
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(codeMCP));

		// verify that growl message is seen
		pause(10);

		uInfo.caseID = caseID;
		List<SubjectInfo> returnList = new ArrayList<MedicalEvaluationRFA.SubjectInfo>();
		returnList.add(uInfo);
		//log out of CATS
		catsLogout();
		return returnList;

	
	

	}
public void generateRequestforMedicalEvaluationLetter(String caseID, String letterName) throws Exception {
	System.out.println("Test: Verify Generate Request for Medical Evaluation Letter functionality \n");
	reusable.generateLetter(caseID,letterName);
	System.out.println("Result: Generate Request for Medical Evaluation Letter functionality was successful- TEST PASS \n");
}

public void verifyRFAPopUpInfo()throws Exception {
	System.out.println("Tets:Evaluation Request RFA pop up- TC \n");
	String[] rfaFields = {"Request for Medical Evaluation","Subject Details","First Name:","Middle Name:","Last Name:","SSN","Eligibility","SMO","Case Detail","Case ID","Requesting SMO", "Case Type","RFA Documents","Adjudicator","Owning SMO"};
	for (String rfaField : rfaFields) {
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='RFARequestForm']", rfaField);
	}
	System.out.println("Result:Evaluation Request RFA pop up-TC- TEST PASS \n");
	
}
public void verifyRFAPopUpErrorMessages() throws Exception{
	System.out.println("Test:Evaluation Request letter not attached \n");
	//click on send 
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='RFARequestForm:sendRFA']"));
	ReusableFunctions.waitAndVerify_IfContains("//div[@id='RFARequestForm:RFARequestFor']//div[contains(@class,'ui-messages-error')]", "Provide Request for Medical Evaluation Letter document.");
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[text()='Upload Document']"));
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='documentUploaderForm:documentUploadAddButtonId']"));
	String[] errormessages = {"Document required","Document name is required","Document type is required"};
	for (String errorMessage : errormessages) {
		ReusableFunctions.waitAndVerify_IfContains("//div[@id='documentUploaderForm:documentUploaderFormMessagesId']//li", errorMessage);
	}
	System.out.println("Result:Evaluation Request letter not attached-Successful- error message is seen correctly-TEST PASS");
	
	//click cancel
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='documentUploaderForm:documentUploadCancelButtonId']"));
	//click on close window
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='RFARequestForm:closeWindowId']"));
	//click on the review tab
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Review"));
	
}

public void sendRFAforMedicalEvaluation(String caseID, String rfaName, String docType,String docName,String docDescription)throws Exception{
	reusable.searchCase(caseID);
	//Verify that the adjudication phase is Determination or Determination Review
	ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//td[preceding-sibling::td/label[contains(text(),'Adjudication Phase')]]/span[text()='Determination' or text()='Determination Review']"));
	String adjPhase = ReusableFunctions.waitAndGetText(By.xpath("//span[@id='majorTabPanel:caseDetailsPanel:caseDetermination']"));
	System.out.println("Adjudication Phase is"+ adjPhase);
	reusable.sendRFA(caseID, rfaName, docType, docName, docDescription);

	
	
	
}
public void initiateRequestforMedicalEvaluation(String rfaName)throws Exception{
	System.out.println("Test:Initiate Request for Medical Evaluation Functionality \n");
	
	
	//click on the RFA tab
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFAs"));
	
	pause(5);
	
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:subjtabview1:dynaButton_button']"));
	
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[contains(@id,'rfaMenu')]/span[contains(text(),'"+rfaName+"')]"));
	System.out.println("Result:Initiate Request for Medical Evaluation functionality succesful- TEST PASS \n");
}
public void verifyRFACancel(String caseID)throws Exception{
	
	reusable.searchCase(caseID);
	//click RFA tab
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RFAs"));
	//Click on the row with the RFA
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//tbody[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailRFATableId_data']//td/span[text()='Request for Medical Evaluation']"));
	
	//Click on Cancel req
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='RFARequestForm:cancelRFA']"));
	
	//
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='cancelRfaConfirmYes']"));
	
	pause(10);
	
	
	reusable.searchCase(caseID);
	
	//
	String status =ReusableFunctions.waitAndGetText(By.xpath("//td[preceding-sibling::td/span[contains(text(),'Request for Medical Evaluation')]]/span"));
	
	if(status.equalsIgnoreCase("Canceled")) {
		System.out.println("Request for RFA has been Canceled- Test Pass \n");
	}
	else {
		System.out.println("Request for RFA was NOT canceled- TEST FAIL \n");
	}
	
	
}
public void claimTask(String firstName,String lastName) throws Exception{
	//click on task inbox
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));
	// Select the task
	String name = firstName + " " + lastName;
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[following-sibling::td/span[contains(text(),'"+name+"')]]/span"));

	// Click claim button
	com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,'PortalClaim')]"));

	// Verify the growl message is seen
	//com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "claimed");
	pause(5);

}
public void verifyNotification(String firstName,String lastName, String notificationText, String caseID)throws Exception{
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Unread Notifications"));
	System.out.println("Test: Verify timer expired notification is seen \n");
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//input[preceding-sibling::span[contains(text(),'Details')] and contains(@id,'filter')]"));
	String text = "Request for Medical Evaluation: (CASE ID: "+caseID;
	ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//input[preceding-sibling::span[contains(text(),'Details')] and contains(@id,'filter')]"),text);
	ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//div[@id='majorTabPanel:Notifications']//td[preceding-sibling::td[contains(text(),'RFA Notification')] and contains(text(),'Subject: "+firstName+" "+lastName+"). "+notificationText+"')]"));
	System.out.println("Result:Timer expired notification is see.- TEST PASS \n");
}

public void requestExtension()throws Exception{
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,':medEvalRequestExtId')]"));
	//verify the error message is seen
	
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[preceding-sibling::td[contains(text(),'Extension Justification')]]/textarea"));
	ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//td[preceding-sibling::td[contains(text(),'Extension Justification')]]/textarea"),"test");
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,':medEvalRequestExtId')]"));
}
public void verifyrequestExtensionErrorMessage(String erroMessage)throws Exception{
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,':medEvalRequestExtId')]"));
	
	//verify the error message is seen
	ReusableFunctions.waitAndVerify_IfContains("//div[contains(@id,'rfaMedEvalSecurityOfficerErrorMessagesID')]//li/span]", erroMessage);
	
	
	
}
public void sendAcknowledgement(String firstName,String lastName)throws Exception{
	//click on task inbox
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));
	// Select the task
	String name = firstName + " " + lastName;
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[following-sibling::td/span[contains(text(),'"+name+"')]]/span"));
	
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[contains(text(),'Upload Document')]"));
	// Call document upload method to upload file
	reusable.docUpload(JVSProperties.InputField.getProperty(), OR.getProperty("filePath"), JVSProperties.DocTypeAcknowledgementReceipt.getProperty(), "Acknowledgement");

	// Click Acknowledge date button
	com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.DatePickerIcon.getProperty()));

	// call Select todays date function to pick todays date in the calendar
	String date = reuse.selectTodaysDate();

	// Select todays date in the calendar
	com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='ui-datepicker-div']//a[text()='"+ date +"']"));
	
	//click save
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,':medEvalPortalSave')]"));
	
	


}
public void sendResponse(String firstName,String lastName)throws Exception{
	//click on task inbox
	ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Task Inbox"));
	// Select the task
	String name = firstName + " " + lastName;
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[following-sibling::td/span[contains(text(),'"+name+"')]]/span"));
	
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button/span[contains(text(),'Upload Document')]"));
	// Call document upload method to upload file
	reusable.docUpload(JVSProperties.InputField.getProperty(), OR.getProperty("filePath"), JVSProperties.DocTypeRFAMedEvalResponse.getProperty(), "RFA Med Evaluation Response");

	//click send  
	ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,':medEvalPortalSubmit')]"));
	
	


}

}
