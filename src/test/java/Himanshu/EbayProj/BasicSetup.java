package Himanshu.EbayProj;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileWriter;
import java.io.IOException;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Test;

//import junit.framework.Assert;

public class BasicSetup {


	public static WebDriver driver;
	public static String url;
	public static String browserName;
	public static String searchString;
	public static String screenSize;
	File f = null;
	public static BufferedWriter bw=null;
	static String startTime;
	static String endTime;

	@BeforeSuite
	public  void gettingURLFromConfigFile() throws InterruptedException, IOException
	{
		Reporting.createHTMLFile();
		//createHTMLFile();
		bw=Reporting.initializeBufferWriter();
		Reporting.createReportHeader(bw);

		File datafile = new File("testDataFile");
		FileInputStream fileinput = null;
		try {
			fileinput = new FileInputStream(datafile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		//load properties file
		try {
			prop.load(fileinput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporting.reportMessage(bw,"Reading The Config File","Pass" ,"","","");
		
		browserName=prop.getProperty("Browser");
		if(browserName==""){
			Reporting.reportMessage(bw,"Failed To Read Browser Name  From Config File SuccessFully ","Fail",browserName,"","" );
		}else{
			Reporting.reportMessage(bw,"Browser Name Has Been Read From Config File SuccessFully ","Pass",browserName,"","" );
		}
		
		url=prop.getProperty("URL");
		
		if(url==""){
			Reporting.reportMessage(bw,"Failed To Read URL   From Config File SuccessFully ","Fail",url,"","" );
		}else{
			Reporting.reportMessage(bw,"URL  Has Been Read From Config File SuccessFully ","Pass",url,"","" );
		}

		searchString=prop.getProperty("SearchKeyWord");
		if(searchString==""){
			Reporting.reportMessage(bw,"Failed To Read Product Search String From Config File SuccessFully ","Fail",searchString,"","" );
		}else{
			Reporting.reportMessage(bw,"Product Search String Has Been Read From Config File SuccessFully ","Pass",searchString,"","" );
		}
		
		screenSize=prop.getProperty("ScreenSize");


	}

	@Test(priority=0)
	public static void InvokeAppInBrowser() throws IOException {
		System.out.println("*****STARTING THE TEST EXECUTIONS ****** \n");
		

		if(browserName.equals("IE")){
			startTime=Reporting.getTime();
			System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
			driver= new InternetExplorerDriver();
			endTime=Reporting.getTime();
			Reporting.reportMessage(bw,"Launching  Browser:","Pass","Browser Opened Is :"+browserName,startTime,endTime );

		}else if(browserName.equals("FF")){
			
			startTime=Reporting.getTime();
			driver = new FirefoxDriver();
			endTime=Reporting.getTime();
			Reporting.reportMessage(bw,"Launching  Browser:","Pass","Browser Opened Is :"+browserName,startTime,endTime  );
		}else if(browserName.equals("CH")){
			startTime=Reporting.getTime();
			driver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			endTime=Reporting.getTime();
			Reporting.reportMessage(bw,"Launching  Browser:","Pass","Browser Opened Is :"+browserName,startTime,endTime  );

		}
		startTime=Reporting.getTime();
		driver.manage().window().maximize();
		driver.get(url);
		String pgTitle=driver.getTitle();
		endTime=Reporting.getTime();
		boolean pgConfirmation=pgTitle.endsWith("and More | eBay");
		if(pgConfirmation){
			Reporting.reportMessage(bw,"Opening Home Page Of Ebay Site " ,"Pass"," Page Title Is :"+pgTitle,startTime,endTime  );
			
		}else{
			Reporting.reportMessage(bw,"Opening Home Page Of Ebay Site " ,"Fail"," Page Title Is :"+pgTitle,startTime,endTime  );
		}	
	}

	@AfterSuite
	public static void closeApplication() throws IOException{

		driver.quit();
		
		Reporting.concludeReport(bw);
		System.out.println("*****EXECUTION COMPLETED PLEASE CHECK THE REPORT FOR FAILURE ****** \n");
		
		System.out.println("*****TEST REPORT CAN BE FOUND IN TEST OUTPUT FOLDER -  \n  REPORT NAME IS - automationReportFile.html ******  \n");
	}
	

}


