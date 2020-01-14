package com.briup.crm.dao;

import com.briup.crm.bean.CstCustomer;
import com.briup.crm.bean.CstCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CstCustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    long countByExample(CstCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int deleteByExample(CstCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int deleteByPrimaryKey(Long custId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int insert(CstCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int insertSelective(CstCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    List<CstCustomer> selectByExample(CstCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    CstCustomer selectByPrimaryKey(Long custId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int updateByExampleSelective(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int updateByExample(@Param("record") CstCustomer record, @Param("example") CstCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int updateByPrimaryKeySelective(CstCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cst_customer
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int updateByPrimaryKey(CstCustomer record);
}