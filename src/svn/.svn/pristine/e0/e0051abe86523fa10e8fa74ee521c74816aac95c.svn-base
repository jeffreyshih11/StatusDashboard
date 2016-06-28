package com.iworks.DISS.test.catsRegression.subjectManagement;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.iworks.DISS.test.common.functions.*;
import com.iworks.DISS.test.common.functions.CATSReusuableFunctions.SSNFNameAndLName;
import com.iworks.DISS.test.common.properties.*;
import com.iworks.DISS.test.common.utils.TestBase;

/**
 * This class will perform the following actions to test create subject functionalities
 * in CATS application 
 * Create test data required programmatically so the test can be run without any human intervention
 * Verify that user with create subject permission are able to do so
 * Validate the data entered and the create subject function
 * @author vshivaraman
 */

public class CATSCreateSubjects extends TestBase {

	private static final String TECT_ROLE = "TECT";
	private static final String TRAINEE_ROLE = "Trainee";
	private static final String CATS_APPLICATION_ADMINISTRATOR_ROLE = "CATS Application Administrator";
	private static final String PROCESS_TEAM_ROLE = "Process Team";
	private static final String ADJUDICATOR_ROLE = "Adjudicator";

	

	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	long startTime;
	long endTime;
	
		
	public static class UserInfo {
		public String fName;
		public String lName;
		public String uName;
		public String ssn;
		public String role;

	}

	public Class<? extends CATSCreateSubjects> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	
	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		DateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		DateFormat dateFormat1 = new SimpleDateFormat("yyMMdd");
		String date1 = dateFormat1.format(cal.getTime());
		String environment = CONFIG.getProperty("Environment");
		File targetFile = new File("C://AutomationOutput//" + environment+"//"+"TestedByBuild"+"//"+ className + "//" + date1 + "//logoutput-" + name.getMethodName() + "-" + date + ".doc");
		reusable.createLogFile(className, name,targetFile );
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
	 * Test to verify Create user functionalities in CATS application: 
	 * 	 a. Create a subject for the user
	 *   b. Create user for the subject (10 different roles)
	 *   c.Verify CATS User status is Active
	 *   d.Verify JPAS Id is required
	 *   e. Verify organization is required and can be added
	 *   f.Verify subject details link
	 *   g.Verify Roles is required and can be added
	 *   h.Verify optional permissions for the role selected is populated in the Available section under Permission section
	 *   i.Verify that user is saved and can log in
	 * 
	 * @throws Exception
	 */
	@Test
	public void catsCreateSubjectTest() throws Exception {
		
		//Create test data
		System.out.println("Create Test Data\n");
		
		// Call the createtestData function to create test data
		List<UserInfo> list = createTestData();
		System.out.println("Test Data creation complete.\n");
		for (UserInfo userInfo : list) {
			reusable.loginToCATS(userInfo.uName);
		}
	
		
		
		catsLogout();
		// Verify Log in function as different user roles 
		// Call the verify log in function to verify log in as different users
		

		


	}


	/**
	 * This function will return a list of user information
	 * of the users created within the function
	 * 
	 * @return List<FNameAndLName>
	 * @throws Exception
	 */
	public List<UserInfo> createTestData() throws Exception {
		// Log in to CATS as user 1
		reusable.loginToCATS("1");
	
		List<UserInfo> retList = new ArrayList<>();
		UserInfo uInfo = new UserInfo();
		//Adjudicator User
		uInfo = new UserInfo();
		createUserForCATSCreateSubjectTest("AdjuFName", "AdjuLName",  ADJUDICATOR_ROLE, uInfo);
		retList.add(uInfo);
		// Process team user
		uInfo = new UserInfo();
		createUserForCATSCreateSubjectTest("ProTeaFName",	"ProTeaLName",  PROCESS_TEAM_ROLE, uInfo);
		retList.add(uInfo);
		// Trainee
		uInfo = new UserInfo();
		createUserForCATSCreateSubjectTest("TraFName",	"TraLName",  TRAINEE_ROLE, uInfo);
		retList.add(uInfo);

		// CATS Application Administrator user
		uInfo = new UserInfo();
		createUserForCATSCreateSubjectTest("CatsAAFName","CatsAALName", CATS_APPLICATION_ADMINISTRATOR_ROLE, uInfo);
		retList.add(uInfo);

		// TECT user
		uInfo = new UserInfo();
		retList.add(uInfo);
		createUserForCATSCreateSubjectTest("TECTUrFName",	"TECTUrLName",  TECT_ROLE, uInfo);
		retList.add(uInfo);
		
		catsLogout();
		// return list of user information
		return retList;

	}

	/**
	 * This function will create the subject based on first name,last name,role passed
	 * @param firstnamepatern
	 * @param lastNamepatern
	 * @param userNamePattern
	 * @param role
	 * @param uInfo
	 * @throws Exception
	 */
	public void createUserForCATSCreateSubjectTest(String firstnamepatern,String lastNamepatern,  String role,
			 UserInfo uInfo  )throws Exception{
			
					
			// call ssn gen to create ssn, first name and last name for user
			List<SSNFNameAndLName> list = reusable.ssnNameGenerator(firstnamepatern, lastNamepatern);
			// get first name and last name
			uInfo.ssn = list.get(0).ssn;
			uInfo.fName = list.get(0).firstName;
			uInfo.lName = list.get(0).lastName;
			uInfo.role= role;
			System.out.println("Role is :"+role);
			System.out.println("SSN is " + uInfo.ssn);
			System.out.println("First name is " + uInfo.fName);
			System.out.println("Last name is " + uInfo.lName);
			int index = (list.get(0).index);
			String lastFour = ("000" + Integer.toString(index));
			
			//create username
			uInfo.uName = uInfo.fName.substring(0, 6)+(lastFour).substring(lastFour.length() - 4);
			System.out.println("Username is "+uInfo.uName+"\n");
			
			
			// create subject for user
			reusable.createSubject(uInfo.ssn, uInfo.fName, uInfo.lName, "1983", "20");
			
			//create user
			reusable.createUser(uInfo.ssn, 22222, "DoD CAF", uInfo.uName, uInfo.role);
			pause(5);
							
		}




	
	

	

	}
	








