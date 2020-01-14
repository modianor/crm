package com.briup.crm.service;

import com.briup.crm.bean.CstLinkman;
import com.github.pagehelper.PageInfo;

public interface LinkmanService {

	//根据custId查询联系人，并且分页展示
	public PageInfo<CstLinkman> findLinkmanByCustId(long custId, int curPage, int size);
	
	//根据linkmanId删除联系人
	public void deleteLinkmanById(long linkmanId);
	
	//添加或保存linkman
	public void saveOrUpdate(CstLinkman linkman);
	
	//根据linkmanId查找联系人
	public CstLinkman findLinkmanById(Long id);
}
