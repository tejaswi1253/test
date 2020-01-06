package com.outagemailtracker.service;

import com.outagemailtracker.beans.Resolution;

public interface ResolutionService {
	public int insertResolution(Resolution res) throws Exception;
	public Resolution getResolutionById(int resId) throws Exception;
	public int updateBackfillStatus(int resId,String backfillStatus) throws Exception;
}
