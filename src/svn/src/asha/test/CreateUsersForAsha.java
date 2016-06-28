package asha.test;

import java.io.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import com.iworks.DISS.test.common.functions.CATSReusuableFunctions;
import com.iworks.DISS.test.common.functions.JVSReusableFunctions;
import com.iworks.DISS.test.common.utils.TestBase;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class CreateUsersForAsha extends TestBase {
	JVSReusableFunctions jvsreusable = new JVSReusableFunctions();
	CATSReusuableFunctions reusable = new CATSReusuableFunctions();
	String firstName = null;
	String lastName = null;
	static jxl.Sheet Sheet;
	static jxl.Sheet Sheet1;
	long startTime;
	long endTime;
	public Class<? extends CreateUsersForAsha> clazz = this.getClass();
	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		String className = clazz.getCanonicalName();
		reusable.createLogFile(className, name);

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

		driverClose();
	}

	@Test
	public void testExcel() throws Exception {
		createTestDataForAsha();
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws BiffException
	 */

	public static void readExcel() throws IOException, BiffException {
		FileInputStream file = new FileInputStream("C:\\Users\\Public\\Documents\\myusers.xls");
		Workbook wBook = Workbook.getWorkbook(file);
		// get sheet
		Sheet = wBook.getSheet(0);
		Sheet1= wBook.getSheet(1);

	}

	public void createTestDataForAsha() throws Exception {
//	reusable.loginToCATS("1");
		readExcel();
		int noRows = Sheet.getRows();
//		System.out.println(noRows);
//		for (int row = 1; row < 16; row++) {
//			String ssn = Sheet.getCell(0, row).getContents().toString();
//			String fName;
//			int fNamelength = Sheet.getCell(1, row).getContents().toString().length();
//			if (fNamelength > 20) {
//				fName = Sheet.getCell(1, row).getContents().toString().substring(0, 20);
//			} else {
//				fName = Sheet.getCell(1, row).getContents().toString();
//			}
//			String lName = Sheet.getCell(2, row).getContents().toString();
//			String userName = Sheet.getCell(4, row).getContents().toString();
//			String role = Sheet.getCell(3, row).getContents().toString();
//			System.out.println("first name is " + fName);
//
//			reusable.createSubject(ssn, fName, lName, "1983", "20");
//
//			reusable.createUser(ssn, 22222, "DoD CAF", userName, role);
//
//		}
//		for (int row = 16; row < noRows; row++) {
//			String ssn = Sheet.getCell(0, row).getContents().toString();
//			String fName;
//			int fNamelength = Sheet.getCell(1, row).getContents().toString().length();
//			if (fNamelength > 20) {
//				fName = Sheet.getCell(1, row).getContents().toString().substring(0, 20);
//			} else {
//				fName = Sheet.getCell(1, row).getContents().toString();
//			}
//
//			String lName = Sheet.getCell(2, row).getContents().toString();
//			System.out.println("first name is " + fName);
//			reusable.createSubject(ssn, fName, lName, "1983", "20");
//		}
//		catsLogout();

		jvsreusable.loginToJVS("1");
		for (int row = 16; row < noRows; row++) {
			String ssn = Sheet.getCell(0, row).getContents().toString();
			String fName;
			int fNamelength = Sheet.getCell(1, row).getContents().toString().length();
			if (fNamelength > 20) {
				fName = Sheet.getCell(1, row).getContents().toString().substring(0, 20);
			} else {
				fName = Sheet.getCell(1, row).getContents().toString();
			}

			String userName = Sheet.getCell(4, row).getContents().toString();
			String role = Sheet.getCell(3, row).getContents().toString();
			System.out.println("first name is " + fName);
			jvsreusable.createUser(ssn, userName, "DISS", role);
		}
		jvsreusable.jvsLogout();
		int noRows1 = Sheet1.getRows();
		System.out.println(noRows1);
		String cafName = Sheet1.getCell(0, 1).getContents();
		String divisionName = Sheet1.getCell(1, 1).getContents();
		String branchName= Sheet1.getCell(2, 1).getContents();
		String teamName= Sheet1.getCell(3, 1).getContents();
		reusable.loginToCATS("1");
		reusable.addDivision("CAF-"+cafName, divisionName);
		String chieFName = Sheet.getCell(1, 3).getContents();
		if (chieFName.length() > 20) {
			chieFName = Sheet.getCell(1, 3).getContents().toString().substring(0, 20);
		} else {
			chieFName = Sheet.getCell(1, 3).getContents().toString();
		}
		String chieFLname = Sheet.getCell(2, 3).getContents();
		reusable.addDivisionChief("CAF-"+cafName, "DIVISION-"+divisionName, chieFName, chieFLname);
		reusable.addBranch("CAF-"+cafName, "DIVISION-"+divisionName, branchName);
		chieFName = Sheet.getCell(1, 4).getContents();
		if (chieFName.length() > 20) {
			chieFName = Sheet.getCell(1, 4).getContents().toString().substring(0, 20);
		} else {
			chieFName = Sheet.getCell(1, 4).getContents().toString();
		}
		chieFLname = Sheet.getCell(2, 4).getContents();
		reusable.addBranchChief("CAF-"+cafName, "DIVISION-"+divisionName, "BRANCH-"+branchName, chieFName, chieFLname);
		reusable.addTeam("CAF-"+cafName, "DIVISION-"+divisionName, "BRANCH-"+branchName, teamName);
		chieFName = Sheet.getCell(1, 5).getContents();
		if (chieFName.length() > 20) {
			chieFName = Sheet.getCell(1, 5).getContents().toString().substring(0, 20);
		} else {
			chieFName = Sheet.getCell(1, 5).getContents().toString();
		}
		chieFLname = Sheet.getCell(2, 5).getContents();
		reusable.addTeamChief("CAF-"+cafName, "DIVISION-"+divisionName, "BRANCH-"+branchName, "TEAM-"+teamName, chieFName, chieFLname);
		chieFName = Sheet.getCell(1, 2).getContents();
		if (chieFName.length() > 20) {
			chieFName = Sheet.getCell(1, 2).getContents().toString().substring(0, 20);
		} else {
			chieFName = Sheet.getCell(1, 2).getContents().toString();
		}
		chieFLname = Sheet.getCell(2, 2).getContents();
		reusable.addTeamMember("CAF-"+cafName, "DIVISION-"+divisionName, "BRANCH-"+branchName, "TEAM-"+teamName, chieFName, chieFLname);
		reusable.editTeamMemberDetails("CAF-"+cafName, "DIVISION-"+divisionName, "BRANCH-"+branchName, "TEAM-"+teamName, chieFName, chieFLname, "yes", "yes");
		chieFName = Sheet.getCell(1, 6).getContents();
		if (chieFName.length() > 20) {
			chieFName = Sheet.getCell(1, 6).getContents().toString().substring(0, 20);
		} else {
			chieFName = Sheet.getCell(1, 6).getContents().toString();
		}
		chieFLname = Sheet.getCell(2, 6).getContents();
		reusable.addProcessTeamMember("CAF-"+cafName, "TEAM-Process Team", chieFName, chieFLname);
		catsLogout();
	}

}
