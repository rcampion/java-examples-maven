package com.rkc.zds.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ORDER_STATUS")
public class OrderStatusEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="status_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer statusId;

	@Column(name="status_value", nullable = true, length = 120)
	private String statusValue;

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}


}
