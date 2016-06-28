package com.iworks.DISS.test.catsRegression.addInvestigation;

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
import org.openqa.selenium.WebElement;

import com.iworks.DISS.test.common.functions.CATSReusuableFunctions;
import com.iworks.DISS.test.common.functions.JVSReusableFunctions;
import com.iworks.DISS.test.common.functions.ReusableFunctions;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.TestBase;

/**
 * This Test script will add a new investigation from Case details page as
 * different users with permission to add investigation, update investigation
 * and add and update documents. It will verify that the investigation is added
 * to the DB tables and in UI tables.
 * 
 * @author vshivaraman
 */

public class AddInvestigationFromCaseDetailsPage extends TestBase {

	private  String adjudicatorID = null; 
	private  String investigationType = null;
	private  String smoName = null; 
	private  String divisionName = null; 
	private  String reviewerID = null;
	private  String reviewerFName = null;
	private  String executiveChiefID = null;
	private String securityOfficerID= null;
	private  String categoryType = null;//JVSProperties.CivilianRetiree.getProperty();
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	public Class<? extends AddInvestigationFromCaseDetailsPage> clazz = this.getClass();

	public class UserInfo {
		public String ssn;
		public String caseID;
	}

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
		reviewerID = PreconditionVariables.getProperty("reviewerID");
		System.out.println("Reviewer ID : "+reviewerID);
		reviewerFName = PreconditionVariables.getProperty("reviewerFName");
		System.out.println("Reviewer First name : "+reviewerFName);
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
	 * This method will create test data for Add investigation functionality
	 * from case details page and verify that the investigation is added
	 * ,displayed , edited correctly.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddInvestigationFromCaseDetailsPage() throws Exception {

		System.out.println("Create Test data for the tests");
		System.out.println();
		List<UserInfo> list = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeSCI.getProperty());
		String ssn = list.get(0).ssn;
		String caseID = list.get(0).caseID;
		System.out.println("Test data for the tests created.");
		System.out.println();

		// Verify test case 23320 - Cancel option for Add investigation from
		// Case details page
		System.out.println("Test: Verify test case 23320 and verify that the capncel option is functional and no investigation is added if cancel is selected");
		System.out.println();
		reusable.loginToCATS(adjudicatorID);
		reusable.searchCase(caseID);
		pause(2);
		addInvestigationAndCancelCaseDetailsInvestigationAction("SSBI", "2015", "Oct", "10", "CIA", ssn, "Add Investigation", "Other");
		System.out.println("Result: Test case 23320-Test PASS - No investigation is added when cancel option is selected");
		System.out.println();

		// Verify test case 23322- Verify that the error message is seen when
		// user tries to add investigation without required fields populated.
		System.out.println("Test: Verify test case 23322 and Verify that the error message is seen when user tries to add investigation without required fields populated.");
		System.out.println();
		addInvestigationErrorMessageInvestigationAction(caseID);
		System.out.println("Result: Test case 23322-Test PASS");
		System.out.println();

		// Verify TC 23316
		System.out.println("Test: Verify TC 23316 -(New to CATS-2027) - Case Details: Add Existing Investigation Drop Down");
		addExistingInvestigationNoExisting(caseID);
		System.out.println("Result: TC 23316- Test PASS");

		// Verify TC 23323
		System.out.println("Verify TC 23323-Add Existing Investigation Error Message");
		addExistingInvestigationErrorMessage(caseID);
		System.out.println("Result: TC 23323 -Test PASS");

		// verify TC 23321
		reusable.searchSubjectCATS(ssn);
		pause(5);
		reusable.addInvestigationSubjectAction("NACLC", "2015", "Jan", "20", "FBI", "1234", "test", "Other");
		System.out.println("Test: Verify TC 23321-Add Existing Investigation Cancel Option ");

		addExistingInvestigationCancelOption(caseID, "NACLC - 2015-01-20 (1234)", "NACLC");
		System.out.println("Result: TC23321- Test PASS");

		// Verify TC 23317, 23319
		System.out.println("Test: Verify TC 23317 -(New to CATS-2027) - Case Details: Add Existing Investigation Information and verify that the confirmation growl message is seen(23319) ");
		reusable.addExistingInvestigation(caseID, "NACLC - 2015-01-20 (1234)");
		System.out.println("Result: TC 23317 and 23319- Test PASS");

