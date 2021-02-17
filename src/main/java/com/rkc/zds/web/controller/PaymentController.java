package com.rkc.zds.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rkc.zds.dto.Payment;

@RestController
@RequestMapping(value = "/api/payments")
public class PaymentController {
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Payment> getPayments(@PathVariable int userId) {
		
        Date now = new java.util.Date();
        System.out.println("Value of java.util.Date : " + now);
     
		Payment one = new Payment(1, 10.00, now);
		
		List<Payment> list = new ArrayList<Payment>();
		
		list.add(one);
		
		return list;
	}
}
