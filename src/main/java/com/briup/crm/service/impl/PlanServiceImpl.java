package com.briup.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import com.briup.crm.dao.SalChanceMapper;
import com.briup.crm.dao.SalPlanMapper;
import com.briup.crm.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private SalPlanMapper salPlanMapper;
	@Autowired
	private SalChanceMapper salChanceMapper;
	
	@Override
	public void savePlan(SalPlan plan, Long chcId) {	
		//根据chanceId查询对应的销售商机
		SalChance chance = salChanceMapper.selectByPrimaryKey(chcId);
		//状态改为：正在开发
		chance.setChcStatus(2);
		salChanceMapper.updateByPrimaryKey(chance);
		plan.setPlaChcId(chcId);
		salPlanMapper.insertSelective(plan);
	
	}
	
	//添加或修改
	@Override
	public void addOrUpdatePlan(SalPlan plan, Long chcId) {
		plan.setPlaChcId(chcId);
		if(plan.getPlaId() == null) {
			salPlanMapper.insertSelective(plan);
		} else {
			salPlanMapper.updateByPrimaryKey(plan);
		}
		
	}

	//根据plaId查找plan
	@Override
	public SalPlan findPlanById(Long plaId) {
		SalPlan plan = salPlanMapper.selectByPrimaryKey(plaId);
		return plan;
	}

	//根据plaId删除plan
	@Override
	public void deletePlanById(Long plaId) {
		salPlanMapper.deleteByPrimaryKey(plaId);
	}

	//开发成功
	@Override
	public void updateChanceById(Long chcId) {
		SalChance chance = salChanceMapper.selectByPrimaryKey(chcId);
		chance.setChcStatus(3);
		System.out.println(chance);
		salChanceMapper.updateByPrimaryKey(chance);
	}

}