		// Verify test case 23314,23315 from case details page and verify that
		// the growl message is seen -TC 23318- Add investigation document-TC
		// 23325
		System.out.println("Test: Verify test case 23314 and verify that the growl message is seen -TC 23318- Add investigation document-TC 23325");
		System.out.println();
		reusable.searchCase(caseID);
		pause(5);
		reusable.addInvestigationCaseDetailsInvestigationAction("SSBI", "2015", "Oct", "10", "CIA", ssn, "Add Investigation", "Other");
		System.out.println("Result: Test case 23314-Test PASS ,TC 23318- Test PASS, TC 23325- Test PASS");
		System.out.println();

		// Verify that the investigation is added in DB-TC 23314
		System.out.println("Test: Verify that the investigation is added in DB-TC 23314");
		System.out.println();
		String query = "select INV_ID from CATS.INV where DISS_SUBJECT_ID=(Select DISS_SUBJECT_ID from JVS.SUBJECT where ssn=" + ssn + ")";
		String investigationID = com.iworks.DISS.test.common.functions.ReusableFunctions.sqlQueryToList("select", query, CONFIG.getProperty("Environment"), "cats", "INV_ID");
		System.out.println("Investigation with investigation id as " + investigationID + " for the subject with the SSN " + ssn + " is seen correctly.");
		System.out.println();
		System.out.println("Result:The investigation is added to DB-TC 23314- Test PASS");
		System.out.println();

		// view investigation details in subject details page investigation
		// section-TC 23313
		System.out.println("Test: View investigation details in Case details page investigation section-TC 23313");
		System.out.println();
		viewInvestigation(caseID, ssn);
		System.out.println("Result: Investigation details is verified in case details page>investigation section-TC 23313- Test PASS");
		System.out.println();

		// Update investigation- TC 23324
		System.out.println("Test: Update investigation- TC 23324");
		System.out.println();
		String agencyCaseNumberNew = ssn + "2";
		reusable.updateInvestigationFromCaseDetails(caseID, "1234", "NACLC", "2014", "Jan", "10", "CIA", agencyCaseNumberNew);
		System.out.println("Result: Investigation updated correctly and verified- TC 23324- Test PASS");
		System.out.println();

		// Add investigation Document- TC 23325
		System.out.println("Test: Add investigation Document- TC 23325");
		System.out.println();
		String docName = "NewDocument";
		String docDescription = "NewDescription";
		String docType = "SF-85";
		reusable.addInvestigationDocumentToExistingInnvestigationCasetDetailsPage(caseID, agencyCaseNumberNew, docType, docName, docDescription);
		System.out.println("Result: Investigation Document added successfuly- TC 23325- Test PASS");
		System.out.println();

		// View and edit investigation document- TC 23326
		System.out.println("Test: View investigation document- TC 23326");
		System.out.println();
		viewInvestigationDoc(caseID, agencyCaseNumberNew, docName);
		System.out.println("Result: Investigation document was verified- TC 23326- Test PASS");
		System.out.println();

		System.out.println("Test: Edit investigation document- TC 23326");
		System.out.println();
		String newDocName = "updatedname";
		String newDocDescription = "updatedDescription";
		String newDocType = "Other";
		reusable.editInvestigationDocCaseDetailsPage(caseID, agencyCaseNumberNew, docName, newDocName, docDescription, newDocDescription, docType, newDocType);
		System.out.println("Result: Investigation document edited and saved successfuly- TC 23326- Test PASS");
		System.out.println();

		// verify 23328
		System.out.println("test: Verify all actions as recorded in History tab-TC 23328");
		verifyHistory(caseID);
		System.out.println("Result: TC 23328 -Test PASS");

		// Verify TC 23327
		// send for review
		System.out.println("Test: Verify TC 23327- reviewer should receive notification when new and existing investigations are added to the case");
		reusable.sendForReview(caseID, "Manually Assign", reviewerFName, "test");
		catsLogout();
		reusable.loginToCATS(executiveChiefID);
		reusable.searchCase(caseID);
		reusable.addInvestigationCaseDetailsInvestigationAction("SSBI", "2015", "Oct", "10", "CIA", ssn, "Add Investigation", "Other");
		reusable.searchSubjectCATS(ssn);
		reusable.addInvestigationSubjectAction("RSI", "2015", "Oct", "10", "FBI", ssn + "3", newDocName, newDocType);
		reusable.addExistingInvestigation(caseID, "RSI - 2015-10-10 ("+ ssn +"3"+")"); // ANACI
																							// -
																							// 2015-10-30
																							// (232323)
		catsLogout();
		
