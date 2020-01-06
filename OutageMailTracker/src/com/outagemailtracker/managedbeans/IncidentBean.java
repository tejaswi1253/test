package com.outagemailtracker.managedbeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.outagemailtracker.beans.Incident;
import com.outagemailtracker.beans.Region;
import com.outagemailtracker.resources.Factory;
import com.outagemailtracker.resources.OmtLogger;

@ManagedBean
@ViewScoped
public class IncidentBean {
	private String from;
	private String to;
	private String[] bcc;
	private String subject;
	private String[] region;
	private String incident;
	private String issueType;
	private Date stopTime;
	private String dataLoss;
	private String businessImpact;
	private String contactTeam;
	private String escalationContact;
	private String message;
	private String streamType;
	private List<Region> regionList;
	private List<SelectItem> regionSelectItem;
	private List<SelectItem> vendorSelectItem;
	private List<SelectItem> mailSelectItem;
	private List<SelectItem> streamSelectItem;
	private List<String> vendor;
	private boolean proceed;
	private Date maintenanceFrom;
	private Date maintenanceTo;
	private boolean bImpactEdit;
	private String rca;
	private List<String> commonVendors;
	private List<String> commonStreams;
	
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public List<String> getVendor() {
		return vendor;
	}
	public void setVendor(List<String> vendor) {
		this.vendor = vendor;
	}
	public List<String> getCommonVendors() {
		return commonVendors;
	}
	public void setCommonVendors(List<String> commonVendors) {
		this.commonVendors = commonVendors;
	}
	public List<String> getCommonStreams() {
		return commonStreams;
	}
	public void setCommonStreams(List<String> commonStreams) {
		this.commonStreams = commonStreams;
	}
	public String getRca() {
		return rca;
	}
	public void setRca(String rca) {
		this.rca = rca;
	}
		public boolean isbImpactEdit() {
		return bImpactEdit;
	}
	public void setbImpactEdit(boolean bImpactEdit) {
		this.bImpactEdit = bImpactEdit;
	}
		public Date getMaintenanceFrom() {
		return maintenanceFrom;
	}
	public void setMaintenanceFrom(Date maintenanceFrom) {
		this.maintenanceFrom = maintenanceFrom;
	}
	public Date getMaintenanceTo() {
		return maintenanceTo;
	}
	public void setMaintenanceTo(Date maintenanceTo) {
		this.maintenanceTo = maintenanceTo;
	}
	public boolean isProceed() {
		return proceed;
	}
	public void setProceed(boolean proceed) {
		this.proceed = proceed;
	}
	public String[] getRegion() {
		return region;
	}
	public void setRegion(String[] region) {
		this.region = region;
	}
	private List<String> vendorMailList;
	
