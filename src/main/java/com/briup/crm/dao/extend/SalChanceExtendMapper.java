package com.briup.crm.dao.extend;

import com.briup.crm.bean.extend.SalChanceExtend;
import org.springframework.stereotype.Repository;

@Repository
public interface SalChanceExtendMapper {
	public SalChanceExtend selectChanWithPlanById(long chcId);
}
