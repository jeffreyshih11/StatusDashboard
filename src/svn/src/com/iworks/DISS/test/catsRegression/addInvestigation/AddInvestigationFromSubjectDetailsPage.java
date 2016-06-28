package com.iworks.DISS.test.catsRegression.addInvestigation;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iworks.DISS.test.common.functions.CATSReusuableFunctions;
import com.iworks.DISS.test.common.functions.JVSReusableFunctions;
import com.iworks.DISS.test.common.functions.ReusableFunctions;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.TestBase;

/**
 * This Test script will add a new investigation from Subject details page as
 * different users with permission to add investigation. It will verify that the
 * investigation is added to the DB tables and in UI tables.
 * 
 * @author vshivaraman
 */

public class AddInvestigationFromSubjectDetailsPage extends TestBase {
	private  String adjudicatorID = null; 
	private  String investigationType = null;
	private  String smoName = null; 
	private  String divisionName = null; 
	private  String executiveChiefID = null;
	private  String categoryType = null;
	private String securityOfficerID = null;

	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	public Class<? extends AddInvestigationFromSubjectDetailsPage> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
	
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className, name);
		
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
	//	AddInvestigationFromSubjectDetailsPage test = new AddInvestigationFromSubjectDetailsPage();
	

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
	 * This method will test the Add investigation functionalities from subject
	 * details page
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddInvestigationFromSubjectDetailsPage() throws Exception {
		
		//Create test data
		System.out.println("Create Test data for the tests \n");
		String ssn = createTestData(CATSProperties.CaseTypeSecret.getProperty(), CATSProperties.LevelCodeSecret.getProperty());
		System.out.println("Test data for the tests created.\n");
		

		// Verify test case 23320 - Cancel option for Add investigation from
		// subject details page
		System.out.println("Test: Verify test case 23320 and verify that the capncel option is functional and no investigation is added if cancel is selected \n");
		reusable.loginToCATS(executiveChiefID);
		reusable.searchSubjectCATS(ssn);
		addInvestigationAndCancelSubjectAction("SSBI", "2015", "Oct", "10", "CIA", ssn, "Add Investigation", "Other");
		System.out.println("Result: Test case 23320-Test PASS - No investigation is added when cancel option is selected \n");
		

		// Verify test case 23322- Verify that the error message is seen when
		// user tries to add investigation without required fields populated.
		System.out.println("Test: Verify test case 23322 and Verify that the error message is seen when user tries to add investigation without required fields populated. \n");
		addInvestigationErrorMessageSubjectAction(ssn);
		System.out.println("Result: Test case 23322-Test PASS \n");
		

		// Verify test case 23311,23315 from subject details page and verify
		// that the growl message is seen -TC 23318- Add investigation
		// document-TC 23325
		System.out.println("Test: Verify test case 23311 and verify that the growl message is seen -TC 23318- Add investigation document-TC 23325 \n");
		reusable.searchSubjectCATS(ssn);
		reusable.addInvestigationSubjectAction("SSBI", "2015", "Oct", "10", "CIA", ssn, "Add Investigation", "Other");
		System.out.println("Result: Test case 23311-Test PASS ,TC 23318- Test PASS, TC 23325- Test PASS");
		

		// Verify that the investigation is added in DB-TC 23311
		System.out.println("Test: Verify that the investigation is added in DB-TC 23311 \n");
		String query = "select INV_ID from CATS.INV where DISS_SUBJECT_ID=(Select DISS_SUBJECT_ID from JVS.SUBJECT where ssn=" + ssn + ")";
		String investigationID = com.iworks.DISS.test.common.functions.ReusableFunctions.sqlQueryToList("select", query, CONFIG.getProperty("Environment"), "cats", "INV_ID");
		System.out.println("Investigation with investigation id as " + investigationID + " for the subject with the SSN " + ssn + " is seen correctly. \n");
		System.out.println("Result:The investigation is added to DB-TC 23311- Test PASS \n");
		

		// view investigation details in subject details page investigation
		// section-TC 23312
		System.out.println("Test: View investigation details in subject details page investigation section-TC 23312 \n");
		viewInvestigation(ssn, ssn);
		System.out.println("Result: Investigation details is verified in subject details page>investigation section-TC 23312- Test PASS \n");
		

		// Update investigation- TC 23324
		System.out.println("Test: Update investigation- TC 23324 \n");
		String agencyCaseNumberNew = ssn + "2";
		reusable.updateInvestigationFromSubjectDetails(ssn, ssn, "NACLC", "2014", "Jan", "10", "FBI", agencyCaseNumberNew);
		System.out.println("Result: Investigation updated correctly and verified- TC 23324- Test PASS \n");
		

		// Add investigation Document- TC 23325
		System.out.println("Test: Add investigation Document- TC 23325 \n");
		String docName = "NewDocument";
		String docDescription = "NewDescription";
		String docType = "SF-85";
		reusable.addInvestigationDocumentToExistingInnvestigationSubjectDetailsPage(ssn, agencyCaseNumberNew, docType, docName, docDescription);
		System.out.println("Result: Investigation Document added successfuly- TC 23325- Test PASS \n");
		

		// View and edit investigation document- TC 23326
		System.out.println("Test: View investigation document- TC 23326 \n");
		viewInvestigationDoc(ssn, agencyCaseNumberNew, docName);
		System.out.println("Result: Investigation document was verified- TC 23326- Test PASS \n");
		

		System.out.println("Test: Edit investigation document- TC 23326 \n");
		String newDocName = "updatedname";
		String newDocDescription = "updatedDescription";
		String newDocType = "Other";
		reusable.editInvestigationDoc(ssn, agencyCaseNumberNew, docName, newDocName, docDescription, newDocDescription, docType, newDocType);
		System.out.println("Result: Investigation document edited and saved successfuly- TC 23326- Test PASS \n");
		

	}

	/**
	 * This method will test the cancel investigation function of Add
	 * investigation
	 * 
	 * @param invType
	 * @param yearFourdigit
	 * @param month3Char
	 * @param closeDate
	 * @param invAgency
	 * @param agencyCaseNumber
	 * @param docName
	 * @param docType
	 */
	public void addInvestigationAndCancelSubjectAction(String invType, String yearFourdigit, String month3Char, String closeDate, String invAgency, String agencyCaseNumber, String docName, String docType) {

		// click on the subject action button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:subjtabview:subactions_button']"));// "//*[@id='majorTabPanel:subjtabview:subactions_button']"

		// Select Add investigation option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:subjtabview:subActionsMenu']//a/span[text()='Add New Investigation']"));

		// Click on the label for investigation type
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='addInvestigationForm:addInvestigationTypeId_label']"));

		// click at the investigation type option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='addInvestigationForm:addInvestigationTypeId_panel']//li[text()='"+ invType +"']"));
		// click on the close date calendar
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//span[@id='addInvestigationForm:addInvestigationCloseDateId']/button"));
		// Select year
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[2]/option[@value='"+ yearFourdigit +"']"));

		// Select the month
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='ui-datepicker-div']/div/div/select[1]/option[text()='"+ month3Char +"']"));

		// Select date
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[text()='"+ closeDate +"']"));

		// Click on the investigation agency drop down
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='addInvestigationForm:addInvestigationAgencyId_label']"));

		// select the investigation agency option in the list
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='addInvestigationForm:addInvestigationAgencyId_panel']//li[text()='"+ invAgency +"']"));

		// click on the agency case number text box
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//form[@id='addInvestigationForm']//td[preceding-sibling::td/label[text()='*Agency Case Number']]/input"));

		// enter the agency case number
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//form[@id='addInvestigationForm']//td[preceding-sibling::td/label[text()='*Agency Case Number']]/input"), agencyCaseNumber);

		// click on Add document button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Add Document");

		// call the doc upload function
		reusable.docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), docType, "For Add Investigation", "test");

		// click on the cancel button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='addInvestigationForm:addInvestigationCancelButtonId']"));

		// Verify investigation is not added to investigation section
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyIfNotContains("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']//table", agencyCaseNumber);

		System.out.println("Result:Investigation with agency case number  " + agencyCaseNumber + " is not seen in teh investigation section. Hence Test pass \n");

	}

	public void addInvestigationErrorMessageSubjectAction(String ssn) {
		reusable.searchSubjectCATS(ssn);

		// click on the subject action button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:subjtabview:subactions_button']"));// "//*[@id='majorTabPanel:subjtabview:subactions_button']"

		// Select Add investigation option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:subjtabview:subActionsMenu']//a/span[text()='Add New Investigation']"));

		// click on the add investigation button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Add New Investigation");

		// Verify that the error message is seen
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='addInvestigationForm:addInvestigationFormMessagesId']", "Investigation Type is required");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='addInvestigationForm:addInvestigationFormMessagesId']", "Investigation Agency is required");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='addInvestigationForm:addInvestigationFormMessagesId']", "Investigation Close Date is required");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='addInvestigationForm:addInvestigationFormMessagesId']", "Agency Case Number is required");
		// click on the cancel button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='addInvestigationForm:addInvestigationCancelButtonId']"));
		pause(2);
	}

	public void viewInvestigation(String ssn, String agencyCaseNumber) {
		reusable.searchSubjectCATS(ssn);
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:subjtabview:subjhometab']", "Investigation");
		// Verify the Investigation section has the following columns
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']", "Investigation Type");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']", "Investigation Status");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']", "Investigating Agency");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']", "Close Date");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']", "Agency Case Number");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyIfElementExists(By.xpath("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']"), By.xpath("//button[@title='Update Investigation']"));

		WebElement element = ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//td[7]/div"));
		String elementclass = element.getAttribute("title");
		System.out.println("Row expand Element with title " + elementclass + " is seen \n");

	}

	public void viewInvestigationDoc(String ssn, String agencyCaseNumber, String docName) {
		// Search for the subject
		reusable.searchSubjectCATS(ssn);

		// Expand the row of the specific agency case number
		ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//tbody[@id='majorTabPanel:subjtabview:subjectInvestigations_data']", By.tagName("div"));

		// Verify if the table has the doc
		ReusableFunctions.waitAndVerify_IfContains("//div[contains(@id,':investigationDocumentDetail')]", docName);

	}

	/**
	 * This method will create the test data for Add investigation from subject
	 * details page functionalities
	 * 
	 * @param caseType
	 * @param caseType1
	 * @param levelCodeOption
	 * @return
	 * @throws Exception
	 */
	public String createTestData(String caseType, String levelCodeOption) throws Exception {

		// Log in to CATS UAT as user 1
		reusable.loginToCATS(executiveChiefID);

		// Call the ssnNameGenerator method to set First name,last name and SSN
		// based on baseSSN and index
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("autoFName", "autoLName");
		String ssn = list.get(0).ssn;
		String firstName = list.get(0).firstName;
		String lastName = list.get(0).lastName;
		System.out.println("SSN is " + ssn);
		System.out.println("First name is " + firstName);
		System.out.println("Last name is " + lastName);

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
		System.out.println("First case ID is : " + caseID +"\n");

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

		return ssn;

	}

	/**
	 * This method will create the test data for Add investigation from subject
	 * details page functionalities
	 * 
	 * @param caseType
	 * @return
	 * @throws Exception
	 */
	public String createTestData1(String caseType, String levelCodeOption) throws Exception {

		// Log in to CATS UAT as user 1
		reusable.loginToCATS(executiveChiefID);

		// Call the ssnNameGenerator method to set First name,last name and SSN
		// based on baseSSN and index
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("autoFName", "autoLName");
		String ssn = list.get(0).ssn;
		String firstName = list.get(0).firstName;
		String lastName = list.get(0).lastName;
		System.out.println("SSN is " + ssn);
		System.out.println("First name is " + firstName);
		System.out.println("Last name is " + lastName);

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
		System.out.println(caseID+"\n");

		// Log out of CATS user 1
		catsLogout();

		return ssn;

	}

}