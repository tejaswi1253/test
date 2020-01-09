package com.outagemailtracker.managedbeans;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.outagemailtracker.beans.Incident;
import com.outagemailtracker.beans.Resolution;
@ManagedBean
@ViewScoped
public class ViewBean {
	private Incident inc;
	private Resolution res;
	private String message;
	private String htmlText;
	private String from;
	private String to;
	private String bcc;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getHtmlText() {
		return htmlText;
	}
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Incident getInc() {
		return inc;
	}
	public void setInc(Incident inc) {
		this.inc = inc;
	}
	public Resolution getRes() {
		return res;
	}
	public void setRes(Resolution res) {
		this.res = res;
	}
	public ViewBean() {

		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String clearSession=req.getParameter("faces-redirect");
		if(clearSession!=null) {
			if(clearSession.equalsIgnoreCase("true")) {
				System.out.println("Clearing session");
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			}
		}
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> requestMap = context.getExternalContext().getSessionMap();
		this.inc= (Incident)requestMap.get("incidentView");
		this.res= (Resolution)requestMap.get("resolvedView");
		
		
		String incidentImgPath=req.getContextPath()+"/app/pages/resources/IncidentNotificationHeader.JPG";
//		System.out.println(incidentImgPath);
		String incidentMaintenanceImgPath=req.getContextPath()+"/app/pages/resources/MaintenanceNotificationHeader.JPG";
		String resImgPath=req.getContextPath()+"/app/pages/resources/ResolvedNotificationHeader.JPG";
		String resCompletedImgPath=req.getContextPath()+"/app/pages/resources/CompletedMaintenanceNotificationHeader.JPG";
		
		String announcementImgPath=req.getContextPath()+"/app/pages/resources/AnnouncementNotificationHeader.JPG";
		
		String region="";
		   
		   SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		if(this.inc != null) {	
			region=String.join("<br>", inc.getRegion());
			this.from=this.inc.getFrom();
			this.to=this.inc.getTo();
			this.bcc=this.inc.getBcc();
			if(!this.inc.getIssueType().equalsIgnoreCase("maintenance")) {
				//String stopTime=sdf.format(inc.getStopTime());
				this.htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\""+incidentImgPath+"\" /></td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;background-color:#ffc000;font-weight:bold;padding:5px 5px 5px 5px;\">Incident Summary</td>\r\n" + 
				   		"  </tr>\r\n" +
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Environment</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+region +"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		
				   		
				   		/*
				   		 * add
				   		 */
					   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Issue Type</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+this.inc.getIssueType()+"</td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"  <tr>\r\n" + 
					   		
					   		
"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Root Cause</td>\r\n" + 
	"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+this.inc.getRca()+"</td> \r\n" + 
	"  </tr>\r\n" + 
	"  <tr>\r\n" + 
	
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Incident Details</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+ this.inc.getIncident()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Streaming Stopped Time</td>\r\n" + 
				   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+stopTime+" GMT</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		/*
				   		 * ADD
				   		 */
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Impact Window</td> \r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+this.inc.getImpact_window()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Data Loss</td>\r\n" + 
				   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.inc.getDataLoss()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.inc.getContactTeam()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.inc.getEscalationContact()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"</table>";
			}else {
				this.htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\""+incidentMaintenanceImgPath+"\" /></td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;background-color:#ffc000;font-weight:bold;padding:5px 5px 5px 5px;\">Maintenance Summary</td>\r\n" + 
				   		"  </tr>\r\n" +
				   		
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Environment</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+region +"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Incident Details</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+ inc.getIncident()+"</td> \r\n" + 
				   		
"  </tr>\r\n" + 
"  <tr>\r\n" + 

"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Impact Details</td> \r\n" + 
	"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+this.inc.getImpact_window()+"</td> \r\n" + 
	"  </tr>\r\n" + 
	"  <tr>\r\n" + 
				   		
				   		"  </tr>\r\n";
		         String mnStart=sdf.format(inc.getMaintenanceFrom());
				   String mnStop=sdf.format(inc.getMaintenanceTo());
				   String maintenanceWindow=mnStart+" GMT TO <br>"+mnStop+" GMT";
				   this.htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Maintenance Window</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+maintenanceWindow+"</td> \r\n" + 
					   		"  </tr>\r\n";
				   		this.htmlText+= "  <tr>\r\n" + 
				   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Current Business Impact</td> \r\n" + 
				   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+inc.getBusinessImpact()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Data Loss</td>\r\n" + 
				   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getDataLoss()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getContactTeam()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+inc.getEscalationContact()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"</table>";
			}
		}
		if(this.res != null) {
			region=String.join("<br>", res.getRegion());
			this.from=this.res.getFrom();
			this.to=this.res.getTo();
			this.bcc=this.res.getBcc();
			if(!this.res.getIssueType().equalsIgnoreCase("maintenance")) {
				//String stopTime=sdf.format(this.res.getStopTime());
				  // String resTime=sdf.format(this.res.getResolvedTime());
				  // String outageWindow=stopTime+" GMT TO <br>"+resTime+" GMT";
				   this.htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
					   		"  <tr>\r\n" + 
					   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\""+resImgPath+"\" /></td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"  <tr>\r\n" + 
					   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;color:#abcf92;background-color:#395524;font-weight:bold;padding:5px 5px 5px 5px;\">Incident Resolution Summary</td>\r\n" + 
					   		"  </tr>\r\n" +
					   		"  <tr>\r\n" + 
					   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Environment</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+region +"</td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"  <tr>\r\n" + 
					   		
				   		
				   		/*
				   		 * add
				   		 */
					   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Issue Type</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+this.res.getIssueType()+"</td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"  <tr>\r\n" + 
					   		
					   		
"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Root Cause</td>\r\n" + 
	"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+this.res.getRca()+"</td> \r\n" + 
	"  </tr>\r\n" + 
	"  <tr>\r\n" + 
					   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Resolution Details</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+ this.res.getResolution()+"</td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"  <tr>\r\n" + 
					   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Streaming Outage Window</td>\r\n" + 
					   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+outageWindow+" </td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"  <tr>\r\n" + 
					   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Current Business Impact</td> \r\n" + 
					   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+this.res.getBusinessImpact()+"</td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"  <tr>\r\n" + 
					   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Data Loss</td>\r\n" + 
					   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.res.getDataLoss()+"</td> \r\n" + 
					   		"  </tr>\r\n"; 

				   /*if((!this.res.isOutageDataNotAvailable()) && this.res.getDataLoss().equalsIgnoreCase("yes") && (res.getStreamType().equalsIgnoreCase("PI") || res.getStreamType().equalsIgnoreCase("OPC"))) {
						   String bfStart=sdf.format(this.res.getBackfillFrom());
						   String bfStop=sdf.format(this.res.getBackfillTo());
						   String extractionWindow=bfStart+" GMT TO <br>"+bfStop+" GMT";
						   this.htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Historical Extraction Window</td>\r\n" + 
							   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+extractionWindow+"</td> \r\n" + 
							   		"  </tr>\r\n";
					   }
				   if(this.res.isOutageDataNotAvailable() && (res.getStreamType().equalsIgnoreCase("PI") || res.getStreamType().equalsIgnoreCase("OPC"))) {
						   String dataUnavailable="Complete Outage. Data unavailable in source for backfill.";
						   this.htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Historical Extraction Window</td>\r\n" + 
							   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+dataUnavailable+"</td> \r\n" + 
							   		"  </tr>\r\n";
					   }*/
					   	this.htmlText+=	"  <tr>\r\n" + 
					   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.res.getContactTeam()+"</td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"  <tr>\r\n" + 
					   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.res.getEscalationContact()+"</td> \r\n" + 
					   		"  </tr>\r\n" + 
					   		"</table>";
			}else {
				this.htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\""+resCompletedImgPath+"\" /></td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;color:#abcf92;background-color:#395524;font-weight:bold;padding:5px 5px 5px 5px;\">Maintenance Completion Summary</td>\r\n" + 
				   		"  </tr>\r\n" +
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Environment</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+region +"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Resolution Details</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+ this.res.getResolution()+"</td> \r\n" + 
				   		"  </tr>\r\n";
		         String mnStart=sdf.format(this.res.getMaintenanceFrom());
				   String mnStop=sdf.format(this.res.getMaintenanceTo());
				   String maintenanceWindow=mnStart+" GMT TO <br>"+mnStop+" GMT";
				   this.htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Maintenance Window</td>\r\n" + 
					   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+maintenanceWindow+"</td> \r\n" + 
					   		"  </tr>\r\n";  		
		         
				   		//this.htmlText+="  <tr>\r\n" + 
				   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Current Business Impact</td> \r\n" + 
				   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\"> "+this.res.getBusinessImpact()+"</td> \r\n" + 
				   		//"  </tr>\r\n" + 
				   		//"  <tr>\r\n" + 
				   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Data Loss</td>\r\n" + 
				   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.res.getDataLoss()+"</td> \r\n" + 
				   		//"  </tr>\r\n"; 

				   /*if((!this.res.isOutageDataNotAvailable()) && this.res.getDataLoss().equalsIgnoreCase("yes") && (res.getStreamType().equalsIgnoreCase("PI") || res.getStreamType().equalsIgnoreCase("OPC"))) {
					   String bfStart=sdf.format(this.res.getBackfillFrom());
					   String bfStop=sdf.format(this.res.getBackfillTo());
					   String extractionWindow=bfStart+" GMT TO <br>"+bfStop+" GMT";
					   this.htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Historical Extraction Window</td>\r\n" + 
						   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+extractionWindow+"</td> \r\n" + 
						   		"  </tr>\r\n";
				   }
				   if(this.res.isOutageDataNotAvailable() && (res.getStreamType().equalsIgnoreCase("PI") || res.getStreamType().equalsIgnoreCase("OPC"))) {
					   String dataUnavailable="Complete Outage. Data unavailable in source for backfill.";
					   this.htmlText+=" <tr>\r\n   <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Historical Extraction Window</td>\r\n" + 
						   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+dataUnavailable+"</td> \r\n" + 
						   		"  </tr>\r\n";
				   }*/
				   		
				   	this.htmlText+=	"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.res.getContactTeam()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.res.getEscalationContact()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"</table>";
			}
		}
		
		
		if(this.inc != null) {	
			region=String.join("<br>", inc.getRegion());
			this.from=this.inc.getFrom();
			this.to=this.inc.getTo();
			this.bcc=this.inc.getBcc();
			if(this.inc.getIssueType().equalsIgnoreCase("announcement")) {
				//String stopTime=sdf.format(inc.getStopTime());
				this.htmlText = "<table max-width=\"650px\" style=\"border:1px solid green; border-collapse:collapse;font-family:Verdana;table-layout:fixed;\">\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td colspan=2 style=\"border:1px solid green;\"><img src=\""+announcementImgPath+"\" /></td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		"    <td colspan=2 style=\"border:1px solid green;font-size:85%;background-color:#ffc000;font-weight:bold;padding:5px 5px 5px 5px;\">Announcement Summary</td>\r\n" + 
				   		"  </tr>\r\n" + 
				   		"  <tr>\r\n" + 
				   		
				   		
				   		/*
				   		 * add
				   		 */
					   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Description</td>\r\n" + 
					   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+this.inc.getIssueType()+"</td> \r\n" + 
					   		//"  </tr>\r\n" + 
					   		//"  <tr>\r\n" + 
					   		
					   		
							"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Announcement Details</td>\r\n" + 
							"    <td  style=\"width:390px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">"+inc.getAnnouncement()+"</td> \r\n" + 
							"  </tr>\r\n" + 
							"  <tr>\r\n" + 

			
				   		"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Contact Team</td>\r\n" + 
				   		"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.inc.getContactTeam()+"</td> \r\n" + 
				   		"  </tr>\r\n" + 
				   		//"  <tr>\r\n" + 
				   		//"    <td  style=\"width:260px; border:1px solid green;font-size:85%;font-weight:bold;padding:5px 5px 5px 5px;\">Escalation Contact</td>\r\n" + 
				   		//"    <td  style=\"width:390px; border:1px solid green;font-size:85%;padding:5px 5px 5px 5px;\">"+this.inc.getEscalationContact()+"</td> \r\n" + 
				   		//"  </tr>\r\n" + 
				   		"</table>";
			}
		
		
		
		
	}
	}
	
}
