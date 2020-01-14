package com.briup.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.Contribution;
import com.briup.crm.dao.extend.CstCustomerExtendMapper;
import com.briup.crm.service.ContributionService;
import com.briup.crm.service.CustomerService;
@Service
public class ContributionServiceImpl implements ContributionService{

	@Autowired
	private CstCustomerExtendMapper mapper;
	@Autowired
	private CustomerService service;
	
	//根据地区查询贡献比
	@Override
	public List<Contribution> findContribution() {
		List<String> list = mapper.selectRegion();
		System.out.println("serviceImpl 地区集合"+list);
	    List<Contribution> c = new ArrayList<Contribution>();
		for (String string : list) {
			Contribution contribution = new Contribution();
			contribution.setName(string);
			contribution.setY(service.getRegionPercent(string)*100);
			c.add(contribution);
		}
		return c;
	}
	
	//查询指定地区贡献比
	@Override
	public Contribution findContributionByRegion(String region) {
		Contribution con = new Contribution();
		con.setName(region);
		con.setY(service.getRegionPercent(region)*100);
		return con;
	}

	

}
