package ru.meow.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/")
	public String greeting(@RequestBody String name) {
		return "Greetings from Spring Boot! " + name + "!";
	}
}