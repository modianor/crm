package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.bean.CstLinkman;
import com.briup.crm.bean.CstLinkmanExample;
import com.briup.crm.dao.CstLinkmanMapper;
import com.briup.crm.service.LinkmanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class LinkmanServiceImpl implements LinkmanService{
	@Autowired
	private CstLinkmanMapper linkmanMapper;
	
	//根据custId查找联系人，分页
	@Override
	public PageInfo<CstLinkman> findLinkmanByCustId(long custId, int curPage, int size) {
		PageHelper.startPage(curPage, size);
		
		CstLinkmanExample example = new CstLinkmanExample();
		example.createCriteria().andLkmCustIdEqualTo(custId);
		List<CstLinkman> list = linkmanMapper.selectByExample(example);
		
		PageInfo<CstLinkman> linkmanInfo = new PageInfo<>(list);
		return linkmanInfo;
		
	}
	
	//根据linkmanId删除联系人
	@Override
	public void deleteLinkmanById(long linkmanId) {
		linkmanMapper.deleteByPrimaryKey(linkmanId);
	}

	//添加或保存linkman
	@Override
	public void saveOrUpdate(CstLinkman linkman) {
		if(linkman.getLkmId() == null) {
			linkmanMapper.insert(linkman);
		} else {
			CstLinkman linkman2 = linkmanMapper.selectByPrimaryKey(linkman.getLkmId());
			linkman.setLkmPostion(linkman2.getLkmPostion());
			linkmanMapper.updateByPrimaryKey(linkman);
		}
	}

	//根据linkmanId查找联系人
	@Override
	public CstLinkman findLinkmanById(Long id) {
		CstLinkman linkman = linkmanMapper.selectByPrimaryKey(id);
		return linkman;
	}

}
