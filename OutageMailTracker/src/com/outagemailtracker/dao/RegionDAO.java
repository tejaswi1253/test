package com.outagemailtracker.dao;

import java.util.List;

import com.outagemailtracker.beans.Region;

public interface RegionDAO {
	public List<Region> getAllRegions() throws Exception;
}
