package com.rkc.zds.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="customer_id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customerId;

	@Size(min = 0, max = 45)
	@Column(name = "first_name", nullable = true, length = 120)
	private String firstName;
	
	@Size(min = 0, max = 45)
	@Column(name = "last_name", nullable = true, length = 120)
	private String lastName;

	@Size(min = 0, max = 45)
	@Column(name = "email", nullable = true, length = 120)
	private String email;
	
	@Column(name="contactId", nullable = true)
	private Integer contactId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

}
