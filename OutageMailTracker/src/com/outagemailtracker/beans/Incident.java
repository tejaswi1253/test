package com.outagemailtracker.beans;

import java.util.Date;
import java.util.List;

public class Incident {
	private int incId;
	private String from;
	private String to;
	private String bcc;
	private String subject;
	private List<String> region;
	private String incident;
	private String issueType;
	private Date stopTime;
	private List<String> vendor;
	private String dataLoss;
	private String businessImpact;
	private String contactTeam;
	private String escalationContact;
	private String streamType;
	private Date maintenanceFrom;
	private Date maintenanceTo;
	private String rca;
	private Date created;
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getRca() {
		return rca;
	}
	public void setRca(String rca) {
		this.rca = rca;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getIncId() {
		return incId;
	}
	public void setIncId(int incId) {
		this.incId = incId;
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
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public List<String> getRegion() {
		return region;
	}
	public void setRegion(List<String> region) {
		this.region = region;
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
	public List<String> getVendor() {
		return vendor;
	}
	public void setVendor(List<String> vendor) {
		this.vendor = vendor;
	}
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
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
	
}
