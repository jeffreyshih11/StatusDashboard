package com.iworks.DISS.test.catsRegression.updateDeterminationInAppealsProcess;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
 * This class has methods that will create test data based on all the preconditions assumed for Favorable determination
 * in Appeals process and verifies that the eligibility is updated and is reflected in DISS portal and CATS as well.
 * This class assumes the following preconditions for test data creation:
 * URLs are available and functional,
 * Subject has been created, 
 * Current eligibility has been set to SCI/TS/S/C/None,
 * New case has been created for the same subject based on requirement 
 * And has been processed such that the case is in Due process phase, 
 * SOR has been initiated and completed, 
 * LOR/LOD has been initiated but adjudicator,
 * Security Officer has indicated that Subject will appeal, 
 * Case has been sent to process team to complete task and upload NOIA document 
 * And the case has been sent to adjudicator and case is in Appeals Process.
 * @author vshivaraman 
 */

public class UpdateEligibilityInAppealsProcessBasedOnDeterminationFavorable extends TestBase {
	
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
	public  Class<? extends UpdateEligibilityInAppealsProcessBasedOnDeterminationFavorable> clazz = this.getClass();
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
	 * This method will create test data for TC17787 with Current eligibility = SCI, Requested eligibility = SCI, 
	 * and determination = Favorable and verify that the eligibility = SCI after appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17787() throws Exception {
//		//Create test data with all preconditions set
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		 verifyCatsEligibility(ssn,"SCI - ICD704");		
//		
//		// Now verify in JVS		
//		portal_verification(ssn,"SCI - ICD704");
//	}
	/**
	 * This method will create test data for TC17788 with Current eligibility = TS, Requested eligibility = SCI
	 * and determination = Favorable and verify that the eligibility = SCI after appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */	
//	@Test
//	public void testTC17788() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//	 verifyCatsEligibility(ssn,"SCI - ICD704");		
//		
//	// Now verify in JVS		
//	portal_verification(ssn,"SCI - ICD704");
//	}

	/**
	 * This method will create test data for TC17789 with Current eligibility = S, Requested eligibility = SCI
	 * and determination = Favorable and verify that the eligibility = SCI after appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17789() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"SCI - ICD704");
//		// Now verify in JVS
//		portal_verification(ssn,"SCI - ICD704");
//	}

	/**
	 * This method will create test data for TC17790 with Current eligibility = C, Requested eligibility = SCI
	 * and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17790() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"SCI - ICD704");
//		// Now verify in JVS
//		portal_verification(ssn,"SCI - ICD704");
//	}

	/**
	 * This method will create test data for TC17791 with Current eligibility = None, Requested eligibility = SCI
	 * and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	
//	@Test
//	public void testTC17791() throws Exception {
//		String ssn = createTestData1(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelSCI.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//
//		// Verify in cats
//		verifyCatsEligibility(ssn, "SCI - ICD704");
//		// Now verify in JVS
//		portal_verification(ssn, "SCI - ICD704");
//	}
	
	/**
	 * This method will create test data for TC17764 with Current eligibility = SCI, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility= TS (5.4 req change) after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17764() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeSCI.getProperty(),
//				                    CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Top Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Top Secret");
//	}
	
	/**
	 * This method will create test data for TC17765 with Current eligibility = TS, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility = Top Secret after appeal adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17765() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Top Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Top Secret");
//	}
	
	/**
	 * This method will create test data for TC17766 with Current eligibility = None, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility = Top Secret after appeal adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */

//	@Test
//	public void testTC17766() throws Exception {
//		String ssn = createTestData1(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Top Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Top Secret");
//	}
	
	/**
	 * This method will create test data for TC17731 with Current eligibility = S, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Secret after appeal adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test	
//	public void testTC17731() throws Exception {
//	String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Secret");
//	// Now verify in JVS
//	portal_verification(ssn,"Secret");
//	}
	
	/**
	 * This method will create test data for TC17732 with Current eligibility = C, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Secret after appeal adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17732() throws Exception {
//	String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Secret");
//	// Now verify in JVS
//	portal_verification(ssn,"Secret");
//	}
	
	/**
	 * This method will create test data for TC17733 with Current eligibility = None, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Secret after appeal adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17733() throws Exception {
//	String ssn = createTestData1(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Secret");
//	// Now verify in JVS
//	portal_verification(ssn,"Secret");
//	}
	
	/**
	 * This method will create test data for TC17688 with Current eligibility = S, Requested eligibility = C
	 * and determination = Favorable and verify that the current eligibility =Confidential (5.4 req change) after appeal adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17688() throws Exception {
//	String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Confidential");
//	// Now verify in JVS
//	portal_verification(ssn,"Confidential");
//	}
	
	/**
	 * This method will create test data for TC17689 with Current eligibility = C, Requested eligibility = C
	 * and determination = Favorable and verify that the eligibility = Confidential after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17689() throws Exception {
//	String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Confidential");
//	// Now verify in JVS
//	portal_verification(ssn,"Confidential");
//	}
	
	/**
	 * This method will create test data for TC17690 with Current eligibility = None, Requested eligibility = C
	 * and determination = Favorable and verify that the eligibility = Confidential after appeal adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17690() throws Exception {
//	String ssn = createTestData1(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Confidential");
//	// Now verify in JVS
//	portal_verification(ssn,"Confidential");
//	}
	/**
	 * This method will create test data for TC with Current eligibility = S, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility = Top Secret after  appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	@Test
	public void testTCSToTS() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
		
		// Verify in cats
		verifyCatsEligibility(ssn,"Top Secret");
		// Now verify in JVS
		portal_verification(ssn,"Top Secret");
	}

	/**
	 * This method will create test data for TC with Current eligibility = C, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility = Top Secret after appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCCToTS() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelTopSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Top Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Top Secret");
//	}
	/**
	 * This method will create test data for TC with Current eligibility =SCI, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Secret (5.4 req change) after appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCSCIToS() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Secret");
//	}

	
	/**
	 * This method will create test data for TC with Current eligibility = TS, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Secret(5.4 req change) after appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	@Test
	public void testTCTSToS() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelSecret.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
		
		// Verify in cats
		verifyCatsEligibility(ssn,"Secret");
		// Now verify in JVS
		portal_verification(ssn,"Secret");
	}
	/**
	 * This method will create test data for TC with Current eligibility =SCI, Requested eligibility = C
	 * and determination = Favorable and verify that the eligibility = C (5.4 req change) after appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCSCIToC() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Confidential");
//		// Now verify in JVS
//		portal_verification(ssn,"Confidential");
//	}

	
	/**
	 * This method will create test data for TC with Current eligibility = TS, Requested eligibility =C 
	 * and determination = Favorable and verify that the eligibility = Confidential (5.4 req change) after appeals adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	@Test
	public void testTCTSToC() throws Exception {
		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelConfidential.getProperty(),CATSProperties.AppealDetermiantionFavorable.getProperty(),CATSProperties.ExceptionNone.getProperty());
		
		// Verify in cats
		verifyCatsEligibility(ssn,"Confidential");
		// Now verify in JVS
		portal_verification(ssn,"Confidential");
	}

	
	/**
	 *
	 * This method will create the test data 
	 * for TC9210,TC9211,TC9212,TC9159,TC9160,TC9091,TC9092,TC9001,TC9002 
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
	public String createTestData(String caseType , String caseType1,String levelCodeOption,String level,String determination,String exception) throws Exception {

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
		System.out.println("First Case ID is : "+ caseID);

		// Log out of CATS user 1
		catsLogout();

		// Login to CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);
		
		// Select case from Open case Datatable based on SSN.
		String searchableSsn = ssn.substring(0, 3)+ "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable_data']");

		// Close the case created with Favorable determination and current eligibility is set to caseType
		reusable.determinationFavorable(caseID,levelCodeOption,CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(),CATSProperties.MitigationCondition.getProperty());
		
		// Logout as adjauto21
		catsLogout();
		
		// Login as 1
		reusable.loginToCATS(executiveChiefID);
		
		// This method will create a new case for the same SSN above
		reusable.createCaseForSubject(ssn, divisionName, caseType1, smoName, investigationType);
		WaitingToLoad();
		
		// Get the new case ID for same subject
		String caseID1 = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("Second Case ID is: " +caseID1);

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
	
		//Log out process team useriga
		catsLogout();
		
		//Log into CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);
		
		//Call the appealsadjudication method to grant favorable determination and update the eligibility after adjudication
		reusable.appealAdjudicationAsAdjudicator(caseID1, "Favorable",level, determination, exception);
		pause(5);
		//Log out adjudicator
		catsLogout();
		return ssn;

	}
	
	/**
	 * This method will create the test data 
	 * for TC9213,TC9163,TC9093,TC9003 with all the required preconditions
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
	public String createTestData1(String caseType,String level, String determination, String exception) throws Exception {

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

		
		//Create Subject based on the SSN, First name and last name passed
		reusable.createSubject(ssn, firstName, lastName,"1983","Oct","20","United States");
		reusable.addCitizenshipForSubject(ssn, "Natural Born Citizen", "United States", "20", "Oct", "1983");
		
		// Log out of CATS user "1"
		catsLogout();
		
		// Log in to JVS as Security Officer
		jvsreusable.loginToJVS(securityOfficerID);

		// Create SMO relationship between the case and the SO
		jvsreusable.createSMORelationship(categoryType, ssn);

		// Log out of JVS
		jvsreusable.jvsLogout();
		
		// Log in to CATS UAT as user 1 
		reusable.loginToCATS("1");

		// Create case for the subject
		reusable.createCaseForSubject(ssn, divisionName, caseType, smoName, investigationType);
		WaitingToLoad();

		// Get the case ID of the cases created
		String caseID = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']"));
		System.out.println("First Case ID is : " +caseID);

		//Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']"));
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

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
	
		//Log out process team user
		catsLogout();
		
		//Log into CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);
		
		//Call the appealsadjudication method to grant favorable determination and update the eligibility after adjudication
		reusable.appealAdjudicationAsAdjudicator(caseID,"Favorable", level, determination, exception);
		
		//Log out adjudicator
		catsLogout();
		return ssn;

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
		System.out.println("Eligibility after Appeals adjudication in CATS is: " + current_eligibility);

		//Check if the current eligibility contains text specified in eligibility
		if (current_eligibility.contains(eligibility)) {
			System.out.println("Correct Eligibility after Appeals adjudication in CATS is verified as" + current_eligibility);
		} else {
			System.out.println("Wrong eligibility after appeals adjudication in CATS is seen"+ current_eligibility);
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
			System.out.println("Correct Eligibility after Appeals adjudication in Portal is verified as " + eligibility1 );
		} else {
			System.out.println("Wrong eligibility after appeals adjudication in PORTAL is seen as "+ eligibility1 );
		}
		
		//Log out SO from JVS
		jvsreusable.jvsLogout();
	}

}
