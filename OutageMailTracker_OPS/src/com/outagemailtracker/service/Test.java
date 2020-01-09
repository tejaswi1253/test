package com.outagemailtracker.service;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			Incident inc=new Incident();
//			inc.setBcc("bcc");
//			inc.setBusinessImpact("businessImpact");
//			inc.setContactTeam("contactTeam");
//			inc.setDataLoss("dataLoss");
//			inc.setEscalationContact("escalationContact");
//			inc.setFrom("from");
//			inc.setIncident("incident");
//			inc.setIssueType("issueType");
//			inc.setRegion("region");
//			inc.setStopTime(new Date());
//			inc.setStreamType("streamType");
//			inc.setSubject("subject");
//			inc.setTo("jeevan.kuduvaravindran@bp.com");
//			
//			//System.out.println("Incident Created: INC"+Factory.createIncidentDAO().insertIncident(inc));
//			inc=(Incident)Factory.createIncidentDAO().getIncidentById(4);
//			System.out.println("INC"+inc.getIncId()+"   Incident - "+inc.getIncident());	
//			Factory.createRegionDAO().getAllRegions();
//			Date d=new Date();
//			System.out.println(d);
//			SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
//			System.out.println(sdf.format(d));
			ClassLoader classLoader = Test.class.getClassLoader();
			File f=new File(classLoader.getResource("notificationHeader.jpg").getFile());
			if(f.exists()) {
				System.out.println("File exists");
				System.out.println(f.getAbsolutePath());
				System.out.println(f.getCanonicalPath());
			}else {
				System.out.println("File not present");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
