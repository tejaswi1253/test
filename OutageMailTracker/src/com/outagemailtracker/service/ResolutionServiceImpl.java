package com.outagemailtracker.service;

import com.outagemailtracker.beans.Resolution;
import com.outagemailtracker.resources.Factory;
import com.outagemailtracker.resources.OmtLogger;

public class ResolutionServiceImpl implements ResolutionService {
	public int insertResolution(Resolution res) throws Exception{
		try {
			int resId=Factory.createResolutionDAO().insertResolution(res);
			if (resId<=0 ) 
			{
				throw new Exception("ResolutionServiceImpl.INVALID_RESOLUTION_ID_CREATED");
				
			}
			int matchId=Factory.createMatchService().insertResolutionToMatch(resId, res.getIncId());
			System.out.println("MatchID: "+matchId);
			
			return resId;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "insertResolution",e.getMessage());
			}
			throw e;
		}
	}
	public Resolution getResolutionById(int resId) throws Exception{
		try {
			Resolution res=Factory.createResolutionDAO().getResolutionById(resId);
			
			if (res == null) 
			{
				throw new Exception("ResolutionServiceImpl.NO_RESOLUTIONS_FOUND");
				
			}
			return res;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "getResolutionById",e.getMessage());
			}
			throw e;
		}
	}
	public int updateBackfillStatus(int resId,String backfillStatus) throws Exception{
		try {
			int resid=Factory.createResolutionDAO().updateBackfillStatus(resId,backfillStatus);
			
			if (resid == 0) 
			{
				throw new Exception("ResolutionServiceImpl.NO_RESOLUTIONS_FOUND");
				
			}
			return resid;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "updateBackfillStatus",e.getMessage());
			}
			throw e;
		}
	}
}
