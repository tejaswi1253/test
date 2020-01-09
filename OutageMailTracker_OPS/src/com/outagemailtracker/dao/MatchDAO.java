package com.outagemailtracker.dao;

import java.util.List;

import com.outagemailtracker.beans.Match;

public interface MatchDAO {
	public int insertIncidentToMatch(int incId) throws Exception;
	public List<Match> getAllFromMatch() throws Exception;
	public int insertResolutionToMatch(int resId,int incId) throws Exception;
	public Match getMatchById(int matchId) throws Exception;
}
