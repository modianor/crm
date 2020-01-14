package com.briup.crm.service;

import com.briup.crm.bean.CstService;
import com.github.pagehelper.PageInfo;

public interface ServiceService {
    PageInfo<CstService> findAllServicesByDispose(String usrName, int curPage, int i);

    void saveOrUpdateService(CstService cstService);
}
