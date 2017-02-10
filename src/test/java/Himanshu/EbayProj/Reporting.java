package Himanshu.EbayProj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting {
	
	public static  BufferedWriter initializeBufferWriter() {
		File sampleFile = new File("automatioReportFile.html");

		FileWriter fw = null;
		try {
			fw = new FileWriter(sampleFile,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);	
		return bw;
	}


	public static void createReportHeader(BufferedWriter bw2) throws IOException {

		bw2.append("<h1>EBAY TEST AUTOMATION SUMMARY REPORT </h1>");
		bw2.newLine();
		bw2.append("<html>");
		bw2.append("<head>");
		bw2.append("<style>");
		bw2.append("table, th, td {border: 1px solid black;}");
		bw2.append("</style>");
		bw2.append("</head>");
		bw2.append("<body>");
		bw2.append("<table style='width:100%'  background-color: #f1f1c1>");
		bw2.append("<tr bgcolor='#6495ED'>");
		bw2.append("<th>Test Scenario Summary</th>");
		bw2.append("<th>Test Status</th>");
		bw2.append("<th>Run Time Value</th>");
		bw2.append("<th>Start Time</th>");
		bw2.append("<th>End Time</th>");
		bw2.append("</tr>");
	}

	public static void createHTMLFile() throws IOException {
		// TODO Auto-generated method stub
		
		
		File f = null;

		f = new File("automatioReportFile.html");
		boolean exists = f.exists();
		if(exists){
			f.delete();
		}else{
			f.createNewFile();
		}
		
	
	}
	
//	public static void reportMessage(BufferedWriter bw2, String testScenario,String resultStatus ,String runTimeValue ,String startTime ,String endTime) throws IOException {
//		
//		String tblColorStr;
//		
//		if(resultStatus=="Pass"){
//			 tblColorStr="bgcolor='#90EE90'";
//		}else{
//			 tblColorStr="bgcolor='#FA8072'";
//		}
//		String logMessage = "<td>" + testScenario + "</td>  <td>"+  resultStatus +  "</td> <td>" + runTimeValue + "</td> <td>" +startTime + "</td> <td>" + endTime +"</td>";     
//		bw2.append("<tr " + tblColorStr + ">" + logMessage + "</tr>");
//
//	}
	
	public static void concludeReport(BufferedWriter bw2) throws IOException {
		// TODO Auto-generated method stub
		bw2.append("</body>");
		bw2.append("</table>");
		bw2.close();
	}

	public static String getTime(){
		
	       DateFormat df = new SimpleDateFormat("HH:mm:ss");
	       Date dateobj = new Date();
	       return df.format(dateobj);
	}


	public static void reportMessage(BufferedWriter bw2, String testScenario, String resultStatus, String runTimeValue,
			String startTime, String endTime) throws IOException {
		// TODO Auto-generated method stub
		String tblColorStr;
		
		if(resultStatus=="Pass"){
			 tblColorStr="bgcolor='#90EE90'";
		}else{
			 tblColorStr="bgcolor='#FA8072'";
		}
		String logMessage = "<td>" + testScenario + "</td>  <td>"+  resultStatus +  "</td> <td>" + runTimeValue + "</td> <td>" +startTime + "</td> <td>" + endTime +"</td>";     
		bw2.append("<tr " + tblColorStr + ">" + logMessage + "</tr>");

		
	}






}
