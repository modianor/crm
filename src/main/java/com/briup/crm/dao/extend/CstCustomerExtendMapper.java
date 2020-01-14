package com.briup.crm.dao.extend;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CstCustomerExtendMapper {
	public List<String> selectRegion();
	
	
}
