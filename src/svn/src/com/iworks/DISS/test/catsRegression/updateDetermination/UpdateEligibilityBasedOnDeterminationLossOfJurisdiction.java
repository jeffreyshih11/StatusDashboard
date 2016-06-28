package com.iworks.DISS.test.catsRegression.updateDetermination;

import java.util.List;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;

import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.*;

/**
 * This Test script will update the eligibility based on Loss Of Jurisdiction determination
 * and verifies that the change is reflected in DISS portal and CATS as well.
 * This TS assumes the following preconditions for the case: URLs are available
 * and functional, Subject has been created, current eligibility has been set
 * and new case has been created for the same subject
 * 
 * @author vshivaraman
 */

public class UpdateEligibilityBasedOnDeterminationLossOfJurisdiction extends TestBase {

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
	public  Class<? extends UpdateEligibilityBasedOnDeterminationLossOfJurisdiction> clazz = this.getClass();
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

		
		quitBrowser();;
	}
	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility changes to "None" once determination is set to
	 * "Loss Of Jurisdiction" for following condition: Current eligibility is SCI, Requested
	 * eligibility is SCI and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */

//	@Test
//	public void testTC17144() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeSCI.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is TS, Requested
	 * eligibility is SCI and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17145() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(), CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeTopSecret.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for following condition: Current eligibility is Secret,
	 * Requested eligibility is SCI and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17146() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeSecret.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for following condition: Current eligibility is Confidential,
	 * Requested eligibility is SCI and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17147() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeConfidential.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for following condition: Current eligibility is None, Requested
	 * eligibility is SCI and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17148() throws Exception {
		String ssn = createTestData1(CATSProperties.CaseTypeSCI.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is SCI, Requested
	 * eligibility is TopSecret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17177() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeTS.getProperty(), CATSProperties.LevelCodeSCI.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction"for the following condition: Current eligibility is Top Secret,
	 * Requested eligibility is TopSecret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17178() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(), CATSProperties.CaseTypeTS.getProperty(), CATSProperties.LevelCodeTopSecret.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction"for the following condition: Current eligibility is Secret, Requested
	 * eligibility is TopSecret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17179() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.CaseTypeTS.getProperty(), CATSProperties.LevelCodeSecret.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change To "None" once determination is set to
	 * "Loss Of Jurisdiction"for the following condition: Current eligibility is Confidential,
	 * Requested eligibility is TopSecret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17180() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.CaseTypeTS.getProperty(), CATSProperties.LevelCodeConfidential.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction"for the following condition: Current eligibility is None, Requested
	 * eligibility is TopSecret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17181() throws Exception {
		String ssn = createTestData1(CATSProperties.CaseTypeTS.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is SCI, Requested
	 * eligibility is Confidential and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17101() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeSCI.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is TS, Requested
	 * eligibility is Confidential and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17102() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeTopSecret.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is Secret, Requested
	 * eligibility is Confidential and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17103() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeSecret.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is Confidential,
	 * Requested eligibility is Confidential and determination type is "Loss Of Jurisdiction"
	 * 
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17104() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeConfidential.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is None, Requested
	 * eligibility is Confidential and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17105() throws Exception {
//		String ssn = createTestData1(CATSProperties.CaseTypeConfedential.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is SCI, Requested
	 * eligibility is Secret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17121() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeSCI.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is TS, Requested
	 * eligibility is Secret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17122() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(), CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeTopSecret.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction"for the following condition: Current eligibility is Secret, Requested
	 * eligibility is Secret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17123() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeSecret.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction" for the following condition: Current eligibility is Confidential,
	 * Requested eligibility is Secret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17124() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeConfidential.getProperty());
//
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "Loss Of Jurisdiction" option is available and
	 * verify that the eligibility will change to "None" once determination is set to
	 * "Loss Of Jurisdiction"for the following condition: Current eligibility is None, Requested
	 * eligibility is Secret and determination type is "Loss Of Jurisdiction".
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17125() throws Exception {
//		String ssn = createTestData1(CATSProperties.CaseTypeSecret1.getProperty());
//
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This method will set up the test data with all the required preconditions
	 * for the tests in the class to run. Create a subject, Create a case for
	 * the subject with CaseType= caseType, Complete the case through Favorable
	 * determination so current eligibility is set !=None, Create a new case
	 * with CaseType= caseType1 for the same subject
	 * 
	 * @author vshivaraman
	 * @param caseType
	 *            Case type to set the current eligibility
	 * @param caseType1
	 *            case type of the requested eligibility
	 * @param levelCodeOption
	 * @return String social security number of the subject created.
	 */

	public String createTestData(String caseType, String caseType1, String levelCodeOption) throws Exception {

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


		// Create Subject based on first name,last name and SSN
		reusable.createSubject(ssn, firstName, lastName,"1983","Oct","20","United States");
		reusable.addCitizenshipForSubject(ssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");

		// Log out of CATS user 1
		catsLogout();

		// Log in to JVS as 1
		jvsreusable.loginToJVS(securityOfficerID);

		// Create SMO relationship between the subject and the SO
		jvsreusable.createSMORelationship(categoryType, ssn);

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS UAT as user 1
		reusable.loginToCATS(executiveChiefID);

		// Create case for the subject with case type= caseType to set current
		// eligibility based on test case
		reusable.createCaseForSubject(ssn, divisionName, caseType, smoName, investigationType);

		// Get the case ID of the cases created
		String caseID = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("First case ID is: " + caseID);

		// Log out of CATS user 1
		catsLogout();

		// Login to CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select case from Open case Datatable based on SSN.
		String searchableSsn = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

		// Close the case created with Favorable determination and current
		// eligibility is set to caseType
		reusable.determinationFavorable(caseID, levelCodeOption);

		// Logout as adjauto21
		catsLogout();

		// Login as 1
		reusable.loginToCATS(executiveChiefID);

		// This method will create a new case for the same SSN above with
		// caseType= caseType1
		reusable.createCaseForSubject(ssn, divisionName, caseType1, smoName, investigationType);

		// Get the new case ID for same subject
		String caseID1 = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("Second case ID is: " + caseID1);

		//Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']")).getText();
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

		// logout as 1
		catsLogout();
		
		// Log in to CATS
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn1 = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn1, "//*[@id='majorTabPanel:SubTable']");

		// Click on the determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

		// Verify that "Loss Of Jurisdiction" is seen in the drop down list
		ReusableFunctions.verifyTextFromDropdown1(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul"), "Loss Of Jurisdiction");
		System.out.println(" Loss of Jurisdiction is seen in the drop down list");

		// Call the determination not favorable method to set the determination
		// as Loss of Jurisdiction and close the case
		reusable.determinationsNotFavorable(caseID1, CATSProperties.DeterminationLOJ.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(), CATSProperties.CivilianMPCCode.getProperty());

		WaitingToLoad();
		
		//Log out of cats
		catsLogout();
		

		// Returns SSN of the subject
		return ssn;

	}

	/**
	 * This method will set up the test data with all the required preconditions
	 * for the tests in the class to run. Create a subject, Create a case for
	 * the subject with CaseType= caseType, Current eligibility is set =None
	 * 
	 * @author vshivaraman
	 * @param caseType
	 *            Case type to set the current eligibility
	 * @return String social security number of the subject created.
	 */

	public String createTestData1(String caseType) throws Exception {

		// Log in to CATS UAT as user 1
		reusable.loginToCATS("1");

		// Call the ssnNameGenerator method to set First name,last name and SSN
		// based on baseSSN and index
		List<SSNFNameAndLName> list = reusable.ssnNameGenerator("autoFName", "autoLName");
		String ssn = list.get(0).ssn;
		String firstName = list.get(0).firstName;
		String lastName = list.get(0).lastName;
		System.out.println("SSN is " + ssn);
		System.out.println("First name is " + firstName);
		System.out.println("Last name is " + lastName);


		// Create Subject based on first name,last name and ssn
		reusable.createSubject(ssn, firstName, lastName,"1983","Oct","20","United States");
		reusable.addCitizenshipForSubject(ssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");

		// Log out of CATS user 1
		catsLogout();


		// Log in to JVS as 1
		jvsreusable.loginToJVS(securityOfficerID);

		// Create SMO relationship between the case and the SO
		jvsreusable.createSMORelationship(categoryType, ssn);

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS UAT as user 1
		reusable.loginToCATS("1");

		// Create case for the subject
		reusable.createCaseForSubject(ssn, divisionName, caseType, smoName, investigationType);

		
		//Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']")).getText();
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

		// Get the case ID of the cases created
		String caseID = reusable.getCaseID(ssn);
		System.out.println("First case ID is :" + caseID);

		// Log out of CATS user 1
		catsLogout();
		
		// Log in to CATS
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

		// Click on the determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

		// Verify that "Loss Of Jurisdiction" is seen in the drop down list
		ReusableFunctions.verifyTextFromDropdown1(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul"), "Loss Of Jurisdiction");
		System.out.println(" Loss of Jurisdiction is seen in the drop down list");

		// Call the determination not favorable method to set the determination
		// as Loss of Jurisdiction and close the case
		reusable.determinationsNotFavorable(caseID, CATSProperties.DeterminationLOJ.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(), CATSProperties.CivilianMPCCode.getProperty());
		WaitingToLoad();
		
		// log out of CATS
		catsLogout();
		// Returns SSN of the subject
		return ssn;

	}

	/**
	 * This method will verify the eligibility in CATS based on SSN
	 * 
	 * @throws Exception
	 * @author vshivaraman
	 */

	public void verifyCatsEligibility(String ssn, String eligibility) throws Exception {

		// Log in to CATS
		reusable.loginToCATS(adjudicatorID);
		
		// Search for SSN
		reusable.searchSubjectCATS(ssn);

		// get eligibility after adjudication
		String currentEligibility = driver.findElement(By.xpath("//td[preceding-sibling::td/div[contains(text(),'Eligibility Level')]]")).getText();
		System.out.println("Eligibility after adjudication is " + currentEligibility);
		
		//Check if the current eligibility contains text specified in eligibility
		if (currentEligibility.contains(eligibility)) {
			System.out.println("Correct Eligibility after adjudication in CATS is verified as " + currentEligibility);
		} else {
			System.out.println("Wrong eligibilityafter adjudication in CATS is seen as "+ currentEligibility);
		}

		// Log adjudicator out of CATS
		catsLogout();

	}

	/**
	 * This method will verify that the current eligibility updated based on
	 * case and determination is reflected in JVS
	 * @author vshivaraman
	 * @param ssn
	 * @param eligibility
	 * @throws Exception
	 */

	public void portal_verification(String ssn, String eligibility) throws Exception {
		// Go to JVS URL
		driver.get(CONFIG.getProperty("CP_Url"));

		// Log in to JVS
		jvsreusable.loginToJVS(securityOfficerID);

		// Search subject based on SSN
		jvsreusable.subjectSearch(ssn);
		WaitingToLoad();

		// Click on Subject Details link in Subject summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SubjectDetailsLink1.getProperty()));

		// Get the eligibility value from subject details page
		String eligibility1 = ReusableFunctions.getObjectByXpath("//td[preceding-sibling::td/div[contains(text(),'Eligibility Level')]]").getText();

		//Check if the eligibility1 contains text specified in eligibility
		if (eligibility1.contains(eligibility)) {
			System.out.println("Correct Eligibility after adjudication in PORTAL is verified as " + eligibility1);
		} else {
			System.out.println("Wrong eligibility after adjudication in PORTAL is seen as " + eligibility1);
		}

		// Log SO out of JVS
		jvsreusable.jvsLogout();

	}

}
