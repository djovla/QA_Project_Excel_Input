package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;

import java.util.Date;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;

import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;

//Here we will store re-usable Functions/Methods
public class GenericWrappers extends BaseClass {

	/********* Chrome browser launch ****************/

	public void launchChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	/********* Firefox browser launch ****************/

	public void launchFirefoxBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	/********* Safari browser launch ****************/
	public void launchSafariBrowser() {
		WebDriverManager.safaridriver().setup();
		driver = new SafariDriver();
		driver.manage().window().maximize();
	}

	/********* Edge browser launch ****************/

	public void launchEdgeBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}

	/***** Validate the web element is enabled or not? *****/
	public void ValidateWebelementIsEnabledOrNot(By locator) {
		WebElement ele = driver.findElement(locator);
		if (ele.isEnabled()) {
			System.out.println("The webelement is Enabled state***");
		} else {
			System.out.println("The webelement is NOT in Enabled state***");
		}
	}

	/** sendkeys by any locators for Editbox/Textbox *********/
	public void sendKeyByAnyLocator(By locator, String testdata) {
		WebElement ele = driver.findElement(locator);
		if (ele.isDisplayed()) {
			if (ele.isEnabled()) {
				ele.clear();
				ele.sendKeys(testdata);
			} else {
				System.out.println("element is disable state, please check the locator***");
			}
		} else {
			System.out.println("element is not displayed, please check the locator***");
		}
	}

	/************ click on any webelement *************/
	public void clickByAnylocator(By locator) {
		WebElement ele = driver.findElement(locator);
		if (ele.isDisplayed() && ele.isEnabled()) {
			ele.click();
		} else {
			System.out.println("The given locator is not displayed on DOM or not in enabled state****");
		}
	}

	/************ Method added for the radio button *************/
	public void clickByAnylocatorButton(By locator) {
		WebElement ele = driver.findElement(locator);
		if (ele.isEnabled()) {
			ele.click();
		} else {
			System.out.println("The given locator is not displayed on DOM or not in enabled state****");
		}
	}

	/******** Implicit wait *****************//*
												 * public void implicitWait(int TimeInMillySeconds) {
												 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
												 * TimeInMillySeconds)); }
												 */

	/********* Read Data from Properties file *********************/

	// to get the test data from Property file
	public void loadTestData(String path) {
		file = new File(path);
		fi = null;
		try {
			fi = new FileInputStream(file);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(new InputStreamReader(fi, Charset.forName("UTF-8")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//renamed and modified method  to initialize parameter for the excel file
	public void loadExcelFile(String path) {
		pathExcel = path;
		filexcel = new File(path);
		excelfi = null;
		try {
			excelfi = new FileInputStream(filexcel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		}
	
	public String getData(String key) {
		String keyvlaue = null;
		try {
			keyvlaue = prop.getProperty(key);
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}
		return keyvlaue;
	}

	/**********
	 * Verify the web element has present or not on DOM (Document Object Model)
	 ***************/
	public void findTheWebelementByAnyLocator(By locator) {
		// Find the given locator, if the locator has present on Screen then the size of
		// locator is ONE '1'
		// else size of locator is ZERO '0'
		if (driver.findElements(locator).size() > 0) {
			System.out.println("The Given locator is present on DOM****" + locator);
		} else {
			System.out.println("The Given locator is NOT present on DOM, please check again****" + locator);
		}

	}

	/*** navigational methods ************/
	public void refreshScreen() {
		driver.navigate().refresh();
		implicitWait(5);
	}

	/*********** timestamp **********/
	public String timestamp() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("ddMMMyyy_HHmmss");
		String timeTamp = df.format(d);
		return timeTamp;
	}

	/*****
	 * takescreenshot
	 * 
	 * @throws Exception
	 ************/
	public void takeScreenshot() throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = ".//Screenshots//";
		FileHandler.copy(scrFile, new File(screenshotPath + "Test" + timestamp() + ".PNG"));
		System.out.println("Screenshot taken*** ");
	}

	public void takeScreenshot(ITestResult res) throws Exception {

		projectDir = System.getProperty("user.dir");
		// code to change to work on mac system
		// screenshotPath = projectDir + "\\Screenshots\\";

		screenshotPath = projectDir + "//Screenshots//";
		className = res.getTestClass().getName().trim();//
		methodName = res.getName().trim();//
		// STATUS_PackageName.ClassName_MethodName_Timestamp.PNG
		if (res.getStatus() == ITestResult.SUCCESS) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "PASS_" + className + "_" + methodName + "_" + timestamp() + ".PNG"));
		}
		if (res.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(scrFile,
					new File(screenshotPath + "FAIL_" + className + "_" + methodName + "_" + timestamp() + ".PNG"));
		}

	}

	/***************** Classes Added JosepH ***********************/
	/***
	 * select value Randomly using the option the method use the index
	 ************/
   public void enterTextCaptcha(By locator) {
	   String str = JOptionPane.showInputDialog("Enter Captcha");
	   sendKeyByAnyLocator(locator, str);  
   }
   
	public void selectOptionRandomly(By locator) {
		Select se = new Select(driver.findElement(locator));
		int number = se.getOptions().size();
		Random r = new Random();

		se.selectByIndex(r.nextInt(number));

	}

	/*** Click One Randomly one Button with same value in the page ************/
	public void RandomClickOneButton(List<WebElement> buttons) {
		int number = buttons.size();
		Random r = new Random();
		buttons.get(r.nextInt(number)).click();

	}
	

	/*** get Text from element by locator in the page ************/
	public String getTextByLocator(By locator) {
		return driver.findElement(locator).getText();
	}

	/*** Get List of Element by locator same value in the page ************/
	public List<WebElement> getListElementByLocator(By locator) {
		return driver.findElements(locator);
	}

	/*** Print to Console List of Element in the page ************/
	public void printListElementConsole(List<WebElement> list) {
		for (WebElement element : list) {
			System.out.println(element.getText());
		}
	}
	/*** Print to Console List of Element in the page ************/
	public String ConcatListElementConsole(List<WebElement> list) {
		String string ="";
		for (WebElement element : list) {
			string += element.getText();
		}
		return noSpaceInStringConvertLowercase(string);
	}
	/************* Close Current Window ***************/
	public void closeCurrentWindow() {
		driver.close();
	}
	/************* Close Current Window ***************/
	public void refreshCurrentWindow() {
		driver.navigate().refresh();
	}
	/************************
	 * Tracking the window and store the value in trackofWindow
	 *********/
	public void trackingOfWindow() {
		trackOfWindow = driver.getWindowHandle();
	}

	/**********
	 * Overload Method to handle popup whit accuracy using the trackOfWindow
	 ********************/
	public void popupHandleToCloseAllChildWindow() throws InterruptedException {
		// get the main windown name from the static variable
		if (!trackOfWindow.equalsIgnoreCase("")) {
			String mainWindowName = trackOfWindow;
			System.out.println("mainWindowName:" + mainWindowName);

			Set<String> allWindowNames = driver.getWindowHandles();
			System.out.println("allWindowNames:" + allWindowNames);

			// Close the child window (popups)
			for (String abc : allWindowNames) {// 2
				// validate the window name is parent window /Child window?
				if (!mainWindowName.equals(abc)) {
					// switch to child window
					driver.switchTo().window(abc);
					Thread.sleep(3000);
					// Close my child window
					driver.close();
				}
			}
			// move cursor point to back to mainwindow
			driver.switchTo().window(mainWindowName);
			// driver.close();
		} else {
			System.out.println("There is no active Window register in the track Window");
		}
	}

	/*****
	 * Method that initialize the Actions Variable from the Base Class
	 ***********/
	public void initiateAction() {
		act = new Actions(driver);
	}

	/**************
	 * Method that Drag From and Drop to the destination
	 ************************/
	public void dragAndDrop(By from, By to) {
		initiateAction();
		WebElement efrom = driver.findElement(from);
		WebElement eto = driver.findElement(to);
		act.dragAndDrop(efrom, eto).build().perform();
	}

	public void moveToElement(By locator) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.moveToElement(element).perform();
	}

	public void moveToElementAndClick(By locator) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.moveToElement(element).click().perform();
	}

	public void moveMouseToElement(By locator) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.moveToElement(element).build().perform();
	}

	// Right click on Element method
	public void rightClickOnElement(By locator) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.contextClick(element).perform();
	}

	// Change Input to Capital Letter in the input text
	public void ChangeInputToCapitalOnElement(By locator, String input) {
		initiateAction();
		WebElement element = driver.findElement(locator);
		act.moveToElement(element).click().keyDown(Keys.SHIFT).sendKeys(input).build().perform();
	}

	// overload method sendKeyByAnyLocator--------------------------
	public void sendKeyByAnyLocator(By locator, Keys key) {
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.clear();
				element.sendKeys(key);
			} else {
				System.out.println("element is disable state, please check the locator***");
			}
		} else {
			System.out.println("element is not displayed, please check the locator***");
		}
	}
	
	/****************File manipulation**************************************/
	public void inputFromExcelFile(String path,String sheet) throws IOException {
		 excelfi = new FileInputStream(path);
		 ww = new XSSFWorkbook(excelfi);
		s = ww.getSheet(sheet);
		}
	/*orverload method for input in the excel file */
	public void inputFromExcelFile(String sheet) throws IOException {
		 ww = new XSSFWorkbook(excelfi);
		s = ww.getSheet(sheet);
		}
	
	 public int[] countRowandCell() throws IOException {
		   int [] rowCell =new int[2];
		    rowCell[0] = s.getLastRowNum();
			Row r1 =s.getRow(0);
			rowCell[1] =r1.getLastCellNum();
			return rowCell;
	 }
	 
	 public int[] countRowandCell(int row) throws IOException {
		   int [] rowCell =new int[2];
			/* rowCell[0] = s.getRow(5); */
			Row r1 =s.getRow(5);
			rowCell[0] = r1.getRowNum();
			rowCell[1] =r1.getLastCellNum();
			return rowCell;
	 }
	 
	 public String cellValue(int row,int cell) throws IOException {
		   
			Row r1 =s.getRow(row);
			Cell rowCell = r1.getCell(cell);
			
			return rowCell.getStringCellValue();
	 }
	 
	 /* this method is to get the value base on the head name
	  *  so we can use for the valid and invalid Data cell*/
	 public int getCellNumberByValue(int ro,String head_Title) {
		    int result = -1;
			Row excelRow = s.getRow(ro);
			int num = excelRow.getLastCellNum();
			Cell excell;
			for(int i=2; i<num; i++) {
				excell = excelRow.getCell(i);
				String head =excell.getStringCellValue();
				if(head.equalsIgnoreCase(head_Title)) {
					result = i;
					break;
				}
				
			}
			return result;
		}
	 
	 
	public void outputToExcelFile() throws IOException {
		FileOutputStream fos  = new FileOutputStream(pathExcel);
		 ww.write(fos);
		 ww.close();
		}
	public void sendKeyByAnyLocatorNumeric(By locator, int row, int cel) {
		WebElement element = driver.findElement(locator);
		 Row excelRow = s.getRow(cel);
		 Cell excelCell = excelRow.getCell(row);
		//vvicto System.out.println(excelCell.getStringCellValue());
		 
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.clear();
				Double doubleValue = excelCell.getNumericCellValue();
				BigDecimal data1 = new BigDecimal(doubleValue.toString());
				long data = data1.longValue();
				String key = String.valueOf(data);
				element.sendKeys(key);
				//element.sendKeys(Integer.toString((int) excelCell.getNumericCellValue()));
				System.out.println("the Numeric value with no conversion :"+excelCell.getNumericCellValue());
				
			} else {
				System.out.println("element is disable state, please check the locator***");
			}
		} else {
			System.out.println("element is not displayed, please check the locator***");
		}
	}
	
	
	
	public void sendKeyByAnyLocator(By locator, int row, int cel) {
		WebElement element = driver.findElement(locator);
		 Row excelRow = s.getRow(cel);
		 Cell excelCell = excelRow.getCell(row);
		 System.out.println(excelCell.getStringCellValue());
		 
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.clear();
				element.sendKeys(excelCell.getStringCellValue().toString());
			} else {
				System.out.println("element is disable state, please check the locator***");
			}
		} else {
			System.out.println("element is not displayed, please check the locator***");
		}
	}
	/* return string with no space */
	public String noSpaceInStringConvertLowercase(String str) {
		String count =str.replaceAll("\\s","");
		return count.toLowerCase();
	}
	/********************* END Joseph Method adding *************************/

	/******************** Frames Handling *******************/

	public int iframeCount() {
		driver.switchTo().defaultContent();
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		int numberOfFrames = 0;
		numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are: " + numberOfFrames);
		return numberOfFrames;
	}

	public void switchToFrameByInt(int i) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}

	public int loopAllFramesForElement(By locator) {
		int elementpresenceCount = 0;
		int loop = 0;
		int maxFramaecount = iframeCount();// 3
		// if given locater has present on webpage, then the element size would be '1'
		// else '0'
		// elementpresenceCount = driver.findElements(locator).size();// 0
		while (elementpresenceCount == 0 && loop < maxFramaecount) {
			try {
				switchToFrameByInt(loop);
				elementpresenceCount = driver.findElements(locator).size();// 2
				System.out.println("Try LoopAllframesAndReturnWebEL : " + loop + "; ElementpresenceCount: "
						+ elementpresenceCount);
				if (elementpresenceCount > 0 || loop > maxFramaecount) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + loop + "; " + ex);
			}
			loop++;// 1
		}
		return elementpresenceCount;
	}

	/************
	 * popupHandle
	 * 
	 * @throws InterruptedException
	 *********************************/
	public void popupHandleToCloseTheChildWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		for (String abc : allWindowNames) {// 2
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(abc)) {
				// switch to child window
				driver.switchTo().window(abc);
				Thread.sleep(3000);
				// Close my child window
				driver.close();
			}
		}
		// move cursor point to back to mainwindow
		driver.switchTo().window(mainWindowName);
		// driver.close();
	}

	public void navigateToPopupWindow() throws InterruptedException {
		// get the main windown name
		String mainWindowName = driver.getWindowHandle();
		System.out.println("mainWindowName:" + mainWindowName);

		Set<String> allWindowNames = driver.getWindowHandles();// 4
		System.out.println("allWindowNames:" + allWindowNames);

		// Close the child window (popups)
		// for (int i = 0; i < array.length; i++) { }
		for (String string : allWindowNames) {
			// validate the window name is parent window /Child window?
			if (!mainWindowName.equals(string)) {
				// switch to child window
				driver.switchTo().window(string);
				Thread.sleep(3000);
			}
		}

	}

	/*********** SwithchToWindow using Tab ***************************/
	public void switchToNewTab() {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();// abc

		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));

		// Switch back to original window
		// driver.switchTo().window(windowHandle);
	}

	/***********
	 * SwithchToWindow using Tab then close the New Tab againg back to Parent Window
	 ***************************/
	public void switchAndCloseNewTab() {
		// Get the current window handle
		String windowHandle = driver.getWindowHandle();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		// Close the newly Opened Window
		driver.close();
		// Switch back to original window
		driver.switchTo().window(windowHandle);
	}

	/*************** Window handler ****************/

	public void WindowHandler() {
		String winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			System.out.println(driver.getCurrentUrl() + " " + driver.getTitle());

		}

	}

	/******** get text info *********/

	public void GetText(By ShahedProLocators) {
		WebElement el = driver.findElement(ShahedProLocators);
		String text = el.getText();
		System.out.println(text);

	}
	public String GetTextValue(By ShahedProLocators) {
		WebElement el = driver.findElement(ShahedProLocators);
		return (el.getText());
	}
	/************** Alert Handle *************************/
	public void alertHandleByAccept() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.accept();
	}

	public void alertHandleByDismiss() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text is: " + alertText);
		alert.dismiss();
	}

	/********************* Close Current Window ******************************/

	public void printAllDropdownValues(By locater) throws Exception {
		WebElement element = driver.findElement(locater);
		highlightElement(element);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (WebElement allvalue : dropdownValues) {
					System.out.println(allvalue.getText());
				}
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}
	}

	public void selectCustomiseOptionFromTheDropdownValues(By locater, String visibleText) throws Exception {
		WebElement element = driver.findElement(locater);
		highlightElement(element);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (int i = 0; i < dropdownValues.size(); i++) {
					System.out.println(dropdownValues.get(i).getText());

					// Select Aug option from the dropdown
					if (dropdownValues.get(i).getText().equals(visibleText)) {
						dropdown.selectByIndex(i);
						break;
					}
				}
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	/************ Javascripts methods ******************/

	public void clickUsingJavaScript(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		highlightElement(element);
		executor.executeScript("arguments[0].click();", element);

	}

	public void highlightElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
	}

	/****************** ScrollToElementBottom *****************************/

	public void ScrollToElementBottom(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		try {
			System.out.println("***ScrollToElementBottom:  ***");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		} catch (StaleElementReferenceException e) {
			System.out.println("Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());

		}
	}

	public void scrollintoviewelement(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		try {
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		} catch (StaleElementReferenceException e) {
			System.out.println("Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}

	}

	/************ waits inselenium ***********************/
	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		System.out.println("Implicit wait method used***");
	}

	public void explicitWait(By locator, int timeinSeconds) {
		WebElement element = driver.findElement(locator);
		// webdriver wait (Explicit wait)
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(timeinSeconds));
		ww.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementVisible(By locator, int time_in_seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time_in_seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementToBeClickable(By locator, int time_in_seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time_in_seconds));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForAlert(int time_in_seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time_in_seconds));
		wait.until(ExpectedConditions.alertIsPresent());
	}

}