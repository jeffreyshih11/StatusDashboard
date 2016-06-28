package com.iworks.DISS.test.catsRegression.updateDetermination;

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
 * This Test script will select Favorable determination for the case and verify that the eligibility of a case is updated
 * and verifies that the changes are reflected in DISS portal as well.
 * This TC assumes the following preconditions for the case: Case current eligibility is set to SCI/TS/S/C/none
 * @author vshivaraman 
 */

public class UpdateEligibilityBasedOnDeterminationFavorable extends TestBase {
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
	public  Class<? extends UpdateEligibilityBasedOnDeterminationFavorable> clazz = this.getClass();
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
	 * This method will create test data for TC9209 with Current eligibility = SCI, Requested eligibility = SCI, LevelCode =SCI - ICD704
	 * and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17192() throws Exception {
//		System.out.println("This Test will create test data for Current eligibility = SCI, Requested eligibility = SCI, and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS");
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelCodeSCI.getProperty());
//		
//		// Verify in cats
//		 verifyCatsEligibility(ssn,"SCI - ICD704");		
//		
//		// Now verify in JVS		
//		portal_verification(ssn,"SCI - ICD704");
//	}
	

	/**
	 * This method will create test data for TC9210 with Current eligibility = TS, Requested eligibility = SCI
	 * and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */	
//	@Test
//	public void testTC17193() throws Exception {
//		System.out.println("This Test will create test data for Current eligibility = TS, Requested eligibility = SCI, and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS");
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelCodeSCI.getProperty());
//		
//		// Verify in cats
//	 verifyCatsEligibility(ssn,"SCI - ICD704");		
//		
//	// Now verify in JVS		
//	portal_verification(ssn,"SCI - ICD704");
//	}


	/**
	 * This method will create test data for TC9211 with Current eligibility = S, Requested eligibility = SCI
	 * and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17194() throws Exception {
//		System.out.println("This Test will create test data for Current eligibility = S, Requested eligibility = SCI, and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS");
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelCodeSCI.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"SCI - ICD704");
//		// Now verify in JVS
//		portal_verification(ssn,"SCI - ICD704");
//	}

	/**
	 * This method will create test data for TC9212 with Current eligibility = C, Requested eligibility = SCI
	 * and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17195() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelCodeSCI.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"SCI - ICD704");
//		// Now verify in JVS
//		portal_verification(ssn,"SCI - ICD704");
//	}


	/**
	 * This method will create test data for TC9213 with Current eligibility = None, Requested eligibility = SCI
	 * and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	
//	@Test
//	public void testTC17196() throws Exception {
//		String ssn = createTestData1(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.LevelCodeSCI.getProperty());
//
//		// Verify in cats
//		verifyCatsEligibility(ssn, "SCI - ICD704");
//		// Now verify in JVS
//		portal_verification(ssn, "SCI - ICD704");
//	}


	
	/**
	 * This method will create test data for TC9159 with Current eligibility = SCI, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility= TS(change in req in 5.4) after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	@Test
	public void testTC17169() throws Exception {
		System.out.println("This Test will create test data for Current eligibility = SCI, Requested eligibility = TS, and determination = Favorable and verify that the eligibility = SCI after adjudication and is reflected in CATS and JVS");
		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
		
		// Verify in cats
		verifyCatsEligibility(ssn,"Top Secret");//should change to TS as per new req
		// Now verify in JVS
		portal_verification(ssn,"Top Secret");
	}

	
	/**
	 * This method will create test data for TC9160 with Current eligibility = TS, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility = Top Secret after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17170() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Top Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Top Secret");
//	}

	/**
	 * This method will create test data for TC with Current eligibility = S, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility = Top Secret after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCSToTS() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Top Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Top Secret");
//	}

	/**
	 * This method will create test data for TC with Current eligibility = C, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility = Top Secret after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCCToTS() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Top Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Top Secret");
//	}

	
	/**
	 * This method will create test data for TC9163 with Current eligibility = None, Requested eligibility = TS
	 * and determination = Favorable and verify that the eligibility = Top Secret after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */

