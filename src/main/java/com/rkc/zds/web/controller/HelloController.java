package com.rkc.zds.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/hello")
public class HelloController {

	@RequestMapping(value = "")
	public String Hello() {
		return "Hello";
	}
}
