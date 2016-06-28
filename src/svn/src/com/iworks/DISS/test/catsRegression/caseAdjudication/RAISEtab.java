package com.iworks.DISS.test.catsRegression.caseAdjudication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
 * This class will create data and test the functionalities of the RAISE Tab in
 * case details page Adjudication section
 * 
 * @author vshivaraman
 *
 */
public class RAISEtab extends TestBase {
	private  String caseType = CATSProperties.CaseTypeSCI.getProperty();
	private  String adjudicatorID = null;
	private  String securityOfficerID = null;
	private  String investigationType =null;
	private  String smoName = null;
	private  String divisionName = null;
	private  String executiveChiefID = "1";
	private  String categoryType = null; 
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	public Class<? extends RAISEtab> clazz = this.getClass();

	public class UserInfo {
		public String ssn;
		public String caseID;
	}

	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
		
		adjudicatorID = PreconditionVariables.getProperty("adjudicatorID");
		investigationType="//*[@id='createCaseForm:createCaseInvType_panel']//li[@data-label='"+PreconditionVariables.getProperty("investigationType")+"']";
		smoName="//li[@data-label='"+PreconditionVariables.getProperty("smoName")+"']";
		divisionName="//*[@id='createCaseForm:selectDivisionMenu_panel']/div/ul/li[@data-label='"+PreconditionVariables.getProperty("divisionName")+"']";
		securityOfficerID=PreconditionVariables.getProperty("securityOfficerID");
		categoryType="//*[@id='categoryCreateForm:categoryCreateTypeSelectOneMenu1_panel']//li[text()='"+PreconditionVariables.getProperty("categoryType")+"']";
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className, name);

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
	 * This method will create test data for testing RAISE survey tab
	 * functionalities
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRAISESurveyTab() throws Exception {
		System.out.println("Create Test data for the tests \n");
		List<UserInfo> list = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeSecret.getProperty());
		String ssn = list.get(0).ssn;
		String caseID = list.get(0).caseID;
		System.out.println("Test data for the tests created.\n");

		// Verify TC 17343
		System.out.println("Test: Verify TC 17343-Pre-Condition to Completing a RAISE Survey \n");
		reusable.loginToCATS(adjudicatorID);
		reusable.searchCase(caseID);
		verifyRAISENotAvailableWhenNoInvestigation();
		System.out.println("Result: Verified user cannot complete RAISE survey when there is no investigation- TC 17343- Test PASS \n");

		// Verify TC 17344
		System.out.println("test: Verify TC 17344-Completing RAISE Survey \n");
		reusable.addInvestigationCaseDetailsInvestigationAction("SSBI", "2015", "Oct", "20", "FBI", ssn, "For Add investigation", "SF-86");
		String investigation = "SSBI - 2015-10-20 ("+ ssn +")";
		verifyCompleteRaiseSurvey(caseID, investigation);
		System.out.println("Result: Verified that when an investigation is present the user has the option of completing the RAISE survey even when RAISE is not mandatory- TC-17344-Test Pass. \n");

		// Verify Viewing Empty RAISE Survey History - TC 17359
		System.out.println("Test: Verify Viewing Empty RAISE Survey History - TC 17359 \n");
		verifyViewEmptyRAISEHistory(caseID, investigation, ssn);
		System.out.println("Result: No RAISE table is shown under Investigations table and indicates there is no prior history of RAISE - TC 17359- TEST PASS \n");

		// Verify TC17346 and 17348
		System.out.println("Test: Verify TC17346-No Displayed Image Indicates RAISE Survey is Not Required and 17348 -RAISE required for No Investigations \n");
		verifyNoRAISEReqWhenNoMark(caseID, investigation);
		System.out.println("Result:Verified that when RAISE survey is not required no mark is seen and when investigation has no '*' no RAISE is required and user is able to close the case without RAISE- TC 17346 and 17348 Test PASS \n");
		catsLogout();

		// Create a new case and Add an investigation and connect to DB to set
		// RAISE survey required for an investigation
		reusable.loginToCATS(executiveChiefID);
		reusable.createCaseForSubject(ssn, divisionName, caseType, smoName, investigationType);
		// Get the case ID of the cases created
		String caseID1 = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("First case ID is : " + caseID1 + "\n");
		catsLogout();
		reusable.loginToCATS(adjudicatorID);
		reusable.searchCase(caseID1);
		reusable.addInvestigationCaseDetailsInvestigationAction("RSI", "2015", "Oct", "20", "FBI", ssn + 1, "For Add investigation", "SF-86");
		String agencyCaseNumber = ssn+1;
		investigation = "RSI - 2015-10-20 ("+ agencyCaseNumber +")";
		

		String query = "update CATS.INV set RAISE_REQUIRED='T'  where EXTERNAL_INV_CASE_ID ='"+ agencyCaseNumber+"'";

		ReusableFunctions.sqlQueryToList("Update", query, CONFIG.getProperty("Environment"), "cats", "RAISE_REQUIRED");
		
		pause(30);

		// Verify TC 17345-Display Image to Indicate RAISE Survey Required
		System.out.println("Test: Verify that a case cannot be closed without RAISE survey when RAISE tab has * meaning it is required \n");
		verifyRAISEReqWhenMarkSeen(caseID1, investigation);
		System.out.println("Result:Unable to close case without RAISE survey when RAISE is required \n");

		// Verify TC 17347-RAISE for Multiple Investigations
		System.out.println("Test: Verify RAISE for multiple Investigations \n ");
		reusable.addInvestigationCaseDetailsInvestigationAction("NACLC", "2015", "Oct", "20", "FBI", ssn + 2, "For Add investigation", "SF-86");
		String secondInvestigation = "NACLC - 2015-10-20 ("+ ssn +"2)";
		verifyMultipeInvestigation(agencyCaseNumber, caseID1, investigation, secondInvestigation);
		System.out.println("Result: Verified that when multiple investigations are present and one with RAISE required can be selected- TC 17347- Test PASS \n");

		// Verify TC 17349-Select Disposition and Select Complete or
		// Insufficient or Justified or Incomplete as Disposition
		System.out.println("Test:Verify TC 17349, TC 17350, TC 17351,TC 17355, TC 17356-Select Disposition and Select Complete or Insufficient or Justified or Incomplete as Disposition and do not save any RAISE comments \n");
		verifyComponentOptions(caseID1, agencyCaseNumber);
		System.out.println("Result:Verified that Disposition contains the 4 options and when complete is selected Component and Deviation are not seen and user can complete RAISE survey as save button is enabled and no raise comment saved- TC 17349 and 17350 and TC 17355- Test PASS and TC 17356-Test Pass \n");
		System.out.println("Result:Verified that Disposition contains the 4 options and when Insuficient or Incomplete or Justified are selected Component and Deviation are seen and user needs to select a component to complete RAISE survey before user can save survey- TC 17351 Test PASS \n");

		// Verify TC 17352, TC 17353 ,TC 17356 and TC 17354- Select deviation,
		// Select component disposition and enter comments and save data
		System.out.println("Test:Verify TC 17352, TC 17353 ,TC 17356 and TC 17354- Select deviation, Select component disposition and enter comments and save data \n");
		verifySaveComponentDispositionDeviationComments(caseID1, agencyCaseNumber);
		System.out.println("Result:Selected deviation, Selected component disposition and entered comments and saved data-  TC 17352, TC 17353 ,TC 17356 and TC 17354-TEST PASS \n");

		// Verify TC 17357 - enter all req data and comments and notes and clear
		// data
		System.out.println("Test:Verify TC 17357 - enter all req data and comments and notes and clear data \n");
		verifyClearComponentDispositionNotesDeviationComments(caseID1, agencyCaseNumber);
		System.out.println("Result: Clear data functionality - TC 17357- TEST PASS\n");

		// Click on Summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

		// Click on Case closed button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Close Case");
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.CaseClosed.getProperty()));

		// Click on confirmation yes button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");

		pause(5);
		System.out.println("case is closed \n");
		System.out.println("RAISE SURVEY tab functionalities - TEST PASS /n");

	}

	/**
	 * This method will create the test data for testing RAISE survey tab
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

		// Log in to JVS as Security officer
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
		System.out.println("First case ID is : " + caseID + "\n");

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
		System.out.println("Second case ID is : " + caseID1 + "\n");

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
	 * This method will create the test data for testing RAISE survey tab
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

		// Log in to JVS as Security officer
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

	public void verifyRAISENotAvailableWhenNoInvestigation() {
		// Verify investigation drop down is disabled
		ReusableFunctions.waitAndVerifyNotEnabled(By.xpath("//div[contains(@id,'investigationType') and contains(@class,'ui-state-disabled')]"));
		// verify that save button is disabled
		ReusableFunctions.waitAndVerifyNotEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:saveSurvey']"));
		// Verify that the clear button is disabled
		ReusableFunctions.waitAndVerifyNotEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseClearRaiseSurvey']"));

	}


	public void verifyCompleteRaiseSurvey(String caseID, String investigation) {
		// Search for the case
		reusable.searchCase(caseID);
		pause(3);
		// verify case status is HA
		ReusableFunctions.waitAndVerify_IfContains("//table[@id='majorTabPanel:caseDetailsPanel:caseSummaryCaseDetail']//*[@id='majorTabPanel:caseDetailsPanel:caseStatusValue']", "Human Adjudication");
		// Verify investigation drop down is enabled
		ReusableFunctions.waitAndVerifyEnabled(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raisePanelId']//div[contains(@class,'ui-selectonemenu ui-widget ui-state-default ui-corner-all ui-helper-clearfix dissDropDown')]"));
		// verify that save button is enabled
		ReusableFunctions.waitAndVerifyEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:saveSurvey']"));
		// Verify that the clear button is enabled
		ReusableFunctions.waitAndVerifyEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseClearRaiseSurvey']"));

		// Verify that the investigation is seen in the drop down
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//label"));
		pause(2);
		String xpath = "//div[contains(@id,':investigationType_panel')]//li";
		pause(2);
		ReusableFunctions.waitAndVerify_IfContains(xpath, investigation);

	}

	public void verifyViewEmptyRAISEHistory(String caseID, String investigation, String ssn) {
		// Search for the case
		reusable.searchCase(caseID);
		pause(3);
		// Verify that the RAISE column is empty
		ReusableFunctions.waitAndVerify_IfContains("//tbody[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId_data']/tr/td[7]", "");

	}

	public void verifyNoRAISEReqWhenNoMark(String caseID, String investigation) {
		// Search for the case
		reusable.searchCase(caseID);
		// Verify that RAISE tab has no astrix marking it as required
		ReusableFunctions.waitAndVerifyIfNotContains("//a[contains(@href, '#majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId')]", "*");
		pause(2);
		// Verify that the investigation is seen in the drop down
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//label"));
		pause(2);
		String xpath = "//div[contains(@id,':investigationType_panel')]//li";
		pause(2);
		ReusableFunctions.waitAndVerify_IfContains(xpath, investigation);
		ReusableFunctions.waitAndVerifyIfNotContains(xpath, "*");
		reusable.determinationFavorable(caseID, CATSProperties.LevelCodeSecret.getProperty());

	}

	public void verifyRAISEReqWhenMarkSeen(String caseID, String investigation) {
		reusable.searchCase(caseID);
		// Verify that RAISE tab has astrix marking it as required
		ReusableFunctions.waitAndVerify_IfContains("//a[contains(@href, '#majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId')]", "*");

		// Verify that the investigation is seen in the drop down
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//label"));
		pause(2);
		String xpath = "//div[contains(@id,':investigationType_panel')]//li";
		pause(2);
		ReusableFunctions.waitAndVerify_IfContains(xpath, investigation);
		ReusableFunctions.waitAndVerify_IfContains(xpath, "*");
		determinationFavorableWhenRAISEReq(caseID, CATSProperties.LevelCodeSecret.getProperty());

	}

	public void determinationFavorableWhenRAISEReq(String caseID, String levelCodeOption) {

		String determination = CATSProperties.DeterminationFavorable.getProperty();
		// System.out.println("Determination to be set is " + determination);

		// Click on Files Review tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewTAb.getProperty()));

		// Click on save button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.FilesReviewSave.getProperty()), 500);
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Save");

		// verify that growl message is seen
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Files Reviewed saved");
		pause(5);

		// Click on Guidelines Tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Guidelinestab.getProperty()));

		// click on No disqualyfying radio button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//table[3]/tbody/tr/td/table/tbody/tr/td[3]/div/div[2]"));


		// verify that growl message is seen
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Guidelines Saved.");
		pause(5);

		// Click on Determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the Determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

		// Click on the option based on value of determination variable
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(determination));

		// Click on the Level code drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:additionalDeterminationPanelId:determinationLevelCodeId_label']"));

		// Select the level code option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(levelCodeOption));

		// Click save Button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));

		// verify that growl message is seen
		// com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title",
		// "Determination saved.");
		pause(5);

		// Click on Summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

		// Verify case close button not enabled when RAISE is required
		ReusableFunctions.waitAndVerifyNotEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSummaryCloseCaseId']"));

		System.out.println("case cannot be closed till RASIE survey is completed \n");
	}

	public void verifyMultipeInvestigation(String agencyCaseNumber, String caseID, String firstInvestigation, String secondInvestigation) {
		reusable.searchCase(caseID);
		pause(5);
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId']//td[text()='RAISE*']"));

		// Verify that the investigation is seen in the drop down
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//label"));
		pause(2);
		// String xpath = "//div[65]";
		pause(2);
		ReusableFunctions.waitAndVerify_IfContains("//div[contains(@id,':investigationType_panel')]//li", secondInvestigation);
		ReusableFunctions.waitAndVerify_IfContains("//div[contains(@id,':investigationType_panel')]//li", firstInvestigation + " *");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,':investigationType_panel')]//li[@data-label='RSI - 2015-10-20 ("+ agencyCaseNumber +") *']"));

	}

	public void verifyComponentOptions(String caseID, String agencyCaseNumber) {
		reusable.searchCase(caseID);
		pause(2);
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//label[contains(@id,':investigationType_label')]"));

		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,':investigationType_panel')]//li[@data-label='RSI - 2015-10-20 ("+ agencyCaseNumber +") *']"));

		String[] dispositionList = { "Complete", "Justified", "Incomplete", "Insufficient" };
		for (String string : dispositionList) {
			ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", string);

			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//span[text()='"+ string +"']"));
			if (string.equalsIgnoreCase("Complete")) {
				ReusableFunctions.waitAndVerifyIfNotContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", "Component");
				ReusableFunctions.waitAndVerifyIfNotContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", "Deviation");
			}
			if ((string.equalsIgnoreCase("Insufficient") || string.equalsIgnoreCase("Justified") || string.equalsIgnoreCase("Incomplete"))) {
				ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", "Component");
				ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", "Deviation");
				ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[text()='Former Spouse']"));
				ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,'componentId_panel')]//li[@data-label='Medical']"));
				ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,'addComponentNotesId')]"));

			}
			// verify that save button is enabled
			ReusableFunctions.waitAndVerifyEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:saveSurvey']"));
			// Verify that the clear button is enabled
			ReusableFunctions.waitAndVerifyEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseClearRaiseSurvey']"));
			// click save button
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:saveSurvey']"));
			
			String query = "Select LAST_UPDATE_TIME from RAISE_SURVEY where INV_ID=(Select INV_ID from INV where EXTERNAL_INV_CASE_ID='"+ agencyCaseNumber +"')";
			String time = ReusableFunctions.sqlQueryToList("Select", query, CONFIG.getProperty("Environment"), "cats", "LAST_UPDATE_TIME");
			
			pause(5);
			// Verify Raise tab now does has the check mark
			ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//td[preceding-sibling::td[contains(text(),'RAISE')]]/span[contains(@class,'ui-icon ui-icon-check')]"));
			//ReusableFunctions.waitAndVerifyIfElementExists(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId']"), By.xpath("//span[contains(@class,'ui-icon ui-icon-check')]"));
			// Click on Summary tab
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

			// Click on Case closed button
			ReusableFunctions.waitAndVerifyEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSummaryCloseCaseId']"));

			// click on Raise Tab
			ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RAISE"));
			// Verify TC 17358-Viewing RAISE Survey History
			System.out.println("Test:Viewing RAISE Survey History-TC 17358 \n");
			verifyViewRAISESurveyHistory(caseID, agencyCaseNumber, time);
			System.out.println("Result:Viewing RAISE Survey History-TC 17358- TEST PASS \n");

			// Verify TC 17360 - Export Pdf for each Raise Survey saved

			System.out.println("test:Verify TC 17360 \n");
			verifyExportPDF(caseID, agencyCaseNumber, time);
			System.out.println("Result: Export PDF verified- TC 17360- TEST PASS \n");

		}

	}

	public void verifyViewRAISESurveyHistory(String caseID, String agencyCaseNumber, String time) {
		reusable.searchCase(caseID);
		pause(2);
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//td[preceding-sibling::td[text()='"+agencyCaseNumber+"']]/button[contains(@id,':raiseSurveyButtonId')]"));
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "RSI - 2015-10-20 ("+ agencyCaseNumber +")");
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "Investigation");
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "RAISE");
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "Disposition");
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "Component");
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "Deviation");
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "RAISE Comments");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='raiseSurveyPopupFormId:raiseSurveySelectId_label']"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='raiseSurveyPopupFormId:raiseSurveySelectId_panel']//li[contains(@data-label,'"+ time +"')]"));
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "Close");
		ReusableFunctions.waitAndVerify_IfContains("//form[@id='raiseSurveyPopupFormId']", "Export to PDF");
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='raiseSurveyPopupFormId:closeRaisePopup']"));

	}

	public void verifyExportPDF(String caseID, String agencyCaseNumber, String time) {
		reusable.searchCase(caseID);
		pause(2);
		ReusableFunctions.waitAndSelectCellFromTableBasedOnvalueOfAnotherCell(agencyCaseNumber, "//div[@id='majorTabPanel:caseDetailsPanel:investigationInfoTableId']/div/table", By.xpath("./td[7]/button"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[@id='raiseSurveyPopupFormId:raiseSurveySelectId_label']"));
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='raiseSurveyPopupFormId:raiseSurveySelectId_panel']//li[contains(@data-label,'"+ time +"')]"));

		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("parent handel is : " + parentWindowHandler);

		// Perform the click operation that opens new window
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//*[@id='raiseSurveyPopupFormId:raiseExportPDF']"));
		pause(10);

		Set<String> set = driver.getWindowHandles();
		int s = set.size();
		System.out.println("set size is" + s);
		Iterator<String> ite = set.iterator();

		String popupHandle = ite.next().toString();
		System.out.println("handle 1 is :" + popupHandle);
		if (popupHandle.equalsIgnoreCase(parentWindowHandler)) {
			popupHandle = ite.next().toString();
			System.out.println("handle 2 is :" + popupHandle);
			driver.switchTo().window(popupHandle);
		} else {
			driver.switchTo().window(popupHandle);
		}

		// driver.manage().window().maximize();
pause(2);
		// Perform the actions on new window
		WebElement element = ReusableFunctions.waitUntilElementExistsAndFindBy(By.xpath("//html/body/object[contains(@type,'/pdf')]"));

		element.click();
		System.out.println("pdf is seen \n");
		String className = clazz.getCanonicalName();
		String path = reusable.captureScreen(className, name);
		System.out.println("The captured screen shot is /n" + path);
		driver.close();
		driver.switchTo().window(parentWindowHandler);
		ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='raiseSurveyPopupFormId:closeRaisePopup']"));
		pause(2);

	}

	public void verifySaveComponentDispositionDeviationComments(String caseID, String agencyCaseNumber) {
		reusable.searchCase(caseID);
		pause(5);

		String[] dispositionList = { "Justified", "Incomplete", "Insufficient" };
		for (String disposition : dispositionList) {
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//label[contains(@id,':investigationType_label')]"));
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,':investigationType_panel')]//li[@data-label='RSI - 2015-10-20 ("+ agencyCaseNumber +") *']"));//RSI - 2015-10-20 (1117700401) *

			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//span[text()='"+ disposition +"']"));
			ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", "Component");
			ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", "Deviation");
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(@id,':componentId_label')]"));
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,'componentId_panel')]//li[@data-label='Medical']"));
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(@id,':componentDispositionId_label')]"));
			String[] componentDispositionList = { "Complete", "Justified", "Incomplete", "Insufficient" };
			for (String componentDisposition : componentDispositionList) {
				ReusableFunctions.waitAndVerify_IfContains("//div[contains(@id,':componentDispositionId_panel')]//li", componentDisposition);
			}

			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,':componentDispositionId_panel')]//li[@data-label='Justified']"));
			String[] deviationList = { "CAF Staff conducted DCII check" };
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(@class,'ui-selectcheckboxmenu-label')]"));
			for (String deviation : deviationList) {

				ReusableFunctions.waitAndVerify_IfContains("//div[contains(@id,':raiseDeviationId_panel')]//li//label", deviation);
			}
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,':raiseDeviationId_panel')]//li//label[text()='CAF Staff obtained SF 86']"));
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//textarea[contains(@id,':raiseCommentId')]"));
			ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//textarea[contains(@id,':raiseCommentId')]"), "Test comments");
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//textarea[contains(@id,':componentNotesId')]"));
			ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//textarea[contains(@id,':componentNotesId')]"), "Test notes");
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,'addComponentNotesId')]"));

			// verify that save button is enabled
			ReusableFunctions.waitAndVerifyEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:saveSurvey']"));
			// Verify that the clear button is enabled
			ReusableFunctions.waitAndVerifyEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseClearRaiseSurvey']"));
			// click save button
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:saveSurvey']"));
			pause(3);
			// Verify Raise tab now does not have * but check mark
			ReusableFunctions.waitAndVerifyIfNotContains("//a[contains(@href, '#majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId')]", "*");
			ReusableFunctions.waitAndVerifyIfElementExists(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId']"), By.xpath("//span[contains(@class,'ui-icon ui-icon-check')]"));
			// Click on Summary tab
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

			// Click on Case closed button
			ReusableFunctions.waitAndVerifyEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSummaryCloseCaseId']"));

			// click on Raise Tab
			ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RAISE"));
		}

	}

	public void verifyClearComponentDispositionNotesDeviationComments(String caseID, String ssn) {
		reusable.searchCase(caseID);
		pause(5);

		String[] dispositionList = { "Justified", "Incomplete", "Insufficient" };
		for (String disposition : dispositionList) {
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//label[contains(@id,':investigationType_label')]"));
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,':investigationType_panel')]//li[@data-label='RSI - 2015-10-20 ("+ ssn +") *']"));

			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']//span[text()='"+ disposition +"']"));
			ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", "Component");
			ReusableFunctions.waitAndVerify_IfContains("//div[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId']", "Deviation");
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(@id,':componentId_label')]"));
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,'componentId_panel')]//li[@data-label='Medical']"));
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(@id,':componentDispositionId_label')]"));

			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,':componentDispositionId_panel')]//li[@data-label='Justified']"));

			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//label[contains(@class,'ui-selectcheckboxmenu-label')]"));

			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//div[contains(@id,':raiseDeviationId_panel')]//li//label[text()='CAF Staff obtained SF 86']"));
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//textarea[contains(@id,':raiseCommentId')]"));
			ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//textarea[contains(@id,':raiseCommentId')]"), "Test Component");
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//textarea[contains(@id,':componentNotesId')]"));
			ReusableFunctions.waitUntilElementExistsAnsSendkeys(By.xpath("//textarea[contains(@id,':componentNotesId')]"), "Test Notes");
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[contains(@id,'addComponentNotesId')]"));

			// click clear button
			ReusableFunctions.waitUntilElementExistsAndClick(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:raiseClearRaiseSurvey']"));
			pause(2);

			// Verify Raise tab now does not have * but check mark
			ReusableFunctions.waitAndVerifyIfNotContains("//a[contains(@href, '#majorTabPanel:adjPanel:adjudicationTabId:raiseSurveyTabId')]", "*");
			// Click on Summary tab
			com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

			// verify Case closed button is not enabled
			ReusableFunctions.waitAndVerifyNotEnabled(By.xpath("//button[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationSummaryCloseCaseId']"));

			// click on Raise Tab
			ReusableFunctions.waitUntilElementExistsAndClick(By.partialLinkText("RAISE"));
		}

	}
}
