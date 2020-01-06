package com.outagemailtracker.service;

import java.util.List;

import com.outagemailtracker.beans.Match;

public interface MatchService {
	public List<Match> getAllMatch() throws Exception;
	public int insertIncidentToMatch(int incId) throws Exception;
	public int insertResolutionToMatch(int resId,int incId) throws Exception;
	public Match getMatchById(int matchId) throws Exception;
}
