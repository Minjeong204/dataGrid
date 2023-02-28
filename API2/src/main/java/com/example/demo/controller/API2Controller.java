package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MenuService;

@RestController
public class API2Controller {
	private final MenuService menuService;

	public API2Controller(MenuService menuService) {
		this.menuService = menuService;
	}

	@PostMapping("/table")
	public ResponseEntity<List<?>> table() {
		return new ResponseEntity<List<?>>(menuService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/regi")
	public ResponseEntity<?> page1(@RequestBody Map<String, String> map) {
		menuService.add(map);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<List<Map<String, String>>> delete(@RequestBody List<Map<String, String>> map) {
		menuService.delete(map);
		return new ResponseEntity<List<Map<String, String>>>(map, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody Map<String, String> map) {
		menuService.update(map);
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
}
