package com.outagemailtracker.service;

import java.util.List;

import com.outagemailtracker.beans.Region;
import com.outagemailtracker.resources.Factory;
import com.outagemailtracker.resources.OmtLogger;

public class RegionServiceImpl implements RegionService{
	public List<Region> getAllRegions() throws Exception{
		try {
//			System.out.println("InSVC");
			List<Region> list=Factory.createRegionDAO().getAllRegions();			
			if (list.isEmpty()) 
			{
				throw new Exception("IncidentServiceImpl.NO_REGIONS_FOUND");
				
			}
			return list;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "getAllRegions",e.getMessage());
			}
			throw e;
		}
	}
}
