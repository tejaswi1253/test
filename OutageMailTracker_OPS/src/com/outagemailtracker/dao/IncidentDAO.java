package com.outagemailtracker.dao;

import java.util.List;

import com.outagemailtracker.beans.Incident;

public interface IncidentDAO 
{
	public int insertIncident(Incident incident) throws Exception;
	public Incident getIncidentById(int incId) throws Exception;
	public List<Incident> getAllIncidents() throws Exception;
}
