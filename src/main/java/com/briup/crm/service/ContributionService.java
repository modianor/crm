package com.briup.crm.service;

import java.util.List;

import com.briup.crm.bean.Contribution;

public interface ContributionService {
	
	public List<Contribution> findContribution();
	
	public Contribution findContributionByRegion(String region);
	
	
}
