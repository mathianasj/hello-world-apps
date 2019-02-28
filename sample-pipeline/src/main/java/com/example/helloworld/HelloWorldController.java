package com.example.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {
	@Value("${environment}")
	String env;
	
	@RequestMapping(method=RequestMethod.GET)
	public String sayHello() {
		return "Hello from Spring in environment " + env;
	}
}
