package com.briup.crm.service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.github.pagehelper.PageInfo;

public interface SaleChanceService {
	
	//查询salChance(分页)
	public PageInfo<SalChance> findSalChance(int curPage, int size);
	
	//模糊查询
	public PageInfo<SalChance> findSaleChanceLike(int curPage, int size, String chcCustName, String chcAddr);
	
	//保存或修改
	public void saveOrUpdate(SalChance chance);
	
	//根据id查询chance
	public SalChance findChanceById(long chanceId);
	
	//删除
	public void deleteChanceById(long chcId);
	
	//根据userId查找SalChance
	public PageInfo<SalChance> findChanceByUserId(int curPage, int size, String dueTo);
	
	//根据经理名和地址查找chance
	public PageInfo<SalChance> findChanceByUserNameAndAddr(int curPage, int size, String dueTo, String addr);
	
	//根据chanId查询chance，包括plans
	public SalChanceExtend findChanceWithPlanWithId(long id);
}
