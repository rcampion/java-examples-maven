package com.rkc.zds.jpa.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SHIPPING_METHOD")
public class ShippingMethodEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="method_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer methodId;

	@Column(name="method_name", nullable = true, length = 120)
	private String methodName;

	@Column(name="cost")
	private BigDecimal cost;

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}


