package com.rkc.zds.dto;

import org.springframework.stereotype.Component;

@Component
public class CompanyDto {
    private AddressDto address;

    public CompanyDto(AddressDto address) {
        this.address = address;
    }

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
    
}
