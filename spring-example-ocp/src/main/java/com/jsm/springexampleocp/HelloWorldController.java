package com.jsm.springexampleocp;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@RequestMapping(value="/hello/{name}", method=RequestMethod.GET)
	public String sayHello(@PathVariable("name") String name) {
		return "Hello " + name;
	}
}
