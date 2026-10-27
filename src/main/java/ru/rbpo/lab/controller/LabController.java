package ru.rbpo.lab.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class LabController {

	private final String applicationName;

	public LabController(@Value("${spring.application.name}") String applicationName) {
		this.applicationName = applicationName;
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(defaultValue = "World") String name) {
		return "Hello, " + name + "! Application: " + applicationName;
	}

	@GetMapping("/squares")
	public List<Integer> squares(@RequestParam(defaultValue = "5") int count) {
		if (count < 1 || count > 100) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "count must be between 1 and 100");
		}
		return IntStream.rangeClosed(1, count).map(i -> i * i).boxed().toList();
	}
}