	public List<SelectItem> getStreamSelectItem() {
		return streamSelectItem;
	}
	public void setStreamSelectItem(List<SelectItem> streamSelectItem) {
		this.streamSelectItem = streamSelectItem;
	}
	public List<SelectItem> getMailSelectItem() {
		return mailSelectItem;
	}
	public void setMailSelectItem(List<SelectItem> mailSelectItem) {
		this.mailSelectItem = mailSelectItem;
	}
	public List<String> getVendorMailList() {
		return vendorMailList;
	}
	public void setVendorMailList(List<String> vendorMailList) {
		this.vendorMailList = vendorMailList;
	}
	public List<SelectItem> getVendorSelectItem() {
		return vendorSelectItem;
	}
	public void setVendorSelectItem(List<SelectItem> vendorSelectItem) {
		this.vendorSelectItem = vendorSelectItem;
	}
	public List<SelectItem> getRegionSelectItem() {
		return regionSelectItem;
	}
	public void setRegionSelectItem(List<SelectItem> regionSelectItem) {
		this.regionSelectItem = regionSelectItem;
	}
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
	public String[] getBcc() {
		return bcc;
	}
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	public String getDataLoss() {
		return dataLoss;
	}
	public void setDataLoss(String dataLoss) {
		this.dataLoss = dataLoss;
	}
	public String getBusinessImpact() {
		return businessImpact;
	}
	public void setBusinessImpact(String businessImpact) {
		this.businessImpact = businessImpact;
	}
	public String getContactTeam() {
		return contactTeam;
	}
	public void setContactTeam(String contactTeam) {
		this.contactTeam = contactTeam;
	}
	public String getEscalationContact() {
		return escalationContact;
	}
	public void setEscalationContact(String escalationContact) {
		this.escalationContact = escalationContact;
	}	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public List<Region> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}
	public IncidentBean() {
		this.message=null;
		this.proceed=false;
		this.from="gdatalake.support@bp.com";
		this.to="gdatalake.support@bp.com";
		this.contactTeam="Data Lake Service Line Support \r\n" + 
				"GDATALAKE.SUPPORT@BP.COM";
		this.escalationContact="Brian.Ripley@bp.com";
		this.bImpactEdit=false;
		this.businessImpact="Realtime streaming has been interrupted. Data is not being sent to the endpoint.";
		try {
			this.regionList=Factory.createRegionService().getAllRegions();
			this.regionSelectItem=new ArrayList<SelectItem>();
			for(Region r:this.regionList) {
				this.regionSelectItem.add(new SelectItem(r.getRegion().toUpperCase(),r.getRegion().toUpperCase()));
			}
			setCommonStreams();
			this.streamSelectItem=new ArrayList<SelectItem>();
			for(String str:this.commonStreams) {
				this.streamSelectItem.add(new SelectItem(str.toUpperCase(),str.toUpperCase()));
				//System.out.println(str.toUpperCase());
			}
		}catch(Exception e){
			OmtLogger.logError(this.getClass().getName(), "IncidentBean", e.getMessage());
			this.message=e.getMessage();
		}
	}
	public void setCommonStreams() {
		Set<String> allStreams=new TreeSet<String>();
		for(Region r:this.regionList) {
			for(String str:r.getStream()) {
				allStreams.add(str);
			}
		}
		this.commonStreams=new ArrayList<String>(allStreams);
	}
	public void setCommonVendors() {
		Set<String> allVendors=new TreeSet<String>();
		for(Region r:this.regionList) {
			for(String vendor:r.getVendors()) {
				allVendors.add(vendor);
			}
		}
		this.commonVendors=new ArrayList<String>(allVendors);
	}
	public String editBImpact() {
		this.bImpactEdit=true;
		this.proceed=false;
//		System.out.println("Inside editBImpact: "+this.bImpactEdit);
		return "";
	}
	public String saveBImpact() {
		this.bImpactEdit=false;
		this.proceed=false;
//		System.out.println("Inside saveBImpact: "+this.bImpactEdit);
		return "";
	}
	public void setStreamTypeList(ValueChangeEvent vce) {
//		System.out.println("In");
		try {
			this.proceed=false;
			this.message=null;
			this.issueType=null;
			this.dataLoss=null;
			this.region=(String[])vce.getNewValue();
			this.streamSelectItem=null;
//			this.vendorSelectItem=null;
//			this.mailSelectItem=null;
			setCommonStreams();
			if(this.region.length!=0) {
				this.streamSelectItem=new ArrayList<SelectItem>();
				for(String reg:region) {
					for(Region r:this.regionList) {
						if(r.getRegion().equalsIgnoreCase(reg)) {						
							this.commonStreams.retainAll(r.getStream());
						}
					}	
				}
				Set<String> sSet=new TreeSet<String>(this.commonStreams);
				for(String str:sSet) {
					this.streamSelectItem.add(new SelectItem(str.toUpperCase(),str.toUpperCase()));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void setVendorList(ValueChangeEvent vce) {
//		System.out.println("In");
		try {
			this.proceed=false;
			this.message=null;
			this.issueType=null;
			this.dataLoss=null;
			this.streamType=(String)vce.getNewValue();
			this.vendorSelectItem=null;
//			this.mailSelectItem=null;
			if(!this.streamType.equals("0")) {
				setCommonVendors();
				for(String reg:this.region) {
					for(Region r:this.regionList) {
						if(r.getRegion().equalsIgnoreCase(reg)) {
							for(String str:r.getStream()) {
								if(str.equalsIgnoreCase(this.streamType)) {
									this.commonVendors.retainAll(r.getVendors());
								}
							}
						}
					}	
				}
				Set<String> vSet=new TreeSet<String>(this.commonVendors);
				this.vendorSelectItem=new ArrayList<SelectItem>();
				for(String vendor:vSet) {
					this.vendorSelectItem.add(new SelectItem(vendor.toUpperCase(),vendor.toUpperCase()));
				}	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void assignIssueType(ValueChangeEvent vce) {
		this.proceed=false;
		String iss=(String)vce.getNewValue();
		if(!iss.equals("0")) {
			this.issueType=iss;
		}
	}
	@SuppressWarnings("unchecked")
	public void setMailAddress(ValueChangeEvent vce) {
		try {
			this.proceed=false;
			this.vendor=(List<String>)vce.getNewValue();
			if(this.vendor.size()>0) {
				this.vendorMailList=new ArrayList<String>();
				for(String v:this.vendor) {
					this.vendorMailList.addAll(Factory.createVendorDAO().fetchAllMailAddress(v.toUpperCase()));
				}
				Set<String> mailSet=new TreeSet<String>(this.vendorMailList);
//				System.out.println(Arrays.toString(mailSet.toArray()));
				this.mailSelectItem=new ArrayList<SelectItem>();
				for(String m:mailSet) {
					this.mailSelectItem.add(new SelectItem(m.toUpperCase(),m.toUpperCase()));
				}
			}else {
				this.mailSelectItem=null;
			}
		}catch(Exception e){
			OmtLogger.logError(this.getClass().getName(), "setMailAddress", e.getMessage());
			this.message=e.getMessage();
			e.printStackTrace();
		}
	}
	public String validate() {
		this.message="";
		if(this.from==null || this.from=="" || this.from.length()==0) {
			this.message="From is Mandatory";
		}
		if(this.to==null || this.to=="" || this.to.length()==0) {
			this.message="To is Mandatory";
		}
		if(this.region == null || this.region.length==0) {
			this.message="Region is Mandatory";
		}
		if(this.vendor == null || this.vendor.size()==0) {
			this.message="Vendor is Mandatory";
		}
		if(this.bcc == null || this.bcc.length==0) {
			this.message="BCC is Mandatory";
		}
		if(this.subject==null || this.subject=="" || this.subject.length()==0)	{	
			this.message="Subject is Mandatory";
		}
		if(this.streamType == null || this.streamType.length()==0) {
			this.message="Stream Type is Mandatory";
		}
		if(this.issueType == null || this.issueType == "" || this.issueType.length()==0 || this.issueType.equals("0")) {
			this.message="Issue Type is Mandatory";
		}
		if(this.businessImpact ==null || this.businessImpact =="") {
			this.message="Business Impact is Mandatory";
		}
		if(this.dataLoss == null || this.dataLoss == "" || this.dataLoss.length()==0 || this.dataLoss.equals("0")) {
			this.message="Data Loss is Mandatory";
		}
		if(this.contactTeam==null || this.contactTeam=="" || this.contactTeam.length()==0) {
			this.message="Contact Team is Mandatory";
		}
		if(this.escalationContact==null || this.escalationContact=="" || this.escalationContact.length()==0) {
			this.message="Escalation Contact is Mandatory";
		}		
		if(this.incident==null || this.incident=="" || this.incident.length()==0) {
			this.message="Incident Details is Mandatory";
		}
		if(this.issueType.equalsIgnoreCase("maintenance") && this.maintenanceFrom==null) {
			this.message="Maintenance From Time is Mandatory";
		}
		if(this.issueType.equalsIgnoreCase("maintenance") && this.maintenanceTo==null) {
			this.message="Maintenance To Time is Mandatory";
		}
		if((!this.issueType.equalsIgnoreCase("maintenance")) && this.stopTime==null) {
			this.message="Streaming Stopped Time is Mandatory";
		}
		if((!this.issueType.equalsIgnoreCase("maintenance")) && (this.rca==null || this.rca.length()==0)) {
			this.message="Root Cause is Mandatory";
		}
		if(this.message!=null && this.message.length()==0 && this.message=="") {
			this.proceed=true;
		}
		return "";
	}
	
	public String sendMail() {
		this.message=null;
		this.proceed=false;
		System.out.println("Form Submitting");
		Incident inc= new Incident();
//		String temp="";
//		for(String strBcc:this.bcc) {
//			temp=temp+strBcc+", ";
//		}
		inc.setBcc(String.join(",", this.bcc));
		inc.setBusinessImpact(this.businessImpact);
		inc.setContactTeam(this.contactTeam);
		inc.setDataLoss(this.dataLoss);
		inc.setEscalationContact(this.escalationContact);
		inc.setFrom(this.from);
		inc.setIncident(this.incident);
		inc.setIssueType(this.issueType);
		List<String> tempList=Arrays.asList(this.region);
//		for(String tmp:this.region) {
//			tempList.add(tmp);
//		}
		inc.setRegion(tempList);
		inc.setStopTime(this.stopTime);
		inc.setStreamType(this.streamType);
		inc.setSubject(this.subject);
		inc.setTo(this.to);
		inc.setVendor(this.vendor);
		inc.setMaintenanceFrom(this.maintenanceFrom);
		inc.setMaintenanceTo(this.maintenanceTo);
		inc.setRca(this.rca);
		Date created=new Date();
		inc.setCreated(created);
		try {
//			int incId=Factory.createIncidentService().insertIncident(inc);
//			this.message="Incident Notification Sent Successfully. INC ID : "+incId;
			if(!this.issueType.equalsIgnoreCase("maintenance")) {
				if(Factory.createMailService().incidentMailSend(inc).equalsIgnoreCase("success")){
					int incId=Factory.createIncidentService().insertIncident(inc);
					this.message="Incident Notification Sent Successfully. INC ID : "+incId;
				}else {
					this.message="Mail Send Failed";
				}
			}else {
				if(Factory.createMailService().maintenanceMailSend(inc).equalsIgnoreCase("success")){
					int incId=Factory.createIncidentService().insertIncident(inc);
					this.message="Maintenance Notification Sent Successfully. INC ID : "+incId;
				}else {
					this.message="Mail Send Failed";
				}
			}
			System.out.println(this.message);

		}catch(Exception e){
			e.printStackTrace();
			this.message=e.getMessage();
		}
		System.out.println("Form Submitted");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "dashboard.jsp?faces-redirect=true";
	}
	public void assignDataLoss(ValueChangeEvent vce) {
		this.proceed=false;
		String dl=(String)vce.getNewValue();
		if(!dl.equals("0")) {
			this.dataLoss=dl;
		}
	}
	public String revalidate() {
		this.proceed=false;
		return "";
	}
}
