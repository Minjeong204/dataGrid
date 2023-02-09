package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class UI_Controller {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	public ModelAndView intro(HttpServletResponse response) throws IOException {

		return new ModelAndView("index");

	}

	@GetMapping("/page1")
	public ModelAndView page1() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("page1");
		return mv;
	}

	@PostMapping("/page1/table")
	public ResponseEntity<Object> page1Table() {
		Object data = restTemplate.postForObject("/page1/table", null, Object.class);
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

}
