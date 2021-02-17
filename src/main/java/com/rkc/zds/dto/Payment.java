package com.rkc.zds.dto;

import java.util.Date;

public class Payment {
	
	private int id;
	private int userId;
	private double amount;
	private Date paymentDate;
	
	public Payment() {
		
	}
	
	public Payment(int userId, double amount, Date paymentDate) {
		this.userId = userId;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
		
}
