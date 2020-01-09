package com.outagemailtracker.resources;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class OmtConfig {

	public static String getMessageFromProperties(String exceptionName){
		Properties properties = new Properties();
		try {
			
			
				
				properties.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(
								"com/outagemailtracker/resources/messages.properties"));
			
			
		} catch (IOException e) {
				OmtLogger
				.logError("OmtConfig", "getErrorMessage", e.toString());
			
		}
		String message=properties.getProperty(exceptionName);
		if(message==null){
		message=getErrorMessageEnglish(exceptionName);
		}
		return message;
	
		
	}

	private static String getErrorMessageEnglish(String exceptionName){
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(
							"com/outagemailtracker/resources/messages.properties"));
		} catch (IOException e) {
			OmtLogger
			.logError("OmtConfig", "getErrorMessage", e.toString());	
			
		}
		return properties.getProperty(exceptionName);
	}
	
	public static String getPath(String path) {
	//	Properties properties = new Properties();
		try {

			if (path.equalsIgnoreCase("resourcePath")) {
				return "../page-resources/images/";
			}
			// constructing path for products in Desktop
			if (path.equalsIgnoreCase("imageUrlPath")) {
				String userHome = System.getProperty("user.home");
				String tillDesktop = userHome + "\\Desktop\\products\\";
				File myFile = new File(tillDesktop);
				if (!myFile.exists()) {
					myFile.mkdir();
				}
				return tillDesktop;
			}

			if (path.equalsIgnoreCase("errorLoggerPath")) {
				String userHome = System.getProperty("user.home");
				String tillDesktop = userHome + "\\Desktop\\logger\\";
				File myFile = new File(tillDesktop);
				if (!myFile.exists()) {
					myFile.mkdir();
				}
				return tillDesktop + "ErrorLogFile.txt";
			}

		} catch (Exception e) {

			OmtLogger.logError("OmtConfig", "getPath", e.toString());
		}
		return "";
	}
}
