package com.outagemailtracker.resources;

import com.outagemailtracker.dao.IncidentDAO;
import com.outagemailtracker.dao.IncidentDAOImpl;
import com.outagemailtracker.dao.MatchDAO;
import com.outagemailtracker.dao.MatchDAOImpl;
import com.outagemailtracker.dao.RegionDAO;
import com.outagemailtracker.dao.RegionDAOImpl;
import com.outagemailtracker.dao.ResolutionDAO;
import com.outagemailtracker.dao.ResolutionDAOImpl;
import com.outagemailtracker.dao.VendorDAO;
import com.outagemailtracker.dao.VendorDAOImpl;
import com.outagemailtracker.service.IncidentService;
import com.outagemailtracker.service.IncidentServiceImpl;
import com.outagemailtracker.service.MailService;
import com.outagemailtracker.service.MailServiceImpl;
import com.outagemailtracker.service.MatchService;
import com.outagemailtracker.service.MatchServiceImpl;
import com.outagemailtracker.service.RegionService;
import com.outagemailtracker.service.RegionServiceImpl;
import com.outagemailtracker.service.ResolutionService;
import com.outagemailtracker.service.ResolutionServiceImpl;
import com.outagemailtracker.service.VendorService;
import com.outagemailtracker.service.VendorServiceImpl;

public class Factory 
{
	public static IncidentDAO createIncidentDAO()
	{
		return new IncidentDAOImpl();
	}
	public static MatchDAO createMatchDAO()
	{
		return new MatchDAOImpl();
	}
	public static IncidentService createIncidentService()
	{
		return new IncidentServiceImpl();
	}
	public static MatchService createMatchService()
	{
		return new MatchServiceImpl();
	}
	public static RegionService createRegionService()
	{
		return new RegionServiceImpl();
	}
	public static RegionDAO createRegionDAO()
	{
		return new RegionDAOImpl();
	}
	public static VendorService createVendorService()
	{
		return new VendorServiceImpl();
	}
	public static VendorDAO createVendorDAO()
	{
		return new VendorDAOImpl();
	}
	public static MailService createMailService()
	{
		return new MailServiceImpl();
	}
	public static ResolutionService createResolutionService()
	{
		return new ResolutionServiceImpl();
	}
	public static ResolutionDAO createResolutionDAO()
	{
		return new ResolutionDAOImpl();
	}
}