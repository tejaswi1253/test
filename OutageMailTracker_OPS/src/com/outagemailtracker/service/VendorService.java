package com.outagemailtracker.service;

import java.util.List;

public interface VendorService {
	public List<String> fetchAllMailAddress(String mail) throws Exception;
}
