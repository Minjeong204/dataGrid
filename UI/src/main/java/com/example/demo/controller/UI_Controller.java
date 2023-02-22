package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	public ResponseEntity<Object> searchTable(@RequestBody String json) {
		System.out.println(json);
		Gson gson = new Gson();

		Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
		}.getType());

		Object data = restTemplate.postForObject("/page1/table", map, Object.class);
		return new ResponseEntity<Object> (data, HttpStatus.OK);
	}

	@PostMapping("/page1/regi")
	public ResponseEntity<Object> regiTable(@RequestBody String json) {

		Gson gson = new Gson();

		Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
		}.getType());

		ResponseEntity<String> data = restTemplate.postForEntity("/page1/regi", map, String.class);

		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	@PostMapping("/page1/delete")
	public ResponseEntity<Object> deleteTable(@RequestBody String json) {
		System.out.println("gggg");
		Gson gson = new Gson();

		List<Map<String, String>> map = gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {
		}.getType());

		ResponseEntity<String> data = restTemplate.postForEntity("/page1/delete", map, String.class);

		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

	/**
	 * @param json
	 * @return
	 */
	@PostMapping("/page1/update")
	public ResponseEntity<Object> updateTable(@RequestBody String json) {
		System.out.println("gggg");
		Gson gson = new Gson();

		Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
		}.getType());

		ResponseEntity<String> data = restTemplate.postForEntity("/page1/update", map, String.class);

		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}

}
