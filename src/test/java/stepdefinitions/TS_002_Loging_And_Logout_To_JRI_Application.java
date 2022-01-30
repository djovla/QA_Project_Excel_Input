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
import io.cucumber.java.en.Then;

public class TS_002_Loging_And_Logout_To_JRI_Application extends GenericWrappers {
	JRI_Locators loc = new JRI_Locators();
	int headCelValue ;
	Row excelRow;
	Cell excelCell;
	
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
	
	@And("Navigate to Sing in page")
	  public void tc_012_Click_On_JRI_Sign_In_Link() throws InterruptedException {
	  clickByAnylocator(loc.JRIHomePage_Sing_In_Account_HyperLink);
	  Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(getData(
	  "JRI_SingInPage_URL"))); }
	
	@Then("Check the Validation Message")
	public void tc_014_Click_Secure_Sign_In_With_Blank_Data_Name_Get_Error_Message() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Home_Button);
		clickByAnylocator(loc.JRIHomePage_Sing_In_Account_HyperLink);
		
		clickByAnylocator(loc.SingInPage_SecureSignIn_Button); 
		
		Assert.assertTrue(GetTextValue(loc.SingInPage_Email_ErrorMsg).equalsIgnoreCase(getData("SIGIN_EMAIL_BLK_MES")));
	}
	
	@Then("Verify user will be able to login with valid data") 
	public void tc_015_Click_Create_Account_With_Valid_Data_To_Create_Account() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Home_Button);
		clickByAnylocator(loc.JRIHomePage_Sing_In_Account_HyperLink);
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_EMAIL"));
		sendKeyByAnyLocator(loc.SingInPage_Email_Input,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		String nameUsed = cellValue(Integer.parseInt(getData("VALID_DATA_ROW")),headCelValue);
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_PASS"));
		sendKeyByAnyLocatorNumeric(loc.SingInPage_Password_Input,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		Thread.sleep(10000);
		enterTextCaptcha(loc.SingInPage_Captcha_Input);
		clickByAnylocator(loc.SingInPage_SecureSignIn_Button);
		//clickByAnylocator(loc.CreateNewAccountPage_terms_CheckBox);
		System.out.println("The Name Enter in the fielad :"+nameUsed);
		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(getData("JRI_DirectoryPage_URL")));
		Thread.sleep(10000);
		System.out.println("from the page "+noSpaceInStringConvertLowercase(GetTextValue(loc.DirectoryPage_Hello_Name_Msg)));
		System.out.println("From the add propertie and name "+noSpaceInStringConvertLowercase(getData("HELLO")+nameUsed));
		
		Assert.assertTrue(noSpaceInStringConvertLowercase(GetTextValue(loc.DirectoryPage_Hello_Name_Msg)).equalsIgnoreCase(noSpaceInStringConvertLowercase(getData("HELLO")+nameUsed)));
		
		
	 }
	
	@And("Check the logout feature") 
	public void tc_016_Click_Sign_Out_With_Check_Log_Out() throws Exception {
		closeCurrentWindow();
		launchChromeBrowser();
		driver.get(getData("JRI_HomePage_URL"));
		clickByAnylocator(loc.JRIHomePage_Sing_In_Account_HyperLink);
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_EMAIL"));
		sendKeyByAnyLocator(loc.SingInPage_Email_Input,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_USERNAME"));
		String nameUsed = cellValue(Integer.parseInt(getData("VALID_DATA_ROW")),headCelValue);
		
		headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_PASS"));
		sendKeyByAnyLocatorNumeric(loc.SingInPage_Password_Input,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
		Thread.sleep(10000);
		enterTextCaptcha(loc.SingInPage_Captcha_Input);
		clickByAnylocator(loc.SingInPage_SecureSignIn_Button);
		
		Thread.sleep(10000);
		clickByAnylocator(loc.SingInPage_Sing_Out_Link);
		System.out.println("The Name Enter in the fielad :"+nameUsed);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(getData("JRI_Home_URL")));
		
	 }
	
	@And("Verify the Forgot password feature")
	public void tc_017_Click_Forgot_Password_Verify_Popup_Message() throws Exception {
		clickByAnylocator(loc.JRIHomePage_Home_Button);
		clickByAnylocator(loc.JRIHomePage_Sing_In_Account_HyperLink);
		Thread.sleep(3000);
		clickByAnylocator(loc.SingInPage_Forgot_Password_Link); 
		Thread.sleep(3000);
		Assert.assertTrue(noSpaceInStringConvertLowercase(GetTextValue(loc.ForgotPasswordPage_Popup_Text_Td)).equalsIgnoreCase(noSpaceInStringConvertLowercase(getData("PASSWORD_FORGOT_POPUP_TEXT"))));
	}
	
	@And("Check the Web Elements on Forgot Password Page")
	public void tc_018_Click_Forgot_Password_Check_Elements_Popup_Message() throws Exception {
		refreshCurrentWindow();
		clickByAnylocator(loc.JRIHomePage_Sing_In_Account_HyperLink);
		Thread.sleep(3000);
		clickByAnylocator(loc.SingInPage_Forgot_Password_Link); 
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(loc.ForgotPasswordPage_Popup_Email_Input).isDisplayed());
		Assert.assertTrue(driver.findElement(loc.ForgotPasswordPage_Popup_Get_Password_Button).isDisplayed());
		
	}
	@And("Check the Validation Messages")
	public void tc_019_Click_Forgot_Password_Check_Elements_Popup_Message() throws Exception {
		refreshCurrentWindow();
		clickByAnylocator(loc.JRIHomePage_Sing_In_Account_HyperLink);
		Thread.sleep(3000);
		clickByAnylocator(loc.SingInPage_Forgot_Password_Link); 
		Thread.sleep(3000);
		clickByAnylocator(loc.ForgotPasswordPage_Popup_Get_Password_Button);
		Assert.assertTrue(noSpaceInStringConvertLowercase(GetTextValue(loc.ForgotPasswordPage_Popup_Text_Td)).equalsIgnoreCase(noSpaceInStringConvertLowercase(getData("PASSWORD_FORGOT_POPUP_TEXT"))));
		Assert.assertTrue(noSpaceInStringConvertLowercase(GetTextValue(loc.ForgotPasswordPage_Popup_Text_Blk_Email_ErrorMsg)).equalsIgnoreCase(noSpaceInStringConvertLowercase(getData("PASSWORD_FORGOT_POPUP_BLK_EMAIL_MES"))));
		
	}
	
	
	  @Then("Verify email Forgot your password feature") 
	  public void tc_020_Click_Forgot_Password_Verify_Email_Feature() throws Exception {
	  refreshCurrentWindow();
	  clickByAnylocator(loc.JRIHomePage_Sing_In_Account_HyperLink);
	  Thread.sleep(3000); 
	  clickByAnylocator(loc.SingInPage_Forgot_Password_Link);
	  Thread.sleep(3000);
	  
	  headCelValue =getCellNumberByValue(Integer.parseInt(getData("TTITLE_DATA_HEAD_ROW")),getData("TITLE_EMAIL"));
	  sendKeyByAnyLocator(loc.ForgotPasswordPage_Popup_Email_Input,headCelValue,Integer.parseInt(getData("VALID_DATA_ROW")));
	  clickByAnylocator(loc.ForgotPasswordPage_Popup_Get_Password_Button);	
	  Thread.sleep(3000);
	  //String message_Confirmation = GetTextValue(loc.ForgotPasswordPage_Popup_Text_Blk_Email_ErrorMsg);
	 // System.out.println(message_Confirmation);
	  String message_Confirmation = ConcatListElementConsole(getListElementByLocator(loc.ForgotPasswordPage_Popup_Text_Blk_Email_ErrorMsg));
		// System.out.println(getData("PASSWORD_FORGOT_POPUP_EMAIL_SENT_CONFIRMATION_MES"));
	  System.out.println(message_Confirmation);
	  System.out.println(noSpaceInStringConvertLowercase(getData(
		  "PASSWORD_FORGOT_POPUP_EMAIL_SENT_CONFIRMATION_MES")));
	  Assert.assertEquals(message_Confirmation, noSpaceInStringConvertLowercase(getData(
		  "PASSWORD_FORGOT_POPUP_EMAIL_SENT_CONFIRMATION_MES")));
		/*
		 * Assert.assertTrue(message_Confirmation.equals(
		 * noSpaceInStringConvertLowercase(getData(
		 * "PASSWORD_FORGOT_POPUP_EMAIL_SENT_CONFIRMATION_MES"))));
		 */
		 
		  }
	  @AfterStep
		public void addScreenshot(Scenario scenario){

			//validate if scenario has failed
			takeFailScreenshots(scenario);
			
		}
	 
}
