package com.briup.crm.dao;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalChanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalChanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    long countByExample(SalChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int deleteByExample(SalChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int deleteByPrimaryKey(Long chcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int insert(SalChance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int insertSelective(SalChance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    List<SalChance> selectByExample(SalChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    SalChance selectByPrimaryKey(Long chcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int updateByExampleSelective(@Param("record") SalChance record, @Param("example") SalChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int updateByExample(@Param("record") SalChance record, @Param("example") SalChanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int updateByPrimaryKeySelective(SalChance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sal_chance
     *
     * @mbg.generated Thu Jan 02 09:35:24 GMT+08:00 2020
     */
    int updateByPrimaryKey(SalChance record);
}