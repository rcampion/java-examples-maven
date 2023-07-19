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
@Table(name = "ORDER_LINE")
public class OrderLineEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="line_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer lineId;

	@Column(name="order_id", nullable = true, length = 120)
	private Integer orderId;
	
	@Column(name="book_id", nullable = true, length = 120)
	private Integer bookId;

	@Column(name="price")
	private BigDecimal price;

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
