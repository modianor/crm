package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstActivity;
import com.briup.crm.bean.CstActivityExample;
import com.briup.crm.dao.CstActivityMapper;
import com.briup.crm.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private CstActivityMapper activityMapper;
	
	//根据custId查询activities(分页显示)
	@Override
	public PageInfo<CstActivity> findActivitiesByCustId(long custId, int size, int curPage) {
		PageHelper.startPage(curPage, size);
		
		CstActivityExample example = new CstActivityExample();
		example.createCriteria().andAtvCustIdEqualTo(custId);
		
		List<CstActivity> list = activityMapper.selectByExample(example);
		PageInfo<CstActivity> activityInfo = new PageInfo<>(list);
		
		return activityInfo;
		
	}

	//添加或修改activity
	@Override
	public void saveOrUpdate(CstActivity activity) {
		if(activity.getAtvId() == null) {
			activityMapper.insert(activity);
		} else {
			activityMapper.updateByPrimaryKey(activity);
		}
	}

	//根据atvId查找activity
	@Override
	public CstActivity findActivityById(long atvId) {
		CstActivity activity = activityMapper.selectByPrimaryKey(atvId);
		return activity;
		
	}

	//根据atvId删除activity
	@Override
	public void deleteActivityById(long atvId) {
		activityMapper.deleteByPrimaryKey(atvId);
	}

}
