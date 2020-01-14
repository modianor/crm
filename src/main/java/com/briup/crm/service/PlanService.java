package com.briup.crm.service;

import com.briup.crm.bean.SalPlan;

public interface PlanService {
	
	//保存plan
	public void savePlan(SalPlan plan, Long chcId);
	
	//添加或修改
	public void addOrUpdatePlan(SalPlan plan, Long chcId);
	
	//根据plaId查找plan
	public SalPlan findPlanById(Long plaId);
	
	//根据plaId删除plan
	public void deletePlanById(Long plaId);
	
	//开发成功
	public void updateChanceById(Long chcId);
}
