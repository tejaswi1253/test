package com.outagemailtracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="omt_resolution_ops")
public class ResolutionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="res_id")
	private int resId;
	@Column(name="inc_id")
	private int incId;
	@Column(name="from_mail")
	private String from;
	@Column(name="to_mail")
	private String to;
	private String bcc;
	private String subject;
	private String region;
	private String resolution;
	@Column(name="issue_type")
	private String issueType;
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name="stop_time")
	//private Date stopTime;
	/*@Temporal(TemporalType.TIMESTAMP)
	@Column(name="resolved_time")
	private Date resolvedTime;
	@Column(name="stream_type")
	private String streamType;
	@Column(name="data_loss")
	private String dataLoss;
	@Column(name="business_impact")
	private String businessImpact;*/
	@Column(name="contact_team")
	private String contactTeam;
	@Column(name="escalation_contact")
	private String escalationContact;
	/*@Temporal(TemporalType.TIMESTAMP)
	@Column(name="backfill_from")
	private Date backfillFrom;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="backfill_to")
	private Date backfillTo;*/
	//private String vendor;	
	/*@Column(name="backfill_status")
	private String backfillStatus;*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="maintenance_from")
	private Date maintenanceFrom;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="maintenance_to")
	private Date maintenanceTo;
	/*@Column(name="outagedata_notavailable")
	private boolean outageDataNotAvailable;*/
	@Column(name="rca")
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
	
	/*public boolean isOutageDataNotAvailable() {
		return outageDataNotAvailable;
	}
	public void setOutageDataNotAvailable(boolean outageDataNotAvailable) {
		this.outageDataNotAvailable = outageDataNotAvailable;
	}*/
	
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
	/*public String getBackfillStatus() {
		return backfillStatus;
	}
	public void setBackfillStatus(String backfillStatus) {
		this.backfillStatus = backfillStatus;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public Date getBackfillFrom() {
		return backfillFrom;
	}
	public void setBackfillFrom(Date backfillFrom) {
		this.backfillFrom = backfillFrom;
	}
	public Date getBackfillTo() {
		return backfillTo;
	}
	public void setBackfillTo(Date backfillTo) {
		this.backfillTo = backfillTo;
	}*/
	/*public Date getResolvedTime() {
		return resolvedTime;
	}
	public void setResolvedTime(Date resolvedTime) {
		this.resolvedTime = resolvedTime;
	}*/
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
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
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	/*public Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
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
	}*/
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
