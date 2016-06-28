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
 * This Test script will update the eligibility based on determination(denied or
 * revoked) of a case and verifies that the change is reflected in DISS portal
 * and CATS as well. This TS assumes the following preconditions for the case:
 * Case went through Due process, SOR was sent and response received. LOD/LOR
 * was sent and not appealed by subject.
 * 
 * @author vshivaraman
 */
public class UpdateEligibilityBasedOnDeterminationDeniedAndRevoked extends TestBase {

	
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
	public  Class<? extends UpdateEligibilityBasedOnDeterminationDeniedAndRevoked> clazz = this.getClass();
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
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is SCI, Requested eligibility is Secret and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is SCI,Requested eligibility is Secret and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */

	 @Test
	 public void testTC17131_TC17139() throws Exception {
	 String ssn =
	 createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeSCI.getProperty());
	 // Verify in cats
	 verifyCatsEligibility(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is TopSecret, Requested eligibility is Secret and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is Top Secret,Requested eligibility is Secret and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	 @Test
//	 public void testTC17132_TC17140() throws Exception {
//	 String ssn =
//	 createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
//	 // Verify in cats
//	 verifyCatsEligibility(ssn,"None");
//	 // Now verify in JVS
//	 portal_verification(ssn,"None");
//	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is Secret, Requested eligibility is Secret and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is Secret,Requested eligibility is Secret and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17133_TC17141() throws Exception {
	 String ssn =
	 createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
	 // Verify in cats
	 verifyCatsEligibility(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is Confidential, Requested eligibility is Secret and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is Confidential,Requested eligibility is Secret and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	 @Test
//	 public void testTC17134_TC17142() throws Exception {
//	 String ssn =
//	 createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.LevelCodeConfidential.getProperty());
//	 // Verify in cats
//	 verifyCatsEligibility(ssn, "None");
//	 // Now verify in JVS
//	 portal_verification(ssn,"None");
//	 }
	
	/**
	 * This test method will verify that the "Revoked" option is not available
	 * for the following condition:
	 * Current eligibility is None, Requested eligibility is Secret and
	 * determination type is Revoked. And also will verify that the "Denied"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is denied for the following condition:
	 * Current eligibility is None,Requested eligibility is Secret and
	 * determination type is denied.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17135_TC17143() throws Exception {
	 String ssn =
	 createTestData1(CATSProperties.CaseTypeSecret1.getProperty());
	 // Verify in cats
	 verifyCatsEligibility1(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }
	 
	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is SCI, Requested eligibility is SCI and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is SCI,Requested eligibility is SCI and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	 @Test
//	 public void testTC17154_TC17159() throws Exception {
//	 String ssn =
//	 createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeSCI.getProperty());
//	 // Verify in cats
//	 verifyCatsEligibility(ssn, "None");
//	 // Now verify in JVS
//	 portal_verification(ssn,"None");
//	 }
	
	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is TS, Requested eligibility is SCI and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is TS,Requested eligibility is SCI and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17155_TC17160() throws Exception {
	 String ssn =
	 createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
	 // Verify in cats
	 verifyCatsEligibility(ssn, "None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is Secret, Requested eligibility is SCI and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is Secret,Requested eligibility is SCI and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17156_TC17161() throws Exception {
	 String ssn =
	 createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
	 // Verify in cats
	 verifyCatsEligibility(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }
	
	/**
	 * This test method will verify that the "Revoked" option is not available
	 * for the condition:
	 * Current eligibility is None, Requested eligibility is SCI and
	 * determination type is Revoked. And also will verify that the "Denied"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is Denied for the following condition:
	 * Current eligibility is None,Requested eligibility is SCI and
	 * determination type is Denied.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	 @Test
//	 public void testTC17158_TC17163() throws Exception {
//	 String ssn =
//	 createTestData1(CATSProperties.CaseTypeSCI.getProperty());
//	 // Verify in cats
//	 verifyCatsEligibility1(ssn,"None");
//	 // Now verify in JVS
//	 portal_verification(ssn,"None");
//	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the condition:
	 * Current eligibility is Confidential, Requested eligibility is SCI and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is Confidential,Requested eligibility is SCI and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	 @Test
//	 public void testTC17157_TC17162() throws Exception {
//	 String ssn =
//	 createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.LevelCodeConfidential.getProperty());
//	 // Verify in cats
//	 verifyCatsEligibility(ssn,"None");
//	 // Now verify in JVS
//	 portal_verification(ssn,"None");
//	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the condition:
	 * Current eligibility is SCI, Requested eligibility is TopSecret and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is SCI,Requested eligibility is TopSecret and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	 @Test
//	 public void testTC17164_TC17187() throws Exception {
//	 String ssn =
//	 createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeSCI.getProperty());
//	 // Verify in cats
//	 verifyCatsEligibility(ssn,"None");
//	 // Now verify in JVS
//	 portal_verification(ssn,"None");
//	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the condition:
	 * Current eligibility is TopSecret, Requested eligibility is TopSecret and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is Revoked for the following condition:
	 * Current eligibility is Top Secret,Requested eligibility is TopSecret and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17165_TC17188() throws Exception {
	 String ssn =
	 createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
	 // Verify in cats
	 verifyCatsEligibility(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is Secret, Requested eligibility is TopSecret and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is Revoked for the following condition:
	 * Current eligibility is Secret,Requested eligibility is TopSecret and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	 @Test
//	 public void testTC17166_TC17189() throws Exception {
//	 String ssn =
//	 createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
//	 // Verify in cats
//	 verifyCatsEligibility(ssn,"None");
//	 // Now verify in JVS
//	 portal_verification(ssn,"None");
//	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is Confidential, Requested eligibility is TopSecret and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is Revoked for the following condition:
	 * Current eligibility is Confidential,Requested eligibility is TopSecret and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17167_TC17190() throws Exception {
	 String ssn =
	 createTestData(CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.CaseTypeTS.getProperty(),CATSProperties.LevelCodeConfidential.getProperty());
	 // Verify in cats
	 verifyCatsEligibility(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }
	
	/**
	 * This test method will verify that the "Revoked" option is not available
	 * for the following condition:
	 * Current eligibility is None, Requested eligibility is TopSecret and
	 * determination type is Revoked. And also will verify that the "Denied"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is Denied for the following condition:
	 * Current eligibility is None,Requested eligibility is TopSecret and
	 * determination type is denied.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17168_TC17191() throws Exception {
	 String ssn =
	 createTestData1(CATSProperties.CaseTypeTS.getProperty());
	 // Verify in cats
	 verifyCatsEligibility1(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is Confidential, Requested eligibility is Confidential and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is Confidential,Requested eligibility is confidential and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17091_TC17114() throws Exception {
	 String ssn =
	 createTestData(CATSProperties.CaseTypeSCI.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeSCI.getProperty());
	 // Verify in cats
	 verifyCatsEligibility(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is Secret, Requested eligibility is Confidential and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is Secret,Requested eligibility is confidential and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	 @Test
//	 public void testTC17090_TC17113() throws Exception {
//	 String ssn =
//	 createTestData(CATSProperties.CaseTypeSecret1.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeSecret.getProperty());
//	 // Verify in cats
//	 verifyCatsEligibility(ssn,"None");
//	 // Now verify in JVS
//	 portal_verification(ssn,"None");
//	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * for the following condition:
	 * Current eligibility is Top secret, Requested eligibility is Confidential and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is Top secret,Requested eligibility is confidential and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	 @Test
	 public void testTC17089_TC17112() throws Exception {
	 String ssn =
	 createTestData(CATSProperties.CaseTypeTS.getProperty(),CATSProperties.CaseTypeConfedential.getProperty(),CATSProperties.LevelCodeTopSecret.getProperty());
	 // Verify in cats
	 verifyCatsEligibility(ssn,"None");
	 // Now verify in JVS
	 portal_verification(ssn,"None");
	 }

	/**
	 * This test method will verify that the "Denied" option is not available
	 * in the determination drop down for the following condition:
	 * Current eligibility is SCI, Requested eligibility is Confidential and
	 * determination type is Denied. And also will verify that the "Revoked"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is revoked for the following condition:
	 * Current eligibility is SCI,Requested eligibility is confidential and
	 * determination type is Revoked.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
	@Test
	public void testTC17088_TC17111() throws Exception {
		//Create test data for TC8989 and 9039
		String ssn = createTestData(CATSProperties.CaseTypeSCI.getProperty(), CATSProperties.CaseTypeConfedential.getProperty(), CATSProperties.LevelCodeSCI.getProperty());
		// Verify in cats
		verifyCatsEligibility(ssn,"None");
		// Now verify in JVS
		portal_verification(ssn,"None");
	}
	
	/**
	 * This test method will verify that the "Revoked" option is not available
	 * for the following condition:
	 * Current eligibility = None, Requested eligibility = Confidential and
	 * determination type = Revoked. And also will verify that the "Denied"
	 * option is seen in the determination drop down and the eligibility will
	 * change to None once determination is denied for the following condition:
	 * Current eligibility = None,Requested eligibility = confidential and
	 * determination type = Denied.
	 * 
	 * @author vshivaraman
	 * @throws Exception
	 */
//	@Test
//	public void testTC17092_TC17115() throws Exception {
//		String ssn = createTestData1( CATSProperties.CaseTypeConfedential.getProperty());
//
//		// Verify in cats
//		verifyCatsEligibility1(ssn,"None");
//		// Now verify in JVS
//		portal_verification(ssn,"None");
//	}

	/**
	 * This method will set up the test data with all the required preconditions
	 * for the tests in the class to run. Create a subject, Create a case for
	 * the subject with CaseType= caseType, Complete the case through Favorable
	 * determination so current eligibility is set !=None, Create a new case
	 * with CaseType= caseType1 for the same subject and send it through SOR
	 * flow and then LOD/LOR flow so the Determination drop down includes
	 * Revoked option and not Denied. Note:Sending the case through LOD/LOR flow
	 * changes the current eligibility to None as soon as the letter is sent by
	 * the adjudicator and even before the determination=revoked is selected.
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
		System.out.println("First Case ID is: "+ caseID);

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
		System.out.println("Second CaseID is : " +caseID1);

		//Get the current eligibility before adjudication
		String currentEligibilityBeforeAdjudication = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:currentEligibilityValue']")).getText();
		System.out.println("Eligibility before Adjudication is " + currentEligibilityBeforeAdjudication);

		// logout as 1
		catsLogout();

		// Login to CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

		// Set the case to none and refer to Due process
		reusable.determinationsNotFavorable(caseID1, CATSProperties.DeterminationNone.getProperty(),CATSProperties.AllegiancePanel.getProperty(),CATSProperties.DisqualifyingCondition.getProperty(),CATSProperties.CivilianMPCCode.getProperty());

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
		jvsreusable.jvsLODLORflow(ssn, firstName, lastName,"No");

		// Log out of JVS
		jvsreusable.jvsLogout();

		// Returns SSN of the subject
		return ssn;

	}

	/**
	 * This method will set up the test data with all the required preconditions
	 * for the tests in the class to run. Create subject, Create a case for the
	 * subject Current eligibility is set to NONE, Send it through SOR flow So
	 * the Determination drop down includes denied option and not revoked option
	 * Note:Sending the case through LOD/LOR flow changes the current
	 * eligibility to None as soon as the letter is sent by the adjudicator and
	 * even before denied determination is selected and case closed
	 * 
	 * @author vshivaraman
	 * @param caseType
	 * 
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
	
	
		// Get the new case ID for same subject
		String caseID = driver.findElement(By.xpath("//*[@id='majorTabPanel:caseDetailsPanel:caseIDValue']")).getText();
		System.out.println("Case ID Is: " + caseID);
		
	
		// Log out of CATS user 1
		catsLogout();

		// Login to CATS as Adjudicator
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

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
		jvsreusable.jvsLODLORflow(ssn, firstName, lastName,"No");

		// Log out of JVS
		jvsreusable.jvsLogout();

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

		// Select the new case from the open case datatable.
		String searchableSsn = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

		// Click on the determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));
		
		
		// Verify that Denied is not seen in the drop down
		ReusableFunctions.assertFalseOnDropMenus(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul"), "Denied");
		//System.out.println(" Denied is not seen in the drop down list");

		// Verify that Revoked is seen in the drop down list
		ReusableFunctions.verifyTextFromDropdown1(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul"), "Revoked");
		System.out.println(" Revoked is seen in the drop down list");

		// Click on revoked option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationRevoked.getProperty()));

		// Click on save button to save determination
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));
		
		// Verify growl message is seen
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Determination saved.");

		// Click on Summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

		// Click on Case closed button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Close Case");
		System.out.println("case is closed");

		// Click on confirmation yes button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");
		WaitingToLoad();

		// Search for SSN
		reusable.searchSubjectCATS(ssn);

		// get eligibility after adjudication
		String currentEligibility = driver.findElement(By.xpath("//td[preceding-sibling::td/div[contains(text(),'Eligibility Level')]]")).getText();
		System.out.println("Eligibility after adjudication is " + currentEligibility);

		//Check if currentEligibility contains the text specified in eligibility
		if (currentEligibility.equalsIgnoreCase(eligibility)) {
			System.out.println("Correct Eligibility after adjudication in CATS is verified as  " + currentEligibility );
		} else {
			System.out.println("Wrong Eligibility after adjudication in CATS is seen as "+ currentEligibility);
		}

		// Log adjudicator out of CATS
		catsLogout();

	}

	/**
	 * This method will verify the eligibility in CATS based on SSN
	 * 
	 * @throws Exception
	 * @author vshivaraman
	 */

	public void verifyCatsEligibility1(String ssn, String eligibility) throws Exception {

		// Log in to CATS
		reusable.loginToCATS(adjudicatorID);

		// Select the new case from the open case datatable.
		String searchableSsn = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
		ReusableFunctions.waitAndselectCellFromTable(searchableSsn, "//*[@id='majorTabPanel:SubTable']");

		// Click on the determination tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationTab1.getProperty()));

		// Click on the determination drop down menu
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDropdownLst.getProperty()));

		// Verify that Revoked is not seen in the drop down
		ReusableFunctions.assertFalseOnDropMenus(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul"), "Revoked");
		System.out.println(" Revoked is not seen in the drop down list");

		// Verify that Denied is seen in the drop down list
		ReusableFunctions.verifyTextFromDropdown1(By.xpath("//*[@id='majorTabPanel:adjPanel:adjudicationTabId:adjudicationDeterminationCodeId_panel']/div/ul"), "Denied");
		System.out.println(" Denied is seen in the drop down list");

		// Click on Denied option
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationDenied.getProperty()));

