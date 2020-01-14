package com.briup.crm.service;

import java.util.List;
import java.util.Set;

import com.briup.crm.bean.CstCustomer;
import com.github.pagehelper.PageInfo;

public interface CustomerService {
	public List<CstCustomer> findAllCustomer();
	
	//分页查询所有顾客
	public PageInfo<CstCustomer> findAllCustomerByPage(int curPage, int size);
	
	//保存顾客对象
	public void saveOrUpdateCustomer(CstCustomer customer);
	
	//删除顾客
	public void deleteCustomerById(long custId);
	
	//根据ID查询顾客信息
	public CstCustomer findCustomerById(long custId);
	
	//根据条件查询顾客(分页)
	public PageInfo<CstCustomer> findCustomerLike(int curPage, int size, CstCustomer customer);
	
	//查每个区域的贡献百分比
	public float getRegionPercent(String region);
	
	
	
	//查询不同等级的顾客人数
	public int getCustByLevel(String level);
	
	//查询不同信誉度的顾客人数
	public int getCustByCredit(int credit);
	
	//查询不同满意度的顾客人数
	public int getCustBySatisfy(int satisfy);
	
	//查询所有的等级
	public Set<String> findAllLevel();
	
	//查询所有的信誉度
	public Set<Integer> findAllCredit();
	
	//查询所有的满意度
	public Set<Integer> findAllSatisfy();
}
