package com.outagemailtracker.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="omt_region_ops")
public class RegionEntity {
	@Id
	private String region;
	//private String vendors;
	//private String stream;
	/*public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}*/
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	/*public String getVendors() {
		return vendors;
	}
	public void setVendors(String vendors) {
		this.vendors = vendors;
	}*/
	
}
