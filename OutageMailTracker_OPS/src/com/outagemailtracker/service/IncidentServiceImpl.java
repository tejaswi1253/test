package com.outagemailtracker.service;

import java.util.List;

import com.outagemailtracker.beans.Incident;
import com.outagemailtracker.resources.Factory;
import com.outagemailtracker.resources.OmtLogger;

public class IncidentServiceImpl implements IncidentService {
	public List<Incident> getAllIncidents() throws Exception{
		try {
			List<Incident> list=Factory.createIncidentDAO().getAllIncidents();
			
			if (list.isEmpty()) 
			{
				throw new Exception("IncidentServiceImpl.NO_INCIDENTS_FOUND");
				
			}
			return list;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "getAllIncidents",e.getMessage());
			}
			throw e;
		}
	}
	public int insertIncident(Incident inc) throws Exception{
		try {
			int incId=Factory.createIncidentDAO().insertIncident(inc);
			if (incId<=0 ) 
			{
				throw new Exception("IncidentServiceImpl.INVALID_INCIDENT_ID_CREATED");
				
			}
			int matchId=Factory.createMatchService().insertIncidentToMatch(incId);
			System.out.println("MatchID: "+matchId);
			
			return incId;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "insertIncident",e.getMessage());
			}
			throw e;
		}
	}
	public Incident getIncidentById(int incId) throws Exception{
		try {
			Incident inc=Factory.createIncidentDAO().getIncidentById(incId);
			
			if (inc == null) 
			{
				throw new Exception("IncidentServiceImpl.NO_INCIDENTS_FOUND");
				
			}
			return inc;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "getIncidentById",e.getMessage());
			}
			throw e;
		}
	}
}