//	@Test
//	public void testTC17171() throws Exception {
//		String ssn = createTestData1(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Top Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Top Secret");
//	}

	/**
	 * This method will create test data for TC with Current eligibility =SCI, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = S(5.4 req change) after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCSCIToS() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Secret");
//	}

	
	/**
	 * This method will create test data for TC with Current eligibility = TS, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Top Secret after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCTSToS() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Secret");
//		// Now verify in JVS
//		portal_verification(ssn,"Secret");
//	}

	/**
	 * This method will create test data for TC9091 with Current eligibility = S, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Secret after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test	
//	public void testTC17136() throws Exception {
//	String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Secret");
//	// Now verify in JVS
//	portal_verification(ssn,"Secret");
//	}

	
	/**
	 * This method will create test data for TC9092 with Current eligibility = C, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Secret after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17137() throws Exception {
//	String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Secret");
//	// Now verify in JVS
//	portal_verification(ssn,"Secret");
//	}

	
	/**
	 * This method will create test data for TC9093 with Current eligibility = None, Requested eligibility = S
	 * and determination = Favorable and verify that the eligibility = Secret after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17138() throws Exception {
//	String ssn = createTestData1(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Secret");
//	// Now verify in JVS
//	portal_verification(ssn,"Secret");
//	}

	
	/**
	 * This method will create test data for TC with Current eligibility =SCI, Requested eligibility = C
	 * and determination = Favorable and verify that the eligibility = C(5.4 req change) after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCSCIToC() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeSCI.getProperty(),CATSProperties.LevelCodeConfidential.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Confidential");
//		// Now verify in JVS
//		portal_verification(ssn,"Confidential");
//	}

	
	/**
	 * This method will create test data for TC with Current eligibility = TS, Requested eligibility =C 
	 * and determination = Favorable and verify that the eligibility = C(5.4 req change) after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTCTSToC() throws Exception {
//		String ssn = createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty(),CATSProperties.LevelCodeConfidential.getProperty());
//		
//		// Verify in cats
//		verifyCatsEligibility(ssn,"Confidential");
//		// Now verify in JVS
//		portal_verification(ssn,"Confidential");
//	}



	/**
	 * This method will create test data for TC9001 with Current eligibility = S, Requested eligibility = C
	 * and determination = Favorable and verify that the current eligibility =C (5.4 req change) after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
	@Test
	public void testTC17093() throws Exception {
	String ssn = createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeSecret.getProperty(),CATSProperties.LevelCodeConfidential.getProperty());
	
	// Verify in cats
	verifyCatsEligibility(ssn,"Confidential");
	// Now verify in JVS
	portal_verification(ssn,"Confidential");
	}

	
	/**
	 * This method will create test data for TC9002 with Current eligibility = C, Requested eligibility = C
	 * and determination = Favorable and verify that the eligibility = Confidential after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17094() throws Exception {
//	String ssn = createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeConfidential.getProperty(),CATSProperties.LevelCodeConfidential.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Confidential");
//	// Now verify in JVS
//	portal_verification(ssn,"Confidential");
//	}

	
	/**
	 * This method will create test data for TC9003 with Current eligibility = None, Requested eligibility = C
	 * and determination = Favorable and verify that the eligibility = Confidential after adjudication and is reflected in CATS and JVS
	 * @throws Exception
	 */
//	@Test
//	public void testTC17095() throws Exception {
//	String ssn = createTestData1(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeConfidential.getProperty());
//	
//	// Verify in cats
//	verifyCatsEligibility(ssn,"Confidential");
//	// Now verify in JVS
//	portal_verification(ssn,"Confidential");
//	}

	
	/**
	 * This method will create the test data 
	 * for TC9210,TC9211,TC9212,TC9159,TC9160,TC9091,TC9092,TC9001,TC9002 
	 * and sets the Current eligibility for each case as specified in the pre-condition
	 * @param caseType
	 * @param caseType1
	 * @param levelCodeOption
	 * @return
	 * @throws Exception
	 */
	public String createTestData(String caseType , String caseType1,String levelCodeOption,String levelCodeOption1) throws Exception {

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
		System.out.println("First case ID is : "+caseID);

		// Log out of CATS user 1
		catsLogout();

		// Login to CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);
		
		// Select case from Open case Datatable based on SSN.
		String searchableSsn = ssn.substring(0, 3)+ "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable_data']");

		// Close the case created with Favorable determination and current eligibility is set to caseType
		reusable.determinationFavorable(caseID,levelCodeOption);
		
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
		String currentEligibilityBeforeAdjudication = com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndGetText(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']"));
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

		// logout as 1
		catsLogout();
		
		//log in to CATS as adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn1 = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn1, "//*[@id='majorTabPanel:SubTable']");

		// Click on the determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

		// Verify that Favorable is seen in the drop down list
		ReusableFunctions.verifyTextFromDropdown1(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul"), "Favorable");
		System.out.println(" Favorable is seen in the drop down list");
		
		// Call the determination favorable method to set the determination
		// as Favorable and close the case
		reusable.determinationFavorable(caseID1,levelCodeOption1);
		
		//
		catsLogout();
		return ssn;

	}
	
	/**
	 * This method will create the test data 
	 * for TC9213,TC9163,TC9093,TC9003 
	 * and sets the Current eligibility for each case as specified in the pre-condition
	 * @param caseType
	 * @return
	 * @throws Exception
	 */
	public String createTestData1(String caseType, String levelCodeOption ) throws Exception {

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

		// Click on the determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

		// Verify that Favorable is seen in the drop down list
		ReusableFunctions.verifyTextFromDropdown1(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul"), "Favorable");
		System.out.println(" Favorable is seen in the drop down list");
		
		// Call the determination favorable method to set the determination
		// as Favorable and close the case
		reusable.determinationFavorable(caseID,levelCodeOption);
		
		//log out of CATS
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
