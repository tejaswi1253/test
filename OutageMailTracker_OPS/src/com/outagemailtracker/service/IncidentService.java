package com.outagemailtracker.service;

import java.util.List;

import com.outagemailtracker.beans.Incident;

public interface IncidentService {
	public List<Incident> getAllIncidents() throws Exception;
	public int insertIncident(Incident inc) throws Exception;
	public Incident getIncidentById(int incId) throws Exception;
}
