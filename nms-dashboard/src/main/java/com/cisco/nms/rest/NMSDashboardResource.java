package com.cisco.nms.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NMSDashboardResource {

	@RequestMapping(value = "/test")
	public String testApp() {
		return "Hello World !!!";
	}

}
