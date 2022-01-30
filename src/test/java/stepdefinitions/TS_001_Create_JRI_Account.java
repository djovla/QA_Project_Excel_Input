package stepdefinitions;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.objectrepository.JRI_Locators;
import com.utilities.GenericWrappers;

import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TS_001_Create_JRI_Account extends GenericWrappers{
	JRI_Locators loc = new JRI_Locators();
	int headCelValue ;
	Row excelRow;
	Cell excelCell;
	/*
	 * public TS_001_Create_JRI_Account() {
	 * loadTestData("./src/test/resources/Properties/JRI_inputdata.properties");
	 * loadExcelFile("./src/test/resources/Properties/JRI_TestCases_Practice.xlsx");
	 * }
	 */
	@Before
	public void doSomethingBefore() throws IOException {
		loadTestData("./src/test/resources/Properties/JRI_inputdata.properties");
		loadExcelFile("./src/test/resources/Properties/JRI_TestCases_Practice.xlsx");
		inputFromExcelFile(getData("SHEET_LOGIN"));

	}
	@AfterAll
	 public static void after_all() {
		closeCurrentWindow();
	}

	/*
	 * @After public void close() { closeCurrentWindow(); }
	 */
	@Given("Invoke JRI Home Page")
	public void tc_001_Invoke_the_JRI_application() throws InterruptedException {
		launchChromeBrowser();
		driver.get(getData("JRI_HomePage_URL"));

	}
	
	  @And("Verify the Create New Account page will display")
	  public void tc_002_Click_On_JRI_Create_New_Account() throws InterruptedException {
	  clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink);
	  Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(getData(
	  "JRI_CreateAccountPage_URL"))); }
	 

	@And("Verify the User Name error message")
	public void tc_003_Click_Create_Account_With_Blank_Data_Name_Get_Error_Message() throws Exception {
		//refreshCurrentWindow();
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink); 
		scrollintoviewelement(loc.CreateNewAccountPage_CreateAccount_Button);
		clickByAnylocator(loc.CreateNewAccountPage_CreateAccount_Button); 
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Name_ErrorMsg).equalsIgnoreCase(getData("ERR_NAME_BLK_MES")));
	}

	@And("Verify the Mobile error message") 
	public void	tc_004_Click_Create_Account_With_Blank_Data_Mobile_Get_Error_Message() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink); 
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Name_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW")));
		clickByAnylocator(loc.CreateNewAccountPage_CreateAccount_Button);
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Mobile_ErrorMsg).equalsIgnoreCase(getData("ERR_MOBILE_BLK_MES")));
	 }
	@And("Verify the Email error message") 
	public void	tc_005_Click_Create_Account_With_Blank_Data_Email_Get_Error_Message() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink);
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		System.out.println("in 005 head value" +headCelValue);
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Name_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW")));
			
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_MOBILE_NUNMBER"));
		System.out.println("in 005 head value" +headCelValue);
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Mobile_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		clickByAnylocator(loc.CreateNewAccountPage_CreateAccount_Button);
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Email_ErrorMsg).equalsIgnoreCase(getData("ERR_EMAIL_BLK_MES")));
	 }
	
	

	@And("Verify the Password error message") 
	public void tc_006_Click_Create_Account_With_Blank_Data_Password_Get_Error_Message() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink);
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Name_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_MOBILE_NUNMBER"));
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Mobile_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_EMAIL"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Email_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW")));
		
		clickByAnylocator(loc.CreateNewAccountPage_CreateAccount_Button);
		
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Password_ErrorMsg).equalsIgnoreCase(getData("ERR_PASS_BLK_MES")));
	 }
	@And("Verify the Term error message") 
	public void tc_007_Click_Create_Account_VeriFy_Term_Get_Error_Message() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink);
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Name_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_MOBILE_NUNMBER"));
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Mobile_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_EMAIL"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Email_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_PASS"));
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Password_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		clickByAnylocator(loc.CreateNewAccountPage_CreateAccount_Button);
		
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Terms_ErrorMsg).equalsIgnoreCase(getData("ERR_TERM_MES")));
	 }
	@And("Check the Refresh feature") 
	public void tc_008_Click_Create_New_Account_With_Data_To_Refresh_Page() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink);
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Name_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_MOBILE_NUNMBER"));
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Mobile_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_EMAIL"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Email_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_PASS"));
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Password_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink); 
		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(getData("JRI_CreateAccountPage_URL")));
	 }
	
	
	@And("Verify the Invalid data validation messages") 
	public void tc_009_Click_Create_Account_With_Data_To_Refresh_Page() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink);
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Name_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW_ADD")));
		
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_MOBILE_NUNMBER"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Mobile_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW_ADD")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_EMAIL"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Email_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW_ADD")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_PASS"));
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Password_EditBox,headCelValue,Integer.parseInt(getData("INVALID_DATA_ROW_ADD")));
		
		clickByAnylocator(loc.CreateNewAccountPage_CreateAccount_Button); 
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Name_With_Ivalid_Input_ErrorMsg).equalsIgnoreCase(getData("ERR_NAME_INV_MES")));
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Mobile_With_Ivalid_Input_ErrorMsg).equalsIgnoreCase(getData("ERR_MOBILE_INV_MES")));
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Email_With_Ivalid_Input_ErrorMsg).equalsIgnoreCase(getData("ERR_EMAIL_INV_MES")));
		Assert.assertTrue(GetTextValue(loc.CreateNewAccountPage_Password_With_Ivalid_Input_ErrorMsg).equalsIgnoreCase(getData("ERR_PASS_INV_MES")));
	 }
	
	@And("Check user will able to Create an account with valid data") 
	public void tc_010_Click_Create_Account_With_Valid_Data_To_Create_Account() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Create_New_Account_HyperLink);
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Name_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		String nameUsed = cellValue(Integer.parseInt(getData("VALID_DATA_ROW")),headCelValue);
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_MOBILE_NUNMBER"));
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Mobile_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_EMAIL"));
		sendKeyByAnyLocator(loc.CreateNewAccountPage_Email_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_PASS"));
		sendKeyByAnyLocatorNumeric(loc.CreateNewAccountPage_Password_EditBox,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		
		
		clickByAnylocator(loc.CreateNewAccountPage_terms_CheckBox);
		System.out.println("The Name Enter in the fielad :"+nameUsed);
		clickByAnylocator(loc.CreateNewAccountPage_CreateAccount_Button); 
		
		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(getData("JRI_DirectoryPage_URL")));
		Thread.sleep(5000);
		System.out.println("from the page "+noSpaceInStringConvertLowercase(GetTextValue(loc.DirectoryPage_Hello_Name_Msg)));
		System.out.println("From the add propertie and name "+noSpaceInStringConvertLowercase(getData("HELLO")+nameUsed));
		
		Assert.assertTrue(noSpaceInStringConvertLowercase(GetTextValue(loc.DirectoryPage_Hello_Name_Msg)).equalsIgnoreCase(noSpaceInStringConvertLowercase(getData("HELLO")+nameUsed)));
		
	 }
	
	
}
