package com.outagemailtracker.dao;

import java.util.List;

public interface VendorDAO {
	public List<String> fetchAllMailAddress(String vendor) throws Exception;
}
