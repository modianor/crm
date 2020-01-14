package com.briup.crm.service;

import com.briup.crm.bean.CstActivity;
import com.github.pagehelper.PageInfo;

public interface ActivityService {
	
	//根据custId查询activities
	public PageInfo<CstActivity> findActivitiesByCustId(long custId,int size, int curPage);
	
	//添加或修改activity
	public void saveOrUpdate(CstActivity activity);
	
	//根据atvId查找activity
	public CstActivity findActivityById(long atvId);
	
	//根据atvId删除activity
	public void deleteActivityById(long atvId);
}
