package com.pranav.learn.RESTWebServices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.learn.RESTWebServices.beans.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path = "/hw")
	public String helloWorld() {

		return "hello-world";
	}

	// Returning a bean
	@GetMapping(path = "/hw-bean")
	public HelloWorldBean helloWorldBean() {

		return new HelloWorldBean("hello-world");
	}

	// using pathVaribale
	
	@GetMapping(path = "/hw-bean/path-var/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {

		return new HelloWorldBean(String.format("hello-world, %s", name));
	}
	
	@GetMapping(path = "/hw-i18n")
	public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {

		return messageSource.getMessage("my.msg", null, locale);
	}
}
