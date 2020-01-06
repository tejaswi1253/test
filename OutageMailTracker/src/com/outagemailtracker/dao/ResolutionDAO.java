package com.outagemailtracker.dao;

import com.outagemailtracker.beans.Resolution;

public interface ResolutionDAO 
{
	public int insertResolution(Resolution resolution) throws Exception;
	public Resolution getResolutionById(int resId) throws Exception;
	public int updateBackfillStatus(int resId,String backfillStatus) throws Exception;
}
