package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalChanceExample;
import com.briup.crm.bean.extend.SalChanceExtend;
import com.briup.crm.dao.SalChanceMapper;
import com.briup.crm.dao.extend.SalChanceExtendMapper;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SalChanceServiceImpl implements SaleChanceService{
	@Autowired
	private SalChanceMapper chanceMapper;
	@Autowired
	private SalChanceExtendMapper chanceExtendMapper;
	
	//查询（分页）
	@Override
	public PageInfo<SalChance> findSalChance(int curPage, int size) {
		PageHelper.startPage(curPage, size);
		SalChanceExample example = new SalChanceExample();
		List<SalChance> list = chanceMapper.selectByExample(example);
		
		PageInfo<SalChance> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	//模糊查询
	@Override
	public PageInfo<SalChance> findSaleChanceLike(int curPage, int size, String chcCustName, String chcAddr) {
			PageHelper.startPage(curPage, size);
			SalChanceExample example = new SalChanceExample();
			example.createCriteria().andChcCustNameLike("%" + chcCustName + "%")
			.andChcAddrLike("%" + chcAddr + "%");
			List<SalChance> list = chanceMapper.selectByExample(example);
			PageInfo<SalChance> pageInfo = new PageInfo<>(list);
			
		return pageInfo;
	}

	//保存或修改
	@Override
	public void saveOrUpdate(SalChance chance) {
		if(chance.getChcId() == null) {
			chanceMapper.insert(chance);
		} else {
			chanceMapper.updateByPrimaryKeySelective(chance);
		}
	}

	//根据id查询chance
	@Override
	public SalChance findChanceById(long chanceId) {
		SalChance chance = chanceMapper.selectByPrimaryKey(chanceId);
		return chance;
	}

	//删除
	@Override
	public void deleteChanceById(long chcId) {
		chanceMapper.deleteByPrimaryKey(chcId);
	}

	//根据userId查找SalChance
	@Override
	public PageInfo<SalChance> findChanceByUserId(int curPage, int size, String dueTo) {
		PageHelper.startPage(curPage, size);
		
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcDueToEqualTo(dueTo);
		List<SalChance> list = chanceMapper.selectByExample(example);
		
		PageInfo<SalChance> info = new PageInfo<>(list);
		return info;
	}

	//根据经理名和地址查找chance
	@Override
	public PageInfo<SalChance> findChanceByUserNameAndAddr(int curPage, int size, String dueTo, String addr) {
		
		
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcDueToEqualTo(dueTo).andChcAddrEqualTo(addr);
		List<SalChance> list = chanceMapper.selectByExample(example);
		System.out.println(list);
		PageHelper.startPage(curPage, size);
		PageInfo<SalChance> info = new PageInfo<>(list);
		
		return info;
	}

	//根据chanId查询chance，包括plans
	@Override
	public SalChanceExtend findChanceWithPlanWithId(long id) {
		SalChanceExtend salChanceExtend = chanceExtendMapper.selectChanWithPlanById(id);
		return salChanceExtend;
	}

}