		// Click on save button to save determination
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.DeterminationSaveBtn.getProperty()));
		// Verify growl message is seen
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndVerifyTextByClassname("ui-growl-title", "Determination saved.");

		// Click on Summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(CATSProperties.Summarytab1.getProperty()));

		// Click on Case closed button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Close Case");
		System.out.println("case is closed");

		// Click on confirmation yes button
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitAndButtonClick("Yes");
		WaitingToLoad();

		// Search for SSN
		reusable.searchSubjectCATS(ssn);
		WaitingToLoad();

		// get eligibility after adjudication
		String currentEligibility = driver.findElement(By.xpath("//td[preceding-sibling::td/div[contains(text(),'Eligibility Level')]]")).getText();
		System.out.println("Eligibility after adjudication is " + currentEligibility);

		//Check if currentEligibility contains the text specified in eligibility
		if (currentEligibility.contains(eligibility)) {
			System.out.println("Correct Eligibility after adjudication in CATS is seen and is verified as "+ currentEligibility+ ". Hence test pass");
		} else {
			System.out.println("Wrong Eligibility after adjudication in CATS is seen as " + currentEligibility);
		}

		// Log adjudicator out of CATS
		catsLogout();

	}

	/**
	 * This method will verify that the current eligibility 
	 * updated based on case and determination is reflected in JVS 
	 * @param ssn
	 * @param eligibilityCATS
	 * @throws Exception
	 */
	public void portal_verification(String ssn, String eligibility) throws Exception {
		//Go to JVS URL
		driver.get(CONFIG.getProperty("CP_Url"));
		
		// Log in to JVS
		jvsreusable.loginToJVS(securityOfficerID);

		//Search subject based on SSN
		jvsreusable.subjectSearch(ssn);
		WaitingToLoad();
		
		// Click on Subject Details link in Subject summary tab
		com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.xpath(JVSProperties.SubjectDetailsLink1.getProperty()));
		
		//Get the eligibility value from subject details page
		String eligibility1 = ReusableFunctions.getObjectByXpath("//td[preceding-sibling::td/div[contains(text(),'Eligibility Level')]]").getText();
		
		//Check if eligibility1 contains the text specified in eligibility
		if (eligibility1.contains(eligibility)) {
			System.out.println("Correct Eligibility after adjudication in PORTAL is seen and is verified as " + eligibility1);
		} else {
			System.out.println("Wrong Eligibility after adjudication in PORTAL is seen as " + eligibility1);
		}
	
		//Log SO out of JVS
		jvsreusable.jvsLogout();

	}

}
