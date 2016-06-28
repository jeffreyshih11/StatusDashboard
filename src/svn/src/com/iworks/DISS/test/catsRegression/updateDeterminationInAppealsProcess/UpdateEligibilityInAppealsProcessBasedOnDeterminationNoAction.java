package com.iworks.DISS.test.catsRegression.updateDeterminationInAppealsProcess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.*;

import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.*;

/**
 * This class has methods that will create test data based on all the preconditions assumed for NoAction determination
 * in Appeals process and verifies that the eligibility is updated and is reflected in DISS portal and CATS as well.
 * This class assumes the following preconditions for test data creation:
 * URLs are available and functional,
 * Subject has been created, 
 * Current eligibility has been set to SCI/TS/S/C/None,
 * New case has been created for the same subject based on requirement 
 * And has been processed such that the case is in Due process phase, 
 * SOR has been initiated and completed, 
 * LOR/LOD has been initiated by adjudicator,
 * Security Officer has indicated that Subject will appeal, 
 * Case has been sent to process team to complete task and upload NOIA document 
 * And the case has been sent to adjudicator and case is in Appeals Process.
 * @author vshivaraman 
 */

public class UpdateEligibilityInAppealsProcessBasedOnDeterminationNoAction extends TestBase {

	private  String adjudicatorID = null;
	private  String securityOfficerID = null;
	private  String investigationType =null;
	private  String smoName = null;
	private  String divisionName = null;
	private  String executiveChiefID = null;
	private  String categoryType = null;
	private String processTeamMemberID= null;

	
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	public  Class<? extends UpdateEligibilityInAppealsProcessBasedOnDeterminationNoAction> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
		
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className,name);
		
		processTeamMemberID = PreconditionVariables.getProperty("processTeamMemberID");
		System.out.println("Process team Member ID : "+processTeamMemberID);
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
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after  appeals adjudication with determination = No action 
	 * for following condition: Current eligibility is SCI, Requested
	 * eligibility is SCI and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */

