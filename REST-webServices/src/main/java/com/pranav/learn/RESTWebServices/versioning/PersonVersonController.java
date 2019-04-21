package com.pranav.learn.RESTWebServices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersonController {

	@GetMapping("/v1/person")
	public PersonV1 getV1Details() {
		return new PersonV1("Pranav Sharma");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getV2Details() {
		return new PersonV2(new Name("Pranav","Sharma"));
	}
	
	// the above are URI Versioning techniques
	
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Pranav Sharma");
	}
	
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Pranav","Sharma"));
	}
	
	// the above are request parameter Versioning techniques
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Pranav Sharma");
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Pranav","Sharma"));
	}
	
	//the above are request parameter Versioning techniques
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Pranav Sharma");
	}
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Pranav","Sharma"));
	}
}