		verifyNotificationReceived(reviewerID, "Investigation Added");
		System.out.println("Result: TC 23327 -Test PASS");

	}

	/**
	 * 
	 * @param userID
	 * @param notification
	 * @throws Exception
	 */
		
	public void verifyNotificationReceived(String userID, String notification) throws Exception {
		reusable.loginToCATS(userID);
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("Unread Notifications"));
		ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:notificationTableId']", notification);
		catsLogout();

	}

	/**
	 * This method will verify that the actions of investigations added and
	 * updated, new and existing, are seen in the History tab correctly
	 * 
	 * @param caseID
	 */
	public void verifyHistory(String caseID) {
		reusable.searchCase(caseID);
		pause(2);
		ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("History"));

		ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId']/div[2]/table", "Investigation Added");
		ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:subjtabview1:caseDetailCaseHistoryTableId']/div[2]/table", "Investigation Updated");
	}

	/**
	 * This method will verify that when the cancel button is selected in the
	 * add existing investigation pop up, no investigation will be added.
	 * 
	 * @param caseID
	 * @param existingInvestigation
	 * @param invType
	 */
	public void addExistingInvestigationCancelOption(String caseID, String existingInvestigation, String invType) {

		reusable.searchCase(caseID);
		pause(2);
		// click on the investigation action button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:InvButton_button']"));// "//*[@id='majorTabPanel:subjtabview:subactions_button']"

		// Select Add existing investigation option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:invActionMenu2']/span[text()='Add Existing Investigation']"));

		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:newInvestigationType']//label[contains(text(),'Select Investigation')]"));//majorTabPanel:caseDetailsPanel:newInvestigationForm:newInvestigationType_label

		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:newInvestigationType_panel']//li[@data-label='"+ existingInvestigation +"']"));////div[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:newInvestigationType_panel']//li[text()='NACLC - 2015-01-20 (1234)']

		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:InvestigationEditCancelButton']"));

		ReusableFunctions.waitAndVerifyIfNotContains("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']/div/table", invType);

	}

	/**
	 * This method will verify that when there is no existing investigation then
	 * no investigation will be added when user selects save in the add existing
	 * investigation pop up
	 * 
	 * @param caseID
	 */
	public void addExistingInvestigationNoExisting(String caseID) {

		reusable.searchCase(caseID);
		pause(2);
		// click on the investigation action button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:InvButton_button']"));// "//*[@id='majorTabPanel:subjtabview:subactions_button']"

		// Select Add investigation option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:invActionMenu2']/span[text()='Add Existing Investigation']"));

		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:newInvestigationType_label']"));
		pause(2);

//		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:newInvestigationType_panel']/div/ul/li"));

		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//form[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm']//button/span[text()='Save']"));

		ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:addExistingInvestigationFormMessagesId']//span", "Please Select Existing Investigation");//majorTabPanel:caseDetailsPanel:newInvestigationForm:addExistingInvestigationFormMessagesId
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//form[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm']//button/span[text()='Cancel']"));

	}

	/**
	 * This method will verify that error message is seen when save is selected
	 * without selecting an existing investigation from the add existing
	 * investigation pop up in the case details page
	 * 
	 * @param caseID
	 */
	public void addExistingInvestigationErrorMessage(String caseID) {

		reusable.searchCase(caseID);
		pause(2);
		// click on the investigation action button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:InvButton_button']"));// "//*[@id='majorTabPanel:subjtabview:subactions_button']"

		// Select Add existing investigation option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:invActionMenu2']/span[text()='Add Existing Investigation']"));


		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//form[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm']//button/span[text()='Save']"));

		ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm:addExistingInvestigationFormMessagesId']//span", "Please Select Existing Investigation");//majorTabPanel:caseDetailsPanel:newInvestigationForm:addExistingInvestigationFormMessagesId
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//form[@id='majorTabPanel:caseDetailsPanel:newInvestigationForm']//button/span[text()='Cancel']"));
	}

	/**
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
	public void addInvestigationAndCancelCaseDetailsInvestigationAction(String invType, String yearFourdigit, String month3Char, String closeDate, String invAgency, String agencyCaseNumber, String docName, String docType) {
		// click on the investigation action button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:InvButton_button']"));// "//*[@id='majorTabPanel:subjtabview:subactions_button']"

		// Select Add new investigation option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:invActionMenu1']"));

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
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='addInvestigationForm:addInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input"));

		// enter the agency case number
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//table[@id='addInvestigationForm:addInvestigationGridId']//td[preceding-sibling::td/label[contains(text(),'*Agency Case Number')]]/input"), agencyCaseNumber);

		// click on Add document button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[@id='addInvestigationForm:addInvestigationGridId']//button/span[contains(text(),'Add Document')]"));

		// call the doc upload function

		reusable.docUpload(CATSProperties.ChooseBtn.getProperty(), OR.getProperty("filePath"), docType, "For Add Investigation", "test");

		// click on the cancel button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='addInvestigationForm:addInvestigationCancelButtonId']"));

		// Verify investigation is not added to investigation section
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyIfNotContains("//div[@id='majorTabPanel:subjtabview:subjectInvestigations']//table", agencyCaseNumber);

		System.out.println("Result:Investigation with agency case number  "+ agencyCaseNumber +" is not seen in teh investigation section. Hence Test pass");

	}

	/**
	 * 
	 * @param caseID
	 */
	public void addInvestigationErrorMessageInvestigationAction(String caseID) {
		reusable.searchCase(caseID);
		pause(2);
		// click on the investigation action button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:InvButton_button']"));// "//*[@id='majorTabPanel:subjtabview:subactions_button']"

		// Select Add new investigation option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//a[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId:invActionMenu1']"));

		// click on the add investigation button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='addInvestigationForm:addInvestigationAddButtonId']"));

		// Verify that the error message is seen
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='addInvestigationForm:addInvestigationFormMessagesId']", "Investigation Type is required");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='addInvestigationForm:addInvestigationFormMessagesId']", "Investigation Agency is required");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='addInvestigationForm:addInvestigationFormMessagesId']", "Investigation Close Date is required");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='addInvestigationForm:addInvestigationFormMessagesId']", "Agency Case Number is required");
		// click on the cancel button
		// click on the cancel button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='addInvestigationForm:addInvestigationCancelButtonId']"));
		pause(3);
	}

	/**
	 * 
	 * @param caseID
	 * @param agencyCaseNumber
	 */

	public void viewInvestigation(String caseID, String agencyCaseNumber) {
		reusable.searchCase(caseID);
		pause(2);
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel']", "Investigation Details");
		// Verify the Investigation section has the following columns
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']", "Type");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']", "Status");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']", "Investigating Agency");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']", "Close Date");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']", "Agency Case Number");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']", "RAISE");
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyIfElementExists(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']"), By.xpath("//button[@title='Update Investigation']"));

		WebElement element = ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//td[9]/div"));
		String elementclass = element.getAttribute("class");
		System.out.println("Row expand Element with class " + elementclass + " is seen");

	}

	/**
	 * 
	 * @param caseID
	 * @param agencyCaseNumber
	 * @param docName
	 */
	public void viewInvestigationDoc(String caseID, String agencyCaseNumber, String docName) {
		// Search for the subject
		reusable.searchCase(caseID);
		pause(5);
		// Expand the row of the specific agencycase
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']//td[preceding-sibling::td[contains(text(),'"+agencyCaseNumber+"')]]/div"));
		//ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']/div/table", By.xpath("./td[9]/div"));

		// Verify if the table has the doc
		ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId_data']", docName);

	}

	/**
	 * This method will create the test data for Add investigation from case
	 * details page functionalities
	 * 
	 * @param caseType
	 * @param caseType1
	 * @param levelCodeOption
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> createTestData(String caseType, String levelCodeOption) throws Exception {

		// Log in to CATS UAT as user 1
		reusable.loginToCATS(executiveChiefID);
		UserInfo item = new UserInfo();

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

		// Log in to JVS as security officer
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

		// Log in to CATS UAT as user 1
		reusable.loginToCATS(executiveChiefID);

		// Create case for the subject
		reusable.createCaseForSubject(ssn, divisionName, caseType, smoName, investigationType);
		WaitingToLoad();

		// Get the case ID of the cases created
		String caseID1 = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("Second case ID is : " + caseID);

		// Log out of CATS user 1
		catsLogout();

		// Create a list of SSN, First name last name to be returned.
		List<UserInfo> retList = new ArrayList<UserInfo>();
		item.ssn = ssn;
		item.caseID = caseID1;
		retList.add(item);
		return retList;

	}

	/**
	 * This method will create the test data for Add investigation from case
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
		System.out.println(caseID);

		// Log out of CATS user 1
		catsLogout();

		return ssn;

	}

}