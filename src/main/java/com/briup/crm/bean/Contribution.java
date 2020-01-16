package com.briup.crm.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户贡献实体")
public class Contribution {
	@ApiModelProperty(value = "用户名称",example = "")
	private String name;
	@ApiModelProperty(value = "贡献值",example = "0.5")
	private float y;
	@Override
	public String toString() {
		return "Contribution [name=" + name + ", y=" + y + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Contribution(String name, float y) {
		super();
		this.name = name;
		this.y = y;
	}
	public Contribution() {
		super();
	}
	
	
}
