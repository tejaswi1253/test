package com.outagemailtracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="omt_match")
public class MatchEntity {
	@Column(name="inc_id")
	private int incId;
	@Column(name="res_id")
	private int resId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="match_id")
	private int matchId;
	private Date updated;
	
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getIncId() {
		return incId;
	}
	public void setIncId(int incId) {
		this.incId = incId;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	
}
