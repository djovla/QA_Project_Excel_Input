package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;

//Here we will store the all the application varaiables
public class BaseClass {
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static File file;
	public static File filexcel;
	public static String pathExcel;
	public static FileInputStream fi;
	/** Tracking window to manage the popup window in page******/
	public static String trackOfWindow = "";
	public static Actions act ;
	
	public String projectDir;
	public String screenshotPath;
	public String className;
	public String methodName;
	
	public static FileInputStream excelfi;
	public static Workbook ww;
	public static Sheet s ;

}