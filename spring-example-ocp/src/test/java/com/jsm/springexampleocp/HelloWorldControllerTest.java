package com.jsm.springexampleocp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloWorldControllerTest {
	@Test
	public void sayHello_withName_returnsSayHelloName() {
		HelloWorldController underTest = new HelloWorldController();
		
		String actual = underTest.sayHello("Joe");
		
		assertEquals("Hello Joe", actual);
	}
}
