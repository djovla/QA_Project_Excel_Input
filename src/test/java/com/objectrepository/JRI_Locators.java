package com.objectrepository;

import org.openqa.selenium.By;

public class JRI_Locators {

	public final By JRIHomePage_Create_New_Account_HyperLink = By.id("signup-link9");
	public final By JRIHomePage_Sing_In_Account_HyperLink = By.id("jriTop_signin9");
	public final By JRIHomePage_Home_Button = By.xpath("//*[@id=\"menu_home\"]/a/div");
	
    
	public final By CreateNewAccountPage_Name_EditBox = By.name("signup_name");
	public final By CreateNewAccountPage_Mobile_EditBox = By.name("signup_mobileno");
	public final By CreateNewAccountPage_Email_EditBox = By.name("signup_email");
	public final By CreateNewAccountPage_Password_EditBox = By.name("signup_password");
	public final By CreateNewAccountPage_newsletters_CheckBox = By.name("checkbox1");
	public final By CreateNewAccountPage_terms_CheckBox = By.name("checkbox");
	public final By CreateNewAccountPage_CreateAccount_Button = By.name("imgbtnSubmit");
	
	
	//Invalid input message for text input
	
	public final By CreateNewAccountPage_Name_With_Ivalid_Input_ErrorMsg = By.id("nameTD");
	public final By CreateNewAccountPage_Mobile_With_Ivalid_Input_ErrorMsg = By.id("mobilenoTD");
	public final By CreateNewAccountPage_Email_With_Ivalid_Input_ErrorMsg = By.id("emailTD");
	public final By CreateNewAccountPage_Password_With_Ivalid_Input_ErrorMsg = By.id("passwordTD");
	
	// Blank page Error messages
	public final By CreateNewAccountPage_Name_ErrorMsg = By.xpath("//span[text()='Enter your name']");
	public final By CreateNewAccountPage_Mobile_ErrorMsg = By.xpath("//span[text()='Enter mobile no.']");
	public final By CreateNewAccountPage_Email_ErrorMsg = By.xpath("//span[text()='Enter your email id']");
	public final By CreateNewAccountPage_Password_ErrorMsg = By.xpath("//span[text()='Enter your password']");
	public final By CreateNewAccountPage_Terms_ErrorMsg = By.id("tdcondition");
	//public final By CreateNewAccountPage_Terms_ErrorMsg = By
		//	.xpath("//*[text()='Please agree to the terms & conditions']");
//tdcondition
	// Invalid test data error messages
	public final By CreateNewAccountPage_Name_InvalidData_ErrorMsg = By.xpath("//*[text()='Enter correct name']");
	public final By CreateNewAccountPage_Mobile_InvalidData_ErrorMsg = By
			.xpath("//*[text()='Enter valid 10 digit mobile no.']");
	public final By CreateNewAccountPage_Email_InvalidData_ErrorMsg = By.xpath("//*[text()='Enter valid email id']");
	public final By CreateNewAccountPage_Password_InvalidData_ErrorMsg = By
			.xpath("//*[text()='Enter 6-digit numeric characters only']");
	
	//Directory Page
	public final By DirectoryPage_Hello_Name_Msg = By.xpath("//*[@id=\"jriTop_lblUserMsg\"]");
	
	//Sign IN Page
	public final By SingInPage_SecureSignIn_Button = By.name("imgbtnSignin");
	public final By SingInPage_Password_Input = By.name("txtPasswd");
	public final By SingInPage_Email_Input = By.id("txtUserName");
	public final By SingInPage_Email_ErrorMsg = By.id("MessageCaption2");
	public final By SingInPage_Sing_Out_Link = By.id("jriTop_signOut");
	public final By SingInPage_Forgot_Password_Link = By.id("forgotpassword");
	
	//Forgot Password Page
	public final By ForgotPasswordPage_Popup_Text_Td = By.xpath("//div[3]/div[2]/table/tbody/tr[1]/td");
	public final By ForgotPasswordPage_Popup_Email_Input = By.name("forgotpass$txtEmailId");
	public final By ForgotPasswordPage_Popup_Get_Password_Button = By.id("forgotpass_imgbtnSubmit");
	public final By ForgotPasswordPage_Popup_Text_Blk_Email_ErrorMsg = By.id("forgotpass_lblError");
	//public final By ForgotPasswordPage_Popup_Text_Email_Sent_Confirmation = By.id("//div[3]/div[2]/table/tbody/tr[4]/td/div/span/span/text()");
	//*[@id="heading"]/span/a/img
	//forgotpass_lblError
	
	
	
	
	
	
	
	 
	

}
