package com.jps.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jps.model.HelloWorldBean;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class HelloController {
	
	@RequestMapping("/hello-world")
	public String sayHello() {
		return "hello-world";
	}
	@RequestMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello-world");
	}

	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world , %s", name));
	}
	
//	@GetMapping("/hello-world/path-variable/{name}")
//	public RuntimeException helloWorldPError(@PathVariable String name) {
//		return new RuntimeException("some thing went wrong");
//	}
	
	
}