//	@Test
//	public void testTC17744() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is TS, Requested
	 * eligibility is SCI and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17745() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(), CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for following condition: Current eligibility is Secret,
	 * Requested eligibility is SCI and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17746() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No Action
	 * for following condition: Current eligibility is Confidential,
	 * Requested eligibility is SCI and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17747() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action 
	 * for following condition: Current eligibility is None, Requested
	 * eligibility is SCI and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17748() throws Exception {
		String ssn = createTestData1(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is SCI, Requested
	 * eligibility is TopSecret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17777() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeTS.getProperty(), CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is Top Secret,
	 * Requested eligibility is TopSecret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17778() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(), CATSProperties.CaseTypeTS.getProperty(), CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is Secret, Requested
	 * eligibility is TopSecret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17779() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.CaseTypeTS.getProperty(), CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is Confidential,
	 * Requested eligibility is TopSecret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17780() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.CaseTypeTS.getProperty(), CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is None, Requested
	 * eligibility is TopSecret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17781() throws Exception {
		String ssn = createTestData1(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn, "None");
		// Now verify in JVS
		portal_verification(ssn, "None");
	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility= None after appeals adjudication with determination = No action
	 * For the following condition: Current eligibility is SCI, Requested
	 * eligibility is Confidential and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17701() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeSCI.getProperty(),
//				                    CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with determination = No action 
	 * for the following condition: Current eligibility is TS, Requested
	 * eligibility is Confidential and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17702() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is Secret, Requested
	 * eligibility is Confidential and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17703() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeal adjudication with Determination = No action
	 * for the following condition: Current eligibility is Confidential,
	 * Requested eligibility is Confidential and determination type is No
	 * Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17704() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verify that the eligibility =None after the appeals adjudication with
	 * No action determination for the following condition: Current eligibility is none, Requested
	 * eligibility is Confidential and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17705() throws Exception {
//		String ssn = createTestData1(CATSProperties.CaseTypeConfedential.getProperty(),
//				                     CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is SCI, Requested
	 * eligibility is Secret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17721() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is TS, Requested
	 * eligibility is Secret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17722() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(), CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is Secret, Requested
	 * eligibility is Secret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17723() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is Confidential,
	 * Requested eligibility is Secret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17724() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.CaseTypeSecret1.getProperty(), CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This test method will verify that the "No Action" option is NOT available and
	 * verifies that the eligibility = None after appeals adjudication with Determination = No action
	 * for the following condition: Current eligibility is None, Requested
	 * eligibility is Secret and determination type is No Action.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17725() throws Exception {
//		String ssn = createTestData1(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionNOAction.getProperty(),CATSProperties.ExceptionNone.getProperty());
//
//		// Verify in cats
//		verifyCatsEligibility(ssn, "None");
//		// Now verify in JVS
//		portal_verification(ssn, "None");
//	}

	/**
	 * This method will create the test data 
	 * with all the required preconditions for the tests in the class to run.
	 * Create a subject, 
	 * Create a case for the subject with CaseType= caseType, 
	 * Complete the case through Favorable determination so current eligibility is set !=None, 
	 * Create a new case with CaseType= caseType1 for the same subject
	 * Refer the case to Due process through determination not favorable
	 * Initiate and complete SOR flow
	 * Initiate LOD/LOR flow as adjudicator
	 * Complete LOD/LOR flow as SO indicating that the subject will appeal
	 * Move the case from Suspense phase to Appeals process as process team member
	 * Adjudicate the case in appeals process as adjudicator
	 * @param caseType
	 * @param caseType1
	 * @param levelCodeOption
	 * @param level
	 * @param determination
	 * @param exception
	 * @return SSN
	 * @throws Exception
	 */

	public String createTestData(String caseType, String caseType1, String levelCodeOption,String level,String determination,String exception) throws Exception {

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
		System.out.println("First Case ID is :"+ caseID);

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
		System.out.println("Second case ID is: "+caseID1);

		//Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']")).getText();
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

		// logout as 1
		catsLogout();
		
		//log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn1 = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn1, "//*[@id='majorTabPanel:SubTable']");

		// Set the case to none and refer to Due process
		reusable.determinationsNotFavorable(caseID1, CATSProperties.DeterminationNone.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(), CATSProperties.CivilianMPCCode.getProperty());

		// Initiate CATS SOR Flow
		reusable.sorFlow(ssn, CATSProperties.SOROption.getProperty());

		// Logout as adjauto21
		catsLogout();

		// Log in to Portal as SO
		jvsreusable.loginToJVS(securityOfficerID);

		// Call JVSSORFlow method
		jvsreusable.jvsSORflow(ssn, firstName, lastName);

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS
		reusable.loginToCATS(adjudicatorID);

		// initiate LOD/LOR flow
		reusable.lodLORFlow(ssn, CATSProperties.LODLOROption.getProperty());

		// Log out Adjudicator from CATS
		catsLogout();

		// Log in to JVS
		jvsreusable.loginToJVS(securityOfficerID);

		// Call the JVSLOD LOR method to complete the LOD/LOR flow
		jvsreusable.jvsLODLORflow(ssn, firstName, lastName,"Yes");

		// Log out of JVS
		jvsreusable.jvsLogout();
		
		//Log into CATS as Process team member
		reusable.loginToCATS(processTeamMemberID);
		
		//Call the lodLORappeals process as process team member
		reusable.lodLORProcessTeamAppealsFlow(firstName, lastName);
	
		//Log out process team user from CATS
		catsLogout();
		
		//Log into CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);
		
		//Call the appeals adjudication method to grant favorable determination and update the eligibility after adjudication
		reusable.appealAdjudicationAsAdjudicator(caseID1, "NoAction",level, determination, exception);
		
		//Log out of CATS
		//catsLogout();

		// Returns SSN of the subject
		return ssn;

	}

	/**
	 * This method will create the test data 
	 * for the tests in the class to run.
	 * Create a subject, 
	 * Create a case for the subject with CaseType= caseType, 
	 * Current eligibility is set =None, 
	 * Refer the case to Due process through determination not favorable
	 * Initiate and complete SOR flow
	 * Initiate LOD/LOR flow as adjudicator
	 * Complete LOD/LOR flow as SO indicating that the subject will appeal
	 * Move the case from Suspense phase to Appeals process as process team member
	 * Adjudicate the case in appeals process as adjudicator
	 * @param caseType
	 * @param level
	 * @param determination
	 * @param exception
	 * @return SSN
	 * @throws Exception
	 */

	public String createTestData1(String caseType, String level, String determination, String exception) throws Exception {

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
		System.out.println("First case ID is: "+caseID);


		// Log out of CATS user 1
		catsLogout();
		
		//log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn1 = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn1, "//*[@id='majorTabPanel:SubTable']");

		// Set the case to none and refer to Due process
		reusable.determinationsNotFavorable(caseID, CATSProperties.DeterminationNone.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(), CATSProperties.CivilianMPCCode.getProperty());

		// Initiate CATS SOR Flow
		reusable.sorFlow(ssn, CATSProperties.SOROption.getProperty());

		// Logout as adjauto21
		catsLogout();

		// Log in to Portal as SO
		jvsreusable.loginToJVS(securityOfficerID);

		// Call JVSSORFlow method
		jvsreusable.jvsSORflow(ssn, firstName, lastName);

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Log in to CATS
		reusable.loginToCATS(adjudicatorID);

		// initiate LOD/LOR flow
		reusable.lodLORFlow(ssn, CATSProperties.LODLOROption.getProperty());

		// Log out Adjudicator from CATS
		catsLogout();

		// Log in to JVS
		jvsreusable.loginToJVS(securityOfficerID);

		// Call the JVSLOD LOR method to complete the LOD/LOR flow
		jvsreusable.jvsLODLORflow(ssn, firstName, lastName,"Yes");

		// Log out of JVS
		jvsreusable.jvsLogout();
		
		//Log into CATS as Process team member
		reusable.loginToCATS(processTeamMemberID);
		
		//Call the lodLORappeals process as process team member
		reusable.lodLORProcessTeamAppealsFlow(firstName, lastName);
	
		//Log out process team useriga
		catsLogout();
		
		//Log into CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);
		
		//Call the appealsadjudication method to grant favorable determination and update the eligibility after adjudication
		reusable.appealAdjudicationAsAdjudicator(caseID,"NoAction", level, determination, exception);
		
		//Log otu of CATS
		//catsLogout();

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
		System.out.println("Eligibility after appeals adjudication is " + currentEligibility);

		//Check if the current eligibility contains text specified in eligibility
		if (currentEligibility.contains(eligibility)) {
			System.out.println("Correct Eligibility after appeals adjudication in CATS is verified as " + currentEligibility);
		} else {
			System.out.println("Wrong eligibility after appeals adjudication is seen as " + currentEligibility);
		}

		// Log adjudicator out of CATS
		catsLogout();

	}

	/**
	 * This method will verify that the current eligibility updated based on
	 * case and determination is reflected in JVS
	 * 
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
			System.out.println("Correct CEligibility after appeals adjudication in Portal is verified as " + eligibility1);
		} else {
			System.out.println("Wrong eligibility after appeals adjudication in PORTAL is seen as: "+ eligibility1);
		}

		// Log SO out of JVS
		jvsreusable.jvsLogout();

	}

}
