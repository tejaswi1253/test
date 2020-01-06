package com.outagemailtracker.service;

import com.outagemailtracker.beans.Incident;
import com.outagemailtracker.beans.Resolution;

public interface MailService {
	public String incidentMailSend(Incident inc) throws Exception;
	public String resolvedMailSend(Resolution res) throws Exception; 
	public String maintenanceMailSend(Incident inc) throws Exception; 
	public String maintenanceCompletionMailSend(Resolution res) throws Exception; 
}

