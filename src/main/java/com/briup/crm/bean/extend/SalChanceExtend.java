package com.briup.crm.bean.extend;

import java.util.List;

import com.briup.crm.bean.SalChance;
import com.briup.crm.bean.SalPlan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商机扩展实体类")
public class SalChanceExtend extends SalChance{

    @ApiModelProperty(value = "商机计划",example = "")
	private List<SalPlan> plans;

	public List<SalPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<SalPlan> plans) {
		this.plans = plans;
	}
	
	
}
