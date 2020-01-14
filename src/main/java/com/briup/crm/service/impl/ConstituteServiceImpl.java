package com.briup.crm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.Contribution;
import com.briup.crm.service.ConstituteService;
import com.briup.crm.service.CustomerService;
@Service
public class ConstituteServiceImpl implements ConstituteService{

	@Autowired
	private CustomerService custService;
	
	
	//根据不同分类查询百分比
	@Override
	public List<Contribution> findCustMarkup(int condition) {
		int count = custService.findAllCustomer().size();
		System.out.println("总人数：" + count);
		List<Contribution> list = new ArrayList<Contribution>();
		if(condition == 0) {
			//按等级
			Set<String> level = custService.findAllLevel();
			
			for (String l : level) {
				int i = custService.getCustByLevel(l);
				float per = (float)i/(float)count;
				Contribution con = new Contribution();
				con.setName(l);
				con.setY(per*100);
				list.add(con);			
			}
			
		} else if(condition == 1) {
			//按信誉度
			Set<Integer> credit = custService.findAllCredit();
			
			for (Integer c : credit) {
				int i = custService.getCustByCredit(c);
				float per = (float)i/(float)count;
				Contribution con = new Contribution();
				con.setName(c.toString());
				con.setY(per*100);
				list.add(con);		
			}
			
		} else if(condition == 2) {
			//按满意度
			Set<Integer> satisfy = custService.findAllSatisfy();
			
			for (Integer s : satisfy) {
				int i = custService.getCustBySatisfy(s);
				float per = (float)i/(float)count;
				Contribution con = new Contribution();
				con.setName(s.toString());
				con.setY(per*100);
				list.add(con);
			}
			
		}
		return list;
	}

}
