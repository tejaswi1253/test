package com.outagemailtracker.service;

import java.util.List;

import com.outagemailtracker.beans.Match;
import com.outagemailtracker.resources.Factory;
import com.outagemailtracker.resources.OmtLogger;

public class MatchServiceImpl implements MatchService{
	public List<Match> getAllMatch() throws Exception{
		try {
			List<Match> list=Factory.createMatchDAO().getAllFromMatch();
			
//			if (list.isEmpty()) 
//			{
//				throw new Exception("MatchServiceImpl.NO_MATCH_FOUND");
//				
//			}
			return list;
		}   catch (Exception e)
		{
			if(e.getMessage().contains("Service"))
			{
			OmtLogger.logError(this.getClass().getName(), "getAllMatch",e.getMessage());
			}
			throw e;
		}
	}
	
	public int insertIncidentToMatch(int incId) throws Exception{
		int matchId=Factory.createMatchDAO().insertIncidentToMatch(incId);
		if (matchId<=0 ) 
		{
			throw new Exception("MatchServiceImpl.INVALID_MATCH_ID_CREATED");
			
		}
		return matchId;
	}
	public int insertResolutionToMatch(int resId,int incId) throws Exception{
		int matchId=Factory.createMatchDAO().insertResolutionToMatch(resId, incId);
		if (matchId<=0 ) 
		{
			throw new Exception("MatchServiceImpl.INVALID_MATCH_ID_CREATED");
			
		}
		return matchId;
	}
	public Match getMatchById(int matchId) throws Exception{
		Match match=Factory.createMatchDAO().getMatchById(matchId);
		if(match==null) {
			throw new Exception("MatchServiceImpl.NO_MATCH_FOUND");
		}
		return match;
	}
}
