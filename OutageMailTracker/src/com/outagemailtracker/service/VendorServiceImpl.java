package com.outagemailtracker.service;

import java.util.List;

import com.outagemailtracker.resources.Factory;
import com.outagemailtracker.resources.OmtLogger;

public class VendorServiceImpl implements VendorService{
	public List<String> fetchAllMailAddress(String vendor) throws Exception{
		try {
			List<String> list=Factory.createVendorDAO().fetchAllMailAddress(vendor);
			
			if (list.isEmpty()) 
			{
				throw new Exception("IncidentServiceImpl.NO_VENDOR_FOUND");
				
			}
			return list;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "fetchAllMailAddress",e.getMessage());
			}
			throw e;
		}
	}
}
