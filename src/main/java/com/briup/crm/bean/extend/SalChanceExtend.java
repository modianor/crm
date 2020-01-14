package com.briup.crm.bean.extend;

import java.util.List;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;

public class SalChanceExtend extends SalChance{
	private List<SalPlan> plans;

	public List<SalPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<SalPlan> plans) {
		this.plans = plans;
	}
	
	
}
