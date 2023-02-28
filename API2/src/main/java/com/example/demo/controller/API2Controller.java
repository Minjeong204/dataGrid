package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class API2Controller {
	@GetMapping("/page2")
	public ResponseEntity page1(@RequestBody String url) {
		String a = "page2";
		return new ResponseEntity(a, HttpStatus.OK);
	}
}
